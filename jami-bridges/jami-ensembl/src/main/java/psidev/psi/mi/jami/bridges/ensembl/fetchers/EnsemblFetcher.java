package psidev.psi.mi.jami.bridges.ensembl.fetchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.InteractorFetcher;
import psidev.psi.mi.jami.bridges.ols.CachedOlsCvTermFetcher;
import psidev.psi.mi.jami.bridges.ols.CachedOlsOntologyTermFetcher;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.*;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EnsemblFetcher implements InteractorFetcher<Interactor> {

    public static final Pattern identifierPattern = Pattern.compile("^((ENS[FPTGE]\\d{11}(\\.\\d+)?)|(FB\\w{2}\\d{7})|(Y[A-Z]{2}\\d{3}[a-zA-Z](-[A-Z])?))$");
    public static final String GET_SUFFIX = "%s?content-type=application/json";
    public static final String MAIN_URL = "https://rest.ensembl.org/lookup/id";
    public static final String SEQUENCE_URL = "https://rest.ensembl.org/sequence/id/";
    public static final String UNIPROT_URL = "https://rest.ensembl.org/xrefs/id/" + GET_SUFFIX + ";external_db=Uniprot_gn";
    public static final String MI_ONTOLOGY_NAME = "psi-mi";

    private static final Map<String, CvTerm> biotypeToCVId = new HashMap<>(Map.of(
            "protein_coding", new DefaultCvTerm("messenger rna", "MI:0324"), // mRNA
            "protein_coding_CDS_not_defined", new DefaultCvTerm("long non-coding ribonucleic acid", "MI:2190"), // lncRNA
            "lncRNA", new DefaultCvTerm("long non-coding ribonucleic acid", "MI:2190"), // lncRNA
            "miRNA", new DefaultCvTerm("micro rna", "MI:2204"), // miRNA
            "snRNA", new DefaultCvTerm("small nuclear rna", "MI:0607") // snRNA
    ));

    public final CvTerm ensemblCV = new DefaultCvTerm("ensembl", "MI:0476");
    public final CvTerm uniprotCV = new DefaultCvTerm("uniprot knowledge base", "MI:0486");
    public final CvTerm rnaCV = new DefaultCvTerm("ribonucleic acid", "MI:0320");
    public final CvTerm identityCv = new DefaultCvTerm(Xref.IDENTITY, Xref.IDENTITY_MI);
    public final CvTerm geneCV = new DefaultCvTerm("gene", "MI:0250");
    public final CvTerm geneNameCV = new DefaultCvTerm("gene name", "MI:0301");
    public final CvTerm geneProductCV = new DefaultCvTerm("gene product", "MI:0251");
    public final CvTerm transcriptCV = new DefaultCvTerm("transcript", "IA:3522"); // TODO change to new MI ID

    public final static Set<ObjectType> supportedTypes = Set.of(ObjectType.GENE, ObjectType.TRANSCRIPT);
    private final ObjectMapper mapper = JsonMapper.builder()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
            .build();

    private final HttpClient client = HttpClient.newHttpClient();
    private final CachedOlsOntologyTermFetcher ontologyFetcher = new CachedOlsOntologyTermFetcher();
    private final CachedOlsCvTermFetcher cvFetcher = new CachedOlsCvTermFetcher();
    private final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json");

    public EnsemblFetcher() throws BridgeFailedException {
    }

    @Override
    public Collection<Interactor> fetchByIdentifiers(Collection<String> identifiers) throws BridgeFailedException {
        try {
            List<String> ids = identifiers.stream()
                    .filter(Objects::nonNull)
                    .filter(id -> identifierPattern.matcher(id).find())
                    .map(id -> id.split("\\.")[0])
                    .collect(Collectors.toList());

            Map<String, Interactor> idToInteractor = new HashMap<>();

            queryInteractors(ids, idToInteractor);

            List<String> transcriptIds = idToInteractor.entrySet().stream().filter(entry -> entry.getValue() instanceof NucleicAcid).map(Map.Entry::getKey).collect(Collectors.toList());

            querySequences(transcriptIds, idToInteractor);

            List<String> geneIds = idToInteractor.entrySet().stream().filter(entry -> entry.getValue() instanceof Gene).map(Map.Entry::getKey).collect(Collectors.toList());

            queryUniprotXrefs(geneIds, idToInteractor);

            return idToInteractor.values();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BridgeFailedException(e.getMessage());
        }
    }

    private void queryInteractors(List<String> ids, Map<String, Interactor> output) throws IOException, InterruptedException {
        this.postRequest(ids, MAIN_URL, new TypeReference<Map<String, ApiObject>>() {
                }, idToEntree -> output.putAll(idToEntree.entrySet().stream()
                        .filter(entry -> entry.getValue() != null)
                        .filter(entry -> supportedTypes.contains(entry.getValue().getObjectType()))
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> EnsemblFetcher.this.buildInteractor(entry.getKey(), entry.getValue()))))
        );
    }

    private void querySequences(List<String> ids, Map<String, Interactor> idToInteractor) throws IOException, InterruptedException {
        this.postRequest(ids, SEQUENCE_URL, new TypeReference<List<ApiSequence>>() {
                }, sequences -> sequences.forEach(
                        apiSequence -> ((NucleicAcid) idToInteractor.get(apiSequence.getId()))
                                .setSequence(apiSequence.getSeq()))
        );
    }

    private void queryUniprotXrefs(List<String> ids, Map<String, Interactor> idToInteractor) throws IOException {
        if (ids == null || ids.isEmpty()) return;
        for (String id : ids) {
            URL uniprotURL = new URL(String.format(UNIPROT_URL, id));
            Collection<Xref> xrefs = idToInteractor.get(id).getXrefs();
            Arrays.stream(mapper.readValue(uniprotURL, ApiXref[].class))
                    .forEach(apiXref -> xrefs.add(
                            new DefaultXref(uniprotCV, apiXref.getPrimaryId(), geneProductCV)
                    ));
        }
    }

    private <R> void postRequest(List<String> ids, String url, TypeReference<R> returnType, Consumer<R> consumer) throws IOException, InterruptedException {
        if (ids == null || ids.isEmpty()) return;
        HttpRequest request = requestBuilder
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(Map.of("ids", ids))))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        consumer.accept(mapper.readValue(response.body(), returnType));
    }

    private Interactor buildInteractor(String identifier, ApiObject entree) {
        DefaultXref id = new DefaultXref(ensemblCV, identifier, identityCv);
        OntologyTerm speciesTaxon = getTermByName(entree.getSpecies().replace("_", " "), "ncbi taxonomy");

        int taxId = Integer.parseInt(speciesTaxon.getIdentifiers().iterator().next().getId().split(":")[1]);
        Organism organism = new DefaultOrganism(taxId, speciesTaxon.getShortName());
        String shortSpecies = speciesTaxon.getSynonyms().stream().map(Alias::getName).findFirst().orElse("");


        Interactor interactor = null;
        String name, fullName;

        switch (entree.getObjectType()) {
            case GENE:
                name = MessageFormat.format("{0} {1} gene", entree.getDisplayName(), shortSpecies);
                fullName = extractPureIdentifier(entree.getDescription());
                DefaultGene gene = new DefaultGene(name, fullName, organism, id);

                gene.setEnsembl(identifier);
                gene.getAliases().add(new DefaultAlias(geneNameCV, entree.getDisplayName()));
                gene.getXrefs().add(new DefaultXref(ensemblCV, extractPureIdentifier(entree.getCanonicalTranscript()), transcriptCV));

                interactor = gene;
                break;
            case TRANSCRIPT:
                CvTerm type = biotypeToCVId.computeIfAbsent(entree.getBiotype(), s -> getCVByName(entree.getBiotype(), rnaCV));
                String geneName = entree.getDisplayName().split("-")[0];
                name = MessageFormat.format("{0} {1} transcript", geneName, shortSpecies);
                fullName = entree.getDisplayName();

                DefaultNucleicAcid nucleicAcid = new DefaultNucleicAcid(name, fullName, type, organism, id);

                nucleicAcid.getXrefs().add(new DefaultXref(ensemblCV, entree.getParentId(), geneCV));
                nucleicAcid.getAliases().add(new DefaultAlias(geneNameCV, geneName));

                interactor = nucleicAcid;
            case TRANSLATION:
            case EXON:
                break;
        }
        return interactor;
    }

    private String extractPureIdentifier(String id) {
        return id.split("\\.")[0];
    }

    private CvTerm getCVByMIId(String id) {
        if (id == null) return null;
        try {
            return ontologyFetcher.fetchByIdentifier(id, MI_ONTOLOGY_NAME);
        } catch (BridgeFailedException e) {
            throw new RuntimeException(e);
        }
    }

    private CvTerm getCVByMIId(String id, CvTerm defaultValue) {
        CvTerm cv = getCVByMIId(id);
        return cv != null ? cv : defaultValue;
    }

    private CvTerm getCVByName(String name) {
        if (name == null) return null;
        try {
            return cvFetcher.fetchByName(name, MI_ONTOLOGY_NAME);
        } catch (BridgeFailedException e) {
            throw new RuntimeException(e);
        }
    }

    private OntologyTerm getTermByName(String name, String ontologyId) {
        if (name == null) return null;
        try {
            return ontologyFetcher.fetchByName(name, ontologyId);
        } catch (BridgeFailedException e) {
            throw new RuntimeException(e);
        }
    }

    private CvTerm getCVByName(String name, CvTerm defaultValue) {
        CvTerm cv = getCVByName(name);
        return cv != null ? cv : defaultValue;
    }
}

package psidev.psi.mi.jami.bridges.ensembl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.InteractorFetcher;
import psidev.psi.mi.jami.bridges.ols.CachedOlsCvTermFetcher;
import psidev.psi.mi.jami.bridges.ols.CachedOlsOntologyTermFetcher;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Gene;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.NucleicAcid;
import psidev.psi.mi.jami.model.OntologyTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultAlias;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.model.impl.DefaultGene;
import psidev.psi.mi.jami.model.impl.DefaultNucleicAcid;
import psidev.psi.mi.jami.model.impl.DefaultOrganism;
import psidev.psi.mi.jami.model.impl.DefaultXref;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractEnsemblFetcher<T extends Interactor> implements InteractorFetcher<T> {

    public static final Pattern identifierPattern = Pattern.compile("^((ENS[FPTGE]\\d{11}(\\.\\d+)?)|(FB\\w{2}\\d{7})|(Y[A-Z]{2}\\d{3}[a-zA-Z](-[A-Z])?))$");
    public static final String GET_SUFFIX = "%s?content-type=application/json";
    public static final String MAIN_URL = "https://rest.ensembl.org/lookup/id";
    public static final String SEQUENCE_URL = "https://rest.ensembl.org/sequence/id/";
    public static final String UNIPROT_URL = "https://rest.ensembl.org/xrefs/id/" + GET_SUFFIX;
    public static final String MI_ONTOLOGY_NAME = "psi-mi";

    private final CachedOlsOntologyTermFetcher ontologyFetcher = new CachedOlsOntologyTermFetcher();

    private final Map<String, CvTerm> biotypeToCVId = this.buildInitialBioTypeMap();

    private final Map<String, String> biotypeToShortestType = new HashMap<>();

    public final CvTerm intactCV = new DefaultCvTerm("intact", "MI:0469");
    public final CvTerm ensemblCV = new DefaultCvTerm("ensembl", "MI:0476");
    public final CvTerm uniprotCV = new DefaultCvTerm("uniprot knowledge base", "MI:0486");
    public final CvTerm rnaCV = new DefaultCvTerm("ribonucleic acid", "MI:0320");
    public final CvTerm identityCv = new DefaultCvTerm(Xref.IDENTITY, Xref.IDENTITY_MI);
    public final CvTerm geneCV = new DefaultCvTerm("gene reference", "MI:2423");
    public final CvTerm geneNameCV = new DefaultCvTerm("gene name", "MI:0301");
    public final CvTerm geneProductCV = new DefaultCvTerm("gene product", "MI:0251");
    public final CvTerm transcriptCV = new DefaultCvTerm("transcript", new DefaultXref(intactCV, "IA:3522", identityCv)); // TODO change to new MI ID

    private final Logger logger = LoggerFactory.getLogger(AbstractEnsemblFetcher.class);

    public final static Set<ObjectType> supportedTypes = Set.of(ObjectType.GENE, ObjectType.TRANSCRIPT);
    private final ObjectMapper mapper = JsonMapper.builder()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
            .build();

    private final HttpClient.Builder clientBuilder = HttpClient.newBuilder();
    private final CachedOlsCvTermFetcher cvFetcher = new CachedOlsCvTermFetcher();
    private final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json");


    public AbstractEnsemblFetcher() throws BridgeFailedException {
    }

    @Override
    public Collection<T> fetchByIdentifiers(Collection<String> identifiers) throws BridgeFailedException {
        try {
            List<String> ids = identifiers.stream()
                    .filter(Objects::nonNull)
                    .filter(id -> identifierPattern.matcher(id).find())
                    .map(id -> id.split("\\.")[0])
                    .collect(Collectors.toList());


            Map<String, T> idToInteractor = new HashMap<>();
            Map<String, Interactor> translationIdToInteractor = new HashMap<>();

            queryInteractors(ids, idToInteractor, translationIdToInteractor);
            logger.info("Fetched {} from Ensembl", idToInteractor);

            List<String> transcriptIds = idToInteractor.entrySet().stream().filter(entry -> entry.getValue() instanceof NucleicAcid).map(Map.Entry::getKey).collect(Collectors.toList());
            logger.info("Fetching sequences for {} transcripts", transcriptIds);
            querySequences(transcriptIds, idToInteractor);
            logger.info("Fetching transcript uniprot id");
            queryUniprotXrefs(translationIdToInteractor);

            return idToInteractor.values();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BridgeFailedException(e.getMessage());
        }
    }

    private void queryInteractors(List<String> ids, Map<String, T> output, Map<String, Interactor> translationIdToInteractor) throws IOException, InterruptedException {
        this.postRequest(ids, MAIN_URL, new HashMap<>(Map.of("expand", 1)), new TypeReference<Map<String, ApiObject>>() {
                }, idToEntree -> output.putAll(idToEntree.entrySet().stream()
                        .filter(entry -> entry.getValue() != null)
                        .filter(entry -> supportedTypes.contains(entry.getValue().getObjectType()))
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> buildInteractor(entry.getValue(), translationIdToInteractor))))
        );
    }

    private void querySequences(List<String> ids, Map<String, T> idToInteractor) throws IOException, InterruptedException {
        this.postRequest(ids, SEQUENCE_URL, new HashMap<>(Map.of("type", "cdna")), new TypeReference<List<ApiSequence>>() {
                }, sequences -> sequences.forEach(
                        apiSequence -> ((NucleicAcid) idToInteractor.get(apiSequence.getQuery()))
                                .setSequence(apiSequence.getSeq()))
        );
    }

    private void queryUniprotXrefs(Map<String, Interactor> translationIdToInteractor) throws IOException {
        for (String id : translationIdToInteractor.keySet()) {
            URL uniprotURL = new URL(String.format(UNIPROT_URL, id));
            Collection<Xref> xrefs = translationIdToInteractor.get(id).getXrefs();
            Arrays.stream(mapper.readValue(uniprotURL, ApiXref[].class))
                    .filter(apiXref -> apiXref.getDbname().toUpperCase().contains("UNIPROT"))
                    .distinct()
                    .forEach(apiXref -> xrefs.add(
                            new DefaultXref(uniprotCV, apiXref.getPrimaryId(), geneProductCV)
                    ));
        }
    }

    private <R> void postRequest(List<String> ids, String url, Map<String, Object> extraParams, TypeReference<R> returnType, Consumer<R> consumer) throws IOException, InterruptedException {
        if (ids == null || ids.isEmpty()) return;
        extraParams.put("ids", ids);
        HttpRequest request = requestBuilder
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(extraParams)))
                .build();

        HttpResponse<String> response = clientBuilder.version(HttpClient.Version.HTTP_1_1)
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        consumer.accept(mapper.readValue(response.body(), returnType));
    }

    protected abstract T buildInteractor(ApiObject entree, Map<String, Interactor> translationIdToInteractor);

    protected Gene buildGeneInteractor(ApiObject entree, Map<String, Interactor> translationIdToInteractor) {
        // entree.id is the one queried, so potentially in the wrong case
        String idValue = Stream.of(entree.getTranscripts())
                .flatMap(List::stream)
                .map(ApiObject::getParentId) // so taking the parent of the first child
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(entree.getId());
        DefaultXref id = new DefaultXref(ensemblCV, idValue, identityCv);

        OntologyTerm speciesTaxon = getTermByName(entree.getSpecies().replace("_", " "), "ncbi taxonomy");

        int taxId = Integer.parseInt(speciesTaxon.getIdentifiers().iterator().next().getId().split(":")[1]);
        Organism organism = new DefaultOrganism(taxId, speciesTaxon.getShortName());

        String name, fullName;

        name = entree.getDisplayName();
        fullName = extractPureIdentifier(entree.getDescription());
        DefaultGene gene = new DefaultGene(name, fullName, organism, id);

        gene.setEnsembl(entree.getId());
        gene.getAliases().add(new DefaultAlias(geneNameCV, entree.getDisplayName()));
        gene.getXrefs().add(new DefaultXref(ensemblCV, extractPureIdentifier(entree.getCanonicalTranscript()), transcriptCV));


        Stream.of(entree.getTranscripts()) // Transcripts
                .filter(Objects::nonNull)
                .flatMap(List::stream)

                .filter(ApiObject::isCanonical) // Canonical transcripts (normally 1)
                .map(ApiObject::getTranslations) // Translations (normally 1)
                .filter(Objects::nonNull)
                .flatMap(List::stream)

                .map(ApiObject::getId) // Translation Ids
                .forEach(translationId -> translationIdToInteractor.put(translationId, gene));

        return gene;
    }

    protected NucleicAcid buildNucleicAcid(ApiObject entree, Map<String, Interactor> translationIdToInteractor) {
        DefaultXref id = new DefaultXref(ensemblCV, ensureIdCase(entree, e -> Stream.concat(e.getTranslations().stream(), e.getExons().stream()).collect(Collectors.toList())), identityCv);
        OntologyTerm speciesTaxon = getTermByName(entree.getSpecies().replace("_", " "), "ncbi taxonomy");

        int taxId = Integer.parseInt(speciesTaxon.getIdentifiers().iterator().next().getId().split(":")[1]);
        Organism organism = new DefaultOrganism(taxId, speciesTaxon.getShortName());

        String name, fullName;

        CvTerm type = biotypeToCVId.computeIfAbsent(entree.getBiotype(), s -> getCVByName(entree.getBiotype(), rnaCV));
        String shortType = biotypeToShortestType.computeIfAbsent(entree.getBiotype(), biotype -> getShortestSynonym(type));
        String geneName = entree.getDisplayName().split("-")[0];
        name = MessageFormat.format("{0}_{1}", shortType, geneName);
        fullName = entree.getDisplayName();

        DefaultNucleicAcid nucleicAcid = new DefaultNucleicAcid(name, fullName, type, organism, id);

        nucleicAcid.getXrefs().add(new DefaultXref(ensemblCV, entree.getParentId(), geneCV));
        nucleicAcid.getAliases().add(new DefaultAlias(geneNameCV, geneName));

        Stream.of(entree.getTranslations()) // Translations (normally 1)
                .filter(Objects::nonNull)
                .flatMap(List::stream)

                .map(ApiObject::getId) // Translation Ids
                .forEach(translationId -> translationIdToInteractor.put(translationId, nucleicAcid));

        return nucleicAcid;
    }

    private String ensureIdCase(ApiObject entree, Function<ApiObject, List<ApiObject>> childrenAccessor) {
        // entree.id is the one queried, so potentially in the wrong case
        return Stream.of(childrenAccessor.apply(entree))
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .map(ApiObject::getParentId) // so taking the parent of the first child
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(entree.getId()); // Default back to entree.id if no children
    }

    private String extractPureIdentifier(String id) {
        return id.split("\\.")[0];
    }

    private Map<String, CvTerm> buildInitialBioTypeMap() {
        Map<String, CvTerm> map = new HashMap<>();
        map.computeIfAbsent("protein_coding", k -> this.getCVByMIId("MI:0324")); // mRNA
        map.computeIfAbsent("protein_coding_CDS_not_defined", k -> this.getCVByMIId("MI:2190")); // lncRNA
        map.computeIfAbsent("lncRNA", k -> this.getCVByMIId("MI:2190")); // lncRNA
        map.computeIfAbsent("miRNA", k -> this.getCVByMIId("MI:2204")); // miRNA
        map.computeIfAbsent("snRNA", k -> this.getCVByMIId("MI:0607")); // snRNA
        return map;
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

    private String getShortestSynonym(CvTerm term) {
        return Stream.concat(Stream.of(term.getShortName()), term.getSynonyms().stream().map(Alias::getName))
                .min((s1, s2) -> {
                    int diff = s1.length() - s2.length();
                    if (diff != 0) return diff;
                    if (s1.equals(s2)) return 0;
                    return s1.chars().anyMatch(Character::isUpperCase) ? -1 : 1;
                }).orElse(term.getShortName());
    }

}
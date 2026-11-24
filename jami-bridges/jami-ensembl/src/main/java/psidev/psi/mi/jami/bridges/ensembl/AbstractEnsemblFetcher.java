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
import psidev.psi.mi.jami.utils.CvTermUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.ArrayList;
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
    public final CvTerm dnaCV = new DefaultCvTerm(NucleicAcid.DNA, NucleicAcid.DNA_MI);
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
            Map<String, List<Interactor>> translationIdToInteractors = new HashMap<>();

            queryInteractors(ids, idToInteractor, translationIdToInteractors);
            logger.info("Fetched {} from Ensembl", idToInteractor);

            Map<String, List<String>> sequenceIdsByType = idToInteractor.entrySet().stream()
                    .filter(entry -> entry.getValue() instanceof NucleicAcid)
                    .collect(Collectors.groupingBy(entry -> CvTermUtils.isCvTerm(entry.getValue().getInteractorType(),
                                    NucleicAcid.DNA_MI, NucleicAcid.DNA) ? "genomic" : "cdna",
                            Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
            for (Map.Entry<String, List<String>> entry : sequenceIdsByType.entrySet()) {
                logger.info("Fetching {} sequences for {}", entry.getKey(), entry.getValue());
                querySequences(entry.getValue(), idToInteractor, entry.getKey());
            }
            logger.info("Fetching transcript uniprot id");
            queryUniprotXrefs(translationIdToInteractors);

            return idToInteractor.values();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BridgeFailedException("Could not fetch Ensembl identifiers " + identifiers, e);
        }
    }

    private void queryInteractors(List<String> ids, Map<String, T> output, Map<String, List<Interactor>> translationIdToInteractors) throws IOException, InterruptedException {
        this.postRequest(ids, MAIN_URL, new HashMap<>(Map.of("expand", 1)), new TypeReference<Map<String, ApiObject>>() {
                }, idToEntree -> output.putAll(idToEntree.entrySet().stream()
                        .filter(entry -> entry.getValue() != null)
                        .filter(entry -> supportedTypes.contains(entry.getValue().getObjectType()))
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> buildInteractor(entry.getValue(), translationIdToInteractors))))
        );
    }

    private void querySequences(List<String> ids, Map<String, T> idToInteractor, String sequenceType) throws IOException, InterruptedException {
        this.postRequest(ids, SEQUENCE_URL, new HashMap<>(Map.of("type", sequenceType)), new TypeReference<List<ApiSequence>>() {
                }, sequences -> sequences.forEach(
                        apiSequence -> ((NucleicAcid) idToInteractor.get(apiSequence.getQuery()))
                                .setSequence(apiSequence.getSeq()))
        );
    }

    private void queryUniprotXrefs(Map<String, List<Interactor>> translationIdToInteractors) throws IOException {
        for (Map.Entry<String, List<Interactor>> entry : translationIdToInteractors.entrySet()) {
            URL uniprotURL = new URL(String.format(UNIPROT_URL, entry.getKey()));
            List<ApiXref> uniprotXrefs = Arrays.stream(mapper.readValue(uniprotURL, ApiXref[].class))
                    .filter(apiXref -> apiXref.getDbname().toUpperCase().contains("UNIPROT"))
                    .distinct()
                    .collect(Collectors.toList());
            // A translation can be shared, e.g. by a gene and its canonical transcript fetched together
            for (Interactor interactor : entry.getValue()) {
                uniprotXrefs.forEach(apiXref -> interactor.getXrefs().add(
                        new DefaultXref(uniprotCV, apiXref.getPrimaryId(), geneProductCV)
                ));
            }
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

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("Ensembl request to " + url + " failed with HTTP " + response.statusCode());
        }

        consumer.accept(mapper.readValue(response.body(), returnType));
    }

    protected abstract T buildInteractor(ApiObject entree, Map<String, List<Interactor>> translationIdToInteractors);

    protected Gene buildGeneInteractor(ApiObject entree, Map<String, List<Interactor>> translationIdToInteractors) {
        DefaultXref id = new DefaultXref(ensemblCV, ensureIdCase(entree, ApiObject::getTranscripts), identityCv);
        DefaultGene gene = new DefaultGene(entree.getDisplayName(), extractPureIdentifier(entree.getDescription()), buildOrganism(entree), id);

        gene.setEnsembl(entree.getId());
        gene.getAliases().add(new DefaultAlias(geneNameCV, entree.getDisplayName()));
        addCanonicalTranscriptXref(gene, entree);
        registerTranslations(canonicalTranslations(entree), gene, translationIdToInteractors);

        return gene;
    }

    protected NucleicAcid buildNucleicAcid(ApiObject entree, Map<String, List<Interactor>> translationIdToInteractors) {
        return entree.getObjectType() == ObjectType.GENE
                ? buildGenomicDna(entree, translationIdToInteractors)
                : buildTranscript(entree, translationIdToInteractors);
    }

    private NucleicAcid buildGenomicDna(ApiObject entree, Map<String, List<Interactor>> translationIdToInteractors) {
        String idValue = ensureIdCase(entree, ApiObject::getTranscripts);
        String geneName = entree.getDisplayName() != null ? entree.getDisplayName() : idValue;
        DefaultNucleicAcid dna = new DefaultNucleicAcid(MessageFormat.format("{0}_{1}", NucleicAcid.DNA, geneName),
                entree.getDescription(), dnaCV, buildOrganism(entree), new DefaultXref(ensemblCV, idValue, identityCv));

        dna.getAliases().add(new DefaultAlias(geneNameCV, geneName));
        addCanonicalTranscriptXref(dna, entree);
        registerTranslations(canonicalTranslations(entree), dna, translationIdToInteractors);

        return dna;
    }

    private NucleicAcid buildTranscript(ApiObject entree, Map<String, List<Interactor>> translationIdToInteractors) {
        String idValue = ensureIdCase(entree, e -> Stream.concat(children(e.getTranslations()), children(e.getExons())).collect(Collectors.toList()));
        String displayName = entree.getDisplayName() != null ? entree.getDisplayName() : idValue;
        String geneName = displayName.split("-")[0];
        CvTerm type = biotypeToCVId.computeIfAbsent(entree.getBiotype(), biotype -> getCVByName(biotype, rnaCV));
        String shortType = biotypeToShortestType.computeIfAbsent(entree.getBiotype(), biotype -> getShortestSynonym(type));
        DefaultNucleicAcid transcript = new DefaultNucleicAcid(MessageFormat.format("{0}_{1}", shortType, geneName),
                displayName, type, buildOrganism(entree), new DefaultXref(ensemblCV, idValue, identityCv));

        transcript.getAliases().add(new DefaultAlias(geneNameCV, geneName));
        if (entree.getParentId() != null) {
            transcript.getXrefs().add(new DefaultXref(ensemblCV, entree.getParentId(), geneCV));
        }
        registerTranslations(children(entree.getTranslations()), transcript, translationIdToInteractors);

        return transcript;
    }

    private Organism buildOrganism(ApiObject entree) {
        OntologyTerm speciesTaxon = getTermByName(entree.getSpecies().replace("_", " "), "ncbi taxonomy");
        int taxId = Integer.parseInt(speciesTaxon.getIdentifiers().iterator().next().getId().split(":")[1]);
        return new DefaultOrganism(taxId, speciesTaxon.getShortName());
    }

    private void addCanonicalTranscriptXref(Interactor interactor, ApiObject gene) {
        if (gene.getCanonicalTranscript() != null) {
            interactor.getXrefs().add(new DefaultXref(ensemblCV, extractPureIdentifier(gene.getCanonicalTranscript()), transcriptCV));
        }
    }

    private Stream<ApiObject> canonicalTranslations(ApiObject gene) {
        return children(gene.getTranscripts())
                .filter(ApiObject::isCanonical) // Canonical transcripts (normally 1)
                .flatMap(transcript -> children(transcript.getTranslations())); // Translations (normally 1)
    }

    private void registerTranslations(Stream<ApiObject> translations, Interactor interactor, Map<String, List<Interactor>> translationIdToInteractors) {
        translations.map(ApiObject::getId)
                .forEach(translationId -> translationIdToInteractors.computeIfAbsent(translationId, k -> new ArrayList<>()).add(interactor));
    }

    private static Stream<ApiObject> children(List<ApiObject> children) {
        return children != null ? children.stream() : Stream.empty();
    }

    private String ensureIdCase(ApiObject entree, Function<ApiObject, List<ApiObject>> childrenAccessor) {
        // entree.id is the one queried, so potentially in the wrong case
        return children(childrenAccessor.apply(entree))
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

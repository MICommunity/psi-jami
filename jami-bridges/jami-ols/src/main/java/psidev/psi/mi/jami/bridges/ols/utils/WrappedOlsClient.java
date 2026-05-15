package psidev.psi.mi.jami.bridges.ols.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.client.RestClientException;
import uk.ac.ebi.pride.utilities.ols.web.service.client.OLSClient;
import uk.ac.ebi.pride.utilities.ols.web.service.config.AbstractOLSWsConfig;
import uk.ac.ebi.pride.utilities.ols.web.service.model.Href;
import uk.ac.ebi.pride.utilities.ols.web.service.model.Identifier;
import uk.ac.ebi.pride.utilities.ols.web.service.model.Term;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WrappedOlsClient extends OLSClient {

    private final ObjectMapper mapper;

    public WrappedOlsClient(AbstractOLSWsConfig config) {
        super(config);
        this.mapper = (new ObjectMapper()).configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    @Override
    public Term getTermByOBOId(String termOBOId, String ontologyId) throws RestClientException {
        String query = String.format("obo_id=%s", termOBOId);
        URI uri = this.encodeURL("/api/ontologies/" + ontologyId + "/terms", query);
        JamiOlsTermQuery result = this.getForObject(uri, JamiOlsTermQuery.class);
        return result != null && result.getTerms() != null && result.getTerms().length == 1 ? result.getTerms()[0] : null;
    }

    @Override
    public Term getTermByShortName(String shortForm, String ontologyId) throws RestClientException {
        String query = String.format("short_form=%s", shortForm);
        URI uri = this.encodeURL("/api/ontologies/" + ontologyId + "/terms", query);
        JamiOlsTermQuery result = this.getForObject(uri, JamiOlsTermQuery.class);
        return result != null && result.getTerms() != null && result.getTerms().length == 1 ? result.getTerms()[0] : null;
    }

    @Override
    public Term getTermByIRIId(String iriId, String ontologyId) throws RestClientException {
        String query = String.format("iri=%s", iriId);
        URI uri = this.encodeURL("/api/ontologies/" + ontologyId + "/terms", query);
        JamiOlsTermQuery result = this.getForObject(uri, JamiOlsTermQuery.class);
        return result != null && result.getTerms() != null && result.getTerms().length == 1 ? result.getTerms()[0] : null;
    }

    @Override
    public List<Term> getAllTermsFromOntology(String ontologyID) throws RestClientException {
        return this.getAllOBOTermsFromOntology(ontologyID);
    }

    @Override
    public List<Term> getTermsByAnnotationData(String ontologyID, String annotationType, double fromDblValue, double toDblValue) {
        List<Term> terms = this.getAllOBOTermsFromOntology(ontologyID);
        List<Term> termResult = new ArrayList<>();

        for(Term term : terms) {
            if (term != null && term.getAnnotation() != null && term.getAnnotation().containsAnnotation(annotationType)) {
                List<String> termValues = term.getAnnotation().getAnnotation(annotationType);
                if (termValues != null && !termValues.isEmpty() && NumberUtils.isNumber(termValues.get(0))) {
                    double value = Double.parseDouble(termValues.get(0));
                    if (value >= fromDblValue && value <= toDblValue) {
                        termResult.add(term);
                    }
                }
            }
        }

        return termResult;
    }

    @Override
    public List<Term> getTermChildren(Identifier termId, String ontologyId, int distance) throws RestClientException {
        List<Term> terms = new ArrayList<>();
        Term term = this.getTermById(termId, ontologyId);
        if (term != null && term.getLink() != null && term.getLink().getAllChildrenRef() != null) {
            terms = this.getTermChildrenMap(term.getLink().getAllChildrenRef(), distance);
        }

        return terms;
    }

    @Override
    public List<Term> getTermParents(Identifier termId, String ontologyId, int distance) throws RestClientException {
        List<Term> terms = new ArrayList<>();
        Term term = this.getTermById(termId, ontologyId);
        if (term != null && term.getLink() != null && term.getLink().getAllParentsRef() != null) {
            terms = this.getTermParentsMap(term.getLink().getAllParentsRef(), distance);
        }

        return terms;
    }

    @Override
    public List<Term> getRootTerms(String ontologyID) {
        return this.getAllRootTerns(ontologyID);
    }

    @Override
    public Map getMetaData(Identifier identifier, String ontologyId) {
        HashMap<String, Object> metaData = new HashMap<>();
        Map<String, String> synonym = this.getOBOSynonyms(identifier, ontologyId) == null
                ? Collections.emptyMap()
                : this.getOBOSynonyms(identifier, ontologyId);

        JamiOlsTerm term = (JamiOlsTerm) this.getTermById(identifier, ontologyId);
        String definition = this.getDefinition(term);
        String comment = this.getComment(identifier, ontologyId);
        if (synonym != null && !synonym.isEmpty()) {
            metaData.put("synonym", synonym);
        }

        if (definition != null && !definition.isEmpty()) {
            metaData.put("definition", definition);
        }

        if (comment != null && !comment.isEmpty()) {
            metaData.put("comment", comment);
        }

        return metaData.isEmpty() ? new HashMap<>() : metaData;
    }

    private String getDefinition(JamiOlsTerm term) {
        if (term != null) {
            if (term.getOboDefinitionCitation() != null) {
                for (JamiOlsOboDefinitionCitation citation : term.getOboDefinitionCitation()) {
                    if (citation.getDefinition() != null && !citation.getDefinition().isEmpty()) {
                        return citation.getDefinition();
                    }
                }
            }
            if (term.getDescription() != null && term.getDescription().length > 0) {
                return term.getDescription()[0];
            } else if (term.getAnnotation() != null && term.getAnnotation().containsAnnotation("definition")) {
                return term.getAnnotation().getAnnotation("definition").get(0);
            }
        }
        return null;
    }

    private List<Term> getAllOBOTermsFromOntology(String ontologyID) throws RestClientException {
        JamiOlsTermQuery currentTermQuery = this.getTermQuery(0, ontologyID);
        List<Term> terms = new ArrayList<>();
        if (currentTermQuery != null && currentTermQuery.getTerms() != null) {
            terms.addAll(Arrays.asList(currentTermQuery.getTerms()));
            if (currentTermQuery.getTerms().length < currentTermQuery.getPage().getTotalElements()) {
                for(int i = 1; i < currentTermQuery.getPage().getTotalElements() / currentTermQuery.getTerms().length + 1; ++i) {
                    JamiOlsTermQuery termQuery = this.getTermQuery(i, ontologyID);
                    if (termQuery != null && termQuery.getTerms() != null) {
                        terms.addAll(Arrays.asList(termQuery.getTerms()));
                    }
                }
            }
        }

        return terms;
    }

    private JamiOlsTermQuery getRootQuery(int page, String ontologyID) {
        String query = String.format("page=%s&size=%s", page, 1000);
        URI uri = this.encodeURL("/api/ontologies/" + ontologyID + "/terms/roots", query);
        return this.getForObject(uri, JamiOlsTermQuery.class);
    }

    private JamiOlsTermQuery getTermQuery(int page, String ontologyID) {
        String query = String.format("page=%s&size=%s", page, 1000);
        URI uri = this.encodeURL("/api/ontologies/" + ontologyID + "/terms", query);
        return this.getForObject(uri, JamiOlsTermQuery.class);
    }

    private List<Term> getTermChildrenMap(Href childrenHRef, int distance) {
        List<Term> children = new ArrayList<>();
        if (distance == 0) {
            return Collections.emptyList();
        } else {
            List<Term> childTerms = this.getTermChildren(childrenHRef, distance);
            children.addAll(childTerms);
            return children;
        }
    }

    private List<Term> getTermParentsMap(Href parentsHRef, int distance) {
        List<Term> parents = new ArrayList<>();
        if (distance == 0) {
            return Collections.emptyList();
        } else {
            List<Term> parentTerms = this.getTermParents(parentsHRef, distance);
            parents.addAll(parentTerms);
            return parents;
        }
    }

    private List<Term> getTermChildren(Href hrefChildren, int distance) {
        if (distance == 0) {
            return new ArrayList<>();
        } else {
            List<Term> childTerms = new ArrayList<>(this.getTermQuery(hrefChildren));
            --distance;
            List<Term> currentChild = new ArrayList<>();

            for(Term child : childTerms) {
                currentChild.addAll(this.getTermChildren(child.getLink().getAllChildrenRef(), distance));
            }

            childTerms.addAll(currentChild);
            return childTerms;
        }
    }

    private List<Term> getTermParents(Href hrefParents, int distance) {
        if (distance == 0) {
            return new ArrayList<>();
        } else {
            List<Term> parentTerms = new ArrayList<>(this.getTermQuery(hrefParents));
            --distance;
            List<Term> currentParent = new ArrayList<>();

            for(Term parent : parentTerms) {
                currentParent.addAll(this.getTermParents(parent.getLink().getAllParentsRef(), distance));
            }

            parentTerms.addAll(currentParent);
            return parentTerms;
        }
    }

    private List<Term> getTermQuery(Href href) throws RestClientException {
        if (href == null) {
            return new ArrayList<>();
        } else {
            List<Term> terms = new ArrayList<>();
            String query = href.getHref();
            JamiOlsTermQuery termQuery = this.getForObject(query, JamiOlsTermQuery.class);
            if (termQuery != null && termQuery.getTerms() != null) {
                terms.addAll(Arrays.asList(termQuery.getTerms()));
            }

            if (termQuery != null && termQuery.getLink() != null && termQuery.getLink().next() != null) {
                terms.addAll(this.getTermQuery(termQuery.getLink().next()));
            }

            return terms;
        }
    }

    private List<Term> getAllRootTerns(String ontologyID) {
        JamiOlsTermQuery currentTermQuery = this.getRootQuery(0, ontologyID);
        List<Term> terms = new ArrayList<>();
        if (currentTermQuery != null && currentTermQuery.getTerms() != null) {
            terms.addAll(Arrays.asList(currentTermQuery.getTerms()));
            if (currentTermQuery.getTerms().length < currentTermQuery.getPage().getTotalElements()) {
                for(int i = 1; i < currentTermQuery.getPage().getTotalElements() / currentTermQuery.getTerms().length + 1; ++i) {
                    JamiOlsTermQuery termQuery = this.getRootQuery(i, ontologyID);
                    if (termQuery != null && termQuery.getTerms() != null) {
                        terms.addAll(Arrays.asList(termQuery.getTerms()));
                    }
                }
            }
        }

        return terms;
    }

    private URI encodeURL(String path, String query) {
        try {
            String hostname = this.getConfig().getHostName().split("/")[0];
            String hostnamePath = this.getConfig().getHostName().split("/")[1];
            return new URI(this.getConfig().getProtocol(), hostname, "/" + hostnamePath + path, query, null);
        } catch (URISyntaxException var6) {
            throw new RestClientException("The query could not be encoded");
        }
    }

    private <T> T getForObject(String url, Class<T> clazz) throws RestClientException {
        try {
            return this.getForObject(new URI(url), clazz);
        } catch (URISyntaxException e) {
            throw new RestClientException(e.getMessage());
        }
    }

    private <T> T getForObject(URI uri, Class<T> clazz) throws RestClientException {
        try {
            return this.mapper.readValue(uri.toURL(), clazz);
        } catch (IOException e) {
            throw new RestClientException(e.getMessage());
        }
    }
}

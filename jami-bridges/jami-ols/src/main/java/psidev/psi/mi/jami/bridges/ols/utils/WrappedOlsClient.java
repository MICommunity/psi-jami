package psidev.psi.mi.jami.bridges.ols.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestClientException;
import uk.ac.ebi.pride.utilities.ols.web.service.client.OLSClient;
import uk.ac.ebi.pride.utilities.ols.web.service.config.AbstractOLSWsConfig;
import uk.ac.ebi.pride.utilities.ols.web.service.model.Term;
import uk.ac.ebi.pride.utilities.ols.web.service.model.TermQuery;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WrappedOlsClient extends OLSClient {

    private final ObjectMapper mapper;

    public WrappedOlsClient(AbstractOLSWsConfig config) {
        super(config);
        this.mapper = (new ObjectMapper()).configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    public List<Term> getRootTerms(String ontologyID) {
        return this.getAllRootTerns(ontologyID);
    }

    private List<Term> getAllRootTerns(String ontologyID) {
        TermQuery currentTermQuery = this.getRootQuery(0, ontologyID);
        List<Term> terms = new ArrayList();
        if (currentTermQuery != null && currentTermQuery.getTerms() != null) {
            terms.addAll(Arrays.asList(currentTermQuery.getTerms()));
            if (currentTermQuery.getTerms().length < currentTermQuery.getPage().getTotalElements()) {
                for(int i = 1; i < currentTermQuery.getPage().getTotalElements() / currentTermQuery.getTerms().length + 1; ++i) {
                    TermQuery termQuery = this.getRootQuery(i, ontologyID);
                    if (termQuery != null && termQuery.getTerms() != null) {
                        terms.addAll(Arrays.asList(termQuery.getTerms()));
                    }
                }
            }
        }

        return terms;
    }

    private TermQuery getRootQuery(int page, String ontologyID) {
        String query = String.format("page=%s&size=%s", page, 1000);
        URI uri = this.encodeURL("/api/ontologies/" + ontologyID + "/terms/roots", query);
        return this.getForObject(uri, TermQuery.class);
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

    private <T> T getForObject(URI uri, Class<T> clazz) throws RestClientException {
        try {
            return (T)this.mapper.readValue(uri.toURL(), clazz);
        } catch (IOException e) {
            throw new RestClientException(e.getMessage());
        }
    }
}

package psidev.psi.mi.jami.bridges.ols.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.ebi.pride.utilities.ols.web.service.model.QueryResult;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class JamiOlsTermQuery extends QueryResult {
    @JsonProperty("_embedded")
    private JamiOlsTermEmbedded termEmbedded;

    public JamiOlsTermQuery() {
    }

    public JamiOlsTerm[] getTerms() {
        return this.termEmbedded != null ? this.termEmbedded.getTerms() : null;
    }

    public JamiOlsTermEmbedded getTermEmbedded() {
        return this.termEmbedded;
    }

    public void setTermEmbedded(JamiOlsTermEmbedded termEmbedded) {
        this.termEmbedded = termEmbedded;
    }
}

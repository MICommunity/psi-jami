package psidev.psi.mi.jami.bridges.ols.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class JamiOlsTermEmbedded {
    @JsonProperty("terms")
    private JamiOlsTerm[] terms;

    public JamiOlsTermEmbedded() {
    }

    public JamiOlsTerm[] getTerms() {
        return this.terms;
    }

    public void setTerms(JamiOlsTerm[] terms) {
        this.terms = terms;
    }
}

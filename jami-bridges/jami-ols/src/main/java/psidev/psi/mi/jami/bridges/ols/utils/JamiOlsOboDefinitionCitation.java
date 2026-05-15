package psidev.psi.mi.jami.bridges.ols.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.ebi.pride.utilities.ols.web.service.model.OBOXRef;
import uk.ac.ebi.pride.utilities.ols.web.service.model.OboDefinitionCitation;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class JamiOlsOboDefinitionCitation extends OboDefinitionCitation {
    @JsonProperty("definition")
    private String definition;

    public JamiOlsOboDefinitionCitation() {
    }

    public JamiOlsOboDefinitionCitation(OBOXRef[] oboXrefs) {
        super(oboXrefs);
    }

    public JamiOlsOboDefinitionCitation(String definition) {
        this.definition = definition;
    }

    public JamiOlsOboDefinitionCitation(OBOXRef[] oboXrefs, String definition) {
        super(oboXrefs);
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}

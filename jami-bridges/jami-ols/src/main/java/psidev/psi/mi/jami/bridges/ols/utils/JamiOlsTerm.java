package psidev.psi.mi.jami.bridges.ols.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.ebi.pride.utilities.ols.web.service.model.Annotation;
import uk.ac.ebi.pride.utilities.ols.web.service.model.Identifier;
import uk.ac.ebi.pride.utilities.ols.web.service.model.Term;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class JamiOlsTerm extends Term {
    @JsonProperty("obo_definition_citation")
    private JamiOlsOboDefinitionCitation[] oboDefinitionCitation;

    public JamiOlsTerm() {
    }

    public JamiOlsTerm(Identifier iri, String label, String[] description, Identifier shortForm, Identifier oboId, String ontologyName, String score, String ontologyIri, boolean definedOntology, JamiOlsOboDefinitionCitation[] oboDefinitionCitation) {
        super(iri, label, description, shortForm, oboId, ontologyName, score, ontologyIri, definedOntology, oboDefinitionCitation);
    }

    public JamiOlsTerm(Identifier iri, String label, String[] description, Identifier shortForm, Identifier oboId, String ontologyName, String score, String ontologyIri, boolean definedOntology, JamiOlsOboDefinitionCitation[] oboDefinitionCitation, Annotation annotation) {
        super(iri, label, description, shortForm, oboId, ontologyName, score, ontologyIri, definedOntology, oboDefinitionCitation, annotation);
    }

    public JamiOlsOboDefinitionCitation[] getOboDefinitionCitation() {
        return this.oboDefinitionCitation;
    }

    public void setOboDefinitionCitation(JamiOlsOboDefinitionCitation[] oboDefinitionCitation) {
        this.oboDefinitionCitation = oboDefinitionCitation;
    }
}

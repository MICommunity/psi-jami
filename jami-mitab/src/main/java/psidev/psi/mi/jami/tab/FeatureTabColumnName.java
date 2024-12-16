package psidev.psi.mi.jami.tab;

/**
 * FeatureTab column names
 */
public enum FeatureTabColumnName {

    FEATURE_AC("Feature AC"),
    FEATURE_SHORT_LABEL("Feature short label"),
    FEATURE_RANGES("Feature range(s)"),
    ORIGINAL_SEQUENCE("Original sequence"),
    RESULTING_SEQUENCE("Resulting sequence"),
    FEATURE_TYPE("Feature type"),
    FEATURE_ANNOTATIONS("Feature annotation(s)"),
    AFFECTED_PROTEIN_AC("Affected protein AC"),
    AFFECTED_PROTEIN_SYMBOL("Affected protein symbol"),
    AFFECTED_PROTEIN_FULL_NAME("Affected protein full name"),
    AFFECTED_PROTEIN_ORGANISM("Affected protein organism"),
    INTERACTION_PARTICIPANTS("Interaction participants"),
    PUBMED_ID("PubMedID"),
    FIGURE_LEGENDS("Figure legend(s)"),
    INTERACTION_AC("Interaction AC"),
    XREF_ID("Xref ID(s)");

    private final String name;

    FeatureTabColumnName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

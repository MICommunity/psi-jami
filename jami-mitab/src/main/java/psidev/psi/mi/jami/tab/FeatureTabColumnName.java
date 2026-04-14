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
    AFFECTED_MOLECULE_IDENTIFIER("Affected molecule identifier"),
    AFFECTED_MOLECULE_SYMBOL("Affected molecule symbol"),
    AFFECTED_MOLECULE_FULL_NAME("Affected molecule full name"),
    AFFECTED_MOLECULE_ORGANISM("Affected molecule organism"),
    INTERACTION_PARTICIPANTS("Interaction participants"),
    PUBMED_ID("PubMed ID"),
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

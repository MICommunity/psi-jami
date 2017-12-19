package psidev.psi.mi.jami.model;

/**
 * An Annotations gives some information about a specific topic.
 * Ex: topic = 'dataset' and value = 'Alzheimers - Interactions investigated in the context of Alzheimers disease'
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/11/12</pre>
 */
public interface Annotation {

    /** Constant <code>COMPLEX_PROPERTIES="complex-properties"</code> */
    public static final String COMPLEX_PROPERTIES="complex-properties";
    /** Constant <code>COMPLEX_PROPERTIES_MI="MI:0629"</code> */
    public static final String COMPLEX_PROPERTIES_MI ="MI:0629";
    /** Constant <code>CAUTION="caution"</code> */
    public static final String CAUTION="caution";
    /** Constant <code>CAUTION_MI="MI:0618"</code> */
    public static final String CAUTION_MI ="MI:0618";
    /** Constant <code>COMMENT="comment"</code> */
    public static final String COMMENT="comment";
    /** Constant <code>COMMENT_MI="MI:0612"</code> */
    public static final String COMMENT_MI ="MI:0612";
    /** Constant <code>COMPLEX_EXPANSION="complex expansion"</code> */
    public static final String COMPLEX_EXPANSION="complex expansion";
    /** Constant <code>COMPLEX_EXPANSION_MI="MI:1059"</code> */
    public static final String COMPLEX_EXPANSION_MI ="MI:1059";
    /** Constant <code>SPOKE_EXPANSION="spoke expansion"</code> */
    public static final String SPOKE_EXPANSION="spoke expansion";
    /** Constant <code>SPOKE_EXPANSION_MI="MI:1060"</code> */
    public static final String SPOKE_EXPANSION_MI ="MI:1060";
    /** Constant <code>MATRIX_EXPANSION="matrix expansion"</code> */
    public static final String MATRIX_EXPANSION="matrix expansion";
    /** Constant <code>MATRIX_EXPANSION_MI="MI:1061"</code> */
    public static final String MATRIX_EXPANSION_MI ="MI:1061";
    /** Constant <code>BIPARTITE_EXPANSION="bipartite expansion"</code> */
    public static final String BIPARTITE_EXPANSION="bipartite expansion";
    /** Constant <code>BIPARTITE_EXPANSION_MI="MI:1062"</code> */
    public static final String BIPARTITE_EXPANSION_MI ="MI:1062";
    /** Constant <code>URL="url"</code> */
    public static final String URL="url";
    /** Constant <code>URL_MI="MI:0614"</code> */
    public static final String URL_MI ="MI:0614";
    /** Constant <code>PUBLICATION_TITLE="publication title"</code> */
    public static final String PUBLICATION_TITLE="publication title";
    /** Constant <code>PUBLICATION_TITLE_MI="MI:1091"</code> */
    public static final String PUBLICATION_TITLE_MI ="MI:1091";
    /** Constant <code>PUBLICATION_JOURNAL="journal"</code> */
    public static final String PUBLICATION_JOURNAL="journal";
    /** Constant <code>PUBLICATION_JOURNAL_MI="MI:0885"</code> */
    public static final String PUBLICATION_JOURNAL_MI ="MI:0885";
    /** Constant <code>PUBLICATION_YEAR="publication year"</code> */
    public static final String PUBLICATION_YEAR="publication year";
    /** Constant <code>PUBLICATION_YEAR_MI="MI:0886"</code> */
    public static final String PUBLICATION_YEAR_MI ="MI:0886";
    /** Constant <code>AUTHOR="author-list"</code> */
    public static final String AUTHOR="author-list";
    /** Constant <code>AUTHOR_MI="MI:0636"</code> */
    public static final String AUTHOR_MI ="MI:0636";
    /** Constant <code>CONTACT_EMAIL="contact-email"</code> */
    public static final String CONTACT_EMAIL = "contact-email";
    /** Constant <code>CONTACT_EMAIL_MI="MI:0634"</code> */
    public static final String CONTACT_EMAIL_MI = "MI:0634";
    /** Constant <code>IMEX_CURATION="imex curation"</code> */
    public static final String IMEX_CURATION = "imex curation";
    /** Constant <code>IMEX_CURATION_MI="MI:0959"</code> */
    public static final String IMEX_CURATION_MI = "MI:0959";
    /** Constant <code>MIMIX_CURATION="mimix curation"</code> */
    public static final String MIMIX_CURATION = "mimix curation";
    /** Constant <code>MIMIX_CURATION_MI="MI:0960"</code> */
    public static final String MIMIX_CURATION_MI = "MI:0960";
    /** Constant <code>RAPID_CURATION="rapid curation"</code> */
    public static final String RAPID_CURATION = "rapid curation";
    /** Constant <code>RAPID_CURATION_MI="MI:0961"</code> */
    public static final String RAPID_CURATION_MI = "MI:0961";
    /** Constant <code>FULL_COVERAGE="full coverage"</code> */
    public static final String FULL_COVERAGE = "full coverage";
    /** Constant <code>FULL_COVERAGE_MI="MI:0957"</code> */
    public static final String FULL_COVERAGE_MI = "MI:0957";
    /** Constant <code>CURATION_DEPTH="curation depth"</code> */
    public static final String CURATION_DEPTH = "curation depth";
    /** Constant <code>CURATION_DEPTH_MI="MI:0955"</code> */
    public static final String CURATION_DEPTH_MI = "MI:0955";
    /** Constant <code>PARTIAL_COVERAGE="partial coverage"</code> */
    public static final String PARTIAL_COVERAGE = "partial coverage";
    /** Constant <code>PARTIAL_COVERAGE_MI="MI:0958"</code> */
    public static final String PARTIAL_COVERAGE_MI = "MI:0958";
    /** Constant <code>EXPERIMENTALLY_OBSERVED="experimentally-observed"</code> */
    public static final String EXPERIMENTALLY_OBSERVED = "experimentally-observed";
    /** Constant <code>EXPERIMENTALLY_OBSERVED_MI="MI:1054"</code> */
    public static final String EXPERIMENTALLY_OBSERVED_MI = "MI:1054";
    /** Constant <code>IMPORTED="imported"</code> */
    public static final String IMPORTED = "imported";
    /** Constant <code>IMPORTED_MI="MI:1058"</code> */
    public static final String IMPORTED_MI = "MI:1058";
    /** Constant <code>INTERNALLY_CURATED="internally-curated"</code> */
    public static final String INTERNALLY_CURATED = "internally-curated";
    /** Constant <code>INTERNALLY_CURATED_MI="MI:1055"</code> */
    public static final String INTERNALLY_CURATED_MI = "MI:1055";
    /** Constant <code>PREDICTED="predicted"</code> */
    public static final String PREDICTED = "predicted";
    /** Constant <code>PREDICTED_MI="MI:1057"</code> */
    public static final String PREDICTED_MI = "MI:1057";
    /** Constant <code>TEXT_MINING="text-mining"</code> */
    public static final String TEXT_MINING = "text-mining";
    /** Constant <code>TEXT_MINING_MI="MI:1056"</code> */
    public static final String TEXT_MINING_MI = "MI:1056";
    /** Constant <code>NUCLEIC_ACID_PROTEIN="nucleicacid-protein"</code> */
    public static final String NUCLEIC_ACID_PROTEIN = "nucleicacid-protein";
    /** Constant <code>NUCLEIC_ACID_PROTEIN_MI="MI:1049"</code> */
    public static final String NUCLEIC_ACID_PROTEIN_MI = "MI:1049";
    /** Constant <code>PROTEIN_PROTEIN="protein-protein"</code> */
    public static final String PROTEIN_PROTEIN = "protein-protein";
    /** Constant <code>PROTEIN_PROTEIN_MI="MI:1047"</code> */
    public static final String PROTEIN_PROTEIN_MI = "MI:1047";
    /** Constant <code>SMALL_MOLECULE_PROTEIN="smallmolecule-protein"</code> */
    public static final String SMALL_MOLECULE_PROTEIN = "smallmolecule-protein";
    /** Constant <code>SMALL_MOLECULE_PROTEIN_MI="MI:1048"</code> */
    public static final String SMALL_MOLECULE_PROTEIN_MI = "MI:1048";
    /** Constant <code>CLUSTERED="clustered"</code> */
    public static final String CLUSTERED = "clustered";
    /** Constant <code>CLUSTERED_MI="MI:1052"</code> */
    public static final String CLUSTERED_MI = "MI:1052";
    /** Constant <code>EVIDENCE="evidence"</code> */
    public static final String EVIDENCE = "evidence";
    /** Constant <code>EVIDENCE_MI="MI:1051"</code> */
    public static final String EVIDENCE_MI = "MI:1051";
    /** Constant <code>FIGURE_LEGEND="figure legend"</code> */
    public static final String FIGURE_LEGEND = "figure legend";
    /** Constant <code>FIGURE_LEGEND_MI="MI:0599"</code> */
    public static final String FIGURE_LEGEND_MI = "MI:0599";
    /** Constant <code>EXP_MODIFICATION="experiment modification"</code> */
    public static final String EXP_MODIFICATION = "experiment modification";
    /** Constant <code>EXP_MODIFICATION_MI="MI:0627"</code> */
    public static final String EXP_MODIFICATION_MI = "MI:0627";
    /** Constant <code>SEARCH_URL="search-url"</code> */
    public static final String SEARCH_URL = "search-url";
    /** Constant <code>SEARCH_URL_MI="MI:0615"</code> */
    public static final String SEARCH_URL_MI = "MI:0615";
    /** Constant <code>VALIDATION_REGEXP="id-validation-regexp"</code> */
    public static final String VALIDATION_REGEXP = "id-validation-regexp";
    /** Constant <code>VALIDATION_REGEXP_MI="MI:0628"</code> */
    public static final String VALIDATION_REGEXP_MI = "MI:0628";
    /** Constant <code>POSTAL_ADDRESS="postaladdress"</code> */
    public static final String POSTAL_ADDRESS = "postaladdress";

    /**
     * The annotation topic is a controlled vocabulary term and it cannot be null.
     * Ex: dataset, comment, caution, ...
     *
     * @return the annotation topic
     */
    public CvTerm getTopic();

    /**
     * The value of this annotation. Usually free text but can be null if the topic itself is just a tag.
     * Ex: NPM1 negatively regulates the MDM2-TP53 interaction.
     *
     * @return the description of an annotation
     */
    public String getValue();

    /**
     * Set the value of this annotation.
     *
     * @param value : the value
     */
    public void setValue(String value);
}

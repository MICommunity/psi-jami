package psidev.psi.mi.jami.model;

/**
 * Cross reference to an external database/resource which can give more information about an object.
 * Ex:
 * - GO cross references for an interactor to give information about its biological role(s) or location.
 * - publication primary references
 * - identifier of an object (use ExternalIdentifier)
 * - imex primary references
 * - secondary references to an external database
 * - ...
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/11/12</pre>
 */
public interface Xref {

    /** Constant <code>ENSEMBL="ensembl"</code> */
    public static final String ENSEMBL = "ensembl";
    /** Constant <code>ENSEMBL_MI="MI:0476"</code> */
    public static final String ENSEMBL_MI = "MI:0476";
    /** Constant <code>ENSEMBL_GENOMES="ensemblgenomes"</code> */
    public static final String ENSEMBL_GENOMES = "ensemblgenomes";
    /** Constant <code>ENSEMBL_GENOMES_MI="MI:1013"</code> */
    public static final String ENSEMBL_GENOMES_MI = "MI:1013";
    /** Constant <code>ENTREZ_GENE="entrezgene/locuslink"</code> */
    public static final String ENTREZ_GENE = "entrezgene/locuslink";
    /** Constant <code>ENTREZ_GENE_MI="MI:0477"</code> */
    public static final String ENTREZ_GENE_MI = "MI:0477";
    /** Constant <code>REFSEQ="refseq"</code> */
    public static final String REFSEQ = "refseq";
    /** Constant <code>REFSEQ_MI="MI:0481"</code> */
    public static final String REFSEQ_MI = "MI:0481";
    /** Constant <code>CHEBI="chebi"</code> */
    public static final String CHEBI = "chebi";
    /** Constant <code>CHEBI_MI="MI:0474"</code> */
    public static final String CHEBI_MI = "MI:0474";
    /** Constant <code>DDBJ_EMBL_GENBANK="ddbj/embl/genbank"</code> */
    public static final String DDBJ_EMBL_GENBANK = "ddbj/embl/genbank";
    /** Constant <code>DDBJ_EMBL_GENBANK_MI="MI:0475"</code> */
    public static final String DDBJ_EMBL_GENBANK_MI = "MI:0475";
    /** Constant <code>UNIPROTKB="uniprotkb"</code> */
    public static final String UNIPROTKB = "uniprotkb";
    /** Constant <code>UNIPROTKB_MI="MI:0486"</code> */
    public static final String UNIPROTKB_MI = "MI:0486";
    /** Constant <code>UNIPROTKB_SWISSPROT="swiss-prot"</code> */
    public static final String UNIPROTKB_SWISSPROT = "swiss-prot";
    /** Constant <code>UNIPROTKB_SWISSPROT_MI="MI:1098"</code> */
    public static final String UNIPROTKB_SWISSPROT_MI = "MI:1098";
    /** Constant <code>UNIPROTKB_TREMBL="trembl"</code> */
    public static final String UNIPROTKB_TREMBL = "trembl";
    /** Constant <code>UNIPROTKB_TREMBL_MI="MI:1099"</code> */
    public static final String UNIPROTKB_TREMBL_MI = "MI:1099";
    /** Constant <code>COMPLEX_PORTAL="complex portal"</code> */
    public static final String COMPLEX_PORTAL="complex portal";
    /** Constant <code>COMPLEX_PORTAL_MI="MI:2279</code> */
    public static final String COMPLEX_PORTAL_MI="MI:2279";
    /** Constant <code>IMEX="imex"</code> */
    public static final String IMEX = "imex";
    /** Constant <code>IMEX_MI="MI:0670"</code> */
    public static final String IMEX_MI = "MI:0670";
    /** Constant <code>PUBMED="pubmed"</code> */
    public static final String PUBMED = "pubmed";
    /** Constant <code>PUBMED_MI="MI:0446"</code> */
    public static final String PUBMED_MI = "MI:0446";
    /** Constant <code>DOI="doi"</code> */
    public static final String DOI = "doi";
    /** Constant <code>DOI_MI="MI:0574"</code> */
    public static final String DOI_MI = "MI:0574";
    /** Constant <code>INTERPRO="interpro"</code> */
    public static final String INTERPRO = "interpro";
    /** Constant <code>INTERPRO_MI="MI:0449"</code> */
    public static final String INTERPRO_MI = "MI:0449";

    /** Constant <code>COMPLEX_PRIMARY="complex-primary"</code> */
    public static final String COMPLEX_PRIMARY="complex-primary";
    /** Constant <code>COMPLEX_PRIMARY_MI="MI:2282"</code> */
    public static final String COMPLEX_PRIMARY_MI="MI:2282";
    /** Constant <code>IMEX_PRIMARY="imex-primary"</code> */
    public static final String IMEX_PRIMARY = "imex-primary";
    /** Constant <code>IMEX_PRIMARY_MI="MI:0662"</code> */
    public static final String IMEX_PRIMARY_MI = "MI:0662";
    /** Constant <code>IDENTITY="identity"</code> */
    public static final String IDENTITY = "identity";
    /** Constant <code>IDENTITY_MI="MI:0356"</code> */
    public static final String IDENTITY_MI = "MI:0356";
    /** Constant <code>SECONDARY="secondary-ac"</code> */
    public static final String SECONDARY = "secondary-ac";
    /** Constant <code>SECONDARY_MI="MI:0360"</code> */
    public static final String SECONDARY_MI = "MI:0360";
    /** Constant <code>PRIMARY="primary-reference"</code> */
    public static final String PRIMARY = "primary-reference";
    /** Constant <code>PRIMARY_MI="MI:0358"</code> */
    public static final String PRIMARY_MI = "MI:0358";
    /** Constant <code>SEE_ALSO="see-also"</code> */
    public static final String SEE_ALSO = "see-also";
    /** Constant <code>SEE_ALSO_MI="MI:0361"</code> */
    public static final String SEE_ALSO_MI = "MI:0361";
    /** Constant <code>GO="go"</code> */
    public static final String GO = "go";
    /** Constant <code>GO_MI="MI:0448"</code> */
    public static final String GO_MI = "MI:0448";
    /** Constant <code>METHOD_REFERENCE="method reference"</code> */
    public static final String METHOD_REFERENCE = "method reference";
    /** Constant <code>METHOD_REFERENCE_MI="MI:0357"</code> */
    public static final String METHOD_REFERENCE_MI = "MI:0357";
    /** Constant <code>RESID="resid"</code> */
    public static final String RESID = "resid";
    /** Constant <code>RESID_MI="MI:0248"</code> */
    public static final String RESID_MI = "MI:0248";
    /** Constant <code>SO="so"</code> */
    public static final String SO = "so";
    /** Constant <code>SO_MI="MI:0601"</code> */
    public static final String SO_MI = "MI:0601";
    /** Constant <code>CHAIN_PARENT_MI="MI:0951"</code> */
    public static final String CHAIN_PARENT_MI = "MI:0951";
    /** Constant <code>CHAIN_PARENT="chain-parent"</code> */
    public static final String CHAIN_PARENT = "chain-parent";
    /** Constant <code>ISOFORM_PARENT_MI="MI:0243"</code> */
    public static final String ISOFORM_PARENT_MI = "MI:0243";
    /** Constant <code>ISOFORM_PARENT="isoform-parent"</code> */
    public static final String ISOFORM_PARENT = "isoform-parent";

    /** Constant <code>INTERACTOR_SET_QUALIFIER="set member"</code> */
    public static final String INTERACTOR_SET_QUALIFIER="set member";
    /** Constant <code>INTERACTOR_SET_QUALIFIER_MI="MI:1341"</code> */
    public static final String INTERACTOR_SET_QUALIFIER_MI="MI:1341";


    /**
     * The database is a controlled vocabulary term. It cannot be null.
     *
     * @return the database
     */
    public CvTerm getDatabase();

    /**
     * The identifier in the external database/resource. It cannot be null or empty.
     *
     * @return the database identifier
     */
    public String getId();

    /**
     * The version of the identifier in the database/resource if relevant.
     * It can be null if no versions have been specified
     *
     * @return the version
     */
    public String getVersion();

    /**
     * The qualifier of the xref is the reference type and is a controlled vocabulary term.
     * It can be null. If null, the Xref is giving unqualified information which is not an identifier (different from ExternalIdentifier)
     * Ex: primary-reference, see-also, identity, method reference, ...
     *
     * @return the qualifier of the xref
     */
    public CvTerm getQualifier();
}

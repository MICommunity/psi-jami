package psidev.psi.mi.jami.model;

/**
 * Alias is a synonym. It is composed of a name and a type.
 *
 * Ex: alias type = 'gene' and name = 'BRCA2'
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/11/12</pre>
 */
public interface Alias {

    /** Constant <code>SYNONYM_MI="MI:1041"</code> */
    public static final String SYNONYM_MI = "MI:1041";
    /** Constant <code>SYNONYM="synonym"</code> */
    public static final String SYNONYM = "synonym";
    /** Constant <code>GENE_NAME_MI="MI:0301"</code> */
    public static final String GENE_NAME_MI = "MI:0301";
    /** Constant <code>GENE_NAME="gene name"</code> */
    public static final String GENE_NAME = "gene name";
    /** Constant <code>COMPLEX_SYNONYM="complex-synonym"</code> */
    public static final String COMPLEX_SYNONYM = "complex-synonym";
    /** Constant <code>COMPLEX_SYNONYM_MI="MI:0673"</code> */
    public static final String COMPLEX_SYNONYM_MI = "MI:0673";
    /** Constant <code>AUTHOR_ASSIGNED_NAME="author assigned name"</code> */
    public static final String AUTHOR_ASSIGNED_NAME = "author assigned name";
    /** Constant <code>AUTHOR_ASSIGNED_NAME_MI="MI:0345"</code> */
    public static final String AUTHOR_ASSIGNED_NAME_MI = "MI:0345";
    /** Constant <code>GENE_NAME_SYNONYM="gene name synonym"</code> */
    public static final String GENE_NAME_SYNONYM = "gene name synonym";
    /** Constant <code>GENE_NAME_SYNONYM_MI="MI:0302"</code> */
    public static final String GENE_NAME_SYNONYM_MI = "MI:0302";
    /** Constant <code>ISOFORM_SYNONYM="gene name synonym"</code> */
    public static final String ISOFORM_SYNONYM = "gene name synonym";
    /** Constant <code>ISOFORM_SYNONYM_MI="MI:0304"</code> */
    public static final String ISOFORM_SYNONYM_MI = "MI:0304";
    /** Constant <code>ORF_NAME="orf name"</code> */
    public static final String ORF_NAME = "orf name";
    /** Constant <code>ORF_NAME_MI="MI:0306"</code> */
    public static final String ORF_NAME_MI = "MI:0306";
    /** Constant <code>LOCUS_NAME="locus name"</code> */
    public static final String LOCUS_NAME = "locus name";
    /** Constant <code>LOCUS_NAME_MI="MI:0305"</code> */
    public static final String LOCUS_NAME_MI = "MI:0305";
    /** Constant <code>IUPAC_MI="MI:2007"</code> */
    public static final String IUPAC_MI = "MI:2007";
    /** Constant <code>IUPAC="iupac name"</code> */
    public static final String IUPAC = "iupac name";
    /** Constant <code>COMPLEX_RECOMMENDED_NAME="complex recommended name"</code> */
    public static final String COMPLEX_RECOMMENDED_NAME="complex recommended name";
    /** Constant <code>COMPLEX_RECOMMENDED_NAME_MI="MI:1315"</code> */
    public static final String COMPLEX_RECOMMENDED_NAME_MI="MI:1315";
    /** Constant <code>COMPLEX_SYSTEMATIC_NAME="complex systematic name"</code> */
    public static final String COMPLEX_SYSTEMATIC_NAME="complex systematic name";
    /** Constant <code>COMPLEX_SYSTEMATIC_NAME_MI="MI:1316"</code> */
    public static final String COMPLEX_SYSTEMATIC_NAME_MI="MI:1316";

    /**
     * The alias type is a controlled vocabulary term.
     * The type can be null.
     * Ex : gene name, gene synonym, locus name, ...
     *
     * @return the type of the current alias
     */
    public CvTerm getType();

    /**
     * Alias name cannot be null.
     * Ex: TP53, BRCA2, ..
     *
     * @return the alias name.
     */
    public String getName();
}

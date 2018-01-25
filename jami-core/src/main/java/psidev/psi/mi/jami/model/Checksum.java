package psidev.psi.mi.jami.model;

/**
 * Checksum is a value for checking consistency of the data and can also be used for identifying objects.
 * Ex: ROGID, CROGID, RIGID, CRC64, ...
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/11/12</pre>
 */
public interface Checksum {

    /** Constant <code>SMILE="smiles string"</code> */
    public static final String SMILE = "smiles string";
    /** Constant <code>SMILE_SHORT="smile"</code> */
    public static final String SMILE_SHORT = "smile";
    /** Constant <code>SMILE_MI="MI:2039"</code> */
    public static final String SMILE_MI = "MI:2039";
    /** Constant <code>INCHI="stamdard inchi"</code> */
    public static final String INCHI = "stamdard inchi";
    /** Constant <code>INCHI_SHORT="inchi id"</code> */
    public static final String INCHI_SHORT = "inchi id";
    /** Constant <code>INCHI_MI="MI:2010"</code> */
    public static final String INCHI_MI = "MI:2010";
    /** Constant <code>STANDARD_INCHI_KEY="standard inchi key"</code> */
    public static final String STANDARD_INCHI_KEY = "standard inchi key";
    /** Constant <code>STANDARD_INCHI_KEY_MI="MI:1101"</code> */
    public static final String STANDARD_INCHI_KEY_MI = "MI:1101";
    /** Constant <code>ROGID="rogid"</code> */
    public static final String ROGID = "rogid";
    /** Constant <code>ROGID_MI="MI:1333"</code> */
    public static final String ROGID_MI = "MI:1333";
    /** Constant <code>RIGID="rigid"</code> */
    public static final String RIGID = "rigid";
    /** Constant <code>RIGID_MI="MI:1334"</code> */
    public static final String RIGID_MI = "MI:1334";
    /** Constant <code>IRIGID="irigid"</code> */
    public static final String IRIGID = "irigid";
    /** Constant <code>IROGID="irogid"</code> */
    public static final String IROGID = "irogid";
    /** Constant <code>INCHI_KEY="inchi key"</code> */
    public static final String INCHI_KEY = "inchi key";
    /** Constant <code>INCHI_KEY_MI="MI:0970"</code> */
    public static final String INCHI_KEY_MI = "MI:0970";
    /** Constant <code>CHECKUM="checksum"</code> */
    public static final String CHECKUM = "checksum";
    /** Constant <code>CHECKSUM_MI="MI:1212"</code> */
    public static final String CHECKSUM_MI = "MI:1212";

    /**
     * The method is a controlled vocabulary term and cannot be null
     * Ex: ROGID, CROGID, RIGID, CRC64, ...
     *
     * @return the method used to compute this checksum.
     */
    public CvTerm getMethod();

    /**
     * The checksum cannot be null.
     * Ex: ROGID = UcdngwpTSS6hG/pvQGgpp40u67I9606|crogid:UcdngwpTSS6hG/pvQGgpp40u67I9606
     *
     * @return the checksum
     */
    public String getValue();
}

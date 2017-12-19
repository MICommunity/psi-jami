package psidev.psi.mi.jami.model;

/**
 * The position of a feature in the interactor sequence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/11/12</pre>
 */
public interface Position {
    /** Constant <code>UNDETERMINED="undetermined"</code> */
    public static final String UNDETERMINED = "undetermined";
    /** Constant <code>UNDETERMINED_FULL="undetermined sequence position"</code> */
    public static final String UNDETERMINED_FULL = "undetermined sequence position";
    /** Constant <code>UNDETERMINED_MI="MI:0339"</code> */
    public static final String UNDETERMINED_MI = "MI:0339";

    /** Constant <code>N_TERMINAL_RANGE="n-term range"</code> */
    public static final String N_TERMINAL_RANGE = "n-term range";
    /** Constant <code>N_TERMINAL_RANGE_FULL="n-terminal range"</code> */
    public static final String N_TERMINAL_RANGE_FULL = "n-terminal range";
    /** Constant <code>N_TERMINAL_RANGE_MI="MI:1040"</code> */
    public static final String N_TERMINAL_RANGE_MI = "MI:1040";

    /** Constant <code>C_TERMINAL_RANGE="c-term range"</code> */
    public static final String C_TERMINAL_RANGE = "c-term range";
    /** Constant <code>C_TERMINAL_RANGE_FULL="c-terminal range"</code> */
    public static final String C_TERMINAL_RANGE_FULL = "c-terminal range";
    /** Constant <code>C_TERMINAL_RANGE_MI="MI:1039"</code> */
    public static final String C_TERMINAL_RANGE_MI = "MI:1039";

    /** Constant <code>RAGGED_N_TERMINAL="ragged n-terminus"</code> */
    public static final String RAGGED_N_TERMINAL = "ragged n-terminus";
    /** Constant <code>RAGGED_N_TERMINAL_MI="MI:0341"</code> */
    public static final String RAGGED_N_TERMINAL_MI = "MI:0341";

    /** Constant <code>N_TERMINAL="n-terminal"</code> */
    public static final String N_TERMINAL = "n-terminal";
    /** Constant <code>N_TERMINAL_FULL="n-terminal position"</code> */
    public static final String N_TERMINAL_FULL = "n-terminal position";
    /** Constant <code>N_TERMINAL_MI="MI:0340"</code> */
    public static final String N_TERMINAL_MI = "MI:0340";

    /** Constant <code>C_TERMINAL="c-terminal"</code> */
    public static final String C_TERMINAL = "c-terminal";
    /** Constant <code>C_TERMINAL_FULL="c-terminal position"</code> */
    public static final String C_TERMINAL_FULL = "c-terminal position";
    /** Constant <code>C_TERMINAL_MI="MI:0334"</code> */
    public static final String C_TERMINAL_MI = "MI:0334";

    /** Constant <code>CERTAIN="certain"</code> */
    public static final String CERTAIN = "certain";
    /** Constant <code>CERTAIN_FULL="certain sequence position"</code> */
    public static final String CERTAIN_FULL = "certain sequence position";
    /** Constant <code>CERTAIN_MI="MI:0335"</code> */
    public static final String CERTAIN_MI = "MI:0335";

    /** Constant <code>RANGE="range"</code> */
    public static final String RANGE = "range";
    /** Constant <code>RANGE_FULL="range"</code> */
    public static final String RANGE_FULL = "range";
    /** Constant <code>RANGE_MI="MI:0338"</code> */
    public static final String RANGE_MI = "MI:0338";

    /** Constant <code>GREATER_THAN="greater-than"</code> */
    public static final String GREATER_THAN = "greater-than";
    /** Constant <code>GREATER_THAN_FULL="greater-than"</code> */
    public static final String GREATER_THAN_FULL = "greater-than";
    /** Constant <code>GREATER_THAN_MI="MI:0336"</code> */
    public static final String GREATER_THAN_MI = "MI:0336";

    /** Constant <code>LESS_THAN="less-than"</code> */
    public static final String LESS_THAN = "less-than";
    /** Constant <code>LESS_THAN_FULL="less-than"</code> */
    public static final String LESS_THAN_FULL = "less-than";
    /** Constant <code>LESS_THAN_MI="MI:0337"</code> */
    public static final String LESS_THAN_MI = "MI:0337";


    /**
     * The range status is a controlled vocabulary term which cannot be null.
     * It gives more information about the position.
     * Ex: 'n-terminal', 'certain', 'range', 'greater than', 'less than', 'c-terminal', 'undetermined', ...
     *
     * @return the status
     */
    public CvTerm getStatus();

    /**
     * The start position in the molecule sequence.
     * If the position is an exact sequence position, then start == end. It is possible that a Position represents
     * an interval and in this case, start &lt;= end.
     *
     * @return start position. 0 if the position is undetermined, n-terminal range or c-terminal range
     */
    public long getStart();

    /**
     * The end position in the molecule sequence.
     * If the position is an exact sequence position, then start == end. It is possible that a Position represents
     * an interval and in this case, start &lt;= end.
     *
     * @return end position. 0 if the position is undetermined, n-terminal range or c-terminal range
     */
    public long getEnd();

    /**
     * <p>isPositionUndetermined</p>
     *
     * @return true if the numerical positions exist, false if the range is undetermined, c-terminal range or n-terminal range because
     * we cannot provide numerical positions. In the last case, the methods getStart and getEnd should return 0.
     */
    public boolean isPositionUndetermined();
}

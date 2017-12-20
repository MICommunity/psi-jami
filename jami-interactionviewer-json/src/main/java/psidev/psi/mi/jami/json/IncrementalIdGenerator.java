package psidev.psi.mi.jami.json;

/**
 * Id generator that will increment ids
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>24/07/14</pre>
 */
public class IncrementalIdGenerator {

    private int currentId=0;

    /**
     * <p>Getter for the field <code>currentId</code>.</p>
     *
     * @return a int.
     */
    public int getCurrentId() {
        return currentId;
    }

    /**
     * <p>nextId.</p>
     *
     * @return a int.
     */
    public int nextId() {
        currentId++;
        return currentId;
    }
}

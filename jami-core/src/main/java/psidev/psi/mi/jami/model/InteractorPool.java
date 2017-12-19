package psidev.psi.mi.jami.model;

import java.util.Collection;

/**
 * InteractorPool represents a collection of potential interactors but we cannot determine which one interacts.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/01/13</pre>
 */
public interface InteractorPool extends Interactor,Collection<Interactor> {

    /** Constant <code>MOLECULE_SET="molecule set"</code> */
    public static final String MOLECULE_SET="molecule set";
    /** Constant <code>MOLECULE_SET_MI="MI:1304"</code> */
    public static final String MOLECULE_SET_MI="MI:1304";
    /** Constant <code>CANDIDATE_SET="candidate set"</code> */
    public static final String CANDIDATE_SET="candidate set";
    /** Constant <code>CANDIDATE_SET_MI="MI:1305"</code> */
    public static final String CANDIDATE_SET_MI="MI:1305";
    /** Constant <code>DEFINED_SET="defined set"</code> */
    public static final String DEFINED_SET="defined set";
    /** Constant <code>DEFINED_SET_MI="MI:1307"</code> */
    public static final String DEFINED_SET_MI="MI:1307";
    /** Constant <code>OPEN_SET="open set"</code> */
    public static final String OPEN_SET="open set";
    /** Constant <code>OPEN_SET_MI="MI:1306"</code> */
    public static final String OPEN_SET_MI="MI:1306";
}

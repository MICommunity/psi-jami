package psidev.psi.mi.jami.listener;

import psidev.psi.mi.jami.model.*;

/**
 * Listener for changes in an entity
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 19/07/13
 * @version $Id: $
 */
public interface EntityChangeListener<F extends Entity> extends EntityInteractorChangeListener {

    /**
     * <p>onStoichiometryUpdate</p>
     *
     * @param participant : the updated participant
     * @param oldStoichiometry : old stoichiometry
     * @param <F> a F object.
     */
    public void onStoichiometryUpdate(F participant, Stoichiometry oldStoichiometry);

    /**
     * <p>onAddedCausalRelationship</p>
     *
     * @param participant : the updated participant
     * @param added : old causal relationship
     */
    public void onAddedCausalRelationship(F participant, CausalRelationship added);

    /**
     * <p>onRemovedCausalRelationship</p>
     *
     * @param participant : the updated participant
     * @param removed : removed causal relationship
     */
    public void onRemovedCausalRelationship(F participant, CausalRelationship removed);

    /**
     * <p>onAddedFeature</p>
     *
     * @param participant : the updated participant
     * @param added : added feature
     */
    public void onAddedFeature(F participant, Feature added);

    /**
     * <p>onRemovedFeature</p>
     *
     * @param participant : the updated participant
     * @param removed : removed feature
     */
    public void onRemovedFeature(F participant, Feature removed);
}

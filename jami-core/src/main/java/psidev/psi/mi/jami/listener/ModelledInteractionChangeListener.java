package psidev.psi.mi.jami.listener;

import psidev.psi.mi.jami.model.*;

/**
 * Listener for changes in modelled interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>01/10/13</pre>
 */
public interface ModelledInteractionChangeListener<I extends ModelledInteraction> extends InteractionChangeListener<I>, ParametersChangeListener<I>,
        ConfidencesChangeListener<I> {

    /**
     * <p>onAddedCooperativeEffect</p>
     *
     * @param interaction : updated interaction
     * @param added : added effect
     */
    public void onAddedCooperativeEffect(I interaction, CooperativeEffect added);

    /**
     * <p>onRemovedCooperativeEffect</p>
     *
     * @param interaction : updated interaction
     * @param removed : removed effect
     */
    public void onRemovedCooperativeEffect(I interaction, CooperativeEffect removed);

    /**
     * <p>onAddedInteractionEvidence</p>
     *
     * @param interaction : updated interaction
     * @param added  : added evidence
     */
    public void onAddedInteractionEvidence(I interaction, InteractionEvidence added);

    /**
     * <p>onRemovedInteractionEvidence</p>
     *
     * @param interaction : updated interaction
     * @param removed : removed evidence
     */
    public void onRemovedInteractionEvidence(I interaction, InteractionEvidence removed);

    /**
     * <p>onSourceUpdate</p>
     *
     * @param interaction : updated interaction
     * @param oldSource : old source
     */
    public void onSourceUpdate(I interaction, Source oldSource);

    /**
     * <p>onEvidenceTypeUpdate</p>
     *
     * @param interaction : updated interaction
     * @param oldType : old evidence type
     */
    public void onEvidenceTypeUpdate(I interaction, CvTerm oldType);
}

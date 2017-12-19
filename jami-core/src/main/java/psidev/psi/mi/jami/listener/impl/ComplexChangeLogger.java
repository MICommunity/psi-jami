package psidev.psi.mi.jami.listener.impl;

import psidev.psi.mi.jami.listener.ComplexChangeListener;
import psidev.psi.mi.jami.listener.ModelledInteractionChangeListener;
import psidev.psi.mi.jami.model.*;

import java.util.Date;

/**
 * This listener will just complex change events
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class ComplexChangeLogger extends InteractorChangeLogger<Complex> implements ComplexChangeListener {

    private ModelledInteractionChangeListener<Complex> delegate;

    /**
     * <p>Constructor for ComplexChangeLogger.</p>
     */
    public ComplexChangeLogger() {
        this.delegate = new ModelledInteractionChangeLogger<Complex>();
    }

    /** {@inheritDoc} */
    public void onAddedCooperativeEffect(Complex interaction, CooperativeEffect added) {
        this.delegate.onAddedCooperativeEffect(interaction, added);
    }

    /** {@inheritDoc} */
    public void onRemovedCooperativeEffect(Complex interaction, CooperativeEffect removed) {
        this.delegate.onRemovedCooperativeEffect(interaction, removed);
    }

    /** {@inheritDoc} */
    public void onAddedInteractionEvidence(Complex interaction, InteractionEvidence added) {
        this.delegate.onAddedInteractionEvidence(interaction, added);
    }

    /** {@inheritDoc} */
    public void onRemovedInteractionEvidence(Complex interaction, InteractionEvidence removed) {
        this.delegate.onRemovedInteractionEvidence(interaction, removed);
    }

    /** {@inheritDoc} */
    public void onSourceUpdate(Complex interaction, Source oldSource) {
        this.delegate.onSourceUpdate(interaction, oldSource);
    }

    /** {@inheritDoc} */
    public void onEvidenceTypeUpdate(Complex interaction, CvTerm oldType) {
         this.delegate.onEvidenceTypeUpdate(interaction, oldType);
    }

    /** {@inheritDoc} */
    public void onAddedConfidence(Complex o, Confidence added) {
        this.delegate.onAddedConfidence(o, added);
    }

    /** {@inheritDoc} */
    public void onRemovedConfidence(Complex o, Confidence removed) {
        this.delegate.onRemovedConfidence(o, removed);
    }

    /** {@inheritDoc} */
    public void onUpdatedDateUpdate(Complex interaction, Date oldUpdate) {
        this.delegate.onUpdatedDateUpdate(interaction, oldUpdate);
    }

    /** {@inheritDoc} */
    public void onCreatedDateUpdate(Complex interaction, Date oldCreated) {
        this.delegate.onCreatedDateUpdate(interaction, oldCreated);
    }

    /** {@inheritDoc} */
    public void onInteractionTypeUpdate(Complex interaction, CvTerm oldType) {
        this.delegate.onInteractionTypeUpdate(interaction, oldType);
    }

    /** {@inheritDoc} */
    public void onAddedParticipant(Complex interaction, Participant addedParticipant) {
        this.delegate.onAddedParticipant(interaction, addedParticipant);
    }

    /** {@inheritDoc} */
    public void onRemovedParticipant(Complex interaction, Participant removedParticipant) {
        this.delegate.onRemovedParticipant(interaction, removedParticipant);
    }

    /** {@inheritDoc} */
    public void onAddedParameter(Complex o, Parameter added) {
        this.delegate.onAddedParameter(o, added);
    }

    /** {@inheritDoc} */
    public void onRemovedParameter(Complex o, Parameter removed) {
        this.delegate.onRemovedParameter(o, removed);
    }
}

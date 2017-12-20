package psidev.psi.mi.jami.binary.impl;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.*;

import java.util.Collection;

/**
 * A wrapper for ModelledInteraction which contains two participants
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>05/06/13</pre>
 */
public class ModelledBinaryInteractionWrapper extends AbstractBinaryInteractionWrapper<ModelledInteraction, ModelledParticipant> implements ModelledBinaryInteraction{

    private ModelledInteraction modelledInteraction;

    /**
     * <p>Constructor for ModelledBinaryInteractionWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.ModelledInteraction} object.
     */
    public ModelledBinaryInteractionWrapper(ModelledInteraction interaction) {
        super(interaction);
    }

    /**
     * <p>Constructor for ModelledBinaryInteractionWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.ModelledInteraction} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public ModelledBinaryInteractionWrapper(ModelledInteraction interaction, CvTerm complexExpansion) {
        super(interaction, complexExpansion);
    }

    /**
     * <p>getInteractionEvidences</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<InteractionEvidence> getInteractionEvidences() {
        return getWrappedInteraction().getInteractionEvidences();
    }

    /**
     * <p>getSource</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public Source getSource() {
        return getWrappedInteraction().getSource();
    }

    /** {@inheritDoc} */
    public void setSource(Source source) {
        getWrappedInteraction().setSource(source);
    }

    /**
     * <p>getEvidenceType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getEvidenceType() {
        return getWrappedInteraction().getEvidenceType();
    }

    /** {@inheritDoc} */
    public void setEvidenceType(CvTerm eco) {
        getWrappedInteraction().setEvidenceType(eco);
    }

    /**
     * <p>getModelledConfidences</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledConfidence> getModelledConfidences() {
        return getWrappedInteraction().getModelledConfidences();
    }

    /**
     * <p>getModelledParameters</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledParameter> getModelledParameters() {
        return getWrappedInteraction().getModelledParameters();
    }

    /**
     * <p>getCooperativeEffects</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CooperativeEffect> getCooperativeEffects() {
        return getWrappedInteraction().getCooperativeEffects();
    }
}

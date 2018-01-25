package psidev.psi.mi.jami.binary.impl;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.model.*;

import java.util.Collection;

/**
 * A wrapper for InteractionEvidence which contains two participants
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>05/06/13</pre>
 */
public class BinaryInteractionEvidenceWrapper extends AbstractBinaryInteractionWrapper<InteractionEvidence, ParticipantEvidence> implements BinaryInteractionEvidence {

    /**
     * <p>Constructor for BinaryInteractionEvidenceWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     */
    public BinaryInteractionEvidenceWrapper(InteractionEvidence interaction) {
        super(interaction);
    }

    /**
     * <p>Constructor for BinaryInteractionEvidenceWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public BinaryInteractionEvidenceWrapper(InteractionEvidence interaction, CvTerm complexExpansion) {
        super(interaction, complexExpansion);
    }

    /**
     * <p>getImexId</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getImexId() {
        return getWrappedInteraction().getImexId();
    }

    /** {@inheritDoc} */
    public void assignImexId(String identifier) {
        getWrappedInteraction().assignImexId(identifier);
    }

    /**
     * <p>getExperiment</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public Experiment getExperiment() {
        return getWrappedInteraction().getExperiment();
    }

    /** {@inheritDoc} */
    public void setExperiment(Experiment experiment) {
        getWrappedInteraction().setExperiment(experiment);
    }

    /** {@inheritDoc} */
    public void setExperimentAndAddInteractionEvidence(Experiment experiment) {
        getWrappedInteraction().setExperimentAndAddInteractionEvidence(experiment);
    }

    /**
     * <p>getVariableParameterValues</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<VariableParameterValueSet> getVariableParameterValues() {
        return getWrappedInteraction().getVariableParameterValues();
    }

    /**
     * <p>getAvailability</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAvailability() {
        return getWrappedInteraction().getAvailability();
    }

    /** {@inheritDoc} */
    public void setAvailability(String availability) {
        getWrappedInteraction().setAvailability(availability);
    }

    /**
     * <p>getParameters</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Parameter> getParameters() {
        return getWrappedInteraction().getParameters();
    }

    /**
     * <p>isInferred</p>
     *
     * @return a boolean.
     */
    public boolean isInferred() {
        return getWrappedInteraction().isInferred();
    }

    /** {@inheritDoc} */
    public void setInferred(boolean inferred) {
        getWrappedInteraction().setInferred(inferred);
    }

    /**
     * <p>getConfidences</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Confidence> getConfidences() {
        return getWrappedInteraction().getConfidences();
    }

    /**
     * <p>isNegative</p>
     *
     * @return a boolean.
     */
    public boolean isNegative() {
        return getWrappedInteraction().isNegative();
    }

    /** {@inheritDoc} */
    public void setNegative(boolean negative) {
        getWrappedInteraction().setNegative(negative);
    }
}

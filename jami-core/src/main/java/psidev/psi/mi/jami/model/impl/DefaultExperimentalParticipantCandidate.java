package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

/**
 * Default implementation of experimental participant candidate
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/07/14</pre>
 */
public class DefaultExperimentalParticipantCandidate extends
        AbstractParticipantCandidate<ExperimentalParticipantPool, FeatureEvidence> implements ExperimentalParticipantCandidate{
    /**
     * <p>Constructor for DefaultExperimentalParticipantCandidate.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public DefaultExperimentalParticipantCandidate(Interactor interactor) {
        super(interactor);
    }

    /**
     * <p>Constructor for DefaultExperimentalParticipantCandidate.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public DefaultExperimentalParticipantCandidate(Interactor interactor, Stoichiometry stoichiometry) {
        super(interactor, stoichiometry);
    }
}

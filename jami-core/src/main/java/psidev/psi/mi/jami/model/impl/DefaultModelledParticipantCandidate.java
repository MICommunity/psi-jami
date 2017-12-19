package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

/**
 * Default implementation of modelled participant candidate
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/07/14</pre>
 */
public class DefaultModelledParticipantCandidate extends AbstractParticipantCandidate<ModelledParticipantPool, ModelledFeature>
        implements ModelledParticipantCandidate{
    /**
     * <p>Constructor for DefaultModelledParticipantCandidate.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public DefaultModelledParticipantCandidate(Interactor interactor) {
        super(interactor);
    }

    /**
     * <p>Constructor for DefaultModelledParticipantCandidate.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public DefaultModelledParticipantCandidate(Interactor interactor, Stoichiometry stoichiometry) {
        super(interactor, stoichiometry);
    }
}

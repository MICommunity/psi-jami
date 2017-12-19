package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

/**
 * Abstract class for participant candidate
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/07/14</pre>
 */
public abstract class AbstractParticipantCandidate<P extends ParticipantPool, F extends Feature> extends AbstractEntity<F> implements ParticipantCandidate<P,F> {

    private P parentPool;

    /**
     * <p>Constructor for AbstractParticipantCandidate.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param <P> a P object.
     * @param <F> a F object.
     */
    public AbstractParticipantCandidate(Interactor interactor) {
        super(interactor);
    }

    /**
     * <p>Constructor for AbstractParticipantCandidate.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public AbstractParticipantCandidate(Interactor interactor, Stoichiometry stoichiometry) {
        super(interactor, stoichiometry);
    }

    /**
     * <p>Getter for the field <code>parentPool</code>.</p>
     *
     * @return a P object.
     */
    public P getParentPool() {
        return this.parentPool;
    }

    /**
     * <p>Setter for the field <code>parentPool</code>.</p>
     *
     * @param pool a P object.
     */
    public void setParentPool(P pool) {
        this.parentPool = pool;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Participant candidate: "+getInteractor().toString() + (getStoichiometry() != null ? ", stoichiometry: " + getStoichiometry().toString() : "");
    }
}

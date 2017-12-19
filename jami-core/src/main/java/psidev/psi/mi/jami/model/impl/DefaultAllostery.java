package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

/**
 * Default implementation for Allostery
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the Allostery object is a complex object.
 * To compare Allostery objects, you can use some comparators provided by default:
 * - DefaultAllosteryComparator
 * - UnambiguousAllosteryComparator
 * - AllosteryComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/05/13</pre>
 */
public class DefaultAllostery<T extends AllostericEffector> extends DefaultCooperativeEffect implements Allostery<T> {

    private CvTerm allostericMechanism;
    private CvTerm allosteryType;
    private ModelledEntity allostericMolecule;
    private T allostericEffector;

    /**
     * <p>Constructor for DefaultAllostery.</p>
     *
     * @param outcome a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param allostericMolecule a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param allostericEffector a T object.
     * @param <T> a T object.
     */
    public DefaultAllostery(CvTerm outcome, ModelledParticipant allostericMolecule, T allostericEffector) {
        super(outcome);
        if (allostericMolecule == null){
            throw new IllegalArgumentException("The allosteric molecule cannot be null");
        }
        this.allostericMolecule = allostericMolecule;
        if (allostericEffector == null){
            throw new IllegalArgumentException("The allosteric effector cannot be null");
        }
        this.allostericEffector = allostericEffector;
    }

    /**
     * <p>Constructor for DefaultAllostery.</p>
     *
     * @param outcome a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param response a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param allostericMolecule a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param allostericEffector a T object.
     */
    public DefaultAllostery(CvTerm outcome, CvTerm response, ModelledParticipant allostericMolecule, T allostericEffector) {
        super(outcome, response);
        if (allostericMolecule == null){
            throw new IllegalArgumentException("The allosteric molecule cannot be null");
        }
        this.allostericMolecule = allostericMolecule;
        if (allostericEffector == null){
            throw new IllegalArgumentException("The allosteric effector cannot be null");
        }
        this.allostericEffector = allostericEffector;
    }

    /**
     * <p>Getter for the field <code>allostericMechanism</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getAllostericMechanism() {
        return this.allostericMechanism;
    }

    /** {@inheritDoc} */
    public void setAllostericMechanism(CvTerm mechanism) {
        this.allostericMechanism = mechanism;
    }

    /**
     * <p>Getter for the field <code>allosteryType</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getAllosteryType() {
        return this.allosteryType;
    }

    /** {@inheritDoc} */
    public void setAllosteryType(CvTerm type) {
        this.allosteryType = type;
    }

    /**
     * <p>Getter for the field <code>allostericMolecule</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.ModelledEntity} object.
     */
    public ModelledEntity getAllostericMolecule() {
        return this.allostericMolecule;
    }

    /** {@inheritDoc} */
    public void setAllostericMolecule(ModelledEntity participant) {
        if (participant == null){
            throw new IllegalArgumentException("The allosteric molecule cannot be null");
        }
        this.allostericMolecule = participant;
    }

    /**
     * <p>Getter for the field <code>allostericEffector</code>.</p>
     *
     * @return a T object.
     */
    public T getAllostericEffector() {
        return this.allostericEffector;
    }

    /**
     * <p>Setter for the field <code>allostericEffector</code>.</p>
     *
     * @param effector a T object.
     */
    public void setAllostericEffector(T effector) {
        if (effector == null){
            throw new IllegalArgumentException("The allosteric effector cannot be null");
        }
        this.allostericEffector = effector;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Allostery: outcome: "+(getOutCome() != null ? getOutCome().toString() : "")
                + (getResponse() != null ? ", response: " + getResponse().toString() : "");
    }
}

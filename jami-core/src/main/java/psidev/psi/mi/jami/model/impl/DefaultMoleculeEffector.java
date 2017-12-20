package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.AllostericEffectorType;
import psidev.psi.mi.jami.model.ModelledEntity;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.model.MoleculeEffector;

/**
 * Default implementation for MoleculeEffector
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the MoleculeEffector object is a complex object.
 * To compare MoleculeEffector objects, you can use some comparators provided by default:
 * - DefaultMoleculeEffectorComparator
 * - UnambiguousMoleculeEffectorComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/05/13</pre>
 */
public class DefaultMoleculeEffector implements MoleculeEffector {

    private ModelledEntity participant;

    /**
     * <p>Constructor for DefaultMoleculeEffector.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public DefaultMoleculeEffector(ModelledParticipant participant){
        if (participant == null){
            throw new IllegalArgumentException("The participant of a MoleculeEffector cannot be null.");
        }
        this.participant = participant;
    }

    /**
     * <p>getMolecule</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.ModelledEntity} object.
     */
    public ModelledEntity getMolecule() {
        return participant;
    }

    /**
     * <p>getEffectorType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.AllostericEffectorType} object.
     */
    public AllostericEffectorType getEffectorType() {
        return AllostericEffectorType.molecule;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "molecule effector: " + getMolecule().toString();
    }
}

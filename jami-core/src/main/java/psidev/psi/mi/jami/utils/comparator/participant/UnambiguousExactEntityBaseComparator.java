package psidev.psi.mi.jami.utils.comparator.participant;

import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.utils.comparator.interactor.UnambiguousExactInteractorBaseComparator;
import psidev.psi.mi.jami.utils.comparator.interactor.UnambiguousExactInteractorComparator;

/**
 * Unambiguous exact entity comparator
 * It will first compare the interactors using UnambiguousExactInteractorComparator. If both interactors are the same,
 * it will look at the stoichiometry (participant with lower stoichiometry will come first).
 * <p>
 * This comparator will ignore all the other properties of a participant.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/01/13</pre>
 */
public class UnambiguousExactEntityBaseComparator extends EntityBaseComparator {

    private static UnambiguousExactEntityBaseComparator unambiguousExactParticipantComparator;

    /**
     * {@inheritDoc}
     * <p>
     * Creates a new UnambiguousExactParticipantBaseComparator. It will use a UnambiguousExactInteractorComparator to compare
     * interactors, a UnambiguousCvTermComparator to compare biological roles.
     */
    public UnambiguousExactEntityBaseComparator() {
        super(new UnambiguousExactInteractorComparator());
    }

    /**
     * <p>Constructor for UnambiguousExactEntityBaseComparator.</p>
     *
     * @param comparator a {@link psidev.psi.mi.jami.utils.comparator.interactor.UnambiguousExactInteractorComparator} object.
     */
    public UnambiguousExactEntityBaseComparator(UnambiguousExactInteractorComparator comparator) {
        super(comparator != null ? comparator : new UnambiguousExactInteractorComparator());
    }

    /**
     * Use UnambiguousExactParticipantBaseComparator to know if two participants are equals.
     *
     * @param participant1 a {@link psidev.psi.mi.jami.model.Entity} object.
     * @param participant2 a {@link psidev.psi.mi.jami.model.Entity} object.
     * @return true if the two participants are equal
     */
    public static boolean areEquals(Entity participant1, Entity participant2) {
        if (unambiguousExactParticipantComparator == null) {
            unambiguousExactParticipantComparator = new UnambiguousExactEntityBaseComparator();
        }

        return unambiguousExactParticipantComparator.compare(participant1, participant2) == 0;
    }

    /**
     * {@inheritDoc}
     *
     * <p>hashCode</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.Entity} object.
     * @return the hashcode consistent with the equals method for this comparator
     */
    public static int hashCode(Entity participant) {
        if (unambiguousExactParticipantComparator == null) {
            unambiguousExactParticipantComparator = new UnambiguousExactEntityBaseComparator();
        }

        if (participant == null) {
            return 0;
        }

        int hashcode = 31;
        hashcode = 31 * hashcode + UnambiguousExactInteractorBaseComparator.hashCode(participant.getInteractor());
        hashcode = 31 * hashcode + StoichiometryComparator.hashCode(participant.getStoichiometry());

        return hashcode;
    }

    @Override
    public UnambiguousExactInteractorComparator getInteractorComparator() {
        return (UnambiguousExactInteractorComparator) super.getInteractorComparator();
    }

    /**
     * It will first compare the interactors using UnambiguousExactInteractorComparator. If both interactors are the same,
     * it will look at the stoichiometry (participant with lower stoichiometry will come first).
     */
    @Override
    public int compare(Entity participant1, Entity participant2) {
        return super.compare(participant1, participant2);
    }
}

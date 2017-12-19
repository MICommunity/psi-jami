package psidev.psi.mi.jami.utils.comparator.participant;

import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.utils.comparator.cv.UnambiguousCvTermComparator;

/**
 * Unambiguous exact participant comparator
 * It will first compare the interactors using UnambiguousExactEntityBaseComparator. If both interactors are the same,
 * it will compare the biological roles using UnambiguousCvTermComparator.
 * <p>
 * This comparator will ignore all the other properties of a participant.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/01/13</pre>
 */
public class UnambiguousExactParticipantBaseComparator extends ParticipantBaseComparator {

    private static UnambiguousExactParticipantBaseComparator unambiguousExactParticipantComparator;

    /**
     * {@inheritDoc}
     * <p>
     * Creates a new UnambiguousExactParticipantBaseComparator. It will use a UnambiguousExactInteractorComparator to compare
     * interactors, a UnambiguousCvTermComparator to compare biological roles.
     */
    public UnambiguousExactParticipantBaseComparator() {
        super(new UnambiguousExactEntityBaseComparator(), new UnambiguousCvTermComparator());
    }

    /**
     * <p>Constructor for UnambiguousExactParticipantBaseComparator.</p>
     *
     * @param comparator a {@link psidev.psi.mi.jami.utils.comparator.participant.UnambiguousExactEntityBaseComparator} object.
     */
    public UnambiguousExactParticipantBaseComparator(UnambiguousExactEntityBaseComparator comparator) {
        super(comparator != null ? comparator : new UnambiguousExactEntityBaseComparator(), new UnambiguousCvTermComparator());
    }

    /**
     * Use UnambiguousExactParticipantBaseComparator to know if two participants are equals.
     *
     * @param participant1 a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participant2 a {@link psidev.psi.mi.jami.model.Participant} object.
     * @return true if the two participants are equal
     */
    public static boolean areEquals(Participant participant1, Participant participant2) {
        if (unambiguousExactParticipantComparator == null) {
            unambiguousExactParticipantComparator = new UnambiguousExactParticipantBaseComparator();
        }

        return unambiguousExactParticipantComparator.compare(participant1, participant2) == 0;
    }

    /**
     * <p>hashCode</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.Participant} object.
     * @return the hashcode consistent with the equals method for this comparator
     */
    public static int hashCode(Participant participant) {
        if (unambiguousExactParticipantComparator == null) {
            unambiguousExactParticipantComparator = new UnambiguousExactParticipantBaseComparator();
        }

        if (participant == null) {
            return 0;
        }

        int hashcode = 31;
        hashcode = 31 * hashcode + UnambiguousExactEntityBaseComparator.hashCode(participant);
        hashcode = 31 * hashcode + UnambiguousCvTermComparator.hashCode(participant.getBiologicalRole());

        return hashcode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UnambiguousExactEntityBaseComparator getEntityBaseComparator() {
        return (UnambiguousExactEntityBaseComparator) super.getEntityBaseComparator();
    }

    @Override
    public UnambiguousCvTermComparator getCvTermComparator() {
        return (UnambiguousCvTermComparator) super.getCvTermComparator();
    }

    @Override
    /**
     * It will first compare the interactors using UnambiguousExactEntityBaseComparator. If both interactors are the same,
     * it will compare the biological roles using UnambiguousCvTermComparator.
     */
    public int compare(Participant participant1, Participant participant2) {
        return super.compare(participant1, participant2);
    }
}

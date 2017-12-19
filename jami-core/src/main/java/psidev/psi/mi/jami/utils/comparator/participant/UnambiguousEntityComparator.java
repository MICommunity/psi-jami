package psidev.psi.mi.jami.utils.comparator.participant;

import psidev.psi.mi.jami.model.Entity;

/**
 * Unambiguous generic entity comparator
 * Modelled participants come first and then experimental participants.
 * - It uses UnambiguousParticipantPoolComparator to compare participant sets
 * - It uses UnambiguousModelledParticipantComparator to compare components
 * - It uses UnambiguousParticipantEvidenceComparator to compare experimental participants
 * - It uses UnambiguousParticipantBaseComparator to compare basic participant properties
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/02/13</pre>
 */
public class UnambiguousEntityComparator extends EntityComparator {

    private static UnambiguousEntityComparator unambiguousExactParticipantComparator;

    /**
     * {@inheritDoc}
     *
     * Creates a UnambiguousParticipantComparator. It will use a UnambiguousExactParticipantBaseComparator to compare basic feature properties
     */
    public UnambiguousEntityComparator() {
        super(new UnambiguousEntityBaseComparator(), new UnambiguousExperimentalEntityComparator(),
                new UnambiguousModelledEntityComparator(),
                new UnambiguousParticipantComparator());
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousEntityBaseComparator getEntityBaseComparator() {
        return (UnambiguousEntityBaseComparator) super.getEntityBaseComparator();
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousExperimentalEntityComparator getExperimentalEntityComparator() {
        return (UnambiguousExperimentalEntityComparator) super.getExperimentalEntityComparator();
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousModelledEntityComparator getBiologicalEntityComparator() {
        return (UnambiguousModelledEntityComparator) super.getBiologicalEntityComparator();
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousParticipantComparator getParticipantComparator() {
        return (UnambiguousParticipantComparator) super.getParticipantComparator();
    }

    /**
     * Modelled participants come first and then experimental participants.
     * - It uses UnambiguousParticipantPoolComparator to compare participant sets
     * - It uses UnambiguousModelledParticipantComparator to compare components
     * - It uses UnambiguousParticipantEvidenceComparator to compare experimental participants
     * - It uses UnambiguousParticipantBaseComparator to compare basic participant properties
     *
     */
    @Override
    public int compare(Entity participant1, Entity participant2) {
        return super.compare(participant1, participant2);
    }

    /**
     * Use UnambiguousExactEntityComparator to know if two participants are equals.
     *
     * @param participant1 a {@link psidev.psi.mi.jami.model.Entity} object.
     * @param participant2 a {@link psidev.psi.mi.jami.model.Entity} object.
     * @return true if the two participants are equal
     */
    public static boolean areEquals(Entity participant1, Entity participant2){
        if (unambiguousExactParticipantComparator == null){
            unambiguousExactParticipantComparator = new UnambiguousEntityComparator();
        }

        return unambiguousExactParticipantComparator.compare(participant1, participant2) == 0;
    }
}

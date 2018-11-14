package psidev.psi.mi.jami.utils.comparator.participant;

import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.utils.comparator.interactor.UnambiguousComplexComparator;
import psidev.psi.mi.jami.utils.comparator.interactor.UnambiguousInteractorComparator;

/**
 * Unambiguous experimental participant comparator based on the interactor only.
 * It will compare the basic properties of an interactor using UnambiguousInteractorComparator.
 *
 * This comparator will ignore all the other properties of an experimental participant.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/01/13</pre>
 */
public class UnambiguousParticipantEvidenceInteractorComparator extends
        ParticipantInteractorComparator<Entity<FeatureEvidence>> {
    private static UnambiguousParticipantEvidenceInteractorComparator unambiguousExperimentalParticipantInteractorComparator;

    /**
     * {@inheritDoc}
     *
     * Creates a new UnambiguousParticipantEvidenceInteractorComparator. It will use a UnambiguousInteractorComparator to compare
     * the basic properties of a interactor.
     */
    public UnambiguousParticipantEvidenceInteractorComparator() {
        super(new UnambiguousInteractorComparator(new UnambiguousComplexComparator(new UnambiguousModelledParticipantInteractorComparator())));
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousInteractorComparator getInteractorComparator() {
        return (UnambiguousInteractorComparator) super.getInteractorComparator();
    }

    /**
     * {@inheritDoc}
     *
     * It will compare the basic properties of an interactor using UnambiguousInteractorComparator.
     *
     * This comparator will ignore all the other properties of an experimental participant.
     */
    @Override
    public int compare(Entity<FeatureEvidence> experimentalParticipant1, Entity<FeatureEvidence> experimentalParticipant2) {
        return super.compare(experimentalParticipant1, experimentalParticipant2);
    }

    /**
     * Use UnambiguousParticipantEvidenceInteractorComparator to know if two experimental participants are equals.
     *
     * @param experimentalParticipant1 a {@link psidev.psi.mi.jami.model.Entity} object.
     * @param component2 a {@link psidev.psi.mi.jami.model.Entity} object.
     * @return true if the two experimental participants are equal
     */
    public static boolean areEquals(Entity<FeatureEvidence> experimentalParticipant1, Entity<FeatureEvidence> component2){
        if (unambiguousExperimentalParticipantInteractorComparator == null){
            unambiguousExperimentalParticipantInteractorComparator = new UnambiguousParticipantEvidenceInteractorComparator();
        }

        return unambiguousExperimentalParticipantInteractorComparator.compare(experimentalParticipant1, component2) == 0;
    }
}

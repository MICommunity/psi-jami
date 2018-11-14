package psidev.psi.mi.jami.utils.comparator.participant;

import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.utils.comparator.interactor.UnambiguousExactComplexComparator;
import psidev.psi.mi.jami.utils.comparator.interactor.UnambiguousExactInteractorComparator;

/**
 * Unambiguous exact experimental participant comparator based on the interactor only.
 * It will compare the basic properties of an interactor using UnambiguousExactInteractorComparator.
 *
 * This comparator will ignore all the other properties of an experimental participant.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/05/13</pre>
 */
public class UnambiguousExactParticipantEvidenceInteractorComparator extends ParticipantInteractorComparator<Entity<FeatureEvidence>> {
    private static UnambiguousExactParticipantEvidenceInteractorComparator unambiguousExactExperimentalParticipantInteractorComparator;

    /**
     * {@inheritDoc}
     *
     * Creates a new UnambiguousExactParticipantEvidenceInteractorComparator. It will use a UnambiguousExactInteractorComparator to compare
     * the basic properties of a interactor.
     */
    public UnambiguousExactParticipantEvidenceInteractorComparator() {
        super(new UnambiguousExactInteractorComparator(new UnambiguousExactComplexComparator(new UnambiguousExactModelledParticipantInteractorComparator())));
    }

    @Override
    public UnambiguousExactInteractorComparator getInteractorComparator() {
        return (UnambiguousExactInteractorComparator) super.getInteractorComparator();
    }

    /**
     * It will compare the basic properties of an interactor using UnambiguousInteractorComparator.
     *
     * This comparator will ignore all the other properties of an experimental participant.
     */
    @Override
    public int compare(Entity<FeatureEvidence> experimentalParticipant1, Entity<FeatureEvidence> experimentalParticipant2) {
        return super.compare(experimentalParticipant1, experimentalParticipant2);
    }

    /**
     * It will compare the basic properties of an interactor using UnambiguousExactInteractorComparator.
     *
     * This comparator will ignore all the other properties of an experimental participant.
     *
     * @param experimentalParticipant1 a {@link psidev.psi.mi.jami.model.Entity} object.
     * @param component2 a {@link psidev.psi.mi.jami.model.Entity} object.
     * @return true if the two experimental participants are equal
     */
    public static boolean areEquals(Entity<FeatureEvidence> experimentalParticipant1, Entity<FeatureEvidence> component2){
        if (unambiguousExactExperimentalParticipantInteractorComparator == null){
            unambiguousExactExperimentalParticipantInteractorComparator = new UnambiguousExactParticipantEvidenceInteractorComparator();
        }

        return unambiguousExactExperimentalParticipantInteractorComparator.compare(experimentalParticipant1, component2) == 0;
    }
}

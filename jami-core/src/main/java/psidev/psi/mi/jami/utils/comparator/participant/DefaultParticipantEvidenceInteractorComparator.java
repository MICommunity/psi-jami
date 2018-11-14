package psidev.psi.mi.jami.utils.comparator.participant;

import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.utils.comparator.interactor.DefaultInteractorComparator;

/**
 * Default experimental participant comparator based on the interactor only.
 * It will compare the basic properties of aninteractor using DefaultInteractorComparator.
 *
 * This comparator will ignore all the other properties of an experimental participant.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/01/13</pre>
 */
public class DefaultParticipantEvidenceInteractorComparator {

    /**
     * Use DefaultParticipantEvidenceInteractorComparator to know if two experimental participants are equals.
     *
     * @param participant1 a {@link psidev.psi.mi.jami.model.Entity} object.
     * @param participant2 a {@link psidev.psi.mi.jami.model.Entity} object.
     * @return true if the two experimental participants are equal
     */
    public static boolean areEquals(Entity<FeatureEvidence> participant1, Entity<FeatureEvidence> participant2){
        if (participant1 == participant2){
            return true;
        }
        else if (participant1 == null || participant2 == null){
            return false;
        }
        else {
            // first compares interactors
            Interactor interactor1 = participant1.getInteractor();
            Interactor interactor2 = participant2.getInteractor();

            return DefaultInteractorComparator.areEquals(interactor1, interactor2);
        }
    }
}

package psidev.psi.mi.jami.utils.comparator.participant;

import psidev.psi.mi.jami.model.BiologicalParticipant;

import java.util.Comparator;

/**
 * Basic biological participant comparator.
 * It will compare the basic properties of a biological participant using ParticipantInteractorComparator.
 *
 * This comparator will ignore all the other properties of a biological participant.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/02/13</pre>
 */

public class BiologicalParticipantComparator implements Comparator<BiologicalParticipant> {

    protected ParticipantInteractorComparator participantComparator;

    /**
     * Creates a new ComponentComparator
     * @param participantComparator : the participant comparator required to compare basic participant properties
     */
    public BiologicalParticipantComparator(ParticipantInteractorComparator participantComparator){
        if (participantComparator == null){
            throw new IllegalArgumentException("The participant comparator is required to compare basic participant properties. It cannot be null");
        }
        this.participantComparator = participantComparator;
    }

    public ParticipantInteractorComparator getParticipantComparator() {
        return participantComparator;
    }

    /**
     * It will compare the basic properties of a biological participant using ParticipantInteractorComparator.
     *
     * This comparator will ignore all the other properties of a biological participant.
     * @param bioParticipant1
     * @param bioParticipant2
     * @return
     */
    public int compare(BiologicalParticipant bioParticipant1, BiologicalParticipant bioParticipant2) {
        return participantComparator.compare(bioParticipant1, bioParticipant2);
    }
}

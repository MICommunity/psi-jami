package psidev.psi.mi.jami.utils.comparator.participant;

import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.ParticipantPool;
import psidev.psi.mi.jami.utils.comparator.CollectionComparator;

import java.util.Comparator;

/**
 * Basic participant pool comparator.
 *
 * It will first compares basic participant properties using ParticipantBaseComparator, then it will compare participant pool type using cv term comparator and then it will compare
 * each participant candidate using EntityBaseComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/01/13</pre>
 */
public class ParticipantPoolComparator implements Comparator<ParticipantPool> {

    private ParticipantBaseComparator participantBaseComparator;
    private CollectionComparator<Entity> entityCollectionComparator;

    /**
     * Creates a new ParticipantPoolComparator
     *
     * @param participantBaseComparator : the participant comparator required to compare basic properties of a participant
     */
    public ParticipantPoolComparator(ParticipantBaseComparator participantBaseComparator){
        if (participantBaseComparator == null){
            throw new IllegalArgumentException("The participant comparator is required to compare basic participant properties. It cannot be null");
        }
        this.participantBaseComparator = participantBaseComparator;
        this.entityCollectionComparator = new ParticipantCollectionComparator(participantBaseComparator.getEntityBaseComparator());
    }

    /**
     * <p>Constructor for ParticipantPoolComparator.</p>
     *
     * @param participantBaseComparator a {@link psidev.psi.mi.jami.utils.comparator.participant.ParticipantBaseComparator} object.
     * @param entityCollectionComparator a {@link psidev.psi.mi.jami.utils.comparator.CollectionComparator} object.
     */
    public ParticipantPoolComparator(ParticipantBaseComparator participantBaseComparator, CollectionComparator<Entity> entityCollectionComparator){
        if (participantBaseComparator == null){
            throw new IllegalArgumentException("The participant comparator is required to compare basic participant properties. It cannot be null");
        }
        this.participantBaseComparator = participantBaseComparator;
        this.entityCollectionComparator = entityCollectionComparator;
    }

    /**
     * <p>Getter for the field <code>participantBaseComparator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.utils.comparator.participant.ParticipantBaseComparator} object.
     */
    public ParticipantBaseComparator getParticipantBaseComparator() {
        return participantBaseComparator;
    }

    /**
     * <p>getParticipantCollectionComparator</p>
     *
     * @return a {@link psidev.psi.mi.jami.utils.comparator.CollectionComparator} object.
     */
    public CollectionComparator<Entity> getParticipantCollectionComparator() {
        return entityCollectionComparator;
    }

    /**
     * It will first compares basic participant properties using ParticipantBaseComparator, then it will compare participant pool type using cv term comparator and then it will compare
     * each participant candidate using EntityBaseComparator
     *
     * @param participant1 a {@link psidev.psi.mi.jami.model.ParticipantPool} object.
     * @param participant2 a {@link psidev.psi.mi.jami.model.ParticipantPool} object.
     * @return a int.
     */
    public int compare(ParticipantPool participant1, ParticipantPool participant2) {
        int EQUAL = 0;
        int BEFORE = -1;
        int AFTER = 1;

        if (participant1 == participant2){
            return EQUAL;
        }
        else if (participant1 == null){
            return AFTER;
        }
        else if (participant2 == null){
            return BEFORE;
        }
        else {
            // ignore interactors
           participantBaseComparator.setIgnoreInteractors(true);
            // first compares basic participant properties
            int comp = participantBaseComparator.compare(participant1, participant2);
            if (comp != 0){
               return comp;
            }

            // then compares participant type
            comp = participantBaseComparator.getCvTermComparator().compare(participant1.getType(), participant2.getType());
            if (comp != 0){
                return comp;
            }

            // then compares participant candidates
            return entityCollectionComparator.compare(participant1, participant2);
        }
    }
}

package psidev.psi.mi.jami.utils.comparator.interaction;

import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.utils.comparator.CollectionComparator;
import psidev.psi.mi.jami.utils.comparator.participant.CustomizableModelledParticipantComparator;
import psidev.psi.mi.jami.utils.comparator.participant.ParticipantCollectionComparator;

import java.util.Collection;
import java.util.Comparator;

/**
 * Basic ModelledInteraction comparator.
 *
 * It will use a Comparator<Interaction> to compare basic interaction properties.
 * Then it will compare the modelledParticipants using CustomizableModelledParticipantComparator.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/01/13</pre>
 */
public class ModelledInteractionComparator implements Comparator<ModelledInteraction> {

    private Comparator<Interaction> interactionBaseComparator;
    private CollectionComparator<ModelledParticipant> participantCollectionComparator;

    /**
     * <p>Constructor for ModelledInteractionComparator.</p>
     *
     * @param participantComparator : required to compare participants
     * @param interactionComparator a {@link java.util.Comparator} object.
     */
    public ModelledInteractionComparator(CustomizableModelledParticipantComparator participantComparator, Comparator<Interaction> interactionComparator){
        if (interactionComparator == null){
            throw new IllegalArgumentException("The Interaction comparator is required to compare basic interaction properties. It cannot be null");
        }
        this.interactionBaseComparator = interactionComparator;
        if (participantComparator == null){
            throw new IllegalArgumentException("The participant comparator is required to compare participants of an interaction. It cannot be null");
        }
        this.participantCollectionComparator = new ParticipantCollectionComparator<ModelledParticipant>(participantComparator);

    }

    /**
     * <p>Constructor for ModelledInteractionComparator.</p>
     *
     * @param participantComparator a {@link psidev.psi.mi.jami.utils.comparator.CollectionComparator} object.
     * @param interactionComparator a {@link java.util.Comparator} object.
     */
    public ModelledInteractionComparator(CollectionComparator<ModelledParticipant> participantComparator, Comparator<Interaction> interactionComparator){
        if (interactionComparator == null){
            throw new IllegalArgumentException("The Interaction comparator is required to compare basic interaction properties. It cannot be null");
        }
        this.interactionBaseComparator = interactionComparator;
        if (participantComparator == null){
            throw new IllegalArgumentException("The participant comparator is required to compare participants of an interaction. It cannot be null");
        }
        this.participantCollectionComparator = participantComparator;

    }

    /**
     * <p>Getter for the field <code>participantCollectionComparator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.utils.comparator.CollectionComparator} object.
     */
    public CollectionComparator<ModelledParticipant> getParticipantCollectionComparator() {
        return participantCollectionComparator;
    }

    /**
     * <p>Getter for the field <code>interactionBaseComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<Interaction> getInteractionBaseComparator() {
        return interactionBaseComparator;
    }

    /**
     * It will use a Comparator<Interaction> to compare basic interaction properties.
     * Then it will compare the modelledParticipants using CustomizableModelledParticipantComparator.
     *
     * @param modelledInteraction1 a {@link psidev.psi.mi.jami.model.ModelledInteraction} object.
     * @param modelledInteraction2 a {@link psidev.psi.mi.jami.model.ModelledInteraction} object.
     * @return a int.
     */
    public int compare(ModelledInteraction modelledInteraction1, ModelledInteraction modelledInteraction2) {
        int EQUAL = 0;
        int BEFORE = -1;
        int AFTER = 1;

        if (modelledInteraction1 == modelledInteraction2){
            return 0;
        }
        else if (modelledInteraction1 == null){
            return AFTER;
        }
        else if (modelledInteraction2 == null){
            return BEFORE;
        }
        else {

            int comp = interactionBaseComparator.compare(modelledInteraction1, modelledInteraction2);
            if (comp != 0){
               return comp;
            }

            // first compares participants of an interaction
            Collection<? extends ModelledParticipant> participants1 = modelledInteraction1.getParticipants();
            Collection<? extends ModelledParticipant> participants2 = modelledInteraction2.getParticipants();

            return participantCollectionComparator.compare(participants1, participants2);
        }
    }
}

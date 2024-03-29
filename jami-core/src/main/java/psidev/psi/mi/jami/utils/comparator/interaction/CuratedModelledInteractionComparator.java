package psidev.psi.mi.jami.utils.comparator.interaction;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.utils.comparator.participant.CustomizableModelledParticipantComparator;

import java.util.Comparator;

/**
 * Basic CuratedModelledInteraction comparator.
 *
 * It will use a {@link java.util.Comparator} of type {@link psidev.psi.mi.jami.model.Interaction} to compare basic interaction properties.
 * Then it will compare the modelledParticipants using CustomizableModelledParticipantComparator.
 * Finally, it will compare the source of the modelledInteraction using AbstractCvTermComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>31/05/13</pre>
 */
public class CuratedModelledInteractionComparator extends ModelledInteractionComparator {

    private Comparator<CvTerm> sourceComparator;

    /**
     * <p>Constructor for CuratedModelledInteractionComparator.</p>
     *
     * @param participantComparator : required to compare participants
     * @param interactionComparator a {@link java.util.Comparator} object.
     * @param sourceComparator a {@link java.util.Comparator} object.
     */
    public CuratedModelledInteractionComparator(CustomizableModelledParticipantComparator participantComparator, Comparator<Interaction> interactionComparator, Comparator<CvTerm> sourceComparator) {
        super(participantComparator, interactionComparator);
        this.sourceComparator = sourceComparator;

    }

    /**
     * <p>Getter for the field <code>sourceComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<CvTerm> getSourceComparator() {
        return sourceComparator;
    }

    /**
     * It will use a {@link java.util.Comparator} of type {@link psidev.psi.mi.jami.model.Interaction} to compare basic interaction properties.
     * Then it will compare the modelledParticipants using CustomizableModelledParticipantComparator.
     * Finally, it will compare the source of the modelledInteraction using AbstractCvTermComparator
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

            int comp = super.compare(modelledInteraction1, modelledInteraction2);
            if (comp != 0){
                return comp;
            }

            // then compares source
            Source s1 = modelledInteraction1.getSource();
            Source s2 = modelledInteraction2.getSource();

            return sourceComparator.compare(s1, s2);
        }
    }
}

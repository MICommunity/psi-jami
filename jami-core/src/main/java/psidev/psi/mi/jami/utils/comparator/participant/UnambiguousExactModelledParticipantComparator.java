package psidev.psi.mi.jami.utils.comparator.participant;

import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.utils.comparator.interactor.UnambiguousExactComplexComparator;
import psidev.psi.mi.jami.utils.comparator.interactor.UnambiguousExactInteractorComparator;

/**
 * unambiguous exact biological participant comparator.
 * It will compare the basic properties of a biological participant using UnambiguousExactParticipantBaseComparator.
 * <p>
 * This comparator will ignore all the other properties of a biological participant.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/02/13</pre>
 */
public class UnambiguousExactModelledParticipantComparator extends ModelledParticipantComparator {

    private static UnambiguousExactModelledParticipantComparator defaultParticipantComparator;

    /**
     * {@inheritDoc}
     * <p>
     * Creates a new UnambiguousExactModelledParticipantComparator. It will use a UnambiguousExactParticipantBaseComparator to compare
     * the basic properties of a participant.
     */
    public UnambiguousExactModelledParticipantComparator() {
        super();
        UnambiguousExactInteractorComparator interactorComparator =
                new UnambiguousExactInteractorComparator(new UnambiguousExactComplexComparator(this));
        UnambiguousExactEntityBaseComparator baseComparator = new UnambiguousExactEntityBaseComparator(interactorComparator);
        setPoolComparator(new UnambiguousExactModelledParticipantPoolComparator(baseComparator));
    }

    /**
     * {@inheritDoc}
     *
     * Use UnambiguousExactModelledParticipantComparator to know if two biological participant are equals.
     *
     * @param component1 a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param component2 a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @return true if the two biological participant are equal
     */
    public static boolean areEquals(ModelledParticipant component1, ModelledParticipant component2) {
        if (defaultParticipantComparator == null) {
            defaultParticipantComparator = new UnambiguousExactModelledParticipantComparator();
        }

        return defaultParticipantComparator.compare(component1, component2) == 0;
    }

    @Override
    public UnambiguousExactModelledParticipantPoolComparator getParticipantPoolComparator() {
        return (UnambiguousExactModelledParticipantPoolComparator) super.getParticipantPoolComparator();
    }

    /**
     * It will compare the basic properties of a component using UnambiguousExactParticipantBaseComparator.
     *
     * This comparator will ignore all the other properties of a component.
     */
    @Override
    public int compare(ModelledParticipant component1, ModelledParticipant component2) {
        return super.compare(component1, component2);
    }
}

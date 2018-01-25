package psidev.psi.mi.jami.utils.comparator.interactor;

import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.utils.comparator.cv.UnambiguousCvTermComparator;
import psidev.psi.mi.jami.utils.comparator.participant.UnambiguousModelledParticipantComparator;
import psidev.psi.mi.jami.utils.comparator.participant.UnambiguousModelledParticipantInteractorComparator;

/**
 * Unambiguous Complex comparator
 * <p>
 * It will first look at the default properties of an interactor using UnambiguousInteractorBaseComparator.
 * It will then compare interaction types using UnambiguousCvTermComparator.
 * If the basic interactor properties are the same, It will first compare the collection of components using UnambiguousModelledParticipantComparator.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/01/13</pre>
 */
public class UnambiguousComplexComparator extends ComplexComparator {

    private static UnambiguousComplexComparator unambiguousComplexComparator;

    /**
     * {@inheritDoc}
     * <p>
     * Creates a new UnambiguousComplexComparator. It will use a UnambiguousInteractorBaseComparator, UnambiguousModelledParticipantComparator to
     * compares components.
     */
    public UnambiguousComplexComparator() {
        super(new UnambiguousInteractorBaseComparator(), new UnambiguousModelledParticipantComparator(), new UnambiguousCvTermComparator());
    }

    /**
     * <p>Constructor for UnambiguousComplexComparator.</p>
     *
     * @param comparator a {@link psidev.psi.mi.jami.utils.comparator.participant.UnambiguousModelledParticipantComparator} object.
     */
    public UnambiguousComplexComparator(UnambiguousModelledParticipantComparator comparator) {
        super(new UnambiguousInteractorBaseComparator(), comparator != null ? comparator : new UnambiguousModelledParticipantComparator(), new UnambiguousCvTermComparator());
    }

    /**
     * <p>Constructor for UnambiguousComplexComparator.</p>
     *
     * @param comparator a {@link psidev.psi.mi.jami.utils.comparator.participant.UnambiguousModelledParticipantInteractorComparator} object.
     */
    public UnambiguousComplexComparator(UnambiguousModelledParticipantInteractorComparator comparator) {
        super(new UnambiguousInteractorBaseComparator(), comparator != null ? comparator : new UnambiguousModelledParticipantInteractorComparator(), new UnambiguousCvTermComparator());
    }

    /**
     * Use UnambiguousComplexComparator to know if two complexes are equals.
     *
     * @param complex1 a {@link psidev.psi.mi.jami.model.Complex} object.
     * @param complex2 a {@link psidev.psi.mi.jami.model.Complex} object.
     * @return true if the two complexes are equal
     */
    public static boolean areEquals(Complex complex1, Complex complex2) {
        if (unambiguousComplexComparator == null) {
            unambiguousComplexComparator = new UnambiguousComplexComparator();
        }

        return unambiguousComplexComparator.compare(complex1, complex2) == 0;
    }

    /**
     * {@inheritDoc}
     *
     * It will first look at the default properties of an interactor using UnambiguousInteractorBaseComparator.
     * It will then compare interaction types using UnambiguousCvTermComparator.
     * If the basic interactor properties are the same, It will first compare the collection of components using UnambiguousModelledParticipantComparator.
     */
    @Override
    public int compare(Complex complex1, Complex complex2) {
        return super.compare(complex1, complex2);
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousInteractorBaseComparator getInteractorBaseComparator() {
        return (UnambiguousInteractorBaseComparator) super.getInteractorBaseComparator();
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousCvTermComparator getCvTermComparator() {
        return (UnambiguousCvTermComparator) super.getCvTermComparator();
    }
}

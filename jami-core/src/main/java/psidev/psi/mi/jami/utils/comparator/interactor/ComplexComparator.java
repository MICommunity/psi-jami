package psidev.psi.mi.jami.utils.comparator.interactor;

import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.utils.comparator.participant.CustomizableModelledParticipantComparator;
import psidev.psi.mi.jami.utils.comparator.participant.ModelledParticipantCollectionComparator;

import java.util.Collection;
import java.util.Comparator;

/**
 * Basic ComplexComparator.
 *
 * It will first look at the default properties of an interactor using AbstractInteractorBaseComparator.
 * It will then compare the interaction types using AbstractCvtermComparator
 * If the basic interactor properties are the same, It will first compare the collection of components using ModelledParticipantComparator.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/01/13</pre>
 */
public class ComplexComparator implements Comparator<Complex> {

    private Comparator<Interactor> interactorBaseComparator;
    private ModelledParticipantCollectionComparator componentCollectionComparator;
    private Comparator<CvTerm> cvTermComparator;

    /**
     * Creates a bew ComplexComparator. It needs a AbstractInteractorBaseComparator to compares interactor properties
     *
     * @param interactorBaseComparator a {@link java.util.Comparator} object.
     * @param componentComparator a {@link psidev.psi.mi.jami.utils.comparator.participant.CustomizableModelledParticipantComparator} object.
     * @param cvTermComparator a {@link java.util.Comparator} object.
     */
    public ComplexComparator(Comparator<Interactor> interactorBaseComparator, CustomizableModelledParticipantComparator componentComparator, Comparator<CvTerm> cvTermComparator){

        if (componentComparator == null){
            throw new IllegalArgumentException("The ModelledParticipant comparator is required to compare participants composing the complexes. It cannot be null");
        }
        this.componentCollectionComparator = new ModelledParticipantCollectionComparator(componentComparator);

        if (interactorBaseComparator == null){
            throw new IllegalArgumentException("The comparator<Interactor> is required to compare participants composing the complexes. It cannot be null");
        }
        this.interactorBaseComparator = interactorBaseComparator;

        if (cvTermComparator == null){
            throw new IllegalArgumentException("The CvTermComparator comparator is required to compare the interaction type of a complex. It cannot be null");
        }
        this.cvTermComparator = cvTermComparator;
    }

    /**
     * Creates a bew ComplexComparator. It needs a AbstractInteractorBaseComparator to compares interactor properties
     *
     * @param interactorBaseComparator a {@link java.util.Comparator} object.
     * @param componentComparator a {@link psidev.psi.mi.jami.utils.comparator.participant.ModelledParticipantCollectionComparator} object.
     * @param cvTermComparator a {@link java.util.Comparator} object.
     */
    public ComplexComparator(Comparator<Interactor> interactorBaseComparator, ModelledParticipantCollectionComparator componentComparator,
                             Comparator<CvTerm> cvTermComparator){

        if (componentComparator == null){
            throw new IllegalArgumentException("The ModelledParticipant comparator is required to compare participants composing the complexes. It cannot be null");
        }
        this.componentCollectionComparator = componentComparator;

        if (interactorBaseComparator == null){
            throw new IllegalArgumentException("The comparator<Interactor> is required to compare participants composing the complexes. It cannot be null");
        }
        this.interactorBaseComparator = interactorBaseComparator;

        if (cvTermComparator == null){
            throw new IllegalArgumentException("The CvTermComparator comparator is required to compare the interaction type of a complex. It cannot be null");
        }
        this.cvTermComparator = cvTermComparator;
    }

    /**
     * It will first look at the default properties of an interactor using AbstractInteractorBaseComparator.
     * It will then compare the interaction types using AbstractCvtermComparator
     * If the basic interactor properties are the same, It will first compare the collection of components using ComponentComparator.
     *
     * @param complex1 a {@link psidev.psi.mi.jami.model.Complex} object.
     * @param complex2 a {@link psidev.psi.mi.jami.model.Complex} object.
     * @return a int.
     */
    public int compare(Complex complex1, Complex complex2) {
        int EQUAL = 0;
        int BEFORE = -1;
        int AFTER = 1;

        if (complex1 == complex2){
            return 0;
        }
        else if (complex1 == null){
            return AFTER;
        }
        else if (complex2 == null){
            return BEFORE;
        }
        else {

            this.componentCollectionComparator.getObjectComparator().setCheckComplexesAsInteractors(false);

            // compares the basic interactor properties first
            int comp = interactorBaseComparator.compare(complex1, complex2);
            if (comp != 0){
                return comp;
            }

            // compares the interaction type
            CvTerm type1 = complex1.getInteractionType();
            CvTerm type2 = complex2.getInteractionType();

            comp = cvTermComparator.compare(type1, type2);
            if (comp != 0){
                return comp;
            }

            // then compares collection of components
            Collection<ModelledParticipant> components1 = complex1.getParticipants();
            Collection<ModelledParticipant> components2 = complex2.getParticipants();

            return componentCollectionComparator.compare(components1, components2);
        }
    }

    /**
     * <p>Getter for the field <code>interactorBaseComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<Interactor> getInteractorBaseComparator() {
        return interactorBaseComparator;
    }

    /**
     * <p>Getter for the field <code>componentCollectionComparator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.utils.comparator.participant.ModelledParticipantCollectionComparator} object.
     */
    public ModelledParticipantCollectionComparator getComponentCollectionComparator() {
        return componentCollectionComparator;
    }

    /**
     * <p>Getter for the field <code>cvTermComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<CvTerm> getCvTermComparator() {
        return cvTermComparator;
    }
}

package psidev.psi.mi.jami.listener;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.Organism;


/**
 * A generic listener for an interactor.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 08/08/13
 * @version $Id: $
 */
public interface InteractorChangeListener<T extends Interactor>
        extends AliasesChangeListener<T>, XrefsChangeListener<T>, AnnotationsChangeListener<T>, IdentifiersChangeListener<T>,
                ChecksumsChangeListener<T>{

    /**
     * Listens for the event where the shortName has been changed.
     *
     * @param interactor        The interactor which has changed.
     * @param oldShortName      The old shortName.
     */
    public void onShortNameUpdate(T interactor , String oldShortName);

    /**
     * Listens for the event where the fullName has been changed.
     *
     * @param interactor        The interactor which has changed.
     * @param oldFullName       The old fullName.
     */
    public void onFullNameUpdate(T interactor , String oldFullName);

    /**
     * Listen to the event where the organism of a interactor has been initialised.
     * This event happens when a interactor does not have any organisms
     *
     * @param interactor        The interactor which has changed.
     * @param oldOrganism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public void onOrganismUpdate(T interactor, Organism oldOrganism);

    /**
     * Listen to the event where the interactor type has been initialised.
     * This event happens when a interactor does not have any interactor types
     *
     * @param interactor        The interactor which has changed.
     * @param oldType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public void onInteractorTypeUpdate(T interactor, CvTerm oldType);
}

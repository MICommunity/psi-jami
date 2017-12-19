package psidev.psi.mi.jami.listener;

import psidev.psi.mi.jami.model.Alias;

import java.util.EventListener;


/**
 * A listener for changes to a bioactiveEntity.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 08/08/13
 * @version $Id: $
 */
public interface AliasesChangeListener<T extends Object> extends EventListener {

    /**
     * Listen to the event where an alias has been added to the object aliases.
     *
     * @param o        The object which has changed.
     * @param added             The added alias.
     * @param <T> a T object.
     */
    public void onAddedAlias(T o , Alias added);

    /**
     * Listen to the event where an alias has been removed from the object aliases.
     *
     * @param o        The object which has changed.
     * @param removed           The removed alias.
     */
    public void onRemovedAlias(T o , Alias removed);
}

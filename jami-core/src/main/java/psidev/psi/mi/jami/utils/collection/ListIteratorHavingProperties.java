package psidev.psi.mi.jami.utils.collection;

import java.util.ListIterator;

/**
 * Iterator for list having properties
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/01/15</pre>
 */
public class ListIteratorHavingProperties<T> extends IteratorHavingProperties<T> implements ListIterator<T> {

    private T lastObject;

    /**
     * <p>Constructor for ListIteratorHavingProperties.</p>
     *
     * @param originalCollection a {@link psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties} object.
     * @param originalIterator a {@link java.util.ListIterator} object.
     * @param <T> a T object.
     */
    public ListIteratorHavingProperties(AbstractListHavingProperties<T> originalCollection, ListIterator<T> originalIterator){
        super(originalCollection, originalIterator);
    }

    /**
     * <p>hasPrevious</p>
     *
     * @return a boolean.
     */
    public boolean hasPrevious() {
        return getOriginalIterator().hasPrevious();
    }

    /**
     * <p>previous</p>
     *
     * @return a T object.
     */
    public T previous() {
        this.lastObject = getOriginalIterator().previous();
        return this.lastObject;
    }

    /**
     * <p>nextIndex</p>
     *
     * @return a int.
     */
    public int nextIndex() {
        return getOriginalIterator().nextIndex();
    }

    /**
     * <p>previousIndex</p>
     *
     * @return a int.
     */
    public int previousIndex() {
        return getOriginalIterator().previousIndex();
    }

    /**
     * <p>set</p>
     *
     * @param t a T object.
     */
    public void set(T t) {
        getOriginalCollection().processRemovedObjectEvent(this.lastObject);
        getOriginalIterator().set(t);
        getOriginalCollection().processAddedObjectEvent(this.lastObject);
    }

    /**
     * <p>add</p>
     *
     * @param t a T object.
     */
    public void add(T t) {
        getOriginalIterator().add(t);
        getOriginalCollection().processAddedObjectEvent(t);
    }

    /**
     * <p>getOriginalIterator</p>
     *
     * @return a {@link java.util.ListIterator} object.
     */
    protected ListIterator<T> getOriginalIterator() {
        return (ListIterator<T>)super.getOriginalIterator();
    }

    /** {@inheritDoc} */
    @Override
    public T next() {
        this.lastObject = super.next();
        return this.lastObject;
    }
}

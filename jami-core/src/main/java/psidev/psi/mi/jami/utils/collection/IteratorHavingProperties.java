package psidev.psi.mi.jami.utils.collection;

import java.util.Iterator;

/**
 * Iterator for list having properties
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/01/15</pre>
 */
public class IteratorHavingProperties<T> implements Iterator<T> {

    private Iterator<T> originalIterator;
    private AbstractListHavingProperties<T> originalCollection;

    private T currentObject;

    /**
     * <p>Constructor for IteratorHavingProperties.</p>
     *
     * @param originalCollection a {@link psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties} object.
     * @param originalIterator a {@link java.util.Iterator} object.
     * @param <T> a T object.
     */
    public IteratorHavingProperties(AbstractListHavingProperties<T> originalCollection, Iterator<T> originalIterator){
        if (originalCollection == null){
            throw new IllegalArgumentException("The original collection cannot be null");
        }
        if (originalIterator == null){
            throw new IllegalArgumentException("The original iterator cannot be null");
        }
        this.originalCollection = originalCollection;
        this.originalIterator = originalIterator;
    }

    /**
     * <p>hasNext</p>
     *
     * @return a boolean.
     */
    public boolean hasNext() {
        return this.originalIterator.hasNext();
    }

    /**
     * <p>next</p>
     *
     * @return a T object.
     */
    public T next() {
        this.currentObject = this.originalIterator.next();
        return currentObject;
    }

    /**
     * <p>remove</p>
     */
    public void remove() {
        this.originalIterator.remove();
        this.originalCollection.processRemovedObjectEvent(this.currentObject);
    }

    /**
     * <p>Getter for the field <code>originalIterator</code>.</p>
     *
     * @return a {@link java.util.Iterator} object.
     */
    protected Iterator<T> getOriginalIterator() {
        return originalIterator;
    }

    /**
     * <p>Getter for the field <code>originalCollection</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties} object.
     */
    protected AbstractListHavingProperties<T> getOriginalCollection() {
        return originalCollection;
    }

    /**
     * <p>Getter for the field <code>currentObject</code>.</p>
     *
     * @return a T object.
     */
    protected T getCurrentObject() {
        return currentObject;
    }
}

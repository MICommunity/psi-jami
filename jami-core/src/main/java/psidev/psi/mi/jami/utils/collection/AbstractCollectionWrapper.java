package psidev.psi.mi.jami.utils.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Abstract list which is updating some properties when adding/removing elements.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/02/13</pre>
 */
public abstract class AbstractCollectionWrapper<T> implements Collection<T> {
    private Collection<T> wrappedList;

    /**
     * <p>Constructor for AbstractCollectionWrapper.</p>
     *
     * @param list a {@link java.util.Collection} object.
     */
    public AbstractCollectionWrapper(Collection<T> list){
        super();
        if (list == null){
            this.wrappedList = new ArrayList<T>();
        }
        else{
            this.wrappedList = list;
        }
    }

    /**
     * <p>size</p>
     *
     * @return a int.
     */
    public int size() {
        return this.wrappedList.size();
    }

    /**
     * <p>isEmpty</p>
     *
     * @return a boolean.
     */
    public boolean isEmpty() {
        return this.wrappedList.isEmpty();
    }

    /** {@inheritDoc} */
    public boolean contains(Object o) {
        return this.wrappedList.contains(o);
    }

    /**
     * <p>iterator</p>
     *
     * @return a {@link java.util.Iterator} object.
     */
    public Iterator<T> iterator() {
        return new IteratorWrapper<T>(this, this.wrappedList.iterator());
    }

    /**
     * <p>toArray</p>
     *
     * @return an array of {@link java.lang.Object} objects.
     */
    public Object[] toArray() {
        return this.wrappedList.toArray();
    }

    /**
     * <p>toArray</p>
     *
     * @param a an array of T objects.
     * @param <T> a T object.
     * @return an array of T objects.
     */
    public <T> T[] toArray(T[] a) {
        return this.wrappedList.toArray(a);
    }

    /**
     * <p>add</p>
     *
     * @param xref a T object.
     * @return a boolean.
     */
    public boolean add(T xref) {
        if (needToPreProcessElementToAdd(xref)){
            T clone = processOrWrapElementToAdd(xref);
            return this.wrappedList.add(clone);
        }
        else {
            return this.wrappedList.add(xref);
        }
    }

    /** {@inheritDoc} */
    public boolean remove(Object o) {
        if (needToPreProcessElementToRemove(o)){
            processElementToRemove(o);
            return this.wrappedList.remove(o);
        }
        else {
            return this.wrappedList.remove(o);
        }
    }

    /** {@inheritDoc} */
    public boolean containsAll(Collection<?> c) {
        return this.wrappedList.containsAll(c);
    }

    /** {@inheritDoc} */
    public boolean addAll(Collection<? extends T> c) {
        boolean hasChanged = false;
        for (T xref : c){
            if (add(xref)){
                hasChanged = true;
            }
        }
        return hasChanged;
    }

    /** {@inheritDoc} */
    public boolean removeAll(Collection<?> c) {
        boolean hasRemoved = false;
        for (Object xref : c){
            if (remove(xref)){
                hasRemoved = true;
            }
        }
        return hasRemoved;
    }

    /** {@inheritDoc} */
    public boolean retainAll(Collection<?> c) {
        Iterator<T> iterator = this.wrappedList.iterator();
        boolean hasChanged = false;
        while (iterator.hasNext()){
            if (!c.contains(iterator.next())){
               iterator.remove();
               hasChanged = true;
            }
        }
        return hasChanged;
    }

    /**
     * <p>clear</p>
     */
    public void clear() {
        for (T ref : this.wrappedList){
            if (needToPreProcessElementToRemove(ref)){
                processElementToRemove(ref);
            }
        }
        this.wrappedList.clear();
    }

    /**
     * <p>Getter for the field <code>wrappedList</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<T> getWrappedList() {
        return wrappedList;
    }

    /**
     * Method to know if an element to add needs some processing or being wrapped
     *
     * @param added : element that will be added to the collection
     * @return true if we need to process/ wrap the element that will be added
     */
    protected abstract boolean needToPreProcessElementToAdd(T added);

    /**
     * Method to process or wrap an element to be added to the list
     *
     * @param added : element that will be added to the collection
     * @return the processed/wrapped element that will be added
     */
    protected abstract T processOrWrapElementToAdd(T added);

    /**
     * <p>processElementToRemove</p>
     *
     * @param o a {@link java.lang.Object} object.
     */
    protected abstract void processElementToRemove(Object o);

    /**
     * <p>needToPreProcessElementToRemove</p>
     *
     * @param o a {@link java.lang.Object} object.
     * @return a boolean.
     */
    protected abstract boolean needToPreProcessElementToRemove(Object o);
}

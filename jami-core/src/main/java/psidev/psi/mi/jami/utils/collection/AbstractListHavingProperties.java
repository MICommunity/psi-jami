package psidev.psi.mi.jami.utils.collection;

import java.util.*;

/**
 * Abstract list which is updating some properties when adding/removing elements.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/02/13</pre>
 */
public abstract class AbstractListHavingProperties<T> extends ArrayList<T> {

    /**
     * <p>Constructor for AbstractListHavingProperties.</p>
     *
     * @param <T> a T object.
     */
    public AbstractListHavingProperties(){
        super();
    }

    /** {@inheritDoc} */
    @Override
    public boolean add(T object) {
        boolean added = super.add(object);

        if (added && object != null){
            processAddedObjectEvent(object);
            return true;
        }

        return false;
    }

    /**
     * <p>processAddedObjectEvent</p>
     *
     * @param added a T object.
     */
    protected abstract void processAddedObjectEvent(T added);

    /** {@inheritDoc} */
    @Override
    public boolean remove(Object o) {
        if (super.remove(o)){
            // we have nothing left, reset standard values
            if (isEmpty()){
                clearProperties();
            }
            else if (o != null){
                processRemovedObjectEvent((T) o);
            }

            return true;
        }
        return false;
    }

    /**
     * <p>processRemovedObjectEvent</p>
     *
     * @param removed a T object.
     */
    protected abstract void processRemovedObjectEvent(T removed);

    /** {@inheritDoc} */
    @Override
    public void clear() {
        super.clear();
        clearProperties();
    }

    /**
     * <p>clearProperties</p>
     */
    protected abstract void clearProperties();

    /** {@inheritDoc} */
    @Override
    public void add(int i, T t) {
        super.add(i, t);
        if (t != null){
            processAddedObjectEvent(t);
        }
    }

    /** {@inheritDoc} */
    @Override
    public T remove(int i) {
        T removed = super.remove(i);
        if (removed != null){
            processRemovedObjectEvent(removed);
        }
        return removed;
    }

    /** {@inheritDoc} */
    @Override
    public boolean retainAll(Collection<?> objects) {
        List<T> existingObject = new ArrayList<T>(this);

        boolean removed = false;
        for (T o : existingObject){
            if (!objects.contains(o)){
                if (remove(o)){
                    removed = true;
                }
            }
        }

        return removed;
    }

    /** {@inheritDoc} */
    @Override
    public boolean removeAll(Collection<?> objects) {
        boolean removed = super.removeAll(objects);

        for (Object o : objects){
            if (o != null){
                processRemovedObjectEvent((T)o);
            }
        }

        return removed;
    }

    /** {@inheritDoc} */
    @Override
    protected void removeRange(int i, int i2) {
        if (i != i2){
            for (int j = i; j < i2; j++){
                remove(j);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean addAll(int i, Collection<? extends T> ts) {

        boolean added = super.addAll(i, ts);

        if (added){
            for (T object : ts){
                if (object != null){
                    processAddedObjectEvent(object);
                }
            }
        }

        return added;
    }

    /** {@inheritDoc} */
    @Override
    public boolean addAll(Collection<? extends T> ts) {
        boolean added = super.addAll(ts);

        if (added){
            for (T object : ts){
                if (object != null){
                    processAddedObjectEvent(object);
                }
            }
        }

        return added;
    }

    /** {@inheritDoc} */
    @Override
    public T set(int i, T t) {
        T removed = super.set(i, t);
        if (removed != null){
            processRemovedObjectEvent(removed);
        }
        if (t != null){
            processAddedObjectEvent(t);
        }

        return removed;
    }

    /**
     * <p>addOnly</p>
     *
     * @param i a int.
     * @param object a T object.
     */
    public void addOnly(int i, T object) {
        super.add(i, object);
    }

    /**
     * <p>addOnly</p>
     *
     * @param object a T object.
     * @return a boolean.
     */
    public boolean addOnly(T object) {
        return super.add(object);
    }

    /**
     * <p>removeOnly</p>
     *
     * @param o a {@link java.lang.Object} object.
     * @return a boolean.
     */
    public boolean removeOnly(Object o) {
        return super.remove(o);
    }

    /**
     * <p>clearOnly</p>
     */
    public void clearOnly() {
        super.clear();
    }

    /**
     * <p>retainAllOnly</p>
     *
     * @param objects a {@link java.util.Collection} object.
     * @return a boolean.
     */
    public boolean retainAllOnly(Collection<?> objects) {
        return super.retainAll(objects);
    }

    /**
     * <p>removeOnly</p>
     *
     * @param i a int.
     * @return a T object.
     */
    public T removeOnly(int i) {
        return super.remove(i);
    }

    /**
     * <p>removeAllOnly</p>
     *
     * @param objects a {@link java.util.Collection} object.
     * @return a boolean.
     */
    public boolean removeAllOnly(Collection<?> objects) {
        return super.removeAll(objects);
    }

    /**
     * <p>removeRangeOnly</p>
     *
     * @param i a int.
     * @param i2 a int.
     */
    protected void removeRangeOnly(int i, int i2) {
        super.removeRange(i, i2);
    }

    /**
     * <p>addAllOnly</p>
     *
     * @param i a int.
     * @param ts a {@link java.util.Collection} object.
     * @return a boolean.
     */
    public boolean addAllOnly(int i, Collection<? extends T> ts) {

        return super.addAll(i, ts);
    }

    /**
     * <p>addAllOnly</p>
     *
     * @param ts a {@link java.util.Collection} object.
     * @return a boolean.
     */
    public boolean addAllOnly(Collection<? extends T> ts) {
        return super.addAll(ts);
    }

    /**
     * <p>setOnly</p>
     *
     * @param i a int.
     * @param t a T object.
     * @return a T object.
     */
    public T setOnly(int i, T t) {
        return super.set(i, t);
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<T> iterator() {
        return new IteratorHavingProperties<T>(this, super.iterator());
    }

    /** {@inheritDoc} */
    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIteratorHavingProperties<T>(this, super.listIterator(index));
    }

    /** {@inheritDoc} */
    @Override
    public ListIterator<T> listIterator() {
        return new ListIteratorHavingProperties<T>(this, super.listIterator());
    }
}

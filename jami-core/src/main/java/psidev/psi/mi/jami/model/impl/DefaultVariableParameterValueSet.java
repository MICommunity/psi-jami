package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.VariableParameterValue;
import psidev.psi.mi.jami.model.VariableParameterValueSet;
import psidev.psi.mi.jami.utils.comparator.experiment.VariableParameterValueSetComparator;

import java.util.*;

/**
 * Default implementation for VariableParameterValueSet
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with VariableParameterValueSetComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/05/13</pre>
 */
public class DefaultVariableParameterValueSet implements VariableParameterValueSet{

    private Set<VariableParameterValue> variableParameterValues;

    /**
     * <p>Constructor for DefaultVariableParameterValueSet.</p>
     */
    public DefaultVariableParameterValueSet(){
        initialiseVatiableParameterValuesSet();
    }

    /**
     * <p>initialiseVatiableParameterValuesSet</p>
     */
    protected void initialiseVatiableParameterValuesSet(){
        this.variableParameterValues = new HashSet<VariableParameterValue>();
    }

    /**
     * <p>initialiseVatiableParameterValuesSetWith</p>
     *
     * @param paramValues a {@link java.util.Set} object.
     */
    protected void initialiseVatiableParameterValuesSetWith(Set<VariableParameterValue> paramValues){
        if (paramValues == null){
            this.variableParameterValues = Collections.EMPTY_SET;
        }
        else {
            this.variableParameterValues = paramValues;
        }
    }

    /**
     * <p>size</p>
     *
     * @return a int.
     */
    public int size() {
        return variableParameterValues.size();
    }

    /**
     * <p>isEmpty</p>
     *
     * @return a boolean.
     */
    public boolean isEmpty() {
        return variableParameterValues.isEmpty();
    }

    /** {@inheritDoc} */
    public boolean contains(Object o) {
        return variableParameterValues.contains(o);
    }

    /**
     * <p>iterator</p>
     *
     * @return a {@link java.util.Iterator} object.
     */
    public Iterator<VariableParameterValue> iterator() {
        return variableParameterValues.iterator();
    }

    /**
     * <p>toArray</p>
     *
     * @return an array of {@link java.lang.Object} objects.
     */
    public Object[] toArray() {
        return variableParameterValues.toArray();
    }

    /**
     * <p>toArray</p>
     *
     * @param ts an array of T objects.
     * @param <T> a T object.
     * @return an array of T objects.
     */
    public <T> T[] toArray(T[] ts) {
        return variableParameterValues.toArray(ts);
    }

    /**
     * <p>add</p>
     *
     * @param variableParameterValue a {@link psidev.psi.mi.jami.model.VariableParameterValue} object.
     * @return a boolean.
     */
    public boolean add(VariableParameterValue variableParameterValue) {
        return variableParameterValues.add(variableParameterValue);
    }

    /** {@inheritDoc} */
    public boolean remove(Object o) {
        return variableParameterValues.remove(o);
    }

    /** {@inheritDoc} */
    public boolean containsAll(Collection<?> objects) {
        return variableParameterValues.containsAll(objects);
    }

    /** {@inheritDoc} */
    public boolean addAll(Collection<? extends VariableParameterValue> variableParameterValues) {
        return this.variableParameterValues.addAll(variableParameterValues);
    }

    /** {@inheritDoc} */
    public boolean retainAll(Collection<?> objects) {
        return variableParameterValues.retainAll(objects);
    }

    /** {@inheritDoc} */
    public boolean removeAll(Collection<?> objects) {
        return variableParameterValues.removeAll(objects);
    }

    /**
     * <p>clear</p>
     */
    public void clear() {
        variableParameterValues.clear();
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof VariableParameterValueSet)){
            return false;
        }

        return VariableParameterValueSetComparator.areEquals(this, (VariableParameterValueSet) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return VariableParameterValueSetComparator.hashCode(this);
    }
}

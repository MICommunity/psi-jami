package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.Stoichiometry;
import psidev.psi.mi.jami.utils.comparator.participant.StoichiometryComparator;

/**
 * Default implementation for stoichiometry
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with StoichiometryComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/05/13</pre>
 */
public class DefaultStoichiometry implements Stoichiometry {

    private int minValue;
    private int maxValue;

    public DefaultStoichiometry() {
    }

    /**
     * <p>Constructor for DefaultStoichiometry.</p>
     *
     * @param value a int.
     */
    public DefaultStoichiometry(int value){

        this.minValue = value;
        this.maxValue = value;
    }

    /**
     * <p>Constructor for DefaultStoichiometry.</p>
     *
     * @param minValue a int.
     * @param maxValue a int.
     */
    public DefaultStoichiometry(int minValue, int maxValue){
        if (minValue > maxValue){
           throw new IllegalArgumentException("The minValue " + minValue + " cannot be bigger than the maxValue " + maxValue);
        }

        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * <p>Getter for the field <code>minValue</code>.</p>
     *
     * @return a int.
     */
    public int getMinValue() {
        return this.minValue;
    }

    /**
     * <p>Getter for the field <code>maxValue</code>.</p>
     *
     * @return a int.
     */
    public int getMaxValue() {
        return this.maxValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {

        if (this == o){
            return true;
        }

        if (!(o instanceof Stoichiometry)){
            return false;
        }

        return StoichiometryComparator.areEquals(this, (Stoichiometry) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return StoichiometryComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "minValue: " + getMinValue() + ", maxValue: " + getMaxValue();
    }
}

package psidev.psi.mi.jami.model;

import psidev.psi.mi.jami.utils.comparator.parameter.ParameterValueComparator;

import java.math.BigDecimal;

/**
 * A parameter numeric value.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>26/11/12</pre>
 */
public class ParameterValue extends Number{

    private short base=10;
    private BigDecimal factor;
    private short exponent=0;

    /**
     * <p>Constructor for ParameterValue.</p>
     *
     * @param factor a {@link java.math.BigDecimal} object.
     * @param base a short.
     * @param exponent a short.
     */
    public ParameterValue(BigDecimal factor, short base, short exponent){
        if (factor == null){
            throw new IllegalArgumentException("The factor is required and cannot be null");
        }
        this.base = base;
        this.factor = factor;
        this.exponent = exponent;
    }

    /**
     * <p>Constructor for ParameterValue.</p>
     *
     * @param value a {@link java.math.BigDecimal} object.
     */
    public ParameterValue(BigDecimal value){
        if (value == null){
            throw new IllegalArgumentException("The value is required and cannot be null");
        }
        this.base = 10;
        this.factor = value;
        this.exponent = 0;
    }

    /** {@inheritDoc} */
    @Override
    public int intValue() {
        return factor.multiply(BigDecimal.valueOf(Math.pow(base, exponent))).intValue();
    }

    /** {@inheritDoc} */
    @Override
    public long longValue() {
        return factor.multiply(BigDecimal.valueOf(Math.pow(base, exponent))).longValue();
    }

    /** {@inheritDoc} */
    @Override
    public float floatValue() {
        return factor.multiply(BigDecimal.valueOf(Math.pow(base, exponent))).floatValue();
    }

    /** {@inheritDoc} */
    @Override
    public double doubleValue() {
        return factor.multiply(BigDecimal.valueOf(Math.pow(base, exponent))).doubleValue();
    }

    /**
     * Base of the parameter expression. Defaults to 10.
     *
     * @return the base
     */
    public short getBase() {
        return base;
    }

    /**
     * The "main" value of the parameter.
     *
     * @return the factor
     */
    public BigDecimal getFactor() {
        return factor;
    }

    /**
     * Exponent of the base. By default is 0.
     *
     * @return the exponent
     */
    public short getExponent() {
        return exponent;
    }

    /**
     * <p>toString</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String toString(){
        return (base != 0 && factor.doubleValue() != 0 ? factor.toString()+(exponent != 0 ? "x"+base+"^("+exponent+")" : "") : "0");
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof ParameterValue)){
            return false;
        }

        return ParameterValueComparator.areEquals(this, (ParameterValue) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return ParameterValueComparator.hashCode(this);
    }
}

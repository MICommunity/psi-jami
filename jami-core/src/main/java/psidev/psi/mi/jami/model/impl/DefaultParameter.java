package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.exception.IllegalParameterException;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Parameter;
import psidev.psi.mi.jami.model.ParameterValue;
import psidev.psi.mi.jami.utils.ParameterUtils;
import psidev.psi.mi.jami.utils.comparator.parameter.UnambiguousParameterComparator;

import java.math.BigDecimal;

/**
 * Default implementation for Parameter
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with UnambiguousParameterComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/01/13</pre>
 */
public class DefaultParameter implements Parameter {

    private CvTerm type;
    private BigDecimal uncertainty;
    private CvTerm unit;
    private ParameterValue value;

    /**
     * <p>Constructor for DefaultParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     */
    public DefaultParameter(CvTerm type, ParameterValue value){
        if (type == null){
            throw new IllegalArgumentException("The parameter type is required and cannot be null");
        }
        this.type = type;
        if (value == null){
            throw new IllegalArgumentException("The parameter value is required and cannot be null");
        }
        this.value = value;
    }

    /**
     * <p>Constructor for DefaultParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     * @param unit a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultParameter(CvTerm type, ParameterValue value, CvTerm unit){
        this(type, value);
        this.unit = unit;
    }

    /**
     * <p>Constructor for DefaultParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     * @param unit a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uncertainty a {@link java.math.BigDecimal} object.
     */
    public DefaultParameter(CvTerm type, ParameterValue value, CvTerm unit, BigDecimal uncertainty){
        this(type, value, unit);
        this.uncertainty = uncertainty;
    }

    /**
     * <p>Constructor for DefaultParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     * @param uncertainty a {@link java.math.BigDecimal} object.
     */
    public DefaultParameter(CvTerm type, ParameterValue value, BigDecimal uncertainty){
        this(type, value);
        this.uncertainty = uncertainty;
    }

    /**
     * <p>Constructor for DefaultParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     * @throws psidev.psi.mi.jami.exception.IllegalParameterException if any.
     */
    public DefaultParameter(CvTerm type, String value) throws IllegalParameterException {
        if (type == null){
            throw new IllegalArgumentException("The parameter type is required and cannot be null");
        }
        this.type = type;

        Parameter param = ParameterUtils.createParameterFromString(type, value);
        this.value = param.getValue();
        this.uncertainty = param.getUncertainty();
    }

    /**
     * <p>Constructor for DefaultParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     * @param unit a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @throws psidev.psi.mi.jami.exception.IllegalParameterException if any.
     */
    public DefaultParameter(CvTerm type, String value, CvTerm unit) throws IllegalParameterException {
        this(type, value);
        this.unit = unit;
    }

    /**
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getType() {
        return this.type;
    }

    /**
     * <p>Getter for the field <code>uncertainty</code>.</p>
     *
     * @return a {@link java.math.BigDecimal} object.
     */
    public BigDecimal getUncertainty() {
        return this.uncertainty;
    }

    /**
     * <p>Getter for the field <code>unit</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getUnit() {
        return this.unit;
    }

    /**
     * <p>Getter for the field <code>value</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     */
    public ParameterValue getValue() {
        return this.value;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof Parameter)){
            return false;
        }

        return UnambiguousParameterComparator.areEquals(this, (Parameter) o);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getType().toString() + ": " + getValue()
                + (getUncertainty() != null ? " ~" + getUncertainty().toString() : ""
                + (getUnit() != null ? "("+getUnit().toString()+")" : ""));
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousParameterComparator.hashCode(this);
    }
}

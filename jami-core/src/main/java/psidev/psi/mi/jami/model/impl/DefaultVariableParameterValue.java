package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.VariableParameter;
import psidev.psi.mi.jami.model.VariableParameterValue;
import psidev.psi.mi.jami.utils.comparator.experiment.VariableParameterValueComparator;

/**
 * Default implementation for variableValue.
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with VariableParameterValueComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/05/13</pre>
 */
public class DefaultVariableParameterValue implements VariableParameterValue {

    private String value;
    private Integer order;
    private VariableParameter variableParameter;

    /**
     * <p>Constructor for DefaultVariableParameterValue.</p>
     *
     * @param value a {@link java.lang.String} object.
     * @param variableParameter a {@link psidev.psi.mi.jami.model.VariableParameter} object.
     */
    public DefaultVariableParameterValue(String value, VariableParameter variableParameter){
        if (value == null){
            throw new IllegalArgumentException("The value of a variableParameterValue cannot be null");
        }
        this.value = value;
        this.variableParameter = variableParameter;
    }

    /**
     * <p>Constructor for DefaultVariableParameterValue.</p>
     *
     * @param value a {@link java.lang.String} object.
     * @param variableParameter a {@link psidev.psi.mi.jami.model.VariableParameter} object.
     * @param order a {@link java.lang.Integer} object.
     */
    public DefaultVariableParameterValue(String value, VariableParameter variableParameter, Integer order){
        if (value == null){
            throw new IllegalArgumentException("The value of a variableParameterValue cannot be null");
        }
        this.value = value;
        this.variableParameter = variableParameter;
        this.order = order;
    }

    /**
     * <p>Getter for the field <code>value</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getValue() {
        return value;
    }

    /**
     * <p>Getter for the field <code>order</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * <p>Getter for the field <code>variableParameter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.VariableParameter} object.
     */
    public VariableParameter getVariableParameter() {
        return variableParameter;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof VariableParameterValue)){
            return false;
        }

        return VariableParameterValueComparator.areEquals(this, (VariableParameterValue) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return VariableParameterValueComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getValue() != null ? getValue().toString() : "-");
    }
}

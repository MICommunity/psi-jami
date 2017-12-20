package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.VariableParameter;
import psidev.psi.mi.jami.model.VariableParameterValue;
import psidev.psi.mi.jami.utils.comparator.experiment.UnambiguousVariableParameterComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for variableParameters
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with UnambiguousVariableParameterComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/05/13</pre>
 */
public class DefaultVariableParameter implements VariableParameter {

    private String description;
    private CvTerm unit;
    private Collection<VariableParameterValue> variableValues;

    private Experiment experiment;

    /**
     * <p>Constructor for DefaultVariableParameter.</p>
     *
     * @param description a {@link java.lang.String} object.
     */
    public DefaultVariableParameter(String description){
        if (description == null){
            throw new IllegalArgumentException("The description of the variableParameter is required and cannot be null.");
        }
        this.description = description;
    }

    /**
     * <p>Constructor for DefaultVariableParameter.</p>
     *
     * @param description a {@link java.lang.String} object.
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public DefaultVariableParameter(String description, Experiment experiment){
        this(description);
        this.experiment = experiment;
    }

    /**
     * <p>Constructor for DefaultVariableParameter.</p>
     *
     * @param description a {@link java.lang.String} object.
     * @param unit a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultVariableParameter(String description, CvTerm unit){
        this(description);
        this.unit = unit;
    }

    /**
     * <p>Constructor for DefaultVariableParameter.</p>
     *
     * @param description a {@link java.lang.String} object.
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param unit a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultVariableParameter(String description, Experiment experiment, CvTerm unit){
        this(description, experiment);
        this.unit = unit;
    }

    /**
     * <p>initialiseVatiableParameterValues</p>
     */
    protected void initialiseVatiableParameterValues(){
        this.variableValues = new ArrayList<VariableParameterValue>();
    }

    /**
     * <p>initialiseVatiableParameterValuesWith</p>
     *
     * @param paramValues a {@link java.util.Collection} object.
     */
    protected void initialiseVatiableParameterValuesWith(Collection<VariableParameterValue> paramValues){
        if (paramValues == null){
            this.variableValues = Collections.EMPTY_SET;
        }
        else {
            this.variableValues = paramValues;
        }
    }

    /**
     * <p>Getter for the field <code>description</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDescription() {
        return this.description;
    }

    /** {@inheritDoc} */
    public void setDescription(String description) {
        if (description == null){
           throw new IllegalArgumentException("The description cannot be null");
        }
        this.description = description;
    }

    /**
     * <p>Getter for the field <code>unit</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getUnit() {
        return this.unit;
    }

    /** {@inheritDoc} */
    public void setUnit(CvTerm unit) {
        this.unit = unit;
    }

    /**
     * <p>Getter for the field <code>variableValues</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<VariableParameterValue> getVariableValues() {
        if (variableValues == null){
            initialiseVatiableParameterValues();
        }
        return this.variableValues;
    }

    /**
     * <p>Getter for the field <code>experiment</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public Experiment getExperiment() {
        return this.experiment;
    }

    /** {@inheritDoc} */
    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    /** {@inheritDoc} */
    public void setExperimentAndAddVariableParameter(Experiment experiment) {
        if (this.experiment != null){
            this.experiment.removeVariableParameter(this);
        }
        if (experiment != null){
           experiment.addVariableParameter(this);
        }
    }


    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof VariableParameter)){
            return false;
        }

        return UnambiguousVariableParameterComparator.areEquals(this, (VariableParameter) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousVariableParameterComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getDescription().toString() + (getUnit() != null ? "(unit: "+getUnit().toString()+")":"");
    }
}

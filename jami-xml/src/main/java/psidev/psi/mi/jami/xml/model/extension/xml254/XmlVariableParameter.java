package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.VariableParameter;
import psidev.psi.mi.jami.model.VariableParameterValue;
import psidev.psi.mi.jami.utils.comparator.experiment.UnambiguousVariableParameterComparator;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * XML 3.0 implementation of variable parameter
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/05/14</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlVariableParameter implements VariableParameter,FileSourceContext,Locatable {

    private PsiXmlLocator sourceLocator;

    @XmlLocation
    @XmlTransient
    private Locator locator;

    private String description;
    private CvTerm unit;
    private Experiment experiment;

    private JAXBVariableValueWrapper jaxbVariableValueWrapper;

    /**
     * <p>Constructor for XmlVariableParameter.</p>
     */
    public XmlVariableParameter(){

    }

    /**
     * <p>Constructor for XmlVariableParameter.</p>
     *
     * @param description a {@link String} object.
     */
    public XmlVariableParameter(String description){
        if (description == null){
            throw new IllegalArgumentException("The description of the variableParameter is required and cannot be null.");
        }
        this.description = description;
    }

    /**
     * <p>Constructor for XmlVariableParameter.</p>
     *
     * @param description a {@link String} object.
     * @param experiment a {@link Experiment} object.
     */
    public XmlVariableParameter(String description, Experiment experiment){
        this(description);
        this.experiment = experiment;
    }

    /**
     * <p>Constructor for XmlVariableParameter.</p>
     *
     * @param description a {@link String} object.
     * @param unit a {@link CvTerm} object.
     */
    public XmlVariableParameter(String description, CvTerm unit){
        this(description);
        this.unit = unit;
    }

    /**
     * <p>Constructor for XmlVariableParameter.</p>
     *
     * @param description a {@link String} object.
     * @param experiment a {@link Experiment} object.
     * @param unit a {@link CvTerm} object.
     */
    public XmlVariableParameter(String description, Experiment experiment, CvTerm unit){
        this(description, experiment);
        this.unit = unit;
    }

    /**
     * <p>initialiseVatiableParameterValues.</p>
     */
    protected void initialiseVatiableParameterValues(){
        this.jaxbVariableValueWrapper = new JAXBVariableValueWrapper();
    }

    /**
     * <p>Getter for the field <code>description</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getDescription() {
        if (this.description == null){
            this.description = PsiXmlUtils.UNSPECIFIED;
        }
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
     * @return a {@link CvTerm} object.
     */
    public CvTerm getUnit() {
        return this.unit;
    }

    /** {@inheritDoc} */
    public void setUnit(CvTerm unit) {
        this.unit = unit;
    }

    /**
     * <p>getVariableValues.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<VariableParameterValue> getVariableValues() {
        if (jaxbVariableValueWrapper == null){
            initialiseVatiableParameterValues();
        }
        return this.jaxbVariableValueWrapper.variableValues;
    }

    /**
     * <p>Getter for the field <code>experiment</code>.</p>
     *
     * @return a {@link Experiment} object.
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
    public Locator sourceLocation() {
        return (Locator)getSourceLocator();
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        if (sourceLocator == null && locator != null){
            sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
        }
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else if (sourceLocator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)sourceLocator;
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
        }
    }

    /**
     * <p>setSourceLocation.</p>
     *
     * @param sourceLocator a {@link PsiXmlLocator} object.
     */
    public void setSourceLocation(PsiXmlLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousVariableParameterComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return description.toString() + (unit != null ? "(unit: "+unit.toString()+")":"");
    }

    /**
     * <p>setJAXBDescription.</p>
     *
     * @param desc a {@link String} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name = "description", required = true)
    public void setJAXBDescription(String desc){
        this.description = desc;
    }

    /**
     * <p>setJAXBUnit.</p>
     *
     * @param unit a {@link XmlCvTerm} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name = "unit")
    public void setJAXBUnit(XmlCvTerm unit){
        this.unit = unit;
    }

    /**
     * <p>setJAXBVariableParameterValuesWrapper.</p>
     *
     * @param jaxbVariableValueList a {@link JAXBVariableValueWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name = "variableValueList", required = true)
    public void setJAXBVariableParameterValuesWrapper(JAXBVariableValueWrapper jaxbVariableValueList) {
        this.jaxbVariableValueWrapper = jaxbVariableValueList;
        // initialise all variable values because of back references
        if (this.jaxbVariableValueWrapper != null && !this.jaxbVariableValueWrapper.variableValues.isEmpty()){
            for (VariableParameterValue value : this.jaxbVariableValueWrapper.variableValues){
                ((XmlVariableParameterValue)value).setVariableParameter(this);
            }
        }
    }

    //////////////////////////////////////////////////

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif", name="variableValuesWrapper")
    public static class JAXBVariableValueWrapper implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<VariableParameterValue> variableValues;

        public JAXBVariableValueWrapper(){
            initialiseVariableValues();
        }

        public JAXBVariableValueWrapper(List<VariableParameterValue> values){
            this.variableValues = values;
        }

        @Override
        public Locator sourceLocation() {
            return (Locator)getSourceLocator();
        }

        public FileSourceLocator getSourceLocator() {
            if (sourceLocator == null && locator != null){
                sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
            }
            return sourceLocator;
        }

        public void setSourceLocator(FileSourceLocator sourceLocator) {
            if (sourceLocator == null){
                this.sourceLocator = null;
            }
            else if (sourceLocator instanceof PsiXmlLocator){
                this.sourceLocator = (PsiXmlLocator)sourceLocator;
            }
            else {
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        protected void initialiseVariableValues(){
            this.variableValues = new ArrayList<VariableParameterValue>();
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif", type= XmlVariableParameterValue.class, name="variableValue", required = true)
        public List<VariableParameterValue> getJAXBVariableParameterValues() {
            return this.variableValues;
        }

        @Override
        public String toString() {
            return "Variable parameter values List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

}

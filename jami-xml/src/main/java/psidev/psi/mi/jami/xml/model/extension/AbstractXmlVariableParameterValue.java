package psidev.psi.mi.jami.xml.model.extension;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.VariableParameter;
import psidev.psi.mi.jami.model.VariableParameterValue;
import psidev.psi.mi.jami.utils.comparator.experiment.VariableParameterValueComparator;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

/**
 * XML implementation of variable parameter value
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/05/14</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlTransient
public abstract class AbstractXmlVariableParameterValue implements VariableParameterValue,FileSourceContext,Locatable {

    private PsiXmlLocator sourceLocator;

    @XmlLocation
    @XmlTransient
    private Locator locator;

    private String value;
    private Integer order;
    private VariableParameter variableParameter;

    private int id;

    /**
     * <p>Constructor for XmlVariableParameterValue.</p>
     */
    public AbstractXmlVariableParameterValue(){

    }

    /**
     * <p>Constructor for XmlVariableParameterValue.</p>
     *
     * @param value a {@link java.lang.String} object.
     * @param variableParameter a {@link psidev.psi.mi.jami.model.VariableParameter} object.
     */
    public AbstractXmlVariableParameterValue(String value, VariableParameter variableParameter){
        if (value == null){
            throw new IllegalArgumentException("The value of a variableParameterValue cannot be null");
        }
        this.value = value;
        this.variableParameter = variableParameter;
    }

    /**
     * <p>Constructor for XmlVariableParameterValue.</p>
     *
     * @param value a {@link java.lang.String} object.
     * @param variableParameter a {@link psidev.psi.mi.jami.model.VariableParameter} object.
     * @param order a {@link java.lang.Integer} object.
     */
    public AbstractXmlVariableParameterValue(String value, VariableParameter variableParameter, Integer order){
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
        if (value == null){
            this.value = PsiXmlUtils.UNSPECIFIED;
        }
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

    /**
     * <p>Setter for the field <code>variableParameter</code>.</p>
     *
     * @param variableParameter a {@link psidev.psi.mi.jami.model.VariableParameter} object.
     */
    public void setVariableParameter(VariableParameter variableParameter) {
        this.variableParameter = variableParameter;
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
        return value != null ? value.toString() : super.toString();
    }

    /** {@inheritDoc} */
    @Override
    public Locator sourceLocation() {
        return (Locator)getSourceLocator();
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
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
            this.sourceLocator.setObjectId(id);
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), this.id);
        }
    }

    /**
     * <p>setSourceLocation.</p>
     *
     * @param sourceLocator a {@link psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator} object.
     */
    public void setSourceLocation(PsiXmlLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a int.
     */
    public int getId() {
        return id;
    }

    /**
     * <p>setJAXBValue.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setJAXBValue(String value){
        this.value = value;
    }

    /**
     * <p>setJAXBId.</p>
     *
     * @param id a int.
     */
    public void setJAXBId(int id){
        this.id = id;
        // register variable parameter value
        XmlEntryContext.getInstance().registerVariableParameterValue(this.id, this);
        if (getSourceLocator() != null){
            this.sourceLocator.setObjectId(this.id);
        }
    }

    /**
     * <p>setJAXBOrder.</p>
     *
     * @param order a {@link java.lang.Integer} object.
     */
    public void setJAXBOrder(Integer order){
        this.order = order;
    }
}

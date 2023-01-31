package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import psidev.psi.mi.jami.model.VariableParameter;
import psidev.psi.mi.jami.xml.model.extension.AbstractXmlVariableParameterValue;

/**
 * XML 2.5 implementation of variable parameter value
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/05/14</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlVariableParameterValue extends AbstractXmlVariableParameterValue {

    /**
     * <p>Constructor for XmlVariableParameterValue.</p>
     */
    public XmlVariableParameterValue(){
    }

    /**
     * <p>Constructor for XmlVariableParameterValue.</p>
     *
     * @param value a {@link java.lang.String} object.
     * @param variableParameter a {@link psidev.psi.mi.jami.model.VariableParameter} object.
     */
    public XmlVariableParameterValue(String value, VariableParameter variableParameter){
        super(value, variableParameter);
    }

    /**
     * <p>Constructor for XmlVariableParameterValue.</p>
     *
     * @param value a {@link java.lang.String} object.
     * @param variableParameter a {@link psidev.psi.mi.jami.model.VariableParameter} object.
     * @param order a {@link java.lang.Integer} object.
     */
    public XmlVariableParameterValue(String value, VariableParameter variableParameter, Integer order){
        super(value, variableParameter, order);
    }

    /**
     * <p>setJAXBValue.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name = "value", required = true)
    public void setJAXBValue(String value){
        super.setJAXBValue(value);
    }

    /**
     * <p>setJAXBId.</p>
     *
     * @param id a int.
     */
    @XmlAttribute(name = "id", required = true)
    public void setJAXBId(int id){
        super.setJAXBId(id);
    }

    /**
     * <p>setJAXBOrder.</p>
     *
     * @param order a {@link java.lang.Integer} object.
     */
    @XmlAttribute(name = "order", required = true)
    public void setJAXBOrder(Integer order){
        super.setJAXBOrder(order);
    }
}

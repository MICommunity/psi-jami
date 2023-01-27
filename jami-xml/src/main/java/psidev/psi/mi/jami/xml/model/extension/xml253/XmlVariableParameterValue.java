package psidev.psi.mi.jami.xml.model.extension.xml253;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import psidev.psi.mi.jami.model.VariableParameter;
import psidev.psi.mi.jami.xml.model.extension.AbstractXmlVariableParameterValue;

/**
 * XML 3.0 implementation of variable parameter value
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
     * @param value a {@link String} object.
     * @param variableParameter a {@link VariableParameter} object.
     */
    public XmlVariableParameterValue(String value, VariableParameter variableParameter){
        super(value, variableParameter);
    }

    /**
     * <p>Constructor for XmlVariableParameterValue.</p>
     *
     * @param value a {@link String} object.
     * @param variableParameter a {@link VariableParameter} object.
     * @param order a {@link Integer} object.
     */
    public XmlVariableParameterValue(String value, VariableParameter variableParameter, Integer order){
        super(value, variableParameter, order);
    }

    /**
     * <p>setJAXBValue.</p>
     *
     * @param value a {@link String} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "value", required = true)
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
     * @param order a {@link Integer} object.
     */
    @XmlAttribute(name = "order", required = true)
    public void setJAXBOrder(Integer order){
        super.setJAXBOrder(order);
    }
}

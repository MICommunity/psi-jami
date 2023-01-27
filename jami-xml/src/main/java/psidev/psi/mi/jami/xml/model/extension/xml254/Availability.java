package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import psidev.psi.mi.jami.xml.model.extension.AbstractAvailability;

/**
 * A text describing the availability of data, e.g. a copyright statement.
 *
 * <p>Java class for availability complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="availability&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *

 */
@XmlRootElement(namespace = "http://psi.hupo.org/mi/mif", name = "availability")
@XmlAccessorType(XmlAccessType.NONE)
public class Availability extends AbstractAvailability
{
    /**
     * <p>Constructor for Availability.</p>
     */
    public Availability() {
    }

    /**
     * Sets the value of the id property.
     *
     * @param value a int.
     */
    @XmlAttribute(name = "id", required = true)
    public void setId(int value) {
        super.setId(value);
    }
}

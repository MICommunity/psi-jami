package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.xml.model.extension.AbstractNamesContainer;

import java.util.List;

/**
 * A container for aliases, shortname and fullname
 * The JAXB binding is designed to be read-only and is not designed for writing
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({
        ComplexNamesContainer.class
})
public class NamesContainer extends AbstractNamesContainer {

    /**
     * Sets the value of the shortLabel property.
     *
     * @param value
     *     allowed object is
     *     {@link String}
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name = "shortLabel")
    public void setShortLabel(String value) {
        super.setShortLabel(value);
    }

    /**
     * Sets the value of the fullName property.
     *
     * @param value
     *     allowed object is
     *     {@link String}
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name = "fullName")
    public void setFullName(String value) {
        super.setFullName(value);
    }

    /**
     * Gets the value of the alias property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alias property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlias().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XmlAlias}
     *
     * @return a {@link List} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", type= XmlAlias.class, name = "alias")
    public List<Alias> getAliases() {
        return super.getAliases();
    }
}

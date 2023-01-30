package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.model.CvTerm;

/**
 * Xml implementation of ModelledInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlType(namespace = "http://psi.hupo.org/mi/mif300", name = "defaultModelledInteraction")
@XmlAccessorType(XmlAccessType.NONE)
public class DefaultXmlModelledInteraction extends AbstractXmlModelledInteraction {

    /**
     * <p>Constructor for XmlModelledInteraction.</p>
     */
    public DefaultXmlModelledInteraction() {
    }

    /**
     * <p>Constructor for XmlModelledInteraction.</p>
     *
     * @param shortName a {@link String} object.
     */
    public DefaultXmlModelledInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for XmlModelledInteraction.</p>
     *
     * @param shortName a {@link String} object.
     * @param type a {@link CvTerm} object.
     */
    public DefaultXmlModelledInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }
}
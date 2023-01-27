package psidev.psi.mi.jami.xml.model.extension.xml254;

import psidev.psi.mi.jami.model.CvTerm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Xml implementation of interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlRootElement(namespace = "http://psi.hupo.org/mi/mif", name = "interaction")
@XmlAccessorType(XmlAccessType.NONE)
public class XmlBasicInteraction extends AbstractXmlBasicInteraction {

    /**
     * <p>Constructor for XmlBasicInteraction.</p>
     */
    public XmlBasicInteraction() {
        super();
    }

    /**
     * <p>Constructor for XmlBasicInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public XmlBasicInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for XmlBasicInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBasicInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }
}

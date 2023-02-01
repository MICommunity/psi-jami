package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.model.CvTerm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Xml implementation of ModelledInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlRootElement(namespace = "net:sf:psidev:mi", name = "interaction")
@XmlAccessorType(XmlAccessType.NONE)
public class XmlModelledInteraction extends AbstractXmlModelledInteraction {

    /**
     * <p>Constructor for XmlModelledInteraction.</p>
     */
    public XmlModelledInteraction() {
    }

    /**
     * <p>Constructor for XmlModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public XmlModelledInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for XmlModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }
}

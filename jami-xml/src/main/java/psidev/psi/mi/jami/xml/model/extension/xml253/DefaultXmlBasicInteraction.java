package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.model.CvTerm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.xml.model.extension.xml253.AbstractXmlBasicInteraction;

/**
 * Xml implementation of interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlType(namespace = "net:sf:psidev:mi", name = "defaultBasicInteraction")
@XmlAccessorType(XmlAccessType.NONE)
public class DefaultXmlBasicInteraction extends AbstractXmlBasicInteraction {

    /**
     * <p>Constructor for XmlBasicInteraction.</p>
     */
    public DefaultXmlBasicInteraction() {
        super();
    }

    /**
     * <p>Constructor for XmlBasicInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultXmlBasicInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for XmlBasicInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultXmlBasicInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }
}

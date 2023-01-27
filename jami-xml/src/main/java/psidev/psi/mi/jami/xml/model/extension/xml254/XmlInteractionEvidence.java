package psidev.psi.mi.jami.xml.model.extension.xml254;

import psidev.psi.mi.jami.model.CvTerm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Xml implementation of InteractionEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlRootElement(namespace = "http://psi.hupo.org/mi/mif", name = "interaction")
@XmlAccessorType(XmlAccessType.NONE)
public class XmlInteractionEvidence extends AbstractXmlInteractionEvidence {

    /**
     * <p>Constructor for XmlInteractionEvidence.</p>
     */
    public XmlInteractionEvidence() {
        super();
    }

    /**
     * <p>Constructor for XmlInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public XmlInteractionEvidence(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for XmlInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlInteractionEvidence(String shortName, CvTerm type) {
        super(shortName, type);
    }
}

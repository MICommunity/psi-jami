package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.model.CvTerm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Xml implementation of InteractionEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlType(namespace = "net:sf:psidev:mi", name = "defaultInteractionEvidence")
@XmlAccessorType(XmlAccessType.NONE)
public class DefaultXmlInteractionEvidence extends AbstractXmlInteractionEvidence {

    /**
     * <p>Constructor for DefaultXmlInteractionEvidence.</p>
     */
    public DefaultXmlInteractionEvidence() {
        super();
    }

    /**
     * <p>Constructor for DefaultXmlInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultXmlInteractionEvidence(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for DefaultXmlInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultXmlInteractionEvidence(String shortName, CvTerm type) {
        super(shortName, type);
    }
}

package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.model.CvTerm;

/**
 * Xml implementation of InteractionEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlType(namespace = "http://psi.hupo.org/mi/mif300", name = "defaultInteractionEvidence")
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

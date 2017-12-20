package psidev.psi.mi.jami.xml.model.extension.xml300;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.xml.model.extension.AbstractXmlAnnotation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Xml implementation of an Annotation
 *
 * The JAXB binding is designed to be read-only and is not designed for writing
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/13</pre>
 */
@XmlRootElement(name = "attribute", namespace = "http://psi.hupo.org/mi/mif300")
@XmlAccessorType(XmlAccessType.NONE)
public class XmlAnnotation extends AbstractXmlAnnotation {

    /**
     * <p>Constructor for XmlAnnotation.</p>
     */
    public XmlAnnotation() {
    }

    /**
     * <p>Constructor for XmlAnnotation.</p>
     *
     * @param topic a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     */
    public XmlAnnotation(CvTerm topic, String value) {
        super(topic, value);
    }

    /**
     * <p>Constructor for XmlAnnotation.</p>
     *
     * @param topic a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlAnnotation(CvTerm topic) {
        super(topic);
    }
}

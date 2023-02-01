package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.model.CvTerm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.xml.model.extension.xml253.AbstractXmlAnnotation;

/**
 * Xml implementation of an Annotation
 *
 * The JAXB binding is designed to be read-only and is not designed for writing
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/13</pre>
 */
@XmlType(namespace = "net:sf:psidev:mi", name = "defaultAttribute")
@XmlAccessorType(XmlAccessType.NONE)
public class DefaultXmlAnnotation extends AbstractXmlAnnotation {

    /**
     * <p>Constructor for DefaultXmlAnnotation.</p>
     */
    public DefaultXmlAnnotation() {
    }

    /**
     * <p>Constructor for DefaultXmlAnnotation.</p>
     *
     * @param topic a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     */
    public DefaultXmlAnnotation(CvTerm topic, String value) {
        super(topic, value);
    }

    /**
     * <p>Constructor for DefaultXmlAnnotation.</p>
     *
     * @param topic a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultXmlAnnotation(CvTerm topic) {
        super(topic);
    }
}

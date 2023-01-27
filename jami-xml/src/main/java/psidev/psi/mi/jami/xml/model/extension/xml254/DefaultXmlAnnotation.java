package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.model.CvTerm;

/**
 * Xml implementation of an Annotation
 *
 * The JAXB binding is designed to be read-only and is not designed for writing
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/13</pre>
 */
@XmlType(namespace = "http://psi.hupo.org/mi/mif", name = "defaultAttribute")
@XmlAccessorType(XmlAccessType.NONE)
public class DefaultXmlAnnotation extends AbstractXmlAnnotation {

    /**
     * <p>Constructor for XmlAnnotation.</p>
     */
    public DefaultXmlAnnotation() {
    }

    /**
     * <p>Constructor for XmlAnnotation.</p>
     *
     * @param topic a {@link CvTerm} object.
     * @param value a {@link String} object.
     */
    public DefaultXmlAnnotation(CvTerm topic, String value) {
        super(topic, value);
    }

    /**
     * <p>Constructor for XmlAnnotation.</p>
     *
     * @param topic a {@link CvTerm} object.
     */
    public DefaultXmlAnnotation(CvTerm topic) {
        super(topic);
    }
}

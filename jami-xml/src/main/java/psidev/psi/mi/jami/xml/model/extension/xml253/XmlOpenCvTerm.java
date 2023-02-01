package psidev.psi.mi.jami.xml.model.extension.xml253;

import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Xref;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

/**
 * Xml implementation of CvTerm.
 *
 * Does write annotations
 *
 * The JAXB binding is designed to be read-only and is not designed for writing
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlOpenCvTerm extends AbstractXmlCvTerm {

    @XmlLocation
    @XmlTransient
    private Locator locator;

    /**
     * <p>Constructor for XmlOpenCvTerm.</p>
     */
    public XmlOpenCvTerm() {
    }

    /**
     * <p>Constructor for XmlOpenCvTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public XmlOpenCvTerm(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for XmlOpenCvTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param miIdentifier a {@link java.lang.String} object.
     */
    public XmlOpenCvTerm(String shortName, String miIdentifier) {
        super(shortName, miIdentifier);
    }

    /**
     * <p>Constructor for XmlOpenCvTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param miIdentifier a {@link java.lang.String} object.
     */
    public XmlOpenCvTerm(String shortName, String fullName, String miIdentifier) {
        super(shortName, fullName, miIdentifier);
    }

    /**
     * <p>Constructor for XmlOpenCvTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlOpenCvTerm(String shortName, Xref ontologyId) {
        super(shortName, ontologyId);
    }

    /**
     * <p>Constructor for XmlOpenCvTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlOpenCvTerm(String shortName, String fullName, Xref ontologyId) {
        super(shortName, fullName, ontologyId);
    }

    /**
     * {@inheritDoc}
     *
     * Sets the value of the names property.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "names", required = true)
    public void setJAXBNames(NamesContainer value) {
        super.setJAXBNames(value);
    }

    /**
     * {@inheritDoc}
     *
     * Sets the value of the xrefContainer property.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "xref", required = true)
    public void setJAXBXref(CvTermXrefContainer value) {
        super.setJAXBXref(value);
    }

    /**
     * <p>setJAXBAttributeWrapper.</p>
     *
     * @param wrapper a JAXBAttributeWrapper object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name="attributeList")
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper wrapper){
        super.setAttributeWrapper(wrapper);
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        if (super.getSourceLocator() == null && locator != null){
            super.setSourceLocator(new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null));
        }
        return super.getSourceLocator();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Open Cv Term: "+getSourceLocator().toString():super.toString());
    }
}

package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

/**
 * Xml implementation of CvTerm.
 *
 * Does not write annotations
 *
 * The JAXB binding is designed to be read-only and is not designed for writing
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({
        ExperimentalCvTerm.class
})
public class XmlCvTerm extends AbstractXmlCvTerm {

    @XmlLocation
    @XmlTransient
    private Locator locator;

    /**
     * <p>Constructor for XmlCvTerm.</p>
     */
    public XmlCvTerm() {
    }

    /**
     * <p>Constructor for XmlCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     * @param miIdentifier a {@link String} object.
     */
    public XmlCvTerm(String shortName, String miIdentifier) {
        super(shortName, miIdentifier);
    }

    /**
     * <p>Constructor for XmlCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     */
    public XmlCvTerm(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for XmlCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     * @param miIdentifier a {@link String} object.
     */
    public XmlCvTerm(String shortName, String fullName, String miIdentifier) {
        super(shortName, fullName, miIdentifier);
    }

    /**
     * <p>Constructor for XmlCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     * @param ontologyId a {@link Xref} object.
     */
    public XmlCvTerm(String shortName, Xref ontologyId) {
        super(shortName, ontologyId);
    }

    /**
     * <p>Constructor for XmlCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     * @param ontologyId a {@link Xref} object.
     */
    public XmlCvTerm(String shortName, String fullName, Xref ontologyId) {
        super(shortName, fullName, ontologyId);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "names", required = true)
    public void setJAXBNames(NamesContainer value) {
        super.setJAXBNames(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "xref", required = true)
    public void setJAXBXref(CvTermXrefContainer value) {
        super.setJAXBXref(value);
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
        return (getSourceLocator() != null ? "Cv Term: "+getSourceLocator().toString():super.toString());
    }
}

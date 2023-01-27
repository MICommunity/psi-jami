package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultChecksum;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Xml implementation of a checksum
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>24/07/13</pre>
 */
@XmlTransient
public class XmlChecksum extends DefaultChecksum implements FileSourceContext {
    private PsiXmlLocator sourceLocator;

    /**
     * <p>Constructor for XmlChecksum.</p>
     *
     * @param method a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     */
    public XmlChecksum(CvTerm method, String value) {
        super(method, value);
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator locator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else if (sourceLocator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)sourceLocator;
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
        }
    }

    /**
     * <p>Setter for the field <code>sourceLocator</code>.</p>
     *
     * @param locator a {@link PsiXmlLocator} object.
     */
    public void setSourceLocator(PsiXmlLocator locator) {
        this.sourceLocator = locator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Checksum: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.impl.DefaultStoichiometry;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Xml implementation of stoichiometry
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>02/08/13</pre>
 */
@XmlTransient
public class XmlStoichiometry extends DefaultStoichiometry implements FileSourceContext {

    private PsiXmlLocator sourceLocator;

    /**
     * <p>Constructor for XmlStoichiometry.</p>
     *
     * @param value a int.
     */
    public XmlStoichiometry(int value) {
        super(value);
    }

    /**
     * <p>Constructor for XmlStoichiometry.</p>
     *
     * @param minValue a int.
     * @param maxValue a int.
     */
    public XmlStoichiometry(int minValue, int maxValue) {
        super(minValue, maxValue);
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
    public void setSourceLocator(FileSourceLocator sourceLocator) {
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
     * @param sourceLocator a {@link psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator} object.
     */
    public void setSourceLocator(PsiXmlLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Participant Stoichiometry: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

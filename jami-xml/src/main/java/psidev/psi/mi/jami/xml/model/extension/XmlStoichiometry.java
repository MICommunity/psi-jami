package psidev.psi.mi.jami.xml.model.extension;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.impl.DefaultStoichiometry;

/**
 * Xml implementation of stoichiometry
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>02/08/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlStoichiometry extends DefaultStoichiometry implements FileSourceContext {

    private PsiXmlLocator sourceLocator;

    public XmlStoichiometry() {
    }

    /**
     * <p>Constructor for XmlStoichiometry.</p>
     *
     * @param value a int.
     */
    public XmlStoichiometry(int value) {
        super(value, value);
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

    @XmlAttribute(name = "value")
    public void setValue(int value) {
        setMinValue(value);
        setMaxValue(value);
    }

    @XmlAttribute(name = "minValue")
    public void setMinValue(int value) {
        super.setMinValue(value);
    }

    @XmlAttribute(name = "maxValue")
    public void setMaxValue(int value) {
        super.setMaxValue(value);
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link FileSourceLocator} object.
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
     * @param sourceLocator a {@link PsiXmlLocator} object.
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

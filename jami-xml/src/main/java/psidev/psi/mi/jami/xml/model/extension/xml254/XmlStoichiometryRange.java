package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Stoichiometry;
import psidev.psi.mi.jami.utils.comparator.participant.StoichiometryComparator;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

/**
 * Xml 3.0 implementation of stoichiometry range values
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>02/08/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://psi.hupo.org/mi/mif")
public class XmlStoichiometryRange implements FileSourceContext, Stoichiometry, Locatable {

    private PsiXmlLocator sourceLocator;

    @XmlLocation
    @XmlTransient
    private Locator locator;

    private int minValue=0;
    private int maxValue=0;

    /**
     * <p>Constructor for XmlStoichiometryRange.</p>
     */
    public XmlStoichiometryRange(){

    }

    /**
     * <p>Constructor for XmlStoichiometryRange.</p>
     *
     * @param value a int.
     */
    public XmlStoichiometryRange(int value){

        this.minValue = value;
        this.maxValue = value;
    }

    /**
     * <p>Constructor for XmlStoichiometryRange.</p>
     *
     * @param minValue a int.
     * @param maxValue a int.
     */
    public XmlStoichiometryRange(int minValue, int maxValue){
        if (minValue > maxValue){
            throw new IllegalArgumentException("The minValue " + minValue + " cannot be bigger than the maxValue " + maxValue);
        }

        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * <p>Getter for the field <code>minValue</code>.</p>
     *
     * @return a int.
     */
    public int getMinValue() {
        return this.minValue;
    }

    /**
     * <p>Getter for the field <code>maxValue</code>.</p>
     *
     * @return a int.
     */
    public int getMaxValue() {
        return this.maxValue;
    }

    /**
     * <p>setJAXBMinValue.</p>
     *
     * @param value a int.
     */
    @XmlAttribute(name = "minValue", required = true)
    public void setJAXBMinValue(int value){
        this.minValue = value;
    }

    /**
     * <p>setJAXBMaxValue.</p>
     *
     * @param value a int.
     */
    @XmlAttribute(name = "maxValue", required = true)
    public void setJAXBMaxValue(int value){
        this.maxValue = value;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {

        if (this == o){
            return true;
        }

        if (!(o instanceof Stoichiometry)){
            return false;
        }

        return StoichiometryComparator.areEquals(this, (Stoichiometry) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return StoichiometryComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Participant Stoichiometry: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }

    /** {@inheritDoc} */
    @Override
    public Locator sourceLocation() {
        return (Locator)getSourceLocator();
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        if (sourceLocator == null && locator != null){
            sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
        }
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
     * <p>setSourceLocation.</p>
     *
     * @param sourceLocator a {@link psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator} object.
     */
    public void setSourceLocation(PsiXmlLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }
}

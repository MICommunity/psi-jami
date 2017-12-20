package psidev.psi.mi.jami.xml.model.extension.xml300;

import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Stoichiometry;
import psidev.psi.mi.jami.utils.comparator.participant.StoichiometryComparator;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

import javax.xml.bind.annotation.*;

/**
 * Xml 3.0 implementation of stoichiometry mean value
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>02/08/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://psi.hupo.org/mi/mif300")
public class XmlStoichiometry implements FileSourceContext, Stoichiometry, Locatable {

    private PsiXmlLocator sourceLocator;

    @XmlLocation
    @XmlTransient
    private Locator locator;

    private int minValue=0;
    private int maxValue=0;
    
    /**
     * <p>Constructor for XmlStoichiometry.</p>
     */
    public XmlStoichiometry(){
        
    }

    /**
     * <p>Constructor for XmlStoichiometry.</p>
     *
     * @param value a int.
     */
    public XmlStoichiometry(int value){

        this.minValue = value;
        this.maxValue = value;
    }

    /**
     * <p>Constructor for XmlStoichiometry.</p>
     *
     * @param minValue a int.
     * @param maxValue a int.
     */
    public XmlStoichiometry(int minValue, int maxValue){
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
     * <p>setJAXBValue.</p>
     *
     * @param value a int.
     */
    @XmlAttribute(name = "value", required = true)
    public void setJAXBValue(int value){
        this.maxValue = value;
        this.minValue = value;
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

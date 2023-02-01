package psidev.psi.mi.jami.xml.model.extension.xml253;

import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

/**
 * Xml implementation of a simple Position
 *
 * The JAXB binding is designed to be read-only and is not designed for writing
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlPosition extends AbstractXmlPosition {

    private long pos;
    @XmlLocation
    @XmlTransient
    private Locator locator;

    /**
     * <p>Constructor for XmlPosition.</p>
     */
    public XmlPosition() {
    }

    /**
     * <p>Constructor for XmlPosition.</p>
     *
     * @param status a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param positionUndetermined a boolean.
     */
    public XmlPosition(CvTerm status, boolean positionUndetermined) {
        super(status, positionUndetermined);
    }

    /**
     * <p>Constructor for XmlPosition.</p>
     *
     * @param status a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param pos a long.
     * @param positionUndetermined a boolean.
     */
    public XmlPosition(CvTerm status, long pos, boolean positionUndetermined) {
        super(status, positionUndetermined);
        this.pos = pos;
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getStatus() {
        return super.getStatus();
    }

    /**
     * <p>getStart.</p>
     *
     * @return a long.
     */
    public long getStart() {
        return pos;
    }

    /**
     * <p>getEnd.</p>
     *
     * @return a long.
     */
    public long getEnd() {
        return pos;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPositionUndetermined() {
        return super.isPositionUndetermined();
    }

    /**
     * Sets the value of the position property.
     *
     * @param value
     *     allowed object is
     *     {@link java.math.BigInteger}
     */
    @XmlAttribute(name = "position", required = true)
    public void setJAXBPosition(long value) {
        this.pos = value;
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
        return "Range Position: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

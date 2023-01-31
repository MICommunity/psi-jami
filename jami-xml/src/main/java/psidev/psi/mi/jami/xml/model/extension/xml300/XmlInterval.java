package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

/**
 * Xml implementation of a position which is an interval
 * The JAXB binding is designed to be read-only and is not designed for writing
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlInterval extends AbstractXmlPosition {
    private long start;
    private long end;
    @XmlLocation
    @XmlTransient
    private Locator locator;

    /**
     * <p>Constructor for XmlInterval.</p>
     */
    public XmlInterval() {
    }

    /**
     * <p>Constructor for XmlInterval.</p>
     *
     * @param status a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param positionUndetermined a boolean.
     */
    public XmlInterval(CvTerm status, boolean positionUndetermined) {
        super(status, positionUndetermined);
    }

    /**
     * <p>Constructor for XmlInterval.</p>
     *
     * @param status a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param start a long.
     * @param end a long.
     * @param positionUndetermined a boolean.
     */
    public XmlInterval(CvTerm status, long start, long end, boolean positionUndetermined) {
        super(status, positionUndetermined);
        this.start = start;
        this.end = end;
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getStatus() {
        return super.getStatus();
    }

    /**
     * <p>Getter for the field <code>start</code>.</p>
     *
     * @return a long.
     */
    public long getStart() {
        return start;
    }

    /**
     * <p>Getter for the field <code>end</code>.</p>
     *
     * @return a long.
     */
    public long getEnd() {
        return end;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPositionUndetermined() {
        return super.isPositionUndetermined();
    }

    /**
     * Sets the value of the begin property.
     *
     * @param value
     *     allowed object is
     *     {@link java.math.BigInteger}
     */
    @XmlAttribute(name = "begin", required = true)
    public void setJAXBBeginPosition(long value) {
        this.start = value;
    }

    /**
     * Sets the value of the end property.
     *
     * @param value
     *     allowed object is
     *     {@link java.math.BigInteger}
     */
    @XmlAttribute(name = "end", required = true)
    public void setJAXBEndPosition(long value) {
        this.end = value;
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
        return "Xml Range Interval: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

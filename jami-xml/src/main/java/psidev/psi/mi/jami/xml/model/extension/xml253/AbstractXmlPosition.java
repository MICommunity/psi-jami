package psidev.psi.mi.jami.xml.model.extension.xml253;

import com.sun.xml.bind.Locatable;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Position;
import psidev.psi.mi.jami.utils.comparator.range.UnambiguousPositionComparator;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Abstract class for XmlPosition
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/07/13</pre>
 */
@XmlTransient
public abstract class AbstractXmlPosition implements Position, FileSourceContext, Locatable {

    private CvTerm status;
    private boolean isPositionUndetermined;

    private PsiXmlLocator sourceLocator;

    /**
     * <p>Constructor for AbstractXmlPosition.</p>
     */
    protected AbstractXmlPosition() {
    }

    /**
     * <p>Constructor for AbstractXmlPosition.</p>
     *
     * @param status a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param positionUndetermined a boolean.
     */
    protected AbstractXmlPosition(CvTerm status, boolean positionUndetermined) {
        this.status = status;
        isPositionUndetermined = positionUndetermined;
    }

    /**
     * <p>Getter for the field <code>status</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getStatus() {
        if (status == null){
            this.status = new XmlCvTerm(PsiXmlUtils.UNSPECIFIED);
        }
        return this.status;
    }

    /**
     * <p>setJAXBStatus.</p>
     *
     * @param status a {@link psidev.psi.mi.jami.xml.model.extension.xml253.XmlCvTerm} object.
     */
    public void setJAXBStatus(XmlCvTerm status) {
        this.status = status;
    }

    /**
     * <p>isPositionUndetermined.</p>
     *
     * @return a boolean.
     */
    public boolean isPositionUndetermined() {
        return this.isPositionUndetermined;
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

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof Position)){
            return false;
        }

        return UnambiguousPositionComparator.areEquals(this, (Position) o);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Position: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousPositionComparator.hashCode(this);
    }
}

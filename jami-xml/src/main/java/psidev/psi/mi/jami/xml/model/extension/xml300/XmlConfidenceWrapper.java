package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.model.Confidence;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ModelledConfidence;
import psidev.psi.mi.jami.model.Publication;

/**
 * A wrapper for confidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
@XmlTransient
public class XmlConfidenceWrapper implements ModelledConfidence {

    private Confidence confidence;
    private Publication publication;

    /**
     * <p>Constructor for XmlConfidenceWrapper.</p>
     *
     * @param conf a {@link Confidence} object.
     */
    public XmlConfidenceWrapper(Confidence conf){
        if (conf == null){
           throw new IllegalArgumentException("A confidence wrapper needs a non null Confidence");
        }
        this.confidence = conf;
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getType() {
        return this.confidence.getType();
    }

    /** {@inheritDoc} */
    @Override
    public String getValue() {
        return this.confidence.getValue();
    }

    /** {@inheritDoc} */
    @Override
    public Publication getPublication() {
        return this.publication;
    }

    /** {@inheritDoc} */
    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.confidence.toString();
    }
}

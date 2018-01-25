package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ModelledConfidence;
import psidev.psi.mi.jami.model.Publication;

/**
 * Default implementation for ModelledInteraction
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with UnambiguousConfidenceComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>28/02/13</pre>
 */
public class DefaultModelledConfidence extends DefaultConfidence implements ModelledConfidence {

    private Publication publication;

    /**
     * <p>Constructor for DefaultModelledConfidence.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     */
    public DefaultModelledConfidence(CvTerm type, String value) {
        super(type, value);
    }

    /**
     * <p>Constructor for DefaultModelledConfidence.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public DefaultModelledConfidence(CvTerm type, String value, Publication publication) {
        super(type, value);
        this.publication = publication;
    }

    /**
     * <p>Getter for the field <code>publication</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public Publication getPublication() {
        return this.publication;
    }

    /** {@inheritDoc} */
    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}

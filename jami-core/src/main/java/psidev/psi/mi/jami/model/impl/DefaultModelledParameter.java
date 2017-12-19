package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.exception.IllegalParameterException;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ModelledParameter;
import psidev.psi.mi.jami.model.ParameterValue;
import psidev.psi.mi.jami.model.Publication;

import java.math.BigDecimal;

/**
 * Default implementation for ModelledParameter
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with UnambiguousParameterComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>28/02/13</pre>
 */
public class DefaultModelledParameter extends DefaultParameter implements ModelledParameter{

    private Publication publication;

    /**
     * <p>Constructor for DefaultModelledParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     */
    public DefaultModelledParameter(CvTerm type, ParameterValue value) {
        super(type, value);
    }

    /**
     * <p>Constructor for DefaultModelledParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     * @param unit a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledParameter(CvTerm type, ParameterValue value, CvTerm unit) {
        super(type, value, unit);
    }

    /**
     * <p>Constructor for DefaultModelledParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     * @param unit a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uncertainty a {@link java.math.BigDecimal} object.
     */
    public DefaultModelledParameter(CvTerm type, ParameterValue value, CvTerm unit, BigDecimal uncertainty) {
        super(type, value, unit, uncertainty);
    }

    /**
     * <p>Constructor for DefaultModelledParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     * @param uncertainty a {@link java.math.BigDecimal} object.
     */
    public DefaultModelledParameter(CvTerm type, ParameterValue value, BigDecimal uncertainty) {
        super(type, value, uncertainty);
    }

    /**
     * <p>Constructor for DefaultModelledParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     * @throws psidev.psi.mi.jami.exception.IllegalParameterException if any.
     */
    public DefaultModelledParameter(CvTerm type, String value) throws IllegalParameterException {
        super(type, value);
    }

    /**
     * <p>Constructor for DefaultModelledParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     * @param unit a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @throws psidev.psi.mi.jami.exception.IllegalParameterException if any.
     */
    public DefaultModelledParameter(CvTerm type, String value, CvTerm unit) throws IllegalParameterException {
        super(type, value, unit);
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

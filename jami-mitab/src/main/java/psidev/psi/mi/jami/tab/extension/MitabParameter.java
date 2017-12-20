package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.exception.IllegalParameterException;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ParameterValue;
import psidev.psi.mi.jami.model.impl.DefaultModelledParameter;
import psidev.psi.mi.jami.tab.utils.MitabUtils;

import java.math.BigDecimal;

/**
 * Mitab extension of Parameter
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabParameter extends DefaultModelledParameter implements FileSourceContext {


    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     */
    public MitabParameter(CvTerm type, ParameterValue value) {
        super(type, value);
    }

    /**
     * <p>Constructor for MitabParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     * @param unit a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParameter(CvTerm type, ParameterValue value, CvTerm unit) {
        super(type, value, unit);
    }

    /**
     * <p>Constructor for MitabParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     * @param unit a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uncertainty a {@link java.math.BigDecimal} object.
     */
    public MitabParameter(CvTerm type, ParameterValue value, CvTerm unit, BigDecimal uncertainty) {
        super(type, value, unit, uncertainty);
    }

    /**
     * <p>Constructor for MitabParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     * @param uncertainty a {@link java.math.BigDecimal} object.
     */
    public MitabParameter(CvTerm type, ParameterValue value, BigDecimal uncertainty) {
        super(type, value, uncertainty);
    }

    /**
     * <p>Constructor for MitabParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     * @throws psidev.psi.mi.jami.exception.IllegalParameterException if any.
     */
    public MitabParameter(CvTerm type, String value) throws IllegalParameterException {
        super(type, value);
    }

    /**
     * <p>Constructor for MitabParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     * @param unit a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @throws psidev.psi.mi.jami.exception.IllegalParameterException if any.
     */
    public MitabParameter(CvTerm type, String value, CvTerm unit) throws IllegalParameterException {
        super(type, value, unit);
    }

    /**
     * <p>Constructor for MitabParameter.</p>
     *
     * @param type a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     * @param unit a {@link java.lang.String} object.
     * @throws psidev.psi.mi.jami.exception.IllegalParameterException if any.
     */
    public MitabParameter(String type, String value, String unit) throws IllegalParameterException {
        super(new MitabCvTerm(type!= null ? type : MitabUtils.UNKNOWN_TYPE), value != null ? value : "", unit != null ? new MitabCvTerm(unit) : null);
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return this.sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Mitab Parameter: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

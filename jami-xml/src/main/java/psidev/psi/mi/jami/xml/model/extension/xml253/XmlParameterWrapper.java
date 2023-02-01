package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.model.*;

import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;

/**
 * Xml parameter wrapper
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
@XmlTransient
public class XmlParameterWrapper implements ModelledParameter {

    private Parameter parameter;
    private Publication publication;

    /**
     * <p>Constructor for XmlParameterWrapper.</p>
     *
     * @param param a {@link psidev.psi.mi.jami.model.Parameter} object.
     */
    public XmlParameterWrapper(Parameter param){
        if (param == null){
            throw new IllegalArgumentException("A parameter wrapper needs a non null Parameter");
        }
        this.parameter = param;
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getType() {
        return this.parameter.getType();
    }

    /** {@inheritDoc} */
    @Override
    public BigDecimal getUncertainty() {
        return this.parameter.getUncertainty();
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getUnit() {
        return this.parameter.getUnit();
    }

    /** {@inheritDoc} */
    @Override
    public ParameterValue getValue() {
        return this.parameter.getValue();
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
        return this.parameter.toString();
    }
}

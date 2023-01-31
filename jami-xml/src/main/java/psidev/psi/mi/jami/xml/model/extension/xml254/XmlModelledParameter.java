package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ModelledParameter;
import psidev.psi.mi.jami.model.ParameterValue;
import psidev.psi.mi.jami.model.Publication;

import java.math.BigDecimal;

/**
 * Xml implementation of ModelledParameter
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlModelledParameter extends XmlParameter implements ModelledParameter {
    private Publication publication;

    /**
     * <p>Constructor for XmlModelledParameter.</p>
     */
    public XmlModelledParameter() {
        super();
    }

    /**
     * <p>Constructor for XmlModelledParameter.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link psidev.psi.mi.jami.model.ParameterValue} object.
     * @param uncertainty a {@link java.math.BigDecimal} object.
     * @param unit a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledParameter(CvTerm type, ParameterValue value, BigDecimal uncertainty, CvTerm unit) {
        super(type, value, uncertainty, unit);
    }

    /**
     * <p>Getter for the field <code>publication</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public Publication getPublication() {
        if (publication == null){
            if (getExperiment() != null){
                this.publication = getExperiment().getPublication();
            }
        }
        return this.publication;
    }

    /** {@inheritDoc} */
    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}

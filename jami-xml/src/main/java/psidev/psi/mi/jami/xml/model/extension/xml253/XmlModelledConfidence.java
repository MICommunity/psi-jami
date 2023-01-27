package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.ModelledConfidence;
import psidev.psi.mi.jami.model.Publication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Xml implementation of ModelledConfidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlModelledConfidence extends XmlConfidence implements ModelledConfidence {

    Publication publication;

    /**
     * <p>Constructor for XmlModelledConfidence.</p>
     */
    public XmlModelledConfidence() {
        super();

    }

    /**
     * <p>Constructor for XmlModelledConfidence.</p>
     *
     * @param type a {@link XmlOpenCvTerm} object.
     * @param value a {@link java.lang.String} object.
     */
    public XmlModelledConfidence(XmlOpenCvTerm type, String value) {
        super(type, value);
    }

    /**
     * <p>Getter for the field <code>publication</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public Publication getPublication() {
        if (publication == null){
            for (Experiment exp : getExperiments()){
                if (exp.getPublication() != null){
                    this.publication = exp.getPublication();
                    break;
                }
            }
        }
        return this.publication;
    }

    /** {@inheritDoc} */
    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}

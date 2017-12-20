package psidev.psi.mi.jami.xml.model.extension.xml300;

import psidev.psi.mi.jami.model.ModelledConfidence;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.xml.model.extension.AbstractXmlConfidence;
import psidev.psi.mi.jami.xml.model.extension.BibRef;
import psidev.psi.mi.jami.xml.model.extension.XmlOpenCvTerm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Xml 3.0 implementation of ModelledConfidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlModelledConfidence extends AbstractXmlConfidence implements ModelledConfidence {

    private Publication publication;

    /**
     * <p>Constructor for XmlModelledConfidence.</p>
     */
    public XmlModelledConfidence() {
        super();

    }

    /**
     * <p>Constructor for XmlModelledConfidence.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.xml.model.extension.XmlOpenCvTerm} object.
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
        return this.publication;
    }

    /** {@inheritDoc} */
    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    /**
     * <p>setJAXBPublication.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.xml.model.extension.BibRef} object.
     */
    @XmlElement(name="bibref")
    public void setJAXBPublication(BibRef publication) {
        setPublication(publication);
    }
}

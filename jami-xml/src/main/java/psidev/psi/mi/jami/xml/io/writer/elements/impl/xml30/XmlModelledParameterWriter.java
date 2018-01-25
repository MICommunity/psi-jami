package psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30;

import psidev.psi.mi.jami.model.ModelledParameter;
import psidev.psi.mi.jami.model.Parameter;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlParameterWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * XML 3.0 writer of a modelled parameter
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public class XmlModelledParameterWriter extends AbstractXmlParameterWriter {

    private PsiXmlPublicationWriter publicationWriter;

    /**
     * <p>Constructor for XmlModelledParameterWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlModelledParameterWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        super(writer, objectIndex);
    }

    /**
     * <p>Getter for the field <code>publicationWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     */
    public PsiXmlPublicationWriter getPublicationWriter() {
        if (this.publicationWriter == null){
            initialisePublicationWriter();
        }
        return publicationWriter;
    }

    /**
     * <p>initialisePublicationWriter.</p>
     */
    protected void initialisePublicationWriter() {
        this.publicationWriter = new XmlPublicationWriter(getStreamWriter());
    }

    /**
     * <p>Setter for the field <code>publicationWriter</code>.</p>
     *
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     */
    public void setPublicationWriter(PsiXmlPublicationWriter publicationWriter) {
        this.publicationWriter = publicationWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherProperties(Parameter object) throws XMLStreamException {
        // write publication if not done yet
        ModelledParameter modelledParam = (ModelledParameter)object;

        // write bibref if necessary
        if (modelledParam.getPublication() != null){
            getPublicationWriter().write(modelledParam.getPublication());
        }
    }
}

package psidev.psi.mi.jami.xml.io.writer.elements.impl;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Confidence;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Xml25 writer for confidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public class XmlConfidenceWriter implements PsiXmlElementWriter<Confidence> {
    private XMLStreamWriter streamWriter;
    private PsiXmlVariableNameWriter<CvTerm> typeWriter;

    /**
     * <p>Constructor for XmlConfidenceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    public XmlConfidenceWriter(XMLStreamWriter writer){
        if (writer == null){
            throw new IllegalArgumentException("The XML stream writer is mandatory for the XmlConfidenceWriter");
        }
        this.streamWriter = writer;
    }

    /**
     * <p>Getter for the field <code>typeWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public PsiXmlVariableNameWriter<CvTerm> getTypeWriter() {
        if (this.typeWriter == null){
            initialiseTypeWriter();

        }
        return typeWriter;
    }

    /**
     * <p>initialiseTypeWriter.</p>
     */
    protected void initialiseTypeWriter() {
        this.typeWriter = new XmlOpenCvTermWriter(streamWriter);
    }

    /**
     * <p>Setter for the field <code>typeWriter</code>.</p>
     *
     * @param typeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public void setTypeWriter(PsiXmlVariableNameWriter<CvTerm> typeWriter) {
        this.typeWriter = typeWriter;
    }

    /** {@inheritDoc} */
    @Override
    public void write(Confidence object) throws MIIOException {
        if (object != null){
            try {
                // write start
                this.streamWriter.writeStartElement("confidence");
                // write confidence type
                CvTerm type = object.getType();
                getTypeWriter().write(type, "unit");
                // write value
                this.streamWriter.writeStartElement("value");
                this.streamWriter.writeCharacters(object.getValue());
                this.streamWriter.writeEndElement();

                // write other properties
                writeOtherProperties(object);

                // write end confidence
                this.streamWriter.writeEndElement();

            } catch (XMLStreamException e) {
                throw new MIIOException("Impossible to write the confidence : "+object.toString(), e);
            }
        }
    }

    /**
     * <p>writeOtherProperties.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Confidence} object.
     */
    protected void writeOtherProperties(Confidence object) {
        // nothing to do here
    }

    /**
     * <p>Getter for the field <code>streamWriter</code>.</p>
     *
     * @return a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    protected XMLStreamWriter getStreamWriter() {
        return streamWriter;
    }
}

package psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.CooperativityEvidence;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlCvTermWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Xml 3.0 writer for cooperativity evidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public class XmlCooperativityEvidenceWriter implements PsiXmlElementWriter<CooperativityEvidence> {
    private XMLStreamWriter streamWriter;
    private PsiXmlElementWriter<Publication> publicationWriter;
    private PsiXmlVariableNameWriter<CvTerm> cvWriter;

    /**
     * <p>Constructor for XmlCooperativityEvidenceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    public XmlCooperativityEvidenceWriter(XMLStreamWriter writer){
        if (writer == null){
            throw new IllegalArgumentException("The XML stream writer is mandatory for the XmlCooperativityEvidenceWriter");
        }
        this.streamWriter = writer;
    }

    /**
     * <p>Getter for the field <code>publicationWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Publication> getPublicationWriter() {
        if (this.publicationWriter == null){
            initialisePublicationWriter();
        }
        return publicationWriter;
    }

    /**
     * <p>initialisePublicationWriter.</p>
     */
    protected void initialisePublicationWriter() {
        this.publicationWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlPublicationWriter(streamWriter);
    }

    /**
     * <p>Setter for the field <code>publicationWriter</code>.</p>
     *
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setPublicationWriter(PsiXmlElementWriter<Publication> publicationWriter) {
        this.publicationWriter = publicationWriter;
    }

    /**
     * <p>Getter for the field <code>cvWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public PsiXmlVariableNameWriter<CvTerm> getCvWriter() {
        if (this.cvWriter == null){
            initialiseCvWriter();
        }
        return cvWriter;
    }

    /**
     * <p>initialiseCvWriter.</p>
     */
    protected void initialiseCvWriter() {
        this.cvWriter = new XmlCvTermWriter(streamWriter);
    }

    /**
     * <p>Setter for the field <code>cvWriter</code>.</p>
     *
     * @param cvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public void setCvWriter(PsiXmlVariableNameWriter<CvTerm> cvWriter) {
        this.cvWriter = cvWriter;
    }

    /** {@inheritDoc} */
    @Override
    public void write(CooperativityEvidence object) throws MIIOException {
        try {
            // write start
            this.streamWriter.writeStartElement("cooperativityEvidenceDescription");
            // write publication
            writePublication(object);
            // write evidences
            writeEvidenceMethods(object);
            // write end cooperativity evidence
            this.streamWriter.writeEndElement();

        } catch (XMLStreamException e) {
            throw new MIIOException("Impossible to write the participant : "+object.toString(), e);
        }
    }

    private void writeEvidenceMethods(CooperativityEvidence object) throws XMLStreamException {
        if (!object.getEvidenceMethods().isEmpty()){
            // write start
            this.streamWriter.writeStartElement("evidenceMethodList");
            for (CvTerm term : object.getEvidenceMethods()){
                getCvWriter().write(term, "evidenceMethod");
            }
            // write end list
            this.streamWriter.writeEndElement();
        }
    }

    /**
     * <p>writePublication.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.CooperativityEvidence} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writePublication(CooperativityEvidence object) throws XMLStreamException {
        getPublicationWriter().write(object.getPublication());
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

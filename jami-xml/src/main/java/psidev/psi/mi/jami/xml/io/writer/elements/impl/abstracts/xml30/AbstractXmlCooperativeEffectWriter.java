package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlAnnotationWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlCvTermWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlCooperativityEvidenceWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Abstract Xml 3.0 writer for cooperative effect
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public abstract class AbstractXmlCooperativeEffectWriter<C extends CooperativeEffect> implements PsiXmlElementWriter<C> {
    private XMLStreamWriter streamWriter;
    private PsiXmlElementWriter<CooperativityEvidence> cooperativityEvidenceWriter;
    private PsiXmlVariableNameWriter<CvTerm> cvWriter;
    private PsiXmlObjectCache objectIndex;
    private PsiXmlElementWriter<Annotation> attributeWriter;

    /**
     * <p>Constructor for AbstractXmlCooperativeEffectWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlCooperativeEffectWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        if (writer == null){
            throw new IllegalArgumentException("The XML stream writer is mandatory for the AbstractXmlCooperativeEffectWriter");
        }
        this.streamWriter = writer;
        if (objectIndex == null){
            throw new IllegalArgumentException("The PsiXml 3.0 object index is mandatory for the AbstractXmlCooperativeEffectWriter. It is necessary for generating an id to an interaction");
        }
        this.objectIndex = objectIndex;
    }

    /**
     * <p>Getter for the field <code>cooperativityEvidenceWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<CooperativityEvidence> getCooperativityEvidenceWriter() {
        if (this.cooperativityEvidenceWriter == null){
            initialiseCooperativityEvidenceWriter();
        }
        return cooperativityEvidenceWriter;
    }

    /**
     * <p>initialiseCooperativityEvidenceWriter.</p>
     */
    protected void initialiseCooperativityEvidenceWriter() {
        this.cooperativityEvidenceWriter = new XmlCooperativityEvidenceWriter(streamWriter);
    }

    /**
     * <p>Setter for the field <code>cooperativityEvidenceWriter</code>.</p>
     *
     * @param cooperativityEvidenceWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setCooperativityEvidenceWriter(PsiXmlElementWriter<CooperativityEvidence> cooperativityEvidenceWriter) {
        this.cooperativityEvidenceWriter = cooperativityEvidenceWriter;
    }

    /**
     * <p>Getter for the field <code>attributeWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Annotation> getAttributeWriter() {
        if (this.attributeWriter == null){
            this.attributeWriter = new XmlAnnotationWriter(streamWriter);
        }
        return attributeWriter;
    }

    /**
     * <p>Setter for the field <code>attributeWriter</code>.</p>
     *
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setAttributeWriter(PsiXmlElementWriter<Annotation> attributeWriter) {
        this.attributeWriter = attributeWriter;
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
    public void write(C object) throws MIIOException {
        try {
            // write start
            writeStartCooperativeEffect();
            // write cooperativity evidence list
            writeCooperativityEvidenceList(object);
            // write affected interaction list
            writeAffectedInteractionList(object);
            // write effect outcome
            writeOutcome(object);
            // write effect response
            writeResponse(object);
            // write attributes
            writeAttributes(object);
            // write other properties
            writeOtherProperties(object);
            // write end cooperative effect
            this.streamWriter.writeEndElement();

        } catch (XMLStreamException e) {
            throw new MIIOException("Impossible to write the participant : "+object.toString(), e);
        }
    }

    /**
     * <p>writeOutcome.</p>
     *
     * @param object a C object.
     */
    protected void writeOutcome(C object) {
        if (object.getOutCome() != null){
            getCvWriter().write(object.getOutCome(), "cooperativeEffectOutcome");
        }
    }

    /**
     * <p>writeResponse.</p>
     *
     * @param object a C object.
     */
    protected void writeResponse(C object) {
        if (object.getResponse() != null){
            getCvWriter().write(object.getResponse(), "cooperativeEffectResponse");
        }
    }

    /**
     * <p>writeAttributes.</p>
     *
     * @param object a C object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeAttributes(C object) throws XMLStreamException {
        // write attributes
        if (!object.getAnnotations().isEmpty()){
            // write start attribute list
            this.streamWriter.writeStartElement("attributeList");
            for (Object ann : object.getAnnotations()){
                getAttributeWriter().write((Annotation)ann);
            }
            // write end attributeList
            this.streamWriter.writeEndElement();
        }
    }

    /**
     * <p>writeOtherProperties.</p>
     *
     * @param object a C object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeOtherProperties(C object) throws XMLStreamException;

    /**
     * <p>writeAffectedInteractionList.</p>
     *
     * @param object a C object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeAffectedInteractionList(C object) throws XMLStreamException {
        if (!object.getAffectedInteractions().isEmpty()){
            // write start
            this.streamWriter.writeStartElement("affectedInteractionList");
            for (ModelledInteraction interaction : object.getAffectedInteractions()){
                // write start interaction
                this.streamWriter.writeStartElement("affectedInteractionRef");
                getStreamWriter().writeCharacters(Integer.toString(getObjectIndex().extractIdForInteraction(interaction)));
                // write end interaction
                this.streamWriter.writeEndElement();
            }
            // write end list
            this.streamWriter.writeEndElement();
        }
    }

    /**
     * <p>writeStartCooperativeEffect.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeStartCooperativeEffect()throws XMLStreamException ;

    /**
     * <p>writeCooperativityEvidenceList.</p>
     *
     * @param object a C object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeCooperativityEvidenceList(C object) throws XMLStreamException {
        if (!object.getCooperativityEvidences().isEmpty()){
            // write start
            this.streamWriter.writeStartElement("cooperativityEvidenceList");
            for (CooperativityEvidence evidence : object.getCooperativityEvidences()){
                getCooperativityEvidenceWriter().write(evidence);
            }
            // write end list
            this.streamWriter.writeEndElement();
        }
    }

    /**
     * <p>Getter for the field <code>streamWriter</code>.</p>
     *
     * @return a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    protected XMLStreamWriter getStreamWriter() {
        return streamWriter;
    }

    /**
     * <p>Getter for the field <code>objectIndex</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    protected PsiXmlObjectCache getObjectIndex() {
        return objectIndex;
    }

}

package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultPublication;
import psidev.psi.mi.jami.utils.ExperimentUtils;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.*;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.*;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Date;
import java.util.Iterator;

/**
 * Abstract PSI-XML experiment writer
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public abstract class AbstractXmlExperimentWriter implements PsiXmlExperimentWriter {

    private PsiXmlVersion version;
    private XMLStreamWriter streamWriter;
    private PsiXmlObjectCache objectIndex;
    private PsiXmlPublicationWriter publicationWriter;
    private PsiXmlXrefWriter xrefWriter;
    private PsiXmlElementWriter<Organism> hostOrganismWriter;
    private PsiXmlVariableNameWriter<CvTerm> detectionMethodWriter;
    private PsiXmlElementWriter<Annotation> attributeWriter;
    private PsiXmlElementWriter<Confidence> confidenceWriter;
    private Publication defaultPublication;

    /**
     * <p>Constructor for AbstractXmlExperimentWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlExperimentWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        this.version = version;
        if (writer == null){
            throw new IllegalArgumentException("The XML stream writer is mandatory for the XmlExperimentWriter");
        }
        this.streamWriter = writer;
        if (objectIndex == null){
            throw new IllegalArgumentException("The PsiXml object index is mandatory for the XmlExperimentWriter. It is necessary for generating an id to an experimentDescription");
        }
        this.objectIndex = objectIndex;
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
    protected abstract void initialisePublicationWriter();

    /**
     * <p>Setter for the field <code>publicationWriter</code>.</p>
     *
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     */
    public void setPublicationWriter(PsiXmlPublicationWriter publicationWriter) {
        this.publicationWriter = publicationWriter;
    }

    /**
     * <p>Getter for the field <code>xrefWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     */
    public PsiXmlXrefWriter getXrefWriter() {
        if (this.xrefWriter == null){
            initialiseXrefWriter();
        }
        return xrefWriter;
    }

    /**
     * <p>initialiseXrefWriter.</p>
     */
    protected abstract void initialiseXrefWriter();

    /**
     * <p>Setter for the field <code>xrefWriter</code>.</p>
     *
     * @param xrefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     */
    public void setXrefWriter(PsiXmlXrefWriter xrefWriter) {
        this.xrefWriter = xrefWriter;
    }

    /**
     * <p>Getter for the field <code>hostOrganismWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Organism> getHostOrganismWriter() {
        if (this.hostOrganismWriter == null){
            initialiseHostOrganismWriter();
        }
        return hostOrganismWriter;
    }

    /**
     * <p>initialiseHostOrganismWriter.</p>
     */
    protected abstract void initialiseHostOrganismWriter();

    /**
     * <p>Setter for the field <code>hostOrganismWriter</code>.</p>
     *
     * @param hostOrganismWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setHostOrganismWriter(PsiXmlElementWriter<Organism> hostOrganismWriter) {
        this.hostOrganismWriter = hostOrganismWriter;
    }

    /**
     * <p>Getter for the field <code>detectionMethodWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public PsiXmlVariableNameWriter<CvTerm> getDetectionMethodWriter() {
        if (this.detectionMethodWriter == null){
            initialiseDetectionMethodWriter();
        }
        return detectionMethodWriter;
    }

    /**
     * <p>initialiseDetectionMethodWriter.</p>
     */
    protected abstract void initialiseDetectionMethodWriter();

    /**
     * <p>Setter for the field <code>detectionMethodWriter</code>.</p>
     *
     * @param detectionMethodWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public void setDetectionMethodWriter(PsiXmlVariableNameWriter<CvTerm> detectionMethodWriter) {
        this.detectionMethodWriter = detectionMethodWriter;
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
     * <p>Getter for the field <code>confidenceWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Confidence> getConfidenceWriter() {
        if (this.confidenceWriter == null){
            initialiseConfidenceWriter();
        }
        return confidenceWriter;
    }

    /**
     * <p>initialiseConfidenceWriter.</p>
     */
    protected abstract void initialiseConfidenceWriter();

    /**
     * <p>Setter for the field <code>confidenceWriter</code>.</p>
     *
     * @param confidenceWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setConfidenceWriter(PsiXmlElementWriter<Confidence> confidenceWriter) {
        this.confidenceWriter = confidenceWriter;
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm writeExperiment(Experiment object) throws MIIOException {
        CvTerm det = null;
        try {
            // write start
            this.streamWriter.writeStartElement("experimentDescription");
            int id = this.objectIndex.extractIdForExperiment(object);
            // write id attribute
            this.streamWriter.writeAttribute("id", Integer.toString(id));

            // write names
            writeNames(object);
            // write publication and xrefs
            writePublicationAndXrefs(object);
            // write host organism
            writeHostOrganism(object);
            // write interaction detection method
            writeInteractiondetectionMethod(object);
            // write participant identification method
            det = writeParticipantIdentificationMethod(object);
            // write other properties
            writeOtherProperties(object);
            // write confidences
            writeConfidences(object);
            // write variable parameters
            writeVariableParameters(object);
            // write attribute list
            writeAttributes(object);

            // write end experiment
            this.streamWriter.writeEndElement();

            return det;

        } catch (XMLStreamException e) {
            throw new MIIOException("Impossible to write the experiment : "+object.toString(), e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void write(Experiment object) throws MIIOException {
        writeExperiment(object);
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm extractDefaultParticipantIdentificationMethod(Experiment exp) {
        return ExperimentUtils.extractMostCommonParticipantDetectionMethodFrom(exp);
    }

    /**
     * <p>writeParticipantIdentificationMethod.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    protected CvTerm writeParticipantIdentificationMethod(Experiment object){
        CvTerm pdet = ExperimentUtils.extractMostCommonParticipantDetectionMethodFrom(object);

        if (pdet != null){
            // write cv
            getDetectionMethodWriter().write(pdet, "participantIdentificationMethod");
        }
        return pdet;
    }

    /**
     * <p>writeVariableParameters.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeVariableParameters(Experiment object) throws XMLStreamException;

    /**
     * <p>writeOtherProperties.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeOtherProperties(Experiment object) throws XMLStreamException {
        // nothing to do here
    }

    /**
     * <p>writeConfidences.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeConfidences(Experiment object) throws XMLStreamException {
       if (!object.getConfidences().isEmpty()){
           // write start confidence list
           this.streamWriter.writeStartElement("confidenceList");
           for (Confidence conf : object.getConfidences()){
               getConfidenceWriter().write(conf);
           }
           // write end confidence
           this.streamWriter.writeEndElement();
       }
    }

    /**
     * <p>writeAttributes.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeAttributes(Experiment object) throws XMLStreamException {
        // write annotations from experiment first
        if (!object.getAnnotations().isEmpty()){
            // write start attribute list
            this.streamWriter.writeStartElement("attributeList");
            for (Annotation ann : object.getAnnotations()){
                getAttributeWriter().write(ann);
            }

            // write publication attributes if not done at the bibref level
            writeOtherAttributes(object, false);

            // write end attributeList
            this.streamWriter.writeEndElement();
        }
        // write annotations from publication
        else{
            writeOtherAttributes(object, true);
        }
    }

    /**
     * <p>writeOtherAttributes.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param needToWriteAttributeList a boolean.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeOtherAttributes(Experiment object, boolean needToWriteAttributeList) throws XMLStreamException;

    /**
     * <p>writeInteractiondetectionMethod.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeInteractiondetectionMethod(Experiment object) throws XMLStreamException {
        CvTerm detectionMethod = object.getInteractionDetectionMethod();
        // write cv
        getDetectionMethodWriter().write(detectionMethod, "interactionDetectionMethod");
    }

    /**
     * <p>writeHostOrganism.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeHostOrganism(Experiment object) throws XMLStreamException {
        Organism host = object.getHostOrganism();
        if (host != null){
            // write start host organism list
            this.streamWriter.writeStartElement("hostOrganismList");
            // write host
            getHostOrganismWriter().write(host);
            // write end host organism list
            this.streamWriter.writeEndElement();
        }
    }

    /**
     * <p>writePublicationAndXrefs.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writePublicationAndXrefs(Experiment object) throws XMLStreamException {
        String imexId=null;
        // write publication
        Publication publication = object.getPublication();
        if (publication != null){
            getPublicationWriter().write(publication);
            imexId = publication.getImexId();
        }
        else{
            getPublicationWriter().write(getDefaultPublication());
            imexId = getDefaultPublication().getImexId();
        }
        // write xrefs
        writeExperimentXrefs(object, imexId);
    }

    /**
     * <p>writeExperimentXrefs.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param imexId a {@link java.lang.String} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeExperimentXrefs(Experiment object, String imexId) throws XMLStreamException;

    /**
     * <p>writeXrefFromExperimentXrefs.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param imexId a {@link java.lang.String} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeXrefFromExperimentXrefs(Experiment object, String imexId) throws XMLStreamException {
        Iterator<Xref> refIterator = object.getXrefs().iterator();
        // default qualifier is null as we are not processing identifiers
        getXrefWriter().setDefaultRefType(null);
        getXrefWriter().setDefaultRefTypeAc(null);

        int index = 0;
        while (refIterator.hasNext()){
            Xref ref = refIterator.next();
            // write primaryRef
            if (index == 0){
                getXrefWriter().write(ref,"primaryRef");
                index++;
            }
            // write secondaryref
            else{
                getXrefWriter().write(ref,"secondaryRef");
                index++;
            }
        }
    }

    /**
     * <p>writeNames.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeNames(Experiment object) throws XMLStreamException {
        boolean hasPublicationTitle = object.getPublication() != null && object.getPublication().getTitle() != null;

        if (hasPublicationTitle){
            this.streamWriter.writeStartElement("names");

            // write fullname
            this.streamWriter.writeStartElement("fullName");
            this.streamWriter.writeCharacters(object.getPublication().getTitle());
            this.streamWriter.writeEndElement();

            // write end names
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

    /** {@inheritDoc} */
    @Override
    public Publication getDefaultPublication() {
        if (this.defaultPublication == null){
            initialiseDefaultPublication();
        }
        return this.defaultPublication;
    }

    /** {@inheritDoc} */
    @Override
    public void setDefaultPublication(Publication pub) {
        if (pub == null){
            throw new IllegalArgumentException("The default publication cannot be null");
        }
        this.defaultPublication = pub;
    }

    /**
     * <p>initialiseDefaultPublication.</p>
     */
    protected void initialiseDefaultPublication(){
        this.defaultPublication = new DefaultPublication("Mock publication for experiments that do not have a publication reference",(String)null,(Date)null);
    }

    protected PsiXmlVersion getVersion() {
        return version;
    }
}

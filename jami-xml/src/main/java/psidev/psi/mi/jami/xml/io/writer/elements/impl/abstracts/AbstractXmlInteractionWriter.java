package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts;

import psidev.psi.mi.jami.analysis.graph.BindingSiteCliqueFinder;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.InteractionUtils;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.*;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlAnnotationWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlChecksumWriter;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlExperiment;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Abstract writer of interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public abstract class AbstractXmlInteractionWriter<T extends Interaction, P extends Participant> implements PsiXmlInteractionWriter<T> {

    private PsiXmlVersion version;
    private XMLStreamWriter streamWriter;
    private PsiXmlObjectCache objectIndex;
    private PsiXmlXrefWriter xrefWriter;
    private PsiXmlParticipantWriter<P> participantWriter;
    private PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter;
    private PsiXmlElementWriter<Annotation> attributeWriter;
    private PsiXmlElementWriter<Set<Feature>> inferredInteractionWriter;
    private Experiment defaultExperiment;
    private PsiXmlExperimentWriter experimentWriter;
    private PsiXmlElementWriter<Checksum> checksumWriter;

    /**
     * <p>Constructor for AbstractXmlInteractionWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlInteractionWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        this.version = version;
        if (writer == null){
            throw new IllegalArgumentException("The XML stream writer is mandatory for the AbstractXmlInteractionWriter");
        }
        this.streamWriter = writer;

        if (objectIndex == null){
            throw new IllegalArgumentException("The PsiXml 2.5 object index is mandatory for the AbstractXmlInteractionWriter. It is necessary for generating an id to an experimentDescription");
        }
        this.objectIndex = objectIndex;
    }

    /**
     * <p>Getter for the field <code>participantWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParticipantWriter} object.
     */
    public PsiXmlParticipantWriter<P> getParticipantWriter() {
        if (this.participantWriter == null){
            initialiseParticipantWriter();
        }
        return participantWriter;
    }

    /**
     * <p>initialiseParticipantWriter.</p>
     */
    protected abstract void initialiseParticipantWriter();

    /**
     * <p>Setter for the field <code>participantWriter</code>.</p>
     *
     * @param participantWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParticipantWriter} object.
     */
    public void setParticipantWriter(PsiXmlParticipantWriter<P> participantWriter) {
        this.participantWriter = participantWriter;
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
     * <p>Setter for the field <code>interactionTypeWriter</code>.</p>
     *
     * @param interactionTypeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public void setInteractionTypeWriter(PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter) {
        this.interactionTypeWriter = interactionTypeWriter;
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
     * <p>Getter for the field <code>inferredInteractionWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Set<Feature>> getInferredInteractionWriter() {
        if (this.inferredInteractionWriter == null){
            initialiseInferredInteractionWriter();
        }
        return inferredInteractionWriter;
    }

    /**
     * <p>initialiseInferredInteractionWriter.</p>
     */
    protected abstract void initialiseInferredInteractionWriter();

    /**
     * <p>Setter for the field <code>inferredInteractionWriter</code>.</p>
     *
     * @param inferredInteractionWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setInferredInteractionWriter(PsiXmlElementWriter<Set<Feature>> inferredInteractionWriter) {
        this.inferredInteractionWriter = inferredInteractionWriter;
    }

    /**
     * <p>Setter for the field <code>experimentWriter</code>.</p>
     *
     * @param experimentWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExperimentWriter} object.
     */
    public void setExperimentWriter(PsiXmlExperimentWriter experimentWriter) {
        this.experimentWriter = experimentWriter;
    }

    /**
     * <p>Setter for the field <code>checksumWriter</code>.</p>
     *
     * @param checksumWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setChecksumWriter(PsiXmlElementWriter<Checksum> checksumWriter) {
        this.checksumWriter = checksumWriter;
    }

    /**
     * <p>Getter for the field <code>experimentWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExperimentWriter} object.
     */
    public PsiXmlExperimentWriter getExperimentWriter() {
        if (this.experimentWriter == null){
            initialiseExperimentWriter();
        }
        return experimentWriter;
    }

    /**
     * <p>initialiseExperimentWriter.</p>
     */
    protected abstract void initialiseExperimentWriter();

    /**
     * <p>Getter for the field <code>checksumWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Checksum> getChecksumWriter() {
        if (this.checksumWriter == null){
            this.checksumWriter = new XmlChecksumWriter(streamWriter);
        }
        return checksumWriter;
    }

    /**
     * <p>Getter for the field <code>interactionTypeWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public PsiXmlVariableNameWriter<CvTerm> getInteractionTypeWriter() {
        if (this.interactionTypeWriter == null){
            initialiseInteractionTypeWriter();
        }
        return interactionTypeWriter;
    }

    /**
     * <p>initialiseInteractionTypeWriter.</p>
     */
    protected abstract void initialiseInteractionTypeWriter();

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

    /** {@inheritDoc} */
    @Override
    public void write(T object) throws MIIOException {
        try {
            // write start
            writeStartInteraction();
            // write id attribute
            int id = this.objectIndex.extractIdForInteraction(object);
            this.streamWriter.writeAttribute("id", Integer.toString(id));
            // write other attributes (such as imex id)
            writeOtherAttributes(object);

            // write names
            writeNames(object);
            // write Xref
            writeXref(object);
            // write availability
            writeAvailability(object);
            // write experiments
            CvTerm participantMethod = writeExperiments(object);
            // write participants
            writeParticipants(object, participantMethod);
            // write inferred interactions
            writeInferredInteractions(object);
            // write interaction type
            writeInteractionType(object);
            // write modelled
            writeModelled(object);
            // write intramolecular
            writeIntraMolecular(object);
            // write negative
            writeNegative(object);
            // write confidences
            writeConfidences(object);
            // write parameters
            writeParameters(object);
            // write other properties
            writeOtherProperties(object);
            // write attributes
            writeAttributes(object);
            // write end interaction
            this.streamWriter.writeEndElement();

        } catch (XMLStreamException e) {
            throw new MIIOException("Impossible to write the interaction : "+object.toString(), e);
        }
    }

    /**
     * <p>writeOtherProperties.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeOtherProperties(T object) throws XMLStreamException;

    /**
     * <p>writeStartInteraction.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeStartInteraction() throws XMLStreamException;

    /**
     * <p>Getter for the field <code>defaultExperiment</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public Experiment getDefaultExperiment() {
        if (this.defaultExperiment == null){
            initialiseDefaultExperiment();
        }
        return defaultExperiment;
    }

    /** {@inheritDoc} */
    public void setDefaultExperiment(Experiment defaultExperiment) {
        this.defaultExperiment = defaultExperiment;
    }

    /** {@inheritDoc} */
    @Override
    public Experiment extractDefaultExperimentFrom(T interaction) {
        return getDefaultExperiment();
    }

    /** {@inheritDoc} */
    @Override
    public boolean writeComplexAsInteractor() {
        return this.participantWriter.writeComplexAsInteractor();
    }

    /** {@inheritDoc} */
    @Override
    public void setComplexAsInteractor(boolean complexAsInteractor) {
        getParticipantWriter().setComplexAsInteractor(complexAsInteractor);
    }

    /**
     * <p>writeAttributes.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeAttributes(T object) throws XMLStreamException {
        // write attributes
        if (!object.getAnnotations().isEmpty()){
            // write start attribute list
            this.streamWriter.writeStartElement("attributeList");
            for (Object ann : object.getAnnotations()){
                getAttributeWriter().write((Annotation)ann);
            }
            for (Object c : object.getChecksums()){
                getChecksumWriter().write((Checksum)c);
            }
            // write end attributeList
            this.streamWriter.writeEndElement();
        }
        // write checksum
        else if (!object.getChecksums().isEmpty()){
            // write start attribute list
            this.streamWriter.writeStartElement("attributeList");
            for (Object c : object.getChecksums()){
                getChecksumWriter().write((Checksum)c);
            }
            // write end attributeList
            this.streamWriter.writeEndElement();
        }
    }

    /**
     * <p>writeInteractionType.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeInteractionType(T object) throws XMLStreamException {
        if (object.getInteractionType() != null){
            getInteractionTypeWriter().write(object.getInteractionType(),"interactionType");
        }
    }

    /**
     * <p>writeInferredInteractions.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeInferredInteractions(T object) throws XMLStreamException;

    /**
     * <p>writeParticipants.</p>
     *
     * @param object a T object.
     * @param method a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeParticipants(T object, CvTerm method) throws XMLStreamException {
        if (!object.getParticipants().isEmpty()){
            this.streamWriter.writeStartElement("participantList");
            for (Object participant : object.getParticipants()){
                getParticipantWriter().writeParticipant((P)participant, method);
            }
            this.streamWriter.writeEndElement();
        }
    }

    /**
     * <p>writeNames.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeNames(T object) throws XMLStreamException {
        boolean hasShortLabel = object.getShortName() != null;
        if (hasShortLabel){
            this.streamWriter.writeStartElement("names");
            // write shortname
            this.streamWriter.writeStartElement("shortLabel");
            this.streamWriter.writeCharacters(object.getShortName());
            this.streamWriter.writeEndElement();
            // write end names
            this.streamWriter.writeEndElement();
        }
    }

    /**
     * <p>writeAvailability.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeAvailability(T object) throws XMLStreamException;
    /**
     * <p>writeExperiments.</p>
     *
     * @param object a T object.
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract CvTerm writeExperiments(T object) throws XMLStreamException;
    /**
     * <p>writeOtherAttributes.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeOtherAttributes(T object) throws XMLStreamException;
    /**
     * <p>writeIntraMolecular.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeIntraMolecular(T object) throws XMLStreamException{
        ComplexType type = InteractionUtils.findInteractionCategoryOf(object,true);
        if (type != null && type == ComplexType.self_intra_molecular){
            getStreamWriter().writeStartElement("intraMolecular");
            getStreamWriter().writeCharacters("true");
            // write end intra molecular
            getStreamWriter().writeEndElement();
        }
    }
    /**
     * <p>writeModelled.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeModelled(T object) throws XMLStreamException;

    /**
     * <p>writeXref.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeXref(T object) throws XMLStreamException {
        if (!object.getIdentifiers().isEmpty()){
            writeXrefFromInteractionIdentifiers(object);
        }
        else if (!object.getXrefs().isEmpty()){
            writeXrefFromInteractionXrefs(object);
        }
    }

    /**
     * <p>writeXrefFromInteractionXrefs.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeXrefFromInteractionXrefs(T object) throws XMLStreamException {
        Iterator<Xref> refIterator = object.getXrefs().iterator();
        // default qualifier is null as we are not processing identifiers
        getXrefWriter().setDefaultRefType(null);
        getXrefWriter().setDefaultRefTypeAc(null);
        // write start xref
        this.streamWriter.writeStartElement("xref");

        int index = 0;
        while (refIterator.hasNext()){
            Xref ref = refIterator.next();
            // write primaryRef
            if (index == 0){
                getXrefWriter().write(ref, "primaryRef");
                index++;
            }
            // write secondaryref
            else{
                getXrefWriter().write(ref,"secondaryRef");
                index++;
            }
        }

        // write end xref
        this.streamWriter.writeEndElement();
    }

    /**
     * <p>writeXrefFromInteractionIdentifiers.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeXrefFromInteractionIdentifiers(T object) throws XMLStreamException {
        // write start xref
        this.streamWriter.writeStartElement("xref");

        // all these xrefs are identity
        getXrefWriter().setDefaultRefType(Xref.IDENTITY);
        getXrefWriter().setDefaultRefTypeAc(Xref.IDENTITY_MI);

        // write secondaryRefs and primary ref if not done already)
        Iterator<Xref> refIterator = object.getIdentifiers().iterator();
        boolean hasWrittenPrimaryRef = false;
        while (refIterator.hasNext()){
            Xref ref = refIterator.next();
            if (!hasWrittenPrimaryRef){
                hasWrittenPrimaryRef = true;
                getXrefWriter().write(ref, "primaryRef");
            }
            else{
                getXrefWriter().write(ref,"secondaryRef");
            }
        }

        // write other xrefs
        if (!object.getXrefs().isEmpty()){
            // default qualifier is null
            getXrefWriter().setDefaultRefType(null);
            getXrefWriter().setDefaultRefTypeAc(null);
            for (Object ref : object.getXrefs()){
                getXrefWriter().write((Xref)ref,"secondaryRef");
            }
        }

        // write end xref
        this.streamWriter.writeEndElement();
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

    /**
     * <p>writeParameters.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeParameters(T object) throws XMLStreamException;

    /**
     * <p>writeConfidences.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeConfidences(T object) throws XMLStreamException;

    /**
     * <p>writeNegative.</p>
     *
     * @param object a T object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeNegative(T object) throws XMLStreamException;

    /**
     * <p>writeExperimentRef.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected CvTerm writeExperimentRef() throws XMLStreamException {
        getStreamWriter().writeStartElement("experimentList");
        getStreamWriter().writeStartElement("experimentRef");
        getStreamWriter().writeCharacters(Integer.toString(getObjectIndex().extractIdForExperiment(getDefaultExperiment())));
        getStreamWriter().writeEndElement();
        getStreamWriter().writeEndElement();

        return getExperimentWriter().extractDefaultParticipantIdentificationMethod(getDefaultExperiment());
    }

    /**
     * <p>writeExperimentDescription.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected CvTerm writeExperimentDescription() throws XMLStreamException {
        getStreamWriter().writeStartElement("experimentList");
        CvTerm det = getExperimentWriter().writeExperiment(getDefaultExperiment());
        getStreamWriter().writeEndElement();
        return det;
    }

    /**
     * <p>initialiseDefaultExperiment.</p>
     */
    protected void initialiseDefaultExperiment(){
        this.defaultExperiment = newExperiment(newPublication(
                "Mock publication for interactions that do not have experimental details.",
                null,
                null));
    }

    /**
     * <p>writeAttribute.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param nameAc a {@link java.lang.String} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeAttribute(String name, String nameAc) throws XMLStreamException {
        // write start
        this.streamWriter.writeStartElement("attribute");
        // write topic
        this.streamWriter.writeAttribute("name", name);
        if (nameAc!= null){
            this.streamWriter.writeAttribute("nameAc", nameAc);
        }
        // write end attribute
        this.streamWriter.writeEndElement();
    }

    /**
     * <p>collectInferredInteractionsFrom.</p>
     *
     * @param object a T object.
     * @return a {@link java.util.Collection} object.
     */
    protected Collection<Set<Feature>> collectInferredInteractionsFrom(T object){
        BindingSiteCliqueFinder<T,Feature> cliqueFinder = new BindingSiteCliqueFinder<T, Feature>(object);
        return cliqueFinder.getAllMaximalCliques();
    }

    protected PsiXmlVersion getVersion() {
        return version;
    }

    protected ExtendedPsiXmlExperiment newExperiment(Publication publication) {
        switch (version) {
            case v3_0_0:
                return new psidev.psi.mi.jami.xml.model.extension.xml300.DefaultXmlExperiment(publication);
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlExperiment(publication);
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.DefaultXmlExperiment(publication);
        }
    }

    protected Publication newPublication(String title, String journal, Date publicationDate) {
        switch (version) {
            case v3_0_0:
                return new psidev.psi.mi.jami.xml.model.extension.xml300.BibRef(title, journal, publicationDate);
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.BibRef(title, journal, publicationDate);
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.BibRef(title, journal, publicationDate);
        }
    }

    protected Publication newPublication(String pubmed) {
        switch (version) {
            case v3_0_0:
                return new psidev.psi.mi.jami.xml.model.extension.xml300.BibRef(pubmed);
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.BibRef(pubmed);
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.BibRef(pubmed);
        }
    }

    protected CvTerm newXmlCvTerm(String shortName, CvTerm database, String id, CvTerm qualifier) {
        switch (getVersion()) {
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlCvTerm(
                        shortName,
                        new psidev.psi.mi.jami.xml.model.extension.xml253.XmlXref(database, id, qualifier));
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlCvTerm(
                        shortName,
                        new psidev.psi.mi.jami.xml.model.extension.xml254.XmlXref(database, id, qualifier));
        }
    }
}

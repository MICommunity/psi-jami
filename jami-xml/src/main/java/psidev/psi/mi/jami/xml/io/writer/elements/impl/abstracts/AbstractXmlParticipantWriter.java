package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParticipantWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.*;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Iterator;

/**
 * Abstract Xml writer for participant
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public abstract class AbstractXmlParticipantWriter<P extends Participant, F extends Feature> implements PsiXmlParticipantWriter<P> {
    private PsiXmlVersion version;
    private XMLStreamWriter streamWriter;
    private PsiXmlObjectCache objectIndex;
    private PsiXmlElementWriter<Alias> aliasWriter;
    private PsiXmlXrefWriter xrefWriter;
    private PsiXmlVariableNameWriter<CvTerm> biologicalRoleWriter;
    private PsiXmlElementWriter<F> featureWriter;
    private PsiXmlElementWriter<Annotation> attributeWriter;
    private PsiXmlElementWriter<Interactor> interactorWriter;
    private boolean writeComplexAsInteractor=false;

    /**
     * <p>Constructor for AbstractXmlParticipantWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlParticipantWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        this.version = version;
        if (writer == null){
            throw new IllegalArgumentException("The XML stream writer is mandatory for the AbstractXmlParticipantWriter");
        }
        this.streamWriter = writer;
        if (objectIndex == null){
            throw new IllegalArgumentException("The PsiXml 2.5 object index is mandatory for the AbstractXmlParticipantWriter. It is necessary for generating an id to an experimentDescription");
        }
        this.objectIndex = objectIndex;
    }

    /**
     * <p>Getter for the field <code>aliasWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Alias> getAliasWriter() {
        if (this.aliasWriter == null){
            this.aliasWriter =  new XmlAliasWriter(streamWriter);
        }
        return aliasWriter;
    }

    /**
     * <p>Setter for the field <code>aliasWriter</code>.</p>
     *
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setAliasWriter(PsiXmlElementWriter<Alias> aliasWriter) {
        this.aliasWriter = aliasWriter;
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
     * <p>Getter for the field <code>biologicalRoleWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public PsiXmlVariableNameWriter<CvTerm> getBiologicalRoleWriter() {
        if (this.biologicalRoleWriter == null){
            initialiseBiologicalRoleWriter();
        }
        return biologicalRoleWriter;
    }

    /**
     * <p>initialiseBiologicalRoleWriter.</p>
     */
    protected abstract void initialiseBiologicalRoleWriter();

    /**
     * <p>Setter for the field <code>biologicalRoleWriter</code>.</p>
     *
     * @param biologicalRoleWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public void setBiologicalRoleWriter(PsiXmlVariableNameWriter<CvTerm> biologicalRoleWriter) {
        this.biologicalRoleWriter = biologicalRoleWriter;
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
     * <p>Getter for the field <code>interactorWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Interactor> getInteractorWriter() {
        if (this.interactorWriter == null){
            initialiseInteractorWriter();
        }
        return interactorWriter;
    }

    /**
     * <p>initialiseInteractorWriter.</p>
     */
    protected abstract void initialiseInteractorWriter();

    /**
     * <p>Setter for the field <code>interactorWriter</code>.</p>
     *
     * @param interactorWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setInteractorWriter(PsiXmlElementWriter<Interactor> interactorWriter) {
        this.interactorWriter = interactorWriter;
    }

    /**
     * <p>Getter for the field <code>featureWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<F> getFeatureWriter() {
        if (featureWriter == null){
            initialiseFeatureWriter();
        }
        return featureWriter;
    }

    /**
     * <p>initialiseFeatureWriter.</p>
     */
    protected abstract void initialiseFeatureWriter();

    /**
     * <p>Setter for the field <code>featureWriter</code>.</p>
     *
     * @param featureWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setFeatureWriter(PsiXmlElementWriter<F> featureWriter) {
        this.featureWriter = featureWriter;
    }

    /** {@inheritDoc} */
    @Override
    public void write(P object) throws MIIOException {
        writeParticipant(object, null);
    }

    /** {@inheritDoc} */
    @Override
    public void writeParticipant(P object, CvTerm detectionMethod) throws MIIOException {
        try {
            // write start
            this.streamWriter.writeStartElement("participant");
            int id = this.objectIndex.extractIdForParticipant(object);
            // write id attribute
            this.streamWriter.writeAttribute("id", Integer.toString(id));
            // write names
            writeNames(object);
            // write Xref
            writeXref(object);
            // write interactor
            writeInteractor(object);
            // write participant identification methods
            writeParticipantIdentificationMethods(object, detectionMethod);
            // write biological role
            writeBiologicalRole(object);
            // write experimental roles
            writeExperimentalRoles(object);
            // write experimental preparations
            writeExperimentalPreparations(object);
            // write experimental interactor
            writeExperimentalInteractor(object);
            // write features
            writeFeatures(object);
            // write host organism
            writeHostOrganisms(object);
            // write confidences
            writeConfidences(object);
            // write parameters
            writeParameters(object);
            // write stoichiometry
            writeStoichiometry(object);
            // write attributes
            writeAttributes(object);
            // write end participant
            this.streamWriter.writeEndElement();

        } catch (XMLStreamException e) {
            throw new MIIOException("Impossible to write the participant : "+object.toString(), e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean writeComplexAsInteractor() {
        return this.writeComplexAsInteractor;
    }

    /** {@inheritDoc} */
    @Override
    public void setComplexAsInteractor(boolean complexAsInteractor) {
        this.writeComplexAsInteractor = complexAsInteractor;
    }
    /**
     * <p>writeStoichiometry.</p>
     *
     * @param object a P object.
     */
    protected abstract void writeStoichiometry(P object);

    /**
     * <p>writeOtherAttributes.</p>
     *
     * @param object a P object.
     * @param writeAttributeList a boolean.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeOtherAttributes(P object, boolean writeAttributeList) throws XMLStreamException;

    /**
     * <p>writeAttributes.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeAttributes(P object) throws XMLStreamException {
        // write attributes
        Stoichiometry stc = object.getStoichiometry();
        if (!object.getAnnotations().isEmpty()){
            // write start attribute list
            getStreamWriter().writeStartElement("attributeList");
            for (Object ann : object.getAnnotations()){
                getAttributeWriter().write((Annotation)ann);
            }
            // write stoichiometry attribute if not null
            writeOtherAttributes(object, false);
            // write end attributeList
            getStreamWriter().writeEndElement();
        }
        // write stoichiometry attribute if not null
        else {
            writeOtherAttributes(object, true);
        }
    }

    /**
     * <p>writeFeatures.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeFeatures(P object) throws XMLStreamException {
        if (!object.getFeatures().isEmpty()){
            // write start feature list
            this.streamWriter.writeStartElement("featureList");
            for (Object feature : object.getFeatures()){
                getFeatureWriter().write((F) feature);
            }
            // write end featureList
            getStreamWriter().writeEndElement();
        }
    }

    /**
     * <p>writeNames.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeNames(P object) throws XMLStreamException {
        boolean hasAliases = !object.getAliases().isEmpty();
        if (hasAliases){
            this.streamWriter.writeStartElement("names");
            // write aliases
            for (Object alias : object.getAliases()){
                getAliasWriter().write((Alias)alias);
            }
            // write end names
            this.streamWriter.writeEndElement();
        }
    }

    /**
     * <p>writeBiologicalRole.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeBiologicalRole(P object) throws XMLStreamException {
        getBiologicalRoleWriter().write(object.getBiologicalRole(),"biologicalRole");
    }

    /**
     * <p>writeInteractor.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeInteractor(P object) throws XMLStreamException {
        Interactor interactor = object.getInteractor();
        // write interaction ref
        if (!writeComplexAsInteractor && interactor instanceof Complex){
            Complex complex = (Complex)interactor;
            // write complex as an interactor if no participants as XML 25 interactions must have at least one participant
            if (complex.getParticipants().isEmpty()){
                writeMolecule(interactor);
            }
            else{
                this.streamWriter.writeStartElement("interactionRef");
                int id = this.objectIndex.extractIdForComplex(complex);
                this.streamWriter.writeCharacters(Integer.toString(id));
                this.streamWriter.writeEndElement();

                // register this complex in case it has not been written yet
                this.objectIndex.registerSubComplex((Complex)interactor);
            }
        }
        // write participant candidates
        else if (object instanceof ParticipantPool){
             ParticipantPool pool = (ParticipantPool)object;
            if (!pool.isEmpty()){
                writeParticipantPool(pool);
            }
            else{
                writeMolecule(interactor);
            }
        }
        // write interactor ref or interactor
        else{
            writeMolecule(interactor);
        }
    }

    /**
     * <p>writeParticipantPool.</p>
     *
     * @param pool a {@link psidev.psi.mi.jami.model.ParticipantPool} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeParticipantPool(ParticipantPool pool) throws XMLStreamException;
    /**
     * <p>writeMolecule.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeMolecule(Interactor interactor) throws XMLStreamException ;
    /**
     * <p>writeExperimentalPreparations.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeExperimentalPreparations(P object) throws XMLStreamException;
    /**
     * <p>writeExperimentalRoles.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeExperimentalRoles(P object) throws XMLStreamException;
    /**
     * <p>writeParticipantIdentificationMethods.</p>
     *
     * @param object a P object.
     * @param method a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeParticipantIdentificationMethods(P object, CvTerm method) throws XMLStreamException;
    /**
     * <p>writeExperimentalInteractor.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeExperimentalInteractor(P object) throws XMLStreamException;
    /**
     * <p>writeHostOrganisms.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeHostOrganisms(P object) throws XMLStreamException;
    /**
     * <p>writeConfidences.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeConfidences(P object) throws XMLStreamException;
    /**
     * <p>writeParameters.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeParameters(P object) throws XMLStreamException;

    /**
     * <p>writeXref.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeXref(P object) throws XMLStreamException {
        if (!object.getXrefs().isEmpty()){
            writeXrefFromParticipantXrefs(object);
        }
    }

    /**
     * <p>writeXrefFromParticipantXrefs.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeXrefFromParticipantXrefs(P object) throws XMLStreamException {
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
                getXrefWriter().write(ref,"primaryRef");
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
     * <p>writeMoleculeRef.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeMoleculeRef(Interactor interactor) throws XMLStreamException {
        this.streamWriter.writeStartElement("interactorRef");
        this.streamWriter.writeCharacters(Integer.toString(this.objectIndex.extractIdForInteractor(interactor)));
        this.streamWriter.writeEndElement();
    }

    /**
     * <p>writeMoleculeDescription.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeMoleculeDescription(Interactor interactor) throws XMLStreamException {
        getInteractorWriter().write(interactor);
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

    protected PsiXmlVersion getVersion() {
        return version;
    }
}

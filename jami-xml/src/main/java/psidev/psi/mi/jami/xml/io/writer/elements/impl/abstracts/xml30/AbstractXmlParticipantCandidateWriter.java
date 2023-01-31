package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.ParticipantCandidate;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Abstract Xml writer for participant candidate
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public abstract class AbstractXmlParticipantCandidateWriter<P extends ParticipantCandidate, F extends Feature>
        implements PsiXmlElementWriter<P> {
    private XMLStreamWriter streamWriter;
    private PsiXmlObjectCache objectIndex;
    private PsiXmlElementWriter<F> featureWriter;
    private PsiXmlElementWriter<Interactor> interactorWriter;

    /**
     * <p>Constructor for AbstractXmlParticipantCandidateWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlParticipantCandidateWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        if (writer == null){
            throw new IllegalArgumentException("The XML stream writer is mandatory for the AbstractXmlParticipantCandidateWriter");
        }
        this.streamWriter = writer;
        if (objectIndex == null){
            throw new IllegalArgumentException("The PsiXml 3.0 object index is mandatory for the AbstractXmlParticipantCandidateWriter. It is necessary for generating an id to an experimentDescription");
        }
        this.objectIndex = objectIndex;
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
        try {
            // write start
            this.streamWriter.writeStartElement("interactorCandidate");
            int id = this.objectIndex.extractIdForParticipant(object);
            // write id attribute
            this.streamWriter.writeAttribute("id", Integer.toString(id));
            // write interactor
            writeInteractor(object);
            // write features
            writeFeatures(object);
            // write end participant
            this.streamWriter.writeEndElement();

        } catch (XMLStreamException e) {
            throw new MIIOException("Impossible to write the participant candidate : "+object.toString(), e);
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
     * <p>writeInteractor.</p>
     *
     * @param object a P object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeInteractor(P object) throws XMLStreamException {
        Interactor interactor = object.getInteractor();
        // write interactor ref or interactor
        writeMolecule(interactor);
    }

    /**
     * <p>writeMolecule.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeMolecule(Interactor interactor) throws XMLStreamException ;

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
}

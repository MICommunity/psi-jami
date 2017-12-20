package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlCvTermWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlDbXrefWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlInteractorWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlModelledFeatureWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlStoichiometryWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Abstract class for XML 3.0 writer of modelled participant
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public abstract class AbstractXmlModelledParticipantWriter
        extends psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlModelledParticipantWriter {

    private PsiXmlElementWriter<Stoichiometry> stoichiometryWriter;
    private PsiXmlElementWriter<ModelledParticipantCandidate> participantCandidateWriter;

    /**
     * <p>Constructor for AbstractXmlModelledParticipantWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlModelledParticipantWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /**
     * <p>Getter for the field <code>stoichiometryWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Stoichiometry> getStoichiometryWriter() {
        if (this.stoichiometryWriter == null){
            this.stoichiometryWriter = new XmlStoichiometryWriter(getStreamWriter());
        }
        return stoichiometryWriter;
    }

    /**
     * <p>Setter for the field <code>stoichiometryWriter</code>.</p>
     *
     * @param stoichiometryWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setStoichiometryWriter(PsiXmlElementWriter<Stoichiometry> stoichiometryWriter) {
        this.stoichiometryWriter = stoichiometryWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefWriter() {
        super.setXrefWriter(new XmlDbXrefWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFeatureWriter() {
        super.setFeatureWriter(new XmlModelledFeatureWriter(getStreamWriter(), getObjectIndex()));
    }

    /**
     * <p>writeStoichiometry.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    protected void writeStoichiometry(ModelledParticipant object){
        if (object.getStoichiometry() != null){
            getStoichiometryWriter().write(object.getStoichiometry());
        }
    }

    /** {@inheritDoc} */
    protected void writeOtherAttributes(ModelledParticipant object, boolean writeAttributeList) throws XMLStreamException {
        // nothing to do here
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseBiologicalRoleWriter() {
        super.setBiologicalRoleWriter(new XmlCvTermWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInteractorWriter() {
        super.setInteractorWriter(new XmlInteractorWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalPreparations(ModelledParticipant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalRoles(ModelledParticipant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParticipantIdentificationMethods(ModelledParticipant object, CvTerm method) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalInteractor(ModelledParticipant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeHostOrganisms(ModelledParticipant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeConfidences(ModelledParticipant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParameters(ModelledParticipant object) {
        // nothing to do
    }

    /**
     * <p>Getter for the field <code>participantCandidateWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<ModelledParticipantCandidate> getParticipantCandidateWriter() {
        if (this.participantCandidateWriter == null){
            initialiseParticipantCandidateWriter();
        }
        return participantCandidateWriter;
    }

    /**
     * <p>initialiseParticipantCandidateWriter.</p>
     */
    protected abstract void initialiseParticipantCandidateWriter();

    /**
     * <p>Setter for the field <code>participantCandidateWriter</code>.</p>
     *
     * @param participantCandidateWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setParticipantCandidateWriter(PsiXmlElementWriter<ModelledParticipantCandidate> participantCandidateWriter) {
        this.participantCandidateWriter = participantCandidateWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParticipantPool(ParticipantPool pool) throws XMLStreamException {
        getStreamWriter().writeStartElement("interactorCandidateList");
        // write participant candidate type
        getBiologicalRoleWriter().write(pool.getType(), "moleculeSetType");
        // write candidates
        for (Object candidate : pool){
            getParticipantCandidateWriter().write((ModelledParticipantCandidate)candidate);
        }
        // end list
        getStreamWriter().writeEndElement();
    }
}

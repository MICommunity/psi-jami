package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.*;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlParameterWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlFeatureEvidenceWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlStoichiometryWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Abstract class for XML 3.0 writer of participant evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public abstract class AbstractXmlParticipantEvidenceWriter
        extends psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlParticipantEvidenceWriter {

    private PsiXmlElementWriter<Stoichiometry> stoichiometryWriter;
    private PsiXmlElementWriter<ExperimentalParticipantCandidate> participantCandidateWriter;

    /**
     * <p>Constructor for AbstractXmlParticipantEvidenceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlParticipantEvidenceWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(PsiXmlVersion.v3_0_0, writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefWriter() {
        super.setXrefWriter(new XmlDbXrefWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFeatureWriter() {
        super.setFeatureWriter(new XmlFeatureEvidenceWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseConfidenceWriter() {
        super.setConfidenceWriter(new XmlConfidenceWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseHostOrganismWriter() {
         super.setHostOrganismWriter(new XmlHostOrganismWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParameterWriter() {
        super.setParameterWriter(new XmlParameterWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseCvWriter() {
        super.setExperimentalCvWriter(new XmlCvTermWriter(getStreamWriter()));
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

    /**
     * <p>writeStoichiometry.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    protected void writeStoichiometry(ParticipantEvidence object){
        if (object.getStoichiometry() != null){
            getStoichiometryWriter().write(object.getStoichiometry());
        }
    }

    /** {@inheritDoc} */
    protected void writeOtherAttributes(ParticipantEvidence object, boolean writeAttributeList) throws XMLStreamException {
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
        super.setInteractorWriter(new XmlInteractorWriter(PsiXmlVersion.v3_0_0, getStreamWriter(), getObjectIndex()));
    }

    /**
     * <p>Getter for the field <code>participantCandidateWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<ExperimentalParticipantCandidate> getParticipantCandidateWriter() {
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
    public void setParticipantCandidateWriter(PsiXmlElementWriter<ExperimentalParticipantCandidate> participantCandidateWriter) {
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
             getParticipantCandidateWriter().write((ExperimentalParticipantCandidate)candidate);
        }
        // end list
        getStreamWriter().writeEndElement();
    }
}

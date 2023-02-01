package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Abstract class for writer of participant evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public abstract class AbstractXmlParticipantEvidenceWriter
        extends AbstractXmlParticipantWriter<ParticipantEvidence, FeatureEvidence> {
    private PsiXmlVariableNameWriter<CvTerm> cvWriter;
    private PsiXmlElementWriter<Confidence> confidenceWriter;
    private PsiXmlElementWriter<Organism> hostOrganismWriter;
    private PsiXmlElementWriter<Parameter> parameterWriter;

    /**
     * <p>Constructor for AbstractXmlParticipantEvidenceWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlParticipantEvidenceWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(version, writer, objectIndex);
    }

    /**
     * <p>getExperimentalCvWriter.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public PsiXmlVariableNameWriter<CvTerm> getExperimentalCvWriter() {
        if (this.cvWriter == null){
            initialiseCvWriter();
        }
        return cvWriter;
    }

    /**
     * <p>initialiseCvWriter.</p>
     */
    protected abstract void initialiseCvWriter();

    /**
     * <p>setExperimentalCvWriter.</p>
     *
     * @param cvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public void setExperimentalCvWriter(PsiXmlVariableNameWriter<CvTerm> cvWriter) {
        this.cvWriter = cvWriter;
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

    /**
     * <p>Setter for the field <code>hostOrganismWriter</code>.</p>
     *
     * @param hostOrganismWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setHostOrganismWriter(PsiXmlElementWriter<Organism> hostOrganismWriter) {

        this.hostOrganismWriter = hostOrganismWriter;
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
     * <p>Getter for the field <code>parameterWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Parameter> getParameterWriter() {
        if (this.parameterWriter == null){
            initialiseParameterWriter();
        }
        return parameterWriter;
    }

    /**
     * <p>initialiseParameterWriter.</p>
     */
    protected abstract void initialiseParameterWriter();

    /**
     * <p>Setter for the field <code>parameterWriter</code>.</p>
     *
     * @param parameterWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setParameterWriter(PsiXmlElementWriter<Parameter> parameterWriter) {
        this.parameterWriter = parameterWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalPreparations(ParticipantEvidence object) throws XMLStreamException {
        if (!object.getExperimentalPreparations().isEmpty()){
            getStreamWriter().writeStartElement("experimentalPreparationList");
            for (CvTerm prep : object.getExperimentalPreparations()){
                getExperimentalCvWriter().write(prep, "experimentalPreparation");
            }
            getStreamWriter().writeEndElement();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalRoles(ParticipantEvidence object) throws XMLStreamException {
        getStreamWriter().writeStartElement("experimentalRoleList");
        getExperimentalCvWriter().write(object.getExperimentalRole(), "experimentalRole");
        getStreamWriter().writeEndElement();
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParticipantIdentificationMethods(ParticipantEvidence object, CvTerm experimentMethod) throws XMLStreamException {
        if (!object.getIdentificationMethods().isEmpty()){
            // in case participant identification methods is the same as experiment method, no need to write it again
            if (object.getIdentificationMethods().size() == 1
                    && experimentMethod != null
                    && experimentMethod.equals(object.getIdentificationMethods().iterator().next())){
                 // nothing to write as already written in experiment
            }
            else {
                getStreamWriter().writeStartElement("participantIdentificationMethodList");

                for (CvTerm method : object.getIdentificationMethods()){
                    getExperimentalCvWriter().write(method, "participantIdentificationMethod");
                }
                getStreamWriter().writeEndElement();
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalInteractor(ParticipantEvidence object) throws XMLStreamException {
        // nothing to do here
    }

    /** {@inheritDoc} */
    @Override
    protected void writeHostOrganisms(ParticipantEvidence object) throws XMLStreamException {
        if (object.getExpressedInOrganism() != null){
            getStreamWriter().writeStartElement("hostOrganismList");
            getHostOrganismWriter().write(object.getExpressedInOrganism());
            getStreamWriter().writeEndElement();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeConfidences(ParticipantEvidence object) throws XMLStreamException {
        if (!object.getConfidences().isEmpty()){
            getStreamWriter().writeStartElement("confidenceList");
            for (Confidence conf : object.getConfidences()){
                getConfidenceWriter().write(conf);
            }
            getStreamWriter().writeEndElement();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParameters(ParticipantEvidence object) throws XMLStreamException {
        if (!object.getParameters().isEmpty()){
            getStreamWriter().writeStartElement("parameterList");
            for (Parameter param : object.getParameters()){
                getParameterWriter().write(param);
            }
            getStreamWriter().writeEndElement();
        }
    }
}

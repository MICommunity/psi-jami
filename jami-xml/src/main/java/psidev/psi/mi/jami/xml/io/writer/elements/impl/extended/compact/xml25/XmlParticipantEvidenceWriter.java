package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.*;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.XmlExperimentalInteractorWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlFeatureEvidenceWriter;
import psidev.psi.mi.jami.xml.model.extension.AbstractExperimentalInteractor;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipantEvidence;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Compact XML 2.5 writer for an expanded participant evidence with full experimental details and having experimental interactors, list of host organisms and list of experimental roles.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public class XmlParticipantEvidenceWriter extends psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.XmlNamedParticipantEvidenceWriter{
    private CompactPsiXmlElementWriter<AbstractExperimentalInteractor> experimentalInteractorWriter;

    /**
     * <p>Constructor for XmlParticipantEvidenceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlParticipantEvidenceWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(version, writer, objectIndex);

    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefWriter() {
        super.setXrefWriter(new XmlDbXrefWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseBiologicalRoleWriter() {
        super.setBiologicalRoleWriter(new XmlCvTermWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInteractorWriter() {
        super.setInteractorWriter(new XmlInteractorWriter(getVersion(), getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseConfidenceWriter() {
        switch (getVersion()) {
            case v2_5_3:
                super.setConfidenceWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlConfidenceWriter(
                        getStreamWriter(), getObjectIndex()));
                break;
            case v2_5_4:
            default:
                super.setConfidenceWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlConfidenceWriter(
                        getStreamWriter(), getObjectIndex()));
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseHostOrganismWriter() {
        switch (getVersion()) {
            case v2_5_3:
                super.setHostOrganismWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlHostOrganismWriter(
                        getStreamWriter(), getObjectIndex()));
                break;
            case v2_5_4:
            default:
                super.setHostOrganismWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlHostOrganismWriter(
                        getStreamWriter(), getObjectIndex()));
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParameterWriter() {
        switch (getVersion()) {
            case v2_5_3:
                super.setParameterWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlParameterWriter(
                        getStreamWriter(), getObjectIndex()));
                break;
            case v2_5_4:
            default:
                super.setParameterWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlParameterWriter(
                        getStreamWriter(), getObjectIndex()));
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseCvWriter() {
        switch (getVersion()) {
            case v2_5_3:
                super.setExperimentalCvWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlExperimentalCvTermWriter(
                        getStreamWriter(), getObjectIndex()));
                break;
            case v2_5_4:
            default:
                super.setExperimentalCvWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlExperimentalCvTermWriter(
                        getStreamWriter(), getObjectIndex()));
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFeatureWriter() {
        super.setFeatureWriter(new XmlFeatureEvidenceWriter(getStreamWriter(), getObjectIndex()));
    }

    /**
     * <p>Getter for the field <code>experimentalInteractorWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXmlElementWriter} object.
     */
    public CompactPsiXmlElementWriter<AbstractExperimentalInteractor> getExperimentalInteractorWriter() {
        if (this.experimentalInteractorWriter == null){
            this.experimentalInteractorWriter = new XmlExperimentalInteractorWriter(getVersion(), getStreamWriter(), getObjectIndex());
        }
        return experimentalInteractorWriter;
    }

    /**
     * <p>Setter for the field <code>experimentalInteractorWriter</code>.</p>
     *
     * @param experimentalInteractorWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXmlElementWriter} object.
     */
    public void setExperimentalInteractorWriter(CompactPsiXmlElementWriter<AbstractExperimentalInteractor> experimentalInteractorWriter) {
        this.experimentalInteractorWriter = experimentalInteractorWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalRoles(ParticipantEvidence object) throws XMLStreamException {
        if (object instanceof ExtendedPsiXmlParticipantEvidence){
            ExtendedPsiXmlParticipantEvidence xmlParticipant = (ExtendedPsiXmlParticipantEvidence)object;
            getStreamWriter().writeStartElement("experimentalRoleList");
            for (CvTerm expRole : xmlParticipant.getExperimentalRoles()){
                getExperimentalCvWriter().write(expRole, "experimentalRole");
            }
            getStreamWriter().writeEndElement();
        }
        else{
            super.writeExperimentalRoles(object);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeHostOrganisms(ParticipantEvidence object) throws XMLStreamException {
        if (object instanceof ExtendedPsiXmlParticipantEvidence){
            ExtendedPsiXmlParticipantEvidence xmlParticipant = (ExtendedPsiXmlParticipantEvidence)object;
            if (!xmlParticipant.getHostOrganisms().isEmpty()){
                getStreamWriter().writeStartElement("hostOrganismList");
                for (Organism host : xmlParticipant.getHostOrganisms()){
                    getHostOrganismWriter().write(host);
                }
                getStreamWriter().writeEndElement();
            }
        }
        else{
            super.writeHostOrganisms(object);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalInteractor(ParticipantEvidence object) throws XMLStreamException {
        if (object instanceof ExtendedPsiXmlParticipantEvidence){
            ExtendedPsiXmlParticipantEvidence xmlParticipant = (ExtendedPsiXmlParticipantEvidence)object;
            if (!xmlParticipant.getExperimentalInteractors().isEmpty()){
                getStreamWriter().writeStartElement("experimentalInteractorList");
                for (AbstractExperimentalInteractor expInt : xmlParticipant.getExperimentalInteractors()){
                    getExperimentalInteractorWriter().write(expInt);
                }
                getStreamWriter().writeEndElement();
            }
        }
    }
}

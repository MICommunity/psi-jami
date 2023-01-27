package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.ExpandedPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.*;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.XmlExperimentalInteractorWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlFeatureEvidenceWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlConfidenceWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlExperimentalCvTermWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlHostOrganismWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlParameterWriter;
import psidev.psi.mi.jami.xml.model.extension.AbstractExperimentalInteractor;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipantEvidence;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.List;

/**
 * Expanded XML 2.5 writer for an expanded participant evidence with full experimental details and having experimental interactors, list of host organisms and list of experimental roles.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public class XmlParticipantEvidenceWriter extends psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.XmlNamedParticipantEvidenceWriter {
    private ExpandedPsiXmlElementWriter<AbstractExperimentalInteractor> experimentalInteractorWriter;

    /**
     * <p>Constructor for XmlParticipantEvidenceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlParticipantEvidenceWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
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
        super.setInteractorWriter(new XmlInteractorWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseConfidenceWriter() {
        super.setConfidenceWriter(new XmlConfidenceWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseHostOrganismWriter() {
        super.setHostOrganismWriter(new XmlHostOrganismWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParameterWriter() {
        super.setParameterWriter(new XmlParameterWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseCvWriter() {
        super.setExperimentalCvWriter(new XmlExperimentalCvTermWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFeatureWriter() {
        super.setFeatureWriter(new XmlFeatureEvidenceWriter(getStreamWriter(), getObjectIndex()));
    }

    /**
     * <p>Getter for the field <code>experimentalInteractorWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.ExpandedPsiXmlElementWriter} object.
     */
    public ExpandedPsiXmlElementWriter<AbstractExperimentalInteractor> getExperimentalInteractorWriter() {
        if (this.experimentalInteractorWriter == null){
            this.experimentalInteractorWriter = new XmlExperimentalInteractorWriter(getStreamWriter(), getObjectIndex());

        }
        return experimentalInteractorWriter;
    }

    /**
     * <p>Setter for the field <code>experimentalInteractorWriter</code>.</p>
     *
     * @param experimentalInteractorWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.ExpandedPsiXmlElementWriter} object.
     */
    public void setExperimentalInteractorWriter(ExpandedPsiXmlElementWriter<AbstractExperimentalInteractor> experimentalInteractorWriter) {
        this.experimentalInteractorWriter = experimentalInteractorWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalRoles(ParticipantEvidence object) throws XMLStreamException {
        if (object instanceof ExtendedPsiXmlParticipantEvidence){
            ExtendedPsiXmlParticipantEvidence xmlParticipant = (ExtendedPsiXmlParticipantEvidence)object;
            getStreamWriter().writeStartElement("experimentalRoleList");
            for (CvTerm expRole : xmlParticipant.getExperimentalRoles()){
                getExperimentalCvWriter().write(expRole,"experimentalRole");
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
                for (AbstractExperimentalInteractor expInt : xmlParticipant.getExperimentalInteractors()) {
                    getExperimentalInteractorWriter().write(expInt);
                }
                getStreamWriter().writeEndElement();
            }
        }
    }
}


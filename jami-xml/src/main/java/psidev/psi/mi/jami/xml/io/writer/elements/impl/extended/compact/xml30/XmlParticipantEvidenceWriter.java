package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30.AbstractXmlParticipantEvidenceWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.XmlExperimentalInteractorWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlFeatureEvidenceWriter;
import psidev.psi.mi.jami.xml.model.extension.AbstractExperimentalInteractor;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipantEvidence;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Compact XML 2.5 writer for an expanded participant evidence with full experimental details and having experimental interactors, list of host organisms and list of experimental roles.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public class XmlParticipantEvidenceWriter extends AbstractXmlParticipantEvidenceWriter implements CompactPsiXmlElementWriter<ParticipantEvidence> {
    private CompactPsiXmlElementWriter<AbstractExperimentalInteractor> experimentalInteractorWriter;

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
    protected void initialiseFeatureWriter() {
        super.setFeatureWriter(new XmlFeatureEvidenceWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantCandidateWriter() {
        super.setParticipantCandidateWriter(new XmlExperimentalParticipantCandidateWriter(getStreamWriter(), getObjectIndex()));
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
    protected void writeMolecule(Interactor interactor) throws XMLStreamException {
        super.writeMoleculeRef(interactor);
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

    /** {@inheritDoc} */
    @Override
    protected void writeNames(ParticipantEvidence object) throws XMLStreamException {
        if (object instanceof NamedParticipant){
            NamedParticipant xmlParticipant = (NamedParticipant) object;
            // write names
            PsiXmlUtils.writeCompleteNamesElement(xmlParticipant.getShortName(),
                    xmlParticipant.getFullName(), xmlParticipant.getAliases(), getStreamWriter(),
                    getAliasWriter());
        }
        else{
            super.writeNames(object);
        }
    }
}

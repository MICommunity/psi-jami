package psidev.psi.mi.jami.xml.model.extension.binary.xml30;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.binary.impl.ModelledBinaryInteractionWrapper;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.model.Entry;
import psidev.psi.mi.jami.xml.model.extension.xml300.ExtendedPsiXmlModelledInteraction;
import psidev.psi.mi.jami.xml.model.extension.xml300.BindingFeatures;
import psidev.psi.mi.jami.xml.model.extension.xml300.ExtendedPsiXmlCausalRelationship;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Wrapper for Xml binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
@XmlTransient
public class XmlModelledBinaryInteractionWrapper implements ModelledBinaryInteraction, FileSourceContext, ExtendedPsiXmlModelledInteraction {
    private psidev.psi.mi.jami.xml.model.extension.xml300.ExtendedPsiXmlModelledInteraction wrappedInteraction;
    private ModelledBinaryInteractionWrapper binaryWrapper;

    /**
     * <p>Constructor for XmlModelledBinaryInteractionWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.xml.model.extension.xml300.ExtendedPsiXmlModelledInteraction} object.
     */
    public XmlModelledBinaryInteractionWrapper(psidev.psi.mi.jami.xml.model.extension.xml300.ExtendedPsiXmlModelledInteraction interaction){
        this.wrappedInteraction = interaction;
        this.binaryWrapper = new ModelledBinaryInteractionWrapper(interaction);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteractionWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.xml.model.extension.xml300.ExtendedPsiXmlModelledInteraction} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledBinaryInteractionWrapper(psidev.psi.mi.jami.xml.model.extension.xml300.ExtendedPsiXmlModelledInteraction interaction, CvTerm complexExpansion){
        this(interaction);
        this.binaryWrapper = new ModelledBinaryInteractionWrapper(interaction, complexExpansion);
    }

    /**
     * <p>getParticipantA.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public ModelledParticipant getParticipantA() {
        return this.binaryWrapper.getParticipantA();
    }

    /**
     * <p>getParticipantB.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public ModelledParticipant getParticipantB() {
        return this.binaryWrapper.getParticipantB();
    }

    /**
     * <p>setParticipantA.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public void setParticipantA(ModelledParticipant participantA) {
        this.binaryWrapper.setParticipantA(participantA);
    }

    /**
     * <p>setParticipantB.</p>
     *
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public void setParticipantB(ModelledParticipant participantB) {
        this.binaryWrapper.setParticipantB(participantB);
    }

    /**
     * <p>getComplexExpansion.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getComplexExpansion() {
        return this.binaryWrapper.getComplexExpansion();
    }

    /** {@inheritDoc} */
    public void setComplexExpansion(CvTerm expansion) {
        this.binaryWrapper.setComplexExpansion(expansion);
    }

    /**
     * <p>getCausalRegulatoryMechanism.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getCausalRegulatoryMechanism() {
        return this.binaryWrapper.getCausalRegulatoryMechanism();
    }

    /** {@inheritDoc} */
    public void setCausalRegulatoryMechanism(CvTerm causalRegulatoryMechanism) {
        this.binaryWrapper.setCausalRegulatoryMechanism(causalRegulatoryMechanism);
    }

    public Confidence getMiScore(){
        return this.binaryWrapper.getMiScore();
    }

    public void setMiScore(Confidence miScore){
        this.binaryWrapper.setMiScore(miScore);
    }

    /**
     * {@inheritDoc}
     *
     * The collection of participants for this binary interaction.
     * It cannot be changed.
     */
    @Override
    public Collection<ModelledParticipant> getParticipants() {
        return this.binaryWrapper.getParticipants();
    }

    /**
     * {@inheritDoc}
     *
     * Adds a new Participant and set the Interaction of this participant if added.
     * If the participant B and A are null, it will first set the participantA. If the participantA is set, it will set the ParticipantB
     */
    @Override
    public boolean addParticipant(ModelledParticipant part) {
        return this.binaryWrapper.addParticipant(part);
    }

    /**
     * {@inheritDoc}
     *
     * Removes the Participant from this binary interaction
     */
    @Override
    public boolean removeParticipant(ModelledParticipant part) {
        return this.binaryWrapper.removeParticipant(part);
    }

    /**
     * {@inheritDoc}
     *
     * Adds the participants and set the Interaction of this participant if added.
     * If the participant B and A are null, it will first set the participantA. If the participantA is set, it will set the ParticipantB
     */
    @Override
    public boolean addAllParticipants(Collection<? extends ModelledParticipant> participants) {
        return this.binaryWrapper.addAllParticipants(participants);
    }

    /** {@inheritDoc} */
    @Override
    public boolean removeAllParticipants(Collection<? extends ModelledParticipant> participants) {
        return this.binaryWrapper.removeAllParticipants(participants);
    }

    /** {@inheritDoc} */
    @Override
    public String getFullName() {
        return this.wrappedInteraction.getFullName();
    }

    /** {@inheritDoc} */
    @Override
    public void setFullName(String name) {
        this.wrappedInteraction.setFullName(name);
    }

    /** {@inheritDoc} */
    @Override
    public Xref getPreferredIdentifier() {
        return this.wrappedInteraction.getPreferredIdentifier();
    }

    /** {@inheritDoc} */
    @Override
    public String getPreferredName() {
        return this.getShortName();
    }

    /** {@inheritDoc} */
    @Override
    public Organism getOrganism() {
        return this.wrappedInteraction.getOrganism();
    }

    /** {@inheritDoc} */
    @Override
    public void setOrganism(Organism organism) {
        this.wrappedInteraction.setOrganism(organism);
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getInteractorType() {
        return this.wrappedInteraction.getInteractorType();
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractorType(CvTerm type) {
         this.wrappedInteraction.setInteractorType(type);
    }

    /** {@inheritDoc} */
    @Override
    public List<BindingFeatures> getBindingFeatures() {
        return this.wrappedInteraction.getBindingFeatures();
    }

    /** {@inheritDoc} */
    @Override
    public List<ExtendedPsiXmlCausalRelationship> getCausalRelationships() {
        return this.wrappedInteraction.getCausalRelationships();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Alias> getAliases() {
        return this.wrappedInteraction.getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isIntraMolecular() {
        return this.wrappedInteraction.isIntraMolecular();
    }

    /** {@inheritDoc} */
    @Override
    public void setIntraMolecular(boolean intra) {
        this.wrappedInteraction.setIntraMolecular(intra);
    }

    /** {@inheritDoc} */
    @Override
    public Entry getEntry() {
        return this.wrappedInteraction.getEntry();
    }

    /** {@inheritDoc} */
    @Override
    public void setEntry(Entry entry) {
        this.wrappedInteraction.setEntry(entry);
    }

    /** {@inheritDoc} */
    @Override
    public int getId() {
        return this.wrappedInteraction.getId();
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        this.wrappedInteraction.setId(id);
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        return this.wrappedInteraction.getSourceLocator();
    }

    /** {@inheritDoc} */
    @Override
    public void setSourceLocator(FileSourceLocator locator) {
        this.wrappedInteraction.setSourceLocator(locator);
    }

    /** {@inheritDoc} */
    @Override
    public String getShortName() {
        return this.wrappedInteraction.getShortName();
    }

    /** {@inheritDoc} */
    @Override
    public void setShortName(String name) {
        this.wrappedInteraction.setShortName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getRigid() {
        return this.wrappedInteraction.getRigid();
    }

    /** {@inheritDoc} */
    @Override
    public void setRigid(String rigid) {
        this.wrappedInteraction.setRigid(rigid);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getIdentifiers() {
        return this.wrappedInteraction.getIdentifiers();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getXrefs() {
        return this.wrappedInteraction.getXrefs();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Checksum> getChecksums() {
        return this.wrappedInteraction.getChecksums();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Annotation> getAnnotations() {
        return this.wrappedInteraction.getAnnotations();
    }

    /** {@inheritDoc} */
    @Override
    public Date getUpdatedDate() {
        return this.wrappedInteraction.getUpdatedDate();
    }

    /** {@inheritDoc} */
    @Override
    public void setUpdatedDate(Date updated) {
        this.wrappedInteraction.setUpdatedDate(updated);
    }

    /** {@inheritDoc} */
    @Override
    public Date getCreatedDate() {
        return this.wrappedInteraction.getCreatedDate();
    }

    /** {@inheritDoc} */
    @Override
    public void setCreatedDate(Date created) {
        this.wrappedInteraction.setCreatedDate(created);
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getInteractionType() {
        return this.wrappedInteraction.getInteractionType();
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractionType(CvTerm term) {
        this.wrappedInteraction.setInteractionType(term);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.wrappedInteraction.toString();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<InteractionEvidence> getInteractionEvidences() {
        return this.wrappedInteraction.getInteractionEvidences();
    }

    /** {@inheritDoc} */
    @Override
    public Source getSource() {
        return this.wrappedInteraction.getSource();
    }

    /** {@inheritDoc} */
    @Override
    public void setSource(Source source) {
        this.wrappedInteraction.setSource(source);
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getEvidenceType() {
        return this.wrappedInteraction.getEvidenceType();
    }

    /** {@inheritDoc} */
    @Override
    public void setEvidenceType(CvTerm eco) {
        this.wrappedInteraction.setEvidenceType(eco);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<ModelledConfidence> getModelledConfidences() {
        return this.wrappedInteraction.getModelledConfidences();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<ModelledParameter> getModelledParameters() {
        return this.wrappedInteraction.getModelledParameters();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<CooperativeEffect> getCooperativeEffects() {
        return this.wrappedInteraction.getCooperativeEffects();
    }

    /** {@inheritDoc} */
    @Override
    public String getComplexAc() {
        return this.wrappedInteraction.getComplexAc();
    }

    /** {@inheritDoc} */
    @Override
    public String getComplexVersion() {
        return this.wrappedInteraction.getComplexVersion();
    }

    /** {@inheritDoc} */
    @Override
    public void assignComplexAc(String accession, String version) {
        this.wrappedInteraction.assignComplexAc(accession, version);
    }

    /** {@inheritDoc} */
    @Override
    public void assignComplexAc(String accession) {
        this.wrappedInteraction.assignComplexAc(accession);
    }

    /** {@inheritDoc} */
    @Override
    public String getPhysicalProperties() {
        return this.wrappedInteraction.getPhysicalProperties();
    }

    /** {@inheritDoc} */
    @Override
    public void setPhysicalProperties(String properties) {
        this.wrappedInteraction.setPhysicalProperties(properties);
    }

    /** {@inheritDoc} */
    @Override
    public String getRecommendedName() {
        return this.wrappedInteraction.getRecommendedName();
    }

    /** {@inheritDoc} */
    @Override
    public void setRecommendedName(String name) {
         this.wrappedInteraction.setRecommendedName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getSystematicName() {
        return this.wrappedInteraction.getSystematicName();
    }

    /** {@inheritDoc} */
    @Override
    public void setSystematicName(String name) {
         this.wrappedInteraction.setSystematicName(name);
    }
}

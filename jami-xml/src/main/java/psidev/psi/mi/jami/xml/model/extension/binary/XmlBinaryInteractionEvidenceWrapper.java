package psidev.psi.mi.jami.xml.model.extension.binary;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.impl.BinaryInteractionEvidenceWrapper;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.model.Entry;
import psidev.psi.mi.jami.xml.model.extension.AbstractAvailability;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.xml.model.extension.AbstractInferredInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlExperiment;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Xml implementation of BinaryInteractionWrapper with a source locator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
@XmlTransient
public class XmlBinaryInteractionEvidenceWrapper implements BinaryInteractionEvidence,FileSourceContext,ExtendedPsiXmlInteractionEvidence {
    private ExtendedPsiXmlInteractionEvidence wrappedInteraction;
    private BinaryInteractionEvidenceWrapper binaryWrapper;

    /**
     * <p>Constructor for XmlBinaryInteractionEvidenceWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence} object.
     */
    public XmlBinaryInteractionEvidenceWrapper(ExtendedPsiXmlInteractionEvidence interaction){
        this.wrappedInteraction = interaction;
        this.binaryWrapper = new BinaryInteractionEvidenceWrapper(interaction);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidenceWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteractionEvidenceWrapper(ExtendedPsiXmlInteractionEvidence interaction, CvTerm complexExpansion){
        this(interaction);
        this.binaryWrapper = new BinaryInteractionEvidenceWrapper(interaction, complexExpansion);
    }

    /**
     * <p>getParticipantA.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public ParticipantEvidence getParticipantA() {
        return this.binaryWrapper.getParticipantA();
    }

    /**
     * <p>getParticipantB.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public ParticipantEvidence getParticipantB() {
        return this.binaryWrapper.getParticipantB();
    }

    /**
     * <p>setParticipantA.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public void setParticipantA(ParticipantEvidence participantA) {
        this.binaryWrapper.setParticipantA(participantA);
    }

    /**
     * <p>setParticipantB.</p>
     *
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public void setParticipantB(ParticipantEvidence participantB) {
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
    public Collection<ParticipantEvidence> getParticipants() {
        return this.binaryWrapper.getParticipants();
    }

    /**
     * {@inheritDoc}
     *
     * Adds a new Participant and set the Interaction of this participant if added.
     * If the participant B and A are null, it will first set the participantA. If the participantA is set, it will set the ParticipantB
     */
    @Override
    public boolean addParticipant(ParticipantEvidence part) {
        return this.binaryWrapper.addParticipant(part);
    }

    /**
     * {@inheritDoc}
     *
     * Removes the Participant from this binary interaction
     */
    @Override
    public boolean removeParticipant(ParticipantEvidence part) {
        return this.binaryWrapper.removeParticipant(part);
    }

    /**
     * {@inheritDoc}
     *
     * Adds the participants and set the Interaction of this participant if added.
     * If the participant B and A are null, it will first set the participantA. If the participantA is set, it will set the ParticipantB
     */
    @Override
    public boolean addAllParticipants(Collection<? extends ParticipantEvidence> participants) {
        return this.binaryWrapper.addAllParticipants(participants);
    }

    /** {@inheritDoc} */
    @Override
    public boolean removeAllParticipants(Collection<? extends ParticipantEvidence> participants) {
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
    public Collection<Alias> getAliases() {
        return this.wrappedInteraction.getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public List<CvTerm> getInteractionTypes() {
        return this.wrappedInteraction.getInteractionTypes();
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
    public List<AbstractInferredInteraction> getInferredInteractions() {
        return this.wrappedInteraction.getInferredInteractions();
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
    public AbstractAvailability getXmlAvailability() {
        return this.wrappedInteraction.getXmlAvailability();
    }

    /** {@inheritDoc} */
    @Override
    public void setXmlAvailability(AbstractAvailability availability) {
        this.wrappedInteraction.setXmlAvailability(availability);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isModelled() {
        return this.wrappedInteraction.isModelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setModelled(boolean modelled) {
        this.wrappedInteraction.setModelled(modelled);
    }

    /** {@inheritDoc} */
    @Override
    public List<Experiment> getExperiments() {
        return this.wrappedInteraction.getExperiments();
    }

    /** {@inheritDoc} */
    @Override
    public List<ExtendedPsiXmlExperiment> getOriginalExperiments() {
        return this.wrappedInteraction.getOriginalExperiments();
    }

    /** {@inheritDoc} */
    @Override
    public String getImexId() {
        return this.wrappedInteraction.getImexId();
    }

    /** {@inheritDoc} */
    @Override
    public void assignImexId(String identifier) {
        this.wrappedInteraction.assignImexId(identifier);
    }

    /** {@inheritDoc} */
    @Override
    public Experiment getExperiment() {
        return this.wrappedInteraction.getExperiment();
    }

    /** {@inheritDoc} */
    @Override
    public void setExperiment(Experiment experiment) {
        this.wrappedInteraction.setExperiment(experiment);
    }

    /** {@inheritDoc} */
    @Override
    public void setExperimentAndAddInteractionEvidence(Experiment experiment) {
        this.wrappedInteraction.setExperimentAndAddInteractionEvidence(experiment);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<VariableParameterValueSet> getVariableParameterValues() {
        return this.wrappedInteraction.getVariableParameterValues();
    }

    /** {@inheritDoc} */
    @Override
    public String getAvailability() {
        return this.wrappedInteraction.getAvailability();
    }

    /** {@inheritDoc} */
    @Override
    public void setAvailability(String availability) {
        this.wrappedInteraction.setAvailability(availability);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Parameter> getParameters() {
        return this.wrappedInteraction.getParameters();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isInferred() {
        return this.wrappedInteraction.isInferred();
    }

    /** {@inheritDoc} */
    @Override
    public void setInferred(boolean inferred) {
        this.wrappedInteraction.setInferred(isInferred());
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Confidence> getConfidences() {
        return this.wrappedInteraction.getConfidences();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isNegative() {
        return this.wrappedInteraction.isNegative();
    }

    /** {@inheritDoc} */
    @Override
    public void setNegative(boolean negative) {
        this.wrappedInteraction.setNegative(negative);
    }

    /**
     * <p>Getter for the field <code>wrappedInteraction</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence} object.
     */
    protected ExtendedPsiXmlInteractionEvidence getWrappedInteraction() {
        return wrappedInteraction;
    }
}

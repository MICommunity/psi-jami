package psidev.psi.mi.jami.xml.model.extension.binary.xml25;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.impl.BinaryInteractionWrapper;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.model.Entry;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction;
import psidev.psi.mi.jami.xml.model.extension.InferredInteraction;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Wrapper for ModelledBinaryInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
@XmlTransient
public class XmlBinaryInteractionWrapper implements BinaryInteraction<Participant>, FileSourceContext, ExtendedPsiXmlInteraction<Participant> {
    private ExtendedPsiXmlInteraction wrappedInteraction;
    private BinaryInteractionWrapper binaryWrapper;

    /**
     * <p>Constructor for XmlBinaryInteractionWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction} object.
     */
    public XmlBinaryInteractionWrapper(ExtendedPsiXmlInteraction interaction){
        this.wrappedInteraction = interaction;
        this.binaryWrapper = new BinaryInteractionWrapper(interaction);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteractionWrapper(ExtendedPsiXmlInteraction interaction, CvTerm complexExpansion){
        this(interaction);
        this.binaryWrapper = new BinaryInteractionWrapper(interaction, complexExpansion);
    }

    /**
     * <p>getParticipantA.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public Participant getParticipantA() {
        return this.binaryWrapper.getParticipantA();
    }

    /**
     * <p>getParticipantB.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public Participant getParticipantB() {
        return this.binaryWrapper.getParticipantB();
    }

    /** {@inheritDoc} */
    public void setParticipantA(Participant participantA) {
        this.binaryWrapper.setParticipantA(participantA);
    }

    /** {@inheritDoc} */
    public void setParticipantB(Participant participantB) {
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
     * <p>The causal regulatory mechanism for the binary interaction.</p>
     */
    public CvTerm getCausalRegulatoryMechanism(){
        return this.binaryWrapper.getCausalRegulatoryMechanism();
    };

    /**
     * <p>Sets the causal regulatory mechanism of this binary interaction.</p>
     */
    public void setCausalRegulatoryMechanism(CvTerm causalRegulatoryMechanism){
        this.binaryWrapper.setCausalRegulatoryMechanism(causalRegulatoryMechanism);
    };

    /**
     * {@inheritDoc}
     *
     * The collection of participants for this binary interaction.
     * It cannot be changed.
     */
    @Override
    public Collection<Participant> getParticipants() {
        return this.binaryWrapper.getParticipants();
    }

    /**
     * {@inheritDoc}
     *
     * Adds a new Participant and set the Interaction of this participant if added.
     * If the participant B and A are null, it will first set the participantA. If the participantA is set, it will set the ParticipantB
     */
    @Override
    public boolean addParticipant(Participant part) {
        return this.binaryWrapper.addParticipant(part);
    }

    /**
     * {@inheritDoc}
     *
     * Removes the Participant from this binary interaction
     */
    @Override
    public boolean removeParticipant(Participant part) {
        return this.binaryWrapper.removeParticipant(part);
    }

    /**
     * {@inheritDoc}
     *
     * Adds the participants and set the Interaction of this participant if added.
     * If the participant B and A are null, it will first set the participantA. If the participantA is set, it will set the ParticipantB
     */
    @Override
    public boolean addAllParticipants(Collection<? extends Participant> participants) {
        return this.binaryWrapper.addAllParticipants(participants);
    }

    /** {@inheritDoc} */
    @Override
    public boolean removeAllParticipants(Collection<? extends Participant> participants) {
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
    public List<InferredInteraction> getInferredInteractions() {
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
}

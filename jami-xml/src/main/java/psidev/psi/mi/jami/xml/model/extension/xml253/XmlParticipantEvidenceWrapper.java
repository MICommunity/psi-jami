package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.listener.EntityInteractorChangeListener;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.XmlEntryContext;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeatureEvidence;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipant;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipantEvidence;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Wrapper for Xml participants
 *
 * Addeding new modelled feature to this participant will not add new feature evidences to the wrapped participant evidence as they are incompatibles.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
@XmlTransient
public class XmlParticipantEvidenceWrapper implements ModelledParticipant, ExtendedPsiXmlParticipant<ModelledInteraction, ModelledFeature>,
        FileSourceContext{

    private ExtendedPsiXmlParticipantEvidence participant;
    private Collection<ModelledFeature> modelledFeatures;
    private ModelledInteraction parent;

    /**
     * <p>Constructor for XmlParticipantEvidenceWrapper.</p>
     *
     * @param part a {@link ExtendedPsiXmlParticipantEvidence} object.
     * @param wrapper a {@link XmlInteractionEvidenceComplexWrapper} object.
     */
    public XmlParticipantEvidenceWrapper(ExtendedPsiXmlParticipantEvidence part, XmlInteractionEvidenceComplexWrapper wrapper){
        if (part == null){
            throw new IllegalArgumentException("A participant evidence wrapper needs a non null participant");
        }
        this.participant = part;
        setInteraction(wrapper);
        // register participant as complex participant
        XmlEntryContext.getInstance().registerComplexParticipant(participant.getId(), this);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Alias> getAliases() {
        return this.participant.getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getXrefs() {
        return this.participant.getXrefs();
    }

    /** {@inheritDoc} */
    @Override
    public Interactor getInteractor() {
        return this.participant.getInteractor();
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractor(Interactor interactor) {
        this.participant.setInteractor(interactor);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<CausalRelationship> getCausalRelationships() {
        return this.participant.getCausalRelationships();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Annotation> getAnnotations() {
        return this.participant.getAnnotations();
    }

    /** {@inheritDoc} */
    @Override
    public Stoichiometry getStoichiometry() {
        return this.participant.getStoichiometry();
    }

    /** {@inheritDoc} */
    @Override
    public void setStoichiometry(Integer stoichiometry) {
        this.participant.setStoichiometry(stoichiometry);
    }

    /** {@inheritDoc} */
    @Override
    public void setStoichiometry(Stoichiometry stoichiometry) {
        this.participant.setStoichiometry(stoichiometry);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<ModelledFeature> getFeatures() {
        if (this.modelledFeatures == null){
            initialiseFeatures();
        }
        return this.modelledFeatures;
    }

    /** {@inheritDoc} */
    @Override
    public void setChangeListener(EntityInteractorChangeListener listener) {
        this.participant.setChangeListener(listener);
    }

    /** {@inheritDoc} */
    @Override
    public boolean addFeature(ModelledFeature feature) {
        if (feature == null){
            return false;
        }
        if (this.modelledFeatures == null){
            initialiseFeatures();
        }
        if (this.modelledFeatures.add(feature)){
            feature.setParticipant(this);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean removeFeature(ModelledFeature feature) {
        if (feature == null){
            return false;
        }
        if (this.modelledFeatures == null){
            initialiseFeatures();
        }
        if (this.modelledFeatures.remove(feature)){
            feature.setParticipant(null);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean addAllFeatures(Collection<? extends ModelledFeature> features) {
        if (features == null){
            return false;
        }

        boolean added = false;
        for (ModelledFeature feature : features){
            if (addFeature(feature)){
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    @Override
    public boolean removeAllFeatures(Collection<? extends ModelledFeature> features) {
        if (features == null){
            return false;
        }

        boolean added = false;
        for (ModelledFeature feature : features){
            if (removeFeature(feature)){
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    @Override
    public EntityInteractorChangeListener getChangeListener() {
        return this.participant.getChangeListener();
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getBiologicalRole() {
        return this.participant.getBiologicalRole();
    }

    /** {@inheritDoc} */
    @Override
    public void setBiologicalRole(CvTerm bioRole) {
        this.participant.setBiologicalRole(bioRole);
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getBiologicalEffect() {
        return this.participant.getBiologicalEffect();
    }

    /** {@inheritDoc} */
    @Override
    public void setBiologicalEffect(CvTerm biologicalEffect) {
        this.participant.setBiologicalEffect(biologicalEffect);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.participant.toString();
    }


    /** {@inheritDoc} */
    @Override
    public void setInteractionAndAddParticipant(ModelledInteraction interaction) {
        if (this.parent != null){
            this.parent.removeParticipant(this);
        }

        if (interaction != null){
            interaction.addParticipant(this);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ModelledInteraction getInteraction() {
        if (this.parent == null && this.participant.getInteraction() instanceof ExtendedPsiXmlInteractionEvidence){
            this.parent = new XmlInteractionEvidenceComplexWrapper((ExtendedPsiXmlInteractionEvidence)this.participant.getInteraction());
        }
        return this.parent;
    }

    /** {@inheritDoc} */
    @Override
    public void setInteraction(ModelledInteraction interaction) {
        this.parent = interaction;
    }

    /**
     * <p>getWrappedParticipant.</p>
     *
     * @return a {@link ExtendedPsiXmlParticipantEvidence} object.
     */
    public ExtendedPsiXmlParticipantEvidence getWrappedParticipant(){
        return this.participant;
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        return ((FileSourceContext)participant).getSourceLocator();
    }

    /** {@inheritDoc} */
    @Override
    public void setSourceLocator(FileSourceLocator locator) {
        ((FileSourceContext)participant).setSourceLocator(locator);
    }

    /** {@inheritDoc} */
    @Override
    public int getId() {
        return participant.getId();
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        participant.setId(id);
        // register participant as complex participant
        XmlEntryContext.getInstance().registerComplexParticipant(participant.getId(), this);
    }

    /** {@inheritDoc} */
    @Override
    public String getShortName() {
        return participant.getShortName();
    }

    /** {@inheritDoc} */
    @Override
    public void setShortName(String name) {
        participant.setShortName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getFullName() {
        return participant.getShortName();
    }

    /** {@inheritDoc} */
    @Override
    public void setFullName(String name) {
        participant.setShortName(name);
    }

    /**
     * <p>initialiseFeatures.</p>
     */
    protected void initialiseFeatures(){
        this.modelledFeatures = new ArrayList<ModelledFeature>();
        for (FeatureEvidence feature : this.participant.getFeatures()){
            this.modelledFeatures.add(new XmlFeatureEvidenceWrapper((ExtendedPsiXmlFeatureEvidence)feature, this));
        }
    }
}

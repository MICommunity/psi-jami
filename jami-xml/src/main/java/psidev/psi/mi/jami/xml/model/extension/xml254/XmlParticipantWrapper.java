package psidev.psi.mi.jami.xml.model.extension.xml254;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.listener.EntityInteractorChangeListener;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.CausalRelationship;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.ModelledFeature;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.model.Stoichiometry;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlEntity;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeature;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipant;

import java.util.Collection;
import java.util.List;

/**
 * Wrapper for XmlParticipant
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/10/13</pre>
 */
public class XmlParticipantWrapper implements ModelledParticipant,
        FileSourceContext, ExtendedPsiXmlEntity<ModelledFeature>, psidev.psi.mi.jami.model.NamedParticipant<ModelledInteraction, ModelledFeature> {

    private ExtendedPsiXmlParticipant<Interaction,Feature> participant;
    private ModelledInteraction parent;
    private SynchronizedFeatureList modelledFeatures;

    /**
     * <p>Constructor for XmlParticipantWrapper.</p>
     *
     * @param part a {@link ExtendedPsiXmlParticipant} object.
     * @param wrapper a {@link ModelledInteraction} object.
     */
    public XmlParticipantWrapper(ExtendedPsiXmlParticipant part, ModelledInteraction wrapper){
        if (part == null){
            throw new IllegalArgumentException("A participant wrapper needs a non null participant");
        }
        this.participant = part;
        this.parent = wrapper;
        // register participant as complex participant
        XmlEntryContext.getInstance().registerComplexParticipant(participant.getId(), this);
    }

    /** {@inheritDoc} */
    @Override
    public List<Alias> getAliases() {
        return (List<Alias>)this.participant.getAliases();
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

    /**
     * <p>initialiseFeatures.</p>
     */
    protected void initialiseFeatures(){
        this.modelledFeatures = new SynchronizedFeatureList();
        for (Feature feature : this.participant.getFeatures()){
            this.modelledFeatures.addOnly(new XmlFeatureWrapper((ExtendedPsiXmlFeature)feature, this));
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.participant.toString();
    }

    /**
     * <p>getWrappedParticipant.</p>
     *
     * @return a {@link ExtendedPsiXmlParticipant} object.
     */
    public ExtendedPsiXmlParticipant<Interaction, Feature> getWrappedParticipant(){
        return this.participant;
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
        if (this.parent == null && this.participant.getInteraction() instanceof ExtendedPsiXmlInteraction){
            this.parent = new XmlBasicInteractionComplexWrapper((ExtendedPsiXmlInteraction)this.participant.getInteraction());
        }
        return this.parent;
    }

    /** {@inheritDoc} */
    @Override
    public void setInteraction(ModelledInteraction interaction) {
        this.parent = interaction;
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

    ////////////////////////////////////// classes
    private class SynchronizedFeatureList extends AbstractListHavingProperties<ModelledFeature> {

        private SynchronizedFeatureList() {
        }

        @Override
        protected void processAddedObjectEvent(ModelledFeature added) {
            participant.getFeatures().add(added);
        }

        @Override
        protected void processRemovedObjectEvent(ModelledFeature removed) {
            participant.getFeatures().remove(removed);
        }

        @Override
        protected void clearProperties() {
            participant.getFeatures().clear();
        }
    }
}

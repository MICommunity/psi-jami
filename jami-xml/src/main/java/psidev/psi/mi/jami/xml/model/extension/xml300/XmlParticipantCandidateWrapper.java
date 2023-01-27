package psidev.psi.mi.jami.xml.model.extension.xml300;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.listener.EntityInteractorChangeListener;
import psidev.psi.mi.jami.model.CausalRelationship;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.ModelledFeature;
import psidev.psi.mi.jami.model.ModelledParticipantCandidate;
import psidev.psi.mi.jami.model.ModelledParticipantPool;
import psidev.psi.mi.jami.model.ParticipantCandidate;
import psidev.psi.mi.jami.model.ParticipantPool;
import psidev.psi.mi.jami.model.Stoichiometry;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlEntity;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeature;

import java.util.Collection;

/**
 * Wrapper for XmlParticipantCandidate so it implements ModelledParticipantCanidate
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/10/13</pre>
 */
public class XmlParticipantCandidateWrapper implements ModelledParticipantCandidate, ExtendedPsiXmlEntity<ModelledFeature>, FileSourceContext {

    private ParticipantCandidate<ParticipantPool,Feature> participant;
    private SynchronizedFeatureList modelledFeatures;

    private ModelledParticipantPool parent;

    /**
     * <p>Constructor for XmlParticipantCandidateWrapper.</p>
     *
     * @param part a {@link ParticipantCandidate} object.
     * @param parent a {@link ModelledParticipantPool} object.
     */
    public XmlParticipantCandidateWrapper(ParticipantCandidate part, ModelledParticipantPool parent){
        if (part == null){
            throw new IllegalArgumentException("A participant candidate wrapper needs a non null participant candidate");
        }
        this.participant = part;
        this.parent = parent;
        // register participant as complex participant
        XmlEntryContext.getInstance().registerComplexParticipant(((ExtendedPsiXmlEntity)this.participant).getId(), this);
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
    public Stoichiometry getStoichiometry() {
        if (this.participant.getStoichiometry() == null
                && this.participant.getParentPool() != null){
            return this.participant.getParentPool().getStoichiometry();
        }
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

    /**
     * <p>initialiseFeatures.</p>
     */
    protected void initialiseFeatures(){
        this.modelledFeatures = new SynchronizedFeatureList();
        if (!this.participant.getFeatures().isEmpty()){
            for (Feature feature : this.participant.getFeatures()){
                this.modelledFeatures.addOnly(new XmlFeatureWrapper((ExtendedPsiXmlFeature)feature, this));
            }
        }
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
    public String toString() {
        return this.participant.toString();
    }

    /**
     * <p>getWrappedParticipant.</p>
     *
     * @return a {@link ParticipantCandidate} object.
     */
    public ParticipantCandidate<ParticipantPool, Feature> getWrappedParticipant(){
        return this.participant;
    }

    /** {@inheritDoc} */
    @Override
    public ModelledParticipantPool getParentPool() {
        if (this.parent == null && this.participant.getParentPool() instanceof ParticipantPool){
            this.parent = new XmlParticipantPoolWrapper(this.participant.getParentPool(), null);
        }
        return this.parent;
    }

    /** {@inheritDoc} */
    @Override
    public void setParentPool(ModelledParticipantPool pool) {
        this.parent = pool;
    }

    /** {@inheritDoc} */
    @Override
    public int getId() {
        return ((ExtendedPsiXmlEntity)participant).getId();
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        ((ExtendedPsiXmlEntity)participant).setId(id);
        // register participant as complex participant
        XmlEntryContext.getInstance().registerComplexParticipant(((ExtendedPsiXmlEntity)this.participant).getId(), this);
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

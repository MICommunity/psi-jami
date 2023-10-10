package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.listener.EntityInteractorChangeListener;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.XmlEntryContext;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlEntity;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeatureEvidence;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Wrapper for Xml participants candidates
 *
 * Addeding new modelled feature to this participant will not add new feature evidences to the wrapped participant evidence as they are incompatibles.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
@XmlTransient
public class XmlExperimentalParticipantCandidateWrapper implements ModelledParticipantCandidate, ExtendedPsiXmlEntity<ModelledFeature>,
        FileSourceContext {

    private ExperimentalParticipantCandidate participant;
    private Collection<ModelledFeature> modelledFeatures;
    private ModelledParticipantPool parent;

    /**
     * <p>Constructor for XmlExperimentalParticipantCandidateWrapper.</p>
     *
     * @param part a {@link psidev.psi.mi.jami.model.ExperimentalParticipantCandidate} object.
     * @param wrapper a {@link psidev.psi.mi.jami.model.ModelledParticipantPool} object.
     */
    public XmlExperimentalParticipantCandidateWrapper(ExperimentalParticipantCandidate part, ModelledParticipantPool wrapper){
        if (part == null){
            throw new IllegalArgumentException("A experimental participant candidate wrapper needs a non null participant");
        }
        this.participant = part;
        setParentPool(wrapper);
        // register feature as complex participant
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

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.participant.toString();
    }

    /** {@inheritDoc} */
    @Override
    public ModelledParticipantPool getParentPool() {
        if (this.parent == null &&
                this.participant.getParentPool() != null &&
                this.participant.getParentPool() instanceof ExperimentalParticipantPool){
            this.parent = new XmlExperimentalParticipantPoolWrapper(this.participant.
                    getParentPool(), null);
        }
        return this.parent;
    }

    /** {@inheritDoc} */
    @Override
    public void setParentPool(ModelledParticipantPool pool) {
        this.parent = pool;
    }

    /**
     * <p>getWrappedParticipant.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.ExperimentalParticipantCandidate} object.
     */
    public ExperimentalParticipantCandidate getWrappedParticipant(){
        return this.participant;
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
        return ((ExtendedPsiXmlEntity)participant).getId();
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        ((ExtendedPsiXmlEntity)participant).setId(id);
        // register participant  as complex participant
        XmlEntryContext.getInstance().registerComplexParticipant(((ExtendedPsiXmlEntity)this.participant).getId(), this);
    }
}

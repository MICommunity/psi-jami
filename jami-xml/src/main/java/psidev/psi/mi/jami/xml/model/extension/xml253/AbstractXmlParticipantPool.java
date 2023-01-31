package psidev.psi.mi.jami.xml.model.extension.xml253;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.listener.EntityInteractorChangeListener;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.CausalRelationship;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.InteractorPool;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.ParticipantCandidate;
import psidev.psi.mi.jami.model.ParticipantPool;
import psidev.psi.mi.jami.model.Stoichiometry;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipant;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Abstract class for XML Participant pool
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/07/13</pre>
 */
@XmlTransient
public abstract class AbstractXmlParticipantPool<I extends Interaction, F extends Feature, P extends ParticipantCandidate>
        implements ExtendedPsiXmlParticipant<I,F>, ParticipantPool<I,F,P>, EntityInteractorChangeListener, FileSourceContext{
    private Collection<P> candidates;
    private CvTerm type;

    private AbstractXmlParticipant<I,F> delegate;
    private InteractorPool generatedInteractor;
    private PsiXmlLocator sourceLocator;

    private I interaction;

    /**
     * <p>Constructor for AbstractXmlParticipantPool.</p>
     */
    public AbstractXmlParticipantPool(){
        initialiseComponentCandidatesSet();
    }

    /**
     * <p>Constructor for AbstractXmlParticipantPool.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.xml.model.extension.xml253.AbstractXmlParticipant} object.
     */
    public AbstractXmlParticipantPool(AbstractXmlParticipant<I,F> delegate){
        this.delegate = delegate;
        initialiseComponentCandidatesSet();
        for (F feature : this.delegate.getFeatures()){
            feature.setParticipant(this);
        }
    }

    /**
     * <p>initialiseComponentCandidatesSet.</p>
     */
    protected void initialiseComponentCandidatesSet() {
        this.candidates = new ArrayList<P>();
    }

    /**
     * <p>initialiseComponentCandidatesSetWith.</p>
     *
     * @param candidates a {@link java.util.Collection} object.
     */
    protected void initialiseComponentCandidatesSetWith(Collection<P> candidates) {
        if (candidates == null){
            this.candidates = Collections.EMPTY_LIST;
        }
        else {
            this.candidates = candidates;
        }
    }

    /** {@inheritDoc} */
    @Override
    public InteractorPool getInteractor() {
        if (this.generatedInteractor == null){
            this.generatedInteractor = new XmlInteractorPool("generated interactor pool");
            ((XmlInteractorPool)this.generatedInteractor).setSourceLocator(getSourceLocator());
        }
        return this.generatedInteractor;
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractor(Interactor interactor) {
        throw new UnsupportedOperationException("Cannot set the interactor of an ParticipantPool as it is an interactorSet that is related to the interactors in the set of entities");
    }

    /**
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getType() {
        if (this.type == null){
            this.type = new XmlCvTerm(InteractorPool.MOLECULE_SET, new XmlXref(CvTermUtils.createPsiMiDatabase(),InteractorPool.MOLECULE_SET_MI, CvTermUtils.createIdentityQualifier()));
        }
        return type;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the component set type.
     * Sets the type to molecule set (MI:1304) if the given type is null
     */
    public void setType(CvTerm type) {
        if (type == null){
            this.type = CvTermUtils.createMoleculeSetType();
        }
        else {
            this.type = type;
        }
        getInteractor().setInteractorType(this.type);
    }

    /**
     * <p>size.</p>
     *
     * @return a int.
     */
    public int size() {
        return candidates.size();
    }

    /**
     * <p>isEmpty.</p>
     *
     * @return a boolean.
     */
    public boolean isEmpty() {
        return candidates.isEmpty();
    }

    /** {@inheritDoc} */
    public boolean contains(Object o) {
        return candidates.contains(o);
    }

    /**
     * <p>iterator.</p>
     *
     * @return a {@link java.util.Iterator} object.
     */
    public Iterator<P> iterator() {
        return candidates.iterator();
    }

    /**
     * <p>toArray.</p>
     *
     * @return an array of {@link java.lang.Object} objects.
     */
    public Object[] toArray() {
        return candidates.toArray();
    }

    /**
     * <p>toArray.</p>
     *
     * @param ts an array of T objects.
     * @param <T> a T object.
     * @return an array of T objects.
     */
    public <T> T[] toArray(T[] ts) {
        return candidates.toArray(ts);
    }

    /**
     * <p>add.</p>
     *
     * @param interactor a P object.
     * @return a boolean.
     */
    public boolean add(P interactor) {
        if (candidates.add(interactor)){
            interactor.setChangeListener(this);
            getInteractor().add(interactor.getInteractor());
            interactor.setParentPool(this);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean remove(Object o) {
        if (candidates.remove(o)){
            ParticipantCandidate entity = (ParticipantCandidate)o;
            entity.setChangeListener(null);
            getInteractor().remove(entity.getInteractor());
            entity.setParentPool(null);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean containsAll(Collection<?> objects) {
        return candidates.containsAll(objects);
    }

    /** {@inheritDoc} */
    public boolean addAll(Collection<? extends P> interactors) {
        boolean added = this.candidates.addAll(interactors);
        if (added){
            for (P entity : this){
                entity.setChangeListener(this);
                getInteractor().add(entity.getInteractor());
                entity.setParentPool(this);
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    public boolean retainAll(Collection<?> objects) {
        boolean retain = candidates.retainAll(objects);
        if (retain){
            Collection<Interactor> interactors = new ArrayList<Interactor>(objects.size());
            for (Object o : objects){
                interactors.add(((Participant)o).getInteractor());
            }
            getInteractor().retainAll(interactors);
        }
        return retain;
    }

    /** {@inheritDoc} */
    public boolean removeAll(Collection<?> objects) {
        boolean remove = candidates.removeAll(objects);
        if (remove){
            Collection<Interactor> interactors = new ArrayList<Interactor>(objects.size());
            for (Object o : objects){
                Participant entity = (Participant)o;
                entity.setChangeListener(null);
                interactors.add(entity.getInteractor());
                entity.setInteraction(null);
            }
            // check if an interactor is not in another entity that is kept.
            // remove any interactors that are kept with other entities
            for (P entity : this){
                interactors.remove(entity.getInteractor());
            }
            getInteractor().removeAll(interactors);
        }
        return remove;
    }

    /**
     * <p>clear.</p>
     */
    public void clear() {
        for (P entity : this){
            entity.setChangeListener(null);
            entity.setParentPool(null);
        }
        candidates.clear();
        getInteractor().clear();
    }

    /** {@inheritDoc} */
    public void onInteractorUpdate(Entity entity, Interactor oldInteractor) {
        // check that the listener still makes sensr
        if (contains(entity)){
            boolean needsToRemoveOldInteractor = true;
            // check if an interactor is not in another entity that is kept.
            // remove any interactors that are kept with other entities
            for (P e : this){
                // we want to check if an interactor is the same as old interactor in another entry
                if (e != entity){
                    if (oldInteractor.equals(e.getInteractor())){
                        needsToRemoveOldInteractor = false;
                    }
                }
            }
            if (!needsToRemoveOldInteractor){
                getInteractor().remove(oldInteractor);
            }
            getInteractor().add(entity.getInteractor());
        }
    }

    /**
     * Sets the value of the molecule set type property.
     *
     * @param type
     *     allowed object is
     *     {@link psidev.psi.mi.jami.xml.model.extension.xml253.XmlCvTerm}
     */
    public void setJAXBType(XmlCvTerm type) {
        setType(type);
    }

    /**
     * <p>getJAXBInteractorCandidates.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<P> getJAXBInteractorCandidates() {
        return candidates;
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else if (sourceLocator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)sourceLocator;
            this.sourceLocator.setObjectId(getId());
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), getId());
        }
    }

    /** {@inheritDoc} */
    @Override
    public int getId() {
        return getDelegate().getId();
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        getDelegate().setId(id);
        XmlEntryContext.getInstance().registerParticipant(id, this);
    }

    /** {@inheritDoc} */
    @Override
    public String getShortName() {
        return getDelegate().getShortName();
    }

    /** {@inheritDoc} */
    @Override
    public void setShortName(String name) {
        getDelegate().setShortName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getFullName() {
        return getDelegate().getFullName();
    }

    /** {@inheritDoc} */
    @Override
    public void setFullName(String name) {
        getDelegate().setFullName(name);
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractionAndAddParticipant(I interaction) {
        if (this.interaction != null){
            this.interaction.removeParticipant(this);
        }

        if (interaction != null){
            interaction.addParticipant(this);
        }
    }

    /** {@inheritDoc} */
    @Override
    public I getInteraction() {
        return this.interaction;
    }

    /** {@inheritDoc} */
    @Override
    public void setInteraction(I interaction) {
        this.interaction = interaction;
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getBiologicalRole() {
        return getDelegate().getBiologicalRole();
    }

    /** {@inheritDoc} */
    @Override
    public void setBiologicalRole(CvTerm bioRole) {
        getDelegate().setBiologicalRole(bioRole);
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getBiologicalEffect() {
        return getDelegate().getBiologicalEffect();
    }

    /** {@inheritDoc} */
    @Override
    public void setBiologicalEffect(CvTerm biologicalEffect) {
        getDelegate().setBiologicalEffect(biologicalEffect);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getXrefs() {
        return getDelegate().getXrefs();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Annotation> getAnnotations() {
        return getDelegate().getAnnotations();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Alias> getAliases() {
        return getDelegate().getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<CausalRelationship> getCausalRelationships() {
        return getDelegate().getCausalRelationships();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<F> getFeatures() {
        return getDelegate().getFeatures();
    }

    /** {@inheritDoc} */
    @Override
    public EntityInteractorChangeListener getChangeListener() {
        return getDelegate().getChangeListener();
    }

    /** {@inheritDoc} */
    @Override
    public void setChangeListener(EntityInteractorChangeListener listener) {
        getDelegate().setChangeListener(listener);
    }

    /**
     * <p>addFeature.</p>
     *
     * @param feature a F object.
     * @return a boolean.
     */
    public boolean addFeature(F feature) {

        if (feature == null){
            return false;
        }

        if (getFeatures().add(feature)){
            feature.setParticipant(this);
            return true;
        }
        return false;
    }

    /**
     * <p>removeFeature.</p>
     *
     * @param feature a F object.
     * @return a boolean.
     */
    public boolean removeFeature(F feature) {

        if (feature == null){
            return false;
        }

        if (getFeatures().remove(feature)){
            feature.setParticipant(null);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean addAllFeatures(Collection<? extends F> features) {
        if (features == null){
            return false;
        }

        boolean added = false;
        for (F feature : features){
            if (addFeature(feature)){
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    public boolean removeAllFeatures(Collection<? extends F> features) {
        if (features == null){
            return false;
        }

        boolean added = false;
        for (F feature : features){
            if (removeFeature(feature)){
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    @Override
    public Stoichiometry getStoichiometry() {
        return getDelegate().getStoichiometry();
    }

    /** {@inheritDoc} */
    @Override
    public void setStoichiometry(Integer stoichiometry) {
        getDelegate().setStoichiometry(stoichiometry);
    }

    /** {@inheritDoc} */
    @Override
    public void setStoichiometry(Stoichiometry stoichiometry) {
        getDelegate().setStoichiometry(stoichiometry);
    }

    /**
     * <p>Getter for the field <code>delegate</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.xml253.AbstractXmlParticipant} object.
     */
    protected AbstractXmlParticipant<I, F> getDelegate() {
        if (this.delegate == null){
           initialiseDefaultDelegate();
        }
        return delegate;
    }

    /**
     * <p>initialiseDefaultDelegate.</p>
     */
    protected abstract void initialiseDefaultDelegate();

    /**
     * <p>Setter for the field <code>delegate</code>.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.xml.model.extension.xml253.AbstractXmlParticipant} object.
     */
    public void setDelegate(AbstractXmlParticipant<I, F> delegate) {
        this.delegate = delegate;
        XmlEntryContext.getInstance().registerParticipant(this.delegate.getId(), this);
    }
}

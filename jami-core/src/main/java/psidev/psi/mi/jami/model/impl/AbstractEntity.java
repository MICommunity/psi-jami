package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.listener.EntityInteractorChangeListener;
import psidev.psi.mi.jami.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Abstract class for Entity
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/07/13</pre>
 */
public abstract class AbstractEntity<F extends Feature> implements Entity<F> {
    private Interactor interactor;
    private Stoichiometry stoichiometry;
    private Collection<CausalRelationship> causalRelationships;
    private Collection<F> features;
    private EntityInteractorChangeListener changeListener;

    /**
     * <p>Constructor for AbstractEntity.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param <F> a F object.
     */
    public AbstractEntity(Interactor interactor){
        if (interactor == null){
            throw new IllegalArgumentException("The interactor cannot be null.");
        }
        this.interactor = interactor;
    }

    /**
     * <p>Constructor for AbstractEntity.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public AbstractEntity(Interactor interactor, Stoichiometry stoichiometry){
        this(interactor);
        this.stoichiometry = stoichiometry;
    }

    /**
     * <p>initialiseFeatures</p>
     */
    protected void initialiseFeatures(){
        this.features = new ArrayList<F>();
    }

    /**
     * <p>initialiseCausalRelationships</p>
     */
    protected void initialiseCausalRelationships(){
        this.causalRelationships = new ArrayList<CausalRelationship>();
    }

    /**
     * <p>initialiseCausalRelationshipsWith</p>
     *
     * @param relationships a {@link java.util.Collection} object.
     */
    protected void initialiseCausalRelationshipsWith(Collection<CausalRelationship> relationships) {
        if (relationships == null){
            this.causalRelationships = Collections.EMPTY_LIST;
        }
        else {
            this.causalRelationships = relationships;
        }
    }

    /**
     * <p>initialiseFeaturesWith</p>
     *
     * @param features a {@link java.util.Collection} object.
     */
    protected void initialiseFeaturesWith(Collection<F> features) {
        if (features == null){
            this.features = Collections.EMPTY_LIST;
        }
        else {
            this.features = features;
        }
    }

    /**
     * <p>Getter for the field <code>interactor</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public Interactor getInteractor() {
        return this.interactor;
    }

    /** {@inheritDoc} */
    public void setInteractor(Interactor interactor) {
        if (interactor == null){
            throw new IllegalArgumentException("The interactor cannot be null.");
        }
        Interactor oldInteractor = this.interactor;
        this.interactor = interactor;
        if (this.changeListener != null){
            this.changeListener.onInteractorUpdate(this, oldInteractor);
        }
    }

    /**
     * <p>Getter for the field <code>causalRelationships</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CausalRelationship> getCausalRelationships() {
        if (this.causalRelationships == null){
            initialiseCausalRelationships();
        }
        return this.causalRelationships;
    }

    /**
     * <p>Getter for the field <code>stoichiometry</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public Stoichiometry getStoichiometry() {
        return this.stoichiometry;
    }

    /** {@inheritDoc} */
    public void setStoichiometry(Integer stoichiometry) {
        if (stoichiometry == null){
            this.stoichiometry = null;
        }
        else {
            this.stoichiometry = new DefaultStoichiometry(stoichiometry, stoichiometry);
        }
    }

    /**
     * <p>Setter for the field <code>stoichiometry</code>.</p>
     *
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public void setStoichiometry(Stoichiometry stoichiometry) {
        this.stoichiometry = stoichiometry;
    }

    /**
     * <p>Getter for the field <code>features</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<F> getFeatures() {
        if (features == null){
            initialiseFeatures();
        }
        return this.features;
    }

    /**
     * <p>Getter for the field <code>changeListener</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.listener.EntityInteractorChangeListener} object.
     */
    public EntityInteractorChangeListener getChangeListener() {
        return this.changeListener;
    }

    /** {@inheritDoc} */
    public void setChangeListener(EntityInteractorChangeListener listener) {
        this.changeListener = listener;
    }

    /**
     * <p>addFeature</p>
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
     * <p>removeFeature</p>
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
    public String toString() {
        return "Entity: "+getInteractor().toString() + (getStoichiometry() != null ? ", stoichiometry: " + getStoichiometry().toString() : "");
    }
}

package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for CooperativeEffect
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the CooperativeEffect object is a complex object.
 * To compare CooperativeEffect objects, you can use some comparators provided by default:
 * - DefaultCooperativeEffectComparator
 * - UnambiguousCooperativeEffectComparator
 * - DefaultExactCooperativeEffectComparator
 * - UnambiguousExactCooperativeEffectComparator
 * - CooperativeEffectComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/05/13</pre>
 */
public class DefaultCooperativeEffect implements CooperativeEffect {

    private Collection<CooperativityEvidence> cooperativityEvidences;
    private Collection<ModelledInteraction> affectedInteractions;
    private Collection<Annotation> annotations;
    private CvTerm outcome;
    private CvTerm response;

    /**
     * <p>Constructor for DefaultCooperativeEffect.</p>
     *
     * @param outcome a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultCooperativeEffect(CvTerm outcome){
        if (outcome == null){
            throw new IllegalArgumentException("The outcome of a CooperativeEffect cannot be null");
        }
        this.outcome = outcome;
    }

    /**
     * <p>Constructor for DefaultCooperativeEffect.</p>
     *
     * @param outcome a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param response a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultCooperativeEffect(CvTerm outcome, CvTerm response){
        this(outcome);
        this.response = response;
    }

    /**
     * <p>initialiseCooperativityEvidences</p>
     */
    protected void initialiseCooperativityEvidences(){
        this.cooperativityEvidences = new ArrayList<CooperativityEvidence>();
    }

    /**
     * <p>initialiseCooperativityEvidencesWith</p>
     *
     * @param evidences a {@link java.util.Collection} object.
     */
    protected void initialiseCooperativityEvidencesWith(Collection<CooperativityEvidence> evidences){
        if (evidences == null){
            this.cooperativityEvidences = Collections.EMPTY_LIST;
        }
        else{
            this.cooperativityEvidences = evidences;
        }
    }

    /**
     * <p>initialiseAffectedInteractions</p>
     */
    protected void initialiseAffectedInteractions(){
        this.affectedInteractions = new ArrayList<ModelledInteraction>();
    }

    /**
     * <p>initialiseAffectedInteractionsWith</p>
     *
     * @param affected a {@link java.util.Collection} object.
     */
    protected void initialiseAffectedInteractionsWith(Collection<ModelledInteraction> affected){
        if (affected == null){
            this.affectedInteractions = Collections.EMPTY_LIST;
        }
        else{
            this.affectedInteractions = affected;
        }
    }

    /**
     * <p>initialiseAnnotations</p>
     */
    protected void initialiseAnnotations(){
        this.annotations = new ArrayList<Annotation>();
    }

    /**
     * <p>initialiseAnnotationsWith</p>
     *
     * @param annotations a {@link java.util.Collection} object.
     */
    protected void initialiseAnnotationsWith(Collection<Annotation> annotations){
        if (annotations == null){
            this.annotations = Collections.EMPTY_LIST;
        }
        else{
            this.annotations = annotations;
        }
    }

    /**
     * <p>Getter for the field <code>cooperativityEvidences</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CooperativityEvidence> getCooperativityEvidences() {
        if (cooperativityEvidences == null){
            initialiseCooperativityEvidences();
        }
        return cooperativityEvidences;
    }

    /**
     * <p>Getter for the field <code>affectedInteractions</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledInteraction> getAffectedInteractions() {
        if (affectedInteractions == null){
            initialiseAffectedInteractions();
        }
        return affectedInteractions;
    }

    /**
     * <p>Getter for the field <code>annotations</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        if (annotations == null){
            initialiseAnnotations();
        }
        return annotations;
    }

    /**
     * <p>getOutCome</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getOutCome() {
        return outcome;
    }

    /** {@inheritDoc} */
    public void setOutCome(CvTerm effect) {
        if (effect == null){
           throw new IllegalArgumentException("The outcome of a CooperativeEffect cannot be null");
        }
        this.outcome = effect;
    }

    /**
     * <p>Getter for the field <code>response</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getResponse() {
        return this.response;
    }

    /** {@inheritDoc} */
    public void setResponse(CvTerm response) {
        this.response = response;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Outcome: "+(getOutCome() != null ? getOutCome().toString() : "") + (getResponse() != null ? ", response: " + getResponse().toString() : "");
    }
}

package psidev.psi.mi.jami.enricher.impl;

import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.FeatureEnricher;
import psidev.psi.mi.jami.enricher.ParticipantEnricher;
import psidev.psi.mi.jami.enricher.ParticipantPoolEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.listener.EntityEnricherListener;
import psidev.psi.mi.jami.model.*;

import java.util.Collection;
import java.util.Collections;

/**
 * General enricher for entities and participants that can use sub enrichers for enriching specific interactors
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/02/14</pre>
 */
public class CompositeParticipantEnricher implements ParticipantEnricher<Participant, Feature>{

    private ParticipantEnricher<Participant,Feature> entityBaseEnricher;
    private CompositeModelledParticipantEnricher modelledEntityEnricher;
    private CompositeParticipantEvidenceEnricher experimentalEntityEnricher;
    private ParticipantPoolEnricher<ParticipantPool,Feature> poolEnricher;

    /**
     * <p>Constructor for CompositeParticipantEnricher.</p>
     *
     * @param entityBaseEnricher a {@link psidev.psi.mi.jami.enricher.ParticipantEnricher} object.
     */
    public CompositeParticipantEnricher(ParticipantEnricher<Participant, Feature> entityBaseEnricher){
        super();
        if (entityBaseEnricher == null){
            throw new IllegalArgumentException("At least the Participant base enricher is needed and cannot be null") ;
        }
        this.entityBaseEnricher = entityBaseEnricher;
    }

    /**
     * <p>Getter for the field <code>entityBaseEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.ParticipantEnricher} object.
     */
    public ParticipantEnricher<Participant, Feature> getEntityBaseEnricher() {
        return entityBaseEnricher;
    }

    /**
     * <p>Getter for the field <code>modelledEntityEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.CompositeModelledParticipantEnricher} object.
     */
    public CompositeModelledParticipantEnricher getModelledEntityEnricher() {
        return modelledEntityEnricher;
    }

    /**
     * <p>Setter for the field <code>modelledEntityEnricher</code>.</p>
     *
     * @param modelledEntityEnricher a {@link psidev.psi.mi.jami.enricher.impl.CompositeModelledParticipantEnricher} object.
     */
    public void setModelledEntityEnricher(CompositeModelledParticipantEnricher modelledEntityEnricher) {
        this.modelledEntityEnricher = modelledEntityEnricher;
    }

    /**
     * <p>Getter for the field <code>experimentalEntityEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.CompositeParticipantEvidenceEnricher} object.
     */
    public CompositeParticipantEvidenceEnricher getExperimentalEntityEnricher() {
        return experimentalEntityEnricher;
    }

    /**
     * <p>Setter for the field <code>experimentalEntityEnricher</code>.</p>
     *
     * @param experimentalEntityEnricher a {@link psidev.psi.mi.jami.enricher.impl.CompositeParticipantEvidenceEnricher} object.
     */
    public void setExperimentalEntityEnricher(CompositeParticipantEvidenceEnricher experimentalEntityEnricher) {
        this.experimentalEntityEnricher = experimentalEntityEnricher;
    }

    /**
     * <p>Getter for the field <code>poolEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.ParticipantPoolEnricher} object.
     */
    public ParticipantPoolEnricher<ParticipantPool, Feature> getPoolEnricher() {
        return poolEnricher;
    }

    /**
     * <p>Setter for the field <code>poolEnricher</code>.</p>
     *
     * @param poolEnricher a {@link psidev.psi.mi.jami.enricher.ParticipantPoolEnricher} object.
     */
    public void setPoolEnricher(ParticipantPoolEnricher<ParticipantPool, Feature> poolEnricher) {
        this.poolEnricher = poolEnricher;
    }

    /**
     * <p>enrich.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Participant} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void enrich(Participant object) throws EnricherException {
        if(object == null)
            throw new IllegalArgumentException("Cannot enrich a null entity.");
        if (object instanceof ParticipantEvidence){
            if (this.experimentalEntityEnricher != null){
               this.experimentalEntityEnricher.enrich((ParticipantEvidence)object);
            }
            else{
                this.entityBaseEnricher.enrich(object);
            }
        }
        else if (object instanceof ModelledParticipant){
            if (this.modelledEntityEnricher != null){
                this.modelledEntityEnricher.enrich((ModelledParticipant)object);
            }
            else{
                this.entityBaseEnricher.enrich(object);
            }
        }
        else if (object instanceof ParticipantPool){
            if (this.poolEnricher != null){
                this.poolEnricher.enrich(Collections.singleton((ParticipantPool)object));
            }
            else{
                this.entityBaseEnricher.enrich(object);
            }
        }
        else{
            this.entityBaseEnricher.enrich(object);
        }
    }

    /**
     * <p>enrich.</p>
     *
     * @param objects a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void enrich(Collection<Participant> objects) throws EnricherException {
        if(objects == null)
            throw new IllegalArgumentException("Cannot enrich a null collection of interactors.");

        for (Participant object : objects){
            enrich(object);
        }
    }

    /**
     * <p>enrich.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param objectSource a {@link psidev.psi.mi.jami.model.Participant} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void enrich(Participant object, Participant objectSource) throws EnricherException {
        if (object instanceof ParticipantEvidence && objectSource instanceof ParticipantEvidence){
            if (this.experimentalEntityEnricher != null){
                this.experimentalEntityEnricher.enrich((ParticipantEvidence)object, (ParticipantEvidence)objectSource);
            }
            else{
                this.entityBaseEnricher.enrich(object, objectSource);
            }
        }
        else if (object instanceof ModelledParticipant && objectSource instanceof ModelledParticipant){
            if (this.modelledEntityEnricher != null){
                this.modelledEntityEnricher.enrich((ModelledParticipant)object, (ModelledParticipant)objectSource);
            }
            else{
                this.entityBaseEnricher.enrich(object, objectSource);
            }
        }
        else if (object instanceof ParticipantPool && objectSource instanceof ParticipantPool){
            if (this.poolEnricher != null){
                this.poolEnricher.enrich((ParticipantPool)object, (ParticipantPool)objectSource);
            }
            else{
                this.entityBaseEnricher.enrich(object, objectSource);
            }
        }
        else{
            this.entityBaseEnricher.enrich(object);
        }
    }

    /**
     * <p>getInteractorEnricher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.CompositeInteractorEnricher} object.
     */
    public CompositeInteractorEnricher getInteractorEnricher() {
        return this.entityBaseEnricher.getInteractorEnricher();
    }

    /**
     * <p>getCvTermEnricher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.CvTermEnricher} object.
     */
    public CvTermEnricher<CvTerm> getCvTermEnricher() {
        return this.entityBaseEnricher.getCvTermEnricher();
    }

    /** {@inheritDoc} */
    public void setCvTermEnricher(CvTermEnricher<CvTerm> enricher) {
        this.entityBaseEnricher.setCvTermEnricher(enricher);

        if (this.experimentalEntityEnricher != null){
            this.experimentalEntityEnricher.setCvTermEnricher(enricher);
        }
        if (this.modelledEntityEnricher != null){
            this.modelledEntityEnricher.setCvTermEnricher(enricher);
        }
        if (this.poolEnricher != null){
            this.poolEnricher.setCvTermEnricher(enricher);
        }
    }

    /**
     * <p>getFeatureEnricher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.FeatureEnricher} object.
     */
    public FeatureEnricher getFeatureEnricher() {
        return this.entityBaseEnricher.getFeatureEnricher();
    }

    /**
     * <p>getParticipantEnricherListener.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.listener.EntityEnricherListener} object.
     */
    public EntityEnricherListener getParticipantEnricherListener() {
        return this.entityBaseEnricher.getParticipantEnricherListener();
    }

    /** {@inheritDoc} */
    public void setInteractorEnricher(CompositeInteractorEnricher interactorEnricher) {
        this.entityBaseEnricher.setInteractorEnricher(interactorEnricher);

        if (this.experimentalEntityEnricher != null){
            this.experimentalEntityEnricher.setInteractorEnricher(interactorEnricher);
        }
        if (this.modelledEntityEnricher != null){
            this.modelledEntityEnricher.setInteractorEnricher(interactorEnricher);
        }
        if (this.poolEnricher != null){
            this.poolEnricher.setInteractorEnricher(interactorEnricher);
        }
    }

    /** {@inheritDoc} */
    public void setFeatureEnricher(FeatureEnricher<Feature> enricher) {
        this.entityBaseEnricher.setFeatureEnricher(enricher);

        if (this.poolEnricher != null){
            this.poolEnricher.setFeatureEnricher(enricher);
        }
    }

    /** {@inheritDoc} */
    public void setParticipantEnricherListener(EntityEnricherListener listener) {
        this.entityBaseEnricher.setParticipantEnricherListener(listener);

        if (this.poolEnricher != null){
            this.poolEnricher.setParticipantEnricherListener(listener);
        }
    }
}

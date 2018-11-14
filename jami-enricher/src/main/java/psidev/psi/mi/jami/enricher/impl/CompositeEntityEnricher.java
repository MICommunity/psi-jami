package psidev.psi.mi.jami.enricher.impl;

import psidev.psi.mi.jami.enricher.EntityEnricher;
import psidev.psi.mi.jami.enricher.FeatureEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.listener.EntityEnricherListener;
import psidev.psi.mi.jami.model.*;

import java.util.Collection;

/**
 * General enricher for entities and participants candidates that can use sub enrichers for enriching specific interactors
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/02/14</pre>
 */
public class CompositeEntityEnricher implements EntityEnricher<Entity, Feature> {

    private EntityEnricher<Entity,Feature> entityBaseEnricher;
    private EntityEnricher<ModelledEntity,ModelledFeature> modelledEntityEnricher;
    private EntityEnricher<Entity<FeatureEvidence>, FeatureEvidence> experimentalEntityEnricher;

    /**
     * <p>Constructor for CompositeEntityEnricher.</p>
     *
     * @param entityBaseEnricher a {@link psidev.psi.mi.jami.enricher.EntityEnricher} object.
     */
    public CompositeEntityEnricher(EntityEnricher<Entity, Feature> entityBaseEnricher){
        super();
        if (entityBaseEnricher == null){
            throw new IllegalArgumentException("At least the Participant base enricher is needed and cannot be null") ;
        }
        this.entityBaseEnricher = entityBaseEnricher;
    }

    /**
     * <p>Getter for the field <code>entityBaseEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.EntityEnricher} object.
     */
    public EntityEnricher<Entity, Feature> getEntityBaseEnricher() {
        return entityBaseEnricher;
    }

    /**
     * <p>Getter for the field <code>modelledEntityEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.EntityEnricher} object.
     */
    public EntityEnricher<ModelledEntity,ModelledFeature> getModelledEntityEnricher() {
        return modelledEntityEnricher;
    }

    /**
     * <p>Setter for the field <code>modelledEntityEnricher</code>.</p>
     *
     * @param modelledEntityEnricher a {@link psidev.psi.mi.jami.enricher.EntityEnricher} object.
     */
    public void setModelledEntityEnricher(EntityEnricher<ModelledEntity,ModelledFeature> modelledEntityEnricher) {
        this.modelledEntityEnricher = modelledEntityEnricher;
    }

    /**
     * <p>Getter for the field <code>experimentalEntityEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.EntityEnricher} object.
     */
    public EntityEnricher<Entity<FeatureEvidence>, FeatureEvidence> getExperimentalEntityEnricher() {
        return experimentalEntityEnricher;
    }

    /**
     * <p>Setter for the field <code>experimentalEntityEnricher</code>.</p>
     *
     * @param experimentalEntityEnricher a {@link psidev.psi.mi.jami.enricher.EntityEnricher} object.
     */
    public void setExperimentalEntityEnricher(EntityEnricher<Entity<FeatureEvidence>, FeatureEvidence> experimentalEntityEnricher) {
        this.experimentalEntityEnricher = experimentalEntityEnricher;
    }

    /**
     * <p>enrich.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Entity} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void enrich(Entity object) throws EnricherException {
        if(object == null)
            throw new IllegalArgumentException("Cannot enrich a null entity.");
        if (object instanceof Entity){
            if (this.experimentalEntityEnricher != null){
               this.experimentalEntityEnricher.enrich((Entity<FeatureEvidence>)object);
            }
            else{
                this.entityBaseEnricher.enrich(object);
            }
        }
        else if (object instanceof ModelledEntity){
            if (this.modelledEntityEnricher != null){
                this.modelledEntityEnricher.enrich((ModelledEntity)object);
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
    public void enrich(Collection<Entity> objects) throws EnricherException {
        if(objects == null)
            throw new IllegalArgumentException("Cannot enrich a null collection of interactors.");

        for (Entity object : objects){
            enrich(object);
        }
    }

    /**
     * <p>enrich.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Entity} object.
     * @param objectSource a {@link psidev.psi.mi.jami.model.Entity} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void enrich(Entity object, Entity objectSource) throws EnricherException {
        if (object instanceof Entity && objectSource instanceof Entity){
            if (this.experimentalEntityEnricher != null){
                this.experimentalEntityEnricher.enrich((Entity<FeatureEvidence>)object, (Entity<FeatureEvidence>)objectSource);
            }
            else{
                this.entityBaseEnricher.enrich(object, objectSource);
            }
        }
        else if (object instanceof ModelledEntity && objectSource instanceof ModelledEntity){
            if (this.modelledEntityEnricher != null){
                this.modelledEntityEnricher.enrich((ModelledEntity)object, (ModelledEntity)objectSource);
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
        if (getModelledEntityEnricher() != null){
            getModelledEntityEnricher().setInteractorEnricher(interactorEnricher);
        }
        if (getExperimentalEntityEnricher() != null){
            getExperimentalEntityEnricher().setInteractorEnricher(interactorEnricher);
        }
    }

    /** {@inheritDoc} */
    public void setFeatureEnricher(FeatureEnricher<Feature> enricher) {
        this.entityBaseEnricher.setFeatureEnricher(enricher);
    }

    /** {@inheritDoc} */
    public void setParticipantEnricherListener(EntityEnricherListener listener) {
        this.entityBaseEnricher.setParticipantEnricherListener(listener);
    }
}

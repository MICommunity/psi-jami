package psidev.psi.mi.jami.enricher.impl;

import psidev.psi.mi.jami.enricher.*;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.listener.EntityEnricherListener;
import psidev.psi.mi.jami.model.*;

import java.util.Collection;

/**
 * General enricher for entities and participants that can use sub enrichers for enriching specific interactors
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/02/14</pre>
 */
public class CompositeParticipantEvidenceEnricher implements ParticipantEnricher<ParticipantEvidence, FeatureEvidence>{

    private ParticipantEnricher<ParticipantEvidence,FeatureEvidence> entityBaseEnricher;
    private ExperimentalParticipantPoolEnricher poolEntityEnricher;

    /**
     * <p>Constructor for CompositeParticipantEvidenceEnricher.</p>
     *
     * @param entityBaseEnricher a {@link psidev.psi.mi.jami.enricher.ParticipantEnricher} object.
     */
    public CompositeParticipantEvidenceEnricher(ParticipantEnricher<ParticipantEvidence, FeatureEvidence> entityBaseEnricher){
        super();
        if (entityBaseEnricher == null){
            throw new IllegalArgumentException("At least the modelled Participant base enricher is needed and cannot be null") ;
        }
        this.entityBaseEnricher = entityBaseEnricher;
    }

    /**
     * <p>Getter for the field <code>poolEntityEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.ExperimentalParticipantPoolEnricher} object.
     */
    public ExperimentalParticipantPoolEnricher getPoolEntityEnricher() {
        return poolEntityEnricher;
    }

    /**
     * <p>Setter for the field <code>poolEntityEnricher</code>.</p>
     *
     * @param poolEntityEnricher a {@link psidev.psi.mi.jami.enricher.ExperimentalParticipantPoolEnricher} object.
     */
    public void setPoolEntityEnricher(ExperimentalParticipantPoolEnricher poolEntityEnricher) {
        this.poolEntityEnricher = poolEntityEnricher;
    }

    /**
     * <p>enrich.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void enrich(ParticipantEvidence object) throws EnricherException {
        if(object == null)
            throw new IllegalArgumentException("Cannot enrich a null entity.");
        if (object instanceof ExperimentalParticipantPool){
            if (this.poolEntityEnricher != null){
               this.poolEntityEnricher.enrich((ExperimentalParticipantPool)object);
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
    public void enrich(Collection<ParticipantEvidence> objects) throws EnricherException {
        if(objects == null)
            throw new IllegalArgumentException("Cannot enrich a null collection of interactors.");

        for (ParticipantEvidence object : objects){
            enrich(object);
        }
    }

    /**
     * <p>enrich.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param objectSource a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void enrich(ParticipantEvidence object, ParticipantEvidence objectSource) throws EnricherException {
        if (object instanceof ExperimentalParticipantPool && objectSource instanceof ExperimentalParticipantPool){
            if (this.poolEntityEnricher != null){
                this.poolEntityEnricher.enrich((ExperimentalParticipantPool)object, (ExperimentalParticipantPool)objectSource);
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
    public void setCvTermEnricher(CvTermEnricher<CvTerm> enricher) {
        this.entityBaseEnricher.setCvTermEnricher(enricher);
        if (poolEntityEnricher != null){
            poolEntityEnricher.setCvTermEnricher(enricher);
        }
    }

    /** {@inheritDoc} */
    public void setInteractorEnricher(CompositeInteractorEnricher interactorEnricher) {
        this.entityBaseEnricher.setInteractorEnricher(interactorEnricher);
        if (poolEntityEnricher != null){
            poolEntityEnricher.setInteractorEnricher(interactorEnricher);
        }
    }

    /** {@inheritDoc} */
    public void setFeatureEnricher(FeatureEnricher<FeatureEvidence> enricher) {
        this.entityBaseEnricher.setFeatureEnricher(enricher);
        if (poolEntityEnricher != null){
            poolEntityEnricher.setFeatureEnricher(enricher);
        }
    }

    /** {@inheritDoc} */
    public void setParticipantEnricherListener(EntityEnricherListener listener) {
        this.entityBaseEnricher.setParticipantEnricherListener(listener);
        if (poolEntityEnricher != null){
            poolEntityEnricher.setParticipantEnricherListener(listener);
        }
    }
}

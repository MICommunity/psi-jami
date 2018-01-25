package psidev.psi.mi.jami.enricher.impl.full;

import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.InteractionEvidenceEnricher;
import psidev.psi.mi.jami.enricher.ParticipantEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalModelledInteractionEnricher;
import psidev.psi.mi.jami.enricher.listener.InteractionEnricherListener;
import psidev.psi.mi.jami.enricher.listener.ModelledInteractionEnricherListener;
import psidev.psi.mi.jami.enricher.util.EnricherUtils;
import psidev.psi.mi.jami.model.CooperativeEffect;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.ModelledInteraction;

import java.util.Iterator;

/**
 * Full enricher for ModelledInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>01/10/13</pre>
 */
public class FullModelledInteractionEnricher<I extends ModelledInteraction> extends MinimalModelledInteractionEnricher<I> {

    private FullInteractionEnricher<I> interactionEnricher;
    private InteractionEvidenceEnricher evidenceEnricher;

    /**
     * <p>Constructor for FullModelledInteractionEnricher.</p>
     */
    public FullModelledInteractionEnricher() {
        super();
        this.interactionEnricher = new FullInteractionEnricher<I>();
    }

    /**
     * <p>Constructor for FullModelledInteractionEnricher.</p>
     *
     * @param interactionEnricher a {@link psidev.psi.mi.jami.enricher.impl.full.FullInteractionEnricher} object.
     */
    protected FullModelledInteractionEnricher(FullInteractionEnricher<I> interactionEnricher) {
        super();
        this.interactionEnricher = interactionEnricher != null ? interactionEnricher : new FullInteractionEnricher<I>();
    }

    /** {@inheritDoc} */
    @Override
    protected void processOtherProperties(I interactionToEnrich) throws EnricherException {
        super.processOtherProperties(interactionToEnrich);

        // PROCESS RIGID
        this.interactionEnricher.processOtherProperties(interactionToEnrich);

        // process interaction evidences
        processInteractionEvidences(interactionToEnrich);

        // process evidence type
        processEvidenceType(interactionToEnrich);

    }

    /**
     * <p>processEvidenceType.</p>
     *
     * @param interactionToEnrich a I object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processEvidenceType(I interactionToEnrich) throws EnricherException {

        if (interactionToEnrich.getEvidenceType() != null && getCvTermEnricher() != null){
            getCvTermEnricher().enrich(interactionToEnrich.getEvidenceType());
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void processOtherProperties(I objectToEnrich, I objectSource) throws EnricherException {
        super.processOtherProperties(objectToEnrich, objectSource);

        this.interactionEnricher.processOtherProperties(objectToEnrich, objectSource);

        // evidence code
        processEvidenceType(objectToEnrich, objectSource);
        // confidences
        processConfidences(objectToEnrich, objectSource);
        // parameters
        processParameters(objectToEnrich, objectSource);
        // cooperative effects
        processCooperativeEffects(objectToEnrich, objectSource);
        // interactionEvidences
        processInteractionEvidences(objectToEnrich, objectSource);
    }

    /**
     * <p>processEvidenceType.</p>
     *
     * @param objectToEnrich a I object.
     * @param objectSource a I object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processEvidenceType(I objectToEnrich, I objectSource) throws EnricherException {

        if (objectSource.getEvidenceType() != null && objectToEnrich.getEvidenceType() == null){
            objectSource.setEvidenceType(objectToEnrich.getEvidenceType());
            if (getInteractionEnricherListener() instanceof ModelledInteractionEnricherListener){
                ((ModelledInteractionEnricherListener)getInteractionEnricherListener()).onEvidenceTypeUpdate(objectToEnrich, null);
            }
        }
        processEvidenceType(objectToEnrich);
    }

    /** {@inheritDoc} */
    @Override
    public CvTermEnricher<CvTerm> getCvTermEnricher() {
        return this.interactionEnricher.getCvTermEnricher();
    }

    /** {@inheritDoc} */
    @Override
    public ParticipantEnricher getParticipantEnricher() {
        return this.interactionEnricher.getParticipantEnricher();
    }

    /** {@inheritDoc} */
    @Override
    public InteractionEnricherListener<I> getInteractionEnricherListener() {
        return this.interactionEnricher.getInteractionEnricherListener();
    }

    /** {@inheritDoc} */
    @Override
    public void setCvTermEnricher(CvTermEnricher cvTermEnricher) {
        this.interactionEnricher.setCvTermEnricher(cvTermEnricher);
    }

    /** {@inheritDoc} */
    @Override
    public void setParticipantEnricher(ParticipantEnricher participantEnricher) {
        this.interactionEnricher.setParticipantEnricher(participantEnricher);
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractionEnricherListener(InteractionEnricherListener<I> listener) {
        this.interactionEnricher.setInteractionEnricherListener(listener);
    }

    /**
     * <p>processConfidences.</p>
     *
     * @param objectToEnrich a I object.
     * @param objectSource a I object.
     */
    protected void processConfidences(I objectToEnrich, I objectSource) {
        EnricherUtils.mergeConfidences(objectToEnrich, objectToEnrich.getModelledConfidences(), objectSource.getModelledConfidences(), false,
                (getInteractionEnricherListener() instanceof ModelledInteractionEnricherListener ? (ModelledInteractionEnricherListener) getInteractionEnricherListener() : null));
    }

    /**
     * <p>processParameters.</p>
     *
     * @param objectToEnrich a I object.
     * @param objectSource a I object.
     */
    protected void processParameters(I objectToEnrich, I objectSource) {

        EnricherUtils.mergeParameters(objectToEnrich, objectToEnrich.getModelledParameters(), objectSource.getModelledParameters(), false,
                (getInteractionEnricherListener() instanceof ModelledInteractionEnricherListener ? (ModelledInteractionEnricherListener)getInteractionEnricherListener():null));
    }

    /**
     * <p>processCooperativeEffects.</p>
     *
     * @param objectToEnrich a I object.
     * @param objectSource a I object.
     */
    protected void processCooperativeEffects(I objectToEnrich, I objectSource) {
        mergeCooperativeEffects(objectToEnrich, objectSource, false);
    }

    /**
     * <p>mergeCooperativeEffects.</p>
     *
     * @param interactionToEnrich a I object.
     * @param objectSource a I object.
     * @param remove a boolean.
     */
    protected void mergeCooperativeEffects(I interactionToEnrich, I objectSource, boolean remove){
        Iterator<CooperativeEffect> effectIterator = interactionToEnrich.getCooperativeEffects().iterator();
        if (remove){
            while(effectIterator.hasNext()){
                CooperativeEffect effect = effectIterator.next();

                boolean containsEffect = false;
                for (CooperativeEffect effect2 : objectSource.getCooperativeEffects()){
                    // identical parameter
                    if (effect == effect2){
                        containsEffect = true;
                        break;
                    }
                }
                // remove parameter not in second list
                if (!containsEffect){
                    effectIterator.remove();
                    if (getInteractionEnricherListener() instanceof ModelledInteractionEnricherListener){
                        ((ModelledInteractionEnricherListener)getInteractionEnricherListener()).onRemovedCooperativeEffect(interactionToEnrich, effect);
                    }
                }
            }
        }

        effectIterator = objectSource.getCooperativeEffects().iterator();
        while(effectIterator.hasNext()){
            CooperativeEffect effect = effectIterator.next();
            boolean containsEffect = false;
            for (CooperativeEffect effect2 : interactionToEnrich.getCooperativeEffects()){
                // identical param
                if (effect == effect2){
                    containsEffect = true;
                    break;
                }
            }
            if (!containsEffect){
                interactionToEnrich.getCooperativeEffects().add(effect);
                if (getInteractionEnricherListener() instanceof ModelledInteractionEnricherListener){
                    ((ModelledInteractionEnricherListener)getInteractionEnricherListener()).onAddedCooperativeEffect(interactionToEnrich, effect);
                }
            }
        }
    }

    /**
     * <p>processInteractionEvidences.</p>
     *
     * @param objectToEnrich a I object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processInteractionEvidences(I objectToEnrich) throws EnricherException {
        if (getEvidenceEnricher() != null){
           getEvidenceEnricher().enrich(objectToEnrich.getInteractionEvidences());
        }
    }

    /**
     * <p>processInteractionEvidences.</p>
     *
     * @param objectToEnrich a I object.
     * @param objectSource a I object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processInteractionEvidences(I objectToEnrich, I objectSource) throws EnricherException {
        mergeInteractionEvidences(objectToEnrich, objectSource, false);

        processInteractionEvidences(objectToEnrich);
    }

    /**
     * <p>mergeInteractionEvidences.</p>
     *
     * @param interactionToEnrich a I object.
     * @param objectSource a I object.
     * @param remove a boolean.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void mergeInteractionEvidences(I interactionToEnrich, I objectSource, boolean remove) throws EnricherException {
        Iterator<InteractionEvidence> evidenceIterator = interactionToEnrich.getInteractionEvidences().iterator();
        if (remove){
            while(evidenceIterator.hasNext()){
                InteractionEvidence inter = evidenceIterator.next();

                boolean containsEvidence = false;
                for (InteractionEvidence inter2 : objectSource.getInteractionEvidences()){
                    // identical parameter
                    if (inter == inter2){
                        containsEvidence = true;
                        break;
                    }
                }
                // remove parameter not in second list
                if (!containsEvidence){
                    evidenceIterator.remove();
                    if (getInteractionEnricherListener() instanceof ModelledInteractionEnricherListener){
                        ((ModelledInteractionEnricherListener)getInteractionEnricherListener()).onRemovedInteractionEvidence(interactionToEnrich, inter);
                    }
                }
            }
        }

        evidenceIterator = objectSource.getInteractionEvidences().iterator();
        while(evidenceIterator.hasNext()){
            InteractionEvidence inter = evidenceIterator.next();
            boolean containsEvidences = false;
            for (InteractionEvidence inter2 : interactionToEnrich.getInteractionEvidences()){
                // identical param
                if (inter == inter2){
                    containsEvidences = true;
                    if (getEvidenceEnricher() != null){
                        getEvidenceEnricher().enrich(inter2, inter);
                    }
                    break;
                }
            }
            if (!containsEvidences){
                interactionToEnrich.getInteractionEvidences().add(inter);
                if (getInteractionEnricherListener() instanceof ModelledInteractionEnricherListener){
                    ((ModelledInteractionEnricherListener)getInteractionEnricherListener()).onAddedInteractionEvidence(interactionToEnrich, inter);
                }
            }
        }
    }

    /**
     * <p>Getter for the field <code>evidenceEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.InteractionEvidenceEnricher} object.
     */
    public InteractionEvidenceEnricher getEvidenceEnricher() {
        return evidenceEnricher;
    }

    /**
     * <p>Setter for the field <code>evidenceEnricher</code>.</p>
     *
     * @param evidenceEnricher a {@link psidev.psi.mi.jami.enricher.InteractionEvidenceEnricher} object.
     */
    public void setEvidenceEnricher(InteractionEvidenceEnricher evidenceEnricher) {
        this.evidenceEnricher = evidenceEnricher;
    }

    /**
     * <p>Getter for the field <code>interactionEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.full.FullInteractionEnricher} object.
     */
    protected FullInteractionEnricher<I> getInteractionEnricher() {
        return interactionEnricher;
    }
}

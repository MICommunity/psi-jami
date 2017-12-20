package psidev.psi.mi.jami.enricher.impl.minimal;

import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.InteractionEvidenceEnricher;
import psidev.psi.mi.jami.enricher.ParticipantEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.listener.InteractionEnricherListener;
import psidev.psi.mi.jami.enricher.listener.InteractionEvidenceEnricherListener;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.utils.comparator.experiment.DefaultCuratedExperimentComparator;

/**
 * Minimal updater for interaction evidence
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 13/08/13

 */
public class MinimalInteractionEvidenceUpdater
        extends MinimalInteractionEvidenceEnricher
        implements InteractionEvidenceEnricher {

    private MinimalInteractionUpdater<InteractionEvidence> delegate;

    /**
     * <p>Constructor for MinimalInteractionEvidenceUpdater.</p>
     */
    public MinimalInteractionEvidenceUpdater(){
        super();
        this.delegate = new MinimalInteractionUpdater<InteractionEvidence>();
    }

    /**
     * <p>Constructor for MinimalInteractionEvidenceUpdater.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalInteractionUpdater} object.
     */
    protected MinimalInteractionEvidenceUpdater( MinimalInteractionUpdater<InteractionEvidence> delegate){
        super();
        this.delegate = delegate != null ? delegate : new MinimalInteractionUpdater<InteractionEvidence>();
    }

    /** {@inheritDoc} */
    @Override
    public void processMinimalUpdates(InteractionEvidence objectToEnrich, InteractionEvidence objectSource) throws EnricherException {
        this.delegate.processMinimalUpdates(objectToEnrich, objectSource);
    }

    /** {@inheritDoc} */
    @Override
    protected void processExperiment(InteractionEvidence objectToEnrich, InteractionEvidence objectSource) throws EnricherException {
         if (!DefaultCuratedExperimentComparator.areEquals(objectSource.getExperiment(), objectToEnrich.getExperiment())){
             Experiment old = objectToEnrich.getExperiment();
              objectToEnrich.setExperiment(objectSource.getExperiment());
             if (getInteractionEnricherListener() instanceof InteractionEvidenceEnricherListener){
                 ((InteractionEvidenceEnricherListener)getInteractionEnricherListener()).onExperimentUpdate(objectToEnrich, old);
             }
         }
         else if (getExperimentEnricher() != null
                 && objectToEnrich.getExperiment() != objectSource.getExperiment()){
             getExperimentEnricher().enrich(objectToEnrich.getExperiment(), objectSource.getExperiment());
         }
    }

    /** {@inheritDoc} */
    @Override
    protected void processOtherProperties(InteractionEvidence objectToEnrich, InteractionEvidence objectSource) throws EnricherException{
        super.processOtherProperties(objectToEnrich, objectSource);

        // set negative
        if (objectSource.isNegative() != objectToEnrich.isNegative()){
            objectToEnrich.setNegative(objectSource.isNegative());
            if (getInteractionEnricherListener() instanceof InteractionEvidenceEnricherListener){
                ((InteractionEvidenceEnricherListener)getInteractionEnricherListener()).onNegativePropertyUpdate(objectToEnrich, !objectSource.isNegative());
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setCvTermEnricher(CvTermEnricher<CvTerm> cvTermEnricher) {
        this.delegate.setCvTermEnricher(cvTermEnricher);
    }

    /** {@inheritDoc} */
    @Override
    public CvTermEnricher<CvTerm> getCvTermEnricher() {
        return this.delegate.getCvTermEnricher();
    }

    /** {@inheritDoc} */
    @Override
    public void setParticipantEnricher(ParticipantEnricher participantEnricher) {
        this.delegate.setParticipantEnricher(participantEnricher);
    }

    /** {@inheritDoc} */
    @Override
    public ParticipantEnricher getParticipantEnricher() {
        return this.delegate.getParticipantEnricher();
    }

    /** {@inheritDoc} */
    @Override
    public InteractionEnricherListener<InteractionEvidence> getInteractionEnricherListener() {
        return this.delegate.getInteractionEnricherListener();
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractionEnricherListener(InteractionEnricherListener<InteractionEvidence> listener) {
        this.delegate.setInteractionEnricherListener(listener);
    }

    /**
     * <p>Getter for the field <code>delegate</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalInteractionUpdater} object.
     */
    public MinimalInteractionUpdater<InteractionEvidence> getDelegate() {
        return delegate;
    }
}


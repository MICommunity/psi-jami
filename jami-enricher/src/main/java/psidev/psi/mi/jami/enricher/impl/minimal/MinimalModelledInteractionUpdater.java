package psidev.psi.mi.jami.enricher.impl.minimal;

import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.ParticipantEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.listener.InteractionEnricherListener;
import psidev.psi.mi.jami.enricher.listener.ModelledInteractionEnricherListener;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.utils.comparator.cv.DefaultCvTermComparator;

/**
 * Minimal updater for modelled interaction
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 13/08/13

 */
public class MinimalModelledInteractionUpdater<I extends ModelledInteraction>
        extends MinimalModelledInteractionEnricher<I>{

    private MinimalInteractionUpdater<I> delegate;

    /**
     * <p>Constructor for MinimalModelledInteractionUpdater.</p>
     */
    public MinimalModelledInteractionUpdater(){
        super();
        this.delegate = new MinimalInteractionUpdater<I>();
    }

    /**
     * <p>Constructor for MinimalModelledInteractionUpdater.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalInteractionUpdater} object.
     */
    protected MinimalModelledInteractionUpdater( MinimalInteractionUpdater<I> delegate){
        super();
        this.delegate = delegate != null ? delegate : new MinimalInteractionUpdater<I>();
    }

    /** {@inheritDoc} */
    @Override
    public void processMinimalUpdates(I objectToEnrich, I objectSource) throws EnricherException {
        this.delegate.processMinimalUpdates(objectToEnrich, objectSource);
    }

    /** {@inheritDoc} */
    @Override
    protected void processSource(I objectToEnrich, I objectSource) throws EnricherException {
         if (!DefaultCvTermComparator.areEquals(objectSource.getSource(), objectToEnrich.getSource())){
             Source old = objectToEnrich.getSource();
              objectToEnrich.setSource(objectSource.getSource());
             if (getInteractionEnricherListener() instanceof ModelledInteractionEnricherListener){
                 ((ModelledInteractionEnricherListener)getInteractionEnricherListener()).onSourceUpdate(objectToEnrich, old);
             }
         }
         else if (getSourceEnricher() != null
                 && objectToEnrich.getSource() != objectSource.getSource()){
             getSourceEnricher().enrich(objectToEnrich.getSource(), objectSource.getSource());
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
    public InteractionEnricherListener<I> getInteractionEnricherListener() {
        return this.delegate.getInteractionEnricherListener();
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractionEnricherListener(InteractionEnricherListener<I> listener) {
        this.delegate.setInteractionEnricherListener(listener);
    }

    /**
     * <p>Getter for the field <code>delegate</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalInteractionUpdater} object.
     */
    protected MinimalInteractionUpdater<I> getDelegate() {
        return delegate;
    }
}


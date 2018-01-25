package psidev.psi.mi.jami.enricher.impl.full;


import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.FeatureEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.CompositeInteractorEnricher;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalParticipantEvidenceUpdater;
import psidev.psi.mi.jami.enricher.listener.EntityEnricherListener;
import psidev.psi.mi.jami.enricher.listener.ParticipantEvidenceEnricherListener;
import psidev.psi.mi.jami.enricher.util.EnricherUtils;
import psidev.psi.mi.jami.listener.XrefsChangeListener;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.ParticipantEvidence;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 28/06/13

 */
public class FullParticipantEvidenceUpdater<P extends ParticipantEvidence> extends FullParticipantEvidenceEnricher<P> {

    private MinimalParticipantEvidenceUpdater<P> minimalUpdater;

    /**
     * <p>Constructor for FullParticipantEvidenceUpdater.</p>
     */
    public FullParticipantEvidenceUpdater(){
        super();
        this.minimalUpdater = new MinimalParticipantEvidenceUpdater<P>();
    }

    /**
     * <p>Constructor for FullParticipantEvidenceUpdater.</p>
     *
     * @param participantEnricher a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalParticipantEvidenceUpdater} object.
     */
    protected FullParticipantEvidenceUpdater(MinimalParticipantEvidenceUpdater<P> participantEnricher){
        super();
        this.minimalUpdater = participantEnricher != null ? participantEnricher : new MinimalParticipantEvidenceUpdater<P>();
    }

    /** {@inheritDoc} */
    @Override
    protected void processExperimentalPreparations(P participantEvidenceToEnrich, P objectSource) throws EnricherException {
        mergeExperimentalPreparations(participantEvidenceToEnrich, participantEvidenceToEnrich.getExperimentalPreparations(), objectSource.getExperimentalPreparations(),
                true);
    }

    /** {@inheritDoc} */
    @Override
    protected void processParameters(P participantEvidenceToEnrich, P objectSource) throws EnricherException{
        EnricherUtils.mergeParameters(participantEvidenceToEnrich, participantEvidenceToEnrich.getParameters(), objectSource.getParameters(), true,
                (getParticipantEnricherListener() instanceof ParticipantEvidenceEnricherListener ? (ParticipantEvidenceEnricherListener)getParticipantEnricherListener() : null));
    }

    /** {@inheritDoc} */
    @Override
    protected void processConfidences(P participantEvidenceToEnrich, P objectSource) throws EnricherException{
        EnricherUtils.mergeConfidences(participantEvidenceToEnrich, participantEvidenceToEnrich.getConfidences(), objectSource.getConfidences(), true,
                (getParticipantEnricherListener() instanceof ParticipantEvidenceEnricherListener ? (ParticipantEvidenceEnricherListener) getParticipantEnricherListener() : null));
    }

    /** {@inheritDoc} */
    @Override
    protected void processXrefs(P participantEvidenceToEnrich, P objectSource) throws EnricherException{
        EnricherUtils.mergeXrefs(participantEvidenceToEnrich, participantEvidenceToEnrich.getXrefs(), objectSource.getXrefs(), true, false,
                getParticipantEnricherListener() instanceof XrefsChangeListener ? (XrefsChangeListener)getParticipantEnricherListener():null, null);
    }

    /** {@inheritDoc} */
    @Override
    public void processIdentificationMethods(P participantEvidenceToEnrich, P objectSource) throws EnricherException {
        this.minimalUpdater.processIdentificationMethods(participantEvidenceToEnrich, objectSource);
    }

    /** {@inheritDoc} */
    @Override
    public void processExperimentalRole(P participantEvidenceToEnrich, P objectSource) throws EnricherException {
        this.minimalUpdater.processExperimentalRole(participantEvidenceToEnrich, objectSource);
    }

    /** {@inheritDoc} */
    @Override
    public void processInteractor(P objectToEnrich, P objectSource) throws EnricherException {
        this.minimalUpdater.processInteractor(objectToEnrich, objectSource);
    }

    /** {@inheritDoc} */
    @Override
    public void processFeatures(P objectToEnrich, P objectSource) throws EnricherException {
        this.minimalUpdater.processFeatures(objectToEnrich, objectSource);
    }

    /** {@inheritDoc} */
    @Override
    public void processBiologicalRole(P objectToEnrich, P objectSource) throws EnricherException {
        this.minimalUpdater.processBiologicalRole(objectToEnrich, objectSource);
    }

    /** {@inheritDoc} */
    @Override
    protected void processAliases(P objectToEnrich, P objectSource) throws EnricherException{
        this.minimalUpdater.processAliases(objectToEnrich, objectSource);
    }

    /** {@inheritDoc} */
    @Override
    public void setParticipantEnricherListener(EntityEnricherListener listener) {
        this.minimalUpdater.setParticipantEnricherListener(listener);
    }

    /** {@inheritDoc} */
    @Override
    public EntityEnricherListener getParticipantEnricherListener() {
        return this.minimalUpdater.getParticipantEnricherListener();
    }

    /** {@inheritDoc} */
    @Override
    public void setCvTermEnricher(CvTermEnricher<CvTerm> cvTermEnricher) {
        this.minimalUpdater.setCvTermEnricher(cvTermEnricher);
    }

    /** {@inheritDoc} */
    @Override
    public CvTermEnricher<CvTerm> getCvTermEnricher() {
        return this.minimalUpdater.getCvTermEnricher();
    }

    /** {@inheritDoc} */
    @Override
    public void setFeatureEnricher(FeatureEnricher<FeatureEvidence> featureEnricher) {
        this.minimalUpdater.setFeatureEnricher(featureEnricher);
    }

    /** {@inheritDoc} */
    @Override
    public FeatureEnricher<FeatureEvidence> getFeatureEnricher() {
        return this.minimalUpdater.getFeatureEnricher();
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractorEnricher(CompositeInteractorEnricher interactorEnricher) {
        this.minimalUpdater.setInteractorEnricher(interactorEnricher);
    }

    /** {@inheritDoc} */
    @Override
    public CompositeInteractorEnricher getInteractorEnricher() {
        return this.minimalUpdater.getInteractorEnricher();
    }

    /**
     * <p>Getter for the field <code>minimalUpdater</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalParticipantEvidenceUpdater} object.
     */
    protected MinimalParticipantEvidenceUpdater<P> getMinimalUpdater() {
        return minimalUpdater;
    }
}

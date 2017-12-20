package psidev.psi.mi.jami.enricher.impl.full;

import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalFeatureEvidenceEnricher;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.enricher.listener.FeatureEnricherListener;
import psidev.psi.mi.jami.enricher.listener.FeatureEvidenceEnricherListener;
import psidev.psi.mi.jami.enricher.util.EnricherUtils;
import psidev.psi.mi.jami.model.*;

/**
 * Provides full enrichment of feature evidence.
 *
 * - enrich full properties of feature. See FullFeatureEnricher
 * - enrich detection methods if cv term enricher is not null
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 13/08/13

 */
public class FullFeatureEvidenceEnricher extends FullFeatureEnricher<FeatureEvidence> {

    private MinimalFeatureEvidenceEnricher minimalEnricher;

    /**
     * <p>Constructor for FullFeatureEvidenceEnricher.</p>
     */
    public FullFeatureEvidenceEnricher(){
        super();
        this.minimalEnricher = new MinimalFeatureEvidenceEnricher();
    }

    /**
     * <p>Constructor for FullFeatureEvidenceEnricher.</p>
     *
     * @param minimalEnricher a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalFeatureEvidenceEnricher} object.
     */
    protected FullFeatureEvidenceEnricher(MinimalFeatureEvidenceEnricher minimalEnricher){
        super();
        this.minimalEnricher = minimalEnricher != null ? minimalEnricher : new MinimalFeatureEvidenceEnricher();
    }

    /** {@inheritDoc} */
    @Override
    public void processMinimalUpdates(FeatureEvidence objectToEnrich, FeatureEvidence objectSource) throws EnricherException {
        this.minimalEnricher.processMinimalUpdates(objectToEnrich, objectSource);
    }

    /** {@inheritDoc} */
    @Override
    public void setFeatureEnricherListener(FeatureEnricherListener<FeatureEvidence> featureEnricherListener) {
        this.minimalEnricher.setFeatureEnricherListener(featureEnricherListener);
    }

    /** {@inheritDoc} */
    @Override
    public FeatureEnricherListener<FeatureEvidence> getFeatureEnricherListener() {
        return this.minimalEnricher.getFeatureEnricherListener();
    }

    /** {@inheritDoc} */
    @Override
    public void setCvTermEnricher(CvTermEnricher cvTermEnricher) {
        this.minimalEnricher.setCvTermEnricher(cvTermEnricher);
    }

    /** {@inheritDoc} */
    @Override
    public CvTermEnricher getCvTermEnricher() {
        return this.minimalEnricher.getCvTermEnricher();
    }

    /** {@inheritDoc} */
    @Override
    public void onSequenceUpdate(Protein protein, String oldSequence) {
        this.minimalEnricher.onSequenceUpdate(protein, oldSequence);
    }

    /** {@inheritDoc} */
    @Override
    public void onShortNameUpdate(Protein interactor, String oldShortName) {
        this.minimalEnricher.onShortNameUpdate(interactor, oldShortName);
    }

    /** {@inheritDoc} */
    @Override
    public void onFullNameUpdate(Protein interactor, String oldFullName) {
        this.minimalEnricher.onFullNameUpdate(interactor, oldFullName);
    }

    /** {@inheritDoc} */
    @Override
    public void onOrganismUpdate(Protein interactor, Organism o) {
        this.minimalEnricher.onOrganismUpdate(interactor, o);
    }

    /** {@inheritDoc} */
    @Override
    public void onInteractorTypeUpdate(Protein interactor, CvTerm old) {
        this.minimalEnricher.onInteractorTypeUpdate(interactor, old);
    }

    /** {@inheritDoc} */
    @Override
    public void onAddedAlias(Protein o, Alias added) {
        this.minimalEnricher.onAddedAlias(o, added);
    }

    /** {@inheritDoc} */
    @Override
    public void onRemovedAlias(Protein o, Alias removed) {
        this.minimalEnricher.onRemovedAlias(o, removed);
    }

    /** {@inheritDoc} */
    @Override
    public void onAddedAnnotation(Protein o, Annotation added) {
        this.minimalEnricher.onAddedAnnotation(o, added);
    }

    /** {@inheritDoc} */
    @Override
    public void onRemovedAnnotation(Protein o, Annotation removed) {
        this.minimalEnricher.onRemovedAnnotation(o, removed);
    }

    /** {@inheritDoc} */
    @Override
    public void onAddedChecksum(Protein interactor, Checksum added) {
        this.minimalEnricher.onAddedChecksum(interactor, added);
    }

    /** {@inheritDoc} */
    @Override
    public void onRemovedChecksum(Protein interactor, Checksum removed) {
        this.minimalEnricher.onRemovedChecksum(interactor, removed);
    }

    /** {@inheritDoc} */
    @Override
    public void onAddedIdentifier(Protein o, Xref added) {
        this.minimalEnricher.onAddedIdentifier(o, added);
    }

    /** {@inheritDoc} */
    @Override
    public void onRemovedIdentifier(Protein o, Xref removed) {
        this.minimalEnricher.onRemovedIdentifier(o, removed);
    }

    /** {@inheritDoc} */
    @Override
    public void onAddedXref(Protein o, Xref added) {
        this.minimalEnricher.onAddedXref(o, added);
    }

    /** {@inheritDoc} */
    @Override
    public void onRemovedXref(Protein o, Xref removed) {
        this.minimalEnricher.onRemovedXref(o, removed);
    }

    /** {@inheritDoc} */
    @Override
    public void onEnrichmentComplete(Protein object, EnrichmentStatus status, String message) {
        this.minimalEnricher.onEnrichmentComplete(object, status, message);
    }

    /** {@inheritDoc} */
    @Override
    public void onEnrichmentError(Protein object, String message, Exception e) {
        this.minimalEnricher.onEnrichmentError(object, message, e);
    }

    /** {@inheritDoc} */
    @Override
    protected void processOtherProperties(FeatureEvidence featureToEnrich, FeatureEvidence objectSource) throws EnricherException {
        super.processOtherProperties(featureToEnrich, objectSource);

        // process parameters
        processParameters(featureToEnrich, objectSource);
    }

    /**
     * <p>processParameters.</p>
     *
     * @param featureToEnrich a {@link psidev.psi.mi.jami.model.FeatureEvidence} object.
     * @param objectSource a {@link psidev.psi.mi.jami.model.FeatureEvidence} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processParameters(FeatureEvidence featureToEnrich, FeatureEvidence objectSource) throws EnricherException{
        EnricherUtils.mergeParameters(featureToEnrich, objectSource.getParameters(), objectSource.getParameters(), false,
                getFeatureEnricherListener() instanceof FeatureEvidenceEnricherListener ? (psidev.psi.mi.jami.listener.ParametersChangeListener<FeatureEvidence>)getFeatureEnricherListener() : null);
    }

    /**
     * <p>Getter for the field <code>minimalEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalFeatureEvidenceEnricher} object.
     */
    protected MinimalFeatureEvidenceEnricher getMinimalEnricher() {
        return minimalEnricher;
    }
}

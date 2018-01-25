package psidev.psi.mi.jami.enricher.impl.minimal;

import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.enricher.listener.FeatureEnricherListener;
import psidev.psi.mi.jami.model.*;

import java.util.Collection;

/**
 * Provides minimal update of feature evidence.
 *
 * - update minimal properties of feature. See MinimalFeatureUpdater
 * - update detection methods if cv term enricher is not null
 * - Ignore all other properties of a feature
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 13/08/13

 */
public class MinimalFeatureEvidenceUpdater extends MinimalFeatureEvidenceEnricher {

    private MinimalFeatureUpdater<FeatureEvidence> minimalUpdater;

    /**
     * <p>Constructor for MinimalFeatureEvidenceUpdater.</p>
     */
    public MinimalFeatureEvidenceUpdater(){
        super();
        this.minimalUpdater = new MinimalFeatureUpdater<FeatureEvidence>();
    }

    /**
     * <p>Constructor for MinimalFeatureEvidenceUpdater.</p>
     *
     * @param minimalUpdater a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalFeatureUpdater} object.
     */
    protected MinimalFeatureEvidenceUpdater(MinimalFeatureUpdater<FeatureEvidence> minimalUpdater){
        this.minimalUpdater = minimalUpdater != null ? minimalUpdater : new MinimalFeatureUpdater<FeatureEvidence>();
    }

    /** {@inheritDoc} */
    @Override
    protected void processDetectionMethods(FeatureEvidence featureToEnrich, FeatureEvidence source) throws EnricherException {
        mergeDetectionMethods(featureToEnrich, featureToEnrich.getDetectionMethods(), source.getDetectionMethods(), true);
        processDetectionMethods(featureToEnrich);
    }

    /** {@inheritDoc} */
    @Override
    public void setFeatureEnricherListener(FeatureEnricherListener<FeatureEvidence> featureEnricherListener) {
        this.minimalUpdater.setFeatureEnricherListener(featureEnricherListener);
    }

    /** {@inheritDoc} */
    @Override
    public FeatureEnricherListener<FeatureEvidence> getFeatureEnricherListener() {
        return this.minimalUpdater.getFeatureEnricherListener();
    }

    /** {@inheritDoc} */
    @Override
    public void setCvTermEnricher(CvTermEnricher cvTermEnricher) {
        this.minimalUpdater.setCvTermEnricher(cvTermEnricher);
    }

    /** {@inheritDoc} */
    @Override
    public CvTermEnricher getCvTermEnricher() {
        return this.minimalUpdater.getCvTermEnricher();
    }

    /** {@inheritDoc} */
    @Override
    public void onSequenceUpdate(Protein protein, String oldSequence) {
        this.minimalUpdater.onSequenceUpdate(protein, oldSequence);
    }

    /** {@inheritDoc} */
    @Override
    public void onShortNameUpdate(Protein interactor, String oldShortName) {
        this.minimalUpdater.onShortNameUpdate(interactor, oldShortName);
    }

    /** {@inheritDoc} */
    @Override
    public void onFullNameUpdate(Protein interactor, String oldFullName) {
        this.minimalUpdater.onFullNameUpdate(interactor, oldFullName);
    }

    /** {@inheritDoc} */
    @Override
    public void onOrganismUpdate(Protein interactor, Organism o) {
        this.minimalUpdater.onOrganismUpdate(interactor, o);
    }

    /** {@inheritDoc} */
    @Override
    public void onInteractorTypeUpdate(Protein interactor, CvTerm old) {
        this.minimalUpdater.onInteractorTypeUpdate(interactor, old);
    }

    /** {@inheritDoc} */
    @Override
    public void onAddedAlias(Protein o, Alias added) {
        this.minimalUpdater.onAddedAlias(o, added);
    }

    /** {@inheritDoc} */
    @Override
    public void onRemovedAlias(Protein o, Alias removed) {
        this.minimalUpdater.onRemovedAlias(o, removed);
    }

    /** {@inheritDoc} */
    @Override
    public void onAddedAnnotation(Protein o, Annotation added) {
        this.minimalUpdater.onAddedAnnotation(o, added);
    }

    /** {@inheritDoc} */
    @Override
    public void onRemovedAnnotation(Protein o, Annotation removed) {
        this.minimalUpdater.onRemovedAnnotation(o, removed);
    }

    /** {@inheritDoc} */
    @Override
    public void onAddedChecksum(Protein interactor, Checksum added) {
        this.minimalUpdater.onAddedChecksum(interactor, added);
    }

    /** {@inheritDoc} */
    @Override
    public void onRemovedChecksum(Protein interactor, Checksum removed) {
        this.minimalUpdater.onRemovedChecksum(interactor, removed);
    }

    /** {@inheritDoc} */
    @Override
    public void onAddedIdentifier(Protein o, Xref added) {
        this.minimalUpdater.onAddedIdentifier(o, added);
    }

    /** {@inheritDoc} */
    @Override
    public void onRemovedIdentifier(Protein o, Xref removed) {
        this.minimalUpdater.onRemovedIdentifier(o, removed);
    }

    /** {@inheritDoc} */
    @Override
    public void onAddedXref(Protein o, Xref added) {
        this.minimalUpdater.onAddedXref(o, added);
    }

    /** {@inheritDoc} */
    @Override
    public void onRemovedXref(Protein o, Xref removed) {
        this.minimalUpdater.onRemovedXref(o, removed);
    }

    /** {@inheritDoc} */
    @Override
    protected boolean updateRangePositions() {
        return this.minimalUpdater.updateRangePositions();
    }

    /** {@inheritDoc} */
    @Override
    protected void onInvalidRange(FeatureEvidence feature, Range range, Collection<String> errorMessages) {
        this.minimalUpdater.onInvalidRange(feature, range, errorMessages);
    }

    /** {@inheritDoc} */
    @Override
    protected void onOutOfDateRange(FeatureEvidence feature, Range range, Collection<String> errorMessages, String oldSequence) {
        this.minimalUpdater.onOutOfDateRange(feature, range, errorMessages, oldSequence);
    }

    /** {@inheritDoc} */
    @Override
    public void onEnrichmentComplete(Protein object, EnrichmentStatus status, String message) {
        this.minimalUpdater.onEnrichmentComplete(object, status, message);
    }

    /** {@inheritDoc} */
    @Override
    public void onEnrichmentError(Protein object, String message, Exception e) {
        this.minimalUpdater.onEnrichmentError(object, message, e);
    }

    /**
     * <p>Getter for the field <code>minimalUpdater</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalFeatureUpdater} object.
     */
    protected MinimalFeatureUpdater<FeatureEvidence> getMinimalUpdater() {
        return minimalUpdater;
    }
}

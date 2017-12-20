package psidev.psi.mi.jami.enricher.impl.minimal;

import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.ExperimentEnricher;
import psidev.psi.mi.jami.enricher.OrganismEnricher;
import psidev.psi.mi.jami.enricher.PublicationEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.AbstractMIEnricher;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.enricher.listener.ExperimentEnricherListener;
import psidev.psi.mi.jami.model.Experiment;

import java.util.Collection;

/**
 * Provides minimal enrichment of experiment.
 *
 * - enrich publication using Publication enricher if not null. Will not override an existing publication with the
 * publication loaded with the fetched experiment
 * - enrich interaction detection method using CvTerm enricher if not null. Will not override an existing interaction detection method with the
 * interaction detection method loaded with the fetched interaction detection method
 * - enrich host organism using Organism enricher if not null. Will not override an existing host organism with the
 * host organism loaded with the fetched experiment
 *
 * It will ignore all other properties of a Experiment
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 13/08/13

 */
public class MinimalExperimentEnricher extends AbstractMIEnricher<Experiment> implements ExperimentEnricher{

    private PublicationEnricher publicationEnricher = null;
    private CvTermEnricher cvTermEnricher = null;
    private ExperimentEnricherListener listener = null;
    private OrganismEnricher organismEnricher = null;

    /**
     * <p>Constructor for MinimalExperimentEnricher.</p>
     */
    public MinimalExperimentEnricher(){

    }

    /**
     * <p>processOrganism.</p>
     *
     * @param experimentToEnrich a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processOrganism(Experiment experimentToEnrich) throws EnricherException {
        if( getOrganismEnricher() != null
                && experimentToEnrich.getHostOrganism() != null )
            getOrganismEnricher().enrich(experimentToEnrich.getHostOrganism());
    }

    /**
     * <p>processInteractionDetectionMethod.</p>
     *
     * @param experimentToEnrich a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processInteractionDetectionMethod(Experiment experimentToEnrich) throws EnricherException {
        if( getCvTermEnricher() != null
                && experimentToEnrich.getInteractionDetectionMethod() != null )
            getCvTermEnricher().enrich(experimentToEnrich.getInteractionDetectionMethod());
    }

    /**
     * <p>processPublication.</p>
     *
     * @param experimentToEnrich a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processPublication(Experiment experimentToEnrich) throws EnricherException {
        if( getPublicationEnricher() != null
                && experimentToEnrich.getPublication() != null )
            getPublicationEnricher().enrich(experimentToEnrich.getPublication());
    }

    /**
     * <p>processOtherProperties.</p>
     *
     * @param experimentToEnrich a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processOtherProperties(Experiment experimentToEnrich) throws EnricherException{
        // do nothing
    }

    /** {@inheritDoc} */
    public void enrich(Collection<Experiment> objects) throws EnricherException {
        if( objects == null )
            throw new IllegalArgumentException("Cannot enrich a null collection of experiments.");
        for(Experiment exp : objects){
            enrich(exp);
        }
    }

    /**
     * <p>enrich.</p>
     *
     * @param experimentToEnrich a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param objectSource a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void enrich(Experiment experimentToEnrich, Experiment objectSource) throws EnricherException {
        if (objectSource == null){
            enrich(experimentToEnrich);
        }
        else{
            processExperiment(experimentToEnrich, objectSource);

            if( getExperimentEnricherListener() != null )
                getExperimentEnricherListener().onEnrichmentComplete(experimentToEnrich , EnrichmentStatus.SUCCESS , "The experiment has been successfully enriched.");
        }
    }

    /**
     * <p>processExperiment.</p>
     *
     * @param experimentToEnrich a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param objectSource a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void processExperiment(Experiment experimentToEnrich, Experiment objectSource) throws EnricherException {
        processPublication(experimentToEnrich, objectSource);

        processInteractionDetectionMethod(experimentToEnrich, objectSource);

        processOrganism(experimentToEnrich, objectSource);

        processOtherProperties(experimentToEnrich, objectSource);
    }

    /** {@inheritDoc} */
    @Override
    public Experiment find(Experiment objectToEnrich) throws EnricherException {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected void onEnrichedVersionNotFound(Experiment experimentToEnrich) throws EnricherException {
        processExperiment(experimentToEnrich);

        if( getExperimentEnricherListener() != null )
            getExperimentEnricherListener().onEnrichmentComplete(experimentToEnrich , EnrichmentStatus.SUCCESS , "The experiment has been successfully enriched.");
    }

    /**
     * <p>processExperiment.</p>
     *
     * @param experimentToEnrich a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void processExperiment(Experiment experimentToEnrich) throws EnricherException {
        processPublication(experimentToEnrich);

        processInteractionDetectionMethod(experimentToEnrich);

        processOrganism(experimentToEnrich);

        processOtherProperties(experimentToEnrich);
    }

    /**
     * <p>processOtherProperties.</p>
     *
     * @param experimentToEnrich a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param objectSource a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processOtherProperties(Experiment experimentToEnrich, Experiment objectSource) throws EnricherException{

        processOtherProperties(experimentToEnrich);
    }

    /**
     * <p>processInteractionDetectionMethod.</p>
     *
     * @param experimentToEnrich a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param objectSource a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processInteractionDetectionMethod(Experiment experimentToEnrich, Experiment objectSource) throws EnricherException {
        // nothing to do
        processInteractionDetectionMethod(experimentToEnrich);
    }

    /**
     * <p>processPublication.</p>
     *
     * @param experimentToEnrich a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param objectSource a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processPublication(Experiment experimentToEnrich, Experiment objectSource) throws EnricherException {
        if (experimentToEnrich.getPublication() == null && objectSource.getPublication() != null){
            experimentToEnrich.setPublication(objectSource.getPublication());
            if (getExperimentEnricherListener() != null){
                getExperimentEnricherListener().onPublicationUpdate(experimentToEnrich, null);
            }
        }
        processPublication(experimentToEnrich);
    }

    /**
     * <p>processOrganism.</p>
     *
     * @param experimentToEnrich a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param objectSource a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processOrganism(Experiment experimentToEnrich, Experiment objectSource) throws EnricherException {
        if (experimentToEnrich.getHostOrganism() == null && objectSource.getHostOrganism() != null){
            experimentToEnrich.setHostOrganism(objectSource.getHostOrganism());
            if (getExperimentEnricherListener() != null){
                getExperimentEnricherListener().onHostOrganismUpdate(experimentToEnrich, null);
            }
        }
        processOrganism(experimentToEnrich);
    }

    /**
     * {@inheritDoc}
     *
     * Sets the subEnricher for CvTerms. Can be null.
     */
    public void setCvTermEnricher(CvTermEnricher cvTermEnricher) {
        this.cvTermEnricher = cvTermEnricher;
    }
    /**
     * Gets the subEnricher for CvTerms. Can be null.
     *
     * @return  The CvTerm enricher which is being used.
     */
    public CvTermEnricher getCvTermEnricher() {
        return cvTermEnricher;
    }

    /**
     * Sets the subEnricher for publications. Can be null.
     *
     * @return  The publications enricher which is being used.
     */
    public PublicationEnricher getPublicationEnricher() {
        return publicationEnricher;
    }
    /**
     * {@inheritDoc}
     *
     * Gets the publications for organisms. Can be null.
     */
    public void setPublicationEnricher(PublicationEnricher publicationEnricher) {
        this.publicationEnricher = publicationEnricher;
    }

    /**
     * Gets current ExperimentEnricherListener. Can be null.
     *
     * @return      The listener which is currently beign used.
     */
    public ExperimentEnricherListener getExperimentEnricherListener() {
        return listener;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the ExperimentEnricherListener. Can be null.
     */
    public void setExperimentEnricherListener(ExperimentEnricherListener listener) {
        this.listener = listener;
    }

    /**
     * <p>Getter for the field <code>organismEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.OrganismEnricher} object.
     */
    public OrganismEnricher getOrganismEnricher() {
        return organismEnricher;
    }

    /** {@inheritDoc} */
    public void setOrganismEnricher(OrganismEnricher organismEnricher) {
        this.organismEnricher = organismEnricher;
    }
}

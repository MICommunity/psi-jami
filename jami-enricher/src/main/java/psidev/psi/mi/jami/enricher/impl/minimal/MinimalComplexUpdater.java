package psidev.psi.mi.jami.enricher.impl.minimal;

import psidev.psi.mi.jami.bridges.fetcher.InteractorFetcher;
import psidev.psi.mi.jami.enricher.ComplexEnricher;
import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.OrganismEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.listener.InteractorEnricherListener;
import psidev.psi.mi.jami.model.Complex;

/**
 * Minimal updater for complexes
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 13/08/13

 */
public class MinimalComplexUpdater extends MinimalModelledInteractionUpdater<Complex> implements ComplexEnricher {

    private MinimalInteractorBaseUpdater<Complex> interactorEnricher = null;

    /**
     * <p>Constructor for MinimalComplexUpdater.</p>
     */
    public MinimalComplexUpdater(){
        super();
        this.interactorEnricher = new MinimalInteractorBaseUpdater<Complex>();
    }

    /**
     * <p>Constructor for MinimalComplexUpdater.</p>
     *
     * @param interactorEnricher a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalInteractorBaseUpdater} object.
     */
    public MinimalComplexUpdater(MinimalInteractorBaseUpdater<Complex> interactorEnricher){
        super();
        this.interactorEnricher = interactorEnricher != null ? interactorEnricher : new MinimalInteractorBaseUpdater<Complex>();
    }

    /**
     * Strategy for the Interaction enrichment.
     * This method can be overwritten to change how the interaction is enriched.
     *
     * @param interactionToEnrich   The interaction to be enriched.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processOtherProperties(Complex interactionToEnrich) throws EnricherException {
        super.processOtherProperties(interactionToEnrich);

        // interactor properties

        // Interactor type
        this.interactorEnricher.processInteractorType(interactionToEnrich, null);

        // Organism
        this.interactorEnricher.processOrganism(interactionToEnrich, null);
    }

    /** {@inheritDoc} */
    @Override
    protected void processOtherProperties(Complex objectToEnrich, Complex objectSource) throws EnricherException {
        super.processOtherProperties(objectToEnrich, objectSource);

        // interactor properties

        // Interactor type
        this.interactorEnricher.processInteractorType(objectToEnrich, objectSource);

        // Organism
        this.interactorEnricher.processOrganism(objectToEnrich, objectSource);

        // FULL NAME
        this.interactorEnricher.processFullName(objectToEnrich, objectSource);

        //ALIASES
        this.interactorEnricher.processAliases(objectToEnrich, objectSource);
    }

    /**
     * <p>getInteractorFetcher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.bridges.fetcher.InteractorFetcher} object.
     */
    public InteractorFetcher<Complex> getInteractorFetcher() {
        return this.interactorEnricher.getInteractorFetcher();
    }

    /** {@inheritDoc} */
    public void setListener(InteractorEnricherListener<Complex> listener) {
        this.interactorEnricher.setListener(listener);
    }

    /**
     * <p>getListener.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.listener.InteractorEnricherListener} object.
     */
    public InteractorEnricherListener<Complex> getListener() {
        return this.interactorEnricher.getListener();
    }

    /** {@inheritDoc} */
    public void setCvTermEnricher(CvTermEnricher cvTermEnricher) {
        this.interactorEnricher.setCvTermEnricher(cvTermEnricher);
    }

    /** {@inheritDoc} */
    public void setOrganismEnricher(OrganismEnricher organismEnricher) {
        this.interactorEnricher.setOrganismEnricher(organismEnricher);
    }

    /**
     * <p>getOrganismEnricher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.OrganismEnricher} object.
     */
    public OrganismEnricher getOrganismEnricher() {
        return this.interactorEnricher.getOrganismEnricher();
    }

    /**
     * <p>Getter for the field <code>interactorEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalInteractorBaseUpdater} object.
     */
    protected MinimalInteractorBaseUpdater<Complex> getInteractorEnricher() {
        return interactorEnricher;
    }
}

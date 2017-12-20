package psidev.psi.mi.jami.enricher.impl.full;

import psidev.psi.mi.jami.bridges.fetcher.InteractorFetcher;
import psidev.psi.mi.jami.enricher.ComplexEnricher;
import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.OrganismEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.listener.InteractorEnricherListener;
import psidev.psi.mi.jami.model.Complex;

/**
 * Full enricher for complexes
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 13/08/13

 */
public class FullComplexEnricher extends FullModelledInteractionEnricher<Complex> implements ComplexEnricher {

    private FullInteractorBaseEnricher<Complex> interactorEnricher = null;

    /**
     * <p>Constructor for FullComplexEnricher.</p>
     */
    public FullComplexEnricher(){
        super();
        this.interactorEnricher = new FullInteractorBaseEnricher<Complex>();
    }

    /**
     * <p>Constructor for FullComplexEnricher.</p>
     *
     * @param interactorEnricher a {@link psidev.psi.mi.jami.enricher.impl.full.FullInteractorBaseEnricher} object.
     */
    protected FullComplexEnricher(FullInteractorBaseEnricher<Complex> interactorEnricher){
        super();
        this.interactorEnricher = interactorEnricher != null ? interactorEnricher : new FullInteractorBaseEnricher<Complex>();
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
     * @return a {@link psidev.psi.mi.jami.enricher.impl.full.FullInteractorBaseEnricher} object.
     */
    protected FullInteractorBaseEnricher<Complex> getInteractorEnricher() {
        return interactorEnricher;
    }
}

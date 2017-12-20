package psidev.psi.mi.jami.enricher.impl.minimal;


import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.OrganismFetcher;
import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.OrganismEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.AbstractMIEnricher;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.enricher.listener.OrganismEnricherListener;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;

/**
 * Provides minimum enrichment of a Organism.
 *
 * - enrich common name if not set. Does not override it with fetched organism common name if already set.
 * - enrich scientific name if not set. Does not override it with fetched organism scientific name if already set.
 *
 * The organism fetcher is required for enriching organism
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 22/05/13

 */
public class MinimalOrganismEnricher extends AbstractMIEnricher<Organism>
        implements OrganismEnricher {

    private int retryCount = 5;

    private OrganismFetcher fetcher = null;
    private OrganismEnricherListener listener = null;

    /**
     * <p>Constructor for MinimalOrganismEnricher.</p>
     *
     * @param organismFetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OrganismFetcher} object.
     */
    public MinimalOrganismEnricher(OrganismFetcher organismFetcher) {
        super();
        if (organismFetcher == null){
            throw new IllegalArgumentException("The organism fetcher is required");
        }
        this.fetcher = organismFetcher;
    }

    /**
     * <p>Getter for the field <code>retryCount</code>.</p>
     *
     * @return a int.
     */
    public int getRetryCount() {
        return retryCount;
    }

    /**
     * <p>Setter for the field <code>retryCount</code>.</p>
     *
     * @param retryCount a int.
     */
    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    /**
     * <p>getOrganismFetcher.</p>
     *
     * @return  The current organism fetcher
     */
    public OrganismFetcher getOrganismFetcher() {
        return fetcher;
    }


    /** {@inheritDoc} */
    public void setOrganismEnricherListener(OrganismEnricherListener organismEnricherListener) {
        this.listener = organismEnricherListener;
    }

    /** {@inheritDoc} */
    public void setCvTermEnricher(CvTermEnricher<CvTerm> enricher) {
        // nothing to do
    }

    /**
     * <p>getOrganismEnricherListener.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.listener.OrganismEnricherListener} object.
     */
    public OrganismEnricherListener getOrganismEnricherListener() {
        return listener;
    }

    /*
     * Cv enricher is not needed here
      */
    /**
     * <p>getCvTermEnricher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.CvTermEnricher} object.
     */
    public CvTermEnricher<CvTerm> getCvTermEnricher() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Organism find(Organism objectToEnrich) throws EnricherException {
        Organism organismFetched = null;
        try {
            organismFetched = getOrganismFetcher().fetchByTaxID(objectToEnrich.getTaxId());
            return organismFetched;
        } catch (BridgeFailedException e) {
            int index = 0;
            while(index < retryCount){
                try {
                    organismFetched = getOrganismFetcher().fetchByTaxID(objectToEnrich.getTaxId());
                    return organismFetched;
                } catch (BridgeFailedException ee) {
                    ee.printStackTrace();
                }
                index++;
            }
            throw new EnricherException("Retried "+retryCount+" times", e);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void onEnrichedVersionNotFound(Organism objectToEnrich) throws EnricherException {
        if(getOrganismEnricherListener() != null)
            getOrganismEnricherListener().onEnrichmentComplete(objectToEnrich, EnrichmentStatus.FAILED, "The organism does not exist.");
    }

    /** {@inheritDoc} */
    @Override
    public void enrich(Organism organismToEnrich, Organism organismFetched) throws EnricherException {

        processTaxid(organismToEnrich, organismFetched);

        // Scientific name
        processScientificName(organismToEnrich, organismFetched);

        //Common name
        processCommonName(organismToEnrich, organismFetched);

        //process other info
        processOtherProperties(organismToEnrich, organismFetched);

        if(listener != null)
            listener.onEnrichmentComplete(organismToEnrich, EnrichmentStatus.SUCCESS, "Organism successfully enriched.");
    }

    /**
     * <p>processTaxid.</p>
     *
     * @param organismToEnrich a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param organismFetched a {@link psidev.psi.mi.jami.model.Organism} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processTaxid(Organism organismToEnrich, Organism organismFetched) throws EnricherException{
        // nothing to do
    }

    /**
     * <p>processOtherProperties.</p>
     *
     * @param organismToEnrich a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param organismFetched a {@link psidev.psi.mi.jami.model.Organism} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processOtherProperties(Organism organismToEnrich, Organism organismFetched) throws EnricherException {
        // do nothing
    }

    /**
     * <p>processCommonName.</p>
     *
     * @param organismToEnrich a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param organismFetched a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    protected void processCommonName(Organism organismToEnrich, Organism organismFetched) {
        if(organismToEnrich.getCommonName() == null
                && organismFetched.getCommonName() != null){
            organismToEnrich.setCommonName(organismFetched.getCommonName());
            if (getOrganismEnricherListener() != null)
                getOrganismEnricherListener().onCommonNameUpdate(organismToEnrich, null);
        }
    }

    /**
     * <p>processScientificName.</p>
     *
     * @param organismToEnrich a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param organismFetched a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    protected void processScientificName(Organism organismToEnrich, Organism organismFetched) {
        //Scientific name
        if(organismToEnrich.getScientificName() == null
                && organismFetched.getScientificName() != null){
            organismToEnrich.setScientificName(organismFetched.getScientificName());
            if (getOrganismEnricherListener() != null)
                getOrganismEnricherListener().onScientificNameUpdate(organismToEnrich, null);
        }
    }
}

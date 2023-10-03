package psidev.psi.mi.jami.enricher.impl.minimal;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.GeneFetcher;
import psidev.psi.mi.jami.bridges.fetcher.InteractorFetcher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.AbstractInteractorEnricher;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.model.Gene;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.comparator.organism.OrganismTaxIdComparator;

import java.util.Collection;

/**
 * A basic minimal enricher for genes.
 * <p>
 * See description of minimal enrichment in AbstractInteractorEnricher.
 * <p>
 * The GeneFetcher is required for enriching genes.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 04/09/13
 */
public class MinimalGeneEnricher extends AbstractInteractorEnricher<Gene> {

    protected GeneFetcher ensemblFetcher;

    public GeneFetcher getEnsemblFetcher() {
        return ensemblFetcher == null ? getInteractorFetcher() : ensemblFetcher;
    }

    public void setEnsemblFetcher(GeneFetcher ensemblFetcher) {
        this.ensemblFetcher = ensemblFetcher;
    }

    /**
     * <p>Constructor for MinimalGeneEnricher.</p>
     *
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.GeneFetcher} object.
     */
    public MinimalGeneEnricher(GeneFetcher fetcher) {
        super(fetcher);
        if (fetcher == null) throw new IllegalArgumentException("The gene enricher needs a non null fetcher");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GeneFetcher getInteractorFetcher() {
        return (GeneFetcher) super.getInteractorFetcher();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Gene find(Gene geneToEnrich) throws EnricherException {
        int taxid = -3;
        if (geneToEnrich.getOrganism() != null) {
            taxid = geneToEnrich.getOrganism().getTaxId();
        }

        Gene geneFetched = null;

        if (geneToEnrich.getEnsembl() != null)
            geneFetched = fetchGeneByIdentifier(getEnsemblFetcher(), geneToEnrich, geneToEnrich.getEnsembl(), taxid);

        if (geneFetched == null && geneToEnrich.getRefseq() != null)
            geneFetched = fetchGeneByIdentifier(getInteractorFetcher(), geneToEnrich, geneToEnrich.getRefseq(), taxid);

        if (geneFetched == null && geneToEnrich.getEnsemblGenome() != null)
            geneFetched = fetchGeneByIdentifier(getInteractorFetcher(), geneToEnrich, geneToEnrich.getEnsemblGenome(), taxid);

        return geneFetched;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEnrichedVersionNotFound(Gene objectToEnrich) throws EnricherException {
        if (getListener() != null)
            getListener().onEnrichmentComplete(
                    objectToEnrich, EnrichmentStatus.FAILED,
                    "Could not fetch a gene with the provided identifier [" + objectToEnrich.getPreferredIdentifier() + "].");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isFullEnrichment() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCompletedEnrichment(Gene objectToEnrich) {
        if (getListener() != null)
            getListener().onEnrichmentComplete(
                    objectToEnrich, EnrichmentStatus.SUCCESS, "The gene [" + objectToEnrich.getPreferredIdentifier() + "] has been successfully enriched.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onInteractorCheckFailure(Gene objectToEnrich, Gene fetchedObject) throws EnricherException {
        if (getListener() != null)
            getListener().onEnrichmentComplete(
                    objectToEnrich, EnrichmentStatus.FAILED, "Cannot enrich the gene [" + objectToEnrich.getPreferredIdentifier() + "] because the interactor type is not a gene type or we have a mismatch between the gene taxid to enrich and the fetched gene taxid.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean canEnrichInteractor(Gene entityToEnrich, Gene fetchedEntity) throws EnricherException {
        if (fetchedEntity == null) {
            onEnrichedVersionNotFound(entityToEnrich);
            return false;
        }
        // if the interactor type is not a valid bioactive entity interactor type, we cannot enrich
        if (entityToEnrich.getInteractorType() != null &&
                !CvTermUtils.isCvTerm(entityToEnrich.getInteractorType(), Gene.GENE_MI, Gene.GENE)
                && !CvTermUtils.isCvTerm(entityToEnrich.getInteractorType(), Interactor.UNKNOWN_INTERACTOR_MI, Interactor.UNKNOWN_INTERACTOR)) {
            return false;
        }

        // if the organism is different, we cannot enrich
        if (entityToEnrich.getOrganism() != null && fetchedEntity.getOrganism() != null &&
                !OrganismTaxIdComparator.areEquals(entityToEnrich.getOrganism(), fetchedEntity.getOrganism())) {
            return false;
        }

        return true;
    }

    private Gene fetchGeneByIdentifier(GeneFetcher fetcher, Gene gene, String identifier, int taxid) throws EnricherException {
        Collection<Gene> results;
        try {
            results = fetcher.fetchByIdentifier(identifier, taxid);
            if (results.size() == 1) {
                return results.iterator().next();
            } else if (!results.isEmpty()) {
                if (getListener() != null) {
                    getListener().onEnrichmentError(gene, "The identifier [" + identifier + "] and taxid " + taxid + " can match " +
                            results.size() + " genes and it is not possible to enrich with multiple entries", new EnricherException("Multiple gene entries found for " + identifier + " and taxid " + taxid));
                }
                return null;
            } else {
                return null;
            }
        } catch (BridgeFailedException e) {
            int index = 0;
            while (index < getRetryCount()) {
                try {
                    results = fetcher.fetchByIdentifier(identifier, taxid);
                    if (results.size() == 1) {
                        return results.iterator().next();
                    } else if (!results.isEmpty()) {
                        if (getListener() != null) {
                            getListener().onEnrichmentError(gene, "The identifier [" + identifier + "] and taxid " + taxid + " can match " +
                                    results.size() + " genes and it is not possible to enrich with multiple entries", new EnricherException("Multiple gene entries found for [" + identifier + "] and taxid " + taxid));
                        }
                        return null;
                    } else {
                        return null;
                    }
                } catch (BridgeFailedException ee) {
                    ee.printStackTrace();
                    index++;
                }
            }
            throw new EnricherException("Retried " + getRetryCount() + " times to connect to the gene fetcher", e);
        }
    }
}

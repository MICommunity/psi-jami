package psidev.psi.mi.jami.enricher.impl.minimal;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.NucleicAcidFetcher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.AbstractInteractorEnricher;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.NucleicAcid;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.comparator.organism.OrganismTaxIdComparator;

import java.util.Collection;

/**
 * A basic minimal enricher for nucleic acids.
 * <p>
 * See description of minimal enrichment in AbstractInteractorEnricher.
 * <p>
 * The NucleicAcidFetcher is required for enriching nucleic acids.
 * The EnsemblNucleicAcidFetcher is required for enriching ensembl identifiers
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 04/09/13
 */
public class MinimalNucleicAcidEnricher extends AbstractInteractorEnricher<NucleicAcid> {

    public static final String RNA_CENTRAL_MI = "MI:1357";
    public static final String RNA_CENTRAL = "RNACentral";
    public static final String ENSEMBL_MI = "MI:0476";
    public static final String ENSEMBL = "ensembl";
    protected NucleicAcidFetcher ensemblFetcher;

    public NucleicAcidFetcher getEnsemblFetcher() {
        return ensemblFetcher == null ? getInteractorFetcher() : ensemblFetcher;
    }

    public void setEnsemblFetcher(NucleicAcidFetcher ensemblFetcher) {
        this.ensemblFetcher = ensemblFetcher;
    }

    /**
     * <p>Constructor for MinimalNucleicAcidEnricher.</p>
     *
     * @param fetcher a {@link NucleicAcidFetcher} object.
     */
    public MinimalNucleicAcidEnricher(NucleicAcidFetcher fetcher) {
        super(fetcher);
        if (fetcher == null) throw new IllegalArgumentException("The nucleic acid enricher needs a non null fetcher");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NucleicAcidFetcher getInteractorFetcher() {
        return (NucleicAcidFetcher) super.getInteractorFetcher();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NucleicAcid find(NucleicAcid objectToEnrich) throws EnricherException {
        Xref id = objectToEnrich.getPreferredIdentifier();
        if (CvTermUtils.isCvTerm(id.getDatabase(), RNA_CENTRAL_MI, RNA_CENTRAL)) {
            return fetchNAByIdentifier(getInteractorFetcher(), objectToEnrich, id.getId());
        } else if (CvTermUtils.isCvTerm(id.getDatabase(), ENSEMBL_MI, ENSEMBL)) {
            return fetchNAByIdentifier(getEnsemblFetcher(), objectToEnrich, id.getId());
        }
        return null;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEnrichedVersionNotFound(NucleicAcid objectToEnrich) throws EnricherException {
        if (getListener() != null)
            getListener().onEnrichmentComplete(
                    objectToEnrich, EnrichmentStatus.FAILED,
                    "Could not fetch a nucleic acid with the provided identifier [" + objectToEnrich.getPreferredIdentifier() + "].");
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
    protected void onCompletedEnrichment(NucleicAcid objectToEnrich) {
        if (getListener() != null)
            getListener().onEnrichmentComplete(
                    objectToEnrich, EnrichmentStatus.SUCCESS, "The nucleic acid [" + objectToEnrich.getPreferredIdentifier() + "] has been successfully enriched.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onInteractorCheckFailure(NucleicAcid objectToEnrich, NucleicAcid fetchedObject) throws EnricherException {
        if (getListener() != null)
            getListener().onEnrichmentComplete(
                    objectToEnrich, EnrichmentStatus.FAILED, "Cannot enrich the nucleic acid [" + objectToEnrich.getPreferredIdentifier() + "] because the interactor type is not a nucleic acid type or we have a mismatch between the nucleic acid taxid to enrich and the fetched nucleic acid taxid.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean canEnrichInteractor(NucleicAcid entityToEnrich, NucleicAcid fetchedEntity) throws EnricherException {
        if (fetchedEntity == null) {
            onEnrichedVersionNotFound(entityToEnrich);
            return false;
        }
        // if the interactor type is not a valid bioactive entity interactor type, we cannot enrich
        if (entityToEnrich.getInteractorType() != null &&
                !CvTermUtils.isCvTerm(entityToEnrich.getInteractorType(), NucleicAcid.NULCEIC_ACID_MI, NucleicAcid.NULCEIC_ACID)
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

    private NucleicAcid fetchNAByIdentifier(NucleicAcidFetcher fetcher, NucleicAcid nucleicAcid, String identifier) throws EnricherException {
        Collection<NucleicAcid> results;
        try {
            results = fetcher.fetchByIdentifier(identifier);
            if (results.size() == 1) {
                return results.iterator().next();
            } else if (!results.isEmpty()) {
                if (getListener() != null) {
                    getListener().onEnrichmentError(nucleicAcid, "The identifier [" + identifier + "] can match " +
                            results.size() + " nucleic acids and it is not possible to enrich with multiple entries", new EnricherException("Multiple nucleicAcid entries found for " + identifier));
                }
                return null;
            } else {
                return null;
            }
        } catch (BridgeFailedException e) {
            int index = 0;
            while (index < getRetryCount()) {
                try {
                    results = fetcher.fetchByIdentifier(identifier);
                    if (results.size() == 1) {
                        return results.iterator().next();
                    } else if (!results.isEmpty()) {
                        if (getListener() != null) {
                            getListener().onEnrichmentError(nucleicAcid, "The identifier [" + identifier + "] can match " +
                                    results.size() + " nucleic acids and it is not possible to enrich with multiple entries", new EnricherException("Multiple nucleicAcid entries found for [" + identifier + "]"));
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
            throw new EnricherException("Retried " + getRetryCount() + " times to connect to the nucleicAcid fetcher", e);
        }
    }
}

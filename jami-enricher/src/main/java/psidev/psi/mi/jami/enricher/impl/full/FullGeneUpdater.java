package psidev.psi.mi.jami.enricher.impl.full;

import psidev.psi.mi.jami.bridges.fetcher.GeneFetcher;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalGeneUpdater;

/**
 * A full updater for genes.
 *
 * See description of full update in AbstractInteractorEnricher.
 *
 * The GeneFetcher is required for enriching genes.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 04/09/13

 */
public class FullGeneUpdater extends MinimalGeneUpdater {

    /**
     * <p>Constructor for FullGeneUpdater.</p>
     *
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.GeneFetcher} object.
     */
    public FullGeneUpdater(GeneFetcher fetcher) {
        super(fetcher);
    }

    /** {@inheritDoc} */
    @Override
    protected boolean isFullEnrichment() {
        return true;
    }
}

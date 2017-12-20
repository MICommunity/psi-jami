package psidev.psi.mi.jami.enricher.impl.full;

import psidev.psi.mi.jami.bridges.fetcher.InteractorFetcher;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalInteractorBaseEnricher;
import psidev.psi.mi.jami.model.Interactor;

/**
 * Full interactor enricher
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/01/14</pre>
 */
public class FullInteractorBaseEnricher<T extends Interactor> extends MinimalInteractorBaseEnricher<T> {

    /**
     * <p>Constructor for FullInteractorBaseEnricher.</p>
     */
    public FullInteractorBaseEnricher() {
        super();
    }

    /**
     * <p>Constructor for FullInteractorBaseEnricher.</p>
     *
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.InteractorFetcher} object.
     */
    public FullInteractorBaseEnricher(InteractorFetcher<T> fetcher) {
        super(fetcher);
    }

    /** {@inheritDoc} */
    @Override
    protected boolean isFullEnrichment() {
        return true;
    }
}

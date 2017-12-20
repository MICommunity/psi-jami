package psidev.psi.mi.jami.enricher.impl.full;

import psidev.psi.mi.jami.bridges.fetcher.InteractorFetcher;
import psidev.psi.mi.jami.enricher.impl.AbstractInteractorUpdater;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalInteractorBaseEnricher;
import psidev.psi.mi.jami.model.Interactor;

/**
 * Full updater of interactor
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/01/14</pre>
 */
public class FullInteractorBaseUpdater<T extends Interactor> extends AbstractInteractorUpdater<T> {
    /**
     * <p>Constructor for FullInteractorBaseUpdater.</p>
     */
    public FullInteractorBaseUpdater() {
        super(new MinimalInteractorBaseEnricher<T>());
    }

    /**
     * <p>Constructor for FullInteractorBaseUpdater.</p>
     *
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.InteractorFetcher} object.
     */
    public FullInteractorBaseUpdater(InteractorFetcher<T> fetcher) {
        super(new MinimalInteractorBaseEnricher<T>(fetcher));
    }

    /**
     * <p>Constructor for FullInteractorBaseUpdater.</p>
     *
     * @param enricher a {@link psidev.psi.mi.jami.enricher.impl.full.FullInteractorBaseEnricher} object.
     */
    protected FullInteractorBaseUpdater(FullInteractorBaseEnricher<T> enricher) {
        super(enricher);
    }

    /** {@inheritDoc} */
    @Override
    protected boolean isFullEnrichment() {
        return true;
    }
}

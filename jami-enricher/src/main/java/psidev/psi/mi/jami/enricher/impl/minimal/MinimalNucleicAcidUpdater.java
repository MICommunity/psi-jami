package psidev.psi.mi.jami.enricher.impl.minimal;

import psidev.psi.mi.jami.bridges.fetcher.NucleicAcidFetcher;
import psidev.psi.mi.jami.enricher.impl.AbstractInteractorEnricher;
import psidev.psi.mi.jami.enricher.impl.AbstractInteractorUpdater;
import psidev.psi.mi.jami.model.Gene;
import psidev.psi.mi.jami.model.NucleicAcid;

/**
 * A basic minimal updater for nucleic acids.
 * <p>
 * See description of minimal update in AbstractInteractorUpdater.
 * <p>
 * The NucleicAcidFetcher is required for enriching nucleic acids.
 * The EnsemblNucleicAcidFetcher is required for enriching ensembl identifiers
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 04/09/13

 */
public class MinimalNucleicAcidUpdater extends AbstractInteractorUpdater<NucleicAcid> {

    public NucleicAcidFetcher getEnsemblFetcher() {
        return ((MinimalNucleicAcidEnricher) getInteractorEnricher()).getEnsemblFetcher();
    }

    public void setEnsemblFetcher(NucleicAcidFetcher ensemblFetcher) {
        ((MinimalNucleicAcidEnricher) getInteractorEnricher()).setEnsemblFetcher(ensemblFetcher);
    }

    /**
     * <p>Constructor for MinimalGeneUpdater.</p>
     *
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.GeneFetcher} object.
     */
    public MinimalNucleicAcidUpdater(NucleicAcidFetcher fetcher) {
        super(new MinimalNucleicAcidEnricher(fetcher));
    }

    /**
     * <p>Constructor for MinimalGeneUpdater.</p>
     *
     * @param interactorEnricher a {@link psidev.psi.mi.jami.enricher.impl.AbstractInteractorEnricher} object.
     */
    protected MinimalNucleicAcidUpdater(AbstractInteractorEnricher<NucleicAcid> interactorEnricher) {
        super(interactorEnricher);
    }
}

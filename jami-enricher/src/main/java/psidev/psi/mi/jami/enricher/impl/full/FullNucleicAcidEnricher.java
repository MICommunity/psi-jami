package psidev.psi.mi.jami.enricher.impl.full;

import psidev.psi.mi.jami.bridges.fetcher.GeneFetcher;
import psidev.psi.mi.jami.bridges.fetcher.NucleicAcidFetcher;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalGeneEnricher;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalNucleicAcidEnricher;
import psidev.psi.mi.jami.enricher.listener.NucleicAcidEnricherListener;
import psidev.psi.mi.jami.enricher.listener.ProteinEnricherListener;
import psidev.psi.mi.jami.model.NucleicAcid;
import psidev.psi.mi.jami.model.Protein;

/**
 * A full enricher for genes.
 *
 * See description of full enrichment in AbstractInteractorEnricher.
 *
 * The GeneFetcher is required for enriching genes.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 04/09/13

 */
public class FullNucleicAcidEnricher extends MinimalNucleicAcidEnricher {

    /**
     * <p>Constructor for FullGeneEnricher.</p>
     *
     * @param fetcher a {@link GeneFetcher} object.
     */
    public FullNucleicAcidEnricher(NucleicAcidFetcher fetcher) {
        super(fetcher);
    }

    /** {@inheritDoc} */
    @Override
    protected boolean isFullEnrichment() {
        return true;
    }

    @Override
    protected void processOtherProperties(NucleicAcid objectToEnrich, NucleicAcid fetched) {

        // sequence
        if (objectToEnrich.getSequence() == null && fetched.getSequence() != null){
            objectToEnrich.setSequence(fetched.getSequence());
            if (getListener() instanceof NucleicAcidEnricherListener){
                ((NucleicAcidEnricherListener)getListener()).onSequenceUpdate(objectToEnrich, null);
            }
        }
    }
}

package psidev.psi.mi.jami.enricher.impl.full;

import psidev.psi.mi.jami.bridges.fetcher.GeneFetcher;
import psidev.psi.mi.jami.bridges.fetcher.NucleicAcidFetcher;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalNucleicAcidUpdater;
import psidev.psi.mi.jami.enricher.listener.NucleicAcidEnricherListener;
import psidev.psi.mi.jami.model.NucleicAcid;

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
public class FullNucleicAcidUpdater extends MinimalNucleicAcidUpdater {

    /**
     * <p>Constructor for FullGeneUpdater.</p>
     *
     * @param fetcher a {@link GeneFetcher} object.
     */
    public FullNucleicAcidUpdater(NucleicAcidFetcher fetcher) {
        super(fetcher);
    }

    /** {@inheritDoc} */
    @Override
    protected boolean isFullEnrichment() {
        return true;
    }

    @Override
    protected void processOtherProperties(NucleicAcid toEnrich, NucleicAcid fetched) {

        // sequence
        if ((fetched.getSequence() != null && !fetched.getSequence().equalsIgnoreCase(toEnrich.getSequence())
                || (fetched.getSequence() == null && toEnrich.getSequence() != null))){
            String oldSeq = toEnrich.getSequence();
            toEnrich.setSequence(fetched.getSequence());
            if (getListener() instanceof NucleicAcidEnricherListener){
                ((NucleicAcidEnricherListener)getListener()).onSequenceUpdate(toEnrich, oldSeq);
            }
        }
    }
}

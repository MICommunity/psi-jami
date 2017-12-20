package psidev.psi.mi.jami.enricher.impl.full;

import psidev.psi.mi.jami.bridges.fetcher.PublicationFetcher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalCuratedPublicationEnricher;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalPublicationEnricher;
import psidev.psi.mi.jami.model.CurationDepth;
import psidev.psi.mi.jami.model.Publication;

/**
 * Provides full enrichment of curated Publication.
 *
 * - enrich full properties as described in FullPublicationEnricher
 *  - enrich released date. It will only enrich the released date if it is not already set in the publication to enrich. It will not
 * override any existing released date
 * - enrich curation depth. It will only enrich the curation depth if it is not already set in the publication to enrich. It will not
 * override any existing curation depth
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 31/07/13

 */
public class FullCuratedPublicationEnricher extends MinimalCuratedPublicationEnricher {

    /**
     * <p>Constructor for FullCuratedPublicationEnricher.</p>
     *
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.PublicationFetcher} object.
     */
    public FullCuratedPublicationEnricher(PublicationFetcher fetcher) {
        super(new FullPublicationEnricher(fetcher));
    }

    /**
     * <p>Constructor for FullCuratedPublicationEnricher.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalPublicationEnricher} object.
     */
    protected FullCuratedPublicationEnricher(MinimalPublicationEnricher delegate) {
        super(delegate);
    }

    /**
     * {@inheritDoc}
     *
     * The strategy for the enrichment of the publication.
     * This methods can be overwritten to change the behaviour of the enrichment.
     */
    @Override
    protected void processOtherProperties(Publication publicationToEnrich, Publication fetched) throws EnricherException {

        // == RELEASE date ===================================================================
        processReleasedDate(publicationToEnrich, fetched);

        // == CURATION depth ===================================================================
        processCurationDepth(publicationToEnrich, fetched);
    }

    /**
     * <p>processCurationDepth.</p>
     *
     * @param publicationToEnrich a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param fetched a {@link psidev.psi.mi.jami.model.Publication} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processCurationDepth(Publication publicationToEnrich, Publication fetched) throws EnricherException{
        if (publicationToEnrich.getCurationDepth().equals(CurationDepth.undefined)
                && !fetched.getCurationDepth().equals(CurationDepth.undefined)){
            publicationToEnrich.setCurationDepth(fetched.getCurationDepth());
            if (getPublicationEnricherListener() != null){
                getPublicationEnricherListener().onCurationDepthUpdate(publicationToEnrich, CurationDepth.undefined);
            }
        }
    }

    /**
     * <p>processReleasedDate.</p>
     *
     * @param publicationToEnrich a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param fetched a {@link psidev.psi.mi.jami.model.Publication} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processReleasedDate(Publication publicationToEnrich, Publication fetched) throws EnricherException{
        if (publicationToEnrich.getReleasedDate() == null && fetched.getReleasedDate() != null){
            publicationToEnrich.setReleasedDate(fetched.getReleasedDate());
            if (getPublicationEnricherListener() != null){
                getPublicationEnricherListener().onReleaseDateUpdated(publicationToEnrich, null);
            }
        }
    }
}

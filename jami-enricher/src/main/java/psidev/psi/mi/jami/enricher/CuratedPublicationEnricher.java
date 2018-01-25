package psidev.psi.mi.jami.enricher;

/**
 * An enricher for curated publications which can enrich either a single publication or a collection.
 * It must be initiated with a fetcher.
 * Sub enrichers:
 * - Source enricher
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since  31/07/13

 */
public interface CuratedPublicationEnricher extends PublicationEnricher{

    /**
     * <p>getSourceEnricher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.SourceEnricher} object.
     */
    public SourceEnricher getSourceEnricher();

    /**
     * <p>setSourceEnricher.</p>
     *
     * @param enricher a {@link psidev.psi.mi.jami.enricher.SourceEnricher} object.
     */
    public void setSourceEnricher(SourceEnricher enricher);
}

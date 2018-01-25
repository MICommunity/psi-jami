package psidev.psi.mi.jami.enricher;

import psidev.psi.mi.jami.model.ModelledInteraction;

/**
 * The enricher for Interactions which can enrich a single interaction or a collection.
 * The interaction enricher has no fetcher.
 * Sub enrichers: Participant, CvTerm.
 *
 * @param <I>   The type of interaction to be enriched
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 28/06/13

 */
public interface ModelledInteractionEnricher<I extends ModelledInteraction> extends InteractionEnricher<I> {

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

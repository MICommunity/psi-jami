package psidev.psi.mi.jami.enricher.listener;

/**
 * An enricher listener has enricher specific methods,
 * fired after the object has been changed and upon completion of the enrichment.
 *
 * @param <T>   The type of JAMI object being enriched.
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since  09/07/13

 */
public interface EnricherListener<T> {

    /**
     * <p>onEnrichmentComplete.</p>
     *
     * @param object a T object.
     * @param status a {@link psidev.psi.mi.jami.enricher.listener.EnrichmentStatus} object.
     * @param message a {@link java.lang.String} object.
     */
    public void onEnrichmentComplete(T object , EnrichmentStatus status , String message);

    /**
     * <p>onEnrichmentError.</p>
     *
     * @param object a T object.
     * @param message a {@link java.lang.String} object.
     * @param e a {@link java.lang.Exception} object.
     */
    public void onEnrichmentError(T object , String message, Exception e);
}

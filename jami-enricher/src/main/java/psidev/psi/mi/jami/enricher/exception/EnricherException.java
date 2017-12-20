package psidev.psi.mi.jami.enricher.exception;

/**
 * This exception is thrown when exceptions are induced in the enricher.
 * This includes re-wrapping the BridgeFailedException.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 11/06/13

 */
public class EnricherException extends Exception {
    /**
     * <p>Constructor for EnricherException.</p>
     */
    public EnricherException() {
        super();
    }

    /**
     * <p>Constructor for EnricherException.</p>
     *
     * @param s a {@link java.lang.String} object.
     */
    public EnricherException(String s) {
        super(s);
    }

    /**
     * <p>Constructor for EnricherException.</p>
     *
     * @param s a {@link java.lang.String} object.
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public EnricherException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     * <p>Constructor for EnricherException.</p>
     *
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public EnricherException(Throwable throwable) {
        super(throwable);
    }
}

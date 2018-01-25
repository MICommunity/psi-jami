package psidev.psi.mi.jami.exception;

/**
 * Exception for Interactions that cannot be expanded with a specific complex expansion
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/07/14</pre>
 */
public class ComplexExpansionException extends Exception{

    /**
     * <p>Constructor for ComplexExpansionException.</p>
     */
    public ComplexExpansionException() {
        super();
    }

    /**
     * <p>Constructor for ComplexExpansionException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public ComplexExpansionException(String message) {
        super(message);
    }

    /**
     * <p>Constructor for ComplexExpansionException.</p>
     *
     * @param message a {@link java.lang.String} object.
     * @param cause a {@link java.lang.Throwable} object.
     */
    public ComplexExpansionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <p>Constructor for ComplexExpansionException.</p>
     *
     * @param cause a {@link java.lang.Throwable} object.
     */
    public ComplexExpansionException(Throwable cause) {
        super(cause);
    }

    /**
     * <p>Constructor for ComplexExpansionException.</p>
     *
     * @param message a {@link java.lang.String} object.
     * @param cause a {@link java.lang.Throwable} object.
     * @param enableSuppression a boolean.
     * @param writableStackTrace a boolean.
     */
    protected ComplexExpansionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package psidev.psi.mi.jami.bridges.exception;

/**
 * An error object to signify that a query to an external service has failed.
 * This exception would usually be expected pass the original Exception that caused the problem.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since  15/04/13

 */
public class BridgeFailedException extends Exception{

    /**
     * <p>Constructor for BridgeFailedException.</p>
     */
    public BridgeFailedException() {
        super();
    }

    /**
     * <p>Constructor for BridgeFailedException.</p>
     *
     * @param s a {@link java.lang.String} object.
     */
    public BridgeFailedException(String s) {
        super(s);
    }

    /**
     * <p>Constructor for BridgeFailedException.</p>
     *
     * @param s a {@link java.lang.String} object.
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public BridgeFailedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     * <p>Constructor for BridgeFailedException.</p>
     *
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public BridgeFailedException(Throwable throwable) {
        super(throwable);
    }
}

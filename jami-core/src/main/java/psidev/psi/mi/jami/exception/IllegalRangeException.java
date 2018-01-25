package psidev.psi.mi.jami.exception;

/**
 * Exception thrown when trying to create an Illegal range
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/02/13</pre>
 */
public class IllegalRangeException extends Exception {
    /**
     * <p>Constructor for IllegalRangeException.</p>
     */
    public IllegalRangeException() {
        super();
    }

    /**
     * <p>Constructor for IllegalRangeException.</p>
     *
     * @param s a {@link java.lang.String} object.
     */
    public IllegalRangeException(String s) {
        super(s);
    }

    /**
     * <p>Constructor for IllegalRangeException.</p>
     *
     * @param s a {@link java.lang.String} object.
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public IllegalRangeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     * <p>Constructor for IllegalRangeException.</p>
     *
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public IllegalRangeException(Throwable throwable) {
        super(throwable);
    }
}

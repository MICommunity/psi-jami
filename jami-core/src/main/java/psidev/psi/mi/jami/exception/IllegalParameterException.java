package psidev.psi.mi.jami.exception;

/**
 * Exception to throw if a Parameter is not valid
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/02/13</pre>
 */
public class IllegalParameterException extends Exception {

    /**
     * <p>Constructor for IllegalParameterException.</p>
     */
    public IllegalParameterException() {
        super();
    }

    /**
     * <p>Constructor for IllegalParameterException.</p>
     *
     * @param s a {@link java.lang.String} object.
     */
    public IllegalParameterException(String s) {
        super(s);
    }

    /**
     * <p>Constructor for IllegalParameterException.</p>
     *
     * @param s a {@link java.lang.String} object.
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public IllegalParameterException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     * <p>Constructor for IllegalParameterException.</p>
     *
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public IllegalParameterException(Throwable throwable) {
        super(throwable);
    }
}

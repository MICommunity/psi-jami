package psidev.psi.mi.jami.bridges.ols;

/**
 * Exception thrown when a Lazy cv term cannot load the lazy data from OLS
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>05/09/13</pre>
 */
public class LazyTermLoadingException extends RuntimeException {

    /**
     * <p>Constructor for LazyTermLoadingException.</p>
     */
    public LazyTermLoadingException() {
        super();
    }

    /**
     * <p>Constructor for LazyTermLoadingException.</p>
     *
     * @param s a {@link java.lang.String} object.
     */
    public LazyTermLoadingException(String s) {
        super(s);
    }

    /**
     * <p>Constructor for LazyTermLoadingException.</p>
     *
     * @param s a {@link java.lang.String} object.
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public LazyTermLoadingException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     * <p>Constructor for LazyTermLoadingException.</p>
     *
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public LazyTermLoadingException(Throwable throwable) {
        super(throwable);
    }
}

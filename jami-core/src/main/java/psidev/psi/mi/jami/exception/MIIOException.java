package psidev.psi.mi.jami.exception;

/**
 * Exception thrown by a dataSource or a writer when it cannot read/write MI data
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/07/13</pre>
 */
public class MIIOException extends RuntimeException{

    /**
     * <p>Constructor for MIIOException.</p>
     */
    public MIIOException() {
        super();
    }

    /**
     * <p>Constructor for MIIOException.</p>
     *
     * @param s a {@link java.lang.String} object.
     */
    public MIIOException(String s) {
        super(s);
    }

    /**
     * <p>Constructor for MIIOException.</p>
     *
     * @param s a {@link java.lang.String} object.
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public MIIOException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     * <p>Constructor for MIIOException.</p>
     *
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public MIIOException(Throwable throwable) {
        super(throwable);
    }
}

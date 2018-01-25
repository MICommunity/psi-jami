package psidev.psi.mi.jami.bridges.picr.io;

/**
 * TODO comment this
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>25-Mar-2010</pre>
 */
public class PicrParsingException extends Exception{

    /**
     * <p>Constructor for PicrParsingException.</p>
     */
    public PicrParsingException() {
        super();   
    }

    /**
     * <p>Constructor for PicrParsingException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public PicrParsingException(String message) {
        super(message);
    }

    /**
     * <p>Constructor for PicrParsingException.</p>
     *
     * @param message a {@link java.lang.String} object.
     * @param cause a {@link java.lang.Throwable} object.
     */
    public PicrParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <p>Constructor for PicrParsingException.</p>
     *
     * @param cause a {@link java.lang.Throwable} object.
     */
    public PicrParsingException(Throwable cause) {
        super(cause);
    }
}

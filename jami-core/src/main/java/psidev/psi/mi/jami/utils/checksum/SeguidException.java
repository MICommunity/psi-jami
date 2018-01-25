package psidev.psi.mi.jami.utils.checksum;

/**
 * Custom Exception class to handle exceptions from this module
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 */
public class SeguidException extends Exception {

    /**
     * <p>Constructor for SeguidException.</p>
     */
    public SeguidException() {
        super();
    }

    /**
     * <p>Constructor for SeguidException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public SeguidException( String message ) {
        super(message);
    }

    /**
     * <p>Constructor for SeguidException.</p>
     *
     * @param message a {@link java.lang.String} object.
     * @param cause a {@link java.lang.Throwable} object.
     */
    public SeguidException( String message, Throwable cause ) {
        super( message,cause );

    }

    /**
     * <p>Constructor for SeguidException.</p>
     *
     * @param cause a {@link java.lang.Throwable} object.
     */
    public SeguidException(Throwable cause){
        super(cause);
    }

}

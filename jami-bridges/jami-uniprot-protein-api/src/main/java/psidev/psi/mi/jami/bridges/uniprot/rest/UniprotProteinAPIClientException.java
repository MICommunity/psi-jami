package psidev.psi.mi.jami.bridges.uniprot.rest;

public class UniprotProteinAPIClientException extends Exception {
    public UniprotProteinAPIClientException() {
        super();
    }

    public UniprotProteinAPIClientException(String message) {
        super(message);
    }

    public UniprotProteinAPIClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniprotProteinAPIClientException(Throwable cause) {
        super(cause);
    }
}

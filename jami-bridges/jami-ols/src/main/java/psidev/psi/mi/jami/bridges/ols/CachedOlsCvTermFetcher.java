package psidev.psi.mi.jami.bridges.ols;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.CvTerm;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 29/07/13

 */
public class CachedOlsCvTermFetcher
        extends CachedOlsFetcher<CvTerm> {

    /** Constant <code>CACHE_NAME="cvterm-cache"</code> */
    public static final String CACHE_NAME = "cvterm-cache";

    private OlsCvTermFetcher delegateFetcher;

    /**
     * <p>Constructor for CachedOlsCvTermFetcher.</p>
     *
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public CachedOlsCvTermFetcher() throws BridgeFailedException {
        super(CACHE_NAME, new OlsCvTermFetcher());
    }
}

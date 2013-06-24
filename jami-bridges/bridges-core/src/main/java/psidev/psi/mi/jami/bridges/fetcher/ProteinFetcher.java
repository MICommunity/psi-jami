package psidev.psi.mi.jami.bridges.fetcher;

import com.sun.istack.internal.NotNull;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.Protein;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * Date: 14/05/13
 */
public interface ProteinFetcher {

    /**
     * Takes a string identifier and returns the proteins which match.
     * Returns an empty collection of no entries are returned
     *
     * @param identifier    The identifier to search for.
     * @return  The proteins which match the search term. Never null
     * @throws BridgeFailedException    A problem has been encountered when contacting the service
     */
    @NotNull
    public Collection<Protein> getProteinsByIdentifier(String identifier)
            throws BridgeFailedException;

    /**
     *
     * @param identifiers
     * @return
     * @throws BridgeFailedException
     */
    @NotNull
    public Collection<Protein> getProteinsByIdentifiers(Collection<String> identifiers)
            throws BridgeFailedException;
}

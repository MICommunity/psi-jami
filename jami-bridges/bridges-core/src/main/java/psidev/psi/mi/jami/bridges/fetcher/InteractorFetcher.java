package psidev.psi.mi.jami.bridges.fetcher;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.Interactor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Fetches the complete records which match an interactor.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 14/05/13
 */
public interface InteractorFetcher<I extends Interactor> {

    /**
     * Takes a string identifier and returns the interactors which match.
     * Returns an empty collection of no entries are returned
     *
     * @param identifier The identifier to search for.
     * @return The proteins which match the search term. Empty if no matches.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException A problem has been encountered when contacting the service
     */
    default Collection<I> fetchByIdentifier(String identifier)
            throws BridgeFailedException {
        return fetchByIdentifiers(List.of(identifier));
    }

    /**
     * Takes a collection of string identifiers and returns the interactors which match.
     * Returns an empty collection of no entries are returned.
     *
     * @param identifiers The identifiers to search for.
     * @return The proteins which match the search term. Empty if no matches.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    default Collection<I> fetchByIdentifiers(Collection<String> identifiers)
            throws BridgeFailedException {
        List<I> list = new ArrayList<>();
        for (String identifier : identifiers) {
            list.addAll(fetchByIdentifier(identifier));
        }
        return list;
    }
}

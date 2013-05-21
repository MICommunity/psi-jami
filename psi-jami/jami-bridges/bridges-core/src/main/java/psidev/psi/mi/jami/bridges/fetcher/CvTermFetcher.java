package psidev.psi.mi.jami.bridges.fetcher;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.exception.EntryNotFoundException;
import psidev.psi.mi.jami.bridges.exception.FetcherException;
import psidev.psi.mi.jami.bridges.exception.NullSearchException;
import psidev.psi.mi.jami.model.CvTerm;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Gabriel Aldam (galdam@ebi.ac.uk)
 * Date: 08/05/13
 * Time: 16:34
 */
public interface CvTermFetcher {

    /**
     * Identifies and initiates the CvTerm using its identifier.
     * If the identifier can not be accessed, returns null.
     *
     * @param identifier    The identifier to search by
     * @param database
     * @return A fully enriched CvTerm
     * @throws BridgeFailedException    Thrown if there are problems with the connection
     */
    public CvTerm getCvTermByID(String identifier, String database)
            throws FetcherException;

    /**
     * Identifies and initiates a CvTerm using its name.
     * The identifier is retrieved using the search by exact name method in the bridge.
     * If the identifier is null, or multiple identifiers are found, throws exceptions.
     *
     * @param searchName
     * @param database
     * @return
     * @throws BridgeFailedException
     * @throws NullSearchException
     * @throws EntryNotFoundException
     */
    public CvTerm getCvTermByTerm(String searchName, String database)
            throws FetcherException;

    /**
     * Identifies and initiates a CvTerm using its name.
     * A fuzzy search can also be used by setting @link{useFuzzySearch} to true.
     * This will extend to search possibilities to partial matches if no exact matches can be found.
     * @param searchName
     * @param database
     * @param useFuzzySearch
     * @return
     * @throws BridgeFailedException
     * @throws NullSearchException
     * @throws EntryNotFoundException
     */
    public CvTerm getCvTermByTerm(String searchName, String database, boolean useFuzzySearch)
            throws FetcherException;
}

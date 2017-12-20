package psidev.psi.mi.jami.bridges.ols;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.OntologyTerm;

import java.util.Set;


/**
 *
 *
 * Code for the cache based on the CachedOntologyService at uk.ac.ebi.intact.bridges.olslight;
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 03/07/13

 */
public class CachedOlsOntologyTermFetcher extends CachedOlsFetcher<OntologyTerm> implements OntologyTermFetcher{

    /** Constant <code>CACHE_NAME="ontology-cache"</code> */
    public static final String CACHE_NAME = "ontology-cache";


    /**
     * <p>Constructor for CachedOlsOntologyTermFetcher.</p>
     *
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public CachedOlsOntologyTermFetcher() throws BridgeFailedException {
        super(CACHE_NAME, new OlsOntologyTermFetcher());
    }

    /** {@inheritDoc} */
    public Set<OntologyTerm> fetchRootTerms(String databaseName) throws BridgeFailedException {
        return getDelegateFetcher().fetchRootTerms(databaseName);
    }

    /**
     * <p>fetchRootTerms.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @return a {@link java.util.Set} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public Set<OntologyTerm> fetchRootTerms(CvTerm database) throws BridgeFailedException {
        return getDelegateFetcher().fetchRootTerms(database);
    }

    /** {@inheritDoc} */
    @Override
    protected OntologyTermFetcher getDelegateFetcher() {
        return (OntologyTermFetcher) super.getDelegateFetcher();
    }
}

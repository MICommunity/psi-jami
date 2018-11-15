package psidev.psi.mi.jami.bridges.fetcher.mock;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.GeneFetcher;
import psidev.psi.mi.jami.model.Gene;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A mock fetcher for testing.
 * It extends all the methods of the true fetcher, but does not access an external service.
 * Instead, it holds a map which can be loaded with objects and keys. which are then returned.
 * It attempts to replicate the responses of the fetcher in most scenarios.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/05/13</pre>
 */
//TODO REVIEW
public class MockGeneFetcher
        extends AbstractMockFetcher<Collection<Gene>>
        implements GeneFetcher {

    /** {@inheritDoc} */
    protected Collection<Gene> getEntry(String identifier) throws BridgeFailedException {
        if(identifier == null) throw new IllegalArgumentException(
                "Attempted to query mock gene fetcher for null identifier.");

        if(! localMap.containsKey(identifier)) {
            return new ArrayList<Gene>();
        }else {
            return super.getEntry(identifier);
        }
    }

    /** {@inheritDoc} */
    public Collection<Gene> fetchByIdentifier(String identifier) throws BridgeFailedException {
        return getEntry(identifier);
    }

    /** {@inheritDoc} */
    public Collection<Gene> fetchByIdentifiers(Collection<String> identifiers) throws BridgeFailedException {
        Collection<Gene> resultsList= new ArrayList<Gene>();
        for(String identifier : identifiers){
            resultsList.addAll( getEntry(identifier) );
        }
        return resultsList;
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Gene> fetchByIdentifier(String identifier, int taxID) throws BridgeFailedException {
        return getEntry(identifier);
    }

    @Override
    public Collection<Gene> fetchByIdentifiers(Collection<String> identifiers, int taxID) throws BridgeFailedException {
        Collection<Gene> resultsList= new ArrayList<Gene>();
        for(String identifier : identifiers){
            resultsList.addAll( getEntry(identifier) );
        }
        return resultsList;
    }


}

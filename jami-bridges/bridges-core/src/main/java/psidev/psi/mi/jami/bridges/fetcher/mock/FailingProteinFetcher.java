package psidev.psi.mi.jami.bridges.fetcher.mock;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.ProteinFetcher;
import psidev.psi.mi.jami.model.Protein;

import java.util.*;

/**
 * A mock fetcher for testing exceptions.
 * It extends the functionality of the mock fetcher but can also throw exceptions.
 * Upon initialisation, an integer is given which sets how many times a query is made before returning the result.
 * If the current query matches the last query and the counter of the number of times is less than the maxQuery
 * set at initialisation, then an exception will be thrown.
 * Additionally, if the maxQuery is set to -1, the fetcher will always throw an exception.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/05/13</pre>
 */
public class FailingProteinFetcher
        extends AbstractFailingFetcher<Collection<Protein>>
        implements ProteinFetcher {

    /**
     * <p>Constructor for FailingProteinFetcher.</p>
     *
     * @param maxQuery a int.
     */
    public FailingProteinFetcher(int maxQuery) {
        super(maxQuery);
    }

    /** {@inheritDoc} */
    protected Collection<Protein> getEntry(String identifier) throws BridgeFailedException {
        if(identifier == null) throw new IllegalArgumentException(
                "Attempted to query mock protein fetcher for null identifier.");

        if(! localMap.containsKey(identifier)) {
            return new ArrayList<Protein>();
        }else {
            return super.getEntry(identifier);
        }
    }

    /**
     * <p>addEntry.</p>
     *
     * @param identifier a {@link java.lang.String} object.
     * @param protein a {@link psidev.psi.mi.jami.model.Protein} object.
     */
    public void addEntry(String identifier, Protein protein){
        if(protein == null) return;
        if(! localMap.containsKey(identifier)){
            ArrayList<Protein> array = new ArrayList<Protein>();
            localMap.put(identifier, array);
        }
        localMap.get(identifier).add(protein);
    }


    /** {@inheritDoc} */
    public Collection<Protein> fetchByIdentifier(String identifier) throws BridgeFailedException {
        return getEntry(identifier);
    }

    /** {@inheritDoc} */
    public Collection<Protein> fetchByIdentifiers(Collection<String> identifiers) throws BridgeFailedException {
        Collection<Protein> resultsList= new ArrayList<Protein>();
        for(String identifier : identifiers){
            resultsList.addAll( getEntry(identifier) );
        }
        return resultsList;
    }

}

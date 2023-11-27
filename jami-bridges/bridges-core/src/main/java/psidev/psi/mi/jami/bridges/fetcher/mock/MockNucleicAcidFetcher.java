package psidev.psi.mi.jami.bridges.fetcher.mock;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.NucleicAcidFetcher;
import psidev.psi.mi.jami.model.NucleicAcid;

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
public class MockNucleicAcidFetcher
        extends AbstractMockFetcher<Collection<NucleicAcid>>
        implements NucleicAcidFetcher {

    /**
     * {@inheritDoc}
     */
    protected Collection<NucleicAcid> getEntry(String identifier) throws BridgeFailedException {
        if (identifier == null) throw new IllegalArgumentException(
                "Attempted to query mock NucleicAcid fetcher for null identifier.");

        if (!localMap.containsKey(identifier)) {
            return new ArrayList<>();
        } else {
            return super.getEntry(identifier);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Collection<NucleicAcid> fetchByIdentifier(String identifier) throws BridgeFailedException {
        return getEntry(identifier);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<NucleicAcid> fetchByIdentifiers(Collection<String> identifiers) throws BridgeFailedException {
        Collection<NucleicAcid> resultsList = new ArrayList<>();
        for (String identifier : identifiers) {
            resultsList.addAll(getEntry(identifier));
        }
        return resultsList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<NucleicAcid> fetchByIdentifier(String identifier, int taxID) throws BridgeFailedException {
        return getEntry(identifier);
    }

    @Override
    public Collection<NucleicAcid> fetchByIdentifiers(Collection<String> identifiers, int taxID) throws BridgeFailedException {
        Collection<NucleicAcid> resultsList = new ArrayList<>();
        for (String identifier : identifiers) {
            resultsList.addAll(getEntry(identifier));
        }
        return resultsList;
    }

}

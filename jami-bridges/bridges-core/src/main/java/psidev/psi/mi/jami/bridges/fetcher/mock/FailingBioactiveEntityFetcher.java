package psidev.psi.mi.jami.bridges.fetcher.mock;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.BioactiveEntityFetcher;
import psidev.psi.mi.jami.model.BioactiveEntity;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 09/08/13

 */
public class FailingBioactiveEntityFetcher
        extends AbstractFailingFetcher<Collection<BioactiveEntity>>
        implements BioactiveEntityFetcher {


    /**
     * <p>Constructor for FailingBioactiveEntityFetcher.</p>
     *
     * @param maxQuery a int.
     */
    public FailingBioactiveEntityFetcher(int maxQuery) {
        super(maxQuery);
    }

    /** {@inheritDoc} */
    public Collection<BioactiveEntity> fetchByIdentifier(String identifier) throws BridgeFailedException {
        return super.getEntry(identifier);
    }

    /** {@inheritDoc} */
    public Collection<BioactiveEntity> fetchByIdentifiers(Collection<String> identifiers) throws BridgeFailedException {
        Collection<BioactiveEntity> resultsList= new ArrayList<BioactiveEntity>();
        for(String identifier : identifiers){
            resultsList.addAll( getEntry(identifier) );
        }
        return resultsList;
    }
}

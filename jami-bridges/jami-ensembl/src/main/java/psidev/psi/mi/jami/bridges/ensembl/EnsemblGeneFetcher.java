package psidev.psi.mi.jami.bridges.ensembl;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.GeneFetcher;
import psidev.psi.mi.jami.model.Gene;
import psidev.psi.mi.jami.model.Interactor;

import java.util.Collection;
import java.util.Map;

public class EnsemblGeneFetcher extends AbstractEnsemblFetcher<Gene> implements GeneFetcher {

    public EnsemblGeneFetcher() throws BridgeFailedException {
    }

    protected Gene buildInteractor(String identifier, ApiObject entree, Map<String, Interactor> translationIdToInteractor) {
        return buildGeneInteractor(identifier, entree, translationIdToInteractor);
    }

    @Override
    public Collection<Gene> fetchByIdentifier(String identifier, int taxID) throws BridgeFailedException {
        return fetchByIdentifier(identifier);
    }

    @Override
    public Collection<Gene> fetchByIdentifiers(Collection<String> identifiers, int taxID) throws BridgeFailedException {
        return fetchByIdentifiers(identifiers);
    }
}
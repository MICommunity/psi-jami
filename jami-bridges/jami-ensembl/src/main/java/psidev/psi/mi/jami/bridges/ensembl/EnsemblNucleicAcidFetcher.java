package psidev.psi.mi.jami.bridges.ensembl;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.NucleicAcidFetcher;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.NucleicAcid;

import java.util.Collection;
import java.util.Map;

public class EnsemblNucleicAcidFetcher extends AbstractEnsemblFetcher<NucleicAcid> implements NucleicAcidFetcher {

    public EnsemblNucleicAcidFetcher() throws BridgeFailedException {
    }

    protected NucleicAcid buildInteractor(String identifier, ApiObject entree, Map<String, Interactor> translationIdToInteractor) {
        return buildNucleicAcid(identifier, entree, translationIdToInteractor);
    }

    @Override
    public Collection<NucleicAcid> fetchByIdentifier(String identifier, int taxID) throws BridgeFailedException {
        return fetchByIdentifier(identifier);
    }

    @Override
    public Collection<NucleicAcid> fetchByIdentifiers(Collection<String> identifiers, int taxID) throws BridgeFailedException {
        return NucleicAcidFetcher.super.fetchByIdentifiers(identifiers, taxID);
    }
}
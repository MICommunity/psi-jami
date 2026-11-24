package psidev.psi.mi.jami.bridges.ensembl;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.*;

import java.util.*;

public class EnsemblInteractorFetcher extends AbstractEnsemblFetcher<Interactor> {

    public EnsemblInteractorFetcher() throws BridgeFailedException {
    }

    protected Interactor buildInteractor(ApiObject entree, Map<String, List<Interactor>> translationIdToInteractors) {

        switch (entree.getObjectType()) {
            case GENE:
                return buildGeneInteractor(entree, translationIdToInteractors);
            case TRANSCRIPT:
                return buildNucleicAcid(entree, translationIdToInteractors);
            case TRANSLATION:
            case EXON:
                break;
        }
        return null;
    }
}
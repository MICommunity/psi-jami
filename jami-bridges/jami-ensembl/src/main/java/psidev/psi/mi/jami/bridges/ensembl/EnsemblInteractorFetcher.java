package psidev.psi.mi.jami.bridges.ensembl;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.*;

import java.util.*;

public class EnsemblInteractorFetcher extends AbstractEnsemblFetcher<Interactor> {

    public EnsemblInteractorFetcher() throws BridgeFailedException {
    }

    protected Interactor buildInteractor(ApiObject entree, Map<String, Interactor> translationIdToInteractor) {

        switch (entree.getObjectType()) {
            case GENE:
                return buildGeneInteractor(entree, translationIdToInteractor);
            case TRANSCRIPT:
                return buildNucleicAcid(entree, translationIdToInteractor);
            case TRANSLATION:
            case EXON:
                break;
        }
        return null;
    }
}
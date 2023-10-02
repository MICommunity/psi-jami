package psidev.psi.mi.jami.bridges.ensembl;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.*;

import java.util.*;

public class EnsemblInteractorFetcher extends AbstractEnsemblFetcher<Interactor> {

    public EnsemblInteractorFetcher() throws BridgeFailedException {
    }

    protected Interactor buildInteractor(String identifier, ApiObject entree, Map<String, Interactor> translationIdToInteractor) {

        switch (entree.getObjectType()) {
            case GENE:
                return buildGeneInteractor(identifier, entree, translationIdToInteractor);
            case TRANSCRIPT:
                return buildNucleicAcid(identifier, entree, translationIdToInteractor);
            case TRANSLATION:
            case EXON:
                break;
        }
        return null;
    }
}
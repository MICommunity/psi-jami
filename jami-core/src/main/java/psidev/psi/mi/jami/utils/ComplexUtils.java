package psidev.psi.mi.jami.utils;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultModelledParticipant;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComplexUtils {

    public static void maintainProteinComparableParticipantMap(
            Map<String, ModelledComparableParticipant> map,
            boolean useChainParentId,
            Function<String, Interactor> fetchInteractorByAcFunction,
            ModelledParticipant... modelledParticipants) {

        if (map == null) {
            map = new HashMap<>();
        }
        for (ModelledParticipant modelledParticipant : modelledParticipants) {
            if (modelledParticipant.getInteractor() instanceof Protein || modelledParticipant.getInteractor() instanceof InteractorPool) {
                delegateMapMaintenance(modelledParticipant.getInteractor(), modelledParticipant, map, useChainParentId, fetchInteractorByAcFunction);
            }
        }
    }

    public static void maintainParticipantMap(Map<String, ModelledComparableParticipant> map, ModelledParticipant... modelledParticipants) {
        if (map == null) {
            map = new HashMap<>();
        }
        for (ModelledParticipant modelledParticipant : modelledParticipants) {
            delegateMapMaintenance(modelledParticipant.getInteractor(), modelledParticipant, map, false, null);
        }
    }

    private static void delegateMapMaintenance(
            Interactor interactor,
            ModelledParticipant modelledParticipant,
            Map<String, ModelledComparableParticipant> map,
            boolean useChainParentId,
            Function<String, Interactor> fetchInteractorByAcFunction) {

        String interactorId = extractIdentifier(interactor, useChainParentId, fetchInteractorByAcFunction);
        if (interactorId != null) {
            if (map.get(interactorId) != null) {
                ModelledComparableParticipant modelledComparableParticipant = map.get(interactorId);
                if (modelledParticipant.getStoichiometry() != null) {
                    int addedStoichiometry = modelledComparableParticipant.getStoichiometry() + modelledParticipant.getStoichiometry().getMinValue();
                    modelledComparableParticipant.setStoichiometry(addedStoichiometry);
                }
            } else {
                ModelledComparableParticipant modelledComparableParticipant = new ModelledComparableParticipant();
                modelledComparableParticipant.setInteractorId(interactorId);
                modelledComparableParticipant.setIdentifiers(extractAllIdentifiers(interactor, useChainParentId, fetchInteractorByAcFunction));
                modelledComparableParticipant.setInteractorType(interactor.getInteractorType());
                if (modelledParticipant.getStoichiometry() != null) {
                    modelledComparableParticipant.setStoichiometry(modelledParticipant.getStoichiometry().getMinValue());
                }
                map.put(interactorId, modelledComparableParticipant);
            }
        }
    }

    private static Collection<Xref> extractAllIdentifiers(Interactor interactor, boolean useChainParentId, Function<String, Interactor> fetchInteractorByAcFunction) {
        Set<Xref> identifiers = new HashSet<>(interactor.getIdentifiers());

        if (useChainParentId) {
            Collection<Xref> chainParentXrefs = getChainParentXrefs(interactor);
            Optional<Xref> intactChainParentXref = chainParentXrefs.stream()
                    .filter(xref -> xref.getDatabase() != null && Xref.INTACT_MI.equals(xref.getDatabase().getMIIdentifier()))
                    .findAny();
            if (intactChainParentXref.isPresent()) {
                Interactor chainParentInteractor = fetchInteractorByAcFunction.apply(intactChainParentXref.get().getId());
                if (chainParentInteractor != null) {
                    identifiers.addAll(chainParentInteractor.getIdentifiers());
                }
            } else {
                identifiers.addAll(chainParentXrefs);
            }
        }
        if (interactor instanceof InteractorPool) {
            for (Interactor subInteractor : (InteractorPool) interactor) {
                identifiers.addAll(extractAllIdentifiers(subInteractor, useChainParentId, fetchInteractorByAcFunction));
            }

        }
        return identifiers;
    }

    private static String extractIdentifier(Interactor interactor, boolean useChainParentId, Function<String, Interactor> fetchInteractorByAcFunction) {
        if (useChainParentId) {
            Collection<Xref> chainParentXrefs = getChainParentXrefs(interactor);
            Optional<Xref> intactChainParentXref = chainParentXrefs.stream()
                    .filter(xref -> xref.getDatabase() != null && Xref.INTACT_MI.equals(xref.getDatabase().getMIIdentifier()))
                    .findAny();
            if (intactChainParentXref.isPresent()) {
                Interactor chainParentInteractor = fetchInteractorByAcFunction.apply(intactChainParentXref.get().getId());
                if (chainParentInteractor != null) {
                    return extractIdentifier(chainParentInteractor);
                }
            }

            Optional<Xref> anyChainParentXref = chainParentXrefs.stream().findAny();
            if (anyChainParentXref.isPresent()) {
                return anyChainParentXref.get().getId();
            }
        }
        String identifier = extractIdentifier(interactor);
        if (identifier == null && interactor instanceof InteractorPool) {
            for (Interactor subInteractor : (InteractorPool) interactor) {
                identifier = extractIdentifier(subInteractor, useChainParentId, fetchInteractorByAcFunction);
                if (identifier != null) {
                    return identifier;
                }
            }
        }
        return identifier;
    }

    private static Collection<Xref> getChainParentXrefs(Interactor interactor) {
        return Stream.concat(interactor.getXrefs().stream(), interactor.getIdentifiers().stream())
                .filter(xref -> xref.getQualifier() != null)
                .filter(xref -> Xref.CHAIN_PARENT_MI.equals(xref.getQualifier().getMIIdentifier()) ||
                        Xref.ISOFORM_PARENT_MI.equals(xref.getQualifier().getMIIdentifier()))
                .collect(Collectors.toList());
    }

    private static String extractIdentifier(Interactor interactor) {
        String id=null;
        if (interactor.getPreferredIdentifier() !=null && !interactor.getPreferredIdentifier().getId().isEmpty()) {
            id = interactor.getPreferredIdentifier().getId();
        } else {
            String ac = CommonUtils.extractIntactAcFromIdentifier(interactor.getIdentifiers());
            if (ac != null && !ac.isEmpty()) {
                id = ac;
            }
        }

        return id;
    }

    /**
     * Expands the given complex participant.
     * Changes/expands the stoichiometry in expanded participants.
     * Inherit the features in expanded participants.
     * Expanded participants created are new instances.
     *
     * @param parentParticipant a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @return a collection of {@link psidev.psi.mi.jami.model.ModelledParticipant} objects.
     */
    public static Collection<ModelledParticipant> expandComplexIntoParticipants(ModelledParticipant parentParticipant) {

        Collection<ModelledParticipant> expandedModelledParticipants = new ArrayList<>();
        if (parentParticipant.getInteractor() instanceof Complex) {
            Complex complex = (Complex) parentParticipant.getInteractor();
            for (ModelledParticipant complexParticipant : complex.getParticipants()) {
                expandComplexIntoParticipants(complexParticipant).forEach(participant -> {
                    // expand stoichiometry
                    int minStoichiometry = 0;
                    int maxStoichiometry = 0;
                    Stoichiometry expandedStoichiometry = null;
                    if (participant.getStoichiometry() != null && parentParticipant.getStoichiometry() != null) {
                        minStoichiometry = participant.getStoichiometry().getMinValue() * parentParticipant.getStoichiometry().getMinValue();
                        maxStoichiometry = participant.getStoichiometry().getMaxValue() * parentParticipant.getStoichiometry().getMaxValue();
                        Class stoichiometryClass = participant.getStoichiometry().getClass();
                        try {
                            expandedStoichiometry = (Stoichiometry) stoichiometryClass.getConstructor(Integer.TYPE, Integer.TYPE).newInstance(minStoichiometry, maxStoichiometry);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    DefaultModelledParticipant defaultModelledParticipant = new DefaultModelledParticipant(participant.getInteractor(),expandedStoichiometry);
                    expandedModelledParticipants.add(defaultModelledParticipant);
                });
            }
            return expandedModelledParticipants;
        }

        // return back the participant if not complex
        expandedModelledParticipants.add(parentParticipant);
        return expandedModelledParticipants;
    }
}

package psidev.psi.mi.jami.utils;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultModelledParticipant;

import java.util.*;

public class ComplexUtils {

    public static void maintainProteinComparableParticipantMap(Map<String, ModelledComparableParticipant> map, ModelledParticipant... modelledParticipants) {
        if (map == null) {
            map = new HashMap<>();
        }
        for (ModelledParticipant modelledParticipant : modelledParticipants) {
            if (modelledParticipant.getInteractor() instanceof Protein) {
                delegateMapMaintenance(modelledParticipant.getInteractor(), modelledParticipant, map);
            } else if (modelledParticipant.getInteractor() instanceof InteractorPool) {
                for (Interactor interactor : (InteractorPool) modelledParticipant.getInteractor()) {
                    if (interactor instanceof Protein) {
                        delegateMapMaintenance(interactor, modelledParticipant, map);
                    }
                }
            }
        }
    }

    public static void maintainParticipantMap(Map<String, ModelledComparableParticipant> map, ModelledParticipant... modelledParticipants) {
        if (map == null) {
            map = new HashMap<>();
        }
        for (ModelledParticipant modelledParticipant : modelledParticipants) {
            if (modelledParticipant.getInteractor() instanceof InteractorPool) {
                for (Interactor interactor : (InteractorPool) modelledParticipant.getInteractor()) {
                    delegateMapMaintenance(interactor, modelledParticipant, map);
                }
            } else {
                delegateMapMaintenance(modelledParticipant.getInteractor(), modelledParticipant, map);
            }
        }
    }

    private static void delegateMapMaintenance(Interactor interactor, ModelledParticipant modelledParticipant, Map<String, ModelledComparableParticipant> map) {
        String interactorId = extractIdentifier(interactor);
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
                modelledComparableParticipant.setInteractorType(interactor.getInteractorType());
                if (modelledParticipant.getStoichiometry() != null) {
                    modelledComparableParticipant.setStoichiometry(modelledParticipant.getStoichiometry().getMinValue());
                }
                map.put(interactorId, modelledComparableParticipant);
            }
        }
    }

    private static String extractIdentifier(Interactor interactor){
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

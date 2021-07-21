package psidev.psi.mi.jami.utils;

import psidev.psi.mi.jami.model.*;

import java.util.*;

public class ComplexUtils {

    public static void maintainProteinComparableParticipantMap(Map<String, ModelledComparableParticipant> map, ModelledParticipant... modelledParticipants) {
        if (map == null) {
            map = new HashMap<String, ModelledComparableParticipant>();
        }
        for (ModelledParticipant modelledParticipant : modelledParticipants) {
            if (modelledParticipant.getInteractor() instanceof Protein) {
                String preferredIdentifier = ((Protein) modelledParticipant.getInteractor()).getPreferredIdentifier().getId();
                delegateMapMaintenance(preferredIdentifier, modelledParticipant, map);
            } else if (modelledParticipant.getInteractor() instanceof InteractorPool) {
                Iterator<Interactor> poolIterator = ((InteractorPool) modelledParticipant.getInteractor()).iterator();
                while (poolIterator.hasNext()) {
                    Interactor interactor = poolIterator.next();
                    if (interactor instanceof Protein) {
                        String preferredIdentifier = interactor.getPreferredIdentifier().getId();
                        delegateMapMaintenance(preferredIdentifier, modelledParticipant, map);
                    }
                }
            }
        }
    }

    private static void delegateMapMaintenance(String preferredIdentifier, ModelledParticipant modelledParticipant, Map<String, ModelledComparableParticipant> map) {
        if (map.get(preferredIdentifier) != null) {
            ModelledComparableParticipant modelledComparableParticipant = map.get(preferredIdentifier);
            if (modelledParticipant.getStoichiometry() != null) {
                int addedStoichiometry = modelledComparableParticipant.getStoichiometry() + modelledParticipant.getStoichiometry().getMinValue();
                modelledComparableParticipant.setStoichiometry(addedStoichiometry);
            }
        } else {
            ModelledComparableParticipant modelledComparableParticipant = new ModelledComparableParticipant();
            modelledComparableParticipant.setInteractorPreferredIdentifier(preferredIdentifier);
            if (modelledParticipant.getStoichiometry() != null) {
                modelledComparableParticipant.setStoichiometry(modelledParticipant.getStoichiometry().getMinValue());
            }
            map.put(preferredIdentifier, modelledComparableParticipant);
        }
    }

    /**
     * Expands the given complex participant.
     * Changes/expands the stoichiometry in expanded participants.
     * Inherit the features in expanded participants.
     * Expanded participants created are new instances.
     */
    public static Collection<ModelledParticipant> expandComplexIntoParticipants(ModelledParticipant parentParticipant) {

        Collection<ModelledParticipant> expandedModelledParticipants = new ArrayList<>();
        if (parentParticipant.getInteractor() instanceof Complex) {
            Complex complex = (Complex) parentParticipant.getInteractor();
            for (ModelledParticipant complexParticipant : complex.getParticipants()) {

                // clone as we do not want to change the stoichiometry of interactor complex participants
                ModelledParticipant expandedModelledParticipant = (ModelledParticipant) CommonUtils.cloneAnObject(complexParticipant);

                // expand stoichiometry
                int minStoichiometry = 0;
                int maxStoichiometry = 0;
                Stoichiometry expandedStoichiometry = null;
                if (complexParticipant.getStoichiometry() != null && parentParticipant.getStoichiometry() != null) {
                    minStoichiometry = (complexParticipant.getStoichiometry().getMinValue())
                            *
                            (parentParticipant.getStoichiometry().getMinValue());
                    maxStoichiometry = (complexParticipant.getStoichiometry().getMaxValue())
                            *
                            (parentParticipant.getStoichiometry().getMaxValue());
                    Class stoichiometryClass = expandedModelledParticipant.getStoichiometry().getClass();
                    try {
                        expandedStoichiometry = (Stoichiometry) stoichiometryClass.getConstructor(Integer.TYPE, Integer.TYPE).newInstance(minStoichiometry, maxStoichiometry);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                expandedModelledParticipant.setStoichiometry(expandedStoichiometry);
                expandedModelledParticipant.getFeatures().addAll(parentParticipant.getFeatures());
                expandedModelledParticipants.add(expandedModelledParticipant);
            }
            return expandedModelledParticipants;
        }

        // return back the participant if not complex
        expandedModelledParticipants.add(parentParticipant);
        return expandedModelledParticipants;
    }
}

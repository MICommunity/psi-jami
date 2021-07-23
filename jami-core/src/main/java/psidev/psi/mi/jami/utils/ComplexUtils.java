package psidev.psi.mi.jami.utils;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultModelledParticipant;

import java.util.*;

public class ComplexUtils {

    public static void maintainProteinComparableParticipantMap(Map<String, ModelledComparableParticipant> map, ModelledParticipant... modelledParticipants) {
        if (map == null) {
            map = new HashMap<String, ModelledComparableParticipant>();
        }
        for (ModelledParticipant modelledParticipant : modelledParticipants) {
            if (modelledParticipant.getInteractor() instanceof Protein) {
                String proteinId = extractIdentifier((Protein) modelledParticipant.getInteractor());
                if(proteinId!=null) {
                    delegateMapMaintenance(proteinId, modelledParticipant, map);
                }
            } else if (modelledParticipant.getInteractor() instanceof InteractorPool) {
                Iterator<Interactor> poolIterator = ((InteractorPool) modelledParticipant.getInteractor()).iterator();
                while (poolIterator.hasNext()) {
                    Interactor interactor = poolIterator.next();
                    if (interactor instanceof Protein) {
                        String proteinId = extractIdentifier((Protein)interactor);
                        if(proteinId!=null) {
                            delegateMapMaintenance(proteinId, modelledParticipant, map);
                        }
                    }
                }
            }
        }
    }

    private static void delegateMapMaintenance(String proteinId, ModelledParticipant modelledParticipant, Map<String, ModelledComparableParticipant> map) {
        if (map.get(proteinId) != null) {
            ModelledComparableParticipant modelledComparableParticipant = map.get(proteinId);
            if (modelledParticipant.getStoichiometry() != null) {
                int addedStoichiometry = modelledComparableParticipant.getStoichiometry() + modelledParticipant.getStoichiometry().getMinValue();
                modelledComparableParticipant.setStoichiometry(addedStoichiometry);
            }
        } else {
            ModelledComparableParticipant modelledComparableParticipant = new ModelledComparableParticipant();
            modelledComparableParticipant.setProteinId(proteinId);
            if (modelledParticipant.getStoichiometry() != null) {
                modelledComparableParticipant.setStoichiometry(modelledParticipant.getStoichiometry().getMinValue());
            }
            map.put(proteinId, modelledComparableParticipant);
        }
    }

    private static String extractIdentifier(Protein protein){
        String id=null;
        if(protein.getUniprotkb()!=null&&!protein.getUniprotkb().isEmpty()){
            id=protein.getUniprotkb();
        }else {
            String ac=CommonUtils.extractIntactAcFromIdentifier(protein.getIdentifiers());
            if (ac != null && !ac.isEmpty()) {
              id=ac;
            }
        }

        return id;
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
                    Class stoichiometryClass = complexParticipant.getStoichiometry().getClass();
                    try {
                        expandedStoichiometry = (Stoichiometry) stoichiometryClass.getConstructor(Integer.TYPE, Integer.TYPE).newInstance(minStoichiometry, maxStoichiometry);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                DefaultModelledParticipant defaultModelledParticipant=new DefaultModelledParticipant(complexParticipant.getInteractor(),expandedStoichiometry);
                expandedModelledParticipants.add(defaultModelledParticipant);
            }
            return expandedModelledParticipants;
        }

        // return back the participant if not complex
        expandedModelledParticipants.add(parentParticipant);
        return expandedModelledParticipants;
    }
}

package psidev.psi.mi.jami.utils;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultExperiment;
import psidev.psi.mi.jami.model.impl.DefaultNamedExperiment;
import psidev.psi.mi.jami.model.impl.DefaultOrganism;
import psidev.psi.mi.jami.model.impl.DefaultPublication;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for experiment
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/02/13</pre>
 */
public class ExperimentUtils {

    /**
     * <p>createUnknownBasicExperiment</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public static Experiment createUnknownBasicExperiment(){
        return new DefaultExperiment(PublicationUtils.createUnknownBasicPublication(), CvTermUtils.createUnspecifiedMethod());
    }

    /**
     * <p>createExperimentWithoutPublication</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public static Experiment createExperimentWithoutPublication(){
        return new DefaultExperiment(null, CvTermUtils.createUnspecifiedMethod());
    }

    /**
     * <p>createBasicExperimentForModelledInteractions</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public static Experiment createBasicExperimentForModelledInteractions(){
        return new DefaultExperiment(PublicationUtils.createBasicPublicationForModelledInteractions(), CvTermUtils.createUnspecifiedMethod());
    }

    /**
     * <p>createBasicExperimentForComplexes</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public static Experiment createBasicExperimentForComplexes(){
        return new DefaultExperiment(PublicationUtils.createBasicPublicationForModelledInteractions(), CvTermUtils.createMICvTerm(Experiment.INFERRED_BY_CURATOR, Experiment.INFERRED_BY_CURATOR_MI));
    }

    /**
     * <p>createBasicExperimentForComplexes</p>
     *
     * @param taxid a int.
     * @return a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public static Experiment createBasicExperimentForComplexes(int taxid){
        Experiment experiment = new DefaultExperiment(PublicationUtils.createBasicPublicationForModelledInteractions(), CvTermUtils.createMICvTerm(Experiment.INFERRED_BY_CURATOR,Experiment.INFERRED_BY_CURATOR_MI));
        experiment.setHostOrganism(new DefaultOrganism(taxid));
        return experiment;
    }

    /**
     * <p>createBasicExperimentForComplexes</p>
     *
     * @param taxid a int.
     * @param name a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public static Experiment createBasicExperimentForComplexes(int taxid, String name){
        Experiment experiment = new DefaultExperiment(PublicationUtils.createBasicPublicationForModelledInteractions(), CvTermUtils.createMICvTerm(Experiment.INFERRED_BY_CURATOR,Experiment.INFERRED_BY_CURATOR_MI));
        experiment.setHostOrganism(new DefaultOrganism(taxid, name));
        return experiment;
    }
    
    /**
     * <p>getPubmedId</p>
     *
     * @param exp a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @return a {@link java.lang.String} object.
     */
    public static String getPubmedId(Experiment exp){
       if (exp == null){
          return null; 
       }
        else if (exp.getPublication() == null){
           return null; 
       }
        else{
           return exp.getPublication().getPubmedId();
       }
    }

    /**
     * <p>getDoiId</p>
     *
     * @param exp a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @return a {@link java.lang.String} object.
     */
    public static String getDoiId(Experiment exp){
        if (exp == null){
            return null;
        }
        else if (exp.getPublication() == null){
            return null;
        }
        else{
            return exp.getPublication().getDoi();
        }
    }

    /**
     * <p>getPubmedReference</p>
     *
     * @param exp a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @return a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public static Xref getPubmedReference(Experiment exp){
        if (exp == null){
            return null;
        }
        else{
            return PublicationUtils.getPubmedReference(exp.getPublication());
        }
    }

    /**
     * <p>getDoiReference</p>
     *
     * @param exp a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @return a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public static Xref getDoiReference(Experiment exp){
        if (exp == null){
            return null;
        }
        else{
            return PublicationUtils.getDoiReference(exp.getPublication());
        }
    }

    /**
     * <p>createExperiment</p>
     *
     * @param pubmedId a {@link java.lang.String} object.
     * @param interactionDetectionMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param hostOrganism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @return a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public static Experiment createExperiment(String pubmedId, CvTerm interactionDetectionMethod,
                                              Organism hostOrganism) {
        Experiment experiment = new DefaultExperiment(new DefaultPublication(pubmedId), interactionDetectionMethod);
        experiment.setHostOrganism(hostOrganism);

        return experiment;
    }

    /**
     * <p>createExperiment</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param pubmedId a {@link java.lang.String} object.
     * @param interactionDetectionMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param hostOrganism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @return a {@link psidev.psi.mi.jami.model.NamedExperiment} object.
     */
    public static NamedExperiment createExperiment(String name, String pubmedId, CvTerm interactionDetectionMethod,
                                              Organism hostOrganism) {
        NamedExperiment experiment = new DefaultNamedExperiment(new DefaultPublication(pubmedId), interactionDetectionMethod);
        experiment.setHostOrganism(hostOrganism);
        experiment.setShortName(name);

        return experiment;
    }

    /**
     * Compare all participant identification methods in all participant evidences from all the interactions evidences.
     *
     * @param exp a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @return the most frequent participant identification method (first one if several have same frequency), null if no participant identification methods
     */
    public static CvTerm extractMostCommonParticipantDetectionMethodFrom(Experiment exp){
        Map<CvTerm,Integer> frequencyMap = new HashMap<CvTerm, Integer>(exp.getInteractionEvidences().size());
        int max=0;
        CvTerm commonDetectionMethod = null;
        if (exp != null && !exp.getInteractionEvidences().isEmpty()){
            for (InteractionEvidence interaction : exp.getInteractionEvidences()){
                for (ParticipantEvidence p : interaction.getParticipants()){
                    for (CvTerm identification : p.getIdentificationMethods()){
                        if (frequencyMap.containsKey(identification)){
                            int frequency = frequencyMap.get(identification)+1;
                            frequencyMap.put(identification, frequency);

                            if (max < frequency){
                               max = frequency;
                                commonDetectionMethod = identification;
                            }
                        }
                        else{
                            frequencyMap.put(identification, 1);
                            max = 1;
                            commonDetectionMethod = identification;
                        }
                    }
                }
            }
        }

        frequencyMap.clear();

        return commonDetectionMethod;
    }
}

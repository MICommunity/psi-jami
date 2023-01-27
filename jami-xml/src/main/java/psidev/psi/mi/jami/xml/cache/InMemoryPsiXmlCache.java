package psidev.psi.mi.jami.xml.cache;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.model.extension.AbstractAvailability;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlEntity;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeatureEvidence;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlModelledInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeature;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipant;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipantEvidence;

import java.util.HashMap;
import java.util.Map;

/**
 * PsiXmlIdCache that stores objects in memory using a map
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>06/11/13</pre>
 */
public class InMemoryPsiXmlCache implements PsiXmlIdCache {

    private PsiXmlVersion version;
    private Map<Integer, Complex> mapOfReferencedComplexes;
    private Map<Integer, ModelledEntity> mapOfReferencedComplexParticipants;
    private Map<Integer, ModelledFeature> mapOfReferencedComplexFeatures;
    private Map<Integer, Interaction> mapOfReferencedInteractions;
    private Map<Integer, Experiment> mapOfReferencedExperiments;
    private Map<Integer, Interactor> mapOfReferencedInteractors;
    private Map<Integer, Entity> mapOfReferencedParticipants;
    private Map<Integer, Feature> mapOfReferencedFeatures;
    private Map<Integer, VariableParameterValue> mapOfReferencedVariableParameterValues;
    private Map<Integer, AbstractAvailability> mapOfReferencedAvailabilities;

    /**
     * <p>Constructor for InMemoryPsiXmlCache.</p>
     */
    public InMemoryPsiXmlCache(PsiXmlVersion version){
        this.version = version;
        this.mapOfReferencedAvailabilities = new HashMap<Integer, AbstractAvailability>();
        this.mapOfReferencedExperiments = new HashMap<Integer, Experiment>();
        this.mapOfReferencedFeatures = new HashMap<Integer, Feature>();
        this.mapOfReferencedInteractions = new HashMap<Integer, Interaction>();
        this.mapOfReferencedInteractors = new HashMap<Integer, Interactor>();
        this.mapOfReferencedParticipants = new HashMap<Integer, Entity>();
        this.mapOfReferencedVariableParameterValues = new HashMap<Integer, VariableParameterValue>();
        this.mapOfReferencedComplexes = new HashMap<Integer, Complex>();
        this.mapOfReferencedComplexParticipants = new HashMap<Integer, ModelledEntity>();
        this.mapOfReferencedComplexFeatures = new HashMap<Integer, ModelledFeature>();
    }

    /** {@inheritDoc} */
    @Override
    public void registerAvailability(int id, AbstractAvailability object) {
        this.mapOfReferencedAvailabilities.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public AbstractAvailability getAvailability(int id) {
        return this.mapOfReferencedAvailabilities.get(id);
    }

    /** {@inheritDoc} */
    @Override
    public void registerExperiment(int id, Experiment object) {
        this.mapOfReferencedExperiments.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public Experiment getExperiment(int id) {
        return this.mapOfReferencedExperiments.get(id);
    }

    /** {@inheritDoc} */
    @Override
    public void registerInteraction(int id, Interaction object) {
        this.mapOfReferencedInteractions.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public Interaction getInteraction(int id) {
        return this.mapOfReferencedInteractions.get(id);
    }

    /** {@inheritDoc} */
    @Override
    public void registerInteractor(int id, Interactor object) {
        this.mapOfReferencedInteractors.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public Interactor getInteractor(int id) {
        return this.mapOfReferencedInteractors.get(id);
    }

    /** {@inheritDoc} */
    @Override
    public void registerParticipant(int id, Entity object) {
        this.mapOfReferencedParticipants.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public Entity getParticipant(int id) {
        return this.mapOfReferencedParticipants.get(id);
    }

    /** {@inheritDoc} */
    @Override
    public void registerFeature(int id, Feature object) {
        this.mapOfReferencedFeatures.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public Feature getFeature(int id) {
        return this.mapOfReferencedFeatures.get(id);
    }

    /** {@inheritDoc} */
    @Override
    public void registerComplexParticipant(int id, ModelledEntity object) {
        this.mapOfReferencedComplexParticipants.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public ModelledEntity getComplexParticipant(int id) {
        ModelledEntity object = this.mapOfReferencedComplexParticipants.get(id);
        if (object != null){
            return object;
        }
        else{
            Entity object2 = this.mapOfReferencedParticipants.get(id);
            if (object2 instanceof ModelledEntity){
                return (ModelledEntity)object2;
            }
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void registerComplexFeature(int id, ModelledFeature object) {
        this.mapOfReferencedComplexFeatures.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public ModelledFeature getComplexFeature(int id) {
        ModelledFeature object = this.mapOfReferencedComplexFeatures.get(id);
        if (object != null){
            return object;
        }
        else{
            Feature object2 = this.mapOfReferencedFeatures.get(id);
            if (object2 instanceof ModelledFeature){
                return (ModelledFeature)object2;
            }
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void registerComplex(int id, Complex object) {
        this.mapOfReferencedComplexes.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public Complex getComplex(int id) {
        Complex object = this.mapOfReferencedComplexes.get(id);
        if (object != null){
            return object;
        }
        else{
            Interaction object2 = this.mapOfReferencedInteractions.get(id);
            if (object2 instanceof Complex){
                return (Complex)object2;
            }
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void registerVariableParameterValue(int id, VariableParameterValue object) {
        this.mapOfReferencedVariableParameterValues.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public VariableParameterValue getVariableParameterValue(int id) {
        return this.mapOfReferencedVariableParameterValues.get(id);
    }


    /** {@inheritDoc} */
    @Override
    public void clear() {
        this.mapOfReferencedVariableParameterValues.clear();
        this.mapOfReferencedFeatures.clear();
        this.mapOfReferencedParticipants.clear();
        this.mapOfReferencedInteractions.clear();
        this.mapOfReferencedInteractors.clear();
        this.mapOfReferencedAvailabilities.clear();
        this.mapOfReferencedExperiments.clear();
        this.mapOfReferencedComplexes.clear();
        this.mapOfReferencedComplexParticipants.clear();
        this.mapOfReferencedComplexFeatures.clear();
    }

    /** {@inheritDoc} */
    @Override
    public void close() {
        clear();
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsExperiment(int id) {
        return this.mapOfReferencedExperiments.containsKey(id);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsAvailability(int id) {
        return this.mapOfReferencedAvailabilities.containsKey(id);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsInteraction(int id) {
        return this.mapOfReferencedInteractions.containsKey(id);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsInteractor(int id) {
        return this.mapOfReferencedInteractors.containsKey(id);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsParticipant(int id) {
        return this.mapOfReferencedParticipants.containsKey(id);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsFeature(int id) {
        return this.mapOfReferencedFeatures.containsKey(id);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsVariableParameter(int id) {
        return this.mapOfReferencedVariableParameterValues.containsKey(id);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsComplex(int id) {
        return this.mapOfReferencedComplexes.containsKey(id);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsComplexParticipant(int id) {
        return this.mapOfReferencedComplexParticipants.containsKey(id);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsComplexFeature(int id) {
        return this.mapOfReferencedComplexFeatures.containsKey(id);
    }


    /** {@inheritDoc} */
    @Override
    public ModelledFeature registerModelledFeatureLoadedFrom(Feature f) {
        Entity parent = f.getParticipant();
        ModelledEntity registeredEntity = null;

        if (parent != null){
            registeredEntity = registerModelledParticipantLoadedFrom(parent);
            if (containsComplexFeature(((ExtendedPsiXmlFeature)f).getId())){
                return getComplexFeature(((ExtendedPsiXmlFeature)f).getId());
            }
        }

        if (f instanceof ExtendedPsiXmlFeatureEvidence){
            switch (version) {
                case v3_0_0:
                    return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlFeatureEvidenceWrapper((ExtendedPsiXmlFeatureEvidence)f, registeredEntity);
                case v2_5_3:
                    return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlFeatureEvidenceWrapper((ExtendedPsiXmlFeatureEvidence)f, registeredEntity);
                case v2_5_4:
                default:
                    return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlFeatureEvidenceWrapper((ExtendedPsiXmlFeatureEvidence)f, registeredEntity);
            }
        }
        else if (f instanceof ModelledFeature){
            return (ModelledFeature)f;
        }
        else {
            switch (version) {
                case v3_0_0:
                    return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlFeatureWrapper((ExtendedPsiXmlFeature)f, registeredEntity);
                case v2_5_3:
                    return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlFeatureWrapper((ExtendedPsiXmlFeature)f, registeredEntity);
                case v2_5_4:
                default:
                    return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlFeatureWrapper((ExtendedPsiXmlFeature)f, registeredEntity);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public ModelledEntity registerModelledParticipantLoadedFrom(Entity f) {
        if (f instanceof ParticipantCandidate){
            ParticipantPool parent = ((ParticipantCandidate)f).getParentPool();
            ModelledParticipantPool registeredEntity = null;

            if (parent != null){
                registeredEntity = (ModelledParticipantPool)registerModelledParticipantLoadedFrom(parent);
                if (containsComplexParticipant(((ExtendedPsiXmlEntity) f).getId())){
                    return getComplexParticipant(((ExtendedPsiXmlEntity) f).getId());
                }
            }

            if (f instanceof ExperimentalParticipantCandidate){
                switch (version) {
                    case v3_0_0:
                        return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlExperimentalParticipantCandidateWrapper((ExperimentalParticipantCandidate)f, registeredEntity);
                    case v2_5_3:
                        return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlExperimentalParticipantCandidateWrapper((ExperimentalParticipantCandidate)f, registeredEntity);
                    case v2_5_4:
                    default:
                        return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlExperimentalParticipantCandidateWrapper((ExperimentalParticipantCandidate)f, registeredEntity);
                }
            }
            else if (f instanceof ModelledParticipantCandidate){
                return (ModelledParticipantCandidate)f;
            }
            else {
                switch (version) {
                    case v3_0_0:
                        return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantCandidateWrapper((ParticipantCandidate)f, registeredEntity);
                    case v2_5_3:
                        return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlParticipantCandidateWrapper((ParticipantCandidate)f, registeredEntity);
                    case v2_5_4:
                    default:
                        return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlParticipantCandidateWrapper((ParticipantCandidate)f, registeredEntity);
                }
            }
        }
        else {
            Interaction parent = ((Participant)f).getInteraction();
            Complex registeredComplex = null;

            if (parent != null){
                registeredComplex = registerComplexLoadedFrom(parent);
                if (containsComplexParticipant(((ExtendedPsiXmlEntity) f).getId())){
                    return getComplexParticipant(((ExtendedPsiXmlEntity) f).getId());
                }
            }

            if (f instanceof ExperimentalParticipantPool){
                switch (version) {
                    case v3_0_0:
                        return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlExperimentalParticipantPoolWrapper(
                                (ExperimentalParticipantPool)f,
                                (psidev.psi.mi.jami.xml.model.extension.xml300.XmlInteractionEvidenceComplexWrapper)registeredComplex);
                    case v2_5_3:
                        return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlExperimentalParticipantPoolWrapper(
                                (ExperimentalParticipantPool)f,
                                (psidev.psi.mi.jami.xml.model.extension.xml253.XmlInteractionEvidenceComplexWrapper)registeredComplex);
                    case v2_5_4:
                    default:
                        return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlExperimentalParticipantPoolWrapper(
                                (ExperimentalParticipantPool)f,
                                (psidev.psi.mi.jami.xml.model.extension.xml254.XmlInteractionEvidenceComplexWrapper)registeredComplex);
                }
            }
            else if (f instanceof ExtendedPsiXmlParticipantEvidence){
                switch (version) {
                    case v3_0_0:
                        return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantEvidenceWrapper(
                                (ExtendedPsiXmlParticipantEvidence)f,
                                (psidev.psi.mi.jami.xml.model.extension.xml300.XmlInteractionEvidenceComplexWrapper)registeredComplex);
                    case v2_5_3:
                        return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlParticipantEvidenceWrapper(
                                (ExtendedPsiXmlParticipantEvidence)f,
                                (psidev.psi.mi.jami.xml.model.extension.xml253.XmlInteractionEvidenceComplexWrapper)registeredComplex);
                    case v2_5_4:
                    default:
                        return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlParticipantEvidenceWrapper(
                                (ExtendedPsiXmlParticipantEvidence)f,
                                (psidev.psi.mi.jami.xml.model.extension.xml254.XmlInteractionEvidenceComplexWrapper)registeredComplex);
                }
            }
            else if (f instanceof ModelledParticipant){
                return (ModelledParticipant)f;
            }
            else if (f instanceof ParticipantPool){
                switch (version) {
                    case v3_0_0:
                        return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantPoolWrapper((ParticipantPool)f, registeredComplex);
                    case v2_5_3:
                        return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlParticipantPoolWrapper((ParticipantPool)f, registeredComplex);
                    case v2_5_4:
                    default:
                        return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlParticipantPoolWrapper((ParticipantPool)f, registeredComplex);
                }
            }
            else {
                switch (version) {
                    case v3_0_0:
                        return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantWrapper((ExtendedPsiXmlParticipant)f, registeredComplex);
                    case v2_5_3:
                        return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlParticipantWrapper((ExtendedPsiXmlParticipant)f, registeredComplex);
                    case v2_5_4:
                    default:
                        return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlParticipantWrapper((ExtendedPsiXmlParticipant)f, registeredComplex);
                }
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public Complex registerComplexLoadedFrom(Interaction f) {

        // convert interaction evidence in a complex
        if (f instanceof ExtendedPsiXmlInteractionEvidence){
            switch (version) {
                case v3_0_0:
                    return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlInteractionEvidenceComplexWrapper((ExtendedPsiXmlInteractionEvidence)f);
                case v2_5_3:
                    return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlInteractionEvidenceComplexWrapper((ExtendedPsiXmlInteractionEvidence)f);
                case v2_5_4:
                default:
                    return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlInteractionEvidenceComplexWrapper((ExtendedPsiXmlInteractionEvidence)f);
            }
        }
        // wrap modelled interaction
        else if (f instanceof ExtendedPsiXmlModelledInteraction){
            switch (version) {
                case v3_0_0:
                    return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlModelledInteractionComplexWrapper((ExtendedPsiXmlModelledInteraction)f);
                case v2_5_3:
                    return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlModelledInteractionComplexWrapper((ExtendedPsiXmlModelledInteraction)f);
                case v2_5_4:
                default:
                    return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlModelledInteractionComplexWrapper((ExtendedPsiXmlModelledInteraction)f);
            }
        }
        // wrap basic interaction
        else if (f instanceof ExtendedPsiXmlInteraction){
            switch (version) {
                case v3_0_0:
                    return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlBasicInteractionComplexWrapper((ExtendedPsiXmlInteraction)f);
                case v2_5_3:
                    return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlBasicInteractionComplexWrapper((ExtendedPsiXmlInteraction)f);
                case v2_5_4:
                default:
                    return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlBasicInteractionComplexWrapper((ExtendedPsiXmlInteraction)f);
            }
        }
        else{
            return null;
        }
    }
}

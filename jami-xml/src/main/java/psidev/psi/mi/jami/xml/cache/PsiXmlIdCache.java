package psidev.psi.mi.jami.xml.cache;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.model.extension.AbstractAvailability;

/**
 * Index that can retrieve a MI object given its id
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>06/11/13</pre>
 */
public interface PsiXmlIdCache {

    /**
     * Register an availability with an id
     *
     * @param id a int.
     * @param object a {@link psidev.psi.mi.jami.xml.model.extension.AbstractAvailability} object.
     */
    public void registerAvailability(int id, AbstractAvailability object);

    /**
     * <p>getAvailability.</p>
     *
     * @param id a int.
     * @return the availability registered with this id, null if it does not exist
     */
    public AbstractAvailability getAvailability(int id);

    /**
     * Register an experiment with an id
     *
     * @param id a int.
     * @param object a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public void registerExperiment(int id, Experiment object);

    /**
     * <p>getExperiment.</p>
     *
     * @param id a int.
     * @return the experiment registered with this id, null if it does not exist
     */
    public Experiment getExperiment(int id);

    /**
     * Register an interaction with an id
     *
     * @param id a int.
     * @param object a {@link psidev.psi.mi.jami.model.Interaction} object.
     */
    public void registerInteraction(int id, Interaction object);

    /**
     * <p>getInteraction.</p>
     *
     * @param id a int.
     * @return the interaction registered with this id, null if it does not exist
     */
    public Interaction getInteraction(int id);

    /**
     * Register an interactor with an id
     *
     * @param id a int.
     * @param object a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public void registerInteractor(int id, Interactor object);

    /**
     * <p>getInteractor.</p>
     *
     * @param id a int.
     * @return the interactor registered with this id, null if it does not exist
     */
    public Interactor getInteractor(int id);

    /**
     * Register a participant with an id
     *
     * @param id a int.
     * @param object a {@link psidev.psi.mi.jami.model.Entity} object.
     */
    public void registerParticipant(int id, Entity object);

    /**
     * <p>getParticipant.</p>
     *
     * @param id a int.
     * @return the participant registered with this id, null if it does not exist
     */
    public Entity getParticipant(int id);

    /**
     * Register a feature with an id
     *
     * @param id a int.
     * @param object a {@link psidev.psi.mi.jami.model.Feature} object.
     */
    public void registerFeature(int id, Feature object);

    /**
     * <p>getFeature.</p>
     *
     * @param id a int.
     * @return the feature registered with this id, null if it does not exist
     */
    public Feature getFeature(int id);

    /**
     * Register a participant involved in a complex with an id
     *
     * @param id a int.
     * @param object a {@link psidev.psi.mi.jami.model.ModelledEntity} object.
     */
    public void registerComplexParticipant(int id, ModelledEntity object);

    /**
     * <p>getComplexParticipant.</p>
     *
     * @param id a int.
     * @return the participant registered with this id, null if it does not exist
     */
    public ModelledEntity getComplexParticipant(int id);

    /**
     * Register a complex feature with an id
     *
     * @param id a int.
     * @param object a {@link psidev.psi.mi.jami.model.ModelledFeature} object.
     */
    public void registerComplexFeature(int id, ModelledFeature object);

    /**
     * <p>getComplexFeature.</p>
     *
     * @param id a int.
     * @return the feature registered with this id, null if it does not exist
     */
    public ModelledFeature getComplexFeature(int id);

    /**
     * Register a complex with an id
     *
     * @param id a int.
     * @param object a {@link psidev.psi.mi.jami.model.Complex} object.
     */
    public void registerComplex(int id, Complex object);

    /**
     * <p>getComplex.</p>
     *
     * @param id a int.
     * @return the complex registered with this id, null if it does not exist
     */
    public Complex getComplex(int id);

    /**
     * Register a variable parameter value with an id
     *
     * @param id a int.
     * @param object a {@link psidev.psi.mi.jami.model.VariableParameterValue} object.
     */
    public void registerVariableParameterValue(int id, VariableParameterValue object);

    /**
     * <p>getVariableParameterValue.</p>
     *
     * @param id a int.
     * @return the variable parameter value registered with this id, null if it does not exist
     */
    public VariableParameterValue getVariableParameterValue(int id);

    /**
     * Clear all existing records
     */
    public void clear();

    /**
     * Close cache
     */
    public void close();

    /**
     * To know if it contains a specific id
     *
     * @param id a int.
     * @return a boolean.
     */
    public boolean containsExperiment(int id);

    /**
     * To know if it contains a specific id
     *
     * @param id a int.
     * @return a boolean.
     */
    public boolean containsAvailability(int id);

    /**
     * To know if it contains a specific id
     *
     * @param id a int.
     * @return a boolean.
     */
    public boolean containsInteraction(int id);

    /**
     * To know if it contains a specific id
     *
     * @param id a int.
     * @return a boolean.
     */
    public boolean containsInteractor(int id);

    /**
     * To know if it contains a specific id
     *
     * @param id a int.
     * @return a boolean.
     */
    public boolean containsParticipant(int id);

    /**
     * To know if it contains a specific id
     *
     * @param id a int.
     * @return a boolean.
     */
    public boolean containsFeature(int id);

    /**
     * To know if it contains a specific id
     *
     * @param id a int.
     * @return a boolean.
     */
    public boolean containsVariableParameter(int id);

    /**
     * To know if it contains a specific id
     *
     * @param id a int.
     * @return a boolean.
     */
    public boolean containsComplex(int id);

    /**
     * To know if it contains a specific id
     *
     * @param id a int.
     * @return a boolean.
     */
    public boolean containsComplexParticipant(int id);

    /**
     * To know if it contains a specific id
     *
     * @param id a int.
     * @return a boolean.
     */
    public boolean containsComplexFeature(int id);

    /**
     * <p>registerModelledFeatureLoadedFrom.</p>
     *
     * @param f : the loaded feature
     * @return the converted and registered modelled feature
     */
    public ModelledFeature registerModelledFeatureLoadedFrom(Feature f);

    /**
     * <p>registerModelledParticipantLoadedFrom.</p>
     *
     * @param f : the loaded participant
     * @return the converted and registered modelled participant
     */
    public ModelledEntity registerModelledParticipantLoadedFrom(Entity f);

    /**
     * <p>registerComplexLoadedFrom.</p>
     *
     * @param f : the loaded interaction
     * @return the converted and registered complex
     */
    public Complex registerComplexLoadedFrom(Interaction f);
}

package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.CvTermUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for Experiment
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the Experiment object is a complex object.
 * To compare Experiment objects, you can use some comparators provided by default:
 * - DefaultExperimentComparator
 * - UnambiguousExperimentComparator
 * - DefaultECuratedExperimentComparator
 * - UnambiguousCuratedExperimentComparator
 * - ExperimentComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/01/13</pre>
 */
public class DefaultExperiment implements Experiment {

    private Publication publication;
    private Collection<Xref> xrefs;
    private Collection<Annotation> annotations;
    private CvTerm interactionDetectionMethod;
    private Organism hostOrganism;
    private Collection<InteractionEvidence> interactions;

    private Collection<Confidence> confidences;
    private Collection<VariableParameter> variableParameters;

    /**
     * <p>Constructor for DefaultExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public DefaultExperiment(Publication publication){

        this.publication = publication;
        this.interactionDetectionMethod = CvTermUtils.createUnspecifiedMethod();

    }

    /**
     * <p>Constructor for DefaultExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param interactionDetectionMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultExperiment(Publication publication, CvTerm interactionDetectionMethod){

        this.publication = publication;
        if (interactionDetectionMethod == null){
            this.interactionDetectionMethod = CvTermUtils.createUnspecifiedMethod();
        }
        else {
            this.interactionDetectionMethod = interactionDetectionMethod;
        }
    }

    /**
     * <p>Constructor for DefaultExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param interactionDetectionMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultExperiment(Publication publication, CvTerm interactionDetectionMethod, Organism organism){
        this(publication, interactionDetectionMethod);
        this.hostOrganism = organism;
    }

    /**
     * <p>initialiseXrefs</p>
     */
    protected void initialiseXrefs(){
        this.xrefs = new ArrayList<Xref>();
    }

    /**
     * <p>initialiseAnnotations</p>
     */
    protected void initialiseAnnotations(){
        this.annotations = new ArrayList<Annotation>();
    }

    /**
     * <p>initialiseInteractions</p>
     */
    protected void initialiseInteractions(){
        this.interactions = new ArrayList<InteractionEvidence>();
    }

    /**
     * <p>initialiseXrefsWith</p>
     *
     * @param xrefs a {@link java.util.Collection} object.
     */
    protected void initialiseXrefsWith(Collection<Xref> xrefs){
        if (xrefs == null){
            this.xrefs = Collections.EMPTY_LIST;
        }
        else{
            this.xrefs = xrefs;
        }
    }

    /**
     * <p>initialiseAnnotationsWith</p>
     *
     * @param annotations a {@link java.util.Collection} object.
     */
    protected void initialiseAnnotationsWith(Collection<Annotation> annotations){
        if (annotations == null){
            this.annotations = Collections.EMPTY_LIST;
        }
        else{
            this.annotations = annotations;
        }
    }

    /**
     * <p>initialiseInteractionsWith</p>
     *
     * @param interactionEvidences a {@link java.util.Collection} object.
     */
    protected void initialiseInteractionsWith(Collection<InteractionEvidence> interactionEvidences){
        if (interactionEvidences == null){
            this.interactions = Collections.EMPTY_LIST;
        }
        else{
            this.interactions = interactionEvidences;
        }
    }

    /**
     * <p>initialiseConfidences</p>
     */
    protected void initialiseConfidences(){
        this.confidences = new ArrayList<Confidence>();
    }

    /**
     * <p>initialiseVariableParameters</p>
     */
    protected void initialiseVariableParameters(){
        this.variableParameters = new ArrayList<VariableParameter>();
    }

    /**
     * <p>initialiseConfidencesWith</p>
     *
     * @param confs a {@link java.util.Collection} object.
     */
    protected void initialiseConfidencesWith(Collection<Confidence> confs){
        if (confs == null){
            this.confidences = Collections.EMPTY_LIST;
        }
        else{
            this.confidences = confs;
        }
    }

    /**
     * <p>initialiseVariableParametersWith</p>
     *
     * @param variableParameters a {@link java.util.Collection} object.
     */
    protected void initialiseVariableParametersWith(Collection<VariableParameter> variableParameters){
        if (variableParameters == null){
            this.variableParameters = Collections.EMPTY_LIST;
        }
        else{
            this.variableParameters = variableParameters;
        }
    }

    /**
     * <p>Getter for the field <code>publication</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public Publication getPublication() {
        return this.publication;
    }

    /** {@inheritDoc} */
    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    /** {@inheritDoc} */
    public void setPublicationAndAddExperiment(Publication publication) {
        if (this.publication != null){
           this.publication.removeExperiment(this);
        }

        if (publication != null){
            publication.addExperiment(this);
        }
    }

    /**
     * <p>Getter for the field <code>xrefs</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getXrefs() {
        if (xrefs == null){
            initialiseXrefs();
        }
        return this.xrefs;
    }

    /**
     * <p>Getter for the field <code>annotations</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        if (annotations == null){
           initialiseAnnotations();
        }
        return this.annotations;
    }

    /**
     * <p>Getter for the field <code>confidences</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Confidence> getConfidences() {
        if (confidences == null){
            initialiseConfidences();
        }
        return confidences;
    }

    /**
     * <p>Getter for the field <code>interactionDetectionMethod</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getInteractionDetectionMethod() {
        return this.interactionDetectionMethod;
    }

    /** {@inheritDoc} */
    public void setInteractionDetectionMethod(CvTerm term) {
        if (term == null){
            this.interactionDetectionMethod = CvTermUtils.createUnspecifiedMethod();
        }
        else{
            this.interactionDetectionMethod = term;
        }
    }

    /**
     * <p>Getter for the field <code>hostOrganism</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public Organism getHostOrganism() {
        return this.hostOrganism;
    }

    /** {@inheritDoc} */
    public void setHostOrganism(Organism organism) {
        this.hostOrganism = organism;
    }

    /**
     * <p>getInteractionEvidences</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<InteractionEvidence> getInteractionEvidences() {
        if (interactions == null){
            initialiseInteractions();
        }
        return this.interactions;
    }

    /** {@inheritDoc} */
    public boolean addInteractionEvidence(InteractionEvidence evidence) {
        if (evidence == null){
            return false;
        }

        if (getInteractionEvidences().add(evidence)){
            evidence.setExperiment(this);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean removeInteractionEvidence(InteractionEvidence evidence) {
        if (evidence == null){
            return false;
        }

        if (getInteractionEvidences().remove(evidence)){
            evidence.setExperiment(null);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean addAllInteractionEvidences(Collection<? extends InteractionEvidence> evidences) {
        if (evidences == null){
            return false;
        }

        boolean added = false;
        for (InteractionEvidence ev : evidences){
            if (addInteractionEvidence(ev)){
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    public boolean removeAllInteractionEvidences(Collection<? extends InteractionEvidence> evidences) {
        if (evidences == null){
            return false;
        }

        boolean removed = false;
        for (InteractionEvidence ev : evidences){
            if (removeInteractionEvidence(ev)){
                removed = true;
            }
        }
        return removed;
    }

    /**
     * <p>Getter for the field <code>variableParameters</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<VariableParameter> getVariableParameters() {
        if (variableParameters == null){
            initialiseVariableParameters();
        }
        return variableParameters;
    }

    /** {@inheritDoc} */
    public boolean addVariableParameter(VariableParameter variableParameter) {
        if (variableParameter == null){
            return false;
        }

        if (getVariableParameters().add(variableParameter)){
            variableParameter.setExperiment(this);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean removeVariableParameter(VariableParameter variableParameter) {
        if (variableParameter == null){
            return false;
        }

        if (getVariableParameters().remove(variableParameter)){
            variableParameter.setExperiment(null);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean addAllVariableParameters(Collection<? extends VariableParameter> variableParameters) {
        if (variableParameters == null){
            return false;
        }

        boolean added = false;
        for (VariableParameter param : variableParameters){
            if (addVariableParameter(param)){
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    public boolean removeAllVariableParameters(Collection<? extends VariableParameter> variableParameters) {
        if (variableParameters == null){
            return false;
        }

        boolean removed = false;
        for (VariableParameter param : variableParameters){
            if (removeVariableParameter(param)){
                removed = true;
            }
        }
        return removed;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Experiment: "
                +(getPublication() != null ? getPublication().toString():"no publication")
                + "( " + getInteractionDetectionMethod().toString()
                + (getHostOrganism() != null ? ", " + getHostOrganism().toString():"") + " )";
    }
}

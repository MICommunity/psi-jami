package psidev.psi.mi.jami.xml.model.reference.xml253;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlExperiment;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlExperiment;
import psidev.psi.mi.jami.xml.model.reference.AbstractXmlIdReference;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class for an ModelledInteractionRef
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
public abstract class AbstractExperimentRef extends AbstractXmlIdReference implements ExtendedPsiXmlExperiment {
    private static final Logger logger = Logger.getLogger("AbstractExperimentRef");
    private ExtendedPsiXmlExperiment delegate;

    /**
     * <p>Constructor for AbstractExperimentRef.</p>
     *
     * @param ref a int.
     */
    public AbstractExperimentRef(int ref) {
        super(ref);
    }

    /**
     * <p>getPublication.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public Publication getPublication() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getPublication();
    }

    /** {@inheritDoc} */
    public void setPublication(Publication publication) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        this.delegate.setPublication(publication);
    }

    /** {@inheritDoc} */
    public void setPublicationAndAddExperiment(Publication publication) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        this.delegate.setPublicationAndAddExperiment(publication);
    }

    /**
     * <p>getXrefs.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getXrefs() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getXrefs();
    }

    /**
     * <p>getAnnotations.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getAnnotations();
    }

    /**
     * <p>getConfidences.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Confidence> getConfidences() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getConfidences();
    }

    /**
     * <p>getInteractionDetectionMethod.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getInteractionDetectionMethod() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getInteractionDetectionMethod();
    }

    /** {@inheritDoc} */
    public void setInteractionDetectionMethod(CvTerm term) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        this.delegate.setInteractionDetectionMethod(term);
    }

    /**
     * <p>getHostOrganism.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public Organism getHostOrganism() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getHostOrganism();
    }

    /** {@inheritDoc} */
    public void setHostOrganism(Organism organism) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        this.delegate.setHostOrganism(organism);
    }

    /**
     * <p>getInteractionEvidences.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<InteractionEvidence> getInteractionEvidences() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getInteractionEvidences();
    }

    /** {@inheritDoc} */
    public boolean addInteractionEvidence(InteractionEvidence evidence) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.addInteractionEvidence(evidence);
    }

    /** {@inheritDoc} */
    public boolean removeInteractionEvidence(InteractionEvidence evidence) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.removeInteractionEvidence(evidence);
    }

    /** {@inheritDoc} */
    public boolean addAllInteractionEvidences(Collection<? extends InteractionEvidence> evidences) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.addAllInteractionEvidences(evidences);
    }

    /** {@inheritDoc} */
    public boolean removeAllInteractionEvidences(Collection<? extends InteractionEvidence> evidences) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.removeAllInteractionEvidences(evidences);
    }

    /**
     * <p>getVariableParameters.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<VariableParameter> getVariableParameters() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getVariableParameters();
    }

    /** {@inheritDoc} */
    public boolean addVariableParameter(VariableParameter variableParameter) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.addVariableParameter(variableParameter);
    }

    /** {@inheritDoc} */
    public boolean removeVariableParameter(VariableParameter variableParameter) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.removeVariableParameter(variableParameter);
    }

    /** {@inheritDoc} */
    public boolean addAllVariableParameters(Collection<? extends VariableParameter> variableParameters) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.addAllVariableParameters(variableParameters);
    }

    /** {@inheritDoc} */
    public boolean removeAllVariableParameters(Collection<? extends VariableParameter> variableParameters) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.removeAllVariableParameters(variableParameters);
    }

    /** {@inheritDoc} */
    @Override
    public void setFeatureDetectionMethod(CvTerm method) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        this.delegate.setFeatureDetectionMethod(method);
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getFeatureDetectionMethod() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getFeatureDetectionMethod();
    }

    /** {@inheritDoc} */
    @Override
    public void setParticipantIdentificationMethod(CvTerm method) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        this.delegate.setParticipantIdentificationMethod(method);
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getParticipantIdentificationMethod() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getParticipantIdentificationMethod();
    }

    /** {@inheritDoc} */
    @Override
    public List<Organism> getHostOrganisms() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getHostOrganisms();
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        this.delegate.setId(id);
    }

    /** {@inheritDoc} */
    @Override
    public int getId() {
        return this.delegate != null ? this.delegate.getId() : this.ref;
    }

    /** {@inheritDoc} */
    @Override
    public String getShortName() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getShortName();
    }

    /** {@inheritDoc} */
    @Override
    public void setShortName(String name) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        this.delegate.setShortName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getFullName() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getFullName();
    }

    /** {@inheritDoc} */
    @Override
    public void setFullName(String name) {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        this.delegate.setFullName(name);
    }

    /** {@inheritDoc} */
    @Override
    public <A extends Alias> Collection<A> getAliases() {
        logger.log(Level.WARNING, "The experiment reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseExperimentDelegate();
        }
        return this.delegate.getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Experiment Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
    }
    /**
     * <p>initialiseExperimentDelegate.</p>
     */
    protected void initialiseExperimentDelegate(){
        this.delegate = new DefaultXmlExperiment();
        this.delegate.setId(this.ref);
    }

    /**
     * <p>Getter for the field <code>delegate</code>.</p>
     *
     * @return a {@link ExtendedPsiXmlExperiment} object.
     */
    protected ExtendedPsiXmlExperiment getDelegate() {
        return delegate;
    }
}

package psidev.psi.mi.jami.binary.impl;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for ModelledBinaryInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/06/13</pre>
 */
public class DefaultModelledBinaryInteraction extends AbstractBinaryInteraction<ModelledParticipant> implements ModelledBinaryInteraction{

    private Collection<InteractionEvidence> interactionEvidences;
    private Source source;
    private Collection<ModelledConfidence> modelledConfidences;
    private Collection<ModelledParameter> modelledParameters;
    private Collection<CooperativeEffect> cooperativeEffects;
    private CvTerm evidenceCode;

    /**
     * <p>Constructor for DefaultModelledBinaryInteraction.</p>
     */
    public DefaultModelledBinaryInteraction() {
        super();
    }

    /**
     * <p>Constructor for DefaultModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultModelledBinaryInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for DefaultModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledBinaryInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for DefaultModelledBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public DefaultModelledBinaryInteraction(ModelledParticipant participantA, ModelledParticipant participantB) {
        super(participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public DefaultModelledBinaryInteraction(String shortName, ModelledParticipant participantA, ModelledParticipant participantB) {
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public DefaultModelledBinaryInteraction(String shortName, CvTerm type, ModelledParticipant participantA, ModelledParticipant participantB) {
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultModelledBinaryInteraction.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledBinaryInteraction(CvTerm complexExpansion) {
        super(complexExpansion);
    }

    /**
     * <p>Constructor for DefaultModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledBinaryInteraction(String shortName, CvTerm type, CvTerm complexExpansion) {
        super(shortName, type, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultModelledBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledBinaryInteraction(ModelledParticipant participantA, ModelledParticipant participantB, CvTerm complexExpansion) {
        super(participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledBinaryInteraction(String shortName, ModelledParticipant participantA, ModelledParticipant participantB, CvTerm complexExpansion) {
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledBinaryInteraction(String shortName, CvTerm type, ModelledParticipant participantA, ModelledParticipant participantB, CvTerm complexExpansion) {
        super(shortName, type, participantA, participantB, complexExpansion);
    }

    /**
     * <p>initialiseInteractionEvidences</p>
     */
    protected void initialiseInteractionEvidences(){
        this.interactionEvidences = new ArrayList<InteractionEvidence>();
    }

    /**
     * <p>initialiseCooperativeEffects</p>
     */
    protected void initialiseCooperativeEffects(){
        this.cooperativeEffects = new ArrayList<CooperativeEffect>();
    }

    /**
     * <p>initialiseCooperativeEffectsWith</p>
     *
     * @param cooperativeEffects a {@link java.util.Collection} object.
     */
    protected void initialiseCooperativeEffectsWith(Collection<CooperativeEffect> cooperativeEffects){
        if (cooperativeEffects == null){
            this.cooperativeEffects = Collections.EMPTY_LIST;
        }
        else{
            this.cooperativeEffects = cooperativeEffects;
        }
    }

    /**
     * <p>initialiseModelledConfidences</p>
     */
    protected void initialiseModelledConfidences(){
        this.modelledConfidences = new ArrayList<ModelledConfidence>();
    }

    /**
     * <p>initialiseModelledConfidencesWith</p>
     *
     * @param confidences a {@link java.util.Collection} object.
     */
    protected void initialiseModelledConfidencesWith(Collection<ModelledConfidence> confidences){
        if (confidences == null){
            this.modelledConfidences = Collections.EMPTY_LIST;
        }
        else {
            this.modelledConfidences = confidences;
        }
    }

    /**
     * <p>initialiseInteractionEvidencesWith</p>
     *
     * @param evidences a {@link java.util.Collection} object.
     */
    protected void initialiseInteractionEvidencesWith(Collection<InteractionEvidence> evidences){
        if (evidences == null){
            this.interactionEvidences = Collections.EMPTY_LIST;
        }
        else {
            this.interactionEvidences = evidences;
        }
    }

    /**
     * <p>initialiseModelledParameters</p>
     */
    protected void initialiseModelledParameters(){
        this.modelledParameters = new ArrayList<ModelledParameter>();
    }

    /**
     * <p>initialiseModelledParametersWith</p>
     *
     * @param parameters a {@link java.util.Collection} object.
     */
    protected void initialiseModelledParametersWith(Collection<ModelledParameter> parameters){
        if (parameters == null){
            this.modelledParameters = Collections.EMPTY_LIST;
        }
        else {
            this.modelledParameters = parameters;
        }
    }

    /**
     * <p>Getter for the field <code>interactionEvidences</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<InteractionEvidence> getInteractionEvidences() {
        if (interactionEvidences == null){
            initialiseInteractionEvidences();
        }
        return this.interactionEvidences;
    }

    /**
     * <p>Getter for the field <code>source</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public Source getSource() {
        return this.source;
    }

    /** {@inheritDoc} */
    public void setSource(Source source) {
        this.source = source;
    }

    /**
     * <p>getEvidenceType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getEvidenceType() {
        return this.evidenceCode;
    }

    /** {@inheritDoc} */
    public void setEvidenceType(CvTerm eco) {
        this.evidenceCode = eco;
    }

    /**
     * <p>Getter for the field <code>modelledConfidences</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledConfidence> getModelledConfidences() {
        if (modelledConfidences == null){
            initialiseModelledConfidences();
        }
        return this.modelledConfidences;
    }

    /**
     * <p>Getter for the field <code>modelledParameters</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledParameter> getModelledParameters() {
        if (modelledParameters == null){
            initialiseModelledParameters();
        }
        return this.modelledParameters;
    }

    /**
     * <p>Getter for the field <code>cooperativeEffects</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CooperativeEffect> getCooperativeEffects() {
        if (cooperativeEffects == null){
            initialiseCooperativeEffects();
        }
        return this.cooperativeEffects;
    }
}

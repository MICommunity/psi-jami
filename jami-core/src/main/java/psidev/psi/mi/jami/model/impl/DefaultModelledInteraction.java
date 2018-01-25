package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implemntation for ModelledInteraction
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the ModelledInteraction object is a complex object.
 * To compare ModelledInteraction objects, you can use some comparators provided by default:
 * - DefaultModelledInteractionComparator
 * - UnambiguousModelledInteractionComparator
 * - DefaultCuratedModelledInteractionComparator
 * - UnambiguousCuratedModelledInteractionComparator
 * - DefaultCuratedExactModelledInteractionComparator
 * - UnambiguousCuratedExactModelledInteractionComparator
 * - DefaultExactModelledInteractionComparator
 * - UnambiguousExactModelledInteractionComparator
 * - ModelledInteractionComparator
 * - CuratedModelledInteractionComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>05/02/13</pre>
 */
public class DefaultModelledInteraction extends AbstractInteraction<ModelledParticipant> implements ModelledInteraction{

    private Collection<InteractionEvidence> interactionEvidences;
    private Source source;
    private Collection<ModelledConfidence> modelledConfidences;
    private Collection<ModelledParameter> modelledParameters;
    private Collection<CooperativeEffect> cooperativeEffects;
    private CvTerm evidenceType;

    /**
     * <p>Constructor for DefaultModelledInteraction.</p>
     */
    public DefaultModelledInteraction() {
        super();
    }

    /**
     * <p>Constructor for DefaultModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultModelledInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for DefaultModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public DefaultModelledInteraction(String shortName, Source source) {
        super(shortName);
        this.source = source;
    }

    /**
     * <p>Constructor for DefaultModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for DefaultModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledInteraction(String shortName, Source source, CvTerm type) {
        this(shortName, type);
        this.source = source;
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

    /**
     * <p>Getter for the field <code>evidenceType</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getEvidenceType() {
        return this.evidenceType;
    }

    /** {@inheritDoc} */
    public void setEvidenceType(CvTerm eco) {
        this.evidenceType = eco;
    }
}

package psidev.psi.mi.jami.xml.model.extension.binary;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlModelledInteraction;
import psidev.psi.mi.jami.xml.model.extension.binary.AbstractExtendedXmlBinaryInteraction;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Xml implementation of ModelledInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
@XmlTransient
public class XmlModelledBinaryInteraction extends AbstractExtendedXmlBinaryInteraction<ModelledParticipant> implements
        ModelledBinaryInteraction, ExtendedPsiXmlModelledInteraction {
    private Collection<InteractionEvidence> interactionEvidences;
    private Source source;
    private Collection<ModelledConfidence> modelledConfidences;
    private Collection<ModelledParameter> modelledParameters;
    private Collection<CooperativeEffect> cooperativeEffects;
    private CvTerm evidenceType;
    private List<Experiment> experiments;

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     */
    public XmlModelledBinaryInteraction() {
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledBinaryInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public XmlModelledBinaryInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public XmlModelledBinaryInteraction(ModelledParticipant participantA, ModelledParticipant participantB) {
        super(participantA, participantB);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public XmlModelledBinaryInteraction(String shortName, ModelledParticipant participantA, ModelledParticipant participantB) {
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public XmlModelledBinaryInteraction(String shortName, CvTerm type, ModelledParticipant participantA, ModelledParticipant participantB) {
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledBinaryInteraction(CvTerm complexExpansion) {
        super(complexExpansion);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledBinaryInteraction(String shortName, CvTerm type, CvTerm complexExpansion) {
        super(shortName, type, complexExpansion);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledBinaryInteraction(ModelledParticipant participantA, ModelledParticipant participantB, CvTerm complexExpansion) {
        super(participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledBinaryInteraction(String shortName, ModelledParticipant participantA, ModelledParticipant participantB, CvTerm complexExpansion) {
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledBinaryInteraction(String shortName, CvTerm type, ModelledParticipant participantA, ModelledParticipant participantB, CvTerm complexExpansion) {
        super(shortName, type, participantA, participantB, complexExpansion);
    }

    /**
     * <p>initialiseInteractionEvidences.</p>
     */
    protected void initialiseInteractionEvidences(){
        this.interactionEvidences = new ArrayList<InteractionEvidence>();
    }

    /**
     * <p>initialiseCooperativeEffects.</p>
     */
    protected void initialiseCooperativeEffects(){
        this.cooperativeEffects = new ArrayList<CooperativeEffect>();
    }

    /**
     * <p>initialiseModelledConfidences.</p>
     */
    protected void initialiseModelledConfidences(){
        this.modelledConfidences = new ArrayList<ModelledConfidence>();
    }

    /**
     * <p>initialiseModelledParameters.</p>
     */
    protected void initialiseModelledParameters(){
        this.modelledParameters = new ArrayList<ModelledParameter>();
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
        return evidenceType;
    }

    /** {@inheritDoc} */
    public void setEvidenceType(CvTerm evidenceType) {
        this.evidenceType = evidenceType;
    }

    /** {@inheritDoc} */
    @Override
    public List<Experiment> getExperiments() {
        if (this.experiments == null){
           this.experiments = new ArrayList<Experiment>();
        }
        return this.experiments;
    }
}

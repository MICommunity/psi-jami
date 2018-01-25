package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for FeatureEvidence
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the FeatureEvidence object is a complex object.
 * To compare FeatureEvidence objects, you can use some comparators provided by default:
 * - DefaultFeatureEvidenceComparator
 * - UnambiguousFeatureEvidenceComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/02/13</pre>
 */
public class DefaultFeatureEvidence extends AbstractFeature<ExperimentalEntity, FeatureEvidence> implements FeatureEvidence {
    private Collection<CvTerm> detectionMethods;
    private Collection<Parameter> parameters;

    /**
     * <p>Constructor for DefaultFeatureEvidence.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public DefaultFeatureEvidence(ParticipantEvidence participant) {
        super();
        setParticipant(participant);
    }

    /**
     * <p>Constructor for DefaultFeatureEvidence.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public DefaultFeatureEvidence(ParticipantEvidence participant, String shortName, String fullName) {
        super(shortName, fullName);
        setParticipant(participant);
    }

    /**
     * <p>Constructor for DefaultFeatureEvidence.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultFeatureEvidence(ParticipantEvidence participant, CvTerm type) {
        super(type);
        setParticipant(participant);
    }

    /**
     * <p>Constructor for DefaultFeatureEvidence.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultFeatureEvidence(ParticipantEvidence participant, String shortName, String fullName, CvTerm type) {
        super(shortName, fullName, type);
        setParticipant(participant);
    }

    /**
     * <p>Constructor for DefaultFeatureEvidence.</p>
     */
    public DefaultFeatureEvidence() {
        super();
    }

    /**
     * <p>Constructor for DefaultFeatureEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public DefaultFeatureEvidence(String shortName, String fullName) {
        super(shortName, fullName);
    }

    /**
     * <p>Constructor for DefaultFeatureEvidence.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultFeatureEvidence(CvTerm type) {
        super(type);
    }

    /**
     * <p>Constructor for DefaultFeatureEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultFeatureEvidence(String shortName, String fullName, CvTerm type) {
        super(shortName, fullName, type);
    }

    /**
     * <p>initialiseParameters</p>
     */
    protected void initialiseParameters() {
        this.parameters = new ArrayList<Parameter>();
    }

    /**
     * <p>initialiseDetectionMethods</p>
     */
    protected void initialiseDetectionMethods(){
        this.detectionMethods = new ArrayList<CvTerm>();
    }

    /**
     * <p>initialiseDetectionMethodsWith</p>
     *
     * @param methods a {@link java.util.Collection} object.
     */
    protected void initialiseDetectionMethodsWith(Collection<CvTerm> methods){
        if (methods == null){
            this.detectionMethods = Collections.EMPTY_LIST;
        }
        else {
            this.detectionMethods = methods;
        }
    }

    /**
     * <p>initialiseParametersWith</p>
     *
     * @param parameters a {@link java.util.Collection} object.
     */
    protected void initialiseParametersWith(Collection<Parameter> parameters) {
        if (parameters == null){
            this.parameters = Collections.EMPTY_LIST;
        }
        else {
            this.parameters = parameters;
        }
    }

    /**
     * <p>Getter for the field <code>detectionMethods</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CvTerm> getDetectionMethods() {
        if (detectionMethods == null){
            initialiseDetectionMethods();
        }
        return this.detectionMethods;
    }

    /**
     * <p>Getter for the field <code>parameters</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Parameter> getParameters() {
        if (parameters == null){
            initialiseParameters();
        }
        return this.parameters;
    }
}

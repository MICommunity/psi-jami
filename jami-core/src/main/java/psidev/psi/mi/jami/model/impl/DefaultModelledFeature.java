package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ModelledEntity;
import psidev.psi.mi.jami.model.ModelledFeature;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.utils.CvTermUtils;

/**
 * Default implementation for ModelledFeature
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the ModelledFeature object is a complex object.
 * To compare ModelledFeature objects, you can use some comparators provided by default:
 * - DefaultModelledFeatureComparator
 * - UnambiguousModelledFeatureComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/02/13</pre>
 */
public class DefaultModelledFeature extends AbstractFeature<ModelledEntity, ModelledFeature> implements ModelledFeature {

    /**
     * <p>Constructor for DefaultModelledFeature.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public DefaultModelledFeature(ModelledParticipant participant) {
        super(CvTermUtils.createBiologicalFeatureType());
        setParticipant(participant);
    }

    /**
     * <p>Constructor for DefaultModelledFeature.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public DefaultModelledFeature(ModelledParticipant participant, String shortName, String fullName) {
        super(shortName, fullName, CvTermUtils.createBiologicalFeatureType());
        setParticipant(participant);
    }

    /**
     * <p>Constructor for DefaultModelledFeature.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledFeature(ModelledParticipant participant, CvTerm type) {
        super(type);
        setParticipant(participant);
    }

    /**
     * <p>Constructor for DefaultModelledFeature.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledFeature(ModelledParticipant participant, String shortName, String fullName, CvTerm type) {
        super(shortName, fullName, type);
        setParticipant(participant);
    }

    /**
     * <p>Constructor for DefaultModelledFeature.</p>
     */
    public DefaultModelledFeature() {
        super(CvTermUtils.createBiologicalFeatureType());
    }

    /**
     * <p>Constructor for DefaultModelledFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public DefaultModelledFeature(String shortName, String fullName) {
        super(shortName, fullName, CvTermUtils.createBiologicalFeatureType());
    }

    /**
     * <p>Constructor for DefaultModelledFeature.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledFeature(CvTerm type) {
        super(type);
    }

    /**
     * <p>Constructor for DefaultModelledFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledFeature(String shortName, String fullName, CvTerm type) {
        super(shortName, fullName, type);
    }
}

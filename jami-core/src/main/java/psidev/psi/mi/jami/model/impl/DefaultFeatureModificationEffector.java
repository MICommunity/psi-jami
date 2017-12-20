package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.AllostericEffectorType;
import psidev.psi.mi.jami.model.FeatureModificationEffector;
import psidev.psi.mi.jami.model.ModelledFeature;

/**
 * Default implementation for FeatureModificationEffector
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the FeatureModificationEffector object is a complex object.
 * To compare FeatureModificationEffector objects, you can use some comparators provided by default:
 * - DefaultFeatureModificationEffectorComparator
 * - UnambiguousFeatureModificationEffectorComparator
 * - FeatureModificationEffector comparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/05/13</pre>
 */
public class DefaultFeatureModificationEffector implements FeatureModificationEffector {

    private ModelledFeature feature;

    /**
     * <p>Constructor for DefaultFeatureModificationEffector.</p>
     *
     * @param feature a {@link psidev.psi.mi.jami.model.ModelledFeature} object.
     */
    public DefaultFeatureModificationEffector(ModelledFeature feature){
        if (feature == null){
            throw new IllegalArgumentException("The feature of a FeatureModificationEffector cannot be null.");
        }
        this.feature = feature;
    }

    /**
     * <p>getFeatureModification</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.ModelledFeature} object.
     */
    public ModelledFeature getFeatureModification() {
        return feature;
    }

    /**
     * <p>getEffectorType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.AllostericEffectorType} object.
     */
    public AllostericEffectorType getEffectorType() {
        return AllostericEffectorType.feature_modification;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "feature modification effector: " + getFeatureModification().toString();
    }
}

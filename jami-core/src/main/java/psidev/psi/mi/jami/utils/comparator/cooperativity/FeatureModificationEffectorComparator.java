package psidev.psi.mi.jami.utils.comparator.cooperativity;

import psidev.psi.mi.jami.model.FeatureModificationEffector;
import psidev.psi.mi.jami.model.ModelledFeature;
import psidev.psi.mi.jami.utils.comparator.feature.ModelledFeatureComparator;

import java.util.Comparator;

/**
 * Basic comparator for FeatureModificationEffector.
 *
 * It will use a ModelledFeatureComparator to compare the feature.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/05/13</pre>
 */
public class FeatureModificationEffectorComparator implements Comparator<FeatureModificationEffector> {

    private ModelledFeatureComparator featureComparator;

    /**
     * <p>Constructor for FeatureModificationEffectorComparator.</p>
     *
     * @param featureComparator a {@link psidev.psi.mi.jami.utils.comparator.feature.ModelledFeatureComparator} object.
     */
    public FeatureModificationEffectorComparator(ModelledFeatureComparator featureComparator){

        if (featureComparator == null){
            throw new IllegalArgumentException("The featureComparator is required to compare the features of featureModificationEffectors");
        }
        this.featureComparator = featureComparator;
    }

    /**
     * <p>Getter for the field <code>featureComparator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.utils.comparator.feature.ModelledFeatureComparator} object.
     */
    public ModelledFeatureComparator getFeatureComparator() {
        return featureComparator;
    }

    /**
     * <p>compare</p>
     *
     * @param featureModificationEffector1 a {@link psidev.psi.mi.jami.model.FeatureModificationEffector} object.
     * @param featureModificationEffector2 a {@link psidev.psi.mi.jami.model.FeatureModificationEffector} object.
     * @return a int.
     */
    public int compare(FeatureModificationEffector featureModificationEffector1, FeatureModificationEffector featureModificationEffector2) {

        int EQUAL = 0;
        int BEFORE = -1;
        int AFTER = 1;

        if (featureModificationEffector1 == featureModificationEffector2){
            return EQUAL;
        }
        else if (featureModificationEffector1 == null){
            return AFTER;
        }
        else if (featureModificationEffector2 == null){
            return BEFORE;
        }
        else {

            ModelledFeature feature1 = featureModificationEffector1.getFeatureModification();
            ModelledFeature feature2 = featureModificationEffector2.getFeatureModification();

            return featureComparator.compare(feature1, feature2);
        }
    }
}

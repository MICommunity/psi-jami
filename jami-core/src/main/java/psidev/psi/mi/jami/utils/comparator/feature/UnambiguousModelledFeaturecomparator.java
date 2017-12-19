package psidev.psi.mi.jami.utils.comparator.feature;

import psidev.psi.mi.jami.model.ModelledFeature;

/**
 * Unambiguous ModelledFeature comparator.
 * It will use a UnambiguousFeatureBaseComparator to compare basic properties of a feature.
 * <p>
 * This comparator will ignore all the other properties of a biological feature.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/01/13</pre>
 */
public class UnambiguousModelledFeaturecomparator extends ModelledFeatureComparator {

    private static UnambiguousModelledFeaturecomparator unambiguousBiologicalFeatureComparator;

    /**
     * {@inheritDoc}
     * <p>
     * Creates a new UnambiguousModelledFeaturecomparator. It will use a UnambiguousFeatureBaseComparator to compare basic feature properties
     */
    public UnambiguousModelledFeaturecomparator() {
        super(new UnambiguousFeatureBaseComparator());
    }

    /**
     * Use UnambiguousModelledFeaturecomparator to know if two biological features are equals.
     *
     * @param feature1 a {@link psidev.psi.mi.jami.model.ModelledFeature} object.
     * @param feature2 a {@link psidev.psi.mi.jami.model.ModelledFeature} object.
     * @return true if the two biological features are equal
     */
    public static boolean areEquals(ModelledFeature feature1, ModelledFeature feature2) {
        if (unambiguousBiologicalFeatureComparator == null) {
            unambiguousBiologicalFeatureComparator = new UnambiguousModelledFeaturecomparator();
        }

        return unambiguousBiologicalFeatureComparator.compare(feature1, feature2) == 0;
    }

    @Override
    public UnambiguousFeatureBaseComparator getFeatureComparator() {
        return (UnambiguousFeatureBaseComparator) super.getFeatureComparator();
    }

    /**
     * It will use a UnambiguousFeatureBaseComparator to compare basic properties of a feature.
     * <p>
     * This comparator will ignore all the other properties of a biological feature.
     */
    @Override
    public int compare(ModelledFeature biologicalFeature1, ModelledFeature biologicalFeature2) {
        return super.compare(biologicalFeature1, biologicalFeature2);
    }
}

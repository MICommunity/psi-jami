package psidev.psi.mi.jami.utils.comparator.parameter;

import psidev.psi.mi.jami.model.Parameter;
import psidev.psi.mi.jami.utils.comparator.CollectionComparator;

/**
 * Comparator for collection of parameters
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/01/13</pre>
 */
public class ParameterCollectionComparator extends CollectionComparator<Parameter> {
    /**
     * Creates a new parameter CollectionComparator. It requires a Comparator for the parameters in the Collection
     *
     * @param parameterComparator a {@link psidev.psi.mi.jami.utils.comparator.parameter.ParameterComparator} object.
     */
    public ParameterCollectionComparator(ParameterComparator parameterComparator) {
        super(parameterComparator);
    }

    /** {@inheritDoc} */
    @Override
    public ParameterComparator getObjectComparator() {
        return (ParameterComparator) super.getObjectComparator();
    }
}

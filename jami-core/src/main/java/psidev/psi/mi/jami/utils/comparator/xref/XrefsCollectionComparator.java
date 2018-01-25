package psidev.psi.mi.jami.utils.comparator.xref;

import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.comparator.CollectionComparator;

import java.util.Comparator;

/**
 * Comparator for collection of Xrefs
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/12/12</pre>
 */
public class XrefsCollectionComparator extends CollectionComparator<Xref> {
    /**
     * Creates a new xref CollectionComparator. It requires a Comparator for the xrefs in the Collection
     *
     * @param xrefComparator a {@link java.util.Comparator} object.
     */
    public XrefsCollectionComparator(Comparator<Xref> xrefComparator) {
        super(xrefComparator);
    }

    /** {@inheritDoc} */
    @Override
    public Comparator<Xref> getObjectComparator() {
        return (Comparator<Xref>) super.getObjectComparator();
    }
}

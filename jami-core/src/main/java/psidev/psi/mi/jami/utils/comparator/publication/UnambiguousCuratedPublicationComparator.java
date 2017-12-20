package psidev.psi.mi.jami.utils.comparator.publication;

import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.utils.comparator.cv.UnambiguousCvTermComparator;

/**
 * Unambiguous curated publication comparator.
 * It uses a UnambiguousPublicationComparator to compares the bibliographic details and then will compare first the curation depth, then the source using UnambiguousCvTermComparator and then the released date.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/12/12</pre>
 */
public class UnambiguousCuratedPublicationComparator extends CuratedPublicationComparator {

    private static UnambiguousCuratedPublicationComparator unambiguousCuratedPublicationComparator;

    /**
     * {@inheritDoc}
     * <p>
     * Creates a new DefaultCuratedPublicationComparator based on DefaultPublicationComparator
     */
    public UnambiguousCuratedPublicationComparator() {
        super(new UnambiguousPublicationComparator(), new UnambiguousCvTermComparator());
    }

    /**
     * Use UnambiguousCuratedPublicationComparator to know if two publications are equals.
     *
     * @param pub1 a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param pub2 a {@link psidev.psi.mi.jami.model.Publication} object.
     * @return true if the two publications are equal
     */
    public static boolean areEquals(Publication pub1, Publication pub2) {
        if (unambiguousCuratedPublicationComparator == null) {
            unambiguousCuratedPublicationComparator = new UnambiguousCuratedPublicationComparator();
        }

        return unambiguousCuratedPublicationComparator.compare(pub1, pub2) == 0;
    }

    /**
     * <p>hashCode</p>
     *
     * @param pub a {@link psidev.psi.mi.jami.model.Publication} object.
     * @return the hashcode consistent with the equals method for this comparator
     */
    public static int hashCode(Publication pub) {
        if (unambiguousCuratedPublicationComparator == null) {
            unambiguousCuratedPublicationComparator = new UnambiguousCuratedPublicationComparator();
        }

        if (pub == null) {
            return 0;
        }

        int hashcode = UnambiguousPublicationComparator.hashCode(pub);
        hashcode = 31 * hashcode + pub.getCurationDepth().hashCode();
        hashcode = 31 * hashcode + UnambiguousCvTermComparator.hashCode(pub.getSource());
        hashcode = 31 * hashcode + (pub.getReleasedDate() != null ? pub.getReleasedDate().hashCode() : 0);

        return hashcode;
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousPublicationComparator getPublicationComparator() {
        return (UnambiguousPublicationComparator) super.getPublicationComparator();
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousCvTermComparator getSourceComparator() {
        return (UnambiguousCvTermComparator) super.getSourceComparator();
    }

    /**
     * {@inheritDoc}
     *
     * It uses a UnambiguousPublicationComparator to compares the bibliographic details and then will compare first the curation depth, then the source using UnambiguousCvTermComparator and then the released date.
     */
    @Override
    public int compare(Publication publication1, Publication publication2) {
        return super.compare(publication1, publication2);
    }
}

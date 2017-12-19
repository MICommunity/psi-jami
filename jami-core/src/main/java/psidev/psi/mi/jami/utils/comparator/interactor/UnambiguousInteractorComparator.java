package psidev.psi.mi.jami.utils.comparator.interactor;

import psidev.psi.mi.jami.model.Interactor;

/**
 * Unambiguous generic Interactor Comparator.
 *
 * Bioactive entities come first, then proteins, then genes, then nucleic acids, then complexes and finally InteractorPool.
 * If two interactors are from the same Interactor interface, it will use a more specific Comparator :
 * - Uses UnambiguousBioactiveEntityComparator for comparing BioactiveEntity objects.
 * - Uses UnambiguousProteinComparator for comparing Protein objects.
 * - Uses UnambiguousGeneComparator for comparing Gene objects.
 * - Uses UnambiguousNucleicAcidComparator for comparing NucleicAcids objects.
 * - Uses UnambiguousPolymerComparator for comparing Polymer objects
 * - Uses UnambiguousComplexComparator for comparing complexes
 * - Uses UnambiguousInteractorPoolComparator for comparing interactor candidates
 * - use UnambiguousInteractorBaseComparator for comparing basic interactors that are not one of the above..
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/01/13</pre>
 */
public class UnambiguousInteractorComparator extends InteractorComparator{

    private static UnambiguousInteractorComparator unambiguousInteractorComparator;

    /**
     * {@inheritDoc}
     *
     * Creates a new UnambiguousInteractorComparator.
     * - Uses UnambiguousBioactiveEntityComparator for comparing BioactiveEntity objects.
     * - Uses UnambiguousProteinComparator for comparing Protein objects.
     * - Uses UnambiguousGeneComparator for comparing Gene objects.
     * - Uses UnambiguousNucleicAcidComparator for comparing NucleicAcids objects.
     * - Uses UnambiguousInteractorPoolComparator for comparing interactor candidates
     * - use UnambiguousInteractorBaseComparator for comparing basic interactors that are not one of the above..
     */
    public UnambiguousInteractorComparator() {
        super(new UnambiguousInteractorBaseComparator(), new UnambiguousComplexComparator(), new UnambiguousPolymerComparator(),
                new UnambiguousBioactiveEntityComparator(), new UnambiguousGeneComparator(), new UnambiguousNucleicAcidComparator(),
                new UnambiguousProteinComparator());
    }

    /**
     * <p>Constructor for UnambiguousInteractorComparator.</p>
     *
     * @param complexComparator a {@link psidev.psi.mi.jami.utils.comparator.interactor.UnambiguousComplexComparator} object.
     */
    public UnambiguousInteractorComparator(UnambiguousComplexComparator complexComparator) {
        super(new UnambiguousInteractorBaseComparator(), complexComparator != null ? complexComparator : new UnambiguousComplexComparator(), new UnambiguousPolymerComparator(),
                new UnambiguousBioactiveEntityComparator(), new UnambiguousGeneComparator(), new UnambiguousNucleicAcidComparator(),
                new UnambiguousProteinComparator());
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousInteractorBaseComparator getInteractorBaseComparator() {
        return (UnambiguousInteractorBaseComparator) super.getInteractorBaseComparator();
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousComplexComparator getComplexComparator() {
        return (UnambiguousComplexComparator) super.getComplexComparator();
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousPolymerComparator getPolymerComparator() {
        return (UnambiguousPolymerComparator) super.getPolymerComparator();
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousBioactiveEntityComparator getBioactiveEntityComparator() {
        return (UnambiguousBioactiveEntityComparator) super.getBioactiveEntityComparator();
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousGeneComparator getGeneComparator() {
        return (UnambiguousGeneComparator) super.getGeneComparator();
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousNucleicAcidComparator getNucleicAcidComparator() {
        return (UnambiguousNucleicAcidComparator) super.getNucleicAcidComparator();
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousProteinComparator getProteinComparator() {
        return (UnambiguousProteinComparator) super.getProteinComparator();
    }

    /**
     * {@inheritDoc}
     *
     * Bioactive entities come first, then proteins, then genes, then nucleic acids, then complexes and finally InteractorPool.
     * If two interactors are from the same Interactor interface, it will use a more specific Comparator :
     * - Uses UnambiguousBioactiveEntityComparator for comparing BioactiveEntity objects.
     * - Uses UnambiguousProteinComparator for comparing Protein objects.
     * - Uses UnambiguousGeneComparator for comparing Gene objects.
     * - Uses UnambiguousNucleicAcidComparator for comparing NucleicAcids objects.
     * - Uses UnambiguousInteractorPoolComparator for comparing interactor candidates
     * - use UnambiguousInteractorBaseComparator for comparing basic interactors that are not one of the above..
     */
    @Override
    public int compare(Interactor interactor1, Interactor interactor2) {
        return super.compare(interactor1, interactor2);
    }

    /**
     * Use UnambiguousInteractorComparator to know if two interactors are equals.
     *
     * @param interactor1 a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param interactor2 a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @return true if the two interactors are equal
     */
    public static boolean areEquals(Interactor interactor1, Interactor interactor2){
        if (unambiguousInteractorComparator == null){
            unambiguousInteractorComparator = new UnambiguousInteractorComparator();
        }

        return unambiguousInteractorComparator.compare(interactor1, interactor2) == 0;
    }
}

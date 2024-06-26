package psidev.psi.mi.jami.utils.comparator.interactor;

import psidev.psi.mi.jami.model.*;

import java.util.Comparator;

/**
 * Generic Interactor Comparator.
 *
 * Bioactive entities come first, then proteins, then genes, then nucleic acids, then interactorSet and finally Complexes.
 * If two interactors are from the same Interactor interface, it will use a more specific Comparator :
 * - Uses AbstractBioactiveEntityComparator for comparing BioactiveEntity objects.
 * - Uses AbstractProteinComparator for comparing Protein objects.
 * - Uses AbstractGeneComparator for comparing Gene objects.
 * - Uses AbstractNucleicAcidComparator for comparing NucleicAcids objects.
 * - Uses InteractorPoolComparator for comparing interactor candidates
 * - Uses ComplexComparator for comparing complexes
 * - Uses AbstractPolymerComparator for comparing polymers
 * - use AbstractInteractorBaseComparator for comparing basic interactors that are not one of the above.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/01/13</pre>
 */
public class InteractorComparator implements Comparator<Interactor> {

    private Comparator<BioactiveEntity> bioactiveEntityComparator;
    private Comparator<Gene> geneComparator;
    private Comparator<Protein> proteinComparator;
    private Comparator<NucleicAcid> nucleicAcidComparator;
    private Comparator<Interactor> interactorBaseComparator;
    private Comparator<Complex> complexComparator;
    private Comparator<InteractorPool> interactorCandidatesComparator;
    private Comparator<Polymer> polymerComparator;

    /**
     * Creates a new InteractorComparator.
     *
     * @param interactorBaseComparator : required to create more specific comparators and to compare basic interactor objects
     * @param complexComparator : required to compare complex objects
     * @param polymerComparator a {@link java.util.Comparator} object.
     * @param bioactiveEntityComparator a {@link java.util.Comparator} object.
     * @param geneComparator a {@link java.util.Comparator} object.
     * @param nucleicAcidComparator a {@link java.util.Comparator} object.
     * @param proteinComparator a {@link java.util.Comparator} object.
     */
    public InteractorComparator(Comparator<Interactor> interactorBaseComparator, Comparator<Complex> complexComparator, Comparator<Polymer> polymerComparator,
                                Comparator<BioactiveEntity> bioactiveEntityComparator, Comparator<Gene> geneComparator,
                                Comparator<NucleicAcid> nucleicAcidComparator, Comparator<Protein> proteinComparator){
        if (interactorBaseComparator == null){
            throw new IllegalArgumentException("The interactorBaseComparator is required to create more specific interactor comparators and compares basic interactor properties. It cannot be null");
        }
        this.interactorBaseComparator = interactorBaseComparator;
        if (bioactiveEntityComparator == null){
            throw new IllegalArgumentException("The BioactiveEntityComparator is required to compare bioactive entities. It cannot be null");
        }
        this.bioactiveEntityComparator = bioactiveEntityComparator;
        if (geneComparator == null){
            throw new IllegalArgumentException("The GeneComparator is required to compare genes. It cannot be null");
        }
        this.geneComparator = geneComparator;
        if (proteinComparator == null){
            throw new IllegalArgumentException("The ProteinComparator is required to compare proteins. It cannot be null");
        }
        this.proteinComparator = proteinComparator;
        if (nucleicAcidComparator == null){
            throw new IllegalArgumentException("The NucleicAcidComparator is required to compare nucleicAcids. It cannot be null");
        }
        this.nucleicAcidComparator = nucleicAcidComparator;
        if (polymerComparator == null){
            throw new IllegalArgumentException("The PolymerComparator is required to compare polymers. It cannot be null");
        }
        this.polymerComparator = polymerComparator;

        if (complexComparator == null){
            throw new IllegalArgumentException("The ComplexComparator is required to compare complexes. It cannot be null");
        }
        this.complexComparator = complexComparator;
        this.interactorCandidatesComparator = new InteractorPoolComparator(this);
    }

    /**
     * <p>Constructor for InteractorComparator.</p>
     *
     * @param interactorBaseComparator a {@link java.util.Comparator} object.
     * @param complexComparator a {@link java.util.Comparator} object.
     * @param polymerComparator a {@link java.util.Comparator} object.
     * @param bioactiveEntityComparator a {@link java.util.Comparator} object.
     * @param geneComparator a {@link java.util.Comparator} object.
     * @param nucleicAcidComparator a {@link java.util.Comparator} object.
     * @param proteinComparator a {@link java.util.Comparator} object.
     * @param poolComparator a {@link java.util.Comparator} object.
     */
    public InteractorComparator(Comparator<Interactor> interactorBaseComparator, Comparator<Complex> complexComparator, Comparator<Polymer> polymerComparator,
                                Comparator<BioactiveEntity> bioactiveEntityComparator, Comparator<Gene> geneComparator,
                                Comparator<NucleicAcid> nucleicAcidComparator, Comparator<Protein> proteinComparator,
    Comparator<InteractorPool> poolComparator){
        if (interactorBaseComparator == null){
            throw new IllegalArgumentException("The interactorBaseComparator is required to create more specific interactor comparators and compares basic interactor properties. It cannot be null");
        }
        this.interactorBaseComparator = interactorBaseComparator;
        if (bioactiveEntityComparator == null){
            throw new IllegalArgumentException("The BioactiveEntityComparator is required to compare bioactive entities. It cannot be null");
        }
        this.bioactiveEntityComparator = bioactiveEntityComparator;
        if (geneComparator == null){
            throw new IllegalArgumentException("The GeneComparator is required to compare genes. It cannot be null");
        }
        this.geneComparator = geneComparator;
        if (proteinComparator == null){
            throw new IllegalArgumentException("The ProteinComparator is required to compare proteins. It cannot be null");
        }
        this.proteinComparator = proteinComparator;
        if (nucleicAcidComparator == null){
            throw new IllegalArgumentException("The NucleicAcidComparator is required to compare nucleicAcids. It cannot be null");
        }
        this.nucleicAcidComparator = nucleicAcidComparator;
        if (polymerComparator == null){
            throw new IllegalArgumentException("The PolymerComparator is required to compare polymers. It cannot be null");
        }
        this.polymerComparator = polymerComparator;

        if (complexComparator == null){
            throw new IllegalArgumentException("The ComplexComparator is required to compare complexes. It cannot be null");
        }
        this.complexComparator = complexComparator;
        this.interactorCandidatesComparator = poolComparator != null ? poolComparator : new InteractorPoolComparator(this);
    }

    /**
     * <p>Getter for the field <code>bioactiveEntityComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<BioactiveEntity> getBioactiveEntityComparator() {
        return bioactiveEntityComparator;
    }

    /**
     * <p>Getter for the field <code>geneComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<Gene> getGeneComparator() {
        return geneComparator;
    }

    /**
     * <p>Getter for the field <code>proteinComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<Protein> getProteinComparator() {
        return proteinComparator;
    }

    /**
     * <p>Getter for the field <code>nucleicAcidComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<NucleicAcid> getNucleicAcidComparator() {
        return nucleicAcidComparator;
    }

    /**
     * <p>Getter for the field <code>interactorBaseComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<Interactor> getInteractorBaseComparator() {
        return interactorBaseComparator;
    }

    /**
     * <p>Getter for the field <code>complexComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<Complex> getComplexComparator() {
        return complexComparator;
    }

    /**
     * <p>Getter for the field <code>polymerComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<Polymer> getPolymerComparator() {
        return polymerComparator;
    }

    /**
     * <p>Getter for the field <code>interactorCandidatesComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<InteractorPool> getInteractorCandidatesComparator() {
        return interactorCandidatesComparator;
    }

    /**
     *
     * Bioactive entities come first, then proteins, then genes, then nucleic acids, then complexes and finally InteractorPool.
     * If two interactors are from the same Interactor interface, it will use a more specific Comparator :
     * - Uses AbstractBioactiveEntityComparator for comparing BioactiveEntity objects.
     * - Uses AbstractProteinComparator for comparing Protein objects.
     * - Uses AbstractGeneComparator for comparing Gene objects.
     * - Uses AbstractNucleicAcidComparator for comparing NucleicAcids objects.
     * - Uses InteractorPoolComparator for comparing interactor candidates
     * - Uses polymerComparator for comparing Polymer objects.
     * - use AbstractInteractorBaseComparator for comparing basic interactors that are not one of the above.
     *
     * @param interactor1 a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param interactor2 a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @return a int.
     */
    public int compare(Interactor interactor1, Interactor interactor2) {
        int EQUAL = 0;
        int BEFORE = -1;
        int AFTER = 1;

        if (interactor1 == interactor2){
            return EQUAL;
        }
        else if (interactor1 == null){
            return AFTER;
        }
        else if (interactor2 == null){
            return BEFORE;
        }
        else {
            // first check if both interactors are from the same interface

            // both are small molecules
            boolean isBioactiveEntity1 = interactor1 instanceof BioactiveEntity;
            boolean isBioactiveEntity2 = interactor2 instanceof BioactiveEntity;
            if (isBioactiveEntity1 && isBioactiveEntity2){
                return bioactiveEntityComparator.compare((BioactiveEntity) interactor1, (BioactiveEntity) interactor2);
            }
            // the small molecule is before
            else if (isBioactiveEntity1){
                return BEFORE;
            }
            else if (isBioactiveEntity2){
                return AFTER;
            }
            else {
                // both are proteins
                boolean isProtein1 = interactor1 instanceof Protein;
                boolean isProtein2 = interactor2 instanceof Protein;
                if (isProtein1 && isProtein2){
                    return proteinComparator.compare((Protein) interactor1, (Protein) interactor2);
                }
                // the protein is before
                else if (isProtein1){
                    return BEFORE;
                }
                else if (isProtein2){
                    return AFTER;
                }
                else {
                    // both are genes
                    boolean isGene1 = interactor1 instanceof Gene;
                    boolean isGene2 = interactor2 instanceof Gene;
                    if (isGene1 && isGene2){
                        return geneComparator.compare((Gene) interactor1, (Gene) interactor2);
                    }
                    // the gene is before
                    else if (isGene1){
                        return BEFORE;
                    }
                    else if (isGene2){
                        return AFTER;
                    }
                    else {
                        // both are nucleic acids
                        boolean isNucleicAcid1 = interactor1 instanceof NucleicAcid;
                        boolean isNucleicAcid2 = interactor2 instanceof NucleicAcid;
                        if (isNucleicAcid1 && isNucleicAcid2){
                            return nucleicAcidComparator.compare((NucleicAcid) interactor1, (NucleicAcid) interactor2);
                        }
                        // the nucleic acid is before
                        else if (isNucleicAcid1){
                            return BEFORE;
                        }
                        else if (isNucleicAcid2){
                            return AFTER;
                        }
                        else {
                            boolean isPolymer1 = interactor1 instanceof Polymer;
                            boolean isPolymer2 = interactor2 instanceof Polymer;
                            // both are polymers
                            if (isPolymer1 && isPolymer2){
                                return polymerComparator.compare((Polymer) interactor1, (Polymer) interactor2);
                            }
                            // the polymer is before
                            else if (isPolymer1){
                                return BEFORE;
                            }
                            else if (isPolymer2){
                                return AFTER;
                            }
                            else {

                                // both are interactor candidates
                                boolean isCandidates1 = interactor1 instanceof InteractorPool;
                                boolean isCandidates2 = interactor2 instanceof InteractorPool;
                                if (isCandidates1 && isCandidates2){
                                    return interactorCandidatesComparator.compare((InteractorPool) interactor1, (InteractorPool) interactor2);
                                }
                                // the complex is before
                                else if (isCandidates1){
                                    return BEFORE;
                                }
                                else if (isCandidates2){
                                    return AFTER;
                                }
                                else {
                                    boolean isComplex1 = interactor1 instanceof Complex;
                                    boolean isComplex2 = interactor2 instanceof Complex;
                                    // both are complexes
                                    if (isComplex1 && isComplex2){
                                        return complexComparator.compare((Complex) interactor1, (Complex) interactor2);
                                    }
                                    // the complex is before
                                    else if (isComplex1){
                                        return BEFORE;
                                    }
                                    else if (isComplex2){
                                        return AFTER;
                                    }
                                    else {
                                        return interactorBaseComparator.compare(interactor1, interactor2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

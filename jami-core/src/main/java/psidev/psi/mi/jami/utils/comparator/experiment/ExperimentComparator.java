package psidev.psi.mi.jami.utils.comparator.experiment;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.comparator.CollectionComparator;
import psidev.psi.mi.jami.utils.comparator.organism.OrganismComparator;

import java.util.Collection;
import java.util.Comparator;

/**
 * Basic experiment comparator.
 * It will look first at the publications using a Comparator<Publication>. If the publications are the same, it will look at the
 * interaction detection methods using AbstractCvTermComparator. If the interaction detection methods are the same, it will look at
 * the host organisms using OrganismComparator.
 * If the host organisms are the same, it will look at the variableParameters using VariableParameterComparator.
 *
 * This comparator will ignore all the other properties of an experiment.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/01/13</pre>
 */
public class ExperimentComparator implements Comparator<Experiment>{

    private Comparator<Publication> publicationComparator;
    private OrganismComparator organismComparator;
    private Comparator<CvTerm> cvTermComparator;
    private CollectionComparator<VariableParameter> variableParameterCollectionComparator;

    /**
     * Creates a new ExperimentComparator. It needs a Comparator<Publication> to compare publications, a OrganismComparator to compare host organisms
     * and a AbstractCvTermComparator to compare interaction detection methods.
     *
     * @param publicationComparator : comparator for the publication which is required
     * @param organismComparator : comparator for the host organism which is required
     */
    public ExperimentComparator(Comparator<Publication> publicationComparator, OrganismComparator organismComparator){
        if (publicationComparator == null){
            throw new IllegalArgumentException("The publication comparator is required to compare the publications where the experiments have been published. It cannot be null");
        }
        this.publicationComparator = publicationComparator;
        if (organismComparator == null){
            throw new IllegalArgumentException("The organism comparator is required to compare the host organisms where the experiments took place. It cannot be null");
        }
        this.organismComparator = organismComparator;
        this.cvTermComparator = this.organismComparator.getCvTermComparator();
        this.variableParameterCollectionComparator = new VariableParameterCollectionComparator(new VariableParameterComparator(this.cvTermComparator));
    }

    /**
     * <p>Constructor for ExperimentComparator.</p>
     *
     * @param publicationComparator a {@link java.util.Comparator} object.
     * @param organismComparator a {@link psidev.psi.mi.jami.utils.comparator.organism.OrganismComparator} object.
     * @param variableParameter a {@link psidev.psi.mi.jami.utils.comparator.CollectionComparator} object.
     */
    public ExperimentComparator(Comparator<Publication> publicationComparator, OrganismComparator organismComparator, CollectionComparator<VariableParameter> variableParameter){
        if (publicationComparator == null){
            throw new IllegalArgumentException("The publication comparator is required to compare the publications where the experiments have been published. It cannot be null");
        }
        this.publicationComparator = publicationComparator;
        if (organismComparator == null){
            throw new IllegalArgumentException("The organism comparator is required to compare the host organisms where the experiments took place. It cannot be null");
        }
        this.organismComparator = organismComparator;
        this.cvTermComparator = this.organismComparator.getCvTermComparator();
        if (variableParameter == null){
            throw new IllegalArgumentException("The variable parameter comparator is required. It cannot be null");
        }
        this.variableParameterCollectionComparator = variableParameter;
    }

    /**
     * It will look first at the publications using a AbstractPublicationComparator. If the publications are the same, it will look at the
     * interaction detection methods using AbstractCvTermComparator. If the interaction detection methods are the same, it will look at
     * the host organisms using OrganismComparator.
     * If the host organisms are the same, it will look at the variableParameters using VariableParameterComparator.
     *
     * This comparator will ignore all the other properties of an experiment.
     *
     * @param experiment1 a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param experiment2 a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @return a int.
     */
    public int compare(Experiment experiment1, Experiment experiment2) {
        int EQUAL = 0;
        int BEFORE = -1;
        int AFTER = 1;
        if (experiment1 == experiment2){
            return 0;
        }
        else if (experiment1 == null){
            return AFTER;
        }
        else if (experiment2 == null){
            return BEFORE;
        }
        else {
            // first compares publications
            Publication pub1 = experiment1.getPublication();
            Publication pub2 = experiment2.getPublication();

            int comp = publicationComparator.compare(pub1, pub2);
            if (comp != 0){
               return comp;
            }

            // then compares interaction detection method
            CvTerm detMethod1 = experiment1.getInteractionDetectionMethod();
            CvTerm detMethod2 = experiment2.getInteractionDetectionMethod();

            comp = cvTermComparator.compare(detMethod1, detMethod2);
            if (comp != 0){
                return comp;
            }

            // then compares host organisms
            Organism organism1 = experiment1.getHostOrganism();
            Organism organism2 = experiment2.getHostOrganism();

            comp = organismComparator.compare(organism1, organism2);
            if (comp != 0){
                return comp;
            }

            // then compares variable parameters
            Collection<VariableParameter> variableParameters1 = experiment1.getVariableParameters();
            Collection<VariableParameter> variableParameters2 = experiment2.getVariableParameters();

            return variableParameterCollectionComparator.compare(variableParameters1, variableParameters2);
        }
    }

    /**
     * <p>Getter for the field <code>cvTermComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<CvTerm> getCvTermComparator() {
        return cvTermComparator;
    }

    /**
     * <p>Getter for the field <code>publicationComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<Publication> getPublicationComparator() {
        return publicationComparator;
    }

    /**
     * <p>Getter for the field <code>organismComparator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.utils.comparator.organism.OrganismComparator} object.
     */
    public OrganismComparator getOrganismComparator() {
        return organismComparator;
    }

    /**
     * <p>Getter for the field <code>variableParameterCollectionComparator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.utils.comparator.CollectionComparator} object.
     */
    public CollectionComparator<VariableParameter> getVariableParameterCollectionComparator() {
        return variableParameterCollectionComparator;
    }
}

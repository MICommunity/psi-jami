package psidev.psi.mi.jami.enricher;

import psidev.psi.mi.jami.enricher.impl.CompositeInteractorEnricher;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.InteractorPool;

import java.util.Comparator;

/**
 * The Interactor pool enricher is an enricher which can enrich either single interactor pool or a collection.
 * Sub enrichers:
 * CvTerm, Organism, interactor
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since  16/05/13

 */
public interface InteractorPoolEnricher extends InteractorEnricher<InteractorPool>{

    /**
     * <p>getInteractorEnricher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.CompositeInteractorEnricher} object.
     */
    public CompositeInteractorEnricher getInteractorEnricher();

    /**
     * <p>getInteractorComparator.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<Interactor> getInteractorComparator();

    /**
     * <p>setInteractorEnricher.</p>
     *
     * @param enricher a {@link psidev.psi.mi.jami.enricher.impl.CompositeInteractorEnricher} object.
     */
    public void setInteractorEnricher(CompositeInteractorEnricher enricher);

    /**
     * <p>setInteractorComparator.</p>
     *
     * @param comparator a {@link java.util.Comparator} object.
     */
    public void setInteractorComparator(Comparator<Interactor> comparator);

}

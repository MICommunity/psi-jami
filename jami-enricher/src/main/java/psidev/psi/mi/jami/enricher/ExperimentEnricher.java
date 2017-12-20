package psidev.psi.mi.jami.enricher;

import psidev.psi.mi.jami.enricher.listener.ExperimentEnricherListener;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;

/**
 * The experimentEnricher can enrich either a single experiment or a collection.
 * It has no fetcher and only enrich through subEnrichers.
 * Sub enrichers: CvTerm, Organism, Publication.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since  31/07/13

 */
public interface ExperimentEnricher extends MIEnricher<Experiment>{

    /**
     * Gets the subEnricher for Organisms. Can be null.
     *
     * @return  The Organism enricher which is being used.
     */
    public OrganismEnricher getOrganismEnricher();

    /**
     * Gets the subEnricher for CvTerms. Can be null.
     *
     * @return  The CvTerm enricher which is being used.
     */
    public CvTermEnricher<CvTerm> getCvTermEnricher();

    /**
     * Sets the subEnricher for publications. Can be null.
     *
     * @return  The publications enricher which is being used.
     */
    public PublicationEnricher getPublicationEnricher();


    /**
     * Gets current ExperimentEnricherListener. Can be null.
     *
     * @return      The listener which is currently being used.
     */
    public ExperimentEnricherListener getExperimentEnricherListener();

    /**
     * <p>setOrganismEnricher.</p>
     *
     * @param organismEnricher a {@link psidev.psi.mi.jami.enricher.OrganismEnricher} object.
     */
    public void setOrganismEnricher(OrganismEnricher organismEnricher);

    /**
     * <p>setCvTermEnricher.</p>
     *
     * @param cvEnricher a {@link psidev.psi.mi.jami.enricher.CvTermEnricher} object.
     */
    public void setCvTermEnricher(CvTermEnricher<CvTerm> cvEnricher);

    /**
     * <p>setPublicationEnricher.</p>
     *
     * @param publicationEnricher a {@link psidev.psi.mi.jami.enricher.PublicationEnricher} object.
     */
    public void setPublicationEnricher(PublicationEnricher publicationEnricher);

    /**
     * <p>setExperimentEnricherListener.</p>
     *
     * @param listener a {@link psidev.psi.mi.jami.enricher.listener.ExperimentEnricherListener} object.
     */
    public void setExperimentEnricherListener(ExperimentEnricherListener listener);
}

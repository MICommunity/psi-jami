package psidev.psi.mi.jami.enricher.impl.minimal;

import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.listener.OntologyTermEnricherListener;
import psidev.psi.mi.jami.model.OntologyTerm;

/**
 * Provides minimal update of ontologYTerm.
 *
 * - update minimal properties of Cv Term. See description in MinimalCvTermUpdater
 * - update children of a term. It will use DefaultCvTermComparator to compare children and add missing children and
 * remove children that are not in fetched ontology term. It will enrich all the children of the ontologyTerm
 *
 * It will ignore all other properties of an ontologyTerm
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/01/14</pre>
 */
public class MinimalOntologyTermUpdater extends MinimalOntologyTermEnricher{
    /**
     * <p>Constructor for MinimalOntologyTermUpdater.</p>
     *
     * @param cvTermFetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public MinimalOntologyTermUpdater(OntologyTermFetcher cvTermFetcher) {
        super(new MinimalCvTermUpdater<OntologyTerm>(cvTermFetcher));
    }

    /**
     * <p>Constructor for MinimalOntologyTermUpdater.</p>
     *
     * @param cvEnricher a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalCvTermEnricher} object.
     */
    protected MinimalOntologyTermUpdater(MinimalCvTermEnricher<OntologyTerm> cvEnricher) {
        super(cvEnricher);
    }

    /** {@inheritDoc} */
    @Override
    protected void processChildren(OntologyTerm cvTermToEnrich, OntologyTerm cvTermFetched) throws EnricherException {
        mergeOntologyTerms(cvTermToEnrich, cvTermToEnrich.getParents(), cvTermFetched.getParents(), true);
        enrichRelatedTerms(cvTermToEnrich.getParents());
    }

    /** {@inheritDoc} */
    @Override
    protected void processDefinition(OntologyTerm cvTermToEnrich, OntologyTerm cvTermFetched) throws EnricherException{
        if ((cvTermFetched.getDefinition() != null && !cvTermFetched.getDefinition().equalsIgnoreCase(cvTermToEnrich.getDefinition())
                || (cvTermFetched.getDefinition() == null && cvTermFetched.getDefinition() != null))){
            String oldDef = cvTermToEnrich.getDefinition();
            cvTermToEnrich.setDefinition(cvTermFetched.getDefinition());
            if (getCvTermEnricherListener() instanceof OntologyTermEnricherListener){
                ((OntologyTermEnricherListener)getCvTermEnricherListener()).onDefinitionUpdate(cvTermToEnrich, oldDef);
            }
        }
    }
}

package psidev.psi.mi.jami.enricher.impl.minimal;

import org.apache.commons.collections.map.IdentityMap;
import psidev.psi.mi.jami.bridges.fetcher.CvTermFetcher;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.AbstractMIEnricher;
import psidev.psi.mi.jami.enricher.listener.CvTermEnricherListener;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.enricher.listener.OntologyTermEnricherListener;
import psidev.psi.mi.jami.model.OntologyTerm;
import psidev.psi.mi.jami.utils.comparator.cv.DefaultCvTermComparator;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Provides minimal enrichment of ontologYTerm.
 *
 * - enrich minimal properties of Cv Term. See description in MinimalCvTermEnricher
 * - enrich children of a term. It will use DefaultCvTermComparator to compare children and add missing children without
 * removing any existing children. It will enrich the children of the ontologyTerm
 *
 * It will ignore all other properties of an ontologyTerm
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 08/05/13

 */
public class MinimalOntologyTermEnricher extends AbstractMIEnricher<OntologyTerm> implements CvTermEnricher<OntologyTerm>{

    private MinimalCvTermEnricher<OntologyTerm> cvEnricher = null;

    private Map<OntologyTerm, OntologyTerm> processedTerms;

    /**
     * A constructor matching super.
     *
     * @param cvTermFetcher The fetcher to initiate the enricher with.
     *                      If null, an illegal state exception will be thrown at the next enrichment.
     */
    public MinimalOntologyTermEnricher(OntologyTermFetcher cvTermFetcher) {
        this.cvEnricher = new MinimalCvTermEnricher<OntologyTerm>(cvTermFetcher);
        this.processedTerms = new IdentityMap();
    }

    /**
     * <p>Constructor for MinimalOntologyTermEnricher.</p>
     *
     * @param cvEnricher a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalCvTermEnricher} object.
     */
    protected MinimalOntologyTermEnricher(MinimalCvTermEnricher<OntologyTerm> cvEnricher) {
        if (cvEnricher == null){
           throw new IllegalArgumentException("The cv term enricher cannot be null in ontology term enricher");
        }
        this.cvEnricher = cvEnricher;
        this.processedTerms = new IdentityMap();
    }

    /** {@inheritDoc} */
    @Override
    public void enrich(OntologyTerm objectToEnrich) throws EnricherException {
        this.processedTerms.clear();
        this.processedTerms.put(objectToEnrich, objectToEnrich);
        super.enrich(objectToEnrich);
        this.processedTerms.clear();
    }

    /**
     * A method that can be overridden to add to or change the behaviour of enrichment without effecting fetching.
     *
     * @param cvTermToEnrich the CvTerm to enrich
     * @param cvTermFetched a {@link psidev.psi.mi.jami.model.OntologyTerm} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processOntologyTerm(OntologyTerm cvTermToEnrich, OntologyTerm cvTermFetched) throws EnricherException {
        // process definition
        processDefinition(cvTermToEnrich, cvTermFetched);
        // process children
        processChildren(cvTermToEnrich, cvTermFetched);
    }

    /**
     * <p>processDefinition.</p>
     *
     * @param cvTermToEnrich a {@link psidev.psi.mi.jami.model.OntologyTerm} object.
     * @param cvTermFetched a {@link psidev.psi.mi.jami.model.OntologyTerm} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processDefinition(OntologyTerm cvTermToEnrich, OntologyTerm cvTermFetched) throws EnricherException{
        if (cvTermToEnrich.getDefinition() == null && cvTermFetched.getDefinition() != null){
            String oldDef = cvTermToEnrich.getDefinition();
            cvTermToEnrich.setDefinition(cvTermFetched.getDefinition());
            if (getCvTermEnricherListener() instanceof OntologyTermEnricherListener){
                ((OntologyTermEnricherListener)getCvTermEnricherListener()).onDefinitionUpdate(cvTermToEnrich, oldDef);
            }
        }
    }

    /**
     * <p>processChildren.</p>
     *
     * @param cvTermToEnrich a {@link psidev.psi.mi.jami.model.OntologyTerm} object.
     * @param cvTermFetched a {@link psidev.psi.mi.jami.model.OntologyTerm} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processChildren(OntologyTerm cvTermToEnrich, OntologyTerm cvTermFetched) throws EnricherException {
        mergeOntologyTerms(cvTermToEnrich, cvTermToEnrich.getChildren(), cvTermFetched.getChildren(), false);
        enrichRelatedTerms(cvTermToEnrich.getChildren());
    }

    /**
     * <p>enrichRelatedTerms.</p>
     *
     * @param cvTermToEnrich a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void enrichRelatedTerms(Collection<OntologyTerm> cvTermToEnrich) throws EnricherException {
        for (OntologyTerm child : cvTermToEnrich){
            enrichChild(child);
        }
    }

    /**
     * <p>enrichChild.</p>
     *
     * @param objectToEnrich a {@link psidev.psi.mi.jami.model.OntologyTerm} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void enrichChild(OntologyTerm objectToEnrich) throws EnricherException {
        // fetch the object
        OntologyTerm enrichedObject = find(objectToEnrich);

        // if the enriched object is fetched, it exists and enrichment can begin
        if (enrichedObject != null){
            this.cvEnricher.enrich(objectToEnrich, enrichedObject);
            // process definition
            processDefinition(objectToEnrich, enrichedObject);
            // process children
            processChildren(objectToEnrich, enrichedObject);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void onEnrichedVersionNotFound(OntologyTerm cvTermToEnrich) throws EnricherException {
        if(getCvTermEnricherListener() != null)
            getCvTermEnricherListener().onEnrichmentComplete(cvTermToEnrich, EnrichmentStatus.FAILED, "The ontology term does not exist.");
    }

    /** {@inheritDoc} */
    @Override
    public void enrich(OntologyTerm cvTermToEnrich, OntologyTerm cvTermFetched) throws EnricherException {
        this.cvEnricher.enrich(cvTermToEnrich, cvTermFetched);
        processOntologyTerm(cvTermToEnrich, cvTermFetched);
        if(getCvTermEnricherListener() != null) getCvTermEnricherListener().onEnrichmentComplete(cvTermToEnrich, EnrichmentStatus.SUCCESS, "Ontology term enriched successfully.");
    }

    /** {@inheritDoc} */
    @Override
    public OntologyTerm find(OntologyTerm objectToEnrich) throws EnricherException {
        return ((AbstractMIEnricher<OntologyTerm>)this.cvEnricher).find(objectToEnrich);
    }

    /**
     * Takes two lists of ontology terms and produces a list of those to add and those to remove.
     *
     * It will add in toEnrichTerms all xref from fetchedTerms that are not there. It will also remove extra identifiers from toEnrichTerms
     * if remove boolean is true.
     *
     * @param termToEnrich     The object to enrich
     * @param fetchedTerms      The new terms to be added.
     * @param remove: if true, we remove terms that are not in enriched list
     * @param toEnrichTerms a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void mergeOntologyTerms(OntologyTerm termToEnrich, Collection<OntologyTerm> toEnrichTerms, Collection<OntologyTerm> fetchedTerms , boolean remove) throws EnricherException {

        Iterator<OntologyTerm> termIterator = toEnrichTerms.iterator();
        // remove xrefs in toEnrichXrefs that are not in fetchedXrefs
        if (remove){
            while(termIterator.hasNext()){
                OntologyTerm term = termIterator.next();
                boolean containsTerm = false;
                for (OntologyTerm term2 : fetchedTerms){
                    // identical terms
                    if (DefaultCvTermComparator.areEquals(term, term2)){
                        containsTerm = true;
                        break;
                    }
                }
                // remove term not in second list
                if (!containsTerm){
                    termIterator.remove();
                    if (getCvTermEnricherListener() instanceof OntologyTermEnricherListener){
                        ((OntologyTermEnricherListener)getCvTermEnricherListener()).onRemovedChild(termToEnrich, term);
                    }
                }
            }
        }

        // add terms from fetchedTerms that are not in toEnrichTerm
        termIterator = fetchedTerms.iterator();
        while(termIterator.hasNext()){
            OntologyTerm term = termIterator.next();
            boolean containsTerm = false;
            for (OntologyTerm term2 : toEnrichTerms){
                // identical terms
                if (DefaultCvTermComparator.areEquals(term, term2)){
                    containsTerm = true;
                    // enrich terms that are here
                    if (!this.processedTerms.containsKey(term2)){
                        this.processedTerms.put(term2, term2);
                        this.cvEnricher.enrich(term2, term);
                    }
                    break;
                }
            }
            // add missing xref not in second list
            if (!containsTerm){
                toEnrichTerms.add(term);
                if (getCvTermEnricherListener() instanceof OntologyTermEnricherListener){
                    ((OntologyTermEnricherListener)getCvTermEnricherListener()).onAddedChild(termToEnrich, term);
                }
            }
        }
    }

    /**
     * <p>getCvTermFetcher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.bridges.fetcher.CvTermFetcher} object.
     */
    public CvTermFetcher<OntologyTerm> getCvTermFetcher() {
        return this.cvEnricher.getCvTermFetcher();
    }

    /** {@inheritDoc} */
    public void setCvTermEnricherListener(CvTermEnricherListener<OntologyTerm> listener) {
        this.cvEnricher.setCvTermEnricherListener(listener);
    }

    /**
     * <p>getCvTermEnricherListener.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.listener.CvTermEnricherListener} object.
     */
    public CvTermEnricherListener<OntologyTerm> getCvTermEnricherListener() {
        return this.cvEnricher.getCvTermEnricherListener();
    }

    /**
     * <p>Getter for the field <code>cvEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalCvTermEnricher} object.
     */
    protected MinimalCvTermEnricher<OntologyTerm> getCvEnricher() {
        return cvEnricher;
    }
}

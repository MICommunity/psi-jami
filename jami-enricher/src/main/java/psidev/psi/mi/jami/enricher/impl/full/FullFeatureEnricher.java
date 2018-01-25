package psidev.psi.mi.jami.enricher.impl.full;

import org.apache.commons.collections.map.IdentityMap;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalFeatureEnricher;
import psidev.psi.mi.jami.enricher.util.EnricherUtils;
import psidev.psi.mi.jami.model.Feature;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Provides full enrichment of feature.
 *
 * - enrich minimal properties of feature (see MinimalFeatureEnricher)
 * - enrich interaction dependency
 * - enrich interaction effect
 * - enrich xrefs
 * - enrich aliases
 * - enrich annotations
 * - enrich linked features
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 13/08/13

 */
public class FullFeatureEnricher<F extends Feature> extends MinimalFeatureEnricher<F> {
    private Map<F, F> processedFeatures;

    /**
     * <p>Constructor for FullFeatureEnricher.</p>
     */
    public FullFeatureEnricher(){
        this.processedFeatures = new IdentityMap();
    }

    /** {@inheritDoc} */
    @Override
    public void enrich(F objectToEnrich) throws EnricherException {
        this.processedFeatures.clear();
        this.processedFeatures.put(objectToEnrich, objectToEnrich);
        super.enrich(objectToEnrich);
    }

    /** {@inheritDoc} */
    @Override
    protected void processOtherProperties(F featureToEnrich, F objectSource) throws EnricherException {
        processRole(featureToEnrich, objectSource);

        // process xrefs
        processXrefs(featureToEnrich, objectSource);

        // process synonyms
        processAliases(featureToEnrich, objectSource);

        // process annotations
        processAnnotations(featureToEnrich, objectSource);

        // process linked features
        processLinkedFeatures(featureToEnrich, objectSource);
    }

    /**
     * <p>processLinkedFeatures.</p>
     *
     * @param featureToEnrich a F object.
     * @param objectSource a F object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processLinkedFeatures(F featureToEnrich, F objectSource) throws EnricherException {
        mergeLinkedFeatures(featureToEnrich, (Collection<F>)featureToEnrich.getLinkedFeatures(), (Collection<F>)objectSource.getLinkedFeatures(), false);
    }

    /** {@inheritDoc} */
    @Override
    protected void processOtherProperties(F featureToEnrich) throws EnricherException {
        // process interaction dependency
        processRole(featureToEnrich);

    }

    /**
     * <p>processRole.</p>
     *
     * @param featureToEnrich a F object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processRole(F featureToEnrich) throws EnricherException {
        if(getCvTermEnricher() != null && featureToEnrich.getRole() != null) {
            getCvTermEnricher().enrich( featureToEnrich.getRole() );
        }
    }

    /**
     * <p>processRole.</p>
     *
     * @param featureToEnrich a F object.
     * @param objectSource a F object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processRole(F featureToEnrich, F objectSource) throws EnricherException {
        if (objectSource.getRole() != null && featureToEnrich.getRole() == null){
            featureToEnrich.setRole(objectSource.getRole());
            if(getFeatureEnricherListener() != null) {
                getFeatureEnricherListener().onRoleUpdate(featureToEnrich, null);
            }
        }
        processRole(featureToEnrich);
    }

    /**
     * <p>processXrefs.</p>
     *
     * @param objectToEnrich a F object.
     * @param cvTermFetched a F object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processXrefs(F objectToEnrich, F cvTermFetched) throws EnricherException{
        EnricherUtils.mergeXrefs(objectToEnrich, objectToEnrich.getXrefs(), cvTermFetched.getXrefs(), false, false,
                getFeatureEnricherListener(), getFeatureEnricherListener());
    }

    /**
     * <p>processAnnotations.</p>
     *
     * @param objectToEnrich a F object.
     * @param fetchedObject a F object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processAnnotations(F objectToEnrich, F fetchedObject) throws EnricherException{
        EnricherUtils.mergeAnnotations(objectToEnrich, objectToEnrich.getAnnotations(), fetchedObject.getAnnotations(), false,
                getFeatureEnricherListener());
    }

    /**
     * <p>processAliases.</p>
     *
     * @param objectToEnrich a F object.
     * @param termFetched a F object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processAliases(F objectToEnrich, F termFetched) throws EnricherException{
        EnricherUtils.mergeAliases(objectToEnrich, objectToEnrich.getAliases(), termFetched.getAliases(), false, getFeatureEnricherListener());
    }

    /**
     * <p>mergeLinkedFeatures.</p>
     *
     * @param objectToEnrich a F object.
     * @param linkedFeaturesToEnrich a {@link java.util.Collection} object.
     * @param fetchedFeatures a {@link java.util.Collection} object.
     * @param remove a boolean.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void mergeLinkedFeatures(F objectToEnrich, Collection<F> linkedFeaturesToEnrich, Collection<F> fetchedFeatures, boolean remove) throws EnricherException {

        Iterator<F> featureIterator = linkedFeaturesToEnrich.iterator();
        if (remove){
            while(featureIterator.hasNext()){
                F feature = featureIterator.next();
                boolean containsFeature = false;
                for (F feature2 : fetchedFeatures){
                    if (feature == feature2){
                        containsFeature = true;
                        break;
                    }
                }
                // remove term not in second list
                if (!containsFeature){
                    featureIterator.remove();
                    if (getFeatureEnricherListener() != null){
                        getFeatureEnricherListener().onRemovedLinkedFeature(objectToEnrich, feature);
                    }
                }
            }
        }

        // add terms from fetchedTerms that are not in toEnrichTerm
        featureIterator = fetchedFeatures.iterator();
        while(featureIterator.hasNext()){
            F feature = featureIterator.next();
            boolean containsFeature = false;
            for (F feature2 : linkedFeaturesToEnrich){
                // identical terms
                if (feature == feature2){
                    containsFeature = true;
                    // enrich terms that are here
                    if (!processedFeatures.containsKey(feature2)){
                        processedFeatures.put(feature2, feature2);
                        enrich(feature2, feature);
                    }
                    break;
                }
            }
            // add missing xref not in second list
            if (!containsFeature){
                linkedFeaturesToEnrich.add(feature);
                if (getFeatureEnricherListener() != null){
                    getFeatureEnricherListener().onAddedLinkedFeature(objectToEnrich, feature);
                }
            }
        }
    }
}

package psidev.psi.mi.jami.enricher.impl;

import psidev.psi.mi.jami.enricher.MIEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;

import java.util.Collection;

/**
 * Abstract implementation of a MIEnricher
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/09/13</pre>
 */
public abstract class AbstractMIEnricher<T extends Object> implements MIEnricher<T>{

    /**
     * <p>enrich.</p>
     *
     * @param objectToEnrich a T object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void enrich(T objectToEnrich) throws EnricherException {
        if(objectToEnrich == null)
            throw new IllegalArgumentException("Cannot enrich a null object.");

        // fetch the object
        T enrichedObject = find(objectToEnrich);

        // if the enriched object is fetched, it exists and enrichment can begin
        if (enrichedObject != null){
            enrich(objectToEnrich, enrichedObject);
        }
        // no enriched version of the object
        else{
            onEnrichedVersionNotFound(objectToEnrich);
        }
    }

    /**
     * <p>enrich.</p>
     *
     * @param objects a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void enrich(Collection<T> objects) throws EnricherException {
        if(objects == null)
            throw new IllegalArgumentException("Cannot enrich a null collection of objects.");

        for (T object : objects){
            enrich(object);
        }
    }

    /**
     * <p>enrich.</p>
     *
     * @param objectToEnrich a T object.
     * @param fetchedObject a T object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public abstract void enrich(T objectToEnrich, T fetchedObject) throws EnricherException ;

    /**
     * <p>find.</p>
     *
     * @param objectToEnrich a T object.
     * @return a T object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public abstract T find(T objectToEnrich) throws EnricherException ;

    /**
     * <p>onEnrichedVersionNotFound.</p>
     *
     * @param objectToEnrich a T object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected abstract void onEnrichedVersionNotFound(T objectToEnrich) throws EnricherException ;
}

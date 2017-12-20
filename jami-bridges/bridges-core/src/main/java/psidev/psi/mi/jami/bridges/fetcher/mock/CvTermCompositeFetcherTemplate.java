package psidev.psi.mi.jami.bridges.fetcher.mock;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.CvTermFetcher;
import psidev.psi.mi.jami.model.CvTerm;

import java.util.*;

/**
 * Template for a CvTermCompositeFetcher which delegates fetching to other fetcher
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/13</pre>
 */
public class CvTermCompositeFetcherTemplate<T extends CvTerm, F extends CvTermFetcher<T>> implements CvTermFetcher<T>{

    private Map<String, F> delegateFetchers;

    /**
     * <p>Constructor for CvTermCompositeFetcherTemplate.</p>
     */
    public CvTermCompositeFetcherTemplate(){
        this.delegateFetchers = new HashMap<String, F>();
    }

    /** {@inheritDoc} */
    public T fetchByIdentifier(String termIdentifier, String ontologyDatabaseName) throws BridgeFailedException {

        if (ontologyDatabaseName == null || !this.delegateFetchers.containsKey(ontologyDatabaseName)){
            T firstTermRetrieved = null;
            Iterator<F> fetcherIterator = delegateFetchers.values().iterator();
            while(firstTermRetrieved == null && fetcherIterator.hasNext()){
                 firstTermRetrieved = fetcherIterator.next().fetchByIdentifier(termIdentifier, ontologyDatabaseName);
            }

            return firstTermRetrieved;
        }

        F fetcher = this.delegateFetchers.get(ontologyDatabaseName);
        if (fetcher == null){
            return null;
        }
        else {
            return fetcher.fetchByIdentifier(termIdentifier, ontologyDatabaseName);
        }
    }

    /**
     * <p>fetchByIdentifier.</p>
     *
     * @param termIdentifier a {@link java.lang.String} object.
     * @param ontologyDatabase a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @return a T object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public T fetchByIdentifier(String termIdentifier, CvTerm ontologyDatabase) throws BridgeFailedException {
        if (ontologyDatabase == null || !this.delegateFetchers.containsKey(ontologyDatabase.getShortName())){
            T firstTermRetrieved = null;
            Iterator<F> fetcherIterator = delegateFetchers.values().iterator();
            while(firstTermRetrieved == null && fetcherIterator.hasNext()){
                firstTermRetrieved = fetcherIterator.next().fetchByIdentifier(termIdentifier, ontologyDatabase);
            }

            return firstTermRetrieved;
        }

        F fetcher = this.delegateFetchers.get(ontologyDatabase.getShortName());
        if (fetcher == null){
            return null;
        }
        else {
            return fetcher.fetchByIdentifier(termIdentifier, ontologyDatabase);
        }
    }

    /** {@inheritDoc} */
    public T fetchByName(String searchName, String ontologyDatabaseName) throws BridgeFailedException {
        if (ontologyDatabaseName == null || !this.delegateFetchers.containsKey(ontologyDatabaseName)){
            T firstTermRetrieved = null;
            Iterator<F> fetcherIterator = delegateFetchers.values().iterator();
            while(firstTermRetrieved == null && fetcherIterator.hasNext()){
                firstTermRetrieved = fetcherIterator.next().fetchByName(searchName, ontologyDatabaseName);
            }

            return firstTermRetrieved;
        }

        F fetcher = this.delegateFetchers.get(ontologyDatabaseName);
        if (fetcher == null){
            return null;
        }
        else {
            return fetcher.fetchByName(searchName, ontologyDatabaseName);
        }
    }

    /** {@inheritDoc} */
    public Collection<T> fetchByName(String searchName) throws BridgeFailedException {
        Collection<T> firstTermRetrieved = null;
        Iterator<F> fetcherIterator = delegateFetchers.values().iterator();
        while(firstTermRetrieved == null && fetcherIterator.hasNext()){
            firstTermRetrieved = fetcherIterator.next().fetchByName(searchName);
        }

        return firstTermRetrieved;
    }

    /**
     * <p>fetchByIdentifiers.</p>
     *
     * @param termIdentifiers a {@link java.util.Collection} object.
     * @param ontologyDatabaseName a {@link java.lang.String} object.
     * @return a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public Collection<T> fetchByIdentifiers(Collection<String> termIdentifiers, String ontologyDatabaseName) throws BridgeFailedException {
        if (ontologyDatabaseName == null || !this.delegateFetchers.containsKey(ontologyDatabaseName)){
            Collection<T> firstTermRetrieved = Collections.EMPTY_LIST;
            Iterator<F> fetcherIterator = delegateFetchers.values().iterator();
            while(firstTermRetrieved.isEmpty() && fetcherIterator.hasNext()){
                firstTermRetrieved = fetcherIterator.next().fetchByIdentifiers(termIdentifiers, ontologyDatabaseName);
            }

            return firstTermRetrieved;
        }

        F fetcher = this.delegateFetchers.get(ontologyDatabaseName);
        if (fetcher == null){
            return null;
        }
        else {
            return fetcher.fetchByIdentifiers(termIdentifiers, ontologyDatabaseName);
        }
    }

    /** {@inheritDoc} */
    public Collection<T> fetchByIdentifiers(Collection<String> termIdentifiers, CvTerm ontologyDatabase) throws BridgeFailedException {
        if (ontologyDatabase == null || !this.delegateFetchers.containsKey(ontologyDatabase.getShortName())){
            Collection<T> firstTermRetrieved = Collections.EMPTY_LIST;
            Iterator<F> fetcherIterator = delegateFetchers.values().iterator();
            while(firstTermRetrieved.isEmpty() && fetcherIterator.hasNext()){
                firstTermRetrieved = fetcherIterator.next().fetchByIdentifiers(termIdentifiers, ontologyDatabase);
            }

            return firstTermRetrieved;
        }

        F fetcher = this.delegateFetchers.get(ontologyDatabase.getShortName());
        if (fetcher == null){
            return null;
        }
        else {
            return fetcher.fetchByIdentifiers(termIdentifiers, ontologyDatabase);
        }
    }

    /** {@inheritDoc} */
    public Collection<T> fetchByNames(Collection<String> searchNames, String ontologyDatabaseName) throws BridgeFailedException {
        if (ontologyDatabaseName == null || !this.delegateFetchers.containsKey(ontologyDatabaseName)){
            Collection<T> firstTermRetrieved = Collections.EMPTY_LIST;
            Iterator<F> fetcherIterator = delegateFetchers.values().iterator();
            while(firstTermRetrieved.isEmpty() && fetcherIterator.hasNext()){
                firstTermRetrieved = fetcherIterator.next().fetchByNames(searchNames, ontologyDatabaseName);
            }

            return firstTermRetrieved;
        }

        F fetcher = this.delegateFetchers.get(ontologyDatabaseName);
        if (fetcher == null){
            return null;
        }
        else {
            return fetcher.fetchByNames(searchNames, ontologyDatabaseName);
        }
    }

    /** {@inheritDoc} */
    public Collection<T> fetchByNames(Collection<String> searchNames) throws BridgeFailedException {
        Collection<T> firstTermRetrieved = Collections.EMPTY_LIST;
        Iterator<F> fetcherIterator = delegateFetchers.values().iterator();
        while(firstTermRetrieved.isEmpty() && fetcherIterator.hasNext()){
            firstTermRetrieved = fetcherIterator.next().fetchByNames(searchNames);
        }

        return firstTermRetrieved;
    }

    /**
     * <p>addCvTermFetcher.</p>
     *
     * @param ontologyDatabase a {@link java.lang.String} object.
     * @param fetcher a F object.
     */
    public void addCvTermFetcher(String ontologyDatabase, F fetcher){
        this.delegateFetchers.put(ontologyDatabase, fetcher);
    }

    /**
     * <p>removeCvTermFetcher.</p>
     *
     * @param ontologyDatabase a {@link java.lang.String} object.
     */
    public void removeCvTermFetcher(String ontologyDatabase){
        this.delegateFetchers.remove(ontologyDatabase);
    }

    /**
     * <p>Getter for the field <code>delegateFetchers</code>.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    protected Map<String, F> getDelegateFetchers() {
        return delegateFetchers;
    }
}

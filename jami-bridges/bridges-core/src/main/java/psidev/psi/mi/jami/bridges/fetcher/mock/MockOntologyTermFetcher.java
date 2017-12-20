package psidev.psi.mi.jami.bridges.fetcher.mock;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.OntologyTerm;

import java.util.*;

/**
 * A mock fetcher for testing.
 * It extends all the methods of the true fetcher, but does not access an external service.
 * Instead, it holds a map which can be loaded with objects and keys. which are then returned.
 * It attempts to replicate the responses of the fetcher in most scenarios.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 08/07/13

 */
public class MockOntologyTermFetcher
        implements OntologyTermFetcher{

    private Map<String,OntologyTerm> localOntologyTerms;

    /**
     * <p>Constructor for MockOntologyTermFetcher.</p>
     */
    public MockOntologyTermFetcher(){
        localOntologyTerms = new HashMap<String, OntologyTerm>();
    }

    /**
     * <p>addOntologyTerm.</p>
     *
     * @param identifier a {@link java.lang.String} object.
     * @param ontologyTerm a {@link psidev.psi.mi.jami.model.OntologyTerm} object.
     */
    public void addOntologyTerm(String identifier , OntologyTerm ontologyTerm){
        if(ontologyTerm == null || identifier == null) return;
        this.localOntologyTerms.put(identifier , ontologyTerm);
    }

    /**
     * <p>clear.</p>
     */
    public void clear(){
        localOntologyTerms.clear();
    }

    private Collection<OntologyTerm> getMockTermCollection(Collection<String> termIdentifiers){
        Collection<OntologyTerm> value = new ArrayList<OntologyTerm>();
        for(String term : termIdentifiers){
            value.add(localOntologyTerms.get(term));
        }
        return value;
    }

    /** {@inheritDoc} */
    public OntologyTerm fetchByIdentifier(String termIdentifier, String ontologyDatabaseName)
            throws BridgeFailedException {
        return localOntologyTerms.get(termIdentifier);
    }

    /**
     * <p>fetchByIdentifier.</p>
     *
     * @param termIdentifier a {@link java.lang.String} object.
     * @param ontologyDatabase a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @return a {@link psidev.psi.mi.jami.model.OntologyTerm} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public OntologyTerm fetchByIdentifier(String termIdentifier, CvTerm ontologyDatabase)
            throws BridgeFailedException {
        return localOntologyTerms.get(termIdentifier);
    }

    /** {@inheritDoc} */
    public OntologyTerm fetchByName(String searchName, String ontologyDatabaseName)
            throws BridgeFailedException {
        return localOntologyTerms.get(searchName);
    }

    /** {@inheritDoc} */
    public Collection<OntologyTerm> fetchByName(String searchName) throws BridgeFailedException {
        return Arrays.asList(localOntologyTerms.get(searchName));
    }

    /**
     * <p>fetchByIdentifiers.</p>
     *
     * @param identifiers a {@link java.util.Collection} object.
     * @param ontologyDatabaseName a {@link java.lang.String} object.
     * @return a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public Collection<OntologyTerm> fetchByIdentifiers(Collection<String> identifiers, String ontologyDatabaseName)
            throws BridgeFailedException {
        return getMockTermCollection(identifiers);
    }

    /** {@inheritDoc} */
    public Collection<OntologyTerm> fetchByIdentifiers(Collection<String> identifiers, CvTerm ontologyDatabase)
            throws BridgeFailedException {
        return getMockTermCollection(identifiers);
    }

    /** {@inheritDoc} */
    public Collection<OntologyTerm> fetchByNames(Collection<String> searchNames, String ontologyDatabaseName)
            throws BridgeFailedException {
        return getMockTermCollection(searchNames);
    }

    /** {@inheritDoc} */
    public Collection<OntologyTerm> fetchByNames(Collection<String> searchNames)
            throws BridgeFailedException {
        return getMockTermCollection(searchNames);
    }

    /** {@inheritDoc} */
    public Set<OntologyTerm> fetchRootTerms(String databaseName) throws BridgeFailedException {
        return Collections.EMPTY_SET;
    }

    /**
     * <p>fetchRootTerms.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @return a {@link java.util.Set} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public Set<OntologyTerm> fetchRootTerms(CvTerm database) throws BridgeFailedException {
        return Collections.EMPTY_SET;
    }
}

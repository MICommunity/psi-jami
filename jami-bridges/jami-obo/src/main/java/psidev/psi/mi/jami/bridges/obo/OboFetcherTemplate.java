package psidev.psi.mi.jami.bridges.obo;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.CvTermFetcher;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.utils.comparator.cv.DefaultCvTermComparator;

import java.io.File;
import java.util.*;

/**
 * Template fetcher for OBO files
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/07/13</pre>
 */
public class OboFetcherTemplate<T extends CvTerm> implements CvTermFetcher<T> {

    private Map<String, T> id2Term;
    private Map<String, T> name2Term;
    private CvTerm ontologyDatabase;

    /**
     * <p>Constructor for OboFetcherTemplate.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param oboLoader a {@link psidev.psi.mi.jami.bridges.obo.AbstractOboLoader} object.
     * @param filePath a {@link java.lang.String} object.
     */
    public OboFetcherTemplate(CvTerm database, AbstractOboLoader<T> oboLoader, String filePath){
        if (oboLoader == null){
            throw new IllegalArgumentException("The OBO loader cannot be null and is needed to parse the OBO file");
        }
        if (filePath == null){
            throw new IllegalArgumentException("The OBO file cannot be null and is needed to load an ontology");
        }
        this.ontologyDatabase = database != null ? database : new DefaultCvTerm("unknown");
        initialiseLocalMaps();
        oboLoader.parseOboFile(new File(filePath), id2Term, name2Term);
    }

    /**
     * <p>Constructor for OboFetcherTemplate.</p>
     *
     * @param databaseName a {@link java.lang.String} object.
     * @param oboLoader a {@link psidev.psi.mi.jami.bridges.obo.AbstractOboLoader} object.
     * @param filePath a {@link java.lang.String} object.
     */
    public OboFetcherTemplate(String databaseName, AbstractOboLoader<T> oboLoader, String filePath){
        if (oboLoader == null){
            throw new IllegalArgumentException("The OBO loader cannot be null and is needed to parse the OBO file");
        }
        if (filePath == null){
            throw new IllegalArgumentException("The OBO file cannot be null and is needed to load an ontology");
        }
        this.ontologyDatabase = databaseName != null ? new DefaultCvTerm(databaseName) : new DefaultCvTerm("unknown");
        initialiseLocalMaps();
        oboLoader.parseOboFile(new File(filePath), id2Term, name2Term);
    }

    /** {@inheritDoc} */
    public T fetchByIdentifier(String termIdentifier, String ontologyDatabaseName) throws BridgeFailedException {
        if (ontologyDatabaseName != null && !this.ontologyDatabase.getShortName().equalsIgnoreCase(ontologyDatabaseName)){
            return null;
        }
        return id2Term.get(termIdentifier);
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
        if (ontologyDatabase != null && !DefaultCvTermComparator.areEquals(ontologyDatabase, this.ontologyDatabase)){
            return null;
        }
        return id2Term.get(termIdentifier);
    }

    /** {@inheritDoc} */
    public T fetchByName(String searchName, String ontologyDatabaseName) throws BridgeFailedException {
        if (ontologyDatabaseName != null && !this.ontologyDatabase.getShortName().equalsIgnoreCase(ontologyDatabaseName)){
            return null;
        }
        return name2Term.get(searchName);
    }

    /** {@inheritDoc} */
    public Collection<T> fetchByName(String searchName) throws BridgeFailedException {
        return Collections.singletonList(name2Term.get(searchName));
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
        if (ontologyDatabaseName != null && !this.ontologyDatabase.getShortName().equalsIgnoreCase(ontologyDatabaseName)){
            return Collections.EMPTY_LIST;
        }
        Collection<T> terms = new ArrayList<T>(termIdentifiers.size());

        for (String id : termIdentifiers){
            if (id2Term.containsKey(id)){
                terms.add(id2Term.get(id));
            }
        }

        return terms;
    }

    /** {@inheritDoc} */
    public Collection<T> fetchByIdentifiers(Collection<String> termIdentifiers, CvTerm ontologyDatabase) throws BridgeFailedException {
        if (ontologyDatabase != null && !DefaultCvTermComparator.areEquals(ontologyDatabase, this.ontologyDatabase)){
            return Collections.EMPTY_LIST;
        }
        Collection<T> terms = new ArrayList<T>(termIdentifiers.size());

        for (String id : termIdentifiers){
            if (id2Term.containsKey(id)){
                terms.add(id2Term.get(id));
            }
        }

        return terms;
    }

    /** {@inheritDoc} */
    public Collection<T> fetchByNames(Collection<String> searchNames, String ontologyDatabaseName) throws BridgeFailedException {
        if (ontologyDatabaseName != null && !this.ontologyDatabase.getShortName().equalsIgnoreCase(ontologyDatabaseName)){
            return Collections.EMPTY_LIST;
        }
        Collection<T> terms = new ArrayList<T>(searchNames.size());

        for (String name : searchNames){
            if (name2Term.containsKey(name)){
                terms.add(name2Term.get(name));
            }
        }

        return terms;
    }

    /** {@inheritDoc} */
    public Collection<T> fetchByNames(Collection<String> searchNames) throws BridgeFailedException {
        Collection<T> terms = new ArrayList<T>(searchNames.size());

        for (String name : searchNames){
            if (name2Term.containsKey(name)){
                terms.add(name2Term.get(name));
            }
        }

        return terms;
    }

    /**
     * <p>initialiseLocalMaps.</p>
     */
    protected void initialiseLocalMaps(){
        this.id2Term = new HashMap<String, T>();
        this.name2Term = new HashMap<String, T>();
    }

    /**
     * <p>Getter for the field <code>id2Term</code>.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    protected Map<String, T> getId2Term() {
        return id2Term;
    }

    /**
     * <p>Getter for the field <code>name2Term</code>.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    protected Map<String, T> getName2Term() {
        return name2Term;
    }

    /**
     * <p>Getter for the field <code>ontologyDatabase</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    protected CvTerm getOntologyDatabase() {
        return ontologyDatabase;
    }
}

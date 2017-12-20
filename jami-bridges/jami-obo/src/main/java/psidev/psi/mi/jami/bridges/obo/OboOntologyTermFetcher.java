package psidev.psi.mi.jami.bridges.obo;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.OntologyTerm;
import psidev.psi.mi.jami.utils.comparator.cv.DefaultCvTermComparator;

import java.util.*;

/**
 * The ontology fetcher based on OBO file
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/07/13</pre>
 */
public class OboOntologyTermFetcher extends OboFetcherTemplate<OntologyTerm> implements OntologyTermFetcher{

    /**
     * <p>Constructor for OboOntologyTermFetcher.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param filePath a {@link java.lang.String} object.
     */
    public OboOntologyTermFetcher(CvTerm database, String filePath) {
        super(database, new OntologyOboLoader(database), filePath);
    }

    /**
     * <p>Constructor for OboOntologyTermFetcher.</p>
     *
     * @param databaseName a {@link java.lang.String} object.
     * @param filePath a {@link java.lang.String} object.
     */
    public OboOntologyTermFetcher(String databaseName, String filePath) {
        super(databaseName, new OntologyOboLoader(databaseName), filePath);
    }

    /** {@inheritDoc} */
    public Set<OntologyTerm> fetchRootTerms(String database) throws BridgeFailedException {

        if (database != null && !getOntologyDatabase().getShortName().equalsIgnoreCase(database)){
            return Collections.EMPTY_SET;
        }
        Set<OntologyTerm> terms = new HashSet<OntologyTerm>();

        for ( Iterator<OntologyTerm> iterator = getId2Term().values().iterator(); iterator.hasNext(); ) {
            OntologyTerm ontologyTerm = iterator.next();

            if ( ontologyTerm.getParents().isEmpty() ) {
                terms.add( ontologyTerm );
            }
        }

        if ( terms.isEmpty() ) {
            return Collections.EMPTY_SET;
        }

        return terms;
    }

    /**
     * <p>fetchRootTerms.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @return a {@link java.util.Set} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public Set<OntologyTerm> fetchRootTerms(CvTerm database) throws BridgeFailedException{

        if (database != null && !DefaultCvTermComparator.areEquals(getOntologyDatabase(), database)){
            return Collections.EMPTY_SET;
        }

        Set<OntologyTerm> terms = new HashSet<OntologyTerm>();

        for ( Iterator<OntologyTerm> iterator = getId2Term().values().iterator(); iterator.hasNext(); ) {
            OntologyTerm ontologyTerm = iterator.next();

            if ( ontologyTerm.getParents().isEmpty() ) {
                terms.add( ontologyTerm );
            }
        }

        if ( terms.isEmpty() ) {
            return Collections.EMPTY_SET;
        }

        return terms;
    }
}

package psidev.psi.mi.jami.bridges.ols;


import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.OntologyTerm;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.XrefUtils;
import uk.ac.ebi.pride.utilities.ols.web.service.model.Term;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Finds ontology terms in the Ontology Lookup Service
 * as well as having options to recursively find parents and or children.
 *
 * Implements CvTermFetcher, returning the results as OntologyTerms.
 * Further to this, the OntologyTermFetcher is implemented to include methods for finding children and parents.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 03/07/13

 */
public class OlsOntologyTermFetcher extends AbstractOlsFetcher<OntologyTerm> implements OntologyTermFetcher{

    /**
     * <p>Constructor for OlsOntologyTermFetcher.</p>
     *
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public OlsOntologyTermFetcher() throws BridgeFailedException {
        super();
    }

    /** {@inheritDoc} */
    @Override
    protected OntologyTerm instantiateCvTerm(String termName, Xref identity, String ontologyName) {
        return new LazyOntologyTerm(olsClient, termName, identity, ontologyName);
    }

    /** {@inheritDoc} */
    public Set<OntologyTerm> fetchRootTerms(String databaseName) throws BridgeFailedException{
        if(databaseName == null || databaseName.isEmpty())
            throw new IllegalArgumentException("Can not search for a root term of an ontology without its ontology name.");

        String olsOntologyName = null;
        if(dbMap.containsKey(databaseName))
            olsOntologyName = dbMap.get(databaseName);

        Map roots = new HashMap<>();
        for(Term term : this.olsClient.getRootTerms(olsOntologyName)){
            roots.put(term.getTermOBOId().getIdentifier(), term);
        }
        Set<OntologyTerm> rootTerms = new HashSet<>();
        for (Object obj : roots.keySet()){
            if (obj != null){
                rootTerms.add(new LazyOntologyTerm(olsClient, null, createXref((String)obj , databaseName), olsOntologyName));
            }
        }
        return rootTerms;
    }

    /**
     * <p>fetchRootTerms.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @return a {@link java.util.Set} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public Set<OntologyTerm> fetchRootTerms(CvTerm database) throws BridgeFailedException {
        if(database == null || database.getShortName().isEmpty())
            throw new IllegalArgumentException("Can not search for a root term of an ontology without its ontology name.");

        String olsOntologyName = null;
        if(dbMap.containsKey(database.getShortName()))
            olsOntologyName = dbMap.get(database.getShortName());

        Map roots = new HashMap<>();
        for(Term term : this.olsClient.getRootTerms(olsOntologyName)){
            roots.put(term.getTermOBOId().getIdentifier(), term);
        }
        Set<OntologyTerm> rootTerms = new HashSet<OntologyTerm>();
        for (Object obj : roots.keySet()){
            if (obj != null){
                rootTerms.add(new LazyOntologyTerm(olsClient, null, XrefUtils.createIdentityXref(database.getShortName(), database.getMIIdentifier(), (String)obj),
                        olsOntologyName));
            }
        }
        return rootTerms;
    }
}

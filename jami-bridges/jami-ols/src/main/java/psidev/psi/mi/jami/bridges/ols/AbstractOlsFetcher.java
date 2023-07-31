package psidev.psi.mi.jami.bridges.ols;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.CvTermFetcher;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.XrefUtils;
import uk.ac.ebi.pride.utilities.ols.web.service.client.OLSClient;
import uk.ac.ebi.pride.utilities.ols.web.service.config.OLSWsConfigProd;
import uk.ac.ebi.pride.utilities.ols.web.service.model.Identifier;
import uk.ac.ebi.pride.utilities.ols.web.service.model.Term;

import java.util.*;

/**
 * Abstract class for OlsCvTermFetcher
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/08/13</pre>
 */
public abstract class AbstractOlsFetcher<T extends CvTerm> implements CvTermFetcher<T> {

    protected OLSClient olsClient;
    protected Map<String,String> dbMap = new HashMap<String, String>();

    /**
     * <p>Constructor for AbstractOlsFetcher.</p>
     *
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public AbstractOlsFetcher() throws BridgeFailedException {
        this.olsClient = new OLSClient(new OLSWsConfigProd());
        initialiseDbMap();
    }

    /**
     * <p>initialiseDbMap.</p>
     */
    protected void initialiseDbMap(){
        dbMap.put("psi-mi", "MI");
        dbMap.put("psi-mod", "MOD");
        dbMap.put("psi-par", "PAR");
        dbMap.put("go", "GO");
        dbMap.put("evidence ontology", "ECO");
        dbMap.put("ncbi taxonomy", "NCBITaxon");
    }

    /**
     * <p>createXref.</p>
     *
     * @param identifier a {@link java.lang.String} object.
     * @param miOntologyName a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected Xref createXref(String identifier, String  miOntologyName){
        if (CvTerm.PSI_MI.equalsIgnoreCase(miOntologyName)){
            return XrefUtils.createPsiMiIdentity(identifier);
        } else if(CvTerm.PSI_MOD.equalsIgnoreCase(miOntologyName)) {
            return XrefUtils.createPsiModIdentity(identifier);
        } else {
            return XrefUtils.createIdentityXref(miOntologyName , identifier);
        }
    }

    /** {@inheritDoc} */
    public T fetchByIdentifier(String termIdentifier, String miOntologyName) throws BridgeFailedException {

        if(termIdentifier == null || termIdentifier.isEmpty())
            throw new IllegalArgumentException("Can not search for an identifier without a value.");
        if(miOntologyName == null || miOntologyName.isEmpty())
            throw new IllegalArgumentException("Can not search for an identifier in an ontology without a value.");

        String olsOntologyName = null;
        if(dbMap.containsKey(miOntologyName))
            olsOntologyName = dbMap.get(miOntologyName);
        String fullName = null;

        // 1) query ols which returns full name.
        Identifier identifier = new Identifier(termIdentifier, Identifier.IdentifierType.OBO);
        try {
            fullName = olsClient.getTermById(identifier, olsOntologyName).getLabel();
        }
        catch (HttpClientErrorException e){
            //If the exception is different to 404 (not found) we pass it,
            // in other case we ignore it a return null
           if(!HttpStatus.NOT_FOUND.equals(e.getStatusCode())){
               throw new BridgeFailedException(e);
           }
        }

        // 2) if no results, return null
        if (fullName == null || fullName.equals(termIdentifier))
            return null;

        // 3) if a result, call instantiateCvTerm with provided fullName and create identity xref
        return instantiateCvTerm(fullName , createXref(termIdentifier , miOntologyName), olsOntologyName);
    }

    /**
     * <p>fetchByIdentifier.</p>
     *
     * @param termIdentifier a {@link java.lang.String} object.
     * @param ontologyCvTerm a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @return a T object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public T fetchByIdentifier(String termIdentifier, CvTerm ontologyCvTerm) throws BridgeFailedException {

        if(termIdentifier == null || termIdentifier.isEmpty())
            throw new IllegalArgumentException("Can not search for an identifier without a value.");
        if(ontologyCvTerm == null)
            throw new IllegalArgumentException("Can not search for an identifier in an ontology without a value.");

        String olsOntologyName = null;
        if(dbMap.containsKey(ontologyCvTerm.getShortName()))
            olsOntologyName = dbMap.get(ontologyCvTerm.getShortName());
        String fullName = null;

        // 1) query ols which returns full name.
        Identifier identifier = new Identifier(termIdentifier, Identifier.IdentifierType.OBO);
        try {
            fullName = olsClient.getTermById(identifier, olsOntologyName).getLabel();
        }
        catch (HttpClientErrorException e){
            //If the exception is different to 404 (not found) we pass it,
            // in other case we ignore it a return null
            if(!HttpStatus.NOT_FOUND.equals(e.getStatusCode())){
                throw new BridgeFailedException(e);
            }
        }

        // 2) if no results, return null
        if (fullName == null || fullName.equals(termIdentifier))
            return null;

        // 3) if a result, call instantiateCvTerm with provided fullName and create identity xref
        return instantiateCvTerm(fullName , XrefUtils.createIdentityXref(ontologyCvTerm.getShortName(), ontologyCvTerm.getMIIdentifier() , termIdentifier), olsOntologyName);
    }

    /** {@inheritDoc} */
    //TODO review ols 404 client exception
    public T fetchByName(String searchName, String miOntologyName) throws BridgeFailedException {

        if(searchName == null || searchName.isEmpty())
            throw new IllegalArgumentException("Can not search for an identifier without a value.");

        String olsOntologyName = null;
        if(dbMap.containsKey(miOntologyName))
            olsOntologyName = dbMap.get(miOntologyName).toLowerCase();

        // 1) query ols which returns full name.
        HashMap<String,String> termNamesMap = new HashMap<>();
        Term term = olsClient.getExactTermByName(searchName, olsOntologyName);

        // 2) if no results, return null
        if (term == null) return null;
        termNamesMap.put(term.getTermOBOId().getIdentifier(), term.getLabel());

        Map.Entry<String, String> entry = termNamesMap.entrySet().iterator().next();
        String fullName = entry.getValue();

        // 3) if a result, call instantiateCvTerm with provided fullName and create identity xref
        return instantiateCvTerm(fullName , createXref(entry.getKey() , miOntologyName), olsOntologyName);
    }

    /** {@inheritDoc} */
    //TODO review ols 404 client exception
    public Collection<T> fetchByName(String searchName) throws BridgeFailedException {
        if(searchName == null || searchName.isEmpty())
            throw new IllegalArgumentException("Can not search for an identifier without a value.");

        // 1) query ols which returns full name.
        HashMap<String,String> termNamesMap = new HashMap<>();
        Term term = olsClient.getExactTermByName(searchName, null);
        termNamesMap.put(term.getTermOBOId().getIdentifier(), term.getLabel());

        // 2) if no results, return null
        if(termNamesMap.isEmpty())
            return Collections.EMPTY_LIST;

        Collection<T> results = new ArrayList<T>(termNamesMap.size());

        for (String key : termNamesMap.keySet()){
            String fullName = termNamesMap.get(key);

            // 3) if a result, call instantiateCvTerm with provided fullName and create identity xref
            results.add(instantiateCvTerm(fullName, createXref(key, "unknown"), term.getOntologyName().toLowerCase()));
        }

        return results;
    }

    /**
     * <p>fetchByIdentifiers.</p>
     *
     * @param termIdentifiers a {@link java.util.Collection} object.
     * @param miOntologyName a {@link java.lang.String} object.
     * @return a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public Collection<T> fetchByIdentifiers(Collection<String> termIdentifiers, String miOntologyName)
            throws BridgeFailedException {
        if (termIdentifiers == null){
            throw new IllegalArgumentException("The term identifiers cannot be null.");
        }

        Collection<T> results = new ArrayList<T>(termIdentifiers.size());
        for (String id : termIdentifiers){
            T element = fetchByIdentifier(id, miOntologyName);
            if (element != null){
                results.add(element);
            }
        }
        return results;
    }

    /** {@inheritDoc} */
    public Collection<T> fetchByIdentifiers(Collection<String> termIdentifiers, CvTerm ontologyDatabase)
            throws BridgeFailedException {
        if (termIdentifiers == null){
            throw new IllegalArgumentException("The term identifiers cannot be null.");
        }

        Collection<T> results = new ArrayList<T>(termIdentifiers.size());
        for (String id : termIdentifiers){
            T element = fetchByIdentifier(id, ontologyDatabase);
            if (element != null){
                results.add(element);
            }
        }
        return results;
    }

    /** {@inheritDoc} */
    public Collection<T> fetchByNames(Collection<String> searchNames, String miOntologyName)
            throws BridgeFailedException {
        if (searchNames == null){
            throw new IllegalArgumentException("The term identifiers cannot be null.");
        }

        Collection<T> results = new ArrayList<T>(searchNames.size());
        for (String id : searchNames){
            T element = fetchByName(id, miOntologyName);
            if (element != null){
                results.add(element);
            }
        }
        return results;
    }

    /** {@inheritDoc} */
    public Collection<T> fetchByNames(Collection<String> searchNames)
            throws BridgeFailedException {
        if (searchNames == null){
            throw new IllegalArgumentException("The term identifiers cannot be null.");
        }

        Collection<T> results = new ArrayList<T>(searchNames.size());
        for (String id : searchNames){
            results.addAll(fetchByName(id));

        }
        return results;
    }

    /**
     * <p>instantiateCvTerm.</p>
     *
     * @param termName a {@link java.lang.String} object.
     * @param identity a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param olsOntologyName a {@link java.lang.String} object.
     * @return a T object.
     */
    protected abstract T instantiateCvTerm(String termName, Xref identity, String olsOntologyName);
}

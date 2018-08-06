package psidev.psi.mi.jami.bridges.ols;

import psidev.psi.mi.jami.model.OntologyTerm;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultXref;
import uk.ac.ebi.pride.utilities.ols.web.service.client.OLSClient;
import uk.ac.ebi.pride.utilities.ols.web.service.model.Identifier;
import uk.ac.ebi.pride.utilities.ols.web.service.model.Term;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * A lazy ontology term, which only checks for parents of children when required.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 21/08/13

 */
public class LazyOntologyTerm
        extends LazyCvTerm implements OntologyTerm{

    protected final Logger log = Logger.getLogger(LazyOntologyTerm.class.getName());

    private Collection<OntologyTerm> parents;
    private Collection<OntologyTerm> children;
    private String definition;

    /**
     * <p>Constructor for LazyOntologyTerm.</p>
     *
     * @param olsClient a {@link uk.ac.ebi.pride.utilities.ols.web.service.client.OLSClient} object.
     * @param fullName a {@link java.lang.String} object.
     * @param identityRef a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param ontologyName a {@link java.lang.String} object.
     */
    public LazyOntologyTerm(OLSClient olsClient, String fullName, Xref identityRef, String ontologyName) {
        super(olsClient, fullName, identityRef, ontologyName);
    }

    /**
     * <p>Getter for the field <code>definition</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDefinition(){
        if (!hasLoadedMetadata()){
            initialiseMetaData( getOriginalXref() );
        }
        return this.definition;
    }

    /** {@inheritDoc} */
    public void setDefinition(String def) {
        this.definition = def;
    }

    /**
     * <p>Getter for the field <code>parents</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<OntologyTerm> getParents() {
        if(parents == null){
            initialiseOlsParents( getOriginalXref() );
        }
        return this.parents;
    }

    /**
     * <p>Getter for the field <code>children</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<OntologyTerm> getChildren() {
        if (children == null){
            initialiseOlsChildren( getOriginalXref() );
        }
        return this.children;
    }


    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getMIIdentifier() != null ? getMIIdentifier() : (getMODIdentifier() != null ? getMODIdentifier() : (getPARIdentifier() != null ? getPARIdentifier() : "-"))) + " ("+getFullName()+")";
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefinition(String description) {
        if (this.definition == null){
            this.definition = description;
        }
    }

    // == QUERY METHODS =======================================================================

    private void initialiseOlsChildren(Xref xref){
        this.children = new ArrayList<OntologyTerm>();
        HashMap<String,String> childrenIDs = new HashMap<>();
        Identifier identifier = new Identifier(xref.getId(), Identifier.IdentifierType.OBO);
        for(Term term : getOlsClient().getTermChildren(identifier, getOntologyName(), 1)){
            childrenIDs.put(term.getTermOBOId().getIdentifier(), term.getLabel());
        }

        for(Map.Entry<String,String> entry: childrenIDs.entrySet()){
            this.children.add( new LazyOntologyTerm(getOlsClient(),
                    entry.getValue(),
                    new DefaultXref(getOriginalXref().getDatabase() , entry.getKey(), getOriginalXref().getQualifier()),
                    getOntologyName()));
        }
    }

    private void initialiseOlsParents(Xref xref){
        this.parents = new ArrayList<OntologyTerm>();
        HashMap<String,String> parentIDs = new HashMap<>();
        Identifier identifier = new Identifier(xref.getId(), Identifier.IdentifierType.OBO);
        for(Term term : getOlsClient().getTermParents(identifier, getOntologyName(), 1)){
            parentIDs.put(term.getTermOBOId().getIdentifier(), term.getLabel());
        }

        for(Map.Entry<String,String> entry: parentIDs.entrySet()){
            this.parents.add( new LazyOntologyTerm(getOlsClient(),
                    entry.getValue(),
                    new DefaultXref(getOriginalXref().getDatabase() , entry.getKey(), getOriginalXref().getQualifier()),
                    getOntologyName()));
        }
    }
}

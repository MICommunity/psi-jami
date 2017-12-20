package psidev.psi.mi.jami.bridges.ontologymanager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.bridges.ols.CachedOlsOntologyTermFetcher;
import psidev.psi.mi.jami.bridges.ontologymanager.MIOntologyAccess;
import psidev.psi.mi.jami.bridges.ontologymanager.MIOntologyTermI;
import psidev.psi.mi.jami.model.OntologyTerm;
import psidev.psi.tools.ontology_manager.impl.local.OntologyLoaderException;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * abstract class for OntologyAccess
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>01/11/11</pre>
 */
public abstract class AbstractMIOntologyAccess implements MIOntologyAccess {

    /** Constant <code>log</code> */
    public static final Log log = LogFactory.getLog(AbstractMIOntologyAccess.class);

    private OntologyTermFetcher termFetcher;

    private String databaseName;
    private String databaseIdentifier;
    private Pattern databaseRegexp;
    private String parentIdentifier;
    private String ontologyID;

    /**
     * <p>Constructor for AbstractMIOntologyAccess.</p>
     *
     * @throws psidev.psi.tools.ontology_manager.impl.local.OntologyLoaderException if any.
     */
    public AbstractMIOntologyAccess() throws OntologyLoaderException {
        super();
        try {
            this.termFetcher = new CachedOlsOntologyTermFetcher();
        } catch (BridgeFailedException e) {
            throw new OntologyLoaderException("Impossible to create a new OLS fetcher", e);
        }
        this.databaseName = null;
        this.databaseIdentifier=null;
        this.databaseRegexp=null;
        this.parentIdentifier=null;
    }

    /**
     * <p>Constructor for AbstractMIOntologyAccess.</p>
     *
     * @param termBuilder a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @throws psidev.psi.tools.ontology_manager.impl.local.OntologyLoaderException if any.
     */
    public AbstractMIOntologyAccess(OntologyTermFetcher termBuilder) throws OntologyLoaderException {
        super();
        if (termBuilder == null){
            throw new IllegalArgumentException("The OntologyTermWrapper fetcher must be non null");
        }
        this.termFetcher = termBuilder;
        this.databaseName = null;
        this.databaseIdentifier=null;
        this.databaseRegexp=null;
        this.parentIdentifier=null;
    }


    /**
     * <p>Constructor for AbstractMIOntologyAccess.</p>
     *
     * @param dbName a {@link java.lang.String} object.
     * @param dbIdentifier a {@link java.lang.String} object.
     * @param dbRegexp a {@link java.util.regex.Pattern} object.
     * @param parent a {@link java.lang.String} object.
     * @throws psidev.psi.tools.ontology_manager.impl.local.OntologyLoaderException if any.
     */
    public AbstractMIOntologyAccess(String dbName, String dbIdentifier, Pattern dbRegexp, String parent) throws OntologyLoaderException {
        super();
        try {
            this.termFetcher = new CachedOlsOntologyTermFetcher();
        } catch (BridgeFailedException e) {
            throw new OntologyLoaderException("Impossible to create a new OLS fetcher", e);
        }
        this.databaseName = dbName;
        this.databaseIdentifier=dbIdentifier;
        this.databaseRegexp=dbRegexp;
        this.parentIdentifier=parent;
    }

    /**
     * <p>Constructor for AbstractMIOntologyAccess.</p>
     *
     * @param termBuilder a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param dbName a {@link java.lang.String} object.
     * @param dbIdentifier a {@link java.lang.String} object.
     * @param dbRegexp a {@link java.util.regex.Pattern} object.
     * @param parent a {@link java.lang.String} object.
     * @throws psidev.psi.tools.ontology_manager.impl.local.OntologyLoaderException if any.
     */
    public AbstractMIOntologyAccess(OntologyTermFetcher termBuilder, String dbName, String dbIdentifier, Pattern dbRegexp, String parent) throws OntologyLoaderException {
        super();
        if (termBuilder == null){
            throw new IllegalArgumentException("The OntologyTermWrapper fetcher must be non null");
        }
        this.termFetcher = termBuilder;
        this.databaseName = dbName;
        this.databaseIdentifier=dbIdentifier;
        this.databaseRegexp=dbRegexp;
        this.parentIdentifier=parent;
    }

    /**
     * <p>createNewOntologyTerm.</p>
     *
     * @param identifier a {@link java.lang.String} object.
     * @param name a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.bridges.ontologymanager.MIOntologyTermI} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    protected MIOntologyTermI createNewOntologyTerm(String identifier, String name) throws BridgeFailedException {
        if (identifier != null){
            OntologyTerm fetched = this.termFetcher.fetchByIdentifier(identifier, this.databaseName != null ? this.databaseName : name);
            return fetched != null ? new OntologyTermWrapper(fetched) : null;
        }
        return null;
    }

    /**
     * <p>Getter for the field <code>ontologyID</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getOntologyID() {
        return this.ontologyID;
    }

    /**
     * <p>Getter for the field <code>databaseIdentifier</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDatabaseIdentifier() {
        return this.databaseIdentifier;
    }

    /**
     * <p>getParentFromOtherOntology.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getParentFromOtherOntology() {
        return this.parentIdentifier;
    }

    /**
     * <p>getRootTerms.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<MIOntologyTermI> getRootTerms() {
        Collection<MIOntologyTermI> roots = new ArrayList<MIOntologyTermI>();

        try {
            for (OntologyTerm acc : getOntologyTermFetcher().fetchRootTerms(databaseName)){
                MIOntologyTermI term = new OntologyTermWrapper(acc);

                roots.add(term);
            }
        } catch (BridgeFailedException e) {
            if ( log.isWarnEnabled() ) {
                log.warn( "Error while loading root term from OLS for ontology: " + ontologyID, e );
            }
        }
        return roots;
    }

    /**
     * <p>Getter for the field <code>databaseRegexp</code>.</p>
     *
     * @return a {@link java.util.regex.Pattern} object.
     */
    public Pattern getDatabaseRegexp() {
        return this.databaseRegexp;
    }

    /**
     * <p>getOntologyTermFetcher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public OntologyTermFetcher getOntologyTermFetcher() {
        return termFetcher;
    }

    /** {@inheritDoc} */
    public abstract void loadOntology(String ontologyID, String name, String version, String format, URI uri) throws OntologyLoaderException;

    /** {@inheritDoc} */
    public void setOntologyDirectory(File directory) {
         // nothing to do
    }

    /**
     * {@inheritDoc}
     *
     * Method that is used by the validator to determine a Set of Ontology terms that are valid terms
     * for a particular rule. E.g. according to the flags, this can be the term corresponding to the
     * provided accession or its children or both.
     */
    public Set<MIOntologyTermI> getValidTerms( String accession, boolean allowChildren, boolean useTerm ) {
        Set<MIOntologyTermI> validTerms = new HashSet<MIOntologyTermI>();
        MIOntologyTermI term = getTermForAccession( accession );
        if ( term != null ) {
            if ( useTerm ) {
                validTerms.add( term );
            }
            if ( allowChildren ) {
                collectChildren(term.getDelegate(), validTerms);
            }
        }
        return validTerms;
    }

    private void collectChildren(OntologyTerm term, Set<MIOntologyTermI> children) {
        for (OntologyTerm child : term.getChildren()){
            if (children.add(new OntologyTermWrapper(child))){
                collectChildren(child, children);
            }
        }
    }

    /** {@inheritDoc} */
    public MIOntologyTermI getTermForAccession(String accession) {
        // if we don't even have a valid input, there is no point in trying to query OLS
        if (accession == null) { return null; }

        try {
            return createNewOntologyTerm(accession, null);
        } catch (BridgeFailedException e) {
            if ( log.isWarnEnabled() ) {
                log.warn( "Error while loading term from OLS : " + accession, e );
            }
        }
        return null;
    }

    /**
     * <p>isObsolete.</p>
     *
     * @param term a {@link psidev.psi.mi.jami.bridges.ontologymanager.MIOntologyTermI} object.
     * @return a boolean.
     */
    public boolean isObsolete(MIOntologyTermI term) {
        return term.getObsoleteMessage() != null;
    }

    /**
     * <p>getDirectParents.</p>
     *
     * @param term a {@link psidev.psi.mi.jami.bridges.ontologymanager.MIOntologyTermI} object.
     * @return a {@link java.util.Set} object.
     */
    public Set<MIOntologyTermI> getDirectParents(MIOntologyTermI term) {
        Collection<OntologyTerm> parents = term.getDelegate().getParents();
        Set<MIOntologyTermI> directParents = new HashSet<MIOntologyTermI>(parents.size());
        for (OntologyTerm parent : parents){
            directParents.add(new OntologyTermWrapper(parent));
        }
        return directParents;
    }

    /**
     * <p>getDirectChildren.</p>
     *
     * @param term a {@link psidev.psi.mi.jami.bridges.ontologymanager.MIOntologyTermI} object.
     * @return a {@link java.util.Set} object.
     */
    public Set<MIOntologyTermI> getDirectChildren(MIOntologyTermI term) {
        Collection<OntologyTerm> children = term.getDelegate().getChildren();
        Set<MIOntologyTermI> directChildren = new HashSet<MIOntologyTermI>(children.size());
        for (OntologyTerm child : children){
            directChildren.add(new OntologyTermWrapper(child));
        }
        return directChildren;
    }

    /**
     * <p>getAllParents.</p>
     *
     * @param term a {@link psidev.psi.mi.jami.bridges.ontologymanager.MIOntologyTermI} object.
     * @return a {@link java.util.Set} object.
     */
    public Set<MIOntologyTermI> getAllParents(MIOntologyTermI term) {
        Set<MIOntologyTermI> allParents = getDirectParents(term);
        Set<MIOntologyTermI> allParentsClone = new HashSet<MIOntologyTermI>(allParents);
        for (MIOntologyTermI termParent : allParentsClone){
            allParents.addAll(getAllParents(termParent));
        }
        return allParents;
    }

    /**
     * <p>getAllChildren.</p>
     *
     * @param term a {@link psidev.psi.mi.jami.bridges.ontologymanager.MIOntologyTermI} object.
     * @return a {@link java.util.Set} object.
     */
    public Set<MIOntologyTermI> getAllChildren(MIOntologyTermI term) {
        Set<MIOntologyTermI> allChildren = getDirectChildren(term);
        Set<MIOntologyTermI> allChildrenClone = new HashSet<MIOntologyTermI>(allChildren);
        for (MIOntologyTermI termChild : allChildrenClone){
            allChildren.addAll(getAllChildren(termChild));
        }
        return allChildren;
    }

    /**
     * <p>isOntologyUpToDate.</p>
     *
     * @return a boolean.
     * @throws psidev.psi.tools.ontology_manager.impl.local.OntologyLoaderException if any.
     */
    public abstract boolean isOntologyUpToDate() throws OntologyLoaderException;

    /**
     * <p>isUseTermSynonyms.</p>
     *
     * @return a boolean.
     */
    public boolean isUseTermSynonyms() {
        return true;
    }

    /** {@inheritDoc} */
    public void setUseTermSynonyms(boolean useTermSynonyms) {
         throw new UnsupportedOperationException("The MI ontology fetcher always load term synonyms");
    }

    /**
     * <p>Getter for the field <code>databaseName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * <p>Setter for the field <code>ontologyID</code>.</p>
     *
     * @param ontologyID a {@link java.lang.String} object.
     */
    protected void setOntologyID(String ontologyID) {
        this.ontologyID = ontologyID;
    }

    /**
     * <p>setOntologyTermFetcher.</p>
     *
     * @param termFetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    protected void setOntologyTermFetcher(OntologyTermFetcher termFetcher) {
        this.termFetcher = termFetcher;
    }
}

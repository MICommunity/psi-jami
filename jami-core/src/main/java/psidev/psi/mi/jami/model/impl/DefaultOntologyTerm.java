package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.OntologyTerm;
import psidev.psi.mi.jami.model.Xref;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for ontology term
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with UnambiguousCvTermComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/02/13</pre>
 */
public class DefaultOntologyTerm extends DefaultCvTerm implements OntologyTerm{
    private String definition;

    private Collection<OntologyTerm> parents;
    private Collection<OntologyTerm> children;

    /**
     * <p>Constructor for DefaultOntologyTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultOntologyTerm(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for DefaultOntologyTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param miIdentifier a {@link java.lang.String} object.
     */
    public DefaultOntologyTerm(String shortName, String miIdentifier) {
        super(shortName, miIdentifier);
    }

    /**
     * <p>Constructor for DefaultOntologyTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param miIdentifier a {@link java.lang.String} object.
     */
    public DefaultOntologyTerm(String shortName, String fullName, String miIdentifier) {
        super(shortName, fullName, miIdentifier);
    }

    /**
     * <p>Constructor for DefaultOntologyTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param miIdentifier a {@link java.lang.String} object.
     * @param def a {@link java.lang.String} object.
     */
    public DefaultOntologyTerm(String shortName, String fullName, String miIdentifier, String def){
        this(shortName, fullName, miIdentifier);
        this.definition = def;
    }

    /**
     * <p>Constructor for DefaultOntologyTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultOntologyTerm(String shortName, Xref ontologyId) {
        super(shortName, ontologyId);
    }

    /**
     * <p>Constructor for DefaultOntologyTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultOntologyTerm(String shortName, String fullName, Xref ontologyId) {
        super(shortName, fullName, ontologyId);
    }

    /**
     * <p>Constructor for DefaultOntologyTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param def a {@link java.lang.String} object.
     */
    public DefaultOntologyTerm(String shortName, String fullName, Xref ontologyId, String def){
        this(shortName, fullName, ontologyId);
        this.definition = def;
    }

    /**
     * <p>initialiseParents</p>
     */
    protected void initialiseParents(){
        this.parents = new ArrayList<OntologyTerm>();
    }

    /**
     * <p>initialiseParentsWith</p>
     *
     * @param parents a {@link java.util.Collection} object.
     */
    protected void initialiseParentsWith(Collection<OntologyTerm> parents){
        if (parents == null){
            this.parents = Collections.EMPTY_LIST;
        }
        else {
            this.parents = parents;
        }
    }

    /**
     * <p>initialiseChildren</p>
     */
    protected void initialiseChildren(){
        this.children = new ArrayList<OntologyTerm>();
    }

    /**
     * <p>initialiseChildrenWith</p>
     *
     * @param children a {@link java.util.Collection} object.
     */
    protected void initialiseChildrenWith(Collection<OntologyTerm> children){
        if (children == null){
            this.children = Collections.EMPTY_LIST;
        }
        else {
            this.children = children;
        }
    }

    /**
     * <p>Getter for the field <code>definition</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDefinition() {
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
        if (parents == null){
            initialiseParents();
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
            initialiseChildren();
        }
        return this.children;
    }
}

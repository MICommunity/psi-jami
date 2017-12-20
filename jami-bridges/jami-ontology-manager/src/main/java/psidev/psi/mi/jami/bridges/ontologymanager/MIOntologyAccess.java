package psidev.psi.mi.jami.bridges.ontologymanager;

import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.tools.ontology_manager.interfaces.OntologyAccessTemplate;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Extension of OntologyAcessTemplate for jami
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/11/11</pre>
 */
public interface MIOntologyAccess extends OntologyAccessTemplate<MIOntologyTermI> {

    /**
     * <p>getOntologyID.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getOntologyID();
    /**
     * <p>getDatabaseIdentifier.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDatabaseIdentifier();
    /**
     * <p>getParentFromOtherOntology.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getParentFromOtherOntology();
    /**
     * <p>getRootTerms.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<MIOntologyTermI> getRootTerms();
    /**
     * <p>getDatabaseRegexp.</p>
     *
     * @return a {@link java.util.regex.Pattern} object.
     */
    public Pattern getDatabaseRegexp();
    /**
     * <p>getOntologyTermFetcher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public OntologyTermFetcher getOntologyTermFetcher();
    /**
     * <p>getDatabaseName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDatabaseName();
}

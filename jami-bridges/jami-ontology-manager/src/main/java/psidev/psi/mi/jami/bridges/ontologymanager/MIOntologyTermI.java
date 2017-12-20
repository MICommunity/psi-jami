package psidev.psi.mi.jami.bridges.ontologymanager;

import psidev.psi.mi.jami.model.OntologyTerm;
import psidev.psi.tools.ontology_manager.interfaces.OntologyTermI;

import java.util.Set;

/**
 * Extension of OntologyTermI for jami
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>01/11/11</pre>
 */
public interface MIOntologyTermI extends OntologyTermI {

    /**
     * <p>getObsoleteMessage.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getObsoleteMessage();
    /**
     * <p>getRemappedTerm.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRemappedTerm();
    /**
     * <p>getPossibleTermsToRemapTo.</p>
     *
     * @return a {@link java.util.Set} object.
     */
    public Set<String> getPossibleTermsToRemapTo();
    /**
     * <p>getDelegate.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.OntologyTerm} object.
     */
    public OntologyTerm getDelegate();
}

package psidev.psi.mi.jami.tab.listener;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.listener.MIFileParserListener;
import psidev.psi.mi.jami.tab.extension.*;

import java.util.Collection;

/**
 * A listener listening to events when parsing a mitab file
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/06/13</pre>
 */
public interface MitabParserListener extends MIFileParserListener{

    /**
     * <p>onTextFoundInIdentifier.</p>
     *
     * @param xref a {@link psidev.psi.mi.jami.tab.extension.MitabXref} object.
     */
    public void onTextFoundInIdentifier(MitabXref xref);

    /**
     * <p>onTextFoundInConfidence.</p>
     *
     * @param conf a {@link psidev.psi.mi.jami.tab.extension.MitabConfidence} object.
     */
    public void onTextFoundInConfidence(MitabConfidence conf);

    /**
     * <p>onMissingExpansionId.</p>
     *
     * @param expansion a {@link psidev.psi.mi.jami.tab.extension.MitabCvTerm} object.
     */
    public void onMissingExpansionId(MitabCvTerm expansion);

    /**
     * <p>onSeveralUniqueIdentifiers.</p>
     *
     * @param ids a {@link java.util.Collection} object.
     */
    public void onSeveralUniqueIdentifiers(Collection<MitabXref> ids);

    /**
     * <p>onEmptyUniqueIdentifiers.</p>
     *
     * @param line a int.
     * @param column a int.
     * @param mitabColumn a int.
     */
    public void onEmptyUniqueIdentifiers(int line, int column, int mitabColumn);

    /**
     * <p>onMissingInteractorIdentifierColumns.</p>
     *
     * @param line a int.
     * @param column a int.
     * @param mitabColumn a int.
     */
    public void onMissingInteractorIdentifierColumns(int line, int column, int mitabColumn);

    /**
     * <p>onSeveralOrganismFound.</p>
     *
     * @param organisms a {@link java.util.Collection} object.
     */
    public void onSeveralOrganismFound(Collection<MitabOrganism> organisms);

    /**
     * <p>onSeveralStoichiometryFound.</p>
     *
     * @param stoichiometry a {@link java.util.Collection} object.
     */
    public void onSeveralStoichiometryFound(Collection<MitabStoichiometry> stoichiometry);

    /**
     * <p>onSeveralFirstAuthorFound.</p>
     *
     * @param authors a {@link java.util.Collection} object.
     */
    public void onSeveralFirstAuthorFound(Collection<MitabAuthor> authors);

    /**
     * <p>onSeveralSourceFound.</p>
     *
     * @param sources a {@link java.util.Collection} object.
     */
    public void onSeveralSourceFound(Collection<MitabSource> sources);

    /**
     * <p>onSeveralCreatedDateFound.</p>
     *
     * @param dates a {@link java.util.Collection} object.
     */
    public void onSeveralCreatedDateFound(Collection<MitabDate> dates);

    /**
     * <p>onSeveralUpdatedDateFound.</p>
     *
     * @param dates a {@link java.util.Collection} object.
     */
    public void onSeveralUpdatedDateFound(Collection<MitabDate> dates);

    /**
     * <p>onAliasWithoutDbSource.</p>
     *
     * @param alias a {@link psidev.psi.mi.jami.tab.extension.MitabAlias} object.
     */
    public void onAliasWithoutDbSource(MitabAlias alias);

    /**
     * Listen to an event where several CvTerms were found and only one was expected.
     * Can happen when reading a clustered interaction evidence for instance
     *
     * @param terms a {@link java.util.Collection} object.
     * @param context a {@link psidev.psi.mi.jami.datasource.FileSourceContext} object.
     * @param message a {@link java.lang.String} object.
     */
    public void onSeveralCvTermsFound(Collection<MitabCvTerm> terms, FileSourceContext context, String message);

    /**
     * Listen to an event where several host organisms were found in a single experiment and only one was expected.
     * Can happen when reading a clustered interaction evidence for instance
     *
     * @param organisms a {@link java.util.Collection} object.
     * @param context a {@link psidev.psi.mi.jami.datasource.FileSourceContext} object.
     */
    public void onSeveralHostOrganismFound(Collection<MitabOrganism> organisms, FileSourceContext context);
}

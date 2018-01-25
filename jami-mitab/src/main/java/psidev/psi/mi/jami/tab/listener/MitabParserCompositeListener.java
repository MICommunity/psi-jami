package psidev.psi.mi.jami.tab.listener;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.listener.impl.MIFileParserCompositeListener;
import psidev.psi.mi.jami.tab.extension.*;

import java.util.Collection;

/**
 * This class contains several MI file listeners
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/12/13</pre>
 */
public class MitabParserCompositeListener extends MIFileParserCompositeListener<MitabParserListener> implements MitabParserListener {

    /**
     * <p>Constructor for MitabParserCompositeListener.</p>
     */
    public MitabParserCompositeListener(){
        super();
    }


    /** {@inheritDoc} */
    public void onTextFoundInIdentifier(MitabXref xref) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onTextFoundInIdentifier(xref);
        }
    }

    /** {@inheritDoc} */
    public void onTextFoundInConfidence(MitabConfidence conf) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onTextFoundInConfidence(conf);
        }
    }

    /** {@inheritDoc} */
    public void onMissingExpansionId(MitabCvTerm expansion) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onMissingExpansionId(expansion);
        }
    }

    /** {@inheritDoc} */
    public void onSeveralUniqueIdentifiers(Collection<MitabXref> ids) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onSeveralUniqueIdentifiers(ids);
        }
    }

    /** {@inheritDoc} */
    public void onEmptyUniqueIdentifiers(int line, int column, int mitabColumn) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onEmptyUniqueIdentifiers(line, column, mitabColumn);
        }
    }

    /** {@inheritDoc} */
    public void onMissingInteractorIdentifierColumns(int line, int column, int mitabColumn) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onMissingInteractorIdentifierColumns(line, column, mitabColumn);
        }
    }

    /** {@inheritDoc} */
    public void onSeveralOrganismFound(Collection<MitabOrganism> organisms) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onSeveralOrganismFound(organisms);
        }
    }

    /** {@inheritDoc} */
    public void onSeveralStoichiometryFound(Collection<MitabStoichiometry> stoichiometry) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onSeveralStoichiometryFound(stoichiometry);
        }
    }

    /** {@inheritDoc} */
    public void onSeveralFirstAuthorFound(Collection<MitabAuthor> authors) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onSeveralFirstAuthorFound(authors);
        }
    }

    /** {@inheritDoc} */
    public void onSeveralSourceFound(Collection<MitabSource> sources) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onSeveralSourceFound(sources);
        }
    }

    /** {@inheritDoc} */
    public void onSeveralCreatedDateFound(Collection<MitabDate> dates) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onSeveralCreatedDateFound(dates);
        }
    }

    /** {@inheritDoc} */
    public void onSeveralUpdatedDateFound(Collection<MitabDate> dates) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onSeveralUpdatedDateFound(dates);
        }
    }

    /** {@inheritDoc} */
    public void onAliasWithoutDbSource(MitabAlias alias) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onAliasWithoutDbSource(alias);
        }
    }

    /** {@inheritDoc} */
    public void onSeveralCvTermsFound(Collection<MitabCvTerm> terms, FileSourceContext context, String message) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onSeveralCvTermsFound(terms, context, message);
        }
    }

    /** {@inheritDoc} */
    public void onSeveralHostOrganismFound(Collection<MitabOrganism> organisms, FileSourceContext context) {
        for (MitabParserListener delegate : getDelegates()){
            delegate.onSeveralHostOrganismFound(organisms, context);
        }
    }
}

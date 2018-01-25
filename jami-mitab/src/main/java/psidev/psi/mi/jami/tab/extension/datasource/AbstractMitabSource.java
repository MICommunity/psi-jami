package psidev.psi.mi.jami.tab.extension.datasource;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.MIFileDataSourceOptions;
import psidev.psi.mi.jami.listener.MIFileParserListener;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.tab.extension.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Abstract class for an InteractionSource coming from a MITAB file.
 *
 * This datasource provides interaction iterator and collection
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public abstract class AbstractMitabSource<T extends Interaction, P extends Participant, F extends Feature> implements MitabSource<T>{
    private AbstractMitabStreamSource<T,P,F> delegatedSource;
    private Collection<T> loadedInteractions;

    /**
     * <p>Constructor for AbstractMitabSource.</p>
     *
     * @param delegatedSource a {@link psidev.psi.mi.jami.tab.extension.datasource.AbstractMitabStreamSource} object.
     */
    public AbstractMitabSource(AbstractMitabStreamSource<T,P,F> delegatedSource){
        if (delegatedSource == null){
            throw new IllegalArgumentException("The delegated MITAB stream source is required.");
        }
        this.delegatedSource = delegatedSource;
    }

    /**
     * <p>getInteractions.</p>
     *
     * @return a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public Collection<T> getInteractions() throws MIIOException {
        if (this.loadedInteractions == null){
            initialiseInteractionCollection();
        }
        return this.loadedInteractions;
    }

    /**
     * <p>getNumberOfInteractions.</p>
     *
     * @return a long.
     */
    public long getNumberOfInteractions() {
        if (this.loadedInteractions == null){
            initialiseInteractionCollection();
        }
        return this.loadedInteractions.size();
    }

    /**
     * <p>getInteractionsIterator.</p>
     *
     * @return a {@link java.util.Iterator} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public Iterator<T> getInteractionsIterator() throws MIIOException {
        if (this.loadedInteractions == null){
            initialiseInteractionCollection();
        }
        return this.loadedInteractions.iterator();
    }

    /**
     * <p>getFileParserListener.</p>
     *
     * @return a {@link psidev.psi.mi.jami.listener.MIFileParserListener} object.
     */
    public MIFileParserListener getFileParserListener() {
        return this.delegatedSource.getFileParserListener();
    }

    /** {@inheritDoc} */
    public void setFileParserListener(MIFileParserListener listener) {
        this.delegatedSource.setFileParserListener(listener);
    }

    /**
     * <p>validateSyntax.</p>
     *
     * @return a boolean.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public boolean validateSyntax() throws MIIOException {
        return this.delegatedSource.validateSyntax();
    }

    /** {@inheritDoc} */
    public boolean validateSyntax(MIFileParserListener listener) throws MIIOException {
        return this.delegatedSource.validateSyntax(listener);
    }

    /** {@inheritDoc} */
    public void initialiseContext(Map<String, Object> options) {
        this.delegatedSource.initialiseContext(options);
    }

    /**
     * <p>close.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void close() throws MIIOException {
        this.delegatedSource.close();
        this.loadedInteractions = null;
    }

    /**
     * <p>reset.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void reset() throws MIIOException {
        this.delegatedSource.reset();
        this.loadedInteractions = null;
    }

    /** {@inheritDoc} */
    public void onTextFoundInIdentifier(MitabXref xref) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onTextFoundInIdentifier(xref);
    }

    /** {@inheritDoc} */
    public void onTextFoundInConfidence(MitabConfidence conf) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onTextFoundInConfidence(conf);
    }

    /** {@inheritDoc} */
    public void onMissingExpansionId(MitabCvTerm expansion) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onMissingExpansionId(expansion);
    }

    /** {@inheritDoc} */
    public void onSeveralUniqueIdentifiers(Collection<MitabXref> ids) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onSeveralUniqueIdentifiers(ids);
    }

    /** {@inheritDoc} */
    public void onEmptyUniqueIdentifiers(int line, int column, int mitabColumn) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onEmptyUniqueIdentifiers(line, column, mitabColumn);
    }

    /** {@inheritDoc} */
    public void onMissingInteractorIdentifierColumns(int line, int column, int mitabColumn) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onMissingInteractorIdentifierColumns(line, column, mitabColumn);
    }

    /** {@inheritDoc} */
    public void onSeveralOrganismFound(Collection<MitabOrganism> organisms) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onSeveralOrganismFound(organisms);
    }

    /** {@inheritDoc} */
    public void onSeveralStoichiometryFound(Collection<MitabStoichiometry> stoichiometry) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onSeveralStoichiometryFound(stoichiometry);
    }

    /** {@inheritDoc} */
    public void onSeveralFirstAuthorFound(Collection<MitabAuthor> authors) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onSeveralFirstAuthorFound(authors);
    }

    /** {@inheritDoc} */
    public void onSeveralSourceFound(Collection<psidev.psi.mi.jami.tab.extension.MitabSource> sources) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onSeveralSourceFound(sources);
    }

    /** {@inheritDoc} */
    public void onSeveralCreatedDateFound(Collection<MitabDate> dates) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onSeveralCreatedDateFound(dates);
    }

    /** {@inheritDoc} */
    public void onSeveralUpdatedDateFound(Collection<MitabDate> dates) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onSeveralUpdatedDateFound(dates);
    }

    /** {@inheritDoc} */
    public void onAliasWithoutDbSource(MitabAlias alias) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onAliasWithoutDbSource(alias);
    }

    /** {@inheritDoc} */
    public void onSeveralCvTermsFound(Collection<MitabCvTerm> terms, FileSourceContext context, String message) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onSeveralCvTermsFound(terms, context, message);
    }

    /** {@inheritDoc} */
    public void onSeveralHostOrganismFound(Collection<MitabOrganism> organisms, FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onSeveralHostOrganismFound(organisms, context);
    }

    /** {@inheritDoc} */
    public void onInvalidSyntax(FileSourceContext context, Exception e) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onInvalidSyntax(context, e);
    }

    /** {@inheritDoc} */
    public void onSyntaxWarning(FileSourceContext context, String message) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onSyntaxWarning(context, message);
    }

    /** {@inheritDoc} */
    public void onMissingCvTermName(CvTerm term, FileSourceContext context, String message) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onMissingCvTermName(term, context, message);
    }

    /** {@inheritDoc} */
    public void onMissingInteractorName(Interactor interactor, FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onMissingInteractorName(interactor, context);
    }

    /** {@inheritDoc} */
    public void onParticipantWithoutInteractor(Participant participant, FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onParticipantWithoutInteractor(participant, context);
    }

    /** {@inheritDoc} */
    public void onInteractionWithoutParticipants(Interaction interaction, FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onInteractionWithoutParticipants(interaction, context);
    }

    /** {@inheritDoc} */
    public void onInvalidOrganismTaxid(String taxid, FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onInvalidOrganismTaxid(taxid, context);
    }

    /** {@inheritDoc} */
    public void onMissingParameterValue(FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onMissingParameterValue(context);
    }

    /** {@inheritDoc} */
    public void onMissingParameterType(FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onMissingParameterType(context);
    }

    /** {@inheritDoc} */
    public void onMissingConfidenceValue(FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onMissingConfidenceValue(context);
    }

    /** {@inheritDoc} */
    public void onMissingConfidenceType(FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onMissingConfidenceType(context);
    }

    /** {@inheritDoc} */
    public void onMissingChecksumValue(FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onMissingChecksumValue(context);
    }

    /** {@inheritDoc} */
    public void onMissingChecksumMethod(FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onMissingChecksumMethod(context);
    }

    /** {@inheritDoc} */
    public void onInvalidPosition(String message, FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onInvalidPosition(message, context);
    }

    /** {@inheritDoc} */
    public void onInvalidRange(String message, FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onInvalidRange(message, context);
    }

    /** {@inheritDoc} */
    public void onInvalidStoichiometry(String message, FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onInvalidStoichiometry(message, context);
    }

    /** {@inheritDoc} */
    public void onXrefWithoutDatabase(FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onXrefWithoutDatabase(context);
    }

    /** {@inheritDoc} */
    public void onXrefWithoutId(FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onXrefWithoutId(context);
    }

    /** {@inheritDoc} */
    public void onAnnotationWithoutTopic(FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onAnnotationWithoutTopic(context);
    }

    /** {@inheritDoc} */
    public void onAliasWithoutName(FileSourceContext context) {
        if (this.delegatedSource == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegatedSource.onAliasWithoutName(context);
    }

    private void initialiseInteractionCollection(){
        Iterator<T> interactionIterator = this.delegatedSource.getInteractionsIterator();
        this.loadedInteractions = new ArrayList<T>();
        while(interactionIterator.hasNext()){
            this.loadedInteractions.add(interactionIterator.next());
        }
    }
}

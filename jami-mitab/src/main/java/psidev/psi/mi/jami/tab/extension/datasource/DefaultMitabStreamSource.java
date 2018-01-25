package psidev.psi.mi.jami.tab.extension.datasource;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.MIFileDataSourceOptions;
import psidev.psi.mi.jami.listener.MIFileParserListener;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.tab.extension.*;
import psidev.psi.mi.jami.tab.extension.MitabSource;
import psidev.psi.mi.jami.tab.extension.factory.MitabDataSourceFactory;
import psidev.psi.mi.jami.tab.extension.factory.options.MitabDataSourceOptions;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Generic class for Mitab streaming datasource.
 *
 * This datasource streams the interactions from a MITAB file
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/06/13</pre>
 */
public class DefaultMitabStreamSource implements MitabStreamSource{

    private MitabStreamSource delegate;

    /**
     * Empty constructor for the factory
     */
    public DefaultMitabStreamSource(){
    }

    /**
     * <p>getInteractionsIterator.</p>
     *
     * @return a {@link java.util.Iterator} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public Iterator<Interaction> getInteractionsIterator() throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        return this.delegate.getInteractionsIterator();
    }

    /**
     * <p>getFileParserListener.</p>
     *
     * @return a {@link psidev.psi.mi.jami.listener.MIFileParserListener} object.
     */
    public MIFileParserListener getFileParserListener() {
        return this.delegate != null ? this.delegate.getFileParserListener() : null;
    }

    /** {@inheritDoc} */
    public void setFileParserListener(MIFileParserListener listener) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.setFileParserListener(listener);
    }

    /**
     * <p>validateSyntax.</p>
     *
     * @return a boolean.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public boolean validateSyntax() throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        return this.delegate.validateSyntax();
    }

    /** {@inheritDoc} */
    public boolean validateSyntax(MIFileParserListener listener) throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        return this.delegate.validateSyntax(listener);
    }

    /** {@inheritDoc} */
    public void initialiseContext(Map<String, Object> options) {
        MitabDataSourceFactory factory = MitabDataSourceFactory.getInstance();
        InteractionCategory category = InteractionCategory.evidence;
        ComplexType type = ComplexType.n_ary;

        if (options == null || !options.containsKey(MitabDataSourceOptions.INPUT_OPTION_KEY)){
            throw new IllegalArgumentException("The options for the Mitab interaction datasource should contain at least "+
                    MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }

        if (options.containsKey(MitabDataSourceOptions.INTERACTION_CATEGORY_OPTION_KEY)){
            Object value = options.get(MitabDataSourceOptions.INTERACTION_CATEGORY_OPTION_KEY);
            if (value instanceof InteractionCategory){
                category = (InteractionCategory)value;
            }
        }
        if (options.containsKey(MitabDataSourceOptions.COMPLEX_TYPE_OPTION_KEY)){
            Object value = options.get(MitabDataSourceOptions.COMPLEX_TYPE_OPTION_KEY);
            if (value instanceof ComplexType){
                type = (ComplexType)value;
            }
        }

        initialiseDelegate(options, factory, category, type);
    }

    /**
     * <p>initialiseDelegate.</p>
     *
     * @param options a {@link java.util.Map} object.
     * @param factory a {@link psidev.psi.mi.jami.tab.extension.factory.MitabDataSourceFactory} object.
     * @param category a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @param type a {@link psidev.psi.mi.jami.model.ComplexType} object.
     */
    protected void initialiseDelegate(Map<String, Object> options, MitabDataSourceFactory factory, InteractionCategory category, ComplexType type) {
        this.delegate = factory.createMitabDataSource(category, type, true);
        this.delegate.initialiseContext(options);
    }

    /**
     * <p>close.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void close() throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.close();
    }

    /**
     * <p>reset.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void reset() throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.reset();
    }

    /** {@inheritDoc} */
    public void onTextFoundInIdentifier(MitabXref xref) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onTextFoundInIdentifier(xref);
    }

    /** {@inheritDoc} */
    public void onTextFoundInConfidence(MitabConfidence conf) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onTextFoundInConfidence(conf);
    }

    /** {@inheritDoc} */
    public void onMissingExpansionId(MitabCvTerm expansion) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onMissingExpansionId(expansion);
    }

    /** {@inheritDoc} */
    public void onSeveralUniqueIdentifiers(Collection<MitabXref> ids) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSeveralUniqueIdentifiers(ids);
    }

    /** {@inheritDoc} */
    public void onEmptyUniqueIdentifiers(int line, int column, int mitabColumn) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onEmptyUniqueIdentifiers(line, column, mitabColumn);
    }

    /** {@inheritDoc} */
    public void onMissingInteractorIdentifierColumns(int line, int column, int mitabColumn) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onMissingInteractorIdentifierColumns(line, column, mitabColumn);
    }

    /** {@inheritDoc} */
    public void onSeveralOrganismFound(Collection<MitabOrganism> organisms) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSeveralOrganismFound(organisms);
    }

    /** {@inheritDoc} */
    public void onSeveralStoichiometryFound(Collection<MitabStoichiometry> stoichiometry) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSeveralStoichiometryFound(stoichiometry);
    }

    /** {@inheritDoc} */
    public void onSeveralFirstAuthorFound(Collection<MitabAuthor> authors) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSeveralFirstAuthorFound(authors);
    }

    /** {@inheritDoc} */
    public void onSeveralSourceFound(Collection<MitabSource> sources) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSeveralSourceFound(sources);
    }

    /** {@inheritDoc} */
    public void onSeveralCreatedDateFound(Collection<MitabDate> dates) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSeveralCreatedDateFound(dates);
    }

    /** {@inheritDoc} */
    public void onSeveralUpdatedDateFound(Collection<MitabDate> dates) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSeveralUpdatedDateFound(dates);
    }

    /** {@inheritDoc} */
    public void onAliasWithoutDbSource(MitabAlias alias) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onAliasWithoutDbSource(alias);
    }

    /** {@inheritDoc} */
    public void onSeveralCvTermsFound(Collection<MitabCvTerm> terms, FileSourceContext context, String message) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSeveralCvTermsFound(terms, context, message);
    }

    /** {@inheritDoc} */
    public void onSeveralHostOrganismFound(Collection<MitabOrganism> organisms, FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSeveralHostOrganismFound(organisms, context);
    }

    /** {@inheritDoc} */
    public void onInvalidSyntax(FileSourceContext context, Exception e) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onInvalidSyntax(context, e);
    }

    /** {@inheritDoc} */
    public void onSyntaxWarning(FileSourceContext context, String message) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSyntaxWarning(context, message);
    }

    /** {@inheritDoc} */
    public void onMissingCvTermName(CvTerm term, FileSourceContext context, String message) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onMissingCvTermName(term, context, message);
    }

    /** {@inheritDoc} */
    public void onMissingInteractorName(Interactor interactor, FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onMissingInteractorName(interactor, context);
    }

    /** {@inheritDoc} */
    public void onParticipantWithoutInteractor(Participant participant, FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onParticipantWithoutInteractor(participant, context);
    }

    /** {@inheritDoc} */
    public void onInteractionWithoutParticipants(Interaction interaction, FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onInteractionWithoutParticipants(interaction, context);
    }

    /** {@inheritDoc} */
    public void onInvalidOrganismTaxid(String taxid, FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onInvalidOrganismTaxid(taxid, context);
    }

    /** {@inheritDoc} */
    public void onMissingParameterValue(FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onMissingParameterValue(context);
    }

    /** {@inheritDoc} */
    public void onMissingParameterType(FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onMissingParameterType(context);
    }

    /** {@inheritDoc} */
    public void onMissingConfidenceValue(FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onMissingConfidenceValue(context);
    }

    /** {@inheritDoc} */
    public void onMissingConfidenceType(FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onMissingConfidenceType(context);
    }

    /** {@inheritDoc} */
    public void onMissingChecksumValue(FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onMissingChecksumValue(context);
    }

    /** {@inheritDoc} */
    public void onMissingChecksumMethod(FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onMissingChecksumMethod(context);
    }

    /** {@inheritDoc} */
    public void onInvalidPosition(String message, FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onInvalidPosition(message, context);
    }

    /** {@inheritDoc} */
    public void onInvalidRange(String message, FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onInvalidRange(message, context);
    }

    /** {@inheritDoc} */
    public void onInvalidStoichiometry(String message, FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onInvalidStoichiometry(message, context);
    }

    /** {@inheritDoc} */
    public void onXrefWithoutDatabase(FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onXrefWithoutDatabase(context);
    }

    /** {@inheritDoc} */
    public void onXrefWithoutId(FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onXrefWithoutId(context);
    }

    /** {@inheritDoc} */
    public void onAnnotationWithoutTopic(FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onAnnotationWithoutTopic(context);
    }

    /** {@inheritDoc} */
    public void onAliasWithoutName(FileSourceContext context) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onAliasWithoutName(context);
    }

    /**
     * <p>Getter for the field <code>delegate</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.tab.extension.datasource.MitabStreamSource} object.
     */
    protected MitabStreamSource getDelegate() {
        return delegate;
    }

    /**
     * <p>Setter for the field <code>delegate</code>.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.tab.extension.datasource.MitabStreamSource} object.
     */
    protected void setDelegate(MitabStreamSource delegate) {
        this.delegate = delegate;
    }
}

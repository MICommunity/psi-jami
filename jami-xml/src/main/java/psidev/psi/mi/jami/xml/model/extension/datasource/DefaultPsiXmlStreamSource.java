package psidev.psi.mi.jami.xml.model.extension.datasource;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.MIFileDataSourceOptions;
import psidev.psi.mi.jami.listener.MIFileParserListener;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.model.extension.factory.PsiXmlDataSourceFactory;
import psidev.psi.mi.jami.xml.model.extension.factory.options.PsiXmlDataSourceOptions;
import psidev.psi.mi.jami.xml.model.reference.XmlIdReference;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Generic class for PSI-XML streaming datasource.
 *
 * This datasource streams the interactions from a PSI-XML file
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/06/13</pre>
 */
public class DefaultPsiXmlStreamSource implements PsiXmlStreamSource {

    private PsiXmlStreamSource delegate;

    /**
     * Empty constructor for the factory
     */
    public DefaultPsiXmlStreamSource(){
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
        PsiXmlDataSourceFactory factory = PsiXmlDataSourceFactory.getInstance();
        InteractionCategory category = null;
        ComplexType type = ComplexType.n_ary;

        if (options == null || !options.containsKey(PsiXmlDataSourceOptions.INPUT_OPTION_KEY)){
            throw new IllegalArgumentException("The options for the PSI-XML interaction datasource should contain at least "+
                    MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }

        if (options.containsKey(PsiXmlDataSourceOptions.INTERACTION_CATEGORY_OPTION_KEY)){
            Object value = options.get(PsiXmlDataSourceOptions.INTERACTION_CATEGORY_OPTION_KEY);
            if (value instanceof InteractionCategory){
                category = (InteractionCategory)value;
            }
        }
        if (options.containsKey(PsiXmlDataSourceOptions.COMPLEX_TYPE_OPTION_KEY)){
            Object value = options.get(PsiXmlDataSourceOptions.COMPLEX_TYPE_OPTION_KEY);
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
     * @param factory a {@link psidev.psi.mi.jami.xml.model.extension.factory.PsiXmlDataSourceFactory} object.
     * @param category a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @param type a {@link psidev.psi.mi.jami.model.ComplexType} object.
     */
    protected void initialiseDelegate(Map<String, Object> options, PsiXmlDataSourceFactory factory, InteractionCategory category, ComplexType type) {
        this.delegate = factory.createPsiXmlDataSource(category, type, true);
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
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.datasource.PsiXmlStreamSource} object.
     */
    protected PsiXmlStreamSource getDelegate() {
        return delegate;
    }

    /**
     * <p>Setter for the field <code>delegate</code>.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.xml.model.extension.datasource.PsiXmlStreamSource} object.
     */
    protected void setDelegate(PsiXmlStreamSource delegate) {
        this.delegate = delegate;
    }

    /** {@inheritDoc} */
    @Override
    public void warning(SAXParseException e) throws SAXException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.warning(e);
    }

    /** {@inheritDoc} */
    @Override
    public void error(SAXParseException e) throws SAXException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.error(e);
    }

    /** {@inheritDoc} */
    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.fatalError(e);
    }

    /** {@inheritDoc} */
    @Override
    public void onUnresolvedReference(XmlIdReference ref, String message) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onUnresolvedReference(ref, message);
    }

    /** {@inheritDoc} */
    @Override
    public void onSeveralHostOrganismFound(Collection<Organism> organisms, FileSourceLocator locator) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSeveralHostOrganismFound(organisms, locator);
    }

    /** {@inheritDoc} */
    @Override
    public void onSeveralExpressedInOrganismFound(Collection<Organism> organisms, FileSourceLocator locator) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSeveralExpressedInOrganismFound(organisms, locator);
    }

    /** {@inheritDoc} */
    @Override
    public void onSeveralExperimentalRolesFound(Collection<CvTerm> roles, FileSourceLocator locator) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSeveralExperimentalRolesFound(roles, locator);
    }

    /** {@inheritDoc} */
    @Override
    public void onSeveralExperimentsFound(Collection<Experiment> experiments, FileSourceLocator locator) {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource " +
                    "should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        this.delegate.onSeveralExperimentsFound(experiments, locator);
    }
}

package psidev.psi.mi.jami.xml.model.extension.datasource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.datasource.*;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.MIDataSourceOptions;
import psidev.psi.mi.jami.factory.options.MIFileDataSourceOptions;
import psidev.psi.mi.jami.listener.MIFileParserListener;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.MIFileDatasourceUtils;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.exception.PsiXmlParserException;
import psidev.psi.mi.jami.xml.io.parser.PsiXmlParser;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory;
import psidev.psi.mi.jami.xml.model.extension.factory.options.PsiXmlDataSourceOptions;
import psidev.psi.mi.jami.xml.model.reference.XmlIdReference;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class for psiXml data source
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
public abstract class AbstractPsiXmlStream<T extends Interaction> implements PsiXmlStreamSource<T> {

    private static final Logger logger = Logger.getLogger("AbstractPsiXmlStream");

    private boolean isInitialised = false;
    private PsiXmlParser<T> parser;
    private MIFileParserListener defaultParserListener;
    private PsiXmlParserListener parserListener;
    private URL originalURL;
    private File originalFile;
    private InputStream originalStream;
    private Reader originalReader;
    private XmlInteractorFactory interactorFactory;

    private Boolean isValid = null;
    private PsiXmlIdCache elementCache;

    /** Constant <code>VALIDATION_FEATURE="http://xml.org/sax/features/validation"</code> */
    public static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    /** Constant <code>SCHEMA_FEATURE="http://apache.org/xml/features/validati"{trunked}</code> */
    public static final String SCHEMA_FEATURE = "http://apache.org/xml/features/validation/schema";

    /**
     * <p>Constructor for AbstractPsiXmlStream.</p>
     */
    public AbstractPsiXmlStream(){
    }

    /**
     * <p>Constructor for AbstractPsiXmlStream.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public AbstractPsiXmlStream(File file) {

        initialiseFile(file);
        isInitialised = true;
    }

    /**
     * <p>Constructor for AbstractPsiXmlStream.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public AbstractPsiXmlStream(InputStream input) {

        initialiseInputStream(input);
        isInitialised = true;
    }

    /**
     * <p>Constructor for AbstractPsiXmlStream.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public AbstractPsiXmlStream(Reader reader) {

        initialiseReader(reader);
        isInitialised = true;
    }

    /**
     * <p>Constructor for AbstractPsiXmlStream.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public AbstractPsiXmlStream(URL url) {

        initialiseURL(url);
        isInitialised = true;
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<T> getInteractionsIterator() throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The PsiXml interaction datasource has not been initialised. The options for the Psi xml interaction datasource should contains at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        // reset parser if possible
        try {
            if (this.parser.hasFinished()){
                reInit();
            }
        } catch (PsiXmlParserException e) {
            if (defaultParserListener != null){
                defaultParserListener.onInvalidSyntax(new DefaultFileSourceContext(e.getLocator()), e);
            }
            else{
                throw new MIIOException("Cannot know if the parser has finished.", e);
            }
        }
        return createXmlIterator();
    }

    /** {@inheritDoc} */
    @Override
    public MIFileParserListener getFileParserListener() {
        return this.defaultParserListener;
    }

    /** {@inheritDoc} */
    @Override
    public void setFileParserListener(MIFileParserListener listener) {
        this.defaultParserListener = listener;
        if (listener instanceof PsiXmlParserListener){
            this.parserListener = (PsiXmlParserListener)listener;
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean validateSyntax() throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The PsiXml interaction datasource has not been initialised. The options for the Psi xml interaction datasource should contains at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }

        if (isValid != null){
            return isValid;
        }

        // if the parser did parse all interactions, we reset the current streams
        try {
            if (this.parser.hasFinished()){
                reInit();
            }
        } catch (PsiXmlParserException e) {
            if (defaultParserListener != null){
                defaultParserListener.onInvalidSyntax(new DefaultFileSourceContext(e.getLocator()), e);
            }
            else{
                throw new MIIOException("Cannot know if the parser has finished.", e);
            }
        }

        try {
            XMLReader r = XMLReaderFactory.createXMLReader();

            r.setFeature( VALIDATION_FEATURE, true );
            r.setFeature( SCHEMA_FEATURE, true );

            r.setErrorHandler( this );
            if (this.originalFile != null){
                r.parse( new InputSource( new FileInputStream(this.originalFile)));
            }
            else if (this.originalURL != null){
                r.parse( new InputSource( this.originalURL.openStream()));
            }
            else if (this.originalStream != null){
                r.parse( new InputSource( this.originalStream));
            }
            else if (this.originalReader != null){
                r.parse( new InputSource( this.originalReader));
            }

        } catch (SAXException e) {
            onInvalidSyntax(new DefaultFileSourceContext(), e);
        } catch (FileNotFoundException e) {
            throw new MIIOException("Impossible to validate the source file",e);
        } catch (IOException e) {
            throw new MIIOException("Impossible to validate the source file",e);
        }
        if (isValid == null){
           isValid = true;
        }

        return isValid;
    }

    /** {@inheritDoc} */
    @Override
    public boolean validateSyntax(MIFileParserListener listener) throws MIIOException {
        setMIFileParserListener(listener);
        return validateSyntax();
    }

    /**
     * <p>Getter for the field <code>interactorFactory</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory} object.
     */
    public XmlInteractorFactory getInteractorFactory() {
        return interactorFactory;
    }

    /**
     * <p>Setter for the field <code>interactorFactory</code>.</p>
     *
     * @param interactorFactory a {@link psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory} object.
     */
    public void setInteractorFactory(XmlInteractorFactory interactorFactory) {
        this.interactorFactory = interactorFactory;
        if (this.parser != null){
            this.parser.setInteractorFactory(interactorFactory);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void initialiseContext(Map<String, Object> options) {
        File sourceFile = null;
        URL sourceUrl = null;
        InputStream sourceStream = null;
        Reader sourceReader = null;

        if (options == null && !isInitialised){
            throw new IllegalArgumentException("The options for the PsiXml interaction datasource should contains at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        else if (options == null){
            return;
        }
        else if (options.containsKey(MIFileDataSourceOptions.INPUT_OPTION_KEY)){
            Object input = options.get(MIFileDataSourceOptions.INPUT_OPTION_KEY);
            if (input instanceof URL){
                sourceUrl = (URL) input;
            }
            else if (input instanceof File){
                sourceFile = (File) input;
            }
            else if (input instanceof InputStream){
                sourceStream = (InputStream) input;
            }
            else if (input instanceof Reader){
                sourceReader = (Reader) input;
            }
            // suspect a file/url path
            else if (input instanceof String){
                String inputString = (String)input;
                SourceCategory category = MIFileDatasourceUtils.findSourceCategoryFromString(inputString);
                switch (category){
                    // file uri
                    case file_URI:
                        try {
                            sourceFile = new File(new URI(inputString));
                        } catch (URISyntaxException e) {
                            throw new IllegalArgumentException("Impossible to open and read the file " + inputString, e);
                        }
                        break;
                    // we have a url
                    case URL:
                        try {
                            sourceUrl = new URL(inputString);
                        } catch (MalformedURLException e) {
                            throw new IllegalArgumentException("Impossible to open and read the URL " + inputString, e);
                        }
                        break;
                    // we have a file
                    default:
                        sourceFile = new File(inputString);
                        break;

                }
            }
            else {
                throw new IllegalArgumentException("Impossible to read the provided input "+input.getClass().getName() + ", a File, InputStream, Reader, URL or file/URL path was expected.");
            }
        }
        else if (!isInitialised){
            throw new IllegalArgumentException("The options for the Psi xml interaction datasource should contains at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }

        if (options.containsKey(MIDataSourceOptions.COMPLEX_EXPANSION_OPTION_KEY)){
            initialiseExpansionMethod((ComplexExpansionMethod<T,? extends BinaryInteraction>)options.get(MIDataSourceOptions.COMPLEX_EXPANSION_OPTION_KEY));
        }

        if (options.containsKey(MIFileDataSourceOptions.PARSER_LISTENER_OPTION_KEY)){
            setMIFileParserListener((MIFileParserListener) options.get(MIFileDataSourceOptions.PARSER_LISTENER_OPTION_KEY));
        }

        if (options.containsKey(PsiXmlDataSourceOptions.ELEMENT_WITH_ID_CACHE_OPTION)){
            this.elementCache = (PsiXmlIdCache)options.get(PsiXmlDataSourceOptions.ELEMENT_WITH_ID_CACHE_OPTION);
        }

        if (options.containsKey(PsiXmlDataSourceOptions.INTERACTOR_FACTORY_OPTION_KEY)){
           setInteractorFactory(new XmlInteractorFactory((XmlInteractorFactory)options.get(PsiXmlDataSourceOptions.ELEMENT_WITH_ID_CACHE_OPTION)));
        }

        // initialise parser after reading all options
        if (sourceFile != null){
            initialiseFile(sourceFile);
        }
        else if (sourceUrl != null){
            initialiseURL(sourceUrl);
        }
        else if (sourceStream != null){
            initialiseInputStream(sourceStream);
        }
        else if (sourceReader!= null){
            initialiseReader(sourceReader);
        }
        isInitialised = true;
    }

    /** {@inheritDoc} */
    @Override
    public void close() throws MIIOException{
        if (isInitialised){
            this.elementCache = null;
            this.parserListener = null;
            this.defaultParserListener = null;
            this.isValid = null;
            isInitialised = false;
            if (this.parser != null){
                this.parser.close();
            }
            this.interactorFactory = null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void reset() throws MIIOException{
        if (isInitialised){
            this.elementCache = null;
            this.parser = null;
            this.parserListener = null;
            this.defaultParserListener = null;
            isInitialised = false;
            this.isValid = null;
            this.interactorFactory = null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onUnresolvedReference(XmlIdReference ref, String message) {
        if (parserListener != null){
            parserListener.onUnresolvedReference(ref, message);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(ref, message);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onSeveralHostOrganismFound(Collection<Organism> organisms, FileSourceLocator locator) {
        if (parserListener != null){
            parserListener.onSeveralHostOrganismFound(organisms, locator);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning((FileSourceContext)organisms.iterator().next(), organisms.size()+" host organism attached to the same experiment");
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onSeveralExpressedInOrganismFound(Collection<Organism> organisms, FileSourceLocator locator) {
        if (parserListener != null){
            parserListener.onSeveralExpressedInOrganismFound(organisms, locator);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning((FileSourceContext)organisms.iterator().next(), organisms.size()+" host organism attached to the same participant");
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onSeveralExperimentalRolesFound(Collection<CvTerm> roles, FileSourceLocator locator) {
        if (parserListener != null){
            parserListener.onSeveralExperimentalRolesFound(roles, locator);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning((FileSourceContext)roles.iterator().next(), roles.size()+" experimental roles attached to the same participant");
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onSeveralExperimentsFound(Collection<Experiment> experiments, FileSourceLocator locator) {
        if (parserListener != null){
            parserListener.onSeveralExperimentsFound(experiments, locator);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning((FileSourceContext)experiments.iterator().next(), experiments.size()+" experiments attached to the same interaction");
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onInvalidSyntax(FileSourceContext context, Exception e) {
        if (defaultParserListener != null){
            defaultParserListener.onInvalidSyntax(context, e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onSyntaxWarning(FileSourceContext context, String message) {
        if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(context, message);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onMissingCvTermName(CvTerm term, FileSourceContext context, String message) {
        if (defaultParserListener != null){
            defaultParserListener.onMissingCvTermName(term, context, message);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onMissingInteractorName(Interactor interactor, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onMissingInteractorName(interactor, context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onParticipantWithoutInteractor(Participant participant, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onParticipantWithoutInteractor(participant, context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onInteractionWithoutParticipants(Interaction interaction, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onInteractionWithoutParticipants(interaction, context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(new DefaultFileSourceContext(new FileSourceLocator(exception.getLineNumber(), exception.getColumnNumber())), ExceptionUtils.getFullStackTrace(exception));
        }
    }

    /** {@inheritDoc} */
    @Override
    public void error(SAXParseException exception) throws SAXException {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onInvalidSyntax(new DefaultFileSourceContext(new FileSourceLocator(exception.getLineNumber(), exception.getColumnNumber())), exception);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onInvalidSyntax(new DefaultFileSourceContext(new FileSourceLocator(exception.getLineNumber(), exception.getColumnNumber())), exception);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onInvalidOrganismTaxid(String taxid, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onInvalidOrganismTaxid(taxid, context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onMissingParameterValue(FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onMissingParameterValue(context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onMissingParameterType(FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onMissingParameterType(context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onMissingConfidenceValue(FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onMissingConfidenceValue(context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onMissingConfidenceType(FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onMissingConfidenceType(context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onMissingChecksumValue(FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onMissingChecksumValue(context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onMissingChecksumMethod(FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onMissingChecksumMethod(context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onInvalidPosition(String message, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onInvalidPosition(message, context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onInvalidRange(String message, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onInvalidRange(message, context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onInvalidStoichiometry(String message, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onInvalidStoichiometry(message, context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onXrefWithoutDatabase(FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onXrefWithoutDatabase(context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onXrefWithoutId(FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onXrefWithoutId(context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onAnnotationWithoutTopic(FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onAnnotationWithoutTopic(context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onAliasWithoutName(FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onAliasWithoutName(context);
        }
    }

    /**
     * <p>initialiseXmlParser.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    protected abstract void initialiseXmlParser(Reader reader);

    /**
     * <p>initialiseXmlParser.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    protected abstract void initialiseXmlParser(File file);

    /**
     * <p>initialiseXmlParser.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    protected abstract void initialiseXmlParser(InputStream input);

    /**
     * <p>initialiseXmlParser.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    protected abstract void initialiseXmlParser(URL url);

    /**
     * <p>setXmlFileParserListener.</p>
     *
     * @param listener a {@link psidev.psi.mi.jami.xml.listener.PsiXmlParserListener} object.
     */
    protected void setXmlFileParserListener(PsiXmlParserListener listener) {
        this.parserListener = listener;
        this.defaultParserListener = listener;
    }

    /**
     * <p>setMIFileParserListener.</p>
     *
     * @param listener a {@link psidev.psi.mi.jami.listener.MIFileParserListener} object.
     */
    protected void setMIFileParserListener(MIFileParserListener listener) {
        if (listener instanceof PsiXmlParserListener){
            setXmlFileParserListener((PsiXmlParserListener) listener);
        }
        else{
            this.parserListener = null;
            this.defaultParserListener = listener;
        }
    }

    /**
     * <p>reInit.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    protected void reInit() throws MIIOException{
        if (this.elementCache != null){
            this.elementCache.clear();
        }
        // release the thread local
        XmlEntryContext.getInstance().clear();
        XmlEntryContext.remove();
        // release the thread local
        if (this.originalFile != null){
            // close the previous reader
            if (this.originalReader != null){
                try {
                    this.originalReader.close();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Could not close the reader.", e);
                }
            }
            // close the previous stream and reader
            if (this.originalStream != null){
                try {
                    this.originalStream.close();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Could not close the inputStream.", e);
                }
            }
            initialiseXmlParser(this.originalFile);
        }
        else if (this.originalURL != null){
            // close the previous reader
            if (this.originalReader != null){
                try {
                    this.originalReader.close();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Could not close the reader.", e);
                }
            }
            // close the previous stream
            if (this.originalStream != null){
                try {
                    this.originalStream.close();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Could not close the inputStream.", e);
                }
            }
            initialiseXmlParser(this.originalURL);
        }
        else if (this.originalReader != null){
            // reinit line parser if reader can be reset
            if (this.originalReader.markSupported()){
                try {
                    this.originalReader.reset();
                } catch (IOException e) {
                    throw new MIIOException("We cannot open the reader  ", e);
                }
            }
            else {
                throw new MIIOException("The reader has been consumed and cannot be reset");
            }
            initialiseXmlParser(this.originalReader);
        }
        else if (this.originalStream != null){
            // reinit parser if inputStream can be reset
            if (this.originalStream.markSupported()){
                try {
                    this.originalStream.reset();
                } catch (IOException e) {
                    throw new MIIOException("We cannot read the inputStream  ", e);
                }
            }
            else {
                throw new MIIOException("The inputStream has been consumed and cannot be reset");
            }
            initialiseXmlParser(this.originalStream);
        }
    }

    /**
     * <p>initialiseExpansionMethod.</p>
     *
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    protected abstract void initialiseExpansionMethod(ComplexExpansionMethod<? extends Interaction, ? extends BinaryInteraction> expansionMethod);

    /**
     * <p>createXmlIterator.</p>
     *
     * @return a {@link java.util.Iterator} object.
     */
    protected abstract Iterator<T> createXmlIterator();

    /**
     * <p>Getter for the field <code>parser</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.parser.PsiXmlParser} object.
     */
    protected PsiXmlParser<T> getParser() {
        return parser;
    }

    /**
     * <p>Setter for the field <code>parser</code>.</p>
     *
     * @param parser a {@link psidev.psi.mi.jami.xml.io.parser.PsiXmlParser} object.
     */
    protected void setParser(PsiXmlParser<T> parser) {
        this.parser = parser;
        this.parser.setInteractorFactory(getInteractorFactory());
    }

    /**
     * <p>isInitialised.</p>
     *
     * @return a boolean.
     */
    protected boolean isInitialised() {
        return isInitialised;
    }

    /**
     * <p>Getter for the field <code>elementCache</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.cache.PsiXmlIdCache} object.
     */
    protected PsiXmlIdCache getElementCache() {
        return elementCache;
    }

    private void initialiseReader(Reader reader) {
        if (reader == null){
            throw new IllegalArgumentException("The reader cannot be null.");
        }
        this.originalReader = reader;
        initialiseXmlParser(reader);
    }

    private void initialiseInputStream(InputStream input) {
        if (input == null){
            throw new IllegalArgumentException("The input stream cannot be null.");
        }
        this.originalStream = input;
        initialiseXmlParser(input);
    }

    private void initialiseFile(File file)  {
        if (file == null){
            throw new IllegalArgumentException("The file cannot be null.");
        }
        else if (!file.canRead()){
            throw new IllegalArgumentException("Does not have the permissions to read the file "+file.getAbsolutePath());
        }
        this.originalFile = file;
        initialiseXmlParser(file);
    }

    private void initialiseURL(URL url)  {
        if (url == null){
            throw new IllegalArgumentException("The url cannot be null.");
        }
        this.originalURL = url;
        initialiseXmlParser(url);
    }
}

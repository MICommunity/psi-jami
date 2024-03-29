package psidev.psi.mi.jami.tab.extension.datasource;

import psidev.psi.mi.jami.datasource.DefaultFileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.SourceCategory;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.InteractorFactory;
import psidev.psi.mi.jami.factory.options.MIDataSourceOptions;
import psidev.psi.mi.jami.factory.options.MIFileDataSourceOptions;
import psidev.psi.mi.jami.listener.MIFileParserListener;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.tab.extension.*;
import psidev.psi.mi.jami.tab.extension.MitabSource;
import psidev.psi.mi.jami.tab.io.parser.AbstractInteractionLineParser;
import psidev.psi.mi.jami.tab.io.parser.MitabLineParser;
import psidev.psi.mi.jami.tab.io.parser.ParseException;
import psidev.psi.mi.jami.tab.listener.MitabParserListener;
import psidev.psi.mi.jami.utils.MIFileDatasourceUtils;

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
 * abstract class for Mitab datasource.
 *
 * This datasource loads all the interactions from a MITAB file
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/06/13</pre>
 */
public abstract class AbstractMitabStreamSource<T extends Interaction, P extends Participant, F extends Feature> implements MitabStreamSource<T>{

    private static final Logger logger = Logger.getLogger("AbstractMitabStreamSource");
    private AbstractInteractionLineParser<T,P,F> lineParser;
    private boolean isInitialised = false;

    private URL originalURL;
    private File originalFile;
    private InputStream originalStream;
    private Reader originalReader;

    private Boolean isValid = null;

    private MitabParserListener parserListener;
    private MIFileParserListener defaultParserListener;

    private InteractorFactory interactorFactory;

    /**
     * Empty constructor for the factory
     */
    public AbstractMitabStreamSource(){
    }

    /**
     * <p>Constructor for AbstractMitabStreamSource.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public AbstractMitabStreamSource(File file) throws IOException {

        initialiseFile(file);
        isInitialised = true;
    }

    /**
     * <p>Constructor for AbstractMitabStreamSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public AbstractMitabStreamSource(InputStream input) {

        initialiseInputStream(input);
        isInitialised = true;
    }

    /**
     * <p>Constructor for AbstractMitabStreamSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public AbstractMitabStreamSource(Reader reader) {

        initialiseReader(reader);
        isInitialised = true;
    }

    /**
     * <p>Constructor for AbstractMitabStreamSource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public AbstractMitabStreamSource(URL url) {

        initialiseURL(url);
        isInitialised = true;
    }

    /**
     * <p>getFileParserListener.</p>
     *
     * @return a {@link psidev.psi.mi.jami.listener.MIFileParserListener} object.
     */
    public MIFileParserListener getFileParserListener() {
        return this.defaultParserListener;
    }

    /** {@inheritDoc} */
    public void setFileParserListener(MIFileParserListener listener) {
        this.defaultParserListener = listener;
        if (listener instanceof MitabParserListener){
            this.parserListener = (MitabParserListener)listener;
        }
    }

    /** {@inheritDoc} */
    public void initialiseContext(Map<String, Object> options) {
        if (options == null && !isInitialised){
            throw new IllegalArgumentException("The options for the Mitab interaction datasource should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        else if (options == null){
            return;
        }
        else if (options.containsKey(MIFileDataSourceOptions.INPUT_OPTION_KEY)){
            Object input = options.get(MIFileDataSourceOptions.INPUT_OPTION_KEY);
            if (input instanceof URL){
               initialiseURL((URL) input);
            }
            else if (input instanceof File){
                initialiseFile((File) input);
            }
            else if (input instanceof InputStream){
                initialiseInputStream((InputStream) input);
            }
            else if (input instanceof Reader){
                initialiseReader((Reader) input);
            }
            // suspect a file/url path
            else if (input instanceof String){
                String inputString = (String)input;
                SourceCategory category = MIFileDatasourceUtils.findSourceCategoryFromString(inputString);
                switch (category){
                    // file uri
                    case file_URI:
                        try {
                            initialiseFile(new File(new URI(inputString)));
                        } catch (URISyntaxException e) {
                            throw new IllegalArgumentException("Impossible to open and read the file " + inputString, e);
                        }
                        break;
                    // we have a url
                    case URL:
                        try {
                            initialiseURL(new URL(inputString));
                        } catch (MalformedURLException e) {
                            throw new IllegalArgumentException("Impossible to open and read the URL " + inputString, e);
                        }
                        break;
                    // we have a file
                    default:
                        initialiseFile(new File(inputString));
                        break;

                }
            }
            else {
                throw new IllegalArgumentException("Impossible to read the provided input "+input.getClass().getName() + ", a File, InputStream, Reader, URL or file/URL path was expected.");
            }
        }
        else if (!isInitialised){
            throw new IllegalArgumentException("The options for the Mitab interaction datasource should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }

        if (options.containsKey(MIFileDataSourceOptions.PARSER_LISTENER_OPTION_KEY)){
            setMIFileParserListener((MIFileParserListener) options.get(MIFileDataSourceOptions.PARSER_LISTENER_OPTION_KEY));
        }

        if (options.containsKey(MIDataSourceOptions.INTERACTOR_FACTORY_OPTION_KEY)){
            setInteractorFactory((InteractorFactory) options.get(MIDataSourceOptions.INTERACTOR_FACTORY_OPTION_KEY));
        }

        isInitialised = true;
    }

    /**
     * <p>close.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void close() throws MIIOException{
        if (isInitialised){

            if (this.originalStream != null){
                try {
                    this.originalStream.close();
                } catch (IOException e) {
                    throw new MIIOException("Impossible to close the original stream", e);
                }
                finally {
                    this.originalFile = null;
                    this.originalURL = null;
                    this.originalStream = null;
                    this.originalReader = null;
                    this.lineParser = null;
                    this.parserListener = null;
                    this.defaultParserListener = null;
                    isInitialised = false;
                    isValid = null;
                    isInitialised = false;
                    this.interactorFactory = null;
                }
            }
            else if (this.originalReader != null){
                try {
                    this.originalReader.close();
                } catch (IOException e) {
                    throw new MIIOException("Impossible to close the original reader", e);
                }
                finally {
                    this.originalFile = null;
                    this.originalURL = null;
                    this.originalStream = null;
                    this.originalReader = null;
                    this.lineParser = null;
                    this.parserListener = null;
                    this.defaultParserListener = null;
                    isInitialised = false;
                    isValid = null;
                    isInitialised = false;
                    this.interactorFactory = null;
                }
            }
            else{
                this.originalFile = null;
                this.originalURL = null;
                this.originalStream = null;
                this.originalReader = null;
                this.lineParser = null;
                this.parserListener = null;
                this.defaultParserListener = null;
                isInitialised = false;
                isValid = null;
                isInitialised = false;
                this.interactorFactory = null;
            }
        }
    }

    /**
     * <p>reset.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void reset() throws MIIOException{
        if (isInitialised){
            this.originalFile = null;
            this.originalURL = null;
            this.originalStream = null;
            this.originalReader = null;
            this.lineParser = null;
            this.parserListener = null;
            this.defaultParserListener = null;
            isInitialised = false;
            isValid = null;
            isInitialised = false;
            this.interactorFactory = null;
        }
    }

    /** {@inheritDoc} */
    public boolean validateSyntax(MIFileParserListener listener) {
        setMIFileParserListener(listener);
        return validateSyntax();
    }

    /**
     * <p>validateSyntax.</p>
     *
     * @return a boolean.
     */
    public boolean validateSyntax() {
        if (!isInitialised){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }

        if (lineParser.hasFinished() && isValid == null){
            isValid = true;
        }

        if (isValid != null){
            return isValid;
        }

        // read the datasource
        Iterator<T> interactionIterator = getInteractionsIterator();
        while(interactionIterator.hasNext()){
            interactionIterator.next();
        }
        // if isValid is not null, it means that the file syntax is invalid, otherwise, we say that the file syntax is valid
        if (isValid == null){
            isValid = true;
        }
        return isValid;
    }

    /** {@inheritDoc} */
    public void onInvalidSyntax(FileSourceContext context, Exception e) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onInvalidSyntax(context, e);
        }
    }

    /** {@inheritDoc} */
    public void onSyntaxWarning(FileSourceContext context, String message) {
        if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(context, message);
        }
    }

    /** {@inheritDoc} */
    public void onMissingCvTermName(CvTerm term, FileSourceContext context, String message) {
        if (defaultParserListener != null){
            defaultParserListener.onMissingCvTermName(term, context, message);
        }
    }

    /** {@inheritDoc} */
    public void onMissingInteractorName(Interactor interactor, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onMissingInteractorName(interactor, context);
        }
    }

    /** {@inheritDoc} */
    public void onSeveralCvTermsFound(Collection<MitabCvTerm> terms, FileSourceContext context, String message) {
        if (parserListener != null){
            parserListener.onSeveralCvTermsFound(terms, context, message);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(context, message);
        }
    }

    /** {@inheritDoc} */
    public void onSeveralHostOrganismFound(Collection<MitabOrganism> organisms, FileSourceContext context) {
        if (parserListener != null){
            parserListener.onSeveralHostOrganismFound(organisms, context);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(context, organisms.size() + " host organisms found. Only one is expected. Only the first organism will be loaded, the other organisms will be ignored.");
        }
    }

    /** {@inheritDoc} */
    public void onParticipantWithoutInteractor(Participant participant, FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onParticipantWithoutInteractor(participant, context);
        }
    }

    /** {@inheritDoc} */
    public void onInteractionWithoutParticipants(Interaction interaction, FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onInteractionWithoutParticipants(interaction, context);
        }
    }


    /** {@inheritDoc} */
    public void onMissingInteractorIdentifierColumns(int line, int column, int mitabColumn) {
        isValid = false;
        if (parserListener != null){
            parserListener.onMissingInteractorIdentifierColumns(line, column, mitabColumn);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onInvalidSyntax(new DefaultFileSourceContext(new MitabSourceLocator(line, column, mitabColumn)), new ParseException("Interactor without any identifiers."));
        }
    }

    /** {@inheritDoc} */
    public void onSeveralOrganismFound(Collection<MitabOrganism> organisms) {
        if (parserListener != null){
            parserListener.onSeveralOrganismFound(organisms);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(organisms.iterator().next(), organisms.size() + " organisms found. Only one is expected. Only the first organism will be loaded, the other organisms will be ignored.");
        }
    }

    /** {@inheritDoc} */
    public void onSeveralStoichiometryFound(Collection<MitabStoichiometry> stoichiometry) {
        if (parserListener != null){
            parserListener.onSeveralStoichiometryFound(stoichiometry);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(stoichiometry.iterator().next(), stoichiometry.size() + " different stoichiometries found. Only one is expected. Only the first stoichiometry will be loaded, the other stoichiometries will be ignored.");
        }
    }

    /** {@inheritDoc} */
    public void onSeveralFirstAuthorFound(Collection<MitabAuthor> authors) {
        if (parserListener != null){
            parserListener.onSeveralFirstAuthorFound(authors);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(authors.iterator().next(), authors.size() + " first authors found. Only one is expected. Only the first author will be loaded, the other authors will be ignored.");
        }
    }

    /** {@inheritDoc} */
    public void onSeveralSourceFound(Collection<MitabSource> sources) {
        if (parserListener != null){
            parserListener.onSeveralSourceFound(sources);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(sources.iterator().next(), sources.size() + " sources found. Only one is expected. Only the first source will be loaded, the other sources will be ignored.");
        }
    }

    /** {@inheritDoc} */
    public void onSeveralCreatedDateFound(Collection<MitabDate> dates) {
        if (parserListener != null){
            parserListener.onSeveralCreatedDateFound(dates);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(dates.iterator().next(), dates.size() + " created dates found. Only one is expected. Only the first created date will be loaded, the other created dates will be ignored.");
        }
    }

    /** {@inheritDoc} */
    public void onSeveralUpdatedDateFound(Collection<MitabDate> dates) {
        if (parserListener != null){
            parserListener.onSeveralUpdatedDateFound(dates);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(dates.iterator().next(), dates.size() + " update dates found. Only one is expected. Only the first update date will be loaded, the other update dates will be ignored.");
        }
    }

    /** {@inheritDoc} */
    public void onTextFoundInIdentifier(MitabXref xref) {
        if (parserListener != null){
            parserListener.onTextFoundInIdentifier(xref);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(xref, "Some text has been found attached to one identifier. Usually in mitab, identifiers do not have any text in parenthesis.");
        }
    }

    /** {@inheritDoc} */
    public void onTextFoundInConfidence(MitabConfidence conf) {
        if (parserListener != null){
            parserListener.onTextFoundInConfidence(conf);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(conf, "Some text has been found attached to one confidence. Usually in mitab, confidences do not have any text in parenthesis.");
        }
    }

    /** {@inheritDoc} */
    public void onMissingExpansionId(MitabCvTerm expansion) {
        if (parserListener != null){
            parserListener.onMissingExpansionId(expansion);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(expansion, "The provided expansion method does not have an identifier. As it is a controlled vocabulary, a PSI-MI identifier was expected.");
        }
    }

    /** {@inheritDoc} */
    public void onSeveralUniqueIdentifiers(Collection<MitabXref> ids) {
        if (parserListener != null){
            parserListener.onSeveralUniqueIdentifiers(ids);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(ids.iterator().next(), ids.size() + " unique identifiers found. Only one should be provided as unique identifier, other identifiers should go in alternative identifiers columns.");
        }
    }

    /** {@inheritDoc} */
    public void onEmptyUniqueIdentifiers(int line, int column, int mitabColumn) {
        isValid = false;
        if (parserListener != null){
            parserListener.onEmptyUniqueIdentifiers(line, column, mitabColumn);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onInvalidSyntax(new DefaultFileSourceContext(new MitabSourceLocator(line, column, mitabColumn)), new ParseException("No unique identifier found. Each interactor should have at least one unique identifier in MITAB."));
        }
    }

    /** {@inheritDoc} */
    public void onAliasWithoutDbSource(MitabAlias alias) {
        isValid = false;
        if (parserListener != null){
            parserListener.onAliasWithoutDbSource(alias);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onInvalidSyntax(alias, new ParseException("Invalid MITAB alias: all aliases should have a db source."));
        }
    }

    /** {@inheritDoc} */
    public void onInvalidOrganismTaxid(String taxid, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onInvalidOrganismTaxid(taxid, context);
        }
    }

    /** {@inheritDoc} */
    public void onMissingParameterValue(FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onMissingParameterValue(context);
        }
    }

    /** {@inheritDoc} */
    public void onMissingParameterType(FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onMissingParameterType(context);
        }
    }

    /** {@inheritDoc} */
    public void onMissingConfidenceValue(FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onMissingConfidenceValue(context);
        }
    }

    /** {@inheritDoc} */
    public void onMissingConfidenceType(FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onMissingConfidenceType(context);
        }
    }

    /** {@inheritDoc} */
    public void onMissingChecksumValue(FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onMissingChecksumValue(context);
        }
    }

    /** {@inheritDoc} */
    public void onMissingChecksumMethod(FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onMissingChecksumMethod(context);
        }
    }

    /** {@inheritDoc} */
    public void onInvalidPosition(String message, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onInvalidPosition(message, context);
        }
    }

    /** {@inheritDoc} */
    public void onInvalidRange(String message, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onInvalidRange(message, context);
        }
    }

    /** {@inheritDoc} */
    public void onInvalidStoichiometry(String message, FileSourceContext context) {
        if (defaultParserListener != null){
            defaultParserListener.onInvalidStoichiometry(message, context);
        }
    }

    /** {@inheritDoc} */
    public void onXrefWithoutDatabase(FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onXrefWithoutDatabase(context);
        }
    }

    /** {@inheritDoc} */
    public void onXrefWithoutId(FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onXrefWithoutId(context);
        }
    }

    /** {@inheritDoc} */
    public void onAnnotationWithoutTopic(FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onAnnotationWithoutTopic(context);
        }
    }

    /** {@inheritDoc} */
    public void onAliasWithoutName(FileSourceContext context) {
        isValid = false;
        if (defaultParserListener != null){
            defaultParserListener.onAliasWithoutName(context);
        }
    }

    /**
     * <p>getInteractionsIterator.</p>
     *
     * @return a {@link java.util.Iterator} object.
     */
    public Iterator<T> getInteractionsIterator() {
        if (!isInitialised){
            throw new IllegalStateException("The Mitab interaction datasource has not been initialised. The options for the Mitab interaction datasource should contain at least "+ MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
        }
        // reset parser if possible
        if (lineParser.hasFinished()){
           reInit();
        }
        return createMitabIterator();
    }

    /**
     * <p>Getter for the field <code>interactorFactory</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.factory.InteractorFactory} object.
     */
    public InteractorFactory getInteractorFactory() {
        return interactorFactory;
    }

    /**
     * <p>Setter for the field <code>interactorFactory</code>.</p>
     *
     * @param interactorFactory a {@link psidev.psi.mi.jami.factory.InteractorFactory} object.
     */
    public void setInteractorFactory(InteractorFactory interactorFactory) {
        this.interactorFactory = interactorFactory;
        if (this.lineParser != null){
            this.lineParser.setInteractorFactory(interactorFactory);
        }
    }

    /**
     * <p>Getter for the field <code>lineParser</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.tab.io.parser.MitabLineParser} object.
     */
    protected MitabLineParser<T,P,F> getLineParser() {
        return lineParser;
    }

    /**
     * <p>Setter for the field <code>lineParser</code>.</p>
     *
     * @param lineParser a {@link psidev.psi.mi.jami.tab.io.parser.AbstractInteractionLineParser} object.
     */
    protected void setLineParser(AbstractInteractionLineParser<T,P,F> lineParser) {
        this.lineParser = lineParser;
        this.lineParser.setParserListener(this);
        this.lineParser.setInteractorFactory(this.interactorFactory);
    }

    /**
     * <p>initialiseMitabLineParser.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    protected abstract void initialiseMitabLineParser(Reader reader);

    /**
     * <p>initialiseMitabLineParser.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    protected abstract void initialiseMitabLineParser(File file);

    /**
     * <p>initialiseMitabLineParser.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    protected abstract void initialiseMitabLineParser(InputStream input);

    /**
     * <p>initialiseMitabLineParser.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    protected abstract void initialiseMitabLineParser(URL url);

    /**
     * <p>createMitabIterator.</p>
     *
     * @return a {@link java.util.Iterator} object.
     */
    protected abstract Iterator<T> createMitabIterator();

    /**
     * <p>reInit.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    protected void reInit() throws MIIOException{
        if (isInitialised){
            if (this.originalFile != null){
                // close the previous stream
                if (this.originalStream != null){
                    try {
                        this.originalStream.close();
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "Could not close the inputStream.", e);
                    }
                }
                // reinitialise mitab parser
                try {
                    this.originalStream = new BufferedInputStream(new FileInputStream(this.originalFile));
                    this.lineParser.ReInit(this.originalStream);

                } catch (FileNotFoundException e) {
                    throw new MIIOException("We cannot open the file " + this.originalFile.getName(), e);
                }
            }
            else if (this.originalURL != null){
                // close the previous stream
                if (this.originalStream != null){
                    try {
                        this.originalStream.close();
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "Could not close the inputStream.", e);
                    }
                }
                // reinitialise mitab parser
                try {
                    this.originalStream = originalURL.openStream();
                    this.lineParser.ReInit(this.originalStream);

                } catch (IOException e) {
                    throw new MIIOException("We cannot open the URL " + this.originalURL.toExternalForm(), e);
                }
            }
            else if (this.originalStream != null){
                // reinit line parser if inputStream can be reset
                if (this.originalStream.markSupported()){
                    try {
                        this.originalStream.reset();
                        this.lineParser.ReInit(this.originalStream);

                    } catch (IOException e) {
                        throw new MIIOException("The inputStream has been consumed and cannot be reset", e);
                    }
                }
                else {
                    throw new MIIOException("The inputStream has been consumed and cannot be reset");
                }
            }
            else if (this.originalReader != null){
                // reinit line parser if reader can be reset
                if (this.originalReader.markSupported()){
                    try {
                        this.originalReader.reset();
                        this.lineParser.ReInit(this.originalReader);
                    } catch (IOException e) {
                        throw new MIIOException("The reader has been consumed and cannot be reset", e);
                    }
                }
                else {
                    throw new MIIOException("The reader has been consumed and cannot be reset");
                }
            }
        }
    }

    private void initialiseReader(Reader reader) {
        if (reader == null){
            throw new IllegalArgumentException("The reader cannot be null.");
        }
        this.originalFile = null;
        this.originalReader = reader;
        this.originalStream = null;
        this.originalURL = null;

        initialiseMitabLineParser(reader);
    }

    private void initialiseInputStream(InputStream input) {
        if (input == null){
            throw new IllegalArgumentException("The input stream cannot be null.");
        }
        this.originalFile = null;
        this.originalReader = null;
        this.originalStream = input;
        this.originalURL = null;

        initialiseMitabLineParser(input);
    }

    private void initialiseFile(File file)  {
        if (file == null){
            throw new IllegalArgumentException("The file cannot be null.");
        }
        else if (!file.canRead()){
            throw new IllegalArgumentException("Does not have the permissions to read the file "+file.getAbsolutePath());
        }
        this.originalFile = file;
        this.originalReader = null;
        this.originalStream = null;
        this.originalURL = null;

        initialiseMitabLineParser(file);
    }

    private void initialiseURL(URL url)  {
        if (url == null){
            throw new IllegalArgumentException("The url cannot be null.");
        }
        this.originalURL = url;
        this.originalReader = null;
        this.originalStream = null;
        this.originalFile = null;

        initialiseMitabLineParser(url);
    }

    /**
     * <p>Setter for the field <code>originalFile</code>.</p>
     *
     * @param originalFile a {@link java.io.File} object.
     */
    protected void setOriginalFile(File originalFile) {
        this.originalFile = originalFile;
    }

    /**
     * <p>Setter for the field <code>originalStream</code>.</p>
     *
     * @param originalStream a {@link java.io.InputStream} object.
     */
    protected void setOriginalStream(InputStream originalStream) {
        this.originalStream = originalStream;
    }

    /**
     * <p>Setter for the field <code>originalReader</code>.</p>
     *
     * @param originalReader a {@link java.io.Reader} object.
     */
    protected void setOriginalReader(Reader originalReader) {
        this.originalReader = originalReader;
    }

    /**
     * <p>Setter for the field <code>originalURL</code>.</p>
     *
     * @param originalURL a {@link java.net.URL} object.
     */
    protected void setOriginalURL(URL originalURL) {
        this.originalURL = originalURL;
    }

    /**
     * <p>setMitabFileParserListener.</p>
     *
     * @param listener a {@link psidev.psi.mi.jami.tab.listener.MitabParserListener} object.
     */
    protected void setMitabFileParserListener(MitabParserListener listener) {
        this.parserListener = listener;
        this.defaultParserListener = listener;
    }

    /**
     * <p>setMIFileParserListener.</p>
     *
     * @param listener a {@link psidev.psi.mi.jami.listener.MIFileParserListener} object.
     */
    protected void setMIFileParserListener(MIFileParserListener listener) {
        if (listener instanceof MitabParserListener){
            setMitabFileParserListener((MitabParserListener) listener);
        }
        else{
            this.parserListener = null;
            this.defaultParserListener = listener;
        }
    }
}

package psidev.psi.mi.jami.crosslink.extension.datasource;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
import psidev.psi.mi.jami.crosslink.extension.CsvProtein;
import psidev.psi.mi.jami.crosslink.extension.CsvRange;
import psidev.psi.mi.jami.crosslink.extension.CsvSourceLocator;
import psidev.psi.mi.jami.crosslink.io.iterator.CsvInteractionEvidenceIterator;
import psidev.psi.mi.jami.crosslink.io.parser.AbstractCsvInteractionEvidenceParser;
import psidev.psi.mi.jami.crosslink.listener.CsvParserListener;
import psidev.psi.mi.jami.datasource.DefaultFileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.SourceCategory;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.MIFileDataSourceOptions;
import psidev.psi.mi.jami.listener.MIFileParserListener;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.MIFileDatasourceUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * abstract class for Crosslink Csv datasource.
 *
 * This datasource loads all the interactions from a Crosslink CSV file
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/06/13</pre>
 */
public abstract class AbstractCsvStreamSource<T extends InteractionEvidence> implements CsvStreamSource<T> {

    private static final Logger logger = Logger.getLogger("AbstractCsvStreamSource");
    private AbstractCsvInteractionEvidenceParser<T> lineParser;
    private boolean isInitialised = false;

    private URL originalURL;
    private File originalFile;
    private InputStream originalStream;
    private Reader originalReader;

    private Boolean isValid = null;

    private CsvParserListener parserListener;
    private MIFileParserListener defaultParserListener;

    private CSVReader<T> csvReader;

    /**
     * Empty constructor for the factory
     */
    public AbstractCsvStreamSource(){
    }

    /**
     * <p>Constructor for AbstractCsvStreamSource.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public AbstractCsvStreamSource(File file) throws IOException {

        initialiseFile(file);
        isInitialised = true;
    }

    /**
     * <p>Constructor for AbstractCsvStreamSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public AbstractCsvStreamSource(InputStream input) {

        initialiseInputStream(input);
        isInitialised = true;
    }

    /**
     * <p>Constructor for AbstractCsvStreamSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public AbstractCsvStreamSource(Reader reader) {

        initialiseReader(reader);
        isInitialised = true;
    }

    /**
     * <p>Constructor for AbstractCsvStreamSource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public AbstractCsvStreamSource(URL url) {

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
        if (listener instanceof CsvParserListener){
            this.parserListener = (CsvParserListener)listener;
        }
    }

    /** {@inheritDoc} */
    public void initialiseContext(Map<String, Object> options) {
        if (options == null && !isInitialised){
            throw new IllegalArgumentException("The options for the CrossLink CSV interaction datasource should contain at least "+
                    MIFileDataSourceOptions.INPUT_OPTION_KEY + " to know where to read the interactions from.");
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
                    this.csvReader = null;
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
                    this.csvReader = null;
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
                this.csvReader = null;
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
            this.csvReader = null;
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

        if (this.lineParser.isStarted() && isValid == null){
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
    public void onMismatchBetweenPeptideAndLinkedPositions(List<CsvRange> peptidePositions, List<CsvRange> linkedPositions) {
        if (parserListener != null){
            parserListener.onMismatchBetweenPeptideAndLinkedPositions(peptidePositions, linkedPositions);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(!peptidePositions.isEmpty()? peptidePositions.iterator().next() : linkedPositions.iterator().next()
                    , "The number of peptide positions does not match the number of linked positions and therefore, will be ignored");
        }
    }

    /** {@inheritDoc} */
    public void onMismatchBetweenRangePositionsAndProteins(List<CsvRange> rangePositions, List<CsvProtein> proteins) {
        if (parserListener != null){
            parserListener.onMismatchBetweenRangePositionsAndProteins(rangePositions, proteins);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(!rangePositions.isEmpty()? rangePositions.iterator().next() : proteins.iterator().next()
                    , "The number of range/linked positions does not match the number of protein identifiers and therefore, will be ignored");
        }
    }

    /** {@inheritDoc} */
    public void onInvalidProteinIdentifierSyntax(String[] identifiers, int lineNumber, int columnNumber) {
        if (parserListener != null){
            parserListener.onInvalidProteinIdentifierSyntax(identifiers, lineNumber, columnNumber);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(new DefaultFileSourceContext(new CsvSourceLocator(lineNumber, -1, columnNumber))
                    , "The protein1/protein2 columns syntax should be sp|uniprotId|name and if several proteins are provided, the identifiers " +
                    "should be separeted by ';'");
        }
    }

    /** {@inheritDoc} */
    public void onMissingProtein1Column(int lineNumber) {
        if (parserListener != null){
            parserListener.onMissingProtein1Column(lineNumber);
        }
        else if (defaultParserListener != null){
            defaultParserListener.onSyntaxWarning(new DefaultFileSourceContext(new CsvSourceLocator(lineNumber, -1, -1))
                    , "The protein1 column is missing or empty and it is required.");
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
        if (this.lineParser.isStarted()){
            reInit();
        }
        return createCsvIterator();
    }

    /**
     * <p>Getter for the field <code>lineParser</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.crosslink.io.parser.AbstractCsvInteractionEvidenceParser} object.
     */
    protected AbstractCsvInteractionEvidenceParser<T> getLineParser() {
        return lineParser;
    }

    /**
     * <p>Setter for the field <code>lineParser</code>.</p>
     *
     * @param lineParser a {@link psidev.psi.mi.jami.crosslink.io.parser.AbstractCsvInteractionEvidenceParser} object.
     */
    protected void setLineParser(AbstractCsvInteractionEvidenceParser<T> lineParser) {
        this.lineParser = lineParser;
        this.lineParser.setParserListener(this);
    }

    /**
     * <p>createCsvIterator.</p>
     *
     * @return a {@link java.util.Iterator} object.
     */
    protected Iterator<T> createCsvIterator(){
        return new CsvInteractionEvidenceIterator<T>(this.csvReader, this);
    }

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
                    this.originalReader = new BufferedReader(new InputStreamReader(this.originalStream));
                } catch (FileNotFoundException e) {
                    throw new MIIOException("We cannot open the file " + this.originalFile.getName(), e);
                } catch (IOException e) {
                    throw new MIIOException("The CSV file does not have valid headers: " + this.originalFile.getName(), e);
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
                    this.originalReader = new BufferedReader(new InputStreamReader(this.originalStream));
                } catch (IOException e) {
                    throw new MIIOException("We cannot open the URL " + this.originalURL.toExternalForm(), e);
                }
            }
            else if (this.originalStream != null){
                // reinit line parser if inputStream can be reset
                if (this.originalStream.markSupported()){
                    try {
                        this.originalStream.reset();
                        this.originalReader = new BufferedReader(new InputStreamReader(this.originalStream));

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
                    } catch (IOException e) {
                        throw new MIIOException("The reader has been consumed and cannot be reset", e);
                    }
                }
                else {
                    throw new MIIOException("The reader has been consumed and cannot be reset");
                }
            }
            createCsvReader();
        }
    }

    /**
     * <p>instantiateLineParser.</p>
     *
     * @return a {@link psidev.psi.mi.jami.crosslink.io.parser.AbstractCsvInteractionEvidenceParser} object.
     */
    protected abstract AbstractCsvInteractionEvidenceParser<T> instantiateLineParser();

    private void initialiseReader(Reader reader) {
        if (reader == null){
            throw new IllegalArgumentException("The reader cannot be null.");
        }
        this.originalFile = null;
        this.originalReader = reader;
        this.originalStream = null;
        this.originalURL = null;

        createCsvReader();
    }

    private void initialiseInputStream(InputStream input) {
        if (input == null){
            throw new IllegalArgumentException("The input stream cannot be null.");
        }
        this.originalFile = null;
        this.originalStream = input;
        this.originalURL = null;
        this.originalReader = new BufferedReader(new InputStreamReader(this.originalStream));

        createCsvReader();
    }

    private void initialiseFile(File file)  {
        if (file == null){
            throw new IllegalArgumentException("The file cannot be null.");
        }
        else if (!file.canRead()){
            throw new IllegalArgumentException("Does not have the permissions to read the file "+file.getAbsolutePath());
        }
        this.originalFile = file;
        try {
            this.originalStream = new BufferedInputStream(new FileInputStream(this.originalFile));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Cannot find file "+file.getAbsolutePath());
        }
        this.originalReader = new BufferedReader(new InputStreamReader(this.originalStream));
        this.originalURL = null;
        createCsvReader();
    }

    private void initialiseURL(URL url)  {
        if (url == null){
            throw new IllegalArgumentException("The url cannot be null.");
        }
        this.originalURL = url;
        try {
            this.originalStream = originalURL.openStream();
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot open URL  "+this.originalURL.toString());
        }
        this.originalReader = new BufferedReader(new InputStreamReader(this.originalStream));
        this.originalFile = null;

        createCsvReader();
    }

    private void createCsvReader() {
        this.lineParser = instantiateLineParser();
        this.lineParser.setParserListener(this);

        this.csvReader = new CSVReaderBuilder<T>(this.originalReader).
                entryParser(this.lineParser).
                strategy(CSVStrategy.UK_DEFAULT).
                build();
        try {
            this.lineParser.initialiseColumnNames(this.csvReader.readHeader());
        } catch (IOException e) {
            throw new MIIOException("The CSV file does not have valid headers", e);
        }
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
     * <p>setCsvFileParserListener.</p>
     *
     * @param listener a {@link psidev.psi.mi.jami.crosslink.listener.CsvParserListener} object.
     */
    protected void setCsvFileParserListener(CsvParserListener listener) {
        this.parserListener = listener;
        this.defaultParserListener = listener;
    }

    /**
     * <p>setMIFileParserListener.</p>
     *
     * @param listener a {@link psidev.psi.mi.jami.listener.MIFileParserListener} object.
     */
    protected void setMIFileParserListener(MIFileParserListener listener) {
        if (listener instanceof CsvParserListener){
            setCsvFileParserListener((CsvParserListener) listener);
        }
        else{
            this.parserListener = null;
            this.defaultParserListener = listener;
        }
    }
}

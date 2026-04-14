package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.datasource.FeatureWriter;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.InteractionWriterOptions;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.tab.FeatureTabColumnName;
import psidev.psi.mi.jami.tab.extension.factory.options.MitabWriterOptions;
import psidev.psi.mi.jami.tab.io.writer.feeder.FeatureTabColumnFeeder;
import psidev.psi.mi.jami.tab.utils.MitabUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Abstract class for Feature writer.
 */
public abstract class AbstractFeatureTabWriter<F extends Feature, I extends InteractionEvidence> implements FeatureWriter<F, I> {

    private Writer writer;
    private boolean isInitialised = false;
    private boolean writeHeader = true;
    private FeatureTabColumnFeeder<F, I> columnFeeder;
    private boolean hasStarted;

    /**
     * <p>Constructor for AbstractFeatureTabWriter.</p>
     */
    public AbstractFeatureTabWriter(){
    }

    /**
     * <p>Constructor for AbstractFeatureTabWriter.</p>
     *
     * @param file a {@link File} object.
     * @throws IOException if any.
     */
    public AbstractFeatureTabWriter(File file) throws IOException {
        initialiseFile(file);
        isInitialised = true;
    }

    /**
     * <p>Constructor for AbstractFeatureTabWriter.</p>
     *
     * @param output a {@link OutputStream} object.
     */
    public AbstractFeatureTabWriter(OutputStream output) {
        initialiseOutputStream(output);
        isInitialised = true;
    }

    /**
     * <p>Constructor for AbstractFeatureTabWriter.</p>
     *
     * @param writer a {@link Writer} object.
     */
    public AbstractFeatureTabWriter(Writer writer) {
        initialiseWriter(writer);
        isInitialised = true;
    }

    /**
     * <p>isWriteHeader.</p>
     *
     * @return a boolean.
     */
    public boolean isWriteHeader() {
        return writeHeader;
    }

    /**
     * <p>Setter for the field <code>writeHeader</code>.</p>
     *
     * @param writeHeader a boolean.
     */
    public void setWriteHeader(boolean writeHeader) {
        this.writeHeader = writeHeader;
    }

    public void initialiseContext(Map<String, Object> options) {
        if (options == null && !isInitialised){
            throw new IllegalArgumentException("The options for the FeatureTab writer should contain at least " +
                    InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the features.");
        } else if (options == null){
            return;
        } else if (options.containsKey(InteractionWriterOptions.OUTPUT_OPTION_KEY)){
            Object output = options.get(InteractionWriterOptions.OUTPUT_OPTION_KEY);

            if (output instanceof File){
                try {
                    initialiseFile((File) output);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Impossible to open and write in output file " + ((File)output).getName(), e);
                }
            } else if (output instanceof OutputStream){
                initialiseOutputStream((OutputStream) output);
            } else if (output instanceof Writer){
                initialiseWriter((Writer) output);
            } else if (output instanceof String){
                // suspect a file path
                try {
                    initialiseFile(new File((String)output));
                } catch (IOException e) {
                    throw new IllegalArgumentException("Impossible to open and write in output file " + output, e);
                }
            } else {
                throw new IllegalArgumentException("Impossible to write in the provided output " +
                        output.getClass().getName() +
                        ", a File, OutputStream, Writer or file path was expected.");
            }
        } else if (!isInitialised){
            throw new IllegalArgumentException("The options for the FeatureTab writer should contain at least " +
                    InteractionWriterOptions.OUTPUT_OPTION_KEY +
                    " to know where to write the interactions.");
        }

        if (options.containsKey(MitabWriterOptions.MITAB_HEADER_OPTION)){
            setWriteHeader((Boolean)options.get(MitabWriterOptions.MITAB_HEADER_OPTION));
        }

        isInitialised = true;
    }

    /**
     * <p>end.</p>
     *
     * @throws MIIOException if any.
     */
    public void end() throws MIIOException {
        // nothing to do
        if (!isInitialised){
            throw new IllegalStateException("The FeatureTab writer was not initialised. The options for the FeatureTab writer should contain at least " +
                    InteractionWriterOptions.OUTPUT_OPTION_KEY +
                    " to know where to write the features.");
        }
    }

    /**
     * <p>start.</p>
     *
     * @throws MIIOException if any.
     */
    public void start() throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The FeatureTab writer was not initialised. " +
                    "The options for the FeatureTab should contain at least " +
                    InteractionWriterOptions.OUTPUT_OPTION_KEY +
                    " to know where to write the features.");
        }
        else if (writeHeader){
            try {
                writeHeader();
            } catch (IOException e) {
                throw new MIIOException("Impossible to write the FeatureTab header.", e);
            }
        }
        hasStarted = true;
    }

    /**
     * Writes a feature.
     *
     * @param feature a F object.
     * @throws MIIOException if any.
     */
    public void write(F feature) throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The FeatureTab writer was not initialised. " +
                    "The options for the FeatureTab should contain at least " +
                    InteractionWriterOptions.OUTPUT_OPTION_KEY +
                    " to know where to write the features.");
        }

        try{
            writeFeature(feature);
        } catch (IOException e) {
            throw new MIIOException("Impossible to write " + feature.toString(), e);
        }
    }

    /**
     * Writes a collection of features.
     *
     * @param features a {@link Collection} object.
     * @throws MIIOException if any.
     */
    public void write(Collection<? extends F> features) throws MIIOException {
        Iterator<? extends F> featureIterator = features.iterator();
        write(featureIterator);
    }

    /**
     * Writes a collection of features.
     *
     * @param features a {@link Collection} object.
     * @throws MIIOException if any.
     */
    public void write(Iterator<? extends F> features) throws MIIOException {
        while(features.hasNext()){
            write(features.next());
        }
    }

    /**
     * Writes the features of an interaction.
     *
     * @param interaction an I object.
     * @throws MIIOException if any.
     */
    public void write(I interaction) throws MIIOException {
        for (ParticipantEvidence participant : interaction.getParticipants()) {
            write(participant.getFeatures());
        }
    }

    /**
     * <p>flush.</p>
     *
     * @throws MIIOException if any.
     */
    public void flush() throws MIIOException{
        if (isInitialised){
            try {
                writer.flush();
            } catch (IOException e) {
                throw new MIIOException("Impossible to flush the FeatureTab writer", e);
            }
        }
    }

    /**
     * <p>close.</p>
     *
     * @throws MIIOException if any.
     */
    public void close() throws MIIOException{
        if (isInitialised){
            try {
                writer.flush();
            } catch (IOException e) {
                throw new MIIOException("Impossible to flush the FeatureTab writer", e);
            }
            finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new MIIOException("Impossible to close the FeatureTab writer", e);
                }
                finally {
                    isInitialised = false;
                    writer = null;
                    writeHeader = true;
                    columnFeeder = null;
                    hasStarted = false;
                }
            }
        }
    }
    /**
     * <p>reset.</p>
     *
     * @throws MIIOException if any.
     */
    public void reset() throws MIIOException{
        if (isInitialised){
            try {
                writer.flush();
            } catch (IOException e) {
                throw new MIIOException("Impossible to flush the FeatureTab writer", e);
            }
            finally {
                isInitialised = false;
                writer = null;
                writeHeader = true;
                columnFeeder = null;
                hasStarted = false;
            }
        }
    }

    /**
     * <p>Getter for the field <code>columnFeeder</code>.</p>
     *
     * @return a {@link FeatureTabColumnFeeder} object.
     */
    protected FeatureTabColumnFeeder<F, I> getColumnFeeder() {
        return columnFeeder;
    }

    /**
     * <p>Setter for the field <code>columnFeeder</code>.</p>
     *
     * @param columnFeeder a {@link FeatureTabColumnFeeder} object.
     */
    protected void setColumnFeeder(FeatureTabColumnFeeder<F, I> columnFeeder) {
        this.columnFeeder = columnFeeder;
    }

    /**
     * <p>initialiseColumnFeeder.</p>
     */
    protected abstract void initialiseColumnFeeder();

    /**
     * Writes the feature in FeatureTab format
     *
     * @param feature a F object.
     * @throws IOException if any.
     */
    protected void writeFeature(F feature) throws IOException {
        I interaction = (I) ((ParticipantEvidence) feature.getParticipant()).getInteraction();
        Experiment experiment = interaction.getExperiment();

        if (hasStarted){
            writer.write(MitabUtils.LINE_BREAK);
        } else {
            start();
        }

        this.columnFeeder.writeFeatureAc(feature);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeFeatureShortLabel(feature);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeRanges(feature);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeOriginalSequence(feature);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeResultingSequence(feature);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeFeatureType(feature);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeAnnotations(feature);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeAffectedProteinAc(feature);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeAffectedProteinSymbol(feature);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeAffectedProteinFullName(feature);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeAffectedProteinOrganism(feature);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeInteractionParticipants(interaction);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writePubMedId(experiment);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeFigureLegends(interaction);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeInteractionAc(interaction);
        writer.write(MitabUtils.COLUMN_SEPARATOR);
        this.columnFeeder.writeXrefIds(feature);
    }

    /**
     * <p>Getter for the field <code>writer</code>.</p>
     *
     * @return a {@link Writer} object.
     */
    protected Writer getWriter() {
        return writer;
    }

    /**
     * Write the header
     *
     * @throws IOException if any.
     */
    protected void writeHeader() throws IOException{
        writer.write(MitabUtils.COMMENT_PREFIX);
        writer.write(" ");

        for (FeatureTabColumnName colName : FeatureTabColumnName.values()) {
            writer.write(colName.toString());
            // starts with 0
            if (colName.ordinal() < FeatureTabColumnName.values().length - 1){
                writer.write(MitabUtils.COLUMN_SEPARATOR);
            }
        }
    }

    /**
     * <p>hasStarted.</p>
     *
     * @return a boolean.
     */
    protected boolean hasStarted() {
        return hasStarted;
    }

    /**
     * <p>setStarted.</p>
     *
     * @param start a boolean.
     */
    protected void setStarted(boolean start){
        this.hasStarted = start;
    }

    private void initialiseWriter(Writer writer) {
        if (writer == null){
            throw new IllegalArgumentException("The writer cannot be null.");
        }

        this.writer = writer;
        initialiseColumnFeeder();
    }

    private void initialiseOutputStream(OutputStream output) {
        if (output == null){
            throw new IllegalArgumentException("The output stream cannot be null.");
        }

        this.writer = new OutputStreamWriter(output);
        initialiseColumnFeeder();
    }

    private void initialiseFile(File file) throws IOException {
        if (file == null){
            throw new IllegalArgumentException("The file cannot be null.");
        }

        this.writer = new BufferedWriter(new FileWriter(file));
        initialiseColumnFeeder();
    }
}

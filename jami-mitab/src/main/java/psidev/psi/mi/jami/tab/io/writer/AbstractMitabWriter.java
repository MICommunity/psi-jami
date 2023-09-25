package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.datasource.InteractionWriter;
import psidev.psi.mi.jami.exception.ComplexExpansionException;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.InteractionWriterOptions;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.tab.MitabVersion;
import psidev.psi.mi.jami.tab.extension.factory.options.MitabWriterOptions;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Abstract writer for Mitab 2.5.
 *
 * The general options when calling method {@link #initialiseContext} are :
 *  - output_file_key : File. Specifies the file where to write
 *  - output_stream_key : OutputStream. Specifies the stream where to write
 *  - output_writer_key : Writer. Specifies the writer.
 *  If these three options are given, output_file_key will take priority, then output_stream_key an finally output_writer_key. At leats
 *  one of these options should be provided when initialising the context of the writer
 *  - complex_expansion_key : ComplexExpansionMethod. Specifies the ComplexExpansion object to use. By default, it is SpokeExpansion if nothing is specified
 *  - mitab_header_key : Boolean. Specifies if the writer should write the MITAB header when starting to write or not
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public abstract class AbstractMitabWriter<T extends Interaction, B extends BinaryInteraction, P extends Participant> implements InteractionWriter<T>{

    private ComplexExpansionMethod<T, B> expansionMethod;
    private AbstractMitab25BinaryWriter<B, P> binaryWriter;
    private boolean hasStarted;

    /**
     * <p>Constructor for AbstractMitabWriter.</p>
     */
    public AbstractMitabWriter(){

    }

    /**
     * <p>Constructor for AbstractMitabWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public AbstractMitabWriter(File file) throws IOException {

        initialiseFile(file);
        initialiseExpansionMethod(null);
    }

    /**
     * <p>Constructor for AbstractMitabWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public AbstractMitabWriter(OutputStream output) {

        initialiseOutputStream(output);
        initialiseExpansionMethod(null);
    }

    /**
     * <p>Constructor for AbstractMitabWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public AbstractMitabWriter(Writer writer) {

        initialiseWriter(writer);
        initialiseExpansionMethod(null);
    }

    /**
     * <p>Constructor for AbstractMitabWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public AbstractMitabWriter(File file, ComplexExpansionMethod<T, B> expansionMethod) throws IOException {

        initialiseFile(file);
        initialiseExpansionMethod(expansionMethod);
    }

    /**
     * <p>Constructor for AbstractMitabWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public AbstractMitabWriter(OutputStream output, ComplexExpansionMethod<T, B> expansionMethod) {

        initialiseOutputStream(output);
        initialiseExpansionMethod(expansionMethod);
    }

    /**
     * <p>Constructor for AbstractMitabWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public AbstractMitabWriter(Writer writer, ComplexExpansionMethod<T, B> expansionMethod) {

        initialiseWriter(writer);
        initialiseExpansionMethod(expansionMethod);
    }

    /** {@inheritDoc} */
    public void initialiseContext(Map<String, Object> options) {

        if (options == null && this.binaryWriter == null){
            throw new IllegalArgumentException("The options for the Mitab writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        else if (options.containsKey(InteractionWriterOptions.OUTPUT_OPTION_KEY)){
            Object output = options.get(InteractionWriterOptions.OUTPUT_OPTION_KEY);

            if (output instanceof File){
                try {
                    initialiseFile((File) output);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Impossible to open and write in output file " + ((File)output).getName(), e);
                }
            }
            else if (output instanceof OutputStream){
                initialiseOutputStream((OutputStream) output);
            }
            else if (output instanceof Writer){
                initialiseWriter((Writer) output);
            }
            // suspect a file path
            else if (output instanceof String){
                try {
                    initialiseFile(new File((String)output));
                } catch (IOException e) {
                    throw new IllegalArgumentException("Impossible to open and write in output file " + output, e);
                }
            }
            else {
                throw new IllegalArgumentException("Impossible to write in the provided output "+output.getClass().getName() + ", a File, OuputStream, Writer or file path was expected.");
            }
        }
        else if (this.binaryWriter == null){
            throw new IllegalArgumentException("The options for the Mitab writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }

        if (options.containsKey(MitabWriterOptions.MITAB_HEADER_OPTION)){
            setWriteHeader((Boolean)options.get(MitabWriterOptions.MITAB_HEADER_OPTION));
        }

        if (options.containsKey(InteractionWriterOptions.COMPLEX_EXPANSION_OPTION_KEY)){
            initialiseExpansionMethod((ComplexExpansionMethod<T,B>)options.get(InteractionWriterOptions.COMPLEX_EXPANSION_OPTION_KEY));
        }
        else {
            initialiseExpansionMethod(null);
        }
    }

    /**
     * <p>end.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void end() throws MIIOException {
        if (binaryWriter == null){
            throw new IllegalStateException("The mitab writer was not initialised. The options for the Mitab writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
    }

    /**
     * <p>start.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void start() throws MIIOException {
        if (binaryWriter == null){
            throw new IllegalStateException("The mitab writer was not initialised. The options for the Mitab writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.binaryWriter.start();
        hasStarted = true;
    }

    /**
     * <p>write.</p>
     *
     * @param interaction a T object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(T interaction) throws MIIOException {
        if (this.binaryWriter == null){
            throw new IllegalStateException("The mitab writer was not initialised. The options for the Mitab writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }

        try {
            this.binaryWriter.write(getExpansionMethod().expand(interaction));
        } catch (ComplexExpansionException e) {
            throw new MIIOException("Impossible to expand the n-ary interaction "+interaction.toString(), e);
        }
    }

    /**
     * <p>write.</p>
     *
     * @param interactions a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(Collection<? extends T> interactions) throws MIIOException {
        Iterator<? extends T> binaryIterator = interactions.iterator();
        write(binaryIterator);
    }

    /** {@inheritDoc} */
    public void write(Iterator<? extends T> interactions) throws MIIOException {
        while (interactions.hasNext()){
            write(interactions.next());
        }
    }

    /**
     * <p>flush.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void flush() throws MIIOException{
        if (this.binaryWriter != null){
            this.binaryWriter.flush();
        }
    }

    /**
     * <p>close.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void close() throws MIIOException{
        try{
            if (this.binaryWriter != null){
                this.binaryWriter.close();
            }
        }
        finally {
            this.binaryWriter = null;
            this.expansionMethod = null;
        }
    }

    /**
     * <p>reset.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void reset() throws MIIOException {
        try{
            if (this.binaryWriter != null){
                this.binaryWriter.reset();
            }
        }
        finally {
            this.binaryWriter = null;
            this.expansionMethod = null;
        }
    }

    /**
     * <p>getVersion.</p>
     *
     * @return a {@link psidev.psi.mi.jami.tab.MitabVersion} object.
     */
    public abstract MitabVersion getVersion();

    /**
     * <p>isWriteHeader.</p>
     *
     * @return a boolean.
     */
    public boolean isWriteHeader() {
        return binaryWriter != null ? binaryWriter.isWriteHeader():false;
    }

    /**
     * <p>setWriteHeader.</p>
     *
     * @param writeHeader a boolean.
     */
    public void setWriteHeader(boolean writeHeader) {
        if (this.binaryWriter != null){
            this.binaryWriter.setWriteHeader(writeHeader);
        }
    }

    /**
     * <p>initialiseExpansionMethod.</p>
     *
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    protected abstract void initialiseExpansionMethod(ComplexExpansionMethod<T, B> expansionMethod);

    /**
     * <p>initialiseWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    protected abstract void initialiseWriter(Writer writer);

    /**
     * <p>initialiseOutputStream.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    protected abstract void initialiseOutputStream(OutputStream output);

    /**
     * <p>initialiseFile.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    protected abstract void initialiseFile(File file) throws IOException;

    /**
     * <p>Setter for the field <code>expansionMethod</code>.</p>
     *
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    protected void setExpansionMethod(ComplexExpansionMethod<T, B> expansionMethod) {
        this.expansionMethod = expansionMethod;
    }

    /**
     * <p>Setter for the field <code>binaryWriter</code>.</p>
     *
     * @param binaryWriter a {@link psidev.psi.mi.jami.tab.io.writer.AbstractMitab25BinaryWriter} object.
     */
    protected void setBinaryWriter(AbstractMitab25BinaryWriter<B, P> binaryWriter) {
        this.binaryWriter = binaryWriter;
    }

    /**
     * <p>Getter for the field <code>binaryWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.tab.io.writer.AbstractMitab25BinaryWriter} object.
     */
    protected AbstractMitab25BinaryWriter<B, P> getBinaryWriter() {
        return binaryWriter;
    }

    /**
     * <p>Getter for the field <code>expansionMethod</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    protected ComplexExpansionMethod<T, B> getExpansionMethod() {
        if (expansionMethod == null){
            initialiseExpansionMethod(null);
        }
        return expansionMethod;
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
     * @param hasStarted a boolean.
     */
    protected void setStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }
}

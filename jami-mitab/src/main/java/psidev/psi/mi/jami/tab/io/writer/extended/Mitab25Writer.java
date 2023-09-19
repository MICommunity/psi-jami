package psidev.psi.mi.jami.tab.io.writer.extended;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.SpokeExpansion;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.InteractionWriterOptions;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.tab.MitabVersion;
import psidev.psi.mi.jami.tab.io.writer.AbstractMitabWriter;

import java.io.*;

/**
 * The simple MITAB 2.5 writer will write interactions using the JAMI interfaces.
 *
 * It will not check for MITAB extended objects (such as MitabAlias and DefaultMitabFeature).
 *
 * The default Complex expansion method is spoke expansion.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>10/06/13</pre>
 */
public class Mitab25Writer extends AbstractMitabWriter<Interaction, BinaryInteraction, Participant> {

    private Mitab25ModelledWriter modelledInteractionWriter;
    private Mitab25EvidenceWriter interactionEvidenceWriter;
    private Writer writer;

    /**
     * <p>Constructor for Mitab25Writer.</p>
     */
    public Mitab25Writer() {
        super();
    }

    /**
     * <p>Constructor for Mitab25Writer.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public Mitab25Writer(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for Mitab25Writer.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public Mitab25Writer(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for Mitab25Writer.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public Mitab25Writer(Writer writer) {
        super(writer);
    }

    /**
     * <p>Constructor for Mitab25Writer.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public Mitab25Writer(File file, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(file, expansionMethod);
    }

    /**
     * <p>Constructor for Mitab25Writer.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public Mitab25Writer(OutputStream output, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(output, expansionMethod);
    }

    /**
     * <p>Constructor for Mitab25Writer.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public Mitab25Writer(Writer writer, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(writer, expansionMethod);
    }

    /** {@inheritDoc} */
    @Override
    public MitabVersion getVersion() {
        return MitabVersion.v2_5;
    }

    /** {@inheritDoc} */
    @Override
    public void close() throws MIIOException {
        try{
            super.close();
        }
        finally {
            this.modelledInteractionWriter = null;
            this.interactionEvidenceWriter = null;
            this.writer = null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void reset() throws MIIOException {
        try{
            super.reset();
        }
        finally {
            this.modelledInteractionWriter = null;
            this.interactionEvidenceWriter = null;
            this.writer = null;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExpansionMethod(ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) {
        setExpansionMethod(expansionMethod != null ? expansionMethod : new SpokeExpansion());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseWriter(Writer writer) {
        this.writer = writer;
        setBinaryWriter(new psidev.psi.mi.jami.tab.io.writer.Mitab25BinaryWriter(this.writer));
        initialiseSubWriters();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseOutputStream(OutputStream output) {
        this.writer = new OutputStreamWriter(output);
        setBinaryWriter(new psidev.psi.mi.jami.tab.io.writer.Mitab25BinaryWriter(this.writer));
        initialiseSubWriters();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFile(File file) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(file));
        initialiseSubWriters();
    }

    /** {@inheritDoc} */
    @Override
    public void start() throws MIIOException {
        super.start();
    }

    /** {@inheritDoc} */
    @Override
    public void write(Interaction interaction) throws MIIOException {
        write(interaction, null);
    }

    /** {@inheritDoc} */
    @Override
    public void write(Interaction interaction, Double miScore) throws MIIOException {
        if (this.interactionEvidenceWriter == null || this.modelledInteractionWriter == null){
            throw new IllegalStateException("The Mitab writer has not been initialised. The options for the Mitab writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }

        boolean hasJustStarted = !hasStarted();
        if (hasJustStarted){
            start();
        }

        if (interaction instanceof InteractionEvidence){
            this.interactionEvidenceWriter.write((InteractionEvidence) interaction, miScore);
            if (hasJustStarted){
                this.modelledInteractionWriter.start();
            }
        }
        else if (interaction instanceof ModelledInteraction){
            this.modelledInteractionWriter.write((ModelledInteraction) interaction, miScore);
            if (hasJustStarted){
                this.interactionEvidenceWriter.start();
            }
        }
        else {
            super.write(interaction, miScore);
        }
    }

    /**
     * <p>initialiseSubWriters.</p>
     */
    protected void initialiseSubWriters() {
        setBinaryWriter(new Mitab25BinaryWriter(this.writer));
        this.modelledInteractionWriter = new Mitab25ModelledWriter(writer);
        this.modelledInteractionWriter.setWriteHeader(false);
        this.interactionEvidenceWriter = new Mitab25EvidenceWriter(writer);
        this.interactionEvidenceWriter.setWriteHeader(false);
    }

    /**
     * <p>Setter for the field <code>modelledInteractionWriter</code>.</p>
     *
     * @param modelledInteractionWriter a {@link psidev.psi.mi.jami.tab.io.writer.extended.Mitab25ModelledWriter} object.
     */
    protected void setModelledInteractionWriter(Mitab25ModelledWriter modelledInteractionWriter) {
        this.modelledInteractionWriter = modelledInteractionWriter;
        this.modelledInteractionWriter.setWriteHeader(false);
    }

    /**
     * <p>Setter for the field <code>interactionEvidenceWriter</code>.</p>
     *
     * @param interactionEvidenceWriter a {@link psidev.psi.mi.jami.tab.io.writer.extended.Mitab25EvidenceWriter} object.
     */
    protected void setInteractionEvidenceWriter(Mitab25EvidenceWriter interactionEvidenceWriter) {
        this.interactionEvidenceWriter = interactionEvidenceWriter;
        this.interactionEvidenceWriter.setWriteHeader(false);
    }

    /**
     * <p>Getter for the field <code>writer</code>.</p>
     *
     * @return a {@link java.io.Writer} object.
     */
    protected Writer getWriter() {
        return writer;
    }
}

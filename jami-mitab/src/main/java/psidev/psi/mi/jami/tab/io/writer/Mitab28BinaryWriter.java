package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.InteractionWriterOptions;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.tab.io.writer.feeder.DefaultMitabColumnFeeder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

/**
 * The basic Mitab 2.8 writer for BinaryInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class Mitab28BinaryWriter extends AbstractMitab28BinaryWriter<BinaryInteraction, Participant>{

    private AbstractMitab28BinaryWriter<ModelledBinaryInteraction, ModelledParticipant> modelledBinaryWriter;
    private AbstractMitab28BinaryWriter<BinaryInteractionEvidence, ParticipantEvidence> binaryEvidenceWriter;

    /**
     * <p>Constructor for Mitab28BinaryWriter.</p>
     */
    public Mitab28BinaryWriter() {
        super();
    }

    /**
     * <p>Constructor for Mitab28BinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws IOException if any.
     */
    public Mitab28BinaryWriter(File file) throws IOException {
        super(file);
        initialiseSubWritersWith(getWriter());
    }

    /**
     * <p>Constructor for Mitab28BinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public Mitab28BinaryWriter(OutputStream output) {
        super(output);
        initialiseSubWritersWith(getWriter());
    }

    /**
     * <p>Constructor for Mitab28BinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public Mitab28BinaryWriter(Writer writer) {
        super(writer);
        initialiseSubWritersWith(writer);
    }

    /** {@inheritDoc} */
    @Override
    public void initialiseContext(Map<String, Object> options) {
        super.initialiseContext(options);
        initialiseSubWritersWith(getWriter());
    }

    /** {@inheritDoc} */
    @Override
    public void start() throws MIIOException {
        super.start();
    }

    /** {@inheritDoc} */
    @Override
    public void close() throws MIIOException {
        try{
            super.close();
        }
        finally {
            this.modelledBinaryWriter = null;
            this.binaryEvidenceWriter = null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void reset() throws MIIOException {
        try{
            super.reset();
        }
        finally {
            this.modelledBinaryWriter = null;
            this.binaryEvidenceWriter = null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void write(BinaryInteraction interaction) throws MIIOException {
        if (this.binaryEvidenceWriter == null || this.modelledBinaryWriter == null){
            throw new IllegalStateException("The Mitab writer has not been initialised. The options for the Mitab writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }

        boolean hasJustStarted = !hasStarted();
        if (hasJustStarted){
            start();
        }

        if (interaction instanceof BinaryInteractionEvidence){
            this.binaryEvidenceWriter.write((BinaryInteractionEvidence) interaction);
            if (hasJustStarted){
                this.modelledBinaryWriter.start();
            }
        }
        else if (interaction instanceof ModelledBinaryInteraction){
            this.modelledBinaryWriter.write((ModelledBinaryInteraction) interaction);
            if (hasJustStarted){
                this.binaryEvidenceWriter.start();
            }
        }
        else {
            super.write(interaction);
        }
    }

    /**
     * <p>initialiseSubWritersWith.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    protected void initialiseSubWritersWith(Writer writer) {

        this.modelledBinaryWriter = new Mitab28ModelledBinaryWriter(writer);
        this.modelledBinaryWriter.setWriteHeader(isWriteHeader());
        this.binaryEvidenceWriter = new Mitab28BinaryEvidenceWriter(writer);
        this.binaryEvidenceWriter.setWriteHeader(isWriteHeader());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseColumnFeeder() {
        setColumnFeeder(new DefaultMitabColumnFeeder(getWriter()));
    }

    /** {@inheritDoc} */
    @Override
    public void setWriteHeader(boolean writeHeader) {
        super.setWriteHeader(writeHeader);
        if (this.modelledBinaryWriter != null){
            this.modelledBinaryWriter.setWriteHeader(false);
        }
        if (this.binaryEvidenceWriter != null){
            this.binaryEvidenceWriter.setWriteHeader(false);
        }
    }

    /**
     * <p>Setter for the field <code>modelledBinaryWriter</code>.</p>
     *
     * @param modelledBinaryWriter a {@link psidev.psi.mi.jami.tab.io.writer.AbstractMitab28BinaryWriter} object.
     */
    protected void setModelledBinaryWriter(AbstractMitab28BinaryWriter<ModelledBinaryInteraction, ModelledParticipant> modelledBinaryWriter) {
        this.modelledBinaryWriter = modelledBinaryWriter;
        this.modelledBinaryWriter.setWriteHeader(false);
    }

    /**
     * <p>Setter for the field <code>binaryEvidenceWriter</code>.</p>
     *
     * @param binaryEvidenceWriter a {@link psidev.psi.mi.jami.tab.io.writer.AbstractMitab28BinaryWriter} object.
     */
    protected void setBinaryEvidenceWriter(AbstractMitab28BinaryWriter<BinaryInteractionEvidence, ParticipantEvidence> binaryEvidenceWriter) {
        this.binaryEvidenceWriter = binaryEvidenceWriter;
        this.binaryEvidenceWriter.setWriteHeader(false);
    }
}

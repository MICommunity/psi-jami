package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.exception.DataSourceWriterException;
import psidev.psi.mi.jami.factory.InteractionWriterFactory;
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
 * The basic Mitab 2.7 writer for BinaryInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */

public class Mitab27BinaryWriter extends AbstractMitab27BinaryWriter<BinaryInteraction, Participant>{

    private AbstractMitab27BinaryWriter<ModelledBinaryInteraction, ModelledParticipant> modelledBinaryWriter;
    private AbstractMitab27BinaryWriter<BinaryInteractionEvidence, ParticipantEvidence> binaryEvidenceWriter;

    public Mitab27BinaryWriter() {
        super();
    }

    public Mitab27BinaryWriter(File file) throws IOException {
        super(file);
        initialiseSubWritersWith(getWriter());
    }

    public Mitab27BinaryWriter(OutputStream output) {
        super(output);
        initialiseSubWritersWith(getWriter());
    }

    public Mitab27BinaryWriter(Writer writer) {
        super(writer);
        initialiseSubWritersWith(writer);
    }

    @Override
    public void initialiseContext(Map<String, Object> options) {
        super.initialiseContext(options);
        initialiseSubWritersWith(getWriter());
    }

    @Override
    public void close() throws DataSourceWriterException {
        try{
            super.close();
        }
        finally {
            this.modelledBinaryWriter = null;
            this.binaryEvidenceWriter = null;
        }
    }

    @Override
    public void reset() throws DataSourceWriterException {
        try{
            super.reset();
        }
        finally {
            this.modelledBinaryWriter = null;
            this.binaryEvidenceWriter = null;
        }
    }

    @Override
    public void write(BinaryInteraction interaction) throws DataSourceWriterException {
        if (this.binaryEvidenceWriter == null || this.modelledBinaryWriter == null){
            throw new IllegalStateException("The Mitab writer has not been initialised. The options for the Mitab writer should contain at least "+ InteractionWriterFactory.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        // did not start yet so need to write the header if required
        else if (!hasWrittenHeader()){
            try{
                writeHeaderIfNotDone();
                this.binaryEvidenceWriter.setHasWrittenHeader(false);
                this.modelledBinaryWriter.setHasWrittenHeader(false);
            }
            catch (IOException e) {
                throw new DataSourceWriterException("Impossible to write MITAB header ", e);
            }
        }
        // we already started to write binary
        else{
            this.binaryEvidenceWriter.setHasWrittenHeader(true);
            this.modelledBinaryWriter.setHasWrittenHeader(true);
        }

        if (interaction instanceof BinaryInteractionEvidence){
            this.binaryEvidenceWriter.write((BinaryInteractionEvidence) interaction);
        }
        else if (interaction instanceof ModelledBinaryInteraction){
            this.modelledBinaryWriter.write((ModelledBinaryInteraction) interaction);
        }
        else {
            super.write(interaction);
        }
    }

    protected void initialiseSubWritersWith(Writer writer) {

        this.modelledBinaryWriter = new Mitab27ModelledBinaryWriter(writer);
        this.modelledBinaryWriter.setWriteHeader(isWriteHeader());
        this.binaryEvidenceWriter = new Mitab27BinaryEvidenceWriter(writer);
        this.binaryEvidenceWriter.setWriteHeader(isWriteHeader());
    }

    @Override
    protected void initialiseColumnFeeder() {
        setColumnFeeder(new DefaultMitabColumnFeeder(getWriter()));
    }

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

    protected void setModelledBinaryWriter(AbstractMitab27BinaryWriter<ModelledBinaryInteraction, ModelledParticipant> modelledBinaryWriter) {
        this.modelledBinaryWriter = modelledBinaryWriter;
        this.modelledBinaryWriter.setWriteHeader(false);
    }

    protected void setBinaryEvidenceWriter(AbstractMitab27BinaryWriter<BinaryInteractionEvidence, ParticipantEvidence> binaryEvidenceWriter) {
        this.binaryEvidenceWriter = binaryEvidenceWriter;
        this.binaryEvidenceWriter.setWriteHeader(false);
    }
}
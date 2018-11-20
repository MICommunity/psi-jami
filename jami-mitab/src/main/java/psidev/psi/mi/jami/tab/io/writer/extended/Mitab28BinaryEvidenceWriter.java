package psidev.psi.mi.jami.tab.io.writer.extended;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.tab.io.writer.AbstractMitab28BinaryWriter;
import psidev.psi.mi.jami.tab.io.writer.feeder.extended.ExtendedMitabInteractionEvidenceFeeder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Mitab 2.8 writer for binary interaction evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class Mitab28BinaryEvidenceWriter extends AbstractMitab28BinaryWriter<BinaryInteractionEvidence, ParticipantEvidence> {

    /**
     * <p>Constructor for Mitab28BinaryEvidenceWriter.</p>
     */
    public Mitab28BinaryEvidenceWriter() {
        super();
    }

    /**
     * <p>Constructor for Mitab28BinaryEvidenceWriter.</p>
     *
     * @param file a {@link File} object.
     * @throws IOException if any.
     */
    public Mitab28BinaryEvidenceWriter(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for Mitab28BinaryEvidenceWriter.</p>
     *
     * @param output a {@link OutputStream} object.
     */
    public Mitab28BinaryEvidenceWriter(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for Mitab28BinaryEvidenceWriter.</p>
     *
     * @param writer a {@link Writer} object.
     */
    public Mitab28BinaryEvidenceWriter(Writer writer) {
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseColumnFeeder() {
        setColumnFeeder(new ExtendedMitabInteractionEvidenceFeeder(getWriter()));
    }
}

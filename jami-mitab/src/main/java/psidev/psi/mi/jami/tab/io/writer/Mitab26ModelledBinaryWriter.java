package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.tab.io.writer.feeder.MitabModelledInteractionFeeder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Mitab 2.6 writer for modelled binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class Mitab26ModelledBinaryWriter extends AbstractMitab26BinaryWriter<ModelledBinaryInteraction, ModelledParticipant> {

    /**
     * <p>Constructor for Mitab26ModelledBinaryWriter.</p>
     */
    public Mitab26ModelledBinaryWriter() {
        super();
    }

    /**
     * <p>Constructor for Mitab26ModelledBinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public Mitab26ModelledBinaryWriter(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for Mitab26ModelledBinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public Mitab26ModelledBinaryWriter(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for Mitab26ModelledBinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public Mitab26ModelledBinaryWriter(Writer writer) {
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseColumnFeeder() {
        setColumnFeeder(new MitabModelledInteractionFeeder(getWriter()));
    }
}

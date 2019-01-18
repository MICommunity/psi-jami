package psidev.psi.mi.jami.tab.io.writer.extended;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.tab.io.writer.AbstractMitab28BinaryWriter;
import psidev.psi.mi.jami.tab.io.writer.feeder.extended.ExtendedMitabModelledInteractionFeeder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Mitab 2.8 writer for modelled binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class Mitab28ModelledBinaryWriter extends AbstractMitab28BinaryWriter<ModelledBinaryInteraction, ModelledParticipant> {

    /**
     * <p>Constructor for Mitab28ModelledBinaryWriter.</p>
     */
    public Mitab28ModelledBinaryWriter() {
        super();
    }

    /**
     * <p>Constructor for Mitab28ModelledBinaryWriter.</p>
     *
     * @param file a {@link File} object.
     * @throws IOException if any.
     */
    public Mitab28ModelledBinaryWriter(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for Mitab28ModelledBinaryWriter.</p>
     *
     * @param output a {@link OutputStream} object.
     */
    public Mitab28ModelledBinaryWriter(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for Mitab28ModelledBinaryWriter.</p>
     *
     * @param writer a {@link Writer} object.
     */
    public Mitab28ModelledBinaryWriter(Writer writer) {
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseColumnFeeder() {
        setColumnFeeder(new ExtendedMitabModelledInteractionFeeder(getWriter()));
    }
}

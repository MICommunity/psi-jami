package psidev.psi.mi.jami.tab.io.writer.extended;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.tab.io.writer.AbstractMitab25BinaryWriter;
import psidev.psi.mi.jami.tab.io.writer.feeder.extended.ExtendedMitabModelledInteractionFeeder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Mitab 2.5 writer for modelled binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/06/13</pre>
 */
public class Mitab25ModelledBinaryWriter extends AbstractMitab25BinaryWriter<ModelledBinaryInteraction, ModelledParticipant>{

    /**
     * <p>Constructor for Mitab25ModelledBinaryWriter.</p>
     */
    public Mitab25ModelledBinaryWriter() {
        super();
    }

    /**
     * <p>Constructor for Mitab25ModelledBinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public Mitab25ModelledBinaryWriter(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for Mitab25ModelledBinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public Mitab25ModelledBinaryWriter(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for Mitab25ModelledBinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public Mitab25ModelledBinaryWriter(Writer writer) {
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseColumnFeeder() {
        setColumnFeeder(new ExtendedMitabModelledInteractionFeeder(getWriter()));
    }
}

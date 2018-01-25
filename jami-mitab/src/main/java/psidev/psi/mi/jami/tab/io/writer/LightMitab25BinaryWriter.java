package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.tab.io.writer.feeder.DefaultMitabColumnFeeder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Mitab 2.5 writer for light binary interactions (no experimental details)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/06/13</pre>
 */
public class LightMitab25BinaryWriter extends AbstractMitab25BinaryWriter<BinaryInteraction, Participant>{

    /**
     * <p>Constructor for LightMitab25BinaryWriter.</p>
     */
    public LightMitab25BinaryWriter() {
        super();
    }

    /**
     * <p>Constructor for LightMitab25BinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public LightMitab25BinaryWriter(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for LightMitab25BinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public LightMitab25BinaryWriter(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for LightMitab25BinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public LightMitab25BinaryWriter(Writer writer) {
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseColumnFeeder() {
        setColumnFeeder(new DefaultMitabColumnFeeder(getWriter()));
    }
}

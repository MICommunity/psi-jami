package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.tab.io.writer.feeder.DefaultMitabColumnFeeder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Mitab 2.6 writer for light binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class LightMitab26BinaryWriter extends AbstractMitab26BinaryWriter<BinaryInteraction, Participant> {

    /**
     * <p>Constructor for LightMitab26BinaryWriter.</p>
     */
    public LightMitab26BinaryWriter() {
        super();
    }

    /**
     * <p>Constructor for LightMitab26BinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public LightMitab26BinaryWriter(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for LightMitab26BinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public LightMitab26BinaryWriter(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for LightMitab26BinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public LightMitab26BinaryWriter(Writer writer) {
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseColumnFeeder() {
        setColumnFeeder(new DefaultMitabColumnFeeder(getWriter()));
    }
}

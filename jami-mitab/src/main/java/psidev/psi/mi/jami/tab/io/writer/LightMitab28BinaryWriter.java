package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.tab.io.writer.feeder.DefaultMitabColumnFeeder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Mitab 2.8 writer for light binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class LightMitab28BinaryWriter extends AbstractMitab28BinaryWriter<BinaryInteraction, Participant> {

    /**
     * <p>Constructor for LightMitab28BinaryWriter.</p>
     */
    public LightMitab28BinaryWriter() {
        super();
    }

    /**
     * <p>Constructor for LightMitab28BinaryWriter.</p>
     *
     * @param file a {@link File} object.
     * @throws IOException if any.
     */
    public LightMitab28BinaryWriter(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for LightMitab28BinaryWriter.</p>
     *
     * @param output a {@link OutputStream} object.
     */
    public LightMitab28BinaryWriter(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for LightMitab28BinaryWriter.</p>
     *
     * @param writer a {@link Writer} object.
     */
    public LightMitab28BinaryWriter(Writer writer) {
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseColumnFeeder() {
        setColumnFeeder(new DefaultMitabColumnFeeder(getWriter()));
    }
}

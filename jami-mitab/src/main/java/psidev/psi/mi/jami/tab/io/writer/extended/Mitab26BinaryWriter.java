package psidev.psi.mi.jami.tab.io.writer.extended;

import psidev.psi.mi.jami.tab.io.writer.feeder.extended.DefaultExtendedMitabColumnFeeder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * The basic Mitab 2.6 writer for BinaryInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class Mitab26BinaryWriter extends psidev.psi.mi.jami.tab.io.writer.Mitab26BinaryWriter{

    /**
     * <p>Constructor for Mitab26BinaryWriter.</p>
     */
    public Mitab26BinaryWriter() {
        super();
    }

    /**
     * <p>Constructor for Mitab26BinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public Mitab26BinaryWriter(File file) throws IOException {
        super(file);
        initialiseSubWritersWith(getWriter());
    }

    /**
     * <p>Constructor for Mitab26BinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public Mitab26BinaryWriter(OutputStream output) {
        super(output);
        initialiseSubWritersWith(getWriter());
    }

    /**
     * <p>Constructor for Mitab26BinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public Mitab26BinaryWriter(Writer writer){
        super(writer);
        initialiseSubWritersWith(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseColumnFeeder() {
        setColumnFeeder(new DefaultExtendedMitabColumnFeeder(getWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWritersWith(Writer writer) {

        setModelledBinaryWriter(new Mitab26ModelledBinaryWriter(writer));
        setBinaryEvidenceWriter(new Mitab26BinaryEvidenceWriter(writer));
    }
}

package psidev.psi.mi.jami.tab.io.writer.extended;

import psidev.psi.mi.jami.tab.io.writer.feeder.extended.DefaultExtendedMitabColumnFeeder;

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
public class Mitab28BinaryWriter extends psidev.psi.mi.jami.tab.io.writer.Mitab28BinaryWriter{

    /**
     * <p>Constructor for Mitab28BinaryWriter.</p>
     */
    public Mitab28BinaryWriter() {
        super();
    }

    /**
     * <p>Constructor for Mitab28BinaryWriter.</p>
     *
     * @param file a {@link File} object.
     * @throws IOException if any.
     */
    public Mitab28BinaryWriter(File file) throws IOException {
        super(file);
        initialiseSubWritersWith(getWriter());
    }

    /**
     * <p>Constructor for Mitab28BinaryWriter.</p>
     *
     * @param output a {@link OutputStream} object.
     */
    public Mitab28BinaryWriter(OutputStream output){
        super(output);
        initialiseSubWritersWith(getWriter());
    }

    /**
     * <p>Constructor for Mitab28BinaryWriter.</p>
     *
     * @param writer a {@link Writer} object.
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
    protected void initialiseColumnFeeder() {
        setColumnFeeder(new DefaultExtendedMitabColumnFeeder(getWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWritersWith(Writer writer) {

        setModelledBinaryWriter(new Mitab28ModelledBinaryWriter(writer));
        setBinaryEvidenceWriter(new Mitab28BinaryEvidenceWriter(writer));
    }
}

package psidev.psi.mi.jami.tab.io.writer.extended;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.tab.MitabVersion;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Mitab 2.8 writer for Modelled interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class Mitab28ModelledWriter extends Mitab27ModelledWriter {

    /**
     * <p>Constructor for Mitab28ModelledWriter.</p>
     */
    public Mitab28ModelledWriter() {
        super();
    }

    /**
     * <p>Constructor for Mitab28ModelledWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws IOException if any.
     */
    public Mitab28ModelledWriter(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for Mitab28ModelledWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public Mitab28ModelledWriter(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for Mitab28ModelledWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public Mitab28ModelledWriter(Writer writer) {
        super(writer);
    }

    /**
     * <p>Constructor for Mitab28ModelledWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws IOException if any.
     */
    public Mitab28ModelledWriter(File file, ComplexExpansionMethod<ModelledInteraction, ModelledBinaryInteraction> expansionMethod) throws IOException {
        super(file, expansionMethod);
    }

    /**
     * <p>Constructor for Mitab28ModelledWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public Mitab28ModelledWriter(OutputStream output, ComplexExpansionMethod<ModelledInteraction, ModelledBinaryInteraction> expansionMethod) {
        super(output, expansionMethod);
    }

    /**
     * <p>Constructor for Mitab28ModelledWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public Mitab28ModelledWriter(Writer writer, ComplexExpansionMethod<ModelledInteraction, ModelledBinaryInteraction> expansionMethod) {
        super(writer, expansionMethod);
    }

    /** {@inheritDoc} */
    @Override
    public MitabVersion getVersion() {
        return MitabVersion.v2_8;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseWriter(Writer writer){
        setBinaryWriter(new Mitab28ModelledBinaryWriter(writer));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseOutputStream(OutputStream output) {
        setBinaryWriter(new Mitab28ModelledBinaryWriter(output));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFile(File file) throws IOException {
        setBinaryWriter(new Mitab28ModelledBinaryWriter(file));
    }
}

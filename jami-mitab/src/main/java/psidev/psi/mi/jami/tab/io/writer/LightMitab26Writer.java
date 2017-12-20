package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.tab.MitabVersion;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Mitab 2.6 writer for light interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class LightMitab26Writer extends LightMitab25Writer {

    /**
     * <p>Constructor for LightMitab26Writer.</p>
     */
    public LightMitab26Writer() {
        super();
    }

    /**
     * <p>Constructor for LightMitab26Writer.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public LightMitab26Writer(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for LightMitab26Writer.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public LightMitab26Writer(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for LightMitab26Writer.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public LightMitab26Writer(Writer writer) {
        super(writer);
    }

    /**
     * <p>Constructor for LightMitab26Writer.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public LightMitab26Writer(File file, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(file, expansionMethod);
    }

    /**
     * <p>Constructor for LightMitab26Writer.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public LightMitab26Writer(OutputStream output, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) {
        super(output, expansionMethod);
    }

    /**
     * <p>Constructor for LightMitab26Writer.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public LightMitab26Writer(Writer writer, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) {
        super(writer, expansionMethod);
    }

    /** {@inheritDoc} */
    @Override
    public MitabVersion getVersion() {
        return MitabVersion.v2_6;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseWriter(Writer writer){
        setBinaryWriter(new LightMitab26BinaryWriter(writer));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseOutputStream(OutputStream output) {
        setBinaryWriter(new LightMitab26BinaryWriter(output));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFile(File file) throws IOException {
        setBinaryWriter(new LightMitab26BinaryWriter(file));
    }
}

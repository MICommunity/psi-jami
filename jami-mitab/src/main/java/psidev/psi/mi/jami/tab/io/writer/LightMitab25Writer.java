package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.SpokeExpansion;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.tab.MitabVersion;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * The mitab 2.5 writer for light Interaction  (no experimental details)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class LightMitab25Writer extends AbstractMitabWriter<Interaction, BinaryInteraction, Participant> {

    /**
     * <p>Constructor for LightMitab25Writer.</p>
     */
    public LightMitab25Writer() {
        super();
    }

    /**
     * <p>Constructor for LightMitab25Writer.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public LightMitab25Writer(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for LightMitab25Writer.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public LightMitab25Writer(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for LightMitab25Writer.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public LightMitab25Writer(Writer writer) {
        super(writer);
    }

    /**
     * <p>Constructor for LightMitab25Writer.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public LightMitab25Writer(File file, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(file, expansionMethod);
    }

    /**
     * <p>Constructor for LightMitab25Writer.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public LightMitab25Writer(OutputStream output, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) {
        super(output, expansionMethod);
    }

    /**
     * <p>Constructor for LightMitab25Writer.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public LightMitab25Writer(Writer writer, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) {
        super(writer, expansionMethod);
    }

    /** {@inheritDoc} */
    @Override
    public MitabVersion getVersion() {
        return MitabVersion.v2_5;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExpansionMethod(ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) {
        setExpansionMethod(expansionMethod != null ? expansionMethod : new SpokeExpansion());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseWriter(Writer writer){
        setBinaryWriter(new LightMitab25BinaryWriter(writer));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseOutputStream(OutputStream output) {
        setBinaryWriter(new LightMitab25BinaryWriter(output));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFile(File file) throws IOException {
        setBinaryWriter(new LightMitab25BinaryWriter(file));
    }
}

package psidev.psi.mi.jami.tab.io.writer.extended;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.tab.MitabVersion;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * The simple MITAB 2.7 writer will write interactions using the JAMI interfaces.
 *
 * It will not check for MITAB extended objects (such as MitabAlias and DefaultMitabFeature).
 *
 * The default Complex expansion method is spoke expansion.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/06/13</pre>
 */
public class Mitab27Writer extends Mitab26Writer {

    /**
     * <p>Constructor for Mitab27Writer.</p>
     */
    public Mitab27Writer() {
        super();
    }

    /**
     * <p>Constructor for Mitab27Writer.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public Mitab27Writer(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for Mitab27Writer.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public Mitab27Writer(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for Mitab27Writer.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public Mitab27Writer(Writer writer) {
        super(writer);
    }

    /**
     * <p>Constructor for Mitab27Writer.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public Mitab27Writer(File file, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(file, expansionMethod);
    }

    /**
     * <p>Constructor for Mitab27Writer.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public Mitab27Writer(OutputStream output, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(output, expansionMethod);
    }

    /**
     * <p>Constructor for Mitab27Writer.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public Mitab27Writer(Writer writer, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(writer, expansionMethod);
    }

    /** {@inheritDoc} */
    @Override
    public MitabVersion getVersion() {
        return MitabVersion.v2_7;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWriters() {
        setBinaryWriter(new Mitab27BinaryWriter(getWriter()));
        setModelledInteractionWriter(new Mitab27ModelledWriter(getWriter()));
        setInteractionEvidenceWriter(new Mitab27EvidenceWriter(getWriter()));
    }
}

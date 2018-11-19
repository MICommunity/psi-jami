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
 * The simple MITAB 2.8 writer will write interactions using the JAMI interfaces.
 *
 * It will not check for MITAB extended objects (such as MitabAlias and DefaultMitabFeature).
 *
 * The default Complex expansion method is spoke expansion.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/06/13</pre>
 */
public class Mitab28Writer extends Mitab27Writer {

    /**
     * <p>Constructor for Mitab28Writer.</p>
     */
    public Mitab28Writer() {
        super();
    }

    /**
     * <p>Constructor for Mitab28Writer.</p>
     *
     * @param file a {@link File} object.
     * @throws IOException if any.
     */
    public Mitab28Writer(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for Mitab28Writer.</p>
     *
     * @param output a {@link OutputStream} object.
     */
    public Mitab28Writer(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for Mitab28Writer.</p>
     *
     * @param writer a {@link Writer} object.
     */
    public Mitab28Writer(Writer writer) {
        super(writer);
    }

    /**
     * <p>Constructor for Mitab28Writer.</p>
     *
     * @param file a {@link File} object.
     * @param expansionMethod a {@link ComplexExpansionMethod} object.
     * @throws IOException if any.
     */
    public Mitab28Writer(File file, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(file, expansionMethod);
    }

    /**
     * <p>Constructor for Mitab28Writer.</p>
     *
     * @param output a {@link OutputStream} object.
     * @param expansionMethod a {@link ComplexExpansionMethod} object.
     * @throws IOException if any.
     */
    public Mitab28Writer(OutputStream output, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(output, expansionMethod);
    }

    /**
     * <p>Constructor for Mitab28Writer.</p>
     *
     * @param writer a {@link Writer} object.
     * @param expansionMethod a {@link ComplexExpansionMethod} object.
     * @throws IOException if any.
     */
    public Mitab28Writer(Writer writer, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(writer, expansionMethod);
    }

    /** {@inheritDoc} */
    @Override
    public MitabVersion getVersion() {
        return MitabVersion.v2_8;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWriters() {
        setBinaryWriter(new Mitab28BinaryWriter(getWriter()));
        setModelledInteractionWriter(new Mitab28ModelledWriter(getWriter()));
        setInteractionEvidenceWriter(new Mitab28EvidenceWriter(getWriter()));
    }
}

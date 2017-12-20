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
 * The simple MITAB 2.6 writer will write interactions using the JAMI interfaces.
 *
 * It will not check for MITAB extended objects (such as MitabAlias and DefaultMitabFeature).
 *
 * The default Complex expansion method is spoke expansion.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/06/13</pre>
 */
public class Mitab26Writer extends Mitab25Writer {

    /**
     * <p>Constructor for Mitab26Writer.</p>
     */
    public Mitab26Writer() {
        super();
    }

    /**
     * <p>Constructor for Mitab26Writer.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public Mitab26Writer(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for Mitab26Writer.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public Mitab26Writer(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for Mitab26Writer.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public Mitab26Writer(Writer writer) {
        super(writer);
    }

    /**
     * <p>Constructor for Mitab26Writer.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public Mitab26Writer(File file, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(file, expansionMethod);
    }

    /**
     * <p>Constructor for Mitab26Writer.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public Mitab26Writer(OutputStream output, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(output, expansionMethod);    //To change body of overridden methods use File | Settings | File Templates.
    }

    /**
     * <p>Constructor for Mitab26Writer.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public Mitab26Writer(Writer writer, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(writer, expansionMethod);
    }

    /** {@inheritDoc} */
    @Override
    public MitabVersion getVersion() {
        return MitabVersion.v2_6;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWriters() {
        setBinaryWriter(new Mitab26BinaryWriter(getWriter()));
        setModelledInteractionWriter(new Mitab26ModelledWriter(getWriter()));
        setInteractionEvidenceWriter(new Mitab26EvidenceWriter(getWriter()));
    }
}

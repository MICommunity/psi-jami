package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.tab.MitabVersion;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Mitab 2.8 writer for interaction evidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class Mitab28EvidenceWriter extends Mitab27EvidenceWriter {

    /**
     * <p>Constructor for Mitab28EvidenceWriter.</p>
     */
    public Mitab28EvidenceWriter() {
    super();
}

    /**
     * <p>Constructor for Mitab28EvidenceWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws IOException if any.
     */
    public Mitab28EvidenceWriter(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for Mitab28EvidenceWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public Mitab28EvidenceWriter(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for Mitab28EvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public Mitab28EvidenceWriter(Writer writer) {
        super(writer);
    }

    /**
     * <p>Constructor for Mitab28EvidenceWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public Mitab28EvidenceWriter(OutputStream output, ComplexExpansionMethod<InteractionEvidence, BinaryInteractionEvidence> expansionMethod) {
        super(output, expansionMethod);
    }

    /**
     * <p>Constructor for Mitab28EvidenceWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws IOException if any.
     */
    public Mitab28EvidenceWriter(File file, ComplexExpansionMethod<InteractionEvidence, BinaryInteractionEvidence> expansionMethod) throws IOException {
        super(file, expansionMethod);
    }

    /**
     * <p>Constructor for Mitab28EvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public Mitab28EvidenceWriter(Writer writer, ComplexExpansionMethod<InteractionEvidence, BinaryInteractionEvidence> expansionMethod) {
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
        setBinaryWriter(new Mitab28BinaryEvidenceWriter(writer));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseOutputStream(OutputStream output) {
        setBinaryWriter(new Mitab28BinaryEvidenceWriter(output));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFile(File file) throws IOException {
        setBinaryWriter(new Mitab28BinaryEvidenceWriter(file));
    }
}

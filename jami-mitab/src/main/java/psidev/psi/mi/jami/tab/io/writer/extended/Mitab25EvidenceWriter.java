package psidev.psi.mi.jami.tab.io.writer.extended;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.InteractionEvidenceSpokeExpansion;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.tab.MitabVersion;
import psidev.psi.mi.jami.tab.io.writer.AbstractMitabWriter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * The mitab 2.5 writer for interaction evidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class Mitab25EvidenceWriter extends AbstractMitabWriter<InteractionEvidence, BinaryInteractionEvidence, ParticipantEvidence> {

    /**
     * <p>Constructor for Mitab25EvidenceWriter.</p>
     */
    public Mitab25EvidenceWriter() {
        super();
    }

    /**
     * <p>Constructor for Mitab25EvidenceWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public Mitab25EvidenceWriter(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for Mitab25EvidenceWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public Mitab25EvidenceWriter(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for Mitab25EvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public Mitab25EvidenceWriter(Writer writer) {
        super(writer);
    }

    /**
     * <p>Constructor for Mitab25EvidenceWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public Mitab25EvidenceWriter(OutputStream output, ComplexExpansionMethod<InteractionEvidence, BinaryInteractionEvidence> expansionMethod) {
        super(output, expansionMethod);
    }

    /**
     * <p>Constructor for Mitab25EvidenceWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public Mitab25EvidenceWriter(File file, ComplexExpansionMethod<InteractionEvidence, BinaryInteractionEvidence> expansionMethod) throws IOException {
        super(file, expansionMethod);
    }

    /**
     * <p>Constructor for Mitab25EvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public Mitab25EvidenceWriter(Writer writer, ComplexExpansionMethod<InteractionEvidence, BinaryInteractionEvidence> expansionMethod) {
        super(writer, expansionMethod);
    }

    /** {@inheritDoc} */
    @Override
    public MitabVersion getVersion() {
        return MitabVersion.v2_5;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExpansionMethod(ComplexExpansionMethod<InteractionEvidence, BinaryInteractionEvidence> expansionMethod) {
        setExpansionMethod(expansionMethod != null ? expansionMethod : new InteractionEvidenceSpokeExpansion());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseWriter(Writer writer) {
        setBinaryWriter(new Mitab25BinaryEvidenceWriter(writer));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseOutputStream(OutputStream output) {
        setBinaryWriter(new Mitab25BinaryEvidenceWriter(output));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFile(File file) throws IOException {
        setBinaryWriter(new Mitab25BinaryEvidenceWriter(file));
    }
}

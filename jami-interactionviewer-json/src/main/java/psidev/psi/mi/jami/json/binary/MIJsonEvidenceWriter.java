package psidev.psi.mi.jami.json.binary;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.InteractionEvidenceSpokeExpansion;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.model.InteractionEvidence;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * The jsonWriter which writes the interaction evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>05/07/13</pre>
 */
public class MIJsonEvidenceWriter extends AbstractMIJsonWriter<InteractionEvidence,BinaryInteractionEvidence> {


    /**
     * <p>Constructor for MIJsonEvidenceWriter.</p>
     */
    public MIJsonEvidenceWriter(){
        super();
    }

    /**
     * <p>Constructor for MIJsonEvidenceWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @throws java.io.IOException if any.
     */
    public MIJsonEvidenceWriter(File file, OntologyTermFetcher fetcher) throws IOException {
        super(file, fetcher);
    }

    /**
     * <p>Constructor for MIJsonEvidenceWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public MIJsonEvidenceWriter(OutputStream output, OntologyTermFetcher fetcher) {
        super(output, fetcher);
    }

    /**
     * <p>Constructor for MIJsonEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public MIJsonEvidenceWriter(Writer writer, OntologyTermFetcher fetcher) {
        super(writer, fetcher);
    }

    /**
     * <p>Constructor for MIJsonEvidenceWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public MIJsonEvidenceWriter(File file, OntologyTermFetcher fetcher, ComplexExpansionMethod<InteractionEvidence, BinaryInteractionEvidence> expansionMethod) throws IOException {
        super(file, fetcher, expansionMethod);
    }

    /**
     * <p>Constructor for MIJsonEvidenceWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public MIJsonEvidenceWriter(OutputStream output, OntologyTermFetcher fetcher, ComplexExpansionMethod<InteractionEvidence, BinaryInteractionEvidence> expansionMethod) {
        super(output, fetcher, expansionMethod);
    }

    /**
     * <p>Constructor for MIJsonEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public MIJsonEvidenceWriter(Writer writer, OntologyTermFetcher fetcher, ComplexExpansionMethod<InteractionEvidence, BinaryInteractionEvidence> expansionMethod) {
        super(writer, fetcher, expansionMethod);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultExpansionMethod() {
        super.setExpansionMethod(new InteractionEvidenceSpokeExpansion());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseBinaryWriter(File file, OntologyTermFetcher fetcher) throws IOException {
        super.setBinaryWriter(new MIJsonBinaryEvidenceWriter(file, fetcher));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseBinaryWriter(OutputStream output, OntologyTermFetcher fetcher) {
        super.setBinaryWriter(new MIJsonBinaryEvidenceWriter(output, fetcher));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseBinaryWriter(Writer writer, OntologyTermFetcher fetcher) {
        super.setBinaryWriter(new MIJsonBinaryEvidenceWriter(writer, fetcher));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultBinaryWriter() {
        super.setBinaryWriter(new MIJsonBinaryEvidenceWriter());

    }
}

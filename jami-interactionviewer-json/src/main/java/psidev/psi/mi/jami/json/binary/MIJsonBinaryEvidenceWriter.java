package psidev.psi.mi.jami.json.binary;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.json.IncrementalIdGenerator;
import psidev.psi.mi.jami.json.binary.elements.SimpleJsonBinaryInteractionEvidenceWriter;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

/**
 * JSON writer for InteractionEvidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>03/07/13</pre>
 */
public class MIJsonBinaryEvidenceWriter extends AbstractMIJsonBinaryWriter<BinaryInteractionEvidence>{

    /**
     * <p>Constructor for MIJsonBinaryEvidenceWriter.</p>
     */
    public MIJsonBinaryEvidenceWriter() {
        super();
    }

    /**
     * <p>Constructor for MIJsonBinaryEvidenceWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @throws java.io.IOException if any.
     */
    public MIJsonBinaryEvidenceWriter(File file, OntologyTermFetcher fetcher) throws IOException {
        super(file, fetcher);
    }

    /**
     * <p>Constructor for MIJsonBinaryEvidenceWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public MIJsonBinaryEvidenceWriter(OutputStream output, OntologyTermFetcher fetcher) {
        super(output, fetcher);
    }

    /**
     * <p>Constructor for MIJsonBinaryEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public MIJsonBinaryEvidenceWriter(Writer writer, OntologyTermFetcher fetcher) {
        super(writer, fetcher);
    }

    /**
     * <p>Constructor for MIJsonBinaryEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public MIJsonBinaryEvidenceWriter(Writer writer, OntologyTermFetcher fetcher, Map<String, String> processedInteractors, Map<Feature, Integer> processedFeatures, Map<Entity, Integer> processedParticipants, IncrementalIdGenerator idGenerator) {
        super(writer, fetcher, processedInteractors, processedFeatures, processedParticipants, idGenerator);
    }

    /**
     * <p>Constructor for MIJsonBinaryEvidenceWriter.</p>
     *
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public MIJsonBinaryEvidenceWriter(Map<String, String> processedInteractors, Map<Feature, Integer> processedFeatures, Map<Entity, Integer> processedParticipants, IncrementalIdGenerator idGenerator) {
        super(processedInteractors, processedFeatures, processedParticipants, idGenerator);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInteractionWriter() {
        super.setInteractionWriter(new SimpleJsonBinaryInteractionEvidenceWriter(getWriter(), getProcessedFeatures(),
                getProcessedInteractors(), getProcessedParticipants(), getIdGenerator()));
        if (getExpansionId() != null){
            ((SimpleJsonBinaryInteractionEvidenceWriter)getInteractionWriter()).setExpansionId(getExpansionId());
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initExpansionMethodInteractionWriter(Integer expansionId) {
        ((SimpleJsonBinaryInteractionEvidenceWriter) getInteractionWriter()).setExpansionId(expansionId);
    }
}

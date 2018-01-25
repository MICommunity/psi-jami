package psidev.psi.mi.jami.json.binary;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.json.IncrementalIdGenerator;
import psidev.psi.mi.jami.json.binary.elements.SimpleJsonBinaryInteractionWriter;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

/**
 * JSON writer for light interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>03/07/13</pre>
 */
public class LightMIJsonBinaryWriter extends AbstractMIJsonBinaryWriter<BinaryInteraction> {

    /**
     * <p>Constructor for LightMIJsonBinaryWriter.</p>
     */
    public LightMIJsonBinaryWriter() {
    }

    /**
     * <p>Constructor for LightMIJsonBinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @throws java.io.IOException if any.
     */
    public LightMIJsonBinaryWriter(File file, OntologyTermFetcher fetcher) throws IOException {
        super(file, fetcher);
    }

    /**
     * <p>Constructor for LightMIJsonBinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public LightMIJsonBinaryWriter(OutputStream output, OntologyTermFetcher fetcher) {
        super(output, fetcher);
    }

    /**
     * <p>Constructor for LightMIJsonBinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public LightMIJsonBinaryWriter(Writer writer, OntologyTermFetcher fetcher) {
        super(writer, fetcher);
    }

    /**
     * <p>Constructor for LightMIJsonBinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public LightMIJsonBinaryWriter(Writer writer, OntologyTermFetcher fetcher, Map<String, String> processedInteractors,
                                   Map<Feature, Integer> processedFeatures, Map<Entity, Integer> processedParticipants,
                                   IncrementalIdGenerator idGenerator) {
        super(writer, fetcher, processedInteractors, processedFeatures, processedParticipants, idGenerator);
    }

    /**
     * <p>Constructor for LightMIJsonBinaryWriter.</p>
     *
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public LightMIJsonBinaryWriter(Map<String, String> processedInteractors, Map<Feature, Integer> processedFeatures,
                                   Map<Entity, Integer> processedParticipants, IncrementalIdGenerator idGenerator) {
        super(processedInteractors, processedFeatures, processedParticipants, idGenerator);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInteractionWriter() {
        super.setInteractionWriter(new SimpleJsonBinaryInteractionWriter<BinaryInteraction>(getWriter(), getProcessedFeatures(),
                getProcessedInteractors(), getProcessedParticipants(), getIdGenerator()));
        if (getExpansionId() != null){
            ((SimpleJsonBinaryInteractionWriter)getInteractionWriter()).setExpansionId(getExpansionId());
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initExpansionMethodInteractionWriter(Integer expansionId) {
        ((SimpleJsonBinaryInteractionWriter) getInteractionWriter()).setExpansionId(expansionId);
    }
}

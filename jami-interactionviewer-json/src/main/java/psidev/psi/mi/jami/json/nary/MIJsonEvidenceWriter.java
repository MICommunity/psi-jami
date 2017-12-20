package psidev.psi.mi.jami.json.nary;

import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.json.IncrementalIdGenerator;
import psidev.psi.mi.jami.json.elements.SimpleJsonInteractionEvidenceWriter;
import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.InteractionEvidence;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

/**
 * Abstract JSON writer for interactions (n-ary json format)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>03/07/13</pre>
 */
public class MIJsonEvidenceWriter extends AbstractMIJsonWriter<InteractionEvidence> {

    private MIJsonModelledWriter complexWriter;

    /**
     * <p>Constructor for MIJsonEvidenceWriter.</p>
     */
    public MIJsonEvidenceWriter(){
        super();
        this.complexWriter = new MIJsonModelledWriter(getProcessedInteractors(), getProcessedFeatures(), getProcessedParticipants(), getIdGenerator());
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
        this.complexWriter = new MIJsonModelledWriter(getWriter(), fetcher,
                getProcessedInteractors(), getProcessedFeatures(), getProcessedParticipants(), getIdGenerator());
    }

    /**
     * <p>Constructor for MIJsonEvidenceWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public MIJsonEvidenceWriter(OutputStream output, OntologyTermFetcher fetcher) {

        super(output, fetcher);
        this.complexWriter = new MIJsonModelledWriter(getWriter(), fetcher,
                getProcessedInteractors(), getProcessedFeatures(), getProcessedParticipants(), getIdGenerator());
    }

    /**
     * <p>Constructor for MIJsonEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public MIJsonEvidenceWriter(Writer writer, OntologyTermFetcher fetcher) {

        super(writer, fetcher);
        this.complexWriter = new MIJsonModelledWriter(writer, fetcher,
                getProcessedInteractors(), getProcessedFeatures(), getProcessedParticipants(), getIdGenerator());
    }

    /**
     * <p>Constructor for MIJsonEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public MIJsonEvidenceWriter(Writer writer, OntologyTermFetcher fetcher, Map<String, String> processedInteractors,
                                Map<Feature, Integer> processedFeatures, Map<Entity, Integer> processedParticipants, IncrementalIdGenerator idGenerator) {
        super(writer, fetcher, processedInteractors, processedFeatures, processedParticipants, idGenerator);
        this.complexWriter = new MIJsonModelledWriter(writer, fetcher,
                getProcessedInteractors(), getProcessedFeatures(), getProcessedParticipants(), getIdGenerator());
    }

    /**
     * <p>Constructor for MIJsonEvidenceWriter.</p>
     *
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public MIJsonEvidenceWriter(Map<String, String> processedInteractors, Map<Feature, Integer> processedFeatures,
                                Map<Entity, Integer> processedParticipants, IncrementalIdGenerator idGenerator) {
        super(processedInteractors, processedFeatures, processedParticipants, idGenerator);
        this.complexWriter = new MIJsonModelledWriter(getWriter(), getFetcher(),
                getProcessedInteractors(), getProcessedFeatures(), getProcessedParticipants(), getIdGenerator());
    }

    /**
     * <p>flush.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void flush() throws MIIOException {
        if (complexWriter != null){
            complexWriter.flush();
        }
    }

    /**
     * <p>close.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void close() throws MIIOException {
        try{
            if (complexWriter != null){
                complexWriter.close();
            }
        }
        finally {
            complexWriter = null;
        }
    }

    /**
     * <p>reset.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void reset() throws MIIOException {
        try{
            if (complexWriter != null){
                complexWriter.reset();
            }
        }
        finally {
            complexWriter = null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void initialiseContext(Map<String, Object> options) {
        super.initialiseContext(options);
        this.complexWriter = new MIJsonModelledWriter(getWriter(), getFetcher(), getProcessedInteractors(), getProcessedFeatures(),
                getProcessedParticipants(), getIdGenerator());
    }

    /** {@inheritDoc} */
    @Override
    protected void writeComplex(Complex complex) {
        this.complexWriter.write(complex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInteractionWriter() {
        super.setInteractionWriter(new SimpleJsonInteractionEvidenceWriter<InteractionEvidence>(getWriter(), getProcessedFeatures(),
                getProcessedInteractors(), getProcessedParticipants(), getIdGenerator()));
        ((SimpleJsonInteractionEvidenceWriter<InteractionEvidence>)getInteractionWriter()).setFetcher(getFetcher());
    }
}

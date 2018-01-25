package psidev.psi.mi.jami.json.binary;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.json.IncrementalIdGenerator;
import psidev.psi.mi.jami.json.nary.MIJsonModelledWriter;
import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

/**
 * Abstract JSON writer for binary interactions (binary json format)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>03/07/13</pre>
 */
public abstract class AbstractMIJsonBinaryWriter<I extends BinaryInteraction> extends psidev.psi.mi.jami.json.nary.AbstractMIJsonWriter<I> {

    private Integer expansionId;
    private psidev.psi.mi.jami.json.nary.MIJsonModelledWriter complexWriter;

    /**
     * <p>Constructor for AbstractMIJsonBinaryWriter.</p>
     */
    public AbstractMIJsonBinaryWriter() {
    }

    /**
     * <p>Constructor for AbstractMIJsonBinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @throws java.io.IOException if any.
     */
    public AbstractMIJsonBinaryWriter(File file, OntologyTermFetcher fetcher) throws IOException {
        super(file, fetcher);
        this.complexWriter = new MIJsonModelledWriter(getWriter(), fetcher, getProcessedInteractors(), getProcessedFeatures(), getProcessedParticipants(),
                getIdGenerator());
    }

    /**
     * <p>Constructor for AbstractMIJsonBinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public AbstractMIJsonBinaryWriter(OutputStream output, OntologyTermFetcher fetcher) {
        super(output, fetcher);
        this.complexWriter = new MIJsonModelledWriter(getWriter(), fetcher, getProcessedInteractors(), getProcessedFeatures(), getProcessedParticipants(),
                getIdGenerator());
    }

    /**
     * <p>Constructor for AbstractMIJsonBinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public AbstractMIJsonBinaryWriter(Writer writer, OntologyTermFetcher fetcher) {
        super(writer, fetcher);
        this.complexWriter = new MIJsonModelledWriter(getWriter(), fetcher, getProcessedInteractors(), getProcessedFeatures(), getProcessedParticipants(),
                getIdGenerator());
    }

    /**
     * <p>Constructor for AbstractMIJsonBinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    protected AbstractMIJsonBinaryWriter(Writer writer, OntologyTermFetcher fetcher, Map<String, String> processedInteractors,
                                         Map<Feature, Integer> processedFeatures, Map<Entity, Integer> processedParticipants,
                                         IncrementalIdGenerator idGenerator) {
        super(writer, fetcher, processedInteractors, processedFeatures, processedParticipants, idGenerator);
        this.complexWriter = new MIJsonModelledWriter(getWriter(), fetcher, getProcessedInteractors(), getProcessedFeatures(), getProcessedParticipants(),
                getIdGenerator());
    }

    /**
     * <p>Constructor for AbstractMIJsonBinaryWriter.</p>
     *
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    protected AbstractMIJsonBinaryWriter(Map<String, String> processedInteractors, Map<Feature, Integer> processedFeatures,
                                         Map<Entity, Integer> processedParticipants, IncrementalIdGenerator idGenerator) {
        super(processedInteractors, processedFeatures, processedParticipants, idGenerator);
        this.complexWriter = new MIJsonModelledWriter(getWriter(), getFetcher(), getProcessedInteractors(), getProcessedFeatures(),
                getProcessedParticipants(),
                getIdGenerator());
    }

    /**
     * <p>close.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void close() throws MIIOException{
        expansionId = null;
        if(this.complexWriter != null){
           this.complexWriter.clear();
        }
        super.close();
    }

    /**
     * <p>reset.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void reset() throws MIIOException {
        expansionId = null;
        if(this.complexWriter != null){
            this.complexWriter.clear();
        }
        super.reset();
    }

    /** {@inheritDoc} */
    @Override
    public void initialiseContext(Map<String, Object> options) {
        super.initialiseContext(options);
        this.complexWriter = new MIJsonModelledWriter(getWriter(), getFetcher(), getProcessedInteractors(), getProcessedFeatures(),
                getProcessedParticipants(),
                getIdGenerator());
    }

    /** {@inheritDoc} */
    @Override
    public void flush() throws MIIOException {
        super.flush();
    }

    /**
     * <p>Setter for the field <code>expansionId</code>.</p>
     *
     * @param expansionId a {@link java.lang.Integer} object.
     */
    public void setExpansionId(Integer expansionId) {
        this.expansionId = expansionId;
        initExpansionMethodInteractionWriter(expansionId);
    }

    /**
     * <p>initExpansionMethodInteractionWriter.</p>
     *
     * @param expansionId a {@link java.lang.Integer} object.
     */
    protected abstract void initExpansionMethodInteractionWriter(Integer expansionId);

    /**
     * <p>Getter for the field <code>expansionId</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    protected Integer getExpansionId() {
        return expansionId;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeComplex(Complex complex) {
        this.complexWriter.write(complex);
    }
}

package psidev.psi.mi.jami.json.nary;

import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.json.IncrementalIdGenerator;
import psidev.psi.mi.jami.json.elements.SimpleJsonModelledInteractionWriter;
import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.ModelledInteraction;

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
public class MIJsonModelledWriter extends AbstractMIJsonWriter<ModelledInteraction> {

    /**
     * <p>Constructor for MIJsonModelledWriter.</p>
     */
    public MIJsonModelledWriter(){
        super();
    }

    /**
     * <p>Constructor for MIJsonModelledWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @throws java.io.IOException if any.
     */
    public MIJsonModelledWriter(File file, OntologyTermFetcher fetcher) throws IOException {

        super(file, fetcher);
    }

    /**
     * <p>Constructor for MIJsonModelledWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public MIJsonModelledWriter(OutputStream output, OntologyTermFetcher fetcher) {

        super(output, fetcher);
    }

    /**
     * <p>Constructor for MIJsonModelledWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public MIJsonModelledWriter(Writer writer, OntologyTermFetcher fetcher) {

        super(writer, fetcher);
    }

    /**
     * <p>Constructor for MIJsonModelledWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public MIJsonModelledWriter(Writer writer, OntologyTermFetcher fetcher, Map<String, String> processedInteractors,
                                Map<Feature, Integer> processedFeatures, Map<Entity, Integer> processedParticipants, IncrementalIdGenerator idGenerator) {
        super(writer, fetcher, processedInteractors, processedFeatures, processedParticipants, idGenerator);
    }

    /**
     * <p>Constructor for MIJsonModelledWriter.</p>
     *
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public MIJsonModelledWriter(Map<String, String> processedInteractors, Map<Feature, Integer> processedFeatures,
                                Map<Entity, Integer> processedParticipants, IncrementalIdGenerator idGenerator) {
        super(processedInteractors, processedFeatures, processedParticipants, idGenerator);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeComplex(Complex complex) {
        write(complex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInteractionWriter() {
        super.setInteractionWriter(new SimpleJsonModelledInteractionWriter<ModelledInteraction>(getWriter(), getProcessedFeatures(), getProcessedInteractors(),
                getProcessedParticipants(), getIdGenerator()));
        ((SimpleJsonModelledInteractionWriter<ModelledInteraction>)getInteractionWriter()).setFetcher(getFetcher());
    }

}

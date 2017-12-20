package psidev.psi.mi.jami.json.elements;

import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.json.IncrementalIdGenerator;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.ModelledParticipant;

import java.io.Writer;
import java.util.Map;

/**
 * Json writer for modelled participants
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/14</pre>
 */
public class SimpleJsonModelledParticipantWriter extends SimpleJsonParticipantWriter<ModelledParticipant>{

    /**
     * <p>Constructor for SimpleJsonModelledParticipantWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     */
    public SimpleJsonModelledParticipantWriter(Writer writer, Map<Feature, Integer> processedFeatures,
                                               Map<String, String> processedInteractors, Map<Entity, Integer> processedParticipants) {
        super(writer, processedFeatures, processedInteractors, processedParticipants);
    }

    /**
     * <p>Constructor for SimpleJsonModelledParticipantWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public SimpleJsonModelledParticipantWriter(Writer writer, Map<Feature, Integer> processedFeatures,
                                               Map<String, String> processedInteractors, Map<Entity, Integer> processedParticipants,
                                               IncrementalIdGenerator idGenerator, OntologyTermFetcher fetcher) {
        super(writer, processedFeatures, processedInteractors, processedParticipants, idGenerator, fetcher);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultFeatureWriter() {
        super.setFeatureWriter(new SimpleJsonModelledFeatureWriter(getWriter(), getProcessedFeatures(), getProcessedInteractors(),
                getProcessedParticipants(), getIdGenerator(), getFetcher()));
        ((SimpleJsonModelledFeatureWriter)getFeatureWriter()).setCvWriter(getCvWriter());
    }
}

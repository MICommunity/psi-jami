package psidev.psi.mi.jami.json.elements;

import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.json.MIJsonUtils;
import psidev.psi.mi.jami.json.IncrementalIdGenerator;
import psidev.psi.mi.jami.model.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

/**
 * Json writer for modelled features
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/14</pre>
 */
public class SimpleJsonFeatureEvidenceWriter extends SimpleJsonFeatureWriter<FeatureEvidence>{

    private JsonElementWriter<Parameter> parameterWriter;

    /**
     * <p>Constructor for SimpleJsonFeatureEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     */
    public SimpleJsonFeatureEvidenceWriter(Writer writer, Map<Feature, Integer> processedFeatures, Map<String, String> processedInteractors,
                                           Map<Entity, Integer> processedParticipants) {
        super(writer, processedFeatures, processedInteractors, processedParticipants);
    }

    /**
     * <p>Constructor for SimpleJsonFeatureEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public SimpleJsonFeatureEvidenceWriter(Writer writer, Map<Feature, Integer> processedFeatures, Map<String, String> processedInteractors,
                                           Map<Entity, Integer> processedParticipants, IncrementalIdGenerator idGenerator,
                                           OntologyTermFetcher fetcher) {
        super(writer, processedFeatures, processedInteractors, processedParticipants, idGenerator,
                fetcher);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherProperties(FeatureEvidence object) throws IOException {
        // detection methods
        if (!object.getDetectionMethods().isEmpty()){
            MIJsonUtils.writeSeparator(getWriter());
            MIJsonUtils.writePropertyKey("detmethods", getWriter());
            MIJsonUtils.writeOpenArray(getWriter());

            Iterator<CvTerm> methodIterator = object.getDetectionMethods().iterator();
            while (methodIterator.hasNext()){
                getCvWriter().write(methodIterator.next());
                if (methodIterator.hasNext()){
                    MIJsonUtils.writeSeparator(getWriter());
                }
            }

            MIJsonUtils.writeEndArray(getWriter());
        }

        // parameters
        if (!object.getParameters().isEmpty()){
            MIJsonUtils.writeSeparator(getWriter());
            MIJsonUtils.writePropertyKey("parameters", getWriter());
            MIJsonUtils.writeOpenArray(getWriter());

            Iterator<Parameter> paramIterator = object.getParameters().iterator();
            while (paramIterator.hasNext()){
                getParameterWriter().write(paramIterator.next());
                if (paramIterator.hasNext()){
                    MIJsonUtils.writeSeparator(getWriter());
                }
            }

            MIJsonUtils.writeEndArray(getWriter());
        }
    }

    /**
     * <p>Getter for the field <code>parameterWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public JsonElementWriter<Parameter> getParameterWriter() {
        if (this.parameterWriter == null){
           this.parameterWriter = new SimpleJsonParameterWriter(getWriter());
        }
        return parameterWriter;
    }

    /**
     * <p>Setter for the field <code>parameterWriter</code>.</p>
     *
     * @param parameterWriter a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public void setParameterWriter(JsonElementWriter<Parameter> parameterWriter) {
        this.parameterWriter = parameterWriter;
    }
}

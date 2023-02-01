package psidev.psi.mi.jami.json.elements;

import psidev.psi.mi.jami.json.MIJsonUtils;
import psidev.psi.mi.jami.json.IncrementalIdGenerator;
import psidev.psi.mi.jami.model.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

/**
 * Json writer for modelled interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/14</pre>
 */
public class SimpleJsonModelledInteractionWriter<I extends ModelledInteraction> extends SimpleJsonInteractionWriter<I>{

    private JsonElementWriter<Organism> organismWriter;
    private JsonElementWriter<Confidence> confidenceWriter;
    private JsonElementWriter<Parameter> parameterWriter;

    /**
     * <p>Constructor for SimpleJsonModelledInteractionWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     */
    public SimpleJsonModelledInteractionWriter(Writer writer, Map<Feature, Integer> processedFeatures,
                                               Map<String, String> processedInteractors, Map<Entity, Integer> processedParticipants) {
        super(writer, processedFeatures, processedInteractors, processedParticipants);
    }

    /**
     * <p>Constructor for SimpleJsonModelledInteractionWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public SimpleJsonModelledInteractionWriter(Writer writer, Map<Feature, Integer> processedFeatures,
                                               Map<String, String> processedInteractors, Map<Entity, Integer> processedParticipants,
                                               IncrementalIdGenerator idGenerator) {
        super(writer, processedFeatures, processedInteractors, processedParticipants, idGenerator);
    }

    /**
     * <p>writeOtherProperties.</p>
     *
     * @param object a I object.
     * @throws java.io.IOException if any.
     */
    protected void writeOtherProperties(I object) throws IOException {

        if (object instanceof Complex) {
            Complex complex = (Complex) object;
            MIJsonUtils.writeSeparator(getWriter());
            MIJsonUtils.writePropertyKey("complexType", getWriter());
            getCvWriter().write(complex.getInteractorType());

            if (complex.getEvidenceType() != null) {
                MIJsonUtils.writeSeparator(getWriter());
                MIJsonUtils.writePropertyKey("evidenceType", getWriter());
                getCvWriter().write(complex.getEvidenceType());
            }

            if (complex.getOrganism() != null) {
                MIJsonUtils.writeSeparator(getWriter());
                MIJsonUtils.writePropertyKey("organism", getWriter());
                getOrganismWriter().write(complex.getOrganism());
            }
        }

        // confidences
        if (!object.getModelledConfidences().isEmpty()) {
            MIJsonUtils.writeSeparator(getWriter());
            MIJsonUtils.writePropertyKey("confidences", getWriter());
            MIJsonUtils.writeOpenArray(getWriter());

            Iterator<ModelledConfidence> confIterator = object.getModelledConfidences().iterator();
            while (confIterator.hasNext()) {
                getConfidenceWriter().write(confIterator.next());
                if (confIterator.hasNext()) {
                    MIJsonUtils.writeSeparator(getWriter());
                }
            }

            MIJsonUtils.writeEndArray(getWriter());
        }
        // parameters
        if (!object.getModelledParameters().isEmpty()) {
            MIJsonUtils.writeSeparator(getWriter());
            MIJsonUtils.writePropertyKey("parameters", getWriter());
            MIJsonUtils.writeOpenArray(getWriter());

            Iterator<ModelledParameter> paramIterator = object.getModelledParameters().iterator();
            while (paramIterator.hasNext()) {
                getParameterWriter().write(paramIterator.next());
                if (paramIterator.hasNext()) {
                    MIJsonUtils.writeSeparator(getWriter());
                }
            }

            MIJsonUtils.writeEndArray(getWriter());
        }
    }

    /**
     * <p>initialiseDefaultParticipantWriter.</p>
     */
    protected void initialiseDefaultParticipantWriter() {
        super.setParticipantWriter(new SimpleJsonModelledParticipantWriter(getWriter(), getProcessedFeatures(), getProcessedInteractors(),
                getProcessedParticipants(), getIdGenerator(), getFetcher()));
        ((SimpleJsonModelledParticipantWriter)getParticipantWriter()).setCvWriter(getCvWriter());
    }

    /**
     * <p>Getter for the field <code>organismWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public JsonElementWriter<Organism> getOrganismWriter() {
        if (this.organismWriter == null){
           this.organismWriter = new SimpleJsonOrganismWriter(getWriter());
        }
        return organismWriter;
    }

    /**
     * <p>Setter for the field <code>organismWriter</code>.</p>
     *
     * @param organismWriter a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public void setOrganismWriter(JsonElementWriter<Organism> organismWriter) {
        this.organismWriter = organismWriter;
    }

    /**
     * <p>Getter for the field <code>confidenceWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public JsonElementWriter<Confidence> getConfidenceWriter() {
        if (this.confidenceWriter == null){
            this.confidenceWriter = new SimpleJsonConfidenceWriter(getWriter());
        }
        return confidenceWriter;
    }

    /**
     * <p>Setter for the field <code>confidenceWriter</code>.</p>
     *
     * @param confidenceWriter a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public void setConfidenceWriter(JsonElementWriter<Confidence> confidenceWriter) {
        this.confidenceWriter = confidenceWriter;
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

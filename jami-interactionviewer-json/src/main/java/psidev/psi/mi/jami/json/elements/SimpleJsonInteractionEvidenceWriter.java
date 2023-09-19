package psidev.psi.mi.jami.json.elements;

import psidev.psi.mi.jami.json.MIJsonUtils;
import psidev.psi.mi.jami.json.IncrementalIdGenerator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultConfidence;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.utils.XrefUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Json writer for interaction evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/14</pre>
 */
public class SimpleJsonInteractionEvidenceWriter<I extends InteractionEvidence> extends SimpleJsonInteractionWriter<I>{

    private JsonElementWriter<InteractionEvidence> experimentWriter;
    private JsonElementWriter<Confidence> confidenceWriter;
    private JsonElementWriter<Parameter> parameterWriter;

    /**
     * <p>Constructor for SimpleJsonInteractionEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     */
    public SimpleJsonInteractionEvidenceWriter(Writer writer, Map<Feature, Integer> processedFeatures,
                                               Map<String, String> processedInteractors, Map<Entity, Integer> processedParticipants) {
        super(writer, processedFeatures, processedInteractors, processedParticipants);
    }

    /**
     * <p>Constructor for SimpleJsonInteractionEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public SimpleJsonInteractionEvidenceWriter(Writer writer, Map<Feature, Integer> processedFeatures,
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
    protected void writeOtherProperties(I object, Double miScore) throws IOException {

        // write experiment
        getExperimentWriter().write(object);

        // confidences
        if (!object.getConfidences().isEmpty() || miScore != null) {
            MIJsonUtils.writeSeparator(getWriter());
            MIJsonUtils.writePropertyKey("confidences", getWriter());
            MIJsonUtils.writeOpenArray(getWriter());

            if (miScore != null) {
                getConfidenceWriter().write(new DefaultConfidence(new DefaultCvTerm("intact-miscore"), Double.toString(miScore)));
                if (!object.getConfidences().isEmpty()) {
                    MIJsonUtils.writeSeparator(getWriter());
                }
            }

            Iterator<Confidence> confIterator = object.getConfidences().iterator();
            while (confIterator.hasNext()) {
                getConfidenceWriter().write(confIterator.next());
                if (confIterator.hasNext()) {
                    MIJsonUtils.writeSeparator(getWriter());
                }
            }

            MIJsonUtils.writeEndArray(getWriter());
        }
        // parameters
        if (!object.getParameters().isEmpty()) {
            MIJsonUtils.writeSeparator(getWriter());
            MIJsonUtils.writePropertyKey("parameters", getWriter());
            MIJsonUtils.writeOpenArray(getWriter());

            Iterator<Parameter> paramIterator = object.getParameters().iterator();
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
        super.setParticipantWriter(new SimpleJsonParticipantEvidenceWriter(getWriter(), getProcessedFeatures(), getProcessedInteractors(),
                getProcessedParticipants(), getIdGenerator(), getFetcher()));
        ((SimpleJsonParticipantEvidenceWriter)getParticipantWriter()).setCvWriter(getCvWriter());
    }

    /**
     * <p>Getter for the field <code>experimentWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public JsonElementWriter<InteractionEvidence> getExperimentWriter() {
        if (experimentWriter == null){
            experimentWriter = new SimpleJsonExperimentWriter(getWriter());
            ((SimpleJsonExperimentWriter)experimentWriter).setCvWriter(getCvWriter());
        }
        return experimentWriter;
    }

    /**
     * <p>Setter for the field <code>experimentWriter</code>.</p>
     *
     * @param experimentWriter a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public void setExperimentWriter(JsonElementWriter<InteractionEvidence> experimentWriter) {
        this.experimentWriter = experimentWriter;
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

    /** {@inheritDoc} */
    @Override
    protected void writeAllIdentifiers(I object) throws IOException {
        super.writeAllIdentifiers(object);
        if (object.getImexId() != null && object.getIdentifiers().isEmpty()){
            Collection<Xref> imexIds = XrefUtils.collectAllXrefsHavingDatabaseAndId(object.getXrefs(), Xref.IMEX_MI, Xref.IMEX, object.getImexId());
            if (!imexIds.isEmpty()){
                MIJsonUtils.writeSeparator(getWriter());
                getIdentifierWriter().write(imexIds.iterator().next());
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherIdentifiers(I object) throws IOException {
        if (object.getImexId() != null){
            Collection<Xref> imexIds = XrefUtils.collectAllXrefsHavingDatabaseAndId(object.getXrefs(), Xref.IMEX_MI, Xref.IMEX, object.getImexId());
            if (!imexIds.isEmpty()){
                MIJsonUtils.writeSeparator(getWriter());
                getIdentifierWriter().write(imexIds.iterator().next());
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    protected boolean hasIdentifiers(I object) {
        return super.hasIdentifiers(object) || object.getImexId() != null;
    }
}

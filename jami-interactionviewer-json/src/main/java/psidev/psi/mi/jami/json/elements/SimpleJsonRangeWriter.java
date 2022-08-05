package psidev.psi.mi.jami.json.elements;

import org.json.simple.JSONValue;
import psidev.psi.mi.jami.json.MIJsonUtils;
import psidev.psi.mi.jami.json.IncrementalIdGenerator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.RangeUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Json writer for ranges
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/14</pre>
 */
public class SimpleJsonRangeWriter implements JsonRangeWriter{

    private Writer writer;
    private Map<String, String> processedInteractors;
    private Map<Entity, Integer> processedParticipants;
    private IncrementalIdGenerator idGenerator;

    /**
     * <p>Constructor for SimpleJsonRangeWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     */
    public SimpleJsonRangeWriter(Writer writer, Map<String, String> processedInteractors,
                                 Map<Entity, Integer> processedParticipants){
        if (writer == null){
            throw new IllegalArgumentException("The json range writer needs a non null Writer");
        }
        this.writer = writer;
        if (processedInteractors == null){
            throw new IllegalArgumentException("The json range writer needs a non null map of processed interactors");
        }
        this.processedInteractors = processedInteractors;
        if (processedParticipants == null){
            throw new IllegalArgumentException("The json range writer needs a non null map of processed participants");
        }
        this.processedParticipants = processedParticipants;
    }

    /**
     * <p>Constructor for SimpleJsonRangeWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public SimpleJsonRangeWriter(Writer writer, Map<String, String> processedInteractors,
                                 Map<Entity, Integer> processedParticipants, IncrementalIdGenerator idGenerator){
        this(writer, processedInteractors, processedParticipants);
        this.idGenerator = idGenerator;
    }

    /**
     * <p>write.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Range} object.
     * @throws java.io.IOException if any.
     */
    public void write(Range object) throws IOException {
        write(object, null);
    }

    /** {@inheritDoc} */
    public void write(Range object, Feature parent) throws IOException {
        if (object == null) return;
        // start object
        MIJsonUtils.writeStartObject(writer);
        // position
        MIJsonUtils.writeProperty("pos", RangeUtils.convertRangeToString(object), writer);
        // resulting sequence
        if (object.getResultingSequence() != null && object.getResultingSequence().getNewSequence() != null){
            MIJsonUtils.writeSeparator(writer);
            MIJsonUtils.writeProperty("resultingSequence", JSONValue.escape(object.getResultingSequence().getNewSequence()), writer);
        }

        Interactor interactor = null;
        Entity participant = null;
        if (object.getParticipant() != null && !(object.getParticipant().getInteractor() instanceof Complex)){
            interactor = object.getParticipant().getInteractor();
            participant = object.getParticipant();
        }
        // This case branch takes into account too the cases that the feature hasn't selected a member of the complex participant. To avoid the viewer to
        // fail it will point to the whole complex cover the case
        else if (object.getParticipant() == null &&
                parent != null && parent.getParticipant() != null){
            interactor = parent.getParticipant().getInteractor();
            participant = parent.getParticipant();
        }
        if (interactor != null){
            String[] extractedInteractorId =  MIJsonUtils.extractInteractorId(interactor.getPreferredIdentifier(), interactor);
            String key = extractedInteractorId[0]+"_"+extractedInteractorId[1];

            if (!this.processedInteractors.containsKey(key)){
                this.processedInteractors.put(key, key);
            }
            MIJsonUtils.writeSeparator(writer);
            MIJsonUtils.writeProperty("interactorRef", this.processedInteractors.get(key), writer);
        }
        if (participant != null){
            int id = 0;
            if (this.processedParticipants.containsKey(participant)){
                id = this.processedParticipants.get(participant);
            }
            else{
                id = getIdGenerator().nextId();
                this.processedParticipants.put(participant, id);
            }
            MIJsonUtils.writeSeparator(writer);
            MIJsonUtils.writeProperty("participantRef", Integer.toString(id), writer);
        }
        // end object
        MIJsonUtils.writeEndObject(writer);

    }

    /**
     * <p>Getter for the field <code>idGenerator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public IncrementalIdGenerator getIdGenerator() {
        if (idGenerator == null){
           idGenerator = new IncrementalIdGenerator();
        }
        return idGenerator;
    }

    /**
     * <p>Setter for the field <code>idGenerator</code>.</p>
     *
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public void setIdGenerator(IncrementalIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
}

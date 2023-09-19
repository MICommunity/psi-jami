package psidev.psi.mi.jami.json.elements;

import org.json.simple.JSONValue;
import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.json.MIJsonUtils;
import psidev.psi.mi.jami.json.IncrementalIdGenerator;
import psidev.psi.mi.jami.model.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

/**
 * Json writer for interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/14</pre>
 */
public class SimpleJsonInteractionWriter<I extends Interaction> implements JsonElementWriter<I>{

    private Writer writer;
    private JsonElementWriter<CvTerm> cvWriter;
    private JsonElementWriter<Xref> identifierWriter;
    private JsonElementWriter participantWriter;
    private Map<Feature, Integer> processedFeatures;
    private Map<Entity, Integer> processedParticipants;
    private Map<String, String> processedInteractors;
    private IncrementalIdGenerator idGenerator;
    private OntologyTermFetcher fetcher;

    /**
     * <p>Constructor for SimpleJsonInteractionWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     */
    public SimpleJsonInteractionWriter(Writer writer, Map<Feature, Integer> processedFeatures,
                                       Map<String, String> processedInteractors, Map<Entity, Integer> processedParticipants){
        if (writer == null){
            throw new IllegalArgumentException("The json interactions writer needs a non null Writer");
        }
        this.writer = writer;
        if (processedFeatures == null){
            throw new IllegalArgumentException("The json interactions writer needs a non null map of processed features");
        }
        this.processedFeatures = processedFeatures;
        if (processedInteractors == null){
            throw new IllegalArgumentException("The json interactions writer needs a non null map of processed interactors");
        }
        this.processedInteractors = processedInteractors;
        if (processedParticipants == null){
            throw new IllegalArgumentException("The json interactions writer needs a non null map of processed participants");
        }
        this.processedParticipants = processedParticipants;
    }

    /**
     * <p>Constructor for SimpleJsonInteractionWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param processedFeatures a {@link java.util.Map} object.
     * @param processedInteractors a {@link java.util.Map} object.
     * @param processedParticipants a {@link java.util.Map} object.
     * @param idGenerator a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public SimpleJsonInteractionWriter(Writer writer, Map<Feature, Integer> processedFeatures,
                                       Map<String, String> processedInteractors, Map<Entity, Integer> processedParticipants,
                                       IncrementalIdGenerator idGenerator){
        this(writer, processedFeatures, processedInteractors, processedParticipants);
        this.idGenerator = idGenerator;
    }

    /**
     * <p>write.</p>
     *
     * @param object a I object.
     * @throws java.io.IOException if any.
     */
    public void write(I object) throws IOException {
        write(object, null);
    }

    /**
     * <p>write.</p>
     *
     * @param object a I object.
     * @param miScore : the MI score of the interaction to write
     * @throws java.io.IOException if any.
     */
    public void write(I object, Double miScore) throws IOException {
        Xref preferredIdentifier;

        if(object instanceof Complex){
            preferredIdentifier = ((Complex) object).getPreferredIdentifier();
        }
        else {
            preferredIdentifier = !object.getIdentifiers().isEmpty() ? (Xref)object.getIdentifiers().iterator().next() : null;
        }

        String[] keyValues = generateInteractionIdentifier(object, preferredIdentifier);
        String id = null;

        // if the interaction has not yet been processed, we write the interactor
        if (!processedInteractors.containsKey(keyValues[0]+"_"+keyValues[1])){
            // when the interactor is not the first one, we write an element separator
            if (!processedInteractors.isEmpty()){
                MIJsonUtils.writeSeparator(writer);
            }
            id = keyValues[0]+"_"+keyValues[1];
            this.processedInteractors.put(keyValues[0]+"_"+keyValues[1], keyValues[0]+"_"+keyValues[1]);

            MIJsonUtils.writeStartObject(writer);
            MIJsonUtils.writeProperty("object","interaction", writer);
            // write accession
            MIJsonUtils.writeSeparator(writer);
            MIJsonUtils.writeProperty("id", id, writer);

            // then interaction type
            if (object.getInteractionType() != null){
                MIJsonUtils.writeSeparator(writer);
                MIJsonUtils.writePropertyKey("interactionType", writer);
                getCvWriter().write(object.getInteractionType());
            }

            writeOtherProperties(object, miScore);

            // then interaction identifiers
            if (hasIdentifiers(object)){
                MIJsonUtils.writeSeparator(writer);
                MIJsonUtils.writePropertyKey("identifiers", writer);
                writeAllIdentifiers(object);
            }

            // expansion method if necessary
            if (object instanceof BinaryInteraction){
                BinaryInteraction binary = (BinaryInteraction)object;
                if (binary.getComplexExpansion() != null){
                    MIJsonUtils.writeSeparator(writer);
                    MIJsonUtils.writePropertyKey("expansion", writer);
                    writeExpansionMethod(binary.getComplexExpansion());
                }
            }

            // then participant A and B
            MIJsonUtils.writeSeparator(writer);
            MIJsonUtils.writePropertyKey("participants", writer);
            MIJsonUtils.writeOpenArray(writer);

            Iterator<Participant> participantIterator = object.getParticipants().iterator();
            while (participantIterator.hasNext()){
                getParticipantWriter().write(participantIterator.next());
                if (participantIterator.hasNext()){
                    MIJsonUtils.writeSeparator(writer);
                }
            }
            MIJsonUtils.writeEndArray(writer);

            MIJsonUtils.writeEndObject(writer);

        }
    }

    /**
     * <p>generateInteractionIdentifier.</p>
     *
     * @param object a I object.
     * @param preferredIdentifier a {@link psidev.psi.mi.jami.model.Xref} object.
     * @return an array of {@link java.lang.String} objects.
     */
    protected String[] generateInteractionIdentifier(I object, Xref preferredIdentifier) {
        return MIJsonUtils.extractInteractionId(preferredIdentifier, object);
    }

    /**
     * <p>writeExpansionMethod.</p>
     *
     * @param expansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @throws java.io.IOException if any.
     */
    protected void writeExpansionMethod(CvTerm expansion) throws IOException {
        MIJsonUtils.writeStartObject(writer);
        MIJsonUtils.writeProperty("name", JSONValue.escape(expansion.getShortName()), writer);
        writeOtherExpansionMethodProperties();
        MIJsonUtils.writeEndObject(writer);
    }

    /**
     * <p>writeOtherExpansionMethodProperties.</p>
     *
     * @throws java.io.IOException if any.
     */
    protected void writeOtherExpansionMethodProperties() throws IOException {
        // nothing to do here
    }

    /**
     * <p>writeAllIdentifiers.</p>
     *
     * @param object a I object.
     * @throws java.io.IOException if any.
     */
    protected void writeAllIdentifiers(I object) throws IOException {
        if (!object.getIdentifiers().isEmpty()){
            MIJsonUtils.writeOpenArray(writer);

            Iterator<Xref> identifierIterator = object.getIdentifiers().iterator();
            while (identifierIterator.hasNext()){
                getIdentifierWriter().write(identifierIterator.next());

                if (identifierIterator.hasNext()){
                    MIJsonUtils.writeSeparator(writer);
                }
            }

            writeOtherIdentifiers(object);
            MIJsonUtils.writeEndArray(writer);
        }
    }

    /**
     * <p>writeOtherIdentifiers.</p>
     *
     * @param object a I object.
     * @throws java.io.IOException if any.
     */
    protected void writeOtherIdentifiers(I object) throws IOException {
        // to be overridden
    }

    /**
     * <p>hasIdentifiers.</p>
     *
     * @param object a I object.
     * @return a boolean.
     */
    protected boolean hasIdentifiers(I object) {
        return !object.getIdentifiers().isEmpty();
    }

    /**
     * <p>writeOtherProperties.</p>
     *
     * @param object a I object.
     * @param miScore : the MI score of the interaction to write
     * @throws java.io.IOException if any.
     */
    protected void writeOtherProperties(I object, Double miScore) throws IOException {
        // nothing to write here but can be overridden
    }

    /**
     * <p>Getter for the field <code>cvWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public JsonElementWriter<CvTerm> getCvWriter() {
        if (this.cvWriter == null){
            this.cvWriter = new SimpleJsonCvTermWriter(writer);
        }
        return cvWriter;
    }

    /**
     * <p>Setter for the field <code>cvWriter</code>.</p>
     *
     * @param cvWriter a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public void setCvWriter(JsonElementWriter<CvTerm> cvWriter) {
        this.cvWriter = cvWriter;
    }

    /**
     * <p>Getter for the field <code>identifierWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public JsonElementWriter<Xref> getIdentifierWriter() {
        if (this.identifierWriter == null){
            this.identifierWriter = new SimpleJsonIdentifierWriter(writer);
        }
        return identifierWriter;
    }

    /**
     * <p>Setter for the field <code>identifierWriter</code>.</p>
     *
     * @param identifierWriter a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public void setIdentifierWriter(JsonElementWriter<Xref> identifierWriter) {
        this.identifierWriter = identifierWriter;
    }

    /**
     * <p>Getter for the field <code>idGenerator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.json.IncrementalIdGenerator} object.
     */
    public IncrementalIdGenerator getIdGenerator() {
        if (this.idGenerator == null){
            this.idGenerator = new IncrementalIdGenerator();
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

    /**
     * <p>Getter for the field <code>participantWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public JsonElementWriter getParticipantWriter() {
        if (this.participantWriter == null){
            initialiseDefaultParticipantWriter();
        }
        return participantWriter;
    }

    /**
     * <p>initialiseDefaultParticipantWriter.</p>
     */
    protected void initialiseDefaultParticipantWriter() {
        this.participantWriter = new SimpleJsonParticipantWriter(this.writer, this.processedFeatures, this.processedInteractors,
                this.processedParticipants, getIdGenerator(), this.fetcher);
        ((SimpleJsonParticipantWriter)this.participantWriter).setCvWriter(getCvWriter());
    }

    /**
     * <p>Setter for the field <code>participantWriter</code>.</p>
     *
     * @param participantWriter a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public void setParticipantWriter(JsonElementWriter participantWriter) {
        this.participantWriter = participantWriter;
    }

    /**
     * <p>Getter for the field <code>fetcher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public OntologyTermFetcher getFetcher() {
        return fetcher;
    }

    /**
     * <p>Setter for the field <code>fetcher</code>.</p>
     *
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public void setFetcher(OntologyTermFetcher fetcher) {
        this.fetcher = fetcher;
    }

    /**
     * <p>Getter for the field <code>writer</code>.</p>
     *
     * @return a {@link java.io.Writer} object.
     */
    protected Writer getWriter() {
        return writer;
    }

    /**
     * <p>Getter for the field <code>processedInteractors</code>.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    protected Map<String, String> getProcessedInteractors() {
        return processedInteractors;
    }

    /**
     * <p>Getter for the field <code>processedFeatures</code>.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    protected Map<Feature, Integer> getProcessedFeatures() {
        return processedFeatures;
    }

    /**
     * <p>Getter for the field <code>processedParticipants</code>.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    protected Map<Entity, Integer> getProcessedParticipants() {
        return processedParticipants;
    }
}

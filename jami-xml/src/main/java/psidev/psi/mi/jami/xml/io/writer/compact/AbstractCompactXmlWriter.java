package psidev.psi.mi.jami.xml.io.writer.compact;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.cache.InMemoryIdentityObjectCache;
import psidev.psi.mi.jami.xml.io.writer.AbstractXmlWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExperimentWriter;
import psidev.psi.mi.jami.xml.model.extension.factory.options.PsiXmlWriterOptions;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.*;

/**
 * Abstract class for Compact XML writers.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public abstract class AbstractCompactXmlWriter<T extends Interaction> extends AbstractXmlWriter<T> {

    private PsiXmlElementWriter<String> availabilityWriter;
    private PsiXmlExperimentWriter experimentWriter;
    private PsiXmlElementWriter<Interactor> interactorWriter;
    private List<T> subInteractionsToWrite;

    private Class<T> type;

    private Set<Experiment> experiments;
    private Set<String> availabilities;
    private Set<Interactor> interactors;

    /**
     * <p>Constructor for AbstractCompactXmlWriter.</p>
     *
     * @param type a {@link java.lang.Class} object.
     */
    public AbstractCompactXmlWriter(PsiXmlVersion version, Class<T> type) {
        super(version);
        this.type = type;
        this.subInteractionsToWrite = new ArrayList<T>();
    }

    /**
     * <p>Constructor for AbstractCompactXmlWriter.</p>
     *
     * @param type a {@link java.lang.Class} object.
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractCompactXmlWriter(Class<T> type, File file) throws IOException, XMLStreamException {
        super(file);
        this.type = type;
        this.subInteractionsToWrite = new ArrayList<T>();
    }

    /**
     * <p>Constructor for AbstractCompactXmlWriter.</p>
     *
     * @param type a {@link java.lang.Class} object.
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractCompactXmlWriter(Class<T> type, OutputStream output) throws XMLStreamException {
        super(output);
        this.type = type;
        this.subInteractionsToWrite = new ArrayList<T>();
    }

    /**
     * <p>Constructor for AbstractCompactXmlWriter.</p>
     *
     * @param type a {@link java.lang.Class} object.
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractCompactXmlWriter(Class<T> type, Writer writer) throws XMLStreamException {
        super(writer);
        this.type = type;
        this.subInteractionsToWrite = new ArrayList<T>();
    }

    /**
     * <p>Constructor for AbstractCompactXmlWriter.</p>
     *
     * @param type a {@link java.lang.Class} object.
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param elementCache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    protected AbstractCompactXmlWriter(Class<T> type, XMLStreamWriter streamWriter, PsiXmlObjectCache elementCache) {
        super(streamWriter, elementCache);
        this.type = type;
        this.subInteractionsToWrite = new ArrayList<T>();
    }

    /** {@inheritDoc} */
    @Override
    public void initialiseContext(Map<String, Object> options) {
        super.initialiseContext(options);

        if (options.containsKey(PsiXmlWriterOptions.COMPACT_XML_EXPERIMENT_SET_OPTION)){
            setExperimentSet((Set<Experiment>) options.get(PsiXmlWriterOptions.COMPACT_XML_EXPERIMENT_SET_OPTION));
        }
        // use the default cache option
        else{
            initialiseDefaultExperimentSet();
        }
        if (options.containsKey(PsiXmlWriterOptions.COMPACT_XML_AVAILABILITY_SET_OPTION)){
            setAvailabilitySet((Set<String>) options.get(PsiXmlWriterOptions.COMPACT_XML_AVAILABILITY_SET_OPTION));
        }
        // use the default cache option
        else{
            initialiseDefaultAvailabilitySet();
        }
        if (options.containsKey(PsiXmlWriterOptions.COMPACT_XML_INTERACTOR_SET_OPTION)){
            setInteractorSet((Set<Interactor>) options.get(PsiXmlWriterOptions.COMPACT_XML_INTERACTOR_SET_OPTION));
        }
        // use the default cache option
        else{
            initialiseDefaultInteractorSet();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void close() throws MIIOException {
        this.interactors = null;
        this.availabilities = null;
        this.experiments = null;
        this.subInteractionsToWrite.clear();
        super.close();
    }

    /** {@inheritDoc} */
    @Override
    public void reset() throws MIIOException {
        this.interactors = null;
        this.availabilities = null;
        this.experiments = null;
        this.subInteractionsToWrite.clear();
        super.reset();
    }

    /**
     * <p>setExperimentSet.</p>
     *
     * @param experiments a {@link java.util.Set} object.
     */
    public void setExperimentSet(Set<Experiment> experiments) {
        this.experiments = experiments;
    }

    /**
     * <p>setAvailabilitySet.</p>
     *
     * @param availabilities a {@link java.util.Set} object.
     */
    public void setAvailabilitySet(Set<String> availabilities) {
        this.availabilities = availabilities;
    }

    /**
     * <p>setInteractorSet.</p>
     *
     * @param interactors a {@link java.util.Set} object.
     */
    public void setInteractorSet(Set<Interactor> interactors) {
        this.interactors = interactors;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseOptionalWriters(PsiXmlExperimentWriter experimentWriter, PsiXmlElementWriter<String> availabilityWriter, PsiXmlElementWriter<Interactor> interactorWriter) {
        setExperimentWriter(experimentWriter);
        setInteractorWriter(interactorWriter);
        setAvailabilityWriter(availabilityWriter);
    }

    /**
     * <p>registerAllInteractionsProperties.</p>
     */
    protected void registerAllInteractionsProperties() {
        // clear and initialise sets if not done yet
        getInteractors().clear();
        getAvailabilities().clear();
        getExperiments().clear();

        Source firstSource = getCurrentSource();
        T firstInteraction = getCurrentInteraction();
        boolean started = isStarted();
        this.subInteractionsToWrite.clear();

        // register first interaction
        // write first entry
        if (isStarted()){
            setStarted(false);
            registerInteractionProperties();
        }
        else{
            registerInteractionProperties();
        }

        boolean keepRegistering = true;
        while (getInteractionsIterator().hasNext()){
            T inter = getInteractionsIterator().next();
            setCurrentInteraction(inter);
            Source source = extractSourceFromInteraction();
            this.subInteractionsToWrite.add(inter);
            if(keepRegistering){
                // write next entry after closing first one
                if (getCurrentSource() != source){
                    // stops here for the current entry
                    keepRegistering = false;
                }
                else{
                    registerInteractionProperties();
                }
            }
        }

        // reset pointers
        setInteractionsIterator(new ArrayList<T>(this.subInteractionsToWrite).iterator());
        setCurrentSource(firstSource);
        setCurrentInteraction(firstInteraction);
        setStarted(started);
    }

    /**
     * <p>registerAllInteractors.</p>
     *
     * @param interaction a T object.
     */
    protected void registerAllInteractors(T interaction){
        for (Object o : interaction.getParticipants()){
            Participant participant = (Participant)o;
            // in case of a pool, we register all interactors of this pool
            if (participant instanceof ParticipantPool){
                ParticipantPool pool = (ParticipantPool)participant;
                if (!pool.isEmpty()){
                    registerParticipantPoolInteractor(pool);
                }
                else{
                    registerParticipantInteractor(participant);
                }
            }
            else{
                registerParticipantInteractor(participant);
            }
        }
    }

    /**
     * <p>registerParticipantPoolInteractor.</p>
     *
     * @param pool a {@link psidev.psi.mi.jami.model.ParticipantPool} object.
     */
    protected void registerParticipantPoolInteractor(ParticipantPool pool) {
        switch (getVersion()){
            case v3_0_0:
                for (Object candidate : pool){
                    registerParticipantInteractor((ParticipantCandidate)candidate);
                }
                break;
            default:
                registerParticipantInteractor(pool);
        }

    }

    /**
     * <p>registerParticipantInteractor.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.Entity} object.
     */
    protected void registerParticipantInteractor(Entity participant) {
        // we have a complex, we want to register default experiments
        if (!writeComplexesAsInteractors() && participant.getInteractor() instanceof Complex){
            Complex complex = (Complex)participant.getInteractor();
            // the complex will be written as interactor as it does not have any participants
            if (complex.getParticipants().isEmpty()){
                this.interactors.add(complex);
            }
            else {
                registerAllInteractorsAndExperimentsFrom(complex);
            }
        }
        // register interactor
        else{
            this.interactors.add(participant.getInteractor());
        }
    }

    /**
     * <p>registerAllInteractorsAndExperimentsFrom.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.ModelledInteraction} object.
     */
    protected void registerAllInteractorsAndExperimentsFrom(ModelledInteraction interaction){
        // register default experiment
        Experiment modelledExp = getComplexWriter().extractDefaultExperimentFrom(interaction);
        if (modelledExp != null){
            this.experiments.add(modelledExp);
        }

        for (Object o : interaction.getParticipants()){
            Participant participant = (Participant)o;
            // we have a complex, we want to register default experiments
            if (participant.getInteractor() instanceof Complex){
                Complex complex = (Complex)participant.getInteractor();
                // the complex will be written as interactor as it does not have any participants
                if (complex.getParticipants().isEmpty()){
                    this.interactors.add(complex);
                }
                else {
                    registerAllInteractorsAndExperimentsFrom(complex);
                }
            }
            // register interactor
            else{
                this.interactors.add(participant.getInteractor());
            }
        }
    }

    /**
     * <p>registerAvailabilities.</p>
     *
     * @param interaction a T object.
     */
    protected abstract void registerAvailabilities(T interaction);

    /**
     * <p>registerExperiment.</p>
     *
     * @param interaction a T object.
     */
    protected abstract void registerExperiment(T interaction);

    /** {@inheritDoc} */
    @Override
    protected void writeStartEntryContent() throws XMLStreamException {
        registerAllInteractionsProperties();
        // write start entry
        writeStartEntry();
        // write source
        writeSource();
        // write availability list
        if (!availabilities.isEmpty()){
            // write start availability list
            getStreamWriter().writeStartElement(PsiXmlUtils.AVAILABILITYLIST_TAG);
            for (String availability : this.availabilities){
                this.availabilityWriter.write(availability);
            }
            // write end availability list
            getStreamWriter().writeEndElement();
        }
        // write experiment list
        if (!experiments.isEmpty()){
            // write start experiment list
            getStreamWriter().writeStartElement(PsiXmlUtils.EXPERIMENTLIST_TAG);
            for (Experiment experiment : this.experiments){
                this.experimentWriter.write(experiment);
            }
            // write end experiment list
            getStreamWriter().writeEndElement();
        }
        // write interactor list
        if (!interactors.isEmpty()){
            // write start interactor list
            getStreamWriter().writeStartElement(PsiXmlUtils.INTERACTORLIST_TAG);
            for (Interactor interactor : this.interactors){
                this.interactorWriter.write(interactor);
            }
            // write end interactor list
            getStreamWriter().writeEndElement();

        }
        // write start interactionList
        writeStartInteractionList();
    }

    /**
     * <p>Getter for the field <code>availabilityWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    protected PsiXmlElementWriter<String> getAvailabilityWriter() {
        return availabilityWriter;
    }

    /**
     * <p>Setter for the field <code>availabilityWriter</code>.</p>
     *
     * @param availabilityWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    protected void setAvailabilityWriter(PsiXmlElementWriter<String> availabilityWriter) {
        this.availabilityWriter = availabilityWriter;
    }

    /**
     * <p>Getter for the field <code>experimentWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExperimentWriter} object.
     */
    protected PsiXmlExperimentWriter getExperimentWriter() {
        return experimentWriter;
    }

    /**
     * <p>Setter for the field <code>experimentWriter</code>.</p>
     *
     * @param experimentWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExperimentWriter} object.
     */
    protected void setExperimentWriter(PsiXmlExperimentWriter experimentWriter) {
        this.experimentWriter = experimentWriter;
    }

    /**
     * <p>Getter for the field <code>interactorWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    protected PsiXmlElementWriter<Interactor> getInteractorWriter() {
        return interactorWriter;
    }

    /**
     * <p>Setter for the field <code>interactorWriter</code>.</p>
     *
     * @param interactorWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    protected void setInteractorWriter(PsiXmlElementWriter<Interactor> interactorWriter) {
        this.interactorWriter = interactorWriter;
    }

    /**
     * <p>initialiseDefaultExperimentSet.</p>
     */
    protected void initialiseDefaultExperimentSet() {
        this.experiments = Collections.newSetFromMap(new IdentityHashMap<Experiment, Boolean>());
    }

    /**
     * <p>initialiseDefaultInteractorSet.</p>
     */
    protected void initialiseDefaultInteractorSet() {
        this.interactors = Collections.newSetFromMap(new IdentityHashMap<Interactor, Boolean>());
    }

    /**
     * <p>initialiseDefaultAvailabilitySet.</p>
     */
    protected void initialiseDefaultAvailabilitySet() {
        this.availabilities = new HashSet<String>();
    }

    /**
     * <p>Getter for the field <code>experiments</code>.</p>
     *
     * @return a {@link java.util.Set} object.
     */
    protected Set<Experiment> getExperiments() {
        if (this.experiments == null){
            initialiseDefaultExperimentSet();
        }
        return experiments;
    }

    /**
     * <p>Getter for the field <code>availabilities</code>.</p>
     *
     * @return a {@link java.util.Set} object.
     */
    protected Set<String> getAvailabilities() {
        if (this.availabilities == null){
            initialiseDefaultAvailabilitySet();
        }
        return availabilities;
    }

    /**
     * <p>Getter for the field <code>interactors</code>.</p>
     *
     * @return a {@link java.util.Set} object.
     */
    protected Set<Interactor> getInteractors() {
        if (this.interactors == null){
            initialiseDefaultInteractorSet();
        }
        return interactors;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultElementCache() {
        setElementCache(new InMemoryIdentityObjectCache());
    }

    /**
     * <p>getInteractionType.</p>
     *
     * @return a {@link java.lang.Class} object.
     */
    protected Class<T> getInteractionType() {
        return type;
    }

    /**
     * <p>registerInteractionProperties.</p>
     */
    protected void registerInteractionProperties() {
        T interaction = getCurrentInteraction();
        // register all experiments
        registerExperiment(interaction);
        // register all availabilities
        registerAvailabilities(interaction);
        // register all interactors
        registerAllInteractors(interaction);
    }
}

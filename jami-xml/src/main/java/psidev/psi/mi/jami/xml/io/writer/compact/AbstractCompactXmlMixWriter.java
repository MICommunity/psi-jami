package psidev.psi.mi.jami.xml.io.writer.compact;

import psidev.psi.mi.jami.factory.options.InteractionWriterOptions;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.*;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.*;

/**
 * Abstract class for a compact PSI-XML writer of mixed interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/11/13</pre>
 */
public abstract class AbstractCompactXmlMixWriter<I extends Interaction, M extends ModelledInteraction, E extends InteractionEvidence> extends AbstractCompactXmlWriter<I> {

    private AbstractCompactXmlWriter<E> evidenceWriter;
    private AbstractCompactXmlWriter<M> modelledWriter;
    private AbstractCompactXmlWriter<I> lightWriter;

    /**
     * <p>Constructor for AbstractCompactXmlMixWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param type a {@link java.lang.Class} object.
     */
    public AbstractCompactXmlMixWriter(PsiXmlVersion version, Class<I> type) {
        super(version, type);
    }

    /**
     * <p>Constructor for AbstractCompactXmlMixWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param type a {@link java.lang.Class} object.
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractCompactXmlMixWriter(PsiXmlVersion version, Class<I> type, File file) throws IOException, XMLStreamException {
        super(version, type,file);
    }

    /**
     * <p>Constructor for AbstractCompactXmlMixWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param type a {@link java.lang.Class} object.
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractCompactXmlMixWriter(PsiXmlVersion version, Class<I> type, OutputStream output) throws XMLStreamException {
        super(version, type,output);
    }

    /**
     * <p>Constructor for AbstractCompactXmlMixWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param type a {@link java.lang.Class} object.
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractCompactXmlMixWriter(PsiXmlVersion version, Class<I> type, Writer writer) throws XMLStreamException {
        super(version, type,writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWriters() {
        initialiseDelegateWriters();
    }

    /** {@inheritDoc} */
    @Override
    public void close() throws MIIOException {
        super.close();
        this.modelledWriter = null;
        this.evidenceWriter = null;
        this.lightWriter = null;
    }

    /** {@inheritDoc} */
    @Override
    public void reset() throws MIIOException {
        super.reset();
        this.modelledWriter = null;
        this.evidenceWriter = null;
        this.lightWriter = null;
    }

    /** {@inheritDoc} */
    @Override
    protected void setAvailabilityWriter(PsiXmlElementWriter<String> availabilityWriter) {
        this.evidenceWriter.setAvailabilityWriter(availabilityWriter);
    }

    /** {@inheritDoc} */
    @Override
    protected void setExperimentWriter(PsiXmlExperimentWriter experimentWriter) {
        super.setExperimentWriter(experimentWriter);
        if (this.modelledWriter != null){
            this.modelledWriter.setExperimentWriter(experimentWriter);
        }
        if (this.evidenceWriter != null){
            this.evidenceWriter.setExperimentWriter(experimentWriter);
        }
        if (this.lightWriter != null){
            this.lightWriter.setExperimentWriter(experimentWriter);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void setInteractorWriter(PsiXmlElementWriter<Interactor> interactorWriter) {
        super.setInteractorWriter(interactorWriter);
        if (this.modelledWriter != null){
            this.modelledWriter.setInteractorWriter(interactorWriter);
        }
        if (this.evidenceWriter != null){
            this.evidenceWriter.setInteractorWriter(interactorWriter);
        }
        if (this.lightWriter != null){
            this.lightWriter.setInteractorWriter(interactorWriter);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setExperimentSet(Set<Experiment> experiments) {
        super.setExperimentSet(experiments);
        if (this.modelledWriter != null){
            this.modelledWriter.setExperimentSet(experiments);
        }
        if (this.evidenceWriter != null){
            this.evidenceWriter.setExperimentSet(experiments);
        }
        if (this.lightWriter != null){
            this.lightWriter.setExperimentSet(experiments);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setAvailabilitySet(Set<String> availabilities) {
        super.setAvailabilitySet(availabilities);
        if (this.modelledWriter != null){
            this.modelledWriter.setAvailabilitySet(availabilities);
        }
        if (this.evidenceWriter != null){
            this.evidenceWriter.setAvailabilitySet(availabilities);
        }
        if (this.lightWriter != null){
            this.lightWriter.setAvailabilitySet(availabilities);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setVersion(PsiXmlVersion version) {
        super.setVersion(version);
        if (this.modelledWriter != null){
            this.modelledWriter.setVersion(version);
        }
        if (this.evidenceWriter != null){
            this.evidenceWriter.setVersion(version);
        }
        if (this.lightWriter != null){
            this.lightWriter.setVersion(version);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractorSet(Set<Interactor> interactors) {
        super.setInteractorSet(interactors);
        if (this.modelledWriter != null){
            this.modelledWriter.setInteractorSet(interactors);
        }
        if (this.evidenceWriter != null){
            this.evidenceWriter.setInteractorSet(interactors);
        }
        if (this.lightWriter != null){
            this.lightWriter.setInteractorSet(interactors);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractionSet(Set<Interaction> processedInteractions) {
        super.setInteractionSet(processedInteractions);
        if (this.modelledWriter != null){
            this.modelledWriter.setInteractionSet(processedInteractions);
        }
        if (this.evidenceWriter != null){
            this.evidenceWriter.setInteractionSet(processedInteractions);
        }
        if (this.lightWriter != null){
            this.lightWriter.setInteractionSet(processedInteractions);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setDefaultSource(Source defaultSource) {
        super.setDefaultSource(defaultSource);
        if (this.modelledWriter != null){
            this.modelledWriter.setDefaultSource(defaultSource);
        }
        if (this.evidenceWriter != null){
            this.evidenceWriter.setDefaultSource(defaultSource);
        }
        if (this.lightWriter != null){
            this.lightWriter.setDefaultSource(defaultSource);
        }    }

    /** {@inheritDoc} */
    @Override
    public void setDefaultReleaseDate(XMLGregorianCalendar defaultReleaseDate) {
        super.setDefaultReleaseDate(defaultReleaseDate);
        if (this.modelledWriter != null){
            this.modelledWriter.setDefaultReleaseDate(defaultReleaseDate);
        }
        if (this.evidenceWriter != null){
            this.evidenceWriter.setDefaultReleaseDate(defaultReleaseDate);
        }
        if (this.lightWriter != null){
            this.lightWriter.setDefaultReleaseDate(defaultReleaseDate);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void flush() throws MIIOException {
        super.flush();
        this.evidenceWriter.flush();
        this.modelledWriter.flush();
        this.lightWriter.flush();
    }

    /** {@inheritDoc} */
    @Override
    public void setWriteComplexesAsInteractors(boolean writeComplexesAsInteractors) {
        super.setWriteComplexesAsInteractors(writeComplexesAsInteractors);
        if (this.modelledWriter != null){
            this.modelledWriter.setWriteComplexesAsInteractors(writeComplexesAsInteractors);
        }
        if (this.evidenceWriter != null){
            this.evidenceWriter.setWriteComplexesAsInteractors(writeComplexesAsInteractors);
        }
        if (this.lightWriter != null){
            this.lightWriter.setWriteComplexesAsInteractors(writeComplexesAsInteractors);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void registerExperiment(I interaction) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void registerAvailabilities(I interaction) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    public void write(Iterator<? extends I> interactions) throws MIIOException {
        if (this.modelledWriter == null || this.evidenceWriter == null || this.lightWriter == null){
            throw new IllegalStateException("The PSI-XML writer was not initialised. The options for the PSI-XML writer should contains at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        List<E> evidences = new ArrayList<E>();
        List<M> modelledList = new ArrayList<M>();
        List<I> interactionList = new ArrayList<I>();
        I interaction = null;

        if (interactions.hasNext()){
            interaction = interactions.next();
        }

        do{
            if (interaction != null && this.evidenceWriter.getInteractionType() != null
                    && this.evidenceWriter.getInteractionType().isAssignableFrom(interaction.getClass())){
                evidences.clear();
                do{
                    evidences.add((E)interaction);
                    if (interactions.hasNext()){
                        interaction = interactions.next();
                    }
                    else{
                        interaction = null;
                    }
                }
                while(interaction != null && this.evidenceWriter.getInteractionType().isAssignableFrom(interaction.getClass()));
                this.evidenceWriter.write(evidences);
            }
            else if (interaction != null && this.modelledWriter.getInteractionType() != null
                    && this.modelledWriter.getInteractionType().isAssignableFrom(interaction.getClass())){
                modelledList.clear();
                do{
                    modelledList.add((M)interaction);
                    if (interactions.hasNext()){
                        interaction = interactions.next();
                    }
                    else{
                        interaction = null;
                    }
                }
                while(interaction != null && this.modelledWriter.getInteractionType().isAssignableFrom(interaction.getClass()));
                this.modelledWriter.write(modelledList);
            }
            else if (interaction != null){
                interactionList.clear();
                do{
                    interactionList.add(interaction);
                    if (interactions.hasNext()){
                        interaction = interactions.next();
                    }
                    else{
                        interaction = null;
                    }
                }
                while(interaction != null && !this.evidenceWriter.getInteractionType().isAssignableFrom(interaction.getClass())
                        && !this.modelledWriter.getInteractionType().isAssignableFrom(interaction.getClass()));
                this.lightWriter.write(interactionList);
            }
            else{
                break;
            }
        }
        while (interaction != null);
    }

    /** {@inheritDoc} */
    @Override
    public void write(Collection<? extends I> interactions) throws MIIOException {
        write(interactions.iterator());
    }

    /** {@inheritDoc} */
    @Override
    public void write(I interaction) throws MIIOException {
        if (this.modelledWriter == null || this.evidenceWriter == null || this.lightWriter == null){
            throw new IllegalStateException("The PSI-XML writer was not initialised. The options for the PSI-XML writer should contains at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        if (this.evidenceWriter.getInteractionType() != null && this.evidenceWriter.getInteractionType().isAssignableFrom(interaction.getClass())){
            this.evidenceWriter.write((E)interaction);
        }
        else if (this.modelledWriter.getInteractionType() != null && this.modelledWriter.getInteractionType().isAssignableFrom(interaction.getClass())){
            this.modelledWriter.write((M)interaction);
        }
        else{
            this.lightWriter.write(interaction);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setSourceWriter(PsiXmlSourceWriter sourceWriter) {
        super.setSourceWriter(sourceWriter);
        if (this.modelledWriter != null){
            this.modelledWriter.setSourceWriter(sourceWriter);
        }
        if (this.evidenceWriter != null){
            this.evidenceWriter.setSourceWriter(sourceWriter);
        }
        if (this.lightWriter != null){
            this.lightWriter.setSourceWriter(sourceWriter);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setComplexWriter(PsiXmlInteractionWriter<ModelledInteraction> complexWriter) {
        super.setComplexWriter(complexWriter);
        if (this.modelledWriter != null){
            this.modelledWriter.setComplexWriter(complexWriter);
        }
        if (this.evidenceWriter != null){
            this.evidenceWriter.setComplexWriter(complexWriter);
        }
        if (this.lightWriter != null){
            this.lightWriter.setComplexWriter(complexWriter);
        }

    }

    /** {@inheritDoc} */
    @Override
    public void setElementCache(PsiXmlObjectCache elementCache) {
        super.setElementCache(elementCache);
        if (this.modelledWriter != null){
            this.modelledWriter.setElementCache(elementCache);
        }
        if (this.evidenceWriter != null){
            this.evidenceWriter.setElementCache(elementCache);
        }
        if (this.lightWriter != null){
            this.lightWriter.setElementCache(elementCache);
        }
    }

    /**
     * <p>Setter for the field <code>evidenceWriter</code>.</p>
     *
     * @param evidenceWriter a {@link psidev.psi.mi.jami.xml.io.writer.compact.AbstractCompactXmlWriter} object.
     */
    protected void setEvidenceWriter(AbstractCompactXmlWriter<E> evidenceWriter) {
        this.evidenceWriter = evidenceWriter;
        this.evidenceWriter.setElementCache(getElementCache());
    }

    /**
     * <p>Setter for the field <code>modelledWriter</code>.</p>
     *
     * @param modelledWriter a {@link psidev.psi.mi.jami.xml.io.writer.compact.AbstractCompactXmlWriter} object.
     */
    protected void setModelledWriter(AbstractCompactXmlWriter<M> modelledWriter) {
        this.modelledWriter = modelledWriter;
        this.modelledWriter.setElementCache(getElementCache());
    }

    /**
     * <p>Setter for the field <code>lightWriter</code>.</p>
     *
     * @param lightWriter a {@link psidev.psi.mi.jami.xml.io.writer.compact.AbstractCompactXmlWriter} object.
     */
    protected void setLightWriter(AbstractCompactXmlWriter<I> lightWriter) {
        this.lightWriter = lightWriter;
        this.lightWriter.setElementCache(getElementCache());
    }

    /**
     * <p>initialiseDelegateWriters.</p>
     */
    protected abstract void initialiseDelegateWriters();
}

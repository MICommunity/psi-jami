package psidev.psi.mi.jami.xml.io.writer;

import javanet.staxutils.IndentingXMLStreamWriter;
import org.codehaus.stax2.XMLOutputFactory2;
import org.codehaus.stax2.XMLStreamWriter2;
import psidev.psi.mi.jami.datasource.InteractionWriter;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.InteractionWriterOptions;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultSource;
import psidev.psi.mi.jami.xml.PsiXmlType;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.*;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlSource;
import psidev.psi.mi.jami.xml.model.extension.factory.options.PsiXmlWriterOptions;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.*;

/**
 * Abstract class for XML writer of interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/11/13</pre>
 */
public abstract class AbstractXmlWriter<T extends Interaction> implements InteractionWriter<T>{

    private XMLStreamWriter streamWriter;
    private boolean isInitialised = false;
    private PsiXmlInteractionWriter<T> interactionWriter;
    private PsiXmlInteractionWriter<ModelledInteraction> complexWriter;
    private PsiXmlSourceWriter sourceWriter;
    private PsiXmlObjectCache elementCache;
    private List<T> interactionsToWrite;
    private Iterator<? extends T> interactionsIterator;
    private boolean started = false;
    private Source currentSource;
    private T currentInteraction;
    private boolean writeComplexesAsInteractors=false;
    private Set<Interaction> processedInteractions;

    private Source defaultSource;
    private XMLGregorianCalendar defaultReleaseDate;

    private Collection<Annotation> entryAnnotations=null;
    private PsiXmlElementWriter<Annotation> annotationsWriter=null;

    private PsiXmlVersion version;
    private PsiXmlElementWriterFactory subWritersFactory;

    /**
     * <p>Constructor for AbstractXmlWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     */
    public AbstractXmlWriter(PsiXmlVersion version){
        this.version = version;
        this.interactionsToWrite = new ArrayList<T>();
        this.subWritersFactory = PsiXmlElementWriterFactory.getInstance();
    }

    /**
     * <p>Constructor for AbstractXmlWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractXmlWriter(PsiXmlVersion version, File file) throws IOException, XMLStreamException {
        this.version = version;
        initialiseFile(file);
        isInitialised = true;
        this.interactionsToWrite = new ArrayList<T>();
        this.subWritersFactory = PsiXmlElementWriterFactory.getInstance();
    }

    /**
     * <p>Constructor for AbstractXmlWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractXmlWriter(PsiXmlVersion version, OutputStream output) throws XMLStreamException {
        this.version = version;
        initialiseOutputStream(output);
        isInitialised = true;
        this.interactionsToWrite = new ArrayList<T>();
        this.subWritersFactory = PsiXmlElementWriterFactory.getInstance();
    }

    /**
     * <p>Constructor for AbstractXmlWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractXmlWriter(PsiXmlVersion version, Writer writer) throws XMLStreamException {
        this.version = version;
        initialiseWriter(writer);
        isInitialised = true;
        this.interactionsToWrite = new ArrayList<T>();
        this.subWritersFactory = PsiXmlElementWriterFactory.getInstance();
    }

    /**
     * <p>Constructor for AbstractXmlWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param elementCache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    protected AbstractXmlWriter(PsiXmlVersion version, XMLStreamWriter streamWriter, PsiXmlObjectCache elementCache) {
        this.version = version;
        if (streamWriter == null){
            throw new IllegalArgumentException("The stream writer cannot be null.");
        }

        this.elementCache = elementCache;
        initialiseStreamWriter(streamWriter);
        isInitialised = true;
        this.interactionsToWrite = new ArrayList<T>();
        this.subWritersFactory = PsiXmlElementWriterFactory.getInstance();
    }

    /** {@inheritDoc} */
    public void initialiseContext(Map<String, Object> options) {
        if (options != null && options.containsKey(PsiXmlWriterOptions.ELEMENT_WITH_ID_CACHE_OPTION)){
            this.elementCache = (PsiXmlObjectCache)options.get(PsiXmlWriterOptions.ELEMENT_WITH_ID_CACHE_OPTION);
        }
        // use the default cache option
        else{
            initialiseDefaultElementCache();
        }

        if (options == null && !isInitialised){
            throw new IllegalArgumentException("The options for the PSI-XML writer should contains at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        else if (options == null){
            return;
        }
        else if (options.containsKey(InteractionWriterOptions.OUTPUT_OPTION_KEY)){
            Object output = options.get(InteractionWriterOptions.OUTPUT_OPTION_KEY);

            if (output instanceof File){
                try {
                    initialiseFile((File) output);
                } catch (XMLStreamException e) {
                    throw new IllegalArgumentException("Impossible to open and write in output file " + ((File)output).getName(), e);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Impossible to open and write in output file " + ((File)output).getName(), e);
                }
            }
            else if (output instanceof OutputStream){
                try {
                    initialiseOutputStream((OutputStream) output);
                } catch (XMLStreamException e) {
                    throw new IllegalArgumentException("Impossible to open and write in output stream ", e);
                }
            }
            else if (output instanceof Writer){
                try {
                    initialiseWriter((Writer) output);
                } catch (XMLStreamException e) {
                    throw new IllegalArgumentException("Impossible to open and write in output writer ", e);
                }
            }
            // suspect a file path
            else if (output instanceof String){
                try {
                    initialiseFile(new File((String)output));
                } catch (IOException e) {
                    throw new IllegalArgumentException("Impossible to open and write in output file " + output, e);
                }
                catch (XMLStreamException e) {
                    throw new IllegalArgumentException("Impossible to open and write in output file " + ((File)output).getName(), e);
                }
            }
            else if (output instanceof XMLStreamWriter){
                initialiseStreamWriter((XMLStreamWriter) output);
            }
            else {
                throw new IllegalArgumentException("Impossible to write in the provided output "+output.getClass().getName() + ", a File, OuputStream, Writer or file path was expected.");
            }
        }
        else if (!isInitialised){
            throw new IllegalArgumentException("The options for the PSI-XML writer should contains at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }

        // version
        if (options.containsKey(PsiXmlWriterOptions.XML_VERSION_OPTION)){
            setVersion((PsiXmlVersion)options.get(PsiXmlWriterOptions.XML_VERSION_OPTION));
        }
        else{
            setVersion(PsiXmlVersion.v2_5_4);
        }

        if (options.containsKey(PsiXmlWriterOptions.WRITE_COMPLEX_AS_INTERACTOR_OPTION)){
            setWriteComplexesAsInteractors(this.writeComplexesAsInteractors = (Boolean)options.get(PsiXmlWriterOptions.WRITE_COMPLEX_AS_INTERACTOR_OPTION));
        }

        if (options.containsKey(PsiXmlWriterOptions.XML_INTERACTION_SET_OPTION)){
            setInteractionSet((Set<Interaction>) options.get(PsiXmlWriterOptions.XML_INTERACTION_SET_OPTION));
        }
        // use the default cache option
        else{
            initialiseDefaultInteractionSet();
        }

        // default release date
        if (options.containsKey(PsiXmlWriterOptions.DEFAULT_RELEASE_DATE_OPTION)){
            setDefaultReleaseDate((XMLGregorianCalendar)options.get(PsiXmlWriterOptions.DEFAULT_RELEASE_DATE_OPTION));
        }

        // default source
        if (options.containsKey(PsiXmlWriterOptions.DEFAULT_SOURCE_OPTION)){
            setDefaultSource((Source)options.get(PsiXmlWriterOptions.DEFAULT_SOURCE_OPTION));
        }

        isInitialised = true;
    }

    /** {@inheritDoc} */
    @Override
    public void end() throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The PSI-XML writer was not initialised. The options for the PSI-XML writer should contains at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        started = false;
        // write end of entrySet
        try {
            this.streamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            throw new MIIOException("Cannot write the end of entrySet root node.", e);
        }
        // write end of document
        try {
            this.streamWriter.writeEndDocument();
        } catch (XMLStreamException e) {
            throw new MIIOException("Cannot write the end of XML document.", e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void start() throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The PSI-XML writer was not initialised. The options for the PSI-XML writer should contains at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        // write start of document (by default, version = 1 and encoding = UTL-8)
        try {
            this.streamWriter.writeStartDocument();
        } catch (XMLStreamException e) {
            throw new MIIOException("Cannot write the start document of this PSI-XML output.", e);
        }
        // write start of entrySet
        try {
            this.streamWriter.writeStartElement(PsiXmlUtils.ENTRYSET_TAG);

            switch (this.version){
                case v2_5_4:
                    writeEntrySetAttributes("2", "5", "4", PsiXmlUtils.Xml254_NAMESPACE_URI, PsiXmlUtils.PSI_SCHEMA_254_LOCATION);
                    break;
                case v2_5_3:
                    writeEntrySetAttributes("2", "5", "3", PsiXmlUtils.Xml253_NAMESPACE_URI, PsiXmlUtils.PSI_SCHEMA_253_LOCATION);
                    break;
                case v3_0_0:
                    writeEntrySetAttributes("3", "0", "0", PsiXmlUtils.Xml300_NAMESPACE_URI, PsiXmlUtils.PSI_SCHEMA_300_LOCATION);
                    break;
                default:
                    writeEntrySetAttributes("2", "5", "4", PsiXmlUtils.Xml254_NAMESPACE_URI, PsiXmlUtils.PSI_SCHEMA_254_LOCATION);
                    break;
            }
        } catch (XMLStreamException e) {
            throw new MIIOException("Cannot write the start of the entrySet root node.", e);
        }
    }

    /**
     * <p>writeEntrySetAttributes.</p>
     *
     * @param level a {@link java.lang.String} object.
     * @param version a {@link java.lang.String} object.
     * @param minorVersion a {@link java.lang.String} object.
     * @param namespaceURI a {@link java.lang.String} object.
     * @param schemaLocation a {@link java.lang.String} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeEntrySetAttributes(String level, String version, String minorVersion, String namespaceURI, String schemaLocation) throws XMLStreamException {
        this.streamWriter.writeDefaultNamespace(namespaceURI);
        this.streamWriter.writeNamespace(PsiXmlUtils.XML_SCHEMA_PREFIX, PsiXmlUtils.XML_SCHEMA);
        this.streamWriter.writeAttribute(PsiXmlUtils.XML_SCHEMA, PsiXmlUtils.SCHEMA_LOCATION_ATTRIBUTE, schemaLocation);
        this.streamWriter.writeAttribute(PsiXmlUtils.LEVEL_ATTRIBUTE,level);
        this.streamWriter.writeAttribute(PsiXmlUtils.VERSION_ATTRIBUTE,version);
        this.streamWriter.writeAttribute(PsiXmlUtils.MINOR_VERSION_ATTRIBUTE,minorVersion);
    }

    /** {@inheritDoc} */
    @Override
    public void write(T interaction) throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The PSI-XML writer was not initialised. The options for the PSI-XML writer should contains at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        registerInteractionForEntry(interaction);
        writeInteractionListContent(null);
    }

    /** {@inheritDoc} */
    @Override
    public void write(T interaction, Double miScore) throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The PSI-XML writer was not initialised. The options for the PSI-XML writer should contains at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        registerInteractionForEntry(interaction);
        writeInteractionListContent(miScore);
    }

    /** {@inheritDoc} */
    @Override
    public void write(Collection<? extends T> interactions) throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The PSI-XML writer was not initialised. The options for the PSI-XML writer should contains at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        registerInteractionsForEntry(interactions);
        writeInteractionListContent(null);
    }

    /** {@inheritDoc} */
    @Override
    public void write(Collection<? extends T> interactions, Double miScore) throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The PSI-XML writer was not initialised. The options for the PSI-XML writer should contains at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        registerInteractionsForEntry(interactions);
        writeInteractionListContent(miScore);
    }

    /** {@inheritDoc} */
    @Override
    public void write(Iterator<? extends T> interactions) throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The PSI-XML writer was not initialised. The options for the PSI-XML writer should contains at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        registerInteractionsForEntry(interactions);
        writeInteractionListContent(null);
    }

    /** {@inheritDoc} */
    @Override
    public void write(Iterator<? extends T> interactions, Double miScore) throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The PSI-XML writer was not initialised. The options for the PSI-XML writer should contains at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        registerInteractionsForEntry(interactions);
        writeInteractionListContent(miScore);
    }

    /** {@inheritDoc} */
    @Override
    public void flush() throws MIIOException{
        if (isInitialised){
            try {
                this.streamWriter.flush();
            } catch (XMLStreamException e) {
                throw new MIIOException("Impossible to flush the PSI-XML writer", e);
            }
        }
    }

    /**
     * <p>close.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void close() throws MIIOException{
        if (isInitialised){
            try {
                this.streamWriter.flush();
            } catch (XMLStreamException e) {
                throw new MIIOException("Impossible to flush the PSI-XML writer", e);
            } finally {
                this.isInitialised = false;
                this.streamWriter = null;
                if (this.elementCache != null){
                    this.elementCache.close();
                }
                this.elementCache = null;
                this.interactionsToWrite.clear();
                this.interactionsIterator = null;
                this.processedInteractions = null;
            }
        }
    }
    /**
     * <p>reset.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void reset() throws MIIOException{
        if (isInitialised){
            try {
                this.streamWriter.flush();
            } catch (XMLStreamException e) {
                throw new MIIOException("Impossible to flush the PSI-XML writer", e);
            } finally {
                isInitialised = false;
                this.streamWriter = null;
                this.elementCache = null;
                this.interactionsToWrite.clear();
                this.interactionsIterator = null;
                this.processedInteractions = null;
            }
        }
    }

    /**
     * <p>Setter for the field <code>writeComplexesAsInteractors</code>.</p>
     *
     * @param writeComplexesAsInteractors a boolean.
     */
    public void setWriteComplexesAsInteractors(boolean writeComplexesAsInteractors) {
        this.writeComplexesAsInteractors = writeComplexesAsInteractors;
        if (this.interactionWriter != null){
            this.interactionWriter.setComplexAsInteractor(writeComplexesAsInteractors);
        }
        if (this.complexWriter != null){
            this.complexWriter.setComplexAsInteractor(writeComplexesAsInteractors);
        }
    }

    /**
     * <p>setInteractionSet.</p>
     *
     * @param processedInteractions a {@link java.util.Set} object.
     */
    public void setInteractionSet(Set<Interaction> processedInteractions) {
        this.processedInteractions = processedInteractions;
    }

    /**
     * <p>Setter for the field <code>sourceWriter</code>.</p>
     *
     * @param sourceWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlSourceWriter} object.
     */
    public void setSourceWriter(PsiXmlSourceWriter sourceWriter) {
        if (sourceWriter == null){
            throw new IllegalArgumentException("The source writer cannot be null");
        }
        this.sourceWriter = sourceWriter;
        this.sourceWriter.setDefaultReleaseDate(this.defaultReleaseDate);
    }
    /**
     * <p>Setter for the field <code>complexWriter</code>.</p>
     *
     * @param complexWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlInteractionWriter} object.
     */
    public void setComplexWriter(PsiXmlInteractionWriter<ModelledInteraction> complexWriter) {
        if (complexWriter == null){
            throw new IllegalArgumentException("The Complex writer cannot be null");
        }
        this.complexWriter = complexWriter;
    }

    /**
     * <p>Setter for the field <code>elementCache</code>.</p>
     *
     * @param elementCache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public void setElementCache(PsiXmlObjectCache elementCache) {
        if (elementCache == null){
            initialiseDefaultElementCache();
        }
        else{
            this.elementCache = elementCache;
        }
        // reinit subwriters
        initialiseSubWriters();
    }

    /**
     * <p>Setter for the field <code>started</code>.</p>
     *
     * @param started a boolean.
     */
    public void setStarted(boolean started) {
        this.started = started;
    }

    /**
     * <p>Setter for the field <code>defaultSource</code>.</p>
     *
     * @param defaultSource a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public void setDefaultSource(Source defaultSource) {
        this.defaultSource = defaultSource;
    }

    /**
     * <p>Setter for the field <code>defaultReleaseDate</code>.</p>
     *
     * @param defaultReleaseDate a {@link javax.xml.datatype.XMLGregorianCalendar} object.
     */
    public void setDefaultReleaseDate(XMLGregorianCalendar defaultReleaseDate) {
        this.defaultReleaseDate = defaultReleaseDate;
        if (this.sourceWriter != null){
            this.sourceWriter.setDefaultReleaseDate(this.defaultReleaseDate);
        }
    }

    /**
     * <p>Setter for the field <code>entryAnnotations</code>.</p>
     *
     * @param entryAnnotations a {@link java.util.Collection} object.
     */
    public void setEntryAnnotations(Collection<Annotation> entryAnnotations) {
        this.entryAnnotations = entryAnnotations;
    }

    /**
     * <p>Setter for the field <code>annotationsWriter</code>.</p>
     *
     * @param annotationsWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setAnnotationsWriter(PsiXmlElementWriter<Annotation> annotationsWriter) {
        if (annotationsWriter == null){
            throw new IllegalArgumentException("The annotations writer cannot be null");
        }
        this.annotationsWriter = annotationsWriter;
    }

    /**
     * <p>Setter for the field <code>version</code>.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     */
    public void setVersion(PsiXmlVersion version) {
        this.version = version;
        initialiseSubWriters();
    }

    /**
     * <p>Getter for the field <code>processedInteractions</code>.</p>
     *
     * @return a {@link java.util.Set} object.
     */
    protected Set<Interaction> getProcessedInteractions() {
        if (processedInteractions == null){
            initialiseDefaultInteractionSet();
        }
        return processedInteractions;
    }

    /**
     * <p>writeEndEntryContent.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeEndEntryContent() throws XMLStreamException {
        // write subComplexes
        writeSubComplexInEntry();
        // write end interactionsList
        writeEndInteractionList();
        // write annotations if any
        writeEntryAttributes();
        // write end previous entry
        writeEndEntry();
    }

    /**
     * <p>writeEntryAttributes.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeEntryAttributes() throws XMLStreamException {
        if (this.entryAnnotations != null && !this.entryAnnotations.isEmpty()){
            this.streamWriter.writeStartElement(PsiXmlUtils.ATTRIBUTELIST_TAG);
            for (Annotation annotation : this.entryAnnotations){
                this.annotationsWriter.write(annotation);
            }
            this.streamWriter.writeEndElement();
        }
    }

    /**
     * <p>writeInteractionListContent.</p>
     */
    protected void writeInteractionListContent(Double miScore) {
        started = true;
        try {
            while (this.interactionsIterator.hasNext()){
                this.currentInteraction = this.interactionsIterator.next();
                Source source = extractSourceFromInteraction();
                // write first entry
                if (started){
                    setStarted(false);
                    this.currentSource = source;
                    getProcessedInteractions().clear();
                    writeStartEntryContent();

                }
                // write next entry after closing first one
                else if (this.currentSource != source){
                    // write subComplexes
                    writeEndEntryContent();
                    // change current source
                    this.currentSource = source;
                    getProcessedInteractions().clear();
                    // write start entry
                    writeStartEntryContent();
                }

                // write interaction
                if (getProcessedInteractions().add(this.currentInteraction)){
                    writeInteraction(miScore);
                }
            }

            // write final end entry
            writeEndEntryContent();
            setStarted(true);
        } catch (XMLStreamException e) {
            throw new MIIOException("Cannot write interactions ", e);
        }
    }

    /**
     * <p>writeStartEntryContent.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeStartEntryContent() throws XMLStreamException;

    /**
     * <p>writeStartInteractionList.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeStartInteractionList() throws XMLStreamException {
        // write start interaction list
        this.streamWriter.writeStartElement(PsiXmlUtils.INTERACTIONLIST_TAG);
    }

    /**
     * <p>writeEndInteractionList.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeEndInteractionList() throws XMLStreamException {
        // write end interaction list
        this.streamWriter.writeEndElement();
    }

    /**
     * <p>writeInteraction.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeInteraction(Double miScore) throws XMLStreamException {
        // write interaction
        this.interactionWriter.write(this.currentInteraction, miScore);
    }

    /**
     * <p>writeSubComplexInEntry.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeSubComplexInEntry() throws XMLStreamException {
         while (this.elementCache.hasRegisteredSubComplexes()){
             Set<ModelledInteraction> registeredComplexes = this.elementCache.clearRegisteredSubComplexes();
             for (ModelledInteraction modelled : registeredComplexes){
                 writeComplex(modelled);
             }
         }
    }

    /**
     * <p>writeComplex.</p>
     *
     * @param modelled a {@link psidev.psi.mi.jami.model.ModelledInteraction} object.
     */
    protected void writeComplex(ModelledInteraction modelled) {
        this.complexWriter.write(modelled);
    }

    /**
     * <p>extractSourceFromInteraction.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Source} object.
     */
    protected Source extractSourceFromInteraction(){
        if (this.defaultSource == null && this.defaultReleaseDate != null){
           initialiseDefaultSource();
        }
        return this.defaultSource;
    }

    /**
     * <p>initialiseDefaultSource.</p>
     */
    protected void initialiseDefaultSource() {
        this.defaultSource = new DefaultSource("Unknown source");
    }

    /**
     * <p>writeSource.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeSource() throws XMLStreamException {
        if (this.currentSource != null){
            this.sourceWriter.write(this.currentSource);
        }
    }

    /**
     * <p>writeStartEntry.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeStartEntry() throws XMLStreamException {
        this.elementCache.clear();
        this.streamWriter.writeStartElement(PsiXmlUtils.ENTRY_TAG);
    }

    /**
     * <p>writeEndEntry.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeEndEntry() throws XMLStreamException {
        this.streamWriter.writeEndElement();
        this.elementCache.clear();
    }

    /**
     * <p>initialiseSubWriters.</p>
     *
     * @param extended a boolean.
     * @param named a boolean.
     * @param xmlType a {@link psidev.psi.mi.jami.xml.PsiXmlType} object.
     * @param interactionCategory a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @param complexType a {@link psidev.psi.mi.jami.model.ComplexType} object.
     */
    protected void initialiseSubWriters(boolean extended, boolean named, PsiXmlType xmlType, InteractionCategory interactionCategory,
                                        ComplexType complexType) {
        // basic sub writers
        // aliases
        PsiXmlElementWriter<Alias> aliasWriter = this.subWritersFactory.createAliasWriter(this.streamWriter);
        // attributes
        PsiXmlElementWriter<Annotation> attributeWriter = this.subWritersFactory.createAnnotationWriter(this.streamWriter);
        // xref
        PsiXmlXrefWriter xrefWriter = this.subWritersFactory.createXrefWriter(this.streamWriter, extended, attributeWriter);
        // publication
        PsiXmlPublicationWriter publicationWriter = this.subWritersFactory.createPublicationWriter(this.streamWriter, extended,
                attributeWriter, xrefWriter, this.version);
        // open cv
        PsiXmlVariableNameWriter<CvTerm> openCvWriter = this.subWritersFactory.createOpenCvWriter(this.streamWriter, extended, aliasWriter,
                attributeWriter, xrefWriter);
        // cv
        PsiXmlVariableNameWriter<CvTerm> cvWriter = this.subWritersFactory.createCvWriter(this.streamWriter, extended, aliasWriter, xrefWriter);
        // confidences
        PsiXmlElementWriter<Confidence>[] confidenceWriters = this.subWritersFactory.createConfidenceWriters(this.streamWriter, extended,
                getElementCache(), this.version, openCvWriter, publicationWriter);

        // organism writer
        PsiXmlElementWriter<Organism> organismWriter = this.subWritersFactory.createOrganismWriter(this.streamWriter, extended, aliasWriter,
                attributeWriter, xrefWriter, openCvWriter);

        // host organism (expressed in) writer
        PsiXmlElementWriter<Organism> hostOrganismWriter = this.subWritersFactory.createHostOrganismWriter(this.streamWriter, version, extended, getElementCache(), aliasWriter,
                attributeWriter, xrefWriter, openCvWriter);

        // checksum writer
        PsiXmlElementWriter<Checksum> checksumWriter = this.subWritersFactory.createChecksumWriter(this.streamWriter);
        // interactor writer
        PsiXmlElementWriter<Interactor> interactorWriter = this.subWritersFactory.createInteractorWriter(version, this.streamWriter, extended, getElementCache(),
                aliasWriter, attributeWriter, xrefWriter, cvWriter, organismWriter, checksumWriter);
        // experiment Writer
        PsiXmlExperimentWriter experimentWriter = this.subWritersFactory.createExperimentWriter(this.streamWriter, extended, getElementCache(),
                this.version, named, aliasWriter, attributeWriter, xrefWriter, publicationWriter, hostOrganismWriter, cvWriter,
                confidenceWriters[0]);
        // availability writer
        PsiXmlElementWriter<String> availabilityWriter = this.subWritersFactory.createAvailabilityWriter(this.streamWriter, getElementCache());
        // initialise source
        setSourceWriter(this.subWritersFactory.createSourceWriter(this.streamWriter, extended, this.version, aliasWriter, attributeWriter,
                xrefWriter, publicationWriter));
        // initialise optional writers
        initialiseOptionalWriters(experimentWriter, availabilityWriter, interactorWriter);
        // initialise interaction
        PsiXmlInteractionWriter[] interactionWriters = this.subWritersFactory.createInteractionWritersFor(this.streamWriter, getElementCache(),
                this.version, xmlType, interactionCategory, complexType, extended, named, aliasWriter, attributeWriter, xrefWriter,
                confidenceWriters, checksumWriter, cvWriter, openCvWriter, experimentWriter, availabilityWriter,
                interactorWriter, publicationWriter);
        setInteractionWriter(interactionWriters[0]);
        // initialise complex
        setComplexWriter(interactionWriters[1]);
        // initialise annotation writer
        setAnnotationsWriter(attributeWriter);
    }

    /**
     * <p>initialiseOptionalWriters.</p>
     *
     * @param experimentWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExperimentWriter} object.
     * @param availabilityWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param interactorWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    protected abstract void initialiseOptionalWriters(PsiXmlExperimentWriter experimentWriter, PsiXmlElementWriter<String> availabilityWriter,
                                                      PsiXmlElementWriter<Interactor> interactorWriter);

    /**
     * <p>initialiseDefaultElementCache.</p>
     */
    protected abstract void initialiseDefaultElementCache();

    /**
     * <p>Getter for the field <code>interactionWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlInteractionWriter} object.
     */
    protected PsiXmlInteractionWriter<T> getInteractionWriter() {
        return interactionWriter;
    }

    /**
     * <p>Getter for the field <code>complexWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlInteractionWriter} object.
     */
    protected PsiXmlInteractionWriter<ModelledInteraction> getComplexWriter() {
        return complexWriter;
    }

    /**
     * <p>Getter for the field <code>elementCache</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    protected PsiXmlObjectCache getElementCache() {
        if (elementCache == null){
           initialiseDefaultElementCache();
        }
        return elementCache;
    }

    /**
     * <p>Setter for the field <code>currentSource</code>.</p>
     *
     * @param currentSource a {@link psidev.psi.mi.jami.model.Source} object.
     */
    protected void setCurrentSource(Source currentSource) {
        this.currentSource = currentSource;
    }

    /**
     * <p>Getter for the field <code>currentSource</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Source} object.
     */
    protected Source getCurrentSource() {
        return currentSource;
    }

    /**
     * <p>Getter for the field <code>interactionsIterator</code>.</p>
     *
     * @return a {@link java.util.Iterator} object.
     */
    protected Iterator<? extends T> getInteractionsIterator() {
        return interactionsIterator;
    }

    /**
     * <p>Setter for the field <code>interactionsIterator</code>.</p>
     *
     * @param interactionsIterator a {@link java.util.Iterator} object.
     */
    protected void setInteractionsIterator(Iterator<? extends T> interactionsIterator) {
        this.interactionsIterator = interactionsIterator;
    }

    /**
     * <p>Setter for the field <code>interactionWriter</code>.</p>
     *
     * @param interactionWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlInteractionWriter} object.
     */
    protected void setInteractionWriter(PsiXmlInteractionWriter<T> interactionWriter) {
        if (interactionWriter == null){
            throw new IllegalArgumentException("The interaction writer cannot be null");
        }
        this.interactionWriter = interactionWriter;
    }

    /**
     * <p>Getter for the field <code>streamWriter</code>.</p>
     *
     * @return a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    protected XMLStreamWriter getStreamWriter() {
        return streamWriter;
    }

    /**
     * <p>Getter for the field <code>currentInteraction</code>.</p>
     *
     * @return a T object.
     */
    protected T getCurrentInteraction() {
        return currentInteraction;
    }

    /**
     * <p>Setter for the field <code>currentInteraction</code>.</p>
     *
     * @param currentInteraction a T object.
     */
    protected void setCurrentInteraction(T currentInteraction) {
        this.currentInteraction = currentInteraction;
    }

    /**
     * <p>isStarted.</p>
     *
     * @return a boolean.
     */
    protected boolean isStarted() {
        return started;
    }

    /**
     * <p>writeComplexesAsInteractors.</p>
     *
     * @return a boolean.
     */
    protected boolean writeComplexesAsInteractors() {
        return writeComplexesAsInteractors;
    }

    /**
     * <p>initialiseDefaultInteractionSet.</p>
     */
    protected void initialiseDefaultInteractionSet() {
        this.processedInteractions = Collections.newSetFromMap(new IdentityHashMap<Interaction, Boolean>());
    }

    /**
     * <p>Getter for the field <code>version</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     */
    protected PsiXmlVersion getVersion() {
        return version;
    }

    private void initialiseStreamWriter(XMLStreamWriter writer) {
        if (writer == null){
            throw new IllegalArgumentException("The writer cannot be null.");
        }
        this.streamWriter = writer;
        initialiseSubWriters();
    }

    private void initialiseWriter(Writer writer) throws XMLStreamException {
        if (writer == null){
            throw new IllegalArgumentException("The writer cannot be null.");
        }
        XMLOutputFactory outputFactory = XMLOutputFactory2.newInstance();
        XMLStreamWriter2 streamWriter2 = (XMLStreamWriter2)outputFactory.createXMLStreamWriter(writer);
        this.streamWriter = new IndentingXMLStreamWriter(streamWriter2);
        initialiseSubWriters();
    }

    private void initialiseOutputStream(OutputStream output) throws XMLStreamException {
        if (output == null){
            throw new IllegalArgumentException("The output stream cannot be null.");
        }

        XMLOutputFactory outputFactory = XMLOutputFactory2.newInstance();
        XMLStreamWriter2 streamWriter2 = (XMLStreamWriter2)outputFactory.createXMLStreamWriter(output, "UTF-8");
        this.streamWriter = new IndentingXMLStreamWriter(streamWriter2);
        initialiseSubWriters();
    }

    private void initialiseFile(File file) throws IOException, XMLStreamException {
        if (file == null){
            throw new IllegalArgumentException("The file cannot be null.");
        }

        XMLOutputFactory outputFactory = XMLOutputFactory2.newInstance();
        this.streamWriter = new IndentingXMLStreamWriter(outputFactory.createXMLStreamWriter(new FileOutputStream(file), "UTF-8"));
        initialiseSubWriters();
    }

    /**
     * <p>initialiseSubWriters.</p>
     */
    protected abstract void initialiseSubWriters();

    /**
     * <p>Getter for the field <code>subWritersFactory</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriterFactory} object.
     */
    protected PsiXmlElementWriterFactory getSubWritersFactory() {
        return subWritersFactory;
    }

    /**
     * <p>Setter for the field <code>subWritersFactory</code>.</p>
     *
     * @param subWritersFactory a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriterFactory} object.
     */
    protected void setSubWritersFactory(PsiXmlElementWriterFactory subWritersFactory) {
        this.subWritersFactory = subWritersFactory != null ? subWritersFactory : PsiXmlElementWriterFactory.getInstance();
    }

    private void registerInteractionForEntry(T interaction) {
        this.interactionsToWrite.clear();
        this.interactionsToWrite.add(interaction);
        this.interactionsIterator = this.interactionsToWrite.iterator();
        this.currentInteraction = null;
    }

    private void registerInteractionsForEntry(Collection<? extends T> interactions) {
        this.interactionsToWrite.clear();
        this.interactionsToWrite.addAll(interactions);
        this.interactionsIterator = this.interactionsToWrite.iterator();
        this.currentInteraction = null;
    }

    private void registerInteractionsForEntry(Iterator<? extends T> interactions) {
        this.interactionsToWrite.clear();
        this.interactionsIterator = interactions;
        this.currentInteraction = null;
    }

    protected ExtendedPsiXmlSource newXmlSource(String shortName) {
        switch (getVersion()) {
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlSource(shortName);
            case v3_0_0:
                return new psidev.psi.mi.jami.xml.model.extension.xml300.DefaultXmlSource(shortName);
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.DefaultXmlSource(shortName);
        }
    }
}

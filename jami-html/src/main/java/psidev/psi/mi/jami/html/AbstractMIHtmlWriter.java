package psidev.psi.mi.jami.html;

import psidev.psi.mi.jami.datasource.InteractionWriter;
import psidev.psi.mi.jami.factory.options.InteractionWriterOptions;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.html.utils.HtmlWriterOptions;
import psidev.psi.mi.jami.html.utils.HtmlWriterUtils;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.RangeUtils;

import java.io.*;
import java.util.*;

/**
 * Abstract class for MI HTML writer
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>06/12/13</pre>
 */
public abstract class AbstractMIHtmlWriter<T extends Interaction, P extends Participant, F extends Feature> implements InteractionWriter<T>{
    private Writer writer;
    private boolean isInitialised = false;
    private boolean writeHeader = true;

    private Set<ModelledInteraction> complexesToWrite;
    private Set<Object> processedObjects;
    private AbstractMIHtmlWriter<ModelledInteraction, ModelledParticipant, ModelledFeature> complexWriter;

    /**
     * <p>Constructor for AbstractMIHtmlWriter.</p>
     *
     * @param complexWriter a {@link psidev.psi.mi.jami.html.AbstractMIHtmlWriter} object.
     */
    public AbstractMIHtmlWriter(AbstractMIHtmlWriter<ModelledInteraction, ModelledParticipant, ModelledFeature> complexWriter){
        complexesToWrite = new HashSet<ModelledInteraction>();
        processedObjects = Collections.newSetFromMap(new IdentityHashMap<Object, Boolean>());
        this.complexWriter = complexWriter;
    }

    /**
     * <p>Constructor for AbstractMIHtmlWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param complexWriter a {@link psidev.psi.mi.jami.html.AbstractMIHtmlWriter} object.
     * @throws java.io.IOException if any.
     */
    public AbstractMIHtmlWriter(File file, AbstractMIHtmlWriter<ModelledInteraction, ModelledParticipant, ModelledFeature> complexWriter) throws IOException {

        initialiseFile(file);
        isInitialised = true;
        complexesToWrite = new HashSet<ModelledInteraction>();
        processedObjects = Collections.newSetFromMap(new IdentityHashMap<Object, Boolean>());
        this.complexWriter = complexWriter;
    }

    /**
     * <p>Constructor for AbstractMIHtmlWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param complexWriter a {@link psidev.psi.mi.jami.html.AbstractMIHtmlWriter} object.
     */
    public AbstractMIHtmlWriter(OutputStream output, AbstractMIHtmlWriter<ModelledInteraction, ModelledParticipant, ModelledFeature> complexWriter) {

        initialiseOutputStream(output);
        isInitialised = true;
        complexesToWrite = new HashSet<ModelledInteraction>();
        processedObjects = Collections.newSetFromMap(new IdentityHashMap<Object, Boolean>());
        this.complexWriter = complexWriter;
    }

    /**
     * <p>Constructor for AbstractMIHtmlWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param complexWriter a {@link psidev.psi.mi.jami.html.AbstractMIHtmlWriter} object.
     */
    public AbstractMIHtmlWriter(Writer writer, AbstractMIHtmlWriter<ModelledInteraction, ModelledParticipant, ModelledFeature> complexWriter) {

        initialiseWriter(writer);
        isInitialised = true;
        complexesToWrite = new HashSet<ModelledInteraction>();
        processedObjects = Collections.newSetFromMap(new IdentityHashMap<Object, Boolean>());
        this.complexWriter = complexWriter;
    }

    /**
     * <p>write</p>
     *
     * @param interaction a T object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(T interaction) throws MIIOException {
        write(interaction, null);
    }

    /**
     * <p>write</p>
     *
     * @param interaction a T object.
     * @param miScore : the MI score of the interaction to write
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(T interaction, Double miScore) throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The HTML writer was not initialised. The options for the PSI-MI HTML Writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        try {
            // start new table
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("    <table style=\"border-bottom: 1px solid #fff\" cellspacing=\"1\">");
            // add interaction as processed object
            if (this.processedObjects.add(interaction)){
                writer.write(HtmlWriterUtils.NEW_LINE);

                // writer interaction anchor
                writeInteractionAnchor(interaction);

                // write name
                if (interaction.getShortName() != null){
                    writeProperty("Name", interaction.getShortName());
                }

                // write general properties
                writeGeneralProperties(interaction);

                // write identifiers
                if (!interaction.getIdentifiers().isEmpty()){
                    writeSubTitle("Identifiers: ");
                    for (Object object : interaction.getIdentifiers()){
                        Xref ref = (Xref)object;
                        if (ref.getQualifier() != null){
                            writePropertyWithQualifier(ref.getDatabase().getShortName(), ref.getId(), ref.getQualifier().getShortName());
                        }
                        else {
                            writeProperty(ref.getDatabase().getShortName(), ref.getId());
                        }
                    }
                }

                // write experiment
                writeExperiment(interaction);

                // write participants
                if (!interaction.getParticipants().isEmpty()){
                    writeSubTitle("Participants: ");
                    for (Object participant : interaction.getParticipants()){
                        writeParticipant((P)participant);
                    }
                }

                // write type
                writeCvTerm("Interaction type", interaction.getInteractionType());

                // write xrefs
                if (!interaction.getXrefs().isEmpty()){
                    writeSubTitle("Xrefs: ");
                    for (Object object : interaction.getXrefs()){
                        Xref ref = (Xref)object;
                        if (ref.getQualifier() != null){
                            writePropertyWithQualifier(ref.getDatabase().getShortName(), ref.getId(), ref.getQualifier().getShortName());
                        }
                        else {
                            writeProperty(ref.getDatabase().getShortName(), ref.getId());
                        }
                    }
                }

                // write parameters
                writeParameters(interaction);

                // write confidences
                writeConfidences(interaction, miScore);

                // write cooperative effects
                writeCooperativeEffects(interaction);

                // write annotations
                if (!interaction.getAnnotations().isEmpty()){
                    writeSubTitle("Annotations: ");
                    for (Object object : interaction.getAnnotations()){
                        Annotation ref = (Annotation)object;
                        if (ref.getValue() != null){
                            writeProperty(ref.getTopic().getShortName(), ref.getValue());
                        }
                        else {
                            writeTag(ref.getTopic().getShortName());
                        }
                    }
                }

                // write checksums
                if (!interaction.getChecksums().isEmpty()){
                    writeSubTitle("Checksums: ");
                    for (Object object : interaction.getChecksums()){
                        Checksum ref = (Checksum)object;
                        writeProperty(ref.getMethod().getShortName(), ref.getValue());
                    }
                }

                // write created date
                if (interaction.getCreatedDate() != null){
                    writeProperty("Creation date", interaction.getCreatedDate().toString());
                }

                // write updated date
                if (interaction.getUpdatedDate() != null){
                    writeProperty("Last update", interaction.getUpdatedDate().toString());
                }

                // write end table
                writer.write("    </table><br/>");

                // write all remaining complexes
                writeSubComplexes();
            }

        } catch (IOException e) {
            throw new MIIOException("Impossible to write interaction "+interaction.toString());
        }
    }

    /**
     * <p>write</p>
     *
     * @param interactions a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(Collection<? extends T> interactions) throws MIIOException {
        Iterator<? extends T> binaryIterator = interactions.iterator();
        write(binaryIterator);
    }

    /**
     * <p>write</p>
     *
     * @param interactions a {@link java.util.Collection} object.
     * @param miScore : the MI score of the interactions to write
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(Collection<? extends T> interactions, Double miScore) throws MIIOException {
        Iterator<? extends T> binaryIterator = interactions.iterator();
        write(binaryIterator, miScore);
    }

    /** {@inheritDoc} */
    public void write(Iterator<? extends T> interactions) throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The HTML writer was not initialised. The options for the PSI-MI HTML Writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        while(interactions.hasNext()){
            write(interactions.next());
        }
    }

    /** {@inheritDoc} */
    public void write(Iterator<? extends T> interactions, Double miScore) throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The HTML writer was not initialised. The options for the PSI-MI HTML Writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        while(interactions.hasNext()){
            write(interactions.next(), miScore);
        }
    }

    /**
     * <p>start</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void start() throws MIIOException {
        this.processedObjects.clear();
        this.complexesToWrite.clear();
        try {
            if (writeHeader){
                writeStartDocument();
                writeHeader();
                writerStartBody();
            }
            writeHtmlStyle();
            writeInteractionList();
        } catch (IOException e) {
            throw new MIIOException("Cannot write the header and start body tag.", e);
        }
    }

    /**
     * <p>end</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void end() throws MIIOException {
        try {
            this.processedObjects.clear();
            this.complexesToWrite.clear();
            writer.write(HtmlWriterUtils.NEW_LINE);
            // end table
            writer.write("    </table><br/>");
            // end div
            writer.write("    </div>");
            writerEndBody();
            if (writeHeader){
                writeEndDocument();
            }
        } catch (IOException e) {
            throw new MIIOException("Cannot write the end body tag.", e);
        }
    }

    /** {@inheritDoc} */
    public void initialiseContext(Map<String, Object> options) {
        if (options == null && !isInitialised){
            throw new IllegalArgumentException("The options for the HTML writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        else if (options == null){
            return;
        }
        else if (options.containsKey(InteractionWriterOptions.OUTPUT_OPTION_KEY)){
            Object output = options.get(InteractionWriterOptions.OUTPUT_OPTION_KEY);

            if (output instanceof File){
                try {
                    initialiseFile((File) output);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Impossible to open and write in output file " + ((File)output).getName(), e);
                }
            }
            else if (output instanceof OutputStream){
                initialiseOutputStream((OutputStream) output);
            }
            else if (output instanceof Writer){
                initialiseWriter((Writer) output);
            }
            // suspect a file path
            else if (output instanceof String){
                try {
                    initialiseFile(new File((String)output));
                } catch (IOException e) {
                    throw new IllegalArgumentException("Impossible to open and write in output file " + output, e);
                }
            }
            else {
                throw new IllegalArgumentException("Impossible to write in the provided output "+output.getClass().getName() + ", a File, OuputStream, Writer or file path was expected.");
            }
        }
        else if (!isInitialised){
            throw new IllegalArgumentException("The options for the HTML writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }

        if (options.containsKey(HtmlWriterOptions.WRITE_HTML_HEADER_BODY_OPTION)){
            setWriteHeader((Boolean)options.get(HtmlWriterOptions.WRITE_HTML_HEADER_BODY_OPTION));
        }

        isInitialised = true;
    }

    /**
     * <p>flush</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void flush() throws MIIOException {
        if (isInitialised){
            try {
                writer.flush();
            } catch (IOException e) {
                throw new MIIOException("Impossible to flush the HTML writer", e);
            }
        }
    }

    /**
     * <p>close</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void close() throws MIIOException{
        if (isInitialised){
            try {
                writer.flush();
            } catch (IOException e) {
                throw new MIIOException("Impossible to flush the HTML writer", e);
            }
            finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new MIIOException("Impossible to close the HTML writer", e);
                }
                finally {
                    isInitialised = false;
                    writer = null;
                    this.writeHeader = true;
                    this.complexesToWrite.clear();
                    this.processedObjects.clear();
                }
            }
        }
    }
    /**
     * <p>reset</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void reset() throws MIIOException{
        if (isInitialised){
            try {
                writer.flush();
            } catch (IOException e) {
                throw new MIIOException("Impossible to flush the HTML writer", e);
            }
            finally {
                isInitialised = false;
                writer = null;
                this.writeHeader = true;
                this.complexesToWrite.clear();
                this.processedObjects.clear();
            }
        }
    }

    /**
     * <p>Setter for the field <code>writeHeader</code>.</p>
     *
     * @param writeHeader a boolean.
     */
    public void setWriteHeader(boolean writeHeader) {
        this.writeHeader = writeHeader;
    }

    /**
     * <p>writeCooperativeEffects</p>
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    protected abstract void writeCooperativeEffects(T interaction) throws IOException;

    /**
     * <p>writeConfidences</p>
     *
     * @param interaction a T object.
     * @param miScore : the MI score of the interaction to write
     * @throws java.io.IOException if any.
     */
    protected abstract void writeConfidences(T interaction, Double miScore) throws IOException;

    /**
     * <p>writeParameters</p>
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    protected abstract void writeParameters(T interaction) throws IOException;

    /**
     * <p>writeFeature</p>
     *
     * @param feature a F object.
     * @throws java.io.IOException if any.
     */
    protected void writeFeature(F feature) throws IOException {
        if (feature != null){
            String anchor = HtmlWriterUtils.getHtmlAnchorFor(feature);
            writer.write("        <tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("            <td class=\"table-title\"><a name=\"");
            writer.write(anchor);
            writer.write("\">Feature ");
            writer.write(anchor);
            writer.write("</a></td>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("            <td class=\"normal-cell\">");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("<table style=\"border: 1px solid #eee\" cellspacing=\"0\">");
            writer.write(HtmlWriterUtils.NEW_LINE);

            // write name
            if (feature.getShortName() != null){
                writeProperty("Name", feature.getShortName()+(feature.getFullName() != null ? ": "+feature.getFullName() : ""));
            }

            // write identifiers
            if (!feature.getIdentifiers().isEmpty()){
                writeSubTitle("Identifiers: ");
                for (Object object : feature.getIdentifiers()){
                    Xref ref = (Xref)object;
                    if (ref.getQualifier() != null){
                        writePropertyWithQualifier(ref.getDatabase().getShortName(), ref.getId(), ref.getQualifier().getShortName());
                    }
                    else {
                        writeProperty(ref.getDatabase().getShortName(), ref.getId());
                    }
                }
            }

            // write type
            writeCvTerm("Feature type", feature.getType());

            // write detection method
            writeDetectionMethods(feature);

            // write interaction dependency/feature role
            writeCvTerm("Interaction dependency", feature.getRole());

            // write xrefs
            if (!feature.getXrefs().isEmpty()){
                writeSubTitle("Xrefs: ");
                for (Object object : feature.getXrefs()){
                    Xref ref = (Xref)object;
                    if (ref.getQualifier() != null){
                        writePropertyWithQualifier(ref.getDatabase().getShortName(), ref.getId(), ref.getQualifier().getShortName());
                    }
                    else {
                        writeProperty(ref.getDatabase().getShortName(), ref.getId());
                    }
                }
            }

            // write annotations
            if (!feature.getAnnotations().isEmpty()){
                writeSubTitle("Annotations: ");
                for (Object object : feature.getAnnotations()){
                    Annotation ref = (Annotation)object;
                    if (ref.getValue() != null){
                        writeProperty(ref.getTopic().getShortName(), ref.getValue());
                    }
                    else {
                        writeTag(ref.getTopic().getShortName());
                    }
                }
            }

            // write ranges
            if (!feature.getRanges().isEmpty()){
                writeSubTitle("Feature ranges: ");
                for (Object ref : feature.getRanges()){
                    Range range = (Range)ref;
                    if (range.getParticipant() == null){
                        writeTag(RangeUtils.convertRangeToString(range));
                    }
                    else{
                        String anchor2 = HtmlWriterUtils.getHtmlAnchorFor(range.getParticipant());
                        writeProperty(RangeUtils.convertRangeToString(range), "<a href=\"#"+anchor2+"\">Participant "+anchor2+"</a>");
                    }
                }
            }

            writer.write("</table>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("</td></tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
        }
    }

    /**
     * <p>writeDetectionMethods</p>
     *
     * @param feature a F object.
     * @throws java.io.IOException if any.
     */
    protected abstract void writeDetectionMethods(F feature) throws IOException;

    /**
     * <p>writeCvTerm</p>
     *
     * @param label a {@link java.lang.String} object.
     * @param term a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @throws java.io.IOException if any.
     */
    protected void writeCvTerm(String label, CvTerm term) throws IOException {
        if (term != null){
            writer.write("        <tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("            <td class=\"table-title\">");
            writer.write(label);
            writer.write(":</td>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("         <td class=\"normal-cell\">");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("<table style=\"border: 1px solid #eee\" cellspacing=\"0\">");
            writer.write(HtmlWriterUtils.NEW_LINE);

            // write cv name
            writeProperty("Name", term.getFullName() != null ? term.getFullName() : term.getShortName());

            // write identifiers
            if (!term.getIdentifiers().isEmpty()){
                writeSubTitle("Identifiers: ");
                for (Xref ref : term.getIdentifiers()){
                    if (ref.getQualifier() != null){
                        writePropertyWithQualifier(ref.getDatabase().getShortName(), ref.getId(), ref.getQualifier().getShortName());
                    }
                    else {
                        writeProperty(ref.getDatabase().getShortName(), ref.getId());
                    }
                }
            }

            // write xrefs
            if (!term.getXrefs().isEmpty()){
                writeSubTitle("Xrefs: ");
                for (Xref ref : term.getXrefs()){
                    if (ref.getQualifier() != null){
                        writePropertyWithQualifier(ref.getDatabase().getShortName(), ref.getId(), ref.getQualifier().getShortName());
                    }
                    else {
                        writeProperty(ref.getDatabase().getShortName(), ref.getId());
                    }
                }
            }
            writer.write("</table>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("</td></tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
        }
    }

    /**
     * <p>writeOrganism</p>
     *
     * @param label a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @throws java.io.IOException if any.
     */
    protected void writeOrganism(String label, Organism organism) throws IOException {
        if (organism != null){
            writer.write("        <tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("            <td class=\"table-title\">");
            writer.write(label);
            writer.write(":</td>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("         <td class=\"normal-cell\" colspan=\"2\">");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("<table style=\"border: 1px solid #eee\" cellspacing=\"0\">");
            writer.write(HtmlWriterUtils.NEW_LINE);

            // write organism name
            writeProperty("Name", (organism.getCommonName() != null ? organism.getCommonName() : "unspecified")+(organism.getScientificName() != null ? ": "+organism.getScientificName() : ""));

            // write organism taxid
            writeProperty("TaxId", "<a href=\"https://www.uniprot.org/taxonomy/"+organism.getTaxId()+"\">"+organism.getTaxId()+"</a>");

            // write cell type
            writeCvTerm("Cell Type", organism.getCellType());

            // write tissue
            writeCvTerm("Tissue", organism.getTissue());

            // write compartment
            writeCvTerm("Compartment", organism.getCompartment());

            writer.write("</table>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("</td></tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
        }
    }

    /**
     * <p>writeInteractor</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @throws java.io.IOException if any.
     */
    protected void writeInteractor(Interactor interactor) throws IOException {
        // we have an interaction as participant of another interaction
        if (interactor instanceof Complex){
            String anchor = HtmlWriterUtils.getHtmlAnchorFor(interactor);
            writer.write("        <tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("            <td class=\"table-title\" colspan=\"2\"><a href=\"#");
            writer.write(anchor);
            writer.write("\">Interactor ");
            writer.write(anchor);
            writer.write("</a></td></tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);

            if (this.processedObjects.add(interactor)){
                this.complexesToWrite.add((Complex)interactor);
            }
        }
        // normal interactor
        else{
            String anchor = HtmlWriterUtils.getHtmlAnchorFor(interactor);
            writer.write("        <tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("            <td class=\"title\"><a name=\"");
            writer.write(anchor);
            writer.write("\">Interactor ");
            writer.write(anchor);
            writer.write("</a></td>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("       <td class=\"normal-cell\">");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("<table style=\"border: 1px solid #eee\" cellspacing=\"0\">");
            writer.write(HtmlWriterUtils.NEW_LINE);

            // write name
            writeProperty("Name", interactor.getShortName()+(interactor.getFullName() != null ? ": "+interactor.getFullName() : ""));

            // write identifiers
            if (!interactor.getIdentifiers().isEmpty()){
                writeSubTitle("Identifiers: ");
                for (Xref ref : interactor.getIdentifiers()){
                    if (ref.getQualifier() != null){
                        writePropertyWithQualifier(ref.getDatabase().getShortName(), ref.getId(), ref.getQualifier().getShortName());
                    }
                    else {
                        writeProperty(ref.getDatabase().getShortName(), ref.getId());
                    }
                }
            }

            // write organism
            writeOrganism("Organism", interactor.getOrganism());

            // write type
            writeCvTerm("Interactor type", interactor.getInteractorType());

            // write sequence if any
            if (interactor instanceof Polymer){
                Polymer polymer = (Polymer) interactor;
                writeProperty("sequence", convertSequence(polymer));
            }

            // write aliases
            if (!interactor.getAliases().isEmpty()){
                writeSubTitle("Aliases: ");
                for (Alias ref : interactor.getAliases()){
                    if (ref.getType() != null){
                        writeProperty(ref.getType().getShortName(), ref.getName());
                    }
                    else {
                        writeTag(ref.getName());
                    }
                }
            }

            // write xrefs
            if (!interactor.getXrefs().isEmpty()){
                writeSubTitle("Xrefs: ");
                for (Xref ref : interactor.getXrefs()){
                    if (ref.getQualifier() != null){
                        writePropertyWithQualifier(ref.getDatabase().getShortName(), ref.getId(), ref.getQualifier().getShortName());
                    }
                    else {
                        writeProperty(ref.getDatabase().getShortName(), ref.getId());
                    }
                }
            }

            // write annotations
            if (!interactor.getAnnotations().isEmpty()){
                writeSubTitle("Annotations: ");
                for (Annotation ref : interactor.getAnnotations()){
                    if (ref.getValue() != null){
                        writeProperty(ref.getTopic().getShortName(), ref.getValue());
                    }
                    else {
                        writeTag(ref.getTopic().getShortName());
                    }
                }
            }

            // write checksums
            if (!interactor.getChecksums().isEmpty()){
                writeSubTitle("Checksums: ");
                for (Checksum ref : interactor.getChecksums()){
                    writeProperty(ref.getMethod().getShortName(), ref.getValue());
                }
            }

            writer.write("</table>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("</td></tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
        }
    }

    /**
     * <p>writeGeneralProperties</p>
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    protected abstract void writeGeneralProperties(T interaction) throws IOException;

    /**
     * <p>writeExperiment</p>
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    protected abstract void writeExperiment(T interaction) throws IOException;

    /**
     * <p>writeParticipant</p>
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    protected void writeParticipant(P participant) throws IOException {
        if (participant != null){
            String anchor = HtmlWriterUtils.getHtmlAnchorFor(participant);
            writer.write("        <tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("            <td class=\"table-title\"><a name=\"");
            writer.write(anchor);
            writer.write("\">Participant ");
            writer.write(anchor);
            writer.write("</a></td>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("         <td class=\"normal-cell\">");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("<table style=\"border: 1px solid #eee\" cellspacing=\"0\">");
            writer.write(HtmlWriterUtils.NEW_LINE);

            // write interactor
            writeInteractor(participant.getInteractor());

            // write participant identification method
            writeParticipantIdentificationMethods(participant);

            // write biological role
            writeCvTerm("Biological role", participant.getBiologicalRole());

            // write experimental role
            writeExperimentalRole(participant);

            // write host organism
            writeExpressedInOrganism(participant);

            Stoichiometry stc = participant.getStoichiometry();
            if (stc != null){
                if (stc.getMaxValue() == stc.getMinValue()){
                    writeProperty("Stoichiometry", Integer.toString(participant.getStoichiometry().getMinValue()));
                }
                else{
                    writeProperty("Stoichiometry", "From "+Integer.toString(participant.getStoichiometry().getMinValue())+" to " +Long.toString(participant.getStoichiometry().getMaxValue()));
                }
            }

            // experimental preparations
            writeExperimentalPreparations(participant);

            // write parameters
            writeParameters(participant);

            // write confidences
            writeConfidences(participant);

            // write xrefs
            if (!participant.getXrefs().isEmpty()){
                writeSubTitle("Xrefs: ");
                for (Object object : participant.getXrefs()){
                    Xref ref = (Xref)object;
                    if (ref.getQualifier() != null){
                        writePropertyWithQualifier(ref.getDatabase().getShortName(), ref.getId(), ref.getQualifier().getShortName());
                    }
                    else {
                        writeProperty(ref.getDatabase().getShortName(), ref.getId());
                    }
                }
            }

            // write annotations
            if (!participant.getAnnotations().isEmpty()){
                writeSubTitle("Annotations: ");
                for (Object object : participant.getAnnotations()){
                    Annotation ref = (Annotation)object;
                    if (ref.getValue() != null){
                        writeProperty(ref.getTopic().getShortName(), ref.getValue());
                    }
                    else {
                        writeTag(ref.getTopic().getShortName());
                    }
                }
            }

            // write features
            if (!participant.getFeatures().isEmpty()){
                writeSubTitle("Features: ");
                for (Object ref : participant.getFeatures()){
                    writeFeature((F)ref);
                }
            }

            // write
            writer.write("</table>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("</td></tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
        }
    }

    /**
     * <p>writeConfidences</p>
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    protected abstract void writeConfidences(P participant) throws IOException;

    /**
     * <p>writeParameters</p>
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    protected abstract void writeParameters(P participant) throws IOException;

    /**
     * <p>writeExperimentalPreparations</p>
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    protected abstract void writeExperimentalPreparations(P participant) throws IOException;

    /**
     * <p>writeExpressedInOrganism</p>
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    protected abstract void writeExpressedInOrganism(P participant) throws IOException;

    /**
     * <p>writeExperimentalRole</p>
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    protected abstract void writeExperimentalRole(P participant) throws IOException;

    /**
     * <p>writeParticipantIdentificationMethods</p>
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    protected abstract void writeParticipantIdentificationMethods(P participant) throws IOException;

    /**
     * <p>writeSubTitle</p>
     *
     * @param subTitle a {@link java.lang.String} object.
     * @throws java.io.IOException if any.
     */
    protected void writeSubTitle(String subTitle) throws IOException {
        writer.write("<tr>");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("            <td class=\"table-subtitle\" colspan=\"2\">");
        writer.write(subTitle);
        writer.write("</td>");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("</tr>");
        writer.write(HtmlWriterUtils.NEW_LINE);
    }

    /**
     * <p>writeTag</p>
     *
     * @param property a {@link java.lang.String} object.
     * @throws java.io.IOException if any.
     */
    protected void writeTag(String property) throws IOException{
        if (property != null){
            writer.write("        <tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("            <td class=\"table-title\" colspan=\"2\">");
            writer.write(property);
            writer.write("</td>");
            writer.write("        </tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
        }
    }

    /**
     * <p>writeProperty</p>
     *
     * @param property a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     * @throws java.io.IOException if any.
     */
    protected void writeProperty(String property, String value) throws IOException{
        if (property != null && value != null){
            writer.write("        <tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("            <td class=\"table-title\">");
            writer.write(property);
            writer.write(":</td>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("            <td class=\"normal-cell\">");
            writer.write(value);
            writer.write("</td>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("      </tr>");
        }
    }

    /**
     * <p>writePropertyWithQualifier</p>
     *
     * @param property a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     * @param qualifier a {@link java.lang.String} object.
     * @throws java.io.IOException if any.
     */
    protected void writePropertyWithQualifier(String property, String value, String qualifier) throws IOException{
        if (property != null && value != null){
            writer.write("        <tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("            <td class=\"table-title\">");
            writer.write(property);
            writer.write(":</td>");
            writer.write(HtmlWriterUtils.NEW_LINE);
            writer.write("            <td class=\"normal-cell\">");
            writer.write(value);
            writer.write("<font color=\"lightgray\"> [");
            writer.write(qualifier);
            writer.write("]</font>");
            writer.write("</td>");
            writer.write("        </tr>");
            writer.write(HtmlWriterUtils.NEW_LINE);
        }
    }

    /**
     * <p>writeInteractionAnchor</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.Interaction} object.
     * @throws java.io.IOException if any.
     */
    protected void writeInteractionAnchor(Interaction interaction) throws IOException {
        String htmlAnchor = HtmlWriterUtils.getHtmlAnchorFor(interaction);
        writer.write("        <tr>");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("            <td class=\"title\" colspan=\"2\"><a name=\"");
        writer.write(htmlAnchor);
        writer.write("\">Interaction ");
        writer.write(htmlAnchor);
        writer.write("</a></td>");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        </tr>");
        writer.write(HtmlWriterUtils.NEW_LINE);
    }

    /**
     * <p>writeInteractionList</p>
     *
     * @throws java.io.IOException if any.
     */
    protected void writeInteractionList() throws IOException {
        // start table
        writer.write("    <div id=\"interactionList\">");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("    <h2>InteractionList</h2>");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("    <table style=\"border-bottom: 1px solid #fff\" cellspacing=\"1\">");
    }

    /**
     * <p>writerStartBody</p>
     *
     * @throws java.io.IOException if any.
     */
    protected void writerStartBody() throws IOException {
        writer.write("<body>");
        writer.write(HtmlWriterUtils.NEW_LINE);
    }

    /**
     * <p>writerEndBody</p>
     *
     * @throws java.io.IOException if any.
     */
    protected void writerEndBody() throws IOException {
        writer.write("</body>");
    }

    /**
     * <p>writeStartDocument</p>
     *
     * @throws java.io.IOException if any.
     */
    protected void writeStartDocument() throws IOException {
        writer.write("<html>");
    }

    /**
     * <p>writeEndDocument</p>
     *
     * @throws java.io.IOException if any.
     */
    protected void writeEndDocument() throws IOException {
        writer.write("</html>");
    }

    /**
     * <p>writeHeader</p>
     *
     * @throws java.io.IOException if any.
     */
    protected void writeHeader() throws IOException {
        writer.write("<head>");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("    <title>HUPO Proteomics Standards Initiative");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("    Molecular Interaction</title>");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writeHtmlStyle();
        writer.write("</head>");
        writer.write(HtmlWriterUtils.NEW_LINE);
    }

    /**
     * <p>writeHtmlStyle</p>
     *
     * @throws java.io.IOException if any.
     */
    protected void writeHtmlStyle() throws IOException {
        writer.write("    <style>");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        .title  {");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        background-color:   #ddd;");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        font-weight:        bold;}");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        .table-title    {");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        background-color:   #ddd;");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        width:              20%;");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        text-align:         right;");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        padding-right:       5px;");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        color: #666;");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        font-weight: bold;}");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        .table-subtitle    {");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        background-color:   #ddd;");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        font-style:         italic;}");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        .normal-cell {");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        background-color:   #eee;");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        text-align: left;");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        padding-left: 5px;}");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        .sequence   {");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        font-family:        \"Courier New\", monospace;");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        font-size:          11px;");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("        background-color:   #eee;}");
        writer.write(HtmlWriterUtils.NEW_LINE);
        writer.write("    </style>");
        writer.write(HtmlWriterUtils.NEW_LINE);

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
     * <p>Getter for the field <code>processedObjects</code>.</p>
     *
     * @return a {@link java.util.Set} object.
     */
    protected Set<Object> getProcessedObjects() {
        return processedObjects;
    }

    /**
     * <p>Getter for the field <code>complexesToWrite</code>.</p>
     *
     * @return a {@link java.util.Set} object.
     */
    protected Set<ModelledInteraction> getComplexesToWrite() {
        return complexesToWrite;
    }

    /**
     * <p>Getter for the field <code>complexWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.html.AbstractMIHtmlWriter} object.
     */
    protected AbstractMIHtmlWriter<ModelledInteraction, ModelledParticipant, ModelledFeature> getComplexWriter() {
        return complexWriter;
    }

    /**
     * <p>writeModelledInteraction</p>
     *
     * @param c a {@link psidev.psi.mi.jami.model.ModelledInteraction} object.
     */
    protected void writeModelledInteraction(ModelledInteraction c) {
        if (this.complexWriter != null){
            this.complexWriter.write(c);
            this.processedObjects.addAll(this.complexWriter.getProcessedObjects());
            this.complexesToWrite.addAll(this.complexWriter.getComplexesToWrite());
        }
    }

    private void writeSubComplexes() {
        while(!this.complexesToWrite.isEmpty()){
            Collection<ModelledInteraction> complexesToWrite = new ArrayList<ModelledInteraction>(this.complexesToWrite);
            this.complexesToWrite.clear();
            for (ModelledInteraction c : complexesToWrite){
                // write complexes
                writeModelledInteraction(c);
            }
        }
    }

    private String convertSequence(Polymer polymer) {
        if (polymer.getSequence() == null){
            return null;
        }

        StringBuffer buffer = new StringBuffer();
        char[] values = polymer.getSequence().toCharArray();
        int numberChar = 1;
        int numberChunk = 1;

        for (char aminoAcid : values){
            if (numberChar == 11){
                numberChar = 1;
                buffer.append(" ");
            }
            if (numberChunk == 11){
                numberChunk = 1;
                buffer.append(HtmlWriterUtils.NEW_LINE);
            }

            buffer.append(aminoAcid);
            numberChar++;
            numberChunk++;
        }

        return buffer.toString();
    }

    private void initialiseWriter(Writer writer) {
        if (writer == null){
            throw new IllegalArgumentException("The writer cannot be null.");
        }

        this.writer = writer;
        if (this.complexWriter != null){
            this.complexWriter.initialiseWriter(writer);
        }
    }

    private void initialiseOutputStream(OutputStream output) {
        if (output == null){
            throw new IllegalArgumentException("The output stream cannot be null.");
        }

        this.writer = new OutputStreamWriter(output);
        if (this.complexWriter != null){
            this.complexWriter.initialiseWriter(this.writer);
        }
    }

    private void initialiseFile(File file) throws IOException {
        if (file == null){
            throw new IllegalArgumentException("The file cannot be null.");
        }

        this.writer = new BufferedWriter(new FileWriter(file));
        if (this.complexWriter != null){
            this.complexWriter.initialiseWriter(this.writer);
        }
    }
}

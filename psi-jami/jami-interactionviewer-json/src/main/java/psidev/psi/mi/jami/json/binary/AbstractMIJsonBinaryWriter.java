package psidev.psi.mi.jami.json.binary;

import org.json.simple.JSONValue;
import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.datasource.InteractionWriter;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.InteractionWriterOptions;
import psidev.psi.mi.jami.json.MIJsonUtils;
import psidev.psi.mi.jami.json.MIJsonWriterOptions;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.*;
import psidev.psi.mi.jami.utils.comparator.interactor.UnambiguousExactInteractorBaseComparator;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract JSON writer for binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>03/07/13</pre>
 */

public abstract class AbstractMIJsonBinaryWriter<I extends BinaryInteraction> implements InteractionWriter<I> {

    private boolean isInitialised = false;
    private Writer writer;
    private boolean hasOpened = false;
    private Set<String> processedInteractors;
    private static final Logger logger = Logger.getLogger("MitabParserLogger");
    private Integer expansionId;
    private Collection<Feature> experimentalFeatures;
    private Collection<Feature> bindingSites;
    private Collection<Feature> mutations;
    private Collection<Feature> ptms;
    private Collection<Feature> otherFeatures;
    private OntologyTermFetcher fetcher;

    public AbstractMIJsonBinaryWriter(){
        processedInteractors = new HashSet<String>();
        initialiseFeatureCollections();
    }

    public AbstractMIJsonBinaryWriter(File file, OntologyTermFetcher fetcher) throws IOException {

        initialiseFile(file);
        processedInteractors = new HashSet<String>();
        initialiseFeatureCollections();
        if (fetcher == null){
            logger.warning("The ontology fetcher is null so all the features will be listed as otherFeatures");
        }
        this.fetcher = fetcher;
    }

    public AbstractMIJsonBinaryWriter(OutputStream output, OntologyTermFetcher fetcher) {

        initialiseOutputStream(output);
        processedInteractors = new HashSet<String>();
        initialiseFeatureCollections();
        if (fetcher == null){
            logger.warning("The ontology fetcher is null so all the features will be listed as otherFeatures");
        }
        this.fetcher = fetcher;
    }

    public AbstractMIJsonBinaryWriter(Writer writer, OntologyTermFetcher fetcher) {

        initialiseWriter(writer);
        processedInteractors = new HashSet<String>();
        initialiseFeatureCollections();
        if (fetcher == null){
            logger.warning("The ontology fetcher is null so all the features will be listed as otherFeatures");
        }
        this.fetcher = fetcher;
    }

    public void initialiseContext(Map<String, Object> options) {
        if (options == null && !isInitialised){
            throw new IllegalArgumentException("The options for the json writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions and "+ MIJsonWriterOptions.ONTOLOGY_FETCHER_OPTION_KEY+" to know which OntologyTermFetcher to use.");
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

            if (options.containsKey(MIJsonWriterOptions.ONTOLOGY_FETCHER_OPTION_KEY)){
                this.fetcher = (OntologyTermFetcher) options.get(MIJsonWriterOptions.ONTOLOGY_FETCHER_OPTION_KEY);
            }
            else{
                logger.warning("The ontology fetcher is null so all the features will be listed as otherFeatures");
            }
        }
        else if (!isInitialised){
            throw new IllegalArgumentException("The options for the json writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions and "+ MIJsonWriterOptions.ONTOLOGY_FETCHER_OPTION_KEY+" to know which OntologyTermFetcher to use.");
        }

        isInitialised = true;
    }

    public void start() throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The json writer has not been initialised. The options for the json writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions and "+ MIJsonWriterOptions.ONTOLOGY_FETCHER_OPTION_KEY+" to know which OntologyTermFetcher to use.");
        }
        try {
            writeStart();
        } catch (IOException e) {
            throw new MIIOException("Impossible to write start of JSON file", e);
        }
    }

    public void end() throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The json writer has not been initialised. The options for the json writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions and "+ MIJsonWriterOptions.ONTOLOGY_FETCHER_OPTION_KEY+" to know which OntologyTermFetcher to use.");
        }
        try {
            writeEnd();
        } catch (IOException e) {
            throw new MIIOException("Impossible to write end of JSON file", e);
        }
    }

    public void write(I interaction) throws MIIOException {
        if (!isInitialised){
            throw new IllegalStateException("The json writer has not been initialised. The options for the json writer should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions and "+ MIJsonWriterOptions.ONTOLOGY_FETCHER_OPTION_KEY+" to know which OntologyTermFetcher to use.");
        }

        try{
            Participant A = interaction.getParticipantA();
            Participant B = interaction.getParticipantB();

            if (A != null || B != null){
                // write start element and interactor and beginning of interaction
                if (!hasOpened){
                    hasOpened = true;

                    if (A != null && B != null){
                        registerAndWriteInteractor(A, false);
                        registerAndWriteInteractor(B, true);
                    }
                    else if (A != null){
                        registerAndWriteInteractor(A, false);
                    }
                    else {
                        registerAndWriteInteractor(B, false);
                    }
                }
                // write interactors
                else if (A != null && B != null){
                    registerAndWriteInteractor(A, true);
                    registerAndWriteInteractor(B, true);
                }
                else if (A != null){
                    registerAndWriteInteractor(A, true);
                }
                else {
                    registerAndWriteInteractor(B, true);
                }

                writeInteraction(interaction, A, B);
            }
            else {
                logger.log(Level.WARNING, "Ignore interaction as it does not contain any participants : "+interaction.toString());
            }
        }
        catch (IOException e) {
            throw new MIIOException("Impossible to write " +interaction.toString(), e);
        }
    }

    public void write(Collection<? extends I> interactions) throws MIIOException {
        Iterator<? extends I> binaryIterator = interactions.iterator();
        write(binaryIterator);
    }

    public void write(Iterator<? extends I> interactions) throws MIIOException {
        while(interactions.hasNext()){
            write(interactions.next());
        }
    }

    public void flush() throws MIIOException{
        if (isInitialised){
            try {
                writer.flush();
            } catch (IOException e) {
                throw new MIIOException("Impossible to flush the JSON writer", e);
            }
        }
    }

    public void close() throws MIIOException{
        if (isInitialised){

            try {
                flush();
            }
            finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new MIIOException("Impossible to close the JSON writer", e);
                }
            }
            clearFeatureCollections();
            isInitialised = false;
            writer = null;
            hasOpened = false;
            processedInteractors.clear();
            expansionId = null;
            this.fetcher = null;
        }
    }

    public void reset() throws MIIOException {
        if (isInitialised){

            try {
                writer.flush();
            }
            catch (IOException e) {
                throw new MIIOException("Impossible to close the JSON writer", e);
            }
            finally {
                clearFeatureCollections();
                isInitialised = false;
                writer = null;
                hasOpened = false;
                processedInteractors.clear();
                expansionId = null;
                this.fetcher = null;
            }
        }
    }

    public void setExpansionId(Integer expansionId) {
        this.expansionId = expansionId;
    }

    protected void writeFeatures(String name, Collection<Feature> features) throws IOException {
        writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
        writeNextPropertySeparatorAndIndent();
        writer.write(MIJsonUtils.INDENT);
        writeStartObject(name);
        writer.write(MIJsonUtils.OPEN_ARRAY);

        Iterator<Feature> featureIterator = features.iterator();
        while (featureIterator.hasNext()){
            Feature feature = featureIterator.next();
            writeFeature(feature);
            if (featureIterator.hasNext()){
                writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            }
        }

        writer.write(MIJsonUtils.CLOSE_ARRAY);
    }

    protected void writeAllFeatures(Collection<Feature> features) throws IOException {
        // first split the features in the proper collection of features
        clearFeatureCollections();
        Iterator<Feature> featureIterator = features.iterator();
        while (featureIterator.hasNext()){
            recognizeFeatureTypeAndSplitInFeatureCollections(featureIterator.next());
        }

        if (!experimentalFeatures.isEmpty()){
            writeFeatures("experimentalFeatures", experimentalFeatures);
        }
        if (!mutations.isEmpty()){
            writeFeatures("pointMutations", mutations);
        }
        if (!bindingSites.isEmpty()){
            writeFeatures("bindingSites", bindingSites);
        }
        if (!ptms.isEmpty()){
            writeFeatures("ptms", ptms);
        }
        if (!otherFeatures.isEmpty()){
            writeFeatures("otherFeatures", otherFeatures);
        }
    }

    protected void recognizeFeatureTypeAndSplitInFeatureCollections(Feature feature) {

        // feature type is not null, we can recognize the feature
        if (feature.getType() != null){
            CvTerm type = feature.getType();
            // all mod terms are ptms
            if (type.getMODIdentifier() != null){
                ptms.add(feature);
            }
            else if (fetcher == null){
                if (CvTermUtils.isCvTerm(type, Feature.BINDING_SITE_MI, Feature.BINDING_SITE)) {
                    bindingSites.add(feature);
                }
                else if (CvTermUtils.isCvTerm(type, Feature.MUTATION_MI, Feature.MUTATION) ||
                        CvTermUtils.isCvTerm(type, Feature.VARIANT_MI, Feature.VARIANT)) {
                    mutations.add(feature);
                }
                else if (CvTermUtils.isCvTerm(type, Feature.EXPERIMENTAL_FEATURE_MI, Feature.EXPERIMENTAL_FEATURE)) {
                    experimentalFeatures.add(feature);
                }
                else if (CvTermUtils.isCvTerm(type, Feature.ALLOSTERIC_PTM_MI, Feature.ALLOSTERIC_PTM)) {
                    ptms.add(feature);
                }
                else {
                    otherFeatures.add(feature);
                }
            }
            else if (type.getMIIdentifier() != null){
                OntologyTerm term = null;
                try {
                    term = fetcher.fetchByIdentifier(type.getMIIdentifier(), CvTerm.PSI_MI);
                } catch (BridgeFailedException e) {
                    logger.log(Level.SEVERE, "Cannot fetch the ontology information for the term " + type.getMIIdentifier(), e);
                }

                // we cannot retrieve the MI term
                if (term == null){
                    otherFeatures.add(feature);
                }
                else if (OntologyTermUtils.isCvTermChildOf(term, Feature.BINDING_SITE_MI, Feature.BINDING_SITE)) {
                    bindingSites.add(feature);
                }
                else if (OntologyTermUtils.isCvTermChildOf(term, Feature.MUTATION_MI, Feature.MUTATION) ||
                        OntologyTermUtils.isCvTermChildOf(term, Feature.VARIANT_MI, Feature.VARIANT)) {
                    mutations.add(feature);
                }
                else if (OntologyTermUtils.isCvTermChildOf(term, Feature.EXPERIMENTAL_FEATURE_MI, Feature.EXPERIMENTAL_FEATURE)) {
                    experimentalFeatures.add(feature);
                }
                else if (OntologyTermUtils.isCvTermChildOf(term, Feature.ALLOSTERIC_PTM_MI, Feature.ALLOSTERIC_PTM)){
                    ptms.add(feature);
                }
                else {
                    otherFeatures.add(feature);
                }
            }
            else {
                OntologyTerm term = null;
                String name = type.getFullName() != null ? type.getFullName() : type.getShortName();
                try {
                    term = fetcher.fetchByName(name, CvTerm.PSI_MI);
                    if (term == null){
                        term = fetcher.fetchByName(name, CvTerm.PSI_MOD);
                    }
                } catch (BridgeFailedException e) {
                    logger.log(Level.SEVERE, "Cannot fetch the ontology information for the term " + (type.getFullName() != null ? type.getFullName() : type.getShortName()), e);
                }

                // cannot retrieve the term using name
                if (term == null){
                    otherFeatures.add(feature);
                }
                else if (term.getMODIdentifier() != null){
                    ptms.add(feature);
                }
                else if (OntologyTermUtils.isCvTermChildOf(term, Feature.BINDING_SITE_MI, Feature.BINDING_SITE)) {
                    bindingSites.add(feature);
                }
                else if (OntologyTermUtils.isCvTermChildOf(term, Feature.MUTATION_MI, Feature.MUTATION) ||
                        OntologyTermUtils.isCvTermChildOf(term, Feature.VARIANT_MI, Feature.VARIANT)) {
                    mutations.add(feature);
                }
                else if (OntologyTermUtils.isCvTermChildOf(term, Feature.EXPERIMENTAL_FEATURE_MI, Feature.EXPERIMENTAL_FEATURE)) {
                    experimentalFeatures.add(feature);
                }
                else if (OntologyTermUtils.isCvTermChildOf(term, Feature.ALLOSTERIC_PTM_MI, Feature.ALLOSTERIC_PTM)){
                    ptms.add(feature);
                }
                else {
                    otherFeatures.add(feature);
                }
            }
        }
        // we cannot recognize the feature
        else {
            otherFeatures.add(feature);
        }
    }

    protected void writeFeature(Feature feature) throws IOException {

        writeNextPropertySeparatorAndIndent();
        writer.write(MIJsonUtils.INDENT);
        writer.write(MIJsonUtils.INDENT);
        writer.write(MIJsonUtils.OPEN);
        writeNextPropertySeparatorAndIndent();
        writer.write(MIJsonUtils.INDENT);
        writer.write(MIJsonUtils.INDENT);

        // write identifier == hashcode of feature
        writerProperty("id", Integer.toString(feature.hashCode()));

        // write name
        if (feature.getFullName() != null){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writer.write(MIJsonUtils.INDENT);
            writer.write(MIJsonUtils.INDENT);
            writerProperty("name", JSONValue.escape(feature.getFullName()));
        }
        else if (feature.getShortName() != null){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writer.write(MIJsonUtils.INDENT);
            writer.write(MIJsonUtils.INDENT);
            writerProperty("name", JSONValue.escape(feature.getShortName()));
        }

        // write type
        if (feature.getType() != null){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writer.write(MIJsonUtils.INDENT);
            writer.write(MIJsonUtils.INDENT);
            writeStartObject("type");
            writeCvTerm(feature.getType());
        }

        // detection methods
        writeFeatureProperties(feature);

        // ranges
        if (!feature.getRanges().isEmpty()){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writer.write(MIJsonUtils.INDENT);
            writer.write(MIJsonUtils.INDENT);
            writeStartObject("sequenceData");
            writer.write(MIJsonUtils.OPEN_ARRAY);

            Iterator<Range> rangeIterator = feature.getRanges().iterator();
            while (rangeIterator.hasNext()){
                Range range = rangeIterator.next();
                writeRange(range);
                if (rangeIterator.hasNext()){
                    writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
                }
            }

            writer.write(MIJsonUtils.CLOSE_ARRAY);
        }

        // write linked features if required
        if (!feature.getLinkedFeatures().isEmpty()){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writer.write(MIJsonUtils.INDENT);
            writer.write(MIJsonUtils.INDENT);
            writeStartObject("linkedFeatures");
            writer.write(MIJsonUtils.OPEN_ARRAY);

            Iterator<Feature> featureIterator = feature.getLinkedFeatures().iterator();
            while (featureIterator.hasNext()){
                Feature f = featureIterator.next();
                writer.write(MIJsonUtils.PROPERTY_DELIMITER);
                writer.write(Integer.toString(f.hashCode()));
                writer.write(MIJsonUtils.PROPERTY_DELIMITER);
                if (featureIterator.hasNext()){
                    writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
                }
            }

            writer.write(MIJsonUtils.CLOSE_ARRAY);
        }

        // write interpro if required
        if (feature.getInterpro() != null){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writer.write(MIJsonUtils.INDENT);
            writer.write(MIJsonUtils.INDENT);
            writerProperty("InterPro", JSONValue.escape(feature.getInterpro()));
        }

        writeNextPropertySeparatorAndIndent();
        writer.write(MIJsonUtils.INDENT);
        writer.write(MIJsonUtils.INDENT);
        writer.write(MIJsonUtils.CLOSE);
    }

    protected abstract void writeFeatureProperties(Feature feature) throws IOException;

    protected void writeRange(Range range) throws IOException {
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);
        writer.write(RangeUtils.convertRangeToString(range));
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);
    }

    protected void writeParticipant(Participant participant, String name) throws IOException {

        writeStartObject(name);
        writer.write(MIJsonUtils.OPEN);
        writeNextPropertySeparatorAndIndent();
        writer.write(MIJsonUtils.INDENT);

        // write identifier
        writeStartObject("identifier");
        Xref preferredIdentifier = participant.getInteractor().getPreferredIdentifier();
        String interactorId = null;
        String db = null;
        if (preferredIdentifier != null){
            interactorId = JSONValue.escape(preferredIdentifier.getId());
            db = JSONValue.escape(preferredIdentifier.getDatabase().getShortName());
        }
        else{
            interactorId = Integer.toString(UnambiguousExactInteractorBaseComparator.hashCode(participant.getInteractor()));
            db = "generated";
        }
        writeIdentifier(db, interactorId);

        // write biorole
        getWriter().write(MIJsonUtils.ELEMENT_SEPARATOR);
        writeNextPropertySeparatorAndIndent();
        getWriter().write(MIJsonUtils.INDENT);
        writeStartObject("bioRole");
        writeCvTerm(participant.getBiologicalRole());

        writeParticipantProperties(participant);

        // features
        if (!participant.getFeatures().isEmpty()){
            writeAllFeatures(participant.getFeatures());
        }

        writeNextPropertySeparatorAndIndent();
        writer.write(MIJsonUtils.CLOSE);
    }

    protected abstract void writeParticipantProperties(Participant participant) throws IOException;

    protected void writeAnnotation(String text) throws IOException {
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);
        writer.write(text != null ? text : "");
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);

    }

    protected void writeAllAnnotations(Collection<Annotation> figures) throws IOException {
        writer.write(MIJsonUtils.OPEN_ARRAY);

        Iterator<Annotation> annotIterator = figures.iterator();
        while (annotIterator.hasNext()){
            Annotation annot = annotIterator.next();
            writeAnnotation(annot != null ? JSONValue.escape(annot.getValue()):"");
            if (annotIterator.hasNext()){
                writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            }
        }

        writer.write(MIJsonUtils.CLOSE_ARRAY);
    }

    protected void writePublication(Publication publication) throws IOException {
        // publication identifiers
        if (!publication.getIdentifiers().isEmpty()){
            writeStartObject("pubid");
            writeAllIdentifiers(publication.getIdentifiers(), publication.getImexId());
        }

        // publication source
        if (publication.getSource() != null){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writer.write(MIJsonUtils.INDENT);
            writeStartObject("sourceDatabase");
            writeCvTerm(publication.getSource());
        }
    }

    protected void writeExpansionMethod(CvTerm expansion) throws IOException {
        writer.write(MIJsonUtils.OPEN);
        if (expansionId != null){
            writerProperty("id", Integer.toString(expansionId));
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
        }
        writerProperty("name", JSONValue.escape(expansion.getShortName()));
        writer.write(MIJsonUtils.CLOSE);
    }

    protected void writeParameter(String type, String value, String unit) throws IOException {
        writer.write(MIJsonUtils.OPEN);
        writerProperty("type", type);
        writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
        writerProperty("value", value);
        if (unit != null){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writerProperty("unit", unit);
        }
        writer.write(MIJsonUtils.CLOSE);
    }

    protected <P extends Parameter> void writeAllParameters(Collection<P> parameters) throws IOException {
        writer.write(MIJsonUtils.OPEN_ARRAY);

        Iterator<P> paramIterator = parameters.iterator();
        while (paramIterator.hasNext()){
            P param = paramIterator.next();
            if (param != null){
                writeParameter(JSONValue.escape(param.getType().getShortName()), JSONValue.escape(ParameterUtils.getParameterValueAsString(param)), param.getUnit() != null ? JSONValue.escape(param.getUnit().getShortName()) : null);
            }
            else {
                writeParameter("unknown","", null);
            }
            if (paramIterator.hasNext()){
                writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            }
        }

        writer.write(MIJsonUtils.CLOSE_ARRAY);
    }

    protected void writeConfidence(String type, String value) throws IOException {
        writer.write(MIJsonUtils.OPEN);
        writerProperty("type", type);
        writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
        writerProperty("value", value);
        writer.write(MIJsonUtils.CLOSE);
    }

    protected <C extends Confidence> void writeAllConfidences(Collection<C> confidences) throws IOException {
        writer.write(MIJsonUtils.OPEN_ARRAY);

        Iterator<C> confidencesIterator = confidences.iterator();
        while (confidencesIterator.hasNext()){
            C conf = confidencesIterator.next();
            if (conf != null){
                writeConfidence(JSONValue.escape(conf.getType().getShortName()), JSONValue.escape(conf.getValue()));
            }
            else {
                writeConfidence("unknown","");
            }
            if (confidencesIterator.hasNext()){
                writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            }
        }

        writer.write(MIJsonUtils.CLOSE_ARRAY);
    }

    protected void writeAllIdentifiers(Collection<Xref> identifiers, String imex) throws IOException {
        writer.write(MIJsonUtils.OPEN_ARRAY);

        Iterator<Xref> identifierIterator = identifiers.iterator();
        while (identifierIterator.hasNext()){
            Xref identifier = identifierIterator.next();
            if (identifier != null){
                writeIdentifier(JSONValue.escape(identifier.getDatabase().getShortName()), JSONValue.escape(identifier.getId()));
            }
            else {
                writeIdentifier("unknown", JSONValue.escape(identifier.getId()));
            }
            if (identifierIterator.hasNext()){
                writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            }
        }

        if (imex != null){
            if (!identifiers.isEmpty()){
                writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            }
            writeIdentifier("imex", JSONValue.escape(imex));
        }

        writer.write(MIJsonUtils.CLOSE_ARRAY);
    }

    protected abstract boolean writeInteractionProperties(I interaction) throws IOException;

    protected void writeIdentifier(String db, String id) throws IOException {
        writer.write(MIJsonUtils.OPEN);
        writerProperty("db", db);
        writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
        writerProperty("id", id);
        writer.write(MIJsonUtils.CLOSE);
    }

    protected void writeOrganism(Organism organism) throws IOException {
        writer.write(MIJsonUtils.OPEN);
        writerProperty("taxid", Integer.toString(organism.getTaxId()));
        if (organism.getCommonName() != null){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writerProperty("common", JSONValue.escape(organism.getCommonName()));
        }
        if (organism.getScientificName() != null){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writerProperty("scientific", JSONValue.escape(organism.getScientificName()));
        }
        writer.write(MIJsonUtils.CLOSE);
    }

    protected void writeCvTerm(CvTerm term) throws IOException {
        writer.write(MIJsonUtils.OPEN);
        boolean hasId = false;
        if (term.getMIIdentifier() != null){
            writerProperty("id", JSONValue.escape(term.getMIIdentifier()));
            hasId = true;
        }
        else if (term.getMODIdentifier() != null){
            writerProperty("id", JSONValue.escape(term.getMODIdentifier()));
            hasId = true;
        }
        else if (term.getPARIdentifier() != null){
            writerProperty("id", JSONValue.escape(term.getPARIdentifier()));
            hasId = true;
        }

        if (hasId){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
        }

        if (term.getFullName() != null){
            writerProperty("name", JSONValue.escape(term.getFullName()));
        }
        else {
            writerProperty("name", JSONValue.escape(term.getShortName()));
        }
        writer.write(MIJsonUtils.CLOSE);
    }

    protected void writeStartObject(String object) throws IOException {
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);
        writer.write(object);
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);
        writer.write(MIJsonUtils.PROPERTY_VALUE_SEPARATOR);
    }

    protected void writerProperty(String propertyName, String value) throws IOException {
        writeStartObject(propertyName);
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);
        writer.write(value);
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);
    }

    protected void writeNextPropertySeparatorAndIndent() throws IOException {
        writer.write(MIJsonUtils.LINE_SEPARATOR);
        writer.write(MIJsonUtils.INDENT);
        writer.write(MIJsonUtils.INDENT);
    }

    protected void writeInteraction(I binary, Participant A, Participant B) throws IOException {
        writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
        writer.write(MIJsonUtils.LINE_SEPARATOR);
        writer.write(MIJsonUtils.INDENT);
        writer.write(MIJsonUtils.OPEN);

        writeNextPropertySeparatorAndIndent();
        writerProperty("object","interaction");

        // first interaction properties such as experiment, etc
        boolean hasProperties = writeInteractionProperties(binary);

        // then interaction type
        boolean hasType = binary.getInteractionType() != null;
        if (hasType){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writeStartObject("interactionType");
            writeCvTerm(binary.getInteractionType());
        }

        // then interaction identifiers
        boolean hasIdentifiers = !binary.getIdentifiers().isEmpty();
        if (hasIdentifiers){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writeStartObject("identifiers");
            writeAllIdentifiers(binary.getIdentifiers(), extractImexIdFrom(binary));
        }

        // then confidences
        writeConfidences(binary);

        // then parameters
        writeParameters(binary);

        // then complex expansion
        boolean hasComplexExpansion = binary.getComplexExpansion() != null;
        if (hasComplexExpansion){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writeStartObject("expansion");
            writeExpansionMethod(binary.getComplexExpansion());
        }

        // then participant A and B
        if (A != null){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writeParticipant(A, "source");

            if (B != null){
                writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
                writeNextPropertySeparatorAndIndent();
                writeParticipant(B, "target");
            }
            else {
                writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
                writeNextPropertySeparatorAndIndent();
                writeParticipant(A, "target");
                writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
                writeNextPropertySeparatorAndIndent();
                writerProperty("intramolecular", "true");
            }
        }
        else if (B != null){
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writeParticipant(B, "source");
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writeParticipant(B, "target");
            writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
            writeNextPropertySeparatorAndIndent();
            writerProperty("intramolecular", "true");
        }

        writer.write(MIJsonUtils.LINE_SEPARATOR);
        writer.write(MIJsonUtils.INDENT);
        writer.write(MIJsonUtils.CLOSE);
    }

    protected abstract void writeParameters(I binary) throws IOException;

    protected abstract void writeConfidences(I binary) throws IOException;

    protected abstract String extractImexIdFrom(I binary);

    protected void registerAndWriteInteractor(Participant participant, boolean writeElementSeparator) throws IOException {
        if (participant != null){
            Interactor interactor = participant.getInteractor();

            Xref preferredIdentifier = interactor.getPreferredIdentifier();
            String interactorId = null;
            String db = null;
            if (preferredIdentifier != null){
                interactorId = JSONValue.escape(preferredIdentifier.getId());
                db = JSONValue.escape(preferredIdentifier.getDatabase().getShortName());
            }
            else{
                interactorId = Integer.toString(UnambiguousExactInteractorBaseComparator.hashCode(interactor));
                db = "generated";
            }
            String interactorKey = db+"_"+interactorId;

            // if the interactor has not yet been processed, we write the interactor
            if (processedInteractors.add(interactorKey)){
                if (writeElementSeparator){
                    writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
                    writer.write(MIJsonUtils.LINE_SEPARATOR);
                }
                writer.write(MIJsonUtils.INDENT);
                writer.write(MIJsonUtils.OPEN);
                writeNextPropertySeparatorAndIndent();
                writerProperty("object","interactor");

                // write sequence if possible
                if (interactor instanceof Polymer){
                    Polymer polymer = (Polymer) interactor;
                    if (polymer.getSequence() != null){
                        writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
                        writeNextPropertySeparatorAndIndent();
                        writerProperty("sequence", JSONValue.escape(polymer.getSequence()));
                    }
                }
                // write interactor type
                writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
                writeNextPropertySeparatorAndIndent();
                writeStartObject("type");
                writeCvTerm(interactor.getInteractorType());

                // write organism
                if (interactor.getOrganism() != null){
                    writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
                    writeNextPropertySeparatorAndIndent();
                    writeStartObject("organism");
                    writeOrganism(interactor.getOrganism());
                }
                // write accession
                writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
                writeNextPropertySeparatorAndIndent();
                writeStartObject("identifier");
                writeIdentifier(db, interactorId);

                // write label
                writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
                writeNextPropertySeparatorAndIndent();
                writerProperty("label", JSONValue.escape(interactor.getShortName()));
                writer.write(MIJsonUtils.LINE_SEPARATOR);
                writer.write(MIJsonUtils.INDENT);
                writer.write(MIJsonUtils.CLOSE);
            }
        }
    }

    protected void writeStart() throws IOException {
        writer.write(MIJsonUtils.OPEN);
        writer.write(MIJsonUtils.LINE_SEPARATOR);
        writeStartObject("data");
        writer.write(MIJsonUtils.OPEN_ARRAY);
        writer.write(MIJsonUtils.LINE_SEPARATOR);
    }

    protected void writeEnd() throws IOException {
        writer.write(MIJsonUtils.LINE_SEPARATOR);
        writer.write(MIJsonUtils.INDENT);
        writer.write(MIJsonUtils.CLOSE_ARRAY);
        writer.write(MIJsonUtils.LINE_SEPARATOR);
        writer.write(MIJsonUtils.CLOSE);
    }

    protected Writer getWriter() {
        return writer;
    }

    private void initialiseWriter(Writer writer) {
        if (writer == null){
            throw new IllegalArgumentException("The writer cannot be null.");
        }

        this.writer = writer;
        isInitialised = true;
    }

    private void initialiseOutputStream(OutputStream output) {
        if (output == null){
            throw new IllegalArgumentException("The output stream cannot be null.");
        }

        this.writer = new OutputStreamWriter(output);
        isInitialised = true;
    }

    private void initialiseFile(File file) throws IOException {
        if (file == null){
            throw new IllegalArgumentException("The file cannot be null.");
        }
        else if (!file.canWrite()){
            throw new IllegalArgumentException("Does not have the permissions to write in file "+file.getAbsolutePath());
        }

        this.writer = new BufferedWriter(new FileWriter(file));
        isInitialised = true;
    }

    private void initialiseFeatureCollections(){
        this.experimentalFeatures = new ArrayList<Feature>();
        this.bindingSites = new ArrayList<Feature>();
        this.ptms = new ArrayList<Feature>();
        this.mutations = new ArrayList<Feature>();
        this.otherFeatures = new ArrayList<Feature>();
    }

    private void clearFeatureCollections(){
        this.experimentalFeatures.clear();
        this.bindingSites.clear();
        this.ptms.clear();
        this.mutations.clear();
        this.otherFeatures.clear();
    }
}
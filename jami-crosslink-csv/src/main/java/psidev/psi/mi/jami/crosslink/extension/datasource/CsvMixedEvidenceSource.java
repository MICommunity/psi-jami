package psidev.psi.mi.jami.crosslink.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.crosslink.extension.*;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.listener.MIFileParserListener;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.comparator.interactor.UnambiguousExactInteractorComparator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

/**
 * This class is a source that can build a mix of n-ary interaction evidences/binary from a Crosslink csv file depending on the
 * bait column to rebuild the original n-ary interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>27/08/14</pre>
 */
public class CsvMixedEvidenceSource implements CsvSource<InteractionEvidence>{
    private static final Logger logger = Logger.getLogger("CsvMixedEvidenceSource");

    private CsvSource<BinaryInteractionEvidence> csvDelegate;

    private Map<String, ProcessedInteraction> processedInteractions;

    private boolean hasRead = false;

    private Collection<InteractionEvidence> evidences;

    /**
     * Empty constructor for the factory
     */
    public CsvMixedEvidenceSource(){
        this.csvDelegate = new CsvBinaryEvidenceSource();
    }

    /**
     * <p>Constructor for CsvMixedEvidenceSource.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public CsvMixedEvidenceSource(File file) throws IOException {

        this.csvDelegate = new CsvBinaryEvidenceSource(file);
    }

    /**
     * <p>Constructor for CsvMixedEvidenceSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public CsvMixedEvidenceSource(InputStream input) {

        this.csvDelegate = new CsvBinaryEvidenceSource(input);
    }

    /**
     * <p>Constructor for CsvMixedEvidenceSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public CsvMixedEvidenceSource(Reader reader) {

        this.csvDelegate = new CsvBinaryEvidenceSource(reader);
    }

    /**
     * <p>Constructor for CsvMixedEvidenceSource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public CsvMixedEvidenceSource(URL url) {

        this.csvDelegate = new CsvBinaryEvidenceSource(url);
    }

    /** {@inheritDoc} */
    public void onMismatchBetweenPeptideAndLinkedPositions(List<CsvRange> peptidePositions, List<CsvRange> linkedPositions) {
        this.csvDelegate.onMismatchBetweenPeptideAndLinkedPositions(peptidePositions, linkedPositions);
    }

    /** {@inheritDoc} */
    public void onMismatchBetweenRangePositionsAndProteins(List<CsvRange> rangePositions, List<CsvProtein> proteins) {
        this.csvDelegate.onMismatchBetweenRangePositionsAndProteins(rangePositions, proteins);
    }

    /** {@inheritDoc} */
    public void onInvalidProteinIdentifierSyntax(String[] identifiers, int lineNumber, int columnNumber) {
        this.csvDelegate.onInvalidProteinIdentifierSyntax(identifiers, lineNumber, columnNumber);
    }

    /** {@inheritDoc} */
    public void onMissingProtein1Column(int lineNumber) {
        this.csvDelegate.onMissingProtein1Column(lineNumber);
    }

    /**
     * <p>getInteractions.</p>
     *
     * @return a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public Collection<InteractionEvidence> getInteractions() throws MIIOException {
        if (!hasRead){
           buildNaryInteractions();
        }
        return this.evidences;
    }

    /**
     * <p>getNumberOfInteractions.</p>
     *
     * @return a long.
     */
    public long getNumberOfInteractions() {
        if (!hasRead){
            buildNaryInteractions();
        }
        return this.evidences.size();
    }

    /**
     * <p>getInteractionsIterator.</p>
     *
     * @return a {@link java.util.Iterator} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public Iterator<InteractionEvidence> getInteractionsIterator() throws MIIOException {
        if (!hasRead){
            buildNaryInteractions();
        }
        return this.evidences.iterator();
    }

    /**
     * <p>getFileParserListener.</p>
     *
     * @return a {@link psidev.psi.mi.jami.listener.MIFileParserListener} object.
     */
    public MIFileParserListener getFileParserListener() {
        return this.csvDelegate.getFileParserListener();
    }

    /** {@inheritDoc} */
    public void setFileParserListener(MIFileParserListener listener) {
        this.csvDelegate.setFileParserListener(listener);
    }

    /**
     * <p>validateSyntax.</p>
     *
     * @return a boolean.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public boolean validateSyntax() throws MIIOException {
        return false;
    }

    /** {@inheritDoc} */
    public boolean validateSyntax(MIFileParserListener listener) throws MIIOException {
        return this.csvDelegate.validateSyntax(listener);
    }

    /** {@inheritDoc} */
    public void initialiseContext(Map<String, Object> options) {
        this.csvDelegate.initialiseContext(options);
    }

    /**
     * <p>close.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void close() throws MIIOException {
        this.csvDelegate.close();
        this.evidences = null;
        this.processedInteractions = null;
        hasRead = false;
    }

    /**
     * <p>reset.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void reset() throws MIIOException {
        this.csvDelegate.reset();
        this.evidences = null;
        this.processedInteractions = null;
        hasRead = false;
    }

    /** {@inheritDoc} */
    public void onInvalidSyntax(FileSourceContext context, Exception e) {
        this.csvDelegate.onInvalidSyntax(context, e);
    }

    /** {@inheritDoc} */
    public void onSyntaxWarning(FileSourceContext context, String message) {
        this.csvDelegate.onSyntaxWarning(context, message);
    }

    /** {@inheritDoc} */
    public void onMissingCvTermName(CvTerm term, FileSourceContext context, String message) {
        this.csvDelegate.onMissingCvTermName(term, context, message);
    }

    /** {@inheritDoc} */
    public void onMissingInteractorName(Interactor interactor, FileSourceContext context) {
        this.csvDelegate.onMissingInteractorName(interactor, context);
    }

    /** {@inheritDoc} */
    public void onParticipantWithoutInteractor(Participant participant, FileSourceContext context) {
        this.csvDelegate.onParticipantWithoutInteractor(participant, context);
    }

    /** {@inheritDoc} */
    public void onInteractionWithoutParticipants(Interaction interaction, FileSourceContext context) {
        this.csvDelegate.onInteractionWithoutParticipants(interaction, context);
    }

    /** {@inheritDoc} */
    public void onInvalidOrganismTaxid(String taxid, FileSourceContext context) {
        this.csvDelegate.onInvalidOrganismTaxid(taxid, context);
    }

    /** {@inheritDoc} */
    public void onMissingParameterValue(FileSourceContext context) {
        this.csvDelegate.onMissingParameterValue(context);
    }

    /** {@inheritDoc} */
    public void onMissingParameterType(FileSourceContext context) {
        this.csvDelegate.onMissingParameterType(context);
    }

    /** {@inheritDoc} */
    public void onMissingConfidenceValue(FileSourceContext context) {
        this.csvDelegate.onMissingConfidenceValue(context);
    }

    /** {@inheritDoc} */
    public void onMissingConfidenceType(FileSourceContext context) {
        this.csvDelegate.onMissingConfidenceType(context);
    }

    /** {@inheritDoc} */
    public void onMissingChecksumValue(FileSourceContext context) {
        this.csvDelegate.onMissingChecksumValue(context);
    }

    /** {@inheritDoc} */
    public void onMissingChecksumMethod(FileSourceContext context) {
        this.csvDelegate.onMissingChecksumMethod(context);
    }

    /** {@inheritDoc} */
    public void onInvalidPosition(String message, FileSourceContext context) {
        this.csvDelegate.onInvalidPosition(message, context);
    }

    /** {@inheritDoc} */
    public void onInvalidRange(String message, FileSourceContext context) {
        this.csvDelegate.onInvalidRange(message, context);
    }

    /** {@inheritDoc} */
    public void onInvalidStoichiometry(String message, FileSourceContext context) {
        this.csvDelegate.onInvalidStoichiometry(message, context);
    }

    /** {@inheritDoc} */
    public void onXrefWithoutDatabase(FileSourceContext context) {
        this.csvDelegate.onXrefWithoutDatabase(context);
    }

    /** {@inheritDoc} */
    public void onXrefWithoutId(FileSourceContext context) {
        this.csvDelegate.onXrefWithoutId(context);
    }

    /** {@inheritDoc} */
    public void onAnnotationWithoutTopic(FileSourceContext context) {
        this.csvDelegate.onAnnotationWithoutTopic(context);
    }

    /** {@inheritDoc} */
    public void onAliasWithoutName(FileSourceContext context) {
        this.csvDelegate.onAliasWithoutName(context);
    }

    /**
     * <p>buildNaryInteractions.</p>
     */
    protected void buildNaryInteractions(){
        hasRead = true;
        // create map of processInteractions
        this.processedInteractions = new HashMap<String, ProcessedInteraction>();
        // read full binary interactions
        Collection<BinaryInteractionEvidence> binaryInteractions = this.csvDelegate.getInteractions();
        // no binary interactions in the file
        if (binaryInteractions.isEmpty()){
            this.evidences = Collections.EMPTY_LIST;
        }
        // create n-ary
        else {
            // create new n-ary interaction
            this.evidences = new ArrayList<InteractionEvidence>(binaryInteractions.size());

            Iterator<BinaryInteractionEvidence> binaryIterator = binaryInteractions.iterator();
            // process binaries
            while(binaryIterator.hasNext()){
                processBinaryInteraction(binaryIterator.next());
            }
        }
    }

    /**
     * <p>processBinaryInteraction.</p>
     *
     * @param binary a {@link psidev.psi.mi.jami.binary.BinaryInteractionEvidence} object.
     */
    protected void processBinaryInteraction(BinaryInteractionEvidence binary){
        CsvBinaryInteractionEvidence csvBinary = (CsvBinaryInteractionEvidence)binary;
        ParticipantEvidence p1 = binary.getParticipantA();
        ParticipantEvidence p2 = binary.getParticipantB();
        // no bait provided, we keep binary
        if (csvBinary.getNaryGroup() == null){
            this.evidences.add(csvBinary);
        }
        // register as it may be a n-ary interaction
        else {
            // the interaction is already registered
            if (this.processedInteractions.containsKey(csvBinary.getNaryGroup())){
                ProcessedInteraction processedInteraction = this.processedInteractions.get(csvBinary.getNaryGroup());
                mergeOrRegisterParticipant(p1, processedInteraction);
                mergeOrRegisterParticipant(p2, processedInteraction);
            }
            // the interaction needs to be registered
            else{
                // create new n-ary interaction
                CsvInteractionEvidence nary = new CsvInteractionEvidence();
                nary.setSourceLocator(new CsvSourceLocator(1, -1, -1));
                // initialise basic properties of interaction
                nary.setInteractionType(binary.getInteractionType());
                if (binary.getExperiment() != null){
                    binary.getExperiment().getInteractionEvidences().clear();
                    nary.setExperiment(binary.getExperiment());
                }
                ProcessedInteraction processedInteraction = new ProcessedInteraction(nary);
                mergeOrRegisterParticipant(p1, processedInteraction);
                mergeOrRegisterParticipant(p2, processedInteraction);

                this.evidences.add(nary);
                this.processedInteractions.put(csvBinary.getNaryGroup(), processedInteraction);
            }
        }
    }

    private void mergeOrRegisterParticipant(ParticipantEvidence p1, ProcessedInteraction processedInteraction) {
         if (p1 != null){
             Interactor interactor = p1.getInteractor();
             if (processedInteraction.getParticipants().containsKey(interactor)){
                 ParticipantEvidence registered = processedInteraction.getParticipants().get(interactor);

                 registered.addAllFeatures(p1.getFeatures());
             }
             // new participant to add
             else{
                 processedInteraction.getEvidence().addParticipant(p1);
                 processedInteraction.getParticipants().put(interactor, p1);
             }
         }
    }

    // private classes

    private class ProcessedInteraction {

        private SortedMap<Interactor, ParticipantEvidence> participants;
        private InteractionEvidence evidence;

        private ProcessedInteraction(InteractionEvidence evidence) {
            this.evidence = evidence;
            this.participants = new TreeMap<Interactor, ParticipantEvidence>(new UnambiguousExactInteractorComparator());
        }

        public SortedMap<Interactor, ParticipantEvidence> getParticipants() {
            return participants;
        }

        public InteractionEvidence getEvidence() {
            return evidence;
        }
    }
}

package psidev.psi.mi.jami.tab.io.writer.feeder;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.tab.utils.MitabUtils;
import psidev.psi.mi.jami.utils.XrefUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

/**
 * The Mitab 2.5 column feeder for interaction evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class MitabInteractionEvidenceFeeder extends AbstractMitabColumnFeeder<BinaryInteractionEvidence, ParticipantEvidence> {

    /**
     * <p>Constructor for MitabInteractionEvidenceFeeder.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public MitabInteractionEvidenceFeeder(Writer writer) {
        super(writer);
    }

    /**
     * <p>writeInteractionDetectionMethod.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.binary.BinaryInteractionEvidence} object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionDetectionMethod(BinaryInteractionEvidence interaction) throws IOException {
        Experiment experiment = interaction.getExperiment();

        if (experiment != null){
            writeCvTerm(experiment.getInteractionDetectionMethod());
        }
        else{
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /**
     * <p>writeFirstAuthor.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.binary.BinaryInteractionEvidence} object.
     * @throws java.io.IOException if any.
     */
    public void writeFirstAuthor(BinaryInteractionEvidence interaction) throws IOException {
        Experiment experiment = interaction.getExperiment();
        if (experiment != null){
            Publication pub = experiment.getPublication();

            if (pub != null){
                // authors and maybe publication date
                if (!pub.getAuthors().isEmpty()){
                    String first = pub.getAuthors().iterator().next();
                    escapeAndWriteString(first);
                    if (!first.contains(MitabUtils.AUTHOR_SUFFIX)){
                        getWriter().write(MitabUtils.AUTHOR_SUFFIX);
                    }
                    if (pub.getPublicationDate() != null){
                        getWriter().write("(");
                        getWriter().write(MitabUtils.PUBLICATION_YEAR_FORMAT.format(pub.getPublicationDate()));
                        getWriter().write(")");
                    }
                }
                // publication date only
                else if (pub.getPublicationDate() != null){
                    getWriter().write(MitabUtils.PUBLICATION_YEAR_FORMAT.format(pub.getPublicationDate()));
                }
                else {
                    getWriter().write(MitabUtils.EMPTY_COLUMN);
                }
            }
            else{
                getWriter().write(MitabUtils.EMPTY_COLUMN);
            }
        }
        else{
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /**
     * <p>writePublicationIdentifiers.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.binary.BinaryInteractionEvidence} object.
     * @throws java.io.IOException if any.
     */
    public void writePublicationIdentifiers(BinaryInteractionEvidence interaction) throws IOException {
        Experiment experiment = interaction.getExperiment();

        if (experiment != null){
            Publication pub = experiment.getPublication();

            if (pub != null){
                // other identfiers
                if (!pub.getIdentifiers().isEmpty()){
                    Iterator<Xref> identifierIterator = pub.getIdentifiers().iterator();

                    while (identifierIterator.hasNext()){
                        // write alternative identifier
                        writeIdentifier(identifierIterator.next());
                        // write field separator
                        if (identifierIterator.hasNext()){
                            getWriter().write(MitabUtils.FIELD_SEPARATOR);
                        }
                    }

                    // IMEx as well
                    writePublicationImexId(pub, true);
                }
                // IMEx only
                else if (pub.getImexId() != null) {
                    writePublicationImexId(pub, false);
                }
                // nothing
                else{
                    getWriter().write(MitabUtils.EMPTY_COLUMN);
                }
            }
            else{
                getWriter().write(MitabUtils.EMPTY_COLUMN);
            }
        }
        else{
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /**
     * <p>writeSource.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.binary.BinaryInteractionEvidence} object.
     * @throws java.io.IOException if any.
     */
    public void writeSource(BinaryInteractionEvidence interaction) throws IOException {
        Experiment experiment = interaction.getExperiment();
        if (experiment != null){
            Publication pub = experiment.getPublication();

            if (pub != null){
                writeCvTerm(pub.getSource());
            }
            else {
                getWriter().write(MitabUtils.EMPTY_COLUMN);
            }
        }
        else{
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /**
     * <p>writeInteractionIdentifiers.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.binary.BinaryInteractionEvidence} object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionIdentifiers(BinaryInteractionEvidence interaction) throws IOException {
        // other identfiers
        if (!interaction.getIdentifiers().isEmpty()){
            Iterator<Xref> identifierIterator = interaction.getIdentifiers().iterator();

            while (identifierIterator.hasNext()){
                // write alternative identifier
                writeIdentifier(identifierIterator.next());
                // write field separator
                if (identifierIterator.hasNext()){
                    getWriter().write(MitabUtils.FIELD_SEPARATOR);
                }
            }

            // IMEx as well
            if (interaction.getImexId() != null){
                getWriter().write(MitabUtils.FIELD_SEPARATOR);
                getWriter().write(Xref.IMEX);
                getWriter().write(MitabUtils.XREF_SEPARATOR);
                escapeAndWriteString(interaction.getImexId());
            }
        }
        // IMEx only
        else if (interaction.getImexId() != null) {
            getWriter().write(Xref.IMEX);
            getWriter().write(MitabUtils.XREF_SEPARATOR);
            escapeAndWriteString(interaction.getImexId());
        }
        // nothing
        else{
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /**
     * <p>writeInteractionConfidences.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.binary.BinaryInteractionEvidence} object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionConfidences(BinaryInteractionEvidence interaction) throws IOException {
        if (!interaction.getConfidences().isEmpty() || interaction.getMiScore() != null) {

            if (interaction.getMiScore() != null) {
                writeConfidence(interaction.getMiScore());
                if (!interaction.getConfidences().isEmpty()) {
                    getWriter().write(MitabUtils.FIELD_SEPARATOR);
                }
            }

            Iterator<Confidence> confIterator = interaction.getConfidences().iterator();
            while (confIterator.hasNext()) {
                writeConfidence(confIterator.next());

                if (confIterator.hasNext()){
                    getWriter().write(MitabUtils.FIELD_SEPARATOR);
                }
            }
        }
        else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /** {@inheritDoc} */
    public void writeAlias(ParticipantEvidence participant, Alias alias) throws IOException {
        if (alias != null){
            // write db first
            escapeAndWriteString(MitabUtils.findDbSourceForAlias(participant, alias));
            // write xref separator
            getWriter().write(MitabUtils.XREF_SEPARATOR);
            // write name
            escapeAndWriteString(alias.getName());
            // write type
            if (alias.getType() != null){
                getWriter().write("(");
                escapeAndWriteString(alias.getType().getShortName());
                getWriter().write(")");
            }
        }
    }

    /**
     * <p>writeExperimentalRole.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @throws java.io.IOException if any.
     */
    public void writeExperimentalRole(ParticipantEvidence participant) throws IOException {
        if (participant != null){
            writeCvTerm(participant.getExperimentalRole());
        }
        else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /**
     * <p>writeInteractionXrefs.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.binary.BinaryInteractionEvidence} object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionXrefs(BinaryInteractionEvidence interaction) throws IOException {
        // write interaction ref
        if (!interaction.getXrefs().isEmpty()){
            Iterator<Xref> interactionXrefIterator = interaction.getXrefs().iterator();

            Xref next = null;
            boolean isFirst = true;
            do {
                next = interactionXrefIterator.next();
                while (interactionXrefIterator.hasNext() && (XrefUtils.doesXrefHaveQualifier(next, Xref.IMEX_PRIMARY_MI, Xref.IMEX_PRIMARY)
                        && XrefUtils.isXrefFromDatabase(next, Xref.IMEX_MI, Xref.IMEX))){
                    next = interactionXrefIterator.next();
                }

                if (next != null && !(XrefUtils.doesXrefHaveQualifier(next, Xref.IMEX_PRIMARY_MI, Xref.IMEX_PRIMARY)
                        && XrefUtils.isXrefFromDatabase(next, Xref.IMEX_MI, Xref.IMEX))){
                    if (!isFirst){
                        getWriter().write(MitabUtils.FIELD_SEPARATOR);
                    }
                    // write xref ony if it is not an imex id
                    writeXref(next);
                    isFirst = false;
                }
                else {
                    next = null;
                }
            }
            while (next != null && interactionXrefIterator.hasNext()) ;
        }
        else{
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /**
     * <p>writeInteractionAnnotations.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.binary.BinaryInteractionEvidence} object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionAnnotations(BinaryInteractionEvidence interaction) throws IOException {
        // writes interaction annotations first
        if (!interaction.getAnnotations().isEmpty()){
            Iterator<Annotation> interactorAnnotationIterator = interaction.getAnnotations().iterator();

            while (interactorAnnotationIterator.hasNext()){
                Annotation annot = interactorAnnotationIterator.next();
                writeAnnotation(annot);

                if(interactorAnnotationIterator.hasNext()){
                    getWriter().write(MitabUtils.FIELD_SEPARATOR);
                }
            }

            if (interaction.getExperiment() != null){
                Publication pub = interaction.getExperiment().getPublication();

                if (pub != null){
                    getWriter().write(MitabUtils.FIELD_SEPARATOR);
                    writeInteractionAnnotationTagsFrom(pub);


                }
            }
        }
        else if (interaction.getExperiment() != null){
            Publication pub = interaction.getExperiment().getPublication();

            if (pub != null){

                // writes curation depth first
                writeInteractionAnnotationTagsFrom(pub);
            }
        }
        else{
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /**
     * <p>writeHostOrganism.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.binary.BinaryInteractionEvidence} object.
     * @throws java.io.IOException if any.
     */
    public void writeHostOrganism(BinaryInteractionEvidence interaction) throws IOException {
        Experiment experiment = interaction.getExperiment();
        // writes interaction annotations first
        if (experiment != null){
            writeOrganism(experiment.getHostOrganism());
        }
        else{
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /**
     * <p>writeInteractionParameters.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.binary.BinaryInteractionEvidence} object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionParameters(BinaryInteractionEvidence interaction) throws IOException {
        if (!interaction.getParameters().isEmpty()){

            Iterator<Parameter> parameterIterator = interaction.getParameters().iterator();
            while(parameterIterator.hasNext()){
                writeParameter(parameterIterator.next());
                if (parameterIterator.hasNext()){
                    getWriter().write(MitabUtils.FIELD_SEPARATOR);
                }
            }
        }
        else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /**
     * <p>writeNegativeProperty.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.binary.BinaryInteractionEvidence} object.
     * @throws java.io.IOException if any.
     */
    public void writeNegativeProperty(BinaryInteractionEvidence interaction) throws IOException {
        if (interaction.isNegative()){
            getWriter().write("true");
        }
        else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /**
     * <p>writeParticipantIdentificationMethod.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @throws java.io.IOException if any.
     */
    public void writeParticipantIdentificationMethod(ParticipantEvidence participant) throws IOException {
        if (participant != null){

            if (!participant.getIdentificationMethods().isEmpty()){
                Iterator<CvTerm> methodIterator = participant.getIdentificationMethods().iterator();
                while(methodIterator.hasNext()){
                    writeCvTerm(methodIterator.next());
                    if (methodIterator.hasNext()){
                        getWriter().write(MitabUtils.FIELD_SEPARATOR);
                    }
                }
            }
            else{
                getWriter().write(MitabUtils.EMPTY_COLUMN);
            }
        }
        else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /**
     * <p>writePublicationImexId.</p>
     *
     * @param pub a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param writeFieldSeparator a boolean.
     * @throws java.io.IOException if any.
     */
    protected void writePublicationImexId(Publication pub, boolean writeFieldSeparator) throws IOException {
        // IMEx as well
        if (pub.getImexId() != null) {
            if (writeFieldSeparator){
                getWriter().write(MitabUtils.FIELD_SEPARATOR);
            }
            getWriter().write(Xref.IMEX);
            getWriter().write(MitabUtils.XREF_SEPARATOR);
            escapeAndWriteString(pub.getImexId());
        }
    }

    /**
     * <p>writeInteractionAnnotationTagsFrom.</p>
     *
     * @param pub a {@link psidev.psi.mi.jami.model.Publication} object.
     * @throws java.io.IOException if any.
     */
    protected void writeInteractionAnnotationTagsFrom(Publication pub) throws IOException {
        boolean isFirst = true;
        // writes curation depth first
        switch (pub.getCurationDepth()){
            case IMEx:
                isFirst = false;
                getWriter().write(Annotation.IMEX_CURATION);
                break;
            case MIMIx:
                isFirst = false;
                getWriter().write(Annotation.MIMIX_CURATION);
                break;
            case rapid_curation:
                isFirst = false;
                getWriter().write(Annotation.RAPID_CURATION);
                break;
            default:
                break;
        }

        // writes special annotations
        if (!pub.getAnnotations().isEmpty()){
            Iterator<Annotation> publicationAnnotationIterator = pub.getAnnotations().iterator();

            Annotation next = null;
            do {
                next = publicationAnnotationIterator.next();
                while (publicationAnnotationIterator.hasNext() &&
                        !MitabUtils.isAnnotationAnInteractionTag(next)){
                    next = publicationAnnotationIterator.next();
                }

                if (next != null && MitabUtils.isAnnotationAnInteractionTag(next)){
                    if (!isFirst){
                        getWriter().write(MitabUtils.FIELD_SEPARATOR);
                    }
                    // write annotation if interaction tag
                    writeAnnotation(next);
                    isFirst = false;
                }
                else {
                    next = null;
                }
            }
            while (next != null && publicationAnnotationIterator.hasNext()) ;
        }
    }
}

package psidev.psi.mi.jami.tab.io.writer.feeder;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.model.*;

import java.io.IOException;
import java.util.Date;

/**
 * A MITAB 2.5 column feeder will write the content of MITAB 2.5 columns
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public interface MitabColumnFeeder<T extends BinaryInteraction, P extends Participant> {

    /**
     * This method will write the unique identifier of a participant
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeUniqueIdentifier(P participant) throws IOException;

    /**
     * This method writes all the remaining identifiers (ignore the first identifier) of the participant
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeAlternativeIdentifiers(P participant) throws IOException;

    /**
     * This method writes all the aliases of the participant
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeAliases(P participant) throws IOException;

    /**
     * Writes the interaction detection method of the experiment.
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionDetectionMethod(T interaction) throws IOException;

    /**
     * Writes the first author of a publication in an experiment
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeFirstAuthor(T interaction) throws IOException;

    /**
     * Writes the publication identifiers of a publication in an experiment.
     * This method will write the first publication identifier (pubmed before doi) and also the IMEx id
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writePublicationIdentifiers(T interaction) throws IOException;

    /**
     * Writes the organism of a participant.
     * Empty column if the organism is not provided
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractorOrganism(P participant) throws IOException;

    /**
     * Writes the interaction type of an interaction
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionType(T interaction) throws IOException;

    /**
     * Writes the interaction source from the modelled interaction
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeSource(T interaction) throws IOException;

    /**
     * Writes the interaction identifiers of an interaction.
     * This method will write the first interaction identifier and also the IMEx id
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionIdentifiers(T interaction) throws IOException;

    /**
     * Writes the confidences of an interaction evidence
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionConfidences(T interaction) throws IOException;

    /**
     * This method will write a confidence with a text if text is not null
     *
     * @param conf a {@link psidev.psi.mi.jami.model.Confidence} object.
     * @throws java.io.IOException if any.
     */
    public void writeConfidence(Confidence conf) throws IOException;

    /**
     * Write an organism.
     * Will duplicate taxid if needs to provide both common name and scientific name
     *
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @throws java.io.IOException if any.
     */
    public void writeOrganism(Organism organism) throws IOException;

    /**
     * Write the CvTerm. If it is null, it writes an empty column (-)
     *
     * @param cv a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @throws java.io.IOException if any.
     */
    public void writeCvTerm(CvTerm cv) throws IOException;


    /**
     * This methods write the dbsource, alias name and alias type of an alias
     *
     * @param alias a {@link psidev.psi.mi.jami.model.Alias} object.
     * @throws java.io.IOException if any.
     */
    public void writeAlias(Alias alias) throws IOException;

    /**
     * This methods write the dbsource, alias name and alias type of an alias.  It can use the modelled participant to find dbsource
     *
     * @param alias a {@link psidev.psi.mi.jami.model.Alias} object.
     * @throws java.io.IOException if any.
     * @param participant a P object.
     */
    public void writeAlias(P participant, Alias alias) throws IOException;

    /**
     * This methods write the database, id and version of an identifier
     *
     * @param identifier a {@link psidev.psi.mi.jami.model.Xref} object.
     * @throws java.io.IOException if any.
     */
    public void writeIdentifier(Xref identifier) throws IOException;

    /**
     * This method replaces line breaks and tab characters with a space.
     *
     * It escapes the StringToEscape with doble quote if it finds a special MITAB character
     *
     * @param stringToEscape a {@link java.lang.String} object.
     * @throws java.io.IOException if any.
     */
    public void escapeAndWriteString(String stringToEscape) throws IOException;

    /**
     * Writes the complex expansion of a binary interaction
     *
     * @param binary a T object.
     * @throws java.io.IOException if any.
     */
    public void writeComplexExpansion(T binary) throws IOException;

    /**
     * Writes the biological role of a participant
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeBiologicalRole(P participant) throws IOException;

    /**
     * Writes the experimentsl role of a participant evidence
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeExperimentalRole(P participant) throws IOException;

    /**
     * Writes the interactor type of a participant
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractorType(P participant) throws IOException;

    /**
     * Write Xref of participant and interactor
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeParticipantXrefs(P participant) throws IOException;

    /**
     * Write interaction Xref r
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionXrefs(T interaction) throws IOException;

    /**
     * Writes participant annotations
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeParticipantAnnotations(P participant) throws IOException;

    /**
     * Writes interaction annotations
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionAnnotations(T interaction) throws IOException;

    /**
     * Writes experiment host organism
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeHostOrganism(T interaction) throws IOException;

    /**
     * Writes interaction parameters
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionParameters(T interaction) throws IOException;

    /**
     * Writes created date of an interaction
     *
     * @param date a {@link java.util.Date} object.
     * @throws java.io.IOException if any.
     */
    public void writeDate(Date date) throws IOException;

    /**
     * Writes participant checksum
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeParticipantChecksums(P participant) throws IOException;

    /**
     * Writes interaction checksum
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionChecksums(T interaction) throws IOException;

    /**
     * Writes interaction negative property if true
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeNegativeProperty(T interaction) throws IOException;

    /**
     * Writes the checksum
     *
     * @param checksum a {@link psidev.psi.mi.jami.model.Checksum} object.
     * @throws java.io.IOException if any.
     */
    public void writeChecksum(Checksum checksum) throws IOException;

    /**
     * Writes the parameter
     *
     * @param parameter a {@link psidev.psi.mi.jami.model.Parameter} object.
     * @throws java.io.IOException if any.
     */
    public void writeParameter(Parameter parameter) throws IOException;

    /**
     * Writes an annotation
     *
     * @param annotation a {@link psidev.psi.mi.jami.model.Annotation} object.
     * @throws java.io.IOException if any.
     */
    public void writeAnnotation(Annotation annotation) throws IOException;

    /**
     * This methods write the database, id, version and qualifier of an xref
     *
     * @param xref a {@link psidev.psi.mi.jami.model.Xref} object.
     * @throws java.io.IOException if any.
     */
    public void writeXref(Xref xref) throws IOException;

    /**
     * Writes participant features
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeParticipantFeatures(P participant) throws IOException;

    /**
     * Writes participant stoichiometry
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeParticipantStoichiometry(P participant) throws IOException;

    /**
     * Writes participant identification method(s)
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeParticipantIdentificationMethod(P participant) throws IOException ;

    /**
     * Writes a feature
     *
     * @param feature a {@link psidev.psi.mi.jami.model.Feature} object.
     * @throws java.io.IOException if any.
     */
    public void writeFeature(Feature feature) throws IOException;

    /**
     * Writes participant biological effect
     *
     * @param participant a P object.
     * @throws java.io.IOException if any.
     */
    public void writeParticipantBiologicalEffect(P participant) throws IOException;

    /**
     * Writes interaction checksum
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionCausalRegulatoryMechanism(T interaction) throws IOException;

    /**
     * Writes interaction checksum
     *
     * @param interaction a T object.
     * @throws java.io.IOException if any.
     */
    public void writeInteractionCausalStatement(T interaction) throws IOException;
}

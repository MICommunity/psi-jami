package psidev.psi.mi.jami.tab.io.writer.feeder;

import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.InteractionEvidence;

import java.io.IOException;

/**
 * A FeatureTab column feeder will write the content of FeatureTab columns
 */
public interface FeatureTabColumnFeeder<F extends Feature, I extends InteractionEvidence> {

    /**
     * This method will write the accession number of a feature.
     *
     * @param feature a F object.
     * @throws IOException if any.
     */
    void writeFeatureAc(F feature) throws IOException;

    /**
     * This method writes the short label of the feature.
     *
     * @param feature a F object.
     * @throws IOException if any.
     */
    void writeFeatureShortLabel(F feature) throws IOException;

    /**
     * This method writes all the ranges of the feature.
     *
     * @param feature a F object.
     * @throws IOException if any.
     */
    void writeRanges(F feature) throws IOException;

    /**
     * Writes the original sequence of the feature.
     *
     * @param feature a F object.
     * @throws IOException if any.
     */
    void writeOriginalSequence(F feature) throws IOException;

    /**
     * Writes the resulting sequence of the feature.
     *
     * @param feature a F object.
     * @throws IOException if any.
     */
    void writeResultingSequence(F feature) throws IOException;

    /**
     * Writes the feature type of the feature.
     *
     * @param feature a F object.
     * @throws IOException if any.
     */
    void writeFeatureType(F feature) throws IOException;

    /**
     * This method writes all the annotations of the feature.
     *
     * @param feature a F object.
     * @throws IOException if any.
     */
    void writeAnnotations(F feature) throws IOException;

    /**
     * This method writes the accession number of the affected protein of the feature.
     *
     * @param feature a F object.
     * @throws IOException if any.
     */
    void writeAffectedProteinAc(F feature) throws IOException;

    /**
     * This method writes the symbol of the affected protein of the feature.
     *
     * @param feature a F object.
     * @throws IOException if any.
     */
    void writeAffectedProteinSymbol(F feature) throws IOException;

    /**
     * This method writes the full name of the affected protein of the feature.
     *
     * @param feature a F object.
     * @throws IOException if any.
     */
    void writeAffectedProteinFullName(F feature) throws IOException;

    /**
     * This method writes the organism of the affected protein of the feature.
     *
     * @param feature a F object.
     * @throws IOException if any.
     */
    void writeAffectedProteinOrganism(F feature) throws IOException;

    /**
     * This method writes the identifiers of the participants in the interaction affected by the feature.
     *
     * @param interaction an I object.
     * @throws IOException if any.
     */
    void writeInteractionParticipants(I interaction) throws IOException;

    /**
     * This method writes the PubMed ID of the publication where the interaction evidence affected by the feature
     * was reported.
     *
     * @param experiment an Experiment object.
     * @throws IOException if any.
     */
    void writePubMedId(Experiment experiment) throws IOException;

    /**
     * This method writes the reference to the specific figures in the paper where the interaction evidence affected
     * by the feature was reported.
     *
     * @param experiment an Experiment object.
     * @throws IOException if any.
     */
    void writeFigureLegends(Experiment experiment) throws IOException;

    /**
     * This method writes the accession number of the interaction affected by the feature.
     *
     * @param interaction an I object.
     * @throws IOException if any.
     */
    void writeInteractionAc(I interaction) throws IOException;

    /**
     * This method writes the xrefs identifiers associated to the feature.
     *
     * @param feature a F object.
     * @throws IOException if any.
     */
    void writeXrefIds(F feature) throws IOException;
}

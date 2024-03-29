package psidev.psi.mi.jami.enricher.listener.impl.writer;

import psidev.psi.mi.jami.enricher.listener.ExperimentEnricherListener;
import psidev.psi.mi.jami.model.*;

import java.io.File;
import java.io.IOException;

/**
 * A statistics logger which records changes made by the enricher.
 * Each addition, removal or update is counted and, upon the completion of the enrichment of the object,
 * is logged in either a file of successes or failures depending on the enrichmentStatus.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 01/08/13

 */
public class ExperimentEnricherStatisticsWriter
        extends EnricherStatisticsWriter<Experiment>
        implements ExperimentEnricherListener {

    private static final String FILE_NAME = "experiment";

    /**
     * Uses the known name of the JamiObject type as the seed to generate names for the success an failure log files.
     *
     * @throws java.io.IOException      Thrown if a problem is encountered with file location.
     */
    public ExperimentEnricherStatisticsWriter() throws IOException {
        super(FILE_NAME);
    }

    /**
     * Creates the files from the provided seed file name with 'success' and 'failure' appended.
     *
     * @param fileName          The seed to base the names of the files on.
     * @throws java.io.IOException      Thrown if a problem is encountered with file location.
     */
    public ExperimentEnricherStatisticsWriter(String fileName) throws IOException {
        super(fileName);
    }

    /**
     * Uses the provided names to create the files for successful and failed enrichment logging.
     *
     * @param successFileName   The exact name for the file to log successful enrichments in
     * @param failureFileName   The exact name for the file to log failed enrichments in
     * @throws java.io.IOException      Thrown if a problem is encountered with file location.
     */
    public ExperimentEnricherStatisticsWriter(String successFileName, String failureFileName) throws IOException {
        super(successFileName, failureFileName);
    }

    /**
     * Uses the exact files provided to log successful and failed enrichments.
     *
     * @param successFile       The file to log successful enrichments in
     * @param failureFile       The file to log failed enrichments in.
     * @throws java.io.IOException      Thrown if a problem is encountered with file location.
     */
    public ExperimentEnricherStatisticsWriter(File successFile, File failureFile) throws IOException {
        super(successFile, failureFile);
    }

    /** {@inheritDoc} */
    public void onPublicationUpdate(Experiment experiment, Publication oldPublication) {
        checkObject(experiment);
        incrementUpdateCount();
    }

    /** {@inheritDoc} */
    public void onInteractionDetectionMethodUpdate(Experiment experiment, CvTerm oldCv) {
        checkObject(experiment);
        incrementUpdateCount();
    }

    /** {@inheritDoc} */
    public void onHostOrganismUpdate(Experiment experiment, Organism oldOrganism) {
        checkObject(experiment);
        incrementUpdateCount();
    }

    /** {@inheritDoc} */
    public void onAddedVariableParameter(Experiment o, VariableParameter added) {
        checkObject(o);
        incrementAdditionCount();
    }

    /** {@inheritDoc} */
    public void onRemovedVariableParameter(Experiment o, VariableParameter removed) {
        checkObject(o);
        incrementRemovedCount();
    }

    /** {@inheritDoc} */
    public void onAddedAnnotation(Experiment o, Annotation added) {
        checkObject(o);
        incrementAdditionCount();
    }

    /** {@inheritDoc} */
    public void onRemovedAnnotation(Experiment o, Annotation removed) {
        checkObject(o);
        incrementRemovedCount();
    }

    /** {@inheritDoc} */
    public void onAddedConfidence(Experiment o, Confidence added) {
        checkObject(o);
        incrementAdditionCount();
    }

    /** {@inheritDoc} */
    public void onRemovedConfidence(Experiment o, Confidence removed) {
        checkObject(o);
        incrementRemovedCount();
    }

    /** {@inheritDoc} */
    public void onAddedXref(Experiment o, Xref added) {
        checkObject(o);
        incrementAdditionCount();
    }

    /** {@inheritDoc} */
    public void onRemovedXref(Experiment o, Xref removed) {
        checkObject(o);
        incrementRemovedCount();
    }
}

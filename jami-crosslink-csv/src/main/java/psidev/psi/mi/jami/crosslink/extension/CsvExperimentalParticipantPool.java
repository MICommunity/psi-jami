package psidev.psi.mi.jami.crosslink.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultExperimentalParticipantPool;

/**
 * Crosslink CSV extension of ParticipantEvidencePool.
 * It contains a FileSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class CsvExperimentalParticipantPool extends DefaultExperimentalParticipantPool implements FileSourceContext{

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param poolName a {@link java.lang.String} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(InteractionEvidence interaction, String poolName, CvTerm participantIdentificationMethod) {
        super(interaction, poolName, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(InteractionEvidence interaction, String poolName, CvTerm bioRole, CvTerm participantIdentificationMethod) {
        super(interaction, poolName, bioRole, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param poolName a {@link java.lang.String} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(InteractionEvidence interaction, String poolName, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(interaction, poolName, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public CsvExperimentalParticipantPool(String poolName, Stoichiometry stoichiometry) {
        super(poolName, stoichiometry);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     */
    public CsvExperimentalParticipantPool(String poolName) {
        super(poolName);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param expressedIn a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(String poolName, CvTerm bioRole, CvTerm expRole, Stoichiometry stoichiometry, Organism expressedIn, CvTerm participantIdentificationMethod) {
        super(poolName, bioRole, expRole, stoichiometry, expressedIn, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expressedIn a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(String poolName, CvTerm bioRole, CvTerm expRole, Organism expressedIn, CvTerm participantIdentificationMethod) {
        super(poolName, bioRole, expRole, expressedIn, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(String poolName, CvTerm bioRole, CvTerm expRole, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(poolName, bioRole, expRole, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(String poolName, CvTerm bioRole, CvTerm expRole, CvTerm participantIdentificationMethod) {
        super(poolName, bioRole, expRole, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(String poolName, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(poolName, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(String poolName, CvTerm bioRole, CvTerm participantIdentificationMethod) {
        super(poolName, bioRole, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(String poolName, CvTerm participantIdentificationMethod) {
        super(poolName, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(InteractionEvidence interaction, String poolName, CvTerm bioRole, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(interaction, poolName, bioRole, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(InteractionEvidence interaction, String poolName, CvTerm bioRole, CvTerm expRole, CvTerm participantIdentificationMethod) {
        super(interaction, poolName, bioRole, expRole, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(InteractionEvidence interaction, String poolName, CvTerm bioRole, CvTerm expRole, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(interaction, poolName, bioRole, expRole, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expressedIn a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(InteractionEvidence interaction, String poolName, CvTerm bioRole, CvTerm expRole, Organism expressedIn, CvTerm participantIdentificationMethod) {
        super(interaction, poolName, bioRole, expRole, expressedIn, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for CsvExperimentalParticipantPool.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param expressedIn a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvExperimentalParticipantPool(InteractionEvidence interaction, String poolName, CvTerm bioRole, CvTerm expRole, Stoichiometry stoichiometry, Organism expressedIn, CvTerm participantIdentificationMethod) {
        super(interaction, poolName, bioRole, expRole, stoichiometry, expressedIn, participantIdentificationMethod);
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "CSV Participant pool: "+sourceLocator != null ? sourceLocator.toString():super.toString();
    }
}

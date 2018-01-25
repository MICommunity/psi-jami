package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultParticipantEvidence;

/**
 * Mitab extension of ParticipantEvidence.
 * It contains a FileSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabParticipantEvidence extends DefaultParticipantEvidence implements FileSourceContext{

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm bioRole, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, bioRole, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(InteractionEvidence interaction, Interactor interactor, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm bioRole, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, bioRole, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm bioRole, CvTerm expRole, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, bioRole, expRole, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm bioRole, CvTerm expRole, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, bioRole, expRole, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expressedIn a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm bioRole, CvTerm expRole, Organism expressedIn, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, bioRole, expRole, expressedIn, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param expressedIn a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm bioRole, CvTerm expRole, Stoichiometry stoichiometry, Organism expressedIn, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, bioRole, expRole, stoichiometry, expressedIn, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(Interactor interactor, CvTerm participantIdentificationMethod) {
        super(interactor, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(Interactor interactor, CvTerm bioRole, CvTerm participantIdentificationMethod) {
        super(interactor, bioRole, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(Interactor interactor, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(interactor, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(Interactor interactor, CvTerm bioRole, CvTerm expRole, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(interactor, bioRole, expRole, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(Interactor interactor, CvTerm bioRole, CvTerm expRole, CvTerm participantIdentificationMethod) {
        super(interactor, bioRole, expRole, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expressedIn a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(Interactor interactor, CvTerm bioRole, CvTerm expRole, Organism expressedIn, CvTerm participantIdentificationMethod) {
        super(interactor, bioRole, expRole, expressedIn, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param expressedIn a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabParticipantEvidence(Interactor interactor, CvTerm bioRole, CvTerm expRole, Stoichiometry stoichiometry, Organism expressedIn, CvTerm participantIdentificationMethod) {
        super(interactor, bioRole, expRole, stoichiometry, expressedIn, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public MitabParticipantEvidence(Interactor interactor) {
        super(interactor);
    }

    /**
     * <p>Constructor for MitabParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public MitabParticipantEvidence(Interactor interactor, Stoichiometry stoichiometry) {
        super(interactor, stoichiometry);
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
        return (getSourceLocator() != null ? "Participant: "+getSourceLocator().toString():super.toString());
    }
}

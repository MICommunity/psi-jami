package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

/**
 * Default implementation of named participant evidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class DefaultNamedParticipantEvidence extends DefaultParticipantEvidence implements NamedParticipant<InteractionEvidence, FeatureEvidence> {
    private String shortName;
    private String fullName;

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm bioRole, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, bioRole, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public DefaultNamedParticipantEvidence(Interactor interactor, Stoichiometry stoichiometry) {
        super(interactor, stoichiometry);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public DefaultNamedParticipantEvidence(Interactor interactor) {
        super(interactor);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param expressedIn a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(Interactor interactor, CvTerm bioRole, CvTerm expRole, Stoichiometry stoichiometry, Organism expressedIn, CvTerm participantIdentificationMethod) {
        super(interactor, bioRole, expRole, stoichiometry, expressedIn, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expressedIn a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(Interactor interactor, CvTerm bioRole, CvTerm expRole, Organism expressedIn, CvTerm participantIdentificationMethod) {
        super(interactor, bioRole, expRole, expressedIn, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(InteractionEvidence interaction, Interactor interactor, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm bioRole, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, bioRole, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm bioRole, CvTerm expRole, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, bioRole, expRole, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm bioRole, CvTerm expRole, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, bioRole, expRole, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expressedIn a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm bioRole, CvTerm expRole, Organism expressedIn, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, bioRole, expRole, expressedIn, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param expressedIn a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(InteractionEvidence interaction, Interactor interactor, CvTerm bioRole, CvTerm expRole, Stoichiometry stoichiometry, Organism expressedIn, CvTerm participantIdentificationMethod) {
        super(interaction, interactor, bioRole, expRole, stoichiometry, expressedIn, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(Interactor interactor, CvTerm participantIdentificationMethod) {
        super(interactor, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(Interactor interactor, CvTerm bioRole, CvTerm participantIdentificationMethod) {
        super(interactor, bioRole, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(Interactor interactor, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(interactor, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(Interactor interactor, CvTerm bioRole, CvTerm expRole, Stoichiometry stoichiometry, CvTerm participantIdentificationMethod) {
        super(interactor, bioRole, expRole, stoichiometry, participantIdentificationMethod);
    }

    /**
     * <p>Constructor for DefaultNamedParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param expRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantIdentificationMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipantEvidence(Interactor interactor, CvTerm bioRole, CvTerm expRole, CvTerm participantIdentificationMethod) {
        super(interactor, bioRole, expRole, participantIdentificationMethod);
    }

    /**
     * <p>Getter for the field <code>shortName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getShortName() {
        return shortName;
    }

    /** {@inheritDoc} */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * <p>Getter for the field <code>fullName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFullName() {
        return fullName;
    }

    /** {@inheritDoc} */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

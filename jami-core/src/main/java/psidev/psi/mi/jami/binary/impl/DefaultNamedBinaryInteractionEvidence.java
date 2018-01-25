package psidev.psi.mi.jami.binary.impl;

import psidev.psi.mi.jami.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation of a Named binary interaction evidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class DefaultNamedBinaryInteractionEvidence extends DefaultBinaryInteractionEvidence implements NamedInteraction<ParticipantEvidence>{
    private String fullName;
    private Collection<Alias> aliases;

    /**
     * <p>Constructor for DefaultNamedBinaryInteractionEvidence.</p>
     */
    public DefaultNamedBinaryInteractionEvidence() {
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultNamedBinaryInteractionEvidence(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedBinaryInteractionEvidence(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteractionEvidence.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public DefaultNamedBinaryInteractionEvidence(ParticipantEvidence participantA, ParticipantEvidence participantB) {
        super(participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public DefaultNamedBinaryInteractionEvidence(String shortName, ParticipantEvidence participantA, ParticipantEvidence participantB) {
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public DefaultNamedBinaryInteractionEvidence(String shortName, CvTerm type, ParticipantEvidence participantA, ParticipantEvidence participantB) {
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteractionEvidence.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedBinaryInteractionEvidence(CvTerm complexExpansion) {
        super(complexExpansion);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedBinaryInteractionEvidence(String shortName, CvTerm type, CvTerm complexExpansion) {
        super(shortName, type, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteractionEvidence.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedBinaryInteractionEvidence(ParticipantEvidence participantA, ParticipantEvidence participantB, CvTerm complexExpansion) {
        super(participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedBinaryInteractionEvidence(String shortName, ParticipantEvidence participantA, ParticipantEvidence participantB, CvTerm complexExpansion) {
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedBinaryInteractionEvidence(String shortName, CvTerm type, ParticipantEvidence participantA, ParticipantEvidence participantB, CvTerm complexExpansion) {
        super(shortName, type, participantA, participantB, complexExpansion);
    }

    /**
     * <p>initialiseAliases</p>
     */
    protected void initialiseAliases(){
        this.aliases = new ArrayList<Alias>();
    }

    /**
     * <p>initialiseAliasesWith</p>
     *
     * @param aliases a {@link java.util.Collection} object.
     */
    protected void initialiseAliasesWith(Collection<Alias> aliases){
        if (aliases == null){
            this.aliases = Collections.EMPTY_LIST;
        }
        else {
            this.aliases = aliases;
        }
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

    /**
     * <p>Getter for the field <code>aliases</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Alias> getAliases() {
        if (this.aliases == null){
            initialiseAliases();
        }
        return aliases;
    }
}

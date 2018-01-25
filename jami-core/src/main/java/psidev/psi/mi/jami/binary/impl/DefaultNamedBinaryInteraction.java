package psidev.psi.mi.jami.binary.impl;

import psidev.psi.mi.jami.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation of a named binary interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class DefaultNamedBinaryInteraction extends DefaultBinaryInteraction implements NamedInteraction<Participant> {

    private String fullName;
    private Collection<Alias> aliases;

    /**
     * <p>Constructor for DefaultNamedBinaryInteraction.</p>
     */
    public DefaultNamedBinaryInteraction() {
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultNamedBinaryInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedBinaryInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public DefaultNamedBinaryInteraction(Participant participantA, Participant participantB) {
        super(participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public DefaultNamedBinaryInteraction(String shortName, Participant participantA, Participant participantB) {
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public DefaultNamedBinaryInteraction(String shortName, CvTerm type, Participant participantA, Participant participantB) {
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteraction.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedBinaryInteraction(CvTerm complexExpansion) {
        super(complexExpansion);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedBinaryInteraction(String shortName, CvTerm type, CvTerm complexExpansion) {
        super(shortName, type, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedBinaryInteraction(Participant participantA, Participant participantB, CvTerm complexExpansion) {
        super(participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedBinaryInteraction(String shortName, Participant participantA, Participant participantB, CvTerm complexExpansion) {
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultNamedBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedBinaryInteraction(String shortName, CvTerm type, Participant participantA, Participant participantB, CvTerm complexExpansion) {
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

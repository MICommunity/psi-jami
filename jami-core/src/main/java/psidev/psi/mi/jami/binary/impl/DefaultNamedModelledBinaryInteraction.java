package psidev.psi.mi.jami.binary.impl;

import psidev.psi.mi.jami.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for Named modelled binary interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class DefaultNamedModelledBinaryInteraction extends DefaultModelledBinaryInteraction implements NamedInteraction<ModelledParticipant>{
    private String fullName;
    private Collection<Alias> aliases;

    /**
     * <p>Constructor for DefaultNamedModelledBinaryInteraction.</p>
     */
    public DefaultNamedModelledBinaryInteraction() {
    }

    /**
     * <p>Constructor for DefaultNamedModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultNamedModelledBinaryInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for DefaultNamedModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedModelledBinaryInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for DefaultNamedModelledBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public DefaultNamedModelledBinaryInteraction(ModelledParticipant participantA, ModelledParticipant participantB) {
        super(participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultNamedModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public DefaultNamedModelledBinaryInteraction(String shortName, ModelledParticipant participantA, ModelledParticipant participantB) {
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultNamedModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public DefaultNamedModelledBinaryInteraction(String shortName, CvTerm type, ModelledParticipant participantA, ModelledParticipant participantB) {
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultNamedModelledBinaryInteraction.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedModelledBinaryInteraction(CvTerm complexExpansion) {
        super(complexExpansion);
    }

    /**
     * <p>Constructor for DefaultNamedModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedModelledBinaryInteraction(String shortName, CvTerm type, CvTerm complexExpansion) {
        super(shortName, type, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultNamedModelledBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedModelledBinaryInteraction(ModelledParticipant participantA, ModelledParticipant participantB, CvTerm complexExpansion) {
        super(participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultNamedModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedModelledBinaryInteraction(String shortName, ModelledParticipant participantA, ModelledParticipant participantB, CvTerm complexExpansion) {
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultNamedModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedModelledBinaryInteraction(String shortName, CvTerm type, ModelledParticipant participantA, ModelledParticipant participantB, CvTerm complexExpansion) {
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

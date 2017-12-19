package psidev.psi.mi.jami.binary.impl;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.impl.AbstractInteraction;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Abstract class for BinaryInteraction.
 *
 * A binary interaction is an interaction but will not allow to add more than two participants. As a consequence, the method getParticipants
 * return a Unmodifiable collection and to add/remove participants, we must use the setParticipantA/B methods or the add/removeParticipant methods
 * provided by the Interaction interface
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/07/13</pre>
 */
public abstract class AbstractBinaryInteraction<T extends Participant> extends AbstractInteraction<T> implements BinaryInteraction<T> {
    private T participantA;
    private T participantB;
    private CvTerm complexExpansion;

    /**
     * <p>Constructor for AbstractBinaryInteraction.</p>
     */
    public AbstractBinaryInteraction(){
        super();
    }

    /**
     * <p>Constructor for AbstractBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public AbstractBinaryInteraction(String shortName){
        super(shortName);
    }

    /**
     * <p>Constructor for AbstractBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractBinaryInteraction(String shortName, CvTerm type){
        super(shortName, type);
    }

    /**
     * <p>Constructor for AbstractBinaryInteraction.</p>
     *
     * @param participantA a T object.
     * @param participantB a T object.
     */
    public AbstractBinaryInteraction(T participantA, T participantB){
        super();
        this.participantA = participantA;
        this.participantB = participantB;
    }

    /**
     * <p>Constructor for AbstractBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a T object.
     * @param participantB a T object.
     */
    public AbstractBinaryInteraction(String shortName, T participantA, T participantB){
        super(shortName);
        this.participantA = participantA;
        this.participantB = participantB;
    }

    /**
     * <p>Constructor for AbstractBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a T object.
     * @param participantB a T object.
     */
    public AbstractBinaryInteraction(String shortName, CvTerm type, T participantA, T participantB){
        super(shortName, type);
        this.participantA = participantA;
        this.participantB = participantB;
    }

    /**
     * <p>Constructor for AbstractBinaryInteraction.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractBinaryInteraction(CvTerm complexExpansion){
        super();
        this.complexExpansion = complexExpansion;
    }

    /**
     * <p>Constructor for AbstractBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractBinaryInteraction(String shortName, CvTerm type, CvTerm complexExpansion){
        super(shortName, type);
        this.complexExpansion = complexExpansion;
    }

    /**
     * <p>Constructor for AbstractBinaryInteraction.</p>
     *
     * @param participantA a T object.
     * @param participantB a T object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractBinaryInteraction(T participantA, T participantB, CvTerm complexExpansion){
        super();
        this.participantA = participantA;
        this.participantB = participantB;
        this.complexExpansion = complexExpansion;
    }

    /**
     * <p>Constructor for AbstractBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a T object.
     * @param participantB a T object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractBinaryInteraction(String shortName, T participantA, T participantB, CvTerm complexExpansion){
        super(shortName);
        this.complexExpansion = complexExpansion;
        this.participantA = participantA;
        this.participantB = participantB;
    }

    /**
     * <p>Constructor for AbstractBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a T object.
     * @param participantB a T object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractBinaryInteraction(String shortName, CvTerm type, T participantA, T participantB, CvTerm complexExpansion){
        super(shortName, type);
        this.participantA = participantA;
        this.participantB = participantB;
        this.complexExpansion = complexExpansion;
    }

    /**
     * <p>Getter for the field <code>participantA</code>.</p>
     *
     * @return a T object.
     */
    public T getParticipantA() {
        return participantA;
    }

    /**
     * <p>Getter for the field <code>participantB</code>.</p>
     *
     * @return a T object.
     */
    public T getParticipantB() {
        return participantB;
    }

    /**
     * <p>Setter for the field <code>participantA</code>.</p>
     *
     * @param participantA a T object.
     */
    public void setParticipantA(T participantA) {
        this.participantA = participantA;
    }

    /**
     * <p>Setter for the field <code>participantB</code>.</p>
     *
     * @param participantB a T object.
     */
    public void setParticipantB(T participantB) {
        this.participantB = participantB;
    }

    /**
     * <p>Getter for the field <code>complexExpansion</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getComplexExpansion() {
        return this.complexExpansion;
    }

    /** {@inheritDoc} */
    public void setComplexExpansion(CvTerm expansion) {
        this.complexExpansion = expansion;
    }

    /**
     * {@inheritDoc}
     *
     * The collection of participants for this binary interaction.
     * It cannot be changed.
     */
    @Override
    public Collection<T> getParticipants() {
        if (getParticipantA() == null && getParticipantB() == null){
            return Collections.EMPTY_LIST;
        }
        else if (getParticipantB() == null){
            return Arrays.asList(getParticipantA());
        }
        else if (getParticipantA() == null){
            return Arrays.asList(getParticipantB());
        }
        else{
            return Arrays.asList(getParticipantA(), getParticipantB());
        }
    }

    /**
     * {@inheritDoc}
     *
     * Adds a new Participant and set the Interaction of this participant if added.
     * If the participant B and A are null, it will first set the participantA. If the participantA is set, it will set the ParticipantB
     */
    @Override
    public boolean addParticipant(T part) {
        if (part == null){
            return false;
        }
        if (getParticipantB() != null && getParticipantA() != null){
            throw new IllegalStateException("A BinaryInteraction cannot have more than two participants.");
        }
        else if (getParticipantA() != null){
            part.setInteraction(this);
            setParticipantB(part);
            return true;
        }
        else{
            part.setInteraction(this);
            setParticipantA(part);
            return true;
        }
    }

    /**
     * {@inheritDoc}
     *
     * Removes the Participant from this binary interaction
     */
    @Override
    public boolean removeParticipant(T part) {
        if (part == null){
            return false;
        }

        if (getParticipantA() != null && getParticipantA().equals(part)){
            part.setInteraction(null);
            setParticipantA(null);
            return true;
        }
        else if (getParticipantB() != null && getParticipantB().equals(part)){
            part.setInteraction(null);
            setParticipantB(null);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * Adds the participants and set the Interaction of this participant if added.
     * If the participant B and A are null, it will first set the participantA. If the participantA is set, it will set the ParticipantB
     */
    @Override
    public boolean addAllParticipants(Collection<? extends T> participants) {
        if (participants == null){
            return false;
        }
        if (participants.size() > 2){
            throw new IllegalArgumentException("A BinaryInteraction cannot have more than two participants and we try to add " + participants.size() + " participants");
        }

        boolean added = false;
        for (T p : participants){
            if (addParticipant(p)){
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    @Override
    public boolean removeAllParticipants(Collection<? extends T> participants) {
        if (participants == null){
            return false;
        }

        boolean removed = false;
        for (T p : participants){
            if (removeParticipant(p)){
                removed = true;
            }
        }
        return removed;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Binary interaction: " +
                "participant A=["+(getParticipantA() != null ? getParticipantA().toString() : "") + "], participant B=[" +
                (getParticipantB() != null ? getParticipantB().toString() : "")+"], Complex expansion=["+
                (getComplexExpansion() != null ? getComplexExpansion().toString() : "")+"]";
    }

}

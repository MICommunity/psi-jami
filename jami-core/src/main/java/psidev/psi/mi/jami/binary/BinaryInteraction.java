package psidev.psi.mi.jami.binary;

import psidev.psi.mi.jami.model.Confidence;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Participant;

/**
 * A Binary interaction is an interaction only composed of two participants.
 *
 * It can be a 'true' binary interaction (the complexExpansion is null) or it can be an expanded binary interaction
 * from an original n-ary interaction (the complex expansion is not null)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/06/13</pre>
 */
public interface BinaryInteraction<T extends Participant> extends Interaction<T> {

    /**
     * The first participant of the binary interaction.
     *
     * @return first participant of the binary interaction
     */
    public T getParticipantA();

    /**
     * The second participant of the binary interaction
     *
     * @return second participant of the binary interaction
     */
    public T getParticipantB();

    /**
     * Sets the first participant of this interaction
     *
     * @param participantA : the first participant
     */
    public void setParticipantA(T participantA);

    /**
     * Sets the second participant of this interaction
     *
     * @param participantB : the second participant
     */
    public void setParticipantB(T participantB);

    /**
     * The complex expansion method if this binary interaction is expanded from a complex or n-ary interaction.
     * This is a controlled vocabulary term and can be null if the binary interaction has not been expanded.
     *
     * @return the complex expansion method
     */
    public CvTerm getComplexExpansion();

    /**
     * Sets the complex expansion of this binary interaction
     *
     * @param expansion : the complex expansion
     */
    public void setComplexExpansion(CvTerm expansion);

    /**
     * The causal regulatory mechanism for the binary interaction
     *
     * @return the complex regulatory mechanism CvTerm
     */
    public CvTerm getCausalRegulatoryMechanism();

    /**
     * Sets the causal regulatory mechanism of this binary interaction
     *
     * @param causalRegulatoryMechanism : the complex regulatory mechanism CvTerm
     */
    public void setCausalRegulatoryMechanism(CvTerm causalRegulatoryMechanism);

    /**
     * The MI score of this binary interaction
     *
     * @return the MI score Confidence
     */
    public Confidence getMiScore();
}

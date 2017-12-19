package psidev.psi.mi.jami.listener;

import psidev.psi.mi.jami.model.*;

/**
 * Listener for changes in an experimental entity
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 19/07/13
 * @version $Id: $
 */
public interface ParticipantEvidenceChangeListener<F extends ParticipantEvidence> extends ParticipantChangeListener<F>, ParametersChangeListener<F>, ConfidencesChangeListener<F> {

    /**
     * <p>onExperimentalRoleUpdate</p>
     *
     * @param participant : updated participant
     * @param oldType : old role
     * @param <F> a F object.
     */
    public void onExperimentalRoleUpdate(F participant, CvTerm oldType);

    /**
     * <p>onExpressedInUpdate</p>
     *
     * @param participant : updated participant
     * @param oldOrganism : old organism
     */
    public void onExpressedInUpdate(F participant, Organism oldOrganism);

    /**
     * <p>onAddedIdentificationMethod</p>
     *
     * @param participant : updated participant
     * @param added  : added method
     */
    public void onAddedIdentificationMethod(F participant, CvTerm added);

    /**
     * <p>onRemovedIdentificationMethod</p>
     *
     * @param participant : updated participant
     * @param removed : removed method
     */
    public void onRemovedIdentificationMethod(F participant, CvTerm removed);

    /**
     * <p>onAddedExperimentalPreparation</p>
     *
     * @param participant : updated participant
     * @param added : added preparation
     */
    public void onAddedExperimentalPreparation(F participant, CvTerm added);

    /**
     * <p>onRemovedExperimentalPreparation</p>
     *
     * @param participant : updated participant
     * @param removed : removed preparation
     */
    public void onRemovedExperimentalPreparation(F participant, CvTerm removed);
}

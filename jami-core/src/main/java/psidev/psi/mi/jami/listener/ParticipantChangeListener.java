package psidev.psi.mi.jami.listener;

import psidev.psi.mi.jami.model.*;

/**
 * Listener for changes in a participant
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 19/07/13
 * @version $Id: $
 */
public interface ParticipantChangeListener<F extends Participant> extends AnnotationsChangeListener<F>, XrefsChangeListener<F>, AliasesChangeListener<F>, EntityChangeListener<F> {

    /**
     * <p>onBiologicalRoleUpdate</p>
     *
     * @param participant : updated participant
     * @param oldType : old role
     */
    public void onBiologicalRoleUpdate(F participant, CvTerm oldType);
}

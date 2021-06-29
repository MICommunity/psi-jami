package psidev.psi.mi.jami.model;

import java.io.Serializable;

/**
 *  Participant of a modelled interaction.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/02/13</pre>
 */
public interface ModelledParticipant extends Participant<ModelledInteraction, ModelledFeature>,ModelledEntity, Serializable {
}

package psidev.psi.mi.jami.binary.expansion;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.ComplexType;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.impl.DefaultModelledParticipant;
import psidev.psi.mi.jami.utils.InteractionUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * Matrix expansion for ModelledInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/06/13</pre>
 */
public class ModelledInteractionMatrixExpansion extends AbstractMatrixExpansion<ModelledInteraction, ModelledBinaryInteraction>{

    /** {@inheritDoc} */
    @Override
    protected Collection<ModelledBinaryInteraction> createNewSelfBinaryInteractionsFrom(ModelledInteraction interaction) {
        return Collections.singletonList(getBinaryInteractionFactory().createSelfModelledBinaryInteractionFrom(interaction));
    }

    /** {@inheritDoc} */
    @Override
    protected Collection<ModelledBinaryInteraction> createBinaryInteractionWrappersFrom(ModelledInteraction interaction) {
        return Collections.singletonList(getBinaryInteractionFactory().createModelledBinaryInteractionWrapperFrom(interaction));
    }

    /** {@inheritDoc} */
    @Override
    protected ComplexType findInteractionCategory(ModelledInteraction interaction) {
        return InteractionUtils.findModelledInteractionCategoryOf(interaction);
    }

    /** {@inheritDoc} */
    @Override
    protected <P extends Participant> ModelledBinaryInteraction createBinaryInteraction(ModelledInteraction interaction, P p1, P p2) {
        return getBinaryInteractionFactory().createModelledBinaryInteractionFrom(interaction, (ModelledParticipant)p1, (ModelledParticipant)p2, getMethod());
    }

    /** {@inheritDoc} */
    @Override
    protected ModelledParticipant[] createParticipantsArray(ModelledInteraction interaction) {
        return interaction.getParticipants().toArray(new DefaultModelledParticipant[]{});
    }
}

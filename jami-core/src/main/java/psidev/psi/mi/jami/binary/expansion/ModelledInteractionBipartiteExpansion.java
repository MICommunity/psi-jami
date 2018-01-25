package psidev.psi.mi.jami.binary.expansion;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultModelledParticipant;
import psidev.psi.mi.jami.utils.InteractionUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * Bipartite expansion for ModelledInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/06/13</pre>
 */
public class ModelledInteractionBipartiteExpansion extends AbstractBipartiteExpansion<ModelledInteraction, ModelledBinaryInteraction> {

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
    protected <P extends Participant> ModelledBinaryInteraction createBinaryInteraction(ModelledInteraction interaction, P p1, P p2){
        return getBinaryInteractionFactory().createModelledBinaryInteractionFrom(interaction, (ModelledParticipant)p1, (ModelledParticipant)p2, getMethod());
    }

    /** {@inheritDoc} */
    @Override
    protected ModelledParticipant createParticipantForComplexEntity(Complex complexEntity){
        return new DefaultModelledParticipant(complexEntity);
    }
}

package psidev.psi.mi.jami.binary.impl;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Participant;

/**
 * Binary Wrapper for an Interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>05/06/13</pre>
 */
public class BinaryInteractionWrapper extends AbstractBinaryInteractionWrapper<Interaction<Participant>, Participant>{

    /**
     * <p>Constructor for BinaryInteractionWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.Interaction} object.
     */
    public BinaryInteractionWrapper(Interaction interaction){
        super(interaction);
    }

    /**
     * <p>Constructor for BinaryInteractionWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.Interaction} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public BinaryInteractionWrapper(Interaction interaction, CvTerm complexExpansion){
        super(interaction, complexExpansion);
    }
}

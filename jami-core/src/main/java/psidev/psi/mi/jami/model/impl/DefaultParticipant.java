package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

/**
 * Default implementation for participant
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the participant object is a complex object.
 * To compare participant objects, you can use some comparators provided by default:
 * - DefaultParticipantBaseComparator
 * - UnambiguousParticipantBaseComparator
 * - DefaultExactParticipantBaseComparator
 * - UnambiguousExactParticipantBaseComparator
 * - ParticipantBaseComparator
 * - DefaultParticipantComparator
 * - UnambiguousParticipantComparator
 * - DefaultExactParticipantComparator
 * - UnambiguousExactParticipantComparator
 * - ParticipantComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/02/13</pre>
 */
public class DefaultParticipant extends AbstractParticipant<Interaction, Feature> {
    /**
     * <p>Constructor for DefaultParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public DefaultParticipant(Interactor interactor) {
        super(interactor);
    }

    /**
     * <p>Constructor for DefaultParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultParticipant(Interactor interactor, CvTerm bioRole) {
        super(interactor, bioRole);
    }

    /**
     * <p>Constructor for DefaultParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public DefaultParticipant(Interactor interactor, Stoichiometry stoichiometry) {
        super(interactor, stoichiometry);
    }

    /**
     * <p>Constructor for DefaultParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public DefaultParticipant(Interactor interactor, CvTerm bioRole, Stoichiometry stoichiometry) {
        super(interactor, bioRole, stoichiometry);
    }
}

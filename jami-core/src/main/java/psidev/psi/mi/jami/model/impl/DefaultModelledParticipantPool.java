package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

/**
 * Default implementation of ParticipantPool
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/07/14</pre>
 */
public class DefaultModelledParticipantPool extends AbstractParticipantPool<ModelledInteraction,ModelledFeature,ModelledParticipantCandidate>
implements ModelledParticipantPool{
    /**
     * <p>Constructor for DefaultModelledParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     */
    public DefaultModelledParticipantPool(String poolName) {
        super(poolName);
    }

    /**
     * <p>Constructor for DefaultModelledParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultModelledParticipantPool(String poolName, CvTerm bioRole) {
        super(poolName, bioRole);
    }

    /**
     * <p>Constructor for DefaultModelledParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public DefaultModelledParticipantPool(String poolName, Stoichiometry stoichiometry) {
        super(poolName, stoichiometry);
    }

    /**
     * <p>Constructor for DefaultModelledParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public DefaultModelledParticipantPool(String poolName, CvTerm bioRole, Stoichiometry stoichiometry) {
        super(poolName, bioRole, stoichiometry);
    }
}

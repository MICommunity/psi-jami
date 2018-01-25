package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

/**
 * Default implementation of ParticipantPool
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/07/14</pre>
 */
public class DefaultParticipantPool extends AbstractParticipantPool<Interaction,Feature,ParticipantCandidate>{
    /**
     * <p>Constructor for DefaultParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     */
    public DefaultParticipantPool(String poolName) {
        super(poolName);
    }

    /**
     * <p>Constructor for DefaultParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultParticipantPool(String poolName, CvTerm bioRole) {
        super(poolName, bioRole);
    }

    /**
     * <p>Constructor for DefaultParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public DefaultParticipantPool(String poolName, Stoichiometry stoichiometry) {
        super(poolName, stoichiometry);
    }

    /**
     * <p>Constructor for DefaultParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public DefaultParticipantPool(String poolName, CvTerm bioRole, Stoichiometry stoichiometry) {
        super(poolName, bioRole, stoichiometry);
    }
}

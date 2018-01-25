package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

/**
 * Default implementation of named participant.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class DefaultNamedParticipant extends DefaultParticipant implements NamedParticipant<Interaction, Feature> {
    private String shortName;
    private String fullName;

    /**
     * <p>Constructor for DefaultNamedParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public DefaultNamedParticipant(Interactor interactor) {
        super(interactor);
    }

    /**
     * <p>Constructor for DefaultNamedParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedParticipant(Interactor interactor, CvTerm bioRole) {
        super(interactor, bioRole);
    }

    /**
     * <p>Constructor for DefaultNamedParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public DefaultNamedParticipant(Interactor interactor, Stoichiometry stoichiometry) {
        super(interactor, stoichiometry);
    }

    /**
     * <p>Constructor for DefaultNamedParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public DefaultNamedParticipant(Interactor interactor, CvTerm bioRole, Stoichiometry stoichiometry) {
        super(interactor, bioRole, stoichiometry);
    }

    /**
     * <p>Getter for the field <code>shortName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getShortName() {
        return shortName;
    }

    /** {@inheritDoc} */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * <p>Getter for the field <code>fullName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFullName() {
        return fullName;
    }

    /** {@inheritDoc} */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

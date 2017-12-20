package psidev.psi.mi.jami.binary.impl;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Participant;

/**
 * Default implementation for BinaryInteraction
 *
 * Note: the methods equals and hashCode have not been overridden. Use the same comparators as for DefaultInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/06/13</pre>
 */
public class DefaultBinaryInteraction extends AbstractBinaryInteraction<Participant> {

    /**
     * <p>Constructor for DefaultBinaryInteraction.</p>
     */
    public DefaultBinaryInteraction(){
        super();
    }

    /**
     * <p>Constructor for DefaultBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultBinaryInteraction(String shortName){
        super(shortName);
    }

    /**
     * <p>Constructor for DefaultBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultBinaryInteraction(String shortName, CvTerm type){
        super(shortName, type);
    }

    /**
     * <p>Constructor for DefaultBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public DefaultBinaryInteraction(Participant participantA, Participant participantB) {
        super(participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public DefaultBinaryInteraction(String shortName, Participant participantA, Participant participantB) {
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public DefaultBinaryInteraction(String shortName, CvTerm type, Participant participantA, Participant participantB) {
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for DefaultBinaryInteraction.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultBinaryInteraction(CvTerm complexExpansion) {
        super(complexExpansion);
    }

    /**
     * <p>Constructor for DefaultBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultBinaryInteraction(String shortName, CvTerm type, CvTerm complexExpansion) {
        super(shortName, type, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultBinaryInteraction(Participant participantA, Participant participantB, CvTerm complexExpansion) {
        super(participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultBinaryInteraction(String shortName, Participant participantA, Participant participantB, CvTerm complexExpansion) {
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for DefaultBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultBinaryInteraction(String shortName, CvTerm type, Participant participantA, Participant participantB, CvTerm complexExpansion) {
        super(shortName, type, participantA, participantB, complexExpansion);
    }
}

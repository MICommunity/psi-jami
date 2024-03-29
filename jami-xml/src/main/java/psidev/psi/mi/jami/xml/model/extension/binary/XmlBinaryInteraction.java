package psidev.psi.mi.jami.xml.model.extension.binary;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Participant;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Xml implementation of binary interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
@XmlTransient
public class XmlBinaryInteraction extends AbstractExtendedXmlBinaryInteraction<Participant> {

    /**
     * <p>Constructor for XmlBinaryInteraction.</p>
     */
    public XmlBinaryInteraction() {
    }

    /**
     * <p>Constructor for XmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public XmlBinaryInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for XmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for XmlBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public XmlBinaryInteraction(Participant participantA, Participant participantB) {
        super(participantA, participantB);
    }

    /**
     * <p>Constructor for XmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public XmlBinaryInteraction(String shortName, Participant participantA, Participant participantB) {
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for XmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public XmlBinaryInteraction(String shortName, CvTerm type, Participant participantA, Participant participantB) {
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for XmlBinaryInteraction.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteraction(CvTerm complexExpansion) {
        super(complexExpansion);
    }

    /**
     * <p>Constructor for XmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteraction(String shortName, CvTerm type, CvTerm complexExpansion) {
        super(shortName, type, complexExpansion);
    }

    /**
     * <p>Constructor for XmlBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteraction(Participant participantA, Participant participantB, CvTerm complexExpansion) {
        super(participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for XmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteraction(String shortName, Participant participantA, Participant participantB, CvTerm complexExpansion) {
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for XmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteraction(String shortName, CvTerm type, Participant participantA, Participant participantB, CvTerm complexExpansion) {
        super(shortName, type, participantA, participantB, complexExpansion);
    }
}

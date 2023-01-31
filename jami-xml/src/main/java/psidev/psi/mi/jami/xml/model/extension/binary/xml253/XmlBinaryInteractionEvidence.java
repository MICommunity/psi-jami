package psidev.psi.mi.jami.xml.model.extension.binary.xml253;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.xml.model.extension.binary.AbstractXmlBinaryInteractionEvidence;
import psidev.psi.mi.jami.xml.model.extension.xml253.XmlXref;

/**
 * Xml implementation of BinaryInteractionEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
@XmlTransient
public class XmlBinaryInteractionEvidence extends AbstractXmlBinaryInteractionEvidence {

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     */
    public XmlBinaryInteractionEvidence() {
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public XmlBinaryInteractionEvidence(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteractionEvidence(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public XmlBinaryInteractionEvidence(ParticipantEvidence participantA, ParticipantEvidence participantB) {
        super(participantA, participantB);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public XmlBinaryInteractionEvidence(String shortName, ParticipantEvidence participantA, ParticipantEvidence participantB) {
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public XmlBinaryInteractionEvidence(String shortName, CvTerm type, ParticipantEvidence participantA, ParticipantEvidence participantB) {
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteractionEvidence(CvTerm complexExpansion) {
        super(complexExpansion);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteractionEvidence(String shortName, CvTerm type, CvTerm complexExpansion) {
        super(shortName, type, complexExpansion);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteractionEvidence(ParticipantEvidence participantA, ParticipantEvidence participantB, CvTerm complexExpansion) {
        super(participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteractionEvidence(String shortName, ParticipantEvidence participantA, ParticipantEvidence participantB, CvTerm complexExpansion) {
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteractionEvidence(String shortName, CvTerm type, ParticipantEvidence participantA, ParticipantEvidence participantB, CvTerm complexExpansion) {
        super(shortName, type, participantA, participantB, complexExpansion);
    }

    /** {@inheritDoc} */
    public void setImexId(CvTerm database, String id, CvTerm qualifier) {
        setImexId(new XmlXref(database, id, qualifier));
    }
}

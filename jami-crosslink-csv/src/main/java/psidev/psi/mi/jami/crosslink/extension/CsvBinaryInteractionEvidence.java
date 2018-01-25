package psidev.psi.mi.jami.crosslink.extension;

import psidev.psi.mi.jami.binary.impl.DefaultBinaryInteractionEvidence;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ParticipantEvidence;

/**
 * Crosslink CSV implementation of BinaryInteractionEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class CsvBinaryInteractionEvidence extends DefaultBinaryInteractionEvidence implements FileSourceContext, CrosslinkCsvInteraction {

    private FileSourceLocator sourceLocator;
    private String bait;

    /**
     * <p>Constructor for CsvBinaryInteractionEvidence.</p>
     */
    public CsvBinaryInteractionEvidence() {
        super();
    }

    /**
     * <p>Constructor for CsvBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public CsvBinaryInteractionEvidence(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for CsvBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvBinaryInteractionEvidence(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for CsvBinaryInteractionEvidence.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public CsvBinaryInteractionEvidence(ParticipantEvidence participantA, ParticipantEvidence participantB) {
        super(participantA, participantB);
    }

    /**
     * <p>Constructor for CsvBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public CsvBinaryInteractionEvidence(String shortName, ParticipantEvidence participantA, ParticipantEvidence participantB) {
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for CsvBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public CsvBinaryInteractionEvidence(String shortName, CvTerm type, ParticipantEvidence participantA, ParticipantEvidence participantB) {
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for CsvBinaryInteractionEvidence.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvBinaryInteractionEvidence(CvTerm complexExpansion) {
        super(complexExpansion);
    }

    /**
     * <p>Constructor for CsvBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvBinaryInteractionEvidence(String shortName, CvTerm type, CvTerm complexExpansion) {
        super(shortName, type, complexExpansion);
    }

    /**
     * <p>Constructor for CsvBinaryInteractionEvidence.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvBinaryInteractionEvidence(ParticipantEvidence participantA, ParticipantEvidence participantB, CvTerm complexExpansion) {
        super(participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for CsvBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvBinaryInteractionEvidence(String shortName, ParticipantEvidence participantA, ParticipantEvidence participantB, CvTerm complexExpansion) {
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for CsvBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvBinaryInteractionEvidence(String shortName, CvTerm type, ParticipantEvidence participantA, ParticipantEvidence participantB, CvTerm complexExpansion) {
        super(shortName, type, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /**
     * <p>getNaryGroup.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getNaryGroup() {
        return bait;
    }

    /** {@inheritDoc} */
    public void setNaryGroup(String bait) {
        this.bait = bait;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "CSV Binary interaction evidence : "+sourceLocator != null ? sourceLocator.toString():super.toString();
    }
}

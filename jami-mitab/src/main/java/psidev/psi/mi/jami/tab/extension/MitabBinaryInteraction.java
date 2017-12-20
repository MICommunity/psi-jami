package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.binary.impl.DefaultBinaryInteraction;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Participant;

/**
 * A MitabBinaryInteraction is a binaryInteraction with a MitabSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class MitabBinaryInteraction extends DefaultBinaryInteraction implements FileSourceContext {

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabBinaryInteraction.</p>
     */
    public MitabBinaryInteraction() {
        super();
    }

    /**
     * <p>Constructor for MitabBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public MitabBinaryInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for MitabBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabBinaryInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for MitabBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public MitabBinaryInteraction(Participant participantA, Participant participantB) {
        super(participantA, participantB);
    }

    /**
     * <p>Constructor for MitabBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public MitabBinaryInteraction(String shortName, Participant participantA, Participant participantB) {
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for MitabBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public MitabBinaryInteraction(String shortName, CvTerm type, Participant participantA, Participant participantB) {
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for MitabBinaryInteraction.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabBinaryInteraction(CvTerm complexExpansion) {
        super(complexExpansion);
    }

    /**
     * <p>Constructor for MitabBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabBinaryInteraction(String shortName, CvTerm type, CvTerm complexExpansion) {
        super(shortName, type, complexExpansion);
    }

    /**
     * <p>Constructor for MitabBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabBinaryInteraction(Participant participantA, Participant participantB, CvTerm complexExpansion) {
        super(participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for MitabBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabBinaryInteraction(String shortName, Participant participantA, Participant participantB, CvTerm complexExpansion) {
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for MitabBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.Participant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabBinaryInteraction(String shortName, CvTerm type, Participant participantA, Participant participantB, CvTerm complexExpansion) {
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

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Binary interaction: "+getSourceLocator().toString():super.toString());
    }
}

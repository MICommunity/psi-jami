package psidev.psi.mi.jami.crosslink.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.model.impl.DefaultFeatureEvidence;

/**
 * CrossLink CSV extension for FeatureEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/07/13</pre>
 */
public class CsvFeatureEvidence extends DefaultFeatureEvidence implements FileSourceContext{
    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for CsvFeatureEvidence.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public CsvFeatureEvidence(ParticipantEvidence participant) {
        super(participant);
    }

    /**
     * <p>Constructor for CsvFeatureEvidence.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public CsvFeatureEvidence(ParticipantEvidence participant, String shortName, String fullName) {
        super(participant, shortName, fullName);
    }

    /**
     * <p>Constructor for CsvFeatureEvidence.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvFeatureEvidence(ParticipantEvidence participant, CvTerm type) {
        super(participant, type);
    }

    /**
     * <p>Constructor for CsvFeatureEvidence.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvFeatureEvidence(ParticipantEvidence participant, String shortName, String fullName, CvTerm type) {
        super(participant, shortName, fullName, type);
    }

    /**
     * <p>Constructor for CsvFeatureEvidence.</p>
     */
    public CsvFeatureEvidence() {
        super();
    }

    /**
     * <p>Constructor for CsvFeatureEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public CsvFeatureEvidence(String shortName, String fullName) {
        super(shortName, fullName);
    }

    /**
     * <p>Constructor for CsvFeatureEvidence.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvFeatureEvidence(CvTerm type) {
        super(type);
    }

    /**
     * <p>Constructor for CsvFeatureEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvFeatureEvidence(String shortName, String fullName, CvTerm type) {
        super(shortName, fullName, type);
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
        return "Feature: "+sourceLocator != null ? sourceLocator.toString():super.toString();
    }
}

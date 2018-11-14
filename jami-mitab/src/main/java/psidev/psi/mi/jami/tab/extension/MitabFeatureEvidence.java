package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.model.impl.DefaultFeatureEvidence;

/**
 * Mitab extension for FeatureEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/07/13</pre>
 */
public class MitabFeatureEvidence extends DefaultFeatureEvidence implements MitabFeature<Entity<FeatureEvidence>, FeatureEvidence>, FileSourceContext {
    private String text;
    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabFeatureEvidence.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public MitabFeatureEvidence(ParticipantEvidence participant) {
        super(participant);
    }

    /**
     * <p>Constructor for MitabFeatureEvidence.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public MitabFeatureEvidence(ParticipantEvidence participant, String shortName, String fullName) {
        super(participant, shortName, fullName);
    }

    /**
     * <p>Constructor for MitabFeatureEvidence.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabFeatureEvidence(ParticipantEvidence participant, CvTerm type) {
        super(participant, type);
    }

    /**
     * <p>Constructor for MitabFeatureEvidence.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabFeatureEvidence(ParticipantEvidence participant, String shortName, String fullName, CvTerm type) {
        super(participant, shortName, fullName, type);
    }

    /**
     * <p>Constructor for MitabFeatureEvidence.</p>
     */
    public MitabFeatureEvidence() {
        super();
    }

    /**
     * <p>Constructor for MitabFeatureEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public MitabFeatureEvidence(String shortName, String fullName) {
        super(shortName, fullName);
    }

    /**
     * <p>Constructor for MitabFeatureEvidence.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabFeatureEvidence(CvTerm type) {
        super(type);
    }

    /**
     * <p>Constructor for MitabFeatureEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabFeatureEvidence(String shortName, String fullName, CvTerm type) {
        super(shortName, fullName, type);
    }

    /**
     * <p>Getter for the field <code>text</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getText() {
        return text;
    }

    /** {@inheritDoc} */
    public void setText(String text) {
        this.text = text;
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
        return (getSourceLocator() != null ? "Feature: "+getSourceLocator().toString():super.toString());
    }
}

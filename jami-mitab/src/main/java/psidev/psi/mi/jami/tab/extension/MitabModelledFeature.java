package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ModelledEntity;
import psidev.psi.mi.jami.model.ModelledFeature;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.model.impl.DefaultModelledFeature;

/**
 * Mitab extension of ModelledFeature
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/07/13</pre>
 */
public class MitabModelledFeature extends DefaultModelledFeature implements MitabFeature<ModelledEntity,ModelledFeature>, FileSourceContext{
    private String text;
    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabModelledFeature.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public MitabModelledFeature(ModelledParticipant participant) {
        super(participant);
    }

    /**
     * <p>Constructor for MitabModelledFeature.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public MitabModelledFeature(ModelledParticipant participant, String shortName, String fullName) {
        super(participant, shortName, fullName);
    }

    /**
     * <p>Constructor for MitabModelledFeature.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabModelledFeature(ModelledParticipant participant, CvTerm type) {
        super(participant, type);
    }

    /**
     * <p>Constructor for MitabModelledFeature.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabModelledFeature(ModelledParticipant participant, String shortName, String fullName, CvTerm type) {
        super(participant, shortName, fullName, type);
    }

    /**
     * <p>Constructor for MitabModelledFeature.</p>
     */
    public MitabModelledFeature() {
        super();
    }

    /**
     * <p>Constructor for MitabModelledFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public MitabModelledFeature(String shortName, String fullName) {
        super(shortName, fullName);
    }

    /**
     * <p>Constructor for MitabModelledFeature.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabModelledFeature(CvTerm type) {
        super(type);
    }

    /**
     * <p>Constructor for MitabModelledFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabModelledFeature(String shortName, String fullName, CvTerm type) {
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

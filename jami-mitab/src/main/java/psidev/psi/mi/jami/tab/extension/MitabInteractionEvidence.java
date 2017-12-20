package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultInteractionEvidence;

/**
 * Mitab extension for InteractionEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabInteractionEvidence extends DefaultInteractionEvidence implements FileSourceContext {

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public MitabInteractionEvidence(Experiment experiment) {
        super(experiment);
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param shortName a {@link java.lang.String} object.
     */
    public MitabInteractionEvidence(Experiment experiment, String shortName) {
        super(experiment, shortName);
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param shortName a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public MitabInteractionEvidence(Experiment experiment, String shortName, Source source) {
        super(experiment, shortName, source);
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabInteractionEvidence(Experiment experiment, String shortName, CvTerm type) {
        super(experiment, shortName, type);
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabInteractionEvidence(Experiment experiment, Xref imexId) {
        super(experiment, imexId);
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param shortName a {@link java.lang.String} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabInteractionEvidence(Experiment experiment, String shortName, Xref imexId) {
        super(experiment, shortName, imexId);
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param shortName a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabInteractionEvidence(Experiment experiment, String shortName, Source source, Xref imexId) {
        super(experiment, shortName, source, imexId);
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabInteractionEvidence(Experiment experiment, String shortName, CvTerm type, Xref imexId) {
        super(experiment, shortName, type, imexId);
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabInteractionEvidence(Xref imexId) {
        super(imexId);
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabInteractionEvidence(String shortName, Xref imexId) {
        super(shortName, imexId);
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabInteractionEvidence(String shortName, Source source, Xref imexId) {
        super(shortName, source, imexId);
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabInteractionEvidence(String shortName, CvTerm type, Xref imexId) {
        super(shortName, type, imexId);
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     */
    public MitabInteractionEvidence() {
        super();
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public MitabInteractionEvidence(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for MitabInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabInteractionEvidence(String shortName, CvTerm type) {
        super(shortName, type);
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
        return (getSourceLocator() != null ? "Interaction: "+getSourceLocator().toString():super.toString());
    }
}

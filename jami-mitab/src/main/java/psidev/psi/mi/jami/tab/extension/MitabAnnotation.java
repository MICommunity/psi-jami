package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultAnnotation;
import psidev.psi.mi.jami.tab.utils.MitabUtils;

/**
 * Extension of Annotation for Mitab with a sourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabAnnotation extends DefaultAnnotation implements FileSourceContext{

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabAnnotation.</p>
     *
     * @param topic a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabAnnotation(CvTerm topic) {
        super(topic);
    }

    /**
     * <p>Constructor for MitabAnnotation.</p>
     *
     * @param topic a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     */
    public MitabAnnotation(CvTerm topic, String value) {
        super(topic, value);
    }

    /**
     * <p>Constructor for MitabAnnotation.</p>
     *
     * @param topic a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     */
    public MitabAnnotation(String topic, String value) {
        super(new MitabCvTerm(topic != null ? topic : MitabUtils.UNKNOWN_DATABASE), value);
    }

    /**
     * <p>Constructor for MitabAnnotation.</p>
     *
     * @param topic a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param sourceLocator a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public MitabAnnotation(CvTerm topic, FileSourceLocator sourceLocator) {
        super(topic);
        this.sourceLocator = sourceLocator;
    }

    /**
     * <p>Constructor for MitabAnnotation.</p>
     *
     * @param topic a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     * @param sourceLocator a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public MitabAnnotation(CvTerm topic, String value, FileSourceLocator sourceLocator) {
        super(topic, value);
        this.sourceLocator = sourceLocator;
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return this.sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Mitab Annotation: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

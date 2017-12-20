package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.impl.DefaultFeature;

/**
 * A DefaultMitabFeature is a feature in MITAB with some free text.
 *
 * It can be ModelledFeature of FeatureEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>07/06/13</pre>
 */
public class DefaultMitabFeature extends DefaultFeature implements MitabFeature<Entity, Feature>, FileSourceContext{

    private String text;
    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for DefaultMitabFeature.</p>
     */
    public DefaultMitabFeature() {
        super();
    }

    /**
     * <p>Constructor for DefaultMitabFeature.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultMitabFeature(CvTerm type) {
        super(type);
    }

    /**
     * <p>Constructor for DefaultMitabFeature.</p>
     *
     * @param type a {@link java.lang.String} object.
     */
    public DefaultMitabFeature(String type) {
        super(new MitabCvTerm(type));
    }

    /**
     * <p>Constructor for DefaultMitabFeature.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param interpro a {@link java.lang.String} object.
     */
    public DefaultMitabFeature(CvTerm type, String interpro) {
        super(type, interpro);
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

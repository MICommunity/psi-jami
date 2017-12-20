package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultModelledConfidence;
import psidev.psi.mi.jami.tab.utils.MitabUtils;

/**
 * A MitabConfidence is a confidence with some text
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>10/06/13</pre>
 */
public class MitabConfidence extends DefaultModelledConfidence implements FileSourceContext{

    private String text;
    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabConfidence.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     */
    public MitabConfidence(CvTerm type, String value) {
        super(type, value);
    }

    /**
     * <p>Constructor for MitabConfidence.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     * @param text a {@link java.lang.String} object.
     */
    public MitabConfidence(CvTerm type, String value, String text) {
        super(type, value);
        this.text = text;
    }

    /**
     * <p>Constructor for MitabConfidence.</p>
     *
     * @param type a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     * @param text a {@link java.lang.String} object.
     */
    public MitabConfidence(String type, String value, String text) {
        super(new MitabCvTerm(type != null ? type : MitabUtils.UNKNOWN_TYPE), value != null ? value : MitabUtils.UNKNOWN_ID);
        this.text = text;
    }

    /**
     * <p>Getter for the field <code>text</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getText() {
        return text;
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
        return "Mitab Confidence: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

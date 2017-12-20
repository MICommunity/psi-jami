package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.model.impl.DefaultModelledInteraction;

/**
 * Mitab extension for ModelledInteraction.
 * It contains a FileSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabModelledInteraction extends DefaultModelledInteraction implements FileSourceContext{

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabModelledInteraction.</p>
     */
    public MitabModelledInteraction() {
        super();
    }

    /**
     * <p>Constructor for MitabModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public MitabModelledInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for MitabModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public MitabModelledInteraction(String shortName, Source source) {
        super(shortName, source);
    }

    /**
     * <p>Constructor for MitabModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabModelledInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for MitabModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabModelledInteraction(String shortName, Source source, CvTerm type) {
        super(shortName, source, type);
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

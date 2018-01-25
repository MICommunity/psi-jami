package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultInteraction;

/**
 * Mitab extension for Interaction
 * It contains a SourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabInteraction extends DefaultInteraction implements FileSourceContext{

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabInteraction.</p>
     */
    public MitabInteraction() {
        super();
    }

    /**
     * <p>Constructor for MitabInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public MitabInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for MitabInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabInteraction(String shortName, CvTerm type) {
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

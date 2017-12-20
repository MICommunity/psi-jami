package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.impl.DefaultStoichiometry;

/**
 * Mitab extension for Stoichiometry.
 * It contains a FileSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabStoichiometry extends DefaultStoichiometry implements FileSourceContext{

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabStoichiometry.</p>
     *
     * @param value a int.
     */
    public MitabStoichiometry(int value) {
        super(value);
    }

    /**
     * <p>Constructor for MitabStoichiometry.</p>
     *
     * @param minValue a int.
     * @param maxValue a int.
     */
    public MitabStoichiometry(int minValue, int maxValue) {
        super(minValue, maxValue);
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
        return "Mitab Stoichiometry: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

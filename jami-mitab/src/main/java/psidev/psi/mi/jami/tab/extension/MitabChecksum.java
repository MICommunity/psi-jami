package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultChecksum;
import psidev.psi.mi.jami.tab.utils.MitabUtils;

/**
 * Mitab extension for Checksum.
 * It contains a file sourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabChecksum extends DefaultChecksum implements FileSourceContext{

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabChecksum.</p>
     *
     * @param method a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     */
    public MitabChecksum(CvTerm method, String value) {
        super(method, value);
    }

    /**
     * <p>Constructor for MitabChecksum.</p>
     *
     * @param method a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     */
    public MitabChecksum(String method, String value) {
        super(new MitabCvTerm(method != null ? method: MitabUtils.UNKNOWN_TYPE), value!= null ? value : MitabUtils.UNKNOWN_ID);
    }

    /**
     * <p>Constructor for MitabChecksum.</p>
     *
     * @param method a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     * @param locator a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public MitabChecksum(CvTerm method, String value, FileSourceLocator locator) {
        super(method, value);
        this.sourceLocator = locator;
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
        return "Mitab Checksum: "+(getSourceLocator() != null ? getSourceLocator().toString() : super.toString());
    }
}

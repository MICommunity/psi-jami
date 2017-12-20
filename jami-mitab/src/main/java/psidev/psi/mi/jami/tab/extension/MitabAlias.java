package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultAlias;
import psidev.psi.mi.jami.tab.utils.MitabUtils;

/**
 * Extended alias in MITAB
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>07/06/13</pre>
 */
public class MitabAlias extends DefaultAlias implements FileSourceContext{

    private String dbSource;
    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabAlias.</p>
     *
     * @param dbSource a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param name a {@link java.lang.String} object.
     */
    public MitabAlias(String dbSource, CvTerm type, String name) {
        super(type, name != null ? name : MitabUtils.UNKNOWN_NAME);
        this.dbSource = dbSource != null ? dbSource : MitabUtils.UNKNOWN_DATABASE;
    }

    /**
     * <p>Constructor for MitabAlias.</p>
     *
     * @param dbSource a {@link java.lang.String} object.
     * @param name a {@link java.lang.String} object.
     */
    public MitabAlias(String dbSource, String name) {
        super(name != null ? name : MitabUtils.UNKNOWN_NAME);
        this.dbSource = dbSource != null ? dbSource : MitabUtils.UNKNOWN_DATABASE;
    }

    /**
     * <p>Constructor for MitabAlias.</p>
     *
     * @param dbSource a {@link java.lang.String} object.
     * @param name a {@link java.lang.String} object.
     * @param type a {@link java.lang.String} object.
     */
    public MitabAlias(String dbSource, String name, String type) {
        super(type != null ? new MitabCvTerm(type) : null, name != null ? name : MitabUtils.UNKNOWN_NAME);
        this.dbSource = dbSource != null ? dbSource : MitabUtils.UNKNOWN_DATABASE;
    }

    /**
     * <p>Constructor for MitabAlias.</p>
     *
     * @param dbSource a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param name a {@link java.lang.String} object.
     * @param locator a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public MitabAlias(String dbSource, CvTerm type, String name, FileSourceLocator locator) {
        super(type, name != null ? name : MitabUtils.UNKNOWN_NAME);
        this.dbSource = dbSource != null ? dbSource : MitabUtils.UNKNOWN_DATABASE;
        this.sourceLocator = locator;
    }

    /**
     * <p>Constructor for MitabAlias.</p>
     *
     * @param dbSource a {@link java.lang.String} object.
     * @param name a {@link java.lang.String} object.
     * @param locator a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public MitabAlias(String dbSource, String name, FileSourceLocator locator) {
        super(name != null ? name : MitabUtils.UNKNOWN_NAME);
        this.dbSource = dbSource != null ? dbSource : MitabUtils.UNKNOWN_DATABASE;
        this.sourceLocator = locator;
    }

    /**
     * <p>Getter for the field <code>dbSource</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDbSource() {
        return dbSource;
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
        return "Mitab Alias: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

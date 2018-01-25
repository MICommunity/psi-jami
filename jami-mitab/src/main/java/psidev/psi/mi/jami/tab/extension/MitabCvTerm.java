package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.tab.utils.MitabUtils;
import psidev.psi.mi.jami.utils.XrefUtils;

/**
 * Mitab extension for CvTerm.
 * It contains a FileSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabCvTerm extends DefaultCvTerm implements FileSourceContext{

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabCvTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public MitabCvTerm(String shortName) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME);
    }

    /**
     * <p>Constructor for MitabCvTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param miIdentifier a {@link java.lang.String} object.
     */
    public MitabCvTerm(String shortName, String miIdentifier) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME, miIdentifier);
    }

    /**
     * <p>Constructor for MitabCvTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param miIdentifier a {@link java.lang.String} object.
     */
    public MitabCvTerm(String shortName, String fullName, String miIdentifier) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME, fullName, miIdentifier);
    }

    /**
     * <p>Constructor for MitabCvTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabCvTerm(String shortName, Xref ontologyId) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME, ontologyId);
    }

    /**
     * <p>Constructor for MitabCvTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabCvTerm(String shortName, String fullName, Xref ontologyId) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME, fullName, ontologyId);
    }

    /**
     * <p>Constructor for MitabCvTerm.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param db a {@link java.lang.String} object.
     * @param id a {@link java.lang.String} object.
     */
    public MitabCvTerm(String shortName, String fullName, String db, String id) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME);
        if (db != null && id != null && id.length() > 0){
            getIdentifiers().add(XrefUtils.createIdentityXref(db, id));
        }
        else if (db == null && id != null && id.length() > 0){
            getIdentifiers().add(XrefUtils.createIdentityXref(MitabUtils.UNKNOWN_DATABASE, id));
        }
        else if (id == null || (id != null && id.length() == 0)){
            getIdentifiers().add(XrefUtils.createIdentityXref(db, MitabUtils.UNKNOWN_ID));
        }
        setFullName(fullName != null? fullName : shortName);
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
        return "Mitab CV Term: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

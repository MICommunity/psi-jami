package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultSource;
import psidev.psi.mi.jami.tab.utils.MitabUtils;
import psidev.psi.mi.jami.utils.XrefUtils;

/**
 * Mitab extension of DefaultMitabSource
 * It contains a FileSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabSource extends DefaultSource implements FileSourceContext{

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public MitabSource(String shortName) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME);
    }

    /**
     * <p>Constructor for MitabSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabSource(String shortName, Xref ontologyId) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME, ontologyId);
    }

    /**
     * <p>Constructor for MitabSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabSource(String shortName, String fullName, Xref ontologyId) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME, fullName, ontologyId);
    }

    /**
     * <p>Constructor for MitabSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param url a {@link java.lang.String} object.
     * @param address a {@link java.lang.String} object.
     * @param bibRef a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public MitabSource(String shortName, String url, String address, Publication bibRef) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME, url, address, bibRef);
    }

    /**
     * <p>Constructor for MitabSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param url a {@link java.lang.String} object.
     * @param address a {@link java.lang.String} object.
     * @param bibRef a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public MitabSource(String shortName, Xref ontologyId, String url, String address, Publication bibRef) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME, ontologyId, url, address, bibRef);
    }

    /**
     * <p>Constructor for MitabSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param url a {@link java.lang.String} object.
     * @param address a {@link java.lang.String} object.
     * @param bibRef a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public MitabSource(String shortName, String fullName, Xref ontologyId, String url, String address, Publication bibRef) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME, fullName, ontologyId, url, address, bibRef);
    }

    /**
     * <p>Constructor for MitabSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param miId a {@link java.lang.String} object.
     */
    public MitabSource(String shortName, String miId) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME, miId);
    }

    /**
     * <p>Constructor for MitabSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param miId a {@link java.lang.String} object.
     */
    public MitabSource(String shortName, String fullName, String miId) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME, fullName, miId);
    }

    /**
     * <p>Constructor for MitabSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param miId a {@link java.lang.String} object.
     * @param url a {@link java.lang.String} object.
     * @param address a {@link java.lang.String} object.
     * @param bibRef a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public MitabSource(String shortName, String miId, String url, String address, Publication bibRef) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME, miId, url, address, bibRef);
    }

    /**
     * <p>Constructor for MitabSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param miId a {@link java.lang.String} object.
     * @param url a {@link java.lang.String} object.
     * @param address a {@link java.lang.String} object.
     * @param bibRef a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public MitabSource(String shortName, String fullName, String miId, String url, String address, Publication bibRef) {
        super(shortName != null ? shortName : MitabUtils.UNKNOWN_NAME, fullName, miId, url, address, bibRef);
    }

    /**
     * <p>Constructor for MitabSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param db a {@link java.lang.String} object.
     * @param id a {@link java.lang.String} object.
     */
    public MitabSource(String shortName, String fullName, String db, String id) {
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
        return "Source: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

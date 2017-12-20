package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.tab.utils.MitabUtils;
import psidev.psi.mi.jami.utils.comparator.xref.UnambiguousXrefComparator;

/**
 * Mitab extension for Xref.
 * It contains a FileSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/06/13</pre>
 */
public class MitabXref implements Xref,FileSourceContext {

    private FileSourceLocator sourceLocator;
    private CvTerm database;
    private String id;
    private String version;
    private CvTerm qualifier;

    /**
     * <p>Constructor for MitabXref.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param id a {@link java.lang.String} object.
     * @param qualifier a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabXref(CvTerm database, String id, CvTerm qualifier){
        this(database, id);
        this.qualifier = qualifier;
    }

    /**
     * <p>Constructor for MitabXref.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param id a {@link java.lang.String} object.
     * @param version a {@link java.lang.String} object.
     * @param qualifier a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabXref(CvTerm database, String id, String version, CvTerm qualifier){
        this(database, id, version);
        this.qualifier = qualifier;
    }

    /**
     * <p>Constructor for MitabXref.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param id a {@link java.lang.String} object.
     * @param version a {@link java.lang.String} object.
     */
    public MitabXref(CvTerm database, String id, String version){
        this(database, id);
        this.version = version;
    }

    /**
     * <p>Constructor for MitabXref.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param id a {@link java.lang.String} object.
     */
    public MitabXref(CvTerm database, String id){
        this.id = (id == null || (id != null && id.length() == 0)) ? MitabUtils.UNKNOWN_ID:id;
        this.database = database!=null ? database : new MitabCvTerm(MitabUtils.UNKNOWN_DATABASE);
    }

    /**
     * <p>Constructor for MitabXref.</p>
     *
     * @param database a {@link java.lang.String} object.
     * @param id a {@link java.lang.String} object.
     * @param qualifier a {@link java.lang.String} object.
     */
    public MitabXref(String database, String id, String qualifier){
        this(database, id);
        if (qualifier != null){
            this.qualifier = new MitabCvTerm(qualifier);
        }
    }

    /**
     * <p>Constructor for MitabXref.</p>
     *
     * @param database a {@link java.lang.String} object.
     * @param id a {@link java.lang.String} object.
     * @param qualifier a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabXref(String database, String id, CvTerm qualifier){
        this(database, id);
        this.qualifier = qualifier;
    }

    /**
     * <p>Constructor for MitabXref.</p>
     *
     * @param database a {@link java.lang.String} object.
     * @param id a {@link java.lang.String} object.
     */
    public MitabXref(String database, String id){

        this.database = new MitabCvTerm(database != null ? database : MitabUtils.UNKNOWN_DATABASE);
        this.id = (id == null || (id != null && id.length() == 0))?MitabUtils.UNKNOWN_ID:id;
    }

    /**
     * <p>Getter for the field <code>database</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getDatabase() {
        return database;
    }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getId() {
        return id;
    }

    /**
     * <p>Getter for the field <code>version</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getVersion() {
        return version;
    }

    /**
     * <p>Getter for the field <code>qualifier</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getQualifier() {
        return this.qualifier;
    }


    /**
     * <p>Setter for the field <code>qualifier</code>.</p>
     *
     * @param qualifier a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public void setQualifier(CvTerm qualifier){
        this.qualifier = qualifier;
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
    public boolean equals(Object o) {

        if (this == o){
            return true;
        }

        // Xrefs are different and it has to be ExternalIdentifier
        if (!(o instanceof Xref)){
            return false;
        }

        return UnambiguousXrefComparator.areEquals(this, (Xref) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousXrefComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Mitab Xref: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

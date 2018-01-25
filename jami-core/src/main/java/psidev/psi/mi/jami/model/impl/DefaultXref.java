package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.comparator.xref.UnambiguousXrefComparator;

/**
 * Default implementation for Xref
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with UnambiguousXrefComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/01/13</pre>
 */
public class DefaultXref implements Xref {

    private CvTerm database;
    private String id;
    private String version;
    private CvTerm qualifier;

    /**
     * <p>Constructor for DefaultXref.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param id a {@link java.lang.String} object.
     * @param qualifier a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultXref(CvTerm database, String id, CvTerm qualifier){
        this(database, id);
        this.qualifier = qualifier;
    }

    /**
     * <p>Constructor for DefaultXref.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param id a {@link java.lang.String} object.
     * @param version a {@link java.lang.String} object.
     * @param qualifier a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultXref(CvTerm database, String id, String version, CvTerm qualifier){
        this(database, id, version);
        this.qualifier = qualifier;
    }

    /**
     * <p>Constructor for DefaultXref.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param id a {@link java.lang.String} object.
     * @param version a {@link java.lang.String} object.
     */
    public DefaultXref(CvTerm database, String id, String version){
        this(database, id);
        this.version = version;
    }

    /**
     * <p>Constructor for DefaultXref.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param id a {@link java.lang.String} object.
     */
    public DefaultXref(CvTerm database, String id){
        if (database == null){
            throw new IllegalArgumentException("The database is required and cannot be null");
        }
        this.database = database;

        if (id == null || (id != null && id.length() == 0)){
            throw new IllegalArgumentException("The id is required and cannot be null or empty");
        }
        this.id = id;
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
        return getDatabase().toString() + ":" + getId() + (getQualifier() != null ? " (" + getQualifier().toString() + ")" : "");
    }
}

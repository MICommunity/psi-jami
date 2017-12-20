package psidev.psi.mi.jami.crosslink.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultXref;

/**
 * CSV extension for Xref.
 * It contains a FileSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/06/13</pre>
 */
public class CsvXref extends DefaultXref implements FileSourceContext {

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for CsvXref.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param id a {@link java.lang.String} object.
     * @param qualifier a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvXref(CvTerm database, String id, CvTerm qualifier){
        super(database, id, qualifier);
    }

    /**
     * <p>Constructor for CsvXref.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param id a {@link java.lang.String} object.
     * @param version a {@link java.lang.String} object.
     * @param qualifier a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CsvXref(CvTerm database, String id, String version, CvTerm qualifier){
        super(database, id, version, qualifier);
    }

    /**
     * <p>Constructor for CsvXref.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param id a {@link java.lang.String} object.
     * @param version a {@link java.lang.String} object.
     */
    public CsvXref(CvTerm database, String id, String version){
        super(database, id, version);
    }

    /**
     * <p>Constructor for CsvXref.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param id a {@link java.lang.String} object.
     */
    public CsvXref(CvTerm database, String id){
        super(database, id);
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
        return "CSV Xref: "+sourceLocator != null ? sourceLocator.toString():super.toString();
    }
}

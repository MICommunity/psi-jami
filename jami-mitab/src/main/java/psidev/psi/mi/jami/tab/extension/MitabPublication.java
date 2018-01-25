package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CurationDepth;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultPublication;

import java.util.Date;

/**
 * Mitab extension of Publication.
 * It contains a FileSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabPublication extends DefaultPublication implements FileSourceContext{

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabPublication.</p>
     */
    public MitabPublication() {
        super();
    }

    /**
     * <p>Constructor for MitabPublication.</p>
     *
     * @param identifier a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabPublication(Xref identifier) {
        super(identifier);
    }

    /**
     * <p>Constructor for MitabPublication.</p>
     *
     * @param identifier a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param curationDepth a {@link psidev.psi.mi.jami.model.CurationDepth} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public MitabPublication(Xref identifier, CurationDepth curationDepth, Source source) {
        super(identifier, curationDepth, source);
    }

    /**
     * <p>Constructor for MitabPublication.</p>
     *
     * @param identifier a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param imexId a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public MitabPublication(Xref identifier, String imexId, Source source) {
        super(identifier, imexId, source);
    }

    /**
     * <p>Constructor for MitabPublication.</p>
     *
     * @param pubmed a {@link java.lang.String} object.
     */
    public MitabPublication(String pubmed) {
        super(pubmed);
    }

    /**
     * <p>Constructor for MitabPublication.</p>
     *
     * @param pubmed a {@link java.lang.String} object.
     * @param curationDepth a {@link psidev.psi.mi.jami.model.CurationDepth} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public MitabPublication(String pubmed, CurationDepth curationDepth, Source source) {
        super(pubmed, curationDepth, source);
    }

    /**
     * <p>Constructor for MitabPublication.</p>
     *
     * @param pubmed a {@link java.lang.String} object.
     * @param imexId a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public MitabPublication(String pubmed, String imexId, Source source) {
        super(pubmed, imexId, source);
    }

    /**
     * <p>Constructor for MitabPublication.</p>
     *
     * @param title a {@link java.lang.String} object.
     * @param journal a {@link java.lang.String} object.
     * @param publicationDate a {@link java.util.Date} object.
     */
    public MitabPublication(String title, String journal, Date publicationDate) {
        super(title, journal, publicationDate);
    }

    /**
     * <p>Constructor for MitabPublication.</p>
     *
     * @param title a {@link java.lang.String} object.
     * @param journal a {@link java.lang.String} object.
     * @param publicationDate a {@link java.util.Date} object.
     * @param curationDepth a {@link psidev.psi.mi.jami.model.CurationDepth} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public MitabPublication(String title, String journal, Date publicationDate, CurationDepth curationDepth, Source source) {
        super(title, journal, publicationDate, curationDepth, source);
    }

    /**
     * <p>Constructor for MitabPublication.</p>
     *
     * @param title a {@link java.lang.String} object.
     * @param journal a {@link java.lang.String} object.
     * @param publicationDate a {@link java.util.Date} object.
     * @param imexId a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public MitabPublication(String title, String journal, Date publicationDate, String imexId, Source source) {
        super(title, journal, publicationDate, imexId, source);
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
        return (getSourceLocator() != null ? "Publication: "+getSourceLocator().toString():super.toString());
    }
}

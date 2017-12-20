package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.tab.utils.MitabUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * This class wraps a date and a fileSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/06/13</pre>
 */
public class MitabDate implements FileSourceContext{

    private FileSourceLocator sourceLocator;
    private Date date;

    /**
     * <p>Constructor for MitabDate.</p>
     *
     * @param date a {@link java.lang.String} object.
     * @throws java.text.ParseException if any.
     */
    public MitabDate(String date) throws ParseException {
        this.date = MitabUtils.DATE_FORMAT.parse(date);
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return sourceLocator;
    }

    /**
     * <p>Getter for the field <code>date</code>.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getDate() {
        return date;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Mitab date: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

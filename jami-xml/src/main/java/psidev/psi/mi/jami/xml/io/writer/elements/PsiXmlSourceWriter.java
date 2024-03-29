package psidev.psi.mi.jami.xml.io.writer.elements;

import psidev.psi.mi.jami.model.Source;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Interface for PSI-XML source writers
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/11/13</pre>
 */
public interface PsiXmlSourceWriter extends PsiXmlElementWriter<Source> {

    /**
     * <p>getDefaultReleaseDate.</p>
     *
     * @return the default release date. Can be null
     */
    public XMLGregorianCalendar getDefaultReleaseDate();

    /**
     * Sets the default release date
     *
     * @param date a {@link javax.xml.datatype.XMLGregorianCalendar} object.
     */
    public void setDefaultReleaseDate(XMLGregorianCalendar date);
}

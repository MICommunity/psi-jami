package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.model.Source;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Extended source for PSI-XML 2.5 standards
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public interface ExtendedPsiXmlSource extends Source{

    /**
     * Gets the value of the release property.
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRelease();

    /**
     * Sets the value of the release property.
     *
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setRelease(String value);

    /**
     * Gets the value of the releaseDate property.
     *
     * @return a {@link javax.xml.datatype.XMLGregorianCalendar} object.
     */
    public XMLGregorianCalendar getReleaseDate();

    /**
     * Sets the value of the releaseDate property.
     *
     * @param value
     *     allowed object is
     *     {@link javax.xml.datatype.XMLGregorianCalendar}
     */
    public void setReleaseDate(XMLGregorianCalendar value);
}

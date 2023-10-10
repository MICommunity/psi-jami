package psidev.psi.mi.jami.xml.io.writer.elements;

import psidev.psi.mi.jami.exception.MIIOException;

/**
 * Interface for writers of PSI-XML elements
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/11/13</pre>
 */
public interface PsiXmlElementWriter<T extends Object> {

    /**
     * <p>write.</p>
     *
     * @param object a T object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(T object) throws MIIOException;
}

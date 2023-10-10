package psidev.psi.mi.jami.xml.io.writer.elements;

import psidev.psi.mi.jami.exception.MIIOException;

/**
 * Interface for writers of PSI-XML object elements that do have a variable node name but otherwise are the same element type.
 *
 * It asks for the name of the cv node in addition to the cv
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/11/13</pre>
 */
public interface PsiXmlVariableNameWriter<T extends Object> {

    /**
     * <p>write.</p>
     *
     * @param object a T object.
     * @param nodeName a {@link java.lang.String} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(T object, String nodeName) throws MIIOException;
}

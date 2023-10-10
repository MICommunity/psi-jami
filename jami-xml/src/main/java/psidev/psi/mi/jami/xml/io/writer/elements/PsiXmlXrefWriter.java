package psidev.psi.mi.jami.xml.io.writer.elements;

import psidev.psi.mi.jami.model.Xref;

/**
 * Interface for PSI-XML Xref writers
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public interface PsiXmlXrefWriter extends PsiXmlVariableNameWriter<Xref> {

    /**
     * Sets the default refType that will be used when a Xref object does not have a qualifier
     *
     * @param defaultType a {@link java.lang.String} object.
     */
    public void setDefaultRefType(String defaultType);

    /**
     * Sets the default refTypeAc that will be used when a Xref object does not have a qualifier
     *
     * @param defaultTypeAc a {@link java.lang.String} object.
     */
    public void setDefaultRefTypeAc(String defaultTypeAc);
}

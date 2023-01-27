package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.Xref;

import java.util.List;

/**
 * PSI-XML 2.5 extension of an xref
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/10/13</pre>
 */
public interface ExtendedPsiXmlXref extends Xref {

    /**
     * <p>getSecondary.</p>
     *
     * @return a {@link String} object.
     */
    public String getSecondary();
    /**
     * <p>setSecondary.</p>
     *
     * @param secondary a {@link String} object.
     */
    public void setSecondary(String secondary);
    /**
     * <p>getAnnotations.</p>
     *
     * @return a {@link List} object.
     */
    public List<Annotation> getAnnotations();
}

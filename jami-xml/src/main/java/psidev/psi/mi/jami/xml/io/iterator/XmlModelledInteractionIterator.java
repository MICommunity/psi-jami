package psidev.psi.mi.jami.xml.io.iterator;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.xml.io.parser.PsiXmlParser;

/**
 * Xml modelled interaction iterator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/10/13</pre>
 */
public class XmlModelledInteractionIterator extends AbstractXmlIterator<ModelledInteraction> {
    /**
     * <p>Constructor for XmlModelledInteractionIterator.</p>
     *
     * @param lineParser a {@link psidev.psi.mi.jami.xml.io.parser.PsiXmlParser} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public XmlModelledInteractionIterator(PsiXmlParser<ModelledInteraction> lineParser) throws MIIOException {
        super(lineParser);
    }
}

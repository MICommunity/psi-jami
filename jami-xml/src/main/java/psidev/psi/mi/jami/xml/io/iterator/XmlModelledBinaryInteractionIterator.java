package psidev.psi.mi.jami.xml.io.iterator;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.xml.io.parser.PsiXmlParser;

/**
 * Xml modelled binary interaction iterator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/10/13</pre>
 */
public class XmlModelledBinaryInteractionIterator extends AbstractXmlIterator<ModelledBinaryInteraction> {
    /**
     * <p>Constructor for XmlModelledBinaryInteractionIterator.</p>
     *
     * @param lineParser a {@link psidev.psi.mi.jami.xml.io.parser.PsiXmlParser} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public XmlModelledBinaryInteractionIterator(PsiXmlParser<ModelledBinaryInteraction> lineParser) throws MIIOException {
        super(lineParser);
    }
}

package psidev.psi.mi.jami.xml.io.iterator;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.xml.io.parser.PsiXmlParser;

/**
 * Xml binary interaction evidence iterator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/10/13</pre>
 */
public class XmlBinaryInteractionEvidenceIterator extends AbstractXmlIterator<BinaryInteractionEvidence> {
    /**
     * <p>Constructor for XmlBinaryInteractionEvidenceIterator.</p>
     *
     * @param lineParser a {@link psidev.psi.mi.jami.xml.io.parser.PsiXmlParser} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public XmlBinaryInteractionEvidenceIterator(PsiXmlParser<BinaryInteractionEvidence> lineParser) throws MIIOException {
        super(lineParser);
    }
}

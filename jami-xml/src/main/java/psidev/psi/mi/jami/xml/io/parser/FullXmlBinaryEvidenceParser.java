package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.InteractionEvidenceSpokeExpansion;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.xml.model.AbstractEntry;
import psidev.psi.mi.jami.xml.model.AbstractEntrySet;
import psidev.psi.mi.jami.xml.exception.PsiXmlParserException;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;

/**
 * Full Parser generating basic binary interaction objects and ignoring all experimental details.
 *
 * It will load the all entrySet so is consuming a lot of memory in case of large files but is very performant for small files
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public class FullXmlBinaryEvidenceParser extends AbstractPsixmlBinaryParser<InteractionEvidence, BinaryInteractionEvidence> implements FullPsiXmlParser<InteractionEvidence> {
    /**
     * <p>Constructor for FullXmlBinaryEvidenceParser.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws javax.xml.bind.JAXBException if any.
     * @throws java.io.FileNotFoundException if any.
     */
    public FullXmlBinaryEvidenceParser(File file) throws JAXBException, FileNotFoundException {
        super(new FullXmlEvidenceParser(file));
    }

    /**
     * <p>Constructor for FullXmlBinaryEvidenceParser.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public FullXmlBinaryEvidenceParser(InputStream inputStream) throws JAXBException {
        super(new FullXmlEvidenceParser(inputStream));
    }

    /**
     * <p>Constructor for FullXmlBinaryEvidenceParser.</p>
     *
     * @param url a {@link java.net.URL} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public FullXmlBinaryEvidenceParser(URL url) throws IOException, JAXBException {
        super(new FullXmlEvidenceParser(url));
    }

    /**
     * <p>Constructor for FullXmlBinaryEvidenceParser.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public FullXmlBinaryEvidenceParser(Reader reader) throws JAXBException {
        super(new FullXmlEvidenceParser(reader));
    }

    /** {@inheritDoc} */
    @Override
    protected ComplexExpansionMethod<InteractionEvidence, BinaryInteractionEvidence> initialiseDefaultExpansionMethod() {
        return new InteractionEvidenceSpokeExpansion();
    }


    /** {@inheritDoc} */
    @Override
    public AbstractEntrySet<AbstractEntry<InteractionEvidence>> getEntrySet() throws PsiXmlParserException {
        return ((FullPsiXmlParser<InteractionEvidence>)getDelegateParser()).getEntrySet();
    }
}

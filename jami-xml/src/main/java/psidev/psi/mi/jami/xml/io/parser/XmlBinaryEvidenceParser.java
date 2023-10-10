package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.InteractionEvidenceSpokeExpansion;
import psidev.psi.mi.jami.model.InteractionEvidence;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.net.URL;

/**
 * Xml parser creating binary interaction evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
public class XmlBinaryEvidenceParser extends AbstractPsixmlBinaryParser<InteractionEvidence, BinaryInteractionEvidence> {
    /**
     * <p>Constructor for XmlBinaryEvidenceParser.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.FileNotFoundException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public XmlBinaryEvidenceParser(File file) throws FileNotFoundException, XMLStreamException, JAXBException {
        super(new XmlEvidenceParser(file));
    }

    /**
     * <p>Constructor for XmlBinaryEvidenceParser.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public XmlBinaryEvidenceParser(InputStream inputStream) throws XMLStreamException, JAXBException {
        super(new XmlEvidenceParser(inputStream));
    }

    /**
     * <p>Constructor for XmlBinaryEvidenceParser.</p>
     *
     * @param url a {@link java.net.URL} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public XmlBinaryEvidenceParser(URL url) throws IOException, XMLStreamException, JAXBException {
        super(new XmlEvidenceParser(url));
    }

    /**
     * <p>Constructor for XmlBinaryEvidenceParser.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public XmlBinaryEvidenceParser(Reader reader) throws XMLStreamException, JAXBException {
        super(new XmlEvidenceParser(reader));
    }

    /** {@inheritDoc} */
    @Override
    protected ComplexExpansionMethod<InteractionEvidence, BinaryInteractionEvidence> initialiseDefaultExpansionMethod() {
        return new InteractionEvidenceSpokeExpansion();
    }


}

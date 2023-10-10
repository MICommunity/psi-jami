package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.SpokeExpansion;
import psidev.psi.mi.jami.model.Interaction;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.net.URL;

/**
 * Xml parser creating binary interactions that can be a mix of interaction evidences and abstract interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
public class XmlBinaryParser extends AbstractPsixmlBinaryParser<Interaction,BinaryInteraction> {
    /**
     * <p>Constructor for XmlBinaryParser.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.FileNotFoundException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public XmlBinaryParser(File file) throws FileNotFoundException, XMLStreamException, JAXBException {
        super(new XmlParser(file));
    }

    /**
     * <p>Constructor for XmlBinaryParser.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public XmlBinaryParser(InputStream inputStream) throws XMLStreamException, JAXBException {
        super(new XmlParser(inputStream));
    }

    /**
     * <p>Constructor for XmlBinaryParser.</p>
     *
     * @param url a {@link java.net.URL} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public XmlBinaryParser(URL url) throws IOException, XMLStreamException, JAXBException {
        super(new XmlParser(url));
    }

    /**
     * <p>Constructor for XmlBinaryParser.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public XmlBinaryParser(Reader reader) throws XMLStreamException, JAXBException {
        super(new XmlParser(reader));
    }

    /** {@inheritDoc} */
    @Override
    protected ComplexExpansionMethod<Interaction, BinaryInteraction> initialiseDefaultExpansionMethod() {
        return new SpokeExpansion();
    }
}

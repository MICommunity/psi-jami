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
 * Xml 2.5 parser creating light binary interactions and ignoring experimental details
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
public class LightXmlBinaryParser extends AbstractPsixmlBinaryParser<Interaction,BinaryInteraction> {
    /**
     * <p>Constructor for LightXmlBinaryParser.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.FileNotFoundException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public LightXmlBinaryParser(File file) throws FileNotFoundException, XMLStreamException, JAXBException {
        super(new LightXmlParser(file));
    }

    /**
     * <p>Constructor for LightXmlBinaryParser.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public LightXmlBinaryParser(InputStream inputStream) throws XMLStreamException, JAXBException {
        super(new LightXmlParser(inputStream));
    }

    /**
     * <p>Constructor for LightXmlBinaryParser.</p>
     *
     * @param url a {@link java.net.URL} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public LightXmlBinaryParser(URL url) throws IOException, XMLStreamException, JAXBException {
        super(new LightXmlParser(url));
    }

    /**
     * <p>Constructor for LightXmlBinaryParser.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public LightXmlBinaryParser(Reader reader) throws XMLStreamException, JAXBException {
        super(new LightXmlParser(reader));
    }

    /** {@inheritDoc} */
    @Override
    protected ComplexExpansionMethod<Interaction, BinaryInteraction> initialiseDefaultExpansionMethod() {
        return new SpokeExpansion();
    }
}

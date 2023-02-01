package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.ModelledInteractionSpokeExpansion;
import psidev.psi.mi.jami.model.ModelledInteraction;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.net.URL;

/**
 * Xml parser creating modelled binary interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
public class XmlModelledBinaryParser extends AbstractPsixmlBinaryParser<ModelledInteraction, ModelledBinaryInteraction> {
    /**
     * <p>Constructor for XmlModelledBinaryParser.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.FileNotFoundException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public XmlModelledBinaryParser(File file) throws FileNotFoundException, XMLStreamException, JAXBException {
        super(new XmlModelledParser(file));
    }

    /**
     * <p>Constructor for XmlModelledBinaryParser.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public XmlModelledBinaryParser(InputStream inputStream) throws XMLStreamException, JAXBException {
        super(new XmlModelledParser(inputStream));
    }

    /**
     * <p>Constructor for XmlModelledBinaryParser.</p>
     *
     * @param url a {@link java.net.URL} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public XmlModelledBinaryParser(URL url) throws IOException, XMLStreamException, JAXBException {
        super(new XmlModelledParser(url));
    }

    /**
     * <p>Constructor for XmlModelledBinaryParser.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public XmlModelledBinaryParser(Reader reader) throws XMLStreamException, JAXBException {
        super(new XmlModelledParser(reader));
    }

    /** {@inheritDoc} */
    @Override
    protected ComplexExpansionMethod<ModelledInteraction,ModelledBinaryInteraction> initialiseDefaultExpansionMethod() {
        return new ModelledInteractionSpokeExpansion();
    }
}

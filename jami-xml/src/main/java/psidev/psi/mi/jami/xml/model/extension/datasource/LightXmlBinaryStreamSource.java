package psidev.psi.mi.jami.xml.model.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.io.iterator.XmlBinaryInteractionIterator;
import psidev.psi.mi.jami.xml.io.parser.LightXmlBinaryParser;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.net.URL;
import java.util.Iterator;

/**
 * Psi-XML binary interaction datasource
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/10/13</pre>
 */
public class LightXmlBinaryStreamSource extends AbstractPsiXmlBinaryStream<Interaction, BinaryInteraction> {

    /**
     * <p>Constructor for LightXmlBinaryStreamSource.</p>
     */
    public LightXmlBinaryStreamSource() {
    }

    /**
     * <p>Constructor for LightXmlBinaryStreamSource.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public LightXmlBinaryStreamSource(File file) {
        super(file);
    }

    /**
     * <p>Constructor for LightXmlBinaryStreamSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public LightXmlBinaryStreamSource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for LightXmlBinaryStreamSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public LightXmlBinaryStreamSource(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for LightXmlBinaryStreamSource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public LightXmlBinaryStreamSource(URL url) {
        super(url);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(Reader reader) {
        try {
            LightXmlBinaryParser parser = new LightXmlBinaryParser(reader);
            parser.setListener(this);
            parser.setCacheOfObjects(getElementCache());
            parser.setExpansionMethod(getComplexExpansion());
            setParser(parser);
        } catch (XMLStreamException e) {
            throw new MIIOException("Impossible to read with provided reader ",e);
        } catch (JAXBException e) {
            throw new MIIOException("Impossible to read with provided reader",e);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(File file) {
        try {
            LightXmlBinaryParser parser = new LightXmlBinaryParser(file);
            parser.setListener(this);
            parser.setCacheOfObjects(getElementCache());
            parser.setExpansionMethod(getComplexExpansion());
            setParser(parser);
        } catch (XMLStreamException e) {
            throw new MIIOException("Impossible to parse the file "+file.getName(),e);
        } catch (JAXBException e) {
            throw new MIIOException("Impossible to parse the file "+file.getName(),e);
        } catch (FileNotFoundException e) {
            throw new MIIOException("Impossible to parse the file "+file.getName(),e);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(InputStream input) {
        try {
            LightXmlBinaryParser parser = new LightXmlBinaryParser(input);
            parser.setListener(this);
            parser.setCacheOfObjects(getElementCache());
            parser.setExpansionMethod(getComplexExpansion());
            setParser(parser);
        } catch (XMLStreamException e) {
            throw new MIIOException("Impossible to parse the inputstream ",e);
        } catch (JAXBException e) {
            throw new MIIOException("Impossible to parse the inputstream ",e);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(URL url) {
        try {
            LightXmlBinaryParser parser = new LightXmlBinaryParser(url);
            parser.setListener(this);
            parser.setCacheOfObjects(getElementCache());
            parser.setExpansionMethod(getComplexExpansion());
            setParser(parser);
        } catch (IOException e) {
            throw new MIIOException("Impossible to read the url "+url.toExternalForm(),e);
        } catch (XMLStreamException e) {
            throw new MIIOException("Impossible to parse the url "+url.toExternalForm(),e);
        } catch (JAXBException e) {
            throw new MIIOException("Impossible to parse the url "+url.toExternalForm(),e);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected Iterator<BinaryInteraction> createXmlIterator() {
        return new XmlBinaryInteractionIterator(getParser());
    }
}

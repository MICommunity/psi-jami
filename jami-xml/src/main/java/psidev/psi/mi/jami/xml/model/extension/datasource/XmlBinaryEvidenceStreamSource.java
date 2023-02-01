package psidev.psi.mi.jami.xml.model.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.datasource.BinaryInteractionEvidenceStream;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.xml.io.iterator.XmlBinaryInteractionEvidenceIterator;
import psidev.psi.mi.jami.xml.io.parser.XmlBinaryEvidenceParser;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.net.URL;
import java.util.Iterator;

/**
 * PsiXml datasource for binaryInteractionEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/10/13</pre>
 */
public class XmlBinaryEvidenceStreamSource extends AbstractPsiXmlBinaryStream<InteractionEvidence, BinaryInteractionEvidence> implements BinaryInteractionEvidenceStream{

    /**
     * <p>Constructor for XmlBinaryEvidenceStreamSource.</p>
     */
    public XmlBinaryEvidenceStreamSource() {
    }

    /**
     * <p>Constructor for XmlBinaryEvidenceStreamSource.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public XmlBinaryEvidenceStreamSource(File file) {
        super(file);
    }

    /**
     * <p>Constructor for XmlBinaryEvidenceStreamSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public XmlBinaryEvidenceStreamSource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for XmlBinaryEvidenceStreamSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public XmlBinaryEvidenceStreamSource(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for XmlBinaryEvidenceStreamSource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public XmlBinaryEvidenceStreamSource(URL url) {
        super(url);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(Reader reader) {
        try {
            XmlBinaryEvidenceParser parser = new XmlBinaryEvidenceParser(reader);
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
            XmlBinaryEvidenceParser parser = new XmlBinaryEvidenceParser(file);
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
            XmlBinaryEvidenceParser parser = new XmlBinaryEvidenceParser(input);
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
            XmlBinaryEvidenceParser parser = new XmlBinaryEvidenceParser(url);
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
    protected Iterator<BinaryInteractionEvidence> createXmlIterator() {
        return new XmlBinaryInteractionEvidenceIterator(getParser());
    }
}

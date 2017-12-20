package psidev.psi.mi.jami.xml.model.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.datasource.BinaryInteractionSource;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.xml.io.parser.FullXmlBinaryParser;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;

/**
 * PSI-XML 2.5 data source that provides binary interactions (mixed of abstract interactions and interaction evidences).
 * It will load the full interaction dataset
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public class XmlBinarySource extends AbstractPsiXmlBinarySource<Interaction<?extends Participant>, BinaryInteraction> implements BinaryInteractionSource<BinaryInteraction>{

    /**
     * <p>Constructor for XmlBinarySource.</p>
     */
    public XmlBinarySource() {
    }

    /**
     * <p>Constructor for XmlBinarySource.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public XmlBinarySource(File file) {
        super(file);
    }

    /**
     * <p>Constructor for XmlBinarySource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public XmlBinarySource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for XmlBinarySource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public XmlBinarySource(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for XmlBinarySource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public XmlBinarySource(URL url) {
        super(url);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(Reader reader) {
        try {
            FullXmlBinaryParser parser = new FullXmlBinaryParser(reader);
            parser.setListener(this);
            parser.setCacheOfObjects(getElementCache());
            setParser(parser);
        } catch (JAXBException e) {
            throw new MIIOException("Impossible to read with provided reader",e);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(File file) {
        try {
            FullXmlBinaryParser parser = new FullXmlBinaryParser(file);
            parser.setListener(this);
            parser.setCacheOfObjects(getElementCache());
            setParser(parser);
        } catch (JAXBException e) {
            throw new MIIOException("Impossible to read with provided file",e);
        } catch (FileNotFoundException e) {
            throw new MIIOException("Impossible to read with provided file",e);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(InputStream input) {
        try {
            FullXmlBinaryParser parser = new FullXmlBinaryParser(input);
            parser.setListener(this);
            parser.setCacheOfObjects(getElementCache());
            setParser(parser);
        } catch (JAXBException e) {
            throw new MIIOException("Impossible to read with provided Input stream",e);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(URL url) {
        try {
            FullXmlBinaryParser parser = new FullXmlBinaryParser(url);
            parser.setListener(this);
            parser.setCacheOfObjects(getElementCache());
            setParser(parser);
        } catch (JAXBException e) {
            throw new MIIOException("Impossible to read with provided URL",e);
        }
        catch (IOException e) {
            throw new MIIOException("Impossible to read the url "+url.toExternalForm(),e);
        }
    }
}

package psidev.psi.mi.jami.xml.model.extension.datasource;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.datasource.ModelledBinaryInteractionSource;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.xml.io.parser.FullXmlModelledBinaryParser;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;

/**
 * PSI-XML data source that provides modelled binary interactions (ignore experimental details).
 * It will load the full interaction dataset
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/11/13</pre>
 */
public class XmlModelledBinarySource extends AbstractPsiXmlBinarySource<ModelledInteraction, ModelledBinaryInteraction> implements ModelledBinaryInteractionSource {

    /**
     * <p>Constructor for XmlModelledBinarySource.</p>
     */
    public XmlModelledBinarySource() {
    }

    /**
     * <p>Constructor for XmlModelledBinarySource.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public XmlModelledBinarySource(File file) {
        super(file);
    }

    /**
     * <p>Constructor for XmlModelledBinarySource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public XmlModelledBinarySource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for XmlModelledBinarySource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public XmlModelledBinarySource(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for XmlModelledBinarySource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public XmlModelledBinarySource(URL url) {
        super(url);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(Reader reader) {
        try {
            FullXmlModelledBinaryParser parser = new FullXmlModelledBinaryParser(reader);
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
            FullXmlModelledBinaryParser parser = new FullXmlModelledBinaryParser(file);
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
            FullXmlModelledBinaryParser parser = new FullXmlModelledBinaryParser(input);
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
            FullXmlModelledBinaryParser parser = new FullXmlModelledBinaryParser(url);
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

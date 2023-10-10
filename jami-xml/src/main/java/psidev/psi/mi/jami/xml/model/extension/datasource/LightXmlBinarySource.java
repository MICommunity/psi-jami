package psidev.psi.mi.jami.xml.model.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.datasource.BinaryInteractionSource;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.io.parser.LightFullXmlBinaryParser;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;

/**
 * PSI-XML data source that provides light binary interactions (no experimental details).
 * It will load the full interaction dataset
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public class LightXmlBinarySource extends AbstractPsiXmlBinarySource<Interaction, BinaryInteraction> implements BinaryInteractionSource<BinaryInteraction>{

    /**
     * <p>Constructor for LightXmlBinarySource.</p>
     */
    public LightXmlBinarySource() {
    }

    /**
     * <p>Constructor for LightXmlBinarySource.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public LightXmlBinarySource(File file) {
        super(file);
    }

    /**
     * <p>Constructor for LightXmlBinarySource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public LightXmlBinarySource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for LightXmlBinarySource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public LightXmlBinarySource(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for LightXmlBinarySource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public LightXmlBinarySource(URL url) {
        super(url);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(Reader reader) {
        try {
            LightFullXmlBinaryParser parser = new LightFullXmlBinaryParser(reader);
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
            LightFullXmlBinaryParser parser = new LightFullXmlBinaryParser(file);
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
            LightFullXmlBinaryParser parser = new LightFullXmlBinaryParser(input);
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
            LightFullXmlBinaryParser parser = new LightFullXmlBinaryParser(url);
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

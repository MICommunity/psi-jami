package psidev.psi.mi.jami.xml.model.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.xml.io.parser.FullXmlModelledParser;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;

/**
 * PSI-XML data source that provides abstract interactions (ignore full experimental details).
 * It will load the full interaction dataset
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/11/13</pre>
 */
public class XmlModelledSource extends AbstractPsiXmlSource<ModelledInteraction> {

    /**
     * <p>Constructor for XmlModelledSource.</p>
     */
    public XmlModelledSource() {
    }

    /**
     * <p>Constructor for XmlModelledSource.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public XmlModelledSource(File file) {
        super(file);
    }

    /**
     * <p>Constructor for XmlModelledSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public XmlModelledSource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for XmlModelledSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public XmlModelledSource(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for XmlModelledSource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public XmlModelledSource(URL url) {
        super(url);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(Reader reader) {
        try {
            FullXmlModelledParser parser = new FullXmlModelledParser(reader);
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
            FullXmlModelledParser parser = new FullXmlModelledParser(file);
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
            FullXmlModelledParser parser = new FullXmlModelledParser(input);
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
            FullXmlModelledParser parser = new FullXmlModelledParser(url);
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

    /** {@inheritDoc} */
    @Override
    protected void initialiseExpansionMethod(ComplexExpansionMethod<? extends Interaction, ? extends BinaryInteraction> expansionMethod) {
        //do nothing
    }
}

package psidev.psi.mi.jami.xml.model.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.datasource.BinaryInteractionEvidenceSource;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.xml.io.parser.FullXmlBinaryEvidenceParser;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;

/**
 * PSI-XML data source that provides binary interactions evidence (full experimental details).
 * It will load the full interaction dataset
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public class XmlBinaryEvidenceSource extends AbstractPsiXmlBinarySource<InteractionEvidence, BinaryInteractionEvidence> implements BinaryInteractionEvidenceSource{

    /**
     * <p>Constructor for XmlBinaryEvidenceSource.</p>
     */
    public XmlBinaryEvidenceSource() {
    }

    /**
     * <p>Constructor for XmlBinaryEvidenceSource.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public XmlBinaryEvidenceSource(File file) {
        super(file);
    }

    /**
     * <p>Constructor for XmlBinaryEvidenceSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public XmlBinaryEvidenceSource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for XmlBinaryEvidenceSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public XmlBinaryEvidenceSource(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for XmlBinaryEvidenceSource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public XmlBinaryEvidenceSource(URL url) {
        super(url);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(Reader reader) {
        try {
            FullXmlBinaryEvidenceParser parser = new FullXmlBinaryEvidenceParser(reader);
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
            FullXmlBinaryEvidenceParser parser = new FullXmlBinaryEvidenceParser(file);
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
            FullXmlBinaryEvidenceParser parser = new FullXmlBinaryEvidenceParser(input);
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
            FullXmlBinaryEvidenceParser parser = new FullXmlBinaryEvidenceParser(url);
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

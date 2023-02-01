package psidev.psi.mi.jami.xml.model.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.xml.io.parser.FullXmlEvidenceParser;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;

/**
 * PSI-XML data source that provides interaction evidences (with full experimental details).
 * It will load the full interaction dataset
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public class XmlEvidenceSource extends AbstractPsiXmlSource<InteractionEvidence> {

    /**
     * <p>Constructor for XmlEvidenceSource.</p>
     */
    public XmlEvidenceSource() {
    }

    /**
     * <p>Constructor for XmlEvidenceSource.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public XmlEvidenceSource(File file) {
        super(file);
    }

    /**
     * <p>Constructor for XmlEvidenceSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public XmlEvidenceSource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for XmlEvidenceSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public XmlEvidenceSource(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for XmlEvidenceSource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public XmlEvidenceSource(URL url) {
        super(url);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(Reader reader) {
        try {
            FullXmlEvidenceParser parser = new FullXmlEvidenceParser(reader);
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
            FullXmlEvidenceParser parser = new FullXmlEvidenceParser(file);
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
            FullXmlEvidenceParser parser = new FullXmlEvidenceParser(input);
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
            FullXmlEvidenceParser parser = new FullXmlEvidenceParser(url);
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

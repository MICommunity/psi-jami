package psidev.psi.mi.jami.xml.model.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.io.iterator.XmlInteractionIterator;
import psidev.psi.mi.jami.xml.io.parser.XmlParser;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Iterator;

/**
 * Datasource for PSI-xml 2.5 returning mix of abstract interactions and interaction evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/10/13</pre>
 */
public class XmlStreamSource extends AbstractPsiXmlStream<Interaction> {

    /**
     * <p>Constructor for XmlStreamSource.</p>
     */
    public XmlStreamSource() {
    }

    /**
     * <p>Constructor for XmlStreamSource.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public XmlStreamSource(File file) {
        super(file);
    }

    /**
     * <p>Constructor for XmlStreamSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public XmlStreamSource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for XmlStreamSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public XmlStreamSource(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for XmlStreamSource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public XmlStreamSource(URL url) {
        super(url);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(Reader reader) {
        XmlParser parser = new XmlParser(reader);
        parser.setListener(this);
        parser.setCacheOfObjects(getElementCache());
        setParser(parser);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(File file) {
        XmlParser parser = new XmlParser(file);
        parser.setListener(this);
        parser.setCacheOfObjects(getElementCache());
        setParser(parser);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(InputStream input) {
        XmlParser parser = new XmlParser(input);
        parser.setListener(this);
        parser.setCacheOfObjects(getElementCache());
        setParser(parser);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(URL url) {
        XmlParser parser = new XmlParser(url);
        parser.setListener(this);
        parser.setCacheOfObjects(getElementCache());
        setParser(parser);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExpansionMethod(ComplexExpansionMethod<? extends Interaction, ? extends BinaryInteraction> expansionMethod) {
        // nothing to do as we don't expand
    }

    /** {@inheritDoc} */
    @Override
    protected Iterator<Interaction> createXmlIterator() {
        return new XmlInteractionIterator(getParser());
    }
}

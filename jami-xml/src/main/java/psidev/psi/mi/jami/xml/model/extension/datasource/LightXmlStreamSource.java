package psidev.psi.mi.jami.xml.model.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.io.iterator.XmlInteractionIterator;
import psidev.psi.mi.jami.xml.io.parser.LightXmlParser;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Iterator;

/**
 * Datasource for PSI-xml returning basic interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/10/13</pre>
 */
public class LightXmlStreamSource extends AbstractPsiXmlStream<Interaction> {

    /**
     * <p>Constructor for LightXmlStreamSource.</p>
     */
    public LightXmlStreamSource() {
    }

    /**
     * <p>Constructor for LightXmlStreamSource.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public LightXmlStreamSource(File file) {
        super(file);
    }

    /**
     * <p>Constructor for LightXmlStreamSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public LightXmlStreamSource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for LightXmlStreamSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public LightXmlStreamSource(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for LightXmlStreamSource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public LightXmlStreamSource(URL url) {
        super(url);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(Reader reader) {
        LightXmlParser parser = new LightXmlParser(reader);
        parser.setListener(this);
        parser.setCacheOfObjects(getElementCache());
        setParser(parser);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(File file) {
        LightXmlParser parser = new LightXmlParser(file);
        parser.setListener(this);
        parser.setCacheOfObjects(getElementCache());
        setParser(parser);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(InputStream input) {
        LightXmlParser parser = new LightXmlParser(input);
        parser.setListener(this);
        parser.setCacheOfObjects(getElementCache());
        setParser(parser);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXmlParser(URL url) {
        LightXmlParser parser = new LightXmlParser(url);
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

package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionCategory;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URL;

/**
 * Full Parser generating basic interaction objects and ignore experimental details.
 *
 * It will load the all entrySet so is consuming a lot of memory in case of large files but is very performant for small files
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public class LightFullXmlParser extends AbstractFullPsiXmlParser<Interaction> {
    /**
     * <p>Constructor for LightFullXmlParser.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws javax.xml.bind.JAXBException if any.
     * @throws java.io.FileNotFoundException if any.
     */
    public LightFullXmlParser(File file) throws JAXBException, FileNotFoundException {
        super(file);
    }

    /**
     * <p>Constructor for LightFullXmlParser.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public LightFullXmlParser(InputStream inputStream) throws JAXBException {
        super(inputStream);
    }

    /**
     * <p>Constructor for LightFullXmlParser.</p>
     *
     * @param url a {@link java.net.URL} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public LightFullXmlParser(URL url) throws IOException, JAXBException {
        super(url);
    }

    /**
     * <p>Constructor for LightFullXmlParser.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public LightFullXmlParser(Reader reader) throws JAXBException {
        super(reader);
    }

    /** {@inheritDoc} */
    @Override
    protected Unmarshaller createJAXBUnmarshaller() throws JAXBException {
        return JaxbUnmarshallerFactory.getInstance().createFullUnmarshaller(getVersion(), InteractionCategory.basic);
    }

}

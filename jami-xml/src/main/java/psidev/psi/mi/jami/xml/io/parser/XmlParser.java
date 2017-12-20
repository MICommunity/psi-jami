package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionCategory;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

/**
 * Parser generating interaction objects that can be interaction evidences or abstract interactions.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
public class XmlParser extends AbstractPsiXmlParser<Interaction> {
    /**
     * <p>Constructor for XmlParser.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public XmlParser(File file){
        super(file);
    }

    /**
     * <p>Constructor for XmlParser.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     */
    public XmlParser(InputStream inputStream){
        super(inputStream);
    }

    /**
     * <p>Constructor for XmlParser.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public XmlParser(URL url){
        super(url);
    }

    /**
     * <p>Constructor for XmlParser.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public XmlParser(Reader reader){
        super(reader);
    }

    /** {@inheritDoc} */
    @Override
    protected Unmarshaller createJAXBUnmarshaller() throws JAXBException {
        return JaxbUnmarshallerFactory.getInstance().createUnmarshaller(getVersion(), InteractionCategory.mixed);
    }
}

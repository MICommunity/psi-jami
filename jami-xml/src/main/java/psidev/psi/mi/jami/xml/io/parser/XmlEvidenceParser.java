package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.model.InteractionCategory;
import psidev.psi.mi.jami.model.InteractionEvidence;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

/**
 * Parser generating interaction evidence objects with all experimental details
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
public class XmlEvidenceParser extends AbstractPsiXmlParser<InteractionEvidence> {

    /**
     * <p>Constructor for XmlEvidenceParser.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public XmlEvidenceParser(File file) {
        super(file);
    }

    /**
     * <p>Constructor for XmlEvidenceParser.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     */
    public XmlEvidenceParser(InputStream inputStream) {
        super(inputStream);
    }

    /**
     * <p>Constructor for XmlEvidenceParser.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public XmlEvidenceParser(URL url) {
        super(url);
    }

    /**
     * <p>Constructor for XmlEvidenceParser.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public XmlEvidenceParser(Reader reader) {
        super(reader);
    }

    /** {@inheritDoc} */
    @Override
    protected Unmarshaller createJAXBUnmarshaller() throws JAXBException {
        return JaxbUnmarshallerFactory.getInstance().createUnmarshaller(getVersion(), InteractionCategory.evidence);
    }
}

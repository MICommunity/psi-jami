package psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253;

import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.expanded.extended.AbstractLightExpandedXmlBinaryWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Expanded PSI-XML writer for light binary interactions (no experimental evidences)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class LightExpandedXmlBinaryWriter extends AbstractLightExpandedXmlBinaryWriter {

    /**
     * <p>Constructor for LightExpandedXmlBinaryWriter.</p>
     */
    public LightExpandedXmlBinaryWriter() {
        super();
    }

    /**
     * <p>Constructor for LightExpandedXmlBinaryWriter.</p>
     *
     * @param file a {@link File} object.
     * @throws IOException if any.
     * @throws XMLStreamException if any.
     */
    public LightExpandedXmlBinaryWriter(File file) throws IOException, XMLStreamException {
        super(file);
    }

    /**
     * <p>Constructor for LightExpandedXmlBinaryWriter.</p>
     *
     * @param output a {@link OutputStream} object.
     * @throws XMLStreamException if any.
     */
    public LightExpandedXmlBinaryWriter(OutputStream output) throws XMLStreamException {
        super(output);
    }

    /**
     * <p>Constructor for LightExpandedXmlBinaryWriter.</p>
     *
     * @param writer a {@link Writer} object.
     * @throws XMLStreamException if any.
     */
    public LightExpandedXmlBinaryWriter(Writer writer) throws XMLStreamException {
        super(writer);
    }

    /**
     * <p>Constructor for LightExpandedXmlBinaryWriter.</p>
     *
     * @param streamWriter a {@link XMLStreamWriter} object.
     * @param cache a {@link PsiXmlObjectCache} object.
     */
    public LightExpandedXmlBinaryWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(streamWriter, cache);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultSource() {
        setDefaultSource(newXmlSource("Unknown source"));
    }
}

package psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253;

import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.expanded.extended.AbstractLightExpandedXmlWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Expanded PSI-XML writer for light interactions (no experimental evidences)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class LightExpandedXmlWriter extends AbstractLightExpandedXmlWriter {

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     */
    public LightExpandedXmlWriter() {
        super();
    }

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     *
     * @param file a {@link File} object.
     * @throws IOException if any.
     * @throws XMLStreamException if any.
     */
    public LightExpandedXmlWriter(File file) throws IOException, XMLStreamException {
        super(file);
    }

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     *
     * @param output a {@link OutputStream} object.
     * @throws XMLStreamException if any.
     */
    public LightExpandedXmlWriter(OutputStream output) throws XMLStreamException {
        super(output);
    }

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     *
     * @param writer a {@link Writer} object.
     * @throws XMLStreamException if any.
     */
    public LightExpandedXmlWriter(Writer writer) throws XMLStreamException {
        super(writer);
    }

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     *
     * @param streamWriter a {@link XMLStreamWriter} object.
     * @param cache a {@link PsiXmlObjectCache} object.
     */
    public LightExpandedXmlWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(streamWriter, cache);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultSource() {
        setDefaultSource(newXmlSource("Unknown source"));
    }
}

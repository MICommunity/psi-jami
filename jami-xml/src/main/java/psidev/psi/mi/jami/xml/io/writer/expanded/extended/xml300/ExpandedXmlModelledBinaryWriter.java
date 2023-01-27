package psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml300;

import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.expanded.extended.AbstractExpandedXmlModelledBinaryWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public class ExpandedXmlModelledBinaryWriter extends AbstractExpandedXmlModelledBinaryWriter {

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     */
    public ExpandedXmlModelledBinaryWriter() {
        super();
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param file a {@link File} object.
     * @throws IOException if any.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlModelledBinaryWriter(File file) throws IOException, XMLStreamException {
        super(file);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param output a {@link OutputStream} object.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlModelledBinaryWriter(OutputStream output) throws XMLStreamException {
        super(output);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param writer a {@link Writer} object.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlModelledBinaryWriter(Writer writer) throws XMLStreamException {
        super(writer);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param streamWriter a {@link XMLStreamWriter} object.
     * @param cache a {@link PsiXmlObjectCache} object.
     */
    public ExpandedXmlModelledBinaryWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(streamWriter, cache);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultSource() {
        setDefaultSource(newXmlSource("Unknown source"));
    }
}

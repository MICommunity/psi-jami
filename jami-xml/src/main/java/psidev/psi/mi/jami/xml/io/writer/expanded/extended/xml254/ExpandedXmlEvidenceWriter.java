package psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml254;

import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.expanded.extended.AbstractExpandedXmlEvidenceWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public class ExpandedXmlEvidenceWriter extends AbstractExpandedXmlEvidenceWriter {

    /**
     * <p>Constructor for ExpandedXmlEvidenceWriter.</p>
     */
    public ExpandedXmlEvidenceWriter() {
        super();
    }

    /**
     * <p>Constructor for ExpandedXmlEvidenceWriter.</p>
     *
     * @param file a {@link File} object.
     * @throws IOException if any.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlEvidenceWriter(File file) throws IOException, XMLStreamException {
        super(file);
    }

    /**
     * <p>Constructor for ExpandedXmlEvidenceWriter.</p>
     *
     * @param output a {@link OutputStream} object.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlEvidenceWriter(OutputStream output) throws XMLStreamException {
        super(output);
    }

    /**
     * <p>Constructor for ExpandedXmlEvidenceWriter.</p>
     *
     * @param writer a {@link Writer} object.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlEvidenceWriter(Writer writer) throws XMLStreamException {
        super(writer);
    }

    /**
     * <p>Constructor for ExpandedXmlEvidenceWriter.</p>
     *
     * @param streamWriter a {@link XMLStreamWriter} object.
     * @param cache a {@link PsiXmlObjectCache} object.
     */
    public ExpandedXmlEvidenceWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(streamWriter, cache);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultSource() {
        setDefaultSource(newXmlSource("Unknown source"));
    }
}

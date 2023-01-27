package psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml300;

import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.expanded.extended.AbstractExpandedXmlBinaryEvidenceWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public class ExpandedXmlBinaryEvidenceWriter extends AbstractExpandedXmlBinaryEvidenceWriter {

    /**
     * <p>Constructor for ExpandedXmlBinaryEvidenceWriter.</p>
     */
    public ExpandedXmlBinaryEvidenceWriter() {
        super();
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryEvidenceWriter.</p>
     *
     * @param file a {@link File} object.
     * @throws IOException if any.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlBinaryEvidenceWriter(File file) throws IOException, XMLStreamException {
        super(file);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryEvidenceWriter.</p>
     *
     * @param output a {@link OutputStream} object.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlBinaryEvidenceWriter(OutputStream output) throws XMLStreamException {
        super(output);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryEvidenceWriter.</p>
     *
     * @param writer a {@link Writer} object.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlBinaryEvidenceWriter(Writer writer) throws XMLStreamException {
        super(writer);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryEvidenceWriter.</p>
     *
     * @param streamWriter a {@link XMLStreamWriter} object.
     * @param elementCache a {@link PsiXmlObjectCache} object.
     */
    public ExpandedXmlBinaryEvidenceWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache elementCache) {
        super(streamWriter, elementCache);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultSource() {
        setDefaultSource(newXmlSource("Unknown source"));
    }
}

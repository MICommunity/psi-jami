package psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253;

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
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlBinaryEvidenceWriter(File file) throws IOException, XMLStreamException {
        super(file);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryEvidenceWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlBinaryEvidenceWriter(OutputStream output) throws XMLStreamException {
        super(output);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlBinaryEvidenceWriter(Writer writer) throws XMLStreamException {
        super(writer);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryEvidenceWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param elementCache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
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

package psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253;

import psidev.psi.mi.jami.xml.io.writer.expanded.extended.AbstractExpandedXmlBinaryWriter;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public class ExpandedXmlBinaryWriter extends AbstractExpandedXmlBinaryWriter {

    /**
     * <p>Constructor for ExpandedXmlBinaryWriter.</p>
     */
    public ExpandedXmlBinaryWriter() {
        super();
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlBinaryWriter(File file) throws IOException, XMLStreamException {
        super(file);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlBinaryWriter(OutputStream output) throws XMLStreamException {
        super(output);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlBinaryWriter(Writer writer) throws XMLStreamException {
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDelegateWriters() {
        setModelledWriter(new ExpandedXmlModelledBinaryWriter(getStreamWriter(), getElementCache()));
        setEvidenceWriter(new ExpandedXmlBinaryEvidenceWriter(getStreamWriter(), getElementCache()));
        setLightWriter(new LightExpandedXmlBinaryWriter(getStreamWriter(), getElementCache()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultSource() {
        setDefaultSource(newXmlSource("Unknown source"));
    }
}

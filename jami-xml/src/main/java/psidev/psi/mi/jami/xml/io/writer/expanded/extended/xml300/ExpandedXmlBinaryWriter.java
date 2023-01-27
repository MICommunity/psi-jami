package psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml300;

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
     * @param file a {@link File} object.
     * @throws IOException if any.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlBinaryWriter(File file) throws IOException, XMLStreamException {
        super(file);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryWriter.</p>
     *
     * @param output a {@link OutputStream} object.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlBinaryWriter(OutputStream output) throws XMLStreamException {
        super(output);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryWriter.</p>
     *
     * @param writer a {@link Writer} object.
     * @throws XMLStreamException if any.
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

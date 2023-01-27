package psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253;

import psidev.psi.mi.jami.xml.io.writer.expanded.extended.AbstractExpandedXmlWriter;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Expanded PSI-XML writer for a mix of interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class ExpandedXmlWriter extends AbstractExpandedXmlWriter {

    /**
     * <p>Constructor for ExpandedXmlWriter.</p>
     */
    public ExpandedXmlWriter() {
        super();
    }

    /**
     * <p>Constructor for ExpandedXmlWriter.</p>
     *
     * @param file a {@link File} object.
     * @throws IOException if any.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlWriter(File file) throws IOException, XMLStreamException {
        super(file);
    }

    /**
     * <p>Constructor for ExpandedXmlWriter.</p>
     *
     * @param output a {@link OutputStream} object.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlWriter(OutputStream output) throws XMLStreamException {
        super(output);
    }

    /**
     * <p>Constructor for ExpandedXmlWriter.</p>
     *
     * @param writer a {@link Writer} object.
     * @throws XMLStreamException if any.
     */
    public ExpandedXmlWriter(Writer writer) throws XMLStreamException {
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDelegateWriters() {
        setModelledWriter(new ExpandedXmlModelledWriter(getStreamWriter(), getElementCache()));
        setEvidenceWriter(new ExpandedXmlEvidenceWriter(getStreamWriter(), getElementCache()));
        setLightWriter(new LightExpandedXmlWriter(getStreamWriter(), getElementCache()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultSource() {
        setDefaultSource(newXmlSource("Unknown source"));
    }
}

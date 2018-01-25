package psidev.psi.mi.jami.xml.io.writer.expanded;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Expanded PSI-XML writer for a mix of named binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class ExpandedXmlNamedBinaryWriter extends AbstractExpandedXmlMixWriter<BinaryInteraction, ModelledBinaryInteraction, BinaryInteractionEvidence> {

    /**
     * <p>Constructor for ExpandedXmlNamedBinaryWriter.</p>
     */
    public ExpandedXmlNamedBinaryWriter() {
        super(BinaryInteraction.class);
    }

    /**
     * <p>Constructor for ExpandedXmlNamedBinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlNamedBinaryWriter(File file) throws IOException, XMLStreamException {
        super(BinaryInteraction.class, file);
    }

    /**
     * <p>Constructor for ExpandedXmlNamedBinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlNamedBinaryWriter(OutputStream output) throws XMLStreamException {
        super(BinaryInteraction.class, output);
    }

    /**
     * <p>Constructor for ExpandedXmlNamedBinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlNamedBinaryWriter(Writer writer) throws XMLStreamException {
        super(BinaryInteraction.class, writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDelegateWriters() {
        setModelledWriter(new ExpandedXmlNamedModelledBinaryWriter(getStreamWriter(), getElementCache()));
        setEvidenceWriter(new ExpandedXmlNamedBinaryEvidenceWriter(getStreamWriter(), getElementCache()));
        setLightWriter(new LightExpandedXmlNamedBinaryWriter(getStreamWriter(), getElementCache()));
    }
}

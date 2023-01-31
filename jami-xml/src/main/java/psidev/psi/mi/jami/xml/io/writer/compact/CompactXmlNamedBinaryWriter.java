package psidev.psi.mi.jami.xml.io.writer.compact;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.xml.PsiXmlVersion;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Compact PSI-XML writer for a mix of named binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class CompactXmlNamedBinaryWriter extends AbstractCompactXmlMixWriter<BinaryInteraction, ModelledBinaryInteraction, BinaryInteractionEvidence> {

    /**
     * <p>Constructor for CompactXmlNamedBinaryWriter.</p>
     */
    public CompactXmlNamedBinaryWriter(PsiXmlVersion version) {
        super(version, BinaryInteraction.class);
    }

    /**
     * <p>Constructor for CompactXmlNamedBinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlNamedBinaryWriter(PsiXmlVersion version, File file) throws IOException, XMLStreamException {
        super(version, BinaryInteraction.class, file);
    }

    /**
     * <p>Constructor for CompactXmlNamedBinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlNamedBinaryWriter(PsiXmlVersion version, OutputStream output) throws XMLStreamException {
        super(version, BinaryInteraction.class, output);
    }

    /**
     * <p>Constructor for CompactXmlNamedBinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlNamedBinaryWriter(PsiXmlVersion version, Writer writer) throws XMLStreamException {
        super(version, BinaryInteraction.class, writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDelegateWriters() {
        setModelledWriter(new CompactXmlNamedModelledBinaryWriter(getVersion(), getStreamWriter(), getElementCache()));
        setEvidenceWriter(new CompactXmlNamedBinaryEvidenceWriter(getVersion(), getStreamWriter(), getElementCache()));
        setLightWriter(new LightCompactXmlNamedBinaryWriter(getVersion(), getStreamWriter(), getElementCache()));
    }
}

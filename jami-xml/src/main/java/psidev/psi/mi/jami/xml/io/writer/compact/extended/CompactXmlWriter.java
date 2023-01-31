package psidev.psi.mi.jami.xml.io.writer.compact.extended;

import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.io.writer.compact.AbstractCompactXmlMixWriter;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Compact PSI-XML writer for a mix of interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class CompactXmlWriter extends AbstractCompactXmlMixWriter<Interaction, ModelledInteraction, InteractionEvidence> {

    /**
     * <p>Constructor for CompactXmlWriter.</p>
     */
    public CompactXmlWriter(PsiXmlVersion version) {
        super(version, Interaction.class);
    }

    /**
     * <p>Constructor for CompactXmlWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlWriter(PsiXmlVersion version, File file) throws IOException, XMLStreamException {
        super(version, Interaction.class, file);
    }

    /**
     * <p>Constructor for CompactXmlWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlWriter(PsiXmlVersion version, OutputStream output) throws XMLStreamException {
        super(version, Interaction.class, output);
    }

    /**
     * <p>Constructor for CompactXmlWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlWriter(PsiXmlVersion version, Writer writer) throws XMLStreamException {
        super(version, Interaction.class, writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDelegateWriters() {
        setModelledWriter(new CompactXmlModelledWriter(getVersion(), getStreamWriter(), getElementCache()));
        setEvidenceWriter(new CompactXmlEvidenceWriter(getVersion(), getStreamWriter(), getElementCache()));
        setLightWriter(new LightCompactXmlWriter(getVersion(), getStreamWriter(), getElementCache()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultSource() {
        setDefaultSource(newXmlSource("Unknown source"));
    }
}

package psidev.psi.mi.jami.xml.io.writer.expanded;

import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.xml.PsiXmlVersion;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Expanded PSI-XML writer for a mix of named interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class ExpandedXmlNamedWriter extends AbstractExpandedXmlMixWriter<Interaction, ModelledInteraction, InteractionEvidence> {

    /**
     * <p>Constructor for ExpandedXmlNamedWriter.</p>
     */
    public ExpandedXmlNamedWriter(PsiXmlVersion version) {
        super(version, Interaction.class);
    }

    /**
     * <p>Constructor for ExpandedXmlNamedWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlNamedWriter(File file) throws IOException, XMLStreamException {
        super(Interaction.class, file);
    }

    /**
     * <p>Constructor for ExpandedXmlNamedWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlNamedWriter(OutputStream output) throws XMLStreamException {
        super(Interaction.class, output);
    }

    /**
     * <p>Constructor for ExpandedXmlNamedWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlNamedWriter(Writer writer) throws XMLStreamException {
        super(Interaction.class, writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDelegateWriters() {
        setModelledWriter(new ExpandedXmlNamedModelledWriter(getStreamWriter(), getElementCache()));
        setEvidenceWriter(new ExpandedXmlNamedEvidenceWriter(getStreamWriter(), getElementCache()));
        setLightWriter(new LightExpandedXmlNamedWriter(getStreamWriter(), getElementCache()));
    }
}

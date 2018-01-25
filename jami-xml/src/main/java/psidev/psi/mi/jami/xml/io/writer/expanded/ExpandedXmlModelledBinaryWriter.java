package psidev.psi.mi.jami.xml.io.writer.expanded;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.ComplexType;
import psidev.psi.mi.jami.model.InteractionCategory;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.xml.PsiXmlType;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Expanded PSI-XML writer for modelled binary interactions (no experimental evidences)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class ExpandedXmlModelledBinaryWriter extends AbstractExpandedXmlWriter<ModelledBinaryInteraction> {

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     */
    public ExpandedXmlModelledBinaryWriter() {
        super(ModelledBinaryInteraction.class);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlModelledBinaryWriter(File file) throws IOException, XMLStreamException {
        super(ModelledBinaryInteraction.class, file);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlModelledBinaryWriter(OutputStream output) throws XMLStreamException {
        super(ModelledBinaryInteraction.class, output);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlModelledBinaryWriter(Writer writer) throws XMLStreamException {
        super(ModelledBinaryInteraction.class, writer);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param cache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public ExpandedXmlModelledBinaryWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(ModelledBinaryInteraction.class, streamWriter, cache);
    }

    /** {@inheritDoc} */
    @Override
    protected Source extractSourceFromInteraction() {
        return getCurrentInteraction().getSource() != null ? getCurrentInteraction().getSource() : super.extractSourceFromInteraction();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWriters() {
        super.initialiseSubWriters(false, false, PsiXmlType.expanded, InteractionCategory.modelled, ComplexType.binary);
        // initialise same default experiment
        getComplexWriter().setDefaultExperiment(getInteractionWriter().getDefaultExperiment());
    }
}

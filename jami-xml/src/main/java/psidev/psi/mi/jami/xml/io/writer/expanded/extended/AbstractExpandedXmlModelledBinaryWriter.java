package psidev.psi.mi.jami.xml.io.writer.expanded.extended;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.ComplexType;
import psidev.psi.mi.jami.model.InteractionCategory;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.xml.PsiXmlType;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExtendedInteractionWriter;
import psidev.psi.mi.jami.xml.io.writer.expanded.AbstractExpandedXmlWriter;

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
public abstract class AbstractExpandedXmlModelledBinaryWriter extends AbstractExpandedXmlWriter<ModelledBinaryInteraction> {

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     */
    public AbstractExpandedXmlModelledBinaryWriter() {
        super(ModelledBinaryInteraction.class);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractExpandedXmlModelledBinaryWriter(File file) throws IOException, XMLStreamException {
        super(ModelledBinaryInteraction.class, file);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractExpandedXmlModelledBinaryWriter(OutputStream output) throws XMLStreamException {
        super(ModelledBinaryInteraction.class, output);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractExpandedXmlModelledBinaryWriter(Writer writer) throws XMLStreamException {
        super(ModelledBinaryInteraction.class, writer);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param cache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractExpandedXmlModelledBinaryWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
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
        super.initialiseSubWriters(true, true, PsiXmlType.expanded, InteractionCategory.modelled, ComplexType.binary);
        // initialise same default experiment
        getComplexWriter().setDefaultExperiment(getInteractionWriter().getDefaultExperiment());
    }

    /** {@inheritDoc} */
    @Override
    protected void writeInteraction() throws XMLStreamException {
        // write interaction
        super.writeInteraction();
        // remove experiments
        for (Object exp : ((PsiXmlExtendedInteractionWriter)getInteractionWriter()).extractDefaultExperimentsFrom(getCurrentInteraction())){
            getElementCache().removeObject(exp);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeComplex(ModelledInteraction modelled) {
        super.writeComplex(modelled);
        // remove experiments
        for (Object exp : ((PsiXmlExtendedInteractionWriter)getComplexWriter()).extractDefaultExperimentsFrom(modelled)){
            getElementCache().removeObject(exp);
        }
    }
}

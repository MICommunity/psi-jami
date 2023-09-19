package psidev.psi.mi.jami.xml.io.writer.expanded.extended;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.ComplexType;
import psidev.psi.mi.jami.model.InteractionCategory;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.xml.PsiXmlType;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
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
public class ExpandedXmlModelledBinaryWriter extends AbstractExpandedXmlWriter<ModelledBinaryInteraction> {

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     */
    public ExpandedXmlModelledBinaryWriter(PsiXmlVersion version) {
        super(version, ModelledBinaryInteraction.class);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlModelledBinaryWriter(PsiXmlVersion version, File file) throws IOException, XMLStreamException {
        super(version, ModelledBinaryInteraction.class, file);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlModelledBinaryWriter(PsiXmlVersion version, OutputStream output) throws XMLStreamException {
        super(version, ModelledBinaryInteraction.class, output);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlModelledBinaryWriter(PsiXmlVersion version, Writer writer) throws XMLStreamException {
        super(version, ModelledBinaryInteraction.class, writer);
    }

    /**
     * <p>Constructor for ExpandedXmlModelledBinaryWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param cache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public ExpandedXmlModelledBinaryWriter(PsiXmlVersion version, XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(version, ModelledBinaryInteraction.class, streamWriter, cache);
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
    protected void initialiseDefaultSource() {
        setDefaultSource(newXmlSource("Unknown source"));
    }

    /** {@inheritDoc} */
    @Override
    protected void writeInteraction(Double miScore) throws XMLStreamException {
        // write interaction
        super.writeInteraction(miScore);
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

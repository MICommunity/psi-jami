package psidev.psi.mi.jami.xml.io.writer.compact.extended;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXmlType;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.compact.AbstractCompactXmlWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExtendedInteractionWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Compact PSI-XML writer for biological complexes (no experimental evidences)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class CompactXmlComplexWriter extends AbstractCompactXmlWriter<Complex> {

    /**
     * <p>Constructor for CompactXmlComplexWriter.</p>
     */
    public CompactXmlComplexWriter() {
        super(Complex.class);
    }

    /**
     * <p>Constructor for CompactXmlComplexWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlComplexWriter(File file) throws IOException, XMLStreamException {
        super(Complex.class, file);
    }

    /**
     * <p>Constructor for CompactXmlComplexWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlComplexWriter(OutputStream output) throws XMLStreamException {
        super(Complex.class, output);
    }

    /**
     * <p>Constructor for CompactXmlComplexWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlComplexWriter(Writer writer) throws XMLStreamException {
        super(Complex.class, writer);
    }

    /**
     * <p>Constructor for CompactXmlComplexWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param cache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public CompactXmlComplexWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(Complex.class, streamWriter, cache);
    }

    /** {@inheritDoc} */
    @Override
    protected void registerAvailabilities(Complex interaction) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void registerInteractionProperties() {
        super.registerInteractionProperties();
        for (CooperativeEffect effect : getCurrentInteraction().getCooperativeEffects()){
            for (ModelledInteraction interaction : effect.getAffectedInteractions()){
                registerAllInteractorsAndExperimentsFrom(interaction);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void registerExperiment(Complex interaction) {
        getExperiments().addAll(((PsiXmlExtendedInteractionWriter) getInteractionWriter()).extractDefaultExperimentsFrom(interaction));
    }

    /** {@inheritDoc} */
    @Override
    protected Source extractSourceFromInteraction() {
        return getCurrentInteraction().getSource() != null ? getCurrentInteraction().getSource() : super.extractSourceFromInteraction();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWriters() {
        super.initialiseSubWriters(true, true, PsiXmlType.compact, InteractionCategory.complex, ComplexType.n_ary);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultSource() {
        setDefaultSource(newXmlSource("Unknown source"));
    }
}

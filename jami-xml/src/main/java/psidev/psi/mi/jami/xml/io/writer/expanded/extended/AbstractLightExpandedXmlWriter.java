package psidev.psi.mi.jami.xml.io.writer.expanded.extended;

import psidev.psi.mi.jami.model.ComplexType;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionCategory;
import psidev.psi.mi.jami.model.ModelledInteraction;
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
 * Expanded PSI-XML writer for light interactions (no experimental evidences)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public abstract class AbstractLightExpandedXmlWriter extends AbstractExpandedXmlWriter<Interaction> {

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     */
    public AbstractLightExpandedXmlWriter() {
        super(Interaction.class);
    }

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractLightExpandedXmlWriter(File file) throws IOException, XMLStreamException {
        super(Interaction.class, file);
    }

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractLightExpandedXmlWriter(OutputStream output) throws XMLStreamException {
        super(Interaction.class, output);
    }

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractLightExpandedXmlWriter(Writer writer) throws XMLStreamException {
        super(Interaction.class, writer);
    }

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param cache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractLightExpandedXmlWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(Interaction.class, streamWriter, cache);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWriters() {
        super.initialiseSubWriters(true, true, PsiXmlType.expanded, InteractionCategory.basic, ComplexType.n_ary);
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
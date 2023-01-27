package psidev.psi.mi.jami.xml.io.writer.expanded.extended;

import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExtendedInteractionWriter;
import psidev.psi.mi.jami.xml.io.writer.expanded.AbstractExpandedXmlMixWriter;

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
public abstract class AbstractExpandedXmlWriter extends AbstractExpandedXmlMixWriter<Interaction, ModelledInteraction, InteractionEvidence> {

    /**
     * <p>Constructor for ExpandedXmlWriter.</p>
     */
    public AbstractExpandedXmlWriter() {
        super(Interaction.class);
    }

    /**
     * <p>Constructor for ExpandedXmlWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractExpandedXmlWriter(File file) throws IOException, XMLStreamException {
        super(Interaction.class, file);
    }

    /**
     * <p>Constructor for ExpandedXmlWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractExpandedXmlWriter(OutputStream output) throws XMLStreamException {
        super(Interaction.class, output);
    }

    /**
     * <p>Constructor for ExpandedXmlWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public AbstractExpandedXmlWriter(Writer writer) throws XMLStreamException {
        super(Interaction.class, writer);
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

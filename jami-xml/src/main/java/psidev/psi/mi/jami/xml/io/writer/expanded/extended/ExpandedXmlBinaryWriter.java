package psidev.psi.mi.jami.xml.io.writer.expanded.extended;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.io.writer.expanded.AbstractExpandedXmlMixWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExtendedInteractionWriter;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Expanded PSI-XML writer for a mix of expanded binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class ExpandedXmlBinaryWriter extends AbstractExpandedXmlMixWriter<BinaryInteraction, ModelledBinaryInteraction, BinaryInteractionEvidence> {

    /**
     * <p>Constructor for ExpandedXmlBinaryWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     */
    public ExpandedXmlBinaryWriter(PsiXmlVersion version) {
        super(version, BinaryInteraction.class);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlBinaryWriter(PsiXmlVersion version, File file) throws IOException, XMLStreamException {
        super(version, BinaryInteraction.class, file);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlBinaryWriter(PsiXmlVersion version, OutputStream output) throws XMLStreamException {
        super(version, BinaryInteraction.class, output);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlBinaryWriter(PsiXmlVersion version, Writer writer) throws XMLStreamException {
        super(version, BinaryInteraction.class, writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDelegateWriters() {
        setModelledWriter(new ExpandedXmlModelledBinaryWriter(getVersion(), getStreamWriter(), getElementCache()));
        setEvidenceWriter(new ExpandedXmlBinaryEvidenceWriter(getVersion(), getStreamWriter(), getElementCache()));
        setLightWriter(new LightExpandedXmlBinaryWriter(getVersion(), getStreamWriter(), getElementCache()));
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

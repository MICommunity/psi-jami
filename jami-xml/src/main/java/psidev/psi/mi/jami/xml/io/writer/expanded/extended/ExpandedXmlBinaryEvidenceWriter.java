package psidev.psi.mi.jami.xml.io.writer.expanded.extended;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.model.*;
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
 * Expanded PSI-XML writer for expanded binary interaction evidences (full experimental evidences)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class ExpandedXmlBinaryEvidenceWriter extends AbstractExpandedXmlWriter<BinaryInteractionEvidence> {

    /**
     * <p>Constructor for ExpandedXmlBinaryEvidenceWriter.</p>
     */
    public ExpandedXmlBinaryEvidenceWriter(PsiXmlVersion version) {
        super(version, BinaryInteractionEvidence.class);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryEvidenceWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlBinaryEvidenceWriter(PsiXmlVersion version, File file) throws IOException, XMLStreamException {
        super(version, BinaryInteractionEvidence.class, file);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryEvidenceWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlBinaryEvidenceWriter(PsiXmlVersion version, OutputStream output) throws XMLStreamException {
        super(version, BinaryInteractionEvidence.class, output);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlBinaryEvidenceWriter(PsiXmlVersion version, Writer writer) throws XMLStreamException {
        super(version, BinaryInteractionEvidence.class, writer);
    }

    /**
     * <p>Constructor for ExpandedXmlBinaryEvidenceWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param elementCache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public ExpandedXmlBinaryEvidenceWriter(PsiXmlVersion version, XMLStreamWriter streamWriter, PsiXmlObjectCache elementCache) {
        super(version, BinaryInteractionEvidence.class, streamWriter, elementCache);
    }

    /** {@inheritDoc} */
    @Override
    protected Source extractSourceFromInteraction() {
        Experiment exp = getCurrentInteraction().getExperiment();
        if (exp != null && exp.getPublication() != null && exp.getPublication().getSource() != null){
            return exp.getPublication().getSource();
        }
        return super.extractSourceFromInteraction();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWriters() {
        super.initialiseSubWriters(true, true, PsiXmlType.expanded, InteractionCategory.evidence, ComplexType.binary);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultSource() {
        setDefaultSource(newXmlSource("Unknown source"));
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

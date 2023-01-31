package psidev.psi.mi.jami.xml.io.writer.expanded;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.model.ComplexType;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.InteractionCategory;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.xml.PsiXmlType;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Expanded PSI-XML writer for named binary interaction evidences (full experimental evidences)
 * Participants, features and experiments also have expanded names to write
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class ExpandedXmlNamedBinaryEvidenceWriter extends AbstractExpandedXmlWriter<BinaryInteractionEvidence> {

    /**
     * <p>Constructor for ExpandedXmlNamedBinaryEvidenceWriter.</p>
     */
    public ExpandedXmlNamedBinaryEvidenceWriter(PsiXmlVersion version) {
        super(version, BinaryInteractionEvidence.class);
    }

    /**
     * <p>Constructor for ExpandedXmlNamedBinaryEvidenceWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlNamedBinaryEvidenceWriter(PsiXmlVersion version, File file) throws IOException, XMLStreamException {
        super(version, BinaryInteractionEvidence.class, file);
    }

    /**
     * <p>Constructor for ExpandedXmlNamedBinaryEvidenceWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlNamedBinaryEvidenceWriter(PsiXmlVersion version, OutputStream output) throws XMLStreamException {
        super(version, BinaryInteractionEvidence.class, output);
    }

    /**
     * <p>Constructor for ExpandedXmlNamedBinaryEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlNamedBinaryEvidenceWriter(PsiXmlVersion version, Writer writer) throws XMLStreamException {
        super(version, BinaryInteractionEvidence.class, writer);
    }

    /**
     * <p>Constructor for ExpandedXmlNamedBinaryEvidenceWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param cache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public ExpandedXmlNamedBinaryEvidenceWriter(PsiXmlVersion version, XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(version, BinaryInteractionEvidence.class, streamWriter, cache);
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
        super.initialiseSubWriters(false, true, PsiXmlType.expanded, InteractionCategory.evidence, ComplexType.binary);
    }
}

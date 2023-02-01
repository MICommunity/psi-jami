package psidev.psi.mi.jami.xml.io.writer.compact;

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
 * Compact PSI-XML writer for binary interaction evidences (full experimental evidences)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class CompactXmlBinaryEvidenceWriter extends AbstractCompactXmlWriter<BinaryInteractionEvidence> {

    /**
     * <p>Constructor for CompactXmlBinaryEvidenceWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     */
    public CompactXmlBinaryEvidenceWriter(PsiXmlVersion version) {
        super(version, BinaryInteractionEvidence.class);
    }

    /**
     * <p>Constructor for CompactXmlBinaryEvidenceWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlBinaryEvidenceWriter(PsiXmlVersion version, File file) throws IOException, XMLStreamException {
        super(version, BinaryInteractionEvidence.class, file);
    }

    /**
     * <p>Constructor for CompactXmlBinaryEvidenceWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlBinaryEvidenceWriter(PsiXmlVersion version, OutputStream output) throws XMLStreamException {
        super(version, BinaryInteractionEvidence.class, output);
    }

    /**
     * <p>Constructor for CompactXmlBinaryEvidenceWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlBinaryEvidenceWriter(PsiXmlVersion version, Writer writer) throws XMLStreamException {
        super(version, BinaryInteractionEvidence.class, writer);
    }

    /**
     * <p>Constructor for CompactXmlBinaryEvidenceWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param elementCache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public CompactXmlBinaryEvidenceWriter(PsiXmlVersion version, XMLStreamWriter streamWriter, PsiXmlObjectCache elementCache) {
        super(version, BinaryInteractionEvidence.class, streamWriter, elementCache);
    }

    /** {@inheritDoc} */
    @Override
    protected void registerAvailabilities(BinaryInteractionEvidence interaction) {
        if (interaction.getAvailability() != null){
            getAvailabilities().add(interaction.getAvailability());
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void registerExperiment(BinaryInteractionEvidence interaction) {
        getExperiments().add(getInteractionWriter().extractDefaultExperimentFrom(interaction));
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
        super.initialiseSubWriters(false, false, PsiXmlType.compact, InteractionCategory.evidence, ComplexType.binary);
    }
}

package psidev.psi.mi.jami.xml.io.writer.compact;

import psidev.psi.mi.jami.model.*;
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
 * Compact PSI-XML writer for named interaction evidences (full experimental evidences)
 * Participants, features and experiments also have expanded names to write
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class CompactXmlNamedEvidenceWriter extends AbstractCompactXmlWriter<InteractionEvidence> {

    /**
     * <p>Constructor for CompactXmlNamedEvidenceWriter.</p>
     */
    public CompactXmlNamedEvidenceWriter(PsiXmlVersion version) {
        super(version, InteractionEvidence.class);
    }

    /**
     * <p>Constructor for CompactXmlNamedEvidenceWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlNamedEvidenceWriter(PsiXmlVersion version, File file) throws IOException, XMLStreamException {
        super(version, InteractionEvidence.class, file);
    }

    /**
     * <p>Constructor for CompactXmlNamedEvidenceWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlNamedEvidenceWriter(PsiXmlVersion version, OutputStream output) throws XMLStreamException {
        super(version, InteractionEvidence.class, output);
    }

    /**
     * <p>Constructor for CompactXmlNamedEvidenceWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public CompactXmlNamedEvidenceWriter(PsiXmlVersion version, Writer writer) throws XMLStreamException {
        super(version, InteractionEvidence.class, writer);
    }

    /**
     * <p>Constructor for CompactXmlNamedEvidenceWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param cache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public CompactXmlNamedEvidenceWriter(PsiXmlVersion version, XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(version, InteractionEvidence.class, streamWriter, cache);
    }

    /** {@inheritDoc} */
    @Override
    protected void registerAvailabilities(InteractionEvidence interaction) {
        if (interaction.getAvailability() != null){
            getAvailabilities().add(interaction.getAvailability());
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void registerExperiment(InteractionEvidence interaction) {
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
        super.initialiseSubWriters(false, true, PsiXmlType.compact, InteractionCategory.evidence, ComplexType.n_ary);
    }
}

package psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.ExpandedPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml25.xml254.AbstractXmlInteractionEvidenceWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.XmlParticipantEvidenceWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Expanded XML 2.5 writer for an interaction evidence (with full experimental details).
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public class XmlInteractionEvidenceWriter extends AbstractXmlInteractionEvidenceWriter<InteractionEvidence>
        implements ExpandedPsiXmlElementWriter<InteractionEvidence> {
    /**
     * <p>Constructor for XmlInteractionEvidenceWriter.</p>
     *
     * @param writer a {@link XMLStreamWriter} object.
     * @param objectIndex a {@link PsiXmlObjectCache} object.
     */
    public XmlInteractionEvidenceWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantWriter() {
        super.setParticipantWriter(new XmlParticipantEvidenceWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void writeAvailability(InteractionEvidence object) throws XMLStreamException {
        if (object.getAvailability() != null){
            writeAvailabilityDescription(object.getAvailability());
        }
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperiments(InteractionEvidence object) throws XMLStreamException {
        super.writeExperiments(object);
        return writeExperimentDescription();
    }
}

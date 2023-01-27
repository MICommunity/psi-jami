package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.AbstractXmlInteractionEvidenceWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Compact XML 2.5 writer for an expanded interaction evidence (having modelled, intramolecular properties, list
 * of experiments, list of interaction types, etc.).
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public class XmlInteractionEvidenceWriter extends AbstractXmlInteractionEvidenceWriter<InteractionEvidence>
                                                   implements CompactPsiXmlElementWriter<InteractionEvidence> {
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
            writeAvailabilityRef(object.getAvailability());
        }
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperiments(InteractionEvidence object) throws XMLStreamException {
        super.writeExperiments(object);
        return writeExperimentRef();
    }
}

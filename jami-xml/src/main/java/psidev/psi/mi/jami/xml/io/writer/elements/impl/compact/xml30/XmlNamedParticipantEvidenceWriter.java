package psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30;

import psidev.psi.mi.jami.model.NamedParticipant;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Compact Xml 3.0 writer for a named participant evidence with a shortname and a fullname.
 * It writes full experimental details
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public class XmlNamedParticipantEvidenceWriter extends XmlParticipantEvidenceWriter implements CompactPsiXmlElementWriter<ParticipantEvidence> {
    /**
     * <p>Constructor for XmlNamedParticipantEvidenceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlNamedParticipantEvidenceWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeNames(ParticipantEvidence object) throws XMLStreamException {
        NamedParticipant xmlParticipant = (NamedParticipant) object;
        // write names
        // write names
        PsiXmlUtils.writeCompleteNamesElement(xmlParticipant.getShortName(),
                xmlParticipant.getFullName(), xmlParticipant.getAliases(), getStreamWriter(),
                getAliasWriter());
    }
}

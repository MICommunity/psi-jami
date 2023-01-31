package psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30;

import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.NamedParticipant;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.ExpandedPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30.AbstractXmlParticipantEvidenceWriter;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Expanded Xml 3.0 writer for a named participant evidence with a shortname and a fullname.
 * It writes full experimental details
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public class XmlNamedParticipantEvidenceWriter extends AbstractXmlParticipantEvidenceWriter implements ExpandedPsiXmlElementWriter<ParticipantEvidence> {
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
    protected void writeMolecule(Interactor interactor) throws XMLStreamException {
        super.writeMoleculeDescription(interactor);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeNames(ParticipantEvidence object) throws XMLStreamException {
        NamedParticipant xmlParticipant = (NamedParticipant) object;
        // write names
        PsiXmlUtils.writeCompleteNamesElement(xmlParticipant.getShortName(),
                xmlParticipant.getFullName(), xmlParticipant.getAliases(), getStreamWriter(),
                getAliasWriter());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantCandidateWriter() {
        super.setParticipantCandidateWriter(new XmlExperimentalParticipantCandidateWriter(getStreamWriter(), getObjectIndex()));
    }
}

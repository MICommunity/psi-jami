package psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30;

import psidev.psi.mi.jami.model.ExperimentalParticipantCandidate;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlInteractorWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30.AbstractXmlParticipantCandidateWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlFeatureEvidenceWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Expanded XML 3.0 writer for a participant evidence with full experimental details
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public class XmlExperimentalParticipantCandidateWriter extends AbstractXmlParticipantCandidateWriter<ExperimentalParticipantCandidate, FeatureEvidence>
        implements CompactPsiXmlElementWriter<ExperimentalParticipantCandidate> {
    /**
     * <p>Constructor for XmlExperimentalParticipantCandidateWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlExperimentalParticipantCandidateWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInteractorWriter() {
        super.setInteractorWriter(new XmlInteractorWriter(PsiXmlVersion.v3_0_0, getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFeatureWriter() {
       super.setFeatureWriter(new XmlFeatureEvidenceWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void writeMolecule(Interactor interactor) throws XMLStreamException {
        super.writeMoleculeDescription(interactor);
    }
}

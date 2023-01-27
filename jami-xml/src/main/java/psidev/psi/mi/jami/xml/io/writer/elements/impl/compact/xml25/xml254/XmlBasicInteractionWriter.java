package psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml25.xml254.AbstractXmlInteractionWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.XmlParticipantWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Compact XML 2.5 writer for a basic interaction (ignore experimental details)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public class XmlBasicInteractionWriter extends AbstractXmlInteractionWriter<Interaction> implements CompactPsiXmlElementWriter<Interaction> {
    /**
     * <p>Constructor for XmlBasicInteractionWriter.</p>
     *
     * @param writer a {@link XMLStreamWriter} object.
     * @param objectIndex a {@link PsiXmlObjectCache} object.
     */
    public XmlBasicInteractionWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantWriter() {
        super.setParticipantWriter(new XmlParticipantWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperiments(Interaction object) throws XMLStreamException {
        return writeExperimentRef();
    }
}

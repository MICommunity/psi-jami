package psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml25.xml254.AbstractXmlModelledInteractionWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.XmlModelledParticipantWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Compact XML 2.5 writer for a modelled interaction (ignore experimental details).
 * It will write cooperative effects as attributes
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */
public class XmlModelledInteractionWriter extends AbstractXmlModelledInteractionWriter<ModelledInteraction> implements CompactPsiXmlElementWriter<ModelledInteraction> {

    /**
     * <p>Constructor for XmlModelledInteractionWriter.</p>
     *
     * @param writer a {@link XMLStreamWriter} object.
     * @param objectIndex a {@link PsiXmlObjectCache} object.
     */
    public XmlModelledInteractionWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantWriter() {
        super.setParticipantWriter(new XmlModelledParticipantWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperiments(ModelledInteraction object) throws XMLStreamException {
        super.writeExperiments(object);
        return writeExperimentRef();
    }
}

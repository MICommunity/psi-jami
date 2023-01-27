package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.XmlModelledParticipantWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.AbstractXmlModelledInteractionWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Compact XML 2.5 writer for an expanded modelled interaction (ignore experimental details).
 * It will write cooperative effects as attributes.
 * It will write intra-molecular property, names, interaction types and experiments
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */
public class XmlModelledInteractionWriter extends AbstractXmlModelledInteractionWriter<ModelledInteraction>
        implements CompactPsiXmlElementWriter<ModelledInteraction> {

    /**
     * <p>Constructor for XmlModelledInteractionWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
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

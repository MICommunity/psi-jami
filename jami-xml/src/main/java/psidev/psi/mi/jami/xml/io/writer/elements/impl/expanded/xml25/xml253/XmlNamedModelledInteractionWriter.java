package psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253;

import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.XmlNamedModelledParticipantWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlNamedExperimentWriter;

import javax.xml.stream.XMLStreamWriter;

/**
 * Expanded XML 2.5 writer for a modelled interaction (ignore experimental details) which have a fullname and aliases in
 * addition to the shortname.
 * It will write cooperative effects as attributes
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public class XmlNamedModelledInteractionWriter extends XmlModelledInteractionWriter {

    /**
     * <p>Constructor for XmlNamedModelledInteractionWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlNamedModelledInteractionWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantWriter() {
        super.setParticipantWriter(new XmlNamedModelledParticipantWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExperimentWriter(){
        super.setExperimentWriter(new XmlNamedExperimentWriter(getStreamWriter(), getObjectIndex()));
    }
}

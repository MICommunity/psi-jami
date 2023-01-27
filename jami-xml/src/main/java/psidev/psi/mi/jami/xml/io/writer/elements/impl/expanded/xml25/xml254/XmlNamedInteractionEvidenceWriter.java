package psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254;

import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.NamedInteraction;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlAliasWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.XmlNamedParticipantEvidenceWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlNamedExperimentWriter;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Expanded XML 2.5 writer for a named interaction evidence (with full experimental details).
 * The interaction has aliases and a fullname in addition to the shortname
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public class XmlNamedInteractionEvidenceWriter extends XmlInteractionEvidenceWriter {
    private PsiXmlElementWriter<Alias> aliasWriter;

    /**
     * <p>Constructor for XmlNamedInteractionEvidenceWriter.</p>
     *
     * @param writer a {@link XMLStreamWriter} object.
     * @param objectIndex a {@link PsiXmlObjectCache} object.
     */
    public XmlNamedInteractionEvidenceWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantWriter() {
        super.setParticipantWriter(new XmlNamedParticipantEvidenceWriter(getStreamWriter(), getObjectIndex()));
    }

    /**
     * <p>Getter for the field <code>aliasWriter</code>.</p>
     *
     * @return a {@link PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Alias> getAliasWriter() {
        if (this.aliasWriter == null){
            this.aliasWriter = new XmlAliasWriter(getStreamWriter());
        }
        return aliasWriter;
    }

    /**
     * <p>Setter for the field <code>aliasWriter</code>.</p>
     *
     * @param aliasWriter a {@link PsiXmlElementWriter} object.
     */
    public void setAliasWriter(PsiXmlElementWriter<Alias> aliasWriter) {
        this.aliasWriter = aliasWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExperimentWriter(){
        super.setExperimentWriter(new XmlNamedExperimentWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void writeNames(InteractionEvidence object) throws XMLStreamException {
        if (object instanceof NamedInteraction){
            NamedInteraction namedInteraction = (NamedInteraction) object;
            // write names
            PsiXmlUtils.writeCompleteNamesElement(namedInteraction.getShortName(),
                    namedInteraction.getFullName(), namedInteraction.getAliases(), getStreamWriter(),
                    getAliasWriter());
        }
        else{
            super.writeNames(object);
        }
    }
}

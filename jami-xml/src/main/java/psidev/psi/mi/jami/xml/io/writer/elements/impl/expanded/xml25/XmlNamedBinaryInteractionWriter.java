package psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.NamedInteraction;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlAliasWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlNamedExperimentWriter;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Expanded XML 2.5 writer for a basic binary interaction (ignore experimental details).
 * Interactions are named interactions with a fullname and aliases in addition to a shortname
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */
public class XmlNamedBinaryInteractionWriter extends XmlBasicBinaryInteractionWriter {
    private PsiXmlElementWriter<Alias> aliasWriter;

    /**
     * <p>Constructor for XmlNamedBinaryInteractionWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlNamedBinaryInteractionWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(version, writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantWriter() {
        super.setParticipantWriter(new XmlParticipantWriter(getVersion(), getStreamWriter(), getObjectIndex()));
    }

    /**
     * <p>Getter for the field <code>aliasWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
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
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setAliasWriter(PsiXmlElementWriter<Alias> aliasWriter) {
        this.aliasWriter = aliasWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExperimentWriter(){
        super.setExperimentWriter(new XmlNamedExperimentWriter(getVersion(), getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void writeNames(BinaryInteraction object) throws XMLStreamException {
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

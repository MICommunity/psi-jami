package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended;

import org.codehaus.stax2.XMLStreamWriter2;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXml25ObjectIndex;
import psidev.psi.mi.jami.xml.extension.ExtendedPsi25Interaction;
import psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXml25ElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.ExpandedPsiXml25ElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXml25ElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXml25XrefWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.AbstractXml25InteractionWithoutExperimentWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.ExpandedXml25ParticipantWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.Xml25AliasWriter;
import psidev.psi.mi.jami.xml.utils.PsiXml25Utils;

import javax.xml.stream.XMLStreamException;
import java.util.Set;

/**
 * Expanded XML 2.5 writer for a basic interaction (ignore experimental details).
 * Interactions are named interactions with a fullname and aliases in addition to a shortname
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */

public class ExpandedXml25InteractionWriter extends AbstractXml25InteractionWithoutExperimentWriter<Interaction,Participant> implements CompactPsiXml25ElementWriter<Interaction> {
    private PsiXml25ElementWriter<Alias> aliasWriter;

    public ExpandedXml25InteractionWriter(XMLStreamWriter2 writer, PsiXml25ObjectIndex objectIndex) {
        super(writer, objectIndex, new ExpandedXml25ParticipantWriter(writer, objectIndex));
        this.aliasWriter = new Xml25AliasWriter(writer);
    }

    public ExpandedXml25InteractionWriter(XMLStreamWriter2 writer, PsiXml25ObjectIndex objectIndex,
                                          PsiXml25XrefWriter primaryRefWriter, PsiXml25XrefWriter secondaryRefWriter,
                                          ExpandedPsiXml25ElementWriter<Participant> participantWriter, PsiXml25ElementWriter<CvTerm> interactionTypeWriter,
                                          PsiXml25ElementWriter<Annotation> attributeWriter, PsiXml25ElementWriter<Set<Feature>> inferredInteractionWriter,
                                          PsiXml25ElementWriter<Alias> aliasWriter) {
        super(writer, objectIndex, primaryRefWriter, secondaryRefWriter,
                participantWriter != null ? participantWriter : new ExpandedXml25ParticipantWriter(writer, objectIndex), interactionTypeWriter, attributeWriter, inferredInteractionWriter);
        this.aliasWriter = aliasWriter != null ? aliasWriter : new Xml25AliasWriter(writer);
    }

    @Override
    protected void writeAvailability(Interaction object) {
        // nothing to do
    }

    @Override
    protected void writeExperiments(Interaction object) throws XMLStreamException {
        writeExperimentDescription();
    }

    @Override
    protected void writeOtherAttributes(Interaction object) {
        // nothing to do
    }

    @Override
    protected void writeIntraMolecular(Interaction object) throws XMLStreamException {
        ExtendedPsi25Interaction xmlInteraction = (ExtendedPsi25Interaction)object;
        if (xmlInteraction.isIntraMolecular()){
            getStreamWriter().writeCharacters(PsiXml25Utils.LINE_BREAK);
            getStreamWriter().writeStartElement("intraMolecular");
            getStreamWriter().writeCharacters(Boolean.toString(xmlInteraction.isIntraMolecular()));
            // write end intra molecular
            getStreamWriter().writeEndElement();
            getStreamWriter().writeCharacters(PsiXml25Utils.LINE_BREAK);
        }
    }

    @Override
    protected void writeModelled(Interaction object) {
        // nothing to do
    }

    @Override
    protected void writeParameters(Interaction object) {
        // nothing to do
    }

    @Override
    protected void writeConfidences(Interaction object) {
        // nothing to do
    }

    @Override
    protected void writeNegative(Interaction object) {
        // nothing to do
    }

    @Override
    protected void writeNames(Interaction object) throws XMLStreamException {
        NamedInteraction xmlInteraction = (NamedInteraction) object;
        // write names
        boolean hasShortLabel = xmlInteraction.getShortName() != null;
        boolean hasInteractionFullLabel = xmlInteraction.getFullName() != null;
        boolean hasAliases = !xmlInteraction.getAliases().isEmpty();
        if (hasShortLabel || hasInteractionFullLabel || hasAliases){
            getStreamWriter().writeCharacters(PsiXml25Utils.LINE_BREAK);
            getStreamWriter().writeStartElement("names");
            getStreamWriter().writeCharacters(PsiXml25Utils.LINE_BREAK);
            // write shortname
            if (hasShortLabel){
                getStreamWriter().writeStartElement("shortLabel");
                getStreamWriter().writeCharacters(xmlInteraction.getShortName());
                getStreamWriter().writeEndElement();
                getStreamWriter().writeCharacters(PsiXml25Utils.LINE_BREAK);
            }
            // write fullname
            if (hasInteractionFullLabel){
                getStreamWriter().writeStartElement("fullName");
                getStreamWriter().writeCharacters(xmlInteraction.getFullName());
                getStreamWriter().writeEndElement();
                getStreamWriter().writeCharacters(PsiXml25Utils.LINE_BREAK);
            }
            // write aliases
            for (Object alias : xmlInteraction.getAliases()){
                this.aliasWriter.write((Alias)alias);
                getStreamWriter().writeCharacters(PsiXml25Utils.LINE_BREAK);
            }
            // write end names
            getStreamWriter().writeEndElement();
            getStreamWriter().writeCharacters(PsiXml25Utils.LINE_BREAK);
        }
    }
}

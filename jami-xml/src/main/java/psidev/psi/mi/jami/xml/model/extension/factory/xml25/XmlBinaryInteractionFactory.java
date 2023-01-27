package psidev.psi.mi.jami.xml.model.extension.factory.xml25;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.factory.BinaryInteractionFactory;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.clone.InteractionCloner;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.model.extension.binary.AbstractXmlBinaryInteractionEvidence;
import psidev.psi.mi.jami.xml.model.extension.binary.XmlBinaryInteraction;
import psidev.psi.mi.jami.xml.model.extension.binary.XmlBinaryInteractionEvidenceWrapper;
import psidev.psi.mi.jami.xml.model.extension.binary.XmlBinaryInteractionWrapper;
import psidev.psi.mi.jami.xml.model.extension.binary.XmlModelledBinaryInteraction;
import psidev.psi.mi.jami.xml.model.extension.binary.XmlModelledBinaryInteractionWrapper;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlModelledInteraction;

/**
 * Xml extension of BinaryInteractionFactory for XML 2.5
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
public class XmlBinaryInteractionFactory implements BinaryInteractionFactory {

    private PsiXmlVersion version;

    public XmlBinaryInteractionFactory(PsiXmlVersion version) {
        this.version = version;
    }

    /** {@inheritDoc} */
    @Override
    public BinaryInteractionEvidence createSelfBinaryInteractionEvidenceFrom(InteractionEvidence interaction) {
        AbstractXmlBinaryInteractionEvidence binary = instantiateNewBinaryInteractionEvidence();
        InteractionCloner.copyAndOverrideParticipantsEvidencesToBinary(interaction, binary, false, true);

        copyXmlInteractionEvidenceProperties(interaction, binary);
        return binary;
    }

    /** {@inheritDoc} */
    @Override
    public BinaryInteraction createBasicBinaryInteractionFrom(Interaction interaction, Participant p1, Participant p2, CvTerm expansionMethod) {
        XmlBinaryInteraction binary = instantiateNewBinaryInteraction();
        binary.setComplexExpansion(expansionMethod);
        copyBasicXmlInteractionProperties(interaction, binary);
        binary.setParticipantA(p1);
        binary.setParticipantB(p2);
        return binary;    }

    /** {@inheritDoc} */
    @Override
    public BinaryInteractionEvidence createBinaryInteractionEvidenceFrom(InteractionEvidence interaction, ParticipantEvidence p1, ParticipantEvidence p2, CvTerm expansionMethod) {
        AbstractXmlBinaryInteractionEvidence binary = instantiateNewBinaryInteractionEvidence();
        binary.setComplexExpansion(expansionMethod);
        copyXmlInteractionEvidenceProperties(interaction, binary);
        binary.setParticipantA(p1);
        binary.setParticipantB(p2);
        return binary;
    }

    /** {@inheritDoc} */
    @Override
    public BinaryInteraction createSelfBinaryInteractionFrom(Interaction interaction) {
        XmlBinaryInteraction binary = instantiateNewBinaryInteraction();
        InteractionCloner.copyAndOverrideBasicParticipantsToBinary(interaction, binary, false, true);

        copyBasicXmlInteractionProperties(interaction, binary);
        return binary;
    }

    /** {@inheritDoc} */
    @Override
    public ModelledBinaryInteraction createModelledBinaryInteractionFrom(ModelledInteraction interaction, ModelledParticipant p1, ModelledParticipant p2, CvTerm expansionMethod) {
        XmlModelledBinaryInteraction binary = instantiateNewModelledBinaryInteraction();
        binary.setComplexExpansion(expansionMethod);
        copyXmlModelledInteractionProperties(interaction, binary);
        binary.setParticipantA(p1);
        binary.setParticipantB(p2);
        return binary;
    }

    /** {@inheritDoc} */
    @Override
    public ModelledBinaryInteraction createSelfModelledBinaryInteractionFrom(ModelledInteraction interaction) {
        XmlModelledBinaryInteraction binary = instantiateNewModelledBinaryInteraction();
        InteractionCloner.copyAndOverrideModelledParticipantsToBinary(interaction, binary, false, true);

        copyXmlModelledInteractionProperties(interaction, binary);
        return binary;
    }

    /** {@inheritDoc} */
    @Override
    public BinaryInteraction createBinaryInteractionWrapperFrom(Interaction interaction) {
        return new XmlBinaryInteractionWrapper((ExtendedPsiXmlInteraction)interaction);
    }

    /** {@inheritDoc} */
    @Override
    public BinaryInteractionEvidence createBinaryInteractionEvidenceWrapperFrom(InteractionEvidence interaction) {
        return new XmlBinaryInteractionEvidenceWrapper((ExtendedPsiXmlInteractionEvidence)interaction);
    }

    /** {@inheritDoc} */
    @Override
    public ModelledBinaryInteraction createModelledBinaryInteractionWrapperFrom(ModelledInteraction interaction) {
        return new XmlModelledBinaryInteractionWrapper((ExtendedPsiXmlModelledInteraction)interaction);
    }

    /** {@inheritDoc} */
    @Override
    public XmlBinaryInteraction instantiateNewBinaryInteraction() {
        return new XmlBinaryInteraction();
    }

    /** {@inheritDoc} */
    @Override
    public AbstractXmlBinaryInteractionEvidence instantiateNewBinaryInteractionEvidence() {
        switch (version) {
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.binary.xml253.XmlBinaryInteractionEvidence();
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.binary.xml254.XmlBinaryInteractionEvidence();
        }
    }

    /** {@inheritDoc} */
    @Override
    public XmlModelledBinaryInteraction instantiateNewModelledBinaryInteraction() {
        return new XmlModelledBinaryInteraction();
    }

    private void copyXmlInteractionEvidenceProperties(InteractionEvidence interaction, ExtendedPsiXmlInteractionEvidence binary) {
        ExtendedPsiXmlInteractionEvidence xmlSource = (ExtendedPsiXmlInteractionEvidence)interaction;
        InteractionCloner.copyAndOverrideInteractionEvidenceProperties(interaction, binary, false, true);
        binary.setId(xmlSource.getId());
        binary.getInferredInteractions().clear();
        binary.getInferredInteractions().addAll(xmlSource.getInferredInteractions());
        binary.getInteractionTypes().clear();
        binary.getInteractionTypes().addAll(xmlSource.getInteractionTypes());
        binary.setIntraMolecular(xmlSource.isIntraMolecular());
        binary.setSourceLocator(xmlSource.getSourceLocator());
        binary.setEntry(xmlSource.getEntry());
        binary.setFullName(xmlSource.getFullName());
        binary.getAliases().addAll(xmlSource.getAliases());
        binary.setXmlAvailability(xmlSource.getXmlAvailability());
        binary.setModelled(xmlSource.isModelled());
        binary.getExperiments().clear();
        binary.getExperiments().addAll(xmlSource.getExperiments());
        binary.getOriginalExperiments().clear();
        binary.getOriginalExperiments().addAll(xmlSource.getOriginalExperiments());
    }

    private void copyBasicXmlInteractionProperties(Interaction interaction, XmlBinaryInteraction binary) {
        ExtendedPsiXmlInteraction xmlSource = (ExtendedPsiXmlInteraction)interaction;
        InteractionCloner.copyAndOverrideBasicInteractionProperties(interaction, binary, false, true);
        binary.setId(xmlSource.getId());
        binary.getInferredInteractions().clear();
        binary.getInferredInteractions().addAll(xmlSource.getInferredInteractions());
        binary.getInteractionTypes().clear();
        binary.getInteractionTypes().addAll(xmlSource.getInteractionTypes());
        binary.setIntraMolecular(xmlSource.isIntraMolecular());
        binary.setSourceLocator(xmlSource.getSourceLocator());
        binary.setEntry(xmlSource.getEntry());
        binary.setFullName(xmlSource.getFullName());
        binary.getAliases().addAll(xmlSource.getAliases());
    }

    private void copyXmlModelledInteractionProperties(ModelledInteraction interaction, XmlModelledBinaryInteraction binary) {
        ExtendedPsiXmlModelledInteraction xmlSource = (ExtendedPsiXmlModelledInteraction)interaction;
        InteractionCloner.copyAndOverrideModelledInteractionProperties(interaction, binary, false, true);
        binary.setId(xmlSource.getId());
        binary.getInferredInteractions().clear();
        binary.getInferredInteractions().addAll(xmlSource.getInferredInteractions());
        binary.getInteractionTypes().clear();
        binary.getInteractionTypes().addAll(xmlSource.getInteractionTypes());
        binary.setIntraMolecular(xmlSource.isIntraMolecular());
        binary.setSourceLocator(xmlSource.getSourceLocator());
        binary.setEntry(xmlSource.getEntry());
        binary.setFullName(xmlSource.getFullName());
        binary.getAliases().addAll(xmlSource.getAliases());
        binary.getExperiments().clear();
        binary.getExperiments().addAll(xmlSource.getExperiments());
    }
}

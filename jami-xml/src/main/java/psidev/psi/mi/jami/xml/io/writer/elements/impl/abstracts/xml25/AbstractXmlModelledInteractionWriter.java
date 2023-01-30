package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml25;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlConfidenceWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlCvTermWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlDbXrefWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlInferredInteractionWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlExperimentWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlParameterWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Collection;
import java.util.Set;

/**
 * Abstract class for XML 2.5 writers of modelled interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public abstract class AbstractXmlModelledInteractionWriter<I extends ModelledInteraction>
        extends psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlModelledInteractionWriter<I> {

    /**
     * <p>Constructor for AbstractXmlModelledInteractionWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlModelledInteractionWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(version, writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefWriter(){
        super.setXrefWriter(new XmlDbXrefWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExperimentWriter(){
        super.setExperimentWriter(new XmlExperimentWriter(getVersion(), getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseConfidenceWriter(){
        super.setConfidenceWriter(new XmlConfidenceWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParameterWriter(){
        super.setParameterWriter(new XmlParameterWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInferredInteractionWriter() {
        super.setInferredInteractionWriter(new XmlInferredInteractionWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInteractionTypeWriter() {
        super.setInteractionTypeWriter(new XmlCvTermWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultExperiment() {
        super.initialiseDefaultExperiment();
        getParameterWriter().setDefaultExperiment(getDefaultExperiment());
    }

    /** {@inheritDoc} */
    @Override
    public void setDefaultExperiment(Experiment defaultExperiment) {
        super.setDefaultExperiment(defaultExperiment);
        getParameterWriter().setDefaultExperiment(defaultExperiment);
    }

    /** {@inheritDoc} */
    @Override
    public Experiment extractDefaultExperimentFrom(I interaction) {
        Experiment exp = null;
        if (!interaction.getCooperativeEffects().isEmpty()){
            CooperativeEffect effect = interaction.getCooperativeEffects().iterator().next();
            if (!effect.getCooperativityEvidences().isEmpty()){
                CooperativityEvidence evidence = effect.getCooperativityEvidences().iterator().next();
                // set first experiment as default experiment
                if (evidence.getPublication() != null){
                    exp = newExperiment(evidence.getPublication());
                    ((NamedExperiment)exp).setFullName(evidence.getPublication().getTitle());
                }
            }
        }
        return exp != null ? exp : getDefaultExperiment() ;
    }

    /**
     * <p>writeInferredInteractions.</p>
     *
     * @param object a I object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeInferredInteractions(I object) throws XMLStreamException {
        Collection<Set<Feature>> inferredInteractions = collectInferredInteractionsFrom(object);
        if (inferredInteractions != null && !inferredInteractions.isEmpty()){
            getStreamWriter().writeStartElement("inferredInteractionList");
            for (Set<Feature> inferred : inferredInteractions){
                getInferredInteractionWriter().write(inferred);
            }
            getStreamWriter().writeEndElement();
        }
    }

    /** {@inheritDoc} */
    protected void writeCooperativeEffect(I object, boolean startAttributeList) throws XMLStreamException {
        if (startAttributeList){
            // write start attribute list
            getStreamWriter().writeStartElement("attributeList");
        }

        CooperativeEffect effect = object.getCooperativeEffects().iterator().next();
        // write mechanism first
        if (effect instanceof Preassembly){
            writeCooperativeEffectAttribute(CooperativeEffect.PREASSEMBLY, CooperativeEffect.PREASSEMBLY_ID, null);
        }
        else if (effect instanceof Allostery){
            writeCooperativeEffectAttribute(CooperativeEffect.ALLOSTERY, CooperativeEffect.ALLOSTERY_ID, null);
            Allostery allostery = (Allostery)effect;

            // write allosteric molecule
            writeCooperativeEffectAttribute(CooperativeEffect.ALLOSTERIC_MOLECULE, CooperativeEffect.ALLOSTERIC_MOLECULE_ID,
                    Integer.toString(getObjectIndex().extractIdForParticipant(allostery.getAllostericMolecule())));
            // write allosteric effector
            AllostericEffector effector = allostery.getAllostericEffector();
            switch (effector.getEffectorType()){
                case molecule:
                    MoleculeEffector moleculeEffector = (MoleculeEffector)effector;
                    writeCooperativeEffectAttribute(CooperativeEffect.ALLOSTERIC_EFFECTOR, CooperativeEffect.ALLOSTERIC_EFFECTOR_ID,
                            Integer.toString(getObjectIndex().extractIdForParticipant(moleculeEffector.getMolecule())));
                    break;
                case feature_modification:
                    FeatureModificationEffector featureEffector = (FeatureModificationEffector)effector;
                    writeCooperativeEffectAttribute(CooperativeEffect.ALLOSTERIC_PTM, CooperativeEffect.ALLOSTERIC_PTM_ID,
                            Integer.toString(getObjectIndex().extractIdForFeature(featureEffector.getFeatureModification())));
                    break;
                default:
                    break;
            }
            // write allostery type
            if (allostery.getAllosteryType() != null){
                writeCooperativeEffectAttribute(allostery.getAllosteryType().getShortName(), allostery.getAllosteryType().getMIIdentifier(), null);
            }
            // write allostery mechanism
            if (allostery.getAllostericMechanism() != null){
                writeCooperativeEffectAttribute(allostery.getAllostericMechanism().getShortName(), allostery.getAllostericMechanism().getMIIdentifier(), null);
            }
        }
        // write outcome
        writeCooperativeEffectAttribute(effect.getOutCome().getShortName(), effect.getOutCome().getMIIdentifier(), null);
        // write response
        if (effect.getResponse() != null){
            writeCooperativeEffectAttribute(effect.getResponse().getShortName(), effect.getResponse().getMIIdentifier(), null);
        }
        // write affected interactions
        if (!effect.getAffectedInteractions().isEmpty()){
            for (ModelledInteraction affected : effect.getAffectedInteractions()){
                getObjectIndex().registerSubComplex(affected);
                writeCooperativeEffectAttribute(CooperativeEffect.AFFECTED_INTERACTION, CooperativeEffect.AFFECTED_INTERACTION_ID, Integer.toString(getObjectIndex().extractIdForInteraction(affected)));
            }
        }

        if (startAttributeList){
            // write end attributeList
            getStreamWriter().writeEndElement();
        }
    }

    /**
     * <p>writeCooperativeEffectAttribute.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param nameAc a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeCooperativeEffectAttribute(String name, String nameAc, String value) throws XMLStreamException {
        // write start
        getStreamWriter().writeStartElement("attribute");
        // write topic
        getStreamWriter().writeAttribute("name", name);
        if (nameAc != null){
            getStreamWriter().writeAttribute("nameAc", nameAc);
        }
        // write description
        if (value != null){
            getStreamWriter().writeCharacters(value);
        }

        // write end attribute
        getStreamWriter().writeEndElement();
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherProperties(I object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeStartInteraction() throws XMLStreamException {
        getStreamWriter().writeStartElement("interaction");
    }
}

package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExtendedInteractionWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlInferredInteractionWriter;
import psidev.psi.mi.jami.xml.model.extension.AbstractInferredInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.*;

/**
 * Abstract class for XML 2.5 writers of modelled interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public abstract class AbstractXmlModelledInteractionWriter<I extends ModelledInteraction>
        extends psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.AbstractXmlModelledInteractionWriter<I>
        implements PsiXmlExtendedInteractionWriter<I> {

    private PsiXmlElementWriter<AbstractInferredInteraction> inferredInteractionWriter;
    private List<Experiment> defaultExperiments;

    /**
     * <p>Constructor for AbstractXmlModelledInteractionWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlModelledInteractionWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(version, writer, objectIndex);

    }

    /**
     * <p>getXmlInferredInteractionWriter.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<AbstractInferredInteraction> getXmlInferredInteractionWriter() {
        if (this.inferredInteractionWriter == null){
            this.inferredInteractionWriter = new XmlInferredInteractionWriter(getStreamWriter(), getObjectIndex());
        }
        return inferredInteractionWriter;
    }

    /**
     * <p>setXmlInferredInteractionWriter.</p>
     *
     * @param inferredInteractionWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setXmlInferredInteractionWriter(PsiXmlElementWriter<AbstractInferredInteraction> inferredInteractionWriter) {
        this.inferredInteractionWriter = inferredInteractionWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExperimentWriter(){
        super.setExperimentWriter(new XmlExperimentWriter(getVersion(), getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseConfidenceWriter(){
        switch (getVersion()) {
            case v2_5_3:
                super.setConfidenceWriter(
                        new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlConfidenceWriter(
                                getStreamWriter(), getObjectIndex()));
                break;
            case v2_5_4:
            default:
                super.setConfidenceWriter(
                        new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlConfidenceWriter(
                                getStreamWriter(), getObjectIndex()));
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParameterWriter(){
        switch (getVersion()) {
            case v2_5_3:
                super.setParameterWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlParameterWriter(getStreamWriter(), getObjectIndex()));
                break;
            case v2_5_4:
            default:
                super.setParameterWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlParameterWriter(getStreamWriter(), getObjectIndex()));
                break;
        }
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
    public List<Experiment> extractDefaultExperimentsFrom(I interaction) {
        if (!interaction.getCooperativeEffects().isEmpty()){
            List<Experiment> expList = new ArrayList<Experiment>(interaction.getCooperativeEffects().size());
            CooperativeEffect effect = interaction.getCooperativeEffects().iterator().next();
            if (!effect.getCooperativityEvidences().isEmpty()){
                CooperativityEvidence evidence = effect.getCooperativityEvidences().iterator().next();
                // set first experiment as default experiment
                if (evidence.getPublication() != null){
                    Experiment exp = newExperiment(evidence.getPublication());
                    ((NamedExperiment)exp).setFullName(evidence.getPublication().getTitle());
                    expList.add(exp);
                }
            }
            return expList;
        }
        else{
            return Collections.singletonList(getDefaultExperiment());
        }
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

    /** {@inheritDoc} */
    @Override
    protected void writeInteractionType(I object) throws XMLStreamException {
        if (object instanceof ExtendedPsiXmlInteraction){
            ExtendedPsiXmlInteraction xmlInteraction = (ExtendedPsiXmlInteraction)object;
            if (!xmlInteraction.getInteractionTypes().isEmpty()){
                for (Object type : xmlInteraction.getInteractionTypes()){
                    getInteractionTypeWriter().write((CvTerm)type,"interactionType");
                }
            }
        }
        else{
            super.writeInteractionType(object);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperimentRef() throws XMLStreamException {
        getStreamWriter().writeStartElement("experimentList");
        for (Experiment experiment : getDefaultExperiments()){
            getStreamWriter().writeStartElement("experimentRef");
            getStreamWriter().writeCharacters(Integer.toString(getObjectIndex().extractIdForExperiment(experiment)));
            getStreamWriter().writeEndElement();
        }
        getStreamWriter().writeEndElement();
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperimentDescription() throws XMLStreamException {
        getStreamWriter().writeStartElement("experimentList");
        for (Experiment experiment : getDefaultExperiments()){
            getExperimentWriter().write(experiment);
        }
        getStreamWriter().writeEndElement();
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperiments(I object) throws XMLStreamException {
        // set default experiments
        if (!object.getCooperativeEffects().isEmpty()){
            List<Experiment> defExps = new ArrayList<Experiment>(object.getCooperativeEffects().size());
            CooperativeEffect effect = object.getCooperativeEffects().iterator().next();
            if (!effect.getCooperativityEvidences().isEmpty()){
                for (CooperativityEvidence evidence : effect.getCooperativityEvidences()){
                    // set first experiment as default experiment
                    if (evidence.getPublication() != null){
                        NamedExperiment exp = newExperiment(evidence.getPublication());
                        exp.setFullName(evidence.getPublication().getTitle());
                        defExps.add(exp);
                    }
                }
            }

            setDefaultExperiments(defExps);
        }
        return null;
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
        // nothing to write
    }

    /** {@inheritDoc} */
    @Override
    protected void writeStartInteraction() throws XMLStreamException {
        getStreamWriter().writeStartElement("interaction");
    }
}

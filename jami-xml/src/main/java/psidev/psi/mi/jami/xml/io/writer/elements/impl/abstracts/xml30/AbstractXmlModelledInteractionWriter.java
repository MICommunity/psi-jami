package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.InteractionUtils;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlCausalRelationshipWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlCvTermWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlDbXrefWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlOrganismWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.*;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Collection;
import java.util.Set;

/**
 * Abstract class for XML 3.0 writers of modelled interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public abstract class AbstractXmlModelledInteractionWriter<I extends ModelledInteraction>
        extends psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlModelledInteractionWriter<I> {

    private PsiXmlElementWriter<Set<Feature>> bindingFeaturesWriter;
    private PsiXmlElementWriter<Organism> organismWriter;
    private PsiXmlElementWriter<Preassembly> preAssemblyWriter;
    private PsiXmlElementWriter<Allostery> allosteryWriter;
    private PsiXmlCausalRelationshipWriter causalRelationshipWriter;

    /**
     * <p>Constructor for AbstractXmlModelledInteractionWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlModelledInteractionWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /**
     * <p>Getter for the field <code>causalRelationshipWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlCausalRelationshipWriter} object.
     */
    public PsiXmlCausalRelationshipWriter getCausalRelationshipWriter() {
        if (this.causalRelationshipWriter == null){
            initialiseCausalRelationshipWriter();
        }
        return causalRelationshipWriter;
    }

    /**
     * <p>initialiseCausalRelationshipWriter.</p>
     */
    protected void initialiseCausalRelationshipWriter() {
        this.causalRelationshipWriter = new XmlCausalRelationshipWriter(getStreamWriter(), getObjectIndex());
    }

    /**
     * <p>Setter for the field <code>causalRelationshipWriter</code>.</p>
     *
     * @param causalRelationshipWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlCausalRelationshipWriter} object.
     */
    public void setCausalRelationshipWriter(PsiXmlCausalRelationshipWriter causalRelationshipWriter) {
        this.causalRelationshipWriter = causalRelationshipWriter;
    }

    /**
     * <p>Getter for the field <code>preAssemblyWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Preassembly> getPreAssemblyWriter() {
        if (this.preAssemblyWriter == null){
            this.preAssemblyWriter = new XmlPreAssemblyWriter(getStreamWriter(), getObjectIndex());
        }
        return preAssemblyWriter;
    }

    /**
     * <p>Setter for the field <code>preAssemblyWriter</code>.</p>
     *
     * @param preAssemblyWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setPreAssemblyWriter(PsiXmlElementWriter<Preassembly> preAssemblyWriter) {
        this.preAssemblyWriter = preAssemblyWriter;
    }

    /**
     * <p>Getter for the field <code>allosteryWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Allostery> getAllosteryWriter() {
        if (this.allosteryWriter == null){
            this.allosteryWriter = new XmlAllosteryWriter(getStreamWriter(), getObjectIndex());
        }
        return allosteryWriter;
    }

    /**
     * <p>Setter for the field <code>allosteryWriter</code>.</p>
     *
     * @param allosteryWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setAllosteryWriter(PsiXmlElementWriter<Allostery> allosteryWriter) {
        this.allosteryWriter = allosteryWriter;
    }

    /**
     * <p>Getter for the field <code>bindingFeaturesWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Set<Feature>> getBindingFeaturesWriter() {
        if (this.bindingFeaturesWriter == null){
            this.bindingFeaturesWriter = new XmlBindingFeaturesWriter(getStreamWriter(), getObjectIndex());
        }
        return bindingFeaturesWriter;
    }

    /**
     * <p>Setter for the field <code>bindingFeaturesWriter</code>.</p>
     *
     * @param bindingFeaturesWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setBindingFeaturesWriter(PsiXmlElementWriter<Set<Feature>> bindingFeaturesWriter) {
        this.bindingFeaturesWriter = bindingFeaturesWriter;
    }

    /**
     * <p>Getter for the field <code>organismWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Organism> getOrganismWriter() {
        if (this.organismWriter == null){
            this.organismWriter = new XmlOrganismWriter(getStreamWriter());
        }
        return organismWriter;
    }

    /**
     * <p>Setter for the field <code>organismWriter</code>.</p>
     *
     * @param organismWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setOrganismWriter(PsiXmlElementWriter<Organism> organismWriter) {
        this.organismWriter = organismWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInferredInteractionWriter() {
        // nothing to do here
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefWriter(){
        super.setXrefWriter(new XmlDbXrefWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExperimentWriter(){
        super.setExperimentWriter(new XmlExperimentWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseConfidenceWriter(){
        super.setConfidenceWriter(new XmlModelledConfidenceWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParameterWriter(){
        super.setParameterWriter(new XmlModelledParameterWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    protected void writeCooperativeEffect(I object, boolean startAttributeList) throws XMLStreamException {
        // nothing to do here
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInteractionTypeWriter() {
        super.setInteractionTypeWriter(new XmlCvTermWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void writeInferredInteractions(I object) throws XMLStreamException {
        Collection<Set<Feature>> inferredInteractions = collectInferredInteractionsFrom(object);
        if (inferredInteractions != null && !inferredInteractions.isEmpty()){
            getStreamWriter().writeStartElement("bindingFeatureList");
            for (Set<Feature> inferred : inferredInteractions){
                getBindingFeaturesWriter().write(inferred);
            }
            getStreamWriter().writeEndElement();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherProperties(I object) throws XMLStreamException {
        if (object instanceof Complex) {
            Complex complex = (Complex) object;
            // write organism
            writeOrganism(complex);
            // write interactor type
            writeInteractorType(complex);
        }
        // write evidence type
        writeEvidenceType(object);
        // write cooperative effects
        writeCooperativeEffects(object);
        // write causal relationships
        writeCausalRelationships(object);
    }

    /**
     * <p>writeCausalRelationships.</p>
     *
     * @param object a I object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeCausalRelationships(I object) throws XMLStreamException {

        Collection<Participant> participants = InteractionUtils.extractParticipantWithCausalRelationships(object);
        if (!participants.isEmpty()){
            getStreamWriter().writeStartElement("causalRelationshipList");

            for (Participant p : participants){
                for (Object cr : p.getCausalRelationships()){
                    getCausalRelationshipWriter().write((CausalRelationship)cr, p);
                }
            }

            // end list
            getStreamWriter().writeEndElement();
        }
    }

    /**
     * <p>writeCooperativeEffects.</p>
     *
     * @param object a I object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeCooperativeEffects(I object) throws XMLStreamException {
         if (!object.getCooperativeEffects().isEmpty()){
             getStreamWriter().writeStartElement("cooperativeEffectList");
             for (CooperativeEffect effect : object.getCooperativeEffects()){
                 // preassembly
                 if (effect instanceof Preassembly){
                     getPreAssemblyWriter().write((Preassembly)effect);
                 }
                 // allsotery
                 else if (effect instanceof Allostery){
                     getAllosteryWriter().write((Allostery)effect);
                 }
                 // skip the effect
             }
             getStreamWriter().writeEndElement();
         }
    }

    /**
     * <p>writeEvidenceType.</p>
     *
     * @param object a I object.
     */
    protected void writeEvidenceType(I object){
        if (object.getEvidenceType() != null){
            getInteractionTypeWriter().write(object.getEvidenceType(), "evidenceType");
        }
    }

    /**
     * <p>writeInteractorType.</p>
     *
     * @param complex a {@link psidev.psi.mi.jami.model.Complex} object.
     */
    protected void writeInteractorType(Complex complex){
        if (complex.getInteractorType() != null){
            getInteractionTypeWriter().write(complex.getInteractorType(), "interactorType");
        }
    }

    /**
     * <p>writeOrganism.</p>
     *
     * @param complex a {@link psidev.psi.mi.jami.model.Complex} object.
     */
    protected void writeOrganism(Complex complex){
        if (complex.getOrganism() != null){
            getOrganismWriter().write(complex.getOrganism());
        }
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperimentRef() throws XMLStreamException {
        // nothing to do
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperimentDescription() throws XMLStreamException {
        // nothing to do
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperiments(I object) throws XMLStreamException {
        // nothing to write
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeStartInteraction() throws XMLStreamException {
        getStreamWriter().writeStartElement("abstractInteraction");
    }
}

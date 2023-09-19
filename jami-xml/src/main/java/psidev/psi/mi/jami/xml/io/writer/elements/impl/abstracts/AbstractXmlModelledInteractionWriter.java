package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultConfidence;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParameterWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlAliasWriter;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Abstract class for XML writers of modelled interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public abstract class AbstractXmlModelledInteractionWriter<I extends ModelledInteraction> extends AbstractXmlInteractionWriter<I, ModelledParticipant> {
    private PsiXmlElementWriter<Confidence> confidenceWriter;
    private PsiXmlParameterWriter parameterWriter;
    private PsiXmlElementWriter<Alias> aliasWriter;

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
     * <p>Getter for the field <code>confidenceWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Confidence> getConfidenceWriter() {
        if (this.confidenceWriter == null){
            initialiseConfidenceWriter();
        }
        return confidenceWriter;
    }

    /**
     * <p>initialiseConfidenceWriter.</p>
     */
    protected abstract void initialiseConfidenceWriter();

    /**
     * <p>Getter for the field <code>aliasWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Alias> getAliasWriter() {
        if (this.aliasWriter == null){
            this.aliasWriter =  new XmlAliasWriter(getStreamWriter());
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

    /**
     * <p>Setter for the field <code>confidenceWriter</code>.</p>
     *
     * @param confidenceWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setConfidenceWriter(PsiXmlElementWriter<Confidence> confidenceWriter) {
        this.confidenceWriter = confidenceWriter;
    }

    /**
     * <p>Getter for the field <code>parameterWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParameterWriter} object.
     */
    public PsiXmlParameterWriter getParameterWriter() {
        if (this.parameterWriter == null){
            initialiseParameterWriter();
        }
        return parameterWriter;
    }

    /**
     * <p>initialiseParameterWriter.</p>
     */
    protected abstract void initialiseParameterWriter();

    /**
     * <p>Setter for the field <code>parameterWriter</code>.</p>
     *
     * @param parameterWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParameterWriter} object.
     */
    public void setParameterWriter(PsiXmlParameterWriter parameterWriter) {
        this.parameterWriter = parameterWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultExperiment() {
        Experiment defaultExperiment = newExperiment(newPublication(
                "Mock publication and experiment for abstract interactions that are not interaction evidences.",
                null,
                null));
        setDefaultExperiment(defaultExperiment);
    }

    /** {@inheritDoc} */
    @Override
    public void setDefaultExperiment(Experiment defaultExperiment) {
        super.setDefaultExperiment(defaultExperiment);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeAvailability(I object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherAttributes(I object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeModelled(I object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParameters(I object) throws XMLStreamException {
        // write parameters
        if (!object.getModelledParameters().isEmpty()){
            // write start parameter list
            getStreamWriter().writeStartElement("parameterList");
            for (Object ann : object.getModelledParameters()){
                getParameterWriter().write((ModelledParameter)ann);
            }
            // write end parameterList
            getStreamWriter().writeEndElement();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeConfidences(I object, Double miScore) throws XMLStreamException {
        // write confidences
        if (!object.getModelledConfidences().isEmpty() || miScore != null) {
            // write start confidence list
            getStreamWriter().writeStartElement("confidenceList");
            if (miScore != null) {
                getConfidenceWriter().write(new DefaultConfidence(new DefaultCvTerm("intact-miscore"), Double.toString(miScore)));
            }
            for (Object ann : object.getModelledConfidences()){
                getConfidenceWriter().write((ModelledConfidence)ann);
            }
            // write end confidenceList
            getStreamWriter().writeEndElement();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeAttributes(I object) throws XMLStreamException {
        // write attributes
        if (!object.getAnnotations().isEmpty()){
            // write start attribute list
            getStreamWriter().writeStartElement("attributeList");
            // write existing attributes
            for (Object ann : object.getAnnotations()){
                getAttributeWriter().write((Annotation) ann);
            }
            for (Object c : object.getChecksums()){
                getChecksumWriter().write((Checksum)c);
            }
            // write cooperative effect
            // can only write the FIRST cooperative effect
            if (!object.getCooperativeEffects().isEmpty()){
                writeCooperativeEffect(object, false);
            }
            // write end attributeList
            getStreamWriter().writeEndElement();
        }
        // write checksum
        else if (!object.getChecksums().isEmpty()){
            // write start attribute list
            getStreamWriter().writeStartElement("attributeList");
            for (Object c : object.getChecksums()){
                getChecksumWriter().write((Checksum)c);
            }
            // can only write the FIRST cooperative effect
            if (!object.getCooperativeEffects().isEmpty()){
                writeCooperativeEffect(object, false);
            }
            // write end attributeList
            getStreamWriter().writeEndElement();
        }
        // write cooperative effects
        else if (!object.getCooperativeEffects().isEmpty()){
            // write cooperative effects
            writeCooperativeEffect(object, true);
        }
    }

    /**
     * <p>writeCooperativeEffect.</p>
     *
     * @param object a I object.
     * @param startAttributeList a boolean.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeCooperativeEffect(I object, boolean startAttributeList) throws XMLStreamException;

    /** {@inheritDoc} */
    @Override
    protected void writeNegative(I object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperiments(I object) throws XMLStreamException {
        // write experimental evidences
        if (!object.getCooperativeEffects().isEmpty()){
            CooperativeEffect effect = object.getCooperativeEffects().iterator().next();
            if (!effect.getCooperativityEvidences().isEmpty()){
                CooperativityEvidence evidence = effect.getCooperativityEvidences().iterator().next();
                // set first experiment as default experiment
                if (evidence.getPublication() != null){
                    NamedExperiment exp = newExperiment(evidence.getPublication());
                    exp.setFullName(evidence.getPublication().getTitle());
                    setDefaultExperiment(exp);
                }
            }
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeNames(I object) throws XMLStreamException {
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

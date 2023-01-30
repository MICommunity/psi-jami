package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParameterWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlAvailabilityWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Collection;
import java.util.Set;

/**
 * Abstract class for interaction evidence writers
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public abstract class AbstractXmlInteractionEvidenceWriter<I extends InteractionEvidence>
        extends AbstractXmlInteractionWriter<I,ParticipantEvidence> {
    private PsiXmlElementWriter<String> availabilityWriter;
    private PsiXmlElementWriter<Confidence> confidenceWriter;
    private PsiXmlParameterWriter parameterWriter;

    /**
     * <p>Constructor for AbstractXmlInteractionEvidenceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlInteractionEvidenceWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(version, writer, objectIndex);
    }

    /**
     * <p>Getter for the field <code>availabilityWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<String> getAvailabilityWriter() {
        if (this.availabilityWriter == null){
            initialiseAvaliabilityWriter();
        }
        return availabilityWriter;
    }

    /**
     * <p>initialiseAvaliabilityWriter.</p>
     */
    protected void initialiseAvaliabilityWriter() {
        this.availabilityWriter =  new XmlAvailabilityWriter(getStreamWriter(), getObjectIndex());
    }

    /**
     * <p>Setter for the field <code>availabilityWriter</code>.</p>
     *
     * @param availabilityWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setAvailabilityWriter(PsiXmlElementWriter<String> availabilityWriter) {
        this.availabilityWriter = availabilityWriter;
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
        Experiment exp = interaction.getExperiment();
        return exp != null ? exp : getDefaultExperiment() ;
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperiments(I object) throws XMLStreamException {
        if (object.getExperiment() != null){
            setDefaultExperiment(object.getExperiment());
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherAttributes(I object) throws XMLStreamException {
        // write IMEx id
        if (object.getImexId() != null){
            getStreamWriter().writeAttribute("imexId", object.getImexId());
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeModelled(I object) throws XMLStreamException {
        // we say we have a modelled = true if the interaction is inferred
        if (object.isInferred()){
            getStreamWriter().writeStartElement("modelled");
            getStreamWriter().writeCharacters(Boolean.toString(object.isInferred()));
            // write end intra molecular
            getStreamWriter().writeEndElement();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParameters(I object) throws XMLStreamException {
        // write parameters
        if (!object.getParameters().isEmpty()){
            // write start parameter list
            getStreamWriter().writeStartElement("parameterList");
            for (Object ann : object.getParameters()){
                getParameterWriter().write((Parameter)ann);
            }
            // write end parameterList
            getStreamWriter().writeEndElement();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeConfidences(I object) throws XMLStreamException {
        // write confidences
        if (!object.getConfidences().isEmpty()){
            // write start confidence list
            getStreamWriter().writeStartElement("confidenceList");
            for (Object ann : object.getConfidences()){
                getConfidenceWriter().write((Confidence)ann);
            }
            // write end confidenceList
            getStreamWriter().writeEndElement();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeNegative(I object) throws XMLStreamException {
        if (object.isNegative()){
            getStreamWriter().writeStartElement("negative");
            getStreamWriter().writeCharacters(Boolean.toString(object.isNegative()));
            // write end negative
            getStreamWriter().writeEndElement();
        }
    }

    /**
     * <p>writeAvailabilityRef.</p>
     *
     * @param availability a {@link java.lang.String} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeAvailabilityRef(String availability) throws XMLStreamException {
        getStreamWriter().writeStartElement("availabilityRef");
        getStreamWriter().writeCharacters(Integer.toString(getObjectIndex().extractIdForAvailability(availability)));
        getStreamWriter().writeEndElement();
    }

    /**
     * <p>writeAvailabilityDescription.</p>
     *
     * @param availability a {@link java.lang.String} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeAvailabilityDescription(String availability) throws XMLStreamException {
        getAvailabilityWriter().write(availability);
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
    @Override
    protected void writeStartInteraction() throws XMLStreamException {
        getStreamWriter().writeStartElement("interaction");
    }
}

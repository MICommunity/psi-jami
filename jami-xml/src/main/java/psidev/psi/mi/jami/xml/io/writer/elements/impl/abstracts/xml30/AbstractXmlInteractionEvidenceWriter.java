package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30;

import psidev.psi.mi.jami.model.CausalRelationship;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.VariableParameterValueSet;
import psidev.psi.mi.jami.utils.InteractionUtils;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlCausalRelationshipWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlConfidenceWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlCvTermWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlDbXrefWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlInferredInteractionWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlCausalRelationshipWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlExperimentWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlParameterWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlVariableParameterValueSetWriter;
import psidev.psi.mi.jami.xml.model.extension.xml300.BibRef;
import psidev.psi.mi.jami.xml.model.extension.xml300.DefaultXmlExperiment;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Collection;
import java.util.Date;

/**
 * Abstract class for interaction evidence writers in PSI-MI XML 3.0
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public abstract class AbstractXmlInteractionEvidenceWriter<I extends InteractionEvidence>
        extends psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlInteractionEvidenceWriter<I> {

    private PsiXmlElementWriter<VariableParameterValueSet> variableParameterValueSetWriter;
    private PsiXmlCausalRelationshipWriter causalRelationshipWriter;

    /**
     * <p>Constructor for AbstractXmlInteractionEvidenceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlInteractionEvidenceWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /**
     * <p>Getter for the field <code>variableParameterValueSetWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<VariableParameterValueSet> getVariableParameterValueSetWriter() {
        if (this.variableParameterValueSetWriter == null){
            this.variableParameterValueSetWriter = new XmlVariableParameterValueSetWriter(getStreamWriter(), getObjectIndex());
        }
        return variableParameterValueSetWriter;
    }

    /**
     * <p>Setter for the field <code>variableParameterValueSetWriter</code>.</p>
     *
     * @param variableParameterValueSetWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setVariableParameterValueSetWriter(PsiXmlElementWriter<VariableParameterValueSet> variableParameterValueSetWriter) {
        this.variableParameterValueSetWriter = variableParameterValueSetWriter;
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
    protected void writeOtherProperties(I object) throws XMLStreamException {
        // experimental variable values
        writeExperimentalVariableValues(object);        // write causal relationships
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
     * <p>writeExperimentalVariableValues.</p>
     *
     * @param object a I object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeExperimentalVariableValues(I object) throws XMLStreamException {
        if (!object.getVariableParameterValues().isEmpty()){
            getStreamWriter().writeStartElement("experimentalVariableValueList");

            for (VariableParameterValueSet set : object.getVariableParameterValues()){
                 getVariableParameterValueSetWriter().write(set);
            }

            // end list
            getStreamWriter().writeEndElement();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultExperiment() {
        setDefaultExperiment(new DefaultXmlExperiment(new BibRef("Mock publication for interactions that do not have experimental details.",(String)null,(Date)null)));
        getParameterWriter().setDefaultExperiment(getDefaultExperiment());
    }
}

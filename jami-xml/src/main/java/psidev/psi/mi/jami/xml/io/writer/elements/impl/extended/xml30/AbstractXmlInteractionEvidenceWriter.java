package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30;

import psidev.psi.mi.jami.model.CausalRelationship;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.VariableParameterValueSet;
import psidev.psi.mi.jami.utils.InteractionUtils;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlCausalRelationshipWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlVariableParameterValueSetWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Collection;

/**
 * Abstract class for interaction evidence writers that write expanded interactions (having modelled, intramolecular properties, list
 * of experiments, list of interaction types, etc.)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public abstract class AbstractXmlInteractionEvidenceWriter<I extends InteractionEvidence>
        extends psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.AbstractXmlInteractionEvidenceWriter<I> {

    private PsiXmlElementWriter<VariableParameterValueSet> variableParameterValueSetWriter;
    private PsiXmlCausalRelationshipWriter causalRelationshipWriter;

    /**
     * <p>Constructor for AbstractXmlInteractionEvidenceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlInteractionEvidenceWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(PsiXmlVersion.v3_0_0, writer, objectIndex);

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
    protected void initialiseParameterWriter() {
        super.setParameterWriter(new XmlParameterWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExperimentWriter(){
        super.setExperimentWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlExperimentWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherProperties(I object) throws XMLStreamException {
        // experimental variable values
        writeExperimentalVariableValues(object);
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
}

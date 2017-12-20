package psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30;

import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.VariableParameter;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlConfidenceWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlCvTermWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlDbXrefWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlHostOrganismWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlExperimentWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * PSI-XML 3.0.0 experiment writer
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public class XmlExperimentWriter extends AbstractXmlExperimentWriter {

    private PsiXmlElementWriter<VariableParameter> variableParameterWriter;

    /**
     * <p>Constructor for XmlExperimentWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlExperimentWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        super(writer, objectIndex);
    }

    /**
     * <p>Getter for the field <code>variableParameterWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<VariableParameter> getVariableParameterWriter() {
        if (this.variableParameterWriter == null){
            initialiseVariableParameterWriter();
        }
        return variableParameterWriter;
    }

    /**
     * <p>initialiseVariableParameterWriter.</p>
     */
    protected void initialiseVariableParameterWriter() {
        this.variableParameterWriter = new XmlVariableParameterWriter(getStreamWriter(), getObjectIndex());
    }

    /**
     * <p>Setter for the field <code>variableParameterWriter</code>.</p>
     *
     * @param variableParameterWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setVariableParameterWriter(PsiXmlElementWriter<VariableParameter> variableParameterWriter) {
        this.variableParameterWriter = variableParameterWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherAttributes(Experiment object, boolean needToWriteAttributeList) throws XMLStreamException {
        // does not write publication attributes as everything should be in bibref attribute
    }

    /** {@inheritDoc} */
    @Override
    protected void writeVariableParameters(Experiment object) throws XMLStreamException {
        if (!object.getVariableParameters().isEmpty()){
            // write parameter list
            getStreamWriter().writeStartElement("variableParameterList");
            for (VariableParameter param : object.getVariableParameters()){
                getVariableParameterWriter().write(param);
            }
            // write end variable parameter list
            getStreamWriter().writeEndElement();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentXrefs(Experiment object, String imexId) throws XMLStreamException {
        // write xrefs
        if (!object.getXrefs().isEmpty()){
            // write start xref
            getStreamWriter().writeStartElement("xref");
            if (!object.getXrefs().isEmpty()){
                writeXrefFromExperimentXrefs(object, imexId);
            }
            // does not write IMEx id as it should be in bibref

            // write end xref
            getStreamWriter().writeEndElement();
        }
    }

    /**
     * <p>initialisePublicationWriter.</p>
     */
    protected void initialisePublicationWriter() {
        super.setPublicationWriter(new XmlPublicationWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefWriter() {
        super.setXrefWriter(new XmlDbXrefWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseHostOrganismWriter() {
        super.setHostOrganismWriter(new XmlHostOrganismWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDetectionMethodWriter() {
        super.setDetectionMethodWriter(new XmlCvTermWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseConfidenceWriter() {
        super.setConfidenceWriter(new XmlConfidenceWriter(getStreamWriter()));
    }

}

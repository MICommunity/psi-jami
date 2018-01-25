package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.Parameter;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParameterWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Abstract class for feature evidence 3.0 writers
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public abstract class AbstractXmlFeatureEvidenceWriter extends AbstractXmlFeatureWriter<FeatureEvidence> {
    private PsiXmlParameterWriter parameterWriter;

    /**
     * <p>Constructor for AbstractXmlFeatureEvidenceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlFeatureEvidenceWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
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
    protected void writeOtherProperties(FeatureEvidence object) throws XMLStreamException {
        // write feature detection method
        writeFeatureDetectionMethod(object);
    }

    /**
     * <p>writeFeatureDetectionMethod.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.FeatureEvidence} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeFeatureDetectionMethod(FeatureEvidence object) throws XMLStreamException {
        for (CvTerm method : object.getDetectionMethods()){
            // write repeatable detection method
            getFeatureTypeWriter().write(method, "featureDetectionMethod");
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParameters(FeatureEvidence object) throws XMLStreamException {
        if (!object.getParameters().isEmpty()){
            getStreamWriter().writeStartElement("parameterList");
            for (Parameter param : object.getParameters()){
                getParameterWriter().write(param);
            }
            getStreamWriter().writeEndElement();
        }
    }
}

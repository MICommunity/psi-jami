package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml25;

import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Abstract class for feature evidence 2.5 writers
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class AbstractXmlFeatureEvidenceWriter extends AbstractXmlFeatureWriter<FeatureEvidence> {

    /**
     * <p>Constructor for AbstractXmlFeatureEvidenceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlFeatureEvidenceWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParameters(FeatureEvidence object) throws XMLStreamException{
        // nothing to write
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
        if (!object.getDetectionMethods().isEmpty()){
            // only write the first one
            getFeatureTypeWriter().write(object.getDetectionMethods().iterator().next(), "featureDetectionMethod");
        }
    }
}

package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlCvTermWriter;
import psidev.psi.mi.jami.xml.model.extension.xml254.ExperimentalCvTerm;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * XML writer for experimental cv terms
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public class XmlExperimentalCvTermWriter extends XmlCvTermWriter {
    private PsiXmlObjectCache objectIndex;

    /**
     * <p>Constructor for XmlExperimentalCvTermWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlExperimentalCvTermWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer);
        if (objectIndex == null){
            throw new IllegalArgumentException("The PsiXml 2.5 object index is mandatory for the XmlExperimentalCvTermWriter. It is necessary for generating an id to an experimentDescription");
        }
        this.objectIndex = objectIndex;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherProperties(CvTerm object) throws XMLStreamException {
        if (object instanceof ExperimentalCvTerm){
            ExperimentalCvTerm term = (ExperimentalCvTerm) object;
            if (!term.getExperiments().isEmpty()){
                getStreamWriter().writeStartElement("experimentRefList");
                for (Experiment exp : term.getExperiments()){
                    getStreamWriter().writeStartElement("experimentRef");
                    getStreamWriter().writeCharacters(Integer.toString(objectIndex.extractIdForExperiment(exp)));
                    getStreamWriter().writeEndElement();
                }
                getStreamWriter().writeEndElement();
            }
        }
    }
}

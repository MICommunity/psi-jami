package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30;

import psidev.psi.mi.jami.model.Parameter;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.model.extension.xml300.XmlParameter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * XML 3.0 writer of a parameter
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public class XmlParameterWriter extends psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlParameterWriter {

    /**
     * <p>Constructor for XmlParameterWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlParameterWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherProperties(Parameter object) throws XMLStreamException {
        // write experiment Ref
        if (object instanceof XmlParameter){
            XmlParameter xmlParameter = (XmlParameter)object;
            if (xmlParameter.getExperiment() != null){
                getStreamWriter().writeStartElement("experimentRef");
                getStreamWriter().writeCharacters(Integer.toString(getObjectIndex().extractIdForExperiment(xmlParameter.getExperiment())));
                getStreamWriter().writeEndElement();
            }
        }
    }
}

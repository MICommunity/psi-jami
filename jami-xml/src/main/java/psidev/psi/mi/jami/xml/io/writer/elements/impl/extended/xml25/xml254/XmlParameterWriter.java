package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254;

import psidev.psi.mi.jami.model.Parameter;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.model.extension.xml254.XmlParameter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * XML 2.5 writer of a parameter
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public class XmlParameterWriter extends psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlParameterWriter {

    /**
     * <p>Constructor for XmlParameterWriter.</p>
     *
     * @param writer a {@link XMLStreamWriter} object.
     * @param objectIndex a {@link PsiXmlObjectCache} object.
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
            else{
                super.writeOtherProperties(object);

            }
        }
        else{
            super.writeOtherProperties(object);
        }
    }
}

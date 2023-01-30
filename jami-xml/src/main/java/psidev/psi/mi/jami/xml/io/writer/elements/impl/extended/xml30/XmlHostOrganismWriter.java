package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30;

import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlOpenCvTermWriter;
import psidev.psi.mi.jami.xml.model.extension.xml300.HostOrganism;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * XML 2.5 writer for an expanded host organism having experiment references
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public class XmlHostOrganismWriter extends psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlHostOrganismWriter {
    private PsiXmlObjectCache objectIndex;

    /**
     * <p>Constructor for XmlHostOrganismWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlHostOrganismWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer);
        if (objectIndex == null){
            throw new IllegalArgumentException("The PsiXml 2.5 object index is mandatory for the XmlHostOrganismWriter. It is necessary for generating an id to an experimentDescription");
        }
        this.objectIndex = objectIndex;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherProperties(Organism object) throws XMLStreamException {
        if (object instanceof HostOrganism){
            HostOrganism extendedOrganism = (HostOrganism)object;
            // write experiment refs
            if (!extendedOrganism.getExperiments().isEmpty()){
                getStreamWriter().writeStartElement("experimentRefList");
                for (Experiment exp : extendedOrganism.getExperiments()){
                    getStreamWriter().writeStartElement("experimentRef");
                    getStreamWriter().writeCharacters(Integer.toString(this.objectIndex.extractIdForExperiment(exp)));
                    getStreamWriter().writeEndElement();
                }
                getStreamWriter().writeEndElement();
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseCvWriter() {
        super.setCvWriter(new XmlOpenCvTermWriter(getStreamWriter()));
    }
}

package psidev.psi.mi.jami.xml.io.writer.elements.impl;

import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlOrganismWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * PSI-XML writer for organism
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public class XmlOrganismWriter extends AbstractXmlOrganismWriter {
    /**
     * <p>Constructor for XmlOrganismWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    public XmlOrganismWriter(XMLStreamWriter writer) {
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseCvWriter() {
        super.setCvWriter(new XmlOpenCvTermWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherProperties(Organism object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeStartOrganism() throws XMLStreamException {
        getStreamWriter().writeStartElement("organism");
    }
}

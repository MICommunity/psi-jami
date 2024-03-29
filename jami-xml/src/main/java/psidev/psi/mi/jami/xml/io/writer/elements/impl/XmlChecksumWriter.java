package psidev.psi.mi.jami.xml.io.writer.elements.impl;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Checksum;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Xml writer of checksums
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public class XmlChecksumWriter implements PsiXmlElementWriter<Checksum> {
    private XMLStreamWriter streamWriter;

    /**
     * <p>Constructor for XmlChecksumWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    public XmlChecksumWriter(XMLStreamWriter writer){
        if (writer == null){
            throw new IllegalArgumentException("The XML stream writer is mandatory for the XmlChecksumWriter");
        }
        this.streamWriter = writer;
    }
    /** {@inheritDoc} */
    @Override
    public void write(Checksum object) throws MIIOException {
        if (object != null){
            try {
                // write start
                this.streamWriter.writeStartElement("attribute");
                // write topic
                CvTerm topic = object.getMethod();
                this.streamWriter.writeAttribute("name", topic.getShortName());
                if (topic.getMIIdentifier() != null){
                    this.streamWriter.writeAttribute("nameAc", topic.getMIIdentifier());
                }
                // write description
                this.streamWriter.writeCharacters(object.getValue());

                // write end attribute
                this.streamWriter.writeEndElement();

            } catch (XMLStreamException e) {
                throw new MIIOException("Impossible to write the checksum : "+object.toString(), e);
            }
        }
    }
}

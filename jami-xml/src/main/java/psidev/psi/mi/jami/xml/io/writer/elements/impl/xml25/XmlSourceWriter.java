package psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25;

import psidev.psi.mi.jami.xml.io.writer.elements.impl.AbstractXmlSourceWriter;

import javax.xml.stream.XMLStreamWriter;

/**
 * Writer of a source in an 2.5 entry.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/11/13</pre>
 */
public class XmlSourceWriter extends AbstractXmlSourceWriter {

    /**
     * <p>Constructor for XmlSourceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    public XmlSourceWriter(XMLStreamWriter writer){
        super(writer);
    }

    /**
     * <p>initialisePublicationWriter.</p>
     */
    protected void initialisePublicationWriter() {
        setPublicationWriter(new XmlPublicationWriter(getStreamWriter()));
    }
}
package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30;

import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlDbXrefWriter;

import javax.xml.stream.XMLStreamWriter;

/**
 * Xml 30 writer for resulting sequence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public class XmlResultingSequenceWriter extends psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlResultingSequenceWriter {

    /**
     * <p>Constructor for XmlResultingSequenceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    public XmlResultingSequenceWriter(XMLStreamWriter writer){
        super(writer);
    }

    /**
     * <p>initialiseXrefWriter.</p>
     */
    protected void initialiseXrefWriter() {
        super.setXrefWriter(new XmlDbXrefWriter(getStreamWriter()));
    }

}

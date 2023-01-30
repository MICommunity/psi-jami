package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25;

import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlBeginPositionWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlEndPositionWriter;

import javax.xml.stream.XMLStreamWriter;

/**
 * Xml 2.5 writer for a feature range
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class XmlRangeWriter extends psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlRangeWriter {

    /**
     * <p>Constructor for XmlRangeWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    public XmlRangeWriter(XMLStreamWriter writer){
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseStartPositionWriter() {
        super.setStartPositionWriter(new XmlBeginPositionWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseEndPositionWriter() {
        super.setEndPositionWriter(new XmlEndPositionWriter(getStreamWriter()));
    }
}

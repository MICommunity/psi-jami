package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended;

import psidev.psi.mi.jami.model.Position;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlPositionWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * XML writer for the begin position of a range
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class XmlBeginPositionWriter extends AbstractXmlPositionWriter {
    /**
     * <p>Constructor for XmlBeginPositionWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    public XmlBeginPositionWriter(XMLStreamWriter writer) {
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeStartPositionNode() throws XMLStreamException {
        getStreamWriter().writeStartElement("begin");
    }

    /** {@inheritDoc} */
    @Override
    protected void writeStartIntervalNode() throws XMLStreamException {
        getStreamWriter().writeStartElement("beginInterval");
    }

    /** {@inheritDoc} */
    @Override
    protected void writeStatus(Position object) {
        getStatusWriter().write(object.getStatus(),"startStatus");
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseStatusWriter() {
        super.setStatusWriter(new XmlCvTermWriter(getStreamWriter()));
    }
}

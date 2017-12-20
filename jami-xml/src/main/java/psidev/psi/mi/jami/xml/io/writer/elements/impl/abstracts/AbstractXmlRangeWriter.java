package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Position;
import psidev.psi.mi.jami.model.Range;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Abstract Xml writer for a feature range
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public abstract class AbstractXmlRangeWriter implements PsiXmlElementWriter<Range> {
    private XMLStreamWriter streamWriter;
    private PsiXmlElementWriter<Position> startPositionWriter;
    private PsiXmlElementWriter<Position> endPositionWriter;

    /**
     * <p>Constructor for AbstractXmlRangeWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    public AbstractXmlRangeWriter(XMLStreamWriter writer){
        if (writer == null){
            throw new IllegalArgumentException("The XML stream writer is mandatory for the XmlRangeWriter");
        }
        this.streamWriter = writer;
    }

    /**
     * <p>Getter for the field <code>startPositionWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Position> getStartPositionWriter() {
        if (this.startPositionWriter == null){
            initialiseStartPositionWriter();
        }
        return startPositionWriter;
    }

    /**
     * <p>initialiseStartPositionWriter.</p>
     */
    protected abstract void initialiseStartPositionWriter();

    /**
     * <p>Setter for the field <code>startPositionWriter</code>.</p>
     *
     * @param startPositionWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setStartPositionWriter(PsiXmlElementWriter<Position> startPositionWriter) {
        this.startPositionWriter = startPositionWriter;
    }

    /**
     * <p>Getter for the field <code>endPositionWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Position> getEndPositionWriter() {
        if (this.endPositionWriter == null){
            initialiseEndPositionWriter();

        }
        return endPositionWriter;
    }

    /**
     * <p>initialiseEndPositionWriter.</p>
     */
    protected abstract void initialiseEndPositionWriter();

    /**
     * <p>Setter for the field <code>endPositionWriter</code>.</p>
     *
     * @param endPositionWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setEndPositionWriter(PsiXmlElementWriter<Position> endPositionWriter) {
        this.endPositionWriter = endPositionWriter;
    }

    /** {@inheritDoc} */
    @Override
    public void write(Range object) throws MIIOException {
        if (object != null){
            try {
                // write start
                this.streamWriter.writeStartElement("featureRange");
                // write start position
                getStartPositionWriter().write(object.getStart());
                // write end position
                getEndPositionWriter().write(object.getEnd());
                // write isLink
                if (object.isLink()){
                    this.streamWriter.writeStartElement("isLink");
                    this.streamWriter.writeCharacters(Boolean.toString(object.isLink()));
                    this.streamWriter.writeEndElement();
                }

                // write additional information
                writeOtherProperties(object);

                // write end feature range
                this.streamWriter.writeEndElement();

            } catch (XMLStreamException e) {
                throw new MIIOException("Impossible to write the range : "+object.toString(), e);
            }
        }
    }

    /**
     * <p>writeOtherProperties.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Range} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeOtherProperties(Range object) throws XMLStreamException;

    /**
     * <p>Getter for the field <code>streamWriter</code>.</p>
     *
     * @return a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    protected XMLStreamWriter getStreamWriter() {
        return streamWriter;
    }
}

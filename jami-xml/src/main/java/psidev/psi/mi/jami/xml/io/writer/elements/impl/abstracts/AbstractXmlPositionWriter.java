package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Position;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Abstract Xml writer for a range position
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public abstract class AbstractXmlPositionWriter implements PsiXmlElementWriter<Position> {
    private XMLStreamWriter streamWriter;
    private PsiXmlVariableNameWriter<CvTerm> statusWriter;

    /**
     * <p>Constructor for AbstractXmlPositionWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    public AbstractXmlPositionWriter(XMLStreamWriter writer){
        if (writer == null){
            throw new IllegalArgumentException("The XML stream writer is mandatory for the AbstractXmlPositionWriter");
        }
        this.streamWriter = writer;
    }

    /** {@inheritDoc} */
    @Override
    public void write(Position object) throws MIIOException {
        try {
            // write status
            writeStatus(object);
            // write position
            if (!object.isPositionUndetermined()){
                if (object.getStart() == object.getEnd()){
                    // write start
                    writeStartPositionNode();
                    // write position attribute
                    this.streamWriter.writeAttribute("position", Long.toString(object.getStart()));
                    // write end
                    this.streamWriter.writeEndElement();
                }
                // write interval
                else{
                    // write start
                    writeStartIntervalNode();
                    // write start attribute
                    this.streamWriter.writeAttribute("begin", Long.toString(object.getStart()));
                    // write end attribute
                    this.streamWriter.writeAttribute("end", Long.toString(object.getEnd()));
                    // write end
                    this.streamWriter.writeEndElement();
                }
            }

        } catch (Exception e) {
            throw new MIIOException("Impossible to write the range position : "+object.toString(), e);
        }
    }

    /**
     * <p>writeStatus.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Position} object.
     */
    protected abstract void writeStatus(Position object);

    /**
     * <p>writeStartPositionNode.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeStartPositionNode() throws XMLStreamException;
    /**
     * <p>writeStartIntervalNode.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeStartIntervalNode() throws XMLStreamException;

    /**
     * <p>Getter for the field <code>streamWriter</code>.</p>
     *
     * @return a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    protected XMLStreamWriter getStreamWriter() {
        return streamWriter;
    }

    /**
     * <p>Getter for the field <code>statusWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public PsiXmlVariableNameWriter<CvTerm> getStatusWriter() {
        if (this.statusWriter == null){
            initialiseStatusWriter();
        }
        return statusWriter;
    }

    /**
     * <p>initialiseStatusWriter.</p>
     */
    protected abstract void initialiseStatusWriter();

    /**
     * <p>Setter for the field <code>statusWriter</code>.</p>
     *
     * @param statusWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public void setStatusWriter(PsiXmlVariableNameWriter<CvTerm> statusWriter) {
        this.statusWriter = statusWriter;
    }
}

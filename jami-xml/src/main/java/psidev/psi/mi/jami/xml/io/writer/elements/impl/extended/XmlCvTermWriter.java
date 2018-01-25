package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended;

import javax.xml.stream.XMLStreamWriter;

/**
 * CvTerm writers for cvs without attributes
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public class XmlCvTermWriter extends psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlCvTermWriter {

    /**
     * <p>Constructor for XmlCvTermWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    public XmlCvTermWriter(XMLStreamWriter writer){
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefWriter() {
        super.setXrefWriter(new XmlDbXrefWriter(getStreamWriter()));
    }
}

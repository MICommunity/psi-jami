package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30;

import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlBeginPositionWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlEndPositionWriter;

import javax.xml.stream.XMLStreamWriter;

/**
 * Xml 3.0 writer for a feature range
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class XmlRangeWriter extends psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlRangeWriter {

    /**
     * <p>Constructor for XmlRangeWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlRangeWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        super(writer, objectIndex);
    }

    /**
     * <p>initialiseResultingSequenceWriter.</p>
     */
    protected void initialiseResultingSequenceWriter() {
        super.setResultingSequenceWriter(new XmlResultingSequenceWriter(getStreamWriter()));
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

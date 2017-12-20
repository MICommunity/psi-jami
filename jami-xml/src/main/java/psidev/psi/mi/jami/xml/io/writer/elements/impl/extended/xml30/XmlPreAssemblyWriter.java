package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30;

import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlCvTermWriter;

import javax.xml.stream.XMLStreamWriter;

/**
 * Xml 30 writer for preassembly
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public class XmlPreAssemblyWriter extends psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlPreAssemblyWriter {

    /**
     * <p>Constructor for XmlPreAssemblyWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlPreAssemblyWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseCooperativityEvidenceWriter() {
        super.setCooperativityEvidenceWriter(new XmlCooperativityEvidenceWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseCvWriter() {
        super.setCvWriter(new XmlCvTermWriter(getStreamWriter()));
    }
}

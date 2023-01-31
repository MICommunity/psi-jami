package psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30;

import psidev.psi.mi.jami.model.Preassembly;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30.AbstractXmlCooperativeEffectWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Xml 3.0 writer for preassembly
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public class XmlPreAssemblyWriter extends AbstractXmlCooperativeEffectWriter<Preassembly> {

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
    protected void writeOtherProperties(Preassembly object) throws XMLStreamException {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeStartCooperativeEffect() throws XMLStreamException {
        getStreamWriter().writeStartElement("preassembly");
    }
}

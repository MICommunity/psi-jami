package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30;

import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlCvTermWriter;

import javax.xml.stream.XMLStreamWriter;

/**
 * XML 3.0 writer for variable parameter
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/11/13</pre>
 */
public class XmlVariableParameterWriter extends psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlVariableParameterWriter {

    /**
     * <p>Constructor for XmlVariableParameterWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlVariableParameterWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseUnitWriter() {
        super.setUnitWriter(new XmlCvTermWriter(getStreamWriter()));
    }
}

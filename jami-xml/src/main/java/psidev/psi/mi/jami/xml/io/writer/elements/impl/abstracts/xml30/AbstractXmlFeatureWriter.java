package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30;

import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlCvTermWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlDbXrefWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlRangeWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Abstract writer for Xml30Feature.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public abstract class AbstractXmlFeatureWriter<F extends Feature> extends psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlFeatureWriter<F> {

    /**
     * <p>Constructor for AbstractXmlFeatureWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlFeatureWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        super(writer, objectIndex);

    }

    /** {@inheritDoc} */
    @Override
    protected void writeParameters(F object) throws XMLStreamException {
        // nothing to write
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefWriter() {
        super.setXrefWriter(new XmlDbXrefWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseRangeWriter() {
         super.setRangeWriter(new XmlRangeWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFeatureTypeWriter() {
        super.setFeatureTypeWriter(new XmlCvTermWriter(getStreamWriter()));
    }

    /**
     * <p>writeFeatureRole.</p>
     *
     * @param object a F object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeFeatureRole(F object) throws XMLStreamException{
        if (object.getRole() != null){
            getFeatureTypeWriter().write(object.getRole(), "featureRole");
        }
    }

    /** {@inheritDoc} */
    protected void writeOtherAttributes(F object, boolean writeAttributeList) throws XMLStreamException{
        // nothing to write here
    }
}

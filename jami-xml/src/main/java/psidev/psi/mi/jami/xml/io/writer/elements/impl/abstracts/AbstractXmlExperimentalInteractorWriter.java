package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.model.extension.AbstractExperimentalInteractor;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Abstract class for experimental interactor XML 2.5 writer
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public abstract class AbstractXmlExperimentalInteractorWriter implements PsiXmlElementWriter<AbstractExperimentalInteractor> {

    private PsiXmlVersion version;
    private XMLStreamWriter streamWriter;
    private PsiXmlObjectCache objectIndex;

    /**
     * <p>Constructor for AbstractXmlExperimentalInteractorWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlExperimentalInteractorWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        this.version = version;
        if (writer == null){
            throw new IllegalArgumentException("The XML stream writer is mandatory for the AbstractXmlExperimentalInteractorWriter");
        }
        this.streamWriter = writer;
        if (objectIndex == null){
            throw new IllegalArgumentException("The PsiXml 2.5 object index is mandatory for the XmlInteractorWriter. It is necessary for generating an id to an experimentDescription");
        }
        this.objectIndex = objectIndex;
    }

    /** {@inheritDoc} */
    @Override
    public void write(AbstractExperimentalInteractor object) throws MIIOException {
        try {
            // write start
            this.streamWriter.writeStartElement("experimentalInteractor");
            // write interactor
            writeInteractor(object.getInteractor());
            // write experiment refs
            if (!object.getExperiments().isEmpty()){
                this.streamWriter.writeStartElement("experimentRefList");
                for (Experiment exp : object.getExperiments()){
                    this.streamWriter.writeStartElement("experimentRef");
                    this.streamWriter.writeCharacters(Integer.toString(this.objectIndex.extractIdForExperiment(exp)));
                    this.streamWriter.writeEndElement();
                }
                this.streamWriter.writeEndElement();
            }
            // write end experimental interactor
            this.streamWriter.writeEndElement();

        } catch (XMLStreamException e) {
            throw new MIIOException("Impossible to write the experimental interactor : "+object.toString(), e);
        }
    }

    /**
     * <p>writeInteractor.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeInteractor(Interactor interactor) throws XMLStreamException;

    /**
     * <p>Getter for the field <code>streamWriter</code>.</p>
     *
     * @return a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    protected XMLStreamWriter getStreamWriter() {
        return streamWriter;
    }

    /**
     * <p>Getter for the field <code>objectIndex</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    protected PsiXmlObjectCache getObjectIndex() {
        return objectIndex;
    }

    protected PsiXmlVersion getVersion() {
        return version;
    }
}

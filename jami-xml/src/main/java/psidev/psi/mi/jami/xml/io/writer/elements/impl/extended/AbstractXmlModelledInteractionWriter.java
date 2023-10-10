package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended;

import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExtendedInteractionWriter;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlInteraction;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class for XML writers of modelled interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public abstract class AbstractXmlModelledInteractionWriter<I extends ModelledInteraction>
        extends psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlModelledInteractionWriter<I>
        implements PsiXmlExtendedInteractionWriter<I> {

    private List<Experiment> defaultExperiments;

    /**
     * <p>Constructor for AbstractXmlModelledInteractionWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlModelledInteractionWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(version, writer, objectIndex);

    }

    /** {@inheritDoc} */
    @Override
    public List<Experiment> getDefaultExperiments() {
        if (this.defaultExperiments == null || this.defaultExperiments.isEmpty()){
            this.defaultExperiments = Collections.singletonList(getDefaultExperiment());
        }
        return this.defaultExperiments;
    }

    /** {@inheritDoc} */
    @Override
    public void setDefaultExperiments(List<Experiment> exp) {
        this.defaultExperiments = exp;
        if (this.defaultExperiments != null && !this.defaultExperiments.isEmpty()){
            getParameterWriter().setDefaultExperiment(this.defaultExperiments.iterator().next());
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefWriter(){
        super.setXrefWriter(new XmlDbXrefWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInferredInteractionWriter() {
        super.setInferredInteractionWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlInferredInteractionWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInteractionTypeWriter() {
        super.setInteractionTypeWriter(new XmlCvTermWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExperimentWriter(){
        switch (getVersion()) {
            case v3_0_0:
                super.setExperimentWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlExperimentWriter(getStreamWriter(), getObjectIndex()));
                break;
            case v2_5_3:
            case v2_5_4:
            default:
                super.setExperimentWriter(new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlExperimentWriter(getVersion(), getStreamWriter(), getObjectIndex()));
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeIntraMolecular(I object) throws XMLStreamException {
        if (object instanceof PsiXmlInteraction){
            PsiXmlInteraction xmlInteraction = (PsiXmlInteraction)object;
            if (xmlInteraction.isIntraMolecular()){
                getStreamWriter().writeStartElement("intraMolecular");
                getStreamWriter().writeCharacters(Boolean.toString(xmlInteraction.isIntraMolecular()));
                // write end intra molecular
                getStreamWriter().writeEndElement();
            }
        }
        else{
            super.writeIntraMolecular(object);
        }
    }
}

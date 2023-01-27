package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExtendedInteractionWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlAliasWriter;
import psidev.psi.mi.jami.xml.model.extension.AbstractInferredInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlInteraction;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class for interaction evidence writers that write expanded interactions (having modelled, intramolecular properties, list
 * of experiments, list of interaction types, etc.)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public abstract class AbstractXmlInteractionEvidenceWriter<I extends InteractionEvidence>
        extends psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlInteractionEvidenceWriter<I>
        implements PsiXmlExtendedInteractionWriter<I> {

    private PsiXmlElementWriter<AbstractInferredInteraction> inferredInteractionWriter;
    private PsiXmlElementWriter<Alias> aliasWriter;
    private List<Experiment> defaultExperiments;

    /**
     * <p>Constructor for AbstractXmlInteractionEvidenceWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlInteractionEvidenceWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);

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

    /**
     * <p>getXmlInferredInteractionWriter.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<AbstractInferredInteraction> getXmlInferredInteractionWriter() {
        if (this.inferredInteractionWriter == null){
            this.inferredInteractionWriter = new XmlInferredInteractionWriter(getStreamWriter(), getObjectIndex());
        }
        return inferredInteractionWriter;
    }

    /**
     * <p>setXmlInferredInteractionWriter.</p>
     *
     * @param inferredInteractionWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setXmlInferredInteractionWriter(PsiXmlElementWriter<AbstractInferredInteraction> inferredInteractionWriter) {
        this.inferredInteractionWriter = inferredInteractionWriter;
    }

    /**
     * <p>Getter for the field <code>aliasWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Alias> getAliasWriter() {
        if (this.aliasWriter == null){
            this.aliasWriter =  new XmlAliasWriter(getStreamWriter());
        }
        return aliasWriter;
    }

    /**
     * <p>Setter for the field <code>aliasWriter</code>.</p>
     *
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setAliasWriter(PsiXmlElementWriter<Alias> aliasWriter) {
        this.aliasWriter = aliasWriter;
    }

    /** {@inheritDoc} */
    @Override
    public List<Experiment> extractDefaultExperimentsFrom(I interaction) {
        if (interaction instanceof ExtendedPsiXmlInteractionEvidence){
            List<Experiment> exp = ((ExtendedPsiXmlInteractionEvidence)interaction).getExperiments();
            return !exp.isEmpty() ? exp : Collections.singletonList(getDefaultExperiment());
        }
        else{
            return Collections.singletonList(getDefaultExperiment());
        }
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
    protected void initialiseXrefWriter(){
        super.setXrefWriter(new XmlDbXrefWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void writeNames(I object) throws XMLStreamException {
        if (object instanceof NamedInteraction){
            NamedInteraction namedInteraction = (NamedInteraction) object;
            // write names
            PsiXmlUtils.writeCompleteNamesElement(namedInteraction.getShortName(),
                    namedInteraction.getFullName(), namedInteraction.getAliases(), getStreamWriter(),
                    getAliasWriter());
        }
        else{
            super.writeNames(object);
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

    /** {@inheritDoc} */
    @Override
    protected void writeModelled(I object) throws XMLStreamException {
        if (object instanceof ExtendedPsiXmlInteractionEvidence){
            ExtendedPsiXmlInteractionEvidence xmlInteraction = (ExtendedPsiXmlInteractionEvidence)object;
            if (xmlInteraction.isModelled()){
                getStreamWriter().writeStartElement("modelled");
                getStreamWriter().writeCharacters(Boolean.toString(xmlInteraction.isModelled()));
                // write end modelled
                getStreamWriter().writeEndElement();
            }
        }
        else{
            super.writeModelled(object);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeInteractionType(I object) throws XMLStreamException {
        if (object instanceof ExtendedPsiXmlInteraction){
            ExtendedPsiXmlInteraction xmlInteraction = (ExtendedPsiXmlInteraction)object;
            if (!xmlInteraction.getInteractionTypes().isEmpty()){
                for (Object type : xmlInteraction.getInteractionTypes()){
                    getInteractionTypeWriter().write((CvTerm)type,"interactionType");
                }
            }
        }
        else{
            super.writeInteractionType(object);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void writeInferredInteractions(I object) throws XMLStreamException {
        if (object instanceof ExtendedPsiXmlInteraction){
            ExtendedPsiXmlInteraction xmlInteraction = (ExtendedPsiXmlInteraction)object;
            if (!xmlInteraction.getInferredInteractions().isEmpty()){
                getStreamWriter().writeStartElement("inferredInteractionList");
                for (Object inferred : xmlInteraction.getInferredInteractions()){
                    getXmlInferredInteractionWriter().write((AbstractInferredInteraction)inferred);
                }
                getStreamWriter().writeEndElement();
            }
        }
        else{
            super.writeInferredInteractions(object);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperimentRef() throws XMLStreamException {
        getStreamWriter().writeStartElement("experimentList");
        for (Experiment experiment : getDefaultExperiments()){
            getStreamWriter().writeStartElement("experimentRef");
            getStreamWriter().writeCharacters(Integer.toString(getObjectIndex().extractIdForExperiment(experiment)));
            getStreamWriter().writeEndElement();
        }
        getStreamWriter().writeEndElement();
        return getDefaultExperiments().size() == 1 ?
                getExperimentWriter().extractDefaultParticipantIdentificationMethod(getDefaultExperiments().iterator().next()):null;
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperimentDescription() throws XMLStreamException {
        getStreamWriter().writeStartElement("experimentList");
        CvTerm firstMethod = null;
        for (Experiment experiment : getDefaultExperiments()){
            firstMethod = getExperimentWriter().writeExperiment(experiment);
        }
        getStreamWriter().writeEndElement();
        return getDefaultExperiments().size() == 1 ?
                firstMethod : null;
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperiments(I object) throws XMLStreamException {
        if (object instanceof ExtendedPsiXmlInteractionEvidence){
            ExtendedPsiXmlInteractionEvidence xmlInteraction = (ExtendedPsiXmlInteractionEvidence)object;
            // set default experimental evidences
            if (!xmlInteraction.getExperiments().isEmpty()){
                setDefaultExperiments(xmlInteraction.getExperiments());
            }
        }
        else {
            super.writeExperiments(object);
        }
        return null;
    }
}

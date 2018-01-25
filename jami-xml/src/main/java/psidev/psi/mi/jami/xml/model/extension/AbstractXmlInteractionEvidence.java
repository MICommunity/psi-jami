package psidev.psi.mi.jami.xml.model.extension;

import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.reference.AbstractAvailabilityRef;
import psidev.psi.mi.jami.xml.model.reference.AbstractExperimentRef;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Xml implementation of InteractionEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractXmlInteractionEvidence extends AbstractPsiXmlInteraction<ParticipantEvidence> implements ExtendedPsiXmlInteractionEvidence {

    private AbstractAvailability availability;
    private boolean isInferred;
    private boolean isNegative;
    private Collection<VariableParameterValueSet> variableParameterValueSets;
    private JAXBExperimentWrapper jaxbExperimentWrapper;

    private JAXBConfidenceWrapper jaxbConfidenceWrapper;
    private JAXBParameterWrapper jaxbParameterWrapper;

    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * <p>Constructor for AbstractXmlInteractionEvidence.</p>
     */
    public AbstractXmlInteractionEvidence() {
        super();
    }

    /**
     * <p>Constructor for AbstractXmlInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public AbstractXmlInteractionEvidence(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for AbstractXmlInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlInteractionEvidence(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(name = "names")
    public void setInteractionNamesContainer(NamesContainer value) {
        super.setInteractionNamesContainer(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(name = "xref")
    public void setInteractionXrefContainer(InteractionXrefContainer value) {
        super.setInteractionXrefContainer(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(name="attributeList")
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper jaxbAttributeWrapper) {
        super.setJAXBAttributeWrapper(jaxbAttributeWrapper);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(name="inferredInteractionList")
    public void setJAXBInferredInteractionWrapper(JAXBInferredInteractionWrapper jaxbInferredWrapper) {
        super.setJAXBInferredInteractionWrapper(jaxbInferredWrapper);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(name="interactionType", type = XmlCvTerm.class)
    public List<CvTerm> getInteractionTypes() {
        return super.getInteractionTypes();
    }

    /**
     * <p>getImexId.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getImexId() {
        String imexId = super.getImexId();
        // If the imexId is not defined in the attributes
        // of the interaction we try to retrieve from the xrefs
        if (imexId == null){
            return getInteractionXrefContainer() != null ? getInteractionXrefContainer().getImexId() : null;
        }
        return imexId;
    }

    /** {@inheritDoc} */
    public void assignImexId(String identifier) {
        if (getInteractionXrefContainer() == null && identifier != null){
            setInteractionXrefContainer(new InteractionXrefContainer());
        }
        getInteractionXrefContainer().assignImexId(identifier);
        if (getImexId() == null){
            setJAXBImexId(identifier);
        }
    }

    /**
     * <p>getExperiment.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public Experiment getExperiment() {
        if (this.jaxbExperimentWrapper == null || this.jaxbExperimentWrapper.experiments.isEmpty()){
            return null;
        }
        return this.jaxbExperimentWrapper.experiments.iterator().next();
    }

    /** {@inheritDoc} */
    public void setExperiment(Experiment experiment) {
        if (this.jaxbExperimentWrapper == null && experiment != null){
            this.jaxbExperimentWrapper = new JAXBExperimentWrapper();
            this.jaxbExperimentWrapper.experiments.add(experiment);
        }
        else if (experiment != null){
            if (!this.jaxbExperimentWrapper.experiments.isEmpty()){
                this.jaxbExperimentWrapper.experiments.remove(0);
            }
            this.jaxbExperimentWrapper.experiments.add(0, experiment);
        }
        else{
            if (!this.jaxbExperimentWrapper.experiments.isEmpty()){
                this.jaxbExperimentWrapper.experiments.remove(0);
            }
        }
    }

    /**
     * <p>getExperiments.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<Experiment> getExperiments() {
        if (jaxbExperimentWrapper == null){
            jaxbExperimentWrapper = new JAXBExperimentWrapper();
        }
        return jaxbExperimentWrapper.experiments;
    }

    /** {@inheritDoc} */
    public void setExperimentAndAddInteractionEvidence(Experiment experiment) {
        Experiment current = getExperiment();
        if (current != null){
            current.removeInteractionEvidence(this);
        }

        if (experiment != null){
            experiment.addInteractionEvidence(this);
        }
    }

    /**
     * <p>getVariableParameterValues.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<VariableParameterValueSet> getVariableParameterValues() {

        if (variableParameterValueSets == null){
            initialiseVariableParameterValueSets();
        }
        return this.variableParameterValueSets;
    }

    /**
     * <p>getConfidences.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Confidence> getConfidences() {
        if (this.jaxbConfidenceWrapper == null){
            initialiseConfidenceWrapper();
        }
        return this.jaxbConfidenceWrapper.confidences;
    }

    /**
     * <p>Getter for the field <code>availability</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAvailability() {
        return this.availability!= null ? this.availability.getValue() : null;
    }

    /** {@inheritDoc} */
    public void setAvailability(String availability) {
        if (this.availability == null && availability != null){
            this.availability = new Availability();
            this.availability.setValue(availability);
        }
        else if (availability != null){
            this.availability.setValue(availability);
        }
    }

    /**
     * <p>isNegative.</p>
     *
     * @return a boolean.
     */
    public boolean isNegative() {
        return this.isNegative;
    }

    /** {@inheritDoc} */
    public void setNegative(boolean negative) {
        this.isNegative = negative;
    }

    /**
     * <p>getParameters.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Parameter> getParameters() {
        if (jaxbParameterWrapper == null){
            initialiseParameterWrapper();
        }
        return this.jaxbParameterWrapper.parameters;
    }

    /**
     * <p>isInferred.</p>
     *
     * @return a boolean.
     */
    public boolean isInferred() {
        return this.isInferred;
    }

    /** {@inheritDoc} */
    public void setInferred(boolean inferred) {
        this.isInferred = inferred;
    }

    /**
     * <p>setJAXBIntraMolecular.</p>
     *
     * @param intra a {@link java.lang.Boolean} object.
     */
    @XmlElement(name = "intraMolecular", defaultValue = "false")
    public void setJAXBIntraMolecular(Boolean intra) {
        super.setIntraMolecular(intra);
    }

    /**
     * <p>setJAXBId.</p>
     *
     * @param value a int.
     */
    @XmlAttribute(name = "id", required = true)
    public void setJAXBId(int value) {
        super.setId(value);
    }

    /**
     * <p>setJAXBImexId.</p>
     *
     * @param identifier a {@link java.lang.String} object.
     */
    @XmlAttribute(name = "imexId")
    public void setJAXBImexId(String identifier) {
        super.assignImexId(identifier);
    }

    /**
     * <p>setJAXBParticipantWrapper.</p>
     *
     * @param jaxbParticipantWrapper a {@link psidev.psi.mi.jami.xml.model.extension.AbstractXmlInteractionEvidence.JAXBParticipantWrapper} object.
     */
    @XmlElement(name="participantList", required = true)
    public void setJAXBParticipantWrapper(JAXBParticipantWrapper jaxbParticipantWrapper) {
        super.setParticipantWrapper(jaxbParticipantWrapper);
    }

    /**
     * Gets the value of the confidenceList property.
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.AbstractXmlInteractionEvidence.JAXBConfidenceWrapper} object.
     */
    @XmlElement(name="confidenceList")
    public void setJAXBConfidenceWrapper(JAXBConfidenceWrapper wrapper) {
        this.jaxbConfidenceWrapper = wrapper;
    }

    /**
     * Gets the value of the parameterList property.
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.AbstractXmlInteractionEvidence.JAXBParameterWrapper} object.
     */
    @XmlElement(name="parameterList")
    public void setJAXBParameterWrapper(JAXBParameterWrapper wrapper) {
        this.jaxbParameterWrapper = wrapper;
    }

    /**
     * Sets the value of the availability property.
     *
     * @param value allowed object is {@link psidev.psi.mi.jami.xml.model.extension.Availability}
     */
    @XmlElement(name = "availability")
    public void setJAXBAvailability(Availability value) {
        this.availability = value;
    }

    /**
     * Sets the value of the availabilityRef property.
     *
     * @param value allowed object is {@link java.lang.Integer}
     */
    @XmlElement(name = "availabilityRef")
    public void setJAXBAvailabilityRef(Integer value) {
        if (value != null){
            this.availability = new AvailabilityRef(value);
        }
    }

    /**
     * Sets the value of the experimentList property.
     *
     * @param value allowed object is {@link JAXBExperimentWrapper}
     */
    @XmlElement(name="experimentList")
    public void setJAXBExperimentWrapper(JAXBExperimentWrapper value) {
        this.jaxbExperimentWrapper = value;
        // experiment list is set. Because we use back references, we need to post process.
        if (this.jaxbExperimentWrapper != null){
            // set the parent for future references
            this.jaxbExperimentWrapper.parent = this;
            // we don't have experiment refs, need to prepare each loaded experiment
            if (this.jaxbExperimentWrapper.jaxbExperimentRefList == null &&
                    this.jaxbExperimentWrapper.jaxbExperiments != null &&
                    !this.jaxbExperimentWrapper.jaxbExperiments.isEmpty()){
                for (Experiment exp : this.jaxbExperimentWrapper.jaxbExperiments){
                    jaxbExperimentWrapper.experiments.add(exp);
                    exp.getInteractionEvidences().add(this);
                }
            }

            if (this.jaxbExperimentWrapper.experiments.size() > 1 ){
                PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
                if (listener != null){
                    listener.onSeveralExperimentsFound(this.jaxbExperimentWrapper.experiments, this.jaxbExperimentWrapper.getSourceLocator());
                }
            }
        }
    }

    /**
     * Gets the value of the modelled property.
     *
     * @return a boolean.
     */
    public boolean isModelled() {
        return isInferred();
    }

    /** {@inheritDoc} */
    @Override
    public AbstractAvailability getXmlAvailability() {
        return this.availability;
    }

    /** {@inheritDoc} */
    @Override
    public void setXmlAvailability(AbstractAvailability availability) {
        this.availability = availability;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the value of the modelled property.
     */
    public void setModelled(boolean value) {
        setInferred(value);
    }

    /**
     * <p>setJAXBModelled.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    @XmlElement(name = "modelled", defaultValue = "false", type = Boolean.class)
    public void setJAXBModelled(Boolean value) {
        setModelled(value);
    }

    /**
     * Sets the value of the negative property.
     *
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean}
     */
    @XmlElement(name = "negative", defaultValue = "false", type = Boolean.class)
    public void setJAXBNegative(Boolean value) {
        if (value == null){
            this.isNegative = false;
        }
        else{
            this.isNegative = value;
        }
    }

    /**
     * <p>getJAXBConfidenceWrapper.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.AbstractXmlInteractionEvidence.JAXBConfidenceWrapper} object.
     */
    public JAXBConfidenceWrapper getJAXBConfidenceWrapper() {
        return jaxbConfidenceWrapper;
    }

    /**
     * <p>getJAXBParameterWrapper.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.AbstractXmlInteractionEvidence.JAXBParameterWrapper} object.
     */
    public JAXBParameterWrapper getJAXBParameterWrapper() {
        return jaxbParameterWrapper;
    }

    /**
     * <p>getJAXBExperimentWrapper.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.AbstractXmlInteractionEvidence.JAXBExperimentWrapper} object.
     */
    public JAXBExperimentWrapper getJAXBExperimentWrapper() {
        return jaxbExperimentWrapper;
    }

    /**
     * <p>getOriginalExperiments.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<ExtendedPsiXmlExperiment> getOriginalExperiments(){
        return jaxbExperimentWrapper != null ? jaxbExperimentWrapper.jaxbExperiments : Collections.EMPTY_LIST;
    }

    /**
     * <p>initialiseConfidenceWrapper.</p>
     */
    protected void initialiseConfidenceWrapper(){
        this.jaxbConfidenceWrapper = new JAXBConfidenceWrapper();
    }


    /**
     * <p>initialiseVariableParameterValueSets.</p>
     */
    protected void initialiseVariableParameterValueSets(){
        this.variableParameterValueSets = new ArrayList<VariableParameterValueSet>();
    }

    /**
     * <p>initialiseParameterWrapper.</p>
     */
    protected void initialiseParameterWrapper(){
        this.jaxbParameterWrapper = new JAXBParameterWrapper();
    }

    /** {@inheritDoc} */
    @Override
    public void processAddedParticipant(ParticipantEvidence participant) {
        ((XmlParticipantEvidence)participant).setOriginalXmlInteraction(this);
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        if (super.getSourceLocator() == null && locator != null){
            super.setSourceLocator(new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), getId()));
        }
        return super.getSourceLocator();
    }

    private FileSourceLocator getInteractionLocator(){
        return getSourceLocator();
    }
    private Locator getInteractionSaxLocator(){
        return sourceLocation();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantWrapper() {
        super.setParticipantWrapper(new JAXBParticipantWrapper());
    }

    ////////////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="participantEvidenceWrapper")
    public static class JAXBParticipantWrapper extends AbstractPsiXmlInteraction.JAXBParticipantWrapper<ParticipantEvidence> {

        public JAXBParticipantWrapper(){
            super();
        }

        @XmlElement(type=XmlParticipantEvidence.class, name="participant", required = true)
        public List<ParticipantEvidence> getJAXBParticipants() {
            return super.getJAXBParticipants();
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="experimentalConfidenceWrapper")
    public static class JAXBConfidenceWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Confidence> confidences;

        public JAXBConfidenceWrapper(){
            initialiseConfidences();
        }

        @Override
        public Locator sourceLocation() {
            return (Locator)getSourceLocator();
        }

        public FileSourceLocator getSourceLocator() {
            if (sourceLocator == null && locator != null){
                sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
            }
            return sourceLocator;
        }

        public void setSourceLocator(FileSourceLocator sourceLocator) {
            if (sourceLocator == null){
                this.sourceLocator = null;
            }
            else if (sourceLocator instanceof PsiXmlLocator){
                this.sourceLocator = (PsiXmlLocator)sourceLocator;
            }
            else {
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        @XmlElement(type=XmlConfidence.class, name="confidence", required = true)
        public List<Confidence> getJAXBConfidences() {
            return this.confidences;
        }

        protected void initialiseConfidences(){
            this.confidences = new ArrayList<Confidence>();
        }

        @Override
        public String toString() {
            return "Interaction Confidence List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="experimentalParameterWrapper")
    public static class JAXBParameterWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Parameter> parameters;

        public JAXBParameterWrapper(){
            initialiseParameters();
        }

        @Override
        public Locator sourceLocation() {
            return (Locator)getSourceLocator();
        }

        public FileSourceLocator getSourceLocator() {
            if (sourceLocator == null && locator != null){
                sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
            }
            return sourceLocator;
        }

        public void setSourceLocator(FileSourceLocator sourceLocator) {
            if (sourceLocator == null){
                this.sourceLocator = null;
            }
            else if (sourceLocator instanceof PsiXmlLocator){
                this.sourceLocator = (PsiXmlLocator)sourceLocator;
            }
            else {
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        @XmlElement(type=XmlParameter.class, name="parameter", required = true)
        public List<Parameter> getJAXBParameters() {
            return this.parameters;
        }

        protected void initialiseParameters(){
            this.parameters = new ArrayList<Parameter>();
        }

        @Override
        public String toString() {
            return "Interaction Parameter List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    private class AvailabilityRef extends AbstractAvailabilityRef{
        public AvailabilityRef(int ref) {
            super(ref);
        }

        public boolean resolve(PsiXmlIdCache parsedObjects) {
            if (parsedObjects.containsAvailability(this.ref)){
                AbstractAvailability av = parsedObjects.getAvailability(this.ref);
                if (av != null){
                   availability = av;
                    return true;
                }
            }
            return false;
        }

        @Override
        public FileSourceLocator getSourceLocator() {
            return getInteractionLocator();
        }

        @Override
        public void setSourceLocator(FileSourceLocator sourceLocator) {
            throw new UnsupportedOperationException("Cannot set the source locator to an availability ref");
        }

        @Override
        public Locator sourceLocation() {
            return getInteractionSaxLocator();
        }

        @Override
        public String toString() {
            return "Interaction Availability Reference: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name = "experimentListType")
    public static class JAXBExperimentWrapper implements Locatable, FileSourceContext{

        private List<ExtendedPsiXmlExperiment> jaxbExperiments;
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Experiment> experiments;
        private JAXBExperimentRefList jaxbExperimentRefList;
        private ExtendedPsiXmlInteractionEvidence parent;

        public JAXBExperimentWrapper(){
            this.experiments = new ArrayList<Experiment>();
        }

        @XmlElement(name="experimentDescription", required = true, type = XmlExperiment.class)
        public List<ExtendedPsiXmlExperiment> getJAXBExperimentDescriptions() {
            if (jaxbExperiments == null){
                jaxbExperiments = new ArrayList<ExtendedPsiXmlExperiment>();
            }
            return jaxbExperiments;
        }

        @XmlElement(name="experimentRef", required = true, type = Integer.class)
        public List<Integer> getJAXBExperimentRefs() {
            if (this.jaxbExperimentRefList == null){
                this.jaxbExperimentRefList = new JAXBExperimentRefList();
            }
            return jaxbExperimentRefList;
        }

        @Override
        public Locator sourceLocation() {
            return (Locator)getSourceLocator();
        }

        public FileSourceLocator getSourceLocator() {
            if (sourceLocator == null && locator != null){
                sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
            }
            return sourceLocator;
        }

        public void setSourceLocator(FileSourceLocator sourceLocator) {
            if (sourceLocator == null){
                this.sourceLocator = null;
            }
            else if (sourceLocator instanceof PsiXmlLocator){
                this.sourceLocator = (PsiXmlLocator)sourceLocator;
            }
            else {
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        @Override
        public String toString() {
            return "Interaction Experiment List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }

        ////////////////////////////////////////////////// Inner classes of ExperimentList

        /**
         * The experiment ref list used by JAXB to populate experiment refs
         */
        private class JAXBExperimentRefList extends ArrayList<Integer>{

            public JAXBExperimentRefList(){
                super();
                jaxbExperiments = new ArrayList<ExtendedPsiXmlExperiment>();
            }

            @Override
            public boolean add(Integer val) {
                if (val == null){
                    return false;
                }
                return experiments.add(new ExperimentRef(val));
            }

            @Override
            public boolean addAll(Collection<? extends Integer> c) {
                if (c == null){
                    return false;
                }
                boolean added = false;

                for (Integer a : c){
                    if (add(a)){
                        added = true;
                    }
                }
                return added;
            }

            @Override
            public void add(int index, Integer element) {
                addToSpecificIndex(index, element);
            }

            @Override
            public boolean addAll(int index, Collection<? extends Integer> c) {
                int newIndex = index;
                if (c == null){
                    return false;
                }
                boolean add = false;
                for (Integer a : c){
                    if (addToSpecificIndex(newIndex, a)){
                        newIndex++;
                        add = true;
                    }
                }
                return add;
            }

            private boolean addToSpecificIndex(int index, Integer val) {
                if (val == null){
                    return false;
                }
                experiments.add(index, new ExperimentRef(val));
                return true;
            }

            @Override
            public String toString() {
                return "Interaction Experiment Reference List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
            }

            /**
             * Experiment ref for experimental interactor
             */
            private class ExperimentRef extends AbstractExperimentRef{
                public ExperimentRef(int ref) {
                    super(ref);
                }

                public boolean resolve(PsiXmlIdCache parsedObjects) {
                    if (parsedObjects.containsExperiment(this.ref)){
                        Experiment obj = parsedObjects.getExperiment(this.ref);
                        if (obj == null){
                           return false;
                        }
                        else if (obj instanceof ExtendedPsiXmlExperiment){
                            ExtendedPsiXmlExperiment exp = (ExtendedPsiXmlExperiment)obj;
                            experiments.remove(this);
                            experiments.add(exp);
                            jaxbExperiments.add(exp);

                            exp.getInteractionEvidences().add(parent);
                            return true;
                        }
                        else {
                            experiments.remove(this);
                            experiments.add(obj);

                            obj.getInteractionEvidences().add(parent);
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public String toString() {
                    return "Interaction Experiment Reference: "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
                }

                public FileSourceLocator getSourceLocator() {
                    return sourceLocator;
                }

                public void setSourceLocator(FileSourceLocator locator) {
                    throw new UnsupportedOperationException("Cannot set the source locator of an experiment ref");
                }
            }
        }
    }
}

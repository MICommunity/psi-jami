package psidev.psi.mi.jami.xml.model.extension.xml300;

import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.extension.*;
import psidev.psi.mi.jami.xml.model.extension.XmlAnnotation;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Xml 3.0.0 implementation of ModelledInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlRootElement(name = "abstractInteraction", namespace = "http://psi.hupo.org/mi/mif300")
@XmlAccessorType(XmlAccessType.NONE)
public class XmlModelledInteraction extends AbstractXmlInteraction<ModelledParticipant> implements ExtendedPsiXmlModelledInteraction {

    private CvTerm interactionType;
    private Collection<InteractionEvidence> interactionEvidences;
    private Source source;
    private JAXBConfidenceWrapper jaxbConfidenceWrapper;
    private JAXBParameterWrapper jaxbParameterWrapper;
    private CvTerm evidenceType;
    private JAXBBindingFeaturesWrapper jaxbBindingFeaturesWrapper;
    private JAXBCooperativeEffectWrapper jaxbCooperativeEffectWrapper;
    private JAXBCausalRelationshipWrapper jaxbCausalRelationshipWrapper;
    private Organism organism;
    private CvTerm interactorType;

    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * <p>Constructor for XmlModelledInteraction.</p>
     */
    public XmlModelledInteraction() {
        super();
        if (getEntry() != null){
            this.source = getEntry().getSource();
        }
    }

    /**
     * <p>Constructor for XmlModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public XmlModelledInteraction(String shortName) {
        super(shortName);
        XmlEntryContext context = XmlEntryContext.getInstance();
        setEntry(context.getCurrentEntry());
        if (context.getCurrentEntry() != null){
            this.source = context.getCurrentEntry().getSource();
        }
    }

    /**
     * <p>Constructor for XmlModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledInteraction(String shortName, CvTerm type) {
        super(shortName, type);
        XmlEntryContext context = XmlEntryContext.getInstance();
        setEntry(context.getCurrentEntry());
        if (context.getCurrentEntry() != null){
            this.source = context.getCurrentEntry().getSource();
        }
    }

    /**
     * <p>initialiseInteractionEvidences.</p>
     */
    protected void initialiseInteractionEvidences(){
        this.interactionEvidences = new ArrayList<InteractionEvidence>();
    }

    /**
     * <p>initialiseCooperativeEffects.</p>
     */
    protected void initialiseCooperativeEffects(){
        this.jaxbCooperativeEffectWrapper = new JAXBCooperativeEffectWrapper();
    }

    /**
     * <p>initialiseModelledConfidenceWrapper.</p>
     */
    protected void initialiseModelledConfidenceWrapper(){
        this.jaxbConfidenceWrapper = new JAXBConfidenceWrapper();
    }

    /**
     * <p>initialiseModelledParameterWrapper.</p>
     */
    protected void initialiseModelledParameterWrapper(){
        this.jaxbParameterWrapper = new JAXBParameterWrapper();
    }

    /**
     * <p>Getter for the field <code>interactionEvidences</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<InteractionEvidence> getInteractionEvidences() {
        if (interactionEvidences == null){
            initialiseInteractionEvidences();
        }
        return this.interactionEvidences;
    }

    /**
     * <p>Getter for the field <code>source</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public Source getSource() {
        return this.source;
    }

    /** {@inheritDoc} */
    public void setSource(Source source) {
        this.source = source;
    }

    /**
     * <p>getModelledConfidences.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledConfidence> getModelledConfidences() {
        if (this.jaxbConfidenceWrapper == null){
            initialiseModelledConfidenceWrapper();
        }
        return this.jaxbConfidenceWrapper.confidences;
    }

    /**
     * <p>getModelledParameters.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledParameter> getModelledParameters() {
        if (jaxbParameterWrapper == null){
            initialiseModelledParameterWrapper();
        }
        return this.jaxbParameterWrapper.parameters;
    }

    /**
     * <p>getCooperativeEffects.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CooperativeEffect> getCooperativeEffects() {
        if (this.jaxbCooperativeEffectWrapper == null){
            initialiseCooperativeEffects();
        }
        return this.jaxbCooperativeEffectWrapper.cooperativeEffects;
    }

    /**
     * <p>getPhysicalProperties.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPhysicalProperties() {
        Annotation prop = getAttributeWrapper().physicalProperties;
        return prop != null ? prop.getValue() : null;
    }

    /** {@inheritDoc} */
    public void setPhysicalProperties(String properties) {
        JAXBAttributeWrapper.ComplexAnnotationList complexAnnotationList = (JAXBAttributeWrapper.ComplexAnnotationList)getAnnotations();
        Annotation propAnnot = getAttributeWrapper().physicalProperties;

        // add new properties if not null
        if (propAnnot != null){
            CvTerm propTopic = CvTermUtils.createMICvTerm(Annotation.COMPLEX_PROPERTIES, Annotation.COMPLEX_PROPERTIES_MI);
            // first remove properties if not null
            if (propAnnot != null){
                complexAnnotationList.removeOnly(propAnnot);
            }
            getAttributeWrapper().physicalProperties = new XmlAnnotation(propTopic, properties);
            complexAnnotationList.addOnly(getAttributeWrapper().physicalProperties);
        }
        // remove all url if the collection is not empty
        else if (!complexAnnotationList.isEmpty()) {
            AnnotationUtils.removeAllAnnotationsWithTopic(complexAnnotationList, Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
            getAttributeWrapper().physicalProperties = null;
        }
    }

    /**
     * <p>getRecommendedName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRecommendedName() {
        return getNamesContainer().getRecommendedName();
    }

    /** {@inheritDoc} */
    public void setRecommendedName(String name) {
        getNamesContainer().setRecommendedName(name);
    }

    /**
     * <p>getSystematicName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSystematicName() {
        return getNamesContainer().getSystematicName();
    }

    /** {@inheritDoc} */
    public void setSystematicName(String name) {
        getNamesContainer().setSystematicName(name);
    }

    /** {@inheritDoc} */
    @Override
    public Xref getPreferredIdentifier() {
        return !getIdentifiers().isEmpty() ? getIdentifiers().iterator().next() : null;
    }

    /** {@inheritDoc} */
    @Override
    public Organism getOrganism() {
        return this.organism;
    }

    /** {@inheritDoc} */
    @Override
    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    /**
     * <p>Getter for the field <code>interactorType</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getInteractorType() {
        if (this.interactorType == null){
            this.interactorType = new XmlCvTerm(Complex.COMPLEX, new XmlXref(CvTermUtils.createPsiMiDatabase(),Complex.COMPLEX_MI, CvTermUtils.createIdentityQualifier()));
        }

        return this.interactorType;
    }

    /** {@inheritDoc} */
    public void setInteractorType(CvTerm interactorType) {
        this.interactorType = interactorType;
    }

    /** {@inheritDoc} */
    @Override
    public List<Alias> getAliases() {
        return super.getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Checksum> getChecksums() {
        return super.getChecksums();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getXrefs() {
        return super.getXrefs();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getIdentifiers() {
        return super.getIdentifiers();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Annotation> getAnnotations() {
        return super.getAnnotations();
    }

    /**
     * <p>Getter for the field <code>evidenceType</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getEvidenceType() {
        return this.evidenceType;
    }

    /** {@inheritDoc} */
    public void setEvidenceType(CvTerm evidenceType) {
        this.evidenceType = evidenceType;
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getInteractionType() {
        return this.interactionType;
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractionType(CvTerm term) {
        this.interactionType = term;
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        if (super.getSourceLocator() == null && locator != null){
            super.setSourceLocator(new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), getId()));
        }
        return super.getSourceLocator();
    }

    /**
     * <p>getBindingFeatures.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BindingFeatures> getBindingFeatures() {
        if (jaxbBindingFeaturesWrapper == null){
            jaxbBindingFeaturesWrapper = new JAXBBindingFeaturesWrapper();
        }
        return jaxbBindingFeaturesWrapper.bindingFeatures;
    }

    /** {@inheritDoc} */
    @Override
    public List<ExtendedPsiXmlCausalRelationship> getCausalRelationships() {
        if (this.jaxbCausalRelationshipWrapper == null){
             this.jaxbCausalRelationshipWrapper = new JAXBCausalRelationshipWrapper();
        }
        return this.jaxbCausalRelationshipWrapper.causalRelationships;
    }

    /**
     * <p>setJAXBNames.</p>
     *
     * @param value a {@link psidev.psi.mi.jami.xml.model.extension.ComplexNamesContainer} object.
     */
    @XmlElement(name = "names")
    public void setJAXBNames(ComplexNamesContainer value) {
        super.setJAXBNames(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(name = "xref")
    public void setJAXBXref(InteractionXrefContainer value) {
        super.setJAXBXref(value);
    }

    /**
     * <p>setJAXBIntraMolecular.</p>
     *
     * @param intra a boolean.
     */
    @XmlElement(name = "intraMolecular", defaultValue = "false", type = Boolean.class)
    public void setJAXBIntraMolecular(boolean intra) {
        super.setIntraMolecular(intra);
    }

    /**
     * <p>setJAXBAttributeWrapper.</p>
     *
     * @param jaxbAttributeWrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlModelledInteraction.JAXBAttributeWrapper} object.
     */
    @XmlElement(name="attributeList")
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper jaxbAttributeWrapper) {
        super.setJAXBAttributeWrapper(jaxbAttributeWrapper);
    }

    /**
     * <p>setJAXBId.</p>
     *
     * @param value a int.
     */
    @XmlAttribute(name = "id", required = true)
    public void setJAXBId(int value) {
        super.setId(value);
        // register also as a complex
        XmlEntryContext.getInstance().registerComplex(super.getId(), this);
    }

    /**
     * <p>setJAXBParticipantWrapper.</p>
     *
     * @param jaxbParticipantWrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlModelledInteraction.JAXBParticipantWrapper} object.
     */
    @XmlElement(name="participantList", required = true)
    public void setJAXBParticipantWrapper(JAXBParticipantWrapper jaxbParticipantWrapper) {
        super.setParticipantWrapper(jaxbParticipantWrapper);
    }

    /**
     * <p>setJAXBConfidenceWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlModelledInteraction.JAXBConfidenceWrapper} object.
     */
    @XmlElement(name="confidenceList")
    public void setJAXBConfidenceWrapper(JAXBConfidenceWrapper wrapper) {
        this.jaxbConfidenceWrapper = wrapper;
    }

    /**
     * <p>setJAXBParameterWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlModelledInteraction.JAXBParameterWrapper} object.
     */
    @XmlElement(name="parameterList")
    public void setJAXBParameterWrapper(JAXBParameterWrapper wrapper) {
        this.jaxbParameterWrapper = wrapper;
    }

    /**
     * <p>setJAXBOrganism.</p>
     *
     * @param organism a {@link psidev.psi.mi.jami.xml.model.extension.XmlOrganism} object.
     */
    @XmlElement(name="organism")
    public void setJAXBOrganism(XmlOrganism organism) {
        this.organism = organism;
    }

    /**
     * <p>setJAXBInteractorType.</p>
     *
     * @param interactorType a {@link psidev.psi.mi.jami.xml.model.extension.XmlCvTerm} object.
     */
    @XmlElement(name = "interactorType")
    public void setJAXBInteractorType(XmlCvTerm interactorType) {
        this.interactorType = interactorType;
    }

    /**
     * <p>setJAXBInteractionType.</p>
     *
     * @param term a {@link psidev.psi.mi.jami.xml.model.extension.XmlCvTerm} object.
     */
    @XmlElement(name="interactionType")
    public void setJAXBInteractionType(XmlCvTerm term) {
        setInteractionType(term);
    }

    /**
     * <p>setJAXBBindingFeaturesWrapper.</p>
     *
     * @param jaxbInferredWrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlModelledInteraction.JAXBBindingFeaturesWrapper} object.
     */
    @XmlElement(name="bindingFeatureList")
    public void setJAXBBindingFeaturesWrapper(JAXBBindingFeaturesWrapper jaxbInferredWrapper) {
        this.jaxbBindingFeaturesWrapper = jaxbInferredWrapper;
    }

    /**
     * <p>setJAXBCooperativeEffectWrapper.</p>
     *
     * @param jaxbEffectWrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlModelledInteraction.JAXBCooperativeEffectWrapper} object.
     */
    @XmlElement(name="cooperativeEffectList")
    public void setJAXBCooperativeEffectWrapper(JAXBCooperativeEffectWrapper jaxbEffectWrapper) {
        this.jaxbCooperativeEffectWrapper = jaxbEffectWrapper;
    }

    /**
     * <p>setJAXBCausalRelationshipWrapper.</p>
     *
     * @param jaxbCausalRelationshipWrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlModelledInteraction.JAXBCausalRelationshipWrapper} object.
     */
    @XmlElement(name="causalRelationshipList")
    public void setJAXBCausalRelationshipWrapper(JAXBCausalRelationshipWrapper jaxbCausalRelationshipWrapper) {
        this.jaxbCausalRelationshipWrapper = jaxbCausalRelationshipWrapper;
    }

    /**
     * <p>setJAXBEvidenceType.</p>
     *
     * @param evidenceType a {@link psidev.psi.mi.jami.xml.model.extension.XmlCvTerm} object.
     */
    @XmlElement(name = "evidenceType")
    public void setJAXBEvidenceType(XmlCvTerm evidenceType) {
        this.evidenceType = evidenceType;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantWrapper() {
        super.setParticipantWrapper(new JAXBParticipantWrapper());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseNamesContainer() {
        super.setJAXBNames(new ComplexNamesContainer());
    }

    /** {@inheritDoc} */
    @Override
    protected ComplexNamesContainer getNamesContainer() {
        return (ComplexNamesContainer) super.getNamesContainer();
    }

    /**
     * <p>getAttributeWrapper.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlModelledInteraction.JAXBAttributeWrapper} object.
     */
    protected JAXBAttributeWrapper getAttributeWrapper() {
        return (JAXBAttributeWrapper) super.getAttributeWrapper();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseAnnotationWrapper() {
        super.setJAXBAttributeWrapper(new JAXBAttributeWrapper());
    }

    ////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="modelledParticipantWrapper")
    public static class JAXBParticipantWrapper extends AbstractPsiXmlInteraction.JAXBParticipantWrapper<ModelledParticipant> {

        public JAXBParticipantWrapper(){
            super();
        }

        @XmlElement(type=XmlModelledParticipant.class, name="participant", required = true)
        public List<ModelledParticipant> getJAXBParticipants() {
            return super.getJAXBParticipants();
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="modelledConfidenceWrapper30")
    public static class JAXBConfidenceWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<ModelledConfidence> confidences;

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
            else{
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        @XmlElement(type=XmlModelledConfidence.class, name="confidence", required = true)
        public List<ModelledConfidence> getJAXBConfidences() {
            return this.confidences;
        }

        protected void initialiseConfidences(){
            this.confidences = new ArrayList<ModelledConfidence>();
        }

        @Override
        public String toString() {
            return "Interaction Confidence List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="modelledParameterWrapper")
    public static class JAXBParameterWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<ModelledParameter> parameters;

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
            else{
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        @XmlElement(type=XmlModelledParameter.class, name="parameter", required = true)
        public List<ModelledParameter> getJAXBParameters() {
            return this.parameters;
        }

        protected void initialiseParameters(){
            this.parameters = new ArrayList<ModelledParameter>();
        }

        @Override
        public String toString() {
            return "Interaction Parameter List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="cooperativeEffectWrapper")
    public static class JAXBCooperativeEffectWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<CooperativeEffect> cooperativeEffects;

        public JAXBCooperativeEffectWrapper(){
            initialiseCooperativeEffects();
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
            else{
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        @XmlElements({
                @XmlElement(name = "preassembly", type = XmlPreAssembly.class),
                @XmlElement(name = "allostery", type = XmlAllostery.class)
        })
        public List<CooperativeEffect> getJAXBCooperativeEffects() {
            return this.cooperativeEffects;
        }

        protected void initialiseCooperativeEffects(){
            this.cooperativeEffects = new ArrayList<CooperativeEffect>();
        }

        @Override
        public String toString() {
            return "Interaction cooperative effect List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="modelledCausalRelationshipWrapper")
    public static class JAXBCausalRelationshipWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<ExtendedPsiXmlCausalRelationship> causalRelationships;

        public JAXBCausalRelationshipWrapper(){
            initialiseCausalRelationships();
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
            else{
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        @XmlElement(name = "causalRelationship", type = XmlCausalRelationship.class, required = true)
        public List<ExtendedPsiXmlCausalRelationship> getJAXBCausalRelationships() {
            return this.causalRelationships;
        }

        protected void initialiseCausalRelationships(){
            this.causalRelationships = new ArrayList<ExtendedPsiXmlCausalRelationship>();
        }

        @Override
        public String toString() {
            return "CausalRelationship List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="bindingFeaturesWrapper")
    public static class JAXBBindingFeaturesWrapper implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<BindingFeatures> bindingFeatures;

        public JAXBBindingFeaturesWrapper(){
            initialiseBindingFeatures();
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
            else{
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        protected void initialiseBindingFeatures(){
            bindingFeatures = new ArrayList<BindingFeatures>();
        }

        @XmlElement(name="bindingFeatures", required = true)
        public List<BindingFeatures> getJAXBBindingFeatures() {
            return bindingFeatures;
        }

        @Override
        public String toString() {
            return "Binding features List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="complexAttributeWrapper")
    public static class JAXBAttributeWrapper extends AbstractXmlInteraction.JAXBAttributeWrapper{
        private Annotation physicalProperties;

        public JAXBAttributeWrapper(){
            super();
        }

        @Override
        protected void initialiseAnnotations() {
            super.initialiseAnnotationsWith(new ComplexAnnotationList());
        }

        private void processAddedAnnotationEvent(Annotation added) {
            if (physicalProperties == null && AnnotationUtils.doesAnnotationHaveTopic(added, Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES)){
                physicalProperties = added;
            }
        }

        private void processRemovedAnnotationEvent(Annotation removed) {
            if (physicalProperties != null && physicalProperties.equals(removed)){
                physicalProperties = AnnotationUtils.collectFirstAnnotationWithTopic(getAnnotations(), Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
            }
        }

        private void clearPropertiesLinkedToAnnotations() {
            physicalProperties = null;
        }

        private class ComplexAnnotationList extends AbstractListHavingProperties<Annotation> {
            public ComplexAnnotationList(){
                super();
            }

            @Override
            protected void processAddedObjectEvent(Annotation added) {
                processAddedAnnotationEvent(added);
            }

            @Override
            protected void processRemovedObjectEvent(Annotation removed) {
                processRemovedAnnotationEvent(removed);
            }

            @Override
            protected void clearProperties() {
                clearPropertiesLinkedToAnnotations();
            }
        }

        @Override
        public String toString() {
            return "Com-lex Attribute List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}

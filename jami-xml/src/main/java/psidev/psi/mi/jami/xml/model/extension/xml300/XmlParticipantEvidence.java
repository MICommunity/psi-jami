package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Confidence;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.ExperimentalParticipantCandidate;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Parameter;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.Stoichiometry;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.extension.AbstractExperimentalInteractor;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlExperiment;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipantEvidence;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.extension.XmlStoichiometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Xml implementation of ParticipantEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlParticipantEvidence extends AbstractXmlParticipant<InteractionEvidence, FeatureEvidence> implements
        ExtendedPsiXmlParticipantEvidence {

    @XmlLocation
    @XmlTransient
    private Locator locator;
    private boolean initialisedMethods = false;
    private ExtendedPsiXmlInteractionEvidence originalInteraction;
    private JAXBParticipantIdentificationWrapper jaxbParticipantionIdentificationWrapper;
    private JAXBExperimentalPreparationWrapper jaxbExperimentalPreparationWrapper;
    private JAXBExperimentalRoleWrapper jaxbExperimentalRoleWrapper;
    private JAXBExperimentalInteractorWrapper jaxbExperimentalInteractorWrapper;
    private JAXBHostOrganismWrapper jaxbHostOrganismWrapper;
    private JAXBConfidenceWrapper jaxbConfidenceWrapper;
    private JAXBParameterWrapper jaxbParameterWrapper;
    private List<ExperimentalCvTerm> originalIdentificationMethods;

    /**
     * <p>Constructor for XmlParticipantEvidence.</p>
     */
    public XmlParticipantEvidence() {
    }

    /**
     * <p>Constructor for XmlParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public XmlParticipantEvidence(Interactor interactor) {
        super(interactor);
    }

    /**
     * <p>Constructor for XmlParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlParticipantEvidence(Interactor interactor, CvTerm bioRole) {
        super(interactor, bioRole);
    }

    /**
     * <p>Constructor for XmlParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public XmlParticipantEvidence(Interactor interactor, Stoichiometry stoichiometry) {
        super(interactor, stoichiometry);
    }

    /**
     * <p>Constructor for XmlParticipantEvidence.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public XmlParticipantEvidence(Interactor interactor, CvTerm bioRole, Stoichiometry stoichiometry) {
        super(interactor, bioRole, stoichiometry);
    }

    /**
     * <p>initialiseExperimentalPreparationWrapper.</p>
     */
    protected void initialiseExperimentalPreparationWrapper() {
        this.jaxbExperimentalPreparationWrapper = new JAXBExperimentalPreparationWrapper();
    }

    /**
     * <p>initialiseExperimentalRoleWrapper.</p>
     */
    protected void initialiseExperimentalRoleWrapper() {
        this.jaxbExperimentalRoleWrapper = new JAXBExperimentalRoleWrapper();
    }

    /**
     * <p>initialiseConfidenceWrapper.</p>
     */
    protected void initialiseConfidenceWrapper() {
        this.jaxbConfidenceWrapper = new JAXBConfidenceWrapper();
    }

    /**
     * <p>initialiseParameterWrapper.</p>
     */
    protected void initialiseParameterWrapper() {
        this.jaxbParameterWrapper = new JAXBParameterWrapper();
    }

    /**
     * <p>initialiseIdentificationMethodWrapper.</p>
     */
    protected void initialiseIdentificationMethodWrapper(){
        Collection<Experiment> expToIgnore = Collections.EMPTY_LIST;

        if (this.jaxbParticipantionIdentificationWrapper == null){
            this.jaxbParticipantionIdentificationWrapper = new JAXBParticipantIdentificationWrapper();
        }

        if (originalInteraction != null){

            List<ExtendedPsiXmlExperiment> originalExperiments = originalInteraction.getOriginalExperiments();
            if (originalExperiments != null && !originalExperiments.isEmpty()){
                // participant identification method overrides the one in the experiment
                if (originalIdentificationMethods != null && !originalIdentificationMethods.isEmpty()
                        && originalExperiments.size() == 1){
                    this.originalIdentificationMethods = null;
                }
                else{
                    if (originalIdentificationMethods != null){
                        expToIgnore = new ArrayList<Experiment>(originalIdentificationMethods.size());
                        for (ExperimentalCvTerm part : this.originalIdentificationMethods){
                            if (!part.getExperiments().isEmpty()){
                                expToIgnore.addAll(part.getExperiments());
                            }
                        }
                        this.originalIdentificationMethods = null;
                    }

                    for (ExtendedPsiXmlExperiment exp : originalExperiments){
                        if (exp.getParticipantIdentificationMethod() != null && !expToIgnore.contains(exp)){
                            this.jaxbParticipantionIdentificationWrapper.identificationMethods.add(exp.getParticipantIdentificationMethod());
                        }
                    }
                }
            }
        }

        initialisedMethods = true;
    }

    /**
     * <p>initialiseHostOrganismWrapper.</p>
     */
    protected void initialiseHostOrganismWrapper() {
        this.jaxbHostOrganismWrapper = new JAXBHostOrganismWrapper();
    }

    /**
     * <p>initialiseExperimentalInteractorWrapper.</p>
     */
    protected void initialiseExperimentalInteractorWrapper() {
        this.jaxbExperimentalInteractorWrapper = new JAXBExperimentalInteractorWrapper();
    }

    /**
     * <p>getExperimentalRole.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getExperimentalRole() {
        if (this.jaxbExperimentalRoleWrapper == null){
            initialiseExperimentalRoleWrapper();
        }
        if (this.jaxbExperimentalRoleWrapper.experimentalRoles.isEmpty()){
            this.jaxbExperimentalRoleWrapper.experimentalRoles.add(0, new ExperimentalCvTerm(Participant.UNSPECIFIED_ROLE, new XmlXref(CvTermUtils.createPsiMiDatabase(), Participant.UNSPECIFIED_ROLE_MI, CvTermUtils.createIdentityQualifier())));
        }
        return this.jaxbExperimentalRoleWrapper.experimentalRoles.get(0);
    }

    /** {@inheritDoc} */
    public void setExperimentalRole(CvTerm expRole) {
        if (this.jaxbExperimentalRoleWrapper == null && expRole != null){
            initialiseExperimentalRoleWrapper();
            this.jaxbExperimentalRoleWrapper.experimentalRoles.add(0, expRole);
        }
        else if (expRole != null){
            if (!this.jaxbExperimentalRoleWrapper.experimentalRoles.isEmpty()){
                this.jaxbExperimentalRoleWrapper.experimentalRoles.remove(0);
            }
            this.jaxbExperimentalRoleWrapper.experimentalRoles.add(0, expRole);
        }
        else{
            if (!this.jaxbExperimentalRoleWrapper.experimentalRoles.isEmpty()){
                this.jaxbExperimentalRoleWrapper.experimentalRoles.remove(0);
            }
        }
    }

    /**
     * <p>getIdentificationMethods.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CvTerm> getIdentificationMethods() {
        if (!initialisedMethods){
            initialiseIdentificationMethodWrapper();
        }
        return this.jaxbParticipantionIdentificationWrapper.identificationMethods;
    }

    /**
     * <p>getExperimentalPreparations.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CvTerm> getExperimentalPreparations() {
        if (jaxbExperimentalPreparationWrapper == null){
            initialiseExperimentalPreparationWrapper();
        }
        return this.jaxbExperimentalPreparationWrapper.experimentalPreparations;
    }

    /**
     * <p>getExpressedInOrganism.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public Organism getExpressedInOrganism() {
        return (this.jaxbHostOrganismWrapper != null && !this.jaxbHostOrganismWrapper.hostOrganisms.isEmpty())? this.jaxbHostOrganismWrapper.hostOrganisms.iterator().next() : null;
    }

    /** {@inheritDoc} */
    public void setExpressedInOrganism(Organism organism) {
        if (this.jaxbHostOrganismWrapper == null && organism != null){
            initialiseHostOrganismWrapper();
            this.jaxbHostOrganismWrapper.hostOrganisms.add(organism);
        }
        else if (organism != null){
            if (!this.jaxbHostOrganismWrapper.hostOrganisms.isEmpty()){
                this.jaxbHostOrganismWrapper.hostOrganisms.remove(0);
            }
            this.jaxbHostOrganismWrapper.hostOrganisms.add(0, organism);
        }
        else{
            if (!this.jaxbHostOrganismWrapper.hostOrganisms.isEmpty()){
                this.jaxbHostOrganismWrapper.hostOrganisms.remove(0);
            }
        }
    }

    /**
     * <p>getConfidences.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Confidence> getConfidences() {
        if (jaxbConfidenceWrapper == null){
            initialiseConfidenceWrapper();
        }
        return this.jaxbConfidenceWrapper.confidences;
    }

    /**
     * <p>getParameters.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Parameter> getParameters() {
        if (this.jaxbParameterWrapper == null){
            initialiseParameterWrapper();
        }
        return this.jaxbParameterWrapper.parameters;
    }

    /** {@inheritDoc} */
    @Override
    public List<Organism> getHostOrganisms() {
        if (this.jaxbHostOrganismWrapper == null){
            initialiseHostOrganismWrapper();
        }
        return this.jaxbHostOrganismWrapper.hostOrganisms;
    }

    /**
     * <p>getExperimentalInteractors.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<AbstractExperimentalInteractor> getExperimentalInteractors() {
        if (this.jaxbExperimentalInteractorWrapper == null){
            initialiseExperimentalInteractorWrapper();
        }
        return this.jaxbExperimentalInteractorWrapper.experimentalInteractors;
    }

    /** {@inheritDoc} */
    @Override
    public List<CvTerm> getExperimentalRoles() {
        if (this.jaxbExperimentalRoleWrapper == null){
            initialiseExperimentalRoleWrapper();
            this.jaxbExperimentalRoleWrapper.experimentalRoles.add(0, new ExperimentalCvTerm(Participant.UNSPECIFIED_ROLE, new XmlXref(CvTermUtils.createPsiMiDatabase(), Participant.UNSPECIFIED_ROLE_MI, CvTermUtils.createIdentityQualifier())));
        }
        return this.jaxbExperimentalRoleWrapper.experimentalRoles;
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="names")
    public void setJAXBNames(NamesContainer value) {
        super.setJAXBNames(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="xref")
    public void setJAXBXref(XrefContainer value) {
        super.setJAXBXref(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="interactor")
    public void setJAXBInteractor(DefaultXmlInteractor interactor) {
        super.setJAXBInteractor(interactor);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="interactionRef")
    public void setJAXBInteractionRef(Integer value) {
        super.setJAXBInteractionRef(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="interactorRef")
    public void setJAXBInteractorRef(Integer value) {
        super.setJAXBInteractorRef(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="biologicalRole")
    public void setJAXBBiologicalRole(XmlCvTerm bioRole) {
        super.setJAXBBiologicalRole(bioRole);
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

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="attributeList")
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper jaxbAttributeWrapper) {
        super.setJAXBAttributeWrapper(jaxbAttributeWrapper);
    }

    /**
     * <p>setFeatureWrapper.</p>
     *
     * @param jaxbFeatureWrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantEvidence.JAXBFeatureWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="featureList")
    public void setFeatureWrapper(JAXBFeatureWrapper jaxbFeatureWrapper) {
        super.setFeatureWrapper(jaxbFeatureWrapper);
    }

    /**
     * <p>setJAXBParticipantIdentificationMethodWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantEvidence.JAXBParticipantIdentificationWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="participantIdentificationMethodList")
    public void setJAXBParticipantIdentificationMethodWrapper(JAXBParticipantIdentificationWrapper wrapper) {
        this.jaxbParticipantionIdentificationWrapper = wrapper;
        // needs to initialise originalIdentificationMethods
        if (this.jaxbParticipantionIdentificationWrapper != null && !this.jaxbParticipantionIdentificationWrapper.identificationMethods.isEmpty()){
            this.originalIdentificationMethods = new ArrayList<ExperimentalCvTerm>(this.jaxbParticipantionIdentificationWrapper.identificationMethods.size());
            for (CvTerm v : this.jaxbParticipantionIdentificationWrapper.identificationMethods){
                this.originalIdentificationMethods.add((ExperimentalCvTerm)v);
            }
        }
    }

    /**
     * <p>setJAXBExperimentalRoleWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantEvidence.JAXBExperimentalRoleWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="experimentalRoleList")
    public void setJAXBExperimentalRoleWrapper(JAXBExperimentalRoleWrapper wrapper) {
        this.jaxbExperimentalRoleWrapper = wrapper;
        if (this.jaxbExperimentalRoleWrapper != null && this.jaxbExperimentalRoleWrapper.experimentalRoles.size() > 1 ){
            PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
            if (listener != null){
                listener.onSeveralExperimentalRolesFound(this.jaxbExperimentalRoleWrapper.experimentalRoles, this.jaxbExperimentalRoleWrapper.getSourceLocator());
            }
        }
    }

    /**
     * <p>setJAXBExperimentalPreparationWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantEvidence.JAXBExperimentalPreparationWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="experimentalPreparationList")
    public void setJAXBExperimentalPreparationWrapper(JAXBExperimentalPreparationWrapper wrapper) {
        this.jaxbExperimentalPreparationWrapper = wrapper;
    }

    /**
     * <p>setExperimentalInteractorWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantEvidence.JAXBExperimentalInteractorWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="experimentalInteractorList")
    public void setExperimentalInteractorWrapper(JAXBExperimentalInteractorWrapper wrapper) {
        this.jaxbExperimentalInteractorWrapper = wrapper;
    }

    /**
     * <p>setJAXBHostOrganismWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantEvidence.JAXBHostOrganismWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="hostOrganismList")
    public void setJAXBHostOrganismWrapper(JAXBHostOrganismWrapper wrapper) {
        this.jaxbHostOrganismWrapper = wrapper;
        if (this.jaxbHostOrganismWrapper != null && this.jaxbHostOrganismWrapper.hostOrganisms.size() > 1 ){
            PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
            if (listener != null){
                listener.onSeveralExpressedInOrganismFound(this.jaxbHostOrganismWrapper.hostOrganisms, this.jaxbHostOrganismWrapper.getSourceLocator());
            }
        }
    }

    /**
     * <p>setJAXBParameterWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantEvidence.JAXBParameterWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="parameterList")
    public void setJAXBParameterWrapper(JAXBParameterWrapper wrapper) {
        this.jaxbParameterWrapper = wrapper;
    }

    /**
     * <p>setJAXBConfidenceWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantEvidence.JAXBConfidenceWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="confidenceList")
    public void setJAXBConfidenceWrapper(JAXBConfidenceWrapper wrapper) {
        this.jaxbConfidenceWrapper = wrapper;
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="stoichiometry")
    public void setJAXBStoichiometry(XmlStoichiometry stoichiometry) {
        super.setJAXBStoichiometry(stoichiometry);
    }

    /**
     * <p>setJAXBInteractorCandidates.</p>
     *
     * @param pool a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantEvidence.JAXBInteractorCandidatesWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="interactorCandidateList")
    public void setJAXBInteractorCandidates(JAXBInteractorCandidatesWrapper pool) {
        super.setJAXBInteractorCandidates(pool);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="stoichiometryRange")
    public void setJAXBStoichiometryRange(XmlStoichiometryRange stoichiometry) {
        super.setJAXBStoichiometryRange(stoichiometry);
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        if (super.getSourceLocator() == null && locator != null){
            super.setSourceLocator(new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), getId()));
        }
        return super.getSourceLocator();
    }

    /** {@inheritDoc} */
    @Override
    public void processAddedFeature(FeatureEvidence feature) {
        ((XmlFeatureEvidence)feature).setOriginalParticipant(this);
    }

    /**
     * <p>setOriginalXmlInteraction.</p>
     *
     * @param i a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence} object.
     */
    protected void setOriginalXmlInteraction(ExtendedPsiXmlInteractionEvidence i){
        this.originalInteraction = i;
        setInteraction(i);
    }

    /**
     * <p>Getter for the field <code>originalInteraction</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence} object.
     */
    protected ExtendedPsiXmlInteractionEvidence getOriginalInteraction() {
        return originalInteraction;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFeatureWrapper() {
        super.setFeatureWrapper(new JAXBFeatureWrapper());
    }

    ////////////////////////////////////////////////////// classes
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="participantEvidenceFeatureWrapper")
    public static class JAXBFeatureWrapper extends AbstractXmlParticipant.JAXBFeatureWrapper<FeatureEvidence> {

        public JAXBFeatureWrapper(){
            super();
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= XmlFeatureEvidence.class, name="feature", required = true)
        public List<FeatureEvidence> getJAXBFeatures() {
            return super.getJAXBFeatures();
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="participantEvidenceIdentificationWrapper")
    public static class JAXBParticipantIdentificationWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<CvTerm> identificationMethods;

        public JAXBParticipantIdentificationWrapper(){
            initialiseIdentificationMethods();
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

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= ExperimentalCvTerm.class, name="participantIdentificationMethod", required = true)
        public List<CvTerm> getJAXBParticipantIdentificationMethods() {
            return this.identificationMethods;
        }

        protected void initialiseIdentificationMethods(){
            this.identificationMethods = new ArrayList<CvTerm>();
        }

        @Override
        public String toString() {
            return "Participant Identification method List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="participantEvidencePreparationWrapper")
    public static class JAXBExperimentalPreparationWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<CvTerm> experimentalPreparations;

        public JAXBExperimentalPreparationWrapper(){
            initialiseExperimentalPreparations();
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

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= ExperimentalCvTerm.class, name="experimentalPreparation", required = true)
        public List<CvTerm> getJAXBExperimentalPreparations() {
            return this.experimentalPreparations;
        }

        protected void initialiseExperimentalPreparations(){
            this.experimentalPreparations = new ArrayList<CvTerm>();
        }

        @Override
        public String toString() {
            return "Participant Experimental Preparation List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="participantEvidenceRoleWrapper")
    public static class JAXBExperimentalRoleWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<CvTerm> experimentalRoles;

        public JAXBExperimentalRoleWrapper(){
            initialiseExperimentalRoles();
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

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= ExperimentalCvTerm.class, name="experimentalRole", required = true)
        public List<CvTerm> getJAXBExperimentalRoles() {
            return this.experimentalRoles;
        }

        protected void initialiseExperimentalRoles(){
            this.experimentalRoles = new ArrayList<CvTerm>();
        }

        @Override
        public String toString() {
            return "Participant Experimental Role List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="participantEvidenceInteractorWrapper")
    public static class JAXBExperimentalInteractorWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<AbstractExperimentalInteractor> experimentalInteractors;

        public JAXBExperimentalInteractorWrapper(){
            initialiseExperimentalIneteractors();
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

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= ExperimentalInteractor.class, name="experimentalInteractor", required = true)
        public List<AbstractExperimentalInteractor> getJAXBExperimentalInteractors() {
            return this.experimentalInteractors;
        }

        protected void initialiseExperimentalIneteractors(){
            this.experimentalInteractors = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Participant Experimental Interactor List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="participantEvidenceOrganismWrapper")
    public static class JAXBHostOrganismWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Organism> hostOrganisms;

        public JAXBHostOrganismWrapper(){
            initialiseHostOrganisms();
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

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= HostOrganism.class, name="hostOrganism", required = true)
        public List<Organism> getJAXBHostOrganisms() {
            return this.hostOrganisms;
        }

        protected void initialiseHostOrganisms(){
            this.hostOrganisms = new ArrayList<Organism>();
        }

        @Override
        public String toString() {
            return "Participant Host Organism List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="participantEvidenceConfidenceWrapper")
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

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= XmlConfidence.class, name="confidence", required = true)
        public List<Confidence> getJAXBConfidences() {
            return this.confidences;
        }

        protected void initialiseConfidences(){
            this.confidences = new ArrayList<Confidence>();
        }

        @Override
        public String toString() {
            return "Participant Confidence List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="participantEvidenceParameterWrapper")
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

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= XmlParameter.class, name="parameter", required = true)
        public List<Parameter> getJAXBParameters() {
            return this.parameters;
        }

        protected void initialiseParameters(){
            this.parameters = new ArrayList<Parameter>();
        }

        @Override
        public String toString() {
            return "Participant Parameter List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="participantEvidenceInteractorCandidateWrapper")
    public static class JAXBInteractorCandidatesWrapper extends
            JAXBInteractorCandidateWrapper<InteractionEvidence, FeatureEvidence, ExperimentalParticipantCandidate> {

        @Override
        protected void initialisePool() {
            super.setPool(new XmlExperimentalParticipantPool());
        }

        @Override
        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name ="interactorCandidate", type = XmlExperimentalParticipantCandidate.class)
        public Collection<ExperimentalParticipantCandidate> getCandidates() {
            return super.getCandidates();
        }
    }
}

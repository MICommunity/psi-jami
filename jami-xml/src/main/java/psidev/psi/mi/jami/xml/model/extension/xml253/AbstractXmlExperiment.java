package psidev.psi.mi.jami.xml.model.extension.xml253;

import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.apache.commons.lang.StringUtils;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.Entry;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlExperiment;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.bind.annotation.*;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Xml im
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>25/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractXmlExperiment implements ExtendedPsiXmlExperiment, FileSourceContext, Locatable{

    private static final Logger logger = Logger.getLogger("AbstractXmlExperiment");

    private NamesContainer experimentNamesContainer;
    private ExperimentXrefContainer experimentXrefContainer;
    private CvTerm participantIdentificationMethod;
    private CvTerm featureDetectionMethod;
    private int id;
    private PsiXmlLocator sourceLocator;
    private Publication publication;
    private CvTerm interactionDetectionMethod;
    private Collection<InteractionEvidence> interactions;

    private JAXBAttributeWrapper jaxbAttributeWrapper;
    private JAXBHostOrganismWrapper jaxbHostOrganismWrapper;
    private JAXBConfidenceWrapper jaxbConfidenceWrapper;
    private JAXBVariableParameterWrapper jaxbVariableParameterWrapper;

    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * <p>Constructor for AbstractXmlExperiment.</p>
     */
    public AbstractXmlExperiment(){
    }

    /**
     * <p>Constructor for AbstractXmlExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public AbstractXmlExperiment(Publication publication){

        this.publication = publication;
        this.interactionDetectionMethod = new XmlCvTerm(Experiment.UNSPECIFIED_METHOD, new XmlXref(CvTermUtils.createPsiMiDatabase(), Experiment.UNSPECIFIED_METHOD_MI, CvTermUtils.createIdentityQualifier()));
    }

    /**
     * <p>Constructor for AbstractXmlExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param interactionDetectionMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlExperiment(Publication publication, CvTerm interactionDetectionMethod){

        this.publication = publication;
        if (interactionDetectionMethod == null){
            this.interactionDetectionMethod = new XmlCvTerm(Experiment.UNSPECIFIED_METHOD, new XmlXref(CvTermUtils.createPsiMiDatabase(), Experiment.UNSPECIFIED_METHOD_MI, CvTermUtils.createIdentityQualifier()));
        }
        else {
            this.interactionDetectionMethod = interactionDetectionMethod;
        }
    }

    /**
     * <p>Constructor for AbstractXmlExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param interactionDetectionMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public AbstractXmlExperiment(Publication publication, CvTerm interactionDetectionMethod, Organism organism){
        this(publication, interactionDetectionMethod);
        if (organism != null){
            this.jaxbHostOrganismWrapper = new JAXBHostOrganismWrapper();
            this.jaxbHostOrganismWrapper.hostOrganisms.add(organism);
        }
    }

    /**
     * <p>Getter for the field <code>publication</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public Publication getPublication() {
        return this.publication;
    }

    /** {@inheritDoc} */
    public void setPublication(Publication publication) {
        this.publication = publication;
        if (this.experimentXrefContainer == null){
            this.experimentXrefContainer = new ExperimentXrefContainer();
        }
        this.experimentXrefContainer.setPublication(this.publication);
    }

    /** {@inheritDoc} */
    public void setPublicationAndAddExperiment(Publication publication) {
        if (this.publication != null){
            this.publication.removeExperiment(this);
        }

        if (publication != null){
            publication.addExperiment(this);
        }

        if (this.experimentXrefContainer == null){
            this.experimentXrefContainer = new ExperimentXrefContainer();
        }
        this.experimentXrefContainer.setPublication(this.publication);
    }

    /**
     * <p>getXrefs.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getXrefs() {
        if (this.experimentXrefContainer == null){
            this.experimentXrefContainer = new ExperimentXrefContainer();
            this.experimentXrefContainer.setPublication(publication);
        }
        return this.experimentXrefContainer.getXrefs();
    }

    /**
     * <p>getHostOrganism.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public Organism getHostOrganism() {
        return (this.jaxbHostOrganismWrapper != null && !this.jaxbHostOrganismWrapper.hostOrganisms.isEmpty())? this.jaxbHostOrganismWrapper.hostOrganisms.iterator().next() : null;
    }

    /** {@inheritDoc} */
    public void setHostOrganism(Organism organism) {
        if (this.jaxbHostOrganismWrapper == null && organism != null){
            this.jaxbHostOrganismWrapper = new JAXBHostOrganismWrapper();
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
     * Gets the value of the interactionDetectionMethod property.
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getInteractionDetectionMethod() {
        return interactionDetectionMethod;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the value of the interactionDetectionMethod property.
     */
    public void setInteractionDetectionMethod(CvTerm value) {
        if (value == null){
            this.interactionDetectionMethod = new XmlCvTerm(Experiment.UNSPECIFIED_METHOD, new XmlXref(CvTermUtils.createPsiMiDatabase(), Experiment.UNSPECIFIED_METHOD_MI, CvTermUtils.createIdentityQualifier()));
        }
        else{
            this.interactionDetectionMethod = value;
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
     * <p>getAnnotations.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        if (jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        return this.jaxbAttributeWrapper.annotations;
    }

    /**
     * <p>getInteractionEvidences.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<InteractionEvidence> getInteractionEvidences() {
        if (interactions == null){
            initialiseInteractions();
        }
        return this.interactions;
    }

    /** {@inheritDoc} */
    public boolean addInteractionEvidence(InteractionEvidence evidence) {
        if (evidence == null){
            return false;
        }

        if (getInteractionEvidences().add(evidence)){
            evidence.setExperiment(this);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean removeInteractionEvidence(InteractionEvidence evidence) {
        if (evidence == null){
            return false;
        }

        if (getInteractionEvidences().remove(evidence)){
            evidence.setExperiment(null);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean addAllInteractionEvidences(Collection<? extends InteractionEvidence> evidences) {
        if (evidences == null){
            return false;
        }

        boolean added = false;
        for (InteractionEvidence ev : evidences){
            if (addInteractionEvidence(ev)){
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    public boolean removeAllInteractionEvidences(Collection<? extends InteractionEvidence> evidences) {
        if (evidences == null){
            return false;
        }

        boolean removed = false;
        for (InteractionEvidence ev : evidences){
            if (removeInteractionEvidence(ev)){
                removed = true;
            }
        }
        return removed;
    }

    /**
     * <p>Getter for the field <code>variableParameters</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<VariableParameter> getVariableParameters() {
        if (jaxbVariableParameterWrapper == null){
            initialiseVariableParameters();
        }
        return jaxbVariableParameterWrapper.variableParameters;
    }

    /** {@inheritDoc} */
    public boolean addVariableParameter(VariableParameter variableParameter) {
        if (variableParameter == null){
            return false;
        }

        if (getVariableParameters().add(variableParameter)){
            variableParameter.setExperiment(this);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean removeVariableParameter(VariableParameter variableParameter) {
        if (variableParameter == null){
            return false;
        }

        if (getVariableParameters().remove(variableParameter)){
            variableParameter.setExperiment(null);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean addAllVariableParameters(Collection<? extends VariableParameter> variableParameters) {
        if (variableParameters == null){
            return false;
        }

        boolean added = false;
        for (VariableParameter param : variableParameters){
            if (addVariableParameter(param)){
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    public boolean removeAllVariableParameters(Collection<? extends VariableParameter> variableParameters) {
        if (variableParameters == null){
            return false;
        }

        boolean removed = false;
        for (VariableParameter param : variableParameters){
            if (removeVariableParameter(param)){
                removed = true;
            }
        }
        return removed;
    }

    /**
     * Gets the value of the experimentNamesContainer property.
     *
     * @return a {@link NamesContainer} object.
     */
    public NamesContainer getExperimentNamesContainer() {
        return experimentNamesContainer;
    }

    /**
     * Sets the value of the experimentNamesContainer property.
     *
     * @param value
     *     allowed object is
     *     {@link NamesContainer}
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name ="names")
    public void setExperimentNamesContainer(NamesContainer value) {
        this.experimentNamesContainer = value;
    }

    /**
     * <p>setJAXBPublication.</p>
     *
     * @param publication a {@link BibRef} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name ="bibref", required = true)
    public void setJAXBPublication(BibRef publication) {
        setPublicationAndAddExperiment(publication);
        if (publication != null){
            XmlEntryContext context = XmlEntryContext.getInstance();
            Entry entry = context.getCurrentEntry();
            if (entry != null){
                publication.setSource(entry.getSource());
                if (entry.getSource() != null && entry.getSource().getReleaseDate() != null){
                    publication.setReleasedDate(entry.getSource().getReleaseDate().toGregorianCalendar().getTime());
                }
            }
            initialiseFullNameFromPublication(publication);
        }
    }

    /**
     * <p>initialiseFullNameFromPublication.</p>
     *
     * @param publication a {@link BibRef} object.
     */
    protected abstract void initialiseFullNameFromPublication(BibRef publication);

    /**
     * Sets the value of the xref property.
     *
     * @param value
     *     allowed object is
     *     {@link XrefContainer}
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name ="xref")
    public void setExperimentXrefContainer(ExperimentXrefContainer value) {
        this.experimentXrefContainer = value;
        if (value != null){
            this.experimentXrefContainer.setPublication(this.publication);
        }
    }

    /**
     * <p>setJAXBHostOrganismWrapper.</p>
     *
     * @param wrapper a {@link AbstractXmlExperiment.JAXBHostOrganismWrapper} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name="hostOrganismList")
    public void setJAXBHostOrganismWrapper(JAXBHostOrganismWrapper wrapper) {
        this.jaxbHostOrganismWrapper = wrapper;
        if (this.jaxbHostOrganismWrapper != null && this.jaxbHostOrganismWrapper.hostOrganisms.size() > 1 ){
            PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
            if (listener != null){
               listener.onSeveralHostOrganismFound(this.jaxbHostOrganismWrapper.hostOrganisms, this.jaxbHostOrganismWrapper.getSourceLocator());
            }
        }
    }

    /**
     * Sets the value of the interactionDetectionMethod property.
     *
     * @param value
     *     allowed object is
     *     {@link XmlCvTerm}
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name ="interactionDetectionMethod", required = true)
    public void setJAXBInteractionDetectionMethod(XmlCvTerm value) {
        if (value == null){
            this.interactionDetectionMethod = new XmlCvTerm(Experiment.UNSPECIFIED_METHOD, Experiment.UNSPECIFIED_METHOD_MI);
        }
        else{
            this.interactionDetectionMethod = value;
        }
    }

    /**
     * Gets the value of the participantIdentificationMethod property.
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getParticipantIdentificationMethod() {
        return participantIdentificationMethod;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the value of the participantIdentificationMethod property.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name ="participantIdentificationMethod", type = XmlCvTerm.class)
    public void setParticipantIdentificationMethod(CvTerm value) {
        this.participantIdentificationMethod = value;
    }

    /**
     * Gets the value of the featureDetectionMethod property.
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getFeatureDetectionMethod() {
        return featureDetectionMethod;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the value of the featureDetectionMethod property.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name ="featureDetectionMethod", type = XmlCvTerm.class)
    public void setFeatureDetectionMethod(CvTerm value) {
        this.featureDetectionMethod = value;
    }

    /**
     * <p>setJAXBConfidenceWrapper.</p>
     *
     * @param wrapper a {@link AbstractXmlExperiment.JAXBConfidenceWrapper} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name="confidenceList")
    public void setJAXBConfidenceWrapper(JAXBConfidenceWrapper wrapper) {
        this.jaxbConfidenceWrapper = wrapper;
    }

    /**
     * <p>setJAXBVariableParameterValuesWrapper.</p>
     *
     * @param jaxbVariableParameterList a {@link AbstractXmlExperiment.JAXBVariableParameterWrapper} object.
     */
    // We need to handle variable parameters at this level in the class hierarchy to be able to resolve properly the XmlExperiment.class at
    // experimentDescription when it gets call inside of AbstractXmlInteractionEvidence (in the expanded version).
    // It always resolve the class as psidev.psi.mi.jami.xml.model.extension.XmlExperiment
    // but no any of the subclasses, so it is the only way that the unmarshaller find variableParameterList
    // An alternative approach could be delegating the experimentDescription mapping to the specific XmlInteractionEvidence subclasses per schema
    // , but implies more duplicated code
    @XmlElement(namespace = "net:sf:psidev:mi", name ="variableParameterList")
    public void setJAXBVariableParameterValuesWrapper(JAXBVariableParameterWrapper jaxbVariableParameterList) {
        this.jaxbVariableParameterWrapper = jaxbVariableParameterList;
        // initialise all variable parameters because of back references
        if (this.jaxbVariableParameterWrapper != null && !this.jaxbVariableParameterWrapper.variableParameters.isEmpty()){
            for (VariableParameter param : this.jaxbVariableParameterWrapper.variableParameters){
                param.setExperiment(this);
            }
        }
    }

    /**
     * <p>setJAXBAttributeWrapper.</p>
     *
     * @param wrapper a {@link AbstractXmlExperiment.JAXBAttributeWrapper} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name="attributeList")
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper wrapper) {
        this.jaxbAttributeWrapper = wrapper;
        // in case we have publication annotations in the experiment, we can update the publication annotations
        if (this.jaxbAttributeWrapper != null) {
            if (publication == null) {
                publication = new BibRef();
            }
            //we have some publication annotations
            // set curation depth
            if (this.jaxbAttributeWrapper.curationDepth != CurationDepth.undefined) {
                if (publication.getCurationDepth() == null || publication.getCurationDepth() == CurationDepth.undefined) {
                    publication.setCurationDepth(this.jaxbAttributeWrapper.curationDepth);
                }
                this.jaxbAttributeWrapper.annotations.add(new DefaultXmlAnnotation(CvTermUtils.createMICvTerm(
                        Annotation.CURATION_DEPTH, Annotation.CURATION_DEPTH_MI),
                        this.jaxbAttributeWrapper.curationDepth.toString()));
                this.jaxbAttributeWrapper.curationDepth = null;
            } else {
                //we check if we can copy the value form the publication and create the annotation
                if (publication.getCurationDepth() != null && publication.getCurationDepth() != CurationDepth.undefined) {
                    this.jaxbAttributeWrapper.annotations.add(new DefaultXmlAnnotation(CvTermUtils.createMICvTerm(
                            Annotation.CURATION_DEPTH, Annotation.CURATION_DEPTH_MI),
                            publication.getCurationDepth().toString()));
                    this.jaxbAttributeWrapper.curationDepth = null;
                }
            }
            // authors
            if (!this.jaxbAttributeWrapper.authors.isEmpty()) {
                if (this.publication.getAuthors().isEmpty()) {
                    this.publication.getAuthors().addAll(this.jaxbAttributeWrapper.authors);
                }
                this.jaxbAttributeWrapper.annotations.add(new DefaultXmlAnnotation(CvTermUtils.createMICvTerm(
                        Annotation.AUTHOR, Annotation.AUTHOR_MI),
                        StringUtils.join(this.jaxbAttributeWrapper.authors, ",")));
                this.jaxbAttributeWrapper.authors = null;
            } else {
                if (!this.publication.getAuthors().isEmpty()) {
                    this.jaxbAttributeWrapper.annotations.add(new DefaultXmlAnnotation(CvTermUtils.createMICvTerm(
                            Annotation.AUTHOR, Annotation.AUTHOR_MI),
                            StringUtils.join(this.publication.getAuthors(), ",")));
                    this.jaxbAttributeWrapper.authors = null;
                }
            }
            // title
            if (this.jaxbAttributeWrapper.title != null){
                if(this.publication.getTitle() == null){
                    this.publication.setTitle(this.jaxbAttributeWrapper.title);
                }
                this.jaxbAttributeWrapper.annotations.add(new DefaultXmlAnnotation(CvTermUtils.createMICvTerm(
                        Annotation.PUBLICATION_TITLE, Annotation.PUBLICATION_TITLE_MI),
                        this.jaxbAttributeWrapper.title));
                this.jaxbAttributeWrapper.title = null;
            } else { //this.jaxbAttributeWrapper.title = null
                if (this.publication.getTitle() == null && getFullName() != null) {
                    this.publication.setTitle(getFullName());
                }
                if (this.publication.getTitle() != null) {
                    this.jaxbAttributeWrapper.annotations.add(new DefaultXmlAnnotation(CvTermUtils.createMICvTerm(
                            Annotation.PUBLICATION_TITLE, Annotation.PUBLICATION_TITLE_MI),
                            this.publication.getTitle()));
                }
            }
            // journal
            if (this.jaxbAttributeWrapper.journal != null) {
                if (this.publication.getJournal() == null) {
                    this.publication.setJournal(this.jaxbAttributeWrapper.journal);
                }
                this.jaxbAttributeWrapper.annotations.add(new DefaultXmlAnnotation(CvTermUtils.createMICvTerm(
                        Annotation.PUBLICATION_JOURNAL, Annotation.PUBLICATION_JOURNAL_MI),
                        this.jaxbAttributeWrapper.journal));
                this.jaxbAttributeWrapper.journal = null;
            } else { // this.jaxbAttributeWrapper.journal = null
                if (this.publication.getJournal() != null) {
                    this.jaxbAttributeWrapper.annotations.add(new DefaultXmlAnnotation(CvTermUtils.createMICvTerm(
                            Annotation.PUBLICATION_JOURNAL, Annotation.PUBLICATION_JOURNAL_MI),
                            this.publication.getJournal()));
                }
            }
            // publicationDate
            if (this.jaxbAttributeWrapper.publicationDate != null) {
                if (this.publication.getPublicationDate() == null) {
                    this.publication.setPublicationDate(this.jaxbAttributeWrapper.publicationDate);
                }
                this.jaxbAttributeWrapper.annotations.add(new DefaultXmlAnnotation(CvTermUtils.createMICvTerm(
                        Annotation.PUBLICATION_YEAR, Annotation.PUBLICATION_YEAR_MI),
                        PsiXmlUtils.YEAR_FORMAT.format(this.jaxbAttributeWrapper.publicationDate)));
                this.jaxbAttributeWrapper.publicationDate = null;
            } else { //this.jaxbAttributeWrapper.publicationDate = null
                if (this.publication.getPublicationDate() != null) {
                    this.jaxbAttributeWrapper.annotations.add(new DefaultXmlAnnotation(CvTermUtils.createMICvTerm(
                            Annotation.PUBLICATION_YEAR, Annotation.PUBLICATION_YEAR_MI),
                            PsiXmlUtils.YEAR_FORMAT.format(this.publication.getPublicationDate())));
                }
            }
        }
    }


    /**
     * Gets the value of the id property.
     *
     * @return a int.
     */
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the value of the id property.
     */
    public void setId(int value) {
        this.id = value;
        XmlEntryContext.getInstance().registerExperiment(this.id, this);
        if (getSourceLocator() != null){
            sourceLocator.setObjectId(this.id);
        }
    }

    /**
     * <p>setJAXBId.</p>
     *
     * @param value a int.
     */
    @XmlAttribute(name = "id", required = true)
    public void setJAXBId(int value) {
        setId(value);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "ExperimentDescription: "+getSourceLocator().toString():super.toString());
    }

    /** {@inheritDoc} */
    @Override
    public Locator sourceLocation() {
        return (Locator)getSourceLocator();
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        if (sourceLocator == null && locator != null){
            sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), getId());
        }
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else if (sourceLocator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)sourceLocator;
            this.sourceLocator.setObjectId(getId());
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), getId());
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getShortName() {
        return this.experimentNamesContainer != null ? this.experimentNamesContainer.getShortLabel():null;
    }

    /** {@inheritDoc} */
    @Override
    public void setShortName(String name) {
        if (this.experimentNamesContainer == null){
            this.experimentNamesContainer = new NamesContainer();
        }
        this.experimentNamesContainer.setShortLabel(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getFullName() {
        return this.experimentNamesContainer != null ? this.experimentNamesContainer.getFullName():null;
    }

    /** {@inheritDoc} */
    @Override
    public void setFullName(String name) {
        if (this.experimentNamesContainer == null){
            this.experimentNamesContainer = new NamesContainer();
        }
        this.experimentNamesContainer.setFullName(name);
    }

    /** {@inheritDoc} */
    @Override
    public List<Alias> getAliases() {
        if (this.experimentNamesContainer == null){
            this.experimentNamesContainer = new NamesContainer();
        }
        return this.experimentNamesContainer.getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public List<Organism> getHostOrganisms() {
        if (this.jaxbHostOrganismWrapper == null){
            this.jaxbHostOrganismWrapper = new JAXBHostOrganismWrapper();
        }
        return this.jaxbHostOrganismWrapper.hostOrganisms;
    }

    /**
     * <p>initialiseAnnotationWrapper.</p>
     */
    protected void initialiseAnnotationWrapper(){
        this.jaxbAttributeWrapper = new JAXBAttributeWrapper();
    }

    /**
     * <p>initialiseInteractions.</p>
     */
    protected void initialiseInteractions(){
        this.interactions = new ArrayList<InteractionEvidence>();
    }

    /**
     * <p>initialiseConfidenceWrapper.</p>
     */
    protected void initialiseConfidenceWrapper(){
        this.jaxbConfidenceWrapper = new JAXBConfidenceWrapper();
    }

    /**
     * <p>initialiseVariableParameters.</p>
     */
    protected void initialiseVariableParameters(){
        this.jaxbVariableParameterWrapper = new JAXBVariableParameterWrapper();
    }

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "net:sf:psidev:mi", name="experimentAttributeWrapper")
    public static class JAXBAttributeWrapper implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Annotation> annotations;
        private JAXBAttributeList jaxbAttributeList;
        private String title;
        private String journal;
        private Date publicationDate;
        private List<String> authors;
        private CurationDepth curationDepth;

        public JAXBAttributeWrapper(){
            initialiseAnnotations();
            initialiseAuthors();
            this.curationDepth = CurationDepth.undefined;
            this.title = null;
            this.journal = null;
            this.publicationDate = null;
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

        public boolean containsPublicationAnnotations(){
            return this.curationDepth != CurationDepth.undefined || !this.authors.isEmpty()
                    || this.title != null || this.journal != null || this.publicationDate != null;
        }

        protected void initialiseAnnotations(){
            annotations = new ArrayList<Annotation>();
        }

        protected void initialiseAuthors(){
            this.authors = new ArrayList<String>();
        }

        @XmlElement(namespace = "net:sf:psidev:mi", type= DefaultXmlAnnotation.class, name="attribute", required = true)
        public List<Annotation> getJAXBAttributes() {
            if (this.jaxbAttributeList == null){
                this.jaxbAttributeList = new JAXBAttributeList();
            }
            return this.jaxbAttributeList;
        }

        /**
         * The attribute list used by JAXB to populate experiment annotations
         */
        private class JAXBAttributeList extends ArrayList<Annotation>{

            public JAXBAttributeList(){
                super();
                annotations = new ArrayList<Annotation>();
            }

            public JAXBAttributeList(int initialCapacity) {
                super(initialCapacity);
            }

            public JAXBAttributeList(Collection<? extends Annotation> c) {
                super(c);
            }

            @Override
            public boolean add(Annotation annot) {
                return processAnnotation(null, annot);
            }

            @Override
            public boolean addAll(Collection<? extends Annotation> c) {
                if (c == null){
                    return false;
                }
                boolean added = false;

                for (Annotation a : c){
                    if (add(a)){
                        added = true;
                    }
                }
                return added;
            }

            @Override
            public void add(int index, Annotation element) {
                processAnnotation(index, element);
            }

            @Override
            public boolean addAll(int index, Collection<? extends Annotation> c) {
                int newIndex = index;
                if (c == null){
                    return false;
                }
                boolean add = false;
                for (Annotation a : c){
                    if (processAnnotation(newIndex, a)){
                        newIndex++;
                        add = true;
                    }
                }
                return add;
            }

            private boolean processAnnotation(Integer index, Annotation annot) {
                if (annot == null){
                    return false;
                }
                if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.PUBLICATION_TITLE_MI, Annotation.PUBLICATION_TITLE)){
                    title = annot.getValue();
                    return false;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.PUBLICATION_JOURNAL_MI, Annotation.PUBLICATION_JOURNAL)){
                    journal = annot.getValue();
                    return false;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.PUBLICATION_YEAR_MI, Annotation.PUBLICATION_YEAR)){
                    if (annot.getValue() == null){
                        publicationDate = null;
                        return false;
                    }
                    else {
                        try {
                            publicationDate = PsiXmlUtils.YEAR_FORMAT.parse(annot.getValue().trim());
                            return false;
                        } catch (ParseException e) {
                            e.printStackTrace();
                            publicationDate = null;
                            addAnnotation(index, annot);
                            return true;
                        }
                    }
                } else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.CURATION_DEPTH_MI, Annotation.CURATION_DEPTH) && annot.getValue() != null) {
                    if (Annotation.IMEX_CURATION.equalsIgnoreCase(annot.getValue())) {
                        curationDepth = CurationDepth.IMEx;
                        return false;
                    } else if (Annotation.MIMIX_CURATION.equalsIgnoreCase(annot.getValue())) {
                        curationDepth = CurationDepth.MIMIx;
                        return false;
                    } else if (Annotation.RAPID_CURATION.equalsIgnoreCase(annot.getValue())) {
                        curationDepth = CurationDepth.rapid_curation;
                        return false;
                    } else {
                        curationDepth = CurationDepth.undefined;
                        return false;
                    }
                } else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.IMEX_CURATION_MI, Annotation.IMEX_CURATION)) {
                    if(curationDepth!=null && !curationDepth.equals(CurationDepth.undefined) && !curationDepth.equals(CurationDepth.IMEx)) {
                        //just in case was annotated twice in the file we check for consistency
                        logger.log(Level.WARNING, "The curationDepth had already assigned a different value: " +  curationDepth + " it will be overwritten with " +  CurationDepth.IMEx);
                    }
                    curationDepth = CurationDepth.IMEx;
                    return false;
                } else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.MIMIX_CURATION_MI, Annotation.MIMIX_CURATION)) {
                    if(curationDepth!=null && !curationDepth.equals(CurationDepth.undefined) && !curationDepth.equals(CurationDepth.MIMIx)) {
                        //just in case was annotated twice in the file we check for consistency
                        logger.log(Level.WARNING, "The curationDepth had already assigned a different value: " +  curationDepth + " it will be overwritten with " +  CurationDepth.MIMIx);
                    }
                    curationDepth = CurationDepth.MIMIx;
                    return false;
                } else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.RAPID_CURATION_MI, Annotation.RAPID_CURATION)) {
                    if(curationDepth!=null && !curationDepth.equals(CurationDepth.undefined) && !curationDepth.equals(CurationDepth.rapid_curation)) {
                        //just in case was annotated twice in the file we check for consistency
                        logger.log(Level.WARNING, "The curationDepth had already assigned a different value: " +  curationDepth + " it will be overwritten with " +  CurationDepth.rapid_curation);
                    }
                    curationDepth = CurationDepth.rapid_curation;
                    return false;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.AUTHOR_MI, Annotation.AUTHOR)){
                    if (annot.getValue() == null){
                        authors.clear();
                        return false;
                    }
                    else if (annot.getValue().contains(",")){
                        authors.addAll(Arrays.asList(annot.getValue().split(",")));
                        return false;
                    }
                    else {
                        authors.add(annot.getValue());
                        return false;
                    }
                }
                else {
                    return addAnnotation(index, annot);
                }
            }

            private boolean addAnnotation(Integer index, Annotation annot) {
                if (index == null){
                    return annotations.add(annot);
                }
                else{
                    annotations.add(index, annot);
                    return true;
                }
            }
        }
        @Override
        public String toString() {
            return "Experiment Attribute List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "net:sf:psidev:mi", name="experimentOrganismWrapper")
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

        @XmlElement(namespace = "net:sf:psidev:mi", type= XmlOrganism.class, name="hostOrganism", required = true)
        public List<Organism> getJAXBHostOrganisms() {
            return this.hostOrganisms;
        }

        protected void initialiseHostOrganisms(){
            this.hostOrganisms = new ArrayList<Organism>();
        }

        @Override
        public String toString() {
            return "Experiment Host Organism List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "net:sf:psidev:mi", name="experimentConfidenceWrapper")
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

        @XmlElement(namespace = "net:sf:psidev:mi", type= XmlConfidence.class, name="confidence", required = true)
        public List<Confidence> getJAXBConfidences() {
            return this.confidences;
        }

        protected void initialiseConfidences(){
            this.confidences = new ArrayList<Confidence>();
        }

        @Override
        public String toString() {
            return "Experiment Confidence List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "net:sf:psidev:mi", name="variableParametersWrapper")
    public static class JAXBVariableParameterWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<VariableParameter> variableParameters;

        public JAXBVariableParameterWrapper(){
            initialiseVariables();
        }

        public JAXBVariableParameterWrapper(List<VariableParameter> parameters){
            this.variableParameters = parameters;
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

        protected void initialiseVariables(){
            this.variableParameters = new ArrayList<VariableParameter>();
        }

        @XmlElement(namespace = "net:sf:psidev:mi", type=XmlVariableParameter.class, name="variableParameter", required = true)
        public List<VariableParameter> getJAXBVariableParameters() {
            return this.variableParameters;
        }

        @Override
        public String toString() {
            return "Variable parameter values List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}

package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.*;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * Default implementation for complexes
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the Complex object is a complex object.
 * To compare Complex objects, you can use some comparators provided by default:
 * - DefaultComplexComparator
 * - UnambiguousComplexComparator
 * - DefaultExactComplexComparator
 * - UnambiguousExactComplexComparator
 * - ComplexComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/02/13</pre>
 */
public class DefaultComplex extends DefaultInteractor implements Complex {

    private Collection<InteractionEvidence> interactionEvidences;
    private Collection<ModelledParticipant> components;
    private Annotation physicalProperties;
    private Collection<ModelledConfidence> confidences;
    private Collection<ModelledParameter> parameters;

    private Source source;
    private Collection<CooperativeEffect> cooperativeEffects;
    private Checksum rigid;
    private Date updatedDate;
    private Date createdDate;
    private CvTerm interactionType;

    private Alias recommendedName;
    private Alias systematicName;

    private CvTerm evidenceType;

    private Xref complexAcXref;

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultComplex(String name, CvTerm interactorType) {
        super(name, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType());
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultComplex(String name, String fullName, CvTerm interactorType) {
        super(name, fullName, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType());
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultComplex(String name, CvTerm interactorType, Organism organism) {
        super(name, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType(), organism);
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultComplex(String name, String fullName, CvTerm interactorType, Organism organism) {
        super(name, fullName, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType(), organism);
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultComplex(String name, CvTerm interactorType, Xref uniqueId) {
        super(name, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType(), uniqueId);
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultComplex(String name, String fullName, CvTerm interactorType, Xref uniqueId) {
        super(name, fullName, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType(), uniqueId);
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultComplex(String name, CvTerm interactorType, Organism organism, Xref uniqueId) {
        super(name, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType(), organism, uniqueId);
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultComplex(String name, String fullName, CvTerm interactorType, Organism organism, Xref uniqueId) {
        super(name, fullName, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType(), organism, uniqueId);
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public DefaultComplex(String name) {
        super(name, CvTermUtils.createComplexInteractorType());
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public DefaultComplex(String name, String fullName) {
        super(name, fullName, CvTermUtils.createComplexInteractorType());
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultComplex(String name, Organism organism) {
        super(name, CvTermUtils.createComplexInteractorType(), organism);
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultComplex(String name, String fullName, Organism organism) {
        super(name, fullName, CvTermUtils.createComplexInteractorType(), organism);
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultComplex(String name, Xref uniqueId) {
        super(name, CvTermUtils.createComplexInteractorType(), uniqueId);
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultComplex(String name, String fullName, Xref uniqueId) {
        super(name, fullName, CvTermUtils.createComplexInteractorType(), uniqueId);
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultComplex(String name, Organism organism, Xref uniqueId) {
        super(name, CvTermUtils.createComplexInteractorType(), organism, uniqueId);
    }

    /**
     * <p>Constructor for DefaultComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultComplex(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, CvTermUtils.createComplexInteractorType(), organism, uniqueId);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseAnnotations() {
        initialiseAnnotationsWith(new ComplexAnnotationList());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseChecksums(){
        initialiseChecksumsWith(new ComplexChecksumList());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseAliases() {
        initialiseAliasesWith(new ComplexAliasList());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefs() {
        initialiseXrefsWith(new ComplexXrefList());
    }

    /**
     * <p>initialiseInteractionEvidences</p>
     */
    protected void initialiseInteractionEvidences(){
        this.interactionEvidences = new ArrayList<InteractionEvidence>();
    }

    /**
     * <p>initialiseInteractionEvidencesWith</p>
     *
     * @param interactionEvidences a {@link java.util.Collection} object.
     */
    protected void initialiseInteractionEvidencesWith(Collection<InteractionEvidence> interactionEvidences) {
        if (interactionEvidences == null) {
            this.interactionEvidences = Collections.EMPTY_LIST;
        } else {
            this.interactionEvidences = interactionEvidences;
        }
    }

    /**
     * <p>initialiseCooperativeEffects</p>
     */
    protected void initialiseCooperativeEffects() {
        this.cooperativeEffects = new ArrayList<CooperativeEffect>();
    }

    /**
     * <p>initialiseCooperativeEffectsWith</p>
     *
     * @param cooperativeEffects a {@link java.util.Collection} object.
     */
    protected void initialiseCooperativeEffectsWith(Collection<CooperativeEffect> cooperativeEffects) {
        if (cooperativeEffects == null) {
            this.cooperativeEffects = Collections.EMPTY_LIST;
        } else {
            this.cooperativeEffects = cooperativeEffects;
        }
    }

    /**
     * <p>initialiseConfidences</p>
     */
    protected void initialiseConfidences() {
        this.confidences = new ArrayList<ModelledConfidence>();
    }

    /**
     * <p>initialiseConfidencesWith</p>
     *
     * @param confidences a {@link java.util.Collection} object.
     */
    protected void initialiseConfidencesWith(Collection<ModelledConfidence> confidences) {
        if (confidences == null) {
            this.confidences = Collections.EMPTY_LIST;
        } else {
            this.confidences = confidences;
        }
    }

    /**
     * <p>initialiseParameters</p>
     */
    protected void initialiseParameters(){
        this.parameters = new ArrayList<ModelledParameter>();
    }

    /**
     * <p>initialiseParametersWith</p>
     *
     * @param parameters a {@link java.util.Collection} object.
     */
    protected void initialiseParametersWith(Collection<ModelledParameter> parameters){
        if (parameters == null) {
            this.parameters = Collections.EMPTY_LIST;
        } else {
            this.parameters = parameters;
        }
    }

    /**
     * <p>initialiseComponents</p>
     */
    protected void initialiseComponents(){
        this.components = new ArrayList<ModelledParticipant>();
    }

    /**
     * <p>initialiseComponentsWith</p>
     *
     * @param components a {@link java.util.Collection} object.
     */
    protected void initialiseComponentsWith(Collection<ModelledParticipant> components){
        if (components == null) {
            this.components = Collections.EMPTY_LIST;
        } else {
            this.components = components;
        }
    }

    /**
     * <p>Getter for the field <code>source</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Source} object.
     */public Source getSource() {
        return this.source;
    }

    /** {@inheritDoc} */
    public void setSource(Source source) {
        this.source = source;
    }

    /**
     * <p>getParticipants</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledParticipant> getParticipants() {
        if (components == null) {
            initialiseComponents();
        }
        return this.components;
    }

    /**
     * <p>addParticipant</p>
     *
     * @param part a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @return a boolean.
     */
    public boolean addParticipant(ModelledParticipant part) {
        if (part == null) {
            return false;
        }
        if (components == null) {
            initialiseComponents();
        }
        part.setInteraction(this);
        return components.add(part);
    }

    /**
     * <p>removeParticipant</p>
     *
     * @param part a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @return a boolean.
     */
    public boolean removeParticipant(ModelledParticipant part) {
        if (part == null) {
            return false;
        }
        if (components == null) {
            initialiseComponents();
        }
        part.setInteraction(null);
        if (components.remove(part)) {
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean addAllParticipants(Collection<? extends ModelledParticipant> participants) {
        if (participants == null) {
            return false;
        }

        boolean added = false;
        for (ModelledParticipant p : participants) {
            if (addParticipant(p)) {
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    public boolean removeAllParticipants(Collection<? extends ModelledParticipant> participants) {
        if (participants == null) {
            return false;
        }

        boolean removed = false;
        for (ModelledParticipant p : participants) {
            if (removeParticipant(p)) {
                removed = true;
            }
        }
        return removed;
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
     * <p>getModelledConfidences</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledConfidence> getModelledConfidences() {
        if (confidences == null) {
            initialiseConfidences();
        }
        return this.confidences;
    }

    /**
     * <p>getModelledParameters</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledParameter> getModelledParameters() {
        if (parameters == null) {
            initialiseParameters();
        }
        return this.parameters;
    }

    /**
     * <p>Getter for the field <code>cooperativeEffects</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CooperativeEffect> getCooperativeEffects() {
        if (cooperativeEffects == null) {
            initialiseCooperativeEffects();
        }
        return this.cooperativeEffects;
    }

    /** {@inheritDoc} */
    public String getComplexAc() {
        return this.complexAcXref != null ? this.complexAcXref.getId() : null;
    }

    /** {@inheritDoc} */
    public void assignComplexAc(String accession) {

        // add new complex ac if not null
        if (accession != null) {
            ComplexXrefList complexXrefList = (ComplexXrefList) getXrefs();
            String id;
            String version;

            //It checks if the accession is valid and split the version if it is provided
            String[] splittedComplexAc = accession.split("\\.");
            if (splittedComplexAc.length == 1) {
                id = splittedComplexAc[0];
                version = "1";
            } else if (splittedComplexAc.length == 2) {
                {
                    id = splittedComplexAc[0];
                    version = splittedComplexAc[1];
                }
            } else {
                throw new IllegalArgumentException("The complex ac has a non valid format (e.g. CPX-12345.1)");
            }

            CvTerm complexPortalDatabase = CvTermUtils.createComplexPortalDatabase();
            CvTerm complexPortalPrimaryQualifier = CvTermUtils.createComplexPortalPrimaryQualifier();
            // first remove old ac if not null
            if (this.complexAcXref != null) {
                if (!id.equals(complexAcXref.getId())) {
                    // first remove old complexAcXref and creates the new one;
                    complexXrefList.remove(this.complexAcXref);
                    this.complexAcXref = new DefaultXref(complexPortalDatabase, id, version, complexPortalPrimaryQualifier);
                    complexXrefList.add(this.complexAcXref);
                }
            } else {
                this.complexAcXref = new DefaultXref(complexPortalDatabase, id, version, complexPortalPrimaryQualifier);
                complexXrefList.add(this.complexAcXref);
            }
        } else {
            throw new IllegalArgumentException("The complex ac has to be non null.");
        }
    }

    /**
     * <p>Getter for the field <code>physicalProperties</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPhysicalProperties() {
        return this.physicalProperties != null ? this.physicalProperties.getValue() : null;
    }

    /** {@inheritDoc} */
    public void setPhysicalProperties(String properties) {
        ComplexAnnotationList complexAnnotationList = (ComplexAnnotationList) getAnnotations();

        // add new physical properties if not null
        if (properties != null) {

            CvTerm complexPhysicalProperties = CvTermUtils.createComplexPhysicalProperties();
            // first remove old physical property if not null
            if (this.physicalProperties != null) {
                complexAnnotationList.removeOnly(this.physicalProperties);
            }
            this.physicalProperties = new DefaultAnnotation(complexPhysicalProperties, properties);
            complexAnnotationList.addOnly(this.physicalProperties);
        }
        // remove all physical properties if the collection is not empty
        else if (!complexAnnotationList.isEmpty()) {
            AnnotationUtils.removeAllAnnotationsWithTopic(complexAnnotationList, Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
            physicalProperties = null;
        }
    }

    /**
     * <p>Getter for the field <code>recommendedName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRecommendedName() {
        return this.recommendedName != null ? this.recommendedName.getName() : null;
    }

    /** {@inheritDoc} */
    public void setRecommendedName(String name) {
        ComplexAliasList complexAliasList = (ComplexAliasList) getAliases();

        // add new recommended name if not null
        if (name != null) {

            CvTerm recommendedName = CvTermUtils.createComplexRecommendedName();
            // first remove old recommended name if not null
            if (this.recommendedName != null) {
                complexAliasList.removeOnly(this.recommendedName);
            }
            this.recommendedName = new DefaultAlias(recommendedName, name);
            complexAliasList.addOnly(this.recommendedName);
        }
        // remove all recommended name if the collection is not empty
        else if (!complexAliasList.isEmpty()) {
            AliasUtils.removeAllAliasesWithType(complexAliasList, Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
            recommendedName = null;
        }
    }

    /**
     * <p>Getter for the field <code>systematicName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSystematicName() {
        return this.systematicName != null ? this.systematicName.getName() : null;
    }

    /** {@inheritDoc} */
    public void setSystematicName(String name) {
        ComplexAliasList complexAliasList = (ComplexAliasList) getAliases();

        // add new systematic name if not null
        if (name != null) {

            CvTerm systematicName = CvTermUtils.createComplexSystematicName();
            // first remove systematic name  if not null
            if (this.systematicName != null) {
                complexAliasList.removeOnly(this.systematicName);
            }
            this.systematicName = new DefaultAlias(systematicName, name);
            complexAliasList.addOnly(this.systematicName);
        }
        // remove all systematic name  if the collection is not empty
        else if (!complexAliasList.isEmpty()) {
            AliasUtils.removeAllAliasesWithType(complexAliasList, Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
            systematicName = null;
        }
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
    public void setEvidenceType(CvTerm eco) {
        this.evidenceType = eco;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the interactor type for this complex.
     * If the given interactorType is null, it will set the interactor type to 'complex' (MI:0314)
     */
    @Override
    public void setInteractorType(CvTerm interactorType) {
        if (interactorType == null) {
            super.setInteractorType(CvTermUtils.createComplexInteractorType());
        } else {
            super.setInteractorType(interactorType);
        }
    }

    /**
     * <p>Getter for the field <code>rigid</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRigid() {
        return this.rigid != null ? this.rigid.getValue() : null;
    }

    /** {@inheritDoc} */
    public void setRigid(String rigid) {
        Collection<Checksum> checksums = getChecksums();
        if (rigid != null) {
            CvTerm rigidMethod = CvTermUtils.createRigid();
            // first remove old rigid
            if (this.rigid != null) {
                checksums.remove(this.rigid);
            }
            this.rigid = new DefaultChecksum(rigidMethod, rigid);
            checksums.add(this.rigid);
        }
        // remove all smiles if the collection is not empty
        else if (!checksums.isEmpty()) {
            ChecksumUtils.removeAllChecksumWithMethod(checksums, Checksum.RIGID_MI, Checksum.RIGID);
            this.rigid = null;
        }
    }

    /**
     * <p>Getter for the field <code>updatedDate</code>.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getUpdatedDate() {
        return this.updatedDate;
    }

    /** {@inheritDoc} */
    public void setUpdatedDate(Date updated) {
        this.updatedDate = updated;
    }

    /**
     * <p>Getter for the field <code>createdDate</code>.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /** {@inheritDoc} */
    public void setCreatedDate(Date created) {
        this.createdDate = created;
    }

    /**
     * <p>Getter for the field <code>interactionType</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getInteractionType() {
        return this.interactionType;
    }

    /** {@inheritDoc} */
    public void setInteractionType(CvTerm term) {
        this.interactionType = term;
    }

    /**
     * <p>getAnnotations</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        return super.getAnnotations();
    }

    /**
     * <p>getChecksums</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Checksum> getChecksums() {
        return super.getChecksums();
    }

    /**
     * <p>getXrefs</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getXrefs() {
        return super.getXrefs();
    }

    /**
     * <p>getIdentifiers</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getIdentifiers() {
        return super.getIdentifiers();
    }

    /**
     * <p>getAliases</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Alias> getAliases() {
        return super.getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Complex: " + super.toString();
    }

    /**
     * <p>processAddedAnnotationEvent</p>
     *
     * @param added a {@link psidev.psi.mi.jami.model.Annotation} object.
     */
    protected void processAddedAnnotationEvent(Annotation added) {
        if (physicalProperties == null && AnnotationUtils.doesAnnotationHaveTopic(added, Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES)){
            physicalProperties = added;
        }
    }

    /**
     * <p>processRemovedAnnotationEvent</p>
     *
     * @param removed a {@link psidev.psi.mi.jami.model.Annotation} object.
     */
    protected void processRemovedAnnotationEvent(Annotation removed) {
        if (physicalProperties != null && physicalProperties.equals(removed)){
            physicalProperties = AnnotationUtils.collectFirstAnnotationWithTopic(getAnnotations(), Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
        }
    }

    /**
     * <p>clearPropertiesLinkedToAnnotations</p>
     */
    protected void clearPropertiesLinkedToAnnotations() {
        physicalProperties = null;
    }

    /**
     * <p>processAddedChecksumEvent</p>
     *
     * @param added a {@link psidev.psi.mi.jami.model.Checksum} object.
     */
    protected void processAddedChecksumEvent(Checksum added) {
        if (rigid == null && ChecksumUtils.doesChecksumHaveMethod(added, Checksum.RIGID_MI, Checksum.RIGID)) {
            // the rigid is not set, we can set the rigid
            rigid = added;
        }
    }

    /**
     * <p>processRemovedChecksumEvent</p>
     *
     * @param removed a {@link psidev.psi.mi.jami.model.Checksum} object.
     */
    protected void processRemovedChecksumEvent(Checksum removed) {
        if (rigid == removed) {
            rigid = ChecksumUtils.collectFirstChecksumWithMethod(getChecksums(), Checksum.RIGID_MI, Checksum.RIGID);
        }
    }

    /**
     * <p>clearPropertiesLinkedToChecksums</p>
     */
    protected void clearPropertiesLinkedToChecksums() {
        rigid = null;
    }

    /**
     * <p>processAddedAliasEvent</p>
     *
     * @param added a {@link psidev.psi.mi.jami.model.Alias} object.
     */
    protected void processAddedAliasEvent(Alias added) {
        if (recommendedName == null && AliasUtils.doesAliasHaveType(added, Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME)) {
            recommendedName = added;
        } else if (systematicName == null && AliasUtils.doesAliasHaveType(added, Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME)) {
            systematicName = added;
        }
    }

    /**
     * <p>processRemovedAliasEvent</p>
     *
     * @param removed a {@link psidev.psi.mi.jami.model.Alias} object.
     */
    protected void processRemovedAliasEvent(Alias removed) {
        if (recommendedName != null && recommendedName.equals(removed)) {
            recommendedName = AliasUtils.collectFirstAliasWithType(getAliases(), Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
        } else if (systematicName != null && systematicName.equals(removed)) {
            systematicName = AliasUtils.collectFirstAliasWithType(getAliases(), Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
        }
    }

    /**
     * <p>clearPropertiesLinkedToAliases</p>
     */
    protected void clearPropertiesLinkedToAliases() {
        this.recommendedName = null;
        this.systematicName = null;
    }

    /**
     * <p>processAddedXrefEvent</p>
     *
     * @param added a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processAddedXrefEvent(Xref added) {
        // the added identifier is a complexAcXref and the current complexAcXref is not set
        if (complexAcXref == null && XrefUtils.isXrefFromDatabase(added, Xref.COMPLEX_PORTAL_MI, Xref.COMPLEX_PORTAL)) {
            // the added xref is complex-primary
            if (XrefUtils.doesXrefHaveQualifier(added, Xref.COMPLEX_PRIMARY_MI, Xref.COMPLEX_PRIMARY)) {
                complexAcXref = added;
            }
        }
    }

    /**
     * <p>processRemovedXrefEvent</p>
     *
     * @param removed a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processRemovedXrefEvent(Xref removed) {
        // the removed identifier is pubmed
        if (complexAcXref != null && complexAcXref.equals(removed)) {
            Collection<Xref> existingComplexAc = XrefUtils.collectAllXrefsHavingDatabaseAndQualifier(getXrefs(), Xref.COMPLEX_PORTAL_MI, Xref.COMPLEX_PORTAL, Xref.COMPLEX_PRIMARY_MI, Xref.COMPLEX_PRIMARY);
            if (!existingComplexAc.isEmpty()) {
                complexAcXref = existingComplexAc.iterator().next();
            }
        }
    }

    protected void clearPropertiesLinkedToXrefs() {
        complexAcXref = null;
    }


    private class ComplexAnnotationList extends AbstractListHavingProperties<Annotation> {
        public ComplexAnnotationList() {
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

    private class ComplexChecksumList extends AbstractListHavingProperties<Checksum> {
        public ComplexChecksumList() {
            super();
        }

        @Override
        protected void processAddedObjectEvent(Checksum added) {
            processAddedChecksumEvent(added);
        }

        @Override
        protected void processRemovedObjectEvent(Checksum removed) {
            processRemovedChecksumEvent(removed);
        }

        @Override
        protected void clearProperties() {
            clearPropertiesLinkedToChecksums();
        }
    }

    private class ComplexAliasList extends AbstractListHavingProperties<Alias> {
        public ComplexAliasList() {
            super();
        }

        @Override
        protected void processAddedObjectEvent(Alias added) {
            processAddedAliasEvent(added);
        }

        @Override
        protected void processRemovedObjectEvent(Alias removed) {
            processRemovedAliasEvent(removed);
        }

        @Override
        protected void clearProperties() {
            clearPropertiesLinkedToAliases();
        }
    }

    private class ComplexXrefList extends AbstractListHavingProperties<Xref> {
        public ComplexXrefList() {
            super();
        }

        @Override
        protected void processAddedObjectEvent(Xref added) {
            processAddedXrefEvent(added);
        }

        @Override
        protected void processRemovedObjectEvent(Xref removed) {
            processRemovedXrefEvent(removed);
        }

        @Override
        protected void clearProperties() {
            clearPropertiesLinkedToXrefs();
        }
    }
}

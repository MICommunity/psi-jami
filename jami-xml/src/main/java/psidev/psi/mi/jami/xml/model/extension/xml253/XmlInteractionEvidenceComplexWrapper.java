package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultXref;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.Entry;
import psidev.psi.mi.jami.xml.model.extension.AbstractInferredInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipantEvidence;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Xml wrapper for interaction evidences used as complex
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
public class XmlInteractionEvidenceComplexWrapper implements Complex,FileSourceContext, ExtendedPsiXmlInteraction<ModelledParticipant> {
    private ExtendedPsiXmlInteractionEvidence interactionEvidence;
    private Organism organism;
    private CvTerm interactorType;
    private Collection<InteractionEvidence> interactionEvidences;
    private Collection<ModelledConfidence> modelledConfidences;
    private Collection<ModelledParameter> modelledParameters;
    private Collection<CooperativeEffect> cooperativeEffects;
    private Collection<ModelledParticipant> modelledParticipants;
    private CvTerm evidenceType;
    private Xref complexAcXref;

    /**
     * <p>Constructor for XmlInteractionEvidenceComplexWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence} object.
     */
    public XmlInteractionEvidenceComplexWrapper(ExtendedPsiXmlInteractionEvidence interaction){
        if (interaction == null){
            throw new IllegalArgumentException("The complex wrapper needs a non null basic interaction");
        }
        this.interactionEvidence = interaction;
        this.interactorType = new XmlCvTerm(Complex.COMPLEX, new XmlXref(CvTermUtils.createPsiMiDatabase(),Complex.COMPLEX_MI, CvTermUtils.createIdentityQualifier()));
        XmlEntryContext.getInstance().registerComplex(interaction.getId(), this);
    }

    /**
     * <p>getUpdatedDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getUpdatedDate() {
        return this.interactionEvidence.getUpdatedDate();
    }

    /** {@inheritDoc} */
    public void setUpdatedDate(Date updated) {
        this.interactionEvidence.setUpdatedDate(updated);
    }

    /**
     * <p>getCreatedDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getCreatedDate() {
        return this.interactionEvidence.getCreatedDate();
    }

    /** {@inheritDoc} */
    public void setCreatedDate(Date created) {
        this.interactionEvidence.setCreatedDate(created);
    }

    /**
     * <p>getInteractionType.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getInteractionType() {
        return this.interactionEvidence.getInteractionType();
    }

    /** {@inheritDoc} */
    public void setInteractionType(CvTerm term) {
        this.interactionEvidence.setInteractionType(term);
    }

    /**
     * <p>addParticipant.</p>
     *
     * @param part a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @return a boolean.
     */
    public boolean addParticipant(ModelledParticipant part) {
        if (part == null){
            return false;
        }
        if (this.modelledParticipants == null){
            initialiseParticipants();
        }
        if (this.modelledParticipants.add(part)){
            part.setInteraction(this);
            return true;
        }
        return false;
    }

    /**
     * <p>removeParticipant.</p>
     *
     * @param part a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @return a boolean.
     */
    public boolean removeParticipant(ModelledParticipant part) {
        if (part == null){
            return false;
        }
        if (this.modelledParticipants == null){
            initialiseParticipants();
        }
        if (this.modelledParticipants.remove(part)){
            part.setInteraction(null);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean addAllParticipants(Collection<? extends ModelledParticipant> participants) {
        if (participants == null){
            return false;
        }

        boolean added = false;
        for (ModelledParticipant p : participants){
            if (addParticipant(p)){
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    public boolean removeAllParticipants(Collection<? extends ModelledParticipant> participants) {
        if (participants == null){
            return false;
        }

        boolean removed = false;
        for (ModelledParticipant p : participants){
            if (removeParticipant(p)){
                removed = true;
            }
        }
        return removed;
    }

    /**
     * <p>getParticipants.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledParticipant> getParticipants() {
        if (this.modelledParticipants == null){
            initialiseParticipants();
        }
        return this.modelledParticipants;
    }

    /** {@inheritDoc} */
    @Override
    public Collection<InteractionEvidence> getInteractionEvidences() {
        if (this.interactionEvidences == null){
            this.interactionEvidences = new ArrayList<InteractionEvidence>();
        }
        return this.interactionEvidences;
    }

    /**
     * <p>getSource.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public Source getSource() {
        if (this.interactionEvidence.getExperiment() != null){
            Experiment exp =this.interactionEvidence.getExperiment();
            if (exp.getPublication() != null){
                return exp.getPublication().getSource();
            }
        }
        return null;
    }

    /** {@inheritDoc} */
    public void setSource(Source source) {
        if (this.interactionEvidence.getExperiment() != null){
            Experiment exp =this.interactionEvidence.getExperiment();
            if (exp.getPublication() != null){
                exp.getPublication().setSource(source);
            }
            else{
                exp.setPublicationAndAddExperiment(new BibRef());
                exp.getPublication().setSource(source);
            }
        }
        else{
            this.interactionEvidence.setExperimentAndAddInteractionEvidence(new DefaultXmlExperiment(new BibRef()));
            this.interactionEvidence.getExperiment().getPublication().setSource(source);
        }
    }

    /**
     * <p>Getter for the field <code>modelledConfidences</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledConfidence> getModelledConfidences() {
        if (this.modelledConfidences == null){
            initialiseModelledConfidences();
        }
        return this.modelledConfidences;
    }

    /**
     * <p>Getter for the field <code>modelledParameters</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledParameter> getModelledParameters() {
        if (this.modelledParameters == null){
            initialiseModelledParameters();
        }
        return this.modelledParameters;
    }

    /**
     * <p>Getter for the field <code>cooperativeEffects</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CooperativeEffect> getCooperativeEffects() {
        if (this.cooperativeEffects == null){
            this.cooperativeEffects = new ArrayList<CooperativeEffect>();
            // collect cooperative effects from interaction evidence annotations
            Collection<Annotation> annotations = new ArrayList<Annotation>(this.interactionEvidence.getAnnotations());
            CooperativeEffect effect = PsiXmlUtils.extractCooperativeEffectFrom(annotations, this.interactionEvidence.getExperiments(), XmlEntryContext.getInstance().getListener());
            if (effect != null){
                getCooperativeEffects().add(effect);
            }
        }
        return this.cooperativeEffects;
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Annotation> getAnnotations() {
        return this.interactionEvidence.getAnnotations();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Checksum> getChecksums() {
        return this.interactionEvidence.getChecksums();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getXrefs() {
        return this.interactionEvidence.getXrefs();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getIdentifiers() {
        return this.interactionEvidence.getIdentifiers();
    }

    /** {@inheritDoc} */
    @Override
    public String getShortName() {
        return this.interactionEvidence.getShortName() != null ? this.interactionEvidence.getShortName() : PsiXmlUtils.UNSPECIFIED;
    }

    /** {@inheritDoc} */
    @Override
    public void setShortName(String name) {
        this.interactionEvidence.setShortName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getFullName() {
        return this.interactionEvidence.getFullName();
    }

    /** {@inheritDoc} */
    @Override
    public void setFullName(String name) {
        this.interactionEvidence.setFullName(name);
    }

    /** {@inheritDoc} */
    @Override
    public Xref getPreferredIdentifier() {
        return !this.interactionEvidence.getIdentifiers().isEmpty()?this.interactionEvidence.getIdentifiers().iterator().next():null;
    }

    /** {@inheritDoc} */
    @Override
    public String getPreferredName() {
        return this.getShortName();
    }

    /** {@inheritDoc} */
    @Override
    public Organism getOrganism() {
        if (this.organism == null && !this.interactionEvidence.getExperiments().isEmpty()){
            for (Experiment exp : this.interactionEvidence.getExperiments()){
                if (exp.getHostOrganism() != null){
                    this.organism = exp.getHostOrganism();
                    break;
                }
            }
        }
        return this.organism;
    }

    /** {@inheritDoc} */
    @Override
    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getInteractorType() {
        return this.interactorType;
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractorType(CvTerm type) {
        if (type == null){
            this.interactorType = new XmlCvTerm(Complex.COMPLEX, new XmlXref(CvTermUtils.createPsiMiDatabase(),Complex.COMPLEX_MI, CvTermUtils.createIdentityQualifier()));
        }
        else{
            this.interactorType = type;
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getRigid() {
        return this.interactionEvidence.getRigid();
    }

    /** {@inheritDoc} */
    @Override
    public void setRigid(String rigid) {
        this.interactionEvidence.setRigid(rigid);
    }

    /**
     * <p>initialiseModelledParameters.</p>
     */
    protected void initialiseModelledParameters(){
        this.modelledParameters = new ArrayList<ModelledParameter>();
        for (Parameter part : this.interactionEvidence.getParameters()){
            this.modelledParameters.add(new XmlParameterWrapper(part));
        }
    }

    /**
     * <p>initialiseModelledConfidences.</p>
     */
    protected void initialiseModelledConfidences(){
        this.modelledConfidences = new ArrayList<ModelledConfidence>();
        for (Confidence part : this.interactionEvidence.getConfidences()){
            this.modelledConfidences.add(new XmlConfidenceWrapper(part));
        }
    }

    /**
     * <p>initialiseParticipants.</p>
     */
    protected void initialiseParticipants(){
        this.modelledParticipants = new ArrayList<ModelledParticipant>();
        for (ParticipantEvidence part : this.interactionEvidence.getParticipants()){
            this.modelledParticipants.add(new XmlParticipantEvidenceWrapper((ExtendedPsiXmlParticipantEvidence)part, this));
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getComplexAc() {
        Collection<Xref> complexAcs = XrefUtils.collectAllXrefsHavingDatabaseAndQualifier(this.interactionEvidence.getXrefs(), Xref.COMPLEX_PORTAL_MI, Xref.COMPLEX_PORTAL, Xref.COMPLEX_PRIMARY_MI, Xref.COMPLEX_PRIMARY);
        return complexAcs != null && !complexAcs.isEmpty() ? complexAcs.iterator().next().getId() : null;
    }

    /** {@inheritDoc} */
    @Override
    public String getComplexVersion() {
        Collection<Xref> complexAcs = XrefUtils.collectAllXrefsHavingDatabaseAndQualifier(this.interactionEvidence.getXrefs(), Xref.COMPLEX_PORTAL_MI, Xref.COMPLEX_PORTAL, Xref.COMPLEX_PRIMARY_MI, Xref.COMPLEX_PRIMARY);
        return complexAcs != null && !complexAcs.isEmpty() ? complexAcs.iterator().next().getVersion() : null;
    }

    /** {@inheritDoc} */
    @Override
    public void assignComplexAc(String accession, String version) {
        // add new complex ac if not null
        if (accession != null) {
            Collection<Xref> complexXrefList =  getXrefs();

            CvTerm complexPortalDatabase = CvTermUtils.createComplexPortalDatabase();
            CvTerm complexPortalPrimaryQualifier = CvTermUtils.createComplexPortalPrimaryQualifier();
            // first remove old ac if not null
            if (this.complexAcXref != null) {
                if (!accession.equals(complexAcXref.getId())) {
                    // first remove old complexAcXref and creates the new one;
                    complexXrefList.remove(this.complexAcXref);
                    this.complexAcXref = new DefaultXref(complexPortalDatabase, accession, version, complexPortalPrimaryQualifier);
                    complexXrefList.add(this.complexAcXref);
                }
            } else {
                this.complexAcXref = new DefaultXref(complexPortalDatabase, accession, version, complexPortalPrimaryQualifier);
                complexXrefList.add(this.complexAcXref);
            }
        } else {
            throw new IllegalArgumentException("The complex ac has to be non null.");
        }
    }

    /** {@inheritDoc} */
    @Override
    public void assignComplexAc(String accession) {
        // add new complex ac if not null
        if (accession != null) {
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
            assignComplexAc(id, version);

        } else {
            throw new IllegalArgumentException("The complex ac has to be non null.");
        }
    }

    @Override
    public String getPhysicalProperties() {
        Annotation properties = AnnotationUtils.collectFirstAnnotationWithTopic(this.interactionEvidence.getAnnotations(), Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
        return properties != null ? properties.getValue() : null;
    }

    /** {@inheritDoc} */
    @Override
    public void setPhysicalProperties(String properties) {
        Annotation propertiesAnnot = AnnotationUtils.collectFirstAnnotationWithTopic(this.interactionEvidence.getAnnotations(), Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
        if (propertiesAnnot != null){
            propertiesAnnot.setValue(properties);
        }
        else{
            this.interactionEvidence.getAnnotations().add(new DefaultXmlAnnotation(CvTermUtils.createMICvTerm(Annotation.COMPLEX_PROPERTIES, Annotation.COMPLEX_PROPERTIES_MI), properties));
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getRecommendedName() {
        Alias recommendedName = AliasUtils.collectFirstAliasWithType(this.interactionEvidence.getAliases(), Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
        return recommendedName != null ? recommendedName.getName() : null;
    }

    /** {@inheritDoc} */
    @Override
    public void setRecommendedName(String name) {
        AliasUtils.removeAllAliasesWithType(this.interactionEvidence.getAliases(), Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
        if (name != null){
            this.interactionEvidence.getAliases().add(new XmlAlias(name, CvTermUtils.createMICvTerm(Alias.COMPLEX_RECOMMENDED_NAME, Alias.COMPLEX_RECOMMENDED_NAME_MI)));
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getSystematicName() {
        Alias systematicName = AliasUtils.collectFirstAliasWithType(this.interactionEvidence.getAliases(), Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
        return systematicName != null ? systematicName.getName() : null;
    }

    /** {@inheritDoc} */
    @Override
    public void setSystematicName(String name) {
        AliasUtils.removeAllAliasesWithType(this.interactionEvidence.getAliases(), Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
        if (name != null){
            this.interactionEvidence.getAliases().add(new XmlAlias(name, CvTermUtils.createMICvTerm(Alias.COMPLEX_SYSTEMATIC_NAME, Alias.COMPLEX_SYSTEMATIC_NAME_MI)));
        }
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Alias> getAliases() {
        return this.interactionEvidence.getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public List<CvTerm> getInteractionTypes() {
        return this.interactionEvidence.getInteractionTypes();
    }

    /** {@inheritDoc} */
    @Override
    public Entry getEntry() {
        return this.interactionEvidence.getEntry();
    }

    /** {@inheritDoc} */
    @Override
    public void setEntry(Entry entry) {
        this.interactionEvidence.setEntry(entry);
    }

    /** {@inheritDoc} */
    @Override
    public List<AbstractInferredInteraction> getInferredInteractions() {
        return this.interactionEvidence.getInferredInteractions();
    }

    /** {@inheritDoc} */
    @Override
    public int getId() {
        return this.interactionEvidence.getId();
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        this.interactionEvidence.setId(id);
        XmlEntryContext.getInstance().registerComplex(id, this);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isIntraMolecular() {
        return this.interactionEvidence.isIntraMolecular();
    }

    /** {@inheritDoc} */
    @Override
    public void setIntraMolecular(boolean intra) {
        this.setIntraMolecular(intra);
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        return this.interactionEvidence.getSourceLocator();
    }

    /** {@inheritDoc} */
    @Override
    public void setSourceLocator(FileSourceLocator locator) {
        this.interactionEvidence.setSourceLocator(locator);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.interactionEvidence.toString();
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getEvidenceType() {
        return this.evidenceType;
    }

    /** {@inheritDoc} */
    @Override
    public void setEvidenceType(CvTerm eco) {
        this.evidenceType = eco;
    }
}

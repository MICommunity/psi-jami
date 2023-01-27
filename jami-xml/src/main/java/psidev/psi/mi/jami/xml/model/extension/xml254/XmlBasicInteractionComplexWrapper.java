package psidev.psi.mi.jami.xml.model.extension.xml254;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.Checksum;
import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.model.CooperativeEffect;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.ModelledConfidence;
import psidev.psi.mi.jami.model.ModelledParameter;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultXref;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.Entry;
import psidev.psi.mi.jami.xml.model.extension.AbstractInferredInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipant;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Wrapper of basic interactions
 *
 * If we add new modelled participants/remove participants, they will be added/removed from the list of participants of the
 * wrapped interaction.
 *
 * However, the interaction that is in the back references of new participants will be this wrapper.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/10/13</pre>
 */
public class XmlBasicInteractionComplexWrapper implements Complex,FileSourceContext, ExtendedPsiXmlInteraction<ModelledParticipant> {

    private ExtendedPsiXmlInteraction interaction;
    private SynchronizedModelledParticipantList modelledParticipants;
    private Collection<InteractionEvidence> interactionEvidences;
    private Collection<ModelledConfidence> modelledConfidences;
    private Collection<ModelledParameter> modelledParameters;
    private Collection<CooperativeEffect> cooperativeEffects;
    private Source source;
    private Organism organism;
    private CvTerm interactorType;
    private CvTerm evidenceType;
    private Xref complexAcXref;


    /**
     * <p>Constructor for XmlBasicInteractionComplexWrapper.</p>
     *
     * @param interaction a {@link ExtendedPsiXmlInteraction} object.
     */
    public XmlBasicInteractionComplexWrapper(ExtendedPsiXmlInteraction<? extends Participant> interaction){
        if (interaction == null){
            throw new IllegalArgumentException("The complex wrapper needs a non null basic interaction");
        }
        this.interaction = interaction;
        this.interactorType = new XmlCvTerm(Complex.COMPLEX, new XmlXref(CvTermUtils.createPsiMiDatabase(),Complex.COMPLEX_MI, CvTermUtils.createIdentityQualifier()));
        XmlEntryContext.getInstance().registerComplex(interaction.getId(), this);
    }

    /**
     * <p>getUpdatedDate.</p>
     *
     * @return a {@link Date} object.
     */
    public Date getUpdatedDate() {
        return this.interaction.getUpdatedDate();
    }

    /** {@inheritDoc} */
    public void setUpdatedDate(Date updated) {
        this.interaction.setUpdatedDate(updated);
    }

    /**
     * <p>getCreatedDate.</p>
     *
     * @return a {@link Date} object.
     */
    public Date getCreatedDate() {
        return this.interaction.getCreatedDate();
    }

    /** {@inheritDoc} */
    public void setCreatedDate(Date created) {
        this.interaction.setCreatedDate(created);
    }

    /**
     * <p>getInteractionType.</p>
     *
     * @return a {@link CvTerm} object.
     */
    public CvTerm getInteractionType() {
        return this.interaction.getInteractionType();
    }

    /** {@inheritDoc} */
    public void setInteractionType(CvTerm term) {
        this.interaction.setInteractionType(term);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isIntraMolecular() {
        return this.interaction.isIntraMolecular();
    }

    /** {@inheritDoc} */
    @Override
    public void setIntraMolecular(boolean intra) {
        this.interaction.setIntraMolecular(intra);
    }

    /**
     * <p>addParticipant.</p>
     *
     * @param part a {@link ModelledParticipant} object.
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
     * @param part a {@link ModelledParticipant} object.
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
     * @return a {@link Collection} object.
     */
    public Collection<ModelledParticipant> getParticipants() {
        if (this.modelledParticipants == null){
            initialiseParticipants();
        }
        return this.modelledParticipants;
    }

    /**
     * <p>Getter for the field <code>interactionEvidences</code>.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<InteractionEvidence> getInteractionEvidences() {
        if (this.interactionEvidences == null){
            this.interactionEvidences = new ArrayList<InteractionEvidence>();
        }
        return this.interactionEvidences;
    }

    /**
     * <p>Getter for the field <code>source</code>.</p>
     *
     * @return a {@link Source} object.
     */
    public Source getSource() {
        return this.source;
    }

    /** {@inheritDoc} */
    public void setSource(Source source) {
        this.source = source;
    }

    /**
     * <p>Getter for the field <code>modelledConfidences</code>.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<ModelledConfidence> getModelledConfidences() {
        if (this.modelledConfidences == null){
            this.modelledConfidences = new ArrayList<ModelledConfidence>();
        }
        return this.modelledConfidences;
    }

    /**
     * <p>Getter for the field <code>modelledParameters</code>.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<ModelledParameter> getModelledParameters() {
        if (this.modelledParameters == null){
            this.modelledParameters = new ArrayList<ModelledParameter>();
        }
        return this.modelledParameters;
    }

    /**
     * <p>Getter for the field <code>cooperativeEffects</code>.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<CooperativeEffect> getCooperativeEffects() {
        if (this.cooperativeEffects == null){
           this.cooperativeEffects = new ArrayList<CooperativeEffect>();
            // collect cooperative effects from interaction evidence annotations
            Collection<Annotation> annotations = new ArrayList<Annotation>(this.interaction.getAnnotations());
            CooperativeEffect effect = PsiXmlUtils.extractCooperativeEffectFrom(annotations, Collections.EMPTY_LIST, XmlEntryContext.getInstance().getListener());
            if (effect != null){
                getCooperativeEffects().add(effect);
            }
        }
        return this.cooperativeEffects;
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Annotation> getAnnotations() {
        return this.interaction.getAnnotations();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Checksum> getChecksums() {
        return this.interaction.getChecksums();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getXrefs() {
        return this.interaction.getXrefs();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getIdentifiers() {
        return this.interaction.getIdentifiers();
    }

    /** {@inheritDoc} */
    @Override
    public String getShortName() {
        return this.interaction.getShortName() != null ? this.interaction.getShortName() : PsiXmlUtils.UNSPECIFIED;
    }

    /** {@inheritDoc} */
    @Override
    public void setShortName(String name) {
        this.interaction.setShortName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getFullName() {
        return this.interaction.getFullName();
    }

    /** {@inheritDoc} */
    @Override
    public void setFullName(String name) {
        this.interaction.setFullName(name);
    }

    /** {@inheritDoc} */
    @Override
    public Xref getPreferredIdentifier() {
        return !this.interaction.getIdentifiers().isEmpty()?(Xref)this.interaction.getIdentifiers().iterator().next():null;
    }

    /** {@inheritDoc} */
    @Override
    public String getPreferredName() {
        return this.getShortName();
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
        return this.interaction.getRigid();
    }

    /** {@inheritDoc} */
    @Override
    public void setRigid(String rigid) {
        this.interaction.setRigid(rigid);
    }

    /** {@inheritDoc} */
    @Override
    public String getComplexAc() {
        Collection<Xref> complexAcs = XrefUtils.collectAllXrefsHavingDatabaseAndQualifier(this.interaction.getXrefs(), Xref.COMPLEX_PORTAL_MI, Xref.COMPLEX_PORTAL, Xref.COMPLEX_PRIMARY_MI, Xref.COMPLEX_PRIMARY);
        return complexAcs != null && !complexAcs.isEmpty() ? complexAcs.iterator().next().getId() : null;
    }

    /** {@inheritDoc} */
    @Override
    public String getComplexVersion() {
        Collection<Xref> complexAcs = XrefUtils.collectAllXrefsHavingDatabaseAndQualifier(this.interaction.getXrefs(), Xref.COMPLEX_PORTAL_MI, Xref.COMPLEX_PORTAL, Xref.COMPLEX_PRIMARY_MI, Xref.COMPLEX_PRIMARY);
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
        Annotation properties = AnnotationUtils.collectFirstAnnotationWithTopic(this.interaction.getAnnotations(), Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
        return properties != null ? properties.getValue() : null;
    }

    /** {@inheritDoc} */
    @Override
    public void setPhysicalProperties(String properties) {
        Annotation propertiesAnnot = AnnotationUtils.collectFirstAnnotationWithTopic(this.interaction.getAnnotations(), Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
        if (propertiesAnnot != null){
            propertiesAnnot.setValue(properties);
        }
        else{
            this.interaction.getAnnotations().add(new DefaultXmlAnnotation(CvTermUtils.createMICvTerm(Annotation.COMPLEX_PROPERTIES, Annotation.COMPLEX_PROPERTIES_MI), properties));
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getRecommendedName() {
        Alias recommendedName = AliasUtils.collectFirstAliasWithType(this.interaction.getAliases(), Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
        return recommendedName != null ? recommendedName.getName() : null;
    }

    /** {@inheritDoc} */
    @Override
    public void setRecommendedName(String name) {
        AliasUtils.removeAllAliasesWithType(this.interaction.getAliases(), Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
        if (name != null){
            this.interaction.getAliases().add(new XmlAlias(name, CvTermUtils.createMICvTerm(Alias.COMPLEX_RECOMMENDED_NAME, Alias.COMPLEX_RECOMMENDED_NAME_MI)));
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getSystematicName() {
        Alias systematicName = AliasUtils.collectFirstAliasWithType(this.interaction.getAliases(), Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
        return systematicName != null ? systematicName.getName() : null;
    }

    /** {@inheritDoc} */
    @Override
    public void setSystematicName(String name) {
        AliasUtils.removeAllAliasesWithType(this.interaction.getAliases(), Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
        if (name != null){
            this.interaction.getAliases().add(new XmlAlias(name, CvTermUtils.createMICvTerm(Alias.COMPLEX_SYSTEMATIC_NAME, Alias.COMPLEX_SYSTEMATIC_NAME_MI)));
        }
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

    /** {@inheritDoc} */
    @Override
    public Collection<Alias> getAliases() {
        return this.interaction.getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public List<CvTerm> getInteractionTypes() {
        return this.interaction.getInteractionTypes();
    }

    /** {@inheritDoc} */
    @Override
    public Entry getEntry() {
        return this.interaction.getEntry();
    }

    /** {@inheritDoc} */
    @Override
    public void setEntry(Entry entry) {
        this.interaction.setEntry(entry);
    }

    /** {@inheritDoc} */
    @Override
    public List<AbstractInferredInteraction> getInferredInteractions() {
        return this.interaction.getInferredInteractions();
    }

    /** {@inheritDoc} */
    @Override
    public int getId() {
        return this.interaction.getId();
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        this.interaction.setId(id);
        XmlEntryContext.getInstance().registerComplex(id, this);
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        return this.interaction.getSourceLocator();
    }

    /** {@inheritDoc} */
    @Override
    public void setSourceLocator(FileSourceLocator locator) {
        this.interaction.setSourceLocator(locator);
    }

    /**
     * <p>getWrappedInteraction.</p>
     *
     * @return a {@link ExtendedPsiXmlInteraction} object.
     */
    public ExtendedPsiXmlInteraction getWrappedInteraction(){
        return this.interaction;
    }

    /**
     * <p>initialiseParticipants.</p>
     */
    protected void initialiseParticipants(){
        this.modelledParticipants = new SynchronizedModelledParticipantList();
        for (Object part : this.interaction.getParticipants()){
            this.modelledParticipants.addOnly(new XmlParticipantWrapper((ExtendedPsiXmlParticipant)part, this));
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return interaction.toString();
    }

    ////////////////////////////////////// classes
    private class SynchronizedModelledParticipantList extends AbstractListHavingProperties<ModelledParticipant>{

        @Override
        protected void processAddedObjectEvent(ModelledParticipant added) {
            interaction.getParticipants().add(added);
        }

        @Override
        protected void processRemovedObjectEvent(ModelledParticipant removed) {
            interaction.getParticipants().remove(removed);
        }

        @Override
        protected void clearProperties() {
            interaction.getParticipants().clear();
        }
    }
}


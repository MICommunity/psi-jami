package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.Entry;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import java.util.*;

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

    /**
     * <p>Constructor for XmlBasicInteractionComplexWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction} object.
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
     * @return a {@link java.util.Date} object.
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
     * @return a {@link java.util.Date} object.
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
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
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

    /**
     * <p>Getter for the field <code>interactionEvidences</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
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
     * <p>Getter for the field <code>modelledConfidences</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
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
     * @return a {@link java.util.Collection} object.
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
     * @return a {@link java.util.Collection} object.
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
            this.interaction.getAnnotations().add(new XmlAnnotation(CvTermUtils.createMICvTerm(Annotation.COMPLEX_PROPERTIES, Annotation.COMPLEX_PROPERTIES_MI), properties));
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
    public List<InferredInteraction> getInferredInteractions() {
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
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction} object.
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


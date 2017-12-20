package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.Entry;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Wrapper for complexes that were loaded as abstract interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlTransient
public class XmlModelledInteractionComplexWrapper implements Complex, FileSourceContext, ExtendedPsiXmlModelledInteraction {

    private ExtendedPsiXmlModelledInteraction modelledInteraction;
    private Organism organism;
    private CvTerm interactorType;

    /**
     * <p>Constructor for XmlModelledInteractionComplexWrapper.</p>
     *
     * @param modelled a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlModelledInteraction} object.
     */
    public XmlModelledInteractionComplexWrapper(ExtendedPsiXmlModelledInteraction modelled){
        if (modelled == null){
            throw new IllegalArgumentException("The complex wrapper needs a non null xmlModelledInteraction");
        }
        this.modelledInteraction = modelled;
        this.interactorType = new XmlCvTerm(Complex.COMPLEX, new XmlXref(CvTermUtils.createPsiMiDatabase(),Complex.COMPLEX_MI, CvTermUtils.createIdentityQualifier()));

        // add the new generated complex in the referenced complexes
        XmlEntryContext.getInstance().registerComplex(modelled.getId(), this);
    }

    /**
     * <p>getUpdatedDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getUpdatedDate() {
        return this.modelledInteraction.getUpdatedDate();
    }

    /** {@inheritDoc} */
    public void setUpdatedDate(Date updated) {
        this.modelledInteraction.setUpdatedDate(updated);
    }

    /**
     * <p>getCreatedDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getCreatedDate() {
        return this.modelledInteraction.getCreatedDate();
    }

    /** {@inheritDoc} */
    public void setCreatedDate(Date created) {
        this.modelledInteraction.setCreatedDate(created);
    }

    /**
     * <p>getInteractionType.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getInteractionType() {
        return this.modelledInteraction.getInteractionType();
    }

    /** {@inheritDoc} */
    public void setInteractionType(CvTerm term) {
        this.modelledInteraction.setInteractionType(term);
    }

    /**
     * <p>addParticipant.</p>
     *
     * @param part a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @return a boolean.
     */
    public boolean addParticipant(ModelledParticipant part) {
        return this.modelledInteraction.addParticipant(part);
    }

    /**
     * <p>removeParticipant.</p>
     *
     * @param part a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @return a boolean.
     */
    public boolean removeParticipant(ModelledParticipant part) {
        return this.modelledInteraction.removeParticipant(part);
    }

    /** {@inheritDoc} */
    public boolean addAllParticipants(Collection<? extends ModelledParticipant> participants) {
        return this.modelledInteraction.addAllParticipants(participants);
    }

    /** {@inheritDoc} */
    public boolean removeAllParticipants(Collection<? extends ModelledParticipant> participants) {
        return this.modelledInteraction.removeAllParticipants(participants);
    }

    /**
     * <p>getParticipants.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledParticipant> getParticipants() {
        return this.modelledInteraction.getParticipants();
    }

    /**
     * <p>getInteractionEvidences.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<InteractionEvidence> getInteractionEvidences() {
        return this.modelledInteraction.getInteractionEvidences();
    }

    /**
     * <p>getSource.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public Source getSource() {
        return this.modelledInteraction.getSource();
    }

    /** {@inheritDoc} */
    public void setSource(Source source) {
        this.modelledInteraction.setSource(source);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isIntraMolecular() {
        return this.modelledInteraction.isIntraMolecular();
    }

    /** {@inheritDoc} */
    @Override
    public void setIntraMolecular(boolean intra) {
        this.modelledInteraction.setIntraMolecular(intra);
    }

    /**
     * <p>getModelledConfidences.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledConfidence> getModelledConfidences() {
        return this.modelledInteraction.getModelledConfidences();
    }

    /**
     * <p>getModelledParameters.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledParameter> getModelledParameters() {
        return this.modelledInteraction.getModelledParameters();
    }

    /**
     * <p>getCooperativeEffects.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CooperativeEffect> getCooperativeEffects() {
        return this.modelledInteraction.getCooperativeEffects();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Annotation> getAnnotations() {
        return this.modelledInteraction.getAnnotations();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Checksum> getChecksums() {
        return this.modelledInteraction.getChecksums();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getXrefs() {
        return this.modelledInteraction.getXrefs();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getIdentifiers() {
        return this.modelledInteraction.getIdentifiers();
    }

    /** {@inheritDoc} */
    @Override
    public String getShortName() {
        return this.modelledInteraction.getShortName() != null ? this.modelledInteraction.getShortName() : PsiXmlUtils.UNSPECIFIED;
    }

    /** {@inheritDoc} */
    @Override
    public void setShortName(String name) {
        this.modelledInteraction.setShortName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getFullName() {
        return this.modelledInteraction.getFullName();
    }

    /** {@inheritDoc} */
    @Override
    public void setFullName(String name) {
        this.modelledInteraction.setFullName(name);
    }

    /** {@inheritDoc} */
    @Override
    public Xref getPreferredIdentifier() {
        return !this.modelledInteraction.getIdentifiers().isEmpty() ? this.modelledInteraction.getIdentifiers().iterator().next() : null;
    }

    /** {@inheritDoc} */
    @Override
    public Organism getOrganism() {
        if (this.organism == null && !this.modelledInteraction.getExperiments().isEmpty()){
           for (Experiment exp : this.modelledInteraction.getExperiments()){
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
        return this.modelledInteraction.getRigid();
    }

    /** {@inheritDoc} */
    @Override
    public void setRigid(String rigid) {
        this.modelledInteraction.setRigid(rigid);
    }

    /** {@inheritDoc} */
    @Override
    public String getPhysicalProperties() {
        Annotation properties = AnnotationUtils.collectFirstAnnotationWithTopic(this.modelledInteraction.getAnnotations(), Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
        return properties != null ? properties.getValue() : null;
    }

    /** {@inheritDoc} */
    @Override
    public void setPhysicalProperties(String properties) {
        Annotation propertiesAnnot = AnnotationUtils.collectFirstAnnotationWithTopic(this.modelledInteraction.getAnnotations(), Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
        if (propertiesAnnot != null){
            propertiesAnnot.setValue(properties);
        }
        else{
            this.modelledInteraction.getAnnotations().add(new XmlAnnotation(CvTermUtils.createMICvTerm(Annotation.COMPLEX_PROPERTIES, Annotation.COMPLEX_PROPERTIES_MI), properties));
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getRecommendedName() {
        Alias recommendedName = AliasUtils.collectFirstAliasWithType(this.modelledInteraction.getAliases(), Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
        return recommendedName != null ? recommendedName.getName() : null;
    }

    /** {@inheritDoc} */
    @Override
    public void setRecommendedName(String name) {
        AliasUtils.removeAllAliasesWithType(this.modelledInteraction.getAliases(), Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
        if (name != null){
            this.modelledInteraction.getAliases().add(new XmlAlias(name, CvTermUtils.createMICvTerm(Alias.COMPLEX_RECOMMENDED_NAME, Alias.COMPLEX_RECOMMENDED_NAME_MI)));
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getSystematicName() {
        Alias systematicName = AliasUtils.collectFirstAliasWithType(this.modelledInteraction.getAliases(), Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
        return systematicName != null ? systematicName.getName() : null;
    }

    /** {@inheritDoc} */
    @Override
    public void setSystematicName(String name) {
        AliasUtils.removeAllAliasesWithType(this.modelledInteraction.getAliases(), Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
        if (name != null){
            this.modelledInteraction.getAliases().add(new XmlAlias(name, CvTermUtils.createMICvTerm(Alias.COMPLEX_SYSTEMATIC_NAME, Alias.COMPLEX_SYSTEMATIC_NAME_MI)));
        }
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Alias> getAliases() {
        return this.modelledInteraction.getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public List<CvTerm> getInteractionTypes() {
        return this.modelledInteraction.getInteractionTypes();
    }

    /** {@inheritDoc} */
    @Override
    public Entry getEntry() {
        return this.modelledInteraction.getEntry();
    }

    /** {@inheritDoc} */
    @Override
    public void setEntry(Entry entry) {
        this.modelledInteraction.setEntry(entry);
    }

    /** {@inheritDoc} */
    @Override
    public List<InferredInteraction> getInferredInteractions() {
        return this.modelledInteraction.getInferredInteractions();
    }

    /** {@inheritDoc} */
    @Override
    public int getId() {
        return this.modelledInteraction.getId();
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        this.modelledInteraction.setId(id);
        XmlEntryContext.getInstance().registerComplex(id, this);
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        return this.modelledInteraction.getSourceLocator();
    }

    /** {@inheritDoc} */
    @Override
    public void setSourceLocator(FileSourceLocator locator) {
        this.modelledInteraction.setSourceLocator(locator);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.modelledInteraction.toString();
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getEvidenceType() {
        return this.modelledInteraction.getEvidenceType();
    }

    /** {@inheritDoc} */
    @Override
    public void setEvidenceType(CvTerm eco) {
        this.modelledInteraction.setEvidenceType(eco);
    }

    /** {@inheritDoc} */
    @Override
    public List<Experiment> getExperiments() {
        return this.modelledInteraction.getExperiments();
    }
}

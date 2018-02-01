package psidev.psi.mi.jami.xml.model.extension.binary.xml30;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultXref;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.xml.model.extension.XmlAlias;
import psidev.psi.mi.jami.xml.model.extension.XmlAnnotation;
import psidev.psi.mi.jami.xml.model.extension.XmlCvTerm;
import psidev.psi.mi.jami.xml.model.extension.XmlXref;
import psidev.psi.mi.jami.xml.model.extension.binary.AbstractXmlBinaryInteraction;
import psidev.psi.mi.jami.xml.model.extension.xml300.BindingFeatures;
import psidev.psi.mi.jami.xml.model.extension.xml300.ExtendedPsiXmlCausalRelationship;
import psidev.psi.mi.jami.xml.model.extension.xml300.ExtendedPsiXmlModelledInteraction;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Xml implementation of ModelledInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
@XmlTransient
public class XmlModelledBinaryInteraction extends AbstractXmlBinaryInteraction<ModelledParticipant>
        implements ModelledBinaryInteraction, ExtendedPsiXmlModelledInteraction {

    private Collection<InteractionEvidence> interactionEvidences;
    private Source source;
    private Collection<ModelledConfidence> modelledConfidences;
    private Collection<ModelledParameter> modelledParameters;
    private Collection<CooperativeEffect> cooperativeEffects;
    private CvTerm evidenceType;
    private List<BindingFeatures> bindingFeatures;
    private List<ExtendedPsiXmlCausalRelationship> causalRelationships;
    private Organism organism;
    private CvTerm interactorType;
    private Xref complexAcXref;

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     */
    public XmlModelledBinaryInteraction() {
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledBinaryInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public XmlModelledBinaryInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public XmlModelledBinaryInteraction(ModelledParticipant participantA, ModelledParticipant participantB) {
        super(participantA, participantB);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public XmlModelledBinaryInteraction(String shortName, ModelledParticipant participantA, ModelledParticipant participantB) {
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     */
    public XmlModelledBinaryInteraction(String shortName, CvTerm type, ModelledParticipant participantA, ModelledParticipant participantB) {
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledBinaryInteraction(CvTerm complexExpansion) {
        super(complexExpansion);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledBinaryInteraction(String shortName, CvTerm type, CvTerm complexExpansion) {
        super(shortName, type, complexExpansion);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledBinaryInteraction(ModelledParticipant participantA, ModelledParticipant participantB, CvTerm complexExpansion) {
        super(participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledBinaryInteraction(String shortName, ModelledParticipant participantA, ModelledParticipant participantB, CvTerm complexExpansion) {
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for XmlModelledBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledBinaryInteraction(String shortName, CvTerm type, ModelledParticipant participantA, ModelledParticipant participantB, CvTerm complexExpansion) {
        super(shortName, type, participantA, participantB, complexExpansion);
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
        this.cooperativeEffects = new ArrayList<CooperativeEffect>();
    }

    /**
     * <p>initialiseModelledConfidences.</p>
     */
    protected void initialiseModelledConfidences(){
        this.modelledConfidences = new ArrayList<ModelledConfidence>();
    }

    /**
     * <p>initialiseModelledParameters.</p>
     */
    protected void initialiseModelledParameters(){
        this.modelledParameters = new ArrayList<ModelledParameter>();
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
     * <p>Getter for the field <code>modelledConfidences</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledConfidence> getModelledConfidences() {
        if (modelledConfidences == null){
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
        if (modelledParameters == null){
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
        if (cooperativeEffects == null){
            initialiseCooperativeEffects();
        }
        return this.cooperativeEffects;
    }

    /**
     * <p>Getter for the field <code>evidenceType</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getEvidenceType() {
        return evidenceType;
    }

    /** {@inheritDoc} */
    public void setEvidenceType(CvTerm evidenceType) {
        this.evidenceType = evidenceType;
    }

    /** {@inheritDoc} */
    @Override
    public List<BindingFeatures> getBindingFeatures() {
        if (this.bindingFeatures == null){
            this.bindingFeatures = new ArrayList<BindingFeatures>();
        }
        return this.bindingFeatures;
    }

    /** {@inheritDoc} */
    @Override
    public List<ExtendedPsiXmlCausalRelationship> getCausalRelationships() {
        if (this.causalRelationships == null){
            this.causalRelationships = new ArrayList<ExtendedPsiXmlCausalRelationship>();
        }
        return this.causalRelationships;
    }

    /** {@inheritDoc} */
    @Override
    public String getComplexAc() {
        Collection<Xref> complexAcs = XrefUtils.collectAllXrefsHavingDatabaseAndQualifier(getXrefs(), Xref.COMPLEX_PORTAL_MI, Xref.COMPLEX_PORTAL, Xref.COMPLEX_PRIMARY_MI, Xref.COMPLEX_PRIMARY);
        return complexAcs != null && !complexAcs.isEmpty() ? complexAcs.iterator().next().getId() : null;
    }

    /** {@inheritDoc} */
    @Override
    public String getComplexVersion() {
        Collection<Xref> complexAcs = XrefUtils.collectAllXrefsHavingDatabaseAndQualifier(getXrefs(), Xref.COMPLEX_PORTAL_MI, Xref.COMPLEX_PORTAL, Xref.COMPLEX_PRIMARY_MI, Xref.COMPLEX_PRIMARY);
        return complexAcs != null && !complexAcs.isEmpty() ? complexAcs.iterator().next().getVersion(): null;
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
        Annotation properties = AnnotationUtils.collectFirstAnnotationWithTopic(getAnnotations(), Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
        return properties != null ? properties.getValue() : null;
    }

    /** {@inheritDoc} */
    @Override
    public void setPhysicalProperties(String properties) {
        Annotation propertiesAnnot = AnnotationUtils.collectFirstAnnotationWithTopic(getAnnotations(), Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
        if (propertiesAnnot != null){
            propertiesAnnot.setValue(properties);
        }
        else{
            getAnnotations().add(new XmlAnnotation(CvTermUtils.createMICvTerm(Annotation.COMPLEX_PROPERTIES, Annotation.COMPLEX_PROPERTIES_MI), properties));
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getRecommendedName() {
        Alias recommendedName = AliasUtils.collectFirstAliasWithType(getAliases(), Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
        return recommendedName != null ? recommendedName.getName() : null;
    }

    /** {@inheritDoc} */
    @Override
    public void setRecommendedName(String name) {
        AliasUtils.removeAllAliasesWithType(getAliases(), Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
        if (name != null){
            getAliases().add(new XmlAlias(name, CvTermUtils.createMICvTerm(Alias.COMPLEX_RECOMMENDED_NAME, Alias.COMPLEX_RECOMMENDED_NAME_MI)));
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getSystematicName() {
        Alias systematicName = AliasUtils.collectFirstAliasWithType(getAliases(), Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
        return systematicName != null ? systematicName.getName() : null;
    }

    /** {@inheritDoc} */
    @Override
    public void setSystematicName(String name) {
        AliasUtils.removeAllAliasesWithType(getAliases(), Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
        if (name != null){
            getAliases().add(new XmlAlias(name, CvTermUtils.createMICvTerm(Alias.COMPLEX_SYSTEMATIC_NAME, Alias.COMPLEX_SYSTEMATIC_NAME_MI)));
        }
    }

    /** {@inheritDoc} */
    @Override
    public Xref getPreferredIdentifier() {
        return !getIdentifiers().isEmpty()?getIdentifiers().iterator().next():null;
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
            this.interactorType = new XmlCvTerm(Complex.COMPLEX, new XmlXref(CvTermUtils.createPsiMiDatabase(),Complex.COMPLEX_MI,
                    CvTermUtils.createIdentityQualifier()));
        }
        else{
            this.interactorType = type;
        }
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Alias> getAliases() {
        return super.getAliases();
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

    /** {@inheritDoc} */
    @Override
    public Collection<Checksum> getChecksums() {
        return super.getChecksums();
    }
}

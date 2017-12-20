package psidev.psi.mi.jami.xml.model.reference;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.model.Entry;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction;
import psidev.psi.mi.jami.xml.model.extension.InferredInteraction;
import psidev.psi.mi.jami.xml.model.extension.XmlComplex;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class for references to a complex
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
public abstract class AbstractComplexRef extends AbstractInteractorRef implements Complex, ExtendedPsiXmlInteraction<ModelledParticipant> {
    private static final Logger logger = Logger.getLogger("AbstractComplexRef");

    /**
     * <p>Constructor for AbstractComplexRef.</p>
     *
     * @param ref a int.
     */
    public AbstractComplexRef(int ref) {
        super(ref);
    }

    /** {@inheritDoc} */
    @Override
    public void setEvidenceType(CvTerm eco) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        getDelegate().setEvidenceType(eco);
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getEvidenceType() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getEvidenceType();
    }

    /**
     * <p>getPhysicalProperties.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPhysicalProperties() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getPhysicalProperties();
    }

    /** {@inheritDoc} */
    public void setPhysicalProperties(String properties) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        getDelegate().setPhysicalProperties(properties);
    }

    /**
     * <p>getInteractionEvidences.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<InteractionEvidence> getInteractionEvidences() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getInteractionEvidences();
    }

    /**
     * <p>getSource.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public Source getSource() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getSource();
    }

    /** {@inheritDoc} */
    public void setSource(Source source) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        getDelegate().setSource(source);
    }

    /**
     * <p>getModelledConfidences.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledConfidence> getModelledConfidences() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getModelledConfidences();
    }

    /**
     * <p>getModelledParameters.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledParameter> getModelledParameters() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getModelledParameters();
    }

    /**
     * <p>getCooperativeEffects.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CooperativeEffect> getCooperativeEffects() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getCooperativeEffects();
    }

    /**
     * <p>getRigid.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRigid() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getRigid();
    }

    /** {@inheritDoc} */
    public void setRigid(String rigid) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        getDelegate().setRigid(rigid);
    }

    /**
     * <p>getUpdatedDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getUpdatedDate() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getUpdatedDate();
    }

    /** {@inheritDoc} */
    public void setUpdatedDate(Date updated) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        getDelegate().setUpdatedDate(updated);
    }

    /**
     * <p>getCreatedDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getCreatedDate() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getCreatedDate();
    }

    /** {@inheritDoc} */
    public void setCreatedDate(Date created) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        getDelegate().setCreatedDate(created);
    }

    /**
     * <p>getInteractionType.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getInteractionType() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getInteractionType();
    }

    /** {@inheritDoc} */
    public void setInteractionType(CvTerm term) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        getDelegate().setInteractionType(term);
    }

    /**
     * <p>getParticipants.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<ModelledParticipant> getParticipants() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getParticipants();
    }

    /**
     * <p>addParticipant.</p>
     *
     * @param part a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @return a boolean.
     */
    public boolean addParticipant(ModelledParticipant part) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().addParticipant(part);
    }

    /**
     * <p>removeParticipant.</p>
     *
     * @param part a {@link psidev.psi.mi.jami.model.ModelledParticipant} object.
     * @return a boolean.
     */
    public boolean removeParticipant(ModelledParticipant part) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().removeParticipant(part);
    }

    /** {@inheritDoc} */
    public boolean addAllParticipants(Collection<? extends ModelledParticipant> participants) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().addAllParticipants(participants);
    }

    /** {@inheritDoc} */
    public boolean removeAllParticipants(Collection<? extends ModelledParticipant> participants) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().removeAllParticipants(participants);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Annotation> getAnnotations() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getAnnotations();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Checksum> getChecksums() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getChecksums();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getXrefs() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getXrefs();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Alias> getAliases() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getIdentifiers() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getIdentifiers();
    }

    /** {@inheritDoc} */
    @Override
    public void setIntraMolecular(boolean intra) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        getDelegate().setIntraMolecular(intra);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isIntraMolecular() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().isIntraMolecular();
    }

    /** {@inheritDoc} */
    @Override
    public List<InferredInteraction> getInferredInteractions() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getInferredInteractions();
    }

    /** {@inheritDoc} */
    @Override
    public void setEntry(Entry entry) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        getDelegate().setEntry(entry);
    }

    /** {@inheritDoc} */
    @Override
    public Entry getEntry() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getEntry();
    }

    /** {@inheritDoc} */
    @Override
    public List<CvTerm> getInteractionTypes() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getInteractionTypes();
    }

    /** {@inheritDoc} */
    @Override
    public void setSystematicName(String name) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        getDelegate().setSystematicName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getSystematicName() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getSystematicName();
    }

    /** {@inheritDoc} */
    @Override
    public void setRecommendedName(String name) {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        getDelegate().setRecommendedName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getRecommendedName() {
        logger.log(Level.WARNING, "The interaction reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseInteractorDelegate();
        }
        return getDelegate().getRecommendedName();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Interaction Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInteractorDelegate() {
        XmlComplex complex = new XmlComplex(PsiXmlUtils.UNSPECIFIED);
        complex.setId(this.ref);
        setDelegate(complex);
    }

    /** {@inheritDoc} */
    @Override
    protected XmlComplex getDelegate() {
        return (XmlComplex) super.getDelegate();
    }
}

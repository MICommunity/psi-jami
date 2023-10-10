package psidev.psi.mi.jami.xml.model.reference.xml253;

import psidev.psi.mi.jami.listener.EntityInteractorChangeListener;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipant;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract participant reference
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
public abstract class AbstractParticipantRef<I extends Interaction, T extends Feature> extends AbstractEntityRef<T> implements ExtendedPsiXmlParticipant<I,T> {
    private static final Logger logger = Logger.getLogger("AbstractParticipantRef");

    /**
     * <p>Constructor for AbstractParticipantRef.</p>
     *
     * @param ref a int.
     */
    public AbstractParticipantRef(int ref) {
        super(ref);
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractionAndAddParticipant(I interaction) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        getDelegate().setInteractionAndAddParticipant(interaction);
    }

    /** {@inheritDoc} */
    @Override
    public I getInteraction() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().getInteraction();
    }

    /** {@inheritDoc} */
    @Override
    public void setInteraction(I interaction) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        getDelegate().setInteraction(interaction);
    }

    /**
     * <p>getBiologicalRole.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getBiologicalRole() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().getBiologicalRole();
    }

    /** {@inheritDoc} */
    public void setBiologicalRole(CvTerm bioRole) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        getDelegate().setBiologicalRole(bioRole);
    }

    /**
     * The biological effect of the participant in a causal interaction.
     * It is a controlled vocabulary term and can be null.
     *
     * @return the biological effect
     */
    public CvTerm getBiologicalEffect(){
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().getBiologicalEffect();
    }

    /**
     * Sets the biological effect of the participant in a causal interaction.
     *
     * @param biologicalEffect : biological effect
     */
    public void setBiologicalEffect(CvTerm biologicalEffect){
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
        initialiseParticipantDelegate();
    }
    getDelegate().setBiologicalEffect(biologicalEffect);
}



    /**
     * <p>getXrefs.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getXrefs() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().getXrefs();
    }

    /**
     * <p>getAnnotations.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().getAnnotations();
    }

    /**
     * <p>getAliases.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Alias> getAliases() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().getAliases();
    }

    /**
     * <p>getStoichiometry.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public Stoichiometry getStoichiometry() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().getStoichiometry();
    }

    /** {@inheritDoc} */
    public void setStoichiometry(Integer stoichiometry) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        getDelegate().setStoichiometry(stoichiometry);
    }

    /** {@inheritDoc} */
    public void setStoichiometry(Stoichiometry stoichiometry) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        getDelegate().setStoichiometry(stoichiometry);
    }

    /**
     * <p>getFeatures.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<T> getFeatures() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().getFeatures();
    }

    /**
     * <p>getChangeListener.</p>
     *
     * @return a {@link psidev.psi.mi.jami.listener.EntityInteractorChangeListener} object.
     */
    public EntityInteractorChangeListener getChangeListener() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().getChangeListener();
    }

    /** {@inheritDoc} */
    public void setChangeListener(EntityInteractorChangeListener listener) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        getDelegate().setChangeListener(listener);
    }

    /**
     * <p>addFeature.</p>
     *
     * @param feature a T object.
     * @return a boolean.
     */
    public boolean addFeature(T feature) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().addFeature(feature);
    }

    /**
     * <p>removeFeature.</p>
     *
     * @param feature a T object.
     * @return a boolean.
     */
    public boolean removeFeature(T feature) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().removeFeature(feature);
    }

    /** {@inheritDoc} */
    public boolean addAllFeatures(Collection<? extends T> features) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().addAllFeatures(features);
    }

    /** {@inheritDoc} */
    public boolean removeAllFeatures(Collection<? extends T> features) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().removeAllFeatures(features);
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        getDelegate().setId(id);
    }

    /** {@inheritDoc} */
    @Override
    public int getId() {
        return getDelegate() != null ? getDelegate().getId() : this.ref;
    }

    /** {@inheritDoc} */
    @Override
    public String getShortName() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().getShortName();
    }

    /** {@inheritDoc} */
    @Override
    public void setShortName(String name) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        getDelegate().setShortName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getFullName() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        return getDelegate().getFullName();
    }

    /** {@inheritDoc} */
    @Override
    public void setFullName(String name) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (getDelegate() == null){
            initialiseParticipantDelegate();
        }
        getDelegate().setFullName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Participant Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
    }

    /** {@inheritDoc} */
    @Override
    protected ExtendedPsiXmlParticipant<I,T> getDelegate() {
        return (ExtendedPsiXmlParticipant<I,T>)super.getDelegate();
    }
}

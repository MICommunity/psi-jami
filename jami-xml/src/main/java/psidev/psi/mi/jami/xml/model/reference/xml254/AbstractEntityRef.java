package psidev.psi.mi.jami.xml.model.reference.xml254;

import psidev.psi.mi.jami.listener.EntityInteractorChangeListener;
import psidev.psi.mi.jami.model.CausalRelationship;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.Stoichiometry;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlEntity;
import psidev.psi.mi.jami.xml.model.reference.AbstractXmlIdReference;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract entity reference
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
public abstract class AbstractEntityRef<T extends Feature> extends AbstractXmlIdReference implements ExtendedPsiXmlEntity<T> {
    private static final Logger logger = Logger.getLogger("AbstractEntityRef");
    private ExtendedPsiXmlEntity<T> delegate;

    /**
     * <p>Constructor for AbstractEntityRef.</p>
     *
     * @param ref a int.
     */
    public AbstractEntityRef(int ref) {
        super(ref);
    }

    /**
     * <p>getInteractor.</p>
     *
     * @return a {@link Interactor} object.
     */
    public Interactor getInteractor() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        return this.delegate.getInteractor();
    }

    /** {@inheritDoc} */
    public void setInteractor(Interactor interactor) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        this.delegate.setInteractor(interactor);
    }

    /**
     * <p>getCausalRelationships.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<CausalRelationship> getCausalRelationships() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        return this.delegate.getCausalRelationships();
    }

    /**
     * <p>getStoichiometry.</p>
     *
     * @return a {@link Stoichiometry} object.
     */
    public Stoichiometry getStoichiometry() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        return this.delegate.getStoichiometry();
    }

    /** {@inheritDoc} */
    public void setStoichiometry(Integer stoichiometry) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        this.delegate.setStoichiometry(stoichiometry);
    }

    /**
     * <p>setStoichiometry.</p>
     *
     * @param stoichiometry a {@link Stoichiometry} object.
     */
    public void setStoichiometry(Stoichiometry stoichiometry) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        this.delegate.setStoichiometry(stoichiometry);
    }

    /**
     * <p>getFeatures.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<T> getFeatures() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        return this.delegate.getFeatures();
    }

    /**
     * <p>getChangeListener.</p>
     *
     * @return a {@link EntityInteractorChangeListener} object.
     */
    public EntityInteractorChangeListener getChangeListener() {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        return this.delegate.getChangeListener();
    }

    /** {@inheritDoc} */
    public void setChangeListener(EntityInteractorChangeListener listener) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        this.delegate.setChangeListener(listener);
    }

    /**
     * <p>addFeature.</p>
     *
     * @param feature a T object.
     * @return a boolean.
     */
    public boolean addFeature(T feature) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        return this.delegate.addFeature(feature);
    }

    /**
     * <p>removeFeature.</p>
     *
     * @param feature a T object.
     * @return a boolean.
     */
    public boolean removeFeature(T feature) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        return this.delegate.removeFeature(feature);
    }

    /** {@inheritDoc} */
    public boolean addAllFeatures(Collection<? extends T> features) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        return this.delegate.addAllFeatures(features);
    }

    /** {@inheritDoc} */
    public boolean removeAllFeatures(Collection<? extends T> features) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        return this.delegate.removeAllFeatures(features);
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        logger.log(Level.WARNING, "The participant reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseParticipantDelegate();
        }
        this.delegate.setId(id);
    }

    /** {@inheritDoc} */
    @Override
    public int getId() {
        return this.delegate != null ? this.delegate.getId() : this.ref;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Participant Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
    }

    /**
     * <p>initialiseParticipantDelegate.</p>
     */
    protected abstract void initialiseParticipantDelegate();

    /**
     * <p>Getter for the field <code>delegate</code>.</p>
     *
     * @return a {@link ExtendedPsiXmlEntity} object.
     */
    protected ExtendedPsiXmlEntity<T> getDelegate() {
        return delegate;
    }

    /**
     * <p>Setter for the field <code>delegate</code>.</p>
     *
     * @param delegate a {@link ExtendedPsiXmlEntity} object.
     */
    protected void setDelegate(ExtendedPsiXmlEntity<T> delegate) {
        this.delegate = delegate;
    }
}

package psidev.psi.mi.jami.xml.model.reference;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeature;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract feature reference
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
public abstract class AbstractFeatureRef<E extends Entity, F extends Feature> extends AbstractXmlIdReference implements ExtendedPsiXmlFeature<E,F> {
    private static final Logger logger = Logger.getLogger("AbstractFeatureRef");
    private ExtendedPsiXmlFeature<E,F> delegate;

    /**
     * <p>Constructor for AbstractFeatureRef.</p>
     *
     * @param ref a int.
     */
    public AbstractFeatureRef(int ref) {
        super(ref);
    }

    /**
     * <p>getShortName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getShortName() {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        return this.delegate.getShortName();
    }

    /** {@inheritDoc} */
    public void setShortName(String name) {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        this.delegate.setShortName(name);
    }

    /**
     * <p>getFullName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFullName() {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        return this.delegate.getFullName();
    }

    /** {@inheritDoc} */
    public void setFullName(String name) {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        this.delegate.setFullName(name);
    }

    /**
     * <p>getInterpro.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getInterpro() {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        return this.delegate.getInterpro();
    }

    /** {@inheritDoc} */
    public void setInterpro(String interpro) {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        this.delegate.setInterpro(interpro);
    }

    /**
     * <p>getIdentifiers.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getIdentifiers() {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        return this.delegate.getIdentifiers();
    }

    /**
     * <p>getXrefs.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getXrefs() {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        return this.delegate.getXrefs();
    }

    /**
     * <p>getAnnotations.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        return this.delegate.getAnnotations();
    }

    /**
     * <p>getType.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getType() {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        return this.delegate.getType();
    }

    /** {@inheritDoc} */
    public void setType(CvTerm type) {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        this.delegate.setType(type);
    }

    /**
     * <p>getRanges.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Range> getRanges() {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        return this.delegate.getRanges();
    }

    /**
     * <p>getRole.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getRole() {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        return this.delegate.getRole();
    }

    /** {@inheritDoc} */
    public void setRole(CvTerm effect) {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        this.delegate.setRole(effect);
    }

    /**
     * <p>getParticipant.</p>
     *
     * @return a E object.
     */
    public E getParticipant() {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        return this.delegate.getParticipant();
    }

    /**
     * <p>setParticipant.</p>
     *
     * @param participant a E object.
     */
    public void setParticipant(E participant) {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        this.delegate.setParticipant(participant);
    }

    /**
     * <p>setParticipantAndAddFeature.</p>
     *
     * @param participant a E object.
     */
    public void setParticipantAndAddFeature(E participant) {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        this.delegate.setParticipantAndAddFeature(participant);
    }

    /**
     * <p>getLinkedFeatures.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<F> getLinkedFeatures() {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        return this.delegate.getLinkedFeatures();
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
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
    public Collection<Alias> getAliases() {
        logger.log(Level.WARNING, "The feature reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseFeatureDelegate();
        }
        return this.delegate.getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Feature Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
    }
    /**
     * <p>initialiseFeatureDelegate.</p>
     */
    protected abstract void initialiseFeatureDelegate();

    /**
     * <p>Getter for the field <code>delegate</code>.</p>
     *
     * @return a {@link ExtendedPsiXmlFeature} object.
     */
    protected ExtendedPsiXmlFeature<E, F> getDelegate() {
        return delegate;
    }

    /**
     * <p>Setter for the field <code>delegate</code>.</p>
     *
     * @param delegate a {@link ExtendedPsiXmlFeature} object.
     */
    protected void setDelegate(ExtendedPsiXmlFeature<E, F> delegate) {
        this.delegate = delegate;
    }
}

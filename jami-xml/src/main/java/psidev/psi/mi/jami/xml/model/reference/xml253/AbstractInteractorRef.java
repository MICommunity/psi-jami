package psidev.psi.mi.jami.xml.model.reference.xml253;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractor;
import psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlInteractor;
import psidev.psi.mi.jami.xml.model.reference.AbstractXmlIdReference;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of XmlIdReference for interactors
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
public abstract class AbstractInteractorRef extends AbstractXmlIdReference implements ExtendedPsiXmlInteractor {
    private static final Logger logger = Logger.getLogger("AbstractInteractorRef");
    private ExtendedPsiXmlInteractor delegate;

    /**
     * <p>Constructor for AbstractInteractorRef.</p>
     *
     * @param ref a int.
     */
    public AbstractInteractorRef(int ref) {
        super(ref);
    }

    /**
     * <p>getShortName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getShortName() {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        return this.delegate.getShortName();
    }

    /** {@inheritDoc} */
    public void setShortName(String name) {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        this.delegate.setShortName(name);
    }

    /**
     * <p>getFullName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFullName() {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        return this.delegate.getFullName();
    }

    /** {@inheritDoc} */
    public void setFullName(String name) {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        this.delegate.setFullName(name);
    }

    /**
     * <p>getIdentifiers.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getIdentifiers() {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        return this.delegate.getIdentifiers();
    }

    /**
     * <p>getPreferredIdentifier.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public Xref getPreferredIdentifier() {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        return this.delegate.getPreferredIdentifier();
    }

    /**
     * <p>getPreferredName.</p>
     *
     * @return a String.
     */
    public String getPreferredName() {
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        return this.delegate.getPreferredName();
    }

    /**
     * <p>getChecksums.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Checksum> getChecksums() {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        return this.delegate.getChecksums();
    }

    /**
     * <p>getXrefs.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getXrefs() {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        return this.delegate.getXrefs();
    }

    /**
     * <p>getAnnotations.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        return this.delegate.getAnnotations();
    }

    /**
     * <p>getAliases.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Alias> getAliases() {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        return this.delegate.getAliases();
    }

    /**
     * <p>getOrganism.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public Organism getOrganism() {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        return this.delegate.getOrganism();
    }

    /** {@inheritDoc} */
    public void setOrganism(Organism organism) {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        this.delegate.setOrganism(organism);
    }

    /**
     * <p>getInteractorType.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getInteractorType() {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        return this.delegate.getInteractorType();
    }

    /** {@inheritDoc} */
    public void setInteractorType(CvTerm type) {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
        }
        this.delegate.setInteractorType(type);
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        logger.log(Level.WARNING, "The interactor reference "+ref+" is not resolved. Some default properties will be initialised by default");
        if (this.delegate == null){
            initialiseInteractorDelegate();
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
        return "Interactor Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
    }
    /**
     * <p>initialiseInteractorDelegate.</p>
     */
    protected void initialiseInteractorDelegate(){
        this.delegate = new DefaultXmlInteractor();
        this.delegate.setId(this.ref);
    }

    /**
     * <p>Getter for the field <code>delegate</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractor} object.
     */
    protected ExtendedPsiXmlInteractor getDelegate() {
        return delegate;
    }

    /**
     * <p>Setter for the field <code>delegate</code>.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractor} object.
     */
    protected void setDelegate(ExtendedPsiXmlInteractor delegate) {
        this.delegate = delegate;
    }
}

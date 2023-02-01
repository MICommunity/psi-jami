package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.BioactiveEntity;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultBioactiveEntity;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractor;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

/**
 * Xml implementation of BioactiveEntity
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>24/07/13</pre>
 */
@XmlTransient
public class XmlBioactiveEntity extends DefaultBioactiveEntity implements ExtendedPsiXmlInteractor,FileSourceContext {

    private int id;
    private PsiXmlLocator sourceLocator;

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBioactiveEntity(String name, CvTerm type) {
        super(name, type != null ? type : new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier())));
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBioactiveEntity(String name, String fullName, CvTerm type) {
        super(name, fullName, type != null ? type : new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier())));
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public XmlBioactiveEntity(String name, CvTerm type, Organism organism) {
        super(name, type != null ? type : new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier())), organism);
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public XmlBioactiveEntity(String name, String fullName, CvTerm type, Organism organism) {
        super(name, fullName, type != null ? type : new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier())), organism);
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlBioactiveEntity(String name, CvTerm type, Xref uniqueId) {
        super(name, type != null ? type : new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier())), uniqueId);
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlBioactiveEntity(String name, String fullName, CvTerm type, Xref uniqueId) {
        super(name, fullName, type != null ? type : new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier())), uniqueId);
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlBioactiveEntity(String name, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, type != null ? type : new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier())), organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlBioactiveEntity(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, fullName, type != null ? type : new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier())), organism, uniqueId);

    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueChebi a {@link java.lang.String} object.
     */
    public XmlBioactiveEntity(String name, String fullName, CvTerm type, String uniqueChebi) {
        super(name, fullName, type != null ? type : new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier())));
        if (uniqueChebi != null){
            setChebi(uniqueChebi);
        }
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueChebi a {@link java.lang.String} object.
     */
    public XmlBioactiveEntity(String name, CvTerm type, Organism organism, String uniqueChebi) {
        super(name, type != null ? type : new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier())), organism);
        if (uniqueChebi != null){
            setChebi(uniqueChebi);
        }
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueChebi a {@link java.lang.String} object.
     */
    public XmlBioactiveEntity(String name, String fullName, CvTerm type, Organism organism, String uniqueChebi) {
        super(name, fullName, type != null ? type : new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier())), organism);
        if (uniqueChebi != null){
            setChebi(uniqueChebi);
        }
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public XmlBioactiveEntity(String name) {
        super(name, new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier()))
        );
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public XmlBioactiveEntity(String name, String fullName) {
        super(name, fullName, new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier()))
        );
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public XmlBioactiveEntity(String name, Organism organism) {
        super(name, new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier()))
                , organism);
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public XmlBioactiveEntity(String name, String fullName, Organism organism) {
        super(name, fullName, new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier()))
                , organism);
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlBioactiveEntity(String name, Xref uniqueId) {
        super(name, new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier()))
                , uniqueId);
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlBioactiveEntity(String name, String fullName, Xref uniqueId) {
        super(name, fullName, new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier()))
                , uniqueId);
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlBioactiveEntity(String name, Organism organism, Xref uniqueId) {
        super(name, new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier()))
                , organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlBioactiveEntity(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier()))
                , organism, uniqueId);

    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueChebi a {@link java.lang.String} object.
     */
    public XmlBioactiveEntity(String name, String fullName, String uniqueChebi) {
        super(name, fullName, new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier()))
        );
        if (uniqueChebi != null){
            setChebi(uniqueChebi);
        }
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueChebi a {@link java.lang.String} object.
     */
    public XmlBioactiveEntity(String name, Organism organism, String uniqueChebi) {
        super(name, new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier()))
                , organism);
        if (uniqueChebi != null){
            setChebi(uniqueChebi);
        }
    }

    /**
     * <p>Constructor for XmlBioactiveEntity.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueChebi a {@link java.lang.String} object.
     */
    public XmlBioactiveEntity(String name, String fullName, Organism organism, String uniqueChebi) {
        super(name, fullName, new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier()))
                , organism);
        if (uniqueChebi != null){
            setChebi(uniqueChebi);
        }
    }

    /**
     * Sets the interactor type of this bioactive entity.
     * If the given interactorType is null, it sets the interactorType to 'bioactive entity'(MI:1100)
     */
    @Override
    public void setInteractorType(CvTerm interactorType) {
        if (interactorType == null){
            super.setInteractorType(new XmlCvTerm(BioactiveEntity.BIOACTIVE_ENTITY, new XmlXref(CvTermUtils.createPsiMiDatabase(), BioactiveEntity.BIOACTIVE_ENTITY_MI, CvTermUtils.createIdentityQualifier())));
        }
        else {
            super.setInteractorType(interactorType);
        }
    }

    /**
     * Gets the value of the id property.
     *
     * @return a int.
     */
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the value of the id property.
     */
    public void setId(int value) {
        this.id = value;
        XmlEntryContext.getInstance().registerInteractor(this.id, this);
        if (getSourceLocator() != null){
            sourceLocator.setObjectId(this.id);
        }
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else if (sourceLocator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)sourceLocator;
            this.sourceLocator.setObjectId(getId());
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), getId());
        }
    }

    /**
     * <p>Setter for the field <code>sourceLocator</code>.</p>
     *
     * @param sourceLocator a {@link psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator} object.
     */
    public void setSourceLocator(PsiXmlLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Bioactive entity: "+getSourceLocator().toString():super.toString());
    }
}

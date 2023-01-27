package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Gene;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultGene;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractor;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

/**
 * Xml implementation of a Gene
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>24/07/13</pre>
 */
@XmlTransient
public class XmlGene extends DefaultGene implements ExtendedPsiXmlInteractor, FileSourceContext {
    private int id;
    private PsiXmlLocator sourceLocator;

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     */
    public XmlGene(String name) {
        super(name, new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())));
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     */
    public XmlGene(String name, String fullName) {
        super(name, fullName, new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())));
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param organism a {@link Organism} object.
     */
    public XmlGene(String name, Organism organism) {
        super(name, new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())),
                organism);
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param organism a {@link Organism} object.
     */
    public XmlGene(String name, String fullName, Organism organism) {
        super(name, fullName, new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())),
                organism);
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlGene(String name, Xref uniqueId) {
        super(name, new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())),
                uniqueId);
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlGene(String name, String fullName, Xref uniqueId) {
        super(name, fullName, new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())),
                uniqueId);
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlGene(String name, Organism organism, Xref uniqueId) {
        super(name, new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())),
                organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlGene(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())),
                organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param ensembl a {@link Xref} object.
     */
    public XmlGene(String name, String fullName, CvTerm type, Xref ensembl) {
        super(name, fullName, type != null ? type : new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier()))
                , ensembl);
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param ensembl a {@link Xref} object.
     */
    public XmlGene(String name, CvTerm type, Xref ensembl) {
        super(name, type != null ? type : new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())), ensembl);
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     * @param ensembl a {@link Xref} object.
     */
    public XmlGene(String name, CvTerm type, Organism organism, Xref ensembl) {
        super(name, type != null ? type : new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())), organism, ensembl);
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     * @param ensembl a {@link Xref} object.
     */
    public XmlGene(String name, String fullName, CvTerm type, Organism organism, Xref ensembl) {
        super(name, fullName, type != null ? type : new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())), organism, ensembl);
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param uniqueId a {@link Xref} object.
     * @param ensembl a {@link Xref} object.
     */
    public XmlGene(String name, CvTerm type, Xref uniqueId, Xref ensembl) {
        super(name, type != null ? type : new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())), uniqueId, ensembl);
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param uniqueId a {@link Xref} object.
     * @param ensembl a {@link Xref} object.
     */
    public XmlGene(String name, String fullName, CvTerm type, Xref uniqueId, Xref ensembl) {
        super(name, fullName, type != null ? type : new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())), uniqueId, ensembl);
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     * @param ensembl a {@link Xref} object.
     */
    public XmlGene(String name, CvTerm type, Organism organism, Xref uniqueId, Xref ensembl) {
        super(name, type != null ? type : new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())), organism, uniqueId, ensembl);
    }

    /**
     * <p>Constructor for XmlGene.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     * @param ensembl a {@link Xref} object.
     */
    public XmlGene(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId, Xref ensembl) {
        super(name, fullName, type != null ? type : new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())), organism, uniqueId, ensembl);
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractorType(CvTerm interactorType) {
        if (interactorType == null){
            super.setInteractorType(new XmlCvTerm(Gene.GENE, new XmlXref(CvTermUtils.createPsiMiDatabase(),Gene.GENE_MI, CvTermUtils.createIdentityQualifier())));
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
     * @return a {@link FileSourceLocator} object.
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
     * @param sourceLocator a {@link PsiXmlLocator} object.
     */
    public void setSourceLocator(PsiXmlLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Gene: "+getSourceLocator().toString():super.toString());
    }
}

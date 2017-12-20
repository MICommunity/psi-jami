package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CooperativityEvidence;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.utils.comparator.cooperativity.UnambiguousCooperativityEvidenceComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Xml implementation for cooperativity evidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */
public class XmlCooperativityEvidence implements CooperativityEvidence, FileSourceContext {
    private PsiXmlLocator sourceLocator;
    private Experiment exp;
    private Publication publication;
    private Collection<CvTerm> evidenceMethods;

    /**
     * <p>Constructor for XmlCooperativityEvidence.</p>
     *
     * @param exp a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public XmlCooperativityEvidence(Experiment exp) {
        if (exp == null){
            throw new IllegalArgumentException("The experiment is mandatory");
        }
        this.exp = exp;
    }

    /**
     * <p>Getter for the field <code>publication</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public Publication getPublication() {
        if (this.publication == null){
            if (exp.getPublication() == null){
                this.publication = new BibRef();
            }
            else{
                this.publication = exp.getPublication();
            }
            this.exp = null;
        }
        return this.publication;
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
    public void setSourceLocator(FileSourceLocator locator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else if (sourceLocator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)sourceLocator;
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
        }
    }

    /**
     * <p>Setter for the field <code>sourceLocator</code>.</p>
     *
     * @param locator a {@link psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator} object.
     */
    public void setSourceLocator(PsiXmlLocator locator) {
        this.sourceLocator = locator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Cooperativity evidence: "+getSourceLocator().toString():super.toString());
    }

    /**
     * <p>initialiseEvidenceMethods.</p>
     */
    protected void initialiseEvidenceMethods(){
        this.evidenceMethods = new ArrayList<CvTerm>();
    }

    /**
     * <p>initialiseEvidenceMethodsWith.</p>
     *
     * @param methods a {@link java.util.Collection} object.
     */
    protected void initialiseEvidenceMethodsWith(Collection<CvTerm> methods){
        if (methods == null){
            this.evidenceMethods = Collections.EMPTY_LIST;
        }
        else{
            this.evidenceMethods = methods;
        }
    }

    /** {@inheritDoc} */
    public void setPublication(Publication publication) {
        if (publication == null){
            throw new IllegalArgumentException("The publication cannot be null in a CooperativityEvidence");
        }
        this.publication = publication;
    }

    /**
     * <p>Getter for the field <code>evidenceMethods</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CvTerm> getEvidenceMethods() {

        if (evidenceMethods == null){
            initialiseEvidenceMethods();
        }
        return evidenceMethods;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof CooperativityEvidence)){
            return false;
        }

        return UnambiguousCooperativityEvidenceComparator.areEquals(this, (CooperativityEvidence) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousCooperativityEvidenceComparator.hashCode(this);
    }
}

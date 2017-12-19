package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.CooperativityEvidence;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.utils.comparator.cooperativity.UnambiguousCooperativityEvidenceComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for CooperativityEvidence
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with UnambiguousCooperativityEvidenceComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/05/13</pre>
 */
public class DefaultCooperativityEvidence implements CooperativityEvidence {

    private Publication publication;
    private Collection<CvTerm> evidenceMethods;

    /**
     * <p>Constructor for DefaultCooperativityEvidence.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public DefaultCooperativityEvidence(Publication publication){
        if (publication == null){
            throw new IllegalArgumentException("The publication cannot be null in a CooperativityEvidence");
        }
        this.publication = publication;
    }

    /**
     * <p>initialiseEvidenceMethods</p>
     */
    protected void initialiseEvidenceMethods(){
        this.evidenceMethods = new ArrayList<CvTerm>();
    }

    /**
     * <p>initialiseEvidenceMethodsWith</p>
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

    /**
     * <p>Getter for the field <code>publication</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public Publication getPublication() {
        return this.publication;
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

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Cooperativity evidence: "+(getPublication() != null ? getPublication().toString() : "no publication");
    }
}

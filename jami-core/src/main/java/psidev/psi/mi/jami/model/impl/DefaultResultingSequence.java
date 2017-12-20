package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.ResultingSequence;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.comparator.range.ResultingSequenceComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for ResultingSequence
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with ResultingSequenceComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/05/13</pre>
 */
public class DefaultResultingSequence implements ResultingSequence {

    private String originalSequence;
    private String newSequence;
    private Collection<Xref> xrefs;

    /**
     * <p>Constructor for DefaultResultingSequence.</p>
     */
    public DefaultResultingSequence(){
        this.originalSequence = null;
        this.newSequence = null;
    }

    /**
     * <p>Constructor for DefaultResultingSequence.</p>
     *
     * @param oldSequence a {@link java.lang.String} object.
     * @param newSequence a {@link java.lang.String} object.
     */
    public DefaultResultingSequence(String oldSequence, String newSequence){
        this.originalSequence = oldSequence;
        this.newSequence = newSequence;
    }

    /**
     * <p>initialiseXrefs</p>
     */
    protected void initialiseXrefs(){
        this.xrefs = new ArrayList<Xref>();
    }

    /**
     * <p>initialiseXrefsWith</p>
     *
     * @param xrefs a {@link java.util.Collection} object.
     */
    protected void initialiseXrefsWith(Collection<Xref> xrefs){
        if (xrefs == null){
            this.xrefs = Collections.EMPTY_LIST;
        }
        else {
            this.xrefs = xrefs;
        }
    }

    /**
     * <p>Getter for the field <code>newSequence</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getNewSequence() {
        return newSequence;
    }

    /**
     * <p>Getter for the field <code>originalSequence</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getOriginalSequence() {
        return originalSequence;
    }

    /**
     * <p>Getter for the field <code>xrefs</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getXrefs() {
        if (xrefs == null){
           initialiseXrefs();
        }
        return xrefs;
    }

    /** {@inheritDoc} */
    public void setNewSequence(String sequence) {
        this.newSequence = sequence;
    }

    /** {@inheritDoc} */
    public void setOriginalSequence(String sequence) {
        this.originalSequence = sequence;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof ResultingSequence)){
            return false;
        }

        return ResultingSequenceComparator.areEquals(this, (ResultingSequence) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return ResultingSequenceComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getOriginalSequence() != null ? "original sequence: "+getOriginalSequence() : "") +
                (getNewSequence() != null ? "new sequence: "+getNewSequence() : "");
    }
}

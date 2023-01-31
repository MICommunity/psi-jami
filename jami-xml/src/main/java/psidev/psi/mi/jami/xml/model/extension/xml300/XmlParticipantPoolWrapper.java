package psidev.psi.mi.jami.xml.model.extension.xml300;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.ModelledParticipantCandidate;
import psidev.psi.mi.jami.model.ModelledParticipantPool;
import psidev.psi.mi.jami.model.ParticipantCandidate;
import psidev.psi.mi.jami.model.ParticipantPool;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipant;

import java.util.Collection;
import java.util.Iterator;

/**
 * Wrapper for XmlParticipant
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/10/13</pre>
 */
public class XmlParticipantPoolWrapper extends XmlParticipantWrapper implements ModelledParticipantPool{

    private SynchronizedParticipantCandidateList candidateList;

    /**
     * <p>Constructor for XmlParticipantPoolWrapper.</p>
     *
     * @param part a {@link psidev.psi.mi.jami.model.ParticipantPool} object.
     * @param wrapper a {@link psidev.psi.mi.jami.model.ModelledInteraction} object.
     */
    public XmlParticipantPoolWrapper(ParticipantPool part, ModelledInteraction wrapper){
        super((ExtendedPsiXmlParticipant)part, wrapper);
        initialiseCandidates();
    }

    /**
     * <p>getCandidates.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlParticipantPoolWrapper.SynchronizedParticipantCandidateList} object.
     */
    protected SynchronizedParticipantCandidateList getCandidates(){
        return this.candidateList;
    }

    /**
     * <p>initialiseCandidates.</p>
     */
    protected void initialiseCandidates(){
        candidateList = new SynchronizedParticipantCandidateList();
        for (Object candidate : (ParticipantPool)getWrappedParticipant()){
            this.candidateList.addOnly(new XmlParticipantCandidateWrapper((ParticipantCandidate)candidate, this));
        }
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getType() {
        return ((ParticipantPool)getWrappedParticipant()).getType();
    }

    /** {@inheritDoc} */
    @Override
    public void setType(CvTerm type) {
        ((ParticipantPool)getWrappedParticipant()).setType(type);
    }

    /** {@inheritDoc} */
    @Override
    public int size() {
        return getCandidates().size();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        return getCandidates().isEmpty();
    }

    /** {@inheritDoc} */
    @Override
    public boolean contains(Object o) {
        return getCandidates().contains(o);
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<ModelledParticipantCandidate> iterator() {
        return getCandidates().iterator();
    }

    /** {@inheritDoc} */
    @Override
    public Object[] toArray() {
        return getCandidates().toArray();
    }

    /** {@inheritDoc} */
    @Override
    public <T> T[] toArray(T[] ts) {
        return getCandidates().toArray(ts);
    }

    /** {@inheritDoc} */
    @Override
    public boolean add(ModelledParticipantCandidate modelledParticipantCandidate) {
        return getCandidates().add(modelledParticipantCandidate);
    }

    /** {@inheritDoc} */
    @Override
    public boolean remove(Object o) {
        return getCandidates().remove(o);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsAll(Collection<?> objects) {
        return getCandidates().containsAll(objects);
    }

    /** {@inheritDoc} */
    @Override
    public boolean addAll(Collection<? extends ModelledParticipantCandidate> modelledParticipantCandidates) {
        return getCandidates().addAll(modelledParticipantCandidates);
    }

    /** {@inheritDoc} */
    @Override
    public boolean removeAll(Collection<?> objects) {
        return getCandidates().removeAll(objects);
    }

    /** {@inheritDoc} */
    @Override
    public boolean retainAll(Collection<?> objects) {
        return getCandidates().retainAll(objects);
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
         getCandidates().clear();
    }


    ////////////////////////////////////// classes
    private class SynchronizedParticipantCandidateList extends AbstractListHavingProperties<ModelledParticipantCandidate> {

        private SynchronizedParticipantCandidateList() {
        }

        @Override
        protected void processAddedObjectEvent(ModelledParticipantCandidate added) {
            ((ParticipantPool)getWrappedParticipant()).add(added);
        }

        @Override
        protected void processRemovedObjectEvent(ModelledParticipantCandidate removed) {
            ((ParticipantPool)getWrappedParticipant()).remove(removed);
        }

        @Override
        protected void clearProperties() {
            ((ParticipantPool)getWrappedParticipant()).clear();
        }
    }
}

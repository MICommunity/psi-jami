package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ExperimentalParticipantCandidate;
import psidev.psi.mi.jami.model.ExperimentalParticipantPool;
import psidev.psi.mi.jami.model.ModelledParticipantCandidate;
import psidev.psi.mi.jami.model.ModelledParticipantPool;
import psidev.psi.mi.jami.model.ParticipantPool;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipantEvidence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Wrapper for Xml participants pools
 *
 * Addeding new modelled feature to this participant will not add new feature evidences to the wrapped participant evidence as they are incompatibles.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
@XmlTransient
public class XmlExperimentalParticipantPoolWrapper extends XmlParticipantEvidenceWrapper implements ModelledParticipantPool {

    private Collection<ModelledParticipantCandidate> candidateList;

    /**
     * <p>Constructor for XmlExperimentalParticipantPoolWrapper.</p>
     *
     * @param part a {@link psidev.psi.mi.jami.model.ExperimentalParticipantPool} object.
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml254.XmlInteractionEvidenceComplexWrapper} object.
     */
    public XmlExperimentalParticipantPoolWrapper(ExperimentalParticipantPool part, XmlInteractionEvidenceComplexWrapper wrapper){
        super((ExtendedPsiXmlParticipantEvidence)part, wrapper);
        initialiseCandidates();
    }

    /**
     * <p>getCandidates.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    protected Collection<ModelledParticipantCandidate> getCandidates(){
        return this.candidateList;
    }

    /**
     * <p>initialiseCandidates.</p>
     */
    protected void initialiseCandidates(){
        candidateList = new ArrayList<ModelledParticipantCandidate>();
        for (ExperimentalParticipantCandidate candidate : (ExperimentalParticipantPool)getWrappedParticipant()){
            this.candidateList.add(new XmlExperimentalParticipantCandidateWrapper(candidate, this));
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
}

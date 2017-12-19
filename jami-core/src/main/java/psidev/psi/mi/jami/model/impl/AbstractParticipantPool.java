package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.listener.EntityInteractorChangeListener;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.CvTermUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Abstract class for Participant pool
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/07/13</pre>
 */
public abstract class AbstractParticipantPool<I extends Interaction, F extends Feature, P extends ParticipantCandidate>
        extends AbstractParticipant<I,F> implements ParticipantPool<I,F,P>, EntityInteractorChangeListener {
    private Collection<P> candidates;
    private CvTerm type;

    /**
     * <p>Constructor for AbstractParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     */
    public AbstractParticipantPool(String poolName){
        super(new DefaultInteractorPool(poolName));
        initialiseComponentCandidatesSet();
    }

    /**
     * <p>Constructor for AbstractParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractParticipantPool(String poolName, CvTerm bioRole){
        super(new DefaultInteractorPool(poolName));
        initialiseComponentCandidatesSet();
    }

    /**
     * <p>Constructor for AbstractParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public AbstractParticipantPool(String poolName, Stoichiometry stoichiometry){
        super(new DefaultInteractorPool(poolName), stoichiometry);
        initialiseComponentCandidatesSet();
    }

    /**
     * <p>Constructor for AbstractParticipantPool.</p>
     *
     * @param poolName a {@link java.lang.String} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public AbstractParticipantPool(String poolName, CvTerm bioRole, Stoichiometry stoichiometry){
        super(new DefaultInteractorPool(poolName), stoichiometry);
        initialiseComponentCandidatesSet();
    }

    /**
     * <p>initialiseComponentCandidatesSet</p>
     */
    protected void initialiseComponentCandidatesSet() {
        this.candidates = new ArrayList<P>();
    }

    /**
     * <p>initialiseComponentCandidatesSetWith</p>
     *
     * @param candidates a {@link java.util.Collection} object.
     */
    protected void initialiseComponentCandidatesSetWith(Collection<P> candidates) {
        if (candidates == null){
            this.candidates = Collections.EMPTY_LIST;
        }
        else {
            this.candidates = candidates;
        }
    }

    /** {@inheritDoc} */
    @Override
    public InteractorPool getInteractor() {
        return (InteractorPool) super.getInteractor();
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractor(Interactor interactor) {
        throw new UnsupportedOperationException("Cannot set the interactor of an ParticipantPool as it is an interactorSet that is related to the interactors in the set of entities");
    }

    /**
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the component set type.
     * Sets the type to molecule set (MI:1304) if the given type is null
     */
    public void setType(CvTerm type) {
        if (type == null){
            this.type = CvTermUtils.createMoleculeSetType();
        }
        else {
            this.type = type;
        }
        getInteractor().setInteractorType(this.type);
    }

    /**
     * <p>size</p>
     *
     * @return a int.
     */
    public int size() {
        return candidates.size();
    }

    /**
     * <p>isEmpty</p>
     *
     * @return a boolean.
     */
    public boolean isEmpty() {
        return candidates.isEmpty();
    }

    /** {@inheritDoc} */
    public boolean contains(Object o) {
        return candidates.contains(o);
    }

    /**
     * <p>iterator</p>
     *
     * @return a {@link java.util.Iterator} object.
     */
    public Iterator<P> iterator() {
        return candidates.iterator();
    }

    /**
     * <p>toArray</p>
     *
     * @return an array of {@link java.lang.Object} objects.
     */
    public Object[] toArray() {
        return candidates.toArray();
    }

    /**
     * <p>toArray</p>
     *
     * @param ts an array of T objects.
     * @param <T> a T object.
     * @return an array of T objects.
     */
    public <T> T[] toArray(T[] ts) {
        return candidates.toArray(ts);
    }

    /**
     * <p>add</p>
     *
     * @param interactor a P object.
     * @return a boolean.
     */
    public boolean add(P interactor) {
        if (candidates.add(interactor)){
            interactor.setChangeListener(this);
            getInteractor().add(interactor.getInteractor());
            interactor.setParentPool(this);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean remove(Object o) {
        if (candidates.remove(o)){
            ParticipantCandidate entity = (ParticipantCandidate)o;
            entity.setChangeListener(null);
            getInteractor().remove(entity.getInteractor());
            entity.setParentPool(null);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean containsAll(Collection<?> objects) {
        return candidates.containsAll(objects);
    }

    /** {@inheritDoc} */
    public boolean addAll(Collection<? extends P> interactors) {
        boolean added = this.candidates.addAll(interactors);
        if (added){
            for (P entity : this){
                entity.setChangeListener(this);
                getInteractor().add(entity.getInteractor());
                entity.setParentPool(this);
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    public boolean retainAll(Collection<?> objects) {
        boolean retain = candidates.retainAll(objects);
        if (retain){
            Collection<Interactor> interactors = new ArrayList<Interactor>(objects.size());
            for (Object o : objects){
                interactors.add(((Participant)o).getInteractor());
            }
            getInteractor().retainAll(interactors);
        }
        return retain;
    }

    /** {@inheritDoc} */
    public boolean removeAll(Collection<?> objects) {
        boolean remove = candidates.removeAll(objects);
        if (remove){
            Collection<Interactor> interactors = new ArrayList<Interactor>(objects.size());
            for (Object o : objects){
                Participant entity = (Participant)o;
                entity.setChangeListener(null);
                interactors.add(entity.getInteractor());
                entity.setInteraction(null);
            }
            // check if an interactor is not in another entity that is kept.
            // remove any interactors that are kept with other entities
            for (P entity : this){
                interactors.remove(entity.getInteractor());
            }
            getInteractor().removeAll(interactors);
        }
        return remove;
    }

    /**
     * <p>clear</p>
     */
    public void clear() {
        for (P entity : this){
            entity.setChangeListener(null);
            entity.setParentPool(null);
        }
        candidates.clear();
        getInteractor().clear();
    }

    /** {@inheritDoc} */
    public void onInteractorUpdate(Entity entity, Interactor oldInteractor) {
        // check that the listener still makes sensr
        if (contains(entity)){
            boolean needsToRemoveOldInteractor = true;
            // check if an interactor is not in another entity that is kept.
            // remove any interactors that are kept with other entities
            for (P e : this){
                // we want to check if an interactor is the same as old interactor in another entry
                if (e != entity){
                    if (oldInteractor.equals(e.getInteractor())){
                        needsToRemoveOldInteractor = false;
                    }
                }
            }
            if (!needsToRemoveOldInteractor){
                getInteractor().remove(oldInteractor);
            }
            getInteractor().add(entity.getInteractor());
        }
    }


    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Participant pool: "+getInteractor().toString() + (getStoichiometry() != null ? ", pool size: " + size() : "");
    }
}

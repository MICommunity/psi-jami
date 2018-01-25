package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.CvTermUtils;

import java.util.*;

/**
 * Default implementation for interactor pool
 * <p>
 * Notes: The equals and hashcode methods have NOT been overridden because the InteractorPool object is a complex object.
 * To compare InteractorPool objects, you can use some comparators provided by default:
 * - DefaultInteractorPoolComparator
 * - UnambiguousInteractorPoolComparator
 * - DefaultExactInteractorPoolComparator
 * - UnambiguousExactInteractorPoolComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/02/13</pre>
 */
public class DefaultInteractorPool extends DefaultInteractor implements InteractorPool {

    private Collection<Interactor> interactors;

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultInteractorPool(String name, CvTerm type) {
        super(name, type != null ? type : CvTermUtils.createMoleculeSetType());
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultInteractorPool(String name, String fullName, CvTerm type) {
        super(name, fullName, type != null ? type : CvTermUtils.createMoleculeSetType());
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultInteractorPool(String name, CvTerm type, Organism organism) {
        super(name, type != null ? type : CvTermUtils.createMoleculeSetType(), organism);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultInteractorPool(String name, String fullName, CvTerm type, Organism organism) {
        super(name, fullName, type != null ? type : CvTermUtils.createMoleculeSetType(), organism);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractorPool(String name, CvTerm type, Xref uniqueId) {
        super(name, type != null ? type : CvTermUtils.createMoleculeSetType(), uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractorPool(String name, String fullName, CvTerm type, Xref uniqueId) {
        super(name, fullName, type != null ? type : CvTermUtils.createMoleculeSetType(), uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractorPool(String name, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, type != null ? type : CvTermUtils.createMoleculeSetType(), organism, uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractorPool(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, fullName, type != null ? type : CvTermUtils.createMoleculeSetType(), organism, uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public DefaultInteractorPool(String name) {
        super(name, CvTermUtils.createMoleculeSetType());
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public DefaultInteractorPool(String name, String fullName) {
        super(name, fullName, CvTermUtils.createMoleculeSetType());
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultInteractorPool(String name, Organism organism) {
        super(name, CvTermUtils.createMoleculeSetType(), organism);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultInteractorPool(String name, String fullName, Organism organism) {
        super(name, fullName, CvTermUtils.createMoleculeSetType(), organism);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractorPool(String name, Xref uniqueId) {
        super(name, CvTermUtils.createMoleculeSetType(), uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractorPool(String name, String fullName, Xref uniqueId) {
        super(name, fullName, CvTermUtils.createMoleculeSetType(), uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractorPool(String name, Organism organism, Xref uniqueId) {
        super(name, CvTermUtils.createMoleculeSetType(), organism, uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for DefaultInteractorPool.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractorPool(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, CvTermUtils.createMoleculeSetType(), organism, uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>initialiseInteractorCandidatesSet</p>
     */
    protected void initialiseInteractorCandidatesSet() {
        this.interactors = new ArrayList<Interactor>();
    }


    /**
     * {@inheritDoc}
     *
     * <p>initialiseInteractorCandidatesSet</p>
     */
    protected void initialiseInteractorCandidatesSetWith(Set<Interactor> interactorCandidates) {
        if (interactorCandidates == null) {
            this.interactors = Collections.EMPTY_LIST;
        } else {
            this.interactors = interactorCandidates;
        }
    }
    @Override
    /**
     * Sets the interactor type.
     * Sets the type to molecule set (MI:1304) if the given type is null
     */
    public void setInteractorType(CvTerm type) {
        if (type == null) {
            super.setInteractorType(CvTermUtils.createMoleculeSetType());
        } else {
            super.setInteractorType(type);
        }
    }

    /**
     * <p>size</p>
     *
     * @return a int.
     */
    public int size() {
        return interactors.size();
    }

    /**
     * <p>isEmpty</p>
     *
     * @return a boolean.
     */
    public boolean isEmpty() {
        return interactors.isEmpty();
    }

    /** {@inheritDoc} */
    public boolean contains(Object o) {
        return interactors.contains(o);
    }

    /**
     * <p>iterator</p>
     *
     * @return a {@link java.util.Iterator} object.
     */
    public Iterator<Interactor> iterator() {
        return interactors.iterator();
    }

    /**
     * <p>toArray</p>
     *
     * @return an array of {@link java.lang.Object} objects.
     */
    public Object[] toArray() {
        return interactors.toArray();
    }

    /**
     * <p>toArray</p>
     *
     * @param ts  an array of T objects.
     * @param <T> a T object.
     * @return an array of T objects.
     */
    public <T> T[] toArray(T[] ts) {
        return interactors.toArray(ts);
    }

    /**
     * <p>add</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @return a boolean.
     */
    public boolean add(Interactor interactor) {
        return interactors.add(interactor);
    }

    /** {@inheritDoc} */
    public boolean remove(Object o) {
        return interactors.remove(o);
    }

    /** {@inheritDoc} */
    public boolean containsAll(Collection<?> objects) {
        return interactors.containsAll(objects);
    }

    /** {@inheritDoc} */
    public boolean addAll(Collection<? extends Interactor> interactors) {
        return this.interactors.addAll(interactors);
    }

    /** {@inheritDoc} */
    public boolean retainAll(Collection<?> objects) {
        return interactors.retainAll(objects);
    }

    /** {@inheritDoc} */
    public boolean removeAll(Collection<?> objects) {
        return interactors.removeAll(objects);
    }

    /**
     * <p>clear</p>
     */
    public void clear() {
        interactors.clear();
    }
}

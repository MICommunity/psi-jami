package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.InteractorPool;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Xml implementation for InteractorSet
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>24/07/13</pre>
 */
@XmlTransient
public class XmlInteractorPool extends DefaultXmlInteractor implements InteractorPool {

    private Collection<Interactor> interactors;

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     */
    public XmlInteractorPool(){

    }
    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     */
    public XmlInteractorPool(String name, CvTerm type) {
        super(name, type);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     */
    public XmlInteractorPool(String name, String fullName, CvTerm type) {
        super(name, fullName, type);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     */
    public XmlInteractorPool(String name, CvTerm type, Organism organism) {
        super(name, type, organism);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     */
    public XmlInteractorPool(String name, String fullName, CvTerm type, Organism organism) {
        super(name, fullName, type, organism);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlInteractorPool(String name, CvTerm type, Xref uniqueId) {
        super(name, type, uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlInteractorPool(String name, String fullName, CvTerm type, Xref uniqueId) {
        super(name, fullName, type, uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlInteractorPool(String name, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, type, organism, uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlInteractorPool(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, fullName, type, organism, uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     */
    public XmlInteractorPool(String name) {
        super(name);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     */
    public XmlInteractorPool(String name, String fullName) {
        super(name, fullName);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param organism a {@link Organism} object.
     */
    public XmlInteractorPool(String name, Organism organism) {
        super(name, organism);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param organism a {@link Organism} object.
     */
    public XmlInteractorPool(String name, String fullName, Organism organism) {
        super(name, fullName, organism);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlInteractorPool(String name, Xref uniqueId) {
        super(name, uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlInteractorPool(String name, String fullName, Xref uniqueId) {
        super(name, fullName, uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlInteractorPool(String name, Organism organism, Xref uniqueId) {
        super(name, organism, uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>Constructor for XmlInteractorPool.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlInteractorPool(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, organism, uniqueId);
        initialiseInteractorCandidatesSet();
    }

    /**
     * <p>initialiseInteractorCandidatesSet.</p>
     */
    protected void initialiseInteractorCandidatesSet(){
        this.interactors = new HashSet<Interactor>();
    }

    /**
     * <p>initialiseInteractorCandidatesSetWith.</p>
     *
     * @param interactorCandidates a {@link Set} object.
     */
    protected void initialiseInteractorCandidatesSetWith(Set<Interactor> interactorCandidates){
        if (interactorCandidates == null){
            this.interactors = Collections.EMPTY_SET;
        }
        else {
            this.interactors = interactorCandidates;
        }
    }

    /**
     * <p>size.</p>
     *
     * @return a int.
     */
    public int size() {
        return interactors.size();
    }

    /**
     * <p>isEmpty.</p>
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
     * <p>iterator.</p>
     *
     * @return a {@link Iterator} object.
     */
    public Iterator<Interactor> iterator() {
        return interactors.iterator();
    }

    /**
     * <p>toArray.</p>
     *
     * @return an array of {@link Object} objects.
     */
    public Object[] toArray() {
        return interactors.toArray();
    }

    /**
     * <p>toArray.</p>
     *
     * @param ts an array of T objects.
     * @param <T> a T object.
     * @return an array of T objects.
     */
    public <T> T[] toArray(T[] ts) {
        return interactors.toArray(ts);
    }

    /**
     * <p>add.</p>
     *
     * @param interactor a {@link Interactor} object.
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
     * <p>clear.</p>
     */
    public void clear() {
        interactors.clear();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Interactor set: "+getSourceLocator().toString():super.toString());
    }

    /** {@inheritDoc} */
    @Override
    protected void createDefaultInteractorType() {
        setInteractorType(new XmlCvTerm(InteractorPool.MOLECULE_SET, InteractorPool.MOLECULE_SET_MI));
    }
}


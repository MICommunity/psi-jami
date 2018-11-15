package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.CvTermUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for Interactor
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the Interactor object is a complex object.
 * To compare Interactor objects, you can use some comparators provided by default:
 * - DefaultInteractorBaseComparator
 * - UnambiguousInteractorBaseComparator
 * - DefaultExactInteractorBaseComparator
 * - UnambiguousExactInteractorBaseComparator
 * - DefaultInteractorComparator
 * - UnambiguousInteractorComparator
 * - DefaultExactInteractorComparator
 * - UnambiguousExactInteractorComparator
 * - AbstractInteractorBaseComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/01/13</pre>
 */
public class DefaultInteractor implements Interactor {

    private String shortName;
    private String fullName;
    private Collection<Xref> identifiers;
    private Collection<Checksum> checksums;
    private Collection<Xref> xrefs;
    private Collection<Annotation> annotations;
    private Collection<Alias> aliases;
    private Organism organism;
    private CvTerm interactorType;

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultInteractor(String name, CvTerm type){
        if (name == null || (name != null && name.length() == 0)){
            throw new IllegalArgumentException("The short name cannot be null or empty.");
        }
        this.shortName = name;
        if (type == null){
            this.interactorType = CvTermUtils.createUnknownInteractorType();
        }
        else {
            this.interactorType = type;
        }
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultInteractor(String name, String fullName, CvTerm type){
        this(name, type);
        this.fullName = fullName;
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultInteractor(String name, CvTerm type, Organism organism){
        this(name, type);
        this.organism = organism;
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultInteractor(String name, String fullName, CvTerm type, Organism organism){
        this(name, fullName, type);
        this.organism = organism;
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractor(String name, CvTerm type, Xref uniqueId){
        this(name, type);
        getIdentifiers().add(uniqueId);
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractor(String name, String fullName, CvTerm type, Xref uniqueId){
        this(name, fullName, type);
        getIdentifiers().add(uniqueId);
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractor(String name, CvTerm type, Organism organism, Xref uniqueId){
        this(name, type, organism);
        getIdentifiers().add(uniqueId);
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractor(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId){
        this(name, fullName, type, organism);
        getIdentifiers().add(uniqueId);
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public DefaultInteractor(String name){
        if (name == null || (name != null && name.length() == 0)){
            throw new IllegalArgumentException("The short name cannot be null or empty.");
        }
        this.shortName = name;
        this.interactorType = CvTermUtils.createUnknownInteractorType();
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public DefaultInteractor(String name, String fullName){
        this(name);
        this.fullName = fullName;
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultInteractor(String name, Organism organism){
        this(name);
        this.organism = organism;
        this.interactorType = CvTermUtils.createUnknownInteractorType();
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultInteractor(String name, String fullName, Organism organism){
        this(name, fullName);
        this.organism = organism;
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractor(String name, Xref uniqueId){
        this(name);
        getIdentifiers().add(uniqueId);
        this.interactorType = CvTermUtils.createUnknownInteractorType();
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractor(String name, String fullName, Xref uniqueId){
        this(name, fullName);
        getIdentifiers().add(uniqueId);
        this.interactorType = CvTermUtils.createUnknownInteractorType();
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractor(String name, Organism organism, Xref uniqueId){
        this(name, organism);
        getIdentifiers().add(uniqueId);
        this.interactorType = CvTermUtils.createUnknownInteractorType();
    }

    /**
     * <p>Constructor for DefaultInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultInteractor(String name, String fullName, Organism organism, Xref uniqueId){
        this(name, fullName, organism);
        getIdentifiers().add(uniqueId);
        this.interactorType = CvTermUtils.createUnknownInteractorType();
    }

    /**
     * <p>initialiseAnnotations</p>
     */
    protected void initialiseAnnotations(){
        this.annotations = new ArrayList<Annotation>();
    }

    /**
     * <p>initialiseXrefs</p>
     */
    protected void initialiseXrefs(){
        this.xrefs = new ArrayList<Xref>();
    }

    /**
     * <p>initialiseAliases</p>
     */
    protected void initialiseAliases(){
        this.aliases = new ArrayList<Alias>();
    }

    /**
     * <p>initialiseIdentifiers</p>
     */
    protected void initialiseIdentifiers(){
        this.identifiers = new ArrayList<Xref>();
    }

    /**
     * <p>initialiseChecksums</p>
     */
    protected void initialiseChecksums(){
        this.checksums = new ArrayList<Checksum>();
    }

    /**
     * <p>initialiseAnnotationsWith</p>
     *
     * @param annotations a {@link java.util.Collection} object.
     */
    protected void initialiseAnnotationsWith(Collection<Annotation> annotations){
        if (annotations == null){
           this.annotations = Collections.EMPTY_LIST;
        }
        else {
            this.annotations = annotations;
        }
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
     * <p>initialiseAliasesWith</p>
     *
     * @param aliases a {@link java.util.Collection} object.
     */
    protected void initialiseAliasesWith(Collection<Alias> aliases){
        if (aliases == null){
            this.aliases = Collections.EMPTY_LIST;
        }
        else {
            this.aliases = aliases;
        }
    }

    /**
     * <p>initialiseIdentifiersWith</p>
     *
     * @param identifiers a {@link java.util.Collection} object.
     */
    protected void initialiseIdentifiersWith(Collection<Xref> identifiers){
        if (identifiers == null){
            this.identifiers = Collections.EMPTY_LIST;
        }
        else {
            this.identifiers = identifiers;
        }
    }

    /**
     * <p>initialiseChecksumsWith</p>
     *
     * @param checksums a {@link java.util.Collection} object.
     */
    protected void initialiseChecksumsWith(Collection<Checksum> checksums){
        if (checksums == null){
            this.checksums = Collections.EMPTY_LIST;
        }
        else {
            this.checksums = checksums;
        }
    }

    /**
     * <p>Getter for the field <code>shortName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getShortName() {
        return this.shortName;
    }

    /** {@inheritDoc} */
    public void setShortName(String name) {
        if (name == null || (name != null && name.length() == 0)){
             throw new IllegalArgumentException("The short name cannot be null or empty.");
        }
        this.shortName = name;
    }

    /**
     * <p>Getter for the field <code>fullName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFullName() {
        return this.fullName;
    }

    /** {@inheritDoc} */
    public void setFullName(String name) {
        this.fullName = name;
    }

    /**
     * <p>Getter for the field <code>identifiers</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getIdentifiers() {
        if (identifiers == null){
           initialiseIdentifiers();
        }
        return this.identifiers;
    }

    /**
     * <p>getPreferredIdentifier</p>
     *
     * @return the first identifier in the list of identifiers or null if the list is empty
     */
    public Xref getPreferredIdentifier() {
        return !getIdentifiers().isEmpty() ? getIdentifiers().iterator().next() : null;
    }

    public String getPreferredName(){
        return this.getShortName();
    }

    /**
     * <p>Getter for the field <code>checksums</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Checksum> getChecksums() {
        if (checksums == null){
            initialiseChecksums();
        }
        return this.checksums;
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
        return this.xrefs;
    }

    /**
     * <p>Getter for the field <code>annotations</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        if (annotations == null){
            initialiseAnnotations();
        }
        return this.annotations;
    }

    /**
     * <p>Getter for the field <code>aliases</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Alias> getAliases() {
        if (aliases == null){
            initialiseAliases();
        }
        return this.aliases;
    }

    /**
     * <p>Getter for the field <code>organism</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public Organism getOrganism() {
        return this.organism;
    }

    /** {@inheritDoc} */
    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    /**
     * <p>Getter for the field <code>interactorType</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getInteractorType() {
        return this.interactorType;
    }

    /** {@inheritDoc} */
    public void setInteractorType(CvTerm interactorType) {
        if (interactorType == null){
            this.interactorType = CvTermUtils.createUnknownInteractorType();
        }
        else {
            this.interactorType = interactorType;
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getShortName()
                + (getOrganism() != null ? ", " + getOrganism().toString() : "")
                + (getInteractorType() != null ? ", " + getInteractorType().toString() : "")  ;
    }
}

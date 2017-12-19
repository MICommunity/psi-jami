package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Abstract class for Feature
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/07/13</pre>
 */
public abstract class AbstractFeature<P extends Entity, F extends Feature> implements Feature<P,F> {
    private String shortName;
    private String fullName;
    private Xref interpro;
    private Collection<Xref> identifiers;
    private Collection<Xref> xrefs;
    private Collection<Annotation> annotations;
    private CvTerm type;
    private Collection<Range> ranges;
    private Collection<Alias> aliases;

    private CvTerm role;

    private P participant;
    private Collection<F> linkedFeatures;

    /**
     * <p>Constructor for AbstractFeature.</p>
     *
     * @param <P> a P object.
     * @param <F> a F object.
     */
    public AbstractFeature(){
    }

    /**
     * <p>Constructor for AbstractFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public AbstractFeature(String shortName, String fullName){
        this();
        this.shortName = shortName;
        this.fullName = fullName;
    }

    /**
     * <p>Constructor for AbstractFeature.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractFeature(CvTerm type){
        this();
        this.type = type;
    }

    /**
     * <p>Constructor for AbstractFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractFeature(String shortName, String fullName, CvTerm type){
        this(shortName, fullName);
        this.type =type;
    }

    /**
     * <p>Constructor for AbstractFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param interpro a {@link java.lang.String} object.
     */
    public AbstractFeature(String shortName, String fullName, String interpro){
        this(shortName, fullName);
        setInterpro(interpro);
    }

    /**
     * <p>Constructor for AbstractFeature.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param interpro a {@link java.lang.String} object.
     */
    public AbstractFeature(CvTerm type, String interpro){
        this(type);
        setInterpro(interpro);
    }

    /**
     * <p>Constructor for AbstractFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param interpro a {@link java.lang.String} object.
     */
    public AbstractFeature(String shortName, String fullName, CvTerm type, String interpro){
        this(shortName, fullName, type);
        setInterpro(interpro);
    }

    /**
     * <p>initialiseIdentifiers</p>
     */
    protected void initialiseIdentifiers(){
        this.identifiers = new FeatureIdentifierList();
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
     * <p>initialiseRanges</p>
     */
    protected void initialiseRanges(){
        this.ranges = new ArrayList<Range>();
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
     * <p>initialiseRangesWith</p>
     *
     * @param ranges a {@link java.util.Collection} object.
     */
    protected void initialiseRangesWith(Collection<Range> ranges){
        if (ranges == null){
            this.ranges = Collections.EMPTY_LIST;
        }
        else {
            this.ranges = ranges;
        }
    }

    /**
     * <p>initialiseLinkedFeatures</p>
     */
    protected void initialiseLinkedFeatures(){
        this.linkedFeatures = new ArrayList<F>();
    }

    /**
     * <p>initialiseLinkedFeaturesWith</p>
     *
     * @param features a {@link java.util.Collection} object.
     */
    protected void initialiseLinkedFeaturesWith(Collection<F> features){
        if (features == null){
            this.linkedFeatures = Collections.EMPTY_LIST;
        }
        else {
            this.linkedFeatures = features;
        }
    }

    /**
     * <p>initialiseAliases</p>
     */
    protected void initialiseAliases(){
        this.aliases = new ArrayList<Alias>();
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
     * <p>Getter for the field <code>shortName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getShortName() {
        return this.shortName;
    }

    /** {@inheritDoc} */
    public void setShortName(String name) {
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
     * <p>Getter for the field <code>interpro</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getInterpro() {
        return this.interpro != null ? this.interpro.getId() : null;
    }

    /** {@inheritDoc} */
    public void setInterpro(String interpro) {
        Collection<Xref> featureIdentifiers = getIdentifiers();

        // add new interpro if not null
        if (interpro != null){
            CvTerm interproDatabase = CvTermUtils.createInterproDatabase();
            CvTerm identityQualifier = CvTermUtils.createIdentityQualifier();
            // first remove old chebi if not null
            if (this.interpro != null){
                featureIdentifiers.remove(this.interpro);
            }
            this.interpro = new DefaultXref(interproDatabase, interpro, identityQualifier);
            featureIdentifiers.add(this.interpro);
        }
        // remove all interpro if the collection is not empty
        else if (!featureIdentifiers.isEmpty()) {
            XrefUtils.removeAllXrefsWithDatabase(featureIdentifiers, Xref.INTERPRO_MI, Xref.INTERPRO);
            this.interpro = null;
        }
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
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getType() {
        return this.type;
    }

    /** {@inheritDoc} */
    public void setType(CvTerm type) {
        this.type = type;
    }

    /**
     * <p>Getter for the field <code>ranges</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Range> getRanges() {
        if (ranges == null){
            initialiseRanges();
        }
        return this.ranges;
    }

    /**
     * <p>Getter for the field <code>role</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getRole() {
        return this.role;
    }

    /** {@inheritDoc} */
    public void setRole(CvTerm effect) {
        this.role = effect;
    }

    /**
     * <p>Getter for the field <code>participant</code>.</p>
     *
     * @return a P object.
     */
    public P getParticipant() {
        return this.participant;
    }

    /**
     * <p>Setter for the field <code>participant</code>.</p>
     *
     * @param participant a P object.
     */
    public void setParticipant(P participant) {
        this.participant = participant;
    }

    /**
     * <p>setParticipantAndAddFeature</p>
     *
     * @param participant a P object.
     */
    public void setParticipantAndAddFeature(P participant) {
        if (this.participant != null){
            this.participant.removeFeature(this);
        }

        if (participant != null){
            participant.addFeature(this);
        }
    }

    /**
     * <p>Getter for the field <code>linkedFeatures</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<F> getLinkedFeatures() {
        if(linkedFeatures == null){
            initialiseLinkedFeatures();
        }
        return this.linkedFeatures;
    }

    /**
     * <p>Getter for the field <code>aliases</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Alias> getAliases() {
        if (this.aliases == null){
            initialiseAliases();
        }
        return aliases;
    }

    /**
     * <p>processAddedIdentifierEvent</p>
     *
     * @param added a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processAddedIdentifierEvent(Xref added) {
        // the added identifier is interpro and it is not the current interpro identifier
        if (interpro != added && XrefUtils.isXrefFromDatabase(added, Xref.INTERPRO_MI, Xref.INTERPRO)){
            // the current interpro identifier is not identity, we may want to set interpro Identifier
            if (!XrefUtils.doesXrefHaveQualifier(interpro, Xref.IDENTITY_MI, Xref.IDENTITY)){
                // the interpro identifier is not set, we can set the interpro identifier
                if (interpro == null){
                    interpro = added;
                }
                else if (XrefUtils.doesXrefHaveQualifier(added, Xref.IDENTITY_MI, Xref.IDENTITY)){
                    interpro = added;
                }
                // the added xref is secondary object and the current interpro identifier is not a secondary object, we reset interpro identifier
                else if (!XrefUtils.doesXrefHaveQualifier(interpro, Xref.SECONDARY_MI, Xref.SECONDARY)
                        && XrefUtils.doesXrefHaveQualifier(added, Xref.SECONDARY_MI, Xref.SECONDARY)){
                    interpro = added;
                }
            }
        }
    }

    /**
     * <p>processRemovedIdentifierEvent</p>
     *
     * @param removed a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processRemovedIdentifierEvent(Xref removed) {
        if (interpro != null && interpro.equals(removed)){
            interpro = XrefUtils.collectFirstIdentifierWithDatabase(getIdentifiers(), Xref.INTERPRO_MI, Xref.INTERPRO);
        }
    }

    /**
     * <p>clearPropertiesLinkedToIdentifiers</p>
     */
    protected void clearPropertiesLinkedToIdentifiers() {
        interpro = null;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Feature: "+(getShortName() != null ? getShortName()+" " : "")+(getType() != null ? getType().toString()+" " : "")
                + (!getRanges().isEmpty() ? "("+getRanges().toString()+")" : " (-)");
    }

    protected class FeatureIdentifierList extends AbstractListHavingProperties<Xref> {
        public FeatureIdentifierList(){
            super();
        }

        @Override
        protected void processAddedObjectEvent(Xref added) {
            processAddedIdentifierEvent(added);
        }

        @Override
        protected void processRemovedObjectEvent(Xref removed) {
            processRemovedIdentifierEvent(removed);
        }

        @Override
        protected void clearProperties() {
            clearPropertiesLinkedToIdentifiers();
        }
    }
}

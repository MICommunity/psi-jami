package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.ChecksumUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * Abstract class for interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/07/13</pre>
 */
public abstract class AbstractInteraction<T extends Participant> implements Interaction<T> {

    private String shortName;
    private Checksum rigid;
    private Collection<Checksum> checksums;
    private Collection<Xref> identifiers;
    private Collection<Xref> xrefs;
    private Collection<Annotation> annotations;
    private Date updatedDate;
    private Date createdDate;
    private CvTerm interactionType;
    private Collection<T> participants;

    /**
     * <p>Constructor for AbstractInteraction.</p>
     *
     * @param <T> a T object.
     */
    public AbstractInteraction(){
    }

    /**
     * <p>Constructor for AbstractInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public AbstractInteraction(String shortName){
        this.shortName = shortName;
    }

    /**
     * <p>Constructor for AbstractInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractInteraction(String shortName, CvTerm type){
        this(shortName);
        this.interactionType = type;
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
     * <p>initialiseIdentifiers</p>
     */
    protected void initialiseIdentifiers(){
        this.identifiers = new ArrayList<Xref>();
    }

    /**
     * <p>initialiseParticipants</p>
     */
    protected void initialiseParticipants(){
        this.participants = new ArrayList<T>();
    }

    /**
     * <p>initialiseParticipantsWith</p>
     *
     * @param participants a {@link java.util.Collection} object.
     */
    protected void initialiseParticipantsWith(Collection<T> participants){
        if (participants == null){
            this.participants = Collections.EMPTY_LIST;
        }
        else {
            this.participants = participants;
        }
    }

    /**
     * <p>initialiseChecksums</p>
     */
    protected void initialiseChecksums(){
        this.checksums = new InteractionChecksumList();
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
     * <p>initialiseChecksumWith</p>
     *
     * @param checksums a {@link java.util.Collection} object.
     */
    protected void initialiseChecksumWith(Collection<Checksum> checksums){
        if (checksums == null){
            this.checksums = Collections.EMPTY_LIST;
        }
        else {
            this.checksums = checksums;
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
     * <p>Getter for the field <code>rigid</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRigid() {
        return this.rigid != null ? this.rigid.getValue() : null;
    }

    /** {@inheritDoc} */
    public void setRigid(String rigid) {
        InteractionChecksumList checksums = (InteractionChecksumList)getChecksums();
        if (rigid != null){
            CvTerm rigidMethod = CvTermUtils.createRigid();
            // first remove old rigid
            if (this.rigid != null){
                checksums.removeOnly(this.rigid);
            }
            this.rigid = new DefaultChecksum(rigidMethod, rigid);
            checksums.addOnly(this.rigid);
        }
        // remove all smiles if the collection is not empty
        else if (!checksums.isEmpty()) {
            ChecksumUtils.removeAllChecksumWithMethod(checksums, Checksum.RIGID_MI, Checksum.RIGID);
            this.rigid = null;
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
     * <p>Getter for the field <code>updatedDate</code>.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getUpdatedDate() {
        return this.updatedDate;
    }

    /** {@inheritDoc} */
    public void setUpdatedDate(Date updated) {
        this.updatedDate = updated;
    }

    /**
     * <p>Getter for the field <code>createdDate</code>.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /** {@inheritDoc} */
    public void setCreatedDate(Date created) {
        this.createdDate = created;
    }

    /**
     * <p>Getter for the field <code>interactionType</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getInteractionType() {
        return this.interactionType;
    }

    /** {@inheritDoc} */
    public void setInteractionType(CvTerm term) {
        this.interactionType = term;
    }

    /**
     * <p>Getter for the field <code>participants</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<T> getParticipants() {
        if (participants == null){
            initialiseParticipants();
        }
        return participants;
    }

    /**
     * <p>addParticipant</p>
     *
     * @param part a T object.
     * @return a boolean.
     */
    public boolean addParticipant(T part) {
        if (part == null){
            return false;
        }
        if (getParticipants().add(part)){
            part.setInteraction(this);
            return true;
        }
        return false;
    }

    /**
     * <p>removeParticipant</p>
     *
     * @param part a T object.
     * @return a boolean.
     */
    public boolean removeParticipant(T part) {
        if (part == null){
            return false;
        }
        if (getParticipants().remove(part)){
            part.setInteraction(null);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean addAllParticipants(Collection<? extends T> participants) {
        if (participants == null){
            return false;
        }

        boolean added = false;
        for (T p : participants){
            if (addParticipant(p)){
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    public boolean removeAllParticipants(Collection<? extends T> participants) {
        if (participants == null){
            return false;
        }

        boolean removed = false;
        for (T p : participants){
            if (removeParticipant(p)){
                removed = true;
            }
        }
        return removed;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Interaction: "+(getShortName() != null ? getShortName()+", " : "") + (getInteractionType() != null ? getInteractionType().toString() : "");
    }

    /**
     * <p>processAddedChecksumEvent</p>
     *
     * @param added a {@link psidev.psi.mi.jami.model.Checksum} object.
     */
    protected void processAddedChecksumEvent(Checksum added) {
        if (rigid == null && ChecksumUtils.doesChecksumHaveMethod(added, Checksum.RIGID_MI, Checksum.RIGID)){
            // the rigid is not set, we can set the rigid
            rigid = added;
        }
    }

    /**
     * <p>processRemovedChecksumEvent</p>
     *
     * @param removed a {@link psidev.psi.mi.jami.model.Checksum} object.
     */
    protected void processRemovedChecksumEvent(Checksum removed) {
        if (rigid == removed){
            rigid = ChecksumUtils.collectFirstChecksumWithMethod(getChecksums(), Checksum.RIGID_MI, Checksum.RIGID);
        }
    }

    /**
     * <p>clearPropertiesLinkedToChecksums</p>
     */
    protected void clearPropertiesLinkedToChecksums() {
        rigid = null;
    }

    private class InteractionChecksumList extends AbstractListHavingProperties<Checksum> {
        public InteractionChecksumList(){
            super();
        }

        @Override
        protected void processAddedObjectEvent(Checksum added) {
            processAddedChecksumEvent(added);
        }

        @Override
        protected void processRemovedObjectEvent(Checksum removed) {
            processRemovedChecksumEvent(removed);
        }

        @Override
        protected void clearProperties() {
            clearPropertiesLinkedToChecksums();
        }
    }
}

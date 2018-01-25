package psidev.psi.mi.jami.xml.model.extension;

import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.ChecksumUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.Entry;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Abstract class for xml interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/07/13</pre>
 */
@XmlTransient
public abstract class AbstractXmlInteraction<T extends Participant> implements FileSourceContext, Locatable, NamedInteraction<T>{

    private NamesContainer interactionNamesContainer;
    private InteractionXrefContainer interactionXrefContainer;
    private Boolean intraMolecular;
    private int id;
    private String imexId;
    private String complexAc;
    private String complexVersion;
    private Date updatedDate;
    private Date createdDate;
    private PsiXmlLocator sourceLocator;
    private Entry entry;

    private JAXBAttributeWrapper jaxbAttributeWrapper;
    private JAXBParticipantWrapper<T> jaxbParticipantWrapper;

    /**
     * <p>Constructor for AbstractXmlInteraction.</p>
     */
    public AbstractXmlInteraction(){
        XmlEntryContext context = XmlEntryContext.getInstance();
        this.entry = context.getCurrentEntry();
    }

    /**
     * <p>Constructor for AbstractXmlInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public AbstractXmlInteraction(String shortName){
        XmlEntryContext context = XmlEntryContext.getInstance();
        this.entry = context.getCurrentEntry();
        setShortName(shortName);
    }

    /**
     * <p>Constructor for AbstractXmlInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlInteraction(String shortName, CvTerm type){
        this(shortName);
        setInteractionType(type);
    }

    /**
     * <p>getShortName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getShortName() {
        return this.interactionNamesContainer != null ? this.interactionNamesContainer.getShortLabel() : null;
    }

    /** {@inheritDoc} */
    public void setShortName(String name) {
        if (this.interactionNamesContainer == null){
            this.interactionNamesContainer = new NamesContainer();
        }
        this.interactionNamesContainer.setShortLabel(name);
    }

    /** {@inheritDoc} */
    @Override
    public List<Alias> getAliases() {
        if (this.interactionNamesContainer == null){
            this.interactionNamesContainer = new NamesContainer();
        }
        return this.interactionNamesContainer.getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public void setFullName(String name) {
        if (this.interactionNamesContainer == null){
            this.interactionNamesContainer = new NamesContainer();
        }
        this.interactionNamesContainer.setFullName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getFullName() {
        return this.interactionNamesContainer != null ? this.interactionNamesContainer.getFullName() : null;
    }

    /**
     * <p>getRigid.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRigid() {
        Checksum rigid = this.jaxbAttributeWrapper != null ? this.jaxbAttributeWrapper.rigid : null;
        return rigid != null ? rigid.getValue() : null;
    }

    /** {@inheritDoc} */
    public void setRigid(String rigid) {
        JAXBAttributeWrapper.InteractionChecksumList checksums = (JAXBAttributeWrapper.InteractionChecksumList)getChecksums();
        Checksum rigidAnnot = jaxbAttributeWrapper.rigid;

        // add new rigid if not null
        if (rigid != null){
            CvTerm rigidTopic = CvTermUtils.createRigid();
            // first remove old rigid if not null
            if (rigidAnnot != null){
                checksums.removeOnly(rigidAnnot);
            }
            jaxbAttributeWrapper.rigid = new XmlChecksum(rigidTopic, rigid);
            checksums.addOnly(jaxbAttributeWrapper.rigid);
        }
        // remove all rigid if the collection is not empty
        else if (!checksums.isEmpty()) {
            ChecksumUtils.removeAllChecksumWithMethod(checksums, Checksum.RIGID_MI, Checksum.RIGID);
            jaxbAttributeWrapper.rigid = null;
        }
    }

    /**
     * <p>getIdentifiers.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getIdentifiers() {
        if (interactionXrefContainer == null){
            interactionXrefContainer = new InteractionXrefContainer();
        }
        return this.interactionXrefContainer.getIdentifiers();
    }

    /**
     * <p>getXrefs.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getXrefs() {
        if (interactionXrefContainer == null){
            interactionXrefContainer = new InteractionXrefContainer();
        }
        return this.interactionXrefContainer.getXrefs();
    }

    /**
     * <p>getChecksums.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Checksum> getChecksums() {
        if (this.jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        return this.jaxbAttributeWrapper.checksums;
    }

    /**
     * <p>getAnnotations.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        if (this.jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        return this.jaxbAttributeWrapper.annotations;
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
     * <p>getInteractionType.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public abstract CvTerm getInteractionType();

    /** {@inheritDoc} */
    public abstract void setInteractionType(CvTerm term);

    /**
     * <p>getParticipants.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<T> getParticipants() {
        if (jaxbParticipantWrapper == null){
            initialiseParticipantWrapper();
        }
        return this.jaxbParticipantWrapper.participants;
    }

    /**
     * <p>addParticipant.</p>
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
     * <p>removeParticipant.</p>
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

    /**
     * <p>Getter for the field <code>interactionNamesContainer</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.NamesContainer} object.
     */
    protected NamesContainer getInteractionNamesContainer() {
        if (this.interactionNamesContainer == null){
            initialiseNamesContainer();
        }
        return interactionNamesContainer;
    }

    /**
     * Sets the value of the names property.
     *
     * @param value
     *     allowed object is
     *     {@link psidev.psi.mi.jami.xml.model.extension.NamesContainer}
     */
    public void setInteractionNamesContainer(NamesContainer value) {
        this.interactionNamesContainer = value;
    }

    /**
     * <p>getInteractionXrefContainer.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.InteractionXrefContainer} object.
     */
    protected InteractionXrefContainer getInteractionXrefContainer() {
        return interactionXrefContainer;
    }

    /**
     * Sets the value of the interactionXrefContainer property.
     *
     * @param value
     *     allowed object is
     *     {@link psidev.psi.mi.jami.xml.model.extension.InteractionXrefContainer}
     *
     */
    public void setInteractionXrefContainer(InteractionXrefContainer value) {
        this.interactionXrefContainer = value;
        if (value != null && imexId != null){
            this.interactionXrefContainer.assignImexId(imexId);
        }
        if (value != null && complexAc != null){
            this.interactionXrefContainer.assignComplexAc(complexAc);
        }
    }

    /**
     * <p>isIntraMolecular.</p>
     *
     * @return a boolean.
     */
    public boolean isIntraMolecular(){
        return intraMolecular != null ? intraMolecular : false;
    }

    /**
     * Sets the value of the intraMolecular property.
     *
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean}
     */
    public void setIntraMolecular(boolean value) {
        this.intraMolecular = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return a int.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value a int.
     */
    public void setId(int value) {
        this.id = value;
        XmlEntryContext.getInstance().registerInteraction(this.id, this);
        if (getSourceLocator() != null){
            sourceLocator.setObjectId(this.id);
        }
    }

    /**
     * Gets the value of the imexId property.
     *
     * @return a {@link java.lang.String} object.
     */
    public String getImexId() {
        return imexId;
    }

    /**
     * Sets the value of the imexId property
     *
     * @param value a {@link java.lang.String} object.
     */
    public void assignImexId(String value) {
        this.imexId = value;
        if (value != null) {
            if (this.interactionXrefContainer == null) {
                this.interactionXrefContainer = new InteractionXrefContainer();
            }
            this.interactionXrefContainer.assignImexId(value);
        }
    }

    /**
     * Gets the value of the complexAc property.
     *
     * @return a {@link java.lang.String} object.
     */
    public String getComplexAc() {
        return complexAc;
    }

    /**
     * Gets the value of the complexVersion property.
     *
     * @return a {@link java.lang.String} object.
     */
    public String getComplexVersion() {
        return complexVersion;
    }
    /**
     * Sets the value of the complexAc property
     *
     * @param value a {@link java.lang.String} object.
     */
    public void assignComplexAc(String value) {
        this.complexAc = value;
        if (value != null) {
            if (this.interactionXrefContainer == null) {
                this.interactionXrefContainer = new InteractionXrefContainer();
            }
            this.interactionXrefContainer.assignComplexAc(value);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Locator sourceLocation() {
        return (Locator)getSourceLocator();
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else if (sourceLocator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)sourceLocator;
            this.sourceLocator.setObjectId(getId());
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), getId());
        }
    }

    /**
     * <p>Getter for the field <code>entry</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.Entry} object.
     */
    public Entry getEntry() {
        return entry;
    }

    /**
     * <p>Setter for the field <code>entry</code>.</p>
     *
     * @param entry a {@link psidev.psi.mi.jami.xml.model.Entry} object.
     */
    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Interaction: "+getSourceLocator().toString():super.toString());
    }

    /**
     * <p>setJAXBAttributeWrapper.</p>
     *
     * @param jaxbAttributeWrapper a {@link psidev.psi.mi.jami.xml.model.extension.AbstractXmlInteraction.JAXBAttributeWrapper} object.
     */
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper jaxbAttributeWrapper) {
        this.jaxbAttributeWrapper = jaxbAttributeWrapper;
    }

    /**
     * <p>setParticipantWrapper.</p>
     *
     * @param jaxbParticipantWrapper a {@link psidev.psi.mi.jami.xml.model.extension.AbstractXmlInteraction.JAXBParticipantWrapper} object.
     */
    public void setParticipantWrapper(JAXBParticipantWrapper<T> jaxbParticipantWrapper) {
        this.jaxbParticipantWrapper = jaxbParticipantWrapper;
        // initialise all participants because of back references
        if (this.jaxbParticipantWrapper != null && !this.jaxbParticipantWrapper.participants.isEmpty()){
            List<T> participantsToIterate = new ArrayList<T>(this.jaxbParticipantWrapper.participants);
            for (T participant : participantsToIterate){
                processAddedParticipant(participant);
                AbstractXmlParticipant xmlParticipant = (AbstractXmlParticipant)participant;
                // we have a participant pool, remove the previous participant and add the pool
                if (xmlParticipant.getParticipantPool() != null){
                    this.jaxbParticipantWrapper.participants.remove(participant);
                    this.jaxbParticipantWrapper.participants.add((T)xmlParticipant.getParticipantPool());
                }
            }
        }
        else{
            PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
            if (listener != null){
                listener.onInteractionWithoutParticipants(this, this);
            }
        }
    }


    /**
     * <p>processAddedParticipant.</p>
     *
     * @param participant a T object.
     */
    protected void processAddedParticipant(T participant) {
        participant.setInteraction(this);
    }

    /**
     * <p>initialiseAnnotationWrapper.</p>
     */
    protected void initialiseAnnotationWrapper(){
        this.jaxbAttributeWrapper = new JAXBAttributeWrapper();
    }

    /**
     * <p>initialiseParticipantWrapper.</p>
     */
    protected void initialiseParticipantWrapper(){
        this.jaxbParticipantWrapper = new JAXBParticipantWrapper();
    }

    /**
     * <p>getAttributeWrapper.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.AbstractXmlInteraction.JAXBAttributeWrapper} object.
     */
    protected JAXBAttributeWrapper getAttributeWrapper() {
        if (this.jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        return jaxbAttributeWrapper;
    }

    /**
     * <p>initialiseNamesContainer.</p>
     */
    protected void initialiseNamesContainer(){
        this.interactionNamesContainer = new NamesContainer();
    }

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="interactionAttributeWrapper")
    public static class JAXBAttributeWrapper implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Annotation> annotations;
        private InteractionChecksumList checksums;
        private JAXBAttributeList jaxbAttributes;
        private Checksum rigid;

        public JAXBAttributeWrapper(){
            initialiseAnnotations();
        }

        @Override
        public Locator sourceLocation() {
            return (Locator)getSourceLocator();
        }

        public FileSourceLocator getSourceLocator() {
            if (sourceLocator == null && locator != null){
                sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
            }
            return sourceLocator;
        }

        public void setSourceLocator(FileSourceLocator sourceLocator) {
            if (sourceLocator == null){
                this.sourceLocator = null;
            }
            else if (sourceLocator instanceof PsiXmlLocator){
                this.sourceLocator = (PsiXmlLocator)sourceLocator;
            }
            else {
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        protected void initialiseAnnotations(){
            annotations = new ArrayList<Annotation>();
            checksums = new InteractionChecksumList();
        }

        protected void initialiseAnnotationsWith(List<Annotation> annots){
            annotations = annots;
            checksums = new InteractionChecksumList();
        }

        protected List<Annotation> getAnnotations() {
            return annotations;
        }

        private void processAddedChecksumEvent(Checksum added) {
            if (rigid == null && ChecksumUtils.doesChecksumHaveMethod(added, Checksum.RIGID_MI, Checksum.RIGID)){
                // the rigid is not set, we can set the rigid
                rigid = added;
            }
        }

        private void processRemovedChecksumEvent(Checksum removed) {
            if (rigid == removed){
                rigid = ChecksumUtils.collectFirstChecksumWithMethod(checksums, Checksum.RIGID_MI, Checksum.RIGID);
            }
        }

        private void clearPropertiesLinkedToChecksums() {
            rigid = null;
        }

        @XmlElement(type=XmlAnnotation.class, name="attribute", required = true)
        public List<Annotation> getJAXBAttributes() {
            if (jaxbAttributes == null){
               jaxbAttributes = new JAXBAttributeList();
            }
            return jaxbAttributes;
        }

        protected class JAXBAttributeList extends ArrayList<Annotation> {

            public JAXBAttributeList(){
                super();
            }

            @Override
            public boolean add(Annotation annotation) {
                if (annotation == null){
                    return false;
                }
                return processAnnotation(null, annotation);
            }

            @Override
            public boolean addAll(Collection<? extends Annotation> c) {
                if (c == null){
                    return false;
                }
                boolean added = false;

                for (Annotation a : c){
                    if (add(a)){
                        added = true;
                    }
                }
                return added;
            }

            @Override
            public void add(int index, Annotation element) {
                processAnnotation(index, element);
            }

            @Override
            public boolean addAll(int index, Collection<? extends Annotation> c) {
                int newIndex = index;
                if (c == null){
                    return false;
                }
                boolean add = false;
                for (Annotation a : c){
                    if (processAnnotation(newIndex, a)){
                        newIndex++;
                        add = true;
                    }
                }
                return add;
            }

            protected boolean processAnnotation(Integer index, Annotation annotation) {
                if (AnnotationUtils.doesAnnotationHaveTopic(annotation, Checksum.CHECKSUM_MI, Checksum.CHECKUM)
                        || AnnotationUtils.doesAnnotationHaveTopic(annotation, Checksum.RIGID_MI, Checksum.RIGID)){
                    XmlChecksum checksum = new XmlChecksum(annotation.getTopic(), annotation.getValue() != null ? annotation.getValue() : PsiXmlUtils.UNSPECIFIED);
                    checksum.setSourceLocator(((FileSourceContext)annotation).getSourceLocator());
                    checksums.add(checksum);
                    return false;
                }
                else {
                    return addAnnotation(index, annotation);
                }
            }

            private boolean addAnnotation(Integer index, Annotation annotation) {
                if (index == null){
                    return annotations.add(annotation);
                }
                annotations.add(index, annotation);
                return true;
            }
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

        @Override
        public String toString() {
            return "Interaction Attribute List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="interactionParticipantWrapper")
    public static class JAXBParticipantWrapper<T extends Participant> implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<T> participants;

        public JAXBParticipantWrapper(){
            initialiseParticipants();
        }

        @Override
        public Locator sourceLocation() {
            return (Locator)getSourceLocator();
        }

        public FileSourceLocator getSourceLocator() {
            if (sourceLocator == null && locator != null){
                sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
            }
            return sourceLocator;
        }

        public void setSourceLocator(FileSourceLocator sourceLocator) {
            if (sourceLocator == null){
                this.sourceLocator = null;
            }
            else if (sourceLocator instanceof PsiXmlLocator){
                this.sourceLocator = (PsiXmlLocator)sourceLocator;
            }
            else {
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        protected void initialiseParticipants(){
            participants = new ArrayList<T>();
        }

        public List<T> getJAXBParticipants() {
            return participants;
        }

        @Override
        public String toString() {
            return "Participant List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}

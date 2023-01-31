package psidev.psi.mi.jami.xml.model.extension;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.Checksum;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The Xml implementation of Interactor
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlTransient
public abstract class AbstractBaseXmlInteractor implements Interactor, FileSourceContext, Locatable, ExtendedPsiXmlInteractor {

    private Organism organism;
    private CvTerm interactorType;
    private PsiXmlLocator sourceLocator;
    AbstractNamesContainer namesContainer;
    IInteractorXrefContainer xrefContainer;
    private String xmlSequence;
    private int id;
    private AbstractJAXBAttributeWrapper jaxbAttributeWrapper;

    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     */
    public AbstractBaseXmlInteractor(){
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractBaseXmlInteractor(String name, CvTerm type){
        if (name == null || (name != null && name.length() == 0)){
            throw new IllegalArgumentException("The short name cannot be null or empty.");
        }
        setShortName(name);
        if (type == null){
            createDefaultInteractorType();
        }
        else {
            this.interactorType = type;
        }
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractBaseXmlInteractor(String name, String fullName, CvTerm type){
        this(name, type);
        setFullName(fullName);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public AbstractBaseXmlInteractor(String name, CvTerm type, Organism organism){
        this(name, type);
        this.organism = organism;
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public AbstractBaseXmlInteractor(String name, String fullName, CvTerm type, Organism organism){
        this(name, fullName, type);
        this.organism = organism;
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractBaseXmlInteractor(String name, CvTerm type, Xref uniqueId){
        this(name, type);
        getIdentifiers().add(uniqueId);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractBaseXmlInteractor(String name, String fullName, CvTerm type, Xref uniqueId){
        this(name, fullName, type);
        getIdentifiers().add(uniqueId);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractBaseXmlInteractor(String name, CvTerm type, Organism organism, Xref uniqueId){
        this(name, type, organism);
        getIdentifiers().add(uniqueId);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractBaseXmlInteractor(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId){
        this(name, fullName, type, organism);
        getIdentifiers().add(uniqueId);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public AbstractBaseXmlInteractor(String name){
        if (name == null || (name != null && name.length() == 0)){
            throw new IllegalArgumentException("The short name cannot be null or empty.");
        }
        setShortName(name);
        createDefaultInteractorType();
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public AbstractBaseXmlInteractor(String name, String fullName){
        this(name);
        setFullName(fullName);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public AbstractBaseXmlInteractor(String name, Organism organism){
        this(name);
        this.organism = organism;
        createDefaultInteractorType();
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public AbstractBaseXmlInteractor(String name, String fullName, Organism organism){
        this(name, fullName);
        this.organism = organism;
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractBaseXmlInteractor(String name, Xref uniqueId){
        this(name);
        getIdentifiers().add(uniqueId);
        createDefaultInteractorType();
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractBaseXmlInteractor(String name, String fullName, Xref uniqueId){
        this(name, fullName);
        getIdentifiers().add(uniqueId);
        createDefaultInteractorType();
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractBaseXmlInteractor(String name, Organism organism, Xref uniqueId){
        this(name, organism);
        getIdentifiers().add(uniqueId);
        createDefaultInteractorType();
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractBaseXmlInteractor(String name, String fullName, Organism organism, Xref uniqueId){
        this(name, fullName, organism);
        getIdentifiers().add(uniqueId);
        createDefaultInteractorType();
    }

    /**
     * Gets the value of the interactorType property.
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getInteractorType() {
        if (this.interactorType == null){
            createDefaultInteractorType();
        }

        return this.interactorType;
    }

    /** {@inheritDoc} */
    public void setInteractorType(CvTerm interactorType) {
        this.interactorType = interactorType;
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
     * <p>getShortName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getShortName() {
        return getNamesContainer().getShortLabel();
    }

    /** {@inheritDoc} */
    public void setShortName(String name) {
        if (name == null || (name != null && name.length() == 0)){
            throw new IllegalArgumentException("The short name cannot be null or empty.");
        }
        getNamesContainer().setShortLabel(name);
    }

    /**
     * <p>getFullName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFullName() {
        return getNamesContainer().getFullName();
    }

    /** {@inheritDoc} */
    public void setFullName(String name) {
        getNamesContainer().setFullName(name);
    }

    /**
     * <p>getIdentifiers.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getIdentifiers() {
        if (xrefContainer == null){
            initialiseXrefContainer();
        }
        return xrefContainer.getIdentifiers();
    }

    public void setNamesContainer(AbstractNamesContainer namesContainer) {
        this.namesContainer = namesContainer;
    }

    public void setXrefContainer(IInteractorXrefContainer xrefContainer) {
        this.xrefContainer = xrefContainer;
    }

    /**
     * <p>initialiseXrefContainer.</p>
     */
    protected abstract void initialiseXrefContainer();

    /**
     * <p>initialiseNamesContainer.</p>
     */
    protected abstract void initialiseNamesContainer();

    /**
     * <p>getPreferredIdentifier.</p>
     *
     * @return the first identifier in the list of identifiers or null if the list is empty
     */
    public Xref getPreferredIdentifier() {
        return !getIdentifiers().isEmpty() ? getIdentifiers().iterator().next() : null;
    }

    /**
     * <p>getPreferredName.</p>
     *
     * @return preferred name
     */
    public String getPreferredName() {
        return this.getShortName() ;
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
     * <p>getXrefs.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getXrefs() {
        if (xrefContainer == null){
            initialiseXrefContainer();
        }
        return xrefContainer.getXrefs();
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
     * <p>getAliases.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Alias> getAliases() {
        return getNamesContainer().getAliases();
    }

    /**
     * Sets the value of the names property.
     *
     * @param value
     *     allowed object is
     *     {@link psidev.psi.mi.jami.xml.model.extension.AbstractNamesContainer}
     */
    public void setJAXBNames(AbstractNamesContainer value) {
        this.namesContainer = value;
        if (this.namesContainer != null){
            if (this.namesContainer.isEmpty()){
                this.namesContainer.setShortLabel(PsiXmlUtils.UNSPECIFIED);
                PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
                if (listener != null){
                    listener.onMissingInteractorName(this, this);
                }
            }
            else if (this.namesContainer.getShortLabel() == null){
                this.namesContainer.setShortLabel(this.namesContainer.getFullName() != null ? this.namesContainer.getFullName() : this.namesContainer.getAliases().iterator().next().getName());
            }
        }
        else{
            PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
            if (listener != null){
                listener.onMissingInteractorName(this, this );
            }
        }
    }

    /**
     * <p>setJAXBInteractorType.</p>
     *
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public void setJAXBInteractorType(CvTerm interactorType) {
        this.interactorType = interactorType;
    }

    /**
     * <p>setJAXBOrganism.</p>
     *
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public void setJAXBOrganism(Organism organism) {
        this.organism = organism;
    }

    /**
     * Sets the value of the sequence property.
     *
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setSequence(String value) {
        this.xmlSequence = value;
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
     * {@inheritDoc}
     *
     * Sets the value of the id property.
     */
    public void setId(int value) {
        this.id = value;
        XmlEntryContext.getInstance().registerInteractor(this.id, this);
        if (getSourceLocator() != null){
            sourceLocator.setObjectId(this.id);
        }
    }

    /**
     * Gets the value of the attributeList property.
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.AbstractBaseXmlInteractor.AbstractJAXBAttributeWrapper} object.
     */
    public void setJAXBAttributeWrapper(AbstractJAXBAttributeWrapper wrapper) {
        this.jaxbAttributeWrapper = wrapper;
    }

    /**
     * <p>createDefaultInteractorType.</p>
     */
    protected abstract void createDefaultInteractorType();

    /**
     * <p>getSequence.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSequence() {
        return xmlSequence;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Interactor: "+getSourceLocator().toString():super.toString());
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
        if (sourceLocator == null && locator != null){
            sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), getId());
        }
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
     * <p>setSourceLocation.</p>
     *
     * @param sourceLocator a {@link psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator} object.
     */
    public void setSourceLocation(PsiXmlLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /**
     * <p>initialiseAnnotationWrapper.</p>
     */
    protected abstract void initialiseAnnotationWrapper();

    /**
     * <p>Getter for the field <code>namesContainer</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.AbstractNamesContainer} object.
     */
    protected AbstractNamesContainer getNamesContainer() {
        if (namesContainer == null) {
            initialiseNamesContainer();
            namesContainer.setShortLabel(PsiXmlUtils.UNSPECIFIED);
        }
        return namesContainer;
    }

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlTransient
    public static abstract class AbstractJAXBAttributeWrapper implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Annotation> annotations;
        private List<Checksum> checksums;
        private JAXBAttributeList jaxbAttributeList;

        public AbstractJAXBAttributeWrapper(){
            initialiseAnnotations();
            initialiseChecksums();
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
        }

        protected void initialiseChecksums(){
            checksums = new ArrayList<Checksum>();
        }

        protected void initialiseAnnotationsWith(List<Annotation> annotations){
            if (annotations == null){
                this.annotations = Collections.EMPTY_LIST;
            }
            else {
                this.annotations = annotations;
            }
        }

        protected void initialiseChecksumsWith(List<Checksum> checksums){
            if (checksums == null){
                this.checksums = Collections.EMPTY_LIST;
            }
            else {
                this.checksums = checksums;
            }
        }

        public List<Annotation> getJAXBAttributes() {
            if (this.jaxbAttributeList == null){
                this.jaxbAttributeList = new JAXBAttributeList();
            }
            return this.jaxbAttributeList;
        }

        /**
         * The attribute list used by JAXB to populate interactor annotations
         */
        private class JAXBAttributeList extends ArrayList<Annotation>{

            public JAXBAttributeList(){
                super();
            }

            @Override
            public boolean add(Annotation a) {
                return processAnnotations(null, a);
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
                addToSpecificIndex(index, element);
            }

            @Override
            public boolean addAll(int index, Collection<? extends Annotation> c) {
                int newIndex = index;
                if (c == null){
                    return false;
                }
                boolean add = false;
                for (Annotation a : c){
                    if (addToSpecificIndex(newIndex, a)){
                        newIndex++;
                        add = true;
                    }
                }
                return add;
            }

            private boolean addToSpecificIndex(int index, Annotation a) {
                return processAnnotations(index, a);
            }

            private boolean processAnnotations(Integer index, Annotation a) {
                if (a == null){
                    return false;
                }
                if (AnnotationUtils.doesAnnotationHaveTopic(a, Checksum.CHECKSUM_MI, Checksum.CHECKUM)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, Checksum.SMILE_MI, Checksum.SMILE)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, null, Checksum.SMILE_SHORT)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, Checksum.INCHI_MI, Checksum.INCHI)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, null, Checksum.INCHI_SHORT)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, Checksum.INCHI_KEY_MI, Checksum.INCHI_KEY)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, Checksum.STANDARD_INCHI_KEY_MI, Checksum.STANDARD_INCHI_KEY)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, Checksum.ROGID_MI, Checksum.ROGID)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, Checksum.RIGID_MI, Checksum.RIGID)){
                    XmlChecksum checksum = new XmlChecksum(a.getTopic(), a.getValue() != null ? a.getValue() : PsiXmlUtils.UNSPECIFIED);
                    checksum.setSourceLocator((PsiXmlLocator)((FileSourceContext)a).getSourceLocator());
                    checksums.add(checksum);
                    return false;
                }
                else {
                    return addAnnotation(index, a);
                }
            }

            private boolean addAnnotation(Integer index, Annotation a) {
                if (index == null){
                    return annotations.add(a);
                }
                annotations.add(index, a);
                return true;
            }

            @Override
            public String toString() {
                return "Interactor Attribute List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
            }
        }
    }
}

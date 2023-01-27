package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.CooperativeEffect;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Range;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.FeatureUtils;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeature;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstract class for Xml features
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>25/07/13</pre>
 */
@XmlTransient
public abstract class AbstractXmlFeature<P extends Entity, F extends Feature> implements
        Feature<P,F>, FileSourceContext, Locatable, ExtendedPsiXmlFeature<P,F> {

    private P participant;
    private Collection<F> linkedFeatures;
    private PsiXmlLocator sourceLocator;
    private NamesContainer namesContainer;
    private FeatureXrefContainer xrefContainer;
    private CvTerm type;
    private int id;

    private JAXBAttributeWrapper jaxbAttributeWrapper;
    private JAXBRangeWrapper jaxbRangeWrapper;

    private CvTerm role;

    /**
     * <p>Constructor for AbstractXmlFeature.</p>
     */
    public AbstractXmlFeature(){
    }

    /**
     * <p>Constructor for AbstractXmlFeature.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     */
    public AbstractXmlFeature(String shortName, String fullName){
        this();
        this.namesContainer = new NamesContainer();
        this.namesContainer.setShortLabel(shortName);
        this.namesContainer.setFullName(fullName);
    }

    /**
     * <p>Constructor for AbstractXmlFeature.</p>
     *
     * @param type a {@link CvTerm} object.
     */
    public AbstractXmlFeature(CvTerm type){
        this();
        this.type = type;
    }

    /**
     * <p>Constructor for AbstractXmlFeature.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     */
    public AbstractXmlFeature(String shortName, String fullName, CvTerm type){
        this(shortName, fullName);
        this.type =type;
    }

    /**
     * <p>Constructor for AbstractXmlFeature.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     * @param interpro a {@link String} object.
     */
    public AbstractXmlFeature(String shortName, String fullName, String interpro){
        this(shortName, fullName);
        setInterpro(interpro);
    }

    /**
     * <p>Constructor for AbstractXmlFeature.</p>
     *
     * @param type a {@link CvTerm} object.
     * @param interpro a {@link String} object.
     */
    public AbstractXmlFeature(CvTerm type, String interpro){
        this(type);
        setInterpro(interpro);
    }

    /**
     * <p>Constructor for AbstractXmlFeature.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param interpro a {@link String} object.
     */
    public AbstractXmlFeature(String shortName, String fullName, CvTerm type, String interpro){
        this(shortName, fullName, type);
        setInterpro(interpro);
    }

    /**
     * <p>initialiseAnnotationWrapper.</p>
     */
    protected void initialiseAnnotationWrapper(){
        this.jaxbAttributeWrapper = new JAXBAttributeWrapper();
    }

    /**
     * <p>initialiseRangeWrapper.</p>
     */
    protected void initialiseRangeWrapper(){
        this.jaxbRangeWrapper = new JAXBRangeWrapper();
    }

    /**
     * <p>initialiseLinkedFeatures.</p>
     */
    protected void initialiseLinkedFeatures(){
        this.linkedFeatures = new ArrayList<F>();
    }

    /**
     * Sets the value of the names property.
     *
     * @param value
     *     allowed object is
     *     {@link NamesContainer}
     */
    public void setJAXBNames(NamesContainer value) {
        this.namesContainer = value;
    }

    /**
     * <p>getShortName.</p>
     *
     * @return a {@link String} object.
     */
    public String getShortName() {
        return this.namesContainer != null ? this.namesContainer.getShortLabel() : null;
    }

    /** {@inheritDoc} */
    public void setShortName(String name) {
        if (this.namesContainer == null){
            this.namesContainer = new NamesContainer();
        }
        this.namesContainer.setShortLabel(name);
    }

    /**
     * <p>getFullName.</p>
     *
     * @return a {@link String} object.
     */
    public String getFullName() {
        return this.namesContainer != null ? this.namesContainer.getFullName() : null;

    }

    /** {@inheritDoc} */
    public void setFullName(String name) {
        if (this.namesContainer == null){
            this.namesContainer = new NamesContainer();
        }
        this.namesContainer.setFullName(name);
    }

    /** {@inheritDoc} */
    @Override
    public List<Alias> getAliases() {
        if (this.namesContainer == null){
            this.namesContainer = new NamesContainer();
        }
        return this.namesContainer.getAliases();
    }

    /**
     * Sets the value of the xref property.
     *
     * @param value
     *     allowed object is
     *     {@link FeatureXrefContainer}
     */
    public void setJAXBXref(FeatureXrefContainer value) {
        this.xrefContainer = value;
    }

    /**
     * <p>getInterpro.</p>
     *
     * @return a {@link String} object.
     */
    public String getInterpro() {
        return this.xrefContainer != null ? this.xrefContainer.getInterpro() : null;
    }

    /** {@inheritDoc} */
    public void setInterpro(String interpro) {
        if (this.xrefContainer == null){
            this.xrefContainer = new FeatureXrefContainer();
        }
        this.xrefContainer.setInterpro(interpro);
    }

    /**
     * <p>getIdentifiers.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Xref> getIdentifiers() {
        if (this.xrefContainer == null){
            this.xrefContainer = new FeatureXrefContainer();
        }
        return xrefContainer.getIdentifiers();
    }

    /**
     * <p>getXrefs.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Xref> getXrefs() {
        if (this.xrefContainer == null){
            this.xrefContainer = new FeatureXrefContainer();
        }
        return xrefContainer.getXrefs();
    }

    /**
     * <p>getAnnotations.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        if (this.jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        return this.jaxbAttributeWrapper.annotations;
    }

    /**
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return a {@link CvTerm} object.
     */
    public CvTerm getType() {
        return this.type;
    }

    /** {@inheritDoc} */
    public void setType(CvTerm type) {
        this.type = type;
    }

    /**
     * <p>setJAXBType.</p>
     *
     * @param type a {@link XmlCvTerm} object.
     */
    public void setJAXBType(XmlCvTerm type) {
        this.type = type;
    }

    /**
     * <p>getRanges.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Range> getRanges() {
        if (this.jaxbRangeWrapper == null){
            initialiseRangeWrapper();
        }
        return this.jaxbRangeWrapper.ranges;
    }

    /**
     * <p>Getter for the field <code>role</code>.</p>
     *
     * @return a {@link CvTerm} object.
     */
    public CvTerm getRole() {
        if (this.role == null){
            initialiseRole();
        }
        return this.role;
    }

    /** {@inheritDoc} */
    public void setRole(CvTerm effect) {
        if (effect == null){
            this.role = null;
            if (this.jaxbAttributeWrapper != null){
                this.jaxbAttributeWrapper.role = null;
            }
        }
        else {
            this.role = effect;
        }
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
     * <p>setParticipantAndAddFeature.</p>
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
     * @return a {@link Collection} object.
     */
    public Collection<F> getLinkedFeatures() {
        if(linkedFeatures == null){
            initialiseLinkedFeatures();
        }
        return this.linkedFeatures;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Feature: "+getSourceLocator().toString():super.toString());
    }

    /** {@inheritDoc} */
    @Override
    public Locator sourceLocation() {
        return (Locator)getSourceLocator();
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link FileSourceLocator} object.
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
     * Adds this object in the mapOfReferencedObjects of this entry
     */
    public void setId(int value) {
        this.id = value;
        XmlEntryContext.getInstance().registerFeature(this.id, this);
        if (getSourceLocator() != null){
            sourceLocator.setObjectId(this.id);
        }
    }

    /**
     * <p>setJAXBAttributeWrapper.</p>
     *
     * @param jaxbAttributeWrapper a {@link JAXBAttributeWrapper} object.
     */
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper jaxbAttributeWrapper) {
        this.jaxbAttributeWrapper = jaxbAttributeWrapper;
        // initialise participant ref of the ranges
        if (this.jaxbAttributeWrapper != null && this.jaxbAttributeWrapper.participantId != null){
            for (Range range : getRanges()){
                ((XmlRange)range).setJAXBParticipantRef(this.jaxbAttributeWrapper.participantId, this.jaxbAttributeWrapper.sourceLocator);
            }
        }
    }

    /**
     * <p>setJAXBRangeWrapper.</p>
     *
     * @param jaxbRangeWrapper a {@link JAXBRangeWrapper} object.
     */
    public void setJAXBRangeWrapper(JAXBRangeWrapper jaxbRangeWrapper) {
        this.jaxbRangeWrapper = jaxbRangeWrapper;
    }

    /**
     * <p>setJAXBFeatureRole.</p>
     *
     * @param role a {@link XmlCvTerm} object.
     */
    public void setJAXBFeatureRole(XmlCvTerm role){
        this.role = role;
    }

    /**
     * <p>getJAXBRangeWrapper.</p>
     *
     * @return a {@link JAXBRangeWrapper} object.
     */
    protected JAXBRangeWrapper getJAXBRangeWrapper() {
        if (this.jaxbRangeWrapper == null){
            initialiseRangeWrapper();
        }
        return this.jaxbRangeWrapper;
    }

    /**
     * <p>getJAXBAttributeWrapper.</p>
     *
     * @return a {@link JAXBAttributeWrapper} object.
     */
    protected JAXBAttributeWrapper getJAXBAttributeWrapper() {
        if (this.jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        return this.jaxbAttributeWrapper;
    }

    /**
     * <p>initialiseRole.</p>
     */
    protected void initialiseRole() {
        if (this.jaxbAttributeWrapper != null && this.jaxbAttributeWrapper.role != null){
            this.role = this.jaxbAttributeWrapper.role;
        }
    }

    //////////////////////////////// classes
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif", name="featureAttributeWrapper")
    public static class JAXBAttributeWrapper implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Annotation> annotations;
        private JAXBAttributeList jaxbAttributeList;
        private Integer participantId;
        private CvTerm role;

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
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif", type= DefaultXmlAnnotation.class, name="attribute", required = true)
        public List<Annotation> getJAXBAttributes() {
            if (this.jaxbAttributeList == null){
                this.jaxbAttributeList = new JAXBAttributeList();
            }
            return this.jaxbAttributeList;
        }

        @Override
        public String toString() {
            return "Feature Attribute List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }

        private class JAXBAttributeList extends ArrayList<Annotation> {

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

            private boolean processAnnotation(Integer index, Annotation annotation) {
                // we have a participant ref
                if (AnnotationUtils.doesAnnotationHaveTopic(annotation, CooperativeEffect.PARTICIPANT_REF_ID, CooperativeEffect.PARTICIPANT_REF)
                        && annotation.getValue() != null){
                    try{
                        participantId = Integer.parseInt(annotation.getValue());
                        return false;
                    }
                    catch (NumberFormatException e){
                        addAnnotation(index, annotation);
                        return true;
                    }
                }
                // we have a feature role
                else if (FeatureUtils.isFeatureRole(annotation)){
                    role = new XmlCvTerm(annotation.getTopic().getShortName(), annotation.getTopic().getMIIdentifier());
                    ((XmlCvTerm)role).setSourceLocator(((FileSourceContext)annotation).getSourceLocator());
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
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif", name="featureRangeWrapper")
    public static class JAXBRangeWrapper implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Range> ranges;

        public JAXBRangeWrapper(){
            initialiseRanges();
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

        protected void initialiseRanges(){
            ranges = new ArrayList<Range>();
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif", type= XmlRange.class, name="featureRange", required = true)
        public List<Range> getJAXBRanges() {
            return ranges;
        }

        @Override
        public String toString() {
            return "Feature Range List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}

package psidev.psi.mi.jami.xml.model.extension.xml300;

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
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.comparator.cv.UnambiguousCvTermComparator;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Abstract cv term
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/07/13</pre>
 */
@XmlTransient
public abstract class AbstractXmlCvTerm implements CvTerm, FileSourceContext, Locatable{
    private CvTermXrefContainer xrefContainer;
    private NamesContainer namesContainer;

    private PsiXmlLocator sourceLocator;
    private JAXBAttributeWrapper jaxbAttributeWrapper;

    /**
     * <p>Constructor for AbstractXmlCvTerm.</p>
     */
    public AbstractXmlCvTerm(){

    }

    /**
     * <p>Constructor for AbstractXmlCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     */
    public AbstractXmlCvTerm(String shortName){
        if (shortName == null){
            throw new IllegalArgumentException("The short name is required and cannot be null");
        }
        setShortName(shortName);
    }

    /**
     * <p>Constructor for AbstractXmlCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     * @param miIdentifier a {@link String} object.
     */
    public AbstractXmlCvTerm(String shortName, String miIdentifier){
        this(shortName);
        setMIIdentifier(miIdentifier);
    }

    /**
     * <p>Constructor for AbstractXmlCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     * @param miIdentifier a {@link String} object.
     */
    public AbstractXmlCvTerm(String shortName, String fullName, String miIdentifier){
        this(shortName, miIdentifier);
        setFullName(fullName);
    }

    /**
     * <p>Constructor for AbstractXmlCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     * @param ontologyId a {@link Xref} object.
     */
    public AbstractXmlCvTerm(String shortName, Xref ontologyId){
        this(shortName);
        if (ontologyId != null){
            getIdentifiers().add(ontologyId);
        }
    }

    /**
     * <p>Constructor for AbstractXmlCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     * @param ontologyId a {@link Xref} object.
     */
    public AbstractXmlCvTerm(String shortName, String fullName, Xref ontologyId){
        this(shortName, ontologyId);
        setFullName(fullName);
    }

    /**
     * <p>getShortName.</p>
     *
     * @return a {@link String} object.
     */
    public String getShortName() {
        return getNamesContainer().getShortLabel();
    }

    /** {@inheritDoc} */
    public void setShortName(String name) {
        if (name == null){
            throw new IllegalArgumentException("The short name cannot be null");
        }
        getNamesContainer().setShortLabel(name);
    }

    /**
     * <p>getFullName.</p>
     *
     * @return a {@link String} object.
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
     * @return a {@link Collection} object.
     */
    public Collection<Xref> getIdentifiers() {
        return getXrefContainer().getIdentifiers();
    }

    /**
     * <p>getMIIdentifier.</p>
     *
     * @return a {@link String} object.
     */
    public String getMIIdentifier() {
        return getXrefContainer().getMIIdentifier();
    }

    /**
     * <p>getMODIdentifier.</p>
     *
     * @return a {@link String} object.
     */
    public String getMODIdentifier() {
        return getXrefContainer().getMODIdentifier();
    }

    /**
     * <p>getPARIdentifier.</p>
     *
     * @return a {@link String} object.
     */
    public String getPARIdentifier() {
        return getXrefContainer().getPARIdentifier();
    }

    /** {@inheritDoc} */
    public void setMIIdentifier(String mi) {
        getXrefContainer().setMIIdentifier(mi);
    }

    /** {@inheritDoc} */
    public void setMODIdentifier(String mod) {
        getXrefContainer().setMODIdentifier(mod);
    }

    /** {@inheritDoc} */
    public void setPARIdentifier(String par) {
        getXrefContainer().setPARIdentifier(par);
    }

    /**
     * <p>getXrefs.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Xref> getXrefs() {
        return getXrefContainer().getXrefs();
    }

    /**
     * <p>getSynonyms.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Alias> getSynonyms() {
        return getNamesContainer().getAliases();
    }

    /**
     * <p>getAnnotations.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        if (jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        return this.jaxbAttributeWrapper.annotations;
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
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
        }
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousCvTermComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof CvTerm)){
            return false;
        }

        return UnambiguousCvTermComparator.areEquals(this, (CvTerm) o);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        if (xrefContainer == null){
            return getShortName();
        }
        else {
            return (xrefContainer.getMIIdentifier() != null ?
                    xrefContainer.getMIIdentifier() :
                    (xrefContainer.getMODIdentifier() != null ?
                            xrefContainer.getMODIdentifier() :
                            (xrefContainer.getPARIdentifier() != null ?
                                    xrefContainer.getPARIdentifier() : "-")))
                    + " ("+getShortName()+")";
        }
    }

    /**
     * <p>initialiseAnnotationWrapper.</p>
     */
    protected void initialiseAnnotationWrapper(){
        this.jaxbAttributeWrapper = new JAXBAttributeWrapper();
    }

    /**
     * <p>Getter for the field <code>xrefContainer</code>.</p>
     *
     * @return a {@link CvTermXrefContainer} object.
     */
    protected CvTermXrefContainer getXrefContainer() {
        if (xrefContainer == null){
            xrefContainer = new CvTermXrefContainer();
        }
        return xrefContainer;
    }

    /**
     * <p>setJAXBXref.</p>
     *
     * @param value a {@link CvTermXrefContainer} object.
     */
    public void setJAXBXref(CvTermXrefContainer value) {
        this.xrefContainer = value;
    }

    /**
     * <p>Getter for the field <code>namesContainer</code>.</p>
     *
     * @return a {@link NamesContainer} object.
     */
    protected NamesContainer getNamesContainer() {
        if (namesContainer == null){
            namesContainer = new NamesContainer();
            namesContainer.setShortLabel(PsiXmlUtils.UNSPECIFIED);
        }
        return namesContainer;
    }

    /**
     * <p>setJAXBNames.</p>
     *
     * @param value a {@link NamesContainer} object.
     */
    public void setJAXBNames(NamesContainer value) {
        this.namesContainer = value;
        if (this.namesContainer != null){
            if (this.namesContainer.isEmpty()){
                this.namesContainer.setShortLabel(PsiXmlUtils.UNSPECIFIED);
                PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
                if (listener != null){
                    listener.onMissingCvTermName(this, this , "At least the shortLabel of a Cv Term is required. By default will load the Cv Term with 'unknown' shortName");
                }
            }
            else if (this.namesContainer.getShortLabel() == null){
                this.namesContainer.setShortLabel(this.namesContainer.getFullName() != null ? this.namesContainer.getFullName() : this.namesContainer.getAliases().iterator().next().getName());
            }
        }
        else{
            PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
            if (listener != null){
                listener.onMissingCvTermName(this, this , "At least the shortLabel of a Cv Term is required. By default will load the Cv Term with 'unknown' shortName");
            }
        }
    }

    /**
     * <p>getAttributeWrapper.</p>
     *
     * @return a {@link JAXBAttributeWrapper} object.
     */
    protected JAXBAttributeWrapper getAttributeWrapper() {
        if (this.jaxbAttributeWrapper == null){
           initialiseAnnotationWrapper();
        }
        return this.jaxbAttributeWrapper;
    }

    /**
     * <p>setAttributeWrapper.</p>
     *
     * @param jaxbAttributeWrapper a {@link JAXBAttributeWrapper} object.
     */
    protected void setAttributeWrapper(JAXBAttributeWrapper jaxbAttributeWrapper) {
        this.jaxbAttributeWrapper = jaxbAttributeWrapper;
    }

    //////////////////////////////// class wrapper

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="cvAnnotationWrapper")
    public static class JAXBAttributeWrapper implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private Collection<Annotation> annotations;

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

        protected void initialiseAnnotationsWith(List<Annotation> annotations){
            if (annotations == null){
                this.annotations = Collections.EMPTY_LIST;
            }
            else {
                this.annotations = annotations;
            }
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= DefaultXmlAnnotation.class, name="attribute", required = true)
        public List<Annotation> getJAXBAttributes() {
            return (List<Annotation>)annotations;
        }

        @Override
        public String toString() {
            return "Open Cv Attribute List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}

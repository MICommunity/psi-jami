//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.20 at 10:58:57 AM BST 
//


package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.utils.comparator.xref.UnambiguousXrefComparator;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlXref;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Refers to a unique object in an external database.
 *
 * <p>Java class for dbReference complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * The JAXB bindings is designed to be read-only and is not designed for writing
 *

 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlXref
    implements ExtendedPsiXmlXref, FileSourceContext, Locatable
{

    private CvTerm database;
    private CvTerm qualifier;
    private String id;
    private String version;
    private String secondary;
    private JAXBAttributeWrapper jaxbAttributeWrapper;
    private PsiXmlLocator sourceLocator;
    @XmlLocation
    @XmlTransient
    private Locator locator;

    /**
     * <p>Constructor for XmlXref.</p>
     */
    public XmlXref() {
    }

    /**
     * <p>Constructor for XmlXref.</p>
     *
     * @param database a {@link CvTerm} object.
     * @param id a {@link String} object.
     * @param qualifier a {@link CvTerm} object.
     */
    public XmlXref(CvTerm database, String id, CvTerm qualifier) {
        this(database, id);
        this.qualifier = qualifier;
    }

    /**
     * <p>Constructor for XmlXref.</p>
     *
     * @param database a {@link CvTerm} object.
     * @param id a {@link String} object.
     * @param version a {@link String} object.
     * @param qualifier a {@link CvTerm} object.
     */
    public XmlXref(CvTerm database, String id, String version, CvTerm qualifier){
        this(database, id, version);
        this.qualifier = qualifier;
    }

    /**
     * <p>Constructor for XmlXref.</p>
     *
     * @param database a {@link CvTerm} object.
     * @param id a {@link String} object.
     * @param version a {@link String} object.
     */
    public XmlXref(CvTerm database, String id, String version){
        this(database, id);
        this.version = version;
    }

    /**
     * <p>Constructor for XmlXref.</p>
     *
     * @param database a {@link CvTerm} object.
     * @param id a {@link String} object.
     */
    public XmlXref(CvTerm database, String id){
        if (database == null){
            throw new IllegalArgumentException("The database is required and cannot be null");
        }
        this.database = database;

        if (id == null || (id != null && id.length() == 0)){
            throw new IllegalArgumentException("The id is required and cannot be null or empty");
        }
        this.id = id;
    }


    /**
     * <p>Getter for the field <code>database</code>.</p>
     *
     * @return a {@link CvTerm} object.
     */
    public CvTerm getDatabase() {
        if (this.database == null){
            this.database = new DefaultCvTerm(PsiXmlUtils.UNSPECIFIED);
        }
        return this.database;
    }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getId() {
        if (this.id == null){
            this.id = PsiXmlUtils.UNSPECIFIED;
        }
        return this.id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link String}
     */
    @XmlAttribute(name = "id", required = true)
    public void setJAXBId(String value) {
        this.id = value;
        if (this.id == null){
            PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
            if (listener != null){
                listener.onXrefWithoutId(this);
            }
        }
    }

    /**
     * <p>Getter for the field <code>version</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Sets the value of the version property.
     *
     * @param value
     *     allowed object is
     *     {@link String}
     */
    @XmlAttribute(name = "version")
    public void setJAXBVersion(String value) {
        this.version = value;
    }

    /**
     * <p>Getter for the field <code>qualifier</code>.</p>
     *
     * @return a {@link CvTerm} object.
     */
    public CvTerm getQualifier() {
        return this.qualifier;
    }

    /**
     * Sets the value of the db property.
     *
     * @param value
     *     allowed object is
     *     {@link String}
     */
    @XmlAttribute(name = "db", required = true)
    public void setJAXBDb(String value) {
        if (this.database == null && value != null){
            this.database = new DefaultCvTerm(value);
        }
        else if (this.database != null){
            this.database.setShortName(value != null ? value : PsiXmlUtils.UNSPECIFIED);
        }
        if (value == null){
            PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
            if (listener != null){
                listener.onXrefWithoutDatabase(this);
            }
        }
    }

    /**
     * Sets the value of the dbAc property.
     *
     * @param value
     *     allowed object is
     *     {@link String}
     */
    @XmlAttribute(name = "dbAc")
    public void setJAXBDbAc(String value) {
        if (this.database == null && value != null){
            this.database = new DefaultCvTerm(PsiXmlUtils.UNSPECIFIED, value);
        }
        else if (this.database != null){
            this.database.setMIIdentifier(value);
        }
    }

    /**
     * Sets the value of the refType property.
     *
     * @param value
     *     allowed object is
     *     {@link String}
     */
    @XmlAttribute(name = "refType")
    public void setJAXBRefType(String value) {
        if (this.qualifier == null && value != null){
            this.qualifier = new DefaultCvTerm(value);
        }
        else if (this.qualifier != null){
            if (this.qualifier.getMIIdentifier() == null && value == null){
                this.qualifier = null;
            }
            else {
                this.qualifier.setShortName(value != null ? value : PsiXmlUtils.UNSPECIFIED);
            }
        }
    }

    /**
     * Sets the value of the refTypeAc property.
     *
     * @param value
     *     allowed object is
     *     {@link String}
     */
    @XmlAttribute(name = "refTypeAc")
    public void setJAXBRefTypeAc(String value) {
        if (this.qualifier == null && value != null){
            this.qualifier = new DefaultCvTerm(PsiXmlUtils.UNSPECIFIED, value);
        }
        else if (this.qualifier != null){
            if (PsiXmlUtils.UNSPECIFIED.equals(this.qualifier.getShortName()) && value == null){
                this.qualifier = null;
            }
            else {
                this.qualifier.setMIIdentifier(value);
            }
        }
    }

    /**
     * <p>Getter for the field <code>secondary</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getSecondary() {
        return secondary;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the value of the secondary property.
     */
    @XmlAttribute(name = "secondary")
    public void setSecondary(String value) {
        this.secondary = value;
    }


    /**
     * Gets the value of the attributeList property.
     *
     * @param wrapper a {@link JAXBAttributeWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name="attributeList")
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper wrapper) {
        this.jaxbAttributeWrapper = wrapper;
    }

    /** {@inheritDoc} */
    @Override
    public List<Annotation> getAnnotations() {
        if (this.jaxbAttributeWrapper == null){
            this.jaxbAttributeWrapper = new JAXBAttributeWrapper();
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
        if (sourceLocator == null && locator != null){
            sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
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
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {

        if (this == o){
            return true;
        }

        // Xrefs are different and it has to be ExternalIdentifier
        if (!(o instanceof Xref)){
            return false;
        }

        return UnambiguousXrefComparator.areEquals(this, (Xref) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousXrefComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Xml db reference: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }

    //////////////////////////////// classes
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="xrefAttributeWrapper")
    public static class JAXBAttributeWrapper implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Annotation> annotations;

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

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= DefaultXmlAnnotation.class, name="attribute", required = true)
        public List<Annotation> getJAXBAttributes() {
            return annotations;
        }

        @Override
        public String toString() {
            return "Xref Attribute List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}

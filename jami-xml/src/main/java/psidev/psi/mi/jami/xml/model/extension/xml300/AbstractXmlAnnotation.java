package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.utils.comparator.annotation.UnambiguousAnnotationComparator;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

/**
 * Xml implementation of an Annotation
 *
 * The JAXB binding is designed to be read-only and is not designed for writing
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractXmlAnnotation implements Annotation, FileSourceContext, Locatable {

    private CvTerm topic;
    private String value;
    private PsiXmlLocator sourceLocator;

    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * <p>Constructor for AbstractXmlAnnotation.</p>
     */
    public AbstractXmlAnnotation() {
    }

    /**
     * <p>Constructor for AbstractXmlAnnotation.</p>
     *
     * @param topic a {@link CvTerm} object.
     * @param value a {@link String} object.
     */
    public AbstractXmlAnnotation(CvTerm topic, String value) {
        if (topic == null){
            throw new IllegalArgumentException("The annotation topic cannot be null.");
        }
        this.topic = topic;
        this.value = value;
    }

    /**
     * <p>Constructor for AbstractXmlAnnotation.</p>
     *
     * @param topic a {@link CvTerm} object.
     */
    public AbstractXmlAnnotation(CvTerm topic) {
        if (topic == null){
            throw new IllegalArgumentException("The annotation topic cannot be null.");
        }
        this.topic = topic;
    }

    /**
     * <p>Getter for the field <code>topic</code>.</p>
     *
     * @return a {@link CvTerm} object.
     */
    public CvTerm getTopic() {
        if (topic == null){
            this.topic = new DefaultCvTerm(PsiXmlUtils.UNSPECIFIED);
        }
        return this.topic;
    }

    /**
     * <p>Getter for the field <code>value</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getValue() {
        return this.value;
    }

    /** {@inheritDoc} */
    @XmlValue
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String}
     */
    @XmlAttribute(name = "name", required = true)
    public void setJAXBName(String value) {
        if (this.topic == null && value != null){
            this.topic = new DefaultCvTerm(value);
        }
        else if (this.topic != null){
            this.topic.setShortName(value != null ? value : PsiXmlUtils.UNSPECIFIED);
        }
        if (value == null){
            PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
            if (listener != null){
                listener.onAnnotationWithoutTopic(this);
            }
        }
    }

    /**
     * Sets the value of the nameAc property.
     *
     * @param value
     *     allowed object is
     *     {@link String}
     */
    @XmlAttribute(name = "nameAc")
    public void setJAXBNameAc(String value) {
        if (this.topic == null && value != null){
            this.topic = new DefaultCvTerm(PsiXmlUtils.UNSPECIFIED, value);
        }
        else if (this.topic != null){
            this.topic.setMIIdentifier(value);
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
    public int hashCode() {
        return UnambiguousAnnotationComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof Annotation)){
            return false;
        }

        return UnambiguousAnnotationComparator.areEquals(this, (Annotation) o);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Xml Annotation: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}

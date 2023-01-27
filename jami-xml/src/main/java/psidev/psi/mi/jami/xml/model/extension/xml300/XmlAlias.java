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
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.utils.comparator.alias.UnambiguousAliasComparator;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

/**
 * Xml implementation of an Alias.
 *
 * The JAXB binding is designed to be read-only and is not designed for writing
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlAlias implements Alias, FileSourceContext, Locatable {

    private String name;
    private CvTerm type;
    private PsiXmlLocator sourceLocator;
    @XmlLocation
    @XmlTransient
    private Locator locator;

    /**
     * <p>Constructor for XmlAlias.</p>
     */
    public XmlAlias() {
    }

    /**
     * <p>Constructor for XmlAlias.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     */
    public XmlAlias(String name, CvTerm type) {
        if (name == null){
            throw new IllegalArgumentException("The alias name cannot be null.");
        }
        this.name = name;
        this.type = type;
    }

    /**
     * <p>Constructor for XmlAlias.</p>
     *
     * @param name a {@link String} object.
     */
    public XmlAlias(String name) {
        if (name == null){
            throw new IllegalArgumentException("The alias name cannot be null.");
        }
        this.name = name;
    }

    /**
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return a {@link CvTerm} object.
     */
    public CvTerm getType() {
        return this.type;
    }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getName() {
        if (name == null){
            name = PsiXmlUtils.UNSPECIFIED;
        }
        return name;
    }

    /**
     * <p>setJAXBName.</p>
     *
     * @param name a {@link String} object.
     */
    @XmlValue
    public void setJAXBName(String name) {
        this.name = name;
        if (this.name == null){
            PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
            if (listener != null){
                listener.onAliasWithoutName(this);
            }
        }
    }

    /**
     * Sets the value of the typeAc property.
     *
     * @param value
     *     allowed object is
     *     {@link String}
     */
    @XmlAttribute(name = "typeAc")
    public void setJAXBTypeAc(String value) {
        if (this.type == null && value != null){
            this.type = new DefaultCvTerm(PsiXmlUtils.UNSPECIFIED, value);
        }
        else if (this.type != null){
            if (PsiXmlUtils.UNSPECIFIED.equals(this.type.getShortName()) && value == null){
                this.type = null;
            }
            else {
                this.type.setMIIdentifier(value);
            }
        }
    }

    /**
     * Sets the value of the type property.
     *
     * @param value
     *     allowed object is
     *     {@link String}
     */
    @XmlAttribute(name = "type")
    public void setJAXBTypeName(String value) {
        if (this.type == null && value != null){
            this.type = new DefaultCvTerm(value);
        }
        else if (this.type != null){
            if (this.type.getMIIdentifier() == null && value == null){
                this.type = null;
            }
            else {
                this.type.setShortName(value!= null ? value : PsiXmlUtils.UNSPECIFIED);
            }
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
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof Alias)){
            return false;
        }

        return UnambiguousAliasComparator.areEquals(this, (Alias) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousAliasComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Xml Alias: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}


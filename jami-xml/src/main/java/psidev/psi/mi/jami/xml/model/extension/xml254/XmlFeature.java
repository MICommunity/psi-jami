package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

/**
 * Simple Xml implementation of a Feature
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlFeature extends AbstractXmlFeature<Entity,Feature> {

    @XmlLocation
    @XmlTransient
    private Locator locator;

    /**
     * <p>Constructor for XmlFeature.</p>
     */
    public XmlFeature() {
    }

    /**
     * <p>Constructor for XmlFeature.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     */
    public XmlFeature(String shortName, String fullName) {
        super(shortName, fullName);
    }

    /**
     * <p>Constructor for XmlFeature.</p>
     *
     * @param type a {@link CvTerm} object.
     */
    public XmlFeature(CvTerm type) {
        super(type);
    }

    /**
     * <p>Constructor for XmlFeature.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     */
    public XmlFeature(String shortName, String fullName, CvTerm type) {
        super(shortName, fullName, type);
    }

    /**
     * <p>Constructor for XmlFeature.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     * @param interpro a {@link String} object.
     */
    public XmlFeature(String shortName, String fullName, String interpro) {
        super(shortName, fullName, interpro);
    }

    /**
     * <p>Constructor for XmlFeature.</p>
     *
     * @param type a {@link CvTerm} object.
     * @param interpro a {@link String} object.
     */
    public XmlFeature(CvTerm type, String interpro) {
        super(type, interpro);
    }

    /**
     * <p>Constructor for XmlFeature.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param interpro a {@link String} object.
     */
    public XmlFeature(String shortName, String fullName, CvTerm type, String interpro) {
        super(shortName, fullName, type, interpro);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="names")
    public void setJAXBNames(NamesContainer value) {
        super.setJAXBNames(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="xref")
    public void setJAXBXref(FeatureXrefContainer value) {
        super.setJAXBXref(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="featureType")
    public void setJAXBType(XmlCvTerm type) {
        super.setJAXBType(type);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name="attributeList")
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper jaxbAttributeWrapper) {
        super.setJAXBAttributeWrapper(jaxbAttributeWrapper);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name="featureRangeList", required = true)
    public void setJAXBRangeWrapper(JAXBRangeWrapper jaxbRangeWrapper) {
        super.setJAXBRangeWrapper(jaxbRangeWrapper);
    }

    /**
     * <p>setJAXBId.</p>
     *
     * @param id a int.
     */
    @XmlAttribute(name = "id", required = true)
    public void setJAXBId(int id) {
        super.setId(id);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="featureRole")
    public void setJAXBFeatureRole(XmlCvTerm role) {
        super.setJAXBFeatureRole(role);
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        if (super.getSourceLocator() == null && locator != null){
            super.setSourceLocator(new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), getId()));
        }
        return super.getSourceLocator();
    }
}

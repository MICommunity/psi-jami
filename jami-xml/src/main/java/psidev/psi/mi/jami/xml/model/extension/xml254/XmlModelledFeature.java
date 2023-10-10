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
import psidev.psi.mi.jami.model.ModelledEntity;
import psidev.psi.mi.jami.model.ModelledFeature;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

/**
 * The xml implementation of a modelledFeature
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>25/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlModelledFeature extends AbstractXmlFeature<ModelledEntity, ModelledFeature> implements ModelledFeature {

    @XmlLocation
    @XmlTransient
    private Locator locator;

    /**
     * <p>Constructor for XmlModelledFeature.</p>
     */
    public XmlModelledFeature() {
    }

    /**
     * <p>Constructor for XmlModelledFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public XmlModelledFeature(String shortName, String fullName) {
        super(shortName, fullName);
    }

    /**
     * <p>Constructor for XmlModelledFeature.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledFeature(CvTerm type) {
        super(type);
    }

    /**
     * <p>Constructor for XmlModelledFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledFeature(String shortName, String fullName, CvTerm type) {
        super(shortName, fullName, type);
    }

    /**
     * <p>Constructor for XmlModelledFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param interpro a {@link java.lang.String} object.
     */
    public XmlModelledFeature(String shortName, String fullName, String interpro) {
        super(shortName, fullName, interpro);
    }

    /**
     * <p>Constructor for XmlModelledFeature.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param interpro a {@link java.lang.String} object.
     */
    public XmlModelledFeature(CvTerm type, String interpro) {
        super(type, interpro);
    }

    /**
     * <p>Constructor for XmlModelledFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param interpro a {@link java.lang.String} object.
     */
    public XmlModelledFeature(String shortName, String fullName, CvTerm type, String interpro) {
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
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="attributeList")
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper jaxbAttributeWrapper) {
        super.setJAXBAttributeWrapper(jaxbAttributeWrapper);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="featureRangeList", required = true)
    public void setJAXBRangeWrapper(JAXBRangeWrapper jaxbRangeWrapper) {
        super.setJAXBRangeWrapper(jaxbRangeWrapper);
    }

    /**
     * Gets the value of the id property.
     *
     * @param id a int.
     */
    @XmlAttribute(name = "id", required = true)
    public void setJAXBId(int id) {
        super.setId(id);
        // register feature as complex feature
        XmlEntryContext.getInstance().registerComplexFeature(id, this);

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

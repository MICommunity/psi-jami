package psidev.psi.mi.jami.xml.model.extension.xml253;

import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;

import javax.xml.bind.annotation.*;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.extension.XmlStoichiometry;

import java.util.List;

/**
 * Xml implementation of a simple participant
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlParticipant extends AbstractXmlParticipant<Interaction,Feature>  {

    @XmlLocation
    @XmlTransient
    private Locator locator;

    /**
     * <p>Constructor for XmlParticipant.</p>
     */
    public XmlParticipant() {
    }

    /**
     * <p>Constructor for XmlParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public XmlParticipant(Interactor interactor) {
        super(interactor);
    }

    /**
     * <p>Constructor for XmlParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public XmlParticipant(Interactor interactor, Stoichiometry stoichiometry) {
        super(interactor, stoichiometry);
    }

    /**
     * <p>Constructor for XmlParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlParticipant(Interactor interactor, CvTerm bioRole) {
        super(interactor, bioRole);
    }

    /**
     * <p>Constructor for XmlParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public XmlParticipant(Interactor interactor, CvTerm bioRole, Stoichiometry stoichiometry) {
        super(interactor, bioRole, stoichiometry);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "net:sf:psidev:mi", name ="names")
    public void setJAXBNames(NamesContainer value) {
        super.setJAXBNames(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "net:sf:psidev:mi", name ="xref")
    public void setJAXBXref(XrefContainer value) {
        super.setJAXBXref(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "net:sf:psidev:mi", name ="interactor")
    public void setJAXBInteractor(DefaultXmlInteractor interactor) {
        super.setJAXBInteractor(interactor);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "net:sf:psidev:mi", name ="interactionRef")
    public void setJAXBInteractionRef(Integer value) {
        super.setJAXBInteractionRef(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "net:sf:psidev:mi", name ="interactorRef")
    public void setJAXBInteractorRef(Integer value) {
        super.setJAXBInteractorRef(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "net:sf:psidev:mi", name ="biologicalRole")
    public void setJAXBBiologicalRole(XmlCvTerm bioRole) {
        super.setJAXBBiologicalRole(bioRole);
    }

    /**
     * <p>setJAXBId.</p>
     *
     * @param value a int.
     */
    @XmlAttribute(name = "id", required = true)
    public void setJAXBId(int value) {
        super.setId(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "net:sf:psidev:mi", name ="attributeList")
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper jaxbAttributeWrapper) {
        super.setJAXBAttributeWrapper(jaxbAttributeWrapper);
    }

    /**
     * <p>setJAXBFeatureWrapper.</p>
     *
     * @param jaxbFeatureWrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml253.XmlParticipant.JAXBFeatureWrapper} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name ="featureList")
    public void setJAXBFeatureWrapper(JAXBFeatureWrapper jaxbFeatureWrapper) {
        super.setFeatureWrapper(jaxbFeatureWrapper);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "net:sf:psidev:mi", name ="stoichiometry")
    public void setJAXBStoichiometry(XmlStoichiometry stoichiometry) {
        super.setJAXBStoichiometry(stoichiometry);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "net:sf:psidev:mi", name ="stoichiometryRange")
    public void setJAXBStoichiometryRange(XmlStoichiometryRange stoichiometry) {
        super.setJAXBStoichiometryRange(stoichiometry);
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        if (super.getSourceLocator() == null && locator != null){
            super.setSourceLocator(new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), getId()));
        }
        return super.getSourceLocator();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFeatureWrapper() {
        super.setFeatureWrapper(new JAXBFeatureWrapper());
    }

    ////////////////////////////////////////////////////// classes
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "net:sf:psidev:mi", name="participantFeatureWrapper")
    public static class JAXBFeatureWrapper extends AbstractXmlParticipant.JAXBFeatureWrapper<Feature> {

        public JAXBFeatureWrapper(){
            super();
        }

        @XmlElement(namespace = "net:sf:psidev:mi", type=XmlFeature.class, name="feature", required = true)
        public List<Feature> getJAXBFeatures() {
            return super.getJAXBFeatures();
        }
    }
}

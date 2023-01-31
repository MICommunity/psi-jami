package psidev.psi.mi.jami.xml.model.extension.xml253;

import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.XmlEntryContext;

import javax.xml.bind.annotation.*;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.extension.XmlStoichiometry;

import java.util.Collection;
import java.util.List;

/**
 * XML implementation of ModelledParticipant
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlModelledParticipant extends AbstractXmlParticipant<ModelledInteraction, ModelledFeature> implements ModelledParticipant {

    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * <p>Constructor for XmlModelledParticipant.</p>
     */
    public XmlModelledParticipant() {
    }

    /**
     * <p>Constructor for XmlModelledParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public XmlModelledParticipant(Interactor interactor) {
        super(interactor);
    }

    /**
     * <p>Constructor for XmlModelledParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlModelledParticipant(Interactor interactor, CvTerm bioRole) {
        super(interactor, bioRole);
    }

    /**
     * <p>Constructor for XmlModelledParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public XmlModelledParticipant(Interactor interactor, CvTerm bioRole, Stoichiometry stoichiometry) {
        super(interactor, bioRole, stoichiometry);
    }

    /**
     * <p>Constructor for XmlModelledParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public XmlModelledParticipant(Interactor interactor, Stoichiometry stoichiometry) {
        super(interactor, stoichiometry);
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

    /**
     * <p>setJAXBInteractorCandidates.</p>
     *
     * @param pool a {@link psidev.psi.mi.jami.xml.model.extension.xml253.XmlModelledParticipant.JAXBInteractorCandidatesWrapper} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name ="interactorCandidateList")
    public void setJAXBInteractorCandidates(JAXBInteractorCandidatesWrapper pool) {
        super.setJAXBInteractorCandidates(pool);
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
        // register participant as complex participant
        XmlEntryContext.getInstance().registerComplexParticipant(value, this);
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
     * @param jaxbFeatureWrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml253.XmlModelledParticipant.JAXBFeatureWrapper} object.
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
    @XmlType(namespace = "net:sf:psidev:mi", name="modelledParticipantFeatureWrapper")
    public static class JAXBFeatureWrapper extends AbstractXmlParticipant.JAXBFeatureWrapper<ModelledFeature> {

        public JAXBFeatureWrapper(){
            super();
        }

        public JAXBFeatureWrapper(List<ModelledFeature> features) {
            super(features);
        }

        @XmlElement(namespace = "net:sf:psidev:mi", type=XmlModelledFeature.class, name="feature", required = true)
        public List<ModelledFeature> getJAXBFeatures() {
            return super.getJAXBFeatures();
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "net:sf:psidev:mi", name="modelledParticipantInteractorCandidateWrapper")
    public static class JAXBInteractorCandidatesWrapper extends
            AbstractXmlParticipant.JAXBInteractorCandidateWrapper<ModelledInteraction, ModelledFeature, ModelledParticipantCandidate> {

        @Override
        protected void initialisePool() {
            super.setPool(new XmlModelledParticipantPool());
        }

        @Override
        @XmlElement(namespace = "net:sf:psidev:mi", name ="interactorCandidate", type = XmlModelledParticipantCandidate.class)
        public Collection<ModelledParticipantCandidate> getCandidates() {
            return super.getCandidates();
        }
    }
}

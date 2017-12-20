package psidev.psi.mi.jami.xml.model.extension.xml300;

import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.extension.*;
import psidev.psi.mi.jami.xml.model.extension.XmlInteractor;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * XML implementation of ModelledParticipantCandidate
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://psi.hupo.org/mi/mif300")
public class XmlModelledParticipantCandidate extends AbstractXmlEntity<ModelledFeature> implements ModelledParticipantCandidate {

    @XmlLocation
    @XmlTransient
    protected Locator locator;
    private ModelledParticipantPool poolParent;

    /**
     * <p>Constructor for XmlModelledParticipantCandidate.</p>
     */
    public XmlModelledParticipantCandidate() {
        super();
    }

    /**
     * <p>Constructor for XmlModelledParticipantCandidate.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public XmlModelledParticipantCandidate(Interactor interactor) {
        super(interactor);
    }

    /**
     * <p>Constructor for XmlModelledParticipantCandidate.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public XmlModelledParticipantCandidate(Interactor interactor, Stoichiometry stoichiometry) {
        super(interactor, stoichiometry);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(name = "interactor", namespace = "http://psi.hupo.org/mi/mif300")
    public void setJAXBInteractor(XmlInteractor interactor) {
        super.setJAXBInteractor(interactor);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(name = "interactorRef", namespace = "http://psi.hupo.org/mi/mif300")
    public void setJAXBInteractorRef(Integer value) {
        super.setJAXBInteractorRef(value);
    }


    /**
     * <p>setJAXBId.</p>
     *
     * @param value a int.
     */
    @XmlAttribute(name = "id", required = true, namespace = "http://psi.hupo.org/mi/mif300")
    public void setJAXBId(int value) {
        super.setId(value);
        // register participant as complex participant
        XmlEntryContext.getInstance().registerComplexParticipant(value, this);
    }

    /**
     * <p>setJAXBFeatureWrapper.</p>
     *
     * @param jaxbFeatureWrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlModelledParticipantCandidate.JAXBFeatureWrapper} object.
     */
    @XmlElement(name = "featureList", namespace = "http://psi.hupo.org/mi/mif300")
    public void setJAXBFeatureWrapper(JAXBFeatureWrapper jaxbFeatureWrapper) {
        super.setFeatureWrapper(jaxbFeatureWrapper);
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

    /** {@inheritDoc} */
    @Override
    public ModelledParticipantPool getParentPool() {
        return this.poolParent;
    }

    /** {@inheritDoc} */
    @Override
    public void setParentPool(ModelledParticipantPool pool) {
         this.poolParent = pool;
    }

    ////////////////////////////////////////////////////// classes
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="modelledEntityFeatureWrapper", namespace = "http://psi.hupo.org/mi/mif300")
    public static class JAXBFeatureWrapper extends AbstractXmlEntity.JAXBFeatureWrapper<ModelledFeature> {

        public JAXBFeatureWrapper(){
            super();
        }

        public JAXBFeatureWrapper(List<ModelledFeature> features) {
            super(features);
        }

        @XmlElement(type=XmlModelledFeature.class, name="feature", required = true, namespace = "http://psi.hupo.org/mi/mif300")
        public List<ModelledFeature> getJAXBFeatures() {
            return super.getJAXBFeatures();
        }
    }
}

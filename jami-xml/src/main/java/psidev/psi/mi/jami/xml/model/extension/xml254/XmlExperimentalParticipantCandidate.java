package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.ExperimentalParticipantCandidate;
import psidev.psi.mi.jami.model.ExperimentalParticipantPool;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.Stoichiometry;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

import java.util.List;

/**
 * Xml implementation of ParticipantEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://psi.hupo.org/mi/mif")
public class XmlExperimentalParticipantCandidate extends AbstractXmlEntity<FeatureEvidence> implements
        ExperimentalParticipantCandidate {

    @XmlLocation
    @XmlTransient
    private Locator locator;
    private ExperimentalParticipantPool poolParent;

    /**
     * <p>Constructor for XmlExperimentalParticipantCandidate.</p>
     */
    public XmlExperimentalParticipantCandidate() {
        super();
    }

    /**
     * <p>Constructor for XmlExperimentalParticipantCandidate.</p>
     *
     * @param interactor a {@link Interactor} object.
     */
    public XmlExperimentalParticipantCandidate(Interactor interactor) {
        super(interactor);
    }

    /**
     * <p>Constructor for XmlExperimentalParticipantCandidate.</p>
     *
     * @param interactor a {@link Interactor} object.
     * @param stoichiometry a {@link Stoichiometry} object.
     */
    public XmlExperimentalParticipantCandidate(Interactor interactor, Stoichiometry stoichiometry) {
        super(interactor, stoichiometry);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name = "interactor")
    public void setJAXBInteractor(DefaultXmlInteractor interactor) {
        super.setJAXBInteractor(interactor);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name = "interactorRef")
    public void setJAXBInteractorRef(Integer value) {
        super.setJAXBInteractorRef(value);
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

    /**
     * <p>setJAXBFeatureWrapper.</p>
     *
     * @param jaxbFeatureWrapper a {@link JAXBFeatureWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name = "featureList")
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
    public void processAddedFeature(FeatureEvidence feature) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFeatureWrapper() {
        super.setFeatureWrapper(new JAXBFeatureWrapper());
    }

    /** {@inheritDoc} */
    @Override
    public ExperimentalParticipantPool getParentPool() {
        return poolParent;
    }

    /** {@inheritDoc} */
    @Override
    public void setParentPool(ExperimentalParticipantPool pool) {
        this.poolParent = pool;
    }

    ////////////////////////////////////////////////////// classes
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif", name="experimentalEntityFeatureWrapper")
    public static class JAXBFeatureWrapper extends AbstractXmlEntity.JAXBFeatureWrapper<FeatureEvidence> {

        public JAXBFeatureWrapper(){
            super();
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif", type= XmlFeatureEvidence.class, name="feature", required = true)
        public List<FeatureEvidence> getJAXBFeatures() {
            return super.getJAXBFeatures();
        }
    }
}

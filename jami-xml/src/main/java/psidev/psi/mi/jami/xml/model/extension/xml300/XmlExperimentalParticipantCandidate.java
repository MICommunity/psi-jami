package psidev.psi.mi.jami.xml.model.extension.xml300;

import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.model.extension.AbstractXmlEntity;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.extension.XmlFeatureEvidence;
import psidev.psi.mi.jami.xml.model.extension.XmlInteractor;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Xml implementation of ParticipantEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://psi.hupo.org/mi/mif300")
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
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public XmlExperimentalParticipantCandidate(Interactor interactor) {
        super(interactor);
    }

    /**
     * <p>Constructor for XmlExperimentalParticipantCandidate.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public XmlExperimentalParticipantCandidate(Interactor interactor, Stoichiometry stoichiometry) {
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
    }

    /**
     * <p>setJAXBFeatureWrapper.</p>
     *
     * @param jaxbFeatureWrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlExperimentalParticipantCandidate.JAXBFeatureWrapper} object.
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
    @XmlType(name="experimentalEntityFeatureWrapper", namespace = "http://psi.hupo.org/mi/mif300")
    public static class JAXBFeatureWrapper extends AbstractXmlEntity.JAXBFeatureWrapper<FeatureEvidence> {

        public JAXBFeatureWrapper(){
            super();
        }

        @XmlElement(type=XmlFeatureEvidence.class, name="feature", required = true, namespace = "http://psi.hupo.org/mi/mif300")
        public List<FeatureEvidence> getJAXBFeatures() {
            return super.getJAXBFeatures();
        }
    }
}

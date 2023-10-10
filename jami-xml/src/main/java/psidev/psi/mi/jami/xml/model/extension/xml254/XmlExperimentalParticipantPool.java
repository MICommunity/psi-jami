package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.model.Confidence;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ExperimentalParticipantCandidate;
import psidev.psi.mi.jami.model.ExperimentalParticipantPool;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Parameter;
import psidev.psi.mi.jami.model.ParticipantEvidence;

import java.util.Collection;

/**
 * Xml implementation of participant pool
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>02/09/14</pre>
 */
@XmlTransient
public class XmlExperimentalParticipantPool extends AbstractXmlParticipantPool<InteractionEvidence, FeatureEvidence, ExperimentalParticipantCandidate>
implements ExperimentalParticipantPool{

    /**
     * <p>Constructor for XmlExperimentalParticipantPool.</p>
     */
    public XmlExperimentalParticipantPool() {
        super();
    }

    /**
     * <p>Constructor for XmlExperimentalParticipantPool.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.xml.model.extension.xml254.AbstractXmlParticipant} object.
     */
    public XmlExperimentalParticipantPool(AbstractXmlParticipant<InteractionEvidence, FeatureEvidence> delegate) {
        super(delegate);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultDelegate() {
        super.setDelegate(new XmlParticipantEvidence());
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getExperimentalRole() {
        return ((ParticipantEvidence)getDelegate()).getExperimentalRole();
    }

    /** {@inheritDoc} */
    @Override
    public void setExperimentalRole(CvTerm expRole) {
        ((ParticipantEvidence)getDelegate()).setExperimentalRole(expRole);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<CvTerm> getIdentificationMethods() {
        return ((ParticipantEvidence)getDelegate()).getIdentificationMethods();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<CvTerm> getExperimentalPreparations() {
        return ((ParticipantEvidence)getDelegate()).getExperimentalPreparations();
    }

    /** {@inheritDoc} */
    @Override
    public Organism getExpressedInOrganism() {
        return ((ParticipantEvidence)getDelegate()).getExpressedInOrganism();
    }

    /** {@inheritDoc} */
    @Override
    public void setExpressedInOrganism(Organism organism) {
        ((ParticipantEvidence)getDelegate()).setExpressedInOrganism(organism);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Confidence> getConfidences() {
        return ((ParticipantEvidence)getDelegate()).getConfidences();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Parameter> getParameters() {
        return ((ParticipantEvidence)getDelegate()).getParameters();
    }

}

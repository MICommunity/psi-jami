package psidev.psi.mi.jami.xml.model.extension.xml300;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.model.extension.AbstractXmlParticipant;
import psidev.psi.mi.jami.xml.model.extension.XmlParticipantEvidence;

import javax.xml.bind.annotation.XmlTransient;
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
     * @param delegate a {@link psidev.psi.mi.jami.xml.model.extension.AbstractXmlParticipant} object.
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

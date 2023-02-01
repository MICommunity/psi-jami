package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.model.*;

import java.util.List;

/**
 * Extended participant evidence for PSI-XML 2,5 standards
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/10/13</pre>
 */
public interface ExtendedPsiXmlParticipantEvidence extends ExtendedPsiXmlParticipant<InteractionEvidence, FeatureEvidence>,ParticipantEvidence {
    /**
     * <p>getHostOrganisms.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<Organism> getHostOrganisms();
    /**
     * <p>getExperimentalInteractors.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<AbstractExperimentalInteractor> getExperimentalInteractors();
    /**
     * <p>getExperimentalRoles.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<CvTerm> getExperimentalRoles();
}

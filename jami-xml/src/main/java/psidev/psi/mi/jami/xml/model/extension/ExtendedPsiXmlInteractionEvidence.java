package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.ParticipantEvidence;

import java.util.Collection;
import java.util.List;

/**
 * Extended interaction evidence for PSI-XML 2,5 standards
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/10/13</pre>
 */
public interface ExtendedPsiXmlInteractionEvidence extends ExtendedPsiXmlInteraction<ParticipantEvidence>, InteractionEvidence{

    /**
     * <p>getXmlAvailability.</p>
     *
     * @return a {@link AbstractAvailability} object.
     */
    public AbstractAvailability getXmlAvailability();
    /**
     * <p>setXmlAvailability.</p>
     *
     * @param availability a {@link AbstractAvailability} object.
     */
    public void setXmlAvailability(AbstractAvailability availability);
    /**
     * <p>isModelled.</p>
     *
     * @return a boolean.
     */
    public boolean isModelled();
    /**
     * <p>setModelled.</p>
     *
     * @param modelled a boolean.
     */
    public void setModelled(boolean modelled);
    /**
     * <p>getExperiments.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<Experiment> getExperiments();
    /**
     * <p>getOriginalExperiments.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<ExtendedPsiXmlExperiment> getOriginalExperiments();
    /** {@inheritDoc} */
    @Override
    public Collection<Alias> getAliases();
}

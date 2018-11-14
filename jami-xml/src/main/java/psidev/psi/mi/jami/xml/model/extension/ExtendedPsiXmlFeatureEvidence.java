package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.FeatureEvidence;

import java.util.List;

/**
 * PSI-XML 2.5 extension of a feature evidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public interface ExtendedPsiXmlFeatureEvidence extends ExtendedPsiXmlFeature<Entity<FeatureEvidence>, FeatureEvidence>,FeatureEvidence{

    /**
     * <p>getExperiments.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<Experiment> getExperiments();
}

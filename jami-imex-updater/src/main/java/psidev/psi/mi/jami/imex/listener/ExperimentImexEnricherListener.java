package psidev.psi.mi.jami.imex.listener;

import psidev.psi.mi.jami.enricher.listener.ExperimentEnricherListener;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Xref;

import java.util.Collection;

/**
 * An extension of the ExperimentEnricherListener
 * with specific methods related to the process of enriching.
 * Each method will be fired after the change has been made to the publication.
 *

 */
public interface ExperimentImexEnricherListener
        extends ExperimentEnricherListener{


    /**
     * <p>onImexIdConflicts.</p>
     *
     * @param originalExperiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param conflictingXrefs a {@link java.util.Collection} object.
     */
    public void onImexIdConflicts(Experiment originalExperiment, Collection<Xref> conflictingXrefs);

    /**
     * <p>onImexIdAssigned.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param imex a {@link java.lang.String} object.
     */
    public void onImexIdAssigned(Experiment experiment, String imex);


}

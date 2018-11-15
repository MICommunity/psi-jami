package psidev.psi.mi.jami.imex.listener.impl;

import psidev.psi.mi.jami.enricher.listener.impl.log.ExperimentEnricherLogger;
import psidev.psi.mi.jami.imex.listener.ExperimentImexEnricherListener;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Xref;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * A logging listener. It will display a message when each event if fired.
 *

 */
public class ExperimentImexEnricherLogger extends ExperimentEnricherLogger
        implements ExperimentImexEnricherListener {

    private static final Logger log = Logger.getLogger(ExperimentImexEnricherLogger.class.getName());

    /** {@inheritDoc} */
    public void onImexIdConflicts(Experiment originalExperiment, Collection<Xref> conflictingXrefs) {
        log.severe("The experiment "+originalExperiment+" has "+conflictingXrefs.size()+" IMEx primary references and only one" +
                "is allowed");
    }

    /** {@inheritDoc} */
    public void onImexIdAssigned(Experiment experiment, String imex) {
        log.info("The IMEx primary reference "+imex+" has been added to the experiment "+experiment);
    }
}

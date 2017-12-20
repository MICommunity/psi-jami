package psidev.psi.mi.jami.enricher.impl.full;

import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalExperimentUpdater;
import psidev.psi.mi.jami.enricher.util.EnricherUtils;
import psidev.psi.mi.jami.model.Experiment;

/**
 * Provides full update of experiment.
 *
 * - update minimal properties of experiment. See MinimalExperimentUpdater
 * - update xrefs
 * - update annotations
 * - update confidences
 * - update variable parameters
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 13/08/13

 */
public class FullExperimentUpdater extends FullExperimentEnricher {

    private MinimalExperimentUpdater delegate;

    /**
     * <p>Constructor for FullExperimentUpdater.</p>
     */
    public FullExperimentUpdater(){
        super();
        this.delegate = new MinimalExperimentUpdater();
    }

    /**
     * <p>Constructor for FullExperimentUpdater.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalExperimentUpdater} object.
     */
    protected FullExperimentUpdater(MinimalExperimentUpdater delegate){
        super();
        this.delegate = delegate != null ? delegate : new MinimalExperimentUpdater();
    }

    /** {@inheritDoc} */
    @Override
    public void processExperiment(Experiment experimentToEnrich, Experiment objectSource) throws EnricherException {

        this.delegate.processExperiment(experimentToEnrich, objectSource);
        processOtherProperties(experimentToEnrich, objectSource);
    }

    /** {@inheritDoc} */
    @Override
    protected void processXrefs(Experiment experimentToEnrich, Experiment objectSource) throws EnricherException{
        EnricherUtils.mergeXrefs(experimentToEnrich, experimentToEnrich.getXrefs(), objectSource.getXrefs(), true, false,
                getExperimentEnricherListener(), null);
    }

    /** {@inheritDoc} */
    @Override
    protected void processAnnotations(Experiment experimentToEnrich, Experiment objectSource) throws EnricherException{
        EnricherUtils.mergeAnnotations(experimentToEnrich, experimentToEnrich.getAnnotations(), objectSource.getAnnotations(), true,
                getExperimentEnricherListener());
    }

    /** {@inheritDoc} */
    @Override
    protected void processConfidences(Experiment experimentToEnrich, Experiment objectSource) throws EnricherException{
        EnricherUtils.mergeConfidences(experimentToEnrich, experimentToEnrich.getConfidences(), objectSource.getConfidences(), true,
                getExperimentEnricherListener());
    }

    /** {@inheritDoc} */
    @Override
    protected void processVariableParameters(Experiment experimentToEnrich, Experiment objectSource) throws EnricherException{
        mergerVariableParameters(experimentToEnrich, objectSource, true);
    }

    /**
     * <p>Getter for the field <code>delegate</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalExperimentUpdater} object.
     */
    protected MinimalExperimentUpdater getDelegate() {
        return delegate;
    }
}

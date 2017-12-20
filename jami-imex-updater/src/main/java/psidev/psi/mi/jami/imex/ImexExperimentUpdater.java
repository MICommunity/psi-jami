package psidev.psi.mi.jami.imex;

import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.full.FullExperimentEnricher;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.imex.actions.ImexAssigner;
import psidev.psi.mi.jami.imex.listener.ExperimentImexEnricherListener;

/**
 * This enricher will update an experiment attached to a publication having an IMEx id
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/10/14</pre>
 */
public class ImexExperimentUpdater extends FullExperimentEnricher{

    private ImexAssigner imexAssigner;

    /**
     * <p>Constructor for ImexExperimentUpdater.</p>
     */
    public ImexExperimentUpdater() {
        super();
    }

    /** {@inheritDoc} */
    @Override
    protected void processInteractionDetectionMethod(Experiment experimentToEnrich) throws EnricherException {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void processOrganism(Experiment experimentToEnrich) throws EnricherException {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void processConfidences(Experiment experimentToEnrich, Experiment objectSource) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void processAnnotations(Experiment experimentToEnrich, Experiment objectSource) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void processXrefs(Experiment experimentToEnrich, Experiment objectSource) {
        if (experimentToEnrich.getPublication() != null && experimentToEnrich.getPublication().getImexId() != null
                && getImexAssigner() != null){

            try {
                getImexAssigner().updateImexIdentifierForExperiment(experimentToEnrich, experimentToEnrich.getPublication().getImexId());
                if (getExperimentEnricherListener() instanceof ExperimentImexEnricherListener){
                    ((ExperimentImexEnricherListener)getExperimentEnricherListener()).onImexIdAssigned(experimentToEnrich, experimentToEnrich.getPublication().getImexId());
                }

            } catch (EnricherException e) {
                if (getExperimentEnricherListener() instanceof ExperimentImexEnricherListener){
                    ((ExperimentImexEnricherListener)getExperimentEnricherListener()).onImexIdConflicts(experimentToEnrich,
                            XrefUtils.collectAllXrefsHavingDatabaseAndQualifier(experimentToEnrich.getXrefs(), Xref.IMEX_MI, Xref.IMEX, Xref.IMEX_PRIMARY_MI, Xref.IMEX_PRIMARY));
                }
                else if (getExperimentEnricherListener() != null){
                    getExperimentEnricherListener().onEnrichmentError(experimentToEnrich, "Cannot update Imex primary reference", e);
                }
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void processInteractionDetectionMethod(Experiment experimentToEnrich, Experiment objectSource) throws EnricherException {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void processOrganism(Experiment experimentToEnrich, Experiment objectSource) throws EnricherException {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void processVariableParameters(Experiment experimentToEnrich, Experiment objectSource) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void processOtherProperties(Experiment experimentToEnrich) throws EnricherException {
        super.processOtherProperties(experimentToEnrich);
        // now works with xrefs
        processXrefs(experimentToEnrich, null);
    }

    /**
     * <p>Getter for the field <code>imexAssigner</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.imex.actions.ImexAssigner} object.
     */
    public ImexAssigner getImexAssigner() {
        return imexAssigner;
    }

    /**
     * <p>Setter for the field <code>imexAssigner</code>.</p>
     *
     * @param imexAssigner a {@link psidev.psi.mi.jami.imex.actions.ImexAssigner} object.
     */
    public void setImexAssigner(ImexAssigner imexAssigner) {
        this.imexAssigner = imexAssigner;
    }
}



package psidev.psi.mi.jami.enricher.impl.full;

import psidev.psi.mi.jami.enricher.ExperimentalParticipantPoolEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.CompositeEntityEnricher;
import psidev.psi.mi.jami.enricher.listener.ParticipantPoolEnricherListener;
import psidev.psi.mi.jami.enricher.util.EnricherUtils;
import psidev.psi.mi.jami.model.ExperimentalParticipantPool;

/**
 * The participant pool enricher is an enricher which can enrich either single participant or a collection.
 * The participant enricher has subEnrichers and no fetchers.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 19/06/13

 */
public class FullExperimentalParticipantPoolUpdater
        extends FullParticipantEvidenceUpdater<ExperimentalParticipantPool> implements ExperimentalParticipantPoolEnricher {

    private CompositeEntityEnricher entityEnricher;

    /** {@inheritDoc} */
    @Override
    public void processOtherProperties(ExperimentalParticipantPool objectToEnrich, ExperimentalParticipantPool objectSource) throws EnricherException {
        super.processOtherProperties(objectToEnrich, objectSource);

        // merge participant candidates
        processParticipantCandidates(objectToEnrich, objectSource);
    }

    /**
     * <p>processParticipantCandidates.</p>
     *
     * @param objectToEnrich a {@link psidev.psi.mi.jami.model.ExperimentalParticipantPool} object.
     * @param objectSource a {@link psidev.psi.mi.jami.model.ExperimentalParticipantPool} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processParticipantCandidates(ExperimentalParticipantPool objectToEnrich, ExperimentalParticipantPool objectSource) throws EnricherException {
        EnricherUtils.mergeParticipantCandidates(objectToEnrich, objectToEnrich, objectSource, true,
                getParticipantEnricherListener() instanceof ParticipantPoolEnricherListener ? (ParticipantPoolEnricherListener) getParticipantEnricherListener() : null,
                getParticipantCandidateEnricher());
    }

    /**
     * <p>getParticipantCandidateEnricher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.CompositeEntityEnricher} object.
     */
    public CompositeEntityEnricher getParticipantCandidateEnricher() {
        return entityEnricher;
    }

    /** {@inheritDoc} */
    public void setParticipantCandidateEnricher(CompositeEntityEnricher entityEnricher) {
        this.entityEnricher = entityEnricher;
    }
}

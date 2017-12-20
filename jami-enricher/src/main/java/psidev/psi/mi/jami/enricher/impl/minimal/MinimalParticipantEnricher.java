package psidev.psi.mi.jami.enricher.impl.minimal;

import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.ParticipantEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.util.EnricherUtils;
import psidev.psi.mi.jami.listener.AliasesChangeListener;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Participant;

import java.util.Collection;

/**
 * The participant enricher is an enricher which can enrich either single participant or a collection.
 * The participant enricher has subEnrichers and no fetchers.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 19/06/13

 */
public class MinimalParticipantEnricher<P extends Participant , F extends Feature>
        extends MinimalEntityEnricher<P,F> implements ParticipantEnricher<P,F>  {

    private CvTermEnricher<CvTerm> cvTermEnricher;

    /** {@inheritDoc} */
    public void enrich(Collection<P> participantsToEnrich) throws EnricherException {
        if(participantsToEnrich == null) throw new IllegalArgumentException("Cannot enrich a null collection of participants.");

        for(P participant : participantsToEnrich){
            enrich(participant);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void processOtherProperties(P objectToEnrich, P objectSource) throws EnricherException {
        // process aliases
        processAliases(objectToEnrich, objectSource);
        // == CvTerm BioRole =========================================================
        processBiologicalRole(objectToEnrich, objectSource);
    }

    /**
     * <p>processInteractor.</p>
     *
     * @param objectToEnrich a P object.
     * @param objectSource a P object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void processInteractor(P objectToEnrich, P objectSource) throws EnricherException {

        // nothing to do here
        processInteractor(objectToEnrich);
    }

    /**
     * <p>processBiologicalRole.</p>
     *
     * @param objectToEnrich a P object.
     * @param objectSource a P object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void processBiologicalRole(P objectToEnrich, P objectSource) throws EnricherException {
        // nothing to do here
        processBiologicalRole(objectToEnrich);
    }

    /**
     * <p>processAliases.</p>
     *
     * @param objectToEnrich a P object.
     * @param objectSource a P object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processAliases(P objectToEnrich, P objectSource) throws EnricherException{
        EnricherUtils.mergeAliases(objectToEnrich, objectToEnrich.getAliases(), objectSource.getAliases(), false,
                getParticipantEnricherListener() instanceof AliasesChangeListener ? (AliasesChangeListener)getParticipantEnricherListener() : null);
    }

    /** {@inheritDoc} */
    @Override
    public void processOtherProperties(P participantToEnrich) throws EnricherException {
        // == CvTerm BioRole =========================================================
        processBiologicalRole(participantToEnrich);
    }

    /**
     * <p>processBiologicalRole.</p>
     *
     * @param participantToEnrich a P object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    protected void processBiologicalRole(P participantToEnrich) throws EnricherException {
        if (getCvTermEnricher() != null && participantToEnrich.getBiologicalRole() != null)
            getCvTermEnricher().enrich(participantToEnrich.getBiologicalRole());
    }

    /**
     * {@inheritDoc}
     *
     * Sets the enricher for CvTerms. If null, cvTerms are not being enriched.
     */
    public void setCvTermEnricher(CvTermEnricher<CvTerm> cvTermEnricher){
        this.cvTermEnricher = cvTermEnricher;
    }

    /**
     * The current CvTerm enricher, If null, CvTerms will not be enriched.
     *
     * @return  The new enricher for CvTerms. Can be null.
     */
    public CvTermEnricher<CvTerm> getCvTermEnricher(){
        return cvTermEnricher;
    }
}

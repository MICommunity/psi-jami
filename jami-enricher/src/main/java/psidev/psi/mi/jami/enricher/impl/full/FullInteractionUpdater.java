package psidev.psi.mi.jami.enricher.impl.full;

import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.ParticipantEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalInteractionUpdater;
import psidev.psi.mi.jami.enricher.listener.InteractionEnricherListener;
import psidev.psi.mi.jami.enricher.util.EnricherUtils;
import psidev.psi.mi.jami.model.Checksum;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.utils.ChecksumUtils;

/**
 * Full updater of interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>01/10/13</pre>
 */
public class FullInteractionUpdater<I extends Interaction>
        extends FullInteractionEnricher<I>  {

    private MinimalInteractionUpdater<I> delegate;

    /**
     * <p>Constructor for FullInteractionUpdater.</p>
     */
    public FullInteractionUpdater(){
         super();
        this.delegate = new MinimalInteractionUpdater<I>();
    }

    /**
     * <p>Constructor for FullInteractionUpdater.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalInteractionUpdater} object.
     */
    protected FullInteractionUpdater(MinimalInteractionUpdater<I> delegate){
        super();
        this.delegate = delegate != null ? delegate : new MinimalInteractionUpdater<I>();
    }

    /** {@inheritDoc} */
    @Override
    public void processMinimalUpdates(I objectToEnrich, I objectSource) throws EnricherException {
        this.delegate.processMinimalUpdates(objectToEnrich, objectSource);
    }

    /** {@inheritDoc} */
    @Override
    protected void processRigid(I interactionToEnrich) throws EnricherException {
        String rigid = generateRigid(interactionToEnrich);
        String oldRigid = interactionToEnrich.getRigid();

        if ((rigid != null && !rigid.equals(oldRigid)) || (rigid == null && oldRigid != null)){
            Checksum oldChecksum = ChecksumUtils.collectFirstChecksumWithMethodAndValue(interactionToEnrich.getChecksums(), Checksum.RIGID_MI, Checksum.RIGID, oldRigid);
            interactionToEnrich.setRigid(rigid);
            if (getInteractionEnricherListener() != null){
                getInteractionEnricherListener().onRemovedChecksum(interactionToEnrich,oldChecksum);
                getInteractionEnricherListener().onAddedChecksum(interactionToEnrich,
                        ChecksumUtils.collectFirstChecksumWithMethodAndValue(interactionToEnrich.getChecksums(), Checksum.RIGID_MI, Checksum.RIGID, rigid));
            }
        }

        getRigidGenerator().getRogids().clear();
    }

    /** {@inheritDoc} */
    @Override
    protected void processChecksums(I objectToEnrich, I objectSource) throws EnricherException {
        EnricherUtils.mergeChecksums(objectToEnrich, objectToEnrich.getChecksums(), objectSource.getChecksums(), true, getInteractionEnricherListener());
        // process RIGID
        processRigid(objectToEnrich);
    }

    /** {@inheritDoc} */
    @Override
    protected void processXrefs(I objectToEnrich, I objectSource) throws EnricherException {
        EnricherUtils.mergeXrefs(objectToEnrich, objectToEnrich.getXrefs(), objectSource.getXrefs(), true, false, getInteractionEnricherListener(),
                getInteractionEnricherListener());
    }

    /** {@inheritDoc} */
    @Override
    protected void processAnnotations(I objectToEnrich, I objectSource) throws EnricherException {
        EnricherUtils.mergeAnnotations(objectToEnrich, objectToEnrich.getAnnotations(), objectSource.getAnnotations(), true, getInteractionEnricherListener());
    }

    /** {@inheritDoc} */
    @Override
    public CvTermEnricher<CvTerm> getCvTermEnricher() {
        return delegate.getCvTermEnricher();
    }

    /** {@inheritDoc} */
    @Override
    public void setCvTermEnricher(CvTermEnricher<CvTerm> cvTermEnricher) {
        delegate.setCvTermEnricher(cvTermEnricher);
    }

    /** {@inheritDoc} */
    @Override
    public void setParticipantEnricher(ParticipantEnricher participantEnricher) {
        delegate.setParticipantEnricher(participantEnricher);
    }

    /** {@inheritDoc} */
    @Override
    public ParticipantEnricher getParticipantEnricher() {
        return delegate.getParticipantEnricher();
    }

    /** {@inheritDoc} */
    @Override
    public InteractionEnricherListener<I> getInteractionEnricherListener() {
        return delegate.getInteractionEnricherListener();
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractionEnricherListener(InteractionEnricherListener<I> listener) {
        delegate.setInteractionEnricherListener(listener);
    }

    /**
     * <p>Getter for the field <code>delegate</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.minimal.MinimalInteractionUpdater} object.
     */
    protected MinimalInteractionUpdater<I> getDelegate() {
        return delegate;
    }
}

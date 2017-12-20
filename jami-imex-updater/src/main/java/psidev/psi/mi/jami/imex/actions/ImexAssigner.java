package psidev.psi.mi.jami.imex.actions;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.imex.ImexCentralClient;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.Publication;

/**
 * Interface for assigning IMEx id to a publication and updating intact publications, experiments and interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/03/12</pre>
 */
public interface ImexAssigner {

    /**
     * Assign a new IMEx id to a publication that has not been assigned yet and update/clean up the annotations of the publication (full coverage and imex curation). It adds an IMEx primary reference
     * to the intact publication. It is only possible to assign a new IMEx id to publications having valid pubmed ids (no unassigned and no DOI number)
     *
     * @param publication : the publication in IntAct
     * @param imexPublication : the publication in IMEx
     * @return the imex id if the IMEx assigner was successful, null otherwise
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if no record found or no IMEx id
     */
    public Publication assignImexIdentifier(Publication publication, Publication imexPublication) throws BridgeFailedException;

    /**
     * Add a IMEx primary reference to the experiment
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param imexId a {@link java.lang.String} object.
     * @return true if the IMEx assigner did an update of the experiment, false otherwise
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public boolean updateImexIdentifierForExperiment(Experiment experiment, String imexId) throws EnricherException;

    /**
     * Add a IMEx primary reference to the interaction
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param imexId a {@link java.lang.String} object.
     * @return true if the IMEx assigner did an update of the interaction, false otherwise
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public boolean updateImexIdentifierForInteraction(InteractionEvidence interaction, String imexId) throws EnricherException;

    /**
     * <p>isEntitledToImex.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @return true if the interaction can have an IMEx id, false otherwise
     */
    public boolean isEntitledToImex( InteractionEvidence interaction );

    /**
     * <p>clearInteractionImexContext.</p>
     */
    public void clearInteractionImexContext();

    /**
     * <p>getNextImexChunkNumberAndFilterValidImexIdsFrom.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @return a int.
     */
    public int getNextImexChunkNumberAndFilterValidImexIdsFrom(Publication publication);


    /**
     * <p>getImexCentralClient.</p>
     *
     * @return a {@link psidev.psi.mi.jami.bridges.imex.ImexCentralClient} object.
     */
    public ImexCentralClient getImexCentralClient();
}

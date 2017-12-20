package psidev.psi.mi.jami.imex.listener;

import psidev.psi.mi.jami.enricher.listener.InteractionEvidenceEnricherListener;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.Xref;

import java.util.Collection;

/**
 * An extension of the ExperimentEnricherListener
 * with specific methods related to the process of enriching.
 * Each method will be fired after the change has been made to the publication.
 *

 */
public interface InteractionImexEnricherListener
        extends InteractionEvidenceEnricherListener{


    /**
     * <p>onImexIdConflicts.</p>
     *
     * @param originalInteraction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param conflictingXrefs a {@link java.util.Collection} object.
     */
    public void onImexIdConflicts(InteractionEvidence originalInteraction, Collection<Xref> conflictingXrefs);

    /**
     * <p>onImexIdAssigned.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.InteractionEvidence} object.
     * @param imex a {@link java.lang.String} object.
     */
    public void onImexIdAssigned(InteractionEvidence interaction, String imex);


}

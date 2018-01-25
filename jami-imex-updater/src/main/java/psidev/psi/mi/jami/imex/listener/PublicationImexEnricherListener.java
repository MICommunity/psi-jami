package psidev.psi.mi.jami.imex.listener;

import psidev.psi.mi.jami.bridges.imex.PublicationStatus;
import psidev.psi.mi.jami.enricher.listener.PublicationEnricherListener;
import psidev.psi.mi.jami.model.CurationDepth;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.model.Xref;

import java.util.Collection;

/**
 * An extension of the PublicationEnricherListener
 * with specific methods related to the process of enriching.
 * Each method will be fired after the change has been made to the publication.
 *

 */
public interface PublicationImexEnricherListener
        extends PublicationEnricherListener{


    /**
     * <p>onImexIdConflicts.</p>
     *
     * @param originalPublication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param conflictingXrefs a {@link java.util.Collection} object.
     */
    public void onImexIdConflicts(Publication originalPublication, Collection<Xref> conflictingXrefs);

    /**
     * <p>onMissingImexId.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public void onMissingImexId(Publication publication);

    /**
     * <p>onCurationDepthUpdated.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param oldDepth a {@link psidev.psi.mi.jami.model.CurationDepth} object.
     */
    public void onCurationDepthUpdated(Publication publication, CurationDepth oldDepth);

    /**
     * <p>onImexAdminGroupUpdated.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param oldSource a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public void onImexAdminGroupUpdated(Publication publication, Source oldSource);

    /**
     * <p>onImexStatusUpdated.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param oldStatus a {@link psidev.psi.mi.jami.bridges.imex.PublicationStatus} object.
     */
    public void onImexStatusUpdated(Publication publication, PublicationStatus oldStatus);

    /**
     * <p>onImexPublicationIdentifierSynchronized.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public void onImexPublicationIdentifierSynchronized(Publication publication);

    /**
     * <p>onPublicationAlreadyRegisteredInImexCentral.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param imex a {@link java.lang.String} object.
     */
    public void onPublicationAlreadyRegisteredInImexCentral(Publication publication, String imex);

    /**
     * <p>onPublicationRegisteredInImexCentral.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public void onPublicationRegisteredInImexCentral(Publication publication);

    /**
     * <p>onPublicationWhichCannotBeRegistered.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public void onPublicationWhichCannotBeRegistered(Publication publication);

    /**
     * <p>onPublicationNotEligibleForImex.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public void onPublicationNotEligibleForImex(Publication publication);

    /**
     * <p>onImexIdAssigned.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param imex a {@link java.lang.String} object.
     */
    public void onImexIdAssigned(Publication publication, String imex);

    /**
     * <p>onImexIdNotRecognized.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param imex a {@link java.lang.String} object.
     */
    public void onImexIdNotRecognized(Publication publication, String imex);
}

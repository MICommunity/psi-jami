package psidev.psi.mi.jami.imex.listener.impl;


import psidev.psi.mi.jami.bridges.imex.PublicationStatus;
import psidev.psi.mi.jami.enricher.listener.PublicationEnricherListener;
import psidev.psi.mi.jami.enricher.listener.impl.PublicationEnricherListenerManager;
import psidev.psi.mi.jami.imex.listener.PublicationImexEnricherListener;
import psidev.psi.mi.jami.model.CurationDepth;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.model.Xref;

import java.util.Collection;

/**
 * A manager for listeners which holds a list of listeners.
 * Listener manager allows enrichers to send events to multiple listeners.
 * A listener itself, it implements all methods
 * which will then fire the corresponding method in each entry of the listener list.
 * No promise can be given to the order in which the listeners are fired.
 *

 */
public class PublicationImexEnricherListenerManager
        extends PublicationEnricherListenerManager
        implements PublicationImexEnricherListener {

    /**
     * A constructor to create a listener manager with no listeners.
     */
    public PublicationImexEnricherListenerManager(){}

    /**
     * A constructor to initiate a listener manager with as many listeners as required.
     *
     * @param listeners     The listeners to add.
     */
    public PublicationImexEnricherListenerManager(PublicationImexEnricherListener... listeners){
        super(listeners);
    }

    //============================================================================================

    /** {@inheritDoc} */
    public void onImexIdConflicts(Publication originalPublication, Collection<Xref> conflictingXrefs) {
        for(PublicationEnricherListener listener : getListenersList()){
            if (listener instanceof PublicationImexEnricherListener){
                ((PublicationImexEnricherListener) listener).onImexIdConflicts(originalPublication, conflictingXrefs);
            }
        }
    }

    /** {@inheritDoc} */
    public void onMissingImexId(Publication publication) {
        for(PublicationEnricherListener listener : getListenersList()){
            if (listener instanceof PublicationImexEnricherListener){
                ((PublicationImexEnricherListener) listener).onMissingImexId(publication);
            }
        }
    }

    /** {@inheritDoc} */
    public void onCurationDepthUpdated(Publication publication, CurationDepth oldDepth) {
        for(PublicationEnricherListener listener : getListenersList()){
            if (listener instanceof PublicationImexEnricherListener){
                ((PublicationImexEnricherListener) listener).onCurationDepthUpdated(publication, oldDepth);
            }
        }
    }

    /** {@inheritDoc} */
    public void onImexAdminGroupUpdated(Publication publication, Source oldSource) {
        for(PublicationEnricherListener listener : getListenersList()){
            if (listener instanceof PublicationImexEnricherListener){
                ((PublicationImexEnricherListener) listener).onImexAdminGroupUpdated(publication, oldSource);
            }
        }
    }

    /** {@inheritDoc} */
    public void onImexStatusUpdated(Publication publication, PublicationStatus oldStatus) {
        for(PublicationEnricherListener listener : getListenersList()){
            if (listener instanceof PublicationImexEnricherListener){
                ((PublicationImexEnricherListener) listener).onImexStatusUpdated(publication, oldStatus);
            }
        }
    }

    /** {@inheritDoc} */
    public void onImexPublicationIdentifierSynchronized(Publication publication) {
        for(PublicationEnricherListener listener : getListenersList()){
            if (listener instanceof PublicationImexEnricherListener){
                ((PublicationImexEnricherListener) listener).onImexPublicationIdentifierSynchronized(publication);
            }
        }
    }

    /** {@inheritDoc} */
    public void onPublicationAlreadyRegisteredInImexCentral(Publication publication, String imex) {
        for(PublicationEnricherListener listener : getListenersList()){
            if (listener instanceof PublicationImexEnricherListener){
                ((PublicationImexEnricherListener) listener).onPublicationAlreadyRegisteredInImexCentral(publication, imex);
            }
        }
    }

    /** {@inheritDoc} */
    public void onPublicationRegisteredInImexCentral(Publication publication) {
        for(PublicationEnricherListener listener : getListenersList()){
            if (listener instanceof PublicationImexEnricherListener){
                ((PublicationImexEnricherListener) listener).onPublicationRegisteredInImexCentral(publication);
            }
        }
    }

    /** {@inheritDoc} */
    public void onPublicationWhichCannotBeRegistered(Publication publication) {
        for(PublicationEnricherListener listener : getListenersList()){
            if (listener instanceof PublicationImexEnricherListener){
                ((PublicationImexEnricherListener) listener).onPublicationWhichCannotBeRegistered(publication);
            }
        }
    }

    /** {@inheritDoc} */
    public void onPublicationNotEligibleForImex(Publication publication) {
        for(PublicationEnricherListener listener : getListenersList()){
            if (listener instanceof PublicationImexEnricherListener){
                ((PublicationImexEnricherListener) listener).onPublicationNotEligibleForImex(publication);
            }
        }
    }

    /** {@inheritDoc} */
    public void onImexIdAssigned(Publication publication, String imex) {
        for(PublicationEnricherListener listener : getListenersList()){
            if (listener instanceof PublicationImexEnricherListener){
                ((PublicationImexEnricherListener) listener).onImexIdAssigned(publication, imex);
            }
        }
    }

    /** {@inheritDoc} */
    public void onImexIdNotRecognized(Publication publication, String imex) {
        for(PublicationEnricherListener listener : getListenersList()){
            if (listener instanceof PublicationImexEnricherListener){
                ((PublicationImexEnricherListener) listener).onImexIdNotRecognized(publication, imex);
            }
        }    }
}

package psidev.psi.mi.jami.enricher.listener.impl;


import psidev.psi.mi.jami.enricher.listener.PublicationEnricherListener;
import psidev.psi.mi.jami.model.*;

import java.util.Date;

/**
 * A manager for listeners which holds a list of listeners.
 * Listener manager allows enrichers to send events to multiple listeners.
 * A listener itself, it implements all methods
 * which will then fire the corresponding method in each entry of the listener list.
 * No promise can be given to the order in which the listeners are fired.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 31/07/13

 */
public class PublicationEnricherListenerManager
        extends EnricherListenerManager<Publication, PublicationEnricherListener>
        implements PublicationEnricherListener{

    /**
     * A constructor to create a listener manager with no listeners.
     */
    public PublicationEnricherListenerManager(){}

    /**
     * A constructor to initiate a listener manager with as many listeners as required.
     *
     * @param listeners     The listeners to add.
     */
    public PublicationEnricherListenerManager(PublicationEnricherListener... listeners){
        super(listeners);
    }

    //============================================================================================

    /** {@inheritDoc} */
    public void onPubmedIdUpdate(Publication publication, String oldPubmedId) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onPubmedIdUpdate(publication, oldPubmedId);
        }
    }

    /** {@inheritDoc} */
    public void onDoiUpdate(Publication publication, String oldDoi) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onDoiUpdate(publication, oldDoi);
        }
    }

    /** {@inheritDoc} */
    public void onAddedIdentifier(Publication publication, Xref addedXref) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onAddedIdentifier(publication, addedXref);
        }
    }

    /** {@inheritDoc} */
    public void onRemovedIdentifier(Publication publication, Xref removedXref) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onRemovedIdentifier(publication, removedXref);
        }
    }

    /** {@inheritDoc} */
    public void onImexIdentifierUpdate(Publication publication , Xref addedXref) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onImexIdentifierUpdate(publication, addedXref);
        }
    }

    /** {@inheritDoc} */
    public void onTitleUpdated(Publication publication, String oldTitle) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onTitleUpdated(publication, oldTitle);
        }
    }

    /** {@inheritDoc} */
    public void onJournalUpdated(Publication publication, String oldJournal) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onJournalUpdated(publication, oldJournal);
        }
    }

    /** {@inheritDoc} */
    public void onCurationDepthUpdate(Publication publication, CurationDepth oldDepth) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onCurationDepthUpdate(publication, oldDepth);
        }
    }

    /** {@inheritDoc} */
    public void onPublicationDateUpdated(Publication publication, Date oldDate) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onPublicationDateUpdated(publication, oldDate);
        }
    }

    /** {@inheritDoc} */
    public void onAuthorAdded(Publication publication, String addedAuthor) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onAuthorAdded(publication, addedAuthor);
        }
    }

    /** {@inheritDoc} */
    public void onAuthorRemoved(Publication publication, String removedAuthor) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onAuthorRemoved(publication, removedAuthor);
        }
    }

    /** {@inheritDoc} */
    public void onAddedXref(Publication publication, Xref addedXref) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onAddedXref(publication, addedXref);
        }
    }

    /** {@inheritDoc} */
    public void onRemovedXref(Publication publication, Xref removedXref) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onRemovedXref(publication, removedXref);
        }
    }

    /** {@inheritDoc} */
    public void onAddedAnnotation(Publication publication, Annotation annotationAdded) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onAddedAnnotation(publication, annotationAdded);
        }
    }

    /** {@inheritDoc} */
    public void onRemovedAnnotation(Publication publication, Annotation annotationRemoved) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onRemovedAnnotation(publication, annotationRemoved);
        }
    }

    /** {@inheritDoc} */
    public void onReleaseDateUpdated(Publication publication, Date oldDate) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onReleaseDateUpdated(publication, oldDate);
        }
    }

    /** {@inheritDoc} */
    public void onSourceUpdated(Publication publication, Source oldSource) {
        for(PublicationEnricherListener listener : getListenersList()){
            listener.onSourceUpdated(publication, oldSource);
        }
    }
}

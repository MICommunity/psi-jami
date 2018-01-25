package psidev.psi.mi.jami.enricher.listener.impl;

import psidev.psi.mi.jami.enricher.listener.FeatureEnricherListener;
import psidev.psi.mi.jami.model.*;

/**
 * A manager for listeners which holds a list of listeners.
 * Listener manager allows enrichers to send events to multiple listeners.
 * A listener itself, it implements all methods
 * which will then fire the corresponding method in each entry of the listener list.
 * No promise can be given to the order in which the listeners are fired.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 10/07/13

 */
public class FeatureEnricherListenerManager<T extends Feature>
        extends EnricherListenerManager<T, FeatureEnricherListener<T>>
        implements FeatureEnricherListener<T>{

    /**
     * A constructor to create a listener manager with no listeners.
     */
    public FeatureEnricherListenerManager(){}

    /**
     * A constructor to initiate a listener manager with as many listeners as required.
     *
     * @param listeners     The listeners to add.
     */
    public FeatureEnricherListenerManager(FeatureEnricherListener<T>... listeners){
        super(listeners);
    }


    //============================================================================================

    /** {@inheritDoc} */
    public void onShortNameUpdate(T feature, String oldShortName) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onShortNameUpdate(feature, oldShortName);
        }
    }

    /** {@inheritDoc} */
    public void onFullNameUpdate(T feature, String oldFullName) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onFullNameUpdate(feature, oldFullName);
        }
    }

    /** {@inheritDoc} */
    public void onInterproUpdate(T feature, String oldInterpro) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onInterproUpdate(feature, oldInterpro);
        }
    }

    /** {@inheritDoc} */
    public void onTypeUpdate(T feature, CvTerm oldType) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onTypeUpdate(feature, oldType);
        }
    }


    /** {@inheritDoc} */
    public void onAddedIdentifier(T feature, Xref added) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onAddedIdentifier(feature, added);
        }
    }

    /** {@inheritDoc} */
    public void onRemovedIdentifier(T feature, Xref removed) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onRemovedIdentifier(feature, removed);
        }
    }

    /** {@inheritDoc} */
    public void onAddedXref(T feature, Xref added) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onAddedXref(feature, added);
        }
    }

    /** {@inheritDoc} */
    public void onRemovedXref(T feature, Xref removed) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener. onRemovedXref(feature, removed);
        }
    }

    /** {@inheritDoc} */
    public void onAddedAnnotation(T feature, Annotation added) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onAddedAnnotation(feature, added);
        }
    }

    /** {@inheritDoc} */
    public void onRemovedAnnotation(T feature, Annotation removed) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onRemovedAnnotation(feature, removed);
        }
    }

    /** {@inheritDoc} */
    public void onAddedRange(T feature, Range added) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener. onAddedRange(feature, added);
        }
    }

    /** {@inheritDoc} */
    public void onRemovedRange(T feature, Range removed) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onRemovedRange(feature, removed);
        }
    }

    /** {@inheritDoc} */
    public void onUpdatedRangePositions(T feature, Range range, Position position, Position position2) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onUpdatedRangePositions(feature, range, position, position2);
        }
    }

    /** {@inheritDoc} */
    public void onRoleUpdate(T feature, CvTerm oldDependency) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onRoleUpdate(feature, oldDependency);
        }
    }

    /**
     * <p>onAddedLinkedFeature.</p>
     *
     * @param feature a T object.
     * @param added a T object.
     */
    public void onAddedLinkedFeature(T feature, T added) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onAddedLinkedFeature(feature, added);
        }
    }

    /**
     * <p>onRemovedLinkedFeature.</p>
     *
     * @param feature a T object.
     * @param removed a T object.
     */
    public void onRemovedLinkedFeature(T feature, T removed) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onRemovedLinkedFeature(feature, removed);
        }
    }

    /** {@inheritDoc} */
    public void onAddedAlias(T o, Alias added) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onAddedAlias(o, added);
        }
    }

    /** {@inheritDoc} */
    public void onRemovedAlias(T o, Alias removed) {
        for(FeatureEnricherListener listener : getListenersList()){
            listener.onRemovedAlias(o, removed);
        }
    }
}

package psidev.psi.mi.jami.enricher.listener.impl;


import psidev.psi.mi.jami.enricher.listener.NucleicAcidEnricherListener;
import psidev.psi.mi.jami.model.NucleicAcid;

/**
 * A manager for listeners which holds a list of listeners.
 * Listener manager allows enrichers to send events to multiple listeners.
 * A listener itself, it implements all methods
 * which will then fire the corresponding method in each entry of the listener list.
 * No promise can be given to the order in which the listeners are fired.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since  12/06/13

 */
public class NucleicAcidEnricherListenerManager
        extends PolymerEnricherListenerManager<NucleicAcid>
        implements NucleicAcidEnricherListener {

    /**
     * A constructor to create a listener manager with no listeners.
     */
    public NucleicAcidEnricherListenerManager(){ }

    /**
     * A constructor to initiate a listener manager with as many listeners as required.
     *
     * @param listeners     The listeners to add.
     */
    public NucleicAcidEnricherListenerManager(NucleicAcidEnricherListener... listeners){
        super(listeners);
    }

    //============================================================================================
}

package psidev.psi.mi.jami.enricher.listener.impl.log;


import psidev.psi.mi.jami.enricher.listener.NucleicAcidEnricherListener;
import psidev.psi.mi.jami.model.NucleicAcid;

import java.util.logging.Logger;

/**
 * A logging listener. It will display a message when each event if fired.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since  11/06/13

 */
public class NucleicAcidEnricherLogger
        extends PolymerEnricherLogger<NucleicAcid>
        implements NucleicAcidEnricherListener {

    private static final Logger log = Logger.getLogger(NucleicAcidEnricherLogger.class.getName());
}

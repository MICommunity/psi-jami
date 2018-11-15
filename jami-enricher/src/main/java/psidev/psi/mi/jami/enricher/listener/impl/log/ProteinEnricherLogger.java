package psidev.psi.mi.jami.enricher.listener.impl.log;


import psidev.psi.mi.jami.enricher.listener.ProteinEnricherListener;
import psidev.psi.mi.jami.model.Protein;

import java.util.logging.Logger;

/**
 * A logging listener. It will display a message when each event if fired.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since  11/06/13

 */
public class ProteinEnricherLogger
        extends PolymerEnricherLogger<Protein>
        implements ProteinEnricherListener {

    private static final Logger log = Logger.getLogger(ProteinEnricherLogger.class.getName());
}

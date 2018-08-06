package psidev.psi.mi.jami.enricher.listener.impl.log;

import psidev.psi.mi.jami.enricher.listener.BioactiveEntityEnricherListener;
import psidev.psi.mi.jami.model.BioactiveEntity;

import java.util.logging.Logger;

/**
 * A logging listener. It will display a message when each event is fired.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 07/08/13

 */
public class BioactiveEntityEnricherLogger extends InteractorEnricherLogger<BioactiveEntity>
        implements BioactiveEntityEnricherListener {

    private static final Logger log = Logger.getLogger(BioactiveEntityEnricherLogger.class.getName());
}

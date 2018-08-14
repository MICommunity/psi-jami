package psidev.psi.mi.jami.enricher.listener.impl.log;

import psidev.psi.mi.jami.enricher.listener.GeneEnricherListener;
import psidev.psi.mi.jami.model.Gene;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 03/09/13

 */
public class GeneEnricherLogger extends InteractorEnricherLogger<Gene>
        implements GeneEnricherListener {

    private static final Logger log = Logger.getLogger(GeneEnricherLogger.class.getName());
}

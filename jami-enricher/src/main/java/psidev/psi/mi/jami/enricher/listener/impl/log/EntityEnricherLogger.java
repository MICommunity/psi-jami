package psidev.psi.mi.jami.enricher.listener.impl.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.enricher.listener.EntityEnricherListener;
import psidev.psi.mi.jami.listener.impl.EntityChangeLogger;
import psidev.psi.mi.jami.model.Entity;

/**
 * A logging listener. It will display a message when each event if fired.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 22/07/13

 */
public class EntityEnricherLogger<P extends Entity>
        extends EntityChangeLogger<P> implements EntityEnricherListener<P> {

    private static final Logger log = LoggerFactory.getLogger(EntityEnricherLogger.class.getName());

    /** {@inheritDoc} */
    public void onEnrichmentComplete(P participant, EnrichmentStatus status, String message) {
        log.info(participant.toString()+" enrichment complete with status ["+status+"], message: "+message);
    }

    /** {@inheritDoc} */
    public void onEnrichmentError(P object, String message, Exception e) {
        log.error(object.toString()+" enrichment error, message: "+message, e);
    }
}

package psidev.psi.mi.jami.enricher.listener.impl.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psidev.psi.mi.jami.enricher.listener.CvTermEnricherListener;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.listener.impl.CvTermChangeLogger;
import psidev.psi.mi.jami.model.CvTerm;

/**
 * A logging listener. It will display a message when each event is fired.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 18/07/13

 */
public class CvTermEnricherLogger extends CvTermChangeLogger implements CvTermEnricherListener<CvTerm>{

    private static final Logger log = LoggerFactory.getLogger(CvTermEnricherLogger.class.getName());

    /** {@inheritDoc} */
    public void onEnrichmentComplete(CvTerm cvTerm, EnrichmentStatus status, String message) {
        log.info(cvTerm.toString()+" enrichment complete with status ["+status+"], message: "+message);
    }

    /** {@inheritDoc} */
    public void onEnrichmentError(CvTerm object, String message, Exception e) {
        log.error(object.toString()+" enrichment error, message: "+message, e);
    }
}

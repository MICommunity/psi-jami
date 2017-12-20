package psidev.psi.mi.jami.imex.actions;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.imex.ImexCentralClient;
import psidev.psi.mi.jami.bridges.imex.PublicationStatus;
import psidev.psi.mi.jami.bridges.imex.extension.ImexPublication;
import psidev.psi.mi.jami.model.Publication;

/**
 * interface for synchronizing publication status with IMEx central
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/03/12</pre>
 */
public interface PublicationStatusSynchronizer {

    /**
     * Synchronize publication status with IMEx central and update the IMEx central record if necessary.
     *
     * @param intactPublication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param imexPublication a {@link psidev.psi.mi.jami.bridges.imex.extension.ImexPublication} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public void synchronizePublicationStatusWithImexCentral(Publication intactPublication, ImexPublication imexPublication) throws BridgeFailedException;

    /**
     * <p>getPublicationStatus.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @return the imex central publication status that is matching the status of the publication
     */
    public PublicationStatus getPublicationStatus(Publication publication);

    /**
     * <p>getImexCentralClient.</p>
     *
     * @return a {@link psidev.psi.mi.jami.bridges.imex.ImexCentralClient} object.
     */
    public ImexCentralClient getImexCentralClient();

    /**
     * Synchronize publication status with IMEx central and update the IMEx central record if necessary.
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param imexPublication a {@link psidev.psi.mi.jami.bridges.imex.extension.ImexPublication} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException is status not recognized or no records could be found or IMEx central is not responding
     */
    public void discardPublicationInImexCentral(Publication publication, ImexPublication imexPublication) throws BridgeFailedException;
}

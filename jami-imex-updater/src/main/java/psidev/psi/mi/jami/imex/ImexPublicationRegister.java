package psidev.psi.mi.jami.imex;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.imex.ImexCentralClient;
import psidev.psi.mi.jami.bridges.imex.extension.ImexPublication;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.full.FullPublicationEnricher;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.imex.actions.ImexCentralPublicationRegister;
import psidev.psi.mi.jami.imex.actions.PublicationAdminGroupSynchronizer;
import psidev.psi.mi.jami.imex.actions.PublicationStatusSynchronizer;
import psidev.psi.mi.jami.imex.listener.PublicationImexEnricherListener;

import java.util.regex.Pattern;

/**
 * This enricher will update a publication having IMEx id and synchronize it with IMEx central
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/10/14</pre>
 */
public class ImexPublicationRegister extends FullPublicationEnricher{
    private PublicationAdminGroupSynchronizer adminGroupSynchronizer;
    private PublicationStatusSynchronizer statusSynchronizer;
    private ImexCentralPublicationRegister publicationRegister;

    /** Constant <code>IMEX_SECONDARY_MI="MI:0952"</code> */
    public static String IMEX_SECONDARY_MI = "MI:0952";
    /** Constant <code>IMEX_SECONDARY="imex secondary"</code> */
    public static String IMEX_SECONDARY = "imex secondary";
    /** Constant <code>FULL_COVERAGE_MI="MI:0957"</code> */
    public static String FULL_COVERAGE_MI = "MI:0957";
    /** Constant <code>FULL_COVERAGE="full coverage"</code> */
    public static String FULL_COVERAGE = "full coverage";
    /** Constant <code>IMEX_CURATION_MI="MI:0959"</code> */
    public static String IMEX_CURATION_MI = "MI:0959";
    /** Constant <code>IMEX_CURATION="imex curation"</code> */
    public static String IMEX_CURATION = "imex curation";
    /** Constant <code>CURATION_DEPTH_MI="MI:0955"</code> */
    public static String CURATION_DEPTH_MI = "MI:0955";
    /** Constant <code>CURATION_DEPTH="curation depth"</code> */
    public static String CURATION_DEPTH = "curation depth";
    /** Constant <code>FULL_COVERAGE_TEXT="Only protein-protein interactions"</code> */
    public static String FULL_COVERAGE_TEXT = "Only protein-protein interactions";

    /**
     * <p>Constructor for ImexPublicationRegister.</p>
     *
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.imex.ImexCentralClient} object.
     */
    public ImexPublicationRegister(ImexCentralClient fetcher) {
        super(fetcher);
    }

    /** {@inheritDoc} */
    @Override
    protected void processCurationDepth(Publication publicationToEnrich, Publication fetched) {
        if (publicationToEnrich.getSource() != null && getAdminGroupSynchronizer() != null && fetched instanceof ImexPublication){
            try {
                getAdminGroupSynchronizer().synchronizePublicationAdminGroup(publicationToEnrich, (ImexPublication)fetched);
                if (getPublicationEnricherListener() instanceof PublicationImexEnricherListener){
                    ((PublicationImexEnricherListener)getPublicationEnricherListener()).onImexAdminGroupUpdated(publicationToEnrich, fetched.getSource());
                }
            } catch (BridgeFailedException e) {
                if (getPublicationEnricherListener() != null){
                    getPublicationEnricherListener().onEnrichmentError(publicationToEnrich, "Cannot update the admin group of publication "+publicationToEnrich+" in IMEx central", e);
                }
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void processReleasedDate(Publication publicationToEnrich, Publication fetched) {
        if (publicationToEnrich.getReleasedDate() != null && getStatusSynchronizer() != null && fetched instanceof ImexPublication){
            try {
                getStatusSynchronizer().synchronizePublicationStatusWithImexCentral(publicationToEnrich, (ImexPublication)fetched);
                if (getPublicationEnricherListener() instanceof PublicationImexEnricherListener){
                    ((PublicationImexEnricherListener)getPublicationEnricherListener()).onImexStatusUpdated(publicationToEnrich,
                            ((ImexPublication) fetched).getStatus());
                }
            } catch (BridgeFailedException e) {
                if (getPublicationEnricherListener() != null){
                    getPublicationEnricherListener().onEnrichmentError(publicationToEnrich, "Cannot update the status of publication " + publicationToEnrich + " in IMEx central", e);
                }
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void processXrefs(Publication publicationToEnrich, Publication fetched) {
        // nothing to do here
    }

    /** {@inheritDoc} */
    @Override
    protected void processAnnotations(Publication publicationToEnrich, Publication fetched) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void processJournal(Publication publicationToEnrich, Publication fetched) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void processPublicationTitle(Publication publicationToEnrich, Publication fetched) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void processPublicationDate(Publication publicationToEnrich, Publication fetched) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void processAuthors(Publication publicationToEnrich, Publication fetched) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void processIdentifiers(Publication publicationToEnrich, Publication fetched) {
         // nothing to do
    }


    /** {@inheritDoc} */
    @Override
    public ImexPublication find(Publication publicationToEnrich) throws EnricherException {
        String pubId = publicationToEnrich.getPubmedId() != null ? publicationToEnrich.getPubmedId() : publicationToEnrich.getDoi();
        String source = publicationToEnrich.getPubmedId() != null ? Xref.PUBMED : Xref.DOI;
        if (pubId == null && !publicationToEnrich.getIdentifiers().isEmpty()) {
            Xref id = publicationToEnrich.getXrefs().iterator().next();
            source = id.getDatabase().getShortName();
            pubId = id.getId();
        }

        if(source != null && pubId != null){
            ImexPublication imexPublication = fetchImexPublication(pubId, source);
            // we register the publication only if we have a valid pubmed identifier
            if (imexPublication == null && Pattern.matches(ImexCentralClient.pubmed_regexp.toString(), pubId) && getPublicationRegister() != null) {
                try {
                    imexPublication = (ImexPublication)getPublicationRegister().registerPublicationInImexCentral(publicationToEnrich);
                    if (getPublicationEnricherListener() instanceof PublicationImexEnricherListener){
                        ((PublicationImexEnricherListener)getPublicationEnricherListener()).onPublicationRegisteredInImexCentral(publicationToEnrich);
                    }
                } catch (BridgeFailedException e) {
                    throw new EnricherException("The publication "+pubId+" cannot be registered in IMEx central.");
                }

                if (imexPublication == null){
                    if (getPublicationEnricherListener() instanceof PublicationImexEnricherListener){
                        ((PublicationImexEnricherListener)getPublicationEnricherListener()).onPublicationWhichCannotBeRegistered(publicationToEnrich);
                    }
                    else if (getPublicationEnricherListener() != null){
                        getPublicationEnricherListener().onEnrichmentError(publicationToEnrich, "The publication cannot be registered in IMEx central", null);
                    }
                }
            }
            else{
                if (getPublicationEnricherListener() instanceof PublicationImexEnricherListener){
                    ((PublicationImexEnricherListener)getPublicationEnricherListener()).onPublicationWhichCannotBeRegistered(publicationToEnrich);
                }
                else if (getPublicationEnricherListener() != null){
                    getPublicationEnricherListener().onEnrichmentError(publicationToEnrich, "The publication cannot be registered in IMEx central", null);
                }
            }
            return imexPublication;
        }

        return null;
    }

    /**
     * <p>Getter for the field <code>adminGroupSynchronizer</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.imex.actions.PublicationAdminGroupSynchronizer} object.
     */
    public PublicationAdminGroupSynchronizer getAdminGroupSynchronizer() {
        return adminGroupSynchronizer;
    }

    /**
     * <p>Setter for the field <code>adminGroupSynchronizer</code>.</p>
     *
     * @param adminGroupSynchronizer a {@link psidev.psi.mi.jami.imex.actions.PublicationAdminGroupSynchronizer} object.
     */
    public void setAdminGroupSynchronizer(PublicationAdminGroupSynchronizer adminGroupSynchronizer) {
        this.adminGroupSynchronizer = adminGroupSynchronizer;
    }

    /**
     * <p>Getter for the field <code>statusSynchronizer</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.imex.actions.PublicationStatusSynchronizer} object.
     */
    public PublicationStatusSynchronizer getStatusSynchronizer() {
        return statusSynchronizer;
    }

    /**
     * <p>Setter for the field <code>statusSynchronizer</code>.</p>
     *
     * @param statusSynchronizer a {@link psidev.psi.mi.jami.imex.actions.PublicationStatusSynchronizer} object.
     */
    public void setStatusSynchronizer(PublicationStatusSynchronizer statusSynchronizer) {
        this.statusSynchronizer = statusSynchronizer;
    }

    /**
     * <p>Getter for the field <code>publicationRegister</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.imex.actions.ImexCentralPublicationRegister} object.
     */
    public ImexCentralPublicationRegister getPublicationRegister() {
        return publicationRegister;
    }

    /**
     * <p>Setter for the field <code>publicationRegister</code>.</p>
     *
     * @param publicationRegister a {@link psidev.psi.mi.jami.imex.actions.ImexCentralPublicationRegister} object.
     */
    public void setPublicationRegister(ImexCentralPublicationRegister publicationRegister) {
        this.publicationRegister = publicationRegister;
    }

    private ImexPublication fetchImexPublication(String id, String db) throws EnricherException {
        try {
            return (ImexPublication)getPublicationFetcher().fetchByIdentifier(id, db);
        } catch (BridgeFailedException e) {
            int index = 1;
            while(index < getRetryCount()){
                try {
                    return (ImexPublication)getPublicationFetcher().fetchByIdentifier(id, db);
                } catch (BridgeFailedException ee) {
                    ee.printStackTrace();
                }
                index++;
            }
            throw new EnricherException("Re-tried "+ getRetryCount() +" times to fetch the Publication but cannot connect to the fetcher.", e);
        }
    }
}



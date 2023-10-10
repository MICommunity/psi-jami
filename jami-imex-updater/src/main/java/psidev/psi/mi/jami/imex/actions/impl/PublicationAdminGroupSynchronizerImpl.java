package psidev.psi.mi.jami.imex.actions.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.imex.ImexCentralClient;
import psidev.psi.mi.jami.bridges.imex.Operation;
import psidev.psi.mi.jami.bridges.imex.extension.ImexPublication;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.comparator.cv.DefaultCvTermComparator;
import psidev.psi.mi.jami.imex.actions.PublicationAdminGroupSynchronizer;

import java.util.List;
import java.util.Set;

/**
 * This class is for synchronizing the admin group of a publication in imex central
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>28/03/12</pre>
 */
public class PublicationAdminGroupSynchronizerImpl implements PublicationAdminGroupSynchronizer{

    private static final Log log = LogFactory.getLog(PublicationAdminGroupSynchronizerImpl.class);
    private ImexCentralClient imexCentral;

    /**
     * <p>Constructor for PublicationAdminGroupSynchronizerImpl.</p>
     *
     * @param client a {@link psidev.psi.mi.jami.bridges.imex.ImexCentralClient} object.
     */
    public PublicationAdminGroupSynchronizerImpl(ImexCentralClient client){
        if (client == null){
            throw new IllegalArgumentException("The IMEx central client cannot be null");
        }
        this.imexCentral = client;
    }

    /** {@inheritDoc} */
    public void synchronizePublicationAdminGroup(Publication publication, ImexPublication imexPublication) throws BridgeFailedException {

        List<Source> sources = imexPublication.getSources();
        String pubId = null;
        String source = null;
        if (publication.getImexId() != null){
           pubId = imexPublication.getImexId();
           source = Xref.IMEX;
        }
        else{
            pubId = publication.getPubmedId() != null ? publication.getPubmedId() : publication.getDoi();
            source = publication.getPubmedId() != null ? Xref.PUBMED : Xref.DOI;
            if (pubId == null && !publication.getIdentifiers().isEmpty()){
                Xref id = publication.getXrefs().iterator().next();
                source = id.getDatabase().getShortName();
                pubId = id.getId();
            }
        }

        // add other database admin group if it exists
        Source institution = publication.getSource();
        if (source == null){
            return;
        }

        if (!containsAdminGroup(sources, institution)){
            imexCentral.updatePublicationAdminGroup( pubId, source, Operation.ADD, institution.getShortName().toUpperCase() );
            log.info("Added other publication admin group : " + institution);
        }
    }

    /**
     * <p>containsAdminGroup.</p>
     *
     * @param adminGroupList a {@link java.util.List} object.
     * @param group a {@link psidev.psi.mi.jami.model.Source} object.
     * @return a boolean.
     */
    protected boolean containsAdminGroup(List<Source> adminGroupList, Source group){

        if (!adminGroupList.isEmpty()){
            for (Source source : adminGroupList){
                if (DefaultCvTermComparator.areEquals(source, group)){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * <p>checks any of the sources is a imex partner.</p>
     *
     * @param adminGroupList a {@link java.util.List} object.
     * @param imexPartners a set of {@link java.lang.String} objects.
     * @return a boolean.
     */
    protected Source getImexPublicationOwnerGroup(List<Source> adminGroupList, Set<String> imexPartners){

        Source imexPublicationSource=null;
        if (!adminGroupList.isEmpty()){
            for (Source source : adminGroupList){
                if(imexPartners.contains(source.getShortName().toUpperCase())){
                    return source;
                }
            }
        }

        return null;
    }

    /**
     * <p>getImexCentralClient.</p>
     *
     * @return a {@link psidev.psi.mi.jami.bridges.imex.ImexCentralClient} object.
     */
    public ImexCentralClient getImexCentralClient() {
        return imexCentral;
    }

}

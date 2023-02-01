package psidev.psi.mi.jami.bridges.imex;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.PublicationFetcher;
import psidev.psi.mi.jami.model.Publication;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * IMEx Central publication fetcher.
 *

 */
public interface ImexCentralClient extends PublicationFetcher {

    /** Constant <code>IC_TEST="https://imexcentral.org/icentraltest/ws"{trunked}</code> */
    public static final String IC_TEST = "https://imexcentral.org/icentraltest/ws-v20";
    /** Constant <code>IC_BETA="https://imexcentral.org/icentralbeta/ws"{trunked}</code> */
    public static final String IC_BETA = "https://imexcentral.org/icentralbeta/ws-v20";
    /** Constant <code>IC_PROD="https://imexcentral.org/icentral/ws-v20"</code> */
    public static final String IC_PROD = "https://imexcentral.org/icentral/ws-v20";

    /** Constant <code>pubmed_regexp</code> */
    public static Pattern pubmed_regexp = Pattern.compile("\\d+");

    /** Constant <code>USER_NOT_AUTHORIZED=2</code> */
    public final static int USER_NOT_AUTHORIZED = 2;
    /** Constant <code>OPERATION_NOT_VALID=3</code> */
    public final static int OPERATION_NOT_VALID = 3;
    /** Constant <code>IDENTIFIER_MISSING=4</code> */
    public final static int IDENTIFIER_MISSING = 4;
    /** Constant <code>IDENTIFIER_UNKNOWN=5</code> */
    public final static int IDENTIFIER_UNKNOWN = 5;
    /** Constant <code>NO_RECORD=6</code> */
    public final static int NO_RECORD = 6;
    /** Constant <code>NO_RECORD_CREATED=7</code> */
    public final static int NO_RECORD_CREATED = 7;
    /** Constant <code>STATUS_UNKNOWN=8</code> */
    public final static int STATUS_UNKNOWN = 8;
    /** Constant <code>NO_IMEX_ID=9</code> */
    public final static int NO_IMEX_ID = 9;
    /** Constant <code>UNKNOWN_USER=10</code> */
    public final static int UNKNOWN_USER = 10;
    /** Constant <code>UNKNOWN_GROUP=11</code> */
    public final static int UNKNOWN_GROUP = 11;
    /** Constant <code>OPERATION_NOT_SUPPORTED=98</code> */
    public final static int OPERATION_NOT_SUPPORTED = 98;
    /** Constant <code>INTERNAL_SERVER_ERROR=99</code> */
    public final static int INTERNAL_SERVER_ERROR = 99;
    /**
     * <p>getEndpoint.</p>
     *
     * @return the endpoint of the IMEx central webservice
     */
    public String getEndpoint();

    /**
     * Collect a list of publications associated with a specific owner. Selects first and last results
     *
     * @param owner : login of owner
     * @param first : first result
     * @param max : last result
     * @return list of publications associated with this owner, empty list if no results
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public Collection<Publication> fetchPublicationsByOwner(String owner, int first, int max) throws BridgeFailedException;

    /**
     * Collect a list of publications having a specific status. Selects first and last results
     *
     * @param status : the status of publication in IMEx central
     * @param first : first result
     * @param max : last result
     * @return list of publications associated with this status, empty list if no results
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public Collection<Publication> fetchPublicationsByStatus(String status, int first, int max) throws BridgeFailedException;

    /**
     * Update the status of a publication associated with a valid pubmed identifier already registered in IMEx central
     *
     * @param identifier : a valid pubmed identifier
     *  @param source: the source pubmed, doi or imex
     * @param status : new status
     * @return the updated record in IMEx central
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if no record found in IMEx central, no pubmed identifier or invalid status
     */
    public Publication updatePublicationStatus(String identifier, String source, PublicationStatus status) throws BridgeFailedException;

    /**
     * Update the publication admin group given a valid pubmed identifier and a valid operator. The publication must be registered in IMEx central
     *
     * @param identifier : valid pubmed id
     * @param source: the source pubmed, doi or imex
     * @param operation : DROP or ADD
     * @param group : the name of the admin group
     * @return the updated record in IMEx central
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if no record found in IMEX central, no pubmed identifiier or unknown group
     */
    public Publication updatePublicationAdminGroup(String identifier, String source, Operation operation, String group) throws BridgeFailedException;

    /**
     * Update the publication admin user given a valid pubmed identifier and a valid operator. The publication must be registered in IMEx central
     *
     * @param identifier a {@link java.lang.String} object.
     * @param source pubmed, doi or imex
     * @param operation a {@link psidev.psi.mi.jami.bridges.imex.Operation} object.
     * @param user a {@link java.lang.String} object.
     * @return the updated record in IMEx central
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if no record found in IMEX central, no pubmed identifiier or unknown user
     */
    public Publication updatePublicationAdminUser(String identifier, String source, Operation operation, String user) throws BridgeFailedException;

    /**
     * Update publication pubmed identifier, DOI number or internal identifier of an existing record in IMEx central. The new publication identifier should not be already registered in IMEx central
     *
     * @param oldIdentifier : can be pubmed, imex, doi or internal identifier
     * @param oldSource: the source of old identifier
     * @param newIdentifier : can be pubmed, doi or internal identifier
     * @param source: the source pubmed, doi or imex
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if no record found in IMEX central, identifier not recognized or new identifier is associated with another publication in IMEx central
     */
    public Publication updatePublicationIdentifier(String oldIdentifier, String oldSource, String newIdentifier, String source) throws BridgeFailedException;

    /**
     * Create a new publication record in IMEx central (not implemented yet in production but can be used in test with the MockImexCentralClient)
     *
     * @param publication : the publication to create in IMEx central
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public void createPublication( Publication publication ) throws BridgeFailedException;

    /**
     * Creates a publication given a valid pubmed id.
     *
     * @param identifier : valid pubmed id
     * @return the newly created record
     * @param source a {@link java.lang.String} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if identifier is not valid pubmed or if the pubmed id is already registered in IMEx central
     */
    public Publication createPublicationById(String identifier, String source) throws BridgeFailedException;

    /**
     * Create a new IMEx id if requested for a given pubmed id
     *
     * @param identifier: a valid pubmed identifier
     * @param source: a valid source (imex, pubmed or doi)
     * @param assign : true if we want to assign a new IMEx id, false otherwise
     * @return the updated record in IMEx central
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if not a valid pubmed id, not existing record and cannot generate a new IMEx id
     */
    public Publication fetchPublicationImexAccession(String identifier, String source, boolean assign) throws BridgeFailedException;
}

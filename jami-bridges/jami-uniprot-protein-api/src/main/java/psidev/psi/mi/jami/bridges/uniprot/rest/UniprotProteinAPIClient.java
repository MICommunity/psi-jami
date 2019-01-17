package psidev.psi.mi.jami.bridges.uniprot.rest;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import psidev.psi.mi.jami.bridges.uniprot.rest.response.model.DbReferenceType;
import psidev.psi.mi.jami.bridges.uniprot.rest.response.model.Entry;
import psidev.psi.mi.jami.bridges.uniprot.rest.response.model.Uniparc;
import uk.ac.ebi.kraken.interfaces.uniparc.UniParcEntry;
import uk.ac.ebi.kraken.interfaces.uniprot.UniProtEntry;
import uk.ac.ebi.uniprot.dataservice.client.Client;
import uk.ac.ebi.uniprot.dataservice.client.ServiceFactory;
import uk.ac.ebi.uniprot.dataservice.client.exception.ServiceException;
import uk.ac.ebi.uniprot.dataservice.client.uniparc.UniParcQueryBuilder;
import uk.ac.ebi.uniprot.dataservice.client.uniparc.UniParcService;
import uk.ac.ebi.uniprot.dataservice.client.uniprot.UniProtQueryBuilder;
import uk.ac.ebi.uniprot.dataservice.client.uniprot.UniProtService;
import uk.ac.ebi.uniprot.dataservice.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static psidev.psi.mi.jami.bridges.uniprot.rest.SearchDatabase.*;

/**
 * Client for the Uniprot REST Api
 */
public class UniprotProteinAPIClient {

    /**
     * Sets up a logger for that class.
     */
    public static final Logger log = Logger.getLogger(UniprotProteinAPIClient.class.getName());

    private RestTemplate restTemplate;

    public UniprotProteinAPIClient() {
        this.restTemplate = new RestTemplate();
    }


    /**
     * Finds the list of swissProtIds for a provided ID and taxonId
     *
     * @param accession the accession to look for
     * @param taxonId   : the organism of the protein
     * @return the swissprotIds if found, empty list otherwise
     * @throws UniprotProteinAPIClientException : an exception if the given accession is null
     */
    //TODO Review if SearchDatabase.SWISSPROT_VARSPLIC is changing the value of the query in PICR to adapt the query in Uniprot REST API
    public List<String> getSwissprotIdsForAccession(String accession, String taxonId) throws UniprotProteinAPIClientException {
        return getIdsForAccession(accession, taxonId, SWISSPROT, SWISSPROT_VARSPLIC);
    }

    /**
     * Finds the list of termblIds for a provided ID and taxonId
     *
     * @param accession the accession to look for
     * @param taxonId   : the organism of the protein
     * @return the tremblId if found, empty list otherwise
     * @throws UniprotProteinAPIClientException : an exception if the given accession is null
     */
    //TODO Review if SearchDatabase.TREMBL_VARSPLIC, is changing the value of the query in PICR to adapt the query in Uniprot REST API
    public List<String> getTremblIdsForAccession(String accession, String taxonId) throws UniprotProteinAPIClientException {
        return getIdsForAccession(accession, taxonId, TREMBL);
    }

    /**
     * Gets the list of uniparcId matching this accession number
     *
     * @param accession
     * @param taxonId
     * @return the list of uniparc Id or empty list if the accession doesn't match any Uniparc sequence
     * @throws UniprotProteinAPIClientException : an exception if the given accession is null
     */
    //TODO Review if SearchDatabase.TREMBL_VARSPLIC,  SearchDatabase.SWISSPROT_VARSPLIC are changing the value of the query in PICR to adaptt the query in Uniprot REST API
    public List<Entry> getUniparcEntries(String accession, String taxonId) throws UniprotProteinAPIClientException {
        return getUPEntriesForAccession(accession, taxonId, SWISSPROT, SWISSPROT_VARSPLIC, TREMBL);
    }

    /**
     * get the list of cross references accessions for a provided Id and taxonId from a list of databases
     *
     * @param accession the accession to look for
     * @param taxonId   : the organism of the protein
     * @param databases : the databases to query
     * @return the cross reference IDs if found, empty list otherwise
     * @throws UniprotProteinAPIClientException : an exception if the given accession is null
     */
    private List<String> getIdsForAccession(String accession, String taxonId, SearchDatabase... databases) throws UniprotProteinAPIClientException {
        List<Entry> upEntries = getUPEntriesForAccession(accession, taxonId, databases);
        ArrayList<String> idList = new ArrayList<String>();
        for (Entry entry : upEntries) {
            List<DbReferenceType> listOfReferences = entry.getDbReference();
            if (!listOfReferences.isEmpty()) {
                for (DbReferenceType c : listOfReferences) {
                    String ac = c.getId();
                    if (ac != null) {
                        idList.add(ac);
                    }
                }
            }
        }
        return idList;
    }

    /**
     * Converts a list of SearchDatabase into String
     *
     * @param databases : the databases to query
     * @return the list of databases
     */
    //TODO Review if it can work with a List instead of an Array. Apparently the output of both is different
    private String databaseEnumToString(SearchDatabase... databases) {
        List<String> databaseNames = new ArrayList<String>(databases.length);

        for (SearchDatabase database : databases) {
            databaseNames.add(database.getDbName());
        }

        return String.join(",", databaseNames);
    }

    /**
     * Finds the list of UPEntries for a provided ID and organism from the provided list of databases
     *
     * @param accession the accession to look for
     * @param taxonId   the organism of the protein
     * @param databases the databases to query
     * @return the uniprot ID if found, null otherwise
     * @throws UniprotProteinAPIClientException : an exception if the given accession is null
     */
    public List<Entry> getUPEntriesForAccession(String accession, String taxonId, SearchDatabase... databases) throws UniprotProteinAPIClientException {
        if (accession == null) {
            throw new UniprotProteinAPIClientException("The identifier must not be null.");
        }

        String query = "https://www.ebi.ac.uk/proteins/api/uniparc/dbreference/{accession}?rfDdtype={searchDatabases}&rfActive=true";

        if (databases == null) {
            databases = values();
        }

        if (taxonId != null) {
            query += "&rfTaxId=" + taxonId;
        }

        List<Entry> entries = Collections.EMPTY_LIST;

        try {
            //Example end-point
            //curl -X GET --header 'Accept:application/json'
            //'https://www.ebi.ac.uk/proteins/api/uniparc/dbreference/NP_417804?offset=0&size=100&rfDdtype=UniProtKB%2FTrEMBL&rfActive=true'

            Uniparc response = restTemplate.getForObject(query, Uniparc.class, accession, databaseEnumToString(databases));
            entries = response.getEntry();

        } catch (HttpClientErrorException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                log.log(Level.SEVERE, "Uniprot REST API could not found any best guess");
            }
        } catch (RestClientException e) {
            log.log(Level.SEVERE, "Uniprot REST API could not work properly", e);
            throw new UniprotProteinAPIClientException("Uniprot REST API could not work properly.");
        }


        return entries;
    }

    /**
     * get the cross references ids for a provided sequence and taxonId from a list of databases
     *
     * @param sequence  the sequence to look for
     * @param taxonId   : the organism of the protein
     * @param databases : the databases to query
     * @return the list of cross reference IDs if found, empty list otherwise
     * @throws UniprotProteinAPIClientException : an exception if the given sequence is null
     */
    private List<String> getIdsForSequence(String sequence, String taxonId, SearchDatabase... databases) throws UniprotProteinAPIClientException {
        Entry upEntry = getUPEntriesForSequence(sequence, taxonId, databases);
        ArrayList<String> idList = new ArrayList<String>();

        if (upEntry != null) {
            List<DbReferenceType> listOfReferences = upEntry.getDbReference();
            if (!listOfReferences.isEmpty()) {
                for (DbReferenceType c : listOfReferences) {
                    String ac = c.getId();
                    if (ac != null) {
                        idList.add(ac);
                    }
                }
            }
        }
        return idList;
    }

    /**
     * Finds the list of swissProtIds for a provided sequence and taxonId
     *
     * @param sequence the sequence to look for
     * @param taxonId  : the organism of the protein
     * @return the swissprotIds if found, empty list otherwise
     * @throws UniprotProteinAPIClientException : an exception if the given sequence is null
     */
    //TODO Review if SearchDatabase.SWISSPROT_VARSPLIC is changing the value of the query in PICR to adapt the query in Uniprot REST API
    public List<String> getSwissprotIdsForSequence(String sequence, String taxonId) throws UniprotProteinAPIClientException {
        return getIdsForSequence(sequence, taxonId, SWISSPROT, SWISSPROT_VARSPLIC);
    }

    /**
     * Finds the list of termblIds for a provided sequence and taxonId
     *
     * @param sequence the sequence to look for
     * @param taxonId  : the organism of the protein
     * @return the tremblId if found, empty list otherwise
     * @throws UniprotProteinAPIClientException : an exception if the given sequence is null
     */
    //TODO Review if SearchDatabase.TREMBL_VARSPLIC, is changing the value of the query in PICR to adapt the query in Uniprot REST API
    public List<String> getTremblIdsForSequence(String sequence, String taxonId) throws UniprotProteinAPIClientException {
        return getIdsForSequence(sequence, taxonId, TREMBL);
    }

    /**
     * Gets the uniparcId matching this sequence
     *
     * @param sequence
     * @param taxonId
     * @return the uniparc Id or null if the sequence doesn't match any Uniparc sequence
     */
    //TODO Review if SearchDatabase.TREMBL_VARSPLIC,  SearchDatabase.SWISSPROT_VARSPLIC are changing the value of the query in PICR to adaptt the query in Uniprot REST API
    public String getUniparcIdFromSequence(String sequence, String taxonId) throws UniprotProteinAPIClientException {
        Entry upEntry = getUPEntriesForSequence(sequence, taxonId, SWISSPROT, SWISSPROT_VARSPLIC, TREMBL);

        if (upEntry == null) {
            return null;
        }

        return upEntry.getAccession();
    }

    /**
     * Get the Entry which matches the sequence and taxonId in the given databases
     *
     * @param sequence  : sequence of the protein to retrieve
     * @param taxonId   : organism of the sequence
     * @param databases : the databases to look into
     * @return an Entry instance matching the sequence, taxonId in the specific databases
     * @throws UniprotProteinAPIClientException : an exception if the given sequence is null
     */
    public Entry getUPEntriesForSequence(String sequence, String taxonId, SearchDatabase... databases) throws UniprotProteinAPIClientException {
        if (sequence == null) {
            throw new UniprotProteinAPIClientException("The sequence must not be null.");
        }

        String query = "https://www.ebi.ac.uk/proteins/api/uniparc/sequence?rfDdtype={searchDatabases}&rfActive=true";

        if (databases == null) {
            databases = values();
        }

        if (taxonId != null) {
            query += "&rfTaxId=" + taxonId;
        }

        Entry entry = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

            HttpEntity<String> request = new HttpEntity<String>(sequence, headers);
            //Example end-point
            //curl 'https://www.ebi.ac.uk/proteins/api/uniparc/sequence?rfDdtype=UniProtKB%2FSwiss-Prot&rfActive=true'
            // -H 'Content-type:text/plain' -H 'Accept:application/json' -X POST
            // -d 'MRFAIVVTGPAYGTQQASSAFQFAQALIADGHELSSVFFYREGVYNANQLTSPASDEFDLVRAWQQLNAQHGVALNICVAAALRRGVVDETEAGRLGLASSNLQQGFTLSGLGALAEASLTCDRVVQF'

            entry = restTemplate.postForObject(query, request, Entry.class, databaseEnumToString(databases));

        } catch (HttpClientErrorException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                log.log(Level.SEVERE, "Uniprot REST API could not found any best guess");
            }
        } catch (RestClientException e) {
            log.log(Level.SEVERE, "Uniprot REST API could not work properly", e);
            throw new UniprotProteinAPIClientException("Uniprot REST API could not work properly.");
        }

        return entry;
    }

    /**
     * Get the UniprotEntry with its accession number
     *
     * @param accession : the Uniprot identifier of the protein we want to retrieve
     * @return A list of UniprotEntry instances for this identifier
     */
    public List<UniProtEntry> getUniprotEntryForAccession(String accession) {
        ServiceFactory serviceFactoryInstance = Client.getServiceFactoryInstance();
        UniProtService uniProtQueryService = serviceFactoryInstance.getUniProtQueryService();

        Iterator<UniProtEntry> iterator = null;

        uniProtQueryService.start();

        List<UniProtEntry> uniProtEntries = new ArrayList<UniProtEntry>();
        Query query = UniProtQueryBuilder.accession(accession);

        try {
            iterator = uniProtQueryService.getEntries(query);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (iterator != null) {
            while (iterator.hasNext()) {
                UniProtEntry uniProtEntry = iterator.next();
                uniProtEntries.add(uniProtEntry);
            }
        }
        uniProtQueryService.stop();
        return uniProtEntries;
    }

    /**
     * Get the UniparcEntry with an accession number
     *
     * @param accession : the identifier of the protein we want to retrieve
     * @return a list of UniparcEntry instances for this accession
     */
    public List<UniParcEntry> getUniparcEntryForAccession(String accession) {
        ServiceFactory serviceFactoryInstance = Client.getServiceFactoryInstance();
        UniParcService uniParcQueryService = serviceFactoryInstance.getUniParcQueryService();

        Iterator<UniParcEntry> iterator = null;

        uniParcQueryService.start();

        List<UniParcEntry> uniParcEntries = new ArrayList<UniParcEntry>();
        Query query = UniParcQueryBuilder.accession(accession);

        try {
            iterator = uniParcQueryService.getEntries(query);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (iterator != null) {
            while (iterator.hasNext()) {
                UniParcEntry uniParcEntry = iterator.next();
                uniParcEntries.add(uniParcEntry);
            }
        }
        uniParcQueryService.stop();
        return uniParcEntries;
    }

    /**
     * Get an Unique uniprot Id for this accession
     *
     * @param accession : the accession to look at
     * @param taxonId   : the organism
     * @return an Unique uniprot Id
     * @throws UniprotProteinAPIClientException
     */
    public String[] getUniprotBestGuessFor(String accession, String taxonId) throws UniprotProteinAPIClientException {

        if (accession == null) {
            throw new UniprotProteinAPIClientException("The identifier must not be null.");
        }

        String query = "https://www.ebi.ac.uk/proteins/api/uniparc/bestguess?dbid={accession}";

        //curl -X GET --header 'Accept:application/json' 'https://www.ebi.ac.uk/proteins/api/uniparc/bestguess?accession=P45532&taxid=83333'
        //https://www.ebi.ac.uk/proteins/api/uniparc/bestguess?accession=P45532&taxid=83333

        if (taxonId != null) {
            query += "&taxid=" + taxonId;
        }

        String[] result = null;

        try {

           Entry entry = restTemplate.getForObject(query, Entry.class, accession);

            if (entry != null) {
                List<DbReferenceType> dbReferences = entry.getDbReference();

                switch (dbReferences.size()) {
                    case 1:
                        result = new String[2];
                        result[0] = toEnumString(dbReferences.get(0).getType());
                        result[1] = dbReferences.get(0).getId();
                        break;
                    case 2:
                        //We need to check if the best guest is returning the isoform and the swiss-prot at the same time.
                        //In this case there is no error and we need to extract the isoform.
                        String idOne = dbReferences.get(0).getId();
                        String idTwo = dbReferences.get(1).getId();
                        String dbOne = dbReferences.get(0).getType();
                        String dbTwo = dbReferences.get(1).getType();

                        if (idOne.length() > idTwo.length()) { //idOne could be the isoform
                            if (idOne.contains(idTwo)) { //If contains idTwo, is the same entry. The result is correct
                                result = new String[2];
                                result[0] = toEnumString(dbOne);
                                result[1] = idOne;
                                break;
                            }
                        } else {
                            if (idTwo.contains(idOne)) { //If contains idOne, is the same entry. The result is correct
                                result = new String[2];
                                result[0] = toEnumString(dbTwo);
                                result[1] = idTwo;
                                break;
                            }
                        }
                    default:
                        throw new UniprotProteinAPIClientException("Found more than one best guess");
                }
            }

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                log.log(Level.SEVERE, "Uniprot REST API could not found any best guess");
            }
        } catch (RestClientException e) {
            log.log(Level.SEVERE, "Uniprot REST API could not work properly", e);
            throw new UniprotProteinAPIClientException("Uniprot REST API could not work properly.");
        }

        return result;
    }
}



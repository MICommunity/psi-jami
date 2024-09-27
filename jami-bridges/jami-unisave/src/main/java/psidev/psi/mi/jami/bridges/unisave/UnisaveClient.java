package psidev.psi.mi.jami.bridges.unisave;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.SequenceVersionFetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Client to query unisave and collect sequence versions or sequence from a particular version
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/09/13</pre>
 */
public class UnisaveClient implements SequenceVersionFetcher{

    private static final Log log = LogFactory.getLog(UnisaveClient.class);

    private static final String UNISAVE_URL_REST_JSON = "https://rest.uniprot.org/unisave/";
    private HttpClient httpClient;
    private int connectionTimeOut = 20;
    private int socketTimeout = 20;

    /**
     * <p>Constructor for UnisaveClient.</p>
     */
    public UnisaveClient() {

    }

    private Object getJsonDataFromWebService(String query) throws BridgeFailedException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.getDataFromWebService(query)));
        Object obj = JSONValue.parse(reader);
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private FastaSequence getFastaDataFromWebService(String query) throws BridgeFailedException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.getDataFromWebService(query)));
        try {
            return readFastaSequence(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private FastaSequence readFastaSequence(BufferedReader fastaReader) throws IOException {

        // read identifier
        String lastLine = fastaReader.readLine();

        if (lastLine != null && !lastLine.startsWith(">")){
            while (lastLine != null && !lastLine.startsWith(">")){
                lastLine = fastaReader.readLine();
            }
        }

        if (lastLine == null){
            return null;
        }

        String identifier = lastLine.substring(Math.min(lastLine.length()-1,lastLine.indexOf(">")+1));
        StringBuilder sequenceBuffer = new StringBuilder();

        // read sequence
        lastLine = fastaReader.readLine();
        while (lastLine != null && !lastLine.startsWith(">")){

            sequenceBuffer.append(lastLine.replaceAll("\n",""));
            lastLine = fastaReader.readLine();
        }

        if (sequenceBuffer.length() == 0){
            return null;
        }

        return new FastaSequence(identifier, sequenceBuffer.toString());
    }

    private InputStream getDataFromWebService(String query) throws BridgeFailedException{
        HttpGet request = new HttpGet(query);
        request.addHeader("accept", "application/json");
        try {
            HttpResponse response = getHttpClient().execute(request);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new BridgeFailedException("Failed to query unisave : HTTP error code : " + response.getStatusLine().getStatusCode());
            }
            return response.getEntity().getContent();
        } catch (IOException e) {
            throw new BridgeFailedException("Failed to query unisave", e);
        }
    }

    private String buildJsonQuery(String id) {
        return UNISAVE_URL_REST_JSON + id + ".json";
    }

    private String buildFastaQuery(String id, String version) {
        return UNISAVE_URL_REST_JSON + id + ".fasta?versions=" + version;
    }

    private FastaSequence getFastaSequenceForSequenceVersion(String identifier, int sequence_version) throws BridgeFailedException {
        JSONObject jo = (JSONObject) getJsonDataFromWebService(buildJsonQuery(identifier));
        JSONArray results = (JSONArray) jo.get("results");
        for (Object result : results) {
            jo = (JSONObject) result;
            if (sequence_version == Integer.parseInt(String.valueOf(jo.get("sequenceVersion")))) {
                String entryVersion = String.valueOf(jo.get("entryVersion"));
                return getFastaDataFromWebService(buildFastaQuery(identifier, entryVersion));
            }
        }
        return null;
    }

    /**
     * <p>getSequenceFor.</p>
     *
     * @param identifier a {@link java.lang.String} object.
     * @param sequence_version a int.
     * @return a {@link java.lang.String} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public String getSequenceFor(String identifier, int sequence_version) throws BridgeFailedException{
        FastaSequence fastaSequence = getFastaSequenceForSequenceVersion(identifier, sequence_version);
        if (fastaSequence != null) {
            return fastaSequence.getSequence();
        }
        return null;
    }

    /**
     * Searches for all entry version given its identifier.
     *
     * @param identifier  the identifier of the entry we are interested in.
     * @return list of all versions of the given entry from the most recent to the oldest.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if the identifier cannot be found in UniSave.
     */
    public List<Integer> getVersions( String identifier ) throws BridgeFailedException {
        JSONObject jo = (JSONObject) getJsonDataFromWebService(buildJsonQuery(identifier));
        JSONArray results = (JSONArray) jo.get("results");
        List<Integer> list = new ArrayList<>();
        for (Object result : results) {
            jo = (JSONObject) result;
            list.add(Integer.valueOf(String.valueOf(jo.get("entryVersion"))));
        }

        if( list.isEmpty() ) {
            throw new BridgeFailedException( "Failed to find any version for identifier " + identifier );
        }
        return list;
    }

    /**
     * <p>getLastSequenceAtTheDate.</p>
     *
     * @param identifier a {@link java.lang.String} object.
     * @param date a {@link java.util.Date} object.
     * @return a {@link java.lang.String} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public String getLastSequenceAtTheDate(String identifier, Date date) throws BridgeFailedException {

        if (date == null){
            throw new IllegalArgumentException("The date cannot be null.");
        }

        JSONObject jo = (JSONObject) getJsonDataFromWebService(buildJsonQuery(identifier));
        JSONArray results = (JSONArray) jo.get("results");
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date auxDate;
        for (Object result : results) {
            jo = (JSONObject) result;
            try {
                auxDate = formatter.parse((String) jo.get("firstReleaseDate"));
                if (auxDate.before(date)) {
                    String entryVersion = String.valueOf(jo.get("entryVersion"));
                    FastaSequence fastaSequence = getFastaDataFromWebService(buildFastaQuery(identifier, entryVersion));
                    if (fastaSequence != null) {
                        return fastaSequence.getSequence();
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Get the map of sequences (and their sequence version in uniprot) existing in unisave before this date
     *
     * @param identifier a {@link java.lang.String} object.
     * @param date a {@link java.util.Date} object.
     * @return a {@link java.util.Map} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public Map<Integer, String> getAllSequencesBeforeDate(String identifier, Date date) throws BridgeFailedException {

        if (date == null){
            throw new IllegalArgumentException("The date cannot be null.");
        }

        Map<Integer, String> oldSequences = new HashMap<>();
        JSONObject jo = (JSONObject) getJsonDataFromWebService(buildJsonQuery(identifier));
        JSONArray results = (JSONArray) jo.get("results");
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date auxDate;
        for (Object result : results) {
            jo = (JSONObject) result;
            try {
                auxDate = formatter.parse((String) jo.get("firstReleaseDate"));
                Integer sequenceVersion = Integer.valueOf(String.valueOf(jo.get("sequenceVersion")));
                if (auxDate.before(date) && !oldSequences.containsKey(sequenceVersion)) {
                    String entryVersion = String.valueOf(jo.get("entryVersion"));
                    FastaSequence fastaSequence = getFastaDataFromWebService(buildFastaQuery(identifier, entryVersion));
                    if (fastaSequence != null) {
                        oldSequences.put(sequenceVersion, fastaSequence.getSequence());
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return oldSequences;
    }

    /**
     * Retrieve a fasta sequence corresponding to a given EntryVersion.
     *
     * @param version the version for which we want the sequence
     * @return a fasta sequence.
     * @param identifier a {@link java.lang.String} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if the version given doesn't have an entryId that can be found in UniSave.
     */
    public FastaSequence getFastaSequence( String identifier, int version ) throws BridgeFailedException {
        return getFastaSequenceForSequenceVersion(identifier, version);
    }


    /**
     * Returns the sequence version of a sequence for a certain uniprot ac.
     * Returns -1 if the sequence cannot be found for this uniprot ac
     *
     * @param identifier a {@link java.lang.String} object.
     * @param sequence a {@link java.lang.String} object.
     * @return a int.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public int getSequenceVersion(String identifier, String sequence) throws BridgeFailedException{
        JSONObject jo = (JSONObject) getJsonDataFromWebService(buildJsonQuery(identifier));
        JSONArray results = (JSONArray) jo.get("results");

        if ( log.isDebugEnabled() ) {
            log.debug( "Collecting version(s) for entry by ac: " + identifier );
            log.debug( "Found " + results.size() + " version(s)" );
        }
        Set<Integer> versionsChecked = new HashSet<>();
        for (Object result : results) {
            jo = (JSONObject) result;
            Integer sequenceVersion = Integer.valueOf(String.valueOf(jo.get("sequenceVersion")));
            if (!versionsChecked.contains(sequenceVersion)) {
                String entryVersion = String.valueOf(jo.get("entryVersion"));
                FastaSequence fastaSequence = getFastaDataFromWebService(buildFastaQuery(identifier, entryVersion));
                if (fastaSequence != null && sequence.equalsIgnoreCase(fastaSequence.getSequence())) {
                    return sequenceVersion;
                }
                versionsChecked.add(sequenceVersion);
            }
        }
        return -1;
    }

    /**
     * Collects all available sequence update fro mUniSave.
     *
     * @param identifier  identifier of the uniprot entry
     * @param sequence    sequence for which we want the subsequent updates
     * @return a non null ordered list. If the given sequence is found in the entry history, this sequence at least is
     *         returned. If there were say, 2 updates since that protein sequence, the list would contain 3 versions.
     *         If we fail to find a match for the given sequence in UniSave, the list would contain all existing
     *         sequence update available.
     *         The list of ordered from the oldest to the most recent sequence.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if the identifier cannot be found in UniSave.
     */
    public List<SequenceVersion> getAvailableSequenceUpdate( String identifier, String sequence ) throws BridgeFailedException {

        LinkedList<SequenceVersion> sequenceUpdates = new LinkedList<>();

        // 1. get all versions ordered from the most recent to the oldest
        if ( log.isDebugEnabled() ) {
            log.debug( "Collecting version(s) for entry by  ac: " + identifier );
        }

        JSONObject jo = (JSONObject) getJsonDataFromWebService(buildJsonQuery(identifier));
        JSONArray results = (JSONArray) jo.get("results");
        int parameterSequenceVersion = getSequenceVersion(identifier, sequence);
        int currentSequenceVersion = -1;
        for (Object result : results) {
            jo = (JSONObject) result;
            if (parameterSequenceVersion < Integer.parseInt(String.valueOf(jo.get("sequenceVersion")))) {
                if (currentSequenceVersion != Integer.parseInt(String.valueOf(jo.get("sequenceVersion")))) {
                    //New version of the sequence
                    currentSequenceVersion = Integer.parseInt(String.valueOf(jo.get("sequenceVersion")));
                    String entryVersion = String.valueOf(jo.get("entryVersion"));
                    FastaSequence fastaSequence = getFastaDataFromWebService(buildFastaQuery(identifier, entryVersion));
                    if (fastaSequence != null) {
                        sequenceUpdates.add(new SequenceVersion(fastaSequence, currentSequenceVersion));
                    }
                }
            }
        }
        return sequenceUpdates;
    }

    /** {@inheritDoc} */
    public String fetchSequenceFromVersion(String id, int version) throws BridgeFailedException{
        return getSequenceFor(id, version);
    }

    /** {@inheritDoc} */
    public int fetchVersionFromSequence(String id, String sequence) throws BridgeFailedException{
        return getSequenceVersion(id, sequence);
    }

    /**
     * <p>Getter for the field <code>socketTimeout</code>.</p>
     *
     * @return a int.
     */
    public int getSocketTimeout() {
        return socketTimeout;
    }

    /**
     * <p>Setter for the field <code>socketTimeout</code>.</p>
     *
     * @param socketTimeout a int.
     */
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    /**
     * <p>Getter for the field <code>connectionTimeOut</code>.</p>
     *
     * @return a int.
     */
    public int getConnectionTimeOut() {
        return connectionTimeOut;
    }

    /**
     * <p>Setter for the field <code>connectionTimeOut</code>.</p>
     *
     * @param connectionTimeOut a int.
     */
    public void setConnectionTimeOut(int connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    private HttpClient getHttpClient(){
        if (this.httpClient == null){
            RequestConfig.Builder requestBuilder = RequestConfig.custom()
                    .setConnectTimeout(this.connectionTimeOut * 1000)
                    .setConnectionRequestTimeout(this.socketTimeout * 1000);

            this.httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestBuilder.build())
                    .disableAutomaticRetries()
                    .build();
        }
        return this.httpClient;
    }
}

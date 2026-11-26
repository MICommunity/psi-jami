package psidev.psi.mi.jami.bridges.uniprot;

import org.apache.avro.file.DataFileStream;
import org.apache.avro.specific.SpecificDatumReader;
import uk.ac.ebi.kraken.interfaces.uniprot.UniProtEntry;
import uk.ac.ebi.uniprot.dataservice.client.Client;
import uk.ac.ebi.uniprot.dataservice.client.QueryResult;
import uk.ac.ebi.uniprot.dataservice.client.Request;
import uk.ac.ebi.uniprot.dataservice.client.RequestSerializer;
import uk.ac.ebi.uniprot.dataservice.client.Response;
import uk.ac.ebi.uniprot.dataservice.client.exception.ServiceException;
import uk.ac.ebi.uniprot.dataservice.client.impl.BasicService;
import uk.ac.ebi.uniprot.dataservice.client.impl.DefaultQueryResultImpl;
import uk.ac.ebi.uniprot.dataservice.client.impl.DefaultRequestImpl;
import uk.ac.ebi.uniprot.dataservice.client.impl.EmptyResponse;
import uk.ac.ebi.uniprot.dataservice.client.uniprot.UniProtQueryBuilder;
import uk.ac.ebi.uniprot.dataservice.client.uniprot.impl.DefaultUniProtRequest;
import uk.ac.ebi.uniprot.dataservice.query.Query;
import uk.ac.ebi.uniprot.dataservice.serializer.avro.EntryConverter;
import uk.ac.ebi.uniprot.services.data.serializer.model.entry.EntryObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

final class UniprotQueryService implements BasicService<Response<EntryObject>> {
    private final HttpClient client;
    private final URI endpoint;
    private final Duration requestTimeout;
    private final EntryConverter converter = new EntryConverter();
    private boolean started;

    UniprotQueryService() {
        Properties config = new Properties();
        try (InputStream input = Client.class.getResourceAsStream("/config.properties")) {
            if (input != null) {
                config.load(input);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load UniProt client configuration", e);
        }
        endpoint = URI.create(config.getProperty("baseServiceURL", "https://www.ebi.ac.uk/proteins/api")
                + "/proteins/japi");
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofMillis(Long.parseLong(config.getProperty("http.connectionTimeOut", "10000"))))
                .build();
        requestTimeout = Duration.ofMillis(Long.parseLong(config.getProperty("http.socketTimeOut", "20000")));
    }

    QueryResult<UniProtEntry> getEntries(Query query) throws ServiceException {
        DefaultUniProtRequest request = createRequest(query, false);
        request.setResultCardinality(100);
        return new DefaultQueryResultImpl<>(this, request, execute(request), converter::fromAvro);
    }

    UniProtEntry getEntry(String identifier) throws ServiceException {
        Response<EntryObject> response = execute(createRequest(UniProtQueryBuilder.accession(identifier), true));
        return response.getResults().isEmpty() ? null : converter.fromAvro(response.getResults().get(0));
    }

    private DefaultUniProtRequest createRequest(Query query, boolean withIsoform) {
        DefaultUniProtRequest request = new DefaultUniProtRequest(new DefaultRequestImpl());
        request.setQuery(query);
        request.setRetrieveUniProtEntry(true);
        request.setWithIsoform(withIsoform);
        return request;
    }

    @Override
    public Response<EntryObject> execute(Request request) throws ServiceException {
        if (!started) {
            throw new ServiceException("UniProt service is not started");
        }
        if (request.getQuery() == null || request.getQuery().isEmpty() || request.getQuery().isEverything()) {
            return new EmptyResponse<>();
        }
        String payload = RequestSerializer.requestToBase64(request)
                .orElseThrow(() -> new ServiceException("Cannot serialize UniProt query: " + request.getQuery()));
        HttpRequest httpRequest = HttpRequest.newBuilder(endpoint)
                .timeout(requestTimeout)
                .header("Content-Type", "text/plain; charset=ISO-8859-1")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        try {
            HttpResponse<InputStream> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());
            try (InputStream body = response.body()) {
                if (response.statusCode() != 200) {
                    throw new ServiceException("UniProt request to " + endpoint + " failed with HTTP "
                            + response.statusCode() + " for query: " + request.getQuery());
                }
                long hits = Long.parseLong(response.headers().firstValue("X-Pagination-TotalRecords").orElse("0"));
                String cursor = response.headers().firstValue("X-NextPageCursorMark").orElse(null);
                List<EntryObject> entries = new ArrayList<>();
                try (DataFileStream<EntryObject> stream = new DataFileStream<>(body,
                        new SpecificDatumReader<>(EntryObject.class))) {
                    stream.forEach(entries::add);
                }
                return new EntryResponse(entries, request.getResultOffset(), hits, cursor);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServiceException("UniProt request interrupted for query: " + request.getQuery(), e);
        } catch (IOException | RuntimeException e) {
            throw new ServiceException("Cannot retrieve UniProt results for query: " + request.getQuery(), e);
        }
    }

    @Override
    public void start() {
        started = true;
    }

    @Override
    public void stop() {
        started = false;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    private static final class EntryResponse implements Response<EntryObject> {
        private final List<EntryObject> entries;
        private final long offset;
        private final long hits;
        private final String cursor;

        private EntryResponse(List<EntryObject> entries, long offset, long hits, String cursor) {
            this.entries = entries;
            this.offset = offset;
            this.hits = hits;
            this.cursor = cursor;
        }

        @Override
        public long getHitCount() {
            return hits;
        }

        @Override
        public int getResultPageSize() {
            return entries.size();
        }

        @Override
        public long getResultSetStart() {
            return offset;
        }

        @Override
        public List<EntryObject> getResults() {
            return entries;
        }

        @Override
        public String getCursorMark() {
            return cursor;
        }
    }
}

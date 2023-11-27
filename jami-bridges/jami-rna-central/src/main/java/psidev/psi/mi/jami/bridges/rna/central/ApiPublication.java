package psidev.psi.mi.jami.bridges.rna.central;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.net.URL;
import java.util.List;

@Jacksonized
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
class ApiPublication {
    private Integer count;
    private URL next;
    private URL previous;
    private List<ApiXrefs.Result> results;

    @Jacksonized
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    static class Result {
        private String title;
        private List<String> authors;
        private String publication;
        private String pubmedId;
        private String doi;
        private String pubId;
        private String expertDb;
    }
}

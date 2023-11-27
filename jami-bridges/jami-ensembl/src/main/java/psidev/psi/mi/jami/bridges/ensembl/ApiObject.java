package psidev.psi.mi.jami.bridges.ensembl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
class ApiObject {
    private String source;
    /**
     * Format: homo_sapiens
     */
    private String species;
    private int strand;
    private int version;
    private boolean isCanonical;
    private String canonicalTranscript;
    private ObjectType objectType;
    private String id;
    private String dbType;
    private String displayName;
    private String logicName;
    private String biotype;
    private String assemblyName;
    private String seqRegionName;
    private String description;

    private long start;
    private long end;

    @JsonProperty("Parent")
    private String parentId;
    @JsonProperty("Transcript")
    private List<ApiObject> transcripts;
    @JsonProperty("Translation")
    private List<ApiObject> translations;
    @JsonProperty("Exon")
    private List<ApiObject> exons;
}




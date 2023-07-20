package psidev.psi.mi.jami.bridges.rna.central.fetchers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
class ApiEntree {
    private URL url;
    @JsonProperty("rnacentral_id")
    private String RNACentralId;
    private String md5;
    private String sequence;
    private Integer length;
    private String description;
    private String shortDescription;
    private String species;
    private Integer taxid;
    private URL xrefsURL;
    private URL publicationURL;
    private Boolean isActive;
    private String rnaType;
    private Integer countDistinctOrganisms;
    private List<String> distinctDatabases;

    public void setDistinctDatabases(List<String> distinctDatabases) {
        if (distinctDatabases.size() == 1) this.distinctDatabases = List.of(distinctDatabases.get(0).split(","));
        else this.distinctDatabases = distinctDatabases;
    }
}

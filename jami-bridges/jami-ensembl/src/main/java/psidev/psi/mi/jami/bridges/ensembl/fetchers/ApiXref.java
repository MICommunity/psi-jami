package psidev.psi.mi.jami.bridges.ensembl.fetchers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
class ApiXref {
    private List<String> synonyms;
    private String dbname;
    private String version;
    private String primaryId;
    private String description;
    private String infoText;
    private String displayId;
    private String dbDisplayName;
}

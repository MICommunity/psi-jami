package psidev.psi.mi.jami.bridges.rna.central;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.net.URL;
import java.util.Date;
import java.util.List;

@Jacksonized
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
class ApiXrefs {
    private Integer count;
    private URL next;
    private URL previous;
    private List<Result> results;

    @Jacksonized
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    static class Result {
        private String upi;
        private String database;
        private Boolean isActive;
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
        private Date firstSeen;
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
        private Date lastSeen;
        private Integer taxid;

        private Accession accession;

        private List<String> modifications;
        private Boolean isRfamSeed;

        private String ncbiGeneId;
        private URL ndbExternalUrl;
        private URL ncbiExternalUrl;

        private URL mirbasePrecursor;
        private List<URL> mirbaseMatureProducts;

        private URL refseqMirnaPrecursor;
        private List<URL> refseqMirnaMatureProducts;
        private List<URL> refseqSpliceVariants;

        private URL ensemblUrl;
        private List<URL> ensemblSpliceVariants;

        private String gencodeTranscriptId;
        private URL gencodeEnsemblUrl;

        private Integer quickgoHits;

        @Jacksonized
        @Data
        @Builder
        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public static class Accession {
            private URL url;
            private String id;
            private String parentAc;
            private Integer sequenceVersion;
            private Integer featureStart;
            private Integer featureEnd;
            private String featureName;
            private String description;
            private String externalId;
            private String optionalId;
            private String locusTag;
            private String species;
            private String inference;
            private String rnaType;
            private String gene;
            private String product;
            private String organelle;
            private String standardName;
            private String pdbEntityId;
            private String hgncEnemblId;
            private String hgncId;
            private String biotype;
            private String srpdbId;
            @JsonProperty("ensembl_species_url")
            private String ensemblSpecies;

            private List<Disease> malacardsDiseases;
            private Structure pdbStructuredNote;


            private URL citations;

            private URL expertDbUrl;
            private URL enaUrl;

            @Jacksonized
            @Data
            @Builder
            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
            static class Disease {
                private String name;
                private URL url;
            }

            @Jacksonized
            @Data
            @Builder
            @JsonIgnoreProperties(ignoreUnknown = true)
            static class Structure {
                @JsonFormat(pattern = "yyyy-MM-dd")
                private Date releaseDate;
                private String structureTitle;
                private String resolution;
                private String experimentalTechnique;
                private URL url;
            }
        }
    }
}

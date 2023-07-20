package psidev.psi.mi.jami.bridges.rna.central.fetchers;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.NucleicAcid;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
enum XrefType {
    DEFAULT("DEFAULT", result -> null, List.of(), List.of(), List.of()),
    HGNC("HGNC",
            result -> null,
            List.of(),
            List.of(
                    result -> PartialAlias.builder()
                            .name(result.getAccession().getGene())
                            .typeMI(Alias.GENE_NAME_MI)
                            .build()
            ),
            List.of()),
    REF_SEQ("RefSeq",
            result -> null,
            List.of(),
            List.of(),
            List.of((result, nucleicAcid) -> nucleicAcid.setRefseq(result.getAccession().getExternalId()))
            ),
    ENSEMBL("Ensembl",
            result -> {
                switch (result.getAccession().getExternalId().charAt(3)) {
                    case 'T':
                        return "IA:3522"; // transcript TODO Update to new MI id
                    case 'G':
                        return "MI:0250"; // gene
                    case 'P':
                        return "MI:0251"; // gene product
                    default:
                        return null;
                }
            },
            List.of(
                    result -> PartialXref.builder()
                            .identifier(result.getAccession().getGene())
                            .qualifierMI("MI:0250")
                            .build()),
            List.of(),
            List.of()
    );
    public final String database;
    public final Function<ApiXrefs.Result, String> qualifierIdBuilder;
    public final List<Function<ApiXrefs.Result, PartialXref>> extraReferenceBuilders;
    public final List<Function<ApiXrefs.Result, PartialAlias>> aliasBuilders;
    public final List<BiConsumer<ApiXrefs.Result, NucleicAcid>> extraValueSetters;

    private static final Map<String, XrefType> map = Arrays.stream(XrefType.values())
            .collect(Collectors.toMap(
                    type -> type.database,
                    type -> type)
            );

    public static XrefType getByDatabase(String database) {
        return map.getOrDefault(database, DEFAULT);
    }

    @Data
    @Builder
    public static class PartialXref {
        private String identifier;
        private String qualifierMI;
    }

    @Data
    @Builder
    public static class PartialAlias {
        private String name;
        private String typeMI;
    }

}

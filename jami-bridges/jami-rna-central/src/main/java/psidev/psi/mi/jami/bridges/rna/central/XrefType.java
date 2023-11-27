package psidev.psi.mi.jami.bridges.rna.central;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import psidev.psi.mi.jami.bridges.rna.central.partials.PartialAlias;
import psidev.psi.mi.jami.bridges.rna.central.partials.PartialCvTerm;
import psidev.psi.mi.jami.bridges.rna.central.partials.PartialXref;
import psidev.psi.mi.jami.bridges.rna.central.utils.OLSUtils;
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
            result -> PartialCvTerm.builder()
                    .id("MI:0361")
                    .build(),
            List.of(),
            List.of(
                    result -> PartialAlias.builder()
                            .name(result.getAccession().getGene())
                            .type(PartialCvTerm.builder().id(Alias.GENE_NAME_MI).build())
                            .build()
            ),
            List.of()),
    REF_SEQ("RefSeq",
            result -> null,
            List.of(),
            List.of(),
            List.of()
    ),
    ENSEMBL("Ensembl",
            result -> {
                switch (result.getAccession().getExternalId().charAt(3)) {
                    case 'T':
                        return PartialCvTerm.builder()
                                .id("IA:3522")
                                .name("transcript")
                                .ontology(OLSUtils.intactCV)
                                .build(); // TODO Update to new MI id
                    case 'G':
                        return PartialCvTerm.builder()
                                .id("IA:3594")
                                .name("gene ref")
                                .ontology(OLSUtils.intactCV)
                                .build(); // TODO Update to new MI id
                    case 'P':
                        return PartialCvTerm.builder()
                                .id("MI:0251")
                                .name("gene product")
                                .build(); // gene product
                    default:
                        return null;
                }
            },
            List.of(
                    result -> PartialXref.builder()
                            .identifier(result.getAccession().getGene().split("\\.")[0])
                            .qualifier(PartialCvTerm.builder()
                                    .id("IA:3594")
                                    .name("gene ref")
                                    .ontology(OLSUtils.intactCV)
                                    .build())
                            .build()),
            List.of(),
            List.of()
    );
    public final String database;
    public final Function<ApiXrefs.Result, PartialCvTerm> qualifierIdBuilder;
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




}

package psidev.psi.mi.jami.bridges.ensembl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
enum ObjectType {
    GENE("Gene", 'G'),
    TRANSLATION("Translation", 'P'),
    TRANSCRIPT("Transcript", 'T'),
    EXON("Exon", 'E');

    private final String name;
    private final char reprChar;
}

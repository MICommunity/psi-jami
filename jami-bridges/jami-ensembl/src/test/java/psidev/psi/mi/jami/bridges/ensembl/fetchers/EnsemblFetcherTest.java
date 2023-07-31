package psidev.psi.mi.jami.bridges.ensembl.fetchers;

import org.junit.Before;
import org.junit.Test;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.Gene;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.NucleicAcid;
import psidev.psi.mi.jami.model.Xref;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class EnsemblFetcherTest {

    EnsemblFetcher fetcher;

    @Before
    public void setUp() throws Exception {
        fetcher = new EnsemblFetcher();
    }

    @Test
    public void fetchGene() throws BridgeFailedException {
        // https://rest.ensembl.org/lookup/id/ENSG00000157764?content-type=application/json
        Collection<Interactor> interactors = fetcher.fetchByIdentifier("ENSG00000157764");
        if (interactors.isEmpty()) fail();
        assertEquals(1, interactors.size());
        Interactor interactor = interactors.iterator().next();
        assertTrue(interactor instanceof Gene);
        Gene gene = (Gene) interactor;

        assertNotNull(gene.getPreferredIdentifier());
        assertNotNull(gene.getShortName());
        assertNotNull(gene.getFullName());
        assertNotNull(gene.getEnsembl());

        assertTrue("Should have gene name alias", gene.getAliases().stream().anyMatch(alias -> alias.getType().getShortName().equals("gene name")));
        assertTrue("Should have gene product xref", gene.getXrefs().stream().map(Xref::getQualifier).filter(Objects::nonNull).anyMatch(qualifier -> qualifier.getShortName().equals("gene product")));
        assertTrue("Should have transcript xref", gene.getXrefs().stream().map(Xref::getQualifier).filter(Objects::nonNull).anyMatch(qualifier -> qualifier.getShortName().equals("transcript")));
    }

    @Test
    public void fetchTranscript() throws BridgeFailedException {
        // https://rest.ensembl.org/lookup/id/ENST00000646891?content-type=application/json
        Collection<Interactor> interactors = fetcher.fetchByIdentifier("ENST00000646891");
        if (interactors.isEmpty()) fail();
        assertEquals(1, interactors.size());
        Interactor interactor = interactors.iterator().next();
        assertTrue(interactor instanceof NucleicAcid);
        NucleicAcid gene = (NucleicAcid) interactor;

        assertNotNull(gene.getPreferredIdentifier());
        assertNotNull(gene.getShortName());
        assertNotNull(gene.getFullName());
        assertNotNull(gene.getSequence());

        assertTrue(gene.getAliases().stream().anyMatch(alias -> alias.getType().getShortName().equals("gene name")));
        // Not supported for transcripts so far
        // assertTrue(gene.getXrefs().stream().anyMatch(xref -> xref.getQualifier().getShortName().equals("gene product")));
        assertTrue(gene.getXrefs().stream().anyMatch(xref -> xref.getQualifier().getShortName().equals("gene")));
    }

    @Test
    public void fetchCollection() throws BridgeFailedException {
        List<String> identifiers = new ArrayList<>();
        identifiers.add(null); // Should not be queried
        identifiers.add(""); // Should not be queried
        identifiers.add("ksdjbfejwhfbwjehfbwef");// Should not be queried
        identifiers.add("ENSG00000157764.14"); // Should be queried without version
        identifiers.add("ENST00000646891");// Should be queried
        identifiers.add("ENSE00003828230");// Should be queried but not having return (Exon not supported)
        identifiers.add("ENSP00000419060");// Should be queried but not having return (Translation not supported)

        Collection<Interactor> interactors = fetcher.fetchByIdentifiers(identifiers);
        assertEquals(2, interactors.size());
        assertEquals(1, interactors.stream().filter(interactor -> interactor instanceof Gene).count());
        assertEquals(1, interactors.stream().filter(interactor -> interactor instanceof NucleicAcid).count());

    }
}
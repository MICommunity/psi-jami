package psidev.psi.mi.jami.bridges.rna.central.fetchers;

import org.junit.Before;
import org.junit.Test;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.NucleicAcid;
import psidev.psi.mi.jami.model.Xref;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class RNACentralFetcherTest {

    RNACentralFetcher fetcher;

    @Before
    public void setUp() throws Exception {
        fetcher = new RNACentralFetcher();
    }

    @Test
    public void fetchByIdentifiers() throws BridgeFailedException {
        // https://rnacentral.org/api/v1/rna/URS000075C808.json
        Collection<NucleicAcid> nucleicAcids = fetcher.fetchByIdentifier("URS000075C808_9606");
        if (nucleicAcids.isEmpty()) fail();
        assertEquals(1, nucleicAcids.size());
        NucleicAcid acid = nucleicAcids.iterator().next();
        assertNotNull(acid.getPreferredIdentifier());
        assertNotNull(acid.getFullName());
    }

    @Test
    public void testPDBeXref() throws BridgeFailedException {
        // https://rnacentral.org/api/v1/rna/URS0002311975_9606.json
        // https://rnacentral.org/api/v1/rna/URS0002311975/xrefs.json
        Collection<NucleicAcid> nucleicAcids = fetcher.fetchByIdentifier("URS0002311975_9606");
        NucleicAcid acid = nucleicAcids.iterator().next();
        List<Xref> xrefs = acid.getXrefs().stream().filter(xref -> xref.getDatabase().getShortName().equals("pdbe")).collect(Collectors.toList());
        assertFalse(xrefs.isEmpty());
        assertTrue("URS0002311975_9606 should have a PDB Xref to 7ONU",
                xrefs.stream().anyMatch(xref -> xref.getId().equals("7ONU")));
    }


    @Test
    public void testEnsemblXref() throws BridgeFailedException {
        // https://rnacentral.org/api/v1/rna/URS0000031E12_9606.json
        // https://rnacentral.org/api/v1/rna/URS0000031E12/xrefs.json
        Collection<NucleicAcid> nucleicAcids = fetcher.fetchByIdentifier("URS0000031E12_9606");
//        Collection<NucleicAcid> nucleicAcids = fetcher.fetchByIdentifier("URS000077CD3E_9606");
        NucleicAcid acid = nucleicAcids.iterator().next();
        List<Xref> xrefs = acid.getXrefs().stream().filter(xref -> xref.getDatabase().getShortName().equals("ensembl")).collect(Collectors.toList());
        assertFalse(xrefs.isEmpty());
//        assertTrue("URS000077CD3E_9606 should have a transcript Xref to ENST00000618127",
//                xrefs.stream().anyMatch(x -> x.getId().equals("ENST00000618127") && x.getQualifier().getShortName().equals("transcript")));
        assertTrue("URS000077CD3E_9606 should have a gene Xref to ENSG00000199030.2",
                xrefs.stream().anyMatch(x -> x.getId().equals("ENSG00000199030.2") && x.getQualifier().getShortName().equals("gene")));
        assertTrue( "Querying for a human identifier should only get human ensembl XRefs"
                ,xrefs.stream().allMatch(xref -> xref.getId().startsWith("ENS")));
    }

    @Test
    public void testRefSeqXref() throws BridgeFailedException {
        // https://rnacentral.org/api/v1/rna/URS000075C808_9606.json
        // https://rnacentral.org/api/v1/rna/URS000075C808/xrefs.json
        Collection<NucleicAcid> nucleicAcids = fetcher.fetchByIdentifier("URS000075C808_9606");
        NucleicAcid acid = nucleicAcids.iterator().next();
        List<Xref> xrefs = acid.getXrefs().stream().filter(xref -> xref.getDatabase().getShortName().equals("refseq")).collect(Collectors.toList());
        assertFalse(xrefs.isEmpty());
        assertTrue("URS000075C808_9606 should have a RefSeq Xref to NR_003716",
                xrefs.stream().anyMatch(xref -> xref.getId().equals("NR_003716")));
        assertEquals("URS000075C808_9606 should have a RefSeq of NR_003716", "NR_003716", acid.getRefseq());
    }

    @Test
    public void testGeneNameAlias() throws BridgeFailedException {
        // https://rnacentral.org/api/v1/rna/URS000075C808_9606.json
        // https://rnacentral.org/api/v1/rna/URS000075C808/xrefs.json
        Collection<NucleicAcid> nucleicAcids = fetcher.fetchByIdentifier("URS000075C808_9606");
        NucleicAcid acid = nucleicAcids.iterator().next();
        List<Alias> aliases = acid.getAliases().stream().filter(alias -> alias.getType().getShortName().equals("gene name")).collect(Collectors.toList());
        assertEquals("URS000075C808_9606 should only have one Gene Name alias", 1, aliases.size());
        assertTrue("URS000075C808_9606 gene name should be HOTAIR",
                aliases.stream().anyMatch(alias -> alias.getName().equals("HOTAIR")));
    }
}
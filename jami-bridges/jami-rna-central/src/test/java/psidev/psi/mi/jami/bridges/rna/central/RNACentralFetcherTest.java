package psidev.psi.mi.jami.bridges.rna.central;

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
        assertEquals("transfer rna", acid.getInteractorType().getFullName());
        List<Xref> xrefs = acid.getXrefs().stream().filter(xref -> xref.getDatabase().getShortName().equals("pdbe")).collect(Collectors.toList());
        assertTrue(xrefs.isEmpty());
        List<Xref> identifiers = acid.getIdentifiers().stream().filter(xref -> xref.getDatabase().getShortName().equals("pdbe")).collect(Collectors.toList());
        assertFalse(identifiers.isEmpty());
        assertTrue("URS0002311975_9606 should have a PDB Xref to 7ONU",
                identifiers.stream().anyMatch(xref -> xref.getId().equals("7ONU")));
    }


    @Test
    public void testEnsemblXref() throws BridgeFailedException {
        // https://rnacentral.org/rna/URS0000031E12/9606
        // https://rnacentral.org/api/v1/rna/URS0000031E12_9606.json
        // https://rnacentral.org/api/v1/rna/URS0000031E12/xrefs.json
        Collection<NucleicAcid> nucleicAcids = fetcher.fetchByIdentifier("URS0000031E12_9606");
        NucleicAcid acid = nucleicAcids.iterator().next();
        List<Xref> xrefs = acid.getXrefs().stream().filter(xref -> xref.getDatabase().getShortName().equals("ensembl")).collect(Collectors.toList());
        assertFalse(xrefs.isEmpty());
        assertTrue("URS0000031E12_9606 should have a transcript Xref to ENST00000362160",
                xrefs.stream().anyMatch(x -> x.getId().equals("ENST00000362160") && x.getQualifier().getShortName().equals("transcript")));
        assertTrue("URS0000031E12_9606 should have a gene Xref to ENSG00000199030",
                xrefs.stream().anyMatch(x -> x.getId().equals("ENSG00000199030") && x.getQualifier().getShortName().equals("gene ref")));
        assertTrue("Querying for a human identifier should only get human ensembl XRefs",
                xrefs.stream().allMatch(xref -> xref.getId().startsWith("ENS")));

        assertTrue("In case of complicated types like pre_miRNA, the default type should be RNA, but we should add an annotation specifying the RNA Central type",
                acid.getAnnotations().stream().anyMatch(annotation -> annotation.getValue().contains("pre_miRNA")));


        // Side tests

        assertTrue("URS0000031E12_9606 should have a RefSeq Xref to NR_029480",
                acid.getIdentifiers().stream().anyMatch(x -> x.getId().equals("NR_029480")));

        assertTrue("URS0000031E12_9606 should have a miRBase Xref to MI0000064",
                acid.getIdentifiers().stream().anyMatch(x -> x.getId().equals("MI0000064")));

        assertTrue("All xrefs should have a qualifier",
                acid.getIdentifiers().stream().allMatch(xref -> xref.getQualifier() != null));
    }

    @Test
    public void testRefSeqXref() throws BridgeFailedException {
        // https://rnacentral.org/api/v1/rna/URS00026A23F2_9606.json
        // https://rnacentral.org/api/v1/rna/URS00026A23F2/xrefs.json
        Collection<NucleicAcid> nucleicAcids = fetcher.fetchByIdentifier("URS00026A23F2_9606");
        NucleicAcid acid = nucleicAcids.iterator().next();
        List<Xref> xrefs = acid.getXrefs().stream().filter(xref -> xref.getDatabase().getShortName().equals("refseq")).collect(Collectors.toList());
        assertTrue(xrefs.isEmpty());
        List<Xref> identifiers = acid.getIdentifiers().stream().filter(xref -> xref.getDatabase().getShortName().equals("refseq")).collect(Collectors.toList());
        assertFalse(identifiers.isEmpty());
        assertTrue("URS00026A23F2_9606 should have a RefSeq Xref to NR_003716",
                identifiers.stream().anyMatch(xref -> xref.getId().equals("NR_003716")));
    }

    @Test
    public void testGeneNameAlias() throws BridgeFailedException {
        // https://rnacentral.org/api/v1/rna/URS00026A23F2_9606.json
        // https://rnacentral.org/api/v1/rna/URS00026A23F2/xrefs.json
        Collection<NucleicAcid> nucleicAcids = fetcher.fetchByIdentifier("URS00026A23F2_9606");
        NucleicAcid acid = nucleicAcids.iterator().next();
        List<Alias> aliases = acid.getAliases().stream().filter(alias -> alias.getType().getShortName().equals("gene name")).collect(Collectors.toList());
        assertEquals("URS00026A23F2_9606 should only have one Gene Name alias", 1, aliases.size());
        assertTrue("URS00026A23F2_9606 gene name should be HOTAIR",
                aliases.stream().anyMatch(alias -> alias.getName().equals("HOTAIR")));
    }
}
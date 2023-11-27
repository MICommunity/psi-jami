package psidev.psi.mi.jami.enricher.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import psidev.psi.mi.jami.bridges.fetcher.mock.FailingGeneFetcher;
import psidev.psi.mi.jami.bridges.fetcher.mock.MockGeneFetcher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.minimal.MinimalGeneEnricher;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.enricher.listener.GeneEnricherListener;
import psidev.psi.mi.jami.enricher.listener.impl.GeneEnricherListenerManager;
import psidev.psi.mi.jami.enricher.listener.impl.log.GeneEnricherLogger;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultAlias;
import psidev.psi.mi.jami.model.impl.DefaultGene;
import psidev.psi.mi.jami.model.impl.DefaultOrganism;
import psidev.psi.mi.jami.model.impl.DefaultXref;
import psidev.psi.mi.jami.utils.CvTermUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * Unit tests for MinimalGeneEnricher
 *
 * @author Noemi del Toro (ntoro@ebi.ac.uk)
 * @since 23/07/18
 */

public class MinimalGeneEnricherTest {

    private MinimalGeneEnricher geneEnricher;
    private MockGeneFetcher mockEnsemblGeneFetcher;
    private MockGeneFetcher mockGeneFetcher;

    Gene persistentGene;
    int persistentInt;

    private static final String TEST_OLD_SHORTNAME = "test old shortName";
    private static final String TEST_OLD_FULLNAME = "test old fullName";
    private static final String TEST_AC_CUSTOM_GENE = "ENSG00000139617";
    private static final String TEST_SHORTNAME = "test shortName";
    private static final String TEST_SHORTNAME_REF_SEQ = TEST_SHORTNAME + " : RefSeq";
    private static final String TEST_FULLNAME = "test fullName";
    private static final String TEST_FULLNAME_REF_SEQ = TEST_FULLNAME + " : RefSeq";
    private static final String TEST_AC_FULL_GENE = "ENSG00000139618";
    private static final String TEST_AC_HALF_GENE = "ENSG00000139616";
    private static final String TEST_AC_REF_SEQ = "NM_058074.4";
    private static final int TEST_ORGANISM_ID = 1234;
    private static final String TEST_ORGANISM_COMMON = "Common";
    private static final String TEST_ORGANISM_SCIENTIFIC = "Scientific";

    @Before
    public void initialiseFetcherAndEnricher() {
        mockEnsemblGeneFetcher = new MockGeneFetcher();
        mockGeneFetcher = new MockGeneFetcher();
        geneEnricher = new MinimalGeneEnricher(mockGeneFetcher);
        geneEnricher.setEnsemblFetcher(mockEnsemblGeneFetcher);

        Organism organism = new DefaultOrganism(TEST_ORGANISM_ID, TEST_ORGANISM_COMMON, TEST_ORGANISM_SCIENTIFIC);

        Gene fullGene = new DefaultGene(TEST_SHORTNAME, TEST_FULLNAME);
        fullGene.setEnsembl(TEST_AC_FULL_GENE);
        fullGene.setOrganism(organism);
        mockEnsemblGeneFetcher.addEntry(TEST_AC_FULL_GENE, List.of(fullGene));

        Gene halfGene = new DefaultGene(TEST_SHORTNAME);
        halfGene.setEnsembl(TEST_AC_HALF_GENE);
        Collection<Gene> halfGeneList = new ArrayList<Gene>();
        halfGeneList.add(halfGene);
        mockEnsemblGeneFetcher.addEntry(TEST_AC_HALF_GENE, halfGeneList);

        Gene fullGeneRefSeq = new DefaultGene(TEST_SHORTNAME_REF_SEQ, TEST_FULLNAME_REF_SEQ);
        fullGene.setEnsembl(TEST_AC_REF_SEQ);
        fullGene.setOrganism(organism);
        mockGeneFetcher.addEntry(TEST_AC_REF_SEQ, List.of(fullGeneRefSeq));

        persistentGene = null;
        persistentInt = 0;
    }


    @Test(expected = EnricherException.class)
    public void test_bridgeFailure_throws_exception_when_persistent() throws EnricherException {

        FailingGeneFetcher fetcher = new FailingGeneFetcher(-1);
        Gene geneToEnrich = new DefaultGene(TEST_SHORTNAME);
        geneToEnrich.setEnsembl(TEST_AC_HALF_GENE);

        Gene geneFetched = new DefaultGene(TEST_SHORTNAME, TEST_FULLNAME);
        geneFetched.setEnsembl(TEST_AC_HALF_GENE);
        fetcher.addEntry(TEST_AC_HALF_GENE, geneFetched);
        geneEnricher = new MinimalGeneEnricher(fetcher);

        geneEnricher.enrich(geneToEnrich);

        fail("Exception should be thrown before this point");
    }

    @Test
    public void test_bridgeFailure_does_not_throw_exception_when_not_persistent() throws EnricherException {
        int timesToTry = 3;
        assertTrue(timesToTry < geneEnricher.getRetryCount());

        FailingGeneFetcher fetcher = new FailingGeneFetcher(timesToTry);

        Gene geneToEnrich = new DefaultGene(TEST_SHORTNAME);
        geneToEnrich.setEnsembl(TEST_AC_HALF_GENE);

        Gene geneFetched = new DefaultGene(TEST_SHORTNAME, TEST_FULLNAME);
        geneFetched.setEnsembl(TEST_AC_HALF_GENE);
        fetcher.addEntry(TEST_AC_HALF_GENE, geneFetched);
        geneEnricher = new MinimalGeneEnricher(fetcher);
        geneEnricher.enrich(geneToEnrich);

        assertEquals(TEST_FULLNAME, geneToEnrich.getFullName());
    }


    // == FAILURE ON NULL ======================================================================


    /**
     * Assert that when a null gene is provided, an exception is thrown
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_exception_when_fetching_on_null_gene() throws EnricherException {
        Gene null_gene = null;
        this.geneEnricher.enrich(null_gene);
    }


    /**
     * Assert that when a gene has no identifier, the gene is not enriched and that
     * a "failed" status is sent to the listener.
     */
    @Test
    public void test_no_fetching_on_gene_with_null_identifier() throws EnricherException {

        geneEnricher.setListener(new GeneEnricherLogger());

        persistentGene = new DefaultGene(TEST_OLD_SHORTNAME);
        persistentInt = 0;

        assertNotNull(persistentGene);
        assertNull(persistentGene.getEnsembl());


        geneEnricher.setListener(new GeneEnricherListenerManager(new GeneEnricherLogger(),   //Comment this line to silence logging
                new GeneEnricherListener() {
                    public void onEnrichmentComplete(Gene gene, EnrichmentStatus status, String message) {
                        assertTrue(gene == persistentGene);
                        assertEquals(EnrichmentStatus.FAILED, status);
                        persistentInt++;
                    }

                    public void onGeneRemapped(Gene gene, String oldUniprot) {
                        fail();
                    }

                    public void onUniprotKbUpdate(Gene gene, String oldUniprot) {
                        fail();
                    }

                    public void onRefseqUpdate(Gene gene, String oldRefseq) {
                        fail();
                    }

                    public void onGeneNameUpdate(Gene gene, String oldGeneName) {
                        fail();
                    }

                    public void onRogidUpdate(Gene gene, String oldRogid) {
                        fail();
                    }

                    public void onSequenceUpdate(Gene gene, String oldSequence) {
                        fail();
                    }

                    public void onShortNameUpdate(Gene gene, String oldShortName) {
                        fail();
                    }

                    public void onFullNameUpdate(Gene gene, String oldFullName) {
                        fail();
                    }

                    public void onOrganismUpdate(Gene interactor, Organism oldOrganism) {
                        fail();
                    }

                    public void onInteractorTypeUpdate(Gene interactor, CvTerm oldType) {
                        fail();
                    }

                    public void onAddedIdentifier(Gene gene, Xref added) {
                        fail();
                    }

                    public void onRemovedIdentifier(Gene gene, Xref removed) {
                        fail();
                    }

                    public void onAddedXref(Gene gene, Xref added) {
                        fail();
                    }

                    public void onRemovedXref(Gene gene, Xref removed) {
                        fail();
                    }

                    public void onAddedAlias(Gene gene, Alias added) {
                        fail();
                    }

                    public void onRemovedAlias(Gene gene, Alias removed) {
                        fail();
                    }

                    public void onAddedChecksum(Gene gene, Checksum added) {
                        fail();
                    }

                    public void onRemovedChecksum(Gene gene, Checksum removed) {
                        fail();
                    }

                    public void onAddedAnnotation(Gene o, Annotation added) {
                        Assert.fail();
                    }

                    public void onRemovedAnnotation(Gene o, Annotation removed) {
                        Assert.fail();
                    }

                    public void onEnrichmentError(Gene object, String message, Exception e) {
                        Assert.fail();
                    }
                }));

        this.geneEnricher.enrich(persistentGene);

        assertEquals(TEST_OLD_SHORTNAME, persistentGene.getShortName());
        assertNull(persistentGene.getEnsembl());
        assertEquals(1, persistentInt);
    }


    // =====================================================
    // ENRICHING CASES BEGIN

    /**
     * Assert that when a gene has a known interactor type other than gene,
     * the enrichment fails and no changes are made.
     */
    @Test
    public void test_interactorType_conflict_stops_enrichment() throws EnricherException {
        persistentGene = new DefaultGene(TEST_OLD_SHORTNAME, TEST_OLD_FULLNAME);
        persistentGene.setEnsembl(TEST_AC_HALF_GENE);
        persistentGene.setInteractorType(CvTermUtils.createProteinInteractorType());

        assertEquals(Protein.PROTEIN, persistentGene.getInteractorType().getShortName());
        assertEquals(Protein.PROTEIN_MI, persistentGene.getInteractorType().getMIIdentifier());

        geneEnricher.setListener(new GeneEnricherListenerManager(new GeneEnricherLogger(),  //Comment this line to silence logging
                new GeneEnricherListener() {
                    public void onEnrichmentComplete(Gene gene, EnrichmentStatus status, String message) {
                        assertTrue(gene == persistentGene);
                        assertEquals(EnrichmentStatus.FAILED, status);
                        assertTrue(message.toUpperCase().contains("INTERACTOR"));
                        persistentInt++;
                    }

                    public void onGeneRemapped(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onUniprotKbUpdate(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onRefseqUpdate(Gene gene, String oldRefseq) {
                        fail("Should not reach this point");
                    }

                    public void onGeneNameUpdate(Gene gene, String oldGeneName) {
                        fail("Should not reach this point");
                    }

                    public void onRogidUpdate(Gene gene, String oldRogid) {
                        fail("Should not reach this point");
                    }

                    public void onSequenceUpdate(Gene gene, String oldSequence) {
                        fail("Should not reach this point");
                    }

                    public void onShortNameUpdate(Gene gene, String oldShortName) {
                        fail("Should not reach this point");
                    }

                    public void onFullNameUpdate(Gene gene, String oldFullName) {
                        fail("Should not reach this point");
                    }

                    public void onOrganismUpdate(Gene interactor, Organism oldOrganism) {
                        fail();
                    }

                    public void onInteractorTypeUpdate(Gene interactor, CvTerm oldType) {
                        fail();
                    }

                    public void onAddedIdentifier(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedIdentifier(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedXref(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedXref(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAlias(Gene gene, Alias added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedAlias(Gene gene, Alias removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedChecksum(Gene gene, Checksum added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedChecksum(Gene gene, Checksum removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAnnotation(Gene o, Annotation added) {
                        Assert.fail();
                    }

                    public void onRemovedAnnotation(Gene o, Annotation removed) {
                        Assert.fail();
                    }

                    public void onEnrichmentError(Gene object, String message, Exception e) {
                        Assert.fail();
                    }
                }));

        geneEnricher.enrich(persistentGene);
        assertEquals(Protein.PROTEIN, persistentGene.getInteractorType().getShortName());
        assertEquals(Protein.PROTEIN_MI, persistentGene.getInteractorType().getMIIdentifier());
        assertEquals(1, persistentInt);
    }

    /**
     * Assert that when a gene has a known interactor type other than gene,
     * the enrichment fails and no changes are made.
     */
    @Test
    public void test_organism_conflict_between_organism_TAXIDs_stops_enrichment() throws EnricherException {
        Gene customGene = new DefaultGene(TEST_SHORTNAME, TEST_FULLNAME);
        customGene.setEnsembl(TEST_AC_CUSTOM_GENE);
        customGene.setOrganism(new DefaultOrganism(9898));
        Collection<Gene> customList = new ArrayList<Gene>();
        customList.add(customGene);
        mockEnsemblGeneFetcher.addEntry(TEST_AC_CUSTOM_GENE, customList);

        persistentGene = new DefaultGene(TEST_OLD_SHORTNAME, TEST_OLD_FULLNAME);
        persistentGene.setEnsembl(TEST_AC_CUSTOM_GENE);
        persistentGene.setInteractorType(CvTermUtils.createGeneInteractorType());
        persistentGene.setOrganism(new DefaultOrganism(1010));
        persistentGene.setInteractorType(CvTermUtils.createGeneInteractorType());
        assertEquals(Gene.GENE, persistentGene.getInteractorType().getShortName());
        assertEquals(Gene.GENE_MI, persistentGene.getInteractorType().getMIIdentifier());

        geneEnricher.setListener(new GeneEnricherListenerManager(new GeneEnricherLogger(),  //Comment this line to silence logging
                new GeneEnricherListener() {
                    public void onEnrichmentComplete(Gene gene, EnrichmentStatus status, String message) {
                        assertTrue(gene == persistentGene);
                        assertEquals(EnrichmentStatus.FAILED, status);
                        assertTrue(message.toUpperCase().contains("MISMATCH"));
                        assertTrue(message.toUpperCase().contains("INTERACTOR"));
                        persistentInt++;
                    }

                    public void onGeneRemapped(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onUniprotKbUpdate(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onRefseqUpdate(Gene gene, String oldRefseq) {
                        fail("Should not reach this point");
                    }

                    public void onGeneNameUpdate(Gene gene, String oldGeneName) {
                        fail("Should not reach this point");
                    }

                    public void onRogidUpdate(Gene gene, String oldRogid) {
                        fail("Should not reach this point");
                    }

                    public void onSequenceUpdate(Gene gene, String oldSequence) {
                        fail("Should not reach this point");
                    }

                    public void onShortNameUpdate(Gene gene, String oldShortName) {
                        fail("Should not reach this point");
                    }

                    public void onFullNameUpdate(Gene gene, String oldFullName) {
                        fail("Should not reach this point");
                    }

                    public void onOrganismUpdate(Gene interactor, Organism oldOrganism) {
                        fail();
                    }

                    public void onInteractorTypeUpdate(Gene interactor, CvTerm oldType) {
                        fail();
                    }

                    public void onAddedIdentifier(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedIdentifier(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedXref(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedXref(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAlias(Gene gene, Alias added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedAlias(Gene gene, Alias removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedChecksum(Gene gene, Checksum added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedChecksum(Gene gene, Checksum removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAnnotation(Gene o, Annotation added) {
                        Assert.fail();
                    }

                    public void onRemovedAnnotation(Gene o, Annotation removed) {
                        Assert.fail();
                    }

                    public void onEnrichmentError(Gene object, String message, Exception e) {
                        Assert.fail();
                    }
                }));

        geneEnricher.enrich(persistentGene);

        assertEquals(1, persistentInt);
    }

    /**
     * Assert that when a gene has a known interactor type other than gene,
     * the enrichment fails and no changes are made.
     */
    @Test
    public void test_organism_and_interactorType_conflicts_both_reported() throws EnricherException {
        Gene customGene = new DefaultGene(TEST_SHORTNAME, TEST_FULLNAME);
        customGene.setEnsembl(TEST_AC_CUSTOM_GENE);
        customGene.setOrganism(new DefaultOrganism(9898));
        Collection<Gene> customList = new ArrayList<Gene>();
        customList.add(customGene);
        mockEnsemblGeneFetcher.addEntry(TEST_AC_CUSTOM_GENE, customList);

        persistentGene = new DefaultGene(TEST_OLD_SHORTNAME, TEST_OLD_FULLNAME);
        persistentGene.setEnsembl(TEST_AC_CUSTOM_GENE);
        persistentGene.setInteractorType(CvTermUtils.createProteinInteractorType());
        persistentGene.setOrganism(new DefaultOrganism(1010));


        geneEnricher.setListener(new GeneEnricherListenerManager(
                new GeneEnricherLogger(),  //Comment this line to silence logging
                new GeneEnricherListener() {
                    public void onEnrichmentComplete(Gene gene, EnrichmentStatus status, String message) {
                        assertTrue(gene == persistentGene);
                        assertEquals(EnrichmentStatus.FAILED, status);
                        assertTrue(message.toUpperCase().contains("MISMATCH"));
                        persistentInt++;
                    }

                    public void onGeneRemapped(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onUniprotKbUpdate(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onRefseqUpdate(Gene gene, String oldRefseq) {
                        fail("Should not reach this point");
                    }

                    public void onGeneNameUpdate(Gene gene, String oldGeneName) {
                        fail("Should not reach this point");
                    }

                    public void onRogidUpdate(Gene gene, String oldRogid) {
                        fail("Should not reach this point");
                    }

                    public void onSequenceUpdate(Gene gene, String oldSequence) {
                        fail("Should not reach this point");
                    }

                    public void onShortNameUpdate(Gene gene, String oldShortName) {
                        fail("Should not reach this point");
                    }

                    public void onFullNameUpdate(Gene gene, String oldFullName) {
                        fail("Should not reach this point");
                    }

                    public void onOrganismUpdate(Gene interactor, Organism oldOrganism) {
                        fail();
                    }

                    public void onInteractorTypeUpdate(Gene interactor, CvTerm oldType) {
                        fail();
                    }

                    public void onAddedIdentifier(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedIdentifier(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedXref(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedXref(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAlias(Gene gene, Alias added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedAlias(Gene gene, Alias removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedChecksum(Gene gene, Checksum added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedChecksum(Gene gene, Checksum removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAnnotation(Gene o, Annotation added) {
                        Assert.fail();
                    }

                    public void onRemovedAnnotation(Gene o, Annotation removed) {
                        Assert.fail();
                    }

                    public void onEnrichmentError(Gene object, String message, Exception e) {
                        Assert.fail();
                    }
                }));

        geneEnricher.enrich(persistentGene);

        assertEquals(1, persistentInt);
    }

    // == INTERACTOR TYPE =======================================================================

    /**
     * Assert that when a gene already has a gene interactor type,
     * no changes are made and enrichment is successful.
     */
    @Test
    public void test_interactorType_ignored_if_is_already_protein() throws EnricherException {

        persistentGene = new DefaultGene(TEST_OLD_SHORTNAME, TEST_OLD_FULLNAME);
        persistentGene.setEnsembl(TEST_AC_HALF_GENE);

        CvTerm value = CvTermUtils.createGeneInteractorType();
        persistentGene.setInteractorType(value);
        assertEquals(Gene.GENE, persistentGene.getInteractorType().getShortName());
        assertEquals(Gene.GENE_MI, persistentGene.getInteractorType().getMIIdentifier());

        geneEnricher.setListener(new GeneEnricherListenerManager(
                // new GeneEnricherLogger() ,  //Comment this line to silence logging
                new GeneEnricherListener() {
                    public void onEnrichmentComplete(Gene gene, EnrichmentStatus status, String message) {
                        assertTrue(gene == persistentGene);
                        assertEquals(EnrichmentStatus.SUCCESS, status);
                        persistentInt++;
                    }

                    public void onGeneRemapped(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onUniprotKbUpdate(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onRefseqUpdate(Gene gene, String oldRefseq) {
                        fail("Should not reach this point");
                    }

                    public void onGeneNameUpdate(Gene gene, String oldGeneName) {
                        fail("Should not reach this point");
                    }

                    public void onRogidUpdate(Gene gene, String oldRogid) {
                        fail("Should not reach this point");
                    }

                    public void onSequenceUpdate(Gene gene, String oldSequence) {
                        fail("Should not reach this point");
                    }

                    public void onShortNameUpdate(Gene gene, String oldShortName) {
                        fail("Should not reach this point");
                    }

                    public void onFullNameUpdate(Gene gene, String oldFullName) {
                        fail("Should not reach this point");
                    }

                    public void onOrganismUpdate(Gene interactor, Organism oldOrganism) {
                        fail();
                    }

                    public void onInteractorTypeUpdate(Gene interactor, CvTerm oldType) {
                        fail();
                    }

                    public void onAddedIdentifier(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedIdentifier(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedXref(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedXref(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAlias(Gene gene, Alias added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedAlias(Gene gene, Alias removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedChecksum(Gene gene, Checksum added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedChecksum(Gene gene, Checksum removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAnnotation(Gene o, Annotation added) {
                        Assert.fail();
                    }

                    public void onRemovedAnnotation(Gene o, Annotation removed) {
                        Assert.fail();
                    }

                    public void onEnrichmentError(Gene object, String message, Exception e) {
                        Assert.fail();
                    }
                }));


        geneEnricher.enrich(persistentGene);

        assertTrue(persistentGene.getInteractorType() == value); //Show they are the same instance
        assertEquals(Gene.GENE, persistentGene.getInteractorType().getShortName());
        assertEquals(Gene.GENE_MI, persistentGene.getInteractorType().getMIIdentifier());

        assertEquals(1, persistentInt);
    }


    /**
     * Assert that when a gene has an unknown interactor type,
     * the type is updated to gene and the enrichment is successful.
     */
    @Test
    public void test_interactorType_updated_if_unknown() throws EnricherException {

        persistentGene = new DefaultGene(TEST_OLD_SHORTNAME, TEST_OLD_FULLNAME);
        persistentGene.setEnsembl(TEST_AC_HALF_GENE);
        persistentGene.setInteractorType(CvTermUtils.createUnknownInteractorType());

        assertEquals(Gene.UNKNOWN_INTERACTOR_MI, persistentGene.getInteractorType().getMIIdentifier());
        assertEquals(Gene.UNKNOWN_INTERACTOR, persistentGene.getInteractorType().getShortName());

        geneEnricher.setListener(new GeneEnricherListenerManager(new GeneEnricherLogger(),  //Comment this line to silence logging
                new GeneEnricherListener() {
                    public void onEnrichmentComplete(Gene gene, EnrichmentStatus status, String message) {
                        assertTrue(gene == persistentGene);
                        assertEquals(EnrichmentStatus.SUCCESS, status);
                        persistentInt++;
                    }

                    public void onGeneRemapped(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onUniprotKbUpdate(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onRefseqUpdate(Gene gene, String oldRefseq) {
                        fail("Should not reach this point");
                    }

                    public void onGeneNameUpdate(Gene gene, String oldGeneName) {
                        fail("Should not reach this point");
                    }

                    public void onRogidUpdate(Gene gene, String oldRogid) {
                        fail("Should not reach this point");
                    }

                    public void onSequenceUpdate(Gene gene, String oldSequence) {
                        fail("Should not reach this point");
                    }

                    public void onShortNameUpdate(Gene gene, String oldShortName) {
                        fail("Should not reach this point");
                    }

                    public void onFullNameUpdate(Gene gene, String oldFullName) {
                        fail("Should not reach this point");
                    }

                    public void onOrganismUpdate(Gene interactor, Organism oldOrganism) {
                        fail();
                    }

                    public void onInteractorTypeUpdate(Gene interactor, CvTerm oldType) {
                        assertTrue(interactor == persistentGene);
                    }

                    public void onAddedIdentifier(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedIdentifier(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedXref(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedXref(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAlias(Gene gene, Alias added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedAlias(Gene gene, Alias removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedChecksum(Gene gene, Checksum added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedChecksum(Gene gene, Checksum removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAnnotation(Gene o, Annotation added) {
                        Assert.fail();
                    }

                    public void onRemovedAnnotation(Gene o, Annotation removed) {
                        Assert.fail();
                    }

                    public void onEnrichmentError(Gene object, String message, Exception e) {
                        Assert.fail();
                    }
                }));

        geneEnricher.enrich(persistentGene);

        assertEquals(Gene.UNKNOWN_INTERACTOR, persistentGene.getInteractorType().getShortName());
        assertEquals(Gene.UNKNOWN_INTERACTOR_MI, persistentGene.getInteractorType().getMIIdentifier());
        assertEquals(1, persistentInt);
    }

    // == SHORT NAME ===================================================================================

    /**
     * Enrich a gene that has a full name.
     * Check the full name has not been added
     */
    @Test
    public void test_shortName_not_enriched_if_not_null() throws EnricherException {
        persistentInt = 0;
        persistentGene = new DefaultGene(TEST_OLD_SHORTNAME);
        persistentGene.setEnsembl(TEST_AC_CUSTOM_GENE);

        Gene customGene = new DefaultGene(TEST_SHORTNAME);
        customGene.setEnsembl(TEST_AC_CUSTOM_GENE);
        Collection<Gene> customList = new ArrayList<Gene>();
        customList.add(customGene);
        mockEnsemblGeneFetcher.addEntry(TEST_AC_CUSTOM_GENE, customList);

        assertNotNull(persistentGene.getShortName());
        assertEquals(TEST_OLD_SHORTNAME, persistentGene.getShortName());

        geneEnricher.setListener(new GeneEnricherListenerManager(new GeneEnricherLogger(),  //Comment this line to silence logging
                new GeneEnricherListener() {
                    public void onEnrichmentComplete(Gene gene, EnrichmentStatus status, String message) {
                        assertTrue(gene == persistentGene);
                        assertEquals(EnrichmentStatus.SUCCESS, status);
                        persistentInt++;
                    }

                    public void onGeneRemapped(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onUniprotKbUpdate(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onRefseqUpdate(Gene gene, String oldRefseq) {
                        fail("Should not reach this point");
                    }

                    public void onGeneNameUpdate(Gene gene, String oldGeneName) {
                        fail("Should not reach this point");
                    }

                    public void onRogidUpdate(Gene gene, String oldRogid) {
                        fail("Should not reach this point");
                    }

                    public void onSequenceUpdate(Gene gene, String oldSequence) {
                        fail("Should not reach this point");
                    }

                    public void onShortNameUpdate(Gene gene, String oldShortName) {
                        fail("Should not reach this point");
                    }

                    public void onFullNameUpdate(Gene gene, String oldFullName) {
                        fail("Should not reach this point");
                    }

                    public void onOrganismUpdate(Gene interactor, Organism oldOrganism) {
                        fail();
                    }

                    public void onInteractorTypeUpdate(Gene interactor, CvTerm oldType) {
                        fail();
                    }

                    public void onAddedIdentifier(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedIdentifier(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedXref(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedXref(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAlias(Gene gene, Alias added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedAlias(Gene gene, Alias removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedChecksum(Gene gene, Checksum added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedChecksum(Gene gene, Checksum removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAnnotation(Gene o, Annotation added) {
                        Assert.fail();
                    }

                    public void onRemovedAnnotation(Gene o, Annotation removed) {
                        Assert.fail();
                    }

                    public void onEnrichmentError(Gene object, String message, Exception e) {
                        Assert.fail();
                    }
                }));

        this.geneEnricher.enrich(persistentGene);

        assertEquals(TEST_OLD_SHORTNAME, persistentGene.getShortName());
        assertEquals(1, persistentInt);
    }

    // == FULL NAME ===================================================================================

    /**
     * Enrich a gene that has no full name.
     * Check the full name has been added
     */
    @Test
    public void test_fullName_enriched_if_null() throws EnricherException {
        persistentGene = new DefaultGene(TEST_OLD_SHORTNAME);
        persistentGene.setEnsembl(TEST_AC_CUSTOM_GENE);

        Gene customGene = new DefaultGene(TEST_SHORTNAME, TEST_FULLNAME);
        customGene.setEnsembl(TEST_AC_CUSTOM_GENE);

        Collection<Gene> customList = new ArrayList<Gene>();
        customList.add(customGene);
        mockEnsemblGeneFetcher.addEntry(TEST_AC_CUSTOM_GENE, customList);

        assertNull(persistentGene.getFullName());

        geneEnricher.setListener(new GeneEnricherListenerManager(new GeneEnricherLogger(),  //Comment this line to silence logging
                new GeneEnricherListener() {
                    public void onEnrichmentComplete(Gene gene, EnrichmentStatus status, String message) {
                        assertTrue(gene == persistentGene);
                        assertEquals(EnrichmentStatus.SUCCESS, status);
                        persistentInt++;
                    }

                    public void onGeneRemapped(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onUniprotKbUpdate(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onRefseqUpdate(Gene gene, String oldRefseq) {
                        fail("Should not reach this point");
                    }

                    public void onGeneNameUpdate(Gene gene, String oldGeneName) {
                        fail("Should not reach this point");
                    }

                    public void onRogidUpdate(Gene gene, String oldRogid) {
                        fail("Should not reach this point");
                    }

                    public void onSequenceUpdate(Gene gene, String oldSequence) {
                        fail("Should not reach this point");
                    }

                    public void onShortNameUpdate(Gene gene, String oldShortName) {
                        fail("Should not reach this point");
                    }

                    public void onFullNameUpdate(Gene gene, String oldFullName) {
                        assertTrue(gene == persistentGene);
                        assertNull(oldFullName);
                        assertEquals(TEST_FULLNAME, gene.getFullName());
                    }

                    public void onOrganismUpdate(Gene interactor, Organism oldOrganism) {
                        fail();
                    }

                    public void onInteractorTypeUpdate(Gene interactor, CvTerm oldType) {
                        fail();
                    }

                    public void onAddedIdentifier(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedIdentifier(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedXref(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedXref(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAlias(Gene gene, Alias added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedAlias(Gene gene, Alias removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedChecksum(Gene gene, Checksum added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedChecksum(Gene gene, Checksum removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAnnotation(Gene o, Annotation added) {
                        Assert.fail();
                    }

                    public void onRemovedAnnotation(Gene o, Annotation removed) {
                        Assert.fail();
                    }

                    public void onEnrichmentError(Gene object, String message, Exception e) {
                        Assert.fail();
                    }
                }));

        this.geneEnricher.enrich(persistentGene);

        assertEquals(TEST_FULLNAME, persistentGene.getFullName());
        assertEquals(1, persistentInt);
    }

    /**
     * Enrich a gene that has a full name.
     * Check the full name has not been added
     */
    @Test
    public void test_fullName_not_enriched_if_not_null() throws EnricherException {
        persistentInt = 0;

        persistentGene = new DefaultGene(TEST_OLD_SHORTNAME, TEST_OLD_FULLNAME);
        persistentGene.setEnsembl(TEST_AC_CUSTOM_GENE);

        Gene customGene = new DefaultGene(TEST_SHORTNAME, TEST_FULLNAME);
        customGene.setEnsembl(TEST_AC_CUSTOM_GENE);
        Collection<Gene> customList = new ArrayList<Gene>();
        customList.add(customGene);
        mockEnsemblGeneFetcher.addEntry(TEST_AC_CUSTOM_GENE, customList);

        assertNotNull(persistentGene.getFullName());
        assertEquals(TEST_OLD_FULLNAME, persistentGene.getFullName());

        geneEnricher.setListener(new GeneEnricherListenerManager(new GeneEnricherLogger(),  //Comment this line to silence logging
                new GeneEnricherListener() {
                    public void onEnrichmentComplete(Gene gene, EnrichmentStatus status, String message) {
                        assertTrue(gene == persistentGene);
                        assertEquals(EnrichmentStatus.SUCCESS, status);
                        persistentInt++;
                    }

                    public void onGeneRemapped(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onUniprotKbUpdate(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onRefseqUpdate(Gene gene, String oldRefseq) {
                        fail("Should not reach this point");
                    }

                    public void onGeneNameUpdate(Gene gene, String oldGeneName) {
                        fail("Should not reach this point");
                    }

                    public void onRogidUpdate(Gene gene, String oldRogid) {
                        fail("Should not reach this point");
                    }

                    public void onSequenceUpdate(Gene gene, String oldSequence) {
                        fail("Should not reach this point");
                    }

                    public void onShortNameUpdate(Gene gene, String oldShortName) {
                        fail("Should not reach this point");
                    }

                    public void onFullNameUpdate(Gene gene, String oldFullName) {
                        fail("Should not reach this point");
                    }

                    public void onOrganismUpdate(Gene interactor, Organism oldOrganism) {
                        fail();
                    }

                    public void onInteractorTypeUpdate(Gene interactor, CvTerm oldType) {
                        fail();
                    }

                    public void onAddedIdentifier(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedIdentifier(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedXref(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedXref(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAlias(Gene gene, Alias added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedAlias(Gene gene, Alias removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedChecksum(Gene gene, Checksum added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedChecksum(Gene gene, Checksum removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAnnotation(Gene o, Annotation added) {
                        Assert.fail();
                    }

                    public void onRemovedAnnotation(Gene o, Annotation removed) {
                        Assert.fail();
                    }

                    public void onEnrichmentError(Gene object, String message, Exception e) {
                        Assert.fail();
                    }
                }));

        this.geneEnricher.enrich(persistentGene);

        assertEquals(TEST_OLD_FULLNAME, persistentGene.getFullName());
        assertEquals(1, persistentInt);
    }

    // == IDENTIFIERS ===================================================================================

    /**
     * Enrich a gene that has no sequence.
     * Check the sequence has been added
     */
    @Test
    public void test_identifiers_enriched() throws EnricherException {

        persistentGene = new DefaultGene(TEST_OLD_SHORTNAME);
        persistentGene.setEnsembl(TEST_AC_CUSTOM_GENE);
        persistentGene.getIdentifiers().add(new DefaultXref(CvTermUtils.createEnsemblDatabase(), "EN000"));

        Gene customGene = new DefaultGene(TEST_SHORTNAME);
        customGene.setEnsembl(TEST_AC_CUSTOM_GENE);
        customGene.getIdentifiers().add(new DefaultXref(CvTermUtils.createEnsemblDatabase(), "EN999"));

        Collection<Gene> customList = new ArrayList<Gene>();
        customList.add(customGene);
        mockEnsemblGeneFetcher.addEntry(TEST_AC_CUSTOM_GENE, customList);


        assertEquals(2, persistentGene.getIdentifiers().size());

        geneEnricher.setListener(new GeneEnricherListenerManager(new GeneEnricherLogger(),  //Comment this line to silence logging
                new GeneEnricherListener() {
                    public void onEnrichmentComplete(Gene gene, EnrichmentStatus status, String message) {
                        assertTrue(gene == persistentGene);
                        assertEquals(EnrichmentStatus.SUCCESS, status);
                        persistentInt++;
                    }

                    public void onGeneRemapped(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onUniprotKbUpdate(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onRefseqUpdate(Gene gene, String oldRefseq) {
                        fail("Should not reach this point");
                    }

                    public void onGeneNameUpdate(Gene gene, String oldGeneName) {
                        fail("Should not reach this point");
                    }

                    public void onRogidUpdate(Gene gene, String oldRogid) {
                        fail("Should not reach this point");
                    }

                    public void onSequenceUpdate(Gene gene, String oldSequence) {
                        fail("Should not reach this point");
                    }

                    public void onShortNameUpdate(Gene gene, String oldShortName) {
                        fail("Should not reach this point");
                    }

                    public void onFullNameUpdate(Gene gene, String oldFullName) {
                        fail("Should not reach this point");
                    }

                    public void onOrganismUpdate(Gene interactor, Organism oldOrganism) {
                        fail();
                    }

                    public void onInteractorTypeUpdate(Gene interactor, CvTerm oldType) {
                        fail();
                    }

                    public void onAddedIdentifier(Gene gene, Xref added) {
                        assertTrue(gene == persistentGene);
                        assertNotNull(added);
                        assertNotNull(added.getId());
                        assertEquals("EN999", added.getId());
                    }

                    public void onRemovedIdentifier(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedXref(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedXref(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAlias(Gene gene, Alias added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedAlias(Gene gene, Alias removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedChecksum(Gene gene, Checksum added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedChecksum(Gene gene, Checksum removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAnnotation(Gene o, Annotation added) {
                        Assert.fail();
                    }

                    public void onRemovedAnnotation(Gene o, Annotation removed) {
                        Assert.fail();
                    }

                    public void onEnrichmentError(Gene object, String message, Exception e) {
                        Assert.fail();
                    }
                }));

        this.geneEnricher.enrich(persistentGene);

        assertEquals(3, persistentGene.getIdentifiers().size());

        boolean originalXref = false;
        boolean newXref = false;
        boolean idXref = false;
        for (Xref xref : persistentGene.getIdentifiers()) {
            if (xref.getId().equals("EN000")) if (originalXref) fail("multiples of the original id");
            else originalXref = true;
            else if (xref.getId().equals("EN999")) if (newXref) fail("multiples of the new id");
            else newXref = true;
            else if (xref.getId().equals(TEST_AC_CUSTOM_GENE)) if (idXref) fail("multiples of the uniprot id");
            else idXref = true;
            else fail(xref + "unrecognised alias");
        }

        assertTrue(originalXref);
        assertTrue(newXref);
        assertTrue(idXref);

        assertEquals(1, persistentInt);
    }

    // == ALIASES ===================================================================================

    /**
     * Enrich a gene that has no sequence.
     * Check the sequence has been added
     */
    @Test
    public void test_aliases_enriched() throws EnricherException {

        persistentGene = new DefaultGene(TEST_OLD_SHORTNAME);
        persistentGene.setEnsembl(TEST_AC_CUSTOM_GENE);
        persistentGene.getAliases().add(new DefaultAlias(CvTermUtils.createEnsemblDatabase(), "EN000"));

        Gene customGene = new DefaultGene(TEST_SHORTNAME);
        customGene.setEnsembl(TEST_AC_CUSTOM_GENE);
        customGene.getAliases().add(new DefaultAlias(CvTermUtils.createEnsemblDatabase(), "EN999"));

        Collection<Gene> customList = new ArrayList<Gene>();
        customList.add(customGene);
        mockEnsemblGeneFetcher.addEntry(TEST_AC_CUSTOM_GENE, customList);

        assertEquals(1, persistentGene.getAliases().size());

        geneEnricher.setListener(new GeneEnricherListenerManager(new GeneEnricherLogger(),  //Comment this line to silence logging
                new GeneEnricherListener() {
                    public void onEnrichmentComplete(Gene gene, EnrichmentStatus status, String message) {
                        assertTrue(gene == persistentGene);
                        assertEquals(EnrichmentStatus.SUCCESS, status);
                        persistentInt++;
                    }

                    public void onGeneRemapped(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onUniprotKbUpdate(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onRefseqUpdate(Gene gene, String oldRefseq) {
                        fail("Should not reach this point");
                    }

                    public void onGeneNameUpdate(Gene gene, String oldGeneName) {
                        fail("Should not reach this point");
                    }

                    public void onRogidUpdate(Gene gene, String oldRogid) {
                        fail("Should not reach this point");
                    }

                    public void onSequenceUpdate(Gene gene, String oldSequence) {
                        fail("Should not reach this point");
                    }

                    public void onShortNameUpdate(Gene gene, String oldShortName) {
                        fail("Should not reach this point");
                    }

                    public void onFullNameUpdate(Gene gene, String oldFullName) {
                        fail("Should not reach this point");
                    }

                    public void onOrganismUpdate(Gene interactor, Organism oldOrganism) {
                        fail();
                    }

                    public void onInteractorTypeUpdate(Gene interactor, CvTerm oldType) {
                        fail();
                    }

                    public void onAddedIdentifier(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedIdentifier(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedXref(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedXref(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAlias(Gene gene, Alias added) {
                        assertTrue(gene == persistentGene);
                        assertNotNull(added);
                        assertNotNull(added.getName());
                        assertEquals("EN999", added.getName());
                    }

                    public void onRemovedAlias(Gene gene, Alias removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedChecksum(Gene gene, Checksum added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedChecksum(Gene gene, Checksum removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAnnotation(Gene o, Annotation added) {
                        Assert.fail();
                    }

                    public void onRemovedAnnotation(Gene o, Annotation removed) {
                        Assert.fail();
                    }

                    public void onEnrichmentError(Gene object, String message, Exception e) {
                        Assert.fail();
                    }
                }));

        this.geneEnricher.enrich(persistentGene);

        assertEquals(2, persistentGene.getAliases().size());

        boolean originalAlias = false;
        boolean newAlias = false;
        for (Alias alias : persistentGene.getAliases()) {
            if (alias.getName().equals("EN000")) if (originalAlias) fail("multiples of the original alias");
            else originalAlias = true;
            else if (alias.getName().equals("EN999")) if (newAlias) fail("multiples of the new alias");
            else newAlias = true;
            else fail("unrecognised alias");
        }

        assertTrue(originalAlias);
        assertTrue(newAlias);

        assertEquals(1, persistentInt);
    }

    // == CHECKSUMS ===================================================================================
    //TODO - checksums logic is still lacking, check logic carefully before testing!!


    // == XREFS ===================================================================================

    /**
     * Enrich a gene that has no sequence.
     * Check the sequence has been added
     */
    @Test
    public void test_xrefs_enriched() throws EnricherException {

        persistentGene = new DefaultGene(TEST_OLD_SHORTNAME);
        persistentGene.setEnsembl(TEST_AC_CUSTOM_GENE);
        persistentGene.getXrefs().add(new DefaultXref(CvTermUtils.createEnsemblDatabase(), "EN000"));

        Gene customGene = new DefaultGene(TEST_SHORTNAME);
        customGene.setEnsembl(TEST_AC_CUSTOM_GENE);
        customGene.getXrefs().add(new DefaultXref(CvTermUtils.createEnsemblDatabase(), "EN999"));

        Collection<Gene> customList = new ArrayList<Gene>();
        customList.add(customGene);
        mockEnsemblGeneFetcher.addEntry(TEST_AC_CUSTOM_GENE, customList);

        assertEquals(1, persistentGene.getXrefs().size());

        geneEnricher.setListener(new GeneEnricherListenerManager(new GeneEnricherLogger(),  //Comment this line to silence logging
                new GeneEnricherListener() {
                    public void onEnrichmentComplete(Gene gene, EnrichmentStatus status, String message) {
                        assertTrue(gene == persistentGene);
                        assertEquals(EnrichmentStatus.SUCCESS, status);
                        persistentInt++;
                    }

                    public void onGeneRemapped(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onUniprotKbUpdate(Gene gene, String oldUniprot) {
                        fail("Should not reach this point");
                    }

                    public void onRefseqUpdate(Gene gene, String oldRefseq) {
                        fail("Should not reach this point");
                    }

                    public void onGeneNameUpdate(Gene gene, String oldGeneName) {
                        fail("Should not reach this point");
                    }

                    public void onRogidUpdate(Gene gene, String oldRogid) {
                        fail("Should not reach this point");
                    }

                    public void onSequenceUpdate(Gene gene, String oldSequence) {
                        fail("Should not reach this point");
                    }

                    public void onShortNameUpdate(Gene gene, String oldShortName) {
                        fail("Should not reach this point");
                    }

                    public void onFullNameUpdate(Gene gene, String oldFullName) {
                        fail("Should not reach this point");
                    }

                    public void onOrganismUpdate(Gene interactor, Organism oldOrganism) {
                        fail();
                    }

                    public void onInteractorTypeUpdate(Gene interactor, CvTerm oldType) {
                        fail();
                    }

                    public void onAddedIdentifier(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedIdentifier(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedXref(Gene gene, Xref added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedXref(Gene gene, Xref removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAlias(Gene gene, Alias added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedAlias(Gene gene, Alias removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedChecksum(Gene gene, Checksum added) {
                        fail("Should not reach this point");
                    }

                    public void onRemovedChecksum(Gene gene, Checksum removed) {
                        fail("Should not reach this point");
                    }

                    public void onAddedAnnotation(Gene o, Annotation added) {
                        Assert.fail();
                    }

                    public void onRemovedAnnotation(Gene o, Annotation removed) {
                        Assert.fail();
                    }

                    public void onEnrichmentError(Gene object, String message, Exception e) {
                        Assert.fail();
                    }
                }));

        this.geneEnricher.enrich(persistentGene);

        assertEquals(1, persistentGene.getXrefs().size());

        boolean originalXref = false;
        for (Xref xref : persistentGene.getXrefs()) {
            if (xref.getId().equals("EN000")) if (originalXref) fail("multiples of the original xref");
            else originalXref = true;
            else fail(xref + "unrecognised alias");
        }

        assertTrue(originalXref);

        assertEquals(1, persistentInt);
    }


    // == ORGANISM ===================================================================================

    @Test
    public void test_set_organism_if_null() throws EnricherException {
        Gene gene_without_organism = new DefaultGene(TEST_SHORTNAME);
        gene_without_organism.setEnsembl(TEST_AC_HALF_GENE);
        assertNull(gene_without_organism.getOrganism());

        this.geneEnricher.enrich(gene_without_organism);

        assertNull(gene_without_organism.getOrganism());
    }

    // TODO  onRefseqUpdate, onGeneNameUpdate

    // == Fetcher ====================================================================================
    @Test
    public void test_correct_fetcher_used() throws EnricherException {
        Gene gene_ref_seq = new DefaultGene(TEST_SHORTNAME_REF_SEQ);
        gene_ref_seq.setRefseq(TEST_AC_REF_SEQ);
        assertNull(gene_ref_seq.getFullName());

        this.geneEnricher.enrich(gene_ref_seq);

        assertTrue(gene_ref_seq.getFullName().endsWith("RefSeq"));

        Gene gene_ensembl = new DefaultGene(TEST_SHORTNAME);
        gene_ensembl.setEnsembl(TEST_AC_FULL_GENE);
        assertNull(gene_ensembl.getFullName());

        this.geneEnricher.enrich(gene_ensembl);

        assertFalse(gene_ensembl.getFullName().endsWith("RefSeq"));
    }



}

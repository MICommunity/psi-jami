package psidev.psi.mi.jami.enricher.impl;

import org.junit.Before;
import org.junit.Test;
import psidev.psi.mi.jami.bridges.fetcher.mock.MockNucleicAcidFetcher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.impl.full.FullNucleicAcidEnricher;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.NucleicAcid;
import psidev.psi.mi.jami.model.impl.DefaultNucleicAcid;
import psidev.psi.mi.jami.model.impl.DefaultOrganism;
import psidev.psi.mi.jami.model.impl.DefaultXref;
import psidev.psi.mi.jami.utils.CvTermUtils;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * Unit tests for MinimalProteinEnricher
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 23/05/13
 */

public class FullNucleicAcidEnricherTest {

    private FullNucleicAcidEnricher enricher;
    private MockNucleicAcidFetcher mockNucleicAcidFetcher;
    private MockNucleicAcidFetcher mockEnsemblNucleicAcidFetcher;

    private static final String TEST_SHORTNAME = "test shortName";
    private static final String TEST_FULLNAME = "test fullName";
    private static final String TEST_FULLNAME_ENSEMBL = "test fullName ENSEMBL";
    private static final String TEST_AC_FULL = "URS000055C986_4896";
    private static final String TEST_AC_ENSEMBL = "ENST00000652534";
    private static final String TEST_SEQUENCE = "GATTACA";
    private static final int TEST_ORGANISM_ID = 1234;
    private static final String TEST_ORGANISM_COMMON = "Common";
    private static final String TEST_ORGANISM_SCIENTIFIC = "Scientific";
    private DefaultXref rnaCentralId;
    private DefaultXref ensemblId;

    @Before
    public void initialiseFetcherAndEnricher() {
        mockNucleicAcidFetcher = new MockNucleicAcidFetcher();
        mockEnsemblNucleicAcidFetcher = new MockNucleicAcidFetcher();

        enricher = new FullNucleicAcidEnricher(mockNucleicAcidFetcher);
        enricher.setEnsemblFetcher(mockEnsemblNucleicAcidFetcher);

        CvTerm identity = CvTermUtils.createIdentityQualifier();
        CvTerm rnaCentral = CvTermUtils.createRNACentralDatabase();


        NucleicAcid fullNucleicAcid = new DefaultNucleicAcid(TEST_SHORTNAME, TEST_FULLNAME);
        rnaCentralId = new DefaultXref(rnaCentral, TEST_AC_FULL, identity);
        fullNucleicAcid.getIdentifiers().add(rnaCentralId);
        fullNucleicAcid.setSequence(TEST_SEQUENCE);
        fullNucleicAcid.setOrganism(new DefaultOrganism(TEST_ORGANISM_ID, TEST_ORGANISM_COMMON, TEST_ORGANISM_SCIENTIFIC));
        mockNucleicAcidFetcher.addEntry(TEST_AC_FULL, List.of(fullNucleicAcid));

        NucleicAcid ensemblTranscript = new DefaultNucleicAcid(TEST_SHORTNAME, TEST_FULLNAME_ENSEMBL);
        CvTerm ensembl = CvTermUtils.createEnsemblDatabase();
        ensemblId = new DefaultXref(ensembl, TEST_AC_ENSEMBL, identity);
        ensemblTranscript.getIdentifiers().add(ensemblId);
        mockEnsemblNucleicAcidFetcher.addEntry(TEST_AC_ENSEMBL, List.of(ensemblTranscript));
    }

    @Test
    public void test_correct_fetcher_used() throws EnricherException {
        NucleicAcid rnaCentral = new DefaultNucleicAcid(TEST_SHORTNAME);
        rnaCentral.getIdentifiers().add(rnaCentralId);
        assertNull(rnaCentral.getFullName());

        this.enricher.enrich(rnaCentral);

        assertFalse(rnaCentral.getFullName().endsWith("ENSEMBL"));

        NucleicAcid ensembl = new DefaultNucleicAcid(TEST_SHORTNAME);
        ensembl.getIdentifiers().add(ensemblId);
        assertNull(ensembl.getFullName());

        this.enricher.enrich(ensembl);

        assertTrue(ensembl.getFullName().endsWith("ENSEMBL"));
    }

    @Test
    public void test_sequence_added() throws EnricherException {
        NucleicAcid rnaCentral = new DefaultNucleicAcid(TEST_SHORTNAME);
        rnaCentral.getIdentifiers().add(rnaCentralId);
        assertNull(rnaCentral.getSequence());

        this.enricher.enrich(rnaCentral);

        assertEquals(TEST_SEQUENCE, rnaCentral.getSequence());
    }

}

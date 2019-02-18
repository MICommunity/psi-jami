package psidev.psi.mi.jami.bridges.uniprot.rest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import psidev.psi.mi.jami.bridges.uniprot.rest.response.model.Entry;
import uk.ac.ebi.kraken.interfaces.uniparc.UniParcEntry;
import uk.ac.ebi.kraken.interfaces.uniprot.UniProtEntry;

import java.util.List;

public class UniprotProteinAPIClientTest {

    private UniprotProteinAPIClient client;

    @Before
    public void before() {
        client = new UniprotProteinAPIClient();
    }

    @After
    public void after() {
        client = null;
    }

    @Test
    public void getSwissprotIdsForAccession() throws UniprotProteinAPIClientException {
        List<String> upis = client.getSwissprotIdsForAccession("NP_417804", null);

        for (String upi : upis) {
            System.out.println(upi);
        }
        Assert.assertEquals(4, upis.size());

    }

    @Test
    public void getTremblIdsForAccession() throws UniprotProteinAPIClientException {
        List<String> upis = client.getTremblIdsForAccession("NP_417804", null);

        for (String upi : upis) {
            System.out.println(upi);
        }
        Assert.assertEquals(23, upis.size());

    }

    @Test
    public void getUniparcEntries() throws UniprotProteinAPIClientException {
        List<Entry> upis = client.getUniparcEntries("NP_417804", null);

        for (Entry upi : upis) {
            System.out.println(upi.getAccession());
        }
        Assert.assertEquals(1, upis.size());
        Assert.assertEquals("UPI000003EADC", upis.get(0).getAccession());

    }

    @Test
    public void getUPEntriesForAccession() throws UniprotProteinAPIClientException {
        List<Entry> upis = client.getUPEntriesForAccession("NP_417804", null);

        for (Entry upi : upis) {
            System.out.println(upi.getAccession());
        }
        Assert.assertEquals(1, upis.size());
        Assert.assertEquals("UPI000003EADC", upis.get(0).getAccession());


    }

    @Test
    public void getUniprotEntryForAccession() {
        List<UniProtEntry> uniprotEntries = client.getUniprotEntryForAccession("P45532");

        for (UniProtEntry upi : uniprotEntries) {
            System.out.println(upi.getPrimaryUniProtAccession().getValue());
        }
        Assert.assertEquals(1, uniprotEntries.size());
        Assert.assertEquals("P45532", uniprotEntries.get(0).getPrimaryUniProtAccession().getValue());
    }

    @Test
    public void getUniparcEntryForAccession() {
        List<UniParcEntry> uniparcEntries = client.getUniparcEntryForAccession("P45532");

        for (UniParcEntry upi : uniparcEntries) {
            System.out.println(upi.getUniParcId().getValue());
        }
        Assert.assertEquals(1, uniparcEntries.size());
        Assert.assertEquals("UPI000003EADC", uniparcEntries.get(0).getUniParcId().getValue());

    }

    @Test
    public void getSwissprotIdsForSequence() throws UniprotProteinAPIClientException {
        List<String> upis;

        upis = client.getSwissprotIdsForSequence("MRFAIVVTGPAYGTQQASSAFQFAQALIADGHELSSVFFYREGVYNANQLTS" +
                "PASDEFDLVRAWQQLNAQHGVALNICVAAALRRGVVDETEAGRLGLASSNLQQGFTLSGLGALAEASLTCDRVVQF", null);
        for (String upi : upis) {
            System.out.println(upi);
        }
        Assert.assertEquals(4, upis.size());
    }

    @Test
    public void getSwissprotIdsForSequenceAndSpecialOrganism() throws UniprotProteinAPIClientException {
        List<String> upis;

        upis = client.getSwissprotIdsForSequence("YIYTQ", "-2");
        for (String upi : upis) {
            System.out.println(upi);
        }
        Assert.assertEquals(1, upis.size());
    }

    @Test
    public void getTremblIdsForSequence() throws UniprotProteinAPIClientException {
        List<String> upis = null;

        upis = client.getTremblIdsForSequence("MRFAIVVTGPAYGTQQASSAFQFAQALIADGHELSSVFFYREGVYNANQLTS" +
                "PASDEFDLVRAWQQLNAQHGVALNICVAAALRRGVVDETEAGRLGLASSNLQQGFTLSGLGALAEASLTCDRVVQF", null);
        for (String upi : upis) {
            System.out.println(upi);
        }
        Assert.assertEquals(23, upis.size());

    }

    @Test
    public void getUniparcIdFromSequence() throws UniprotProteinAPIClientException {

        String upi = client.getUniparcIdFromSequence("MRFAIVVTGPAYGTQQASSAFQFAQALIADGHELSSVFFYREGVYNANQLTS" +
                "PASDEFDLVRAWQQLNAQHGVALNICVAAALRRGVVDETEAGRLGLASSNLQQGFTLSGLGALAEASLTCDRVVQF", null);
        System.out.println(upi);
        Assert.assertEquals("UPI000003EADC", upi);

    }

    @Test
    public void getUPEntriesForSequence() throws UniprotProteinAPIClientException {

        Entry upi = client.getUPEntriesForSequence("MRFAIVVTGPAYGTQQASSAFQFAQALIADGHELSSVFFYREGVYNANQLTS" +
                "PASDEFDLVRAWQQLNAQHGVALNICVAAALRRGVVDETEAGRLGLASSNLQQGFTLSGLGALAEASLTCDRVVQF",
                null, SearchDatabase.SWISSPROT, SearchDatabase.TREMBL);

        System.out.println(upi.getAccession());
        Assert.assertNotNull(upi);
        Assert.assertEquals("UPI000003EADC", upi.getAccession());
        Assert.assertEquals(27, upi.getDbReference().size());

    }


    @Test
    public void getUniprotBestGuessFor() throws UniprotProteinAPIClientException {
        //Uniprot AC
        String[] result = client.getUniprotBestGuessFor("P45532", "83333");

        System.out.println("database : " + result[0]);
        System.out.println("accession : " + result[1]);

        Assert.assertEquals("SWISSPROT", result[0]);
        Assert.assertEquals("P45532", result[1]);

        //Uniprot Isoform AC
        result = client.getUniprotBestGuessFor("ENSP00000292807", "9606");
        System.out.println("database : " + result[0]);
        System.out.println("accession : " + result[1]);

        Assert.assertEquals("SWISSPROT_VARSPLIC", result[0]);
        Assert.assertEquals("Q96CW1-1", result[1]);

        //Uniprot Isoform AC that is the canonical
        result = client.getUniprotBestGuessFor("ENSP00000409081", "9606");
        System.out.println("database : " + result[0]);
        System.out.println("accession : " + result[1]);

        Assert.assertEquals("SWISSPROT_VARSPLIC", result[0]);
        Assert.assertEquals("Q96CW1-2", result[1]);


        result = client.getUniprotBestGuessFor("IPI00022256", "9606");

        System.out.println("database : " + result[0]);
        System.out.println("accession : " + result[1]);

        Assert.assertEquals("SWISSPROT_VARSPLIC", result[0]);
        Assert.assertEquals("Q96CW1-1", result[1]);
    }


    @Test(expected = UniprotProteinAPIClientException.class)
    public void getUniprotBestGuessForSeveralMappingsException() throws UniprotProteinAPIClientException {
        client.getUniprotBestGuessFor("IPI00022256", null);
    }

    @Test
    public void getUniprotBestGuessForNoResult() throws UniprotProteinAPIClientException {
        //Uniprot AC
        String[] result = client.getUniprotBestGuessFor("ENSG00000139618", null);
        Assert.assertNull(result);
    }
}
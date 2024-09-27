package psidev.psi.mi.jami.bridges.unisave;

import org.junit.Assert;
import org.junit.Test;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * UnisaveService Tester.
 *
 * @author Samuel Kerrien (skerrien@ebi.ac.uk)
 * @version $Id: UnisaveServiceTest.java 20759 2014-09-11 10:05:52Z oscar.forner.martinez@gmail.com $
 * @since 2.0.3
 */
public class UnisaveClientTest {


    /*
         Primary (citable) accession number: Q96IZ0
         Secondary accession number(s): O75796, Q6FHY9, Q8N700
                         isSecondary  isNotSecondary
         primary id          ex            49       (Q96IZ0)
         secondary id        36            18       (Q6FHY9)
     */

    @Test
    public void getVersions_2() throws Exception {
        UnisaveClient service = new UnisaveClient();
        final List<Integer> versions = service.getVersions("Q98753");
        Assert.assertNotNull(versions);
    }


    @Test
    public void getFastaSequence_1() throws Exception {
        UnisaveClient service = new UnisaveClient();
        String id = "Q98753";
        service.getFastaSequence(id, 7);
    }

    @Test
    public void getFastaSequence_2() throws Exception {
        UnisaveClient service = new UnisaveClient();
        String id = "Q00001";
        service.getFastaSequence(id, 7);
    }

    @Test
    public void getLastSequenceReleased() throws Exception {
        UnisaveClient service = new UnisaveClient();

        String sequence = service.getLastSequenceAtTheDate("Q98753", new Date(System.currentTimeMillis()));

        Assert.assertNotNull(sequence);
        Assert.assertEquals("VPFLSKAVRCGPVIPFVIHHFNFRRVTTTKRRRNKYVLVPGYGWVLQDDYLVNSVKMTGE" +
                "NDLPPNQLPHDDDLLFTYAKILLYDYISYFPKFRHNNPDLLDHKTELELFPLKADSAARN" +
                "KANFYARTLWNDTITDKSAFKPGTYNDTVAGLLLWQQCALMWSLPKSVINRTISGVCDAL" +
                "TNRTSLTLLKRISDWLKQLGLACSPIHRLFIELPTLLGRGAIPGDADKDIKHRLAFDPSI" +
                "TVDVPKEQLHLLIYRLLSRNLNITKVNSFEHHLEERLLWSKSGSHYYPDDKINELLPPQP" +
                "TRKEFLDVVTTEYIKECKPQVFIRQSRKLEHGKERFIYNCDTVSYVYFDFILKLFETGWQ" +
                "DSEAILSPGDYTSERLHAKISSYKYKAMLDYTDFNSQHTIQSMRLIFETMKELLPPEATF" +
                "ALDWCIASFDNMQTSDGLKWMATLPSGHRATTFINTVLNWCYTQMVGLKFDSFMCAGDDV" +
                "ILMSQQPISLAPILTSHFKFNPSKQSTGTRGEFLRKHYSEAGVFAYPCRAIASLVSGNWL" +
                "SQSLRENTPILVPIQNGIDRLRSRAGLLGVPWKLGLSELIEREAIPKEVGMALLNSHAAG" +
                "PGLITRDYSSFTVTPKPPKLSSTLEYTATRYGLQDLSKHVPWKQLTTVESDKLSRQIKKI" +
                "SYRHCSQAKITYNCTYEVFKPRGLPTVLSGSSQPSLSMLWWQAMLKQAIQDDSTKKIDAR" +
                "MFAANACTSSVSGDAFLRANASMAGVLITSLITSSS", sequence);
    }

    @Test
    public void getLastSequenceReleased_P51875() throws Exception {
        UnisaveClient service = new UnisaveClient();

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String sequence = service.getLastSequenceAtTheDate("P51875", format.parse("2007/09/17"));

        Assert.assertNotNull(sequence);
        Assert.assertEquals("MGCTMSQEERAALERSRMIEKNLKEDGMQAAKDIKLLLLGAGESGKSTIVKQMKIIHESGFTAEDYKQYKPVVYSNTVQSLVAILRAMSNLGVSFGSADREVDA" +
                "KLVMDVVARMEDTEPFSEELLSSMKRLWGDAGVQDCFSRSNEYQLNDSAKYFLDDLERLGEAIYQPTEQDILRTRVKTTGIVEVHFTFKNLNFKLFDVGGQRSERKKWIHCFEDVTA" +
                "IIFCVAMSEYDQVLHEDETTNRMHESLKLFDSICNNKWFTDTSIILFLNKKDLFEEKIKKSPLTICFPEYSGRQDYHEASAYIQAQFEAKNKSANKEIYCHMTCATDTTNIQFVFDA" +
                "VTDVIIANNLRGCGLY", sequence);
        Assert.assertEquals(354, sequence.length());

    }

    @Test
    public void getSequenceVersion() {
        String sequence = "MNKLAILAIIAMVLFSANAFRFQSRIRSNVEAKTETRDLCEQSALQCNEQGCHNFCSPEDKPGCLGMVWNPELVP";
        String uniprotAc = "P12350";
        UnisaveClient service = new UnisaveClient();

        try {
            int sequenceVersion = service.getSequenceVersion(uniprotAc, sequence);

            Assert.assertEquals(2, sequenceVersion);
        } catch (BridgeFailedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSequenceForSequenceVersion() {
        String sequence = "MNKLAILAIIAMVLFSANAFRFQSRIRSNVEAKTETRDLCEQSALQCNEQGCHNFCSPEDKPGCLGMVWNPELVP";
        String uniprotAc = "P12350";
        UnisaveClient service = new UnisaveClient();

        try {
            String sequenceFromUnisave = service.getSequenceFor(uniprotAc, 2);

            Assert.assertEquals(sequence, sequenceFromUnisave);
        } catch (BridgeFailedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSequenceVersion_sequence_not_found() {
        String sequence = "MNKLAI";
        String uniprotAc = "P12350";
        UnisaveClient service = new UnisaveClient();

        try {
            int sequenceVersion = service.getSequenceVersion(uniprotAc, sequence);

            Assert.assertEquals(-1, sequenceVersion);
        } catch (BridgeFailedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLastSequenceReleased_P51875_2() throws Exception {
        UnisaveClient service = new UnisaveClient();

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String sequence = service.getLastSequenceAtTheDate("P51875", format.parse("2006/09/01"));

        Assert.assertNotNull(sequence);
        Assert.assertEquals("GCTMSQEERAALERSRMIEKNLKEDGMQAAKDIKLLLLGAGESGKSTIVKQMKIIHESGF" +
                "TAEDYKQYKPVVYSNTVQSLVAILRAMSNLGVSFGSADREVDAKLVMDVVARMEDTEPFS" +
                "EELLSSMKRLWGDAGVQDCFSRSNEYQLNDSAKYFLDDLERLGEAIYQPTEQDILRTRVK" +
                "TTGIVEVHFTFKNLNFKLFDVGGQRSERKKWIHCFEDVTAIIFCVAMSEYDQVLHEDETT" +
                "NRMHESLKLFDSICNNKWFTDTSIILFLNKKDLFEEKIKKSPLTICFPEYSGRQDYHEAS" +
                "AYIQAQFEAKNKSANKEIYCHMTCATDTTNIQFVFDAVTDVIIANNLRGCGLY", sequence);
        Assert.assertEquals(353, sequence.length());

    }

    @Test
    public void getAllPreviousSequenceReleased_P51875_2() throws Exception {
        UnisaveClient service = new UnisaveClient();

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Map<Integer, String> sequences = service.getAllSequencesBeforeDate("P51875", format.parse("2006/09/01"));

        Assert.assertEquals(2, sequences.size());

    }

    @Test
    public void getAvailableSequenceUpdate_P12345() throws Exception {

        // example of a sequence that doesn't have any update through its history

        UnisaveClient service = new UnisaveClient();
        final List<SequenceVersion> updates = service.getAvailableSequenceUpdate("P12345", "SSWWAHVEMGPPDPILGVTEAYKRDTNSKK");
        Assert.assertNotNull(updates);
        Assert.assertEquals(1, updates.size());
        final SequenceVersion sv = updates.iterator().next();
        Assert.assertNotNull(sv);
        Assert.assertNotNull(sv.getSequence());
        Assert.assertEquals("MALLHSARVLSGVASAFHPGLAAAASARASSWWAHVEMGPPDPILGVTEAYKRDTNSKKMNLGVGAYRDDNGKPYVLPSVRKAEAQIAAKGLDKEYLPIGGLAEFCRASAELALGENSEVVKSGRFVTVQTISGTGALRIGASFLQRFFKFSRDVFLPKPSWGNHTPIFRDAGMQLQSYRYYDPKTCGFDFTGALEDISKIPEQSVLLLHACAHNPTGVDPRPEQWKEIATVVKKRNLFAFFDMAYQGFASGDGDKDAWAVRHFIEQGINVCLCQSYAKNMGLYGERVGAFTVICKDADEAKRVESQLKILIRPMYSNPPIHGARIASTILTSPDLRKQWLQEVKGMADRIIGMRTQLVSNLKKEGSTHSWQHITDQIGMFCFTGLKPEQVERLTKEFSIYMTKDGRISVAGVTSGNVGYLAHAIHQVTK", sv.getSequence().getSequence());
        Assert.assertEquals("sp|P12345|Release 2024_03/2024_03|29-May-2024", sv.getSequence().getHeader());
        Assert.assertEquals(2, sv.getVersion());
    }

    @Test
    public void getAvailableSequenceUpdate_Q98753_version1() throws Exception {

        // example of a sequence that has multiple updates (1 and 2) through its history, we are searching with version 1

        UnisaveClient service = new UnisaveClient();
        final List<SequenceVersion> updates = service.getAvailableSequenceUpdate("Q98753", "XXX");
        Assert.assertNotNull(updates);
        Assert.assertEquals(2, updates.size());
        SequenceVersion sv;
        final Iterator<SequenceVersion> updateIterator = updates.iterator();

        sv = updateIterator.next();
        Assert.assertNotNull(sv);
        Assert.assertNotNull(sv.getSequence());
        Assert.assertEquals("VPFLSKAVRCGPVIPFVIHHFNFRRVTTTKRRRNKYVLVPGYGWVLQDDYLVNSVKMTGENDLPPNQLPHDDDLLFTYAKILLYDYISYFPKFRHNNPDLLDHKTELELFPLKADSAARNKANFYARTLWNDTITDKSAFKPGTYNDTVAGLLLWQQCALMWSLPKSVINRTISGVCDALTNRTSLTLLKRISDWLKQLGLACSPIHRLFIELPTLLGRGAIPGDADKDIKHRLAFDPSITVDVPKEQLHLLIYRLLSRNLNITKVNSFEHHLEERLLWSKSGSHYYPDDKINELLPPQPTRKEFLDVVTTEYIKECKPQVFIRQSRKLEHGKERFIYNCDTVSYVYFDFILKLFETGWQDSEAILSPGDYTSERLHAKISSYKYKAMLDYTDFNSQHTIQSMRLIFETMKELLPPEATFALDWCIASFDNMQTSDGLKWMATLPSGHRATTFINTVLNWCYTQMVGLKFDSFMCAGDDVILMSQQPISLAPILTSHFKFNPSKQSTGTRGEFLRKHYSEAGVFAYPCRAIASLVSGNWLSQSLRENTPILVPIQNGIDRLRSRAGLLGVPWKLGLSELIEREAIPKEVGMALLNSHAAGPGLITRDYSSFTVTPKPPKLSSTLEYTATRYGLQDLSKHVPWKQLTTVESDKLSRQIKKISYRHCSQAKITYNCTYEVFKPRGLPTVLSGSSQPSLSMLWWQAMLKQAIQDDSTKKIDARMFAANACTSSVSGDAFLRANASMAGVLITSLITSSS", sv.getSequence().getSequence());
        Assert.assertEquals("tr|Q98753|Release 2024_02/2024_02|27-Mar-2024", sv.getSequence().getHeader());
        Assert.assertEquals(2, sv.getVersion());

        sv = updateIterator.next();
        Assert.assertNotNull(sv);
        Assert.assertNotNull(sv.getSequence());
        Assert.assertEquals("XPFLSKAVRCGPVIPFVIHHFNFRRVTTTKRRRNKYVLVPGYGWVLQDDYLVNSVKMTGENDLPPNQLPHDDDLLFTYAKILLYDYISYFPKFRHNNPDLLDHKTELELFPLKADSAARNKANFYARTLWNDTITDKSAFKPGTYNDTVAGLLLWQQCALMWSLPKSVINRTISGVCDALTNRTSLTLLKRISDWLKQLGLACSPIHRLFIELPTLLGRGAIPGDADKDIKHRLAFDPSITVDVPKEQLHLLIYRLLSRNLNITKVNSFEHHLEERLLWSKSGSHYYPDDKINELLPPQPTRKEFLDVVTTEYIKECKPQVFIRQSRKLEHGKERFIYNCDTVSYVYFDFILKLFETGWQDSEAILSPGDYTSERLHAKISSYKYKAMLDYTDFNSQHTIQSMRLIFETMKELLPPEATFALDWCIASFDNMQTSDGLKWMATLPSGHRATTFINTVLNWCYTQMVGLKFDSFMCAGDDVILMSQQPISLAPILTSHFKFNPSKQSTGTRGEFLRKHYSEAGVFAYPCRAIASLVSGNWLSQSLRENTPILVPIQNGIDRLRSRAGLLGVPWKLGLSELIEREAIPKEVGMALLNSHAAGPGLITRDYSSFTVTPKPPKLSSTLEYTATRYGLQDLSKHVPWKQLTTVESDKLSRQIKKISYRHCSQAKITYNCTYEVFKPRGLPTVLSGSSQPSLSMLWWQAMLKQAIQDDSTKKIDARMFAANACTSSVSGDAFLRANASMAGVLITSLITSSS", sv.getSequence().getSequence());
        Assert.assertEquals("tr|Q98753|Release 13|01-May-2000", sv.getSequence().getHeader());
        Assert.assertEquals(1, sv.getVersion());

    }

    @Test
    public void getAvailableSequenceUpdate_sequenceMismatch() throws Exception {

        // example of a sequence that doesn't have any update through its history

        UnisaveClient service = new UnisaveClient();
        final List<SequenceVersion> updates = service.getAvailableSequenceUpdate("P12345", "SSWWAHVEMGPPDPILGVTEAYKRDTNSKK");
        Assert.assertNotNull(updates);
        Assert.assertEquals(1, updates.size());
    }
}

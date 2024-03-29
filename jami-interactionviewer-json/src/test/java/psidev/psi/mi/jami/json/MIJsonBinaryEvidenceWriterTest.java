package psidev.psi.mi.jami.json;

import org.junit.Assert;
import org.junit.Test;
import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.impl.DefaultBinaryInteractionEvidence;
import psidev.psi.mi.jami.factory.options.InteractionWriterOptions;
import psidev.psi.mi.jami.exception.IllegalParameterException;
import psidev.psi.mi.jami.json.binary.MIJsonBinaryEvidenceWriter;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.model.impl.*;
import psidev.psi.mi.jami.utils.*;

import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Unti tester for MIJsonBinaryEvidenceWriter
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/07/13</pre>
 */

public class MIJsonBinaryEvidenceWriterTest {

    private FeatureEvidence testFeature = null;

    @Test(expected = IllegalStateException.class)
    public void test_not_initialised_writer() {
        MIJsonBinaryEvidenceWriter binaryWriter = new MIJsonBinaryEvidenceWriter();
        binaryWriter.write(new DefaultBinaryInteractionEvidence());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_not_initialised_no_options() {
        MIJsonBinaryEvidenceWriter binaryWriter = new MIJsonBinaryEvidenceWriter();
        binaryWriter.initialiseContext(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_writer_no_ontology_fetcher() {
        MIJsonBinaryEvidenceWriter binaryWriter = new MIJsonBinaryEvidenceWriter();
        binaryWriter.initialiseContext(null);
    }

    @Test
    public void test_write_binary() throws ParseException, IllegalParameterException {
        StringWriter writer = new StringWriter();
        MIJsonBinaryEvidenceWriter binaryWriter = new MIJsonBinaryEvidenceWriter(writer, null);

        BinaryInteractionEvidence binary = createBinaryInteractionEvidence();

        binaryWriter.start();

        binaryWriter.write(binary);
        binaryWriter.end();
        binaryWriter.close();

        String expected_json = getExpectedJson();

        Assert.assertEquals(expected_json, writer.toString());
    }

    @Test
    public void test_write_binary_list() throws ParseException, IllegalParameterException {
        StringWriter writer = new StringWriter();
        MIJsonBinaryEvidenceWriter binaryWriter = new MIJsonBinaryEvidenceWriter(writer, null);

        BinaryInteractionEvidence binary = createBinaryInteractionEvidence();

        binaryWriter.start();
        binaryWriter.write(Arrays.asList(binary, binary));
        binaryWriter.end();
        binaryWriter.close();

        String expected_line = getExpectedJson2();
        Assert.assertEquals(expected_line, writer.toString());
    }

    @Test
    public void test_write_binary2() throws ParseException, IllegalParameterException {
        StringWriter writer = new StringWriter();
        MIJsonBinaryEvidenceWriter binaryWriter = new MIJsonBinaryEvidenceWriter();
        Map<String, Object> options = new HashMap<String, Object>();
        options.put(InteractionWriterOptions.OUTPUT_OPTION_KEY, writer);
        binaryWriter.initialiseContext(options);

        BinaryInteractionEvidence binary = createBinaryInteractionEvidence();

        String expected_line = getExpectedJson();

        binaryWriter.start();

        binaryWriter.write(binary);
        binaryWriter.end();
        binaryWriter.close();

        Assert.assertEquals(expected_line, writer.toString());
    }

    private String getExpectedJson() {
        return "{\"data\":[{\"object\":\"interactor\",\"id\":\"uniprotkb_P12345\",\"type\":{\"id\":\"MI:0326\",\"name\":\"protein\"},\"organism\":{\"taxid\":\"9606\",\"common\":\"human\",\"scientific\":\"Homo Sapiens\"},\"identifier\":{\"db\":\"uniprotkb\",\"id\":\"P12345\"},\"label\":\"brca2\"},{\"object\":\"interactor\",\"id\":\"uniprotkb_P12347\",\"type\":{\"id\":\"MI:0326\",\"name\":\"protein\"},\"organism\":{\"taxid\":\"9606\",\"common\":\"human\",\"scientific\":\"Homo Sapiens\"},\"identifier\":{\"db\":\"uniprotkb\",\"id\":\"P12347\"},\"label\":\"protein2\"},{\"object\":\"interaction\",\"id\":\"intact_EBI-xxxx\",\"interactionType\":{\"id\":\"MI:xxxx\",\"name\":\"association\"},\"experiment\":{\"detmethod\":{\"id\":\"MI:xxx2\",\"name\":\"pull down\"},\"host\":{\"taxid\":\"-1\",\"common\":\"in vitro\"},\"pubid\":[{\"db\":\"pubmed\",\"id\":\"12345\"},{\"db\":\"imex\",\"id\":\"IM-1\"}],\"sourceDatabase\":{\"id\":\"MI:xxx1\",\"name\":\"intact\"}},\"confidences\":[{\"type\":\"author-score\",\"value\":\"high\"}],\"parameters\":[{\"type\":\"ic50\",\"value\":\"5x10^(-1)\",\"unit\":\"molar\"}],\"identifiers\":[{\"db\":\"intact\",\"id\":\"EBI-xxxx\"},{\"db\":\"imex\",\"id\":\"IM-1-1\"}],\"expansion\":{\"name\":\"spoke expansion\"},\"participants\":[{\"id\":\"1\",\"interactorRef\":\"uniprotkb_P12345\",\"stoichiometry\":\"2\",\"bioRole\":{\"id\":\"MI:0501\",\"name\":\"enzyme\"},\"expRole\":{\"id\":\"MI:0496\",\"name\":\"bait\"},\"identificationMethods\":[{\"id\":\"MI:xxxx1\",\"name\":\"western blot\"}],\"features\":[{\"id\":\"2\",\"category\":\"otherFeatures\",\"type\":{\"name\":\"binding site region\"},\"sequenceData\":[{\"pos\":\"1..3-6..7\",\"interactorRef\":\"uniprotkb_P12345\",\"participantRef\":\"1\"},{\"pos\":\">9->9\",\"interactorRef\":\"uniprotkb_P12345\",\"participantRef\":\"1\"}],\"InterPro\":\"interpro:xxxx\"}]},{\"id\":\"3\",\"interactorRef\":\"uniprotkb_P12347\",\"stoichiometry\":\"5\",\"bioRole\":{\"id\":\"MI:xxx5\",\"name\":\"enzyme target\"},\"expRole\":{\"id\":\"MI:xxx6\",\"name\":\"prey\"},\"identificationMethods\":[{\"id\":\"MI:xxxx2\",\"name\":\"predetermined\"}]}]}]}";
    }

    private String getExpectedJson2(){
        return "{\"data\":[{\"object\":\"interactor\",\"id\":\"uniprotkb_P12345\",\"type\":{\"id\":\"MI:0326\",\"name\":\"protein\"},\"organism\":{\"taxid\":\"9606\",\"common\":\"human\",\"scientific\":\"Homo Sapiens\"},\"identifier\":{\"db\":\"uniprotkb\",\"id\":\"P12345\"},\"label\":\"brca2\"},{\"object\":\"interactor\",\"id\":\"uniprotkb_P12347\",\"type\":{\"id\":\"MI:0326\",\"name\":\"protein\"},\"organism\":{\"taxid\":\"9606\",\"common\":\"human\",\"scientific\":\"Homo Sapiens\"},\"identifier\":{\"db\":\"uniprotkb\",\"id\":\"P12347\"},\"label\":\"protein2\"},{\"object\":\"interaction\",\"id\":\"intact_EBI-xxxx\",\"interactionType\":{\"id\":\"MI:xxxx\",\"name\":\"association\"},\"experiment\":{\"detmethod\":{\"id\":\"MI:xxx2\",\"name\":\"pull down\"},\"host\":{\"taxid\":\"-1\",\"common\":\"in vitro\"},\"pubid\":[{\"db\":\"pubmed\",\"id\":\"12345\"},{\"db\":\"imex\",\"id\":\"IM-1\"}],\"sourceDatabase\":{\"id\":\"MI:xxx1\",\"name\":\"intact\"}},\"confidences\":[{\"type\":\"author-score\",\"value\":\"high\"}],\"parameters\":[{\"type\":\"ic50\",\"value\":\"5x10^(-1)\",\"unit\":\"molar\"}],\"identifiers\":[{\"db\":\"intact\",\"id\":\"EBI-xxxx\"},{\"db\":\"imex\",\"id\":\"IM-1-1\"}],\"expansion\":{\"name\":\"spoke expansion\"},\"participants\":[{\"id\":\"1\",\"interactorRef\":\"uniprotkb_P12345\",\"stoichiometry\":\"2\",\"bioRole\":{\"id\":\"MI:0501\",\"name\":\"enzyme\"},\"expRole\":{\"id\":\"MI:0496\",\"name\":\"bait\"},\"identificationMethods\":[{\"id\":\"MI:xxxx1\",\"name\":\"western blot\"}],\"features\":[{\"id\":\"2\",\"category\":\"otherFeatures\",\"type\":{\"name\":\"binding site region\"},\"sequenceData\":[{\"pos\":\"1..3-6..7\",\"interactorRef\":\"uniprotkb_P12345\",\"participantRef\":\"1\"},{\"pos\":\">9->9\",\"interactorRef\":\"uniprotkb_P12345\",\"participantRef\":\"1\"}],\"InterPro\":\"interpro:xxxx\"}]},{\"id\":\"3\",\"interactorRef\":\"uniprotkb_P12347\",\"stoichiometry\":\"5\",\"bioRole\":{\"id\":\"MI:xxx5\",\"name\":\"enzyme target\"},\"expRole\":{\"id\":\"MI:xxx6\",\"name\":\"prey\"},\"identificationMethods\":[{\"id\":\"MI:xxxx2\",\"name\":\"predetermined\"}]}]}]}";
    }

    private BinaryInteractionEvidence createBinaryInteractionEvidence() throws ParseException, IllegalParameterException {
        ParticipantEvidence participantA = new DefaultParticipantEvidence(new DefaultProtein("protein1", "full name protein1"));
        // add identifiers
        participantA.getInteractor().getIdentifiers().add(XrefUtils.createUniprotIdentity("P12345"));
        participantA.getInteractor().getIdentifiers().add(XrefUtils.createUniprotSecondary("P12346"));
        participantA.getInteractor().getIdentifiers().add(XrefUtils.createXref("intact", "EBI-12345"));
        // add aliases
        participantA.getInteractor().getAliases().add(AliasUtils.createGeneName("brca2"));
        participantA.getInteractor().getAliases().add(AliasUtils.createGeneNameSynonym("brca2 synonym"));
        participantA.getAliases().add(AliasUtils.createAuthorAssignedName("\"bla\" author assigned name"));
        // species
        participantA.getInteractor().setOrganism(new DefaultOrganism(9606, "human", "Homo Sapiens"));
        //participantA.getAliases()
        ParticipantEvidence participantB = new DefaultParticipantEvidence(new DefaultProtein("protein2", "full name protein2"));
        // add identifiers
        participantB.getInteractor().getIdentifiers().add(XrefUtils.createUniprotIdentity("P12347"));
        participantB.getInteractor().getIdentifiers().add(XrefUtils.createUniprotSecondary("P12348"));
        participantB.getInteractor().getIdentifiers().add(XrefUtils.createXref("intact", "EBI-12346"));
        // species
        participantB.getInteractor().setOrganism(new DefaultOrganism(9606, "human", "Homo Sapiens"));
        // biological roles
        participantA.setBiologicalRole(CvTermUtils.createMICvTerm(Participant.ENZYME_ROLE, Participant.ENZYME_ROLE_MI));
        participantB.setBiologicalRole(CvTermUtils.createMICvTerm("enzyme target", "MI:xxx5"));
        // experimental roles
        participantA.setExperimentalRole(CvTermUtils.createMICvTerm(Participant.BAIT_ROLE, Participant.BAIT_ROLE_MI));
        participantB.setExperimentalRole(CvTermUtils.createMICvTerm("prey", "MI:xxx6"));
        // xrefs
        participantA.getXrefs().add(new DefaultXref(new DefaultCvTerm("go"), "GO:xxxxx", new DefaultCvTerm("component")));
        participantB.getInteractor().getXrefs().add(new DefaultXref(new DefaultCvTerm("interpro"), "INTERPRO:xxxxx"));
        // annotations
        participantA.getAnnotations().add(new DefaultAnnotation(new DefaultCvTerm("comment"), "test comment (to be reviewed)"));
        participantB.getInteractor().getAnnotations().add(new DefaultAnnotation(new DefaultCvTerm("caution"), "sequence withdrawn from uniprot"));
        // checksum
        participantA.getInteractor().getChecksums().add(ChecksumUtils.createRogid("xxxx1"));
        participantB.getInteractor().getChecksums().add(ChecksumUtils.createRogid("xxxx2"));
        // features
        testFeature = new DefaultFeatureEvidence(new DefaultCvTerm("binding site", "binding site region", (String)null));
        testFeature.getRanges().add(RangeUtils.createFuzzyRange(1, 3, 6, 7));
        testFeature.getRanges().add(RangeUtils.createGreaterThanRange(9));
        testFeature.setInterpro("interpro:xxxx");
        participantA.addFeature(testFeature);
        // stc
        participantA.setStoichiometry(2);
        participantB.setStoichiometry(5);
        // participant identification method
        participantA.getIdentificationMethods().add(new DefaultCvTerm("western blot", "MI:xxxx1"));
        participantB.getIdentificationMethods().add(new DefaultCvTerm("predetermined", "MI:xxxx2"));

        BinaryInteractionEvidence binary = new DefaultBinaryInteractionEvidence(participantA, participantB);
        participantA.setInteraction(binary);
        participantB.setInteraction(binary);

        // detection method
        binary.setExperiment(new DefaultExperiment(new DefaultPublication()));
        binary.getExperiment().setInteractionDetectionMethod(new DefaultCvTerm("pull down", "MI:xxx2"));
        // first author
        binary.getExperiment().getPublication().setPublicationDate(new SimpleDateFormat("yyyy").parse("2006"));
        binary.getExperiment().getPublication().getAuthors().add("author1");
        binary.getExperiment().getPublication().getAuthors().add("author2");
        // publication identifiers
        binary.getExperiment().getPublication().setPubmedId("12345");
        binary.getExperiment().getPublication().assignImexId("IM-1");
        // interaction type
        binary.setInteractionType(CvTermUtils.createMICvTerm("association", "MI:xxxx"));
        // source database
        Source source = new DefaultSource("intact");
        source.setMIIdentifier("MI:xxx1");
        binary.getExperiment().getPublication().setSource(source);
        // interaction identifiers
        binary.getIdentifiers().add(XrefUtils.createIdentityXref("intact", "EBI-xxxx"));
        binary.getIdentifiers().add(XrefUtils.createXrefWithQualifier("imex", "IM-1-1", "imex-primary"));
        // confidences
        binary.getConfidences().add(new DefaultConfidence(new DefaultCvTerm("author-score"), "high"));
        // expansion
        binary.setComplexExpansion(CvTermUtils.createMICvTerm(ComplexExpansionMethod.SPOKE_EXPANSION, ComplexExpansionMethod.SPOKE_EXPANSION_MI));
        // xrefs
        binary.getXrefs().add(new DefaultXref(new DefaultCvTerm("go"), "GO:xxxx2", new DefaultCvTerm("process")));
        // annotations
        binary.getAnnotations().add(new DefaultAnnotation(new DefaultCvTerm("figure-legend"), "Fig 1."));
        // parameters
        binary.getParameters().add(new DefaultParameter(new DefaultCvTerm("ic50"), "5x10^(-1)", new DefaultCvTerm("molar")));
        // creation date
        binary.setCreatedDate(new SimpleDateFormat("yyyy/MM/dd").parse("2006/06/06"));
        // update date
        binary.setUpdatedDate(new SimpleDateFormat("yyyy/MM/dd").parse("2007/01/01"));
        // checksum
        binary.getChecksums().add(ChecksumUtils.createRigid("xxxx3"));
        // host organism
        binary.getExperiment().setHostOrganism(new DefaultOrganism(-1, "in vitro"));
        // negative
        binary.setNegative(true);
        return binary;
    }
}

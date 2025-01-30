package psidev.psi.mi.jami.mitab.io.writer;

import org.junit.Test;
import psidev.psi.mi.jami.factory.options.InteractionWriterOptions;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.model.Protein;
import psidev.psi.mi.jami.model.Range;
import psidev.psi.mi.jami.model.ResultingSequence;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.model.impl.DefaultFeature;
import psidev.psi.mi.jami.model.impl.DefaultOrganism;
import psidev.psi.mi.jami.model.impl.DefaultResultingSequence;
import psidev.psi.mi.jami.tab.extension.MitabExperiment;
import psidev.psi.mi.jami.tab.extension.MitabFeatureEvidence;
import psidev.psi.mi.jami.tab.extension.MitabInteractionEvidence;
import psidev.psi.mi.jami.tab.extension.MitabParticipantEvidence;
import psidev.psi.mi.jami.tab.extension.MitabProtein;
import psidev.psi.mi.jami.tab.extension.MitabPublication;
import psidev.psi.mi.jami.tab.extension.factory.options.MitabWriterOptions;
import psidev.psi.mi.jami.tab.io.writer.FeatureTabWriter;
import psidev.psi.mi.jami.tab.utils.MitabUtils;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.RangeUtils;
import psidev.psi.mi.jami.utils.XrefUtils;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FeatureTabWriterTest {

    @Test(expected = IllegalStateException.class)
    public void testNotInitialised() {
        FeatureTabWriter featureTabWriter = new FeatureTabWriter();
        featureTabWriter.write(new DefaultFeature());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitialisedWithNoOptions() {
        FeatureTabWriter featureTabWriter = new FeatureTabWriter();
        featureTabWriter.initialiseContext(null);
    }

    @Test
    public void testWriteFeature() {
        StringWriter writer = new StringWriter();
        FeatureTabWriter featureTabWriter = new FeatureTabWriter(writer);
        featureTabWriter.setWriteHeader(false);

        Feature feature = createFeature1();

        featureTabWriter.write(feature);

        String expectedLine = getExpectedMitabLine1();
        assertEquals(expectedLine, writer.toString());
    }

    @Test
    public void testWriteFeatureList() {
        StringWriter writer = new StringWriter();
        FeatureTabWriter featureTabWriter = new FeatureTabWriter(writer);
        featureTabWriter.setWriteHeader(false);

        Feature feature = createFeature1();
        Feature feature2 = createFeature2();

        featureTabWriter.write(List.of(feature, feature2));

        String expectedLine = getExpectedMitabLine1();
        String expectedLine2 = getExpectedMitabLine2();
        assertEquals(expectedLine + MitabUtils.LINE_BREAK + expectedLine2, writer.toString());
    }

    @Test
    public void testWriteFeatureWithHeader() {
        StringWriter writer = new StringWriter();
        FeatureTabWriter featureTabWriter = new FeatureTabWriter();
        Map<String, Object> options = new HashMap<>();
        options.put(MitabWriterOptions.MITAB_HEADER_OPTION, true);
        options.put(InteractionWriterOptions.OUTPUT_OPTION_KEY, writer);
        featureTabWriter.initialiseContext(options);

        Feature feature = createFeature1();

        String expectedLine = getExpectedMitabLine1();

        featureTabWriter.start();
        featureTabWriter.write(feature);

        assertEquals(getExpectedHeaderLine() + MitabUtils.LINE_BREAK + expectedLine, writer.toString());
    }

    @Test
    public void testWriteFeaturesFromInteraction() {
        StringWriter writer = new StringWriter();
        FeatureTabWriter featureTabWriter = new FeatureTabWriter(writer);
        featureTabWriter.setWriteHeader(false);

        InteractionEvidence interaction = new MitabInteractionEvidence();

        Protein protein = new MitabProtein("p12345_human", "protein test");
        protein.getIdentifiers().add(XrefUtils.createXref("uniprotkb", "P12345"));
        protein.getAliases().add(AliasUtils.createGeneName("test gene"));
        protein.setOrganism(new DefaultOrganism(9606, "human", "Homo Sapiens"));
        ParticipantEvidence participant = new MitabParticipantEvidence(protein);
        interaction.getParticipants().add(participant);
        participant.setInteraction(interaction);

        ParticipantEvidence participant2 = new MitabParticipantEvidence(new MitabProtein("p12345_human", "protein test"));
        interaction.getParticipants().add(participant2);
        participant2.setInteraction(interaction);

        Feature feature = createFeature("feature 1", participant, interaction);
        participant.getFeatures().add((FeatureEvidence) feature);
        Feature feature2 = createFeature("feature 2", participant, interaction);
        participant.getFeatures().add((FeatureEvidence) feature2);
        Feature feature3 = createFeature("feature 3", participant2, interaction);
        participant2.getFeatures().add((FeatureEvidence) feature3);

        featureTabWriter.write(interaction);

        String expectedLine = getExpectedMitabLine1();
        String expectedLine2 = getExpectedMitabLine2();
        String expectedLine3 = getExpectedMitabLine("feature 3", "-", "-", "-");
        assertEquals(
                expectedLine + MitabUtils.LINE_BREAK + expectedLine2 + MitabUtils.LINE_BREAK + expectedLine3,
                writer.toString());
    }

    private String getExpectedHeaderLine() {
        return "# Feature AC" +
                "\tFeature short label" +
                "\tFeature range(s)" +
                "\tOriginal sequence" +
                "\tResulting sequence" +
                "\tFeature type" +
                "\tFeature annotation(s)" +
                "\tAffected protein AC" +
                "\tAffected protein symbol" +
                "\tAffected protein full name" +
                "\tAffected protein organism" +
                "\tInteraction participants" +
                "\tPubMedID" +
                "\tFigure legend(s)" +
                "\tInteraction AC" +
                "\tXref ID(s)";
    }

    private String getExpectedMitabLine1() {
        return getExpectedMitabLine("feature 1");
    }

    private String getExpectedMitabLine2() {
        return getExpectedMitabLine("feature 2");
    }

    private String getExpectedMitabLine(String featureLabel) {
        return getExpectedMitabLine(
                featureLabel,
                "uniprotkb:P12345",
                "uniprotkb:test gene(gene name)",
                "taxid:9606(human)|taxid:9606(Homo Sapiens)");
    }

    private String getExpectedMitabLine(String featureLabel, String proteinId, String geneName, String proteinOrganism) {
        return "-" +
                "\t" + featureLabel +
                "\t1..3-6..7,>9->9" +
                "\told_1,old_2" +
                "\tnew_1,new_2" +
                "\tpsi-mi:\"MI:0001\"(binding site)" +
                "\tcomment:comment1|caution: caution1" +
                "\t" + proteinId +
                "\t" + geneName +
                "\tprotein test" +
                "\t" + proteinOrganism +
                "\t(uniprotkb:P12345(psi-mi:\"MI:0326\"(protein)), taxid:9606(human)|taxid:9606(Homo Sapiens))|(-(psi-mi:\"MI:0326\"(protein)), -)" +
                "\tpubmed:123" +
                "\tfigure legend:test figure 1|figure legend:  test figure 2" +
                "\t-" +
                "\timex:imex_test_id|pubmed:pubmed_test_id(primary-reference)";
    }

    private Feature createFeature1() {
        return createFeature("feature 1");
    }

    private Feature createFeature2() {
        return createFeature("feature 2");
    }

    private Feature createFeature(String featureLabel) {
        InteractionEvidence interaction = new MitabInteractionEvidence();

        Protein protein = new MitabProtein("p12345_human", "protein test");
        protein.getIdentifiers().add(XrefUtils.createXref("uniprotkb", "P12345"));
        protein.getAliases().add(AliasUtils.createGeneName("test gene"));
        protein.setOrganism(new DefaultOrganism(9606, "human", "Homo Sapiens"));
        ParticipantEvidence participant = new MitabParticipantEvidence(protein);
        interaction.getParticipants().add(participant);
        participant.setInteraction(interaction);

        ParticipantEvidence participant2 = new MitabParticipantEvidence(new MitabProtein("p12345_human", "protein test"));
        interaction.getParticipants().add(participant2);
        participant2.setInteraction(interaction);

        Feature feature = createFeature(featureLabel, participant, interaction);
        participant.getFeatures().add((FeatureEvidence) feature);
        return feature;
    }

    private Feature createFeature(String featureLabel, ParticipantEvidence participant, InteractionEvidence interaction) {
        Feature feature = new MitabFeatureEvidence(
                featureLabel, "test description", new DefaultCvTerm("binding site", "MI:0001"));

        Range range1 = RangeUtils.createFuzzyRange(1, 3, 6, 7);
        ResultingSequence resultingSequence1 = new DefaultResultingSequence("old_1", "new_1");
        range1.setResultingSequence(resultingSequence1);
        feature.getRanges().add(range1);
        Range range2 = RangeUtils.createGreaterThanRange(9);
        ResultingSequence resultingSequence2 = new DefaultResultingSequence("old_2", "new_2");
        range2.setResultingSequence(resultingSequence2);
        feature.getRanges().add(range2);

        feature.getAnnotations().add(AnnotationUtils.createAnnotation("comment", "comment1"));
        feature.getAnnotations().add(AnnotationUtils.createAnnotation("caution", "\tcaution1"));

        feature.setParticipant(participant);

        Experiment experiment = new MitabExperiment(new MitabPublication(XrefUtils.createXrefWithQualifier(
                Xref.PUBMED, Xref.PUBMED_MI, "123", Xref.PRIMARY, Xref.PRIMARY_MI)));
        experiment.getAnnotations().add(AnnotationUtils.createAnnotation(
                Annotation.FIGURE_LEGEND, Annotation.FIGURE_LEGEND_MI, "test figure 1"));
        experiment.getAnnotations().add(AnnotationUtils.createAnnotation(
                Annotation.FIGURE_LEGEND, Annotation.FIGURE_LEGEND_MI, "\n\ttest figure 2"));
        interaction.setExperiment(experiment);

        feature.getIdentifiers().add(XrefUtils.createXref(Xref.IMEX, Xref.IMEX_MI, "imex_test_id"));
        feature.getIdentifiers().add(XrefUtils.createXrefWithQualifier(
                Xref.PUBMED, Xref.PUBMED_MI, "pubmed_test_id", Xref.PRIMARY, Xref.PRIMARY_MI));

        return feature;
    }
}

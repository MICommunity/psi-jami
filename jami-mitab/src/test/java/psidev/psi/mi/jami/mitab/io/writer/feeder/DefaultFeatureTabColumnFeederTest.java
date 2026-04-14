package psidev.psi.mi.jami.mitab.io.writer.feeder;

import org.junit.Test;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.model.Protein;
import psidev.psi.mi.jami.model.Range;
import psidev.psi.mi.jami.model.ResultingSequence;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.model.impl.DefaultOrganism;
import psidev.psi.mi.jami.model.impl.DefaultResultingSequence;
import psidev.psi.mi.jami.tab.extension.MitabExperiment;
import psidev.psi.mi.jami.tab.extension.MitabFeatureEvidence;
import psidev.psi.mi.jami.tab.extension.MitabInteractionEvidence;
import psidev.psi.mi.jami.tab.extension.MitabParticipantEvidence;
import psidev.psi.mi.jami.tab.extension.MitabProtein;
import psidev.psi.mi.jami.tab.extension.MitabPublication;
import psidev.psi.mi.jami.tab.io.writer.feeder.DefaultFeatureTabColumnFeeder;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.ExperimentUtils;
import psidev.psi.mi.jami.utils.RangeUtils;
import psidev.psi.mi.jami.utils.XrefUtils;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class DefaultFeatureTabColumnFeederTest {
    
    @Test
    public void writeFeatureAc() throws IOException {
        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeFeatureAc(null);
        assertEquals("-", writer.toString());

        Feature feature = new MitabFeatureEvidence();

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeFeatureAc(feature);
        assertEquals("-", writer.toString());
    }

    @Test
    public void writeFeatureShortLabel() throws IOException {
        Feature featureWithNoShortLabel = new MitabFeatureEvidence();

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeFeatureShortLabel(featureWithNoShortLabel);
        assertEquals("-", writer.toString());

        Feature featureWithShortLabel = new MitabFeatureEvidence("test label", "test description");

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeFeatureShortLabel(featureWithShortLabel);
        assertEquals("test label", writer.toString());
    }

    @Test
    public void writeRanges() throws IOException {
        MitabFeatureEvidence featureWithNoRanges = new MitabFeatureEvidence();

        MitabFeatureEvidence feature = new MitabFeatureEvidence();
        feature.getRanges().add(RangeUtils.createFuzzyRange(1, 3, 6, 7));
        feature.getRanges().add(RangeUtils.createGreaterThanRange(9));

        MitabFeatureEvidence feature2 = new MitabFeatureEvidence(new DefaultCvTerm("binding site"));
        feature2.getRanges().add(RangeUtils.createCertainRange(10));

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeRanges(featureWithNoRanges);
        assertEquals("?-?", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeRanges(feature);
        assertEquals("1..3-6..7,>9->9", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeRanges(feature2);
        assertEquals("10-10", writer.toString());
    }

    @Test
    public void writeOriginalSequence() throws IOException {
        MitabFeatureEvidence featureWithNoRanges = new MitabFeatureEvidence();

        MitabFeatureEvidence feature = new MitabFeatureEvidence();
        Range range = RangeUtils.createCertainRange(1);
        ResultingSequence resultingSequence = new DefaultResultingSequence("old", "new");
        range.setResultingSequence(resultingSequence);
        feature.getRanges().add(range);

        MitabFeatureEvidence feature2 = new MitabFeatureEvidence(new DefaultCvTerm("binding site"));
        Range range2 = RangeUtils.createCertainRange(1);
        ResultingSequence resultingSequence2 = new DefaultResultingSequence("old2", "new2");
        range2.setResultingSequence(resultingSequence2);
        feature2.getRanges().add(range2);
        ResultingSequence resultingSequence3 = new DefaultResultingSequence("old3", "new3");
        Range range3 = RangeUtils.createCertainRange(1);
        range3.setResultingSequence(resultingSequence3);
        feature2.getRanges().add(range3);

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeOriginalSequence(featureWithNoRanges);
        assertEquals("-", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeOriginalSequence(feature);
        assertEquals("old", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeOriginalSequence(feature2);
        assertEquals("old2,old3", writer.toString());
    }

    @Test
    public void writeResultingSequence() throws IOException {
        MitabFeatureEvidence featureWithNoRanges = new MitabFeatureEvidence();

        MitabFeatureEvidence feature = new MitabFeatureEvidence();
        Range range = RangeUtils.createCertainRange(1);
        ResultingSequence resultingSequence = new DefaultResultingSequence("old", "new");
        range.setResultingSequence(resultingSequence);
        feature.getRanges().add(range);

        MitabFeatureEvidence feature2 = new MitabFeatureEvidence(new DefaultCvTerm("binding site"));
        Range range2 = RangeUtils.createCertainRange(1);
        ResultingSequence resultingSequence2 = new DefaultResultingSequence("old2", "new2");
        range2.setResultingSequence(resultingSequence2);
        feature2.getRanges().add(range2);
        ResultingSequence resultingSequence3 = new DefaultResultingSequence("old3", "new3");
        Range range3 = RangeUtils.createCertainRange(1);
        range3.setResultingSequence(resultingSequence3);
        feature2.getRanges().add(range3);

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeResultingSequence(featureWithNoRanges);
        assertEquals("-", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeResultingSequence(feature);
        assertEquals("new", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeResultingSequence(feature2);
        assertEquals("new2,new3", writer.toString());
    }

    @Test
    public void writeFeatureType() throws IOException {
        MitabFeatureEvidence featureWithNoType = new MitabFeatureEvidence();
        MitabFeatureEvidence feature = new MitabFeatureEvidence(new DefaultCvTerm("binding site"));
        MitabFeatureEvidence feature2 = new MitabFeatureEvidence(new DefaultCvTerm("binding site", "MI:0001"));

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeFeatureType(featureWithNoType);
        assertEquals("-", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeFeatureType(feature);
        assertEquals("unknown:unknown(binding site)", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeFeatureType(feature2);
        assertEquals("psi-mi:\"MI:0001\"(binding site)", writer.toString());
    }

    @Test
    public void writeAnnotations() throws IOException {
        MitabFeatureEvidence featureWithNoAnnotations = new MitabFeatureEvidence();
        MitabFeatureEvidence feature = new MitabFeatureEvidence();
        feature.getAnnotations().add(AnnotationUtils.createAnnotation("comment", "comment1"));
        feature.getAnnotations().add(AnnotationUtils.createAnnotation("caution", "\tcaution1"));
        feature.getAnnotations().add(AnnotationUtils.createAnnotation("hidden", "secret annotation content"));

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeAnnotations(featureWithNoAnnotations);
        assertEquals("-", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeAnnotations(feature);
        assertEquals("comment:comment1|caution: caution1", writer.toString());
    }

    @Test
    public void writeAffectedProteinAc() throws IOException {
        MitabFeatureEvidence feature = new MitabFeatureEvidence();
        ParticipantEvidence participantWithNoId = new MitabParticipantEvidence(
                new MitabProtein("p12345_human", "protein test"));
        feature.setParticipant(participantWithNoId);

        MitabFeatureEvidence feature2 = new MitabFeatureEvidence();
        Protein protein = new MitabProtein("p12345_human", "protein test");
        protein.getIdentifiers().add(XrefUtils.createXref("uniprotkb", "P12345"));
        ParticipantEvidence participant = new MitabParticipantEvidence(protein);
        feature2.setParticipant(participant);

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeAffectedProteinAc(feature);
        assertEquals("-", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeAffectedProteinAc(feature2);
        assertEquals("uniprotkb:P12345", writer.toString());
    }

    @Test
    public void writeAffectedProteinSymbol() throws IOException {
        MitabFeatureEvidence feature = new MitabFeatureEvidence();
        ParticipantEvidence participantWithNoAlias = new MitabParticipantEvidence(
                new MitabProtein("p12345_human", "protein test"));
        feature.setParticipant(participantWithNoAlias);

        MitabFeatureEvidence feature2 = new MitabFeatureEvidence();
        Protein protein = new MitabProtein("p12345_human", "protein test");
        protein.getAliases().add(AliasUtils.createGeneName("test gene"));
        ParticipantEvidence participant = new MitabParticipantEvidence(protein);
        feature2.setParticipant(participant);

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeAffectedProteinSymbol(feature);
        assertEquals("-", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeAffectedProteinSymbol(feature2);
        assertEquals("uniprotkb:test gene(gene name)", writer.toString());
    }

    @Test
    public void writeAffectedProteinFullName() throws IOException {
        MitabFeatureEvidence feature = new MitabFeatureEvidence();
        ParticipantEvidence participantWithNoFullName = new MitabParticipantEvidence(
                new MitabProtein("p12345_human"));
        feature.setParticipant(participantWithNoFullName);

        MitabFeatureEvidence feature2 = new MitabFeatureEvidence();
        Protein protein = new MitabProtein("p12345_human", "protein test");
        ParticipantEvidence participant = new MitabParticipantEvidence(protein);
        feature2.setParticipant(participant);

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeAffectedProteinFullName(feature);
        assertEquals("-", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeAffectedProteinFullName(feature2);
        assertEquals("protein test", writer.toString());
    }

    @Test
    public void writeAffectedProteinOrganism() throws IOException {
        MitabFeatureEvidence feature = new MitabFeatureEvidence();
        ParticipantEvidence participantWithNoOrganism = new MitabParticipantEvidence(
                new MitabProtein("p12345_human", "protein test"));
        feature.setParticipant(participantWithNoOrganism);

        MitabFeatureEvidence feature2 = new MitabFeatureEvidence();
        Protein protein = new MitabProtein("p12345_human", "protein test");
        protein.setOrganism(new DefaultOrganism(9606, "human", "Homo Sapiens"));
        ParticipantEvidence participant = new MitabParticipantEvidence(protein);
        feature2.setParticipant(participant);

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeAffectedProteinOrganism(feature);
        assertEquals("-", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeAffectedProteinOrganism(feature2);
        assertEquals("taxid:9606(human)|taxid:9606(Homo Sapiens)", writer.toString());
    }

    @Test
    public void writeInteractionParticipants() throws IOException {
        ParticipantEvidence participant = new MitabParticipantEvidence(
                new MitabProtein("p12345_human", "protein test"));
        InteractionEvidence interaction = new MitabInteractionEvidence();
        interaction.getParticipants().add(participant);

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeInteractionParticipants(interaction);
        assertEquals("(-(psi-mi:\"MI:0326\"(protein)), -)", writer.toString());

        ParticipantEvidence participantWithOrganism = new MitabParticipantEvidence(
                new MitabProtein("p12345_human", "protein:test"));
        participantWithOrganism.getInteractor().setOrganism(
                new DefaultOrganism(9606, "human", "Homo Sapiens"));
        InteractionEvidence interaction2 = new MitabInteractionEvidence();
        interaction2.getParticipants().add(participantWithOrganism);

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeInteractionParticipants(interaction2);
        assertEquals("(-(psi-mi:\"MI:0326\"(protein)), taxid:9606(human)|taxid:9606(Homo Sapiens))", writer.toString());

        ParticipantEvidence participantWithIdentifier = new MitabParticipantEvidence(
                new MitabProtein("p12345_human", "protein:test"));
        participantWithIdentifier.getInteractor().getIdentifiers().add(
                XrefUtils.createXref("uniprotkb", "P12345"));
        InteractionEvidence interaction3 = new MitabInteractionEvidence();
        interaction3.getParticipants().add(participantWithIdentifier);

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeInteractionParticipants(interaction3);
        assertEquals("(uniprotkb:P12345(psi-mi:\"MI:0326\"(protein)), -)", writer.toString());

        InteractionEvidence interaction4 = new MitabInteractionEvidence();
        interaction4.getParticipants().add(participant);
        interaction4.getParticipants().add(participantWithOrganism);
        interaction4.getParticipants().add(participantWithIdentifier);

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeInteractionParticipants(interaction4);
        assertEquals(
                "(-(psi-mi:\"MI:0326\"(protein)), -)" +
                        "|" +
                        "(-(psi-mi:\"MI:0326\"(protein)), taxid:9606(human)|taxid:9606(Homo Sapiens))" +
                        "|" +
                        "(uniprotkb:P12345(psi-mi:\"MI:0326\"(protein)), -)",
                writer.toString());
    }

    @Test
    public void writePubMedId() throws IOException {
        Experiment experimentWithNoPubmedId = ExperimentUtils.createExperimentWithoutPublication();
        Experiment experiment = new MitabExperiment(new MitabPublication("123"));
        Experiment experiment2 = new MitabExperiment(new MitabPublication(XrefUtils.createXrefWithQualifier(
                Xref.PUBMED, Xref.PUBMED_MI, "456", Xref.PRIMARY, Xref.PRIMARY_MI)));

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writePubMedId(experimentWithNoPubmedId);
        assertEquals("-", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writePubMedId(experiment);
        assertEquals("pubmed:123", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writePubMedId(experiment2);
        assertEquals("pubmed:456", writer.toString());
    }

    @Test
    public void writeFigureLegends() throws IOException {
        InteractionEvidence interactionWithNoAnnotations = new MitabInteractionEvidence();
        InteractionEvidence interaction = new MitabInteractionEvidence();
        interaction.getAnnotations().add(AnnotationUtils.createAnnotation(
                Annotation.FIGURE_LEGEND, Annotation.FIGURE_LEGEND_MI, "test figure 1"));
        interaction.getAnnotations().add(AnnotationUtils.createAnnotation(
                Annotation.FIGURE_LEGEND, Annotation.FIGURE_LEGEND_MI, "\n\ttest figure 2"));

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeFigureLegends(interactionWithNoAnnotations);
        assertEquals("-", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeFigureLegends(interaction);
        assertEquals("figure legend:test figure 1|figure legend:  test figure 2", writer.toString());
    }

    @Test
    public void writeInteractionAc() throws IOException {
        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeInteractionAc(null);
        assertEquals("-", writer.toString());

        InteractionEvidence interaction = new MitabInteractionEvidence();

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeInteractionAc(interaction);
        assertEquals("-", writer.toString());
    }

    @Test
    public void writeXrefIds() throws IOException {
        MitabFeatureEvidence featureWithNoXrefs = new MitabFeatureEvidence();
        MitabFeatureEvidence feature = new MitabFeatureEvidence();
        feature.getXrefs().add(XrefUtils.createXref(Xref.IMEX, Xref.IMEX_MI, "imex_test_id"));
        feature.getXrefs().add(XrefUtils.createXref("intact", Xref.INTACT_MI, "EBI-1"));
        feature.getIdentifiers().add(XrefUtils.createXrefWithQualifier(
                Xref.PUBMED, Xref.PUBMED_MI, "pubmed_test_id", Xref.PRIMARY, Xref.PRIMARY_MI));
        MitabFeatureEvidence feature2 = new MitabFeatureEvidence();
        feature2.getIdentifiers().add(XrefUtils.createXref("intact", Xref.INTACT_MI, "EBI-2"));
        feature2.getXrefs().add(XrefUtils.createXref(Xref.IMEX, Xref.IMEX_MI, "imex_test_id_2"));

        StringWriter writer = new StringWriter();
        DefaultFeatureTabColumnFeeder feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeXrefIds(featureWithNoXrefs);
        assertEquals("-", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeXrefIds(feature);
        assertEquals("pubmed:pubmed_test_id(primary-reference)|imex:imex_test_id", writer.toString());

        writer = new StringWriter();
        feeder = new DefaultFeatureTabColumnFeeder(writer);
        feeder.writeXrefIds(feature2);
        assertEquals("imex:imex_test_id_2", writer.toString());
    }
}

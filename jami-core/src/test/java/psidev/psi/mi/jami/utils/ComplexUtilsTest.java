package psidev.psi.mi.jami.utils;

import org.junit.Assert;
import org.junit.Test;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ComplexUtilsTest {

    @Test
    public void test_expand_complex_participants() {
        Complex complexAsAnInteractor1 = new DefaultComplex("complex_interactor", new DefaultCvTerm("protein complex"));
        complexAsAnInteractor1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        Stoichiometry stc = new DefaultStoichiometry(1, 3);
        ModelledParticipant modelledParticipant1 = new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("P12345")), stc);
        modelledParticipant1.addFeature(new DefaultModelledFeature());
        modelledParticipant1.addFeature(new DefaultModelledFeature());
        complexAsAnInteractor1.addParticipant(modelledParticipant1);
        complexAsAnInteractor1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createUniprotIdentity("P12347"))));


        Stoichiometry stc1 = new DefaultStoichiometry(2, 2);
        //with stoichiometry
        ModelledParticipant complexParticipantToExpand1 = new DefaultModelledParticipant(complexAsAnInteractor1, stc1);
        complexParticipantToExpand1.addFeature(new DefaultModelledFeature());
        complexParticipantToExpand1.addFeature(new DefaultModelledFeature());
        //without stoichiometry
        ModelledParticipant complexParticipantToExpand2 = new DefaultModelledParticipant(complexAsAnInteractor1);

        //complexParticipantToExpand1 testing
        Collection<ModelledParticipant> expandedParticipants1 = ComplexUtils.expandComplexIntoParticipants(complexParticipantToExpand1);
        Assert.assertEquals(2, expandedParticipants1.size());
        boolean testedCase1 = false;
        boolean testedCase2 = false;

        try {
            for (ModelledParticipant expandedParticipant : expandedParticipants1) {
                if (expandedParticipant.getInteractor().getPreferredIdentifier().getId().equals("P12345")) {
                    Assert.assertEquals(2, expandedParticipant.getStoichiometry().getMinValue());
                    Assert.assertEquals(6, expandedParticipant.getStoichiometry().getMaxValue());
                    // there is no use case yet to have features in expanded complex participants
                    Assert.assertEquals(0, expandedParticipant.getFeatures().size());
                    testedCase1 = true;
                } else if (expandedParticipant.getInteractor().getPreferredIdentifier().getId().equals("P12347")) {
                    Assert.assertNull(expandedParticipant.getStoichiometry());
                    Assert.assertNull(expandedParticipant.getStoichiometry());
                    testedCase2 = true;
                }
            }
        } catch (Exception e) {
            Assert.assertFalse("Expected expanded participants absent", true);
        }


        Assert.assertTrue("Expected expanded participants absent", (testedCase1 && testedCase2));

        //original interactor complex stoichiometry/features should be same as before
        boolean testedCase3 = false;
        boolean testedCase4 = false;
        try {
            for (ModelledParticipant originalParticipant : complexAsAnInteractor1.getParticipants()) {
                if (originalParticipant.getInteractor().getPreferredIdentifier().getId().equals("P12345")) {
                    Assert.assertEquals(1, originalParticipant.getStoichiometry().getMinValue());
                    Assert.assertEquals(3, originalParticipant.getStoichiometry().getMaxValue());
                    Assert.assertEquals(2, originalParticipant.getFeatures().size());
                    testedCase3 = true;
                } else if (originalParticipant.getInteractor().getPreferredIdentifier().getId().equals("P12347")) {
                    Assert.assertNull(originalParticipant.getStoichiometry());
                    Assert.assertNull(originalParticipant.getStoichiometry());
                    testedCase4 = true;
                }
            }
        } catch (Exception e) {
            Assert.assertFalse("Expected original complex participants absent", true);
        }

        Assert.assertTrue("Expected original complex participants absent", (testedCase3 && testedCase4));

        // original parent participant should be unchanged
        Assert.assertEquals(2, complexParticipantToExpand1.getFeatures().size());
        Assert.assertEquals(2, complexParticipantToExpand1.getStoichiometry().getMinValue());
        Assert.assertEquals(2, complexParticipantToExpand1.getStoichiometry().getMaxValue());

        //complexParticipantToExpand2 testing
        Collection<ModelledParticipant> expandedParticipants2 = ComplexUtils.expandComplexIntoParticipants(complexParticipantToExpand2);
        Assert.assertEquals(2, expandedParticipants2.size());
        boolean testedCase5 = false;
        boolean testedCase6 = false;
        try {
            for (ModelledParticipant expandedParticipant : expandedParticipants2) {
                if (expandedParticipant.getInteractor().getPreferredIdentifier().getId().equals("P12345")) {
                    Assert.assertNull(expandedParticipant.getStoichiometry());
                    Assert.assertNull(expandedParticipant.getStoichiometry());
                    testedCase5 = true;
                } else if (expandedParticipant.getInteractor().getPreferredIdentifier().getId().equals("P12347")) {
                    Assert.assertNull(expandedParticipant.getStoichiometry());
                    Assert.assertNull(expandedParticipant.getStoichiometry());
                    testedCase6 = true;
                }
            }
        } catch (Exception e) {
            Assert.assertFalse("Expected expanded participants absent", true);
        }

        Assert.assertTrue("Expected expanded participants absent", (testedCase5 && testedCase6));
    }

    @Test
    public void test_expand_complex_with_multiple_levels_of_subcomplexes() {
        // Sub-Sub-Complex-1
        Complex subSubComplex1 = new DefaultComplex("sub-sub-complex-1", new DefaultCvTerm("protein complex"));
        subSubComplex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        subSubComplex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createUniprotIdentity("P12345")), new DefaultStoichiometry(1, 3)));

        // Sub-Sub-Complex-2
        Complex subSubComplex2 = new DefaultComplex("sub-sub-complex-2", new DefaultCvTerm("protein complex"));
        subSubComplex2.setInteractionType(new DefaultCvTerm("phosphorylation"));
        subSubComplex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createUniprotIdentity("P12346"))));

        // Sub-Complex
        Complex subComplex = new DefaultComplex("sub-complex", new DefaultCvTerm("protein complex"));
        subComplex.setInteractionType(new DefaultCvTerm("phosphorylation"));
        subComplex.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createUniprotIdentity("P12347")), new DefaultStoichiometry(2, 3)));
        subComplex.addParticipant(new DefaultModelledParticipant(subSubComplex1, new DefaultStoichiometry(1, 2)));
        subComplex.addParticipant(new DefaultModelledParticipant(subSubComplex2, new DefaultStoichiometry(1, 2)));

        // Complex
        Complex complex = new DefaultComplex("complex", new DefaultCvTerm("protein complex"));
        complex.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createUniprotIdentity("P12348"))));
        complex.addParticipant(new DefaultModelledParticipant(subComplex, new DefaultStoichiometry(2, 2)));

        ModelledParticipant complexAsParticipant = new DefaultModelledParticipant(complex, new DefaultStoichiometry(1, 1));

        Collection<ModelledParticipant> expandedParticipants = ComplexUtils.expandComplexIntoParticipants(complexAsParticipant);
        Assert.assertEquals(4, expandedParticipants.size());

        for (ModelledParticipant expandedParticipant : expandedParticipants) {
            if (expandedParticipant.getInteractor().getPreferredIdentifier().getId().equals("P12345")) {
                Assert.assertEquals(2, expandedParticipant.getStoichiometry().getMinValue());
                Assert.assertEquals(12, expandedParticipant.getStoichiometry().getMaxValue());
                Assert.assertEquals(0, expandedParticipant.getFeatures().size());
            } else if (expandedParticipant.getInteractor().getPreferredIdentifier().getId().equals("P12346")) {
                Assert.assertNull(expandedParticipant.getStoichiometry());
                Assert.assertNull(expandedParticipant.getStoichiometry());
                Assert.assertEquals(0, expandedParticipant.getFeatures().size());
            } else if (expandedParticipant.getInteractor().getPreferredIdentifier().getId().equals("P12347")) {
                Assert.assertEquals(4, expandedParticipant.getStoichiometry().getMinValue());
                Assert.assertEquals(6, expandedParticipant.getStoichiometry().getMaxValue());
                Assert.assertEquals(0, expandedParticipant.getFeatures().size());
            } else if (expandedParticipant.getInteractor().getPreferredIdentifier().getId().equals("P12348")) {
                Assert.assertNull(expandedParticipant.getStoichiometry());
                Assert.assertNull(expandedParticipant.getStoichiometry());
                Assert.assertEquals(0, expandedParticipant.getFeatures().size());
            }
        }
    }

    @Test
    public void testMaintainProteinComparableParticipantMap() {
        ModelledParticipant modelledParticipant1 = new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID1")), new DefaultStoichiometry(1, 3));
        ModelledParticipant modelledParticipant2 = new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID1")), new DefaultStoichiometry(1, 3));
        ModelledParticipant modelledParticipant3 = new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID2")));
        ModelledParticipant modelledParticipant4 = new DefaultModelledParticipant(new DefaultProtein("test4 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID2")));
        ModelledParticipant modelledParticipant5 = new DefaultModelledParticipant(new DefaultProtein("test5 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID3")), new DefaultStoichiometry(1, 3));
        ModelledParticipant modelledParticipant6 = new DefaultModelledParticipant(new DefaultNucleicAcid("test"));

        InteractorPool interactorPool1 = new DefaultInteractorPool("test pool 1");
        interactorPool1.add(new DefaultProtein("test6 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID1")));
        interactorPool1.add(new DefaultNucleicAcid("test2 "));
        interactorPool1.add(new DefaultProtein("test2 protein",
                XrefUtils.createIdentityXref("intact", "MI:0469", "EBI-AC1")));

        InteractorPool interactorPool2 = new DefaultInteractorPool("test pool 2");
        interactorPool2.add(new DefaultProtein("test7 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID1")));
        interactorPool2.add(new DefaultProtein("test8 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID4")));

        ModelledParticipant modelledParticipant7 = new DefaultModelledParticipant(interactorPool1, new DefaultStoichiometry(1, 3));
        ModelledParticipant modelledParticipant8 = new DefaultModelledParticipant(interactorPool2);
        ModelledParticipant modelledParticipant9 = new DefaultModelledParticipant(new DefaultProtein("test9 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID1")), new DefaultStoichiometry(1, 3));
        ModelledParticipant modelledParticipant10 = new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createIdentityXref("intact", "MI:0469", "EBI-AC1")), new DefaultStoichiometry(1, 3));

        Collection<ModelledParticipant> modelledParticipants = new ArrayList<>();
        modelledParticipants.add(modelledParticipant1);
        modelledParticipants.add(modelledParticipant2);
        modelledParticipants.add(modelledParticipant3);
        modelledParticipants.add(modelledParticipant4);
        modelledParticipants.add(modelledParticipant5);
        modelledParticipants.add(modelledParticipant6);
        modelledParticipants.add(modelledParticipant7);
        modelledParticipants.add(modelledParticipant8);
        modelledParticipants.add(modelledParticipant10);

        Map<String, ModelledComparableParticipant> testMap = new HashMap<>();

        //test
        ModelledParticipant[] modelledParticipantsArray = new ModelledParticipant[8];
        ComplexUtils.maintainProteinComparableParticipantMap(testMap, false, null, modelledParticipants.toArray(modelledParticipantsArray));
        ComplexUtils.maintainProteinComparableParticipantMap(testMap, false, null, modelledParticipant9);

        Assert.assertEquals(5, testMap.size());

        Assert.assertEquals("UNIPROTID1", testMap.get("UNIPROTID1").getInteractorId());
        Assert.assertEquals(4, testMap.get("UNIPROTID1").getStoichiometry());

        Assert.assertEquals("UNIPROTID2", testMap.get("UNIPROTID2").getInteractorId());
        Assert.assertEquals(0, testMap.get("UNIPROTID2").getStoichiometry());

        Assert.assertEquals("UNIPROTID3", testMap.get("UNIPROTID3").getInteractorId());
        Assert.assertEquals(1, testMap.get("UNIPROTID3").getStoichiometry());

        Assert.assertEquals("UNIPROTID4", testMap.get("UNIPROTID4").getInteractorId());
        Assert.assertEquals(0, testMap.get("UNIPROTID4").getStoichiometry());

        Assert.assertEquals("EBI-AC1", testMap.get("EBI-AC1").getInteractorId());
        Assert.assertEquals(2, testMap.get("EBI-AC1").getStoichiometry());

    }

    @Test
    public void testMaintainParticipantMap() {
        ModelledParticipant modelledParticipant1 = new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID1")), new DefaultStoichiometry(1, 3));
        ModelledParticipant modelledParticipant2 = new DefaultModelledParticipant(new DefaultGene("test2 gene",
                XrefUtils.createEntrezGeneIdIdentity("test gene")), new DefaultStoichiometry(1, 3));
        ModelledParticipant modelledParticipant3 = new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID2")));
        ModelledParticipant modelledParticipant4 = new DefaultModelledParticipant(new DefaultProtein("test4 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID2")));
        ModelledParticipant modelledParticipant5 = new DefaultModelledParticipant(new DefaultProtein("test5 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID3")), new DefaultStoichiometry(1, 3));
        ModelledParticipant modelledParticipant6 = new DefaultModelledParticipant(new DefaultNucleicAcid("test nucleic acid 1",
                XrefUtils.createRefseqIdentity("nucleic acid 1")));

        InteractorPool interactorPool1 = new DefaultInteractorPool("test pool 1");
        interactorPool1.add(new DefaultProtein("test6 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID1")));
        interactorPool1.add(new DefaultNucleicAcid("test2 "));
        interactorPool1.add(new DefaultNucleicAcid("test nucleic acid 1",
                XrefUtils.createRefseqIdentity("nucleic acid 1")));

        InteractorPool interactorPool2 = new DefaultInteractorPool("test pool 2");
        interactorPool2.add(new DefaultProtein("test7 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID1")));
        interactorPool2.add(new DefaultProtein("test8 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID4")));

        ModelledParticipant modelledParticipant7 = new DefaultModelledParticipant(interactorPool1, new DefaultStoichiometry(1, 3));
        ModelledParticipant modelledParticipant8 = new DefaultModelledParticipant(interactorPool2);
        ModelledParticipant modelledParticipant9 = new DefaultModelledParticipant(new DefaultProtein("test9 protein",
                XrefUtils.createUniprotIdentity("UNIPROTID1")), new DefaultStoichiometry(1, 3));
        ModelledParticipant modelledParticipant10 = new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createIdentityXref("intact", "MI:0469", "EBI-AC1")), new DefaultStoichiometry(1, 3));

        Collection<ModelledParticipant> modelledParticipants = new ArrayList<>();
        modelledParticipants.add(modelledParticipant1);
        modelledParticipants.add(modelledParticipant2);
        modelledParticipants.add(modelledParticipant3);
        modelledParticipants.add(modelledParticipant4);
        modelledParticipants.add(modelledParticipant5);
        modelledParticipants.add(modelledParticipant6);
        modelledParticipants.add(modelledParticipant7);
        modelledParticipants.add(modelledParticipant8);
        modelledParticipants.add(modelledParticipant10);

        Map<String, ModelledComparableParticipant> testMap = new HashMap<>();

        //test
        ModelledParticipant[] modelledParticipantsArray = new ModelledParticipant[8];
        ComplexUtils.maintainParticipantMap(testMap, modelledParticipants.toArray(modelledParticipantsArray));
        ComplexUtils.maintainParticipantMap(testMap, modelledParticipant9);

        Assert.assertEquals(7, testMap.size());

        Assert.assertEquals("UNIPROTID1", testMap.get("UNIPROTID1").getInteractorId());
        Assert.assertEquals(3, testMap.get("UNIPROTID1").getStoichiometry());

        Assert.assertEquals("UNIPROTID2", testMap.get("UNIPROTID2").getInteractorId());
        Assert.assertEquals(0, testMap.get("UNIPROTID2").getStoichiometry());

        Assert.assertEquals("UNIPROTID3", testMap.get("UNIPROTID3").getInteractorId());
        Assert.assertEquals(1, testMap.get("UNIPROTID3").getStoichiometry());

        Assert.assertEquals("UNIPROTID4", testMap.get("UNIPROTID4").getInteractorId());
        Assert.assertEquals(0, testMap.get("UNIPROTID4").getStoichiometry());

        Assert.assertEquals("EBI-AC1", testMap.get("EBI-AC1").getInteractorId());
        Assert.assertEquals(1, testMap.get("EBI-AC1").getStoichiometry());

        Assert.assertEquals("test gene", testMap.get("test gene").getInteractorId());
        Assert.assertEquals(1, testMap.get("test gene").getStoichiometry());

        Assert.assertEquals("nucleic acid 1", testMap.get("nucleic acid 1").getInteractorId());
        Assert.assertEquals(1, testMap.get("nucleic acid 1").getStoichiometry());

    }
}

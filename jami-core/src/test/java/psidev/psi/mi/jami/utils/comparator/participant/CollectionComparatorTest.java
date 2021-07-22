package psidev.psi.mi.jami.utils.comparator.participant;

import org.junit.Assert;
import org.junit.Test;
import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.model.ModelledComparableParticipant;
import psidev.psi.mi.jami.model.ModelledFeature;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.model.impl.*;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.comparator.CollectionComparator;

public class CollectionComparatorTest {

    private CollectionComparator<ModelledComparableParticipant> collectionComparator=new CollectionComparator<>(new ModelledComparableParticipantComparator());

    @Test
    public void test_complex_same_participants_different_features(){

        ModelledFeature modelledFeature1=new DefaultModelledFeature();
        ModelledFeature modelledFeature2=new DefaultModelledFeature();
        ModelledFeature modelledFeature3=new DefaultModelledFeature();

        Complex complex1 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        ModelledParticipant modelledParticipant1=new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12347")));

        modelledParticipant1.addFeature(modelledFeature1);
        modelledParticipant1.addFeature(modelledFeature2);

        complex1.addParticipant(modelledParticipant1);

        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12345"))));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12346"))));
        Complex complex2 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex2.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12345"))));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12346"))));
        ModelledParticipant modelledParticipant2=new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12347")));

        modelledParticipant2.addFeature(modelledFeature3);

        complex2.addParticipant(modelledParticipant2);

        Assert.assertTrue(collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()) == 0);
        Assert.assertTrue(collectionComparator.compare(complex2.getComparableParticipants(), complex1.getComparableParticipants()) == 0);
    }

    @Test
    public void test_complex_same_protein_complex_sets_participants(){
        // participant 1
        ModelledParticipant modelledParticipant1=new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12346")));

        //participant2
        ModelledParticipant modelledParticipant2=new DefaultModelledParticipant(new DefaultNucleicAcid("test"));

        //participant3
        Complex complexAsAnInteractor1 = new DefaultComplex("complex_interactor", new DefaultCvTerm("protein complex"));
        complexAsAnInteractor1.setInteractionType(new DefaultCvTerm("phosphorylation"));

        complexAsAnInteractor1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("P12345"))));
        complexAsAnInteractor1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createUniprotIdentity("P12347"))));
        ModelledParticipant modelledParticipant3=new DefaultModelledParticipant(complexAsAnInteractor1);

        //participant4
        ModelledParticipant modelledParticipant4=new DefaultModelledParticipant(new DefaultInteractorPool("pool_or_set"));

        //complex1
        Complex complex1 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex1.addParticipant(modelledParticipant1);
        complex1.addParticipant(modelledParticipant2);
        complex1.addParticipant(modelledParticipant3);
        complex1.addParticipant(modelledParticipant4);

        //participant5
        ModelledParticipant modelledParticipant5=new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12346")));

        //participant6
        Complex complexAsAnInteractor2 = new DefaultComplex("complex_interactor", new DefaultCvTerm("protein complex"));
        complexAsAnInteractor2.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complexAsAnInteractor2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("P12345")), new DefaultStoichiometry(1, 3)));
        complexAsAnInteractor2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createUniprotIdentity("P12347"))));
        ModelledParticipant modelledParticipant6=new DefaultModelledParticipant(complexAsAnInteractor2);

        //participant7
        ModelledParticipant modelledParticipant7=new DefaultModelledParticipant(new DefaultInteractorPool("pool_or_set"));

        //complex2
        Complex complex2 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex2.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex2.addParticipant(modelledParticipant5);
        complex2.addParticipant(modelledParticipant6);
        complex2.addParticipant(modelledParticipant7);

        Assert.assertTrue(collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()) == 0);
        Assert.assertTrue(collectionComparator.compare(complex2.getComparableParticipants(), complex1.getComparableParticipants()) == 0);
    }

    @Test
    public void test_complex_same_interactor_complex_participants(){
        //complex as an interactor
        Complex complexAsAnInteractor1 = new DefaultComplex("complex_interactor", new DefaultCvTerm("protein complex"));
        complexAsAnInteractor1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complexAsAnInteractor1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("P12345")), new DefaultStoichiometry(1, 3)));
        complexAsAnInteractor1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createUniprotIdentity("P12347"))));

        //complex1
        Complex complex1 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex1.addParticipant(new DefaultModelledParticipant(complexAsAnInteractor1, new DefaultStoichiometry(2, 2)));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12346"))));
        //complex2
        Complex complex2 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex2.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12345")),new DefaultStoichiometry(2, 6)));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12346"))));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12347"))));

        Assert.assertTrue(collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()) == 0);
        Assert.assertTrue(collectionComparator.compare(complex2.getComparableParticipants(), complex1.getComparableParticipants()) == 0);
    }

    @Test
    public void test_complex_different_interactors_acs(){
        //complex1
        Complex complex1 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12346"))));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("P12345"))));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createUniprotIdentity("P12347"))));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createIdentityXref("intact","MI:0469","EBI-AC1"))));
        //complex2
        Complex complex2 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex2.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12345"))));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12346"))));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12347"))));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createIdentityXref("intact","MI:0469","EBI-AC2"))));

        Assert.assertTrue(collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()) == -1);
        Assert.assertTrue(collectionComparator.compare(complex2.getComparableParticipants(), complex1.getComparableParticipants()) == 1);
    }
}

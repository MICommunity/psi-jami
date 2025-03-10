package psidev.psi.mi.jami.utils.comparator.participant;

import org.junit.Assert;
import org.junit.Test;
import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.model.ModelledComparableParticipant;
import psidev.psi.mi.jami.model.ModelledFeature;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.model.Protein;
import psidev.psi.mi.jami.model.impl.*;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.comparator.CollectionComparator;

public class CollectionComparatorTest {

    private final CollectionComparator<ModelledComparableParticipant> collectionComparator =
            new CollectionComparator<>(new ModelledComparableParticipantComparator());

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

        Assert.assertEquals(0, collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()));
        Assert.assertEquals(0, collectionComparator.compare(complex2.getComparableParticipants(), complex1.getComparableParticipants()));
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

        Assert.assertEquals(0, collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()));
        Assert.assertEquals(0, collectionComparator.compare(complex2.getComparableParticipants(), complex1.getComparableParticipants()));
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

        Assert.assertEquals(0, collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()));
        Assert.assertEquals(0, collectionComparator.compare(complex2.getComparableParticipants(), complex1.getComparableParticipants()));
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

        Assert.assertEquals(-1, collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()));
        Assert.assertEquals(1, collectionComparator.compare(complex2.getComparableParticipants(), complex1.getComparableParticipants()));
    }

    @Test
    public void test_complex_with_sub_sub_complexes(){
        //complex1
        Complex subSubComplex1 = new DefaultComplex("CPX-5692", new DefaultCvTerm("protein complex"));
        subSubComplex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        subSubComplex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P0DTD1-PRO_0000449628"))));
        subSubComplex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P0DTD1-PRO_0000449631"))));

        Complex subSubComplex2 = new DefaultComplex("CPX-6442", new DefaultCvTerm("protein complex"));
        subSubComplex2.setInteractionType(new DefaultCvTerm("phosphorylation"));
        subSubComplex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P0DTD1-PRO_0000449625")), new DefaultStoichiometry(1, 1)));
        subSubComplex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P0DTD1-PRO_0000449626")), new DefaultStoichiometry(1, 1)));
        subSubComplex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P0DTD1-PRO_0000449626")), new DefaultStoichiometry(1, 1)));
        subSubComplex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P0DTD1-PRO_0000449629")), new DefaultStoichiometry(1, 1)));
        subSubComplex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P0DTD1-PRO_0000449630")), new DefaultStoichiometry(1, 1)));
        subSubComplex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P0DTD1-PRO_0000449630")), new DefaultStoichiometry(1, 1)));

        Complex subComplex = new DefaultComplex("CPX-7041", new DefaultCvTerm("protein complex"));
        subComplex.setInteractionType(new DefaultCvTerm("phosphorylation"));
        subComplex.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P0DTD1-PRO_0000449627")), new DefaultStoichiometry(1, 1)));
        subComplex.addParticipant(new DefaultModelledParticipant(subSubComplex1, new DefaultStoichiometry(1, 1)));
        subComplex.addParticipant(new DefaultModelledParticipant(subSubComplex2, new DefaultStoichiometry(1, 1)));

        Complex complex1 = new DefaultComplex("CPX-7083", new DefaultCvTerm("protein complex"));
        complex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex1.addParticipant(new DefaultModelledParticipant(subComplex, new DefaultStoichiometry(1, 1)));
        complex1.addParticipant(new DefaultModelledParticipant(subComplex, new DefaultStoichiometry(1, 1)));

        //complex2
        Complex complex2 = new DefaultComplex("CPX-5687", new DefaultCvTerm("protein complex"));
        complex2.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P0DTD1-PRO_0000449627")), new DefaultStoichiometry(1, 1)));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P0DTD1-PRO_0000449627")), new DefaultStoichiometry(1, 1)));

        Assert.assertNotEquals(0, collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()));
    }

    @Test
    public void test_uniplex_1(){
        //complex1
        Complex complex1 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("Q15633"))));
        DefaultProtein protein2 = new DefaultProtein("test1 protein",
                XrefUtils.createEnsemblIdentity("ENSP00000343745.3"));
        protein2.getIdentifiers().add(XrefUtils.createUniprotIdentity("B3KRG4"));
        protein2.getIdentifiers().add(XrefUtils.createUniprotIdentity("Q9UPY3"));
        complex1.addParticipant(new DefaultModelledParticipant(protein2));
        //complex2
        Complex complex2 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex2.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("Q15633"))));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("Q9UPY3"))));

        Assert.assertEquals(0, collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()));
    }

    @Test
    public void test_uniplex_2(){
        //complex1
        Complex complex1 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        DefaultProtein protein1 = new DefaultProtein("test1 protein",
                XrefUtils.createEnsemblIdentity("ENSP00000343745.3"));
        protein1.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000376783.1"));
        protein1.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000433060.3"));
        protein1.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000433926.2"));
        protein1.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000435681.1"));
        protein1.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000437256.1"));
        protein1.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000502730.2"));
        protein1.getIdentifiers().add(XrefUtils.createUniprotIdentity("Q9UPY3"));
        complex1.addParticipant(new DefaultModelledParticipant(protein1));
        DefaultProtein protein2 = new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("Q15633"));
        protein2.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000266987.2"));
        complex1.addParticipant(new DefaultModelledParticipant(protein2));
        //complex2
        Complex complex2 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex2.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("Q15633"))));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("Q9UPY3"))));

        Assert.assertEquals(0, collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()));
    }

    @Test
    public void test_pdb_1(){
        //complex1
        Complex complex1 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P0DP23"))));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("P53355"))));
        //complex2
        Complex complex2 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex2.setInteractionType(new DefaultCvTerm("phosphorylation"));

        DefaultInteractorPool interactorPool = new DefaultInteractorPool("calm_human");
        interactorPool.getIdentifiers().add(XrefUtils.createUniprotIdentity("P0DP23"));
        interactorPool.getIdentifiers().add(XrefUtils.createUniprotIdentity("P0DP24"));
        interactorPool.getIdentifiers().add(XrefUtils.createUniprotIdentity("P0DP25"));
        interactorPool.getIdentifiers().add(XrefUtils.createUniprotIdentity("P62158"));
        DefaultProtein protein1 = new DefaultProtein("calm3_human",
                XrefUtils.createEnsemblIdentity("ENSP00000291295.8"));
        protein1.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000472141.1"));
        protein1.getIdentifiers().add(XrefUtils.createUniprotIdentity("P0DP25"));
        interactorPool.add(protein1);
        DefaultProtein protein2 = new DefaultProtein("calm2_human",
                XrefUtils.createEnsemblIdentity("ENSP00000272298.7"));
        protein2.getIdentifiers().add(XrefUtils.createUniprotIdentity("P0DP24"));
        interactorPool.add(protein2);
        DefaultProtein protein3 = new DefaultProtein("calm1_human",
                XrefUtils.createEnsemblIdentity("ENSP00000349467.4"));
        protein3.getIdentifiers().add(XrefUtils.createUniprotIdentity("P0DP23"));
        interactorPool.add(protein3);
        complex2.addParticipant(new DefaultModelledParticipant(interactorPool));

        DefaultProtein protein = new DefaultProtein("dapk1_human",
                XrefUtils.createEnsemblIdentity("ENSP00000350785.5"));
        protein.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000386135.3"));
        protein.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000417076.1"));
        protein.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000484267.1"));
        protein.getIdentifiers().add(XrefUtils.createUniprotIdentity("P53355"));
        complex2.addParticipant(new DefaultModelledParticipant(protein));

        Assert.assertEquals(0, collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()));
    }

    @Test
    public void test_pdb_2(){
        //complex1
        Complex complex1 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P22301"))));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("Q08334"))));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("Q13651"))));
        //complex2
        Complex complex2 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex2.setInteractionType(new DefaultCvTerm("phosphorylation"));

        DefaultProtein protein1 = new DefaultProtein("i10r1_human",
                XrefUtils.createEnsemblIdentity("ENSP00000227752.4"));
        protein1.getIdentifiers().add(XrefUtils.createUniprotIdentity("Q13651"));
        DefaultProtein protein2 = new DefaultProtein("i10r2_human",
                XrefUtils.createEnsemblIdentity("ENSP00000290200.2"));
        protein2.getIdentifiers().add(XrefUtils.createUniprotIdentity("Q08334"));
        DefaultProtein protein3 = new DefaultProtein("il10_human",
                XrefUtils.createEnsemblIdentity("ENSP00000412237.1"));
        protein3.getIdentifiers().add(XrefUtils.createUniprotIdentity("P22301"));

        complex2.addParticipant(new DefaultModelledParticipant(protein1));
        complex2.addParticipant(new DefaultModelledParticipant(protein1));
        complex2.addParticipant(new DefaultModelledParticipant(protein2));
        complex2.addParticipant(new DefaultModelledParticipant(protein2));
        complex2.addParticipant(new DefaultModelledParticipant(protein3));
        complex2.addParticipant(new DefaultModelledParticipant(protein3));

        Assert.assertEquals(0, collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()));
    }

    @Test
    public void test_pdb_3(){
        //complex1
        Complex complex1 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P31785"))));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("Q9HBE4"))));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("Q9HBE5"))));
        //complex2
        Complex complex2 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex2.setInteractionType(new DefaultCvTerm("phosphorylation"));

        DefaultProtein protein1 = new DefaultProtein("il21_human",
                XrefUtils.createEnsemblIdentity("ENSP00000497915.1"));
        protein1.getIdentifiers().add(XrefUtils.createUniprotIdentity("Q9HBE4"));
        DefaultProtein protein2 = new DefaultProtein("il21r_human",
                XrefUtils.createEnsemblIdentity("ENSP00000338010.3"));
        protein2.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000379103.4"));
        protein2.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000456707.1"));
        protein2.getIdentifiers().add(XrefUtils.createUniprotIdentity("Q9HBE5"));
        DefaultProtein protein3 = new DefaultProtein("il2rg_human",
                XrefUtils.createUniprotIdentity("P31785"));
        protein3.getIdentifiers().add(XrefUtils.createEnsemblIdentity("ENSP00000363318.3"));

        complex2.addParticipant(new DefaultModelledParticipant(protein1));
        complex2.addParticipant(new DefaultModelledParticipant(protein2));
        complex2.addParticipant(new DefaultModelledParticipant(protein3));

        Assert.assertEquals(0, collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()));
    }

    @Test
    public void test_protein_and_molecule_set(){
        Protein commonProtein = new DefaultProtein("test protein", XrefUtils.createUniprotIdentity("P0DP24"));

        //complex1
        Complex complex1 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex1.addParticipant(new DefaultModelledParticipant(commonProtein));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("P53355"))));

        //complex2
        Complex complex2 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex2.setInteractionType(new DefaultCvTerm("phosphorylation"));

        DefaultInteractorPool interactorPool = new DefaultInteractorPool("calm_human");
        interactorPool.getIdentifiers().add(XrefUtils.createUniprotIdentity("P0DP23"));
        interactorPool.getIdentifiers().add(XrefUtils.createUniprotIdentity("P0DP24"));
        DefaultProtein protein1 = new DefaultProtein("calm2_human",
                XrefUtils.createEnsemblIdentity("ENSP00000272298.7"));
        protein1.getIdentifiers().add(XrefUtils.createUniprotIdentity("P0DP24"));
        interactorPool.add(protein1);
        DefaultProtein protein2 = new DefaultProtein("calm1_human",
                XrefUtils.createEnsemblIdentity("ENSP00000349467.4"));
        protein2.getIdentifiers().add(XrefUtils.createUniprotIdentity("P0DP23"));
        interactorPool.add(protein2);
        complex2.addParticipant(new DefaultModelledParticipant(interactorPool));

        DefaultProtein protein = new DefaultProtein("dapk1_human",
                XrefUtils.createEnsemblIdentity("ENSP00000350785.5"));
        protein.getIdentifiers().add(XrefUtils.createUniprotIdentity("P53355"));
        complex2.addParticipant(new DefaultModelledParticipant(protein));

        complex2.addParticipant(new DefaultModelledParticipant(commonProtein));

        Assert.assertEquals(0, collectionComparator.compare(complex1.getComparableParticipants(), complex2.getComparableParticipants()));
    }
}

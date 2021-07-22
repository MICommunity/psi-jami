package psidev.psi.mi.jami.model.impl;

import org.junit.Assert;
import org.junit.Test;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.*;

import java.util.Collection;

/**
 * Unit tester for DefaultComplex
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>31/05/13</pre>
 */

public class DefaultComplexTest {

    @Test
    public void create_empty_complex() {

        Complex interaction = new DefaultComplex("test complex");

        Assert.assertEquals("test complex", interaction.getShortName());
        Assert.assertNull(interaction.getSource());
        Assert.assertNotNull(interaction.getParticipants());
        Assert.assertNotNull(interaction.getCooperativeEffects());
        Assert.assertNotNull(interaction.getInteractionEvidences());
        Assert.assertNotNull(interaction.getModelledConfidences());
        Assert.assertNotNull(interaction.getModelledParameters());
        Assert.assertEquals(CvTermUtils.createComplexInteractorType(), interaction.getInteractorType());

        interaction = new DefaultComplex("test", (String) null);
        Assert.assertEquals(CvTermUtils.createComplexInteractorType(), interaction.getInteractorType());
    }

    @Test
    public void create_complex_interactor_type_null() {

        Complex interactor = new DefaultComplex("test", CvTermUtils.createUnknownInteractorType());
        Assert.assertEquals(CvTermUtils.createUnknownInteractorType(), interactor.getInteractorType());

        interactor.setInteractorType(null);
        Assert.assertEquals(CvTermUtils.createComplexInteractorType(), interactor.getInteractorType());

        interactor.setInteractorType(CvTermUtils.createUnknownInteractorType());
        Assert.assertEquals(CvTermUtils.createUnknownInteractorType(), interactor.getInteractorType());
    }

    @Test
    public void test_add_remove_modelledParticipants() {
        Complex interaction = new DefaultComplex("test interaction");
        ModelledParticipant participant = new DefaultModelledParticipant(InteractorUtils.createUnknownBasicInteractor());

        Assert.assertNull(participant.getInteraction());

        // add participant and set modelledInteraction
        interaction.addParticipant(participant);
        Assert.assertEquals(interaction, participant.getInteraction());
        Assert.assertEquals(1, interaction.getParticipants().size());

        // remove modelled participant and set interaction to null
        interaction.removeParticipant(participant);
        Assert.assertNull(participant.getInteraction());
        Assert.assertEquals(0, interaction.getParticipants().size());

        // simply add modelled participant
        interaction.getParticipants().add(participant);
        Assert.assertNull(participant.getInteraction());
        Assert.assertEquals(1, interaction.getParticipants().size());

        // simply remove modelled participant
        interaction.getParticipants().remove(participant);
        Assert.assertNull(participant.getInteraction());
        Assert.assertEquals(0, interaction.getParticipants().size());
    }

    @Test
    public void test_add_remove_rigid() {
        Complex interaction = new DefaultComplex("test interaction");
        Assert.assertNull(interaction.getRigid());

        interaction.getChecksums().add(ChecksumUtils.createRigid("11aa1"));
        Assert.assertEquals("11aa1", interaction.getRigid());

        interaction.getChecksums().remove(ChecksumUtils.createRigid("11aa1"));
        Assert.assertNull(interaction.getRigid());

        interaction.getChecksums().clear();
        Assert.assertNull(interaction.getRigid());

        interaction.setRigid("11aa1");
        Assert.assertEquals("11aa1", interaction.getRigid());
        Assert.assertEquals(1, interaction.getChecksums().size());
        Assert.assertEquals(ChecksumUtils.createRigid("11aa1"), interaction.getChecksums().iterator().next());

        interaction.getChecksums().add(ChecksumUtils.createRigid("11aa2"));
        Assert.assertEquals("11aa1", interaction.getRigid());
        Assert.assertEquals(2, interaction.getChecksums().size());

        interaction.getChecksums().clear();
        Assert.assertNull(interaction.getRigid());
        Assert.assertEquals(0, interaction.getChecksums().size());
    }

    @Test
    public void test_add_remove_physical_properties() {
        Complex interaction = new DefaultComplex("test interaction");
        Assert.assertNull(interaction.getPhysicalProperties());

        interaction.getAnnotations().add(AnnotationUtils.createAnnotation(Annotation.COMPLEX_PROPERTIES, Annotation.COMPLEX_PROPERTIES_MI, "molecular weight x"));
        Assert.assertEquals("molecular weight x", interaction.getPhysicalProperties());

        interaction.getAnnotations().remove(AnnotationUtils.createAnnotation(Annotation.COMPLEX_PROPERTIES, Annotation.COMPLEX_PROPERTIES_MI, "molecular weight x"));
        Assert.assertNull(interaction.getPhysicalProperties());

        interaction.getAnnotations().clear();
        Assert.assertNull(interaction.getPhysicalProperties());

        interaction.setPhysicalProperties("molecular weight x");
        Assert.assertEquals("molecular weight x", interaction.getPhysicalProperties());
        Assert.assertEquals(1, interaction.getAnnotations().size());
        Assert.assertEquals(AnnotationUtils.createAnnotation(Annotation.COMPLEX_PROPERTIES, Annotation.COMPLEX_PROPERTIES_MI, "molecular weight x"), interaction.getAnnotations().iterator().next());

        interaction.getAnnotations().add(AnnotationUtils.createAnnotation(Annotation.COMPLEX_PROPERTIES, Annotation.COMPLEX_PROPERTIES_MI, "molecular weight x2"));
        Assert.assertEquals("molecular weight x", interaction.getPhysicalProperties());
        Assert.assertEquals(2, interaction.getAnnotations().size());

        interaction.getAnnotations().clear();
        Assert.assertNull(interaction.getPhysicalProperties());
        Assert.assertEquals(0, interaction.getAnnotations().size());
    }

    @Test
    public void test_add_remove_complex_ac() {
        Complex interaction = new DefaultComplex("test interaction");
        Assert.assertNull(interaction.getComplexAc());

        interaction.getXrefs().add(XrefUtils.createXrefWithQualifier(Xref.COMPLEX_PORTAL, Xref.COMPLEX_PORTAL_MI, "CPX-1", Xref.COMPLEX_PRIMARY, Xref.COMPLEX_PRIMARY_MI));
        Assert.assertEquals("CPX-1", interaction.getComplexAc());

        interaction.getXrefs().remove(XrefUtils.createXrefWithQualifier(Xref.COMPLEX_PORTAL, Xref.COMPLEX_PORTAL_MI, "CPX-1", Xref.COMPLEX_PRIMARY, Xref.COMPLEX_PRIMARY_MI));
        Assert.assertNull(interaction.getComplexAc());

        interaction.getXrefs().clear();
        Assert.assertNull(interaction.getComplexAc());

        interaction.assignComplexAc("CPX-1");
        Assert.assertEquals("CPX-1", interaction.getComplexAc());
        Assert.assertEquals(1, interaction.getXrefs().size());
        Assert.assertEquals(XrefUtils.createXrefWithQualifier(Xref.COMPLEX_PORTAL, Xref.COMPLEX_PORTAL_MI, "CPX-1", Xref.COMPLEX_PRIMARY, Xref.COMPLEX_PRIMARY_MI), interaction.getXrefs().iterator().next());

        interaction.getXrefs().add(XrefUtils.createXrefWithQualifier(Xref.COMPLEX_PORTAL, Xref.COMPLEX_PORTAL_MI, "CPX-2", Xref.COMPLEX_PRIMARY, Xref.COMPLEX_PRIMARY_MI));
        Assert.assertEquals("CPX-1", interaction.getComplexAc());
        Assert.assertEquals(2, interaction.getXrefs().size());

        interaction.getXrefs().clear();
        Assert.assertNull(interaction.getComplexAc());
        Assert.assertEquals(0, interaction.getXrefs().size());
    }

    @Test
    public void test_get_comparable_participants() {
        Complex complexAsAnInteractor1 = new DefaultComplex("complex_interactor", new DefaultCvTerm("protein complex"));
        complexAsAnInteractor1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        Stoichiometry stc = new DefaultStoichiometry(1, 3);
        complexAsAnInteractor1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("P12345")), stc));
        complexAsAnInteractor1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test2 protein",
                XrefUtils.createUniprotIdentity("P12347"))));

        Complex complex1 = new DefaultComplex("test", new DefaultCvTerm("protein complex"));
        complex1.setInteractionType(new DefaultCvTerm("phosphorylation"));

        ModelledParticipant modelledParticipant1=new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12346")));
        modelledParticipant1.addFeature(new DefaultModelledFeature());
        modelledParticipant1.addFeature(new DefaultModelledFeature());

        ModelledParticipant modelledParticipant2=new DefaultModelledParticipant(new DefaultProtein("test1 protein",
                XrefUtils.createUniprotIdentity("P12345")), new DefaultStoichiometry(1, 3));

        complex1.addParticipant(modelledParticipant2);
        complex1.addParticipant(modelledParticipant1);
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultNucleicAcid("test")));
        complex1.addParticipant(new DefaultModelledParticipant(complexAsAnInteractor1, new DefaultStoichiometry(2, 2)));
        InteractorPool pool=new DefaultInteractorPool("pool_or_set");
        pool.add(new DefaultProtein("test3 protein",
                XrefUtils.createUniprotIdentity("P12345")));
        complex1.addParticipant(new DefaultModelledParticipant(pool,new DefaultStoichiometry(1, 3)));

        Collection<ModelledComparableParticipant> comparableParticipants = complex1.getComparableParticipants();
        Assert.assertEquals(3, comparableParticipants.size());

        boolean testedCase1 = false;
        boolean testedCase2 = false;
        boolean testedCase3 = false;

        try {
            for (ModelledComparableParticipant comparableParticipant : comparableParticipants) {
                if (comparableParticipant.getProteinId().equals("P12345")) {
                    Assert.assertEquals(4,comparableParticipant.getStoichiometry());
                    testedCase1 = true;
                } else if (comparableParticipant.getProteinId().equals("P12347")) {
                    Assert.assertEquals(0, comparableParticipant.getStoichiometry());
                    testedCase2 = true;
                } else if (comparableParticipant.getProteinId().equals("P12346")) {
                    Assert.assertEquals(0, comparableParticipant.getStoichiometry());
                    testedCase3 = true;
                }
            }
        } catch (Exception e) {
            Assert.assertFalse("Expected comparable participants absent", true);
        }

        Assert.assertTrue("Expected comparable participants absent", (testedCase1 && testedCase2 && testedCase3));
    }
}

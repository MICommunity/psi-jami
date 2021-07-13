package psidev.psi.mi.jami.utils.comparator.participant;

import org.junit.Assert;
import org.junit.Test;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.model.impl.*;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.InteractorUtils;
import psidev.psi.mi.jami.utils.XrefUtils;

/**
 * Unit tester for UnambiguousParticipantBaseComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/05/13</pre>
 */

public class UnambiguousParticipantBaseComparatorTest {

    private UnambiguousParticipantBaseComparator comparator = new UnambiguousParticipantBaseComparator();

    @Test
    public void test_participant_null_after() {
        Participant participant1 = null;
        Participant participant2 = new DefaultParticipant(InteractorUtils.createUnknownBasicInteractor());

        Assert.assertTrue(comparator.compare(participant1, participant2) > 0);
        Assert.assertTrue(comparator.compare(participant2, participant1) < 0);

        Assert.assertFalse(UnambiguousParticipantBaseComparator.areEquals(participant1, participant2));
        Assert.assertTrue(UnambiguousParticipantBaseComparator.hashCode(participant1) != UnambiguousParticipantBaseComparator.hashCode(participant2));
    }

    @Test
    public void test_different_interactors() {
        Participant participant1 = new DefaultParticipant(new DefaultProtein("test", XrefUtils.createUniprotIdentity("P12345")));
        Participant participant2 = new DefaultParticipant(new DefaultProtein("test", XrefUtils.createUniprotIdentity("P12346")));

        Assert.assertTrue(comparator.compare(participant1, participant2) < 0);
        Assert.assertTrue(comparator.compare(participant2, participant1) > 0);

        Assert.assertFalse(UnambiguousParticipantBaseComparator.areEquals(participant1, participant2));
        Assert.assertTrue(UnambiguousParticipantBaseComparator.hashCode(participant1) != UnambiguousParticipantBaseComparator.hashCode(participant2));

        // two exact identifiers but one protein has a different interactor type, should be equals because rely on UnambiguousInteractorComparator
        participant1 = new DefaultParticipant(new DefaultProtein("test", CvTermUtils.createUnknownInteractorType(), XrefUtils.createUniprotIdentity("P12345")));
        participant2 = new DefaultParticipant(new DefaultProtein("test", XrefUtils.createUniprotIdentity("P12345")));

        Assert.assertTrue(comparator.compare(participant1, participant2) == 0);
        Assert.assertTrue(comparator.compare(participant2, participant1) == 0);

        Assert.assertTrue(UnambiguousParticipantBaseComparator.areEquals(participant1, participant2));
        Assert.assertTrue(UnambiguousParticipantBaseComparator.hashCode(participant1) == UnambiguousParticipantBaseComparator.hashCode(participant2));
    }

    @Test
    public void test_same_interactors() {
        Participant participant1 = new DefaultParticipant(new DefaultProtein("test", XrefUtils.createUniprotIdentity("P12345")));
        Participant participant2 = new DefaultParticipant(new DefaultProtein("test", XrefUtils.createUniprotIdentity("P12345")));

        Assert.assertTrue(comparator.compare(participant1, participant2) == 0);
        Assert.assertTrue(comparator.compare(participant2, participant1) == 0);

        Assert.assertTrue(UnambiguousParticipantBaseComparator.areEquals(participant1, participant2));
        Assert.assertTrue(UnambiguousParticipantBaseComparator.hashCode(participant1) == UnambiguousParticipantBaseComparator.hashCode(participant2));
    }

    @Test
    public void test_same_interactor_different_roles() {
        Participant participant1 = new DefaultParticipant(new DefaultProtein("test", XrefUtils.createUniprotIdentity("P12345")));
        Participant participant2 = new DefaultParticipant(new DefaultProtein("test", XrefUtils.createUniprotIdentity("P12345")));

        participant1.setBiologicalRole(new DefaultCvTerm("enzyme target"));
        participant2.setBiologicalRole(new DefaultCvTerm("enzyme"));

        Assert.assertTrue(comparator.compare(participant1, participant2) > 0);
        Assert.assertTrue(comparator.compare(participant2, participant1) < 0);

        Assert.assertFalse(UnambiguousParticipantBaseComparator.areEquals(participant1, participant2));
        Assert.assertTrue(UnambiguousParticipantBaseComparator.hashCode(participant1) != UnambiguousParticipantBaseComparator.hashCode(participant2));
    }

    @Test
    public void test_same_roles() {
        Participant participant1 = new DefaultParticipant(new DefaultProtein("test", XrefUtils.createUniprotIdentity("P12345")));
        Participant participant2 = new DefaultParticipant(new DefaultProtein("test", XrefUtils.createUniprotIdentity("P12345")));

        participant1.setBiologicalRole(new DefaultCvTerm("enzyme"));
        participant2.setBiologicalRole(new DefaultCvTerm("enzyme"));

        Assert.assertTrue(comparator.compare(participant1, participant2) == 0);
        Assert.assertTrue(comparator.compare(participant2, participant1) == 0);

        Assert.assertTrue(UnambiguousParticipantBaseComparator.areEquals(participant1, participant2));
        Assert.assertTrue(UnambiguousParticipantBaseComparator.hashCode(participant1) == UnambiguousParticipantBaseComparator.hashCode(participant2));
    }

    @Test
    public void test_same_roles_different_stoichiometry() {
        Participant participant1 = new DefaultParticipant(new DefaultProtein("test", XrefUtils.createUniprotIdentity("P12345")));
        Participant participant2 = new DefaultParticipant(new DefaultProtein("test", XrefUtils.createUniprotIdentity("P12345")));

        participant1.setStoichiometry(1);
        participant2.setStoichiometry(2);

        Assert.assertTrue(comparator.compare(participant1, participant2) < 0);
        Assert.assertTrue(comparator.compare(participant2, participant1) > 0);

        Assert.assertFalse(UnambiguousParticipantBaseComparator.areEquals(participant1, participant2));
        Assert.assertTrue(UnambiguousParticipantBaseComparator.hashCode(participant1) != UnambiguousParticipantBaseComparator.hashCode(participant2));
    }

    @Test
    public void test_same_stoichiometry() {
        Participant participant1 = new DefaultParticipant(new DefaultProtein("test", XrefUtils.createUniprotIdentity("P12345")));
        Participant participant2 = new DefaultParticipant(new DefaultProtein("test", XrefUtils.createUniprotIdentity("P12345")));

        participant1.setBiologicalRole(new DefaultCvTerm("enzyme"));
        participant2.setBiologicalRole(new DefaultCvTerm("enzyme"));
        participant1.setStoichiometry(1);
        participant2.setStoichiometry(1);

        Assert.assertTrue(comparator.compare(participant1, participant2) == 0);
        Assert.assertTrue(comparator.compare(participant2, participant1) == 0);

        Assert.assertTrue(UnambiguousParticipantBaseComparator.areEquals(participant1, participant2));
        Assert.assertTrue(UnambiguousParticipantBaseComparator.hashCode(participant1) == UnambiguousParticipantBaseComparator.hashCode(participant2));
    }

    @Test
    public void test_same_interactors_different_ignored_stoichiometry() {
        ParticipantEvidence participant1 = new DefaultParticipantEvidence(new DefaultProtein("test protein"), new DefaultStoichiometry(2, 2));
        ParticipantEvidence participant2 = new DefaultParticipantEvidence(new DefaultProtein("test protein"), new DefaultStoichiometry(2, 3));

        Assert.assertTrue(comparator.compare(participant1, participant2) != 0);
        Assert.assertTrue(comparator.compare(participant2, participant1) != 0);

        comparator.getEntityBaseComparator().setIgnoreStoichiometry(true);

        Assert.assertTrue(comparator.compare(participant1, participant2) == 0);
        Assert.assertTrue(comparator.compare(participant2, participant1) == 0);

        comparator.getEntityBaseComparator().setIgnoreStoichiometry(false);
    }
}

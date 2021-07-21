package psidev.psi.mi.jami.utils.comparator.participant;

import org.junit.Assert;
import org.junit.Test;
import psidev.psi.mi.jami.model.ModelledComparableParticipant;

public class ModelledComparableParticipantTest {

    @Test
    public void testComparableParticipantComparison() {
        ModelledComparableParticipantComparator comparator = new ModelledComparableParticipantComparator();
        {
            ModelledComparableParticipant modelledComparableParticipant1 = new ModelledComparableParticipant("UNIPROT_ID1", 1);
            ModelledComparableParticipant modelledComparableParticipant2 = new ModelledComparableParticipant("UNIPROT_ID2", 1);
            Assert.assertEquals(-1, comparator.compare(modelledComparableParticipant1, modelledComparableParticipant2));
            Assert.assertEquals(1, comparator.compare(modelledComparableParticipant2, modelledComparableParticipant1));
        }

        {
            ModelledComparableParticipant modelledComparableParticipant1 = new ModelledComparableParticipant("UNIPROT_ID1", 1);
            ModelledComparableParticipant modelledComparableParticipant2 = new ModelledComparableParticipant("UNIPROT_ID1", 2);
            Assert.assertEquals(-1, comparator.compare(modelledComparableParticipant1, modelledComparableParticipant2));
            Assert.assertEquals(1, comparator.compare(modelledComparableParticipant2, modelledComparableParticipant1));
        }

        {
            ModelledComparableParticipant modelledComparableParticipant1 = new ModelledComparableParticipant("UNIPROT_ID1", 1);
            ModelledComparableParticipant modelledComparableParticipant2 = new ModelledComparableParticipant("UNIPROT_ID1", 1);
            Assert.assertEquals(0, comparator.compare(modelledComparableParticipant1, modelledComparableParticipant2));
            Assert.assertEquals(0, comparator.compare(modelledComparableParticipant2, modelledComparableParticipant1));
        }

        {
            // comparator has to be intimated to ignore stoichiometry if needed
            comparator.setIgnoreStoichiometry(true);
            ModelledComparableParticipant modelledComparableParticipant1 = new ModelledComparableParticipant("UNIPROT_ID1", 1);
            ModelledComparableParticipant modelledComparableParticipant2 = new ModelledComparableParticipant("UNIPROT_ID1", 2);
            Assert.assertEquals(0, comparator.compare(modelledComparableParticipant1, modelledComparableParticipant2));
            Assert.assertEquals(0, comparator.compare(modelledComparableParticipant2, modelledComparableParticipant1));
        }

        // comparator has to be intimated to consider stoichiometry again
        {
            comparator.setIgnoreStoichiometry(false);
            ModelledComparableParticipant modelledComparableParticipant1 = new ModelledComparableParticipant("UNIPROT_ID1", 1);
            ModelledComparableParticipant modelledComparableParticipant2 = new ModelledComparableParticipant("UNIPROT_ID1", 2);
            Assert.assertEquals(-1, comparator.compare(modelledComparableParticipant1, modelledComparableParticipant2));
            Assert.assertEquals(1, comparator.compare(modelledComparableParticipant2, modelledComparableParticipant1));
        }
    }
}

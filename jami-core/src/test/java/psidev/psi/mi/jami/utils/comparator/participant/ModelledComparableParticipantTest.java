package psidev.psi.mi.jami.utils.comparator.participant;

import org.junit.Assert;
import org.junit.Test;
import psidev.psi.mi.jami.model.ModelledComparableParticipant;
import psidev.psi.mi.jami.utils.CvTermUtils;

import java.util.List;

public class ModelledComparableParticipantTest {

    @Test
    public void testComparableParticipantComparison() {
        ModelledComparableParticipantComparator comparator = new ModelledComparableParticipantComparator();
        {
            ModelledComparableParticipant modelledComparableParticipant1 = new ModelledComparableParticipant(
                    "UNIPROT_ID1", List.of(), 1, CvTermUtils.createProteinInteractorType());
            ModelledComparableParticipant modelledComparableParticipant2 = new ModelledComparableParticipant(
                    "UNIPROT_ID2", List.of(), 1, CvTermUtils.createProteinInteractorType());
            Assert.assertEquals(-1, comparator.compare(modelledComparableParticipant1, modelledComparableParticipant2));
            Assert.assertEquals(1, comparator.compare(modelledComparableParticipant2, modelledComparableParticipant1));
        }

        {
            ModelledComparableParticipant modelledComparableParticipant1 = new ModelledComparableParticipant(
                    "UNIPROT_ID1", List.of(), 1, CvTermUtils.createProteinInteractorType());
            ModelledComparableParticipant modelledComparableParticipant2 = new ModelledComparableParticipant(
                    "UNIPROT_ID1", List.of(), 2, CvTermUtils.createProteinInteractorType());
            Assert.assertEquals(-1, comparator.compare(modelledComparableParticipant1, modelledComparableParticipant2));
            Assert.assertEquals(1, comparator.compare(modelledComparableParticipant2, modelledComparableParticipant1));
        }

        {
            ModelledComparableParticipant modelledComparableParticipant1 = new ModelledComparableParticipant(
                    "UNIPROT_ID1", List.of(), 1, CvTermUtils.createProteinInteractorType());
            ModelledComparableParticipant modelledComparableParticipant2 = new ModelledComparableParticipant(
                    "UNIPROT_ID1", List.of(), 1, CvTermUtils.createProteinInteractorType());
            Assert.assertEquals(0, comparator.compare(modelledComparableParticipant1, modelledComparableParticipant2));
            Assert.assertEquals(0, comparator.compare(modelledComparableParticipant2, modelledComparableParticipant1));
        }

        {
            // comparator has to be intimated to ignore stoichiometry if needed
            comparator.setIgnoreStoichiometry(true);
            ModelledComparableParticipant modelledComparableParticipant1 = new ModelledComparableParticipant(
                    "UNIPROT_ID1", List.of(), 1, CvTermUtils.createProteinInteractorType());
            ModelledComparableParticipant modelledComparableParticipant2 = new ModelledComparableParticipant(
                    "UNIPROT_ID1", List.of(), 2, CvTermUtils.createProteinInteractorType());
            Assert.assertEquals(0, comparator.compare(modelledComparableParticipant1, modelledComparableParticipant2));
            Assert.assertEquals(0, comparator.compare(modelledComparableParticipant2, modelledComparableParticipant1));
        }

        // comparator has to be intimated to consider stoichiometry again
        {
            comparator.setIgnoreStoichiometry(false);
            ModelledComparableParticipant modelledComparableParticipant1 = new ModelledComparableParticipant(
                    "UNIPROT_ID1", List.of(), 1, CvTermUtils.createProteinInteractorType());
            ModelledComparableParticipant modelledComparableParticipant2 = new ModelledComparableParticipant(
                    "UNIPROT_ID1", List.of(), 2, CvTermUtils.createProteinInteractorType());
            Assert.assertEquals(-1, comparator.compare(modelledComparableParticipant1, modelledComparableParticipant2));
            Assert.assertEquals(1, comparator.compare(modelledComparableParticipant2, modelledComparableParticipant1));
        }
    }
}

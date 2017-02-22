package psidev.psi.mi.jami.utils.comparator.interaction;

import org.junit.Assert;
import org.junit.Test;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.model.impl.DefaultModelledInteraction;
import psidev.psi.mi.jami.model.impl.DefaultSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Unit tester for UnambiguousExactCuratedModelledInteractionComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>31/05/13</pre>
 */

public class UnambiguousExactCuratedModelledInteractionComparatorTest {

    private UnambiguousExactCuratedModelledInteractionComparator comparator = new UnambiguousExactCuratedModelledInteractionComparator();

    @Test
    public void test_modelled_interaction_null_after(){
        ModelledInteraction interaction1 = null;
        ModelledInteraction interaction2 = new DefaultModelledInteraction("test");

        Assert.assertTrue(comparator.compare(interaction1, interaction2) > 0);
        Assert.assertTrue(comparator.compare(interaction2, interaction1) < 0);

        Assert.assertFalse(UnambiguousExactCuratedModelledInteractionComparator.areEquals(interaction1, interaction2));
    }

    @Test
    public void test_different_basic_properties() throws ParseException {
        ModelledInteraction interaction1 = new DefaultModelledInteraction("test", new DefaultCvTerm("prediction"));
        interaction1.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/02/2016"));
        ModelledInteraction interaction2 = new DefaultModelledInteraction("test", new DefaultCvTerm("prediction"));

        Assert.assertTrue(comparator.compare(interaction1, interaction2) < 0);
        Assert.assertTrue(comparator.compare(interaction2, interaction1) > 0);

        Assert.assertFalse(UnambiguousExactCuratedModelledInteractionComparator.areEquals(interaction1, interaction2));
    }

    @Test
    public void test_same_basic_properties() throws ParseException {
        ModelledInteraction interaction1 = new DefaultModelledInteraction("test", new DefaultCvTerm("prediction"));
        interaction1.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/02/2016"));
        ModelledInteraction interaction2 = new DefaultModelledInteraction("test", new DefaultCvTerm("prediction"));
        interaction2.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/02/2016"));

        Assert.assertTrue(comparator.compare(interaction1, interaction2) == 0);
        Assert.assertTrue(comparator.compare(interaction2, interaction1) == 0);

        Assert.assertTrue(UnambiguousExactCuratedModelledInteractionComparator.areEquals(interaction1, interaction2));
    }

    @Test
    public void test_different_sources() throws ParseException {
        ModelledInteraction interaction1 = new DefaultModelledInteraction("test", new DefaultCvTerm("prediction"));
        interaction1.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/02/2016"));
        interaction1.setSource(new DefaultSource("intact"));
        ModelledInteraction interaction2 = new DefaultModelledInteraction("test", new DefaultCvTerm("prediction"));
        interaction2.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/02/2016"));
        interaction2.setSource(new DefaultSource("mint"));

        Assert.assertTrue(comparator.compare(interaction1, interaction2) < 0);
        Assert.assertTrue(comparator.compare(interaction2, interaction1) > 0);

        Assert.assertFalse(UnambiguousExactCuratedModelledInteractionComparator.areEquals(interaction1, interaction2));
    }

    @Test
    public void test_same_source() throws ParseException{
        ModelledInteraction interaction1 = new DefaultModelledInteraction("test", new DefaultCvTerm("prediction"));
        interaction1.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/02/2016"));
        interaction1.setSource(new DefaultSource("mint"));
        ModelledInteraction interaction2 = new DefaultModelledInteraction("test", new DefaultCvTerm("prediction"));
        interaction2.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/02/2016"));
        interaction2.setSource(new DefaultSource("mint"));

        Assert.assertTrue(comparator.compare(interaction1, interaction2) == 0);
        Assert.assertTrue(comparator.compare(interaction2, interaction1) == 0);

        Assert.assertTrue(UnambiguousExactCuratedModelledInteractionComparator.areEquals(interaction1, interaction2));
    }
}

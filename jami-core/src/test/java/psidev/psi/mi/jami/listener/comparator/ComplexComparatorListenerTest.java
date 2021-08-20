package psidev.psi.mi.jami.listener.comparator;

import org.junit.Assert;
import org.junit.Test;
import psidev.psi.mi.jami.listener.comparator.event.ComplexComparisonEvent;
import psidev.psi.mi.jami.listener.comparator.impl.ComplexComparatorListenerImpl;
import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.model.impl.*;
import psidev.psi.mi.jami.utils.XrefUtils;

import java.util.ArrayList;

public class ComplexComparatorListenerTest {

    @Test
    public void onDifferentValueTest() {
        //complex1
        Complex complex1 = new DefaultComplex("test1", new DefaultCvTerm("protein complex"));
        complex1.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex1.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12345")), new DefaultStoichiometry(1, 6)));

        //complex2, different with complex1 only in stoichiometry
        Complex complex2 = new DefaultComplex("test2", new DefaultCvTerm("protein complex"));
        complex2.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex2.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12345")), new DefaultStoichiometry(2, 6)));

        // raise listener event 1
        ComplexComparatorListenerImpl complexComparatorListener = new ComplexComparatorListenerImpl();
        ComplexComparisonEvent complexComparisonEvent =
                new ComplexComparisonEvent(complex1, complex2,
                        ComplexComparisonEvent.EventType.ONLY_STOICHIOMETRY_DIFFERENT);
        complexComparatorListener.onDifferentValue(complexComparisonEvent);

        //check listener observations
        Assert.assertEquals(1, complexComparatorListener.getComplexComparatorObservations().size());
        Assert.assertEquals(1, complexComparatorListener.getComplexComparatorObservations().get(0).getDifferentObservations().size());

        ArrayList<ComplexComparisonEvent> complexComparisonEvents = (ArrayList<ComplexComparisonEvent>) complexComparatorListener.getComplexComparatorObservations().get(0).getDifferentObservations();
        Assert.assertEquals(ComplexComparisonEvent.EventType.ONLY_STOICHIOMETRY_DIFFERENT,
                complexComparisonEvents.get(0).getEventType());

        // raise listener event 2
        ComplexComparisonEvent complexComparisonEvent2 =
                new ComplexComparisonEvent(complex1, complex2,
                        ComplexComparisonEvent.EventType.ONLY_STOICHIOMETRY_DIFFERENT);
        complexComparatorListener.onDifferentValue(complexComparisonEvent2);

        //check listener observations
        Assert.assertEquals(1, complexComparatorListener.getComplexComparatorObservations().size());
        Assert.assertEquals(2, complexComparatorListener.getComplexComparatorObservations().get(0).getDifferentObservations().size());
        ArrayList<ComplexComparisonEvent> complexComparisonEvents2 = (ArrayList<ComplexComparisonEvent>)
                complexComparatorListener.getComplexComparatorObservations().get(0).getDifferentObservations();
        Assert.assertEquals(ComplexComparisonEvent.EventType.ONLY_STOICHIOMETRY_DIFFERENT,
                complexComparisonEvents2.get(1).getEventType());
        Assert.assertEquals(ComplexComparisonEvent.EventType.ONLY_STOICHIOMETRY_DIFFERENT,
                complexComparisonEvents2.get(0).getEventType());
        Assert.assertEquals(complex1.getShortName(),
                complexComparatorListener.getComplexComparatorObservations().get(0).getComplex1().getShortName());
        Assert.assertEquals(complex2.getShortName(),
                complexComparatorListener.getComplexComparatorObservations().get(0).getComplex2().getShortName());

        //complex3
        Complex complex3 = new DefaultComplex("test3", new DefaultCvTerm("protein complex"));
        complex3.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex3.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12345")), new DefaultStoichiometry(1, 6)));

        //complex4, different with complex3 only in stoichiometry
        Complex complex4 = new DefaultComplex("test4", new DefaultCvTerm("protein complex"));
        complex4.setInteractionType(new DefaultCvTerm("phosphorylation"));
        complex4.addParticipant(new DefaultModelledParticipant(new DefaultProtein("test protein",
                XrefUtils.createUniprotIdentity("P12345")), new DefaultStoichiometry(2, 6)));

        // raise listener event 3
        ComplexComparisonEvent complexComparisonEvent3 =
                new ComplexComparisonEvent(complex3, complex4,
                        ComplexComparisonEvent.EventType.ONLY_STOICHIOMETRY_DIFFERENT);
        complexComparatorListener.onDifferentValue(complexComparisonEvent3);

        //check listener observations
        Assert.assertEquals(2, complexComparatorListener.getComplexComparatorObservations().size());
        Assert.assertEquals(2, complexComparatorListener.getComplexComparatorObservations().get(0).getDifferentObservations().size());
        ArrayList<ComplexComparisonEvent> complexComparisonEvents3 = (ArrayList<ComplexComparisonEvent>)
                complexComparatorListener.getComplexComparatorObservations().get(0).getDifferentObservations();
        Assert.assertEquals(ComplexComparisonEvent.EventType.ONLY_STOICHIOMETRY_DIFFERENT,
                complexComparisonEvents3.get(1).getEventType());
        Assert.assertEquals(ComplexComparisonEvent.EventType.ONLY_STOICHIOMETRY_DIFFERENT,
                complexComparisonEvents3.get(0).getEventType());
        Assert.assertEquals(1, complexComparatorListener.getComplexComparatorObservations().get(1).getDifferentObservations().size());
        Assert.assertEquals(ComplexComparisonEvent.EventType.ONLY_STOICHIOMETRY_DIFFERENT,
                complexComparatorListener.getComplexComparatorObservations().get(1).getDifferentObservations().iterator().next().getEventType());
        Assert.assertEquals(complex1.getShortName(),
                complexComparatorListener.getComplexComparatorObservations().get(0).getComplex1().getShortName());
        Assert.assertEquals(complex2.getShortName(),
                complexComparatorListener.getComplexComparatorObservations().get(0).getComplex2().getShortName());
        Assert.assertEquals(complex3.getShortName(),
                complexComparatorListener.getComplexComparatorObservations().get(1).getComplex1().getShortName());
        Assert.assertEquals(complex4.getShortName(),
                complexComparatorListener.getComplexComparatorObservations().get(1).getComplex2().getShortName());
    }
}

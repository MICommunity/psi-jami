package psidev.psi.mi.jami.listener.comparator.analyzer;

import psidev.psi.mi.jami.listener.comparator.ComplexComparatorListener;
import psidev.psi.mi.jami.listener.comparator.event.ComplexComparisonEvent;
import psidev.psi.mi.jami.listener.comparator.observer.ComplexComparatorObserver;
import psidev.psi.mi.jami.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Collection;

public class ComplexComparatorListenerAnalyzer {

    public static Collection<String> getComplexAcsDifferentOnlyByStoichiometry(ComplexComparatorListener complexComparatorListener) {
        Collection<String> complexesDiffOnlyByStoichiometry = new ArrayList<>();
        if (complexComparatorListener != null && !complexComparatorListener.getComplexComparatorObservations().isEmpty()) {
            for (ComplexComparatorObserver complexComparatorObserver : complexComparatorListener.getComplexComparatorObservations()) {
                for (ComplexComparisonEvent complexComparisonEvent : complexComparatorObserver.getDifferentObservations()) {
                    if (ComplexComparisonEvent.EventType.ONLY_STOICHIOMETRY_DIFFERENT.equals(complexComparisonEvent.getEventType())) {
                        complexesDiffOnlyByStoichiometry.add(complexComparatorObserver.getComplex2().getComplexAc());
                        break;
                    }
                }
            }
        }
        return complexesDiffOnlyByStoichiometry;
    }

    public static Collection<String> getComplexAcsDifferentOnlyByNonProteinComponents(ComplexComparatorListener complexComparatorListener) {
        Collection<String> complexesDiffOnlyByNonProteinComponents = new ArrayList<>();
        if (complexComparatorListener != null && !complexComparatorListener.getComplexComparatorObservations().isEmpty()) {
            for (ComplexComparatorObserver complexComparatorObserver : complexComparatorListener.getComplexComparatorObservations()) {
                for (ComplexComparisonEvent complexComparisonEvent : complexComparatorObserver.getDifferentObservations()) {
                    if (ComplexComparisonEvent.EventType.ONLY_NON_PROTEINS_DIFFERENT.equals(complexComparisonEvent.getEventType())) {
                        complexesDiffOnlyByNonProteinComponents.add(complexComparatorObserver.getComplex2().getComplexAc());
                        break;
                    }
                }
            }
        }
        return complexesDiffOnlyByNonProteinComponents;
    }
}



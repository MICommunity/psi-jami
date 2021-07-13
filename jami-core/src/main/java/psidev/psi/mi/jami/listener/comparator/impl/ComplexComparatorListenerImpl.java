package psidev.psi.mi.jami.listener.comparator.impl;

import psidev.psi.mi.jami.listener.comparator.ComplexComparatorListener;
import psidev.psi.mi.jami.listener.comparator.event.ComplexComparisonEvent;
import psidev.psi.mi.jami.listener.comparator.observer.ComplexComparatorObserver;

import java.util.ArrayList;
import java.util.List;

public class ComplexComparatorListenerImpl implements ComplexComparatorListener {

    /*
     * list is needed because we want to have common listener both for single and multiple complex pair comparisons
     * for a unique listener per complex pair comparison, this list will always be of size 1
     * for a unique listener per set of multiple complex pair comparisons, this list will as big as the unique pairs
     * */
    private List<ComplexComparatorObserver> complexComparatorObservations = new ArrayList<>();

    public void onDifferentValue(ComplexComparisonEvent complexComparisonEvent) {
        ComplexComparatorObserver complexComparatorObserver = new ComplexComparatorObserver(complexComparisonEvent.getComplex1(), complexComparisonEvent.getComplex2());
        int alreadyExistingObserverIndex = complexComparatorObservations.indexOf(complexComparatorObserver);
        if (alreadyExistingObserverIndex != -1) {
            complexComparatorObservations.get(alreadyExistingObserverIndex).getDifferentObservations().add(complexComparisonEvent);
        } else {
            complexComparatorObserver.getDifferentObservations().add(complexComparisonEvent);
            complexComparatorObservations.add(complexComparatorObserver);
        }
    }

    public void onSameValue(ComplexComparisonEvent complexComparisonEvent) {
        // for future, if needed, providing the skeleton now
    }

    public List<ComplexComparatorObserver> getComplexComparatorObservations() {
        return complexComparatorObservations;
    }

    public void setComplexComparatorObservations(List<ComplexComparatorObserver> complexComparatorObservations) {
        this.complexComparatorObservations = complexComparatorObservations;
    }
}

package psidev.psi.mi.jami.listener.comparator;

import psidev.psi.mi.jami.listener.comparator.event.ComplexComparisonEvent;
import psidev.psi.mi.jami.listener.comparator.observer.ComplexComparatorObserver;

import java.util.EventListener;
import java.util.List;

public interface ComplexComparatorListener extends EventListener {

    public void onDifferentValue(ComplexComparisonEvent complexComparisonEvent);

    public void onSameValue(ComplexComparisonEvent complexComparisonEvent);

    public List<ComplexComparatorObserver> getComplexComparatorObservations();
}

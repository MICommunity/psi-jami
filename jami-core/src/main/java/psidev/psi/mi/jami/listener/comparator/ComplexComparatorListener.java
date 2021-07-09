package psidev.psi.mi.jami.listener.comparator;

import psidev.psi.mi.jami.listener.comparator.event.ComplexComparisonEvent;

import java.util.EventListener;

public interface ComplexComparatorListener extends EventListener {

    public void onDifferentValue(ComplexComparisonEvent complexComparisonEvent);

    public void onSameValue(ComplexComparisonEvent complexComparisonEvent);
}

package psidev.psi.mi.jami.listener.comparator.observer;

import psidev.psi.mi.jami.listener.comparator.event.ComplexComparisonEvent;
import psidev.psi.mi.jami.model.Complex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ComplexComparatorObserver {

    private Complex complex1;
    private Complex complex2;
    private Collection<ComplexComparisonEvent> sameObservations = new ArrayList<>();
    private Collection<ComplexComparisonEvent> differentObservations = new ArrayList<>();

    public ComplexComparatorObserver(Complex complex1, Complex complex2) {
        this.complex1 = complex1;
        this.complex2 = complex2;
    }

    public Complex getComplex1() {
        return complex1;
    }

    public void setComplex1(Complex complex1) {
        this.complex1 = complex1;
    }

    public Complex getComplex2() {
        return complex2;
    }

    public void setComplex2(Complex complex2) {
        this.complex2 = complex2;
    }

    public Collection<ComplexComparisonEvent> getSameObservations() {
        return sameObservations;
    }

    public void setSameObservations(Collection<ComplexComparisonEvent> sameObservations) {
        this.sameObservations = sameObservations;
    }

    public Collection<ComplexComparisonEvent> getDifferentObservations() {
        return differentObservations;
    }

    public void setDifferentObservations(Collection<ComplexComparisonEvent> differentObservations) {
        this.differentObservations = differentObservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexComparatorObserver that = (ComplexComparatorObserver) o;
        return Objects.equals(complex1, that.complex1) &&
                Objects.equals(complex2, that.complex2);

    }

    @Override
    public int hashCode() {
        return Objects.hash(complex1, complex2);
    }
}

package psidev.psi.mi.jami.listener.comparator.event;

import psidev.psi.mi.jami.model.Complex;

public class ComplexComparisonEvent {
    private Complex complex1;
    private Complex complex2;
    private EventType eventType;
    private String message;

    public ComplexComparisonEvent(Complex complex1, Complex complex2, EventType eventType) {
        this.complex1 = complex1;
        this.complex2 = complex2;
        this.eventType = eventType;
        this.message = eventType.getMessage();
    }

    public Complex getComplex1() {
        return complex1;
    }

    public Complex getComplex2() {
        return complex2;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getMessage() {
        return message;
    }

    public enum EventType {
        ONLY_STOICHIOMETRY_DIFFERENT("Only Stoichiometry is different");

        private String message;

        EventType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}

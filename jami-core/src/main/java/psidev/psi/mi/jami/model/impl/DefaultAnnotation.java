package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.utils.comparator.annotation.UnambiguousAnnotationComparator;

/**
 * Default implementation for Annotation.
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with UnambiguousAnnotationComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/01/13</pre>
 */
public class DefaultAnnotation implements Annotation {

    private CvTerm topic;
    private String value;

    /**
     * <p>Constructor for DefaultAnnotation.</p>
     *
     * @param topic a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultAnnotation(CvTerm topic){
        if (topic == null){
            throw new IllegalArgumentException("The annotation topic is required and cannot be null");
        }
        this.topic = topic;
    }

    /**
     * <p>Constructor for DefaultAnnotation.</p>
     *
     * @param topic a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     */
    public DefaultAnnotation(CvTerm topic, String value){
        this(topic);
        this.value = value;
    }

    /**
     * <p>Getter for the field <code>topic</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getTopic() {
        return this.topic;
    }

    /**
     * <p>Getter for the field <code>value</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getValue() {
        return this.value;
    }

    /** {@inheritDoc} */
    public void setValue(String value) {
        this.value = value;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousAnnotationComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof Annotation)){
            return false;
        }

        return UnambiguousAnnotationComparator.areEquals(this, (Annotation) o);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getTopic().toString()+(getValue() != null ? ": " + getValue() : "");
    }
}

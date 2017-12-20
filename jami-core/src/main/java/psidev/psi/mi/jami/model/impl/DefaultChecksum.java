package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.Checksum;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.utils.comparator.checksum.UnambiguousChecksumComparator;

/**
 * Default implementation for Checksum
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with UnambiguousChecksumComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/01/13</pre>
 */
public class DefaultChecksum implements Checksum {

    private CvTerm method;
    private String value;

    /**
     * <p>Constructor for DefaultChecksum.</p>
     *
     * @param method a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param value a {@link java.lang.String} object.
     */
    public DefaultChecksum(CvTerm method, String value){
        if (method == null){
            throw new IllegalArgumentException("The method is required and cannot be null");
        }
        this.method = method;
        if (value == null){
            throw new IllegalArgumentException("The checksum value is required and cannot be null");
        }
        this.value = value;
    }

    /**
     * <p>Getter for the field <code>method</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getMethod() {
        return this.method;
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
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof Checksum)){
            return false;
        }

        return UnambiguousChecksumComparator.areEquals(this, (Checksum) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousChecksumComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getMethod().toString() + ": " + getValue();
    }
}

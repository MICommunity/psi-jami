package psidev.psi.mi.jami.xml.model.reference;

import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.extension.AbstractAvailability;

/**
 * Abstract class for Availability ref
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
public abstract class AbstractAvailabilityRef extends AbstractAvailability implements XmlIdReference{
    protected int ref;

    /**
     * <p>Constructor for AbstractAvailabilityRef.</p>
     *
     * @param ref a int.
     */
    public AbstractAvailabilityRef(int ref) {
        this.ref = ref;
        registerForResolution();
    }

    /**
     * <p>registerForResolution.</p>
     */
    public void registerForResolution() {
        XmlEntryContext.getInstance().registerReference(this);
    }

    /**
     * <p>Getter for the field <code>ref</code>.</p>
     *
     * @return a int.
     */
    public int getRef() {
        return ref;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Availability Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
    }
}

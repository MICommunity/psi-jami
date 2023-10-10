package psidev.psi.mi.jami.xml.model.reference.xml300;

import javax.xml.bind.annotation.XmlAttribute;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.extension.AbstractAvailability;
import psidev.psi.mi.jami.xml.model.reference.XmlIdReference;

/**
 * Abstract class for Availability ref
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
public abstract class AbstractAvailabilityRef extends AbstractAvailability implements XmlIdReference {
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

    /**
     * Sets the value of the id property.
     *
     * @param value a int.
     */
    @XmlAttribute(name = "id", required = true)
    public void setId(int value) {
        super.setId(value);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Availability Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
    }
}

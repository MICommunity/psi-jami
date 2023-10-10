package psidev.psi.mi.jami.xml.model.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import psidev.psi.mi.jami.xml.model.AbstractEntrySet;

import java.util.List;

/**
 * Modelled EntrySet implementation for JAXB read only
 *
 * Ignore all experimental details
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>07/11/13</pre>
 */
@XmlRootElement(namespace = "http://psi.hupo.org/mi/mif", name = "entrySet")
@XmlAccessorType(XmlAccessType.NONE)
public class Xml254ComplexEntrySet extends AbstractEntrySet<ComplexEntry> {

    /**
     * <p>getEntries.</p>
     *
     * @return a {@link java.util.List} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", type=ComplexEntry.class, name="entry", required = true)
    public List<ComplexEntry> getEntries() {
        return super.getEntries();
    }

}

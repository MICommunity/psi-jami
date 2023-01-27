package psidev.psi.mi.jami.xml.model.xml30;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import psidev.psi.mi.jami.xml.model.AbstractEntrySet;

import java.util.List;

/**
 * Xml300EntrySet implementation for JAXB read only
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>07/11/13</pre>
 */
@XmlRootElement(namespace = "http://psi.hupo.org/mi/mif300", name = "entrySet")
@XmlAccessorType(XmlAccessType.NONE)
public class Xml300EntrySet extends AbstractEntrySet<Entry> {

    /**
     * <p>getEntries.</p>
     *
     * @return a {@link java.util.List} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type=Entry.class, name="entry", required = true)
    public List<Entry> getEntries() {
        return super.getEntries();
    }
}

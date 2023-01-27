package psidev.psi.mi.jami.xml.model.xml253;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import psidev.psi.mi.jami.xml.model.AbstractEntrySet;

import java.util.List;

/**
 * Xml254ExperimentalEntrySet implementation for JAXB read only
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>07/11/13</pre>
 */
@XmlRootElement(namespace = "net:sf:psidev:mi", name = "entrySet")
@XmlAccessorType(XmlAccessType.NONE)
public class Xml253ExperimentalEntrySet extends AbstractEntrySet<ExperimentalEntry> {

    /**
     * <p>getEntries.</p>
     *
     * @return a {@link java.util.List} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", type=ExperimentalEntry.class, name="entry", required = true)
    public List<ExperimentalEntry> getEntries() {
        return super.getEntries();
    }
}

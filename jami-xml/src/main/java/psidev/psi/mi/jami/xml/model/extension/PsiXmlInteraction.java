package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.NamedInteraction;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.xml.model.Entry;

import java.util.Collection;

/**
 * Interaction for PSI-XML which contains the entry
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/10/13</pre>
 */
public interface PsiXmlInteraction<T extends Participant> extends NamedInteraction<T>, FileSourceContext {

    /**
     * <p>getEntry.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.Entry} object.
     */
    public Entry getEntry();
    /**
     * <p>setEntry.</p>
     *
     * @param entry a {@link psidev.psi.mi.jami.xml.model.Entry} object.
     */
    public void setEntry(Entry entry);
    /**
     * <p>getId.</p>
     *
     * @return a int.
     */
    public int getId();
    /**
     * <p>setId.</p>
     *
     * @param id a int.
     */
    public void setId(int id);
    /**
     * <p>isIntraMolecular.</p>
     *
     * @return a boolean.
     */
    public boolean isIntraMolecular();
    /**
     * <p>setIntraMolecular.</p>
     *
     * @param intra a boolean.
     */
    public void setIntraMolecular(boolean intra);
    /**
     * <p>getAliases.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Alias> getAliases();
}

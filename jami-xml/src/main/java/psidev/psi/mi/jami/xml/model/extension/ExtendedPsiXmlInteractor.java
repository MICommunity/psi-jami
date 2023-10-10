package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.model.Interactor;

/**
 * Interface for interactors in PSI-XML standards
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/10/13</pre>
 */
public interface ExtendedPsiXmlInteractor extends Interactor{
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
}

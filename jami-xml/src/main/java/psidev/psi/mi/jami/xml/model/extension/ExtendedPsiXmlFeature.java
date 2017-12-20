package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;

/**
 * Interface for psi25 xml features
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/10/13</pre>
 */
public interface ExtendedPsiXmlFeature<P extends Entity, F extends Feature> extends Feature<P,F> {
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

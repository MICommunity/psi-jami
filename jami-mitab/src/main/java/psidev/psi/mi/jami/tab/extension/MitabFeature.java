package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;

/**
 * Basic interface for Mitab features
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/07/13</pre>
 */
public interface MitabFeature<P extends Entity, F extends Feature> extends Feature<P,F>, FileSourceContext {

    /**
     * <p>getText.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getText();

    /**
     * <p>setText.</p>
     *
     * @param text a {@link java.lang.String} object.
     */
    public void setText(String text);
}

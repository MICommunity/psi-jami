package psidev.psi.mi.jami.json.elements;

import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Range;

import java.io.IOException;

/**
 * JSON writer for Range objects
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/14</pre>
 */
public interface JsonRangeWriter extends JsonElementWriter<Range>{

    /**
     * <p>write.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Range} object.
     * @param parent a {@link psidev.psi.mi.jami.model.Feature} object.
     * @throws java.io.IOException if any.
     */
    public void write(Range object, Feature parent) throws IOException;
}

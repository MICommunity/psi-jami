package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Participant;

import java.util.Collection;
import java.util.List;

/**
 * Extended interaction for PSI-XML 2,5 standards
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/10/13</pre>
 */
public interface ExtendedPsiXmlInteraction<T extends Participant> extends PsiXmlInteraction<T> {

    /**
     * <p>getInteractionTypes.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<CvTerm> getInteractionTypes();
    /**
     * <p>getInferredInteractions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<InferredInteraction> getInferredInteractions();
    /**
     * <p>getAliases.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Alias> getAliases();
}

package psidev.psi.mi.jami.xml.io.writer.elements;

import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Interaction;

import java.util.List;

/**
 * Interface for interaction writers writing extensions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/11/13</pre>
 */
public interface PsiXmlExtendedInteractionWriter<T extends Interaction> extends PsiXmlInteractionWriter<T> {

    /**
     * <p>extractDefaultExperimentsFrom.</p>
     *
     * @param interaction a T object.
     * @return the default experiments for this interaction
     */
    public List<Experiment> extractDefaultExperimentsFrom(T interaction);

    /**
     * The default experiments that will be used to write a valid XML file but are not real experiments attached
     * to the interaction.
     * It cannot be null.
     *
     * @return a {@link java.util.List} object.
     */
    public List<Experiment> getDefaultExperiments();

    /**
     * Sets the default experiments that will be used to write a valid XML file even if the interaction does not have any valid experiment
     *
     * @param exp a {@link java.util.List} object.
     * @throws java.lang.IllegalArgumentException when default experiments is null
     */
    public void setDefaultExperiments(List<Experiment> exp);
}

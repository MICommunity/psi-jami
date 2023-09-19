package psidev.psi.mi.jami.xml.io.writer.elements;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Interaction;

/**
 * Interface for PSI-XML interaction writers that have to write an experiment in XML even if the interaction
 * does not have any experimental details
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public interface PsiXmlInteractionWriter<T extends Interaction> extends PsiXmlElementWriter<T> {

    /**
     * The default experiment that will be used to write a valid XML file but is not a real experiment attached
     * to the interaction.
     * It can be null in some specific cases.
     *
     * @return the default experiment used by this writer
     */
    public Experiment getDefaultExperiment();

    /**
     * Sets the default experiment that will be used to write a valid XML file even if the interaction does not have any valid experiment
     *
     * @param exp : default experiment
     */
    public void setDefaultExperiment(Experiment exp);

    /**
     * <p>extractDefaultExperimentFrom.</p>
     *
     * @param interaction a T object.
     * @return the default experiment for this interaction
     */
    public Experiment extractDefaultExperimentFrom(T interaction);

    /**
     * <p>writeComplexAsInteractor.</p>
     *
     * @return true if the participant writer will write a complex as an interactor
     */
    public boolean writeComplexAsInteractor();

    /**
     * Sets the property of the writer to write a complex as an interactor or as an interaction
     *
     * @param complexAsInteractor a boolean.
     */
    public void setComplexAsInteractor(boolean complexAsInteractor);

    /**
     * <p>write.</p>
     *
     * @param object a T object.
     * @param miScore : the MI score of the interaction to write
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(T object, Double miScore) throws MIIOException;
}

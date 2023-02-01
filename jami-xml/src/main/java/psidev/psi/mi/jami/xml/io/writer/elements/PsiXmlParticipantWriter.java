package psidev.psi.mi.jami.xml.io.writer.elements;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Participant;

/**
 * Interface for PSI-XML participant writers.
 *
 * A participant writer has an option to write a complex as an interactor or as an interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public interface PsiXmlParticipantWriter<T extends Participant> extends PsiXmlElementWriter<T> {

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
     * <p>writeParticipant.</p>
     *
     * @param participant : participant to write
     * @param generalParticipantDetectionMethod : the experiment participant identification method which can be overridden by
     *                                          the participant
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void writeParticipant(T participant, CvTerm generalParticipantDetectionMethod) throws MIIOException;
}
